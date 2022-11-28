package com.google.maps;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.FieldNamingPolicy;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.internal.StringJoin;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceAutocompleteType;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.io.Serializable;
import java.util.UUID;
/* loaded from: classes2.dex */
public class PlaceAutocompleteRequest extends PendingResultBase<AutocompletePrediction[], PlaceAutocompleteRequest, Response> {

    /* renamed from: a  reason: collision with root package name */
    static final ApiConfig f10275a = new ApiConfig("/maps/api/place/autocomplete/json").fieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

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

    /* loaded from: classes2.dex */
    public static final class SessionToken implements StringJoin.UrlValue, Serializable {
        private static final long serialVersionUID = 1;
        private UUID uuid;

        public SessionToken() {
            this.uuid = UUID.randomUUID();
        }

        public SessionToken(UUID uuid) {
            this.uuid = uuid;
        }

        public UUID getUUID() {
            return this.uuid;
        }

        @Override // com.google.maps.internal.StringJoin.UrlValue
        public String toUrlValue() {
            return this.uuid.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PlaceAutocompleteRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, f10275a, Response.class);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PendingResultBase, com.google.maps.PlaceAutocompleteRequest] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ PlaceAutocompleteRequest channel(String str) {
        return super.channel(str);
    }

    public PlaceAutocompleteRequest components(ComponentFilter... componentFilterArr) {
        return (PlaceAutocompleteRequest) c("components", StringJoin.join('|', (StringJoin.UrlValue[]) componentFilterArr));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PendingResultBase, com.google.maps.PlaceAutocompleteRequest] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ PlaceAutocompleteRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        if (!f().containsKey("input")) {
            throw new IllegalArgumentException("Request must contain 'input'.");
        }
    }

    public PlaceAutocompleteRequest input(String str) {
        return (PlaceAutocompleteRequest) c("input", str);
    }

    public PlaceAutocompleteRequest location(LatLng latLng) {
        return (PlaceAutocompleteRequest) b("location", latLng);
    }

    public PlaceAutocompleteRequest offset(int i2) {
        return (PlaceAutocompleteRequest) c(TypedValues.Cycle.S_WAVE_OFFSET, String.valueOf(i2));
    }

    public PlaceAutocompleteRequest radius(int i2) {
        return (PlaceAutocompleteRequest) c(AppConstants.GEO_FENCE_MODE_RADIUS, String.valueOf(i2));
    }

    public PlaceAutocompleteRequest sessionToken(SessionToken sessionToken) {
        return (PlaceAutocompleteRequest) b("sessiontoken", sessionToken);
    }

    public PlaceAutocompleteRequest strictBounds(boolean z) {
        return (PlaceAutocompleteRequest) c("strictbounds", Boolean.toString(z));
    }

    public PlaceAutocompleteRequest type(PlaceAutocompleteType placeAutocompleteType) {
        return types(placeAutocompleteType);
    }

    public PlaceAutocompleteRequest types(PlaceAutocompleteType placeAutocompleteType) {
        return (PlaceAutocompleteRequest) b("types", placeAutocompleteType);
    }
}
