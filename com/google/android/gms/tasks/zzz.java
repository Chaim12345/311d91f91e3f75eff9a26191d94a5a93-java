package com.google.android.gms.tasks;

import java.util.concurrent.Callable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzz implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzw f7094a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Callable f7095b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(zzw zzwVar, Callable callable) {
        this.f7094a = zzwVar;
        this.f7095b = callable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f7094a.zzb(this.f7095b.call());
        } catch (Exception e2) {
            this.f7094a.zza(e2);
        } catch (Throwable th) {
            this.f7094a.zza(new RuntimeException(th));
        }
    }
}
