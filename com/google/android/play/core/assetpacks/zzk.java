package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
/* loaded from: classes2.dex */
final class zzk extends ResultReceiver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7855a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzl f7856b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzk(zzl zzlVar, Handler handler, com.google.android.play.core.tasks.zzi zziVar) {
        super(handler);
        this.f7856b = zzlVar;
        this.f7855a = zziVar;
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i2, Bundle bundle) {
        zzbx zzbxVar;
        if (i2 == 1) {
            this.f7855a.zze(-1);
            zzbxVar = this.f7856b.zzh;
            zzbxVar.b(null);
        } else if (i2 != 2) {
            this.f7855a.zzd(new AssetPackException(-100));
        } else {
            this.f7855a.zze(0);
        }
    }
}
