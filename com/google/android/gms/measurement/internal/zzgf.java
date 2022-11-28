package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzgf extends FutureTask implements Comparable {

    /* renamed from: a  reason: collision with root package name */
    final boolean f6753a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzgh f6754b;
    private final long zzc;
    private final String zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgf(zzgh zzghVar, Runnable runnable, boolean z, String str) {
        super(runnable, null);
        AtomicLong atomicLong;
        this.f6754b = zzghVar;
        Preconditions.checkNotNull(str);
        atomicLong = zzgh.zza;
        long andIncrement = atomicLong.getAndIncrement();
        this.zzc = andIncrement;
        this.zzd = str;
        this.f6753a = z;
        if (andIncrement == LongCompanionObject.MAX_VALUE) {
            zzghVar.f6809a.zzay().zzd().zza("Tasks index overflow");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgf(zzgh zzghVar, Callable callable, boolean z, String str) {
        super(callable);
        AtomicLong atomicLong;
        this.f6754b = zzghVar;
        Preconditions.checkNotNull("Task exception on worker thread");
        atomicLong = zzgh.zza;
        long andIncrement = atomicLong.getAndIncrement();
        this.zzc = andIncrement;
        this.zzd = "Task exception on worker thread";
        this.f6753a = z;
        if (andIncrement == LongCompanionObject.MAX_VALUE) {
            zzghVar.f6809a.zzay().zzd().zza("Tasks index overflow");
        }
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(@NonNull Object obj) {
        zzgf zzgfVar = (zzgf) obj;
        boolean z = this.f6753a;
        if (z != zzgfVar.f6753a) {
            return !z ? 1 : -1;
        }
        int i2 = (this.zzc > zzgfVar.zzc ? 1 : (this.zzc == zzgfVar.zzc ? 0 : -1));
        if (i2 < 0) {
            return -1;
        }
        if (i2 > 0) {
            return 1;
        }
        this.f6754b.f6809a.zzay().zzh().zzb("Two tasks share the same index. index", Long.valueOf(this.zzc));
        return 0;
    }

    @Override // java.util.concurrent.FutureTask
    protected final void setException(Throwable th) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
        this.f6754b.f6809a.zzay().zzd().zzb(this.zzd, th);
        if ((th instanceof zzgd) && (defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()) != null) {
            defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }
}
