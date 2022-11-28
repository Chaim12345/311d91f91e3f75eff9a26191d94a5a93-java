package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;
/* loaded from: classes2.dex */
public class ScreenBasedAlgorithmAdapter<T extends ClusterItem> extends AbstractAlgorithm<T> implements ScreenBasedAlgorithm<T> {
    private Algorithm<T> mAlgorithm;

    public ScreenBasedAlgorithmAdapter(Algorithm<T> algorithm) {
        this.mAlgorithm = algorithm;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean addItem(T t2) {
        return this.mAlgorithm.addItem(t2);
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean addItems(Collection<T> collection) {
        return this.mAlgorithm.addItems(collection);
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void clearItems() {
        this.mAlgorithm.clearItems();
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Set<? extends Cluster<T>> getClusters(float f2) {
        return this.mAlgorithm.getClusters(f2);
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Collection<T> getItems() {
        return this.mAlgorithm.getItems();
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public int getMaxDistanceBetweenClusteredItems() {
        return this.mAlgorithm.getMaxDistanceBetweenClusteredItems();
    }

    @Override // com.google.maps.android.clustering.algo.ScreenBasedAlgorithm
    public void onCameraChange(CameraPosition cameraPosition) {
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean removeItem(T t2) {
        return this.mAlgorithm.removeItem(t2);
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean removeItems(Collection<T> collection) {
        return this.mAlgorithm.removeItems(collection);
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void setMaxDistanceBetweenClusteredItems(int i2) {
        this.mAlgorithm.setMaxDistanceBetweenClusteredItems(i2);
    }

    @Override // com.google.maps.android.clustering.algo.ScreenBasedAlgorithm
    public boolean shouldReclusterOnMapMovement() {
        return false;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean updateItem(T t2) {
        return this.mAlgorithm.updateItem(t2);
    }
}
