package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzll;
import com.google.android.gms.internal.mlkit_common.zzlo;
import com.google.android.gms.internal.mlkit_common.zzlw;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;
@KeepForSdk
/* loaded from: classes2.dex */
public class RemoteModelLoader {
    private static final GmsLogger zza = new GmsLogger("RemoteModelLoader", "");
    @GuardedBy("RemoteModelLoader.class")
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    private final RemoteModel zzd;
    private final RemoteModelDownloadManager zze;
    private final RemoteModelFileManager zzf;
    private final RemoteModelLoaderHelper zzg;
    private final zzll zzh;
    private boolean zzi;

    private RemoteModelLoader(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @NonNull ModelValidator modelValidator, @NonNull RemoteModelLoaderHelper remoteModelLoaderHelper, @NonNull RemoteModelFileMover remoteModelFileMover) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext, remoteModel, modelValidator, new ModelFileHelper(mlKitContext), remoteModelFileMover);
        this.zzf = remoteModelFileManager;
        this.zzi = true;
        this.zze = RemoteModelDownloadManager.getInstance(mlKitContext, remoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
        this.zzg = remoteModelLoaderHelper;
        this.zzc = mlKitContext;
        this.zzd = remoteModel;
        this.zzh = zzlw.zzb("common");
    }

    @NonNull
    @KeepForSdk
    public static synchronized RemoteModelLoader getInstance(@NonNull MlKitContext mlKitContext, @NonNull RemoteModel remoteModel, @NonNull ModelValidator modelValidator, @NonNull RemoteModelLoaderHelper remoteModelLoaderHelper, @NonNull RemoteModelFileMover remoteModelFileMover) {
        RemoteModelLoader remoteModelLoader;
        synchronized (RemoteModelLoader.class) {
            String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
            Map map = zzb;
            if (!map.containsKey(uniqueModelNameForPersist)) {
                map.put(uniqueModelNameForPersist, new RemoteModelLoader(mlKitContext, remoteModel, modelValidator, remoteModelLoaderHelper, remoteModelFileMover));
            }
            remoteModelLoader = (RemoteModelLoader) map.get(uniqueModelNameForPersist);
        }
        return remoteModelLoader;
    }

    @NonNull
    @WorkerThread
    private final MappedByteBuffer zza(@NonNull String str) {
        return this.zzg.loadModelAtPath(str);
    }

    private final MappedByteBuffer zzb(File file) {
        try {
            return zza(file.getAbsolutePath());
        } catch (Exception e2) {
            this.zzf.zzc(file);
            throw new MlKitException("Failed to load newly downloaded model.", 14, e2);
        }
    }

    @NonNull
    @KeepForSdk
    public RemoteModel getRemoteModel() {
        return this.zzd;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00bb A[Catch: all -> 0x00fc, TryCatch #1 {, blocks: (B:3:0x0001, B:7:0x0020, B:9:0x0028, B:28:0x00b5, B:31:0x00bb, B:33:0x00ca, B:35:0x00d2, B:38:0x00d8, B:39:0x00f6, B:40:0x00f7, B:10:0x002c, B:12:0x0043, B:15:0x004c, B:17:0x0060, B:19:0x006a, B:21:0x0076, B:23:0x007e, B:18:0x0065, B:24:0x0090, B:26:0x0098, B:27:0x00ac), top: B:49:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f7 A[Catch: all -> 0x00fc, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:7:0x0020, B:9:0x0028, B:28:0x00b5, B:31:0x00bb, B:33:0x00ca, B:35:0x00d2, B:38:0x00d8, B:39:0x00f6, B:40:0x00f7, B:10:0x002c, B:12:0x0043, B:15:0x004c, B:17:0x0060, B:19:0x006a, B:21:0x0076, B:23:0x007e, B:18:0x0065, B:24:0x0090, B:26:0x0098, B:27:0x00ac), top: B:49:0x0001, inners: #0 }] */
    @Nullable
    @KeepForSdk
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized MappedByteBuffer load() {
        MappedByteBuffer mappedByteBuffer;
        RemoteModelDownloadManager remoteModelDownloadManager;
        MappedByteBuffer mappedByteBuffer2;
        GmsLogger gmsLogger = zza;
        gmsLogger.d("RemoteModelLoader", "Try to load newly downloaded model file.");
        boolean z = this.zzi;
        Long downloadingId = this.zze.getDownloadingId();
        String downloadingModelHash = this.zze.getDownloadingModelHash();
        mappedByteBuffer = null;
        if (downloadingId != null && downloadingModelHash != null) {
            Integer downloadingModelStatusCode = this.zze.getDownloadingModelStatusCode();
            if (downloadingModelStatusCode == null) {
                remoteModelDownloadManager = this.zze;
            } else {
                gmsLogger.d("RemoteModelLoader", "Download Status code: ".concat(downloadingModelStatusCode.toString()));
                if (downloadingModelStatusCode.intValue() == 8) {
                    File zzi = this.zze.zzi(downloadingModelHash);
                    if (zzi != null) {
                        mappedByteBuffer2 = zzb(zzi);
                        String valueOf = String.valueOf(zzi.getParent());
                        gmsLogger.d("RemoteModelLoader", valueOf.length() != 0 ? "Moved the downloaded model to private folder successfully: ".concat(valueOf) : new String("Moved the downloaded model to private folder successfully: "));
                        this.zze.updateLatestModelHashAndType(downloadingModelHash);
                        if (z && this.zzf.zzd(zzi)) {
                            gmsLogger.d("RemoteModelLoader", "All old models are deleted.");
                            mappedByteBuffer2 = zzb(this.zzf.zza(zzi));
                        }
                        if (mappedByteBuffer2 != null) {
                            gmsLogger.d("RemoteModelLoader", "Loading existing model file.");
                            String zzb2 = this.zzf.zzb();
                            if (zzb2 == null) {
                                gmsLogger.d("RemoteModelLoader", "No existing model file");
                            } else {
                                try {
                                    mappedByteBuffer = zza(zzb2);
                                } catch (Exception e2) {
                                    this.zzf.zzc(new File(zzb2));
                                    SharedPrefManager.getInstance(this.zzc).clearLatestModelHash(this.zzd);
                                    throw new MlKitException("Failed to load an already downloaded model.", 14, e2);
                                }
                            }
                        } else {
                            this.zzi = false;
                            mappedByteBuffer = mappedByteBuffer2;
                        }
                    }
                } else if (downloadingModelStatusCode.intValue() == 16) {
                    this.zzh.zzd(zzlo.zzg(), this.zzd, false, this.zze.getFailureReason(downloadingId));
                    remoteModelDownloadManager = this.zze;
                }
                mappedByteBuffer2 = null;
                if (mappedByteBuffer2 != null) {
                }
            }
            remoteModelDownloadManager.removeOrCancelDownload();
            mappedByteBuffer2 = null;
            if (mappedByteBuffer2 != null) {
            }
        }
        gmsLogger.d("RemoteModelLoader", "No new model is downloading.");
        remoteModelDownloadManager = this.zze;
        remoteModelDownloadManager.removeOrCancelDownload();
        mappedByteBuffer2 = null;
        if (mappedByteBuffer2 != null) {
        }
        return mappedByteBuffer;
    }
}
