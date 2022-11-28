package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class Platform {
    private Platform() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] a(Object[] objArr, int i2, int i3, Object[] objArr2) {
        return Arrays.copyOfRange(objArr, i2, i3, objArr2.getClass());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] b(Object[] objArr, int i2) {
        return (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map c(int i2) {
        return CompactHashMap.createWithExpectedSize(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set d(int i2) {
        return CompactHashSet.createWithExpectedSize(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map e(int i2) {
        return CompactLinkedHashMap.createWithExpectedSize(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set f(int i2) {
        return CompactLinkedHashSet.createWithExpectedSize(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set g() {
        return CompactHashSet.create();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map h() {
        return CompactHashMap.create();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MapMaker i(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }
}
