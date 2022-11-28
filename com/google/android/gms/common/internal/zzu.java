package com.google.android.gms.common.internal;

import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class zzu {
    @Nullable
    private final String zza;
    private final String zzb;
    private final int zzc;
    private final boolean zzd;

    public zzu(String str, @Nullable String str2, boolean z, int i2, boolean z2) {
        this.zzb = str;
        this.zza = str2;
        this.zzc = i2;
        this.zzd = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String b() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final String c() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean d() {
        return this.zzd;
    }
}
