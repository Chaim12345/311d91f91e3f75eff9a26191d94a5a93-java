package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
/* loaded from: classes2.dex */
final class zzai extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f7782a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7783b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzaw f7784c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzai(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, int i2, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7784c = zzawVar;
        this.f7782a = i2;
        this.f7783b = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzB;
        Bundle zzA;
        try {
            zzasVar = this.f7784c.zzf;
            str = this.f7784c.zzc;
            zzB = zzaw.zzB(this.f7782a);
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzi(str, zzB, zzA, new zzas(this.f7784c, this.f7783b));
        } catch (RemoteException e2) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e2, "notifySessionFailed", new Object[0]);
        }
    }
}
