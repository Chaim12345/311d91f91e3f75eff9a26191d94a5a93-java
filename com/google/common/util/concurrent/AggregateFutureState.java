package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
@ReflectionSupport(ReflectionSupport.Level.FULL)
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
abstract class AggregateFutureState<OutputT> extends AbstractFuture.TrustedFuture<OutputT> {
    private static final AtomicHelper ATOMIC_HELPER;
    private static final Logger log = Logger.getLogger(AggregateFutureState.class.getName());
    private volatile int remaining;
    private volatile Set<Throwable> seenExceptions = null;

    /* loaded from: classes2.dex */
    private static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        abstract void a(AggregateFutureState aggregateFutureState, Set set, Set set2);

        abstract int b(AggregateFutureState aggregateFutureState);
    }

    /* loaded from: classes2.dex */
    private static final class SafeAtomicHelper extends AtomicHelper {

        /* renamed from: a  reason: collision with root package name */
        final AtomicReferenceFieldUpdater f9478a;

        /* renamed from: b  reason: collision with root package name */
        final AtomicIntegerFieldUpdater f9479b;

        SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            super();
            this.f9478a = atomicReferenceFieldUpdater;
            this.f9479b = atomicIntegerFieldUpdater;
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        void a(AggregateFutureState aggregateFutureState, Set set, Set set2) {
            this.f9478a.compareAndSet(aggregateFutureState, set, set2);
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        int b(AggregateFutureState aggregateFutureState) {
            return this.f9479b.decrementAndGet(aggregateFutureState);
        }
    }

    /* loaded from: classes2.dex */
    private static final class SynchronizedAtomicHelper extends AtomicHelper {
        private SynchronizedAtomicHelper() {
            super();
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        void a(AggregateFutureState aggregateFutureState, Set set, Set set2) {
            synchronized (aggregateFutureState) {
                if (aggregateFutureState.seenExceptions == set) {
                    aggregateFutureState.seenExceptions = set2;
                }
            }
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        int b(AggregateFutureState aggregateFutureState) {
            int s2;
            synchronized (aggregateFutureState) {
                s2 = AggregateFutureState.s(aggregateFutureState);
            }
            return s2;
        }
    }

    static {
        AtomicHelper synchronizedAtomicHelper;
        Throwable th = null;
        try {
            synchronizedAtomicHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(AggregateFutureState.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(AggregateFutureState.class, "remaining"));
        } catch (Throwable th2) {
            th = th2;
            synchronizedAtomicHelper = new SynchronizedAtomicHelper();
        }
        ATOMIC_HELPER = synchronizedAtomicHelper;
        if (th != null) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AggregateFutureState(int i2) {
        this.remaining = i2;
    }

    static /* synthetic */ int s(AggregateFutureState aggregateFutureState) {
        int i2 = aggregateFutureState.remaining - 1;
        aggregateFutureState.remaining = i2;
        return i2;
    }

    abstract void t(Set set);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void u() {
        this.seenExceptions = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int v() {
        return ATOMIC_HELPER.b(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set w() {
        Set<Throwable> set = this.seenExceptions;
        if (set == null) {
            Set newConcurrentHashSet = Sets.newConcurrentHashSet();
            t(newConcurrentHashSet);
            ATOMIC_HELPER.a(this, null, newConcurrentHashSet);
            return this.seenExceptions;
        }
        return set;
    }
}
