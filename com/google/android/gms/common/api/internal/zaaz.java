package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
final class zaaz implements GoogleApiClient.ConnectionCallbacks {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f5649a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ StatusPendingResult f5650b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zabe f5651c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaaz(zabe zabeVar, AtomicReference atomicReference, StatusPendingResult statusPendingResult) {
        this.f5651c = zabeVar;
        this.f5649a = atomicReference;
        this.f5650b = statusPendingResult;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        this.f5651c.zam((GoogleApiClient) Preconditions.checkNotNull((GoogleApiClient) this.f5649a.get()), this.f5650b, true);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i2) {
    }
}
