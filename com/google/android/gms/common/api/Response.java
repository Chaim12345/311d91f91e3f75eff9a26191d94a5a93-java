package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Result;
/* loaded from: classes.dex */
public class Response<T extends Result> {
    private T zza;

    public Response() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public Response(@NonNull Result result) {
        this.zza = result;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public Result a() {
        return this.zza;
    }

    public void setResult(@NonNull T t2) {
        this.zza = t2;
    }
}
