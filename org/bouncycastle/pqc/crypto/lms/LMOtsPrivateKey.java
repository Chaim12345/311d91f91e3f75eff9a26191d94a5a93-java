package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes4.dex */
class LMOtsPrivateKey {
    private final byte[] I;
    private final byte[] masterSecret;
    private final LMOtsParameters parameter;

    /* renamed from: q  reason: collision with root package name */
    private final int f14508q;

    public LMOtsPrivateKey(LMOtsParameters lMOtsParameters, byte[] bArr, int i2, byte[] bArr2) {
        this.parameter = lMOtsParameters;
        this.I = bArr;
        this.f14508q = i2;
        this.masterSecret = bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SeedDerive a() {
        SeedDerive seedDerive = new SeedDerive(this.I, this.masterSecret, DigestUtil.a(this.parameter.getDigestOID()));
        seedDerive.setQ(this.f14508q);
        return seedDerive;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMSContext b(LMSigParameters lMSigParameters, byte[][] bArr) {
        byte[] bArr2 = new byte[32];
        SeedDerive a2 = a();
        a2.setJ(-3);
        a2.deriveSeed(bArr2, false);
        Digest a3 = DigestUtil.a(this.parameter.getDigestOID());
        LmsUtils.b(getI(), a3);
        LmsUtils.e(getQ(), a3);
        LmsUtils.d((short) -32383, a3);
        LmsUtils.b(bArr2, a3);
        return new LMSContext(this, lMSigParameters, a3, bArr2, bArr);
    }

    public byte[] getI() {
        return this.I;
    }

    public byte[] getMasterSecret() {
        return this.masterSecret;
    }

    public LMOtsParameters getParameter() {
        return this.parameter;
    }

    public int getQ() {
        return this.f14508q;
    }
}
