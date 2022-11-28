package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzae extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Map f7763a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7764b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzaw f7765c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzae(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, Map map, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7765c = zzawVar;
        this.f7763a = map;
        this.f7764b = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        try {
            zzasVar = this.f7765c.zzf;
            str = this.f7765c.zzc;
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zze(str, zzaw.d(this.f7763a), new zzao(this.f7765c, this.f7764b));
        } catch (RemoteException e2) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e2, "syncPacks", new Object[0]);
            this.f7764b.zzd(new RuntimeException(e2));
        }
    }
}
