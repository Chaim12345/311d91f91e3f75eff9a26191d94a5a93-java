package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Objects;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.TlsVerifier;
/* loaded from: classes4.dex */
public class JcaTlsRSAVerifier implements TlsVerifier {
    private final JcaTlsCrypto crypto;
    private final PublicKey publicKey;
    private Signature rawVerifier = null;

    public JcaTlsRSAVerifier(JcaTlsCrypto jcaTlsCrypto, PublicKey publicKey) {
        Objects.requireNonNull(jcaTlsCrypto, "crypto");
        Objects.requireNonNull(publicKey, "publicKey");
        this.crypto = jcaTlsCrypto;
        this.publicKey = publicKey;
    }

    protected Signature a() {
        if (this.rawVerifier == null) {
            Signature createSignature = this.crypto.getHelper().createSignature("NoneWithRSA");
            this.rawVerifier = createSignature;
            createSignature.initVerify(this.publicKey);
        }
        return this.rawVerifier;
    }

    protected boolean b() {
        try {
            return JcaUtils.b(a().getProvider());
        } catch (GeneralSecurityException unused) {
            return true;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null && algorithm.getSignature() == 1 && JcaUtils.c() && b()) {
            return this.crypto.p(digitallySigned, this.publicKey);
        }
        return null;
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        try {
            Signature a2 = a();
            if (algorithm == null) {
                a2.update(bArr, 0, bArr.length);
            } else if (algorithm.getSignature() != 1) {
                throw new IllegalStateException("Invalid algorithm: " + algorithm);
            } else {
                byte[] encoded = new DigestInfo(new AlgorithmIdentifier(TlsUtils.getOIDForHashAlgorithm(algorithm.getHash()), DERNull.INSTANCE), bArr).getEncoded();
                a2.update(encoded, 0, encoded.length);
            }
            return a2.verify(digitallySigned.getSignature());
        } catch (GeneralSecurityException e2) {
            throw Exceptions.b("unable to process signature: " + e2.getMessage(), e2);
        }
    }
}
