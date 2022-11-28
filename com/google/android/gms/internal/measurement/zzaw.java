package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class zzaw {

    /* renamed from: a  reason: collision with root package name */
    final List f5933a = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzap a(String str) {
        if (this.f5933a.contains(zzh.zze(str))) {
            throw new UnsupportedOperationException("Command not implemented: ".concat(String.valueOf(str)));
        }
        throw new IllegalArgumentException("Command not supported");
    }

    public abstract zzap zza(String str, zzg zzgVar, List list);
}
