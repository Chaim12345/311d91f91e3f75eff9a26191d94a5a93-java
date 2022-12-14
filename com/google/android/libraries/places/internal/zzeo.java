package com.google.android.libraries.places.internal;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import androidx.annotation.Nullable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes2.dex */
public final class zzeo {
    @Nullable
    public static String zza(PackageManager packageManager, String str) {
        Signature[] signatureArr;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            if (packageInfo != null && (signatureArr = packageInfo.signatures) != null && signatureArr.length != 0 && signatureArr[0] != null) {
                return zzb(signatureArr[0]);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            String valueOf = String.valueOf(str);
            Log.e("CredentialsHelper", valueOf.length() != 0 ? "Unable to get certificate fingerprint for package: ".concat(valueOf) : new String("Unable to get certificate fingerprint for package: "), e2);
            return null;
        }
    }

    @Nullable
    private static String zzb(Signature signature) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-1").digest(signature.toByteArray());
            return zzlc.zzd().zze(digest, 0, digest.length);
        } catch (NoSuchAlgorithmException e2) {
            Log.e("CredentialsHelper", "Unable to get certificate fingerprint.", e2);
            return null;
        }
    }
}
