package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzay {
    private final com.google.android.play.core.splitcompat.zze zza;
    private final zzau zzb;
    private final Context zzc;
    private final zzax zzd;
    @Nullable
    private PackageInfo zze;

    public zzay(Context context, com.google.android.play.core.splitcompat.zze zzeVar, zzau zzauVar) {
        zzax zzaxVar = new zzax(new com.google.android.play.core.splitcompat.zza(zzeVar));
        this.zza = zzeVar;
        this.zzb = zzauVar;
        this.zzc = context;
        this.zzd = zzaxVar;
    }

    @Nullable
    private final PackageInfo zzd() {
        if (this.zze == null) {
            try {
                this.zze = this.zzc.getPackageManager().getPackageInfo(this.zzc.getPackageName(), 64);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }
        return this.zze;
    }

    @Nullable
    private static X509Certificate zze(Signature signature) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
        } catch (CertificateException e2) {
            Log.e("SplitCompat", "Cannot decode certificate.", e2);
            return null;
        }
    }

    public final boolean zza(File[] fileArr) {
        PackageInfo zzd;
        long longVersionCode = Build.VERSION.SDK_INT >= 28 ? zzd().getLongVersionCode() : zzd.versionCode;
        AssetManager assetManager = (AssetManager) zzbw.zzc(AssetManager.class);
        int length = fileArr.length;
        do {
            length--;
            if (length < 0) {
                return true;
            }
            this.zzd.zzb(assetManager, fileArr[length]);
        } while (longVersionCode == this.zzd.zza());
        return false;
    }

    public final boolean zzb(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (!this.zza.zzg(((Intent) it.next()).getStringExtra("split_id")).exists()) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0058, code lost:
        android.util.Log.e("SplitCompat", r13);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean zzc(File[] fileArr) {
        PackageInfo zzd = zzd();
        ArrayList<X509Certificate> arrayList = null;
        if (zzd != null && zzd.signatures != null) {
            arrayList = new ArrayList();
            for (Signature signature : zzd.signatures) {
                X509Certificate zze = zze(signature);
                if (zze != null) {
                    arrayList.add(zze);
                }
            }
        }
        if (arrayList == null || arrayList.isEmpty()) {
            Log.e("SplitCompat", "No app certificates found.");
            return false;
        }
        int length = fileArr.length;
        loop1: while (true) {
            length--;
            if (length < 0) {
                return true;
            }
            try {
                String absolutePath = fileArr[length].getAbsolutePath();
                try {
                    X509Certificate[][] zza = zzi.zza(absolutePath);
                    if (zza == null || zza.length == 0 || zza[0].length == 0) {
                        break;
                    } else if (arrayList.isEmpty()) {
                        String sb = "No certificates found for app.";
                        break;
                    } else {
                        for (X509Certificate x509Certificate : arrayList) {
                            for (X509Certificate[] x509CertificateArr : zza) {
                                int i2 = x509CertificateArr[0].equals(x509Certificate) ? 0 : i2 + 1;
                            }
                        }
                    }
                } catch (Exception e2) {
                    StringBuilder sb2 = new StringBuilder(String.valueOf(absolutePath).length() + 32);
                    sb2.append("Downloaded split ");
                    sb2.append(absolutePath);
                    sb2.append(" is not signed.");
                    Log.e("SplitCompat", sb2.toString(), e2);
                }
            } catch (Exception e3) {
                Log.e("SplitCompat", "Split verification error.", e3);
                return false;
            }
        }
        Log.e("SplitCompat", "Split verification failure.");
        return false;
    }
}
