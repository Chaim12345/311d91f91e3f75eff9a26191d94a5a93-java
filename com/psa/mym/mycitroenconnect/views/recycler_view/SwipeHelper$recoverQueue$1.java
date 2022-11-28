package com.psa.mym.mycitroenconnect.views.recycler_view;

import java.util.LinkedList;
/* loaded from: classes3.dex */
public final class SwipeHelper$recoverQueue$1 extends LinkedList<Integer> {
    public boolean add(int i2) {
        if (contains((Object) Integer.valueOf(i2))) {
            return false;
        }
        return super.add((SwipeHelper$recoverQueue$1) Integer.valueOf(i2));
    }

    @Override // java.util.LinkedList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.Deque, java.util.Queue
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        return add(((Number) obj).intValue());
    }

    public /* bridge */ boolean contains(Integer num) {
        return super.contains((Object) num);
    }

    @Override // java.util.LinkedList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.Deque
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Integer) {
            return contains((Integer) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(Integer num) {
        return super.indexOf((Object) num);
    }

    @Override // java.util.LinkedList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Integer) {
            return indexOf((Integer) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(Integer num) {
        return super.lastIndexOf((Object) num);
    }

    @Override // java.util.LinkedList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Integer) {
            return lastIndexOf((Integer) obj);
        }
        return -1;
    }

    @Override // java.util.LinkedList, java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final /* bridge */ Integer remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ boolean remove(Integer num) {
        return super.remove((Object) num);
    }

    @Override // java.util.LinkedList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.Deque
    public final /* bridge */ boolean remove(Object obj) {
        if (obj == null ? true : obj instanceof Integer) {
            return remove((Integer) obj);
        }
        return false;
    }

    public /* bridge */ Integer removeAt(int i2) {
        return (Integer) super.remove(i2);
    }

    @Override // java.util.LinkedList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.Deque
    public final /* bridge */ int size() {
        return getSize();
    }
}
