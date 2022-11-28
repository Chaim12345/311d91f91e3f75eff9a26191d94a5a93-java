package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.DSADigestSigner;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.SignatureScheme;
/* loaded from: classes4.dex */
public class BcTlsECDSA13Verifier extends BcTlsVerifier {
    private final int signatureScheme;

    public BcTlsECDSA13Verifier(BcTlsCrypto bcTlsCrypto, ECPublicKeyParameters eCPublicKeyParameters, int i2) {
        super(bcTlsCrypto, eCPublicKeyParameters);
        if (!SignatureScheme.isECDSA(i2)) {
            throw new IllegalArgumentException("signatureScheme");
        }
        this.signatureScheme = i2;
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null) {
            int from = SignatureScheme.from(algorithm);
            int i2 = this.signatureScheme;
            if (from == i2) {
                DSADigestSigner dSADigestSigner = new DSADigestSigner(new ECDSASigner(new HMacDSAKCalculator(this.f14986a.createDigest(SignatureScheme.getCryptoHashAlgorithm(i2)))), new NullDigest());
                dSADigestSigner.init(false, this.f14987b);
                dSADigestSigner.update(bArr, 0, bArr.length);
                return dSADigestSigner.verifySignature(digitallySigned.getSignature());
            }
        }
        throw new IllegalStateException("Invalid algorithm: " + algorithm);
    }
}
