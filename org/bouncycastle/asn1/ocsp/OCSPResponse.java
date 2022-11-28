package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes3.dex */
public class OCSPResponse extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    OCSPResponseStatus f12813a;

    /* renamed from: b  reason: collision with root package name */
    ResponseBytes f12814b;

    private OCSPResponse(ASN1Sequence aSN1Sequence) {
        this.f12813a = OCSPResponseStatus.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() == 2) {
            this.f12814b = ResponseBytes.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), true);
        }
    }

    public OCSPResponse(OCSPResponseStatus oCSPResponseStatus, ResponseBytes responseBytes) {
        this.f12813a = oCSPResponseStatus;
        this.f12814b = responseBytes;
    }

    public static OCSPResponse getInstance(Object obj) {
        if (obj instanceof OCSPResponse) {
            return (OCSPResponse) obj;
        }
        if (obj != null) {
            return new OCSPResponse(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static OCSPResponse getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ResponseBytes getResponseBytes() {
        return this.f12814b;
    }

    public OCSPResponseStatus getResponseStatus() {
        return this.f12813a;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.f12813a);
        ResponseBytes responseBytes = this.f12814b;
        if (responseBytes != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, (ASN1Encodable) responseBytes));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
