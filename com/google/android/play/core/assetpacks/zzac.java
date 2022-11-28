package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzac extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f7755a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Map f7756b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7757c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ List f7758d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzaw f7759e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzac(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, List list, Map map, com.google.android.play.core.tasks.zzi zziVar2, List list2) {
        super(zziVar);
        this.f7759e = zzawVar;
        this.f7755a = list;
        this.f7756b = map;
        this.f7757c = zziVar2;
        this.f7758d = list2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        zzco zzcoVar;
        zzeb zzebVar;
        ArrayList l2 = zzaw.l(this.f7755a);
        try {
            zzasVar = this.f7759e.zzf;
            str = this.f7759e.zzc;
            Bundle d2 = zzaw.d(this.f7756b);
            zzaw zzawVar = this.f7759e;
            com.google.android.play.core.tasks.zzi zziVar = this.f7757c;
            zzcoVar = zzawVar.zzd;
            zzebVar = zzawVar.zze;
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzl(str, l2, d2, new zzav(zzawVar, zziVar, zzcoVar, zzebVar, this.f7758d));
        } catch (RemoteException e2) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e2, "startDownload(%s)", this.f7755a);
            this.f7757c.zzd(new RuntimeException(e2));
        }
    }
}
