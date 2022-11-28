package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class Monitor {
    @GuardedBy("lock")
    private Guard activeGuards;
    private final boolean fair;
    private final ReentrantLock lock;

    @Beta
    /* loaded from: classes2.dex */
    public static abstract class Guard {
        @Weak

        /* renamed from: a  reason: collision with root package name */
        final Monitor f9534a;

        /* renamed from: b  reason: collision with root package name */
        final Condition f9535b;
        @GuardedBy("monitor.lock")

        /* renamed from: c  reason: collision with root package name */
        int f9536c = 0;
        @NullableDecl
        @GuardedBy("monitor.lock")

        /* renamed from: d  reason: collision with root package name */
        Guard f9537d;

        /* JADX INFO: Access modifiers changed from: protected */
        public Guard(Monitor monitor) {
            this.f9534a = (Monitor) Preconditions.checkNotNull(monitor, "monitor");
            this.f9535b = monitor.lock.newCondition();
        }

        public abstract boolean isSatisfied();
    }

    public Monitor() {
        this(false);
    }

    public Monitor(boolean z) {
        this.activeGuards = null;
        this.fair = z;
        this.lock = new ReentrantLock(z);
    }

    @GuardedBy("lock")
    private void await(Guard guard, boolean z) {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.f9535b.await();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy("lock")
    private boolean awaitNanos(Guard guard, long j2, boolean z) {
        boolean z2 = true;
        while (j2 > 0) {
            if (z2) {
                if (z) {
                    try {
                        signalNextWaiter();
                    } finally {
                        if (!z2) {
                            endWaitingFor(guard);
                        }
                    }
                }
                beginWaitingFor(guard);
                z2 = false;
            }
            j2 = guard.f9535b.awaitNanos(j2);
            if (guard.isSatisfied()) {
                if (!z2) {
                    endWaitingFor(guard);
                }
                return true;
            }
        }
        return false;
    }

    @GuardedBy("lock")
    private void awaitUninterruptibly(Guard guard, boolean z) {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.f9535b.awaitUninterruptibly();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy("lock")
    private void beginWaitingFor(Guard guard) {
        int i2 = guard.f9536c;
        guard.f9536c = i2 + 1;
        if (i2 == 0) {
            guard.f9537d = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    @GuardedBy("lock")
    private void endWaitingFor(Guard guard) {
        int i2 = guard.f9536c - 1;
        guard.f9536c = i2;
        if (i2 == 0) {
            Guard guard2 = this.activeGuards;
            Guard guard3 = null;
            while (guard2 != guard) {
                guard3 = guard2;
                guard2 = guard2.f9537d;
            }
            Guard guard4 = guard2.f9537d;
            if (guard3 == null) {
                this.activeGuards = guard4;
            } else {
                guard3.f9537d = guard4;
            }
            guard2.f9537d = null;
        }
    }

    private static long initNanoTime(long j2) {
        if (j2 <= 0) {
            return 0L;
        }
        long nanoTime = System.nanoTime();
        if (nanoTime == 0) {
            return 1L;
        }
        return nanoTime;
    }

    @GuardedBy("lock")
    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable th) {
            signalAllWaiters();
            throw th;
        }
    }

    private static long remainingNanos(long j2, long j3) {
        if (j3 <= 0) {
            return 0L;
        }
        return j3 - (System.nanoTime() - j2);
    }

    @GuardedBy("lock")
    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.f9537d) {
            guard.f9535b.signalAll();
        }
    }

    @GuardedBy("lock")
    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.f9537d) {
            if (isSatisfied(guard)) {
                guard.f9535b.signal();
                return;
            }
        }
    }

    private static long toSafeNanos(long j2, TimeUnit timeUnit) {
        return Longs.constrainToRange(timeUnit.toNanos(j2), 0L, 6917529027641081853L);
    }

    public void enter() {
        this.lock.lock();
    }

    public boolean enter(long j2, TimeUnit timeUnit) {
        boolean tryLock;
        long safeNanos = toSafeNanos(j2, timeUnit);
        ReentrantLock reentrantLock = this.lock;
        boolean z = true;
        if (!this.fair && reentrantLock.tryLock()) {
            return true;
        }
        boolean interrupted = Thread.interrupted();
        try {
            long nanoTime = System.nanoTime();
            long j3 = safeNanos;
            while (true) {
                try {
                    try {
                        tryLock = reentrantLock.tryLock(j3, TimeUnit.NANOSECONDS);
                        break;
                    } catch (Throwable th) {
                        th = th;
                        if (z) {
                            Thread.currentThread().interrupt();
                        }
                        throw th;
                    }
                } catch (InterruptedException unused) {
                    j3 = remainingNanos(nanoTime, safeNanos);
                    interrupted = true;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            return tryLock;
        } catch (Throwable th2) {
            th = th2;
            z = interrupted;
        }
    }

    public boolean enterIf(Guard guard) {
        if (guard.f9534a == this) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        }
        throw new IllegalMonitorStateException();
    }

    public boolean enterIf(Guard guard, long j2, TimeUnit timeUnit) {
        if (guard.f9534a == this) {
            if (enter(j2, timeUnit)) {
                try {
                    boolean isSatisfied = guard.isSatisfied();
                    if (!isSatisfied) {
                    }
                    return isSatisfied;
                } finally {
                    this.lock.unlock();
                }
            }
            return false;
        }
        throw new IllegalMonitorStateException();
    }

    public boolean enterIfInterruptibly(Guard guard) {
        if (guard.f9534a == this) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lockInterruptibly();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        }
        throw new IllegalMonitorStateException();
    }

    public boolean enterIfInterruptibly(Guard guard, long j2, TimeUnit timeUnit) {
        if (guard.f9534a == this) {
            ReentrantLock reentrantLock = this.lock;
            if (reentrantLock.tryLock(j2, timeUnit)) {
                try {
                    boolean isSatisfied = guard.isSatisfied();
                    if (!isSatisfied) {
                    }
                    return isSatisfied;
                } finally {
                    reentrantLock.unlock();
                }
            }
            return false;
        }
        throw new IllegalMonitorStateException();
    }

    public void enterInterruptibly() {
        this.lock.lockInterruptibly();
    }

    public boolean enterInterruptibly(long j2, TimeUnit timeUnit) {
        return this.lock.tryLock(j2, timeUnit);
    }

    public void enterWhen(Guard guard) {
        if (guard.f9534a != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        reentrantLock.lockInterruptibly();
        try {
            if (guard.isSatisfied()) {
                return;
            }
            await(guard, isHeldByCurrentThread);
        } catch (Throwable th) {
            leave();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0047, code lost:
        if (awaitNanos(r11, r0, r3) != false) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean enterWhen(Guard guard, long j2, TimeUnit timeUnit) {
        long initNanoTime;
        long safeNanos = toSafeNanos(j2, timeUnit);
        if (guard.f9534a != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        boolean z = false;
        try {
            if (!this.fair) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                if (reentrantLock.tryLock()) {
                    initNanoTime = 0;
                    if (!guard.isSatisfied()) {
                        if (initNanoTime != 0) {
                            safeNanos = remainingNanos(initNanoTime, safeNanos);
                        }
                    }
                    z = true;
                    if (!z) {
                    }
                    return z;
                }
            }
            if (!guard.isSatisfied()) {
            }
            z = true;
            if (!z) {
            }
            return z;
        } catch (Throwable th) {
            if (!isHeldByCurrentThread) {
                try {
                    signalNextWaiter();
                } finally {
                    reentrantLock.unlock();
                }
            }
            throw th;
        }
        initNanoTime = initNanoTime(safeNanos);
        if (!reentrantLock.tryLock(j2, timeUnit)) {
            return false;
        }
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.f9534a != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        reentrantLock.lock();
        try {
            if (guard.isSatisfied()) {
                return;
            }
            awaitUninterruptibly(guard, isHeldByCurrentThread);
        } catch (Throwable th) {
            leave();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004b A[Catch: all -> 0x0073, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0073, blocks: (B:5:0x0013, B:7:0x001a, B:24:0x004b, B:11:0x0023, B:13:0x0028, B:15:0x0030, B:20:0x003b, B:22:0x0045, B:21:0x0041), top: B:45:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean enterWhenUninterruptibly(Guard guard, long j2, TimeUnit timeUnit) {
        long initNanoTime;
        long remainingNanos;
        long safeNanos = toSafeNanos(j2, timeUnit);
        if (guard.f9534a != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        boolean interrupted = Thread.interrupted();
        boolean z = true;
        try {
            if (!this.fair && reentrantLock.tryLock()) {
                initNanoTime = 0;
                while (!guard.isSatisfied()) {
                    try {
                        if (initNanoTime == 0) {
                            initNanoTime = initNanoTime(safeNanos);
                            remainingNanos = safeNanos;
                        } else {
                            remainingNanos = remainingNanos(initNanoTime, safeNanos);
                        }
                        z = awaitNanos(guard, remainingNanos, isHeldByCurrentThread);
                    } catch (InterruptedException unused) {
                        interrupted = z;
                        isHeldByCurrentThread = false;
                    }
                }
                if (!z) {
                    reentrantLock.unlock();
                }
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
                return z;
            }
            initNanoTime = initNanoTime(safeNanos);
            long j3 = safeNanos;
            while (true) {
                try {
                    try {
                        break;
                    } catch (InterruptedException unused2) {
                        j3 = remainingNanos(initNanoTime, safeNanos);
                        interrupted = true;
                    }
                } catch (Throwable th) {
                    th = th;
                    interrupted = true;
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
            if (!reentrantLock.tryLock(j3, TimeUnit.NANOSECONDS)) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
                return false;
            }
            while (!guard.isSatisfied()) {
            }
            if (!z) {
            }
            if (interrupted) {
            }
            return z;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.f9534a == this) {
            this.lock.lock();
            try {
                return guard.f9536c;
            } finally {
                this.lock.unlock();
            }
        }
        throw new IllegalMonitorStateException();
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public void leave() {
        ReentrantLock reentrantLock = this.lock;
        try {
            if (reentrantLock.getHoldCount() == 1) {
                signalNextWaiter();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.f9534a == this) {
            ReentrantLock reentrantLock = this.lock;
            if (reentrantLock.tryLock()) {
                try {
                    boolean isSatisfied = guard.isSatisfied();
                    if (!isSatisfied) {
                    }
                    return isSatisfied;
                } finally {
                    reentrantLock.unlock();
                }
            }
            return false;
        }
        throw new IllegalMonitorStateException();
    }

    public void waitFor(Guard guard) {
        if (!(guard.f9534a == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return;
        }
        await(guard, true);
    }

    public boolean waitFor(Guard guard, long j2, TimeUnit timeUnit) {
        long safeNanos = toSafeNanos(j2, timeUnit);
        if ((guard.f9534a == this) && this.lock.isHeldByCurrentThread()) {
            if (guard.isSatisfied()) {
                return true;
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            return awaitNanos(guard, safeNanos, true);
        }
        throw new IllegalMonitorStateException();
    }

    public void waitForUninterruptibly(Guard guard) {
        if (!(guard.f9534a == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return;
        }
        awaitUninterruptibly(guard, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean waitForUninterruptibly(Guard guard, long j2, TimeUnit timeUnit) {
        long safeNanos = toSafeNanos(j2, timeUnit);
        boolean z = true;
        if (!(guard.f9534a == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return true;
        }
        long initNanoTime = initNanoTime(safeNanos);
        boolean interrupted = Thread.interrupted();
        long j3 = safeNanos;
        boolean z2 = true;
        while (true) {
            try {
                try {
                    boolean awaitNanos = awaitNanos(guard, j3, z2);
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    return awaitNanos;
                } catch (Throwable th) {
                    th = th;
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            } catch (InterruptedException unused) {
                if (guard.isSatisfied()) {
                    Thread.currentThread().interrupt();
                    return true;
                }
                j3 = remainingNanos(initNanoTime, safeNanos);
                z2 = false;
                interrupted = true;
            } catch (Throwable th2) {
                th = th2;
                z = interrupted;
                if (z) {
                }
                throw th;
            }
        }
    }
}
