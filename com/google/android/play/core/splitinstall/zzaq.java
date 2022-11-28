package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzca;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzaq extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f7915a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7916b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzbc f7917c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaq(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar, int i2, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7917c = zzbcVar;
        this.f7915a = i2;
        this.f7916b = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        String str;
        try {
            zzbc zzbcVar = this.f7917c;
            str = zzbcVar.zzd;
            ((zzca) this.f7917c.f7925a.zze()).zzh(str, this.f7915a, new zzay(zzbcVar, this.f7916b));
        } catch (RemoteException e2) {
            zzagVar = zzbc.zzb;
            zzagVar.zzc(e2, "getSessionState(%d)", Integer.valueOf(this.f7915a));
            this.f7916b.zzd(new RuntimeException(e2));
        }
    }
}
