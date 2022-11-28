package org.bouncycastle.asn1.cmp;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class PKIStatusInfo extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12755a;

    /* renamed from: b  reason: collision with root package name */
    PKIFreeText f12756b;

    /* renamed from: c  reason: collision with root package name */
    ASN1BitString f12757c;

    private PKIStatusInfo(ASN1Sequence aSN1Sequence) {
        ASN1BitString aSN1BitString;
        this.f12755a = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.f12756b = null;
        this.f12757c = null;
        if (aSN1Sequence.size() > 2) {
            this.f12756b = PKIFreeText.getInstance(aSN1Sequence.getObjectAt(1));
            aSN1BitString = DERBitString.getInstance((Object) aSN1Sequence.getObjectAt(2));
        } else if (aSN1Sequence.size() <= 1) {
            return;
        } else {
            ASN1Encodable objectAt = aSN1Sequence.getObjectAt(1);
            if (!(objectAt instanceof ASN1BitString)) {
                this.f12756b = PKIFreeText.getInstance(objectAt);
                return;
            }
            aSN1BitString = ASN1BitString.getInstance(objectAt);
        }
        this.f12757c = aSN1BitString;
    }

    public PKIStatusInfo(PKIStatus pKIStatus) {
        this.f12755a = ASN1Integer.getInstance(pKIStatus.toASN1Primitive());
    }

    public PKIStatusInfo(PKIStatus pKIStatus, PKIFreeText pKIFreeText) {
        this.f12755a = ASN1Integer.getInstance(pKIStatus.toASN1Primitive());
        this.f12756b = pKIFreeText;
    }

    public PKIStatusInfo(PKIStatus pKIStatus, PKIFreeText pKIFreeText, PKIFailureInfo pKIFailureInfo) {
        this.f12755a = ASN1Integer.getInstance(pKIStatus.toASN1Primitive());
        this.f12756b = pKIFreeText;
        this.f12757c = pKIFailureInfo;
    }

    public static PKIStatusInfo getInstance(Object obj) {
        if (obj instanceof PKIStatusInfo) {
            return (PKIStatusInfo) obj;
        }
        if (obj != null) {
            return new PKIStatusInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static PKIStatusInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1BitString getFailInfo() {
        return this.f12757c;
    }

    public BigInteger getStatus() {
        return this.f12755a.getValue();
    }

    public PKIFreeText getStatusString() {
        return this.f12756b;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f12755a);
        PKIFreeText pKIFreeText = this.f12756b;
        if (pKIFreeText != null) {
            aSN1EncodableVector.add(pKIFreeText);
        }
        ASN1BitString aSN1BitString = this.f12757c;
        if (aSN1BitString != null) {
            aSN1EncodableVector.add(aSN1BitString);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
