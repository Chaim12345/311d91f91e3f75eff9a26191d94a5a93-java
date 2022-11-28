package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class SHA512Digest extends LongDigest {
    private static final int DIGEST_LENGTH = 64;

    public SHA512Digest() {
    }

    public SHA512Digest(SHA512Digest sHA512Digest) {
        super(sHA512Digest);
    }

    public SHA512Digest(byte[] bArr) {
        g(bArr);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA512Digest(this);
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
        Pack.longToBigEndian(this.f13313g, bArr, i2 + 48);
        Pack.longToBigEndian(this.f13314h, bArr, i2 + 56);
        reset();
        return 64;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-512";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 64;
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
        this.f13307a = 7640891576956012808L;
        this.f13308b = -4942790177534073029L;
        this.f13309c = 4354685564936845355L;
        this.f13310d = -6534734903238641935L;
        this.f13311e = 5840696475078001361L;
        this.f13312f = -7276294671716946913L;
        this.f13313g = 2270897969802886507L;
        this.f13314h = 6620516959819538809L;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        a((SHA512Digest) memoable);
    }
}
