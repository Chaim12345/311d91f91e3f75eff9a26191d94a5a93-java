package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzbg implements zzcp {
    @CheckForNull
    private transient Set zza;
    @CheckForNull
    private transient Map zzb;

    abstract Map a();

    abstract Set b();

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzcp) {
            return zzp().equals(((zzcp) obj).zzp());
        }
        return false;
    }

    public final int hashCode() {
        return zzp().hashCode();
    }

    public final String toString() {
        return ((zzaw) zzp()).f6249a.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcp
    public boolean zzo(Object obj, Object obj2) {
        throw null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcp
    public final Map zzp() {
        Map map = this.zzb;
        if (map == null) {
            Map a2 = a();
            this.zzb = a2;
            return a2;
        }
        return map;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcp
    public final Set zzq() {
        Set set = this.zza;
        if (set == null) {
            Set b2 = b();
            this.zza = b2;
            return b2;
        }
        return set;
    }
}
