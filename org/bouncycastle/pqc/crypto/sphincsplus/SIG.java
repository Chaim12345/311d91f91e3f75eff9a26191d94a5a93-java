package org.bouncycastle.pqc.crypto.sphincsplus;
/* loaded from: classes4.dex */
class SIG {

    /* renamed from: r  reason: collision with root package name */
    private final byte[] f14583r;
    private final SIG_FORS[] sig_fors;
    private final SIG_XMSS[] sig_ht;

    public SIG(int i2, int i3, int i4, int i5, int i6, int i7, byte[] bArr) {
        byte[] bArr2 = new byte[i2];
        this.f14583r = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        this.sig_fors = new SIG_FORS[i3];
        int i8 = i2;
        for (int i9 = 0; i9 != i3; i9++) {
            byte[] bArr3 = new byte[i2];
            System.arraycopy(bArr, i8, bArr3, 0, i2);
            i8 += i2;
            byte[][] bArr4 = new byte[i4];
            for (int i10 = 0; i10 != i4; i10++) {
                bArr4[i10] = new byte[i2];
                System.arraycopy(bArr, i8, bArr4[i10], 0, i2);
                i8 += i2;
            }
            this.sig_fors[i9] = new SIG_FORS(bArr3, bArr4);
        }
        this.sig_ht = new SIG_XMSS[i5];
        for (int i11 = 0; i11 != i5; i11++) {
            int i12 = i7 * i2;
            byte[] bArr5 = new byte[i12];
            System.arraycopy(bArr, i8, bArr5, 0, i12);
            i8 += i12;
            byte[][] bArr6 = new byte[i6];
            for (int i13 = 0; i13 != i6; i13++) {
                bArr6[i13] = new byte[i2];
                System.arraycopy(bArr, i8, bArr6[i13], 0, i2);
                i8 += i2;
            }
            this.sig_ht[i11] = new SIG_XMSS(bArr5, bArr6);
        }
        if (i8 != bArr.length) {
            throw new IllegalArgumentException("signature wrong length");
        }
    }

    public byte[] getR() {
        return this.f14583r;
    }

    public SIG_FORS[] getSIG_FORS() {
        return this.sig_fors;
    }

    public SIG_XMSS[] getSIG_HT() {
        return this.sig_ht;
    }
}
