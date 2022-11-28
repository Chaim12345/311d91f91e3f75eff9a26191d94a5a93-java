package org.bouncycastle.crypto.engines;

import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class XSalsa20Engine extends Salsa20Engine {
    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected int e() {
        return 24;
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine, org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "XSalsa20";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    public void j(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            throw new IllegalArgumentException(getAlgorithmName() + " doesn't support re-init with null key");
        } else if (bArr.length != 32) {
            throw new IllegalArgumentException(getAlgorithmName() + " requires a 256 bit key");
        } else {
            super.j(bArr, bArr2);
            Pack.littleEndianToInt(bArr2, 8, this.f13390b, 8, 2);
            int[] iArr = this.f13390b;
            int[] iArr2 = new int[iArr.length];
            Salsa20Engine.salsaCore(20, iArr, iArr2);
            int[] iArr3 = this.f13390b;
            iArr3[1] = iArr2[0] - iArr3[0];
            iArr3[2] = iArr2[5] - iArr3[5];
            iArr3[3] = iArr2[10] - iArr3[10];
            iArr3[4] = iArr2[15] - iArr3[15];
            iArr3[11] = iArr2[6] - iArr3[6];
            iArr3[12] = iArr2[7] - iArr3[7];
            iArr3[13] = iArr2[8] - iArr3[8];
            iArr3[14] = iArr2[9] - iArr3[9];
            Pack.littleEndianToInt(bArr2, 16, iArr3, 6, 2);
        }
    }
}
