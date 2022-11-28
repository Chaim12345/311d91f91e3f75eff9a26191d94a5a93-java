package org.bouncycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class Tables64kGCMMultiplier implements GCMMultiplier {
    private byte[] H;
    private long[][][] T;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        if (this.T == null) {
            this.T = (long[][][]) Array.newInstance(long.class, 16, 256, 2);
        } else if (GCMUtil.areEqual(this.H, bArr) != 0) {
            return;
        }
        byte[] bArr2 = new byte[16];
        this.H = bArr2;
        GCMUtil.copy(bArr, bArr2);
        for (int i2 = 0; i2 < 16; i2++) {
            long[][][] jArr = this.T;
            long[][] jArr2 = jArr[i2];
            if (i2 == 0) {
                GCMUtil.asLongs(this.H, jArr2[1]);
                GCMUtil.multiplyP7(jArr2[1], jArr2[1]);
            } else {
                GCMUtil.multiplyP8(jArr[i2 - 1][1], jArr2[1]);
            }
            for (int i3 = 2; i3 < 256; i3 += 2) {
                GCMUtil.divideP(jArr2[i3 >> 1], jArr2[i3]);
                GCMUtil.xor(jArr2[i3], jArr2[1], jArr2[i3 + 1]);
            }
        }
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long[] jArr = this.T[15][bArr[15] & 255];
        long j2 = jArr[0];
        long j3 = jArr[1];
        for (int i2 = 14; i2 >= 0; i2--) {
            long[] jArr2 = this.T[i2][bArr[i2] & 255];
            j2 ^= jArr2[0];
            j3 ^= jArr2[1];
        }
        Pack.longToBigEndian(j2, bArr, 0);
        Pack.longToBigEndian(j3, bArr, 8);
    }
}
