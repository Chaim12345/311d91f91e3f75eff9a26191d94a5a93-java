package org.bouncycastle.asn1.ua;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class DSTU4145BinaryField extends ASN1Object {

    /* renamed from: j  reason: collision with root package name */
    private int f12901j;

    /* renamed from: k  reason: collision with root package name */
    private int f12902k;

    /* renamed from: l  reason: collision with root package name */
    private int f12903l;

    /* renamed from: m  reason: collision with root package name */
    private int f12904m;

    public DSTU4145BinaryField(int i2, int i3) {
        this(i2, i3, 0, 0);
    }

    public DSTU4145BinaryField(int i2, int i3, int i4, int i5) {
        this.f12904m = i2;
        this.f12902k = i3;
        this.f12901j = i4;
        this.f12903l = i5;
    }

    private DSTU4145BinaryField(ASN1Sequence aSN1Sequence) {
        this.f12904m = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).intPositiveValueExact();
        if (aSN1Sequence.getObjectAt(1) instanceof ASN1Integer) {
            this.f12902k = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).intPositiveValueExact();
        } else if (!(aSN1Sequence.getObjectAt(1) instanceof ASN1Sequence)) {
            throw new IllegalArgumentException("object parse error");
        } else {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
            this.f12902k = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(0)).intPositiveValueExact();
            this.f12901j = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(1)).intPositiveValueExact();
            this.f12903l = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(2)).intPositiveValueExact();
        }
    }

    public static DSTU4145BinaryField getInstance(Object obj) {
        if (obj instanceof DSTU4145BinaryField) {
            return (DSTU4145BinaryField) obj;
        }
        if (obj != null) {
            return new DSTU4145BinaryField(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int getK1() {
        return this.f12902k;
    }

    public int getK2() {
        return this.f12901j;
    }

    public int getK3() {
        return this.f12903l;
    }

    public int getM() {
        return this.f12904m;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(new ASN1Integer(this.f12904m));
        if (this.f12901j == 0) {
            aSN1EncodableVector.add(new ASN1Integer(this.f12902k));
        } else {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(3);
            aSN1EncodableVector2.add(new ASN1Integer(this.f12902k));
            aSN1EncodableVector2.add(new ASN1Integer(this.f12901j));
            aSN1EncodableVector2.add(new ASN1Integer(this.f12903l));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
