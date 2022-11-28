package org.bouncycastle.asn1.esf;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class SigPolicyQualifiers extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Sequence f12784a;

    private SigPolicyQualifiers(ASN1Sequence aSN1Sequence) {
        this.f12784a = aSN1Sequence;
    }

    public SigPolicyQualifiers(SigPolicyQualifierInfo[] sigPolicyQualifierInfoArr) {
        this.f12784a = new DERSequence(sigPolicyQualifierInfoArr);
    }

    public static SigPolicyQualifiers getInstance(Object obj) {
        if (obj instanceof SigPolicyQualifiers) {
            return (SigPolicyQualifiers) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new SigPolicyQualifiers(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public SigPolicyQualifierInfo getInfoAt(int i2) {
        return SigPolicyQualifierInfo.getInstance(this.f12784a.getObjectAt(i2));
    }

    public int size() {
        return this.f12784a.size();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f12784a;
    }
}
