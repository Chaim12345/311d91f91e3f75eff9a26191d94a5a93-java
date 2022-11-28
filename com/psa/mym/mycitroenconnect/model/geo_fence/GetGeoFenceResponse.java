package com.psa.mym.mycitroenconnect.model.geo_fence;

import java.util.ArrayList;
/* loaded from: classes3.dex */
public final class GetGeoFenceResponse extends ArrayList<GetGeoFenceResponseItem> {
    public /* bridge */ boolean contains(GetGeoFenceResponseItem getGeoFenceResponseItem) {
        return super.contains((Object) getGeoFenceResponseItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof GetGeoFenceResponseItem) {
            return contains((GetGeoFenceResponseItem) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(GetGeoFenceResponseItem getGeoFenceResponseItem) {
        return super.indexOf((Object) getGeoFenceResponseItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof GetGeoFenceResponseItem) {
            return indexOf((GetGeoFenceResponseItem) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(GetGeoFenceResponseItem getGeoFenceResponseItem) {
        return super.lastIndexOf((Object) getGeoFenceResponseItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof GetGeoFenceResponseItem) {
            return lastIndexOf((GetGeoFenceResponseItem) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ GetGeoFenceResponseItem remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ boolean remove(GetGeoFenceResponseItem getGeoFenceResponseItem) {
        return super.remove((Object) getGeoFenceResponseItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof GetGeoFenceResponseItem) {
            return remove((GetGeoFenceResponseItem) obj);
        }
        return false;
    }

    public /* bridge */ GetGeoFenceResponseItem removeAt(int i2) {
        return (GetGeoFenceResponseItem) super.remove(i2);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
