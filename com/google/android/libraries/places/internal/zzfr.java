package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.CancellationTokenSource;
import java.util.Objects;
/* loaded from: classes2.dex */
final class zzfr extends zzfw {
    private final CancellationTokenSource zza;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfr(CancellationTokenSource cancellationTokenSource, String str) {
        this.zza = cancellationTokenSource;
        Objects.requireNonNull(str, "Null placeId");
        this.zzb = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfw) {
            zzfw zzfwVar = (zzfw) obj;
            if (this.zza.equals(zzfwVar.zza()) && this.zzb.equals(zzfwVar.zzb())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String obj = this.zza.toString();
        String str = this.zzb;
        StringBuilder sb = new StringBuilder(obj.length() + 31 + str.length());
        sb.append("PlaceRequest{source=");
        sb.append(obj);
        sb.append(", placeId=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.internal.zzfy
    public final CancellationTokenSource zza() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzfw
    public final String zzb() {
        return this.zzb;
    }
}
