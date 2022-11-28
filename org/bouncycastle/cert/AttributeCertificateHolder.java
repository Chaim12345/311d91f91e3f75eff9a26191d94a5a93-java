package org.bouncycastle.cert;

import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.Holder;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.asn1.x509.ObjectDigestInfo;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;
/* loaded from: classes3.dex */
public class AttributeCertificateHolder implements Selector {
    private static DigestCalculatorProvider digestCalculatorProvider;

    /* renamed from: a  reason: collision with root package name */
    final Holder f13067a;

    public AttributeCertificateHolder(int i2, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier2, byte[] bArr) {
        this.f13067a = new Holder(new ObjectDigestInfo(i2, aSN1ObjectIdentifier2, new AlgorithmIdentifier(aSN1ObjectIdentifier), Arrays.clone(bArr)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AttributeCertificateHolder(ASN1Sequence aSN1Sequence) {
        this.f13067a = Holder.getInstance(aSN1Sequence);
    }

    public AttributeCertificateHolder(X500Name x500Name) {
        this.f13067a = new Holder(generateGeneralNames(x500Name));
    }

    public AttributeCertificateHolder(X500Name x500Name, BigInteger bigInteger) {
        this.f13067a = new Holder(new IssuerSerial(generateGeneralNames(x500Name), new ASN1Integer(bigInteger)));
    }

    public AttributeCertificateHolder(X509CertificateHolder x509CertificateHolder) {
        this.f13067a = new Holder(new IssuerSerial(generateGeneralNames(x509CertificateHolder.getIssuer()), new ASN1Integer(x509CertificateHolder.getSerialNumber())));
    }

    private GeneralNames generateGeneralNames(X500Name x500Name) {
        return new GeneralNames(new GeneralName(x500Name));
    }

    private X500Name[] getPrincipals(GeneralName[] generalNameArr) {
        ArrayList arrayList = new ArrayList(generalNameArr.length);
        for (int i2 = 0; i2 != generalNameArr.length; i2++) {
            if (generalNameArr[i2].getTagNo() == 4) {
                arrayList.add(X500Name.getInstance(generalNameArr[i2].getName()));
            }
        }
        return (X500Name[]) arrayList.toArray(new X500Name[arrayList.size()]);
    }

    private boolean matchesDN(X500Name x500Name, GeneralNames generalNames) {
        GeneralName[] names = generalNames.getNames();
        for (int i2 = 0; i2 != names.length; i2++) {
            GeneralName generalName = names[i2];
            if (generalName.getTagNo() == 4 && X500Name.getInstance(generalName.getName()).equals(x500Name)) {
                return true;
            }
        }
        return false;
    }

    public static void setDigestCalculatorProvider(DigestCalculatorProvider digestCalculatorProvider2) {
        digestCalculatorProvider = digestCalculatorProvider2;
    }

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        return new AttributeCertificateHolder((ASN1Sequence) this.f13067a.toASN1Primitive());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeCertificateHolder) {
            return this.f13067a.equals(((AttributeCertificateHolder) obj).f13067a);
        }
        return false;
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        if (this.f13067a.getObjectDigestInfo() != null) {
            return this.f13067a.getObjectDigestInfo().getDigestAlgorithm();
        }
        return null;
    }

    public int getDigestedObjectType() {
        if (this.f13067a.getObjectDigestInfo() != null) {
            return this.f13067a.getObjectDigestInfo().getDigestedObjectType().intValueExact();
        }
        return -1;
    }

    public X500Name[] getEntityNames() {
        if (this.f13067a.getEntityName() != null) {
            return getPrincipals(this.f13067a.getEntityName().getNames());
        }
        return null;
    }

    public X500Name[] getIssuer() {
        if (this.f13067a.getBaseCertificateID() != null) {
            return getPrincipals(this.f13067a.getBaseCertificateID().getIssuer().getNames());
        }
        return null;
    }

    public byte[] getObjectDigest() {
        if (this.f13067a.getObjectDigestInfo() != null) {
            return this.f13067a.getObjectDigestInfo().getObjectDigest().getBytes();
        }
        return null;
    }

    public ASN1ObjectIdentifier getOtherObjectTypeID() {
        if (this.f13067a.getObjectDigestInfo() != null) {
            new ASN1ObjectIdentifier(this.f13067a.getObjectDigestInfo().getOtherObjectTypeID().getId());
            return null;
        }
        return null;
    }

    public BigInteger getSerialNumber() {
        if (this.f13067a.getBaseCertificateID() != null) {
            return this.f13067a.getBaseCertificateID().getSerial().getValue();
        }
        return null;
    }

    public int hashCode() {
        return this.f13067a.hashCode();
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        if (obj instanceof X509CertificateHolder) {
            X509CertificateHolder x509CertificateHolder = (X509CertificateHolder) obj;
            if (this.f13067a.getBaseCertificateID() != null) {
                return this.f13067a.getBaseCertificateID().getSerial().hasValue(x509CertificateHolder.getSerialNumber()) && matchesDN(x509CertificateHolder.getIssuer(), this.f13067a.getBaseCertificateID().getIssuer());
            } else if (this.f13067a.getEntityName() == null || !matchesDN(x509CertificateHolder.getSubject(), this.f13067a.getEntityName())) {
                if (this.f13067a.getObjectDigestInfo() != null) {
                    try {
                        DigestCalculator digestCalculator = digestCalculatorProvider.get(this.f13067a.getObjectDigestInfo().getDigestAlgorithm());
                        OutputStream outputStream = digestCalculator.getOutputStream();
                        int digestedObjectType = getDigestedObjectType();
                        if (digestedObjectType == 0) {
                            outputStream.write(x509CertificateHolder.getSubjectPublicKeyInfo().getEncoded());
                        } else if (digestedObjectType == 1) {
                            outputStream.write(x509CertificateHolder.getEncoded());
                        }
                        outputStream.close();
                        Arrays.areEqual(digestCalculator.getDigest(), getObjectDigest());
                    } catch (Exception unused) {
                    }
                }
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
