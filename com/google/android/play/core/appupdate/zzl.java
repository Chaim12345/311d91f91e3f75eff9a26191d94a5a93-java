package com.google.android.play.core.appupdate;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzah;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzl extends zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f7740a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7741b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzq f7742c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzl(zzq zzqVar, com.google.android.play.core.tasks.zzi zziVar, String str, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7742c = zzqVar;
        this.f7740a = str;
        this.f7741b = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        zzag zzagVar;
        String str;
        try {
            zzq zzqVar = this.f7742c;
            str = zzqVar.zzd;
            ((com.google.android.play.core.internal.zzp) this.f7742c.f7750a.zze()).zzd(str, zzq.b(zzqVar, this.f7740a), new zzp(this.f7742c, this.f7741b, this.f7740a));
        } catch (RemoteException e2) {
            zzagVar = zzq.zzb;
            zzagVar.zzc(e2, "requestUpdateInfo(%s)", this.f7740a);
            this.f7741b.zzd(new RuntimeException(e2));
        }
    }
}
