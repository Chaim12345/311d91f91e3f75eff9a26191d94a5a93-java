package org.bouncycastle.crypto.modes.gcm;

import com.google.common.base.Ascii;
import java.lang.reflect.Array;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class Tables8kGCMMultiplier implements GCMMultiplier {
    private byte[] H;
    private long[][][] T;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        if (this.T == null) {
            this.T = (long[][][]) Array.newInstance(long.class, 32, 16, 2);
        } else if (GCMUtil.areEqual(this.H, bArr) != 0) {
            return;
        }
        byte[] bArr2 = new byte[16];
        this.H = bArr2;
        GCMUtil.copy(bArr, bArr2);
        for (int i2 = 0; i2 < 32; i2++) {
            long[][][] jArr = this.T;
            long[][] jArr2 = jArr[i2];
            if (i2 == 0) {
                GCMUtil.asLongs(this.H, jArr2[1]);
                GCMUtil.multiplyP3(jArr2[1], jArr2[1]);
            } else {
                GCMUtil.multiplyP4(jArr[i2 - 1][1], jArr2[1]);
            }
            for (int i3 = 2; i3 < 16; i3 += 2) {
                GCMUtil.divideP(jArr2[i3 >> 1], jArr2[i3]);
                GCMUtil.xor(jArr2[i3], jArr2[1], jArr2[i3 + 1]);
            }
        }
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long j2 = 0;
        long j3 = 0;
        for (int i2 = 15; i2 >= 0; i2--) {
            long[][][] jArr = this.T;
            int i3 = i2 + i2;
            long[] jArr2 = jArr[i3 + 1][bArr[i2] & Ascii.SI];
            long[] jArr3 = jArr[i3][(bArr[i2] & 240) >>> 4];
            j2 ^= jArr2[0] ^ jArr3[0];
            j3 ^= jArr3[1] ^ jArr2[1];
        }
        Pack.longToBigEndian(j2, bArr, 0);
        Pack.longToBigEndian(j3, bArr, 8);
    }
}
