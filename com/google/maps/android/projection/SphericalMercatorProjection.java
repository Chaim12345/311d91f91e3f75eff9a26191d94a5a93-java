package com.google.maps.android.projection;

import com.google.android.gms.maps.model.LatLng;
/* loaded from: classes2.dex */
public class SphericalMercatorProjection {

    /* renamed from: a  reason: collision with root package name */
    final double f10324a;

    public SphericalMercatorProjection(double d2) {
        this.f10324a = d2;
    }

    public LatLng toLatLng(com.google.maps.android.geometry.Point point) {
        double d2 = point.x;
        double d3 = this.f10324a;
        return new LatLng(90.0d - Math.toDegrees(Math.atan(Math.exp(((-(0.5d - (point.y / d3))) * 2.0d) * 3.141592653589793d)) * 2.0d), ((d2 / d3) - 0.5d) * 360.0d);
    }

    public Point toPoint(LatLng latLng) {
        double sin = Math.sin(Math.toRadians(latLng.latitude));
        double d2 = this.f10324a;
        return new Point(((latLng.longitude / 360.0d) + 0.5d) * d2, (((Math.log((sin + 1.0d) / (1.0d - sin)) * 0.5d) / (-6.283185307179586d)) + 0.5d) * d2);
    }
}
