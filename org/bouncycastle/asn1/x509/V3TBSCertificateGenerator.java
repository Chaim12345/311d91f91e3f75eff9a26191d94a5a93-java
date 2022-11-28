package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes3.dex */
public class V3TBSCertificateGenerator {

    /* renamed from: a  reason: collision with root package name */
    DERTaggedObject f13014a = new DERTaggedObject(true, 0, (ASN1Encodable) new ASN1Integer(2));
    private boolean altNamePresentAndCritical;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f13015b;

    /* renamed from: c  reason: collision with root package name */
    AlgorithmIdentifier f13016c;

    /* renamed from: d  reason: collision with root package name */
    X500Name f13017d;

    /* renamed from: e  reason: collision with root package name */
    Time f13018e;

    /* renamed from: f  reason: collision with root package name */
    Time f13019f;

    /* renamed from: g  reason: collision with root package name */
    X500Name f13020g;

    /* renamed from: h  reason: collision with root package name */
    SubjectPublicKeyInfo f13021h;

    /* renamed from: i  reason: collision with root package name */
    Extensions f13022i;
    private DERBitString issuerUniqueID;
    private DERBitString subjectUniqueID;

    public TBSCertificate generateTBSCertificate() {
        if (this.f13015b == null || this.f13016c == null || this.f13017d == null || this.f13018e == null || this.f13019f == null || ((this.f13020g == null && !this.altNamePresentAndCritical) || this.f13021h == null)) {
            throw new IllegalStateException("not all mandatory fields set in V3 TBScertificate generator");
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(10);
        aSN1EncodableVector.add(this.f13014a);
        aSN1EncodableVector.add(this.f13015b);
        aSN1EncodableVector.add(this.f13016c);
        aSN1EncodableVector.add(this.f13017d);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(2);
        aSN1EncodableVector2.add(this.f13018e);
        aSN1EncodableVector2.add(this.f13019f);
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        ASN1Encodable aSN1Encodable = this.f13020g;
        if (aSN1Encodable == null) {
            aSN1Encodable = new DERSequence();
        }
        aSN1EncodableVector.add(aSN1Encodable);
        aSN1EncodableVector.add(this.f13021h);
        DERBitString dERBitString = this.issuerUniqueID;
        if (dERBitString != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) dERBitString));
        }
        DERBitString dERBitString2 = this.subjectUniqueID;
        if (dERBitString2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, (ASN1Encodable) dERBitString2));
        }
        Extensions extensions = this.f13022i;
        if (extensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 3, (ASN1Encodable) extensions));
        }
        return TBSCertificate.getInstance(new DERSequence(aSN1EncodableVector));
    }

    public void setEndDate(ASN1UTCTime aSN1UTCTime) {
        this.f13019f = new Time(aSN1UTCTime);
    }

    public void setEndDate(Time time) {
        this.f13019f = time;
    }

    public void setExtensions(Extensions extensions) {
        Extension extension;
        this.f13022i = extensions;
        if (extensions == null || (extension = extensions.getExtension(Extension.subjectAlternativeName)) == null || !extension.isCritical()) {
            return;
        }
        this.altNamePresentAndCritical = true;
    }

    public void setExtensions(X509Extensions x509Extensions) {
        setExtensions(Extensions.getInstance(x509Extensions));
    }

    public void setIssuer(X500Name x500Name) {
        this.f13017d = x500Name;
    }

    public void setIssuer(X509Name x509Name) {
        this.f13017d = X500Name.getInstance(x509Name);
    }

    public void setIssuerUniqueID(DERBitString dERBitString) {
        this.issuerUniqueID = dERBitString;
    }

    public void setSerialNumber(ASN1Integer aSN1Integer) {
        this.f13015b = aSN1Integer;
    }

    public void setSignature(AlgorithmIdentifier algorithmIdentifier) {
        this.f13016c = algorithmIdentifier;
    }

    public void setStartDate(ASN1UTCTime aSN1UTCTime) {
        this.f13018e = new Time(aSN1UTCTime);
    }

    public void setStartDate(Time time) {
        this.f13018e = time;
    }

    public void setSubject(X500Name x500Name) {
        this.f13020g = x500Name;
    }

    public void setSubject(X509Name x509Name) {
        this.f13020g = X500Name.getInstance(x509Name.toASN1Primitive());
    }

    public void setSubjectPublicKeyInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.f13021h = subjectPublicKeyInfo;
    }

    public void setSubjectUniqueID(DERBitString dERBitString) {
        this.subjectUniqueID = dERBitString;
    }
}
