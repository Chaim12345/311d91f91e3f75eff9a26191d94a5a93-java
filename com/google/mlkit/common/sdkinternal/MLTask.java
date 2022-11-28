package com.google.mlkit.common.sdkinternal;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.sdkinternal.MLTaskInput;
@KeepForSdk
/* loaded from: classes2.dex */
public abstract class MLTask<T, S extends MLTaskInput> extends ModelResource {
    @NonNull
    @KeepForSdk
    @WorkerThread
    public abstract T run(@NonNull S s2);
}
