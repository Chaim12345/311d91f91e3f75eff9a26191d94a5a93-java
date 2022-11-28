package com.google.maps;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.FieldNamingPolicy;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.PriceLevel;
import com.google.maps.model.RankBy;
import com.psa.mym.mycitroenconnect.common.AppConstants;
/* loaded from: classes2.dex */
public class TextSearchRequest extends PendingResultBase<PlacesSearchResponse, TextSearchRequest, Response> {

    /* renamed from: a  reason: collision with root package name */
    static final ApiConfig f10282a = new ApiConfig("/maps/api/place/textsearch/json").fieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

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

    public TextSearchRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, f10282a, Response.class);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.TextSearchRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ TextSearchRequest channel(String str) {
        return super.channel(str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.TextSearchRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ TextSearchRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        if (f().containsKey("pagetoken")) {
            return;
        }
        if (!f().containsKey(SearchIntents.EXTRA_QUERY) && !f().containsKey("type")) {
            throw new IllegalArgumentException("Request must contain 'query' or a 'pageToken'. If a 'type' is specified 'query' becomes optional.");
        }
        if (f().containsKey("location") && !f().containsKey(AppConstants.GEO_FENCE_MODE_RADIUS)) {
            throw new IllegalArgumentException("Request must contain 'radius' parameter when it contains a 'location' parameter.");
        }
    }

    public TextSearchRequest location(LatLng latLng) {
        return (TextSearchRequest) b("location", latLng);
    }

    public TextSearchRequest maxPrice(PriceLevel priceLevel) {
        return (TextSearchRequest) b("maxprice", priceLevel);
    }

    public TextSearchRequest minPrice(PriceLevel priceLevel) {
        return (TextSearchRequest) b("minprice", priceLevel);
    }

    public TextSearchRequest name(String str) {
        return (TextSearchRequest) c(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
    }

    public TextSearchRequest openNow(boolean z) {
        return (TextSearchRequest) c("opennow", String.valueOf(z));
    }

    public TextSearchRequest pageToken(String str) {
        return (TextSearchRequest) c("pagetoken", str);
    }

    public TextSearchRequest query(String str) {
        return (TextSearchRequest) c(SearchIntents.EXTRA_QUERY, str);
    }

    public TextSearchRequest radius(int i2) {
        if (i2 <= 50000) {
            return (TextSearchRequest) c(AppConstants.GEO_FENCE_MODE_RADIUS, String.valueOf(i2));
        }
        throw new IllegalArgumentException("The maximum allowed radius is 50,000 meters.");
    }

    public TextSearchRequest rankby(RankBy rankBy) {
        return (TextSearchRequest) b("rankby", rankBy);
    }

    public TextSearchRequest region(String str) {
        return (TextSearchRequest) c("region", str);
    }

    public TextSearchRequest type(PlaceType placeType) {
        return (TextSearchRequest) b("type", placeType);
    }
}
