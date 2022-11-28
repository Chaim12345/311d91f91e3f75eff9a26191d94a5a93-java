package com.google.android.libraries.places.api.model;

import androidx.annotation.FloatRange;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class zzs extends PlaceLikelihood {
    private final Place zza;
    private final double zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(Place place, double d2) {
        Objects.requireNonNull(place, "Null place");
        this.zza = place;
        this.zzb = d2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PlaceLikelihood) {
            PlaceLikelihood placeLikelihood = (PlaceLikelihood) obj;
            if (this.zza.equals(placeLikelihood.getPlace()) && Double.doubleToLongBits(this.zzb) == Double.doubleToLongBits(placeLikelihood.getLikelihood())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.PlaceLikelihood
    @FloatRange(from = 0.0d, to = 1.0d)
    public final double getLikelihood() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.PlaceLikelihood
    public final Place getPlace() {
        return this.zza;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.zzb) >>> 32) ^ Double.doubleToLongBits(this.zzb)));
    }

    public final String toString() {
        String obj = this.zza.toString();
        double d2 = this.zzb;
        StringBuilder sb = new StringBuilder(obj.length() + 60);
        sb.append("PlaceLikelihood{place=");
        sb.append(obj);
        sb.append(", likelihood=");
        sb.append(d2);
        sb.append("}");
        return sb.toString();
    }
}
