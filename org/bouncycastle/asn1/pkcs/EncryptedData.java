package org.bouncycastle.asn1.pkcs;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes3.dex */
public class EncryptedData extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Sequence f12839a;

    public EncryptedData(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1Encodable aSN1Encodable) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(aSN1ObjectIdentifier);
        aSN1EncodableVector.add(algorithmIdentifier.toASN1Primitive());
        aSN1EncodableVector.add(new BERTaggedObject(false, 0, aSN1Encodable));
        this.f12839a = new BERSequence(aSN1EncodableVector);
    }

    private EncryptedData(ASN1Sequence aSN1Sequence) {
        if (!((ASN1Integer) aSN1Sequence.getObjectAt(0)).hasValue(0)) {
            throw new IllegalArgumentException("sequence not version 0");
        }
        this.f12839a = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static EncryptedData getInstance(Object obj) {
        if (obj instanceof EncryptedData) {
            return (EncryptedData) obj;
        }
        if (obj != null) {
            return new EncryptedData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1OctetString getContent() {
        if (this.f12839a.size() == 3) {
            return ASN1OctetString.getInstance(ASN1TaggedObject.getInstance(this.f12839a.getObjectAt(2)), false);
        }
        return null;
    }

    public ASN1ObjectIdentifier getContentType() {
        return ASN1ObjectIdentifier.getInstance(this.f12839a.getObjectAt(0));
    }

    public AlgorithmIdentifier getEncryptionAlgorithm() {
        return AlgorithmIdentifier.getInstance(this.f12839a.getObjectAt(1));
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(new ASN1Integer(0L));
        aSN1EncodableVector.add(this.f12839a);
        return new BERSequence(aSN1EncodableVector);
    }
}
