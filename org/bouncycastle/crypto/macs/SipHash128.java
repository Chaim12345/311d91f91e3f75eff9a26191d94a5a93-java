package org.bouncycastle.crypto.macs;

import org.apache.commons.cli.HelpFormatter;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class SipHash128 extends SipHash {
    public SipHash128() {
    }

    public SipHash128(int i2, int i3) {
        super(i2, i3);
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i2) {
        int i3;
        long j2 = this.f13444i >>> ((7 - this.f13445j) << 3);
        this.f13444i = j2;
        long j3 = j2 >>> 8;
        this.f13444i = j3;
        this.f13444i = j3 | ((((this.f13446k << 3) + i3) & 255) << 56);
        b();
        this.f13442g ^= 238;
        a(this.f13437b);
        long j4 = this.f13440e;
        long j5 = this.f13441f;
        long j6 = ((j4 ^ j5) ^ this.f13442g) ^ this.f13443h;
        this.f13441f = j5 ^ 221;
        a(this.f13437b);
        reset();
        Pack.longToLittleEndian(j6, bArr, i2);
        Pack.longToLittleEndian(((this.f13440e ^ this.f13441f) ^ this.f13442g) ^ this.f13443h, bArr, i2 + 8);
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash
    public long doFinal() {
        throw new UnsupportedOperationException("doFinal() is not supported");
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "SipHash128-" + this.f13436a + HelpFormatter.DEFAULT_OPT_PREFIX + this.f13437b;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public void reset() {
        super.reset();
        this.f13441f ^= 238;
    }
}
