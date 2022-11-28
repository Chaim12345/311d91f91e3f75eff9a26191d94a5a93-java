package com.google.android.libraries.places.internal;

import java.util.Arrays;
/* loaded from: classes2.dex */
public final class zzafu {
    private static final zzafu zza = new zzafu(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;

    private zzafu() {
        this(0, new int[8], new Object[8], true);
    }

    private zzafu(int i2, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = 0;
        this.zzc = iArr;
        this.zzd = objArr;
    }

    public static zzafu zzc() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzafu zzd(zzafu zzafuVar, zzafu zzafuVar2) {
        int i2 = zzafuVar.zzb;
        int i3 = zzafuVar2.zzb;
        int[] copyOf = Arrays.copyOf(zzafuVar.zzc, 0);
        System.arraycopy(zzafuVar2.zzc, 0, copyOf, 0, 0);
        Object[] copyOf2 = Arrays.copyOf(zzafuVar.zzd, 0);
        System.arraycopy(zzafuVar2.zzd, 0, copyOf2, 0, 0);
        return new zzafu(0, copyOf, copyOf2, true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzafu)) {
            zzafu zzafuVar = (zzafu) obj;
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return 506991;
    }

    public final int zza() {
        int i2 = this.zze;
        if (i2 == -1) {
            this.zze = 0;
            return 0;
        }
        return i2;
    }

    public final int zzb() {
        int i2 = this.zze;
        if (i2 == -1) {
            this.zze = 0;
            return 0;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze(StringBuilder sb, int i2) {
    }
}
