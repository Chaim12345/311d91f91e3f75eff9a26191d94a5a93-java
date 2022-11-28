package com.google.maps;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.FieldNamingPolicy;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.internal.StringJoin;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes2.dex */
public class FindPlaceFromTextRequest extends PendingResultBase<FindPlaceFromText, FindPlaceFromTextRequest, Response> {

    /* renamed from: a  reason: collision with root package name */
    static final ApiConfig f10269a = new ApiConfig("/maps/api/place/findplacefromtext/json").fieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).supportsClientId(false);

    /* loaded from: classes2.dex */
    public enum FieldMask implements StringJoin.UrlValue {
        FORMATTED_ADDRESS("formatted_address"),
        GEOMETRY("geometry"),
        ICON("icon"),
        ID("id"),
        NAME(AppMeasurementSdk.ConditionalUserProperty.NAME),
        OPENING_HOURS("opening_hours"),
        PERMANENTLY_CLOSED("permanently_closed"),
        PHOTOS("photos"),
        PLACE_ID("place_id"),
        PRICE_LEVEL("price_level"),
        RATING("rating"),
        TYPES("types");
        
        private final String field;

        FieldMask(String str) {
            this.field = str;
        }

        @Override // com.google.maps.internal.StringJoin.UrlValue
        public String toUrlValue() {
            return this.field;
        }
    }

    /* loaded from: classes2.dex */
    public enum InputType implements StringJoin.UrlValue {
        TEXT_QUERY("textquery"),
        PHONE_NUMBER("phonenumber");
        
        private final String inputType;

        InputType(String str) {
            this.inputType = str;
        }

        @Override // com.google.maps.internal.StringJoin.UrlValue
        public String toUrlValue() {
            return this.inputType;
        }
    }

    /* loaded from: classes2.dex */
    public interface LocationBias extends StringJoin.UrlValue {
    }

    /* loaded from: classes2.dex */
    public static class LocationBiasCircular implements LocationBias {
        private final LatLng center;
        private final int radius;

        public LocationBiasCircular(LatLng latLng, int i2) {
            this.center = latLng;
            this.radius = i2;
        }

        @Override // com.google.maps.internal.StringJoin.UrlValue
        public String toUrlValue() {
            return "circle:" + this.radius + "@" + this.center.toUrlValue();
        }
    }

    /* loaded from: classes2.dex */
    public static class LocationBiasIP implements LocationBias {
        @Override // com.google.maps.internal.StringJoin.UrlValue
        public String toUrlValue() {
            return "ipbias";
        }
    }

    /* loaded from: classes2.dex */
    public static class LocationBiasPoint implements LocationBias {
        private final LatLng point;

        public LocationBiasPoint(LatLng latLng) {
            this.point = latLng;
        }

        @Override // com.google.maps.internal.StringJoin.UrlValue
        public String toUrlValue() {
            return "point:" + this.point.toUrlValue();
        }
    }

    /* loaded from: classes2.dex */
    public static class LocationBiasRectangular implements LocationBias {
        private final LatLng northEast;
        private final LatLng southWest;

        public LocationBiasRectangular(LatLng latLng, LatLng latLng2) {
            this.southWest = latLng;
            this.northEast = latLng2;
        }

        @Override // com.google.maps.internal.StringJoin.UrlValue
        public String toUrlValue() {
            return "rectangle:" + this.southWest.toUrlValue() + "|" + this.northEast.toUrlValue();
        }
    }

    /* loaded from: classes2.dex */
    public static class Response implements ApiResponse<FindPlaceFromText> {
        public PlacesSearchResult[] candidates;
        public String errorMessage;
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
        public FindPlaceFromText getResult() {
            FindPlaceFromText findPlaceFromText = new FindPlaceFromText();
            findPlaceFromText.candidates = this.candidates;
            return findPlaceFromText;
        }

        @Override // com.google.maps.internal.ApiResponse
        public boolean successful() {
            return "OK".equals(this.status) || "ZERO_RESULTS".equals(this.status);
        }
    }

    public FindPlaceFromTextRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, f10269a, Response.class);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.FindPlaceFromTextRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ FindPlaceFromTextRequest channel(String str) {
        return super.channel(str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.FindPlaceFromTextRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ FindPlaceFromTextRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    public FindPlaceFromTextRequest fields(FieldMask... fieldMaskArr) {
        return (FindPlaceFromTextRequest) c("fields", StringJoin.join((char) AbstractJsonLexerKt.COMMA, (StringJoin.UrlValue[]) fieldMaskArr));
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        if (!f().containsKey("input")) {
            throw new IllegalArgumentException("Request must contain 'input'.");
        }
        if (!f().containsKey("inputtype")) {
            throw new IllegalArgumentException("Request must contain 'inputType'.");
        }
    }

    public FindPlaceFromTextRequest input(String str) {
        return (FindPlaceFromTextRequest) c("input", str);
    }

    public FindPlaceFromTextRequest inputType(InputType inputType) {
        return (FindPlaceFromTextRequest) b("inputtype", inputType);
    }

    public FindPlaceFromTextRequest locationBias(LocationBias locationBias) {
        return (FindPlaceFromTextRequest) b("locationbias", locationBias);
    }
}
