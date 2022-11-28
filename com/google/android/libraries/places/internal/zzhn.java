package com.google.android.libraries.places.internal;

import java.io.Serializable;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzhn implements Comparable, Serializable {
    final Comparable zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhn(Comparable comparable) {
        this.zza = comparable;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzhn) {
            try {
                if (compareTo((zzhn) obj) == 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    public abstract int hashCode();

    @Override // java.lang.Comparable
    /* renamed from: zza */
    public int compareTo(zzhn zzhnVar) {
        zzhl zzhlVar;
        zzhj zzhjVar;
        zzhlVar = zzhl.zzb;
        if (zzhnVar != zzhlVar) {
            zzhjVar = zzhj.zzb;
            if (zzhnVar == zzhjVar) {
                return -1;
            }
            int zza = zzie.zza(this.zza, zzhnVar.zza);
            if (zza != 0) {
                return zza;
            }
            boolean z = this instanceof zzhk;
            if (z == (zzhnVar instanceof zzhk)) {
                return 0;
            }
            if (!z) {
                return -1;
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzc(StringBuilder sb);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzd(StringBuilder sb);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zze(Comparable comparable);
}
