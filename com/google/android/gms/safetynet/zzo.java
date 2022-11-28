package com.google.android.gms.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.safetynet.zzad;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes2.dex */
final class zzo extends com.google.android.gms.internal.safetynet.zzd {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f7065a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(SafetyNetClient safetyNetClient, TaskCompletionSource taskCompletionSource) {
        this.f7065a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.safetynet.zzd, com.google.android.gms.internal.safetynet.zzg
    public final void zzb(Status status, boolean z) {
        zzad zzadVar = new zzad(status, z);
        SafetyNetApi.VerifyAppsUserResponse verifyAppsUserResponse = new SafetyNetApi.VerifyAppsUserResponse();
        verifyAppsUserResponse.setResult(zzadVar);
        TaskUtil.setResultOrApiException(status, verifyAppsUserResponse, this.f7065a);
    }
}
