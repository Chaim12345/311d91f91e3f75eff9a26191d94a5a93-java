package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes3.dex */
public class X509CertificateStructure extends ASN1Object implements X509ObjectIdentifiers, PKCSObjectIdentifiers {

    /* renamed from: a  reason: collision with root package name */
    ASN1Sequence f13023a;

    /* renamed from: b  reason: collision with root package name */
    TBSCertificateStructure f13024b;

    /* renamed from: c  reason: collision with root package name */
    AlgorithmIdentifier f13025c;

    /* renamed from: d  reason: collision with root package name */
    ASN1BitString f13026d;

    public X509CertificateStructure(ASN1Sequence aSN1Sequence) {
        this.f13023a = aSN1Sequence;
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("sequence wrong size for a certificate");
        }
        this.f13024b = TBSCertificateStructure.getInstance(aSN1Sequence.getObjectAt(0));
        this.f13025c = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.f13026d = DERBitString.getInstance((Object) aSN1Sequence.getObjectAt(2));
    }

    public static X509CertificateStructure getInstance(Object obj) {
        if (obj instanceof X509CertificateStructure) {
            return (X509CertificateStructure) obj;
        }
        if (obj != null) {
            return new X509CertificateStructure(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static X509CertificateStructure getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Time getEndDate() {
        return this.f13024b.getEndDate();
    }

    public X500Name getIssuer() {
        return this.f13024b.getIssuer();
    }

    public ASN1Integer getSerialNumber() {
        return this.f13024b.getSerialNumber();
    }

    public ASN1BitString getSignature() {
        return this.f13026d;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.f13025c;
    }

    public Time getStartDate() {
        return this.f13024b.getStartDate();
    }

    public X500Name getSubject() {
        return this.f13024b.getSubject();
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.f13024b.getSubjectPublicKeyInfo();
    }

    public TBSCertificateStructure getTBSCertificate() {
        return this.f13024b;
    }

    public int getVersion() {
        return this.f13024b.getVersion();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f13023a;
    }
}
