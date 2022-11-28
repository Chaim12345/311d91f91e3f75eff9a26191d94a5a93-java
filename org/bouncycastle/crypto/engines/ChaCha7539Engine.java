package org.bouncycastle.crypto.engines;

import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class ChaCha7539Engine extends Salsa20Engine {
    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void a() {
        int[] iArr = this.f13390b;
        int i2 = iArr[12] + 1;
        iArr[12] = i2;
        if (i2 == 0) {
            throw new IllegalStateException("attempt to increase counter past 2^32.");
        }
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void b(long j2) {
        int i2 = (int) (j2 >>> 32);
        int i3 = (int) j2;
        if (i2 > 0) {
            throw new IllegalStateException("attempt to increase counter past 2^32.");
        }
        int[] iArr = this.f13390b;
        int i4 = iArr[12];
        iArr[12] = iArr[12] + i3;
        if (i4 != 0 && iArr[12] < i4) {
            throw new IllegalStateException("attempt to increase counter past 2^32.");
        }
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void c(byte[] bArr) {
        ChaChaEngine.chachaCore(this.f13389a, this.f13390b, this.f13391c);
        Pack.intToLittleEndian(this.f13391c, bArr, 0);
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected long d() {
        return this.f13390b[12] & BodyPartID.bodyIdMax;
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected int e() {
        return 12;
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void g() {
        this.f13390b[12] = 0;
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine, org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "ChaCha7539";
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void h() {
        int[] iArr = this.f13390b;
        if (iArr[12] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        iArr[12] = iArr[12] - 1;
    }

    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    protected void i(long j2) {
        int i2 = (int) (j2 >>> 32);
        int i3 = (int) j2;
        if (i2 != 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        int[] iArr = this.f13390b;
        if ((iArr[12] & BodyPartID.bodyIdMax) < (BodyPartID.bodyIdMax & i3)) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        iArr[12] = iArr[12] - i3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.crypto.engines.Salsa20Engine
    public void j(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 256 bit key");
            }
            f(bArr.length, this.f13390b, 0);
            Pack.littleEndianToInt(bArr, 0, this.f13390b, 4, 8);
        }
        Pack.littleEndianToInt(bArr2, 0, this.f13390b, 13, 3);
    }
}
