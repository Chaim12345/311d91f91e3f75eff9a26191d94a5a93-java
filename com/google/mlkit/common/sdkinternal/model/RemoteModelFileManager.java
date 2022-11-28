package com.google.mlkit.common.sdkinternal.model;

import android.annotation.SuppressLint;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.internal.model.ModelUtils;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.common.sdkinternal.model.ModelValidator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
@KeepForSdk
/* loaded from: classes2.dex */
public class RemoteModelFileManager {
    private static final GmsLogger zza = new GmsLogger("RemoteModelFileManager", "");
    private final MlKitContext zzb;
    private final String zzc;
    private final ModelType zzd;
    @Nullable
    private final ModelValidator zze;
    private final RemoteModelFileMover zzf;
    private final SharedPrefManager zzg;
    private final ModelFileHelper zzh;

    @SuppressLint({"FirebaseLambdaLast"})
    public RemoteModelFileManager(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @Nullable ModelValidator modelValidator, @NonNull ModelFileHelper modelFileHelper, @NonNull RemoteModelFileMover remoteModelFileMover) {
        this.zzb = mlKitContext;
        ModelType modelType = remoteModel.getModelType();
        this.zzd = modelType;
        this.zzc = modelType == ModelType.TRANSLATE ? remoteModel.getModelNameForBackend() : remoteModel.getUniqueModelNameForPersist();
        this.zze = modelValidator;
        this.zzg = SharedPrefManager.getInstance(mlKitContext);
        this.zzh = modelFileHelper;
        this.zzf = remoteModelFileMover;
    }

    @NonNull
    @KeepForSdk
    public File getModelDirUnsafe(boolean z) {
        return this.zzh.getModelDirUnsafe(this.zzc, this.zzd, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b0, code lost:
        r10 = com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager.zza;
        r11 = java.lang.String.valueOf(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00bc, code lost:
        if (r11.length() == 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00be, code lost:
        r11 = "Hash does not match with expected: ".concat(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c3, code lost:
        r11 = new java.lang.String("Hash does not match with expected: ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c8, code lost:
        r10.d("RemoteModelFileManager", r11);
        com.google.android.gms.internal.mlkit_common.zzlw.zzb("common").zze(com.google.android.gms.internal.mlkit_common.zzlo.zzg(), r12, com.google.android.gms.internal.mlkit_common.zzid.MODEL_HASH_MISMATCH, true, r9.zzd, com.google.android.gms.internal.mlkit_common.zzij.SUCCEEDED);
        r10 = new com.google.mlkit.common.MlKitException("Hash does not match with expected", 102);
     */
    @Nullable
    @KeepForSdk
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized File moveModelToPrivateFolder(@NonNull ParcelFileDescriptor parcelFileDescriptor, @NonNull String str, @NonNull RemoteModel remoteModel) {
        File file;
        ModelValidator modelValidator;
        file = new File(this.zzh.zza(this.zzc, this.zzd), "to_be_validated_model.tmp");
        ModelValidator.ValidationResult validationResult = null;
        try {
            ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = autoCloseInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
                autoCloseInputStream.close();
                boolean zza2 = ModelUtils.zza(file, str);
                if (zza2 && (modelValidator = this.zze) != null) {
                    validationResult = modelValidator.validateModel(file, remoteModel);
                    if (validationResult.getErrorCode().equals(ModelValidator.ValidationResult.ErrorCode.TFLITE_VERSION_INCOMPATIBLE)) {
                        String appVersion = CommonUtils.getAppVersion(this.zzb.getApplicationContext());
                        this.zzg.setIncompatibleModelInfo(remoteModel, str, appVersion);
                        GmsLogger gmsLogger = zza;
                        String valueOf = String.valueOf(str);
                        gmsLogger.d("RemoteModelFileManager", valueOf.length() != 0 ? "Model is not compatible. Model hash: ".concat(valueOf) : new String("Model is not compatible. Model hash: "));
                        String valueOf2 = String.valueOf(appVersion);
                        gmsLogger.d("RemoteModelFileManager", valueOf2.length() != 0 ? "The current app version is: ".concat(valueOf2) : new String("The current app version is: "));
                    }
                }
                if (zza2 && (validationResult == null || validationResult.isValid())) {
                }
                MlKitException mlKitException = new MlKitException("Model is not compatible with TFLite run time", 100);
                if (!file.delete()) {
                    GmsLogger gmsLogger2 = zza;
                    String valueOf3 = String.valueOf(file.getAbsolutePath());
                    gmsLogger2.d("RemoteModelFileManager", valueOf3.length() != 0 ? "Failed to delete the temp file: ".concat(valueOf3) : new String("Failed to delete the temp file: "));
                }
                throw mlKitException;
            } catch (Throwable th) {
                try {
                    autoCloseInputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (IOException e2) {
            zza.e("RemoteModelFileManager", "Failed to copy downloaded model file to private folder: ".concat(e2.toString()));
            return null;
        }
        return this.zzf.moveAllFilesFromPrivateTempToPrivateDestination(file);
    }

    @NonNull
    @WorkerThread
    public final synchronized File zza(@NonNull File file) {
        File file2 = new File(String.valueOf(this.zzh.getModelDir(this.zzc, this.zzd).getAbsolutePath()).concat("/0"));
        if (file2.exists()) {
            return file;
        }
        return file.renameTo(file2) ? file2 : file;
    }

    @Nullable
    @WorkerThread
    public final synchronized String zzb() {
        return this.zzh.zzb(this.zzc, this.zzd);
    }

    @WorkerThread
    public final synchronized void zzc(@NonNull File file) {
        File modelDirUnsafe = getModelDirUnsafe(false);
        if (modelDirUnsafe.exists()) {
            File[] listFiles = modelDirUnsafe.listFiles();
            if (listFiles == null) {
                return;
            }
            for (File file2 : listFiles) {
                if (file2.equals(file)) {
                    this.zzh.deleteRecursively(file);
                    return;
                }
            }
        }
    }

    @WorkerThread
    public final synchronized boolean zzd(@NonNull File file) {
        File modelDir = this.zzh.getModelDir(this.zzc, this.zzd);
        if (modelDir.exists()) {
            File[] listFiles = modelDir.listFiles();
            boolean z = true;
            if (listFiles == null) {
                return true;
            }
            for (File file2 : listFiles) {
                if (!file2.equals(file) && !this.zzh.deleteRecursively(file2)) {
                    z = false;
                }
            }
            return z;
        }
        return false;
    }
}
