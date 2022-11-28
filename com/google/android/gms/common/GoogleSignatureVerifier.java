package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import javax.annotation.Nullable;
@ShowFirstParty
@KeepForSdk
/* loaded from: classes.dex */
public class GoogleSignatureVerifier {
    @Nullable
    private static GoogleSignatureVerifier zza;
    private final Context zzb;
    private volatile String zzc;

    public GoogleSignatureVerifier(@NonNull Context context) {
        this.zzb = context.getApplicationContext();
    }

    @Nullable
    static final zzi a(PackageInfo packageInfo, zzi... zziVarArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr != null && signatureArr.length == 1) {
            zzj zzjVar = new zzj(packageInfo.signatures[0].toByteArray());
            for (int i2 = 0; i2 < zziVarArr.length; i2++) {
                if (zziVarArr[i2].equals(zzjVar)) {
                    return zziVarArr[i2];
                }
            }
            return null;
        }
        return null;
    }

    @NonNull
    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zza == null) {
                zzm.d(context);
                zza = new GoogleSignatureVerifier(context);
            }
        }
        return zza;
    }

    public static final boolean zzb(@NonNull PackageInfo packageInfo, boolean z) {
        if (packageInfo != null && packageInfo.signatures != null) {
            if ((z ? a(packageInfo, zzl.f5820a) : a(packageInfo, zzl.f5820a[0])) != null) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private final zzw zzc(String str, boolean z, boolean z2) {
        zzw zzwVar;
        ApplicationInfo applicationInfo;
        String str2 = "null pkg";
        if (str == null) {
            return zzw.c("null pkg");
        }
        if (str.equals(this.zzc)) {
            return zzw.b();
        }
        if (zzm.e()) {
            zzwVar = zzm.b(str, GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb), false, false);
        } else {
            try {
                PackageInfo packageInfo = this.zzb.getPackageManager().getPackageInfo(str, 64);
                boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb);
                if (packageInfo != null) {
                    Signature[] signatureArr = packageInfo.signatures;
                    if (signatureArr == null || signatureArr.length != 1) {
                        str2 = "single cert required";
                    } else {
                        zzj zzjVar = new zzj(packageInfo.signatures[0].toByteArray());
                        String str3 = packageInfo.packageName;
                        zzw a2 = zzm.a(str3, zzjVar, honorsDebugCertificates, false);
                        if (!a2.f5825a || (applicationInfo = packageInfo.applicationInfo) == null || (applicationInfo.flags & 2) == 0 || !zzm.a(str3, zzjVar, false, true).f5825a) {
                            zzwVar = a2;
                        } else {
                            str2 = "debuggable release cert app rejected";
                        }
                    }
                }
                zzwVar = zzw.c(str2);
            } catch (PackageManager.NameNotFoundException e2) {
                return zzw.d(str.length() != 0 ? "no pkg ".concat(str) : new String("no pkg "), e2);
            }
        }
        if (zzwVar.f5825a) {
            this.zzc = str;
        }
        return zzwVar;
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(@NonNull PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zzb(packageInfo, false)) {
            return true;
        }
        return zzb(packageInfo, true) && GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb);
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPackageGoogleSigned(@NonNull String str) {
        zzw zzc = zzc(str, false, false);
        zzc.e();
        return zzc.f5825a;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isUidGoogleSigned(int i2) {
        zzw c2;
        int length;
        String[] packagesForUid = this.zzb.getPackageManager().getPackagesForUid(i2);
        if (packagesForUid != null && (length = packagesForUid.length) != 0) {
            c2 = null;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    Preconditions.checkNotNull(c2);
                    break;
                }
                c2 = zzc(packagesForUid[i3], false, false);
                if (c2.f5825a) {
                    break;
                }
                i3++;
            }
        } else {
            c2 = zzw.c("no pkgs");
        }
        c2.e();
        return c2.f5825a;
    }
}
