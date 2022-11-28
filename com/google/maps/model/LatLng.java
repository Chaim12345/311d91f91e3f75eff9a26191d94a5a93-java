package com.google.maps.model;

import com.google.maps.internal.StringJoin;
import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes2.dex */
public class LatLng implements StringJoin.UrlValue, Serializable {
    private static final long serialVersionUID = 1;
    public double lat;
    public double lng;

    public LatLng() {
    }

    public LatLng(double d2, double d3) {
        this.lat = d2;
        this.lng = d3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        return Double.compare(latLng.lat, this.lat) == 0 && Double.compare(latLng.lng, this.lng) == 0;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.lat), Double.valueOf(this.lng));
    }

    public String toString() {
        return toUrlValue();
    }

    @Override // com.google.maps.internal.StringJoin.UrlValue
    public String toUrlValue() {
        return String.format(Locale.ENGLISH, "%.8f,%.8f", Double.valueOf(this.lat), Double.valueOf(this.lng));
    }
}
