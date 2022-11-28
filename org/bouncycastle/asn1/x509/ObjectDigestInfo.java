package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class ObjectDigestInfo extends ASN1Object {
    public static final int otherObjectDigest = 2;
    public static final int publicKey = 0;
    public static final int publicKeyCert = 1;

    /* renamed from: a  reason: collision with root package name */
    ASN1Enumerated f12965a;

    /* renamed from: b  reason: collision with root package name */
    ASN1ObjectIdentifier f12966b;

    /* renamed from: c  reason: collision with root package name */
    AlgorithmIdentifier f12967c;

    /* renamed from: d  reason: collision with root package name */
    ASN1BitString f12968d;

    public ObjectDigestInfo(int i2, ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.f12965a = new ASN1Enumerated(i2);
        if (i2 == 2) {
            this.f12966b = aSN1ObjectIdentifier;
        }
        this.f12967c = algorithmIdentifier;
        this.f12968d = new DERBitString(bArr);
    }

    private ObjectDigestInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() > 4 || aSN1Sequence.size() < 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        int i2 = 0;
        this.f12965a = ASN1Enumerated.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() == 4) {
            this.f12966b = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            i2 = 1;
        }
        this.f12967c = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i2 + 1));
        this.f12968d = DERBitString.getInstance((Object) aSN1Sequence.getObjectAt(i2 + 2));
    }

    public static ObjectDigestInfo getInstance(Object obj) {
        if (obj instanceof ObjectDigestInfo) {
            return (ObjectDigestInfo) obj;
        }
        if (obj != null) {
            return new ObjectDigestInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static ObjectDigestInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.f12967c;
    }

    public ASN1Enumerated getDigestedObjectType() {
        return this.f12965a;
    }

    public ASN1BitString getObjectDigest() {
        return this.f12968d;
    }

    public ASN1ObjectIdentifier getOtherObjectTypeID() {
        return this.f12966b;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        aSN1EncodableVector.add(this.f12965a);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.f12966b;
        if (aSN1ObjectIdentifier != null) {
            aSN1EncodableVector.add(aSN1ObjectIdentifier);
        }
        aSN1EncodableVector.add(this.f12967c);
        aSN1EncodableVector.add(this.f12968d);
        return new DERSequence(aSN1EncodableVector);
    }
}
