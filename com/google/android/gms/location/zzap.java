package com.google.android.gms.location;

import android.location.Location;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes2.dex */
final class zzap extends com.google.android.gms.internal.location.zzan {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f6621a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzap(FusedLocationProviderClient fusedLocationProviderClient, TaskCompletionSource taskCompletionSource) {
        this.f6621a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.location.zzao
    public final void zzb(Status status, @Nullable Location location) {
        TaskUtil.trySetResultOrApiException(status, location, this.f6621a);
    }
}
