package com.google.android.play.core.assetpacks;

import java.util.Objects;
/* loaded from: classes2.dex */
final class zzbl extends AssetLocation {
    private final String zza;
    private final long zzb;
    private final long zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbl(String str, long j2, long j3) {
        Objects.requireNonNull(str, "Null path");
        this.zza = str;
        this.zzb = j2;
        this.zzc = j3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetLocation) {
            AssetLocation assetLocation = (AssetLocation) obj;
            if (this.zza.equals(assetLocation.path()) && this.zzb == assetLocation.offset() && this.zzc == assetLocation.size()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.zza.hashCode();
        long j2 = this.zzb;
        long j3 = this.zzc;
        return ((((hashCode ^ 1000003) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3));
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final long offset() {
        return this.zzb;
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final String path() {
        return this.zza;
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final long size() {
        return this.zzc;
    }

    public final String toString() {
        String str = this.zza;
        long j2 = this.zzb;
        long j3 = this.zzc;
        StringBuilder sb = new StringBuilder(str.length() + 76);
        sb.append("AssetLocation{path=");
        sb.append(str);
        sb.append(", offset=");
        sb.append(j2);
        sb.append(", size=");
        sb.append(j3);
        sb.append("}");
        return sb.toString();
    }
}
