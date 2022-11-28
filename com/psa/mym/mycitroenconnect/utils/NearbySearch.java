package com.psa.mym.mycitroenconnect.utils;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.RankBy;
import com.psa.mym.mycitroenconnect.BuildConfig;
import java.io.IOException;
/* loaded from: classes3.dex */
public class NearbySearch {

    /* renamed from: a  reason: collision with root package name */
    LatLng f10748a;

    public NearbySearch(com.google.android.gms.maps.model.LatLng latLng) {
        this.f10748a = new LatLng(latLng.latitude, latLng.longitude);
    }

    public PlacesSearchResponse run() {
        PlacesSearchResponse placesSearchResponse = new PlacesSearchResponse();
        GeoApiContext build = new GeoApiContext.Builder().apiKey(BuildConfig.GOOGLE_PLACES_ANDROID_API_KEY).build();
        LatLng latLng = this.f10748a;
        try {
            try {
                return PlacesApi.nearbySearchQuery(build, new LatLng(latLng.lat, latLng.lng)).radius(5000).rankby(RankBy.PROMINENCE).keyword("fuel station").language("en").type(PlaceType.GAS_STATION).await();
            } catch (Throwable unused) {
                return placesSearchResponse;
            }
        } catch (IOException | InterruptedException e2) {
            e2.printStackTrace();
            return placesSearchResponse;
        }
    }
}
