package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class BcTlsSM2Signer extends BcTlsSigner {

    /* renamed from: c  reason: collision with root package name */
    protected final byte[] f14981c;

    public BcTlsSM2Signer(BcTlsCrypto bcTlsCrypto, ECPrivateKeyParameters eCPrivateKeyParameters, byte[] bArr) {
        super(bcTlsCrypto, eCPrivateKeyParameters);
        this.f14981c = Arrays.clone(bArr);
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsSigner, org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm != null) {
            ParametersWithID parametersWithID = new ParametersWithID(new ParametersWithRandom(this.f14985b, this.f14984a.getSecureRandom()), this.f14981c);
            SM2Signer sM2Signer = new SM2Signer();
            sM2Signer.init(true, parametersWithID);
            return new BcTlsStreamSigner(sM2Signer);
        }
        throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
    }
}
