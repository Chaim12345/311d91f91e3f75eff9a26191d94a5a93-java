package com.google.android.play.core.splitinstall;

import android.os.Bundle;
/* loaded from: classes2.dex */
final class zzay extends zzbb {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzay(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar) {
        super(zzbcVar, zziVar);
    }

    @Override // com.google.android.play.core.splitinstall.zzbb, com.google.android.play.core.internal.zzcc
    public final void zzg(int i2, Bundle bundle) {
        super.zzg(i2, bundle);
        this.f7923a.zze(SplitInstallSessionState.zzd(bundle));
    }
}
