package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
/* loaded from: classes2.dex */
final class zzd extends ResultReceiver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7738a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzd(zzf zzfVar, Handler handler, com.google.android.play.core.tasks.zzi zziVar) {
        super(handler);
        this.f7738a = zziVar;
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i2, Bundle bundle) {
        com.google.android.play.core.tasks.zzi zziVar;
        int i3 = 1;
        if (i2 == 1) {
            zziVar = this.f7738a;
            i3 = -1;
        } else if (i2 != 2) {
            zziVar = this.f7738a;
        } else {
            zziVar = this.f7738a;
            i3 = 0;
        }
        zziVar.zze(Integer.valueOf(i3));
    }
}
