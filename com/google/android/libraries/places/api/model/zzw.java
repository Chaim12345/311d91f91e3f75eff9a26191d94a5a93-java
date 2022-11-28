package com.google.android.libraries.places.api.model;

import com.google.android.gms.maps.model.LatLng;
import java.util.Objects;
/* loaded from: classes2.dex */
abstract class zzw extends RectangularBounds {
    private final LatLng zza;
    private final LatLng zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(LatLng latLng, LatLng latLng2) {
        Objects.requireNonNull(latLng, "Null southwest");
        this.zza = latLng;
        Objects.requireNonNull(latLng2, "Null northeast");
        this.zzb = latLng2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RectangularBounds) {
            RectangularBounds rectangularBounds = (RectangularBounds) obj;
            if (this.zza.equals(rectangularBounds.getSouthwest()) && this.zzb.equals(rectangularBounds.getNortheast())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.RectangularBounds
    public final LatLng getNortheast() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.RectangularBounds
    public final LatLng getSouthwest() {
        return this.zza;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String obj = this.zza.toString();
        String obj2 = this.zzb.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 41 + obj2.length());
        sb.append("RectangularBounds{southwest=");
        sb.append(obj);
        sb.append(", northeast=");
        sb.append(obj2);
        sb.append("}");
        return sb.toString();
    }
}
