package com.google.android.gms.tasks;
/* loaded from: classes2.dex */
final class zzg implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzh f7084a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(zzh zzhVar) {
        this.f7084a = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnCanceledListener onCanceledListener;
        OnCanceledListener onCanceledListener2;
        obj = this.f7084a.zzb;
        synchronized (obj) {
            zzh zzhVar = this.f7084a;
            onCanceledListener = zzhVar.zzc;
            if (onCanceledListener != null) {
                onCanceledListener2 = zzhVar.zzc;
                onCanceledListener2.onCanceled();
            }
        }
    }
}
