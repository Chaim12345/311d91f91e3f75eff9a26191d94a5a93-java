package com.google.maps;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.FieldNamingPolicy;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.LatLng;
import com.psa.mym.mycitroenconnect.common.AppConstants;
/* loaded from: classes2.dex */
public class QueryAutocompleteRequest extends PendingResultBase<AutocompletePrediction[], QueryAutocompleteRequest, Response> {

    /* renamed from: a  reason: collision with root package name */
    static final ApiConfig f10277a = new ApiConfig("/maps/api/place/queryautocomplete/json").fieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

    /* loaded from: classes2.dex */
    public static class Response implements ApiResponse<AutocompletePrediction[]> {
        public String errorMessage;
        public AutocompletePrediction[] predictions;
        public String status;

        @Override // com.google.maps.internal.ApiResponse
        public ApiException getError() {
            if (successful()) {
                return null;
            }
            return ApiException.from(this.status, this.errorMessage);
        }

        @Override // com.google.maps.internal.ApiResponse
        public AutocompletePrediction[] getResult() {
            return this.predictions;
        }

        @Override // com.google.maps.internal.ApiResponse
        public boolean successful() {
            return "OK".equals(this.status) || "ZERO_RESULTS".equals(this.status);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public QueryAutocompleteRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, f10277a, Response.class);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PendingResultBase, com.google.maps.QueryAutocompleteRequest] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ QueryAutocompleteRequest channel(String str) {
        return super.channel(str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PendingResultBase, com.google.maps.QueryAutocompleteRequest] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ QueryAutocompleteRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        if (!f().containsKey("input")) {
            throw new IllegalArgumentException("Request must contain 'input'.");
        }
    }

    public QueryAutocompleteRequest input(String str) {
        return (QueryAutocompleteRequest) c("input", str);
    }

    public QueryAutocompleteRequest location(LatLng latLng) {
        return (QueryAutocompleteRequest) b("location", latLng);
    }

    public QueryAutocompleteRequest offset(int i2) {
        return (QueryAutocompleteRequest) c(TypedValues.Cycle.S_WAVE_OFFSET, String.valueOf(i2));
    }

    public QueryAutocompleteRequest radius(int i2) {
        return (QueryAutocompleteRequest) c(AppConstants.GEO_FENCE_MODE_RADIUS, String.valueOf(i2));
    }
}
