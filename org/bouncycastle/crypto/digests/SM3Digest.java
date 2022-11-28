package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class SM3Digest extends GeneralDigest {
    private static final int BLOCK_SIZE = 16;
    private static final int DIGEST_LENGTH = 32;
    private static final int[] T = new int[64];
    private int[] V;
    private int[] W;
    private int[] inwords;
    private int xOff;

    static {
        int i2;
        int i3 = 0;
        while (true) {
            if (i3 >= 16) {
                break;
            }
            T[i3] = (2043430169 >>> (32 - i3)) | (2043430169 << i3);
            i3++;
        }
        for (i2 = 16; i2 < 64; i2++) {
            int i4 = i2 % 32;
            T[i2] = (2055708042 >>> (32 - i4)) | (2055708042 << i4);
        }
    }

    public SM3Digest() {
        this.V = new int[8];
        this.inwords = new int[16];
        this.W = new int[68];
        reset();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        super(sM3Digest);
        this.V = new int[8];
        this.inwords = new int[16];
        this.W = new int[68];
        copyIn(sM3Digest);
    }

    private int FF0(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    private int FF1(int i2, int i3, int i4) {
        return (i2 & i4) | (i2 & i3) | (i3 & i4);
    }

    private int GG0(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    private int GG1(int i2, int i3, int i4) {
        return ((~i2) & i4) | (i3 & i2);
    }

    private int P0(int i2) {
        return (i2 ^ ((i2 << 9) | (i2 >>> 23))) ^ ((i2 << 17) | (i2 >>> 15));
    }

    private int P1(int i2) {
        return (i2 ^ ((i2 << 15) | (i2 >>> 17))) ^ ((i2 << 23) | (i2 >>> 9));
    }

    private void copyIn(SM3Digest sM3Digest) {
        int[] iArr = sM3Digest.V;
        int[] iArr2 = this.V;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = sM3Digest.inwords;
        int[] iArr4 = this.inwords;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        this.xOff = sM3Digest.xOff;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void c() {
        int i2;
        int i3 = 0;
        while (true) {
            if (i3 >= 16) {
                break;
            }
            this.W[i3] = this.inwords[i3];
            i3++;
        }
        for (int i4 = 16; i4 < 68; i4++) {
            int[] iArr = this.W;
            int i5 = iArr[i4 - 3];
            int i6 = iArr[i4 - 13];
            iArr[i4] = (P1(((i5 >>> 17) | (i5 << 15)) ^ (iArr[i4 - 16] ^ iArr[i4 - 9])) ^ ((i6 >>> 25) | (i6 << 7))) ^ this.W[i4 - 6];
        }
        int[] iArr2 = this.V;
        int i7 = iArr2[0];
        int i8 = iArr2[1];
        int i9 = iArr2[2];
        int i10 = iArr2[3];
        int i11 = iArr2[4];
        int i12 = iArr2[5];
        int i13 = iArr2[6];
        int i14 = iArr2[7];
        int i15 = 0;
        int i16 = i13;
        for (i2 = 16; i15 < i2; i2 = 16) {
            int i17 = (i7 << 12) | (i7 >>> 20);
            int i18 = i17 + i11 + T[i15];
            int i19 = (i18 << 7) | (i18 >>> 25);
            int[] iArr3 = this.W;
            int i20 = iArr3[i15];
            int i21 = (i8 << 9) | (i8 >>> 23);
            int i22 = (i12 << 19) | (i12 >>> 13);
            i15++;
            i12 = i11;
            i11 = P0(GG0(i11, i12, i16) + i14 + i19 + i20);
            i10 = i9;
            i9 = i21;
            i14 = i16;
            i16 = i22;
            i8 = i7;
            i7 = FF0(i7, i8, i9) + i10 + (i19 ^ i17) + (i20 ^ iArr3[i15 + 4]);
        }
        int i23 = i14;
        int i24 = i11;
        int i25 = i16;
        int i26 = i10;
        int i27 = i9;
        int i28 = i8;
        int i29 = i7;
        int i30 = 16;
        while (i30 < 64) {
            int i31 = (i29 << 12) | (i29 >>> 20);
            int i32 = i31 + i24 + T[i30];
            int i33 = (i32 << 7) | (i32 >>> 25);
            int[] iArr4 = this.W;
            int i34 = iArr4[i30];
            int i35 = (i12 << 19) | (i12 >>> 13);
            i30++;
            i12 = i24;
            i24 = P0(GG1(i24, i12, i25) + i23 + i33 + i34);
            i26 = i27;
            i27 = (i28 >>> 23) | (i28 << 9);
            i28 = i29;
            i29 = FF1(i29, i28, i27) + i26 + (i33 ^ i31) + (i34 ^ iArr4[i30 + 4]);
            i23 = i25;
            i25 = i35;
        }
        int[] iArr5 = this.V;
        iArr5[0] = i29 ^ iArr5[0];
        iArr5[1] = iArr5[1] ^ i28;
        iArr5[2] = iArr5[2] ^ i27;
        iArr5[3] = iArr5[3] ^ i26;
        iArr5[4] = iArr5[4] ^ i24;
        iArr5[5] = iArr5[5] ^ i12;
        iArr5[6] = i25 ^ iArr5[6];
        iArr5[7] = iArr5[7] ^ i23;
        this.xOff = 0;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SM3Digest(this);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void d(long j2) {
        int i2 = this.xOff;
        if (i2 > 14) {
            this.inwords[i2] = 0;
            this.xOff = i2 + 1;
            c();
        }
        while (true) {
            int i3 = this.xOff;
            if (i3 >= 14) {
                int[] iArr = this.inwords;
                int i4 = i3 + 1;
                this.xOff = i4;
                iArr[i3] = (int) (j2 >>> 32);
                this.xOff = i4 + 1;
                iArr[i4] = (int) j2;
                return;
            }
            this.inwords[i3] = 0;
            this.xOff = i3 + 1;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        finish();
        Pack.intToBigEndian(this.V, bArr, i2);
        reset();
        return 32;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void e(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        int i5 = (bArr[i4 + 1] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i3] & 255) << 16) | ((bArr[i4] & 255) << 8);
        int[] iArr = this.inwords;
        int i6 = this.xOff;
        iArr[i6] = i5;
        int i7 = i6 + 1;
        this.xOff = i7;
        if (i7 >= 16) {
            c();
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SM3";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        int[] iArr = this.V;
        iArr[0] = 1937774191;
        iArr[1] = 1226093241;
        iArr[2] = 388252375;
        iArr[3] = -628488704;
        iArr[4] = -1452330820;
        iArr[5] = 372324522;
        iArr[6] = -477237683;
        iArr[7] = -1325724082;
        this.xOff = 0;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SM3Digest sM3Digest = (SM3Digest) memoable;
        super.a(sM3Digest);
        copyIn(sM3Digest);
    }
}
