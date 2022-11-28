package com.google.android.play.core.appupdate;

import android.os.Bundle;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.zzag;
/* loaded from: classes2.dex */
final class zzo extends zzn {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(zzq zzqVar, com.google.android.play.core.tasks.zzi zziVar) {
        super(zzqVar, new zzag("OnCompleteUpdateCallback"), zziVar);
    }

    @Override // com.google.android.play.core.appupdate.zzn, com.google.android.play.core.internal.zzr
    public final void zzb(Bundle bundle) {
        int i2;
        int i3;
        super.zzb(bundle);
        i2 = bundle.getInt("error.code", -2);
        if (i2 == 0) {
            this.f7747b.zze(null);
            return;
        }
        com.google.android.play.core.tasks.zzi zziVar = this.f7747b;
        i3 = bundle.getInt("error.code", -2);
        zziVar.zzd(new InstallException(i3));
    }
}
