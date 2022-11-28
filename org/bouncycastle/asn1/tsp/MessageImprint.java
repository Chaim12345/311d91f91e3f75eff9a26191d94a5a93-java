package org.bouncycastle.asn1.tsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class MessageImprint extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    AlgorithmIdentifier f12891a;

    /* renamed from: b  reason: collision with root package name */
    byte[] f12892b;

    private MessageImprint(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("sequence has wrong number of elements");
        }
        this.f12891a = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.f12892b = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets();
    }

    public MessageImprint(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.f12891a = algorithmIdentifier;
        this.f12892b = Arrays.clone(bArr);
    }

    public static MessageImprint getInstance(Object obj) {
        if (obj instanceof MessageImprint) {
            return (MessageImprint) obj;
        }
        if (obj != null) {
            return new MessageImprint(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.f12891a;
    }

    public byte[] getHashedMessage() {
        return Arrays.clone(this.f12892b);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.f12891a);
        aSN1EncodableVector.add(new DEROctetString(this.f12892b));
        return new DERSequence(aSN1EncodableVector);
    }
}
