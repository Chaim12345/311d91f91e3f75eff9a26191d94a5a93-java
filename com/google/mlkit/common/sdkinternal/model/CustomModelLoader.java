package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzll;
import com.google.android.gms.internal.mlkit_common.zzlo;
import com.google.android.gms.internal.mlkit_common.zzlw;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.Constants;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
@KeepForSdk
/* loaded from: classes2.dex */
public class CustomModelLoader {
    private static final GmsLogger zza = new GmsLogger("CustomModelLoader", "");
    @GuardedBy("CustomModelLoader.class")
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    @Nullable
    private final LocalModel zzd;
    @Nullable
    private final CustomRemoteModel zze;
    @Nullable
    private final RemoteModelDownloadManager zzf;
    @Nullable
    private final RemoteModelFileManager zzg;
    private final zzll zzh;
    private boolean zzi;

    @KeepForSdk
    /* loaded from: classes2.dex */
    public interface CustomModelLoaderHelper {
        @KeepForSdk
        void logLoad();

        @KeepForSdk
        boolean tryLoad(@NonNull LocalModel localModel);
    }

    private CustomModelLoader(@NonNull MlKitContext mlKitContext, @Nullable LocalModel localModel, @Nullable CustomRemoteModel customRemoteModel) {
        if (customRemoteModel != null) {
            RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext, customRemoteModel, null, new ModelFileHelper(mlKitContext), new com.google.mlkit.common.internal.model.zza(mlKitContext, customRemoteModel.getUniqueModelNameForPersist()));
            this.zzg = remoteModelFileManager;
            this.zzf = RemoteModelDownloadManager.getInstance(mlKitContext, customRemoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
            this.zzi = true;
        } else {
            this.zzg = null;
            this.zzf = null;
        }
        this.zzc = mlKitContext;
        this.zzd = localModel;
        this.zze = customRemoteModel;
        this.zzh = zzlw.zzb("common");
    }

    @NonNull
    @KeepForSdk
    public static synchronized CustomModelLoader getInstance(@NonNull MlKitContext mlKitContext, @Nullable LocalModel localModel, @Nullable CustomRemoteModel customRemoteModel) {
        CustomModelLoader customModelLoader;
        synchronized (CustomModelLoader.class) {
            String localModel2 = customRemoteModel == null ? ((LocalModel) Preconditions.checkNotNull(localModel)).toString() : customRemoteModel.getUniqueModelNameForPersist();
            Map map = zzb;
            if (!map.containsKey(localModel2)) {
                map.put(localModel2, new CustomModelLoader(mlKitContext, localModel, customRemoteModel));
            }
            customModelLoader = (CustomModelLoader) map.get(localModel2);
        }
        return customModelLoader;
    }

    @Nullable
    @WorkerThread
    private final File zza() {
        String zzb2 = ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzb();
        if (zzb2 == null) {
            zza.d("CustomModelLoader", "No existing model file");
            return null;
        }
        File file = new File(zzb2);
        File[] listFiles = file.listFiles();
        return ((File[]) Preconditions.checkNotNull(listFiles)).length == 1 ? listFiles[0] : file;
    }

    @WorkerThread
    private final void zzb() {
        ((RemoteModelDownloadManager) Preconditions.checkNotNull(this.zzf)).removeOrCancelDownload();
    }

    @WorkerThread
    private static final LocalModel zzc(File file) {
        LocalModel.Builder builder;
        if (file.isDirectory()) {
            builder = new LocalModel.Builder();
            builder.setAbsoluteManifestFilePath(new File(file.getAbsolutePath(), Constants.AUTOML_IMAGE_LABELING_MANIFEST_JSON_FILE_NAME).toString());
        } else {
            builder = new LocalModel.Builder();
            builder.setAbsoluteFilePath(file.getAbsolutePath());
        }
        return builder.build();
    }

    @VisibleForTesting
    @Nullable
    @KeepForSdk
    @WorkerThread
    public synchronized LocalModel createLocalModelByLatestExistingModel() {
        zza.d("CustomModelLoader", "Try to get the latest existing model file.");
        File zza2 = zza();
        if (zza2 == null) {
            return null;
        }
        return zzc(zza2);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x009f A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a1 A[Catch: all -> 0x00a7, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0023, B:24:0x0099, B:29:0x00a1, B:10:0x002c, B:12:0x0043, B:15:0x004c, B:17:0x005c, B:19:0x0066, B:18:0x0061, B:20:0x0071, B:22:0x0079, B:23:0x0092), top: B:35:0x0001 }] */
    @VisibleForTesting
    @Nullable
    @KeepForSdk
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized LocalModel createLocalModelByNewlyDownloadedModel() {
        File file;
        GmsLogger gmsLogger = zza;
        gmsLogger.d("CustomModelLoader", "Try to get newly downloaded model file.");
        Long downloadingId = ((RemoteModelDownloadManager) Preconditions.checkNotNull(this.zzf)).getDownloadingId();
        String downloadingModelHash = this.zzf.getDownloadingModelHash();
        if (downloadingId != null && downloadingModelHash != null) {
            Integer downloadingModelStatusCode = this.zzf.getDownloadingModelStatusCode();
            if (downloadingModelStatusCode != null) {
                gmsLogger.d("CustomModelLoader", "Download Status code: ".concat(downloadingModelStatusCode.toString()));
                if (downloadingModelStatusCode.intValue() == 8) {
                    file = this.zzf.zzi(downloadingModelHash);
                    if (file != null) {
                        String valueOf = String.valueOf(file.getParent());
                        gmsLogger.d("CustomModelLoader", valueOf.length() != 0 ? "Moved the downloaded model to private folder successfully: ".concat(valueOf) : new String("Moved the downloaded model to private folder successfully: "));
                        this.zzf.updateLatestModelHashAndType(downloadingModelHash);
                        if (file != null) {
                            return null;
                        }
                        return zzc(file);
                    }
                } else if (downloadingModelStatusCode.intValue() == 16) {
                    this.zzh.zzd(zzlo.zzg(), (RemoteModel) Preconditions.checkNotNull(this.zze), false, this.zzf.getFailureReason(downloadingId));
                }
                file = null;
                if (file != null) {
                }
            }
            zzb();
            file = null;
            if (file != null) {
            }
        }
        gmsLogger.d("CustomModelLoader", "No new model is downloading.");
        zzb();
        file = null;
        if (file != null) {
        }
    }

    @KeepForSdk
    @VisibleForTesting
    @WorkerThread
    public void deleteLatestExistingModel() {
        File zza2 = zza();
        if (zza2 != null) {
            ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzc(zza2);
            SharedPrefManager.getInstance(this.zzc).clearLatestModelHash((RemoteModel) Preconditions.checkNotNull(this.zze));
        }
    }

    @KeepForSdk
    @VisibleForTesting
    @WorkerThread
    public void deleteOldModels(@NonNull LocalModel localModel) {
        File parentFile = new File((String) Preconditions.checkNotNull(localModel.getAbsoluteFilePath())).getParentFile();
        if (!((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzd((File) Preconditions.checkNotNull(parentFile))) {
            zza.e("CustomModelLoader", "Failed to delete old models");
            return;
        }
        zza.d("CustomModelLoader", "All old models are deleted.");
        this.zzg.zza(parentFile);
    }

    @KeepForSdk
    @WorkerThread
    public synchronized void load(@NonNull CustomModelLoaderHelper customModelLoaderHelper) {
        LocalModel localModel = this.zzd;
        if (localModel == null) {
            localModel = createLocalModelByNewlyDownloadedModel();
        }
        if (localModel == null) {
            localModel = createLocalModelByLatestExistingModel();
        }
        if (localModel == null) {
            throw new MlKitException("Model is not available.", 14);
        }
        while (!customModelLoaderHelper.tryLoad(localModel)) {
            if (this.zze != null) {
                deleteLatestExistingModel();
                localModel = createLocalModelByLatestExistingModel();
                continue;
            } else {
                localModel = null;
                continue;
            }
            if (localModel == null) {
                customModelLoaderHelper.logLoad();
                return;
            }
        }
        if (this.zze != null && this.zzi) {
            deleteOldModels((LocalModel) Preconditions.checkNotNull(localModel));
            this.zzi = false;
        }
        customModelLoaderHelper.logLoad();
    }
}
