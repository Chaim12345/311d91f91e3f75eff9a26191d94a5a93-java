package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.Point;
/* loaded from: classes2.dex */
public class KmlPoint extends Point {
    private final Double mAltitude;

    public KmlPoint(LatLng latLng) {
        this(latLng, null);
    }

    public KmlPoint(LatLng latLng, Double d2) {
        super(latLng);
        this.mAltitude = d2;
    }

    public Double getAltitude() {
        return this.mAltitude;
    }
}
