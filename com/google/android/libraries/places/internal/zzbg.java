package com.google.android.libraries.places.internal;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public final class zzbg {
    @Nullable
    private final String zza;
    private final int zzb;
    private final zzbf zzc;
    private final boolean zzd;
    private final int zze;

    public zzbg(@Nullable WifiInfo wifiInfo, ScanResult scanResult) {
        zzbf zzbfVar;
        boolean z;
        String str = scanResult.BSSID;
        String str2 = scanResult.capabilities;
        int i2 = scanResult.level;
        int i3 = scanResult.frequency;
        if (!TextUtils.isEmpty(str2)) {
            String upperCase = str2.toUpperCase();
            if (upperCase.equals("[ESS]") || upperCase.equals("[IBSS]")) {
                zzbfVar = zzbf.NONE;
            } else if (upperCase.matches(".*WPA[0-9]*-PSK.*")) {
                zzbfVar = zzbf.PSK;
            } else if (upperCase.matches(".*WPA[0-9]*-EAP.*")) {
                zzbfVar = zzbf.EAP;
            }
            z = false;
            if (wifiInfo != null && !TextUtils.isEmpty(str) && str.equalsIgnoreCase(wifiInfo.getBSSID())) {
                z = true;
            }
            this.zza = str;
            this.zzb = i2;
            this.zzc = zzbfVar;
            this.zzd = z;
            this.zze = i3;
        }
        zzbfVar = zzbf.OTHER;
        z = false;
        if (wifiInfo != null) {
            z = true;
        }
        this.zza = str;
        this.zzb = i2;
        this.zzc = zzbfVar;
        this.zzd = z;
        this.zze = i3;
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final zzbf zzc() {
        return this.zzc;
    }

    @Nullable
    public final String zzd() {
        return this.zza;
    }

    public final boolean zze() {
        return this.zzd;
    }
}
