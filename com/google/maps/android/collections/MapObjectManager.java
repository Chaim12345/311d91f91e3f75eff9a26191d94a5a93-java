package com.google.maps.android.collections;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.collections.MapObjectManager.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class MapObjectManager<O, C extends Collection> {

    /* renamed from: a  reason: collision with root package name */
    protected final GoogleMap f10300a;
    private final Map<String, C> mNamedCollections = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    protected final Map f10301b = new HashMap();

    /* loaded from: classes2.dex */
    public class Collection {
        private final Set<O> mObjects = new HashSet();

        public Collection() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void a(Object obj) {
            this.mObjects.add(obj);
            MapObjectManager.this.f10301b.put(obj, this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public java.util.Collection b() {
            return Collections.unmodifiableCollection(this.mObjects);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean c(Object obj) {
            if (this.mObjects.remove(obj)) {
                MapObjectManager.this.f10301b.remove(obj);
                MapObjectManager.this.a(obj);
                return true;
            }
            return false;
        }

        public void clear() {
            for (O o2 : this.mObjects) {
                MapObjectManager.this.a(o2);
                MapObjectManager.this.f10301b.remove(o2);
            }
            this.mObjects.clear();
        }
    }

    public MapObjectManager(@NonNull GoogleMap googleMap) {
        this.f10300a = googleMap;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.maps.android.collections.MapObjectManager.1
            @Override // java.lang.Runnable
            public void run() {
                MapObjectManager.this.b();
            }
        });
    }

    protected abstract void a(Object obj);

    abstract void b();

    public C getCollection(String str) {
        return this.mNamedCollections.get(str);
    }

    public abstract C newCollection();

    public C newCollection(String str) {
        if (this.mNamedCollections.get(str) == null) {
            C newCollection = newCollection();
            this.mNamedCollections.put(str, newCollection);
            return newCollection;
        }
        throw new IllegalArgumentException("collection id is not unique: " + str);
    }

    public boolean remove(O o2) {
        Collection collection = (Collection) this.f10301b.get(o2);
        return collection != null && collection.c(o2);
    }
}
