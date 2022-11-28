package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.AssetPackErrorCode;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import java.util.Objects;
/* loaded from: classes2.dex */
final class zzbn extends AssetPackState {
    private final String zza;
    private final int zzb;
    private final int zzc;
    private final long zzd;
    private final long zze;
    private final int zzf;
    private final int zzg;
    private final String zzh;
    private final String zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbn(String str, int i2, int i3, long j2, long j3, int i4, int i5, String str2, String str3) {
        Objects.requireNonNull(str, "Null name");
        this.zza = str;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = j2;
        this.zze = j3;
        this.zzf = i4;
        this.zzg = i5;
        Objects.requireNonNull(str2, "Null availableVersionTag");
        this.zzh = str2;
        Objects.requireNonNull(str3, "Null installedVersionTag");
        this.zzi = str3;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final long bytesDownloaded() {
        return this.zzd;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetPackState) {
            AssetPackState assetPackState = (AssetPackState) obj;
            if (this.zza.equals(assetPackState.name()) && this.zzb == assetPackState.status() && this.zzc == assetPackState.errorCode() && this.zzd == assetPackState.bytesDownloaded() && this.zze == assetPackState.totalBytesToDownload() && this.zzf == assetPackState.transferProgressPercentage() && this.zzg == assetPackState.zza() && this.zzh.equals(assetPackState.zzd()) && this.zzi.equals(assetPackState.zze())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    @AssetPackErrorCode
    public final int errorCode() {
        return this.zzc;
    }

    public final int hashCode() {
        int hashCode = this.zza.hashCode();
        int i2 = this.zzb;
        int i3 = this.zzc;
        long j2 = this.zzd;
        long j3 = this.zze;
        return ((((((((((((((((hashCode ^ 1000003) * 1000003) ^ i2) * 1000003) ^ i3) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003) ^ this.zzf) * 1000003) ^ this.zzg) * 1000003) ^ this.zzh.hashCode()) * 1000003) ^ this.zzi.hashCode();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final String name() {
        return this.zza;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    @AssetPackStatus
    public final int status() {
        return this.zzb;
    }

    public final String toString() {
        String str = this.zza;
        int i2 = this.zzb;
        int i3 = this.zzc;
        long j2 = this.zzd;
        long j3 = this.zze;
        int i4 = this.zzf;
        int i5 = this.zzg;
        String str2 = this.zzh;
        String str3 = this.zzi;
        StringBuilder sb = new StringBuilder(str.length() + 261 + str2.length() + str3.length());
        sb.append("AssetPackState{name=");
        sb.append(str);
        sb.append(", status=");
        sb.append(i2);
        sb.append(", errorCode=");
        sb.append(i3);
        sb.append(", bytesDownloaded=");
        sb.append(j2);
        sb.append(", totalBytesToDownload=");
        sb.append(j3);
        sb.append(", transferProgressPercentage=");
        sb.append(i4);
        sb.append(", updateAvailability=");
        sb.append(i5);
        sb.append(", availableVersionTag=");
        sb.append(str2);
        sb.append(", installedVersionTag=");
        sb.append(str3);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final long totalBytesToDownload() {
        return this.zze;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final int transferProgressPercentage() {
        return this.zzf;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final int zza() {
        return this.zzg;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final String zzd() {
        return this.zzh;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final String zze() {
        return this.zzi;
    }
}
