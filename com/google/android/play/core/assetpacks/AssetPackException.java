package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.AssetPackErrorCode;
/* loaded from: classes2.dex */
public class AssetPackException extends com.google.android.play.core.tasks.zzj {
    @AssetPackErrorCode
    private final int zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssetPackException(@AssetPackErrorCode int i2) {
        super(String.format("Asset Pack Download Error(%d): %s", Integer.valueOf(i2), com.google.android.play.core.assetpacks.model.zza.zza(i2)));
        if (i2 == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
        this.zza = i2;
    }

    @Override // com.google.android.play.core.tasks.zzj
    @AssetPackErrorCode
    public int getErrorCode() {
        return this.zza;
    }
}
