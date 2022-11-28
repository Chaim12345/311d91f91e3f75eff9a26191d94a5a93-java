package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
final class zap implements PendingResult.StatusListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PendingResult f5774a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f5775b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ PendingResultUtil.ResultConverter f5776c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zas f5777d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zap(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, PendingResultUtil.ResultConverter resultConverter, zas zasVar) {
        this.f5774a = pendingResult;
        this.f5775b = taskCompletionSource;
        this.f5776c = resultConverter;
        this.f5777d = zasVar;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        if (!status.isSuccess()) {
            this.f5775b.setException(ApiExceptionUtil.fromStatus(status));
            return;
        }
        this.f5775b.setResult(this.f5776c.convert(this.f5774a.await(0L, TimeUnit.MILLISECONDS)));
    }
}
