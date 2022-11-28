package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Objects;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.TlsSigner;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes4.dex */
public class JcaTlsRSASigner implements TlsSigner {
    private final JcaTlsCrypto crypto;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private Signature rawSigner = null;

    public JcaTlsRSASigner(JcaTlsCrypto jcaTlsCrypto, PrivateKey privateKey, PublicKey publicKey) {
        Objects.requireNonNull(jcaTlsCrypto, "crypto");
        Objects.requireNonNull(privateKey, "privateKey");
        this.crypto = jcaTlsCrypto;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    protected Signature a() {
        if (this.rawSigner == null) {
            Signature createSignature = this.crypto.getHelper().createSignature("NoneWithRSA");
            this.rawSigner = createSignature;
            createSignature.initSign(this.privateKey, this.crypto.getSecureRandom());
        }
        return this.rawSigner;
    }

    protected boolean b() {
        try {
            return JcaUtils.b(a().getProvider());
        } catch (GeneralSecurityException unused) {
            return true;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        try {
            try {
                Signature a2 = a();
                if (signatureAndHashAlgorithm != null) {
                    if (signatureAndHashAlgorithm.getSignature() != 1) {
                        throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
                    }
                    bArr = new DigestInfo(new AlgorithmIdentifier(TlsUtils.getOIDForHashAlgorithm(signatureAndHashAlgorithm.getHash()), DERNull.INSTANCE), bArr).getEncoded();
                }
                a2.update(bArr, 0, bArr.length);
                byte[] sign = a2.sign();
                a2.initVerify(this.publicKey);
                a2.update(bArr, 0, bArr.length);
                if (a2.verify(sign)) {
                    return sign;
                }
                throw new TlsFatalAlert((short) 80);
            } catch (GeneralSecurityException e2) {
                throw new TlsFatalAlert((short) 80, (Throwable) e2);
            }
        } finally {
            this.rawSigner = null;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm != null && 1 == signatureAndHashAlgorithm.getSignature() && JcaUtils.c() && b()) {
            return this.crypto.r(signatureAndHashAlgorithm, this.privateKey, true, this.publicKey);
        }
        return null;
    }
}
