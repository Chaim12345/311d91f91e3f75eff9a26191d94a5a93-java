package com.google.android.gms.tasks;
/* loaded from: classes2.dex */
final class zzc implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Task f7080a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzd f7081b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(zzd zzdVar, Task task) {
        this.f7081b = zzdVar;
        this.f7080a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzw zzwVar;
        zzw zzwVar2;
        zzw zzwVar3;
        Continuation continuation;
        zzw zzwVar4;
        zzw zzwVar5;
        if (this.f7080a.isCanceled()) {
            zzwVar5 = this.f7081b.zzc;
            zzwVar5.zzc();
            return;
        }
        try {
            continuation = this.f7081b.zzb;
            Object then = continuation.then(this.f7080a);
            zzwVar4 = this.f7081b.zzc;
            zzwVar4.zzb(then);
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                zzwVar3 = this.f7081b.zzc;
                zzwVar3.zza((Exception) e2.getCause());
                return;
            }
            zzwVar2 = this.f7081b.zzc;
            zzwVar2.zza(e2);
        } catch (Exception e3) {
            zzwVar = this.f7081b.zzc;
            zzwVar.zza(e3);
        }
    }
}
