package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class AttributeCertificate extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    AttributeCertificateInfo f12927a;

    /* renamed from: b  reason: collision with root package name */
    AlgorithmIdentifier f12928b;

    /* renamed from: c  reason: collision with root package name */
    ASN1BitString f12929c;

    private AttributeCertificate(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 3) {
            this.f12927a = AttributeCertificateInfo.getInstance(aSN1Sequence.getObjectAt(0));
            this.f12928b = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            this.f12929c = DERBitString.getInstance((Object) aSN1Sequence.getObjectAt(2));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public AttributeCertificate(AttributeCertificateInfo attributeCertificateInfo, AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString) {
        this.f12927a = attributeCertificateInfo;
        this.f12928b = algorithmIdentifier;
        this.f12929c = dERBitString;
    }

    public static AttributeCertificate getInstance(Object obj) {
        if (obj instanceof AttributeCertificate) {
            return (AttributeCertificate) obj;
        }
        if (obj != null) {
            return new AttributeCertificate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AttributeCertificateInfo getAcinfo() {
        return this.f12927a;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.f12928b;
    }

    public ASN1BitString getSignatureValue() {
        return this.f12929c;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f12927a);
        aSN1EncodableVector.add(this.f12928b);
        aSN1EncodableVector.add(this.f12929c);
        return new DERSequence(aSN1EncodableVector);
    }
}
