package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.SignatureScheme;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes4.dex */
public class BcTlsRSAPSSSigner extends BcTlsSigner {
    private final int signatureScheme;

    public BcTlsRSAPSSSigner(BcTlsCrypto bcTlsCrypto, RSAKeyParameters rSAKeyParameters, int i2) {
        super(bcTlsCrypto, rSAKeyParameters);
        if (!SignatureScheme.isRSAPSS(i2)) {
            throw new IllegalArgumentException("signatureScheme");
        }
        this.signatureScheme = i2;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsSigner, org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm != null) {
            int from = SignatureScheme.from(signatureAndHashAlgorithm);
            int i2 = this.signatureScheme;
            if (from == i2) {
                Digest createDigest = this.f14984a.createDigest(SignatureScheme.getCryptoHashAlgorithm(i2));
                PSSSigner pSSSigner = new PSSSigner(new RSABlindedEngine(), createDigest, createDigest.getDigestSize());
                pSSSigner.init(true, new ParametersWithRandom(this.f14985b, this.f14984a.getSecureRandom()));
                return new BcTlsStreamSigner(pSSSigner);
            }
        }
        throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
    }
}
