package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
public class BCRSAPrivateKey implements RSAPrivateKey, PKCS12BagAttributeCarrier {
    private static BigInteger ZERO = BigInteger.valueOf(0);

    /* renamed from: a  reason: collision with root package name */
    protected BigInteger f13702a;
    private byte[] algorithmIdentifierEnc;

    /* renamed from: b  reason: collision with root package name */
    protected BigInteger f13703b;

    /* renamed from: c  reason: collision with root package name */
    protected transient AlgorithmIdentifier f13704c;

    /* renamed from: d  reason: collision with root package name */
    protected transient RSAKeyParameters f13705d;

    /* renamed from: e  reason: collision with root package name */
    protected transient PKCS12BagAttributeCarrierImpl f13706e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCRSAPrivateKey(RSAPrivateKey rSAPrivateKey) {
        AlgorithmIdentifier algorithmIdentifier = BCRSAPublicKey.f13707a;
        this.algorithmIdentifierEnc = getEncoding(algorithmIdentifier);
        this.f13704c = algorithmIdentifier;
        this.f13706e = new PKCS12BagAttributeCarrierImpl();
        this.f13702a = rSAPrivateKey.getModulus();
        this.f13703b = rSAPrivateKey.getPrivateExponent();
        this.f13705d = new RSAKeyParameters(true, this.f13702a, this.f13703b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCRSAPrivateKey(RSAPrivateKeySpec rSAPrivateKeySpec) {
        AlgorithmIdentifier algorithmIdentifier = BCRSAPublicKey.f13707a;
        this.algorithmIdentifierEnc = getEncoding(algorithmIdentifier);
        this.f13704c = algorithmIdentifier;
        this.f13706e = new PKCS12BagAttributeCarrierImpl();
        this.f13702a = rSAPrivateKeySpec.getModulus();
        this.f13703b = rSAPrivateKeySpec.getPrivateExponent();
        this.f13705d = new RSAKeyParameters(true, this.f13702a, this.f13703b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCRSAPrivateKey(AlgorithmIdentifier algorithmIdentifier, org.bouncycastle.asn1.pkcs.RSAPrivateKey rSAPrivateKey) {
        AlgorithmIdentifier algorithmIdentifier2 = BCRSAPublicKey.f13707a;
        this.algorithmIdentifierEnc = getEncoding(algorithmIdentifier2);
        this.f13704c = algorithmIdentifier2;
        this.f13706e = new PKCS12BagAttributeCarrierImpl();
        this.f13704c = algorithmIdentifier;
        this.algorithmIdentifierEnc = getEncoding(algorithmIdentifier);
        this.f13702a = rSAPrivateKey.getModulus();
        this.f13703b = rSAPrivateKey.getPrivateExponent();
        this.f13705d = new RSAKeyParameters(true, this.f13702a, this.f13703b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCRSAPrivateKey(AlgorithmIdentifier algorithmIdentifier, RSAKeyParameters rSAKeyParameters) {
        AlgorithmIdentifier algorithmIdentifier2 = BCRSAPublicKey.f13707a;
        this.algorithmIdentifierEnc = getEncoding(algorithmIdentifier2);
        this.f13704c = algorithmIdentifier2;
        this.f13706e = new PKCS12BagAttributeCarrierImpl();
        this.f13704c = algorithmIdentifier;
        this.algorithmIdentifierEnc = getEncoding(algorithmIdentifier);
        this.f13702a = rSAKeyParameters.getModulus();
        this.f13703b = rSAKeyParameters.getExponent();
        this.f13705d = rSAKeyParameters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCRSAPrivateKey(RSAKeyParameters rSAKeyParameters) {
        AlgorithmIdentifier algorithmIdentifier = BCRSAPublicKey.f13707a;
        this.algorithmIdentifierEnc = getEncoding(algorithmIdentifier);
        this.f13704c = algorithmIdentifier;
        this.f13706e = new PKCS12BagAttributeCarrierImpl();
        this.f13702a = rSAKeyParameters.getModulus();
        this.f13703b = rSAKeyParameters.getExponent();
        this.f13705d = rSAKeyParameters;
    }

    private static byte[] getEncoding(AlgorithmIdentifier algorithmIdentifier) {
        try {
            return algorithmIdentifier.getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        if (this.algorithmIdentifierEnc == null) {
            this.algorithmIdentifierEnc = getEncoding(BCRSAPublicKey.f13707a);
        }
        this.f13704c = AlgorithmIdentifier.getInstance(this.algorithmIdentifierEnc);
        this.f13706e = new PKCS12BagAttributeCarrierImpl();
        this.f13705d = new RSAKeyParameters(true, this.f13702a, this.f13703b);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RSAKeyParameters a() {
        return this.f13705d;
    }

    public boolean equals(Object obj) {
        if (obj instanceof RSAPrivateKey) {
            if (obj == this) {
                return true;
            }
            RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) obj;
            return getModulus().equals(rSAPrivateKey.getModulus()) && getPrivateExponent().equals(rSAPrivateKey.getPrivateExponent());
        }
        return false;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.f13704c.getAlgorithm().equals((ASN1Primitive) PKCSObjectIdentifiers.id_RSASSA_PSS) ? "RSASSA-PSS" : "RSA";
    }

    @Override // org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.f13706e.getBagAttribute(aSN1ObjectIdentifier);
    }

    @Override // org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public Enumeration getBagAttributeKeys() {
        return this.f13706e.getBagAttributeKeys();
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        AlgorithmIdentifier algorithmIdentifier = this.f13704c;
        BigInteger modulus = getModulus();
        BigInteger bigInteger = ZERO;
        BigInteger privateExponent = getPrivateExponent();
        BigInteger bigInteger2 = ZERO;
        return KeyUtil.getEncodedPrivateKeyInfo(algorithmIdentifier, new org.bouncycastle.asn1.pkcs.RSAPrivateKey(modulus, bigInteger, privateExponent, bigInteger2, bigInteger2, bigInteger2, bigInteger2, bigInteger2));
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // java.security.interfaces.RSAKey
    public BigInteger getModulus() {
        return this.f13702a;
    }

    @Override // java.security.interfaces.RSAPrivateKey
    public BigInteger getPrivateExponent() {
        return this.f13703b;
    }

    public int hashCode() {
        return getModulus().hashCode() ^ getPrivateExponent().hashCode();
    }

    @Override // org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public void setBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.f13706e.setBagAttribute(aSN1ObjectIdentifier, aSN1Encodable);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append("RSA Private Key [");
        stringBuffer.append(RSAUtil.b(getModulus()));
        stringBuffer.append("],[]");
        stringBuffer.append(lineSeparator);
        stringBuffer.append("            modulus: ");
        stringBuffer.append(getModulus().toString(16));
        stringBuffer.append(lineSeparator);
        return stringBuffer.toString();
    }
}
