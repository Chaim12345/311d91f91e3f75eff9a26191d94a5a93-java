package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes3.dex */
public class CertID extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    AlgorithmIdentifier f12807a;

    /* renamed from: b  reason: collision with root package name */
    ASN1OctetString f12808b;

    /* renamed from: c  reason: collision with root package name */
    ASN1OctetString f12809c;

    /* renamed from: d  reason: collision with root package name */
    ASN1Integer f12810d;

    private CertID(ASN1Sequence aSN1Sequence) {
        this.f12807a = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.f12808b = (ASN1OctetString) aSN1Sequence.getObjectAt(1);
        this.f12809c = (ASN1OctetString) aSN1Sequence.getObjectAt(2);
        this.f12810d = (ASN1Integer) aSN1Sequence.getObjectAt(3);
    }

    public CertID(AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString, ASN1OctetString aSN1OctetString2, ASN1Integer aSN1Integer) {
        this.f12807a = algorithmIdentifier;
        this.f12808b = aSN1OctetString;
        this.f12809c = aSN1OctetString2;
        this.f12810d = aSN1Integer;
    }

    public static CertID getInstance(Object obj) {
        if (obj instanceof CertID) {
            return (CertID) obj;
        }
        if (obj != null) {
            return new CertID(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static CertID getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.f12807a;
    }

    public ASN1OctetString getIssuerKeyHash() {
        return this.f12809c;
    }

    public ASN1OctetString getIssuerNameHash() {
        return this.f12808b;
    }

    public ASN1Integer getSerialNumber() {
        return this.f12810d;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        aSN1EncodableVector.add(this.f12807a);
        aSN1EncodableVector.add(this.f12808b);
        aSN1EncodableVector.add(this.f12809c);
        aSN1EncodableVector.add(this.f12810d);
        return new DERSequence(aSN1EncodableVector);
    }
}
