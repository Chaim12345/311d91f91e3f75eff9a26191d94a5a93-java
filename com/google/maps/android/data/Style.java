package com.google.maps.android.data;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.Observable;
/* loaded from: classes2.dex */
public abstract class Style extends Observable {

    /* renamed from: a  reason: collision with root package name */
    protected MarkerOptions f10318a = new MarkerOptions();

    /* renamed from: b  reason: collision with root package name */
    protected PolylineOptions f10319b;

    /* renamed from: c  reason: collision with root package name */
    protected PolygonOptions f10320c;

    public Style() {
        PolylineOptions polylineOptions = new PolylineOptions();
        this.f10319b = polylineOptions;
        polylineOptions.clickable(true);
        PolygonOptions polygonOptions = new PolygonOptions();
        this.f10320c = polygonOptions;
        polygonOptions.clickable(true);
    }

    public float getRotation() {
        return this.f10318a.getRotation();
    }

    public void setLineStringWidth(float f2) {
        this.f10319b.width(f2);
    }

    public void setMarkerHotSpot(float f2, float f3, String str, String str2) {
        if (!str.equals("fraction")) {
            f2 = 0.5f;
        }
        if (!str2.equals("fraction")) {
            f3 = 1.0f;
        }
        this.f10318a.anchor(f2, f3);
    }

    public void setMarkerRotation(float f2) {
        this.f10318a.rotation(f2);
    }

    public void setPolygonFillColor(int i2) {
        this.f10320c.fillColor(i2);
    }

    public void setPolygonStrokeWidth(float f2) {
        this.f10320c.strokeWidth(f2);
    }
}
