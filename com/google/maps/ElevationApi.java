package com.google.maps;

import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.internal.StringJoin;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import org.apache.http.cookie.ClientCookie;
/* loaded from: classes2.dex */
public class ElevationApi {
    private static final ApiConfig API_CONFIG = new ApiConfig("/maps/api/elevation/json");

    /* loaded from: classes2.dex */
    private static class MultiResponse implements ApiResponse<ElevationResult[]> {
        public String errorMessage;
        public ElevationResult[] results;
        public String status;

        private MultiResponse() {
        }

        @Override // com.google.maps.internal.ApiResponse
        public ApiException getError() {
            if (successful()) {
                return null;
            }
            return ApiException.from(this.status, this.errorMessage);
        }

        @Override // com.google.maps.internal.ApiResponse
        public ElevationResult[] getResult() {
            return this.results;
        }

        @Override // com.google.maps.internal.ApiResponse
        public boolean successful() {
            return "OK".equals(this.status);
        }
    }

    /* loaded from: classes2.dex */
    private static class SingularResponse implements ApiResponse<ElevationResult> {
        public String errorMessage;
        public ElevationResult[] results;
        public String status;

        private SingularResponse() {
        }

        @Override // com.google.maps.internal.ApiResponse
        public ApiException getError() {
            if (successful()) {
                return null;
            }
            return ApiException.from(this.status, this.errorMessage);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.maps.internal.ApiResponse
        public ElevationResult getResult() {
            return this.results[0];
        }

        @Override // com.google.maps.internal.ApiResponse
        public boolean successful() {
            return "OK".equals(this.status);
        }
    }

    private ElevationApi() {
    }

    public static PendingResult<ElevationResult[]> getByPath(GeoApiContext geoApiContext, int i2, EncodedPolyline encodedPolyline) {
        ApiConfig apiConfig = API_CONFIG;
        return geoApiContext.b(apiConfig, MultiResponse.class, "samples", String.valueOf(i2), ClientCookie.PATH_ATTR, "enc:" + encodedPolyline.getEncodedPath());
    }

    public static PendingResult<ElevationResult[]> getByPath(GeoApiContext geoApiContext, int i2, LatLng... latLngArr) {
        return geoApiContext.b(API_CONFIG, MultiResponse.class, "samples", String.valueOf(i2), ClientCookie.PATH_ATTR, shortestParam(latLngArr));
    }

    public static PendingResult<ElevationResult> getByPoint(GeoApiContext geoApiContext, LatLng latLng) {
        return geoApiContext.b(API_CONFIG, SingularResponse.class, "locations", latLng.toString());
    }

    public static PendingResult<ElevationResult[]> getByPoints(GeoApiContext geoApiContext, EncodedPolyline encodedPolyline) {
        ApiConfig apiConfig = API_CONFIG;
        return geoApiContext.b(apiConfig, MultiResponse.class, "locations", "enc:" + encodedPolyline.getEncodedPath());
    }

    public static PendingResult<ElevationResult[]> getByPoints(GeoApiContext geoApiContext, LatLng... latLngArr) {
        return geoApiContext.b(API_CONFIG, MultiResponse.class, "locations", shortestParam(latLngArr));
    }

    private static String shortestParam(LatLng[] latLngArr) {
        String join = StringJoin.join('|', (StringJoin.UrlValue[]) latLngArr);
        String str = "enc:" + PolylineEncoding.encode(latLngArr);
        return join.length() < str.length() ? join : str;
    }
}
