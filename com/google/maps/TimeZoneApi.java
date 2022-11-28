package com.google.maps;

import com.google.gson.FieldNamingPolicy;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.LatLng;
import java.util.TimeZone;
/* loaded from: classes2.dex */
public class TimeZoneApi {
    private static final ApiConfig API_CONFIG = new ApiConfig("/maps/api/timezone/json").fieldNamingPolicy(FieldNamingPolicy.IDENTITY);

    /* loaded from: classes2.dex */
    private static class Response implements ApiResponse<TimeZone> {
        public String errorMessage;
        public String status;
        private String timeZoneId;

        private Response() {
        }

        @Override // com.google.maps.internal.ApiResponse
        public ApiException getError() {
            if (successful()) {
                return null;
            }
            return ApiException.from(this.status, this.errorMessage);
        }

        @Override // com.google.maps.internal.ApiResponse
        public TimeZone getResult() {
            String str = this.timeZoneId;
            if (str == null) {
                return null;
            }
            return TimeZone.getTimeZone(str);
        }

        @Override // com.google.maps.internal.ApiResponse
        public boolean successful() {
            return "OK".equals(this.status);
        }
    }

    private TimeZoneApi() {
    }

    public static PendingResult<TimeZone> getTimeZone(GeoApiContext geoApiContext, LatLng latLng) {
        return geoApiContext.b(API_CONFIG, Response.class, "location", latLng.toString(), "timestamp", "0");
    }
}
