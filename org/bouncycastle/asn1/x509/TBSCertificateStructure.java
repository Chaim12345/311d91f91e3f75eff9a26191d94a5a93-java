package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes3.dex */
public class TBSCertificateStructure extends ASN1Object implements X509ObjectIdentifiers, PKCSObjectIdentifiers {

    /* renamed from: a  reason: collision with root package name */
    ASN1Sequence f12991a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12992b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Integer f12993c;

    /* renamed from: d  reason: collision with root package name */
    AlgorithmIdentifier f12994d;

    /* renamed from: e  reason: collision with root package name */
    X500Name f12995e;

    /* renamed from: f  reason: collision with root package name */
    Time f12996f;

    /* renamed from: g  reason: collision with root package name */
    Time f12997g;

    /* renamed from: h  reason: collision with root package name */
    X500Name f12998h;

    /* renamed from: i  reason: collision with root package name */
    SubjectPublicKeyInfo f12999i;

    /* renamed from: j  reason: collision with root package name */
    ASN1BitString f13000j;

    /* renamed from: k  reason: collision with root package name */
    ASN1BitString f13001k;

    /* renamed from: l  reason: collision with root package name */
    X509Extensions f13002l;

    public TBSCertificateStructure(ASN1Sequence aSN1Sequence) {
        int i2;
        this.f12991a = aSN1Sequence;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            this.f12992b = ASN1Integer.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(0), true);
            i2 = 0;
        } else {
            this.f12992b = new ASN1Integer(0L);
            i2 = -1;
        }
        this.f12993c = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i2 + 1));
        this.f12994d = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i2 + 2));
        this.f12995e = X500Name.getInstance(aSN1Sequence.getObjectAt(i2 + 3));
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(i2 + 4);
        this.f12996f = Time.getInstance(aSN1Sequence2.getObjectAt(0));
        this.f12997g = Time.getInstance(aSN1Sequence2.getObjectAt(1));
        this.f12998h = X500Name.getInstance(aSN1Sequence.getObjectAt(i2 + 5));
        int i3 = i2 + 6;
        this.f12999i = SubjectPublicKeyInfo.getInstance(aSN1Sequence.getObjectAt(i3));
        for (int size = (aSN1Sequence.size() - i3) - 1; size > 0; size--) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i3 + size));
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 1) {
                this.f13000j = ASN1BitString.getInstance(aSN1TaggedObject, false);
            } else if (tagNo == 2) {
                this.f13001k = ASN1BitString.getInstance(aSN1TaggedObject, false);
            } else if (tagNo == 3) {
                this.f13002l = X509Extensions.getInstance(aSN1TaggedObject);
            }
        }
    }

    public static TBSCertificateStructure getInstance(Object obj) {
        if (obj instanceof TBSCertificateStructure) {
            return (TBSCertificateStructure) obj;
        }
        if (obj != null) {
            return new TBSCertificateStructure(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TBSCertificateStructure getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Time getEndDate() {
        return this.f12997g;
    }

    public X509Extensions getExtensions() {
        return this.f13002l;
    }

    public X500Name getIssuer() {
        return this.f12995e;
    }

    public ASN1BitString getIssuerUniqueId() {
        return this.f13000j;
    }

    public ASN1Integer getSerialNumber() {
        return this.f12993c;
    }

    public AlgorithmIdentifier getSignature() {
        return this.f12994d;
    }

    public Time getStartDate() {
        return this.f12996f;
    }

    public X500Name getSubject() {
        return this.f12998h;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.f12999i;
    }

    public ASN1BitString getSubjectUniqueId() {
        return this.f13001k;
    }

    public int getVersion() {
        return this.f12992b.intValueExact() + 1;
    }

    public ASN1Integer getVersionNumber() {
        return this.f12992b;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f12991a;
    }
}
