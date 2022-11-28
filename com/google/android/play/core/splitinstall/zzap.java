package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzca;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzap extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f7912a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7913b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzbc f7914c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzap(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar, List list, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7914c = zzbcVar;
        this.f7912a = list;
        this.f7913b = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        String str;
        try {
            str = this.f7914c.zzd;
            ((zzca) this.f7914c.f7925a.zze()).zzf(str, zzbc.d(this.f7912a), zzbc.a(), new zzaw(this.f7914c, this.f7913b));
        } catch (RemoteException e2) {
            zzagVar = zzbc.zzb;
            zzagVar.zzc(e2, "deferredLanguageUninstall(%s)", this.f7912a);
            this.f7913b.zzd(new RuntimeException(e2));
        }
    }
}
