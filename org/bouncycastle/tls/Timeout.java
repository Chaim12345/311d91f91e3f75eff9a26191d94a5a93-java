package org.bouncycastle.tls;
/* loaded from: classes4.dex */
class Timeout {
    private long durationMillis;
    private long startMillis;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Timeout(long j2) {
        this(j2, System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Timeout(long j2, long j3) {
        this.durationMillis = Math.max(0L, j2);
        this.startMillis = Math.max(0L, j3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i2, Timeout timeout, long j2) {
        int d2;
        if (i2 >= 0 && (d2 = d(timeout, j2)) >= 0) {
            return i2 == 0 ? d2 : d2 == 0 ? i2 : Math.min(i2, d2);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Timeout b(int i2) {
        return c(i2, System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Timeout c(int i2, long j2) {
        if (i2 >= 0) {
            if (i2 > 0) {
                return new Timeout(i2, j2);
            }
            return null;
        }
        throw new IllegalArgumentException("'waitMillis' cannot be negative");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(Timeout timeout, long j2) {
        if (timeout == null) {
            return 0;
        }
        long f2 = timeout.f(j2);
        if (f2 < 1) {
            return -1;
        }
        if (f2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) f2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(Timeout timeout, long j2) {
        return timeout != null && timeout.f(j2) < 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long f(long j2) {
        long j3 = this.startMillis;
        if (j3 > j2) {
            this.startMillis = j2;
            return this.durationMillis;
        }
        long j4 = this.durationMillis - (j2 - j3);
        if (j4 <= 0) {
            this.durationMillis = 0L;
            return 0L;
        }
        return j4;
    }
}
