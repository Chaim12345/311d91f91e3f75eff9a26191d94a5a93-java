package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.android.play.core.assetpacks.model.AssetPackErrorCode;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;
/* loaded from: classes2.dex */
public abstract class AssetPackState {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static AssetPackState a(Bundle bundle, String str, zzco zzcoVar, zzeb zzebVar, zzbe zzbeVar) {
        int zza = zzbeVar.zza(bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zza(NotificationCompat.CATEGORY_STATUS, str)), str);
        int i2 = bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zza("error_code", str));
        long j2 = bundle.getLong(com.google.android.play.core.assetpacks.model.zzb.zza("bytes_downloaded", str));
        long j3 = bundle.getLong(com.google.android.play.core.assetpacks.model.zzb.zza("total_bytes_to_download", str));
        double a2 = zzcoVar.a(str);
        long j4 = bundle.getLong(com.google.android.play.core.assetpacks.model.zzb.zza("pack_version", str));
        long j5 = bundle.getLong(com.google.android.play.core.assetpacks.model.zzb.zza("pack_base_version", str));
        int i3 = 1;
        int i4 = 4;
        if (zza != 4) {
            i4 = zza;
        } else if (j5 != 0 && j5 != j4) {
            i3 = 2;
        }
        return zzb(str, i4, i2, j2, j3, a2, i3, bundle.getString(com.google.android.play.core.assetpacks.model.zzb.zza("pack_version_tag", str), String.valueOf(bundle.getInt("app_version_code"))), zzebVar.a(str));
    }

    public static AssetPackState zzb(@NonNull String str, @AssetPackStatus int i2, @AssetPackErrorCode int i3, long j2, long j3, double d2, int i4, String str2, String str3) {
        return new zzbn(str, i2, i3, j2, j3, (int) Math.rint(100.0d * d2), i4, str2, str3);
    }

    public abstract long bytesDownloaded();

    @AssetPackErrorCode
    public abstract int errorCode();

    public abstract String name();

    @AssetPackStatus
    public abstract int status();

    public abstract long totalBytesToDownload();

    public abstract int transferProgressPercentage();

    public abstract int zza();

    public abstract String zzd();

    public abstract String zze();
}
