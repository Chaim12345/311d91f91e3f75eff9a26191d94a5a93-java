package com.google.android.play.core.splitinstall;

import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
/* loaded from: classes2.dex */
public class SplitInstallException extends com.google.android.play.core.tasks.zzj {
    @SplitInstallErrorCode
    private final int zza;

    public SplitInstallException(@SplitInstallErrorCode int i2) {
        super(String.format("Split Install Error(%d): %s", Integer.valueOf(i2), com.google.android.play.core.splitinstall.model.zza.zzb(i2)));
        if (i2 == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
        this.zza = i2;
    }

    @Override // com.google.android.play.core.tasks.zzj
    @SplitInstallErrorCode
    public int getErrorCode() {
        return this.zza;
    }
}
