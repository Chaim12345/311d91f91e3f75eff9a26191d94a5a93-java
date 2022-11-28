package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.safetynet.SafetyNet;
/* loaded from: classes2.dex */
abstract class zze<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzaf> {
    public zze(GoogleApiClient googleApiClient) {
        super(SafetyNet.API, googleApiClient);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl, com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    @KeepForSdk
    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((zze<R>) ((Result) obj));
    }
}
