package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes3.dex */
public class Holder extends ASN1Object {
    public static final int V1_CERTIFICATE_HOLDER = 0;
    public static final int V2_CERTIFICATE_HOLDER = 1;

    /* renamed from: a  reason: collision with root package name */
    IssuerSerial f12956a;

    /* renamed from: b  reason: collision with root package name */
    GeneralNames f12957b;

    /* renamed from: c  reason: collision with root package name */
    ObjectDigestInfo f12958c;
    private int version;

    private Holder(ASN1Sequence aSN1Sequence) {
        this.version = 1;
        if (aSN1Sequence.size() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        for (int i2 = 0; i2 != aSN1Sequence.size(); i2++) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i2));
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.f12956a = IssuerSerial.getInstance(aSN1TaggedObject, false);
            } else if (tagNo == 1) {
                this.f12957b = GeneralNames.getInstance(aSN1TaggedObject, false);
            } else if (tagNo != 2) {
                throw new IllegalArgumentException("unknown tag in Holder");
            } else {
                this.f12958c = ObjectDigestInfo.getInstance(aSN1TaggedObject, false);
            }
        }
        this.version = 1;
    }

    private Holder(ASN1TaggedObject aSN1TaggedObject) {
        this.version = 1;
        int tagNo = aSN1TaggedObject.getTagNo();
        if (tagNo == 0) {
            this.f12956a = IssuerSerial.getInstance(aSN1TaggedObject, true);
        } else if (tagNo != 1) {
            throw new IllegalArgumentException("unknown tag in Holder");
        } else {
            this.f12957b = GeneralNames.getInstance(aSN1TaggedObject, true);
        }
        this.version = 0;
    }

    public Holder(GeneralNames generalNames) {
        this(generalNames, 1);
    }

    public Holder(GeneralNames generalNames, int i2) {
        this.version = 1;
        this.f12957b = generalNames;
        this.version = i2;
    }

    public Holder(IssuerSerial issuerSerial) {
        this(issuerSerial, 1);
    }

    public Holder(IssuerSerial issuerSerial, int i2) {
        this.version = 1;
        this.f12956a = issuerSerial;
        this.version = i2;
    }

    public Holder(ObjectDigestInfo objectDigestInfo) {
        this.version = 1;
        this.f12958c = objectDigestInfo;
    }

    public static Holder getInstance(Object obj) {
        if (obj instanceof Holder) {
            return (Holder) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new Holder(ASN1TaggedObject.getInstance(obj));
        }
        if (obj != null) {
            return new Holder(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public IssuerSerial getBaseCertificateID() {
        return this.f12956a;
    }

    public GeneralNames getEntityName() {
        return this.f12957b;
    }

    public ObjectDigestInfo getObjectDigestInfo() {
        return this.f12958c;
    }

    public int getVersion() {
        return this.version;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        if (this.version != 1) {
            GeneralNames generalNames = this.f12957b;
            return generalNames != null ? new DERTaggedObject(true, 1, (ASN1Encodable) generalNames) : new DERTaggedObject(true, 0, (ASN1Encodable) this.f12956a);
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        IssuerSerial issuerSerial = this.f12956a;
        if (issuerSerial != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, (ASN1Encodable) issuerSerial));
        }
        GeneralNames generalNames2 = this.f12957b;
        if (generalNames2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) generalNames2));
        }
        ObjectDigestInfo objectDigestInfo = this.f12958c;
        if (objectDigestInfo != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, (ASN1Encodable) objectDigestInfo));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
