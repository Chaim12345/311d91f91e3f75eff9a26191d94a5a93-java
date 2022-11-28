package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.collections.GroundOverlayManager;
import com.google.maps.android.collections.MarkerManager;
import com.google.maps.android.collections.PolygonManager;
import com.google.maps.android.collections.PolylineManager;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Renderer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
/* loaded from: classes2.dex */
public class GeoJsonRenderer extends Renderer implements Observer {
    private static final Object FEATURE_NOT_ON_MAP = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeoJsonRenderer(GoogleMap googleMap, HashMap hashMap, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager) {
        super(googleMap, hashMap, markerManager, polygonManager, polylineManager, groundOverlayManager);
    }

    private void redrawFeatureToMap(GeoJsonFeature geoJsonFeature) {
        redrawFeatureToMap(geoJsonFeature, getMap());
    }

    private void redrawFeatureToMap(GeoJsonFeature geoJsonFeature, GoogleMap googleMap) {
        G(l().get(geoJsonFeature));
        B(geoJsonFeature, FEATURE_NOT_ON_MAP);
        if (googleMap == null || !geoJsonFeature.hasGeometry()) {
            return;
        }
        B(geoJsonFeature, d(geoJsonFeature, geoJsonFeature.getGeometry()));
    }

    public void addFeature(GeoJsonFeature geoJsonFeature) {
        super.c(geoJsonFeature);
        if (isLayerOnMap()) {
            geoJsonFeature.addObserver(this);
        }
    }

    public void addLayerToMap() {
        if (isLayerOnMap()) {
            return;
        }
        I(true);
        Iterator<Feature> it = super.getFeatures().iterator();
        while (it.hasNext()) {
            addFeature((GeoJsonFeature) it.next());
        }
    }

    public void removeFeature(GeoJsonFeature geoJsonFeature) {
        super.E(geoJsonFeature);
        if (super.getFeatures().contains(geoJsonFeature)) {
            geoJsonFeature.deleteObserver(this);
        }
    }

    public void removeLayerFromMap() {
        if (isLayerOnMap()) {
            for (Feature feature : super.getFeatures()) {
                G(super.l().get(feature));
                feature.deleteObserver(this);
            }
            I(false);
        }
    }

    @Override // com.google.maps.android.data.Renderer
    public void setMap(GoogleMap googleMap) {
        super.setMap(googleMap);
        Iterator<Feature> it = super.getFeatures().iterator();
        while (it.hasNext()) {
            redrawFeatureToMap((GeoJsonFeature) it.next(), googleMap);
        }
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        if (observable instanceof GeoJsonFeature) {
            GeoJsonFeature geoJsonFeature = (GeoJsonFeature) observable;
            Object obj2 = l().get(geoJsonFeature);
            Object obj3 = FEATURE_NOT_ON_MAP;
            boolean z = obj2 != obj3;
            if (z && geoJsonFeature.hasGeometry()) {
                redrawFeatureToMap(geoJsonFeature);
            } else if (z && !geoJsonFeature.hasGeometry()) {
                G(l().get(geoJsonFeature));
                B(geoJsonFeature, obj3);
            } else if (z || !geoJsonFeature.hasGeometry()) {
            } else {
                addFeature(geoJsonFeature);
            }
        }
    }
}
