package com.google.android.gms.location;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes2.dex */
final class zzar extends com.google.android.gms.internal.location.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f6624a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzar(FusedLocationProviderClient fusedLocationProviderClient, TaskCompletionSource taskCompletionSource) {
        this.f6624a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.location.zzai
    public final void zzb(com.google.android.gms.internal.location.zzaa zzaaVar) {
        Status status = zzaaVar.getStatus();
        if (status == null) {
            this.f6624a.trySetException(new ApiException(new Status(8, "Got null status from location service")));
        } else if (status.getStatusCode() == 0) {
            this.f6624a.setResult(Boolean.TRUE);
        } else {
            this.f6624a.trySetException(ApiExceptionUtil.fromStatus(status));
        }
    }

    @Override // com.google.android.gms.internal.location.zzai
    public final void zzc() {
    }
}
