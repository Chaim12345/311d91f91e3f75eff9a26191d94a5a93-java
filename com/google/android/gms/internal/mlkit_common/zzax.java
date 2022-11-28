package com.google.android.gms.internal.mlkit_common;

import java.util.Iterator;
import java.util.Set;
/* loaded from: classes.dex */
public final class zzax {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Set set) {
        Iterator it = set.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i2 += next != null ? next.hashCode() : 0;
        }
        return i2;
    }
}
