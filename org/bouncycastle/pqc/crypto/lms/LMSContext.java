package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes4.dex */
public class LMSContext implements Digest {
    private final byte[] C;
    private volatile Digest digest;
    private final LMOtsPrivateKey key;
    private final byte[][] path;
    private final LMOtsPublicKey publicKey;
    private final LMSigParameters sigParams;
    private final Object signature;
    private LMSSignedPubKey[] signedPubKeys;

    public LMSContext(LMOtsPrivateKey lMOtsPrivateKey, LMSigParameters lMSigParameters, Digest digest, byte[] bArr, byte[][] bArr2) {
        this.key = lMOtsPrivateKey;
        this.sigParams = lMSigParameters;
        this.digest = digest;
        this.C = bArr;
        this.path = bArr2;
        this.publicKey = null;
        this.signature = null;
    }

    public LMSContext(LMOtsPublicKey lMOtsPublicKey, Object obj, Digest digest) {
        this.publicKey = lMOtsPublicKey;
        this.signature = obj;
        this.digest = digest;
        this.C = null;
        this.key = null;
        this.sigParams = null;
        this.path = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a() {
        return this.C;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[][] b() {
        return this.path;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMOtsPrivateKey c() {
        return this.key;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] d() {
        byte[] bArr = new byte[34];
        this.digest.doFinal(bArr, 0);
        this.digest = null;
        return bArr;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        return this.digest.doFinal(bArr, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMSigParameters e() {
        return this.sigParams;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMSSignedPubKey[] f() {
        return this.signedPubKeys;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMSContext g(LMSSignedPubKey[] lMSSignedPubKeyArr) {
        this.signedPubKeys = lMSSignedPubKeyArr;
        return this;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return this.digest.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.digest.getDigestSize();
    }

    public LMOtsPublicKey getPublicKey() {
        return this.publicKey;
    }

    public Object getSignature() {
        return this.signature;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.digest.reset();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b2) {
        this.digest.update(b2);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        this.digest.update(bArr, i2, i3);
    }
}
