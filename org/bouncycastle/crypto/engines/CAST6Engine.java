package org.bouncycastle.crypto.engines;
/* loaded from: classes3.dex */
public final class CAST6Engine extends CAST5Engine {

    /* renamed from: c  reason: collision with root package name */
    protected int[] f13347c = new int[48];

    /* renamed from: d  reason: collision with root package name */
    protected int[] f13348d = new int[48];

    /* renamed from: e  reason: collision with root package name */
    protected int[] f13349e = new int[192];

    /* renamed from: f  reason: collision with root package name */
    protected int[] f13350f = new int[192];
    private int[] _workingKey = new int[8];

    @Override // org.bouncycastle.crypto.engines.CAST5Engine, org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "CAST6";
    }

    @Override // org.bouncycastle.crypto.engines.CAST5Engine, org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.engines.CAST5Engine
    protected int j(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr = new int[4];
        m(c(bArr, i2), c(bArr, i2 + 4), c(bArr, i2 + 8), c(bArr, i2 + 12), iArr);
        a(iArr[0], bArr2, i3);
        a(iArr[1], bArr2, i3 + 4);
        a(iArr[2], bArr2, i3 + 8);
        a(iArr[3], bArr2, i3 + 12);
        return 16;
    }

    @Override // org.bouncycastle.crypto.engines.CAST5Engine
    protected int k(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr = new int[4];
        n(c(bArr, i2), c(bArr, i2 + 4), c(bArr, i2 + 8), c(bArr, i2 + 12), iArr);
        a(iArr[0], bArr2, i3);
        a(iArr[1], bArr2, i3 + 4);
        a(iArr[2], bArr2, i3 + 8);
        a(iArr[3], bArr2, i3 + 12);
        return 16;
    }

    @Override // org.bouncycastle.crypto.engines.CAST5Engine
    protected void l(byte[] bArr) {
        int i2 = 1518500249;
        int i3 = 19;
        for (int i4 = 0; i4 < 24; i4++) {
            for (int i5 = 0; i5 < 8; i5++) {
                int i6 = (i4 * 8) + i5;
                this.f13350f[i6] = i2;
                i2 += 1859775393;
                this.f13349e[i6] = i3;
                i3 = (i3 + 17) & 31;
            }
        }
        byte[] bArr2 = new byte[64];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        for (int i7 = 0; i7 < 8; i7++) {
            this._workingKey[i7] = c(bArr2, i7 * 4);
        }
        for (int i8 = 0; i8 < 12; i8++) {
            int i9 = i8 * 2;
            int i10 = i9 * 8;
            int[] iArr = this._workingKey;
            iArr[6] = iArr[6] ^ f(iArr[7], this.f13350f[i10], this.f13349e[i10]);
            int[] iArr2 = this._workingKey;
            int i11 = i10 + 1;
            iArr2[5] = iArr2[5] ^ g(iArr2[6], this.f13350f[i11], this.f13349e[i11]);
            int[] iArr3 = this._workingKey;
            int i12 = i10 + 2;
            iArr3[4] = iArr3[4] ^ h(iArr3[5], this.f13350f[i12], this.f13349e[i12]);
            int[] iArr4 = this._workingKey;
            int i13 = i10 + 3;
            iArr4[3] = f(iArr4[4], this.f13350f[i13], this.f13349e[i13]) ^ iArr4[3];
            int[] iArr5 = this._workingKey;
            int i14 = i10 + 4;
            iArr5[2] = g(iArr5[3], this.f13350f[i14], this.f13349e[i14]) ^ iArr5[2];
            int[] iArr6 = this._workingKey;
            int i15 = i10 + 5;
            iArr6[1] = h(iArr6[2], this.f13350f[i15], this.f13349e[i15]) ^ iArr6[1];
            int[] iArr7 = this._workingKey;
            int i16 = i10 + 6;
            iArr7[0] = iArr7[0] ^ f(iArr7[1], this.f13350f[i16], this.f13349e[i16]);
            int[] iArr8 = this._workingKey;
            int i17 = i10 + 7;
            iArr8[7] = g(iArr8[0], this.f13350f[i17], this.f13349e[i17]) ^ iArr8[7];
            int i18 = (i9 + 1) * 8;
            int[] iArr9 = this._workingKey;
            iArr9[6] = iArr9[6] ^ f(iArr9[7], this.f13350f[i18], this.f13349e[i18]);
            int[] iArr10 = this._workingKey;
            int i19 = i18 + 1;
            iArr10[5] = iArr10[5] ^ g(iArr10[6], this.f13350f[i19], this.f13349e[i19]);
            int[] iArr11 = this._workingKey;
            int i20 = i18 + 2;
            iArr11[4] = iArr11[4] ^ h(iArr11[5], this.f13350f[i20], this.f13349e[i20]);
            int[] iArr12 = this._workingKey;
            int i21 = i18 + 3;
            iArr12[3] = f(iArr12[4], this.f13350f[i21], this.f13349e[i21]) ^ iArr12[3];
            int[] iArr13 = this._workingKey;
            int i22 = i18 + 4;
            iArr13[2] = g(iArr13[3], this.f13350f[i22], this.f13349e[i22]) ^ iArr13[2];
            int[] iArr14 = this._workingKey;
            int i23 = i18 + 5;
            iArr14[1] = h(iArr14[2], this.f13350f[i23], this.f13349e[i23]) ^ iArr14[1];
            int[] iArr15 = this._workingKey;
            int i24 = i18 + 6;
            iArr15[0] = iArr15[0] ^ f(iArr15[1], this.f13350f[i24], this.f13349e[i24]);
            int[] iArr16 = this._workingKey;
            int i25 = i18 + 7;
            iArr16[7] = g(iArr16[0], this.f13350f[i25], this.f13349e[i25]) ^ iArr16[7];
            int[] iArr17 = this.f13347c;
            int i26 = i8 * 4;
            int[] iArr18 = this._workingKey;
            iArr17[i26] = iArr18[0] & 31;
            int i27 = i26 + 1;
            iArr17[i27] = iArr18[2] & 31;
            int i28 = i26 + 2;
            iArr17[i28] = iArr18[4] & 31;
            int i29 = i26 + 3;
            iArr17[i29] = iArr18[6] & 31;
            int[] iArr19 = this.f13348d;
            iArr19[i26] = iArr18[7];
            iArr19[i27] = iArr18[5];
            iArr19[i28] = iArr18[3];
            iArr19[i29] = iArr18[1];
        }
    }

    protected final void m(int i2, int i3, int i4, int i5, int[] iArr) {
        int i6;
        int i7 = 0;
        while (true) {
            if (i7 >= 6) {
                break;
            }
            int i8 = (11 - i7) * 4;
            i4 ^= f(i5, this.f13348d[i8], this.f13347c[i8]);
            int i9 = i8 + 1;
            i3 ^= g(i4, this.f13348d[i9], this.f13347c[i9]);
            int i10 = i8 + 2;
            i2 ^= h(i3, this.f13348d[i10], this.f13347c[i10]);
            int i11 = i8 + 3;
            i5 ^= f(i2, this.f13348d[i11], this.f13347c[i11]);
            i7++;
        }
        for (i6 = 6; i6 < 12; i6++) {
            int i12 = (11 - i6) * 4;
            int i13 = i12 + 3;
            i5 ^= f(i2, this.f13348d[i13], this.f13347c[i13]);
            int i14 = i12 + 2;
            i2 ^= h(i3, this.f13348d[i14], this.f13347c[i14]);
            int i15 = i12 + 1;
            i3 ^= g(i4, this.f13348d[i15], this.f13347c[i15]);
            i4 ^= f(i5, this.f13348d[i12], this.f13347c[i12]);
        }
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = i4;
        iArr[3] = i5;
    }

    protected final void n(int i2, int i3, int i4, int i5, int[] iArr) {
        int i6;
        int i7 = 0;
        while (true) {
            if (i7 >= 6) {
                break;
            }
            int i8 = i7 * 4;
            i4 ^= f(i5, this.f13348d[i8], this.f13347c[i8]);
            int i9 = i8 + 1;
            i3 ^= g(i4, this.f13348d[i9], this.f13347c[i9]);
            int i10 = i8 + 2;
            i2 ^= h(i3, this.f13348d[i10], this.f13347c[i10]);
            int i11 = i8 + 3;
            i5 ^= f(i2, this.f13348d[i11], this.f13347c[i11]);
            i7++;
        }
        for (i6 = 6; i6 < 12; i6++) {
            int i12 = i6 * 4;
            int i13 = i12 + 3;
            i5 ^= f(i2, this.f13348d[i13], this.f13347c[i13]);
            int i14 = i12 + 2;
            i2 ^= h(i3, this.f13348d[i14], this.f13347c[i14]);
            int i15 = i12 + 1;
            i3 ^= g(i4, this.f13348d[i15], this.f13347c[i15]);
            i4 ^= f(i5, this.f13348d[i12], this.f13347c[i12]);
        }
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = i4;
        iArr[3] = i5;
    }

    @Override // org.bouncycastle.crypto.engines.CAST5Engine, org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
