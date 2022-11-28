package com.psa.mym.mycitroenconnect.model.secondary_user;

import java.util.ArrayList;
/* loaded from: classes3.dex */
public final class MyCars extends ArrayList<MyCar> {
    public /* bridge */ boolean contains(MyCar myCar) {
        return super.contains((Object) myCar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof MyCar) {
            return contains((MyCar) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(MyCar myCar) {
        return super.indexOf((Object) myCar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof MyCar) {
            return indexOf((MyCar) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(MyCar myCar) {
        return super.lastIndexOf((Object) myCar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof MyCar) {
            return lastIndexOf((MyCar) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ MyCar remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ boolean remove(MyCar myCar) {
        return super.remove((Object) myCar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof MyCar) {
            return remove((MyCar) obj);
        }
        return false;
    }

    public /* bridge */ MyCar removeAt(int i2) {
        return (MyCar) super.remove(i2);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
