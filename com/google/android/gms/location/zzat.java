package com.google.android.gms.location;

import android.location.Location;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes2.dex */
final class zzat extends com.google.android.gms.internal.location.zzan {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f6626a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzat(FusedLocationProviderClient fusedLocationProviderClient, TaskCompletionSource taskCompletionSource) {
        this.f6626a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.location.zzao
    public final void zzb(Status status, @Nullable Location location) {
        TaskUtil.setResultOrApiException(status, location, this.f6626a);
    }
}
