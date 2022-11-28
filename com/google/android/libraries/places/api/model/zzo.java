package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.PhotoMetadata;
import java.util.Objects;
/* loaded from: classes2.dex */
final class zzo extends PhotoMetadata.Builder {
    private String zza;
    private int zzb;
    private int zzc;
    private String zzd;
    private byte zze;

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final String getAttributions() {
        String str = this.zza;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Property \"attributions\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final int getHeight() {
        if ((this.zze & 1) != 0) {
            return this.zzb;
        }
        throw new IllegalStateException("Property \"height\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final int getWidth() {
        if ((this.zze & 2) != 0) {
            return this.zzc;
        }
        throw new IllegalStateException("Property \"width\" has not been set");
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final PhotoMetadata.Builder setAttributions(String str) {
        Objects.requireNonNull(str, "Null attributions");
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final PhotoMetadata.Builder setHeight(int i2) {
        this.zzb = i2;
        this.zze = (byte) (this.zze | 1);
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    public final PhotoMetadata.Builder setWidth(int i2) {
        this.zzc = i2;
        this.zze = (byte) (this.zze | 2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final PhotoMetadata.Builder zza(String str) {
        Objects.requireNonNull(str, "Null photoReference");
        this.zzd = str;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.PhotoMetadata.Builder
    final PhotoMetadata zzb() {
        String str;
        String str2;
        if (this.zze != 3 || (str = this.zza) == null || (str2 = this.zzd) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" attributions");
            }
            if ((this.zze & 1) == 0) {
                sb.append(" height");
            }
            if ((this.zze & 2) == 0) {
                sb.append(" width");
            }
            if (this.zzd == null) {
                sb.append(" photoReference");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzap(str, this.zzb, this.zzc, str2);
    }
}
