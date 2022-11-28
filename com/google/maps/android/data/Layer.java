package com.google.maps.android.data;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.geojson.GeoJsonRenderer;
import com.google.maps.android.data.kml.KmlRenderer;
/* loaded from: classes2.dex */
public abstract class Layer {
    private Renderer mRenderer;

    /* loaded from: classes2.dex */
    public interface OnFeatureClickListener {
        void onFeatureClick(Feature feature);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Feature feature) {
        this.mRenderer.c(feature);
    }

    public abstract void addLayerToMap();

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        Renderer renderer = this.mRenderer;
        if (!(renderer instanceof GeoJsonRenderer)) {
            throw new UnsupportedOperationException("Stored renderer is not a GeoJsonRenderer");
        }
        ((GeoJsonRenderer) renderer).addLayerToMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c() {
        Renderer renderer = this.mRenderer;
        if (!(renderer instanceof KmlRenderer)) {
            throw new UnsupportedOperationException("Stored renderer is not a KmlRenderer");
        }
        ((KmlRenderer) renderer).addLayerToMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean d() {
        return this.mRenderer.z();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(Feature feature) {
        this.mRenderer.E(feature);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(Renderer renderer) {
        this.mRenderer = renderer;
    }

    public Feature getContainerFeature(Object obj) {
        return this.mRenderer.o(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Iterable getContainers() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).getNestedContainers();
        }
        return null;
    }

    public GeoJsonLineStringStyle getDefaultLineStringStyle() {
        return this.mRenderer.q();
    }

    public GeoJsonPointStyle getDefaultPointStyle() {
        return this.mRenderer.r();
    }

    public GeoJsonPolygonStyle getDefaultPolygonStyle() {
        return this.mRenderer.s();
    }

    public Feature getFeature(Object obj) {
        return this.mRenderer.t(obj);
    }

    public Iterable<? extends Feature> getFeatures() {
        return this.mRenderer.getFeatures();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Iterable getGroundOverlays() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).getGroundOverlays();
        }
        return null;
    }

    public GoogleMap getMap() {
        return this.mRenderer.getMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasContainers() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).hasNestedContainers();
        }
        return false;
    }

    public boolean isLayerOnMap() {
        return this.mRenderer.isLayerOnMap();
    }

    public void removeLayerFromMap() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof GeoJsonRenderer) {
            ((GeoJsonRenderer) renderer).removeLayerFromMap();
        } else if (renderer instanceof KmlRenderer) {
            ((KmlRenderer) renderer).removeLayerFromMap();
        }
    }

    public void setMap(GoogleMap googleMap) {
        this.mRenderer.setMap(googleMap);
    }

    public void setOnFeatureClickListener(OnFeatureClickListener onFeatureClickListener) {
        this.mRenderer.J(onFeatureClickListener);
    }
}
