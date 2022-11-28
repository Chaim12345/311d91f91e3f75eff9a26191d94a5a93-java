package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.projection.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes2.dex */
public class NonHierarchicalViewBasedAlgorithm<T extends ClusterItem> extends NonHierarchicalDistanceBasedAlgorithm<T> implements ScreenBasedAlgorithm<T> {
    private static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1.0d);
    private LatLng mMapCenter;
    private int mViewHeight;
    private int mViewWidth;

    public NonHierarchicalViewBasedAlgorithm(int i2, int i3) {
        this.mViewWidth = i2;
        this.mViewHeight = i3;
    }

    private Bounds getVisibleBounds(float f2) {
        LatLng latLng = this.mMapCenter;
        if (latLng == null) {
            return new Bounds(0.0d, 0.0d, 0.0d, 0.0d);
        }
        Point point = PROJECTION.toPoint(latLng);
        double d2 = f2;
        double pow = ((this.mViewWidth / Math.pow(2.0d, d2)) / 256.0d) / 2.0d;
        double pow2 = ((this.mViewHeight / Math.pow(2.0d, d2)) / 256.0d) / 2.0d;
        double d3 = point.x;
        double d4 = point.y;
        return new Bounds(d3 - pow, d3 + pow, d4 - pow2, d4 + pow2);
    }

    @Override // com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm
    protected Collection b(PointQuadTree pointQuadTree, float f2) {
        Bounds visibleBounds = getVisibleBounds(f2);
        ArrayList arrayList = new ArrayList();
        double d2 = visibleBounds.minX;
        if (d2 < 0.0d) {
            arrayList.addAll(pointQuadTree.search(new Bounds(d2 + 1.0d, 1.0d, visibleBounds.minY, visibleBounds.maxY)));
            visibleBounds = new Bounds(0.0d, visibleBounds.maxX, visibleBounds.minY, visibleBounds.maxY);
        }
        double d3 = visibleBounds.maxX;
        if (d3 > 1.0d) {
            arrayList.addAll(pointQuadTree.search(new Bounds(0.0d, d3 - 1.0d, visibleBounds.minY, visibleBounds.maxY)));
            visibleBounds = new Bounds(visibleBounds.minX, 1.0d, visibleBounds.minY, visibleBounds.maxY);
        }
        arrayList.addAll(pointQuadTree.search(visibleBounds));
        return arrayList;
    }

    @Override // com.google.maps.android.clustering.algo.ScreenBasedAlgorithm
    public void onCameraChange(CameraPosition cameraPosition) {
        this.mMapCenter = cameraPosition.target;
    }

    @Override // com.google.maps.android.clustering.algo.ScreenBasedAlgorithm
    public boolean shouldReclusterOnMapMovement() {
        return true;
    }

    public void updateViewSize(int i2, int i3) {
        this.mViewWidth = i2;
        this.mViewHeight = i3;
    }
}
