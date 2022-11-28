package com.google.android.libraries.places.internal;

import com.google.auto.value.AutoValue;
@AutoValue.Builder
/* loaded from: classes2.dex */
public abstract class zzes {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zzes zzb(int i2);

    abstract zzet zzc();

    public abstract zzes zzd(int i2);

    public final zzet zze() {
        zzet zzc = zzc();
        zzha.zzi(!zzc.zzb().isEmpty(), "Package name must not be empty.");
        return zzc;
    }
}
