package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public final class zznf implements zznl {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final List f6402a;

    public zznf(Context context, zzne zzneVar) {
        ArrayList arrayList = new ArrayList();
        this.f6402a = arrayList;
        if (zzneVar.zzc()) {
            arrayList.add(new zznu(context, zzneVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zznl
    public final void zza(zznp zznpVar) {
        for (zznl zznlVar : this.f6402a) {
            zznlVar.zza(zznpVar);
        }
    }
}
