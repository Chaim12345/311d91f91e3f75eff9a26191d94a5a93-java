package org.bouncycastle.tls;
/* loaded from: classes4.dex */
class DTLSReplayWindow {
    private static final long VALID_SEQ_MASK = 281474976710655L;
    private static final long WINDOW_SIZE = 64;
    private long latestConfirmedSeq = -1;
    private long bitmap = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        if ((VALID_SEQ_MASK & j2) != j2) {
            throw new IllegalArgumentException("'seq' out of range");
        }
        long j3 = this.latestConfirmedSeq;
        if (j2 <= j3) {
            long j4 = j3 - j2;
            if (j4 < 64) {
                this.bitmap |= 1 << ((int) j4);
                return;
            }
            return;
        }
        long j5 = j2 - j3;
        if (j5 >= 64) {
            this.bitmap = 1L;
        } else {
            long j6 = this.bitmap << ((int) j5);
            this.bitmap = j6;
            this.bitmap = j6 | 1;
        }
        this.latestConfirmedSeq = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(long j2) {
        if ((VALID_SEQ_MASK & j2) != j2) {
            throw new IllegalArgumentException("'seq' out of range");
        }
        this.latestConfirmedSeq = j2;
        this.bitmap = (-1) >>> ((int) Math.max(0L, 63 - j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(long j2) {
        if ((VALID_SEQ_MASK & j2) != j2) {
            return true;
        }
        long j3 = this.latestConfirmedSeq;
        if (j2 <= j3) {
            long j4 = j3 - j2;
            return j4 >= 64 || (this.bitmap & (1 << ((int) j4))) != 0;
        }
        return false;
    }
}
