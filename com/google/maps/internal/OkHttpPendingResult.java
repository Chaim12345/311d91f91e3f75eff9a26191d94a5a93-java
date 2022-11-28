package com.google.maps.internal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.maps.GeolocationApi;
import com.google.maps.ImageResult;
import com.google.maps.PendingResult;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.AddressType;
import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.Fare;
import com.google.maps.model.LatLng;
import com.google.maps.model.LocationType;
import com.google.maps.model.OpeningHours;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PriceLevel;
import com.google.maps.model.TravelMode;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes2.dex */
public class OkHttpPendingResult<T, R extends ApiResponse<T>> implements PendingResult<T>, Callback {
    private static final Logger LOG = LoggerFactory.getLogger(OkHttpPendingResult.class.getName());
    private static final List<Integer> RETRY_ERROR_CODES = Arrays.asList(500, 503, 504);
    private Call call;
    private PendingResult.Callback<T> callback;
    private final OkHttpClient client;
    private long errorTimeOut;
    private ExceptionsAllowedToRetry exceptionsAllowedToRetry;
    private final FieldNamingPolicy fieldNamingPolicy;
    private final Integer maxRetries;
    private final Request request;
    private final Class<R> responseClass;
    private int retryCounter = 0;
    private long cumulativeSleepTime = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class QueuedResponse {

        /* renamed from: e  reason: collision with root package name */
        private final IOException f10328e;
        private final OkHttpPendingResult<T, R> request;
        private final Response response;

        public QueuedResponse(OkHttpPendingResult okHttpPendingResult, OkHttpPendingResult<T, R> okHttpPendingResult2, IOException iOException) {
            this.request = okHttpPendingResult2;
            this.response = null;
            this.f10328e = iOException;
        }

        public QueuedResponse(OkHttpPendingResult okHttpPendingResult, OkHttpPendingResult<T, R> okHttpPendingResult2, Response response) {
            this.request = okHttpPendingResult2;
            this.response = response;
            this.f10328e = null;
        }
    }

    public OkHttpPendingResult(Request request, OkHttpClient okHttpClient, Class<R> cls, FieldNamingPolicy fieldNamingPolicy, long j2, Integer num, ExceptionsAllowedToRetry exceptionsAllowedToRetry) {
        this.request = request;
        this.client = okHttpClient;
        this.responseClass = cls;
        this.fieldNamingPolicy = fieldNamingPolicy;
        this.errorTimeOut = j2;
        this.maxRetries = num;
        this.exceptionsAllowedToRetry = exceptionsAllowedToRetry;
        this.call = okHttpClient.newCall(request);
    }

    private T parseResponse(OkHttpPendingResult<T, R> okHttpPendingResult, Response response) {
        if (shouldRetry(response)) {
            response.close();
            return okHttpPendingResult.retry();
        }
        ResponseBody body = response.body();
        try {
            byte[] bytes = body.bytes();
            body.close();
            String header = response.header("Content-Type");
            if (header != null && header.startsWith("image") && this.responseClass == ImageResult.Response.class && response.code() == 200) {
                return (T) new ImageResult(header, bytes);
            }
            try {
                ApiResponse apiResponse = (ApiResponse) new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter()).registerTypeAdapter(Distance.class, new DistanceAdapter()).registerTypeAdapter(Duration.class, new DurationAdapter()).registerTypeAdapter(Fare.class, new FareAdapter()).registerTypeAdapter(LatLng.class, new LatLngAdapter()).registerTypeAdapter(AddressComponentType.class, new SafeEnumAdapter(AddressComponentType.UNKNOWN)).registerTypeAdapter(AddressType.class, new SafeEnumAdapter(AddressType.UNKNOWN)).registerTypeAdapter(TravelMode.class, new SafeEnumAdapter(TravelMode.UNKNOWN)).registerTypeAdapter(LocationType.class, new SafeEnumAdapter(LocationType.UNKNOWN)).registerTypeAdapter(PlaceDetails.Review.AspectRating.RatingType.class, new SafeEnumAdapter(PlaceDetails.Review.AspectRating.RatingType.UNKNOWN)).registerTypeAdapter(OpeningHours.Period.OpenClose.DayOfWeek.class, new DayOfWeekAdapter()).registerTypeAdapter(PriceLevel.class, new PriceLevelAdapter()).registerTypeAdapter(Instant.class, new InstantAdapter()).registerTypeAdapter(LocalTime.class, new LocalTimeAdapter()).registerTypeAdapter(GeolocationApi.Response.class, new GeolocationResponseAdapter()).setFieldNamingPolicy(this.fieldNamingPolicy).create().fromJson(new String(bytes, "utf8"), (Class<Object>) this.responseClass);
                if (apiResponse.successful()) {
                    return (T) apiResponse.getResult();
                }
                ApiException error = apiResponse.getError();
                if (shouldRetry(error)) {
                    return okHttpPendingResult.retry();
                }
                throw error;
            } catch (JsonSyntaxException e2) {
                if (response.isSuccessful()) {
                    throw e2;
                }
                throw new IOException(String.format("Server Error: %d %s", Integer.valueOf(response.code()), response.message()));
            }
        } catch (Throwable th) {
            if (body != null) {
                try {
                    body.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private T retry() {
        this.retryCounter++;
        LOG.info("Retrying request. Retry #" + this.retryCounter);
        this.call = this.client.newCall(this.request);
        return await();
    }

    private boolean shouldRetry(ApiException apiException) {
        Integer num;
        return this.exceptionsAllowedToRetry.contains(apiException.getClass()) && this.cumulativeSleepTime < this.errorTimeOut && ((num = this.maxRetries) == null || this.retryCounter < num.intValue());
    }

    private boolean shouldRetry(Response response) {
        Integer num;
        return RETRY_ERROR_CODES.contains(Integer.valueOf(response.code())) && this.cumulativeSleepTime < this.errorTimeOut && ((num = this.maxRetries) == null || this.retryCounter < num.intValue());
    }

    @Override // com.google.maps.PendingResult
    public T await() {
        int i2;
        if (this.retryCounter > 0) {
            long pow = (long) (Math.pow(1.5d, i2 - 1) * 0.5d * (Math.random() + 0.5d) * 1000.0d);
            LOG.debug(String.format("Sleeping between errors for %dms (retry #%d, already slept %dms)", Long.valueOf(pow), Integer.valueOf(this.retryCounter), Long.valueOf(this.cumulativeSleepTime)));
            this.cumulativeSleepTime += pow;
            try {
                Thread.sleep(pow);
            } catch (InterruptedException unused) {
            }
        }
        final ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
        this.call.enqueue(new Callback() { // from class: com.google.maps.internal.OkHttpPendingResult.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                arrayBlockingQueue.add(new QueuedResponse(OkHttpPendingResult.this, this, iOException));
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                arrayBlockingQueue.add(new QueuedResponse(OkHttpPendingResult.this, this, response));
            }
        });
        QueuedResponse queuedResponse = (QueuedResponse) arrayBlockingQueue.take();
        if (queuedResponse.response != null) {
            return parseResponse(queuedResponse.request, queuedResponse.response);
        }
        throw queuedResponse.f10328e;
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
        this.call.cancel();
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException iOException) {
        PendingResult.Callback<T> callback = this.callback;
        if (callback != null) {
            callback.onFailure(iOException);
        }
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        PendingResult.Callback<T> callback = this.callback;
        if (callback != null) {
            try {
                callback.onResult(parseResponse(this, response));
            } catch (Exception e2) {
                this.callback.onFailure(e2);
            }
        }
    }

    @Override // com.google.maps.PendingResult
    public void setCallback(PendingResult.Callback<T> callback) {
        this.callback = callback;
        this.call.enqueue(this);
    }
}
