package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzah;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzm extends zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7743a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f7744b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzq f7745c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzm(zzq zzqVar, com.google.android.play.core.tasks.zzi zziVar, com.google.android.play.core.tasks.zzi zziVar2, String str) {
        super(zziVar);
        this.f7745c = zzqVar;
        this.f7743a = zziVar2;
        this.f7744b = str;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        zzag zzagVar;
        String str;
        Bundle zzi;
        try {
            str = this.f7745c.zzd;
            zzi = zzq.zzi();
            ((com.google.android.play.core.internal.zzp) this.f7745c.f7750a.zze()).zzc(str, zzi, new zzo(this.f7745c, this.f7743a));
        } catch (RemoteException e2) {
            zzagVar = zzq.zzb;
            zzagVar.zzc(e2, "completeUpdate(%s)", this.f7744b);
            this.f7743a.zzd(new RuntimeException(e2));
        }
    }
}
