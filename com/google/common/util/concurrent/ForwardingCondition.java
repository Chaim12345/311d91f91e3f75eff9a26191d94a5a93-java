package com.google.common.util.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
/* loaded from: classes2.dex */
abstract class ForwardingCondition implements Condition {
    abstract Condition a();

    @Override // java.util.concurrent.locks.Condition
    public void await() {
        a().await();
    }

    @Override // java.util.concurrent.locks.Condition
    public boolean await(long j2, TimeUnit timeUnit) {
        return a().await(j2, timeUnit);
    }

    @Override // java.util.concurrent.locks.Condition
    public long awaitNanos(long j2) {
        return a().awaitNanos(j2);
    }

    @Override // java.util.concurrent.locks.Condition
    public void awaitUninterruptibly() {
        a().awaitUninterruptibly();
    }

    @Override // java.util.concurrent.locks.Condition
    public boolean awaitUntil(Date date) {
        return a().awaitUntil(date);
    }

    @Override // java.util.concurrent.locks.Condition
    public void signal() {
        a().signal();
    }

    @Override // java.util.concurrent.locks.Condition
    public void signalAll() {
        a().signalAll();
    }
}
