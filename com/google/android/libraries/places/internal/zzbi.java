package com.google.android.libraries.places.internal;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public final class zzbi {
    public static final /* synthetic */ int zza = 0;
    private static final long zzb = TimeUnit.MINUTES.toMicros(1);
    private final zzas zzc;
    private final Context zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbi(Context context, zzas zzasVar) {
        this.zzd = context;
        this.zzc = zzasVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x008e, code lost:
        if (r11.contains("_optout") == false) goto L25;
     */
    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_FINE_LOCATION"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzhs zza(@Nullable String str) {
        if (Build.VERSION.SDK_INT < 17) {
            return zzhs.zzm();
        }
        WifiManager wifiManager = (WifiManager) this.zzd.getSystemService("wifi");
        if (wifiManager == null || !wifiManager.isWifiEnabled()) {
            return zzhs.zzm();
        }
        List<ScanResult> scanResults = wifiManager.getScanResults();
        if (scanResults == null || scanResults.isEmpty()) {
            return zzhs.zzm();
        }
        zzhs zzp = zzhs.zzp(zzid.zza(zzbh.zza), scanResults);
        ArrayList arrayList = new ArrayList();
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        int size = zzp.size();
        for (int i2 = 0; i2 < size; i2++) {
            ScanResult scanResult = (ScanResult) zzp.get(i2);
            if (Build.VERSION.SDK_INT >= 17 && scanResult != null && !TextUtils.isEmpty(scanResult.SSID)) {
                long zza2 = (this.zzc.zza() * 1000) - scanResult.timestamp;
                long j2 = zzb;
                String str2 = scanResult.SSID;
                if (str2 == null) {
                    throw new IllegalArgumentException("Null SSID.");
                }
                boolean z = true;
                if (str2.indexOf(95) >= 0) {
                    String lowerCase = str2.toLowerCase(Locale.ENGLISH);
                    if (!lowerCase.contains("_nomap")) {
                    }
                    if (zza2 <= j2 && !z) {
                        arrayList.add(new zzbg(connectionInfo, scanResult));
                    }
                }
                z = false;
                if (zza2 <= j2) {
                    arrayList.add(new zzbg(connectionInfo, scanResult));
                }
            }
        }
        return zzhs.zzk(arrayList);
    }
}
