package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class zzlf implements zzlk {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final List f6238a;

    public zzlf(Context context, zzle zzleVar) {
        ArrayList arrayList = new ArrayList();
        this.f6238a = arrayList;
        if (zzleVar.zzc()) {
            arrayList.add(new zzlt(context, zzleVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlk
    public final void zza(zzlc zzlcVar) {
        for (zzlk zzlkVar : this.f6238a) {
            zzlkVar.zza(zzlcVar);
        }
    }
}
