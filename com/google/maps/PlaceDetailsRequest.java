package com.google.maps;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.FieldNamingPolicy;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.internal.StringJoin;
import com.google.maps.model.PlaceDetails;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes2.dex */
public class PlaceDetailsRequest extends PendingResultBase<PlaceDetails, PlaceDetailsRequest, Response> {

    /* renamed from: a  reason: collision with root package name */
    static final ApiConfig f10276a = new ApiConfig("/maps/api/place/details/json").fieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

    /* loaded from: classes2.dex */
    public enum FieldMask implements StringJoin.UrlValue {
        ADDRESS_COMPONENT("address_component"),
        ADR_ADDRESS("adr_address"),
        ALT_ID("alt_id"),
        FORMATTED_ADDRESS("formatted_address"),
        FORMATTED_PHONE_NUMBER("formatted_phone_number"),
        GEOMETRY("geometry"),
        GEOMETRY_LOCATION("geometry/location"),
        GEOMETRY_LOCATION_LAT("geometry/location/lat"),
        GEOMETRY_LOCATION_LNG("geometry/location/lng"),
        GEOMETRY_VIEWPORT("geometry/viewport"),
        GEOMETRY_VIEWPORT_NORTHEAST("geometry/viewport/northeast"),
        GEOMETRY_VIEWPORT_NORTHEAST_LAT("geometry/viewport/northeast/lat"),
        GEOMETRY_VIEWPORT_NORTHEAST_LNG("geometry/viewport/northeast/lng"),
        GEOMETRY_VIEWPORT_SOUTHWEST("geometry/viewport/southwest"),
        GEOMETRY_VIEWPORT_SOUTHWEST_LAT("geometry/viewport/southwest/lat"),
        GEOMETRY_VIEWPORT_SOUTHWEST_LNG("geometry/viewport/southwest/lng"),
        ICON("icon"),
        ID("id"),
        INTERNATIONAL_PHONE_NUMBER("international_phone_number"),
        NAME(AppMeasurementSdk.ConditionalUserProperty.NAME),
        OPENING_HOURS("opening_hours"),
        PERMANENTLY_CLOSED("permanently_closed"),
        USER_RATINGS_TOTAL("user_ratings_total"),
        PHOTOS("photos"),
        PLACE_ID("place_id"),
        PLUS_CODE("plus_code"),
        PRICE_LEVEL("price_level"),
        RATING("rating"),
        REFERENCE("reference"),
        REVIEW("review"),
        SCOPE("scope"),
        TYPES("types"),
        URL(ImagesContract.URL),
        UTC_OFFSET("utc_offset"),
        VICINITY("vicinity"),
        WEBSITE("website");
        
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
    public static class Response implements ApiResponse<PlaceDetails> {
        public String errorMessage;
        public String[] htmlAttributions;
        public PlaceDetails result;
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
        public PlaceDetails getResult() {
            PlaceDetails placeDetails = this.result;
            if (placeDetails != null) {
                placeDetails.htmlAttributions = this.htmlAttributions;
            }
            return placeDetails;
        }

        @Override // com.google.maps.internal.ApiResponse
        public boolean successful() {
            return "OK".equals(this.status) || "ZERO_RESULTS".equals(this.status);
        }
    }

    public PlaceDetailsRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, f10276a, Response.class);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PlaceDetailsRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ PlaceDetailsRequest channel(String str) {
        return super.channel(str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PlaceDetailsRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ PlaceDetailsRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    public PlaceDetailsRequest fields(FieldMask... fieldMaskArr) {
        return (PlaceDetailsRequest) c("fields", StringJoin.join((char) AbstractJsonLexerKt.COMMA, (StringJoin.UrlValue[]) fieldMaskArr));
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        if (!f().containsKey("placeid")) {
            throw new IllegalArgumentException("Request must contain 'placeId'.");
        }
    }

    public PlaceDetailsRequest placeId(String str) {
        return (PlaceDetailsRequest) c("placeid", str);
    }

    public PlaceDetailsRequest region(String str) {
        return (PlaceDetailsRequest) c("region", str);
    }

    public PlaceDetailsRequest sessionToken(PlaceAutocompleteRequest.SessionToken sessionToken) {
        return (PlaceDetailsRequest) b("sessiontoken", sessionToken);
    }
}
