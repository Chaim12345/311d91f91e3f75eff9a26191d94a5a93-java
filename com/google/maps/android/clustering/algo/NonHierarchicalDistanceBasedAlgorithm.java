package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
/* loaded from: classes2.dex */
public class NonHierarchicalDistanceBasedAlgorithm<T extends ClusterItem> extends AbstractAlgorithm<T> {
    private static final int DEFAULT_MAX_DISTANCE_AT_ZOOM = 100;
    private static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1.0d);
    private int mMaxDistance = 100;
    private final Collection<QuadItem<T>> mItems = new LinkedHashSet();
    private final PointQuadTree<QuadItem<T>> mQuadTree = new PointQuadTree<>(0.0d, 1.0d, 0.0d, 1.0d);

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static class QuadItem<T extends ClusterItem> implements PointQuadTree.Item, Cluster<T> {
        private final T mClusterItem;
        private final Point mPoint;
        private final LatLng mPosition;
        private Set<T> singletonSet;

        private QuadItem(T t2) {
            this.mClusterItem = t2;
            LatLng position = t2.getPosition();
            this.mPosition = position;
            this.mPoint = NonHierarchicalDistanceBasedAlgorithm.PROJECTION.toPoint(position);
            this.singletonSet = Collections.singleton(t2);
        }

        public boolean equals(Object obj) {
            if (obj instanceof QuadItem) {
                return ((QuadItem) obj).mClusterItem.equals(this.mClusterItem);
            }
            return false;
        }

        @Override // com.google.maps.android.clustering.Cluster
        public Set<T> getItems() {
            return this.singletonSet;
        }

        @Override // com.google.maps.android.quadtree.PointQuadTree.Item
        public Point getPoint() {
            return this.mPoint;
        }

        @Override // com.google.maps.android.clustering.Cluster
        public LatLng getPosition() {
            return this.mPosition;
        }

        @Override // com.google.maps.android.clustering.Cluster
        public int getSize() {
            return 1;
        }

        public int hashCode() {
            return this.mClusterItem.hashCode();
        }
    }

    private Bounds createBoundsFromSpan(Point point, double d2) {
        double d3 = d2 / 2.0d;
        double d4 = point.x;
        double d5 = d4 - d3;
        double d6 = d4 + d3;
        double d7 = point.y;
        return new Bounds(d5, d6, d7 - d3, d7 + d3);
    }

    private double distanceSquared(Point point, Point point2) {
        double d2 = point.x;
        double d3 = point2.x;
        double d4 = (d2 - d3) * (d2 - d3);
        double d5 = point.y;
        double d6 = point2.y;
        return d4 + ((d5 - d6) * (d5 - d6));
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean addItem(T t2) {
        boolean add;
        QuadItem<T> quadItem = new QuadItem<>(t2);
        synchronized (this.mQuadTree) {
            add = this.mItems.add(quadItem);
            if (add) {
                this.mQuadTree.add(quadItem);
            }
        }
        return add;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean addItems(Collection<T> collection) {
        boolean z = false;
        for (T t2 : collection) {
            if (addItem(t2)) {
                z = true;
            }
        }
        return z;
    }

    protected Collection b(PointQuadTree pointQuadTree, float f2) {
        return this.mItems;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void clearItems() {
        synchronized (this.mQuadTree) {
            this.mItems.clear();
            this.mQuadTree.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Set<? extends Cluster<T>> getClusters(float f2) {
        double pow = (this.mMaxDistance / Math.pow(2.0d, (int) f2)) / 256.0d;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        synchronized (this.mQuadTree) {
            Iterator it = b(this.mQuadTree, f2).iterator();
            while (it.hasNext()) {
                QuadItem quadItem = (QuadItem) it.next();
                if (!hashSet.contains(quadItem)) {
                    Collection<QuadItem<T>> search = this.mQuadTree.search(createBoundsFromSpan(quadItem.getPoint(), pow));
                    if (search.size() == 1) {
                        hashSet2.add(quadItem);
                        hashSet.add(quadItem);
                        hashMap.put(quadItem, Double.valueOf(0.0d));
                    } else {
                        StaticCluster staticCluster = new StaticCluster(quadItem.mClusterItem.getPosition());
                        hashSet2.add(staticCluster);
                        for (QuadItem<T> quadItem2 : search) {
                            Double d2 = (Double) hashMap.get(quadItem2);
                            Iterator it2 = it;
                            double distanceSquared = distanceSquared(quadItem2.getPoint(), quadItem.getPoint());
                            if (d2 != null) {
                                if (d2.doubleValue() < distanceSquared) {
                                    it = it2;
                                } else {
                                    ((StaticCluster) hashMap2.get(quadItem2)).remove(((QuadItem) quadItem2).mClusterItem);
                                }
                            }
                            hashMap.put(quadItem2, Double.valueOf(distanceSquared));
                            staticCluster.add(((QuadItem) quadItem2).mClusterItem);
                            hashMap2.put(quadItem2, staticCluster);
                            it = it2;
                        }
                        hashSet.addAll(search);
                        it = it;
                    }
                }
            }
        }
        return hashSet2;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Collection<T> getItems() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        synchronized (this.mQuadTree) {
            for (QuadItem<T> quadItem : this.mItems) {
                linkedHashSet.add(((QuadItem) quadItem).mClusterItem);
            }
        }
        return linkedHashSet;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public int getMaxDistanceBetweenClusteredItems() {
        return this.mMaxDistance;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean removeItem(T t2) {
        boolean remove;
        QuadItem<T> quadItem = new QuadItem<>(t2);
        synchronized (this.mQuadTree) {
            remove = this.mItems.remove(quadItem);
            if (remove) {
                this.mQuadTree.remove(quadItem);
            }
        }
        return remove;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean removeItems(Collection<T> collection) {
        boolean z;
        synchronized (this.mQuadTree) {
            z = false;
            for (T t2 : collection) {
                QuadItem<T> quadItem = new QuadItem<>(t2);
                if (this.mItems.remove(quadItem)) {
                    this.mQuadTree.remove(quadItem);
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void setMaxDistanceBetweenClusteredItems(int i2) {
        this.mMaxDistance = i2;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean updateItem(T t2) {
        boolean removeItem;
        synchronized (this.mQuadTree) {
            removeItem = removeItem(t2);
            if (removeItem) {
                removeItem = addItem(t2);
            }
        }
        return removeItem;
    }
}
