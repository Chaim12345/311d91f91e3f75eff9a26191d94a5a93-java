package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
class WotsPlus {
    private final SPHINCSPlusEngine engine;
    private final int w;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WotsPlus(SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.engine = sPHINCSPlusEngine;
        this.w = sPHINCSPlusEngine.f14592c;
    }

    int[] a(byte[] bArr, int i2, int i3) {
        int[] iArr = new int[i3];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < i3; i8++) {
            if (i4 == 0) {
                i7 = bArr[i5];
                i5++;
                i4 += 8;
            }
            i4 -= this.engine.f14593d;
            iArr[i6] = (i7 >>> i4) & (i2 - 1);
            i6++;
        }
        return iArr;
    }

    byte[] b(byte[] bArr, int i2, int i3, byte[] bArr2, ADRS adrs) {
        if (i3 == 0) {
            return Arrays.clone(bArr);
        }
        int i4 = i2 + i3;
        if (i4 > this.w - 1) {
            return null;
        }
        byte[] b2 = b(bArr, i2, i3 - 1, bArr2, adrs);
        adrs.setHashAddress(i4 - 1);
        return this.engine.F(bArr2, adrs, b2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] c(byte[] bArr, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        byte[][] bArr3 = new byte[this.engine.f14594e];
        for (int i2 = 0; i2 < this.engine.f14594e; i2++) {
            ADRS adrs3 = new ADRS(adrs);
            adrs3.setChainAddress(i2);
            adrs3.setHashAddress(0);
            bArr3[i2] = b(this.engine.b(bArr, adrs3), 0, this.w - 1, bArr2, adrs3);
        }
        adrs2.setType(1);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr2, adrs2, Arrays.concatenate(bArr3));
    }

    public byte[] pkFromSig(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        SPHINCSPlusEngine sPHINCSPlusEngine;
        ADRS adrs2 = new ADRS(adrs);
        int[] a2 = a(bArr2, this.w, this.engine.f14595f);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            sPHINCSPlusEngine = this.engine;
            if (i2 >= sPHINCSPlusEngine.f14595f) {
                break;
            }
            i3 += (this.w - 1) - a2[i2];
            i2++;
        }
        int i4 = sPHINCSPlusEngine.f14596g;
        int i5 = sPHINCSPlusEngine.f14593d;
        int[] concatenate = Arrays.concatenate(a2, a(Arrays.copyOfRange(Pack.intToBigEndian(i3 << (8 - ((i4 * i5) % 8))), 4 - (((i4 * i5) + 7) / 8), 4), this.w, this.engine.f14596g));
        SPHINCSPlusEngine sPHINCSPlusEngine2 = this.engine;
        byte[] bArr4 = new byte[sPHINCSPlusEngine2.f14591b];
        byte[][] bArr5 = new byte[sPHINCSPlusEngine2.f14594e];
        for (int i6 = 0; i6 < this.engine.f14594e; i6++) {
            adrs.setChainAddress(i6);
            int i7 = this.engine.f14591b;
            System.arraycopy(bArr, i6 * i7, bArr4, 0, i7);
            bArr5[i6] = b(bArr4, concatenate[i6], (this.w - 1) - concatenate[i6], bArr3, adrs);
        }
        adrs2.setType(1);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr3, adrs2, Arrays.concatenate(bArr5));
    }

    public byte[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        SPHINCSPlusEngine sPHINCSPlusEngine;
        ADRS adrs2 = new ADRS(adrs);
        int[] a2 = a(bArr, this.w, this.engine.f14595f);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            sPHINCSPlusEngine = this.engine;
            if (i2 >= sPHINCSPlusEngine.f14595f) {
                break;
            }
            i3 += (this.w - 1) - a2[i2];
            i2++;
        }
        int i4 = sPHINCSPlusEngine.f14593d;
        if (i4 % 8 != 0) {
            i3 <<= 8 - ((sPHINCSPlusEngine.f14596g * i4) % 8);
        }
        byte[] intToBigEndian = Pack.intToBigEndian(i3);
        int[] concatenate = Arrays.concatenate(a2, a(Arrays.copyOfRange(intToBigEndian, ((sPHINCSPlusEngine.f14596g * i4) + 7) / 8, intToBigEndian.length), this.w, this.engine.f14596g));
        byte[][] bArr4 = new byte[this.engine.f14594e];
        for (int i5 = 0; i5 < this.engine.f14594e; i5++) {
            adrs2.setChainAddress(i5);
            adrs2.setHashAddress(0);
            bArr4[i5] = b(this.engine.b(bArr2, adrs2), 0, concatenate[i5], bArr3, adrs2);
        }
        return Arrays.concatenate(bArr4);
    }
}
