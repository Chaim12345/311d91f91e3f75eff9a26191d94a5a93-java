package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzah extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f7777a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f7778b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7779c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ int f7780d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzaw f7781e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzah(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, int i2, String str, com.google.android.play.core.tasks.zzi zziVar2, int i3) {
        super(zziVar);
        this.f7781e = zzawVar;
        this.f7777a = i2;
        this.f7778b = str;
        this.f7779c = zziVar2;
        this.f7780d = i3;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzz;
        Bundle zzA;
        try {
            zzasVar = this.f7781e.zzf;
            str = this.f7781e.zzc;
            zzz = zzaw.zzz(this.f7777a, this.f7778b);
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzh(str, zzz, zzA, new zzar(this.f7781e, this.f7779c, this.f7777a, this.f7778b, this.f7780d));
        } catch (RemoteException e2) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e2, "notifyModuleCompleted", new Object[0]);
        }
    }
}
