package com.google.android.libraries.places.internal;

import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes2.dex */
public final class zzib {
    public static List zza(List list, zzaz zzazVar) {
        return list instanceof RandomAccess ? new zzhy(list, zzazVar, null) : new zzia(list, zzazVar, null);
    }
}
