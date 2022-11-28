package com.google.android.gms.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes2.dex */
final class zzp extends com.google.android.gms.internal.safetynet.zzd {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f7066a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(SafetyNetClient safetyNetClient, TaskCompletionSource taskCompletionSource) {
        this.f7066a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.safetynet.zzd, com.google.android.gms.internal.safetynet.zzg
    public final void zzc(Status status) {
        TaskUtil.setResultOrApiException(status, this.f7066a);
    }
}
