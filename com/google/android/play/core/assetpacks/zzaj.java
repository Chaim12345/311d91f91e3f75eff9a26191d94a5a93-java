package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
/* loaded from: classes2.dex */
final class zzaj extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f7785a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f7786b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f7787c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ int f7788d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7789e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzaw f7790f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaj(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, int i2, String str, String str2, int i3, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7790f = zzawVar;
        this.f7785a = i2;
        this.f7786b = str;
        this.f7787c = str2;
        this.f7788d = i3;
        this.f7789e = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzA;
        try {
            zzasVar = this.f7790f.zzf;
            str = this.f7790f.zzc;
            Bundle a2 = zzaw.a(this.f7785a, this.f7786b, this.f7787c, this.f7788d);
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzd(str, a2, zzA, new zzan(this.f7790f, this.f7789e));
        } catch (RemoteException e2) {
            zzagVar = zzaw.zza;
            zzagVar.zzb("getChunkFileDescriptor(%s, %s, %d, session=%d)", this.f7786b, this.f7787c, Integer.valueOf(this.f7788d), Integer.valueOf(this.f7785a));
            this.f7789e.zzd(new RuntimeException(e2));
        }
    }
}
