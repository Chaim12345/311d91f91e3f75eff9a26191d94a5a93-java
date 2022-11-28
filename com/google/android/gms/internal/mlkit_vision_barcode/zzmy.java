package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes2.dex */
public final class zzmy {
    private final zzcc zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzmy(zzmw zzmwVar, zzmx zzmxVar) {
        zzcc zzccVar;
        zzccVar = zzmwVar.zza;
        this.zza = zzccVar;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzmy) {
            return Objects.equal(this.zza, ((zzmy) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza);
    }

    @Nullable
    @zzdj(zza = 1)
    public final zzcc zza() {
        return this.zza;
    }
}
