package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.util.Properties;
/* loaded from: classes3.dex */
public class TBSCertificate extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Sequence f12979a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12980b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Integer f12981c;

    /* renamed from: d  reason: collision with root package name */
    AlgorithmIdentifier f12982d;

    /* renamed from: e  reason: collision with root package name */
    X500Name f12983e;

    /* renamed from: f  reason: collision with root package name */
    Time f12984f;

    /* renamed from: g  reason: collision with root package name */
    Time f12985g;

    /* renamed from: h  reason: collision with root package name */
    X500Name f12986h;

    /* renamed from: i  reason: collision with root package name */
    SubjectPublicKeyInfo f12987i;

    /* renamed from: j  reason: collision with root package name */
    ASN1BitString f12988j;

    /* renamed from: k  reason: collision with root package name */
    ASN1BitString f12989k;

    /* renamed from: l  reason: collision with root package name */
    Extensions f12990l;

    private TBSCertificate(ASN1Sequence aSN1Sequence) {
        int i2;
        boolean z;
        boolean z2;
        this.f12979a = aSN1Sequence;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            this.f12980b = ASN1Integer.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(0), true);
            i2 = 0;
        } else {
            this.f12980b = new ASN1Integer(0L);
            i2 = -1;
        }
        if (this.f12980b.hasValue(0)) {
            z2 = false;
            z = true;
        } else if (this.f12980b.hasValue(1)) {
            z = false;
            z2 = true;
        } else if (!this.f12980b.hasValue(2)) {
            throw new IllegalArgumentException("version number not recognised");
        } else {
            z = false;
            z2 = false;
        }
        this.f12981c = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i2 + 1));
        this.f12982d = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i2 + 2));
        this.f12983e = X500Name.getInstance(aSN1Sequence.getObjectAt(i2 + 3));
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(i2 + 4);
        this.f12984f = Time.getInstance(aSN1Sequence2.getObjectAt(0));
        this.f12985g = Time.getInstance(aSN1Sequence2.getObjectAt(1));
        this.f12986h = X500Name.getInstance(aSN1Sequence.getObjectAt(i2 + 5));
        int i3 = i2 + 6;
        this.f12987i = SubjectPublicKeyInfo.getInstance(aSN1Sequence.getObjectAt(i3));
        int size = (aSN1Sequence.size() - i3) - 1;
        if (size != 0 && z) {
            throw new IllegalArgumentException("version 1 certificate contains extra data");
        }
        while (size > 0) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(i3 + size);
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 1) {
                this.f12988j = DERBitString.getInstance(aSN1TaggedObject, false);
            } else if (tagNo == 2) {
                this.f12989k = DERBitString.getInstance(aSN1TaggedObject, false);
            } else if (tagNo != 3) {
                throw new IllegalArgumentException("Unknown tag encountered in structure: " + aSN1TaggedObject.getTagNo());
            } else if (z2) {
                throw new IllegalArgumentException("version 2 certificate cannot contain extensions");
            } else {
                this.f12990l = Extensions.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, true));
            }
            size--;
        }
    }

    public static TBSCertificate getInstance(Object obj) {
        if (obj instanceof TBSCertificate) {
            return (TBSCertificate) obj;
        }
        if (obj != null) {
            return new TBSCertificate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TBSCertificate getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Time getEndDate() {
        return this.f12985g;
    }

    public Extensions getExtensions() {
        return this.f12990l;
    }

    public X500Name getIssuer() {
        return this.f12983e;
    }

    public ASN1BitString getIssuerUniqueId() {
        return this.f12988j;
    }

    public ASN1Integer getSerialNumber() {
        return this.f12981c;
    }

    public AlgorithmIdentifier getSignature() {
        return this.f12982d;
    }

    public Time getStartDate() {
        return this.f12984f;
    }

    public X500Name getSubject() {
        return this.f12986h;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.f12987i;
    }

    public ASN1BitString getSubjectUniqueId() {
        return this.f12989k;
    }

    public ASN1Integer getVersion() {
        return this.f12980b;
    }

    public int getVersionNumber() {
        return this.f12980b.intValueExact() + 1;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        if (Properties.getPropertyValue("org.bouncycastle.x509.allow_non-der_tbscert") != null && !Properties.isOverrideSet("org.bouncycastle.x509.allow_non-der_tbscert")) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            if (!this.f12980b.hasValue(0)) {
                aSN1EncodableVector.add(new DERTaggedObject(true, 0, (ASN1Encodable) this.f12980b));
            }
            aSN1EncodableVector.add(this.f12981c);
            aSN1EncodableVector.add(this.f12982d);
            aSN1EncodableVector.add(this.f12983e);
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(2);
            aSN1EncodableVector2.add(this.f12984f);
            aSN1EncodableVector2.add(this.f12985g);
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
            ASN1Encodable aSN1Encodable = this.f12986h;
            if (aSN1Encodable == null) {
                aSN1Encodable = new DERSequence();
            }
            aSN1EncodableVector.add(aSN1Encodable);
            aSN1EncodableVector.add(this.f12987i);
            ASN1BitString aSN1BitString = this.f12988j;
            if (aSN1BitString != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) aSN1BitString));
            }
            ASN1BitString aSN1BitString2 = this.f12989k;
            if (aSN1BitString2 != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, 2, (ASN1Encodable) aSN1BitString2));
            }
            Extensions extensions = this.f12990l;
            if (extensions != null) {
                aSN1EncodableVector.add(new DERTaggedObject(true, 3, (ASN1Encodable) extensions));
            }
            return new DERSequence(aSN1EncodableVector);
        }
        return this.f12979a;
    }
}
