package com.google.maps;

import com.google.gson.FieldNamingPolicy;
import com.google.maps.errors.ApiError;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.internal.StringJoin;
import com.google.maps.model.LatLng;
import com.google.maps.model.SnappedPoint;
import com.google.maps.model.SnappedSpeedLimitResponse;
import com.google.maps.model.SpeedLimit;
import org.apache.http.cookie.ClientCookie;
/* loaded from: classes2.dex */
public class RoadsApi {

    /* renamed from: a  reason: collision with root package name */
    static final ApiConfig f10278a;

    /* renamed from: b  reason: collision with root package name */
    static final ApiConfig f10279b;

    /* renamed from: c  reason: collision with root package name */
    static final ApiConfig f10280c;

    /* loaded from: classes2.dex */
    public static class CombinedResponse implements ApiResponse<SnappedSpeedLimitResponse> {
        private ApiError error;
        private SnappedPoint[] snappedPoints;
        private SpeedLimit[] speedLimits;

        @Override // com.google.maps.internal.ApiResponse
        public ApiException getError() {
            ApiError apiError = this.error;
            return ApiException.from(apiError.status, apiError.message);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.maps.internal.ApiResponse
        public SnappedSpeedLimitResponse getResult() {
            SnappedSpeedLimitResponse snappedSpeedLimitResponse = new SnappedSpeedLimitResponse();
            snappedSpeedLimitResponse.snappedPoints = this.snappedPoints;
            snappedSpeedLimitResponse.speedLimits = this.speedLimits;
            return snappedSpeedLimitResponse;
        }

        @Override // com.google.maps.internal.ApiResponse
        public boolean successful() {
            return this.error == null;
        }
    }

    /* loaded from: classes2.dex */
    public static class RoadsResponse implements ApiResponse<SnappedPoint[]> {
        private ApiError error;
        private SnappedPoint[] snappedPoints;

        @Override // com.google.maps.internal.ApiResponse
        public ApiException getError() {
            ApiError apiError = this.error;
            return ApiException.from(apiError.status, apiError.message);
        }

        @Override // com.google.maps.internal.ApiResponse
        public SnappedPoint[] getResult() {
            return this.snappedPoints;
        }

        @Override // com.google.maps.internal.ApiResponse
        public boolean successful() {
            return this.error == null;
        }
    }

    /* loaded from: classes2.dex */
    public static class SpeedsResponse implements ApiResponse<SpeedLimit[]> {
        private ApiError error;
        private SpeedLimit[] speedLimits;

        @Override // com.google.maps.internal.ApiResponse
        public ApiException getError() {
            ApiError apiError = this.error;
            return ApiException.from(apiError.status, apiError.message);
        }

        @Override // com.google.maps.internal.ApiResponse
        public SpeedLimit[] getResult() {
            return this.speedLimits;
        }

        @Override // com.google.maps.internal.ApiResponse
        public boolean successful() {
            return this.error == null;
        }
    }

    static {
        ApiConfig supportsClientId = new ApiConfig("/v1/snapToRoads").hostName("https://roads.googleapis.com").supportsClientId(false);
        FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
        f10278a = supportsClientId.fieldNamingPolicy(fieldNamingPolicy);
        f10279b = new ApiConfig("/v1/speedLimits").hostName("https://roads.googleapis.com").supportsClientId(false).fieldNamingPolicy(fieldNamingPolicy);
        f10280c = new ApiConfig("/v1/nearestRoads").hostName("https://roads.googleapis.com").supportsClientId(false).fieldNamingPolicy(fieldNamingPolicy);
    }

    private RoadsApi() {
    }

    public static PendingResult<SnappedPoint[]> nearestRoads(GeoApiContext geoApiContext, LatLng... latLngArr) {
        return geoApiContext.b(f10280c, RoadsResponse.class, "points", StringJoin.join('|', (StringJoin.UrlValue[]) latLngArr));
    }

    public static PendingResult<SnappedPoint[]> snapToRoads(GeoApiContext geoApiContext, boolean z, LatLng... latLngArr) {
        return geoApiContext.b(f10278a, RoadsResponse.class, ClientCookie.PATH_ATTR, StringJoin.join('|', (StringJoin.UrlValue[]) latLngArr), "interpolate", String.valueOf(z));
    }

    public static PendingResult<SnappedPoint[]> snapToRoads(GeoApiContext geoApiContext, LatLng... latLngArr) {
        return geoApiContext.b(f10278a, RoadsResponse.class, ClientCookie.PATH_ATTR, StringJoin.join('|', (StringJoin.UrlValue[]) latLngArr));
    }

    public static PendingResult<SnappedSpeedLimitResponse> snappedSpeedLimits(GeoApiContext geoApiContext, LatLng... latLngArr) {
        return geoApiContext.b(f10279b, CombinedResponse.class, ClientCookie.PATH_ATTR, StringJoin.join('|', (StringJoin.UrlValue[]) latLngArr));
    }

    public static PendingResult<SpeedLimit[]> speedLimits(GeoApiContext geoApiContext, LatLng... latLngArr) {
        return geoApiContext.b(f10279b, SpeedsResponse.class, ClientCookie.PATH_ATTR, StringJoin.join('|', (StringJoin.UrlValue[]) latLngArr));
    }

    public static PendingResult<SpeedLimit[]> speedLimits(GeoApiContext geoApiContext, String... strArr) {
        String[] strArr2 = new String[strArr.length * 2];
        int i2 = 0;
        for (String str : strArr) {
            int i3 = i2 + 1;
            strArr2[i2] = "placeId";
            i2 = i3 + 1;
            strArr2[i3] = str;
        }
        return geoApiContext.b(f10279b, SpeedsResponse.class, strArr2);
    }
}
