package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class AccessDescription extends ASN1Object {
    public static final ASN1ObjectIdentifier id_ad_caIssuers = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.2");
    public static final ASN1ObjectIdentifier id_ad_ocsp = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.48.1");

    /* renamed from: a  reason: collision with root package name */
    ASN1ObjectIdentifier f12921a;

    /* renamed from: b  reason: collision with root package name */
    GeneralName f12922b;

    public AccessDescription(ASN1ObjectIdentifier aSN1ObjectIdentifier, GeneralName generalName) {
        this.f12921a = null;
        this.f12922b = null;
        this.f12921a = aSN1ObjectIdentifier;
        this.f12922b = generalName;
    }

    private AccessDescription(ASN1Sequence aSN1Sequence) {
        this.f12921a = null;
        this.f12922b = null;
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("wrong number of elements in sequence");
        }
        this.f12921a = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.f12922b = GeneralName.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static AccessDescription getInstance(Object obj) {
        if (obj instanceof AccessDescription) {
            return (AccessDescription) obj;
        }
        if (obj != null) {
            return new AccessDescription(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GeneralName getAccessLocation() {
        return this.f12922b;
    }

    public ASN1ObjectIdentifier getAccessMethod() {
        return this.f12921a;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.f12921a);
        aSN1EncodableVector.add(this.f12922b);
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "AccessDescription: Oid(" + this.f12921a.getId() + ")";
    }
}
