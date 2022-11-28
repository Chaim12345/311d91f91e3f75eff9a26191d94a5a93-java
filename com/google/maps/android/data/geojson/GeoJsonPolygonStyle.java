package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.data.Style;
import com.google.maps.android.data.kml.KmlPolygon;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes2.dex */
public class GeoJsonPolygonStyle extends Style implements GeoJsonStyle {
    private static final String[] GEOMETRY_TYPE = {KmlPolygon.GEOMETRY_TYPE, "MultiPolygon", "GeometryCollection"};

    public GeoJsonPolygonStyle() {
        PolygonOptions polygonOptions = new PolygonOptions();
        this.f10320c = polygonOptions;
        polygonOptions.clickable(true);
    }

    private void styleChanged() {
        setChanged();
        notifyObservers();
    }

    public int getFillColor() {
        return this.f10320c.getFillColor();
    }

    @Override // com.google.maps.android.data.geojson.GeoJsonStyle
    public String[] getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public int getStrokeColor() {
        return this.f10320c.getStrokeColor();
    }

    public int getStrokeJointType() {
        return this.f10320c.getStrokeJointType();
    }

    public List<PatternItem> getStrokePattern() {
        return this.f10320c.getStrokePattern();
    }

    public float getStrokeWidth() {
        return this.f10320c.getStrokeWidth();
    }

    public float getZIndex() {
        return this.f10320c.getZIndex();
    }

    public boolean isClickable() {
        return this.f10320c.isClickable();
    }

    public boolean isGeodesic() {
        return this.f10320c.isGeodesic();
    }

    @Override // com.google.maps.android.data.geojson.GeoJsonStyle
    public boolean isVisible() {
        return this.f10320c.isVisible();
    }

    public void setClickable(boolean z) {
        this.f10320c.clickable(z);
        styleChanged();
    }

    public void setFillColor(int i2) {
        setPolygonFillColor(i2);
        styleChanged();
    }

    public void setGeodesic(boolean z) {
        this.f10320c.geodesic(z);
        styleChanged();
    }

    public void setStrokeColor(int i2) {
        this.f10320c.strokeColor(i2);
        styleChanged();
    }

    public void setStrokeJointType(int i2) {
        this.f10320c.strokeJointType(i2);
        styleChanged();
    }

    public void setStrokePattern(List<PatternItem> list) {
        this.f10320c.strokePattern(list);
        styleChanged();
    }

    public void setStrokeWidth(float f2) {
        setPolygonStrokeWidth(f2);
        styleChanged();
    }

    @Override // com.google.maps.android.data.geojson.GeoJsonStyle
    public void setVisible(boolean z) {
        this.f10320c.visible(z);
        styleChanged();
    }

    public void setZIndex(float f2) {
        this.f10320c.zIndex(f2);
        styleChanged();
    }

    public PolygonOptions toPolygonOptions() {
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.fillColor(this.f10320c.getFillColor());
        polygonOptions.geodesic(this.f10320c.isGeodesic());
        polygonOptions.strokeColor(this.f10320c.getStrokeColor());
        polygonOptions.strokeJointType(this.f10320c.getStrokeJointType());
        polygonOptions.strokePattern(this.f10320c.getStrokePattern());
        polygonOptions.strokeWidth(this.f10320c.getStrokeWidth());
        polygonOptions.visible(this.f10320c.isVisible());
        polygonOptions.zIndex(this.f10320c.getZIndex());
        polygonOptions.clickable(this.f10320c.isClickable());
        return polygonOptions;
    }

    public String toString() {
        return "PolygonStyle{\n geometry type=" + Arrays.toString(GEOMETRY_TYPE) + ",\n fill color=" + getFillColor() + ",\n geodesic=" + isGeodesic() + ",\n stroke color=" + getStrokeColor() + ",\n stroke joint type=" + getStrokeJointType() + ",\n stroke pattern=" + getStrokePattern() + ",\n stroke width=" + getStrokeWidth() + ",\n visible=" + isVisible() + ",\n z index=" + getZIndex() + ",\n clickable=" + isClickable() + "\n}\n";
    }
}
