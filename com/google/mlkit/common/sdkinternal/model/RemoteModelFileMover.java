package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.File;
@KeepForSdk
/* loaded from: classes2.dex */
public interface RemoteModelFileMover {
    @NonNull
    @KeepForSdk
    File getModelFileDestination();

    @Nullable
    @KeepForSdk
    File moveAllFilesFromPrivateTempToPrivateDestination(@NonNull File file);
}
