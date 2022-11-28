package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.Closeable;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
final class zzt implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaskQueue f10344a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzt(TaskQueue taskQueue, zzs zzsVar) {
        AtomicReference atomicReference;
        this.f10344a = taskQueue;
        atomicReference = taskQueue.zzd;
        Preconditions.checkState(((Thread) atomicReference.getAndSet(Thread.currentThread())) == null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        AtomicReference atomicReference;
        atomicReference = this.f10344a.zzd;
        atomicReference.set(null);
        this.f10344a.zzc();
    }
}
