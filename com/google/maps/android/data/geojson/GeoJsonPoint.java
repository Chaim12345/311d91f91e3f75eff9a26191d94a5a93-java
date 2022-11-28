package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.Point;
/* loaded from: classes2.dex */
public class GeoJsonPoint extends Point {
    private final Double mAltitude;

    public GeoJsonPoint(LatLng latLng) {
        this(latLng, null);
    }

    public GeoJsonPoint(LatLng latLng, Double d2) {
        super(latLng);
        this.mAltitude = d2;
    }

    public Double getAltitude() {
        return this.mAltitude;
    }

    public LatLng getCoordinates() {
        return getGeometryObject();
    }

    public String getType() {
        return getGeometryType();
    }
}
