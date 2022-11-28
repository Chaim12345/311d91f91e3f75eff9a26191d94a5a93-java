package com.google.android.gms.tasks;

import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
final class zze implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Task f7082a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzf f7083b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(zzf zzfVar, Task task) {
        this.f7083b = zzfVar;
        this.f7082a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzw zzwVar;
        zzw zzwVar2;
        zzw zzwVar3;
        Continuation continuation;
        try {
            continuation = this.f7083b.zzb;
            Task task = (Task) continuation.then(this.f7082a);
            if (task == null) {
                this.f7083b.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            Executor executor = TaskExecutors.f7076a;
            task.addOnSuccessListener(executor, this.f7083b);
            task.addOnFailureListener(executor, this.f7083b);
            task.addOnCanceledListener(executor, this.f7083b);
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                zzwVar3 = this.f7083b.zzc;
                zzwVar3.zza((Exception) e2.getCause());
                return;
            }
            zzwVar2 = this.f7083b.zzc;
            zzwVar2.zza(e2);
        } catch (Exception e3) {
            zzwVar = this.f7083b.zzc;
            zzwVar.zza(e3);
        }
    }
}
