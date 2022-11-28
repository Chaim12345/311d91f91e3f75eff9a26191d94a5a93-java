package org.bouncycastle.crypto.modes.gcm;
/* loaded from: classes3.dex */
public class BasicGCMExponentiator implements GCMExponentiator {
    private long[] x;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j2, byte[] bArr) {
        long[] oneAsLongs = GCMUtil.oneAsLongs();
        if (j2 <= 0) {
            GCMUtil.asBytes(oneAsLongs, bArr);
        }
        long[] jArr = new long[2];
        GCMUtil.copy(this.x, jArr);
        do {
            if ((1 & j2) != 0) {
                GCMUtil.multiply(oneAsLongs, jArr);
            }
            GCMUtil.square(jArr, jArr);
            j2 >>>= 1;
        } while (j2 > 0);
        GCMUtil.asBytes(oneAsLongs, bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        this.x = GCMUtil.asLongs(bArr);
    }
}
