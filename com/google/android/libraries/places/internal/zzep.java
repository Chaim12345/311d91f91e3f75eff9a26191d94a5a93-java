package com.google.android.libraries.places.internal;

import java.util.Objects;
/* loaded from: classes2.dex */
final class zzep extends zzes {
    private String zza;
    private int zzb;
    private byte zzc;
    private int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzes zza(String str) {
        Objects.requireNonNull(str, "Null packageName");
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzes
    final zzes zzb(int i2) {
        this.zzb = i2;
        this.zzc = (byte) 1;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzes
    final zzet zzc() {
        String str;
        int i2;
        if (this.zzc != 1 || (str = this.zza) == null || (i2 = this.zzd) == 0) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" packageName");
            }
            if (this.zzc == 0) {
                sb.append(" versionCode");
            }
            if (this.zzd == 0) {
                sb.append(" requestSource");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzer(str, this.zzb, i2, null);
    }

    @Override // com.google.android.libraries.places.internal.zzes
    public final zzes zzd(int i2) {
        this.zzd = i2;
        return this;
    }
}
