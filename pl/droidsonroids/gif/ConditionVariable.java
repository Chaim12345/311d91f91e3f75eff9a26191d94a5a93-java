package pl.droidsonroids.gif;
/* loaded from: classes4.dex */
class ConditionVariable {
    private volatile boolean mCondition;

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a() {
        while (!this.mCondition) {
            wait();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void b() {
        this.mCondition = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void c() {
        boolean z = this.mCondition;
        this.mCondition = true;
        if (!z) {
            notify();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void d(boolean z) {
        if (z) {
            c();
        } else {
            b();
        }
    }
}
