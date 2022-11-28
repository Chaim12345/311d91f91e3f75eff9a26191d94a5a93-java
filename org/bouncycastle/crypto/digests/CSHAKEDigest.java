package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class CSHAKEDigest extends SHAKEDigest {
    private static final byte[] padding = new byte[100];
    private final byte[] diff;

    public CSHAKEDigest(int i2, byte[] bArr, byte[] bArr2) {
        super(i2);
        if ((bArr == null || bArr.length == 0) && (bArr2 == null || bArr2.length == 0)) {
            this.diff = null;
            return;
        }
        this.diff = Arrays.concatenate(XofUtils.leftEncode(this.f13302c / 8), encodeString(bArr), encodeString(bArr2));
        diffPadAndAbsorb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CSHAKEDigest(CSHAKEDigest cSHAKEDigest) {
        super(cSHAKEDigest);
        this.diff = Arrays.clone(cSHAKEDigest.diff);
    }

    private void diffPadAndAbsorb() {
        int i2 = this.f13302c / 8;
        byte[] bArr = this.diff;
        b(bArr, 0, bArr.length);
        int length = this.diff.length % i2;
        if (length == 0) {
            return;
        }
        while (true) {
            i2 -= length;
            byte[] bArr2 = padding;
            if (i2 <= bArr2.length) {
                b(bArr2, 0, i2);
                return;
            } else {
                b(bArr2, 0, bArr2.length);
                length = bArr2.length;
            }
        }
    }

    private byte[] encodeString(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? XofUtils.leftEncode(0L) : Arrays.concatenate(XofUtils.leftEncode(bArr.length * 8), bArr);
    }

    @Override // org.bouncycastle.crypto.digests.SHAKEDigest, org.bouncycastle.crypto.Xof
    public int doOutput(byte[] bArr, int i2, int i3) {
        if (this.diff != null) {
            if (!this.f13305f) {
                c(0, 2);
            }
            d(bArr, i2, i3 * 8);
            return i3;
        }
        return super.doOutput(bArr, i2, i3);
    }

    @Override // org.bouncycastle.crypto.digests.SHAKEDigest, org.bouncycastle.crypto.digests.KeccakDigest, org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "CSHAKE" + this.f13304e;
    }

    @Override // org.bouncycastle.crypto.digests.KeccakDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        if (this.diff != null) {
            diffPadAndAbsorb();
        }
    }
}
