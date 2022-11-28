package com.google.maps;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.FieldNamingPolicy;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.internal.StringJoin;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.PriceLevel;
import com.google.maps.model.RankBy;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.List;
/* loaded from: classes2.dex */
public class NearbySearchRequest extends PendingResultBase<PlacesSearchResponse, NearbySearchRequest, Response> {

    /* renamed from: a  reason: collision with root package name */
    static final ApiConfig f10271a = new ApiConfig("/maps/api/place/nearbysearch/json").fieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

    /* loaded from: classes2.dex */
    public static class Response implements ApiResponse<PlacesSearchResponse> {
        public String errorMessage;
        public String[] htmlAttributions;
        public String nextPageToken;
        public PlacesSearchResult[] results;
        public String status;

        @Override // com.google.maps.internal.ApiResponse
        public ApiException getError() {
            if (successful()) {
                return null;
            }
            return ApiException.from(this.status, this.errorMessage);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.maps.internal.ApiResponse
        public PlacesSearchResponse getResult() {
            PlacesSearchResponse placesSearchResponse = new PlacesSearchResponse();
            placesSearchResponse.htmlAttributions = this.htmlAttributions;
            placesSearchResponse.results = this.results;
            placesSearchResponse.nextPageToken = this.nextPageToken;
            return placesSearchResponse;
        }

        @Override // com.google.maps.internal.ApiResponse
        public boolean successful() {
            return "OK".equals(this.status) || "ZERO_RESULTS".equals(this.status);
        }
    }

    public NearbySearchRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, f10271a, Response.class);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PendingResultBase, com.google.maps.NearbySearchRequest] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ NearbySearchRequest channel(String str) {
        return super.channel(str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PendingResultBase, com.google.maps.NearbySearchRequest] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ NearbySearchRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        if (f().containsKey("pagetoken")) {
            return;
        }
        if (f().containsKey("rankby") && ((String) ((List) f().get("rankby")).get(0)).equals(RankBy.DISTANCE.toString()) && f().containsKey(AppConstants.GEO_FENCE_MODE_RADIUS)) {
            throw new IllegalArgumentException("Request must not contain radius with rankby=distance");
        }
        if (f().containsKey("rankby") && ((String) ((List) f().get("rankby")).get(0)).equals(RankBy.DISTANCE.toString()) && !f().containsKey("keyword") && !f().containsKey(AppMeasurementSdk.ConditionalUserProperty.NAME) && !f().containsKey("type")) {
            throw new IllegalArgumentException("With rankby=distance is specified, then one or more of keyword, name, or type is required");
        }
    }

    public NearbySearchRequest keyword(String str) {
        return (NearbySearchRequest) c("keyword", str);
    }

    public NearbySearchRequest location(LatLng latLng) {
        return (NearbySearchRequest) b("location", latLng);
    }

    public NearbySearchRequest maxPrice(PriceLevel priceLevel) {
        return (NearbySearchRequest) b("maxprice", priceLevel);
    }

    public NearbySearchRequest minPrice(PriceLevel priceLevel) {
        return (NearbySearchRequest) b("minprice", priceLevel);
    }

    public NearbySearchRequest name(String str) {
        return (NearbySearchRequest) c(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
    }

    public NearbySearchRequest openNow(boolean z) {
        return (NearbySearchRequest) c("opennow", String.valueOf(z));
    }

    public NearbySearchRequest pageToken(String str) {
        return (NearbySearchRequest) c("pagetoken", str);
    }

    public NearbySearchRequest radius(int i2) {
        if (i2 <= 50000) {
            return (NearbySearchRequest) c(AppConstants.GEO_FENCE_MODE_RADIUS, String.valueOf(i2));
        }
        throw new IllegalArgumentException("The maximum allowed radius is 50,000 meters.");
    }

    public NearbySearchRequest rankby(RankBy rankBy) {
        return (NearbySearchRequest) b("rankby", rankBy);
    }

    public NearbySearchRequest type(PlaceType placeType) {
        return (NearbySearchRequest) b("type", placeType);
    }

    @Deprecated
    public NearbySearchRequest type(PlaceType... placeTypeArr) {
        return (NearbySearchRequest) c("type", StringJoin.join('|', (StringJoin.UrlValue[]) placeTypeArr));
    }
}
