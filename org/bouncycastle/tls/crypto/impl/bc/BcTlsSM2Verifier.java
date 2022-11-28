package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class BcTlsSM2Verifier extends BcTlsVerifier {

    /* renamed from: c  reason: collision with root package name */
    protected final byte[] f14982c;

    public BcTlsSM2Verifier(BcTlsCrypto bcTlsCrypto, ECPublicKeyParameters eCPublicKeyParameters, byte[] bArr) {
        super(bcTlsCrypto, eCPublicKeyParameters);
        this.f14982c = Arrays.clone(bArr);
    }

    @Override // org.bouncycastle.tls.crypto.impl.bc.BcTlsVerifier, org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null) {
            ParametersWithID parametersWithID = new ParametersWithID(this.f14987b, this.f14982c);
            SM2Signer sM2Signer = new SM2Signer();
            sM2Signer.init(false, parametersWithID);
            return new BcTlsStreamVerifier(sM2Signer, digitallySigned.getSignature());
        }
        throw new IllegalStateException("Invalid algorithm: " + algorithm);
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) {
        throw new UnsupportedOperationException();
    }
}
