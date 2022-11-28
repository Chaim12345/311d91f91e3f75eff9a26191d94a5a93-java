package com.google.android.play.core.tasks;
/* loaded from: classes2.dex */
final class zze implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Task f7942a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzf f7943b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(zzf zzfVar, Task task) {
        this.f7943b = zzfVar;
        this.f7942a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnSuccessListener onSuccessListener;
        OnSuccessListener onSuccessListener2;
        obj = this.f7943b.zzb;
        synchronized (obj) {
            zzf zzfVar = this.f7943b;
            onSuccessListener = zzfVar.zzc;
            if (onSuccessListener != null) {
                onSuccessListener2 = zzfVar.zzc;
                onSuccessListener2.onSuccess(this.f7942a.getResult());
            }
        }
    }
}
