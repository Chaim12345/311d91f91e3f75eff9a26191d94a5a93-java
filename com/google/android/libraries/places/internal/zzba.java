package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
import java.util.Arrays;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes2.dex */
public final class zzba {
    private final String zza;

    private zzba(String str) {
        this.zza = str;
    }

    public static zzba zza(zzba zzbaVar, zzba... zzbaVarArr) {
        String valueOf = String.valueOf(zzbaVar.zza);
        String zzf = zzgv.zzc("").zzf(zzib.zza(Arrays.asList(zzbaVarArr), zzaz.zza));
        return new zzba(zzf.length() != 0 ? valueOf.concat(zzf) : new String(valueOf));
    }

    public static zzba zzb(String str) {
        return new zzba(str);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof zzba) {
            return this.zza.equals(((zzba) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return this.zza;
    }
}
