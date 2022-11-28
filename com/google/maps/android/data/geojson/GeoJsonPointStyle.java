package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.Style;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class GeoJsonPointStyle extends Style implements GeoJsonStyle {
    private static final String[] GEOMETRY_TYPE = {"Point", "MultiPoint", "GeometryCollection"};

    public GeoJsonPointStyle() {
        this.f10318a = new MarkerOptions();
    }

    private void styleChanged() {
        setChanged();
        notifyObservers();
    }

    public float getAlpha() {
        return this.f10318a.getAlpha();
    }

    public float getAnchorU() {
        return this.f10318a.getAnchorU();
    }

    public float getAnchorV() {
        return this.f10318a.getAnchorV();
    }

    @Override // com.google.maps.android.data.geojson.GeoJsonStyle
    public String[] getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public BitmapDescriptor getIcon() {
        return this.f10318a.getIcon();
    }

    public float getInfoWindowAnchorU() {
        return this.f10318a.getInfoWindowAnchorU();
    }

    public float getInfoWindowAnchorV() {
        return this.f10318a.getInfoWindowAnchorV();
    }

    @Override // com.google.maps.android.data.Style
    public float getRotation() {
        return this.f10318a.getRotation();
    }

    public String getSnippet() {
        return this.f10318a.getSnippet();
    }

    public String getTitle() {
        return this.f10318a.getTitle();
    }

    public float getZIndex() {
        return this.f10318a.getZIndex();
    }

    public boolean isDraggable() {
        return this.f10318a.isDraggable();
    }

    public boolean isFlat() {
        return this.f10318a.isFlat();
    }

    @Override // com.google.maps.android.data.geojson.GeoJsonStyle
    public boolean isVisible() {
        return this.f10318a.isVisible();
    }

    public void setAlpha(float f2) {
        this.f10318a.alpha(f2);
        styleChanged();
    }

    public void setAnchor(float f2, float f3) {
        setMarkerHotSpot(f2, f3, "fraction", "fraction");
        styleChanged();
    }

    public void setDraggable(boolean z) {
        this.f10318a.draggable(z);
        styleChanged();
    }

    public void setFlat(boolean z) {
        this.f10318a.flat(z);
        styleChanged();
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        this.f10318a.icon(bitmapDescriptor);
        styleChanged();
    }

    public void setInfoWindowAnchor(float f2, float f3) {
        this.f10318a.infoWindowAnchor(f2, f3);
        styleChanged();
    }

    public void setRotation(float f2) {
        setMarkerRotation(f2);
        styleChanged();
    }

    public void setSnippet(String str) {
        this.f10318a.snippet(str);
        styleChanged();
    }

    public void setTitle(String str) {
        this.f10318a.title(str);
        styleChanged();
    }

    @Override // com.google.maps.android.data.geojson.GeoJsonStyle
    public void setVisible(boolean z) {
        this.f10318a.visible(z);
        styleChanged();
    }

    public void setZIndex(float f2) {
        this.f10318a.zIndex(f2);
        styleChanged();
    }

    public MarkerOptions toMarkerOptions() {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.alpha(this.f10318a.getAlpha());
        markerOptions.anchor(this.f10318a.getAnchorU(), this.f10318a.getAnchorV());
        markerOptions.draggable(this.f10318a.isDraggable());
        markerOptions.flat(this.f10318a.isFlat());
        markerOptions.icon(this.f10318a.getIcon());
        markerOptions.infoWindowAnchor(this.f10318a.getInfoWindowAnchorU(), this.f10318a.getInfoWindowAnchorV());
        markerOptions.rotation(this.f10318a.getRotation());
        markerOptions.snippet(this.f10318a.getSnippet());
        markerOptions.title(this.f10318a.getTitle());
        markerOptions.visible(this.f10318a.isVisible());
        markerOptions.zIndex(this.f10318a.getZIndex());
        return markerOptions;
    }

    public String toString() {
        return "PointStyle{\n geometry type=" + Arrays.toString(GEOMETRY_TYPE) + ",\n alpha=" + getAlpha() + ",\n anchor U=" + getAnchorU() + ",\n anchor V=" + getAnchorV() + ",\n draggable=" + isDraggable() + ",\n flat=" + isFlat() + ",\n info window anchor U=" + getInfoWindowAnchorU() + ",\n info window anchor V=" + getInfoWindowAnchorV() + ",\n rotation=" + getRotation() + ",\n snippet=" + getSnippet() + ",\n title=" + getTitle() + ",\n visible=" + isVisible() + ",\n z index=" + getZIndex() + "\n}\n";
    }
}
