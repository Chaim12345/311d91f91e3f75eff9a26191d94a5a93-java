package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Style;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes2.dex */
public class GeoJsonLineStringStyle extends Style implements GeoJsonStyle {
    private static final String[] GEOMETRY_TYPE = {"LineString", "MultiLineString", "GeometryCollection"};

    public GeoJsonLineStringStyle() {
        PolylineOptions polylineOptions = new PolylineOptions();
        this.f10319b = polylineOptions;
        polylineOptions.clickable(true);
    }

    private void styleChanged() {
        setChanged();
        notifyObservers();
    }

    public int getColor() {
        return this.f10319b.getColor();
    }

    @Override // com.google.maps.android.data.geojson.GeoJsonStyle
    public String[] getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public List<PatternItem> getPattern() {
        return this.f10319b.getPattern();
    }

    public float getWidth() {
        return this.f10319b.getWidth();
    }

    public float getZIndex() {
        return this.f10319b.getZIndex();
    }

    public boolean isClickable() {
        return this.f10319b.isClickable();
    }

    public boolean isGeodesic() {
        return this.f10319b.isGeodesic();
    }

    @Override // com.google.maps.android.data.geojson.GeoJsonStyle
    public boolean isVisible() {
        return this.f10319b.isVisible();
    }

    public void setClickable(boolean z) {
        this.f10319b.clickable(z);
        styleChanged();
    }

    public void setColor(int i2) {
        this.f10319b.color(i2);
        styleChanged();
    }

    public void setGeodesic(boolean z) {
        this.f10319b.geodesic(z);
        styleChanged();
    }

    public void setPattern(List<PatternItem> list) {
        this.f10319b.pattern(list);
        styleChanged();
    }

    @Override // com.google.maps.android.data.geojson.GeoJsonStyle
    public void setVisible(boolean z) {
        this.f10319b.visible(z);
        styleChanged();
    }

    public void setWidth(float f2) {
        setLineStringWidth(f2);
        styleChanged();
    }

    public void setZIndex(float f2) {
        this.f10319b.zIndex(f2);
        styleChanged();
    }

    public PolylineOptions toPolylineOptions() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(this.f10319b.getColor());
        polylineOptions.clickable(this.f10319b.isClickable());
        polylineOptions.geodesic(this.f10319b.isGeodesic());
        polylineOptions.visible(this.f10319b.isVisible());
        polylineOptions.width(this.f10319b.getWidth());
        polylineOptions.zIndex(this.f10319b.getZIndex());
        polylineOptions.pattern(getPattern());
        return polylineOptions;
    }

    public String toString() {
        return "LineStringStyle{\n geometry type=" + Arrays.toString(GEOMETRY_TYPE) + ",\n color=" + getColor() + ",\n clickable=" + isClickable() + ",\n geodesic=" + isGeodesic() + ",\n visible=" + isVisible() + ",\n width=" + getWidth() + ",\n z index=" + getZIndex() + ",\n pattern=" + getPattern() + "\n}\n";
    }
}
