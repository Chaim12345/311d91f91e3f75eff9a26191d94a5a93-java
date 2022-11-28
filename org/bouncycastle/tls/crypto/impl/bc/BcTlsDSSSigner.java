package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.DSADigestSigner;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
/* loaded from: classes4.dex */
public abstract class BcTlsDSSSigner extends BcTlsSigner {
    /* JADX INFO: Access modifiers changed from: protected */
    public BcTlsDSSSigner(BcTlsCrypto bcTlsCrypto, AsymmetricKeyParameter asymmetricKeyParameter) {
        super(bcTlsCrypto, asymmetricKeyParameter);
    }

    protected abstract DSA a(int i2);

    protected abstract short b();

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        int i2;
        int length;
        if (signatureAndHashAlgorithm != null && signatureAndHashAlgorithm.getSignature() != b()) {
            throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
        }
        DSADigestSigner dSADigestSigner = new DSADigestSigner(a(signatureAndHashAlgorithm == null ? 2 : TlsCryptoUtils.getHash(signatureAndHashAlgorithm.getHash())), new NullDigest());
        dSADigestSigner.init(true, new ParametersWithRandom(this.f14985b, this.f14984a.getSecureRandom()));
        if (signatureAndHashAlgorithm == null) {
            i2 = 16;
            length = 20;
        } else {
            i2 = 0;
            length = bArr.length;
        }
        dSADigestSigner.update(bArr, i2, length);
        try {
            return dSADigestSigner.generateSignature();
        } catch (CryptoException e2) {
            throw new TlsFatalAlert((short) 80, (Throwable) e2);
        }
    }
}
