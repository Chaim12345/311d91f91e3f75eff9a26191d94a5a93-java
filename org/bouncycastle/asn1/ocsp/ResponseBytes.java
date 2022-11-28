package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class ResponseBytes extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1ObjectIdentifier f12817a;

    /* renamed from: b  reason: collision with root package name */
    ASN1OctetString f12818b;

    public ResponseBytes(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1OctetString aSN1OctetString) {
        this.f12817a = aSN1ObjectIdentifier;
        this.f12818b = aSN1OctetString;
    }

    private ResponseBytes(ASN1Sequence aSN1Sequence) {
        this.f12817a = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
        this.f12818b = (ASN1OctetString) aSN1Sequence.getObjectAt(1);
    }

    public static ResponseBytes getInstance(Object obj) {
        if (obj instanceof ResponseBytes) {
            return (ResponseBytes) obj;
        }
        if (obj != null) {
            return new ResponseBytes(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static ResponseBytes getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1OctetString getResponse() {
        return this.f12818b;
    }

    public ASN1ObjectIdentifier getResponseType() {
        return this.f12817a;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.f12817a);
        aSN1EncodableVector.add(this.f12818b);
        return new DERSequence(aSN1EncodableVector);
    }
}
