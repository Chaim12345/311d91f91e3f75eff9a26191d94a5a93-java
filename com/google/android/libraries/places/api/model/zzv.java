package com.google.android.libraries.places.api.model;

import com.google.android.gms.maps.model.LatLng;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzv extends zzbi {
    private LatLng zza;
    private LatLng zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbi
    public final zzbi zza(LatLng latLng) {
        Objects.requireNonNull(latLng, "Null northeast");
        this.zzb = latLng;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzbi zzb(LatLng latLng) {
        Objects.requireNonNull(latLng, "Null southwest");
        this.zza = latLng;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbi
    public final RectangularBounds zzc() {
        LatLng latLng;
        LatLng latLng2 = this.zza;
        if (latLng2 == null || (latLng = this.zzb) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" southwest");
            }
            if (this.zzb == null) {
                sb.append(" northeast");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzax(latLng2, latLng);
    }
}
