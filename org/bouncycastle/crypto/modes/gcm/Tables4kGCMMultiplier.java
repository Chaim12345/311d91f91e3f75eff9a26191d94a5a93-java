package org.bouncycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class Tables4kGCMMultiplier implements GCMMultiplier {
    private byte[] H;
    private long[][] T;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        if (this.T == null) {
            this.T = (long[][]) Array.newInstance(long.class, 256, 2);
        } else if (GCMUtil.areEqual(this.H, bArr) != 0) {
            return;
        }
        byte[] bArr2 = new byte[16];
        this.H = bArr2;
        GCMUtil.copy(bArr, bArr2);
        GCMUtil.asLongs(this.H, this.T[1]);
        long[][] jArr = this.T;
        GCMUtil.multiplyP7(jArr[1], jArr[1]);
        for (int i2 = 2; i2 < 256; i2 += 2) {
            long[][] jArr2 = this.T;
            GCMUtil.divideP(jArr2[i2 >> 1], jArr2[i2]);
            long[][] jArr3 = this.T;
            GCMUtil.xor(jArr3[i2], jArr3[1], jArr3[i2 + 1]);
        }
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long[] jArr = this.T[bArr[15] & 255];
        long j2 = jArr[0];
        long j3 = jArr[1];
        for (int i2 = 14; i2 >= 0; i2--) {
            long[] jArr2 = this.T[bArr[i2] & 255];
            long j4 = j3 << 56;
            j3 = ((j3 >>> 8) | (j2 << 56)) ^ jArr2[1];
            j2 = (((((j2 >>> 8) ^ jArr2[0]) ^ j4) ^ (j4 >>> 1)) ^ (j4 >>> 2)) ^ (j4 >>> 7);
        }
        Pack.longToBigEndian(j2, bArr, 0);
        Pack.longToBigEndian(j3, bArr, 8);
    }
}
