package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
final class zzo implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Task f7091a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzp f7092b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(zzp zzpVar, Task task) {
        this.f7092b = zzpVar;
        this.f7091a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SuccessContinuation successContinuation;
        try {
            successContinuation = this.f7092b.zzb;
            Task then = successContinuation.then(this.f7091a.getResult());
            if (then == null) {
                this.f7092b.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            Executor executor = TaskExecutors.f7076a;
            then.addOnSuccessListener(executor, this.f7092b);
            then.addOnFailureListener(executor, this.f7092b);
            then.addOnCanceledListener(executor, this.f7092b);
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                this.f7092b.onFailure((Exception) e2.getCause());
            } else {
                this.f7092b.onFailure(e2);
            }
        } catch (CancellationException unused) {
            this.f7092b.onCanceled();
        } catch (Exception e3) {
            this.f7092b.onFailure(e3);
        }
    }
}
