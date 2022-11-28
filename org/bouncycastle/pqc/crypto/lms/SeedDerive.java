package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes4.dex */
class SeedDerive {
    private final byte[] I;
    private final Digest digest;

    /* renamed from: j  reason: collision with root package name */
    private int f14515j;
    private final byte[] masterSeed;

    /* renamed from: q  reason: collision with root package name */
    private int f14516q;

    public SeedDerive(byte[] bArr, byte[] bArr2, Digest digest) {
        this.I = bArr;
        this.masterSeed = bArr2;
        this.digest = digest;
    }

    public void deriveSeed(byte[] bArr, boolean z) {
        deriveSeed(bArr, z, 0);
    }

    public void deriveSeed(byte[] bArr, boolean z, int i2) {
        deriveSeed(bArr, i2);
        if (z) {
            this.f14515j++;
        }
    }

    public byte[] deriveSeed(byte[] bArr, int i2) {
        if (bArr.length >= this.digest.getDigestSize()) {
            Digest digest = this.digest;
            byte[] bArr2 = this.I;
            digest.update(bArr2, 0, bArr2.length);
            this.digest.update((byte) (this.f14516q >>> 24));
            this.digest.update((byte) (this.f14516q >>> 16));
            this.digest.update((byte) (this.f14516q >>> 8));
            this.digest.update((byte) this.f14516q);
            this.digest.update((byte) (this.f14515j >>> 8));
            this.digest.update((byte) this.f14515j);
            this.digest.update((byte) -1);
            Digest digest2 = this.digest;
            byte[] bArr3 = this.masterSeed;
            digest2.update(bArr3, 0, bArr3.length);
            this.digest.doFinal(bArr, i2);
            return bArr;
        }
        throw new IllegalArgumentException("target length is less than digest size.");
    }

    public byte[] getI() {
        return this.I;
    }

    public int getJ() {
        return this.f14515j;
    }

    public byte[] getMasterSeed() {
        return this.masterSeed;
    }

    public int getQ() {
        return this.f14516q;
    }

    public void setJ(int i2) {
        this.f14515j = i2;
    }

    public void setQ(int i2) {
        this.f14516q = i2;
    }
}
