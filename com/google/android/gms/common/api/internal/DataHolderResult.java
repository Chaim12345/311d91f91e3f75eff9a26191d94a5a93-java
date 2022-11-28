package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
@KeepForSdk
/* loaded from: classes.dex */
public abstract class DataHolderResult implements Result, Releasable {
    @NonNull
    @KeepForSdk

    /* renamed from: a  reason: collision with root package name */
    protected final Status f5629a;
    @NonNull
    @KeepForSdk

    /* renamed from: b  reason: collision with root package name */
    protected final DataHolder f5630b;

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    @KeepForSdk
    public Status getStatus() {
        return this.f5629a;
    }

    @Override // com.google.android.gms.common.api.Releasable
    @KeepForSdk
    public void release() {
        DataHolder dataHolder = this.f5630b;
        if (dataHolder != null) {
            dataHolder.close();
        }
    }
}
