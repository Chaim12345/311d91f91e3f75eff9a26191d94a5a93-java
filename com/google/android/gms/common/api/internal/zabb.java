package com.google.android.gms.common.api.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zabb implements ResultCallback<Status> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ StatusPendingResult f5653a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ boolean f5654b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ GoogleApiClient f5655c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zabe f5656d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabb(zabe zabeVar, StatusPendingResult statusPendingResult, boolean z, GoogleApiClient googleApiClient) {
        this.f5656d = zabeVar;
        this.f5653a = statusPendingResult;
        this.f5654b = z;
        this.f5655c = googleApiClient;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* bridge */ /* synthetic */ void onResult(@NonNull Status status) {
        Context context;
        Status status2 = status;
        context = this.f5656d.zan;
        Storage.getInstance(context).zac();
        if (status2.isSuccess() && this.f5656d.isConnected()) {
            zabe zabeVar = this.f5656d;
            zabeVar.disconnect();
            zabeVar.connect();
        }
        this.f5653a.setResult(status2);
        if (this.f5654b) {
            this.f5655c.disconnect();
        }
    }
}
