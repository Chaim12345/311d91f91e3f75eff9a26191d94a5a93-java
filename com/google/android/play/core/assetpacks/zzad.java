package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
final class zzad extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f7760a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7761b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzaw f7762c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzad(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, List list, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7762c = zzawVar;
        this.f7760a = list;
        this.f7761b = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzA;
        ArrayList l2 = zzaw.l(this.f7760a);
        try {
            zzasVar = this.f7762c.zzf;
            str = this.f7762c.zzc;
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzc(str, l2, zzA, new zzam(this.f7762c, this.f7761b));
        } catch (RemoteException e2) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e2, "cancelDownloads(%s)", this.f7760a);
        }
    }
}
