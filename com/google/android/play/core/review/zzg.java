package com.google.android.play.core.review;

import android.os.Bundle;
import com.google.android.play.core.internal.zzad;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzas;
/* loaded from: classes2.dex */
class zzg extends zzad {

    /* renamed from: a  reason: collision with root package name */
    final zzag f7874a;

    /* renamed from: b  reason: collision with root package name */
    final com.google.android.play.core.tasks.zzi f7875b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzi f7876c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(zzi zziVar, zzag zzagVar, com.google.android.play.core.tasks.zzi zziVar2) {
        this.f7876c = zziVar;
        this.f7874a = zzagVar;
        this.f7875b = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzae
    public void zzb(Bundle bundle) {
        zzas zzasVar = this.f7876c.f7877a;
        if (zzasVar != null) {
            zzasVar.zzs(this.f7875b);
        }
        this.f7874a.zzd("onGetLaunchReviewFlowInfo", new Object[0]);
    }
}
