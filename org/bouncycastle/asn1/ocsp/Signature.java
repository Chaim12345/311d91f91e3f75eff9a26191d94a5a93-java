package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes3.dex */
public class Signature extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    AlgorithmIdentifier f12819a;

    /* renamed from: b  reason: collision with root package name */
    DERBitString f12820b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Sequence f12821c;

    private Signature(ASN1Sequence aSN1Sequence) {
        this.f12819a = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.f12820b = (DERBitString) aSN1Sequence.getObjectAt(1);
        if (aSN1Sequence.size() == 3) {
            this.f12821c = ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(2), true);
        }
    }

    public Signature(AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString) {
        this.f12819a = algorithmIdentifier;
        this.f12820b = dERBitString;
    }

    public Signature(AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString, ASN1Sequence aSN1Sequence) {
        this.f12819a = algorithmIdentifier;
        this.f12820b = dERBitString;
        this.f12821c = aSN1Sequence;
    }

    public static Signature getInstance(Object obj) {
        if (obj instanceof Signature) {
            return (Signature) obj;
        }
        if (obj != null) {
            return new Signature(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static Signature getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1Sequence getCerts() {
        return this.f12821c;
    }

    public DERBitString getSignature() {
        return this.f12820b;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.f12819a;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f12819a);
        aSN1EncodableVector.add(this.f12820b);
        ASN1Sequence aSN1Sequence = this.f12821c;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, (ASN1Encodable) aSN1Sequence));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
