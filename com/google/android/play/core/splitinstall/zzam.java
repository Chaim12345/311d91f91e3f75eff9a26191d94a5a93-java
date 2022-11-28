package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzca;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzam extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f7903a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7904b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzbc f7905c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzam(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar, List list, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7905c = zzbcVar;
        this.f7903a = list;
        this.f7904b = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        String str;
        try {
            str = this.f7905c.zzd;
            ((zzca) this.f7905c.f7925a.zze()).zzg(str, zzbc.e(this.f7903a), zzbc.a(), new zzax(this.f7905c, this.f7904b));
        } catch (RemoteException e2) {
            zzagVar = zzbc.zzb;
            zzagVar.zzc(e2, "deferredUninstall(%s)", this.f7903a);
            this.f7904b.zzd(new RuntimeException(e2));
        }
    }
}
