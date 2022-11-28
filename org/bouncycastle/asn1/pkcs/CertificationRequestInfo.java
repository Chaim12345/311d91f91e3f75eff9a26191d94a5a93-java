package org.bouncycastle.asn1.pkcs;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Name;
/* loaded from: classes3.dex */
public class CertificationRequestInfo extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12832a;

    /* renamed from: b  reason: collision with root package name */
    X500Name f12833b;

    /* renamed from: c  reason: collision with root package name */
    SubjectPublicKeyInfo f12834c;

    /* renamed from: d  reason: collision with root package name */
    ASN1Set f12835d;

    private CertificationRequestInfo(ASN1Sequence aSN1Sequence) {
        this.f12832a = new ASN1Integer(0L);
        this.f12835d = null;
        this.f12832a = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        this.f12833b = X500Name.getInstance(aSN1Sequence.getObjectAt(1));
        this.f12834c = SubjectPublicKeyInfo.getInstance(aSN1Sequence.getObjectAt(2));
        if (aSN1Sequence.size() > 3) {
            this.f12835d = ASN1Set.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(3), false);
        }
        validateAttributes(this.f12835d);
        if (this.f12833b == null || this.f12832a == null || this.f12834c == null) {
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        }
    }

    public CertificationRequestInfo(X500Name x500Name, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1Set aSN1Set) {
        this.f12832a = new ASN1Integer(0L);
        this.f12835d = null;
        if (x500Name == null || subjectPublicKeyInfo == null) {
            throw new IllegalArgumentException("Not all mandatory fields set in CertificationRequestInfo generator.");
        }
        validateAttributes(aSN1Set);
        this.f12833b = x500Name;
        this.f12834c = subjectPublicKeyInfo;
        this.f12835d = aSN1Set;
    }

    public CertificationRequestInfo(X509Name x509Name, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1Set aSN1Set) {
        this(X500Name.getInstance(x509Name.toASN1Primitive()), subjectPublicKeyInfo, aSN1Set);
    }

    public static CertificationRequestInfo getInstance(Object obj) {
        if (obj instanceof CertificationRequestInfo) {
            return (CertificationRequestInfo) obj;
        }
        if (obj != null) {
            return new CertificationRequestInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private static void validateAttributes(ASN1Set aSN1Set) {
        if (aSN1Set == null) {
            return;
        }
        Enumeration objects = aSN1Set.getObjects();
        while (objects.hasMoreElements()) {
            Attribute attribute = Attribute.getInstance(objects.nextElement());
            if (attribute.getAttrType().equals((ASN1Primitive) PKCSObjectIdentifiers.pkcs_9_at_challengePassword) && attribute.getAttrValues().size() != 1) {
                throw new IllegalArgumentException("challengePassword attribute must have one value");
            }
        }
    }

    public ASN1Set getAttributes() {
        return this.f12835d;
    }

    public X500Name getSubject() {
        return this.f12833b;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.f12834c;
    }

    public ASN1Integer getVersion() {
        return this.f12832a;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        aSN1EncodableVector.add(this.f12832a);
        aSN1EncodableVector.add(this.f12833b);
        aSN1EncodableVector.add(this.f12834c);
        ASN1Set aSN1Set = this.f12835d;
        if (aSN1Set != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, (ASN1Encodable) aSN1Set));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
