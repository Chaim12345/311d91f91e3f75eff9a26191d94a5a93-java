package org.bouncycastle.oer.its;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes4.dex */
public class PublicVerificationKey extends ASN1Object implements ASN1Choice {
    public static final int ecdsaBrainpoolP256r1 = 1;
    public static final int ecdsaBrainpoolP384r1 = 3;
    public static final int ecdsaNistP256 = 0;
    public static final int extension = 2;

    /* renamed from: a  reason: collision with root package name */
    final int f14390a;

    /* renamed from: b  reason: collision with root package name */
    final ASN1Encodable f14391b;

    /* loaded from: classes4.dex */
    public static class Builder {
        private int choice;
        private ASN1Encodable curvePoint;

        public PublicVerificationKey createPublicVerificationKey() {
            return new PublicVerificationKey(this.choice, this.curvePoint);
        }

        public Builder ecdsaBrainpoolP256r1(EccP256CurvePoint eccP256CurvePoint) {
            this.curvePoint = eccP256CurvePoint;
            return this;
        }

        public Builder ecdsaBrainpoolP384r1(EccP384CurvePoint eccP384CurvePoint) {
            this.curvePoint = eccP384CurvePoint;
            return this;
        }

        public Builder ecdsaNistP256(EccP256CurvePoint eccP256CurvePoint) {
            this.curvePoint = eccP256CurvePoint;
            return this;
        }

        public Builder extension(byte[] bArr) {
            this.curvePoint = new DEROctetString(bArr);
            return this;
        }

        public Builder setChoice(int i2) {
            this.choice = i2;
            return this;
        }

        public Builder setCurvePoint(EccCurvePoint eccCurvePoint) {
            this.curvePoint = eccCurvePoint;
            return this;
        }
    }

    public PublicVerificationKey(int i2, ASN1Encodable aSN1Encodable) {
        this.f14390a = i2;
        this.f14391b = aSN1Encodable;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static PublicVerificationKey getInstance(Object obj) {
        ASN1Encodable eccP256CurvePoint;
        if (obj instanceof PublicVerificationKey) {
            return (PublicVerificationKey) obj;
        }
        ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(obj);
        int tagNo = aSN1TaggedObject.getTagNo();
        if (tagNo == 0 || tagNo == 1) {
            eccP256CurvePoint = EccP256CurvePoint.getInstance(aSN1TaggedObject.getObject());
        } else if (tagNo == 2) {
            eccP256CurvePoint = ASN1OctetString.getInstance(aSN1TaggedObject.getObject());
        } else if (tagNo != 3) {
            throw new IllegalArgumentException("unknown tag value " + aSN1TaggedObject.getTagNo());
        } else {
            eccP256CurvePoint = EccP384CurvePoint.getInstance(aSN1TaggedObject.getObject());
        }
        return new PublicVerificationKey(aSN1TaggedObject.getTagNo(), eccP256CurvePoint);
    }

    public int getChoice() {
        return this.f14390a;
    }

    public ASN1Encodable getCurvePoint() {
        return this.f14391b;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(this.f14390a, this.f14391b);
    }
}
