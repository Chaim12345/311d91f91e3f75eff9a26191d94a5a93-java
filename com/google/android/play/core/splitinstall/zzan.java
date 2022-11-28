package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzca;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzan extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f7906a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7907b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzbc f7908c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzan(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar, List list, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7908c = zzbcVar;
        this.f7906a = list;
        this.f7907b = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        String str;
        try {
            str = this.f7908c.zzd;
            ((zzca) this.f7908c.f7925a.zze()).zzd(str, zzbc.e(this.f7906a), zzbc.a(), new zzau(this.f7908c, this.f7907b));
        } catch (RemoteException e2) {
            zzagVar = zzbc.zzb;
            zzagVar.zzc(e2, "deferredInstall(%s)", this.f7906a);
            this.f7907b.zzd(new RuntimeException(e2));
        }
    }
}
