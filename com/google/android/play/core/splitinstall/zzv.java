package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
/* loaded from: classes2.dex */
final class zzv implements zzf {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SplitInstallSessionState f7926a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Intent f7927b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Context f7928c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzx f7929d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzv(zzx zzxVar, SplitInstallSessionState splitInstallSessionState, Intent intent, Context context) {
        this.f7929d = zzxVar;
        this.f7926a = splitInstallSessionState;
        this.f7927b = intent;
        this.f7928c = context;
    }

    @Override // com.google.android.play.core.splitinstall.zzf
    public final void zza() {
        r0.zzd.post(new zzw(this.f7929d, this.f7926a, 5, 0));
    }

    @Override // com.google.android.play.core.splitinstall.zzf
    public final void zzb(@SplitInstallErrorCode int i2) {
        r0.zzd.post(new zzw(this.f7929d, this.f7926a, 6, i2));
    }

    @Override // com.google.android.play.core.splitinstall.zzf
    public final void zzc() {
        com.google.android.play.core.internal.zzag zzagVar;
        if (this.f7927b.getBooleanExtra("triggered_from_app_after_verification", false)) {
            zzagVar = ((com.google.android.play.core.listener.zzc) this.f7929d).f7869a;
            zzagVar.zzb("Splits copied and verified more than once.", new Object[0]);
            return;
        }
        this.f7927b.putExtra("triggered_from_app_after_verification", true);
        this.f7928c.sendBroadcast(this.f7927b);
    }
}
