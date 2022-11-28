package com.google.android.gms.tasks;
/* loaded from: classes2.dex */
final class zzi implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Task f7085a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzj f7086b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzi(zzj zzjVar, Task task) {
        this.f7086b = zzjVar;
        this.f7085a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnCompleteListener onCompleteListener;
        OnCompleteListener onCompleteListener2;
        obj = this.f7086b.zzb;
        synchronized (obj) {
            zzj zzjVar = this.f7086b;
            onCompleteListener = zzjVar.zzc;
            if (onCompleteListener != null) {
                onCompleteListener2 = zzjVar.zzc;
                onCompleteListener2.onComplete(this.f7085a);
            }
        }
    }
}
