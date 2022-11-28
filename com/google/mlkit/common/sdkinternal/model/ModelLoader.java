package com.google.mlkit.common.sdkinternal.model;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.MlKitException;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;
@KeepForSdk
@WorkerThread
/* loaded from: classes2.dex */
public class ModelLoader {
    private static final GmsLogger zza = new GmsLogger("ModelLoader", "");
    @NonNull
    @KeepForSdk

    /* renamed from: a  reason: collision with root package name */
    protected ModelLoadingState f10340a = ModelLoadingState.NO_MODEL_LOADED;
    @Nullable
    @KeepForSdk
    public final LocalModelLoader localModelLoader;
    @Nullable
    @KeepForSdk
    @VisibleForTesting
    public final RemoteModelLoader remoteModelLoader;
    @NonNull
    private final ModelLoadingLogger zzb;

    @KeepForSdk
    /* loaded from: classes2.dex */
    public interface ModelContentHandler {
        @KeepForSdk
        void constructModel(@NonNull MappedByteBuffer mappedByteBuffer);
    }

    @KeepForSdk
    /* loaded from: classes2.dex */
    public interface ModelLoadingLogger {
        @KeepForSdk
        void logErrorCodes(@NonNull List<Integer> list);
    }

    @KeepForSdk
    /* loaded from: classes2.dex */
    protected enum ModelLoadingState {
        NO_MODEL_LOADED,
        REMOTE_MODEL_LOADED,
        LOCAL_MODEL_LOADED
    }

    @KeepForSdk
    public ModelLoader(@Nullable RemoteModelLoader remoteModelLoader, @Nullable LocalModelLoader localModelLoader, @NonNull ModelLoadingLogger modelLoadingLogger) {
        boolean z = true;
        if (remoteModelLoader == null && localModelLoader == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "At least one of RemoteModelLoader or LocalModelLoader must be non-null.");
        Preconditions.checkNotNull(modelLoadingLogger);
        this.remoteModelLoader = remoteModelLoader;
        this.localModelLoader = localModelLoader;
        this.zzb = modelLoadingLogger;
    }

    private final String zza() {
        LocalModelLoader localModelLoader = this.localModelLoader;
        String str = null;
        if (localModelLoader != null) {
            if (localModelLoader.getLocalModel().getAssetFilePath() != null) {
                str = this.localModelLoader.getLocalModel().getAssetFilePath();
            } else if (this.localModelLoader.getLocalModel().getAbsoluteFilePath() != null) {
                str = this.localModelLoader.getLocalModel().getAbsoluteFilePath();
            } else if (this.localModelLoader.getLocalModel().getUri() != null) {
                str = ((Uri) Preconditions.checkNotNull(this.localModelLoader.getLocalModel().getUri())).toString();
            }
        }
        RemoteModelLoader remoteModelLoader = this.remoteModelLoader;
        return String.format("Local model path: %s. Remote model name: %s. ", str, remoteModelLoader == null ? "unspecified" : remoteModelLoader.getRemoteModel().getUniqueModelNameForPersist());
    }

    private final synchronized boolean zzb(ModelContentHandler modelContentHandler, List list) {
        boolean z;
        MappedByteBuffer load;
        LocalModelLoader localModelLoader = this.localModelLoader;
        if (localModelLoader == null || (load = localModelLoader.load()) == null) {
            z = false;
        } else {
            try {
                modelContentHandler.constructModel(load);
                zza.d("ModelLoader", "Local model source is loaded successfully");
                z = true;
            } catch (RuntimeException e2) {
                list.add(18);
                throw e2;
            }
        }
        return z;
    }

    private final synchronized boolean zzc(ModelContentHandler modelContentHandler, List list) {
        boolean z;
        RemoteModelLoader remoteModelLoader = this.remoteModelLoader;
        if (remoteModelLoader != null) {
            try {
                MappedByteBuffer load = remoteModelLoader.load();
                if (load != null) {
                    try {
                        modelContentHandler.constructModel(load);
                        zza.d("ModelLoader", "Remote model source is loaded successfully");
                        z = true;
                    } catch (RuntimeException e2) {
                        list.add(19);
                        throw e2;
                    }
                } else {
                    zza.d("ModelLoader", "Remote model source can NOT be loaded, try local model.");
                    list.add(21);
                }
            } catch (MlKitException e3) {
                zza.d("ModelLoader", "Remote model source can NOT be loaded, try local model.");
                list.add(20);
                throw e3;
            }
        }
        z = false;
        return z;
    }

    @KeepForSdk
    public synchronized boolean isRemoteModelLoaded() {
        return this.f10340a == ModelLoadingState.REMOTE_MODEL_LOADED;
    }

    @KeepForSdk
    public synchronized void loadWithModelContentHandler(@NonNull ModelContentHandler modelContentHandler) {
        Exception exc;
        boolean z;
        ArrayList arrayList = new ArrayList();
        Exception e2 = null;
        boolean z2 = false;
        try {
            z = zzc(modelContentHandler, arrayList);
            exc = null;
        } catch (Exception e3) {
            exc = e3;
            z = false;
        }
        if (z) {
            this.zzb.logErrorCodes(arrayList);
            this.f10340a = ModelLoadingState.REMOTE_MODEL_LOADED;
            return;
        }
        try {
            z2 = zzb(modelContentHandler, arrayList);
        } catch (Exception e4) {
            e2 = e4;
        }
        if (z2) {
            this.zzb.logErrorCodes(arrayList);
            this.f10340a = ModelLoadingState.LOCAL_MODEL_LOADED;
            return;
        }
        arrayList.add(17);
        this.zzb.logErrorCodes(arrayList);
        this.f10340a = ModelLoadingState.NO_MODEL_LOADED;
        if (exc != null) {
            String valueOf = String.valueOf(zza());
            throw new MlKitException(valueOf.length() != 0 ? "Remote model load failed with the model options: ".concat(valueOf) : new String("Remote model load failed with the model options: "), 14, exc);
        } else if (e2 != null) {
            String valueOf2 = String.valueOf(zza());
            throw new MlKitException(valueOf2.length() != 0 ? "Local model load failed with the model options: ".concat(valueOf2) : new String("Local model load failed with the model options: "), 14, e2);
        } else {
            String valueOf3 = String.valueOf(zza());
            throw new MlKitException(valueOf3.length() != 0 ? "Cannot load any model with the model options: ".concat(valueOf3) : new String("Cannot load any model with the model options: "), 14);
        }
    }
}
