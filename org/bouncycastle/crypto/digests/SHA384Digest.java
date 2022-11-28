package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class SHA384Digest extends LongDigest {
    private static final int DIGEST_LENGTH = 48;

    public SHA384Digest() {
    }

    public SHA384Digest(SHA384Digest sHA384Digest) {
        super(sHA384Digest);
    }

    public SHA384Digest(byte[] bArr) {
        g(bArr);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA384Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        finish();
        Pack.longToBigEndian(this.f13307a, bArr, i2);
        Pack.longToBigEndian(this.f13308b, bArr, i2 + 8);
        Pack.longToBigEndian(this.f13309c, bArr, i2 + 16);
        Pack.longToBigEndian(this.f13310d, bArr, i2 + 24);
        Pack.longToBigEndian(this.f13311e, bArr, i2 + 32);
        Pack.longToBigEndian(this.f13312f, bArr, i2 + 40);
        reset();
        return 48;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-384";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 48;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[b()];
        super.c(bArr);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.LongDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f13307a = -3766243637369397544L;
        this.f13308b = 7105036623409894663L;
        this.f13309c = -7973340178411365097L;
        this.f13310d = 1526699215303891257L;
        this.f13311e = 7436329637833083697L;
        this.f13312f = -8163818279084223215L;
        this.f13313g = -2662702644619276377L;
        this.f13314h = 5167115440072839076L;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        super.a((SHA384Digest) memoable);
    }
}
