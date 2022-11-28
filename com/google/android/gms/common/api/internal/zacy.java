package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
final class zacy implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Result f5697a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zada f5698b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zacy(zada zadaVar, Result result) {
        this.f5698b = zadaVar;
        this.f5697a = result;
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public final void run() {
        WeakReference weakReference;
        zacz zaczVar;
        zacz zaczVar2;
        WeakReference weakReference2;
        GoogleApiClient googleApiClient;
        ResultTransform resultTransform;
        zacz zaczVar3;
        zacz zaczVar4;
        WeakReference weakReference3;
        try {
            try {
                ThreadLocal threadLocal = BasePendingResult.f5626c;
                threadLocal.set(Boolean.TRUE);
                resultTransform = this.f5698b.zaa;
                PendingResult onSuccess = ((ResultTransform) Preconditions.checkNotNull(resultTransform)).onSuccess(this.f5697a);
                zada zadaVar = this.f5698b;
                zaczVar3 = zadaVar.zah;
                zaczVar4 = zadaVar.zah;
                zaczVar3.sendMessage(zaczVar4.obtainMessage(0, onSuccess));
                threadLocal.set(Boolean.FALSE);
                zada zadaVar2 = this.f5698b;
                zada.zan(this.f5697a);
                weakReference3 = this.f5698b.zag;
                googleApiClient = (GoogleApiClient) weakReference3.get();
                if (googleApiClient == null) {
                    return;
                }
            } catch (RuntimeException e2) {
                zada zadaVar3 = this.f5698b;
                zaczVar = zadaVar3.zah;
                zaczVar2 = zadaVar3.zah;
                zaczVar.sendMessage(zaczVar2.obtainMessage(1, e2));
                BasePendingResult.f5626c.set(Boolean.FALSE);
                zada zadaVar4 = this.f5698b;
                zada.zan(this.f5697a);
                weakReference2 = this.f5698b.zag;
                googleApiClient = (GoogleApiClient) weakReference2.get();
                if (googleApiClient == null) {
                    return;
                }
            }
            googleApiClient.zap(this.f5698b);
        } catch (Throwable th) {
            BasePendingResult.f5626c.set(Boolean.FALSE);
            zada zadaVar5 = this.f5698b;
            zada.zan(this.f5697a);
            weakReference = this.f5698b.zag;
            GoogleApiClient googleApiClient2 = (GoogleApiClient) weakReference.get();
            if (googleApiClient2 != null) {
                googleApiClient2.zap(this.f5698b);
            }
            throw th;
        }
    }
}
