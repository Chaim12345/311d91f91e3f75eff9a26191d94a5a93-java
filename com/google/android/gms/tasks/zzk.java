package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes2.dex */
final class zzk implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Task f7087a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzl f7088b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(zzl zzlVar, Task task) {
        this.f7088b = zzlVar;
        this.f7087a = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        OnFailureListener onFailureListener;
        OnFailureListener onFailureListener2;
        obj = this.f7088b.zzb;
        synchronized (obj) {
            zzl zzlVar = this.f7088b;
            onFailureListener = zzlVar.zzc;
            if (onFailureListener != null) {
                onFailureListener2 = zzlVar.zzc;
                onFailureListener2.onFailure((Exception) Preconditions.checkNotNull(this.f7087a.getException()));
            }
        }
    }
}
