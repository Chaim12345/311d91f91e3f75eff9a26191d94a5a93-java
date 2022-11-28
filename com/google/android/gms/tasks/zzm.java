package com.google.android.gms.tasks;
/* loaded from: classes2.dex */
final class zzm implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Task f7089a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzn f7090b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm(zzn zznVar, Task task) {
        this.f7090b = zznVar;
        this.f7089a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnSuccessListener onSuccessListener;
        OnSuccessListener onSuccessListener2;
        obj = this.f7090b.zzb;
        synchronized (obj) {
            zzn zznVar = this.f7090b;
            onSuccessListener = zznVar.zzc;
            if (onSuccessListener != null) {
                onSuccessListener2 = zznVar.zzc;
                onSuccessListener2.onSuccess(this.f7089a.getResult());
            }
        }
    }
}
