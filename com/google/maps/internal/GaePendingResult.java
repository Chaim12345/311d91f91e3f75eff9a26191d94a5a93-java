package com.google.maps.internal;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.maps.GeolocationApi;
import com.google.maps.ImageResult;
import com.google.maps.PendingResult;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.UnknownErrorException;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.AddressType;
import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.Fare;
import com.google.maps.model.LatLng;
import com.google.maps.model.LocationType;
import com.google.maps.model.OpeningHours;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PriceLevel;
import com.google.maps.model.TravelMode;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes2.dex */
public class GaePendingResult<T, R extends ApiResponse<T>> implements PendingResult<T> {
    private static final Logger LOG = LoggerFactory.getLogger(GaePendingResult.class.getName());
    private static final List<Integer> RETRY_ERROR_CODES = Arrays.asList(500, 503, 504);
    private Future<HTTPResponse> call;
    private final URLFetchService client;
    private long errorTimeOut;
    private final ExceptionsAllowedToRetry exceptionsAllowedToRetry;
    private final FieldNamingPolicy fieldNamingPolicy;
    private final Integer maxRetries;
    private final HTTPRequest request;
    private final Class<R> responseClass;
    private int retryCounter = 0;
    private long cumulativeSleepTime = 0;

    public GaePendingResult(HTTPRequest hTTPRequest, URLFetchService uRLFetchService, Class<R> cls, FieldNamingPolicy fieldNamingPolicy, long j2, Integer num, ExceptionsAllowedToRetry exceptionsAllowedToRetry) {
        this.request = hTTPRequest;
        this.client = uRLFetchService;
        this.responseClass = cls;
        this.fieldNamingPolicy = fieldNamingPolicy;
        this.errorTimeOut = j2;
        this.maxRetries = num;
        this.exceptionsAllowedToRetry = exceptionsAllowedToRetry;
        this.call = uRLFetchService.fetchAsync(hTTPRequest);
    }

    private T parseResponse(GaePendingResult<T, R> gaePendingResult, HTTPResponse hTTPResponse) {
        if (shouldRetry(hTTPResponse)) {
            return gaePendingResult.retry();
        }
        byte[] content = hTTPResponse.getContent();
        String str = null;
        for (HTTPHeader hTTPHeader : hTTPResponse.getHeaders()) {
            if (hTTPHeader.getName().equalsIgnoreCase("Content-Type")) {
                str = hTTPHeader.getValue();
            }
        }
        if (str != null && str.startsWith("image") && this.responseClass == ImageResult.Response.class && hTTPResponse.getResponseCode() == 200) {
            return (T) new ImageResult(str, content);
        }
        try {
            ApiResponse apiResponse = (ApiResponse) new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter()).registerTypeAdapter(Distance.class, new DistanceAdapter()).registerTypeAdapter(Duration.class, new DurationAdapter()).registerTypeAdapter(Fare.class, new FareAdapter()).registerTypeAdapter(LatLng.class, new LatLngAdapter()).registerTypeAdapter(AddressComponentType.class, new SafeEnumAdapter(AddressComponentType.UNKNOWN)).registerTypeAdapter(AddressType.class, new SafeEnumAdapter(AddressType.UNKNOWN)).registerTypeAdapter(TravelMode.class, new SafeEnumAdapter(TravelMode.UNKNOWN)).registerTypeAdapter(LocationType.class, new SafeEnumAdapter(LocationType.UNKNOWN)).registerTypeAdapter(PlaceDetails.Review.AspectRating.RatingType.class, new SafeEnumAdapter(PlaceDetails.Review.AspectRating.RatingType.UNKNOWN)).registerTypeAdapter(OpeningHours.Period.OpenClose.DayOfWeek.class, new DayOfWeekAdapter()).registerTypeAdapter(PriceLevel.class, new PriceLevelAdapter()).registerTypeAdapter(Instant.class, new InstantAdapter()).registerTypeAdapter(LocalTime.class, new LocalTimeAdapter()).registerTypeAdapter(GeolocationApi.Response.class, new GeolocationResponseAdapter()).registerTypeAdapter(EncodedPolyline.class, new EncodedPolylineInstanceCreator("")).setFieldNamingPolicy(this.fieldNamingPolicy).create().fromJson(new String(content, "utf8"), (Class<Object>) this.responseClass);
            if (apiResponse.successful()) {
                return (T) apiResponse.getResult();
            }
            ApiException error = apiResponse.getError();
            if (shouldRetry(error)) {
                return gaePendingResult.retry();
            }
            throw error;
        } catch (JsonSyntaxException e2) {
            if (hTTPResponse.getResponseCode() > 399) {
                throw new IOException(String.format("Server Error: %d %s", Integer.valueOf(hTTPResponse.getResponseCode()), new String(hTTPResponse.getContent(), Charset.defaultCharset())));
            }
            throw e2;
        }
    }

    private T retry() {
        int i2 = this.retryCounter + 1;
        this.retryCounter = i2;
        LOG.info("Retrying request. Retry #{}", Integer.valueOf(i2));
        this.call = this.client.fetchAsync(this.request);
        return await();
    }

    private boolean shouldRetry(HTTPResponse hTTPResponse) {
        Integer num;
        return RETRY_ERROR_CODES.contains(Integer.valueOf(hTTPResponse.getResponseCode())) && this.cumulativeSleepTime < this.errorTimeOut && ((num = this.maxRetries) == null || this.retryCounter < num.intValue());
    }

    private boolean shouldRetry(ApiException apiException) {
        Integer num;
        return this.exceptionsAllowedToRetry.contains(apiException.getClass()) && this.cumulativeSleepTime < this.errorTimeOut && ((num = this.maxRetries) == null || this.retryCounter < num.intValue());
    }

    @Override // com.google.maps.PendingResult
    public T await() {
        try {
            return parseResponse(this, this.call.get());
        } catch (ExecutionException e2) {
            if (e2.getCause() instanceof IOException) {
                throw ((IOException) e2.getCause());
            }
            throw new UnknownErrorException("Unexpected exception from " + e2.getMessage());
        }
    }

    @Override // com.google.maps.PendingResult
    public T awaitIgnoreError() {
        try {
            return await();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.google.maps.PendingResult
    public void cancel() {
        this.call.cancel(true);
    }

    @Override // com.google.maps.PendingResult
    public void setCallback(PendingResult.Callback<T> callback) {
        throw new RuntimeException("setCallback not implemented for Google App Engine");
    }
}
