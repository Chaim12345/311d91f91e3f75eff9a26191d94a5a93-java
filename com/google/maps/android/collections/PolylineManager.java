package com.google.maps.android.collections;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.collections.MapObjectManager;
/* loaded from: classes2.dex */
public class PolylineManager extends MapObjectManager<Polyline, Collection> implements GoogleMap.OnPolylineClickListener {

    /* loaded from: classes2.dex */
    public class Collection extends MapObjectManager.Collection {
        private GoogleMap.OnPolylineClickListener mPolylineClickListener;

        public Collection() {
            super();
        }

        public void addAll(java.util.Collection<PolylineOptions> collection) {
            for (PolylineOptions polylineOptions : collection) {
                addPolyline(polylineOptions);
            }
        }

        public void addAll(java.util.Collection<PolylineOptions> collection, boolean z) {
            for (PolylineOptions polylineOptions : collection) {
                addPolyline(polylineOptions).setVisible(z);
            }
        }

        public Polyline addPolyline(PolylineOptions polylineOptions) {
            Polyline addPolyline = PolylineManager.this.f10300a.addPolyline(polylineOptions);
            super.a(addPolyline);
            return addPolyline;
        }

        public java.util.Collection<Polyline> getPolylines() {
            return b();
        }

        public void hideAll() {
            for (Polyline polyline : getPolylines()) {
                polyline.setVisible(false);
            }
        }

        public boolean remove(Polyline polyline) {
            return super.c(polyline);
        }

        public void setOnPolylineClickListener(GoogleMap.OnPolylineClickListener onPolylineClickListener) {
            this.mPolylineClickListener = onPolylineClickListener;
        }

        public void showAll() {
            for (Polyline polyline : getPolylines()) {
                polyline.setVisible(true);
            }
        }
    }

    public PolylineManager(@NonNull GoogleMap googleMap) {
        super(googleMap);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    void b() {
        GoogleMap googleMap = this.f10300a;
        if (googleMap != null) {
            googleMap.setOnPolylineClickListener(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.maps.android.collections.MapObjectManager
    /* renamed from: c */
    public void a(Polyline polyline) {
        polyline.remove();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.android.collections.PolylineManager$Collection, com.google.maps.android.collections.MapObjectManager$Collection] */
    @Override // com.google.maps.android.collections.MapObjectManager
    public /* bridge */ /* synthetic */ Collection getCollection(String str) {
        return super.getCollection(str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.android.collections.PolylineManager$Collection, com.google.maps.android.collections.MapObjectManager$Collection] */
    @Override // com.google.maps.android.collections.MapObjectManager
    public /* bridge */ /* synthetic */ Collection newCollection(String str) {
        return super.newCollection(str);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public Collection newCollection() {
        return new Collection();
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnPolylineClickListener
    public void onPolylineClick(Polyline polyline) {
        Collection collection = (Collection) this.f10301b.get(polyline);
        if (collection == null || collection.mPolylineClickListener == null) {
            return;
        }
        collection.mPolylineClickListener.onPolylineClick(polyline);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public /* bridge */ /* synthetic */ boolean remove(Polyline polyline) {
        return super.remove(polyline);
    }
}
