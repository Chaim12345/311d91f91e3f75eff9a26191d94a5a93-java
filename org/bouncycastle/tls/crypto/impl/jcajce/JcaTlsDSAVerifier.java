package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.PublicKey;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.HashAlgorithm;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
/* loaded from: classes4.dex */
public class JcaTlsDSAVerifier extends JcaTlsDSSVerifier {
    public JcaTlsDSAVerifier(JcaTlsCrypto jcaTlsCrypto, PublicKey publicKey) {
        super(jcaTlsCrypto, publicKey, (short) 2, "NoneWithDSA");
    }

    @Override // org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsDSSVerifier, org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm == null || this.f15009c != algorithm.getSignature() || HashAlgorithm.getOutputSize(algorithm.getHash()) == 20) {
            return null;
        }
        return this.f15007a.p(digitallySigned, this.f15008b);
    }
}
