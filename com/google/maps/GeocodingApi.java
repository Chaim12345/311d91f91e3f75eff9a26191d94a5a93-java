package com.google.maps;

import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
/* loaded from: classes2.dex */
public class GeocodingApi {

    /* loaded from: classes2.dex */
    public static class Response implements ApiResponse<GeocodingResult[]> {
        public String errorMessage;
        public GeocodingResult[] results;
        public String status;

        @Override // com.google.maps.internal.ApiResponse
        public ApiException getError() {
            if (successful()) {
                return null;
            }
            return ApiException.from(this.status, this.errorMessage);
        }

        @Override // com.google.maps.internal.ApiResponse
        public GeocodingResult[] getResult() {
            return this.results;
        }

        @Override // com.google.maps.internal.ApiResponse
        public boolean successful() {
            return "OK".equals(this.status) || "ZERO_RESULTS".equals(this.status);
        }
    }

    private GeocodingApi() {
    }

    public static GeocodingApiRequest geocode(GeoApiContext geoApiContext, String str) {
        GeocodingApiRequest geocodingApiRequest = new GeocodingApiRequest(geoApiContext);
        geocodingApiRequest.address(str);
        return geocodingApiRequest;
    }

    public static GeocodingApiRequest newRequest(GeoApiContext geoApiContext) {
        return new GeocodingApiRequest(geoApiContext);
    }

    public static GeocodingApiRequest reverseGeocode(GeoApiContext geoApiContext, LatLng latLng) {
        GeocodingApiRequest geocodingApiRequest = new GeocodingApiRequest(geoApiContext);
        geocodingApiRequest.latlng(latLng);
        return geocodingApiRequest;
    }
}
