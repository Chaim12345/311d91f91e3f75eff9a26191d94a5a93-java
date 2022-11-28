package com.google.android.play.core.assetpacks;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public final class AssetPackManagerFactory {
    private AssetPackManagerFactory() {
    }

    @NonNull
    public static synchronized AssetPackManager getInstance(@NonNull Context context) {
        AssetPackManager zza;
        synchronized (AssetPackManagerFactory.class) {
            zza = zzd.a(context).zza();
        }
        return zza;
    }
}
