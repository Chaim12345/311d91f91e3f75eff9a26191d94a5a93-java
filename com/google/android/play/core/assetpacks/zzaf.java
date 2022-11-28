package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzaf extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f7766a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Map f7767b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7768c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzbe f7769d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzaw f7770e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaf(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, List list, Map map, com.google.android.play.core.tasks.zzi zziVar2, zzbe zzbeVar) {
        super(zziVar);
        this.f7770e = zzawVar;
        this.f7766a = list;
        this.f7767b = map;
        this.f7768c = zziVar2;
        this.f7769d = zzbeVar;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        zzco zzcoVar;
        zzeb zzebVar;
        ArrayList l2 = zzaw.l(this.f7766a);
        try {
            zzasVar = this.f7770e.zzf;
            str = this.f7770e.zzc;
            Bundle d2 = zzaw.d(this.f7767b);
            zzaw zzawVar = this.f7770e;
            com.google.android.play.core.tasks.zzi zziVar = this.f7768c;
            zzcoVar = zzawVar.zzd;
            zzebVar = zzawVar.zze;
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzk(str, l2, d2, new zzau(zzawVar, zziVar, zzcoVar, zzebVar, this.f7769d));
        } catch (RemoteException e2) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e2, "getPackStates(%s)", this.f7766a);
            this.f7768c.zzd(new RuntimeException(e2));
        }
    }
}
