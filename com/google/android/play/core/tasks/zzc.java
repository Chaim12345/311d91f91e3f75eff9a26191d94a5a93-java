package com.google.android.play.core.tasks;
/* loaded from: classes2.dex */
final class zzc implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Task f7940a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzd f7941b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(zzd zzdVar, Task task) {
        this.f7941b = zzdVar;
        this.f7940a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnFailureListener onFailureListener;
        OnFailureListener onFailureListener2;
        obj = this.f7941b.zzb;
        synchronized (obj) {
            zzd zzdVar = this.f7941b;
            onFailureListener = zzdVar.zzc;
            if (onFailureListener != null) {
                onFailureListener2 = zzdVar.zzc;
                onFailureListener2.onFailure(this.f7940a.getException());
            }
        }
    }
}
