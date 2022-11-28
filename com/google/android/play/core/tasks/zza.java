package com.google.android.play.core.tasks;
/* loaded from: classes2.dex */
final class zza implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Task f7938a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzb f7939b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(zzb zzbVar, Task task) {
        this.f7939b = zzbVar;
        this.f7938a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnCompleteListener onCompleteListener;
        OnCompleteListener onCompleteListener2;
        obj = this.f7939b.zzb;
        synchronized (obj) {
            zzb zzbVar = this.f7939b;
            onCompleteListener = zzbVar.zzc;
            if (onCompleteListener != null) {
                onCompleteListener2 = zzbVar.zzc;
                onCompleteListener2.onComplete(this.f7938a);
            }
        }
    }
}
