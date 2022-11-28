package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzak extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7791a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzaw f7792b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzak(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7792b = zzawVar;
        this.f7791a = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzA;
        try {
            zzasVar = this.f7792b.zzg;
            str = this.f7792b.zzc;
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzf(str, zzA, new zzap(this.f7792b, this.f7791a));
        } catch (RemoteException e2) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e2, "keepAlive", new Object[0]);
        }
    }
}
