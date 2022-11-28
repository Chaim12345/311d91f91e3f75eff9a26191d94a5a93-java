package com.google.android.gms.internal.mlkit_common;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import androidx.core.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
/* loaded from: classes.dex */
public final class zzh {
    public static final /* synthetic */ int zza = 0;
    private static final String[] zzb = {"com.android.", "com.google.", "com.chrome.", "com.nest.", "com.waymo.", "com.waze"};
    private static final String[] zzc;
    private static final String[] zzd;

    static {
        String[] strArr = new String[2];
        strArr[0] = "media";
        String str = Build.HARDWARE;
        strArr[1] = true != (str.equals("goldfish") || str.equals("ranchu")) ? "" : "androidx.test.services.storage.runfiles";
        zzc = strArr;
        String[] strArr2 = new String[3];
        int i2 = Build.VERSION.SDK_INT;
        strArr2[0] = i2 <= 25 ? "com.google.android.inputmethod.latin.inputcontent" : "";
        strArr2[1] = i2 <= 25 ? "com.google.android.inputmethod.latin.dev.inputcontent" : "";
        strArr2[2] = "com.google.android.apps.docs.storage.legacy";
        zzd = strArr2;
    }

    public static AssetFileDescriptor zza(Context context, Uri uri, String str) {
        zzg zzgVar = zzg.zza;
        ContentResolver contentResolver = context.getContentResolver();
        Uri zzc2 = zzc(uri);
        String scheme = zzc2.getScheme();
        if ("android.resource".equals(scheme)) {
            return contentResolver.openAssetFileDescriptor(zzc2, "r");
        }
        if (FirebaseAnalytics.Param.CONTENT.equals(scheme)) {
            if (zzi(context, zzc2, 1, zzgVar)) {
                AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(zzc2, "r");
                zzd(openAssetFileDescriptor);
                return openAssetFileDescriptor;
            }
            throw new FileNotFoundException("Can't open content uri.");
        } else if ("file".equals(scheme)) {
            AssetFileDescriptor openAssetFileDescriptor2 = contentResolver.openAssetFileDescriptor(zzc2, "r");
            zzd(openAssetFileDescriptor2);
            try {
                zzh(context, openAssetFileDescriptor2.getParcelFileDescriptor(), zzc2, zzgVar);
                return openAssetFileDescriptor2;
            } catch (FileNotFoundException e2) {
                zzf(openAssetFileDescriptor2, e2);
                throw e2;
            } catch (IOException e3) {
                FileNotFoundException fileNotFoundException = new FileNotFoundException("Validation failed.");
                fileNotFoundException.initCause(e3);
                zzf(openAssetFileDescriptor2, fileNotFoundException);
                throw fileNotFoundException;
            }
        } else {
            throw new FileNotFoundException("Unsupported scheme");
        }
    }

    public static InputStream zzb(Context context, Uri uri) {
        zzg zzgVar = zzg.zza;
        ContentResolver contentResolver = context.getContentResolver();
        Uri zzc2 = zzc(uri);
        String scheme = zzc2.getScheme();
        if ("android.resource".equals(scheme)) {
            return contentResolver.openInputStream(zzc2);
        }
        if (FirebaseAnalytics.Param.CONTENT.equals(scheme)) {
            if (zzi(context, zzc2, 1, zzgVar)) {
                InputStream openInputStream = contentResolver.openInputStream(zzc2);
                zzd(openInputStream);
                return openInputStream;
            }
            throw new FileNotFoundException("Can't open content uri.");
        } else if ("file".equals(scheme)) {
            try {
                ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(Uri.fromFile(new File(zzc2.getPath()).getCanonicalFile()), "r");
                try {
                    zzh(context, openFileDescriptor, zzc2, zzgVar);
                    return new ParcelFileDescriptor.AutoCloseInputStream(openFileDescriptor);
                } catch (FileNotFoundException e2) {
                    zzg(openFileDescriptor, e2);
                    throw e2;
                } catch (IOException e3) {
                    FileNotFoundException fileNotFoundException = new FileNotFoundException("Validation failed.");
                    fileNotFoundException.initCause(e3);
                    zzg(openFileDescriptor, fileNotFoundException);
                    throw fileNotFoundException;
                }
            } catch (IOException e4) {
                FileNotFoundException fileNotFoundException2 = new FileNotFoundException("Canonicalization failed.");
                fileNotFoundException2.initCause(e4);
                throw fileNotFoundException2;
            }
        } else {
            throw new FileNotFoundException("Unsupported scheme");
        }
    }

    private static Uri zzc(Uri uri) {
        return Build.VERSION.SDK_INT < 30 ? Uri.parse(uri.toString()) : uri;
    }

    private static Object zzd(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new FileNotFoundException("Content resolver returned null value.");
    }

    private static String zze(File file) {
        String canonicalPath = file.getCanonicalPath();
        return !canonicalPath.endsWith("/") ? canonicalPath.concat("/") : canonicalPath;
    }

    private static void zzf(AssetFileDescriptor assetFileDescriptor, FileNotFoundException fileNotFoundException) {
        try {
            assetFileDescriptor.close();
        } catch (IOException unused) {
        }
    }

    private static void zzg(ParcelFileDescriptor parcelFileDescriptor, FileNotFoundException fileNotFoundException) {
        try {
            parcelFileDescriptor.close();
        } catch (IOException unused) {
        }
    }

    private static void zzh(final Context context, ParcelFileDescriptor parcelFileDescriptor, Uri uri, zzg zzgVar) {
        File[] zzj;
        File dataDir;
        String canonicalPath = new File(uri.getPath()).getCanonicalPath();
        zzo a2 = zzo.a(parcelFileDescriptor.getFileDescriptor());
        zzo b2 = zzo.b(canonicalPath);
        if (b2.f6241c) {
            String valueOf = String.valueOf(canonicalPath);
            throw new FileNotFoundException(valueOf.length() != 0 ? "Can't open file: ".concat(valueOf) : new String("Can't open file: "));
        } else if (a2.f6239a != b2.f6239a || a2.f6240b != b2.f6240b) {
            String valueOf2 = String.valueOf(canonicalPath);
            throw new FileNotFoundException(valueOf2.length() != 0 ? "Can't open file: ".concat(valueOf2) : new String("Can't open file: "));
        } else {
            if (!canonicalPath.startsWith("/proc/") && !canonicalPath.startsWith("/data/misc/")) {
                zzg.a(zzgVar);
                File dataDir2 = ContextCompat.getDataDir(context);
                boolean z = false;
                if (dataDir2 == null ? !canonicalPath.startsWith(zze(Environment.getDataDirectory())) : !canonicalPath.startsWith(zze(dataDir2))) {
                    Context createDeviceProtectedStorageContext = ContextCompat.createDeviceProtectedStorageContext(context);
                    if (createDeviceProtectedStorageContext == null || (dataDir = ContextCompat.getDataDir(createDeviceProtectedStorageContext)) == null || !canonicalPath.startsWith(zze(dataDir))) {
                        File[] zzj2 = zzj(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzb
                            @Override // java.util.concurrent.Callable
                            public final Object call() {
                                Context context2 = context;
                                int i2 = zzh.zza;
                                return ContextCompat.getExternalFilesDirs(context2, null);
                            }
                        });
                        int length = zzj2.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 < length) {
                                File file = zzj2[i2];
                                if (file != null && canonicalPath.startsWith(zze(file))) {
                                    break;
                                }
                                i2++;
                            } else {
                                for (File file2 : zzj(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzc
                                    @Override // java.util.concurrent.Callable
                                    public final Object call() {
                                        Context context2 = context;
                                        int i3 = zzh.zza;
                                        return ContextCompat.getExternalCacheDirs(context2);
                                    }
                                })) {
                                    if (file2 == null || !canonicalPath.startsWith(zze(file2))) {
                                    }
                                }
                            }
                        }
                    }
                }
                z = true;
                if (z == zzg.b(zzgVar)) {
                    return;
                }
            }
            throw new FileNotFoundException(canonicalPath.length() != 0 ? "Can't open file: ".concat(canonicalPath) : new String("Can't open file: "));
        }
    }

    private static boolean zzi(Context context, Uri uri, int i2, zzg zzgVar) {
        String authority = uri.getAuthority();
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(authority, 0);
        if (resolveContentProvider == null) {
            int lastIndexOf = authority.lastIndexOf(64);
            if (lastIndexOf >= 0) {
                authority = authority.substring(lastIndexOf + 1);
                resolveContentProvider = context.getPackageManager().resolveContentProvider(authority, 0);
            }
            if (resolveContentProvider == null) {
                return !zzg.b(zzgVar);
            }
        }
        int c2 = zzg.c(zzgVar, context, new zzp(uri, resolveContentProvider, authority)) - 1;
        if (c2 != 0) {
            if (c2 != 1) {
                if (context.getPackageName().equals(resolveContentProvider.packageName)) {
                    return zzg.b(zzgVar);
                }
                if (zzg.b(zzgVar)) {
                    return false;
                }
                if (context.checkUriPermission(uri, Process.myPid(), Process.myUid(), 1) != 0 && resolveContentProvider.exported) {
                    String[] strArr = zzc;
                    int length = strArr.length;
                    for (int i3 = 0; i3 < 2; i3++) {
                        if (strArr[i3].equals(authority)) {
                            return true;
                        }
                    }
                    String[] strArr2 = zzd;
                    int length2 = strArr2.length;
                    for (int i4 = 0; i4 < 3; i4++) {
                        if (strArr2[i4].equals(authority)) {
                            return true;
                        }
                    }
                    String[] strArr3 = zzb;
                    for (int i5 = 0; i5 < 6; i5++) {
                        String str = strArr3[i5];
                        if (str.charAt(str.length() - 1) == '.') {
                            if (resolveContentProvider.packageName.startsWith(str)) {
                                return false;
                            }
                        } else if (resolveContentProvider.packageName.equals(str)) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        }
        return true;
    }

    private static File[] zzj(Callable callable) {
        try {
            return (File[]) callable.call();
        } catch (NullPointerException e2) {
            if (Build.VERSION.SDK_INT < 22) {
                return new File[0];
            }
            throw e2;
        } catch (Exception e3) {
            throw new RuntimeException(e3);
        }
    }
}
