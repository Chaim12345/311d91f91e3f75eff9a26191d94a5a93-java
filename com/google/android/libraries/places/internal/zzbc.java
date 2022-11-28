package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes2.dex */
final class zzbc implements Continuation {
    final /* synthetic */ zzbd zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbc(zzbd zzbdVar) {
        this.zza = zzbdVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // com.google.android.gms.tasks.Continuation
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object then(Task task) {
        ApiException apiException;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!task.isCanceled()) {
            if (task.getException() == null && task.getResult() == null) {
                apiException = new ApiException(new Status(8, "Location unavailable."));
            }
            return taskCompletionSource.getTask().getException() == null ? taskCompletionSource.getTask() : task;
        }
        apiException = new ApiException(new Status(16, "Location request was cancelled. Please try again."));
        taskCompletionSource.trySetException(apiException);
        if (taskCompletionSource.getTask().getException() == null) {
        }
    }
}
