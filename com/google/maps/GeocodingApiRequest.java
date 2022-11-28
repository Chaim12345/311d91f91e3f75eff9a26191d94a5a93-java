package com.google.maps;

import com.google.maps.GeocodingApi;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.StringJoin;
import com.google.maps.model.AddressType;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.LocationType;
/* loaded from: classes2.dex */
public class GeocodingApiRequest extends PendingResultBase<GeocodingResult[], GeocodingApiRequest, GeocodingApi.Response> {
    private static final ApiConfig API_CONFIG = new ApiConfig("/maps/api/geocode/json");

    public GeocodingApiRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, API_CONFIG, GeocodingApi.Response.class);
    }

    public GeocodingApiRequest address(String str) {
        return (GeocodingApiRequest) c("address", str);
    }

    public GeocodingApiRequest bounds(LatLng latLng, LatLng latLng2) {
        return (GeocodingApiRequest) c("bounds", StringJoin.join('|', latLng, latLng2));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.GeocodingApiRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ GeocodingApiRequest channel(String str) {
        return super.channel(str);
    }

    public GeocodingApiRequest components(ComponentFilter... componentFilterArr) {
        return (GeocodingApiRequest) c("components", StringJoin.join('|', (StringJoin.UrlValue[]) componentFilterArr));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.GeocodingApiRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ GeocodingApiRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        if (f().containsKey("latlng") && f().containsKey("address") && f().containsKey("place_id")) {
            throw new IllegalArgumentException("Request must contain only one of 'address', 'latlng' or 'place_id'.");
        }
        if (!f().containsKey("latlng") && !f().containsKey("address") && !f().containsKey("components") && !f().containsKey("place_id")) {
            throw new IllegalArgumentException("Request must contain at least one of 'address', 'latlng', 'place_id' and 'components'.");
        }
    }

    public GeocodingApiRequest latlng(LatLng latLng) {
        return (GeocodingApiRequest) b("latlng", latLng);
    }

    public GeocodingApiRequest locationType(LocationType... locationTypeArr) {
        return (GeocodingApiRequest) c("location_type", StringJoin.join('|', (StringJoin.UrlValue[]) locationTypeArr));
    }

    public GeocodingApiRequest place(String str) {
        return (GeocodingApiRequest) c("place_id", str);
    }

    public GeocodingApiRequest region(String str) {
        return (GeocodingApiRequest) c("region", str);
    }

    public GeocodingApiRequest resultType(AddressType... addressTypeArr) {
        return (GeocodingApiRequest) c("result_type", StringJoin.join('|', (StringJoin.UrlValue[]) addressTypeArr));
    }
}
