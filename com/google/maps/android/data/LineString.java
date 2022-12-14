package com.google.maps.android.data;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
/* loaded from: classes2.dex */
public class LineString implements Geometry<List<LatLng>> {
    private static final String GEOMETRY_TYPE = "LineString";
    private final List<LatLng> mCoordinates;

    public LineString(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        this.mCoordinates = list;
    }

    @Override // com.google.maps.android.data.Geometry
    public List<LatLng> getGeometryObject() {
        return this.mCoordinates;
    }

    @Override // com.google.maps.android.data.Geometry
    public String getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public String toString() {
        return GEOMETRY_TYPE + "{\n coordinates=" + this.mCoordinates + "\n}\n";
    }
}
