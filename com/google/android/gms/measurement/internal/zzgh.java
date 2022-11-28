package com.google.android.gms.measurement.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
public final class zzgh extends zzhe {
    private static final AtomicLong zza = new AtomicLong(Long.MIN_VALUE);
    @Nullable
    private zzgg zzb;
    @Nullable
    private zzgg zzc;
    private final PriorityBlockingQueue zzd;
    private final BlockingQueue zze;
    private final Thread.UncaughtExceptionHandler zzf;
    private final Thread.UncaughtExceptionHandler zzg;
    private final Object zzh;
    private final Semaphore zzi;
    private volatile boolean zzj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgh(zzgk zzgkVar) {
        super(zzgkVar);
        this.zzh = new Object();
        this.zzi = new Semaphore(2);
        this.zzd = new PriorityBlockingQueue();
        this.zze = new LinkedBlockingQueue();
        this.zzf = new zzge(this, "Thread death: Uncaught exception on worker thread");
        this.zzg = new zzge(this, "Thread death: Uncaught exception on network thread");
    }

    private final void zzt(zzgf zzgfVar) {
        synchronized (this.zzh) {
            this.zzd.add(zzgfVar);
            zzgg zzggVar = this.zzb;
            if (zzggVar == null) {
                zzgg zzggVar2 = new zzgg(this, "Measurement Worker", this.zzd);
                this.zzb = zzggVar2;
                zzggVar2.setUncaughtExceptionHandler(this.zzf);
                this.zzb.start();
            } else {
                zzggVar.zza();
            }
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzhe
    protected final boolean b() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final Object h(AtomicReference atomicReference, long j2, String str, Runnable runnable) {
        synchronized (atomicReference) {
            this.f6809a.zzaz().zzp(runnable);
            try {
                atomicReference.wait(j2);
            } catch (InterruptedException unused) {
                zzey zzk = this.f6809a.zzay().zzk();
                zzk.zza("Interrupted waiting for " + str);
                return null;
            }
        }
        Object obj = atomicReference.get();
        if (obj == null) {
            this.f6809a.zzay().zzk().zza("Timed out waiting for ".concat(str));
        }
        return obj;
    }

    @Override // com.google.android.gms.measurement.internal.zzhd
    public final void zzax() {
        if (Thread.currentThread() != this.zzc) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzhd
    public final void zzg() {
        if (Thread.currentThread() != this.zzb) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final Future zzh(Callable callable) {
        c();
        Preconditions.checkNotNull(callable);
        zzgf zzgfVar = new zzgf(this, callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzb) {
            if (!this.zzd.isEmpty()) {
                this.f6809a.zzay().zzk().zza("Callable skipped the worker queue.");
            }
            zzgfVar.run();
        } else {
            zzt(zzgfVar);
        }
        return zzgfVar;
    }

    public final Future zzi(Callable callable) {
        c();
        Preconditions.checkNotNull(callable);
        zzgf zzgfVar = new zzgf(this, callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzb) {
            zzgfVar.run();
        } else {
            zzt(zzgfVar);
        }
        return zzgfVar;
    }

    public final void zzo(Runnable runnable) {
        c();
        Preconditions.checkNotNull(runnable);
        zzgf zzgfVar = new zzgf(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzh) {
            this.zze.add(zzgfVar);
            zzgg zzggVar = this.zzc;
            if (zzggVar == null) {
                zzgg zzggVar2 = new zzgg(this, "Measurement Network", this.zze);
                this.zzc = zzggVar2;
                zzggVar2.setUncaughtExceptionHandler(this.zzg);
                this.zzc.start();
            } else {
                zzggVar.zza();
            }
        }
    }

    public final void zzp(Runnable runnable) {
        c();
        Preconditions.checkNotNull(runnable);
        zzt(new zzgf(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzq(Runnable runnable) {
        c();
        Preconditions.checkNotNull(runnable);
        zzt(new zzgf(this, runnable, true, "Task exception on worker thread"));
    }

    public final boolean zzs() {
        return Thread.currentThread() == this.zzb;
    }
}
