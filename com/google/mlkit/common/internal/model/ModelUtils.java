package com.google.mlkit.common.internal.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzac;
import com.google.android.gms.internal.mlkit_common.zzh;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;
@KeepForSdk
@WorkerThread
/* loaded from: classes2.dex */
public class ModelUtils {
    private static final GmsLogger zza = new GmsLogger("ModelUtils", "");

    @KeepForSdk
    /* loaded from: classes2.dex */
    public static abstract class AutoMLManifest {
        @NonNull
        @KeepForSdk
        public abstract String getLabelsFile();

        @NonNull
        @KeepForSdk
        public abstract String getModelFile();

        @NonNull
        @KeepForSdk
        public abstract String getModelType();
    }

    @KeepForSdk
    /* loaded from: classes2.dex */
    public static abstract class ModelLoggingInfo {
        static ModelLoggingInfo a(long j2, @Nullable String str, boolean z) {
            return new AutoValue_ModelUtils_ModelLoggingInfo(j2, zzac.zzb(str), z);
        }

        @NonNull
        @KeepForSdk
        public abstract String getHash();

        @KeepForSdk
        public abstract long getSize();

        @KeepForSdk
        public abstract boolean isManifestModel();
    }

    private ModelUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:99:0x00fd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Nullable
    @KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ModelLoggingInfo getModelLoggingInfo(@NonNull Context context, @NonNull LocalModel localModel) {
        long length;
        Throwable th;
        IOException e2;
        InputStream inputStream;
        String zzc;
        String assetFilePath = localModel.getAssetFilePath();
        String absoluteFilePath = localModel.getAbsoluteFilePath();
        Uri uri = localModel.getUri();
        InputStream inputStream2 = null;
        if (assetFilePath != null) {
            if (localModel.isManifestFile() && (assetFilePath = zzb(context, assetFilePath, true)) == null) {
                return null;
            }
            try {
                AssetFileDescriptor openFd = context.getAssets().openFd(assetFilePath);
                length = openFd.getLength();
                openFd.close();
            } catch (IOException e3) {
                zza.e("ModelUtils", "Failed to open model file", e3);
                return null;
            }
        } else if (absoluteFilePath != null) {
            if (localModel.isManifestFile() && (absoluteFilePath = zzb(context, absoluteFilePath, false)) == null) {
                return null;
            }
            length = new File(absoluteFilePath).length();
        } else if (uri == null) {
            zza.e("ModelUtils", "Local model doesn't have any valid path.");
            return null;
        } else {
            try {
                AssetFileDescriptor zza2 = zzh.zza(context, uri, "r");
                length = zza2.getLength();
                zza2.close();
            } catch (IOException e4) {
                zza.e("ModelUtils", "Failed to open model file", e4);
                return null;
            }
        }
        SharedPrefManager sharedPrefManager = (SharedPrefManager) MlKitContext.getInstance().get(SharedPrefManager.class);
        String uri2 = assetFilePath != null ? assetFilePath : absoluteFilePath != null ? absoluteFilePath : ((Uri) Preconditions.checkNotNull(uri)).toString();
        String zza3 = sharedPrefManager.zza(uri2, length);
        if (zza3 != null) {
            return ModelLoggingInfo.a(length, zza3, localModel.isManifestFile());
        }
        try {
            inputStream = assetFilePath != null ? context.getAssets().open(assetFilePath) : absoluteFilePath != null ? new FileInputStream(new File(absoluteFilePath)) : zzh.zzb(context, (Uri) Preconditions.checkNotNull(uri));
            if (inputStream != null) {
                try {
                    try {
                        zzc = zzc(inputStream);
                    } catch (IOException e5) {
                        e2 = e5;
                        zza.e("ModelUtils", "Failed to open model file", e2);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e6) {
                                zza.e("ModelUtils", "Failed to close model file", e6);
                            }
                        }
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream2 = inputStream;
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException e7) {
                            zza.e("ModelUtils", "Failed to close model file", e7);
                        }
                    }
                    throw th;
                }
            } else {
                zzc = null;
            }
            if (zzc != null) {
                sharedPrefManager.zzb(uri2, length, zzc);
            }
            ModelLoggingInfo a2 = ModelLoggingInfo.a(length, zzc, localModel.isManifestFile());
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e8) {
                    zza.e("ModelUtils", "Failed to close model file", e8);
                }
            }
            return a2;
        } catch (IOException e9) {
            e2 = e9;
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream2 != null) {
            }
            throw th;
        }
    }

    @Nullable
    @KeepForSdk
    public static String getSHA256(@NonNull File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            String zzc = zzc(fileInputStream);
            fileInputStream.close();
            return zzc;
        } catch (IOException e2) {
            zza.e("ModelUtils", "Failed to create FileInputStream for model: ".concat(e2.toString()));
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
        if (new java.io.File(r5).exists() == false) goto L8;
     */
    @Nullable
    @KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static AutoMLManifest parseManifestFile(@NonNull String str, boolean z, @NonNull Context context) {
        byte[] bArr;
        GmsLogger gmsLogger = zza;
        String valueOf = String.valueOf(str);
        gmsLogger.d("ModelUtils", valueOf.length() != 0 ? "Manifest file path: ".concat(valueOf) : new String("Manifest file path: "));
        if (z) {
            try {
                InputStream open = context.getAssets().open(str);
                if (open != null) {
                    open.close();
                }
            } catch (IOException unused) {
                zza.e("ModelUtils", "Manifest file does not exist.");
                return null;
            }
        }
        try {
            if (str.isEmpty()) {
                bArr = new byte[0];
            } else {
                InputStream open2 = z ? context.getAssets().open(str) : new FileInputStream(new File(str));
                try {
                    int available = open2.available();
                    byte[] bArr2 = new byte[available];
                    open2.read(bArr2, 0, available);
                    open2.close();
                    bArr = bArr2;
                } catch (Throwable th) {
                    if (open2 != null) {
                        try {
                            open2.close();
                        } catch (Throwable unused2) {
                        }
                    }
                    throw th;
                }
            }
            String str2 = new String(bArr, "UTF-8");
            gmsLogger.d("ModelUtils", str2.length() != 0 ? "Json string from the manifest file: ".concat(str2) : new String("Json string from the manifest file: "));
            JSONObject jSONObject = new JSONObject(str2);
            return new AutoValue_ModelUtils_AutoMLManifest(jSONObject.getString("modelType"), jSONObject.getString("modelFile"), jSONObject.getString("labelsFile"));
        } catch (IOException | JSONException e2) {
            zza.e("ModelUtils", "Error parsing the manifest file.", e2);
            return null;
        }
    }

    public static boolean zza(@NonNull File file, @NonNull String str) {
        String sha256 = getSHA256(file);
        GmsLogger gmsLogger = zza;
        String valueOf = String.valueOf(sha256);
        gmsLogger.d("ModelUtils", valueOf.length() != 0 ? "Calculated hash value is: ".concat(valueOf) : new String("Calculated hash value is: "));
        return str.equals(sha256);
    }

    @Nullable
    private static String zzb(Context context, String str, boolean z) {
        AutoMLManifest parseManifestFile = parseManifestFile(str, z, context);
        if (parseManifestFile == null) {
            zza.e("ModelUtils", "Failed to parse manifest file.");
            return null;
        }
        return new File(new File(str).getParent(), parseManifestFile.getModelFile()).toString();
    }

    @Nullable
    private static String zzc(InputStream inputStream) {
        GmsLogger gmsLogger;
        String str;
        int i2;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bArr = new byte[1048576];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
            }
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (IOException unused) {
            gmsLogger = zza;
            str = "Failed to read model file";
            gmsLogger.e("ModelUtils", str);
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            gmsLogger = zza;
            str = "Do not have SHA-256 algorithm";
            gmsLogger.e("ModelUtils", str);
            return null;
        }
    }
}
