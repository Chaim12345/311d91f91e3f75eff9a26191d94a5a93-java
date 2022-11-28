package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzca;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzao extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f7909a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7910b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzbc f7911c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzao(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar, List list, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7911c = zzbcVar;
        this.f7909a = list;
        this.f7910b = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        String str;
        try {
            str = this.f7911c.zzd;
            ((zzca) this.f7911c.f7925a.zze()).zze(str, zzbc.d(this.f7909a), zzbc.a(), new zzav(this.f7911c, this.f7910b));
        } catch (RemoteException e2) {
            zzagVar = zzbc.zzb;
            zzagVar.zzc(e2, "deferredLanguageInstall(%s)", this.f7909a);
            this.f7910b.zzd(new RuntimeException(e2));
        }
    }
}
