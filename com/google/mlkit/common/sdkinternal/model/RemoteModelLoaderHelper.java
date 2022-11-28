package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.nio.MappedByteBuffer;
@KeepForSdk
/* loaded from: classes2.dex */
public interface RemoteModelLoaderHelper {
    @NonNull
    @KeepForSdk
    MappedByteBuffer loadModelAtPath(@NonNull String str);
}
