package org.bouncycastle.tls;

import org.bouncycastle.tls.crypto.TlsCipher;
/* loaded from: classes4.dex */
class DTLSEpoch {
    private final TlsCipher cipher;
    private final int epoch;
    private final DTLSReplayWindow replayWindow = new DTLSReplayWindow();
    private long sequenceNumber = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DTLSEpoch(int i2, TlsCipher tlsCipher) {
        if (i2 < 0) {
            throw new IllegalArgumentException("'epoch' must be >= 0");
        }
        if (tlsCipher == null) {
            throw new IllegalArgumentException("'cipher' cannot be null");
        }
        this.epoch = i2;
        this.cipher = tlsCipher;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long a() {
        long j2;
        j2 = this.sequenceNumber;
        if (j2 >= 281474976710656L) {
            throw new TlsFatalAlert((short) 80);
        }
        this.sequenceNumber = 1 + j2;
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsCipher b() {
        return this.cipher;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this.epoch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DTLSReplayWindow d() {
        return this.replayWindow;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void e(long j2) {
        this.sequenceNumber = j2;
    }
}
