package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes3.dex */
public class Certificate extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Sequence f12936a;

    /* renamed from: b  reason: collision with root package name */
    TBSCertificate f12937b;

    /* renamed from: c  reason: collision with root package name */
    AlgorithmIdentifier f12938c;

    /* renamed from: d  reason: collision with root package name */
    ASN1BitString f12939d;

    private Certificate(ASN1Sequence aSN1Sequence) {
        this.f12936a = aSN1Sequence;
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("sequence wrong size for a certificate");
        }
        this.f12937b = TBSCertificate.getInstance(aSN1Sequence.getObjectAt(0));
        this.f12938c = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.f12939d = ASN1BitString.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static Certificate getInstance(Object obj) {
        if (obj instanceof Certificate) {
            return (Certificate) obj;
        }
        if (obj != null) {
            return new Certificate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static Certificate getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Time getEndDate() {
        return this.f12937b.getEndDate();
    }

    public X500Name getIssuer() {
        return this.f12937b.getIssuer();
    }

    public ASN1Integer getSerialNumber() {
        return this.f12937b.getSerialNumber();
    }

    public ASN1BitString getSignature() {
        return this.f12939d;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.f12938c;
    }

    public Time getStartDate() {
        return this.f12937b.getStartDate();
    }

    public X500Name getSubject() {
        return this.f12937b.getSubject();
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.f12937b.getSubjectPublicKeyInfo();
    }

    public TBSCertificate getTBSCertificate() {
        return this.f12937b;
    }

    public ASN1Integer getVersion() {
        return this.f12937b.getVersion();
    }

    public int getVersionNumber() {
        return this.f12937b.getVersionNumber();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f12936a;
    }
}
