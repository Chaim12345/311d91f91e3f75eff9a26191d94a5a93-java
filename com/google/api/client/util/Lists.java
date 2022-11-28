package com.google.api.client.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes2.dex */
public final class Lists {
    private Lists() {
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> iterable) {
        return iterable instanceof Collection ? new ArrayList<>(Collections2.a(iterable)) : newArrayList(iterable.iterator());
    }

    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> it) {
        ArrayList<E> newArrayList = newArrayList();
        while (it.hasNext()) {
            newArrayList.add(it.next());
        }
        return newArrayList;
    }

    public static <E> ArrayList<E> newArrayListWithCapacity(int i2) {
        return new ArrayList<>(i2);
    }
}
