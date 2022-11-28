package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzca;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzas extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f7920a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7921b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzbc f7922c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzas(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar, int i2, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7922c = zzbcVar;
        this.f7920a = i2;
        this.f7921b = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        String str;
        try {
            str = this.f7922c.zzd;
            ((zzca) this.f7922c.f7925a.zze()).zzc(str, this.f7920a, zzbc.a(), new zzat(this.f7922c, this.f7921b));
        } catch (RemoteException e2) {
            zzagVar = zzbc.zzb;
            zzagVar.zzc(e2, "cancelInstall(%d)", Integer.valueOf(this.f7920a));
            this.f7921b.zzd(new RuntimeException(e2));
        }
    }
}
