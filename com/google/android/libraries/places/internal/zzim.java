package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Set;
/* loaded from: classes2.dex */
public final class zzim {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(Set set) {
        Iterator it = set.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i2 += next != null ? next.hashCode() : 0;
        }
        return i2;
    }
}
