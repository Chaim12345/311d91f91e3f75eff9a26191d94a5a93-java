package com.google.android.play.core.assetpacks;

import androidx.annotation.Nullable;
import com.google.android.play.core.assetpacks.model.AssetPackStorageMethod;
/* loaded from: classes2.dex */
public abstract class AssetPackLocation {
    private static final AssetPackLocation zza = new zzbm(1, null, null);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AssetPackLocation a() {
        return zza;
    }

    @Nullable
    public abstract String assetsPath();

    @AssetPackStorageMethod
    public abstract int packStorageMethod();

    @Nullable
    public abstract String path();
}
