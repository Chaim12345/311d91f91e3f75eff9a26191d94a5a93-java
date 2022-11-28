package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.PrivateKey;
import java.util.Objects;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.SignatureScheme;
import org.bouncycastle.tls.crypto.TlsSigner;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes4.dex */
public class JcaTlsRSAPSSSigner implements TlsSigner {
    private final JcaTlsCrypto crypto;
    private final PrivateKey privateKey;
    private final int signatureScheme;

    public JcaTlsRSAPSSSigner(JcaTlsCrypto jcaTlsCrypto, PrivateKey privateKey, int i2) {
        Objects.requireNonNull(jcaTlsCrypto, "crypto");
        Objects.requireNonNull(privateKey, "privateKey");
        if (!SignatureScheme.isRSAPSS(i2)) {
            throw new IllegalArgumentException("signatureScheme");
        }
        this.crypto = jcaTlsCrypto;
        this.privateKey = privateKey;
        this.signatureScheme = i2;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm != null) {
            int from = SignatureScheme.from(signatureAndHashAlgorithm);
            int i2 = this.signatureScheme;
            if (from == i2) {
                int cryptoHashAlgorithm = SignatureScheme.getCryptoHashAlgorithm(i2);
                String s2 = this.crypto.s(cryptoHashAlgorithm);
                return this.crypto.m(RSAUtil.a(s2) + "WITHRSAANDMGF1", RSAUtil.b(cryptoHashAlgorithm, s2, this.crypto.getHelper()), this.privateKey, true);
            }
        }
        throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
    }
}
