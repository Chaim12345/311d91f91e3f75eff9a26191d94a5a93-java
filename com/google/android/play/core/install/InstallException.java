package com.google.android.play.core.install;

import com.google.android.play.core.install.model.InstallErrorCode;
import com.google.android.play.core.tasks.zzj;
/* loaded from: classes2.dex */
public class InstallException extends zzj {
    @InstallErrorCode
    private final int zza;

    public InstallException(@InstallErrorCode int i2) {
        super(String.format("Install Error(%d): %s", Integer.valueOf(i2), com.google.android.play.core.install.model.zza.zza(i2)));
        if (i2 == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
        this.zza = i2;
    }

    @Override // com.google.android.play.core.tasks.zzj
    @InstallErrorCode
    public int getErrorCode() {
        return this.zza;
    }
}
