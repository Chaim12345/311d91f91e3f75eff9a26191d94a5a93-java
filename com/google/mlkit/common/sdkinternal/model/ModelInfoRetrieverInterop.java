package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelInfo;
@KeepForSdk
/* loaded from: classes2.dex */
public interface ModelInfoRetrieverInterop {
    @Nullable
    @KeepForSdk
    ModelInfo retrieveRemoteModelInfo(@NonNull RemoteModel remoteModel);
}
