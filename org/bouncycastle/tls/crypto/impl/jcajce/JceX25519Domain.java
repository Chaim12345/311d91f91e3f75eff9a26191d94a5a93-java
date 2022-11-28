package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCryptoException;
import org.bouncycastle.tls.crypto.TlsECDomain;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class JceX25519Domain implements TlsECDomain {

    /* renamed from: a  reason: collision with root package name */
    protected final JcaTlsCrypto f15043a;

    public JceX25519Domain(JcaTlsCrypto jcaTlsCrypto) {
        this.f15043a = jcaTlsCrypto;
    }

    public JceTlsSecret calculateECDHAgreement(PrivateKey privateKey, PublicKey publicKey) {
        try {
            byte[] calculateKeyAgreement = this.f15043a.calculateKeyAgreement(XDHParameterSpec.X25519, privateKey, publicKey, "TlsPremasterSecret");
            if (calculateKeyAgreement == null || calculateKeyAgreement.length != 32) {
                throw new TlsCryptoException("invalid secret calculated");
            }
            if (Arrays.areAllZeroes(calculateKeyAgreement, 0, calculateKeyAgreement.length)) {
                throw new TlsFatalAlert((short) 40);
            }
            return this.f15043a.a(calculateKeyAgreement);
        } catch (GeneralSecurityException e2) {
            throw new TlsCryptoException("cannot calculate secret", e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsECDomain
    public TlsAgreement createECDH() {
        return new JceX25519(this);
    }

    public PublicKey decodePublicKey(byte[] bArr) {
        return XDHUtil.a(this.f15043a, XDHParameterSpec.X25519, EdECObjectIdentifiers.id_X25519, bArr);
    }

    public byte[] encodePublicKey(PublicKey publicKey) {
        return XDHUtil.b(publicKey);
    }

    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator createKeyPairGenerator = this.f15043a.getHelper().createKeyPairGenerator(XDHParameterSpec.X25519);
            createKeyPairGenerator.initialize(255, this.f15043a.getSecureRandom());
            return createKeyPairGenerator.generateKeyPair();
        } catch (GeneralSecurityException e2) {
            throw Exceptions.b("unable to create key pair: " + e2.getMessage(), e2);
        }
    }
}
