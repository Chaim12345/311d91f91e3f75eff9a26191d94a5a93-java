package com.google.android.gms.internal.mlkit_common;

import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes.dex */
public abstract class zzap extends zzai implements Set {
    @CheckForNull
    private transient zzam zza;

    zzam e() {
        return zzam.e(toArray());
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzax.a(this);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzai, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zzd */
    public abstract zzay iterator();

    public final zzam zzf() {
        zzam zzamVar = this.zza;
        if (zzamVar == null) {
            zzam e2 = e();
            this.zza = e2;
            return e2;
        }
        return zzamVar;
    }
}
