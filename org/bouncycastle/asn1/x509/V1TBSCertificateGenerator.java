package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes3.dex */
public class V1TBSCertificateGenerator {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f13004a;

    /* renamed from: b  reason: collision with root package name */
    AlgorithmIdentifier f13005b;

    /* renamed from: c  reason: collision with root package name */
    X500Name f13006c;

    /* renamed from: d  reason: collision with root package name */
    Time f13007d;

    /* renamed from: e  reason: collision with root package name */
    Time f13008e;

    /* renamed from: f  reason: collision with root package name */
    X500Name f13009f;

    /* renamed from: g  reason: collision with root package name */
    SubjectPublicKeyInfo f13010g;

    public V1TBSCertificateGenerator() {
        new DERTaggedObject(true, 0, (ASN1Encodable) new ASN1Integer(0L));
    }

    public TBSCertificate generateTBSCertificate() {
        if (this.f13004a == null || this.f13005b == null || this.f13006c == null || this.f13007d == null || this.f13008e == null || this.f13009f == null || this.f13010g == null) {
            throw new IllegalStateException("not all mandatory fields set in V1 TBScertificate generator");
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(6);
        aSN1EncodableVector.add(this.f13004a);
        aSN1EncodableVector.add(this.f13005b);
        aSN1EncodableVector.add(this.f13006c);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(2);
        aSN1EncodableVector2.add(this.f13007d);
        aSN1EncodableVector2.add(this.f13008e);
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector.add(this.f13009f);
        aSN1EncodableVector.add(this.f13010g);
        return TBSCertificate.getInstance(new DERSequence(aSN1EncodableVector));
    }

    public void setEndDate(ASN1UTCTime aSN1UTCTime) {
        this.f13008e = new Time(aSN1UTCTime);
    }

    public void setEndDate(Time time) {
        this.f13008e = time;
    }

    public void setIssuer(X500Name x500Name) {
        this.f13006c = x500Name;
    }

    public void setIssuer(X509Name x509Name) {
        this.f13006c = X500Name.getInstance(x509Name.toASN1Primitive());
    }

    public void setSerialNumber(ASN1Integer aSN1Integer) {
        this.f13004a = aSN1Integer;
    }

    public void setSignature(AlgorithmIdentifier algorithmIdentifier) {
        this.f13005b = algorithmIdentifier;
    }

    public void setStartDate(ASN1UTCTime aSN1UTCTime) {
        this.f13007d = new Time(aSN1UTCTime);
    }

    public void setStartDate(Time time) {
        this.f13007d = time;
    }

    public void setSubject(X500Name x500Name) {
        this.f13009f = x500Name;
    }

    public void setSubject(X509Name x509Name) {
        this.f13009f = X500Name.getInstance(x509Name.toASN1Primitive());
    }

    public void setSubjectPublicKeyInfo(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.f13010g = subjectPublicKeyInfo;
    }
}
