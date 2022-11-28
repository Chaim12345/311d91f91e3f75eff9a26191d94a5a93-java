package com.google.android.play.core.assetpacks;

import android.os.Bundle;
/* loaded from: classes2.dex */
final class zzar extends zzal {

    /* renamed from: c  reason: collision with root package name */
    final int f7797c;

    /* renamed from: d  reason: collision with root package name */
    final String f7798d;

    /* renamed from: e  reason: collision with root package name */
    final int f7799e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzaw f7800f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzar(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, int i2, String str, int i3) {
        super(zzawVar, zziVar);
        this.f7800f = zzawVar;
        this.f7797c = i2;
        this.f7798d = str;
        this.f7799e = i3;
    }

    @Override // com.google.android.play.core.assetpacks.zzal, com.google.android.play.core.internal.zzw
    public final void zzd(Bundle bundle) {
        com.google.android.play.core.internal.zzas zzasVar;
        com.google.android.play.core.internal.zzag zzagVar;
        zzasVar = this.f7800f.zzf;
        zzasVar.zzs(this.f7793a);
        int i2 = bundle.getInt("error_code");
        zzagVar = zzaw.zza;
        zzagVar.zzb("onError(%d), retrying notifyModuleCompleted...", Integer.valueOf(i2));
        int i3 = this.f7799e;
        if (i3 > 0) {
            this.f7800f.zzD(this.f7797c, this.f7798d, i3 - 1);
        }
    }
}
