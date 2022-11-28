package org.bouncycastle.crypto.digests;

import com.facebook.stetho.dumpapp.Framer;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.MemoableResetException;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class SHA512tDigest extends LongDigest {
    private long H1t;
    private long H2t;
    private long H3t;
    private long H4t;
    private long H5t;
    private long H6t;
    private long H7t;
    private long H8t;
    private int digestLength;

    public SHA512tDigest(int i2) {
        if (i2 >= 512) {
            throw new IllegalArgumentException("bitLength cannot be >= 512");
        }
        if (i2 % 8 != 0) {
            throw new IllegalArgumentException("bitLength needs to be a multiple of 8");
        }
        if (i2 == 384) {
            throw new IllegalArgumentException("bitLength cannot be 384 use SHA384 instead");
        }
        int i3 = i2 / 8;
        this.digestLength = i3;
        tIvGenerate(i3 * 8);
        reset();
    }

    public SHA512tDigest(SHA512tDigest sHA512tDigest) {
        super(sHA512tDigest);
        this.digestLength = sHA512tDigest.digestLength;
        reset(sHA512tDigest);
    }

    public SHA512tDigest(byte[] bArr) {
        this(readDigestLength(bArr));
        g(bArr);
    }

    private static void intToBigEndian(int i2, byte[] bArr, int i3, int i4) {
        int min = Math.min(4, i4);
        while (true) {
            min--;
            if (min < 0) {
                return;
            }
            bArr[i3 + min] = (byte) (i2 >>> ((3 - min) * 8));
        }
    }

    private static void longToBigEndian(long j2, byte[] bArr, int i2, int i3) {
        if (i3 > 0) {
            intToBigEndian((int) (j2 >>> 32), bArr, i2, i3);
            if (i3 > 4) {
                intToBigEndian((int) (j2 & BodyPartID.bodyIdMax), bArr, i2 + 4, i3 - 4);
            }
        }
    }

    private static int readDigestLength(byte[] bArr) {
        return Pack.bigEndianToInt(bArr, bArr.length - 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x007f, code lost:
        if (r4 > 10) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void tIvGenerate(int i2) {
        this.f13307a = -3482333909917012819L;
        this.f13308b = 2216346199247487646L;
        this.f13309c = -7364697282686394994L;
        this.f13310d = 65953792586715988L;
        this.f13311e = -816286391624063116L;
        this.f13312f = 4512832404995164602L;
        this.f13313g = -5033199132376557362L;
        this.f13314h = -124578254951840548L;
        update((byte) 83);
        update((byte) 72);
        update((byte) 65);
        update(Framer.STDIN_FRAME_PREFIX);
        update((byte) 53);
        update(Framer.STDOUT_FRAME_PREFIX);
        update(Framer.STDERR_FRAME_PREFIX);
        update((byte) 47);
        if (i2 > 100) {
            update((byte) ((i2 / 100) + 48));
            i2 %= 100;
        }
        update((byte) ((i2 / 10) + 48));
        i2 %= 10;
        update((byte) (i2 + 48));
        finish();
        this.H1t = this.f13307a;
        this.H2t = this.f13308b;
        this.H3t = this.f13309c;
        this.H4t = this.f13310d;
        this.H5t = this.f13311e;
        this.H6t = this.f13312f;
        this.H7t = this.f13313g;
        this.H8t = this.f13314h;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA512tDigest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        finish();
        longToBigEndian(this.f13307a, bArr, i2, this.digestLength);
        longToBigEndian(this.f13308b, bArr, i2 + 8, this.digestLength - 8);
        longToBigEndian(this.f13309c, bArr, i2 + 16, this.digestLength - 16);
        longToBigEndian(this.f13310d, bArr, i2 + 24, this.digestLength - 24);
        longToBigEndian(this.f13311e, bArr, i2 + 32, this.digestLength - 32);
        longToBigEndian(this.f13312f, bArr, i2 + 40, this.digestLength - 40);
        longToBigEndian(this.f13313g, bArr, i2 + 48, this.digestLength - 48);
        longToBigEndian(this.f13314h, bArr, i2 + 56, this.digestLength - 56);
        reset();
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-512/" + Integer.toString(this.digestLength * 8);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        int b2 = b();
        byte[] bArr = new byte[b2 + 4];
        c(bArr);
        Pack.intToBigEndian(this.digestLength * 8, bArr, b2);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.LongDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f13307a = this.H1t;
        this.f13308b = this.H2t;
        this.f13309c = this.H3t;
        this.f13310d = this.H4t;
        this.f13311e = this.H5t;
        this.f13312f = this.H6t;
        this.f13313g = this.H7t;
        this.f13314h = this.H8t;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SHA512tDigest sHA512tDigest = (SHA512tDigest) memoable;
        if (this.digestLength != sHA512tDigest.digestLength) {
            throw new MemoableResetException("digestLength inappropriate in other");
        }
        super.a(sHA512tDigest);
        this.H1t = sHA512tDigest.H1t;
        this.H2t = sHA512tDigest.H2t;
        this.H3t = sHA512tDigest.H3t;
        this.H4t = sHA512tDigest.H4t;
        this.H5t = sHA512tDigest.H5t;
        this.H6t = sHA512tDigest.H6t;
        this.H7t = sHA512tDigest.H7t;
        this.H8t = sHA512tDigest.H8t;
    }
}
