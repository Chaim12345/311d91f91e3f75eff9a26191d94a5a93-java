package com.google.android.play.core.review;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.common.PlayCoreVersion;
import com.google.android.play.core.internal.zzac;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzah;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzf extends zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7872a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzi f7873b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzf(zzi zziVar, com.google.android.play.core.tasks.zzi zziVar2, com.google.android.play.core.tasks.zzi zziVar3) {
        super(zziVar2);
        this.f7873b = zziVar;
        this.f7872a = zziVar3;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        zzag zzagVar;
        String str;
        String str2;
        String str3;
        try {
            str2 = this.f7873b.zzc;
            Bundle zza = PlayCoreVersion.zza("review");
            zzi zziVar = this.f7873b;
            com.google.android.play.core.tasks.zzi zziVar2 = this.f7872a;
            str3 = zziVar.zzc;
            ((zzac) this.f7873b.f7877a.zze()).zzc(str2, zza, new zzh(zziVar, zziVar2, str3));
        } catch (RemoteException e2) {
            zzagVar = zzi.zzb;
            str = this.f7873b.zzc;
            zzagVar.zzc(e2, "error requesting in-app review for %s", str);
            this.f7872a.zzd(new RuntimeException(e2));
        }
    }
}
