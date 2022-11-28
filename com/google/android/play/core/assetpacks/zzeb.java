package com.google.android.play.core.assetpacks;

import androidx.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzeb {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("PackMetadataManager");
    private final zzbh zzb;
    private final zzed zzc;
    private final com.google.android.play.core.common.zza zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeb(zzbh zzbhVar, zzed zzedVar, com.google.android.play.core.common.zza zzaVar) {
        this.zzb = zzbhVar;
        this.zzc = zzedVar;
        this.zzd = zzaVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String a(String str) {
        if (this.zzd.zza("assetOnlyUpdates") && this.zzb.g(str)) {
            int zza2 = this.zzc.zza();
            zzbh zzbhVar = this.zzb;
            File r2 = zzbhVar.r(str, zza2, zzbhVar.j(str));
            try {
                if (r2.exists()) {
                    FileInputStream fileInputStream = new FileInputStream(r2);
                    Properties properties = new Properties();
                    properties.load(fileInputStream);
                    fileInputStream.close();
                    String property = properties.getProperty("moduleVersionTag");
                    if (property != null) {
                        return property;
                    }
                }
                str = String.valueOf(zza2);
                return str;
            } catch (IOException unused) {
                zza.zzb("Failed to read pack version tag for pack %s", str);
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(String str, int i2, long j2, @Nullable String str2) {
        if (str2 == null || str2.isEmpty()) {
            str2 = String.valueOf(i2);
        }
        Properties properties = new Properties();
        properties.put("moduleVersionTag", str2);
        File r2 = this.zzb.r(str, i2, j2);
        r2.getParentFile().mkdirs();
        r2.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(r2);
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
