package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import kotlinx.coroutines.DebugKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzkp implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final long f7005a;

    /* renamed from: b  reason: collision with root package name */
    final long f7006b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzkq f7007c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkp(zzkq zzkqVar, long j2, long j3) {
        this.f7007c = zzkqVar;
        this.f7005a = j2;
        this.f7006b = j3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f7007c.f7008a.f6809a.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzko
            @Override // java.lang.Runnable
            public final void run() {
                zzkp zzkpVar = zzkp.this;
                zzkq zzkqVar = zzkpVar.f7007c;
                long j2 = zzkpVar.f7005a;
                long j3 = zzkpVar.f7006b;
                zzkqVar.f7008a.zzg();
                zzkqVar.f7008a.f6809a.zzay().zzc().zza("Application going to the background");
                zzkqVar.f7008a.f6809a.zzm().zzl.zza(true);
                Bundle bundle = new Bundle();
                if (!zzkqVar.f7008a.f6809a.zzf().zzu()) {
                    zzkqVar.f7008a.f7015c.b(j3);
                    zzkqVar.f7008a.f7015c.zzd(false, false, j3);
                }
                zzkqVar.f7008a.f6809a.zzq().g(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ab", j2, bundle);
            }
        });
    }
}
