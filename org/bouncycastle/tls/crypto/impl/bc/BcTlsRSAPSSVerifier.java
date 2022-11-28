package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.SignatureScheme;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
/* loaded from: classes4.dex */
public class BcTlsRSAPSSVerifier extends BcTlsVerifier {
    private final int signatureScheme;

    public BcTlsRSAPSSVerifier(BcTlsCrypto bcTlsCrypto, RSAKeyParameters rSAKeyParameters, int i2) {
        super(bcTlsCrypto, rSAKeyParameters);
        if (!SignatureScheme.isRSAPSS(i2)) {
            throw new IllegalArgumentException("signatureScheme");
        }
        this.signatureScheme = i2;
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsVerifier, org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null) {
            int from = SignatureScheme.from(algorithm);
            int i2 = this.signatureScheme;
            if (from == i2) {
                Digest createDigest = this.f14986a.createDigest(SignatureScheme.getCryptoHashAlgorithm(i2));
                PSSSigner pSSSigner = new PSSSigner(new RSAEngine(), createDigest, createDigest.getDigestSize());
                pSSSigner.init(false, this.f14987b);
                return new BcTlsStreamVerifier(pSSSigner, digitallySigned.getSignature());
            }
        }
        throw new IllegalStateException("Invalid algorithm: " + algorithm);
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) {
        throw new UnsupportedOperationException();
    }
}
