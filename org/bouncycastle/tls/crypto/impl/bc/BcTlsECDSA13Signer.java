package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.DSADigestSigner;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.SignatureScheme;
import org.bouncycastle.tls.TlsFatalAlert;
/* loaded from: classes4.dex */
public class BcTlsECDSA13Signer extends BcTlsSigner {
    private final int signatureScheme;

    public BcTlsECDSA13Signer(BcTlsCrypto bcTlsCrypto, ECPrivateKeyParameters eCPrivateKeyParameters, int i2) {
        super(bcTlsCrypto, eCPrivateKeyParameters);
        if (!SignatureScheme.isECDSA(i2)) {
            throw new IllegalArgumentException("signatureScheme");
        }
        this.signatureScheme = i2;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        if (signatureAndHashAlgorithm != null) {
            int from = SignatureScheme.from(signatureAndHashAlgorithm);
            int i2 = this.signatureScheme;
            if (from == i2) {
                DSADigestSigner dSADigestSigner = new DSADigestSigner(new ECDSASigner(new HMacDSAKCalculator(this.f14984a.createDigest(SignatureScheme.getCryptoHashAlgorithm(i2)))), new NullDigest());
                dSADigestSigner.init(true, new ParametersWithRandom(this.f14985b, this.f14984a.getSecureRandom()));
                dSADigestSigner.update(bArr, 0, bArr.length);
                try {
                    return dSADigestSigner.generateSignature();
                } catch (CryptoException e2) {
                    throw new TlsFatalAlert((short) 80, (Throwable) e2);
                }
            }
        }
        throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
    }
}
