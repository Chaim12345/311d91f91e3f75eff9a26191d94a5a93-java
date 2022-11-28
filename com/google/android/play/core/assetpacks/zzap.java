package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes2.dex */
final class zzap extends zzal {

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzaw f7796c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzap(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar) {
        super(zzawVar, zziVar);
        this.f7796c = zzawVar;
    }

    @Override // com.google.android.play.core.assetpacks.zzal, com.google.android.play.core.internal.zzw
    public final void zzd(Bundle bundle) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.f7796c.zzg;
        zzasVar.zzs(this.f7793a);
        int i2 = bundle.getInt("error_code");
        zzagVar = zzaw.zza;
        zzagVar.zzb("onError(%d)", Integer.valueOf(i2));
        this.f7793a.zzd(new AssetPackException(i2));
    }

    @Override // com.google.android.play.core.assetpacks.zzal, com.google.android.play.core.internal.zzw
    public final void zzh(Bundle bundle, Bundle bundle2) {
        AtomicBoolean atomicBoolean;
        com.google.android.play.core.internal.zzag zzagVar;
        super.zzh(bundle, bundle2);
        atomicBoolean = this.f7796c.zzh;
        if (!atomicBoolean.compareAndSet(true, false)) {
            zzagVar = zzaw.zza;
            zzagVar.zze("Expected keepingAlive to be true, but was false.", new Object[0]);
        }
        if (bundle.getBoolean("keep_alive")) {
            this.f7796c.zzf();
        }
    }
}
