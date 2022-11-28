package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.signers.DSADigestSigner;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
/* loaded from: classes4.dex */
public abstract class BcTlsDSSVerifier extends BcTlsVerifier {
    /* JADX INFO: Access modifiers changed from: protected */
    public BcTlsDSSVerifier(BcTlsCrypto bcTlsCrypto, AsymmetricKeyParameter asymmetricKeyParameter) {
        super(bcTlsCrypto, asymmetricKeyParameter);
    }

    protected abstract DSA a(int i2);

    protected abstract short b();

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null && algorithm.getSignature() != b()) {
            throw new IllegalStateException("Invalid algorithm: " + algorithm);
        }
        DSADigestSigner dSADigestSigner = new DSADigestSigner(a(algorithm == null ? 2 : TlsCryptoUtils.getHash(algorithm.getHash())), new NullDigest());
        dSADigestSigner.init(false, this.f14987b);
        if (algorithm == null) {
            dSADigestSigner.update(bArr, 16, 20);
        } else {
            dSADigestSigner.update(bArr, 0, bArr.length);
        }
        return dSADigestSigner.verifySignature(digitallySigned.getSignature());
    }
}
