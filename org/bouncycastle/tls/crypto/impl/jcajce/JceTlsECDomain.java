package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPublicKeySpec;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.tls.NamedGroup;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCryptoException;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsECDomain;
/* loaded from: classes4.dex */
public class JceTlsECDomain implements TlsECDomain {

    /* renamed from: a  reason: collision with root package name */
    protected final JcaTlsCrypto f15036a;

    /* renamed from: b  reason: collision with root package name */
    protected final ECParameterSpec f15037b;

    /* renamed from: c  reason: collision with root package name */
    protected final ECCurve f15038c;

    public JceTlsECDomain(JcaTlsCrypto jcaTlsCrypto, TlsECConfig tlsECConfig) {
        ECParameterSpec f2;
        int namedGroup = tlsECConfig.getNamedGroup();
        if (!NamedGroup.refersToAnECDSACurve(namedGroup) || (f2 = ECUtil.f(jcaTlsCrypto, NamedGroup.getCurveName(namedGroup))) == null) {
            throw new IllegalArgumentException("NamedGroup not supported: " + NamedGroup.getText(namedGroup));
        }
        this.f15036a = jcaTlsCrypto;
        this.f15037b = f2;
        this.f15038c = ECUtil.a(f2.getCurve(), f2.getOrder(), f2.getCofactor());
    }

    public JceTlsSecret calculateECDHAgreement(PrivateKey privateKey, PublicKey publicKey) {
        try {
            return this.f15036a.a(this.f15036a.calculateKeyAgreement("ECDH", privateKey, publicKey, "TlsPremasterSecret"));
        } catch (GeneralSecurityException e2) {
            throw new TlsCryptoException("cannot calculate secret", e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsECDomain
    public TlsAgreement createECDH() {
        return new JceTlsECDH(this);
    }

    public ECPoint decodePoint(byte[] bArr) {
        return this.f15038c.decodePoint(bArr);
    }

    public PublicKey decodePublicKey(byte[] bArr) {
        try {
            ECPoint normalize = decodePoint(bArr).normalize();
            return this.f15036a.getHelper().createKeyFactory("EC").generatePublic(new ECPublicKeySpec(new java.security.spec.ECPoint(normalize.getAffineXCoord().toBigInteger(), normalize.getAffineYCoord().toBigInteger()), this.f15037b));
        } catch (Exception e2) {
            throw new TlsFatalAlert((short) 47, (Throwable) e2);
        }
    }

    public byte[] encodePoint(ECPoint eCPoint) {
        return eCPoint.getEncoded(false);
    }

    public byte[] encodePublicKey(PublicKey publicKey) {
        if (publicKey instanceof ECPublicKey) {
            return encodePoint(((ECPublicKey) publicKey).getQ());
        }
        if (publicKey instanceof java.security.interfaces.ECPublicKey) {
            java.security.spec.ECPoint w = ((java.security.interfaces.ECPublicKey) publicKey).getW();
            return encodePoint(this.f15038c.createPoint(w.getAffineX(), w.getAffineY()));
        }
        return SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getPublicKeyData().getOctets();
    }

    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator createKeyPairGenerator = this.f15036a.getHelper().createKeyPairGenerator("EC");
            createKeyPairGenerator.initialize(this.f15037b, this.f15036a.getSecureRandom());
            return createKeyPairGenerator.generateKeyPair();
        } catch (GeneralSecurityException e2) {
            throw Exceptions.b("unable to create key pair: " + e2.getMessage(), e2);
        }
    }
}
