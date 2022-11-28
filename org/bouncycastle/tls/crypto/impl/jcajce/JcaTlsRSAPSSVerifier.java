package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.PublicKey;
import java.util.Objects;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.SignatureScheme;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.TlsVerifier;
/* loaded from: classes4.dex */
public class JcaTlsRSAPSSVerifier implements TlsVerifier {
    private final JcaTlsCrypto crypto;
    private final PublicKey publicKey;
    private final int signatureScheme;

    public JcaTlsRSAPSSVerifier(JcaTlsCrypto jcaTlsCrypto, PublicKey publicKey, int i2) {
        Objects.requireNonNull(jcaTlsCrypto, "crypto");
        Objects.requireNonNull(publicKey, "publicKey");
        if (!SignatureScheme.isRSAPSS(i2)) {
            throw new IllegalArgumentException("signatureScheme");
        }
        this.crypto = jcaTlsCrypto;
        this.publicKey = publicKey;
        this.signatureScheme = i2;
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null) {
            int from = SignatureScheme.from(algorithm);
            int i2 = this.signatureScheme;
            if (from == i2) {
                int cryptoHashAlgorithm = SignatureScheme.getCryptoHashAlgorithm(i2);
                String s2 = this.crypto.s(cryptoHashAlgorithm);
                return this.crypto.o(RSAUtil.a(s2) + "WITHRSAANDMGF1", RSAUtil.b(cryptoHashAlgorithm, s2, this.crypto.getHelper()), digitallySigned.getSignature(), this.publicKey);
            }
        }
        throw new IllegalStateException("Invalid algorithm: " + algorithm);
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) {
        throw new UnsupportedOperationException();
    }
}
