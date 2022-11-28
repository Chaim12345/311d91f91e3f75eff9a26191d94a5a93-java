package com.google.maps;

import com.google.maps.model.Size;
/* loaded from: classes2.dex */
public class StaticMapsApi {
    private StaticMapsApi() {
    }

    public static StaticMapsRequest newRequest(GeoApiContext geoApiContext, Size size) {
        return new StaticMapsRequest(geoApiContext).size(size);
    }
}
