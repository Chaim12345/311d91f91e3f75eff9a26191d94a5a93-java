package com.google.android.play.core.appupdate;

import android.os.Bundle;
import com.google.android.play.core.internal.zzag;
/* loaded from: classes2.dex */
class zzn extends com.google.android.play.core.internal.zzq {

    /* renamed from: a  reason: collision with root package name */
    final zzag f7746a;

    /* renamed from: b  reason: collision with root package name */
    final com.google.android.play.core.tasks.zzi f7747b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzq f7748c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(zzq zzqVar, zzag zzagVar, com.google.android.play.core.tasks.zzi zziVar) {
        this.f7748c = zzqVar;
        this.f7746a = zzagVar;
        this.f7747b = zziVar;
    }

    @Override // com.google.android.play.core.internal.zzr
    public void zzb(Bundle bundle) {
        this.f7748c.f7750a.zzs(this.f7747b);
        this.f7746a.zzd("onCompleteUpdate", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.zzr
    public void zzc(Bundle bundle) {
        this.f7748c.f7750a.zzs(this.f7747b);
        this.f7746a.zzd("onRequestInfo", new Object[0]);
    }
}
