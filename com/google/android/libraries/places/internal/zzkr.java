package com.google.android.libraries.places.internal;

import java.util.Collections;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes2.dex */
public final class zzkr {
    private static final Comparator zza = new zzkj();
    private static final Comparator zzb = new zzkk();
    private static final zzkr zzc = new zzkr(new zzkp(Collections.emptyList()));
    private final zzkp zzd;

    private zzkr(zzkp zzkpVar) {
        this.zzd = zzkpVar;
    }

    public static zzkr zza() {
        return zzc;
    }

    public final boolean equals(@NullableDecl Object obj) {
        return (obj instanceof zzkr) && ((zzkr) obj).zzd.equals(this.zzd);
    }

    public final int hashCode() {
        return ~this.zzd.hashCode();
    }

    public final String toString() {
        return this.zzd.toString();
    }
}
