package com.google.android.gms.measurement.internal;

import android.os.Process;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzgg extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzgh f6755a;
    private final Object zzb;
    private final BlockingQueue zzc;
    @GuardedBy("threadLifeCycleLock")
    private boolean zzd = false;

    public zzgg(zzgh zzghVar, String str, BlockingQueue blockingQueue) {
        this.f6755a = zzghVar;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zzb = new Object();
        this.zzc = blockingQueue;
        setName(str);
    }

    private final void zzb() {
        Object obj;
        Semaphore semaphore;
        Object obj2;
        zzgg zzggVar;
        zzgg zzggVar2;
        obj = this.f6755a.zzh;
        synchronized (obj) {
            if (!this.zzd) {
                semaphore = this.f6755a.zzi;
                semaphore.release();
                obj2 = this.f6755a.zzh;
                obj2.notifyAll();
                zzgh zzghVar = this.f6755a;
                zzggVar = zzghVar.zzb;
                if (this == zzggVar) {
                    zzghVar.zzb = null;
                } else {
                    zzggVar2 = zzghVar.zzc;
                    if (this == zzggVar2) {
                        zzghVar.zzc = null;
                    } else {
                        zzghVar.f6809a.zzay().zzd().zza("Current scheduler thread is neither worker nor network");
                    }
                }
                this.zzd = true;
            }
        }
    }

    private final void zzc(InterruptedException interruptedException) {
        this.f6755a.f6809a.zzay().zzk().zzb(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Semaphore semaphore;
        Object obj;
        boolean z = false;
        while (!z) {
            try {
                semaphore = this.f6755a.zzi;
                semaphore.acquire();
                z = true;
            } catch (InterruptedException e2) {
                zzc(e2);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                zzgf zzgfVar = (zzgf) this.zzc.poll();
                if (zzgfVar == null) {
                    synchronized (this.zzb) {
                        if (this.zzc.peek() == null) {
                            boolean unused = this.f6755a.zzj;
                            try {
                                this.zzb.wait(AutoConstants.FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
                            } catch (InterruptedException e3) {
                                zzc(e3);
                            }
                        }
                    }
                    obj = this.f6755a.zzh;
                    synchronized (obj) {
                        if (this.zzc.peek() == null) {
                            break;
                        }
                    }
                } else {
                    Process.setThreadPriority(true != zzgfVar.f6753a ? 10 : threadPriority);
                    zzgfVar.run();
                }
            }
            if (this.f6755a.f6809a.zzf().zzs(null, zzen.zzaf)) {
                zzb();
            }
        } finally {
            zzb();
        }
    }

    public final void zza() {
        synchronized (this.zzb) {
            this.zzb.notifyAll();
        }
    }
}
