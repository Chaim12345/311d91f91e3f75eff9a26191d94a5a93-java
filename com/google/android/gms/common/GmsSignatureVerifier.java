package com.google.android.gms.common;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.common.zzag;
@ShowFirstParty
@KeepForSdk
/* loaded from: classes.dex */
public class GmsSignatureVerifier {
    private static final zzz zza;
    private static final zzz zzb;

    static {
        zzx zzxVar = new zzx();
        zzxVar.d("com.google.android.gms");
        zzxVar.a(204200000L);
        zzk zzkVar = zzm.f5824d;
        zzxVar.c(zzag.zzn(zzkVar.c(), zzm.f5822b.c()));
        zzk zzkVar2 = zzm.f5823c;
        zzxVar.b(zzag.zzn(zzkVar2.c(), zzm.f5821a.c()));
        zza = zzxVar.e();
        zzx zzxVar2 = new zzx();
        zzxVar2.d("com.android.vending");
        zzxVar2.a(82240000L);
        zzxVar2.c(zzag.zzm(zzkVar.c()));
        zzxVar2.b(zzag.zzm(zzkVar2.c()));
        zzb = zzxVar2.e();
    }
}
