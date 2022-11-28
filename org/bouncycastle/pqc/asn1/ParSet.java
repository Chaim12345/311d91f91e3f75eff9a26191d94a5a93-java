package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class ParSet extends ASN1Object {

    /* renamed from: h  reason: collision with root package name */
    private int[] f14493h;

    /* renamed from: k  reason: collision with root package name */
    private int[] f14494k;

    /* renamed from: t  reason: collision with root package name */
    private int f14495t;
    private int[] w;

    public ParSet(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        this.f14495t = i2;
        this.f14493h = iArr;
        this.w = iArr2;
        this.f14494k = iArr3;
    }

    private ParSet(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 4) {
            throw new IllegalArgumentException("sie of seqOfParams = " + aSN1Sequence.size());
        }
        this.f14495t = checkBigIntegerInIntRangeAndPositive(aSN1Sequence.getObjectAt(0));
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(2);
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        if (aSN1Sequence2.size() != this.f14495t || aSN1Sequence3.size() != this.f14495t || aSN1Sequence4.size() != this.f14495t) {
            throw new IllegalArgumentException("invalid size of sequences");
        }
        this.f14493h = new int[aSN1Sequence2.size()];
        this.w = new int[aSN1Sequence3.size()];
        this.f14494k = new int[aSN1Sequence4.size()];
        for (int i2 = 0; i2 < this.f14495t; i2++) {
            this.f14493h[i2] = checkBigIntegerInIntRangeAndPositive(aSN1Sequence2.getObjectAt(i2));
            this.w[i2] = checkBigIntegerInIntRangeAndPositive(aSN1Sequence3.getObjectAt(i2));
            this.f14494k[i2] = checkBigIntegerInIntRangeAndPositive(aSN1Sequence4.getObjectAt(i2));
        }
    }

    private static int checkBigIntegerInIntRangeAndPositive(ASN1Encodable aSN1Encodable) {
        int intValueExact = ((ASN1Integer) aSN1Encodable).intValueExact();
        if (intValueExact > 0) {
            return intValueExact;
        }
        throw new IllegalArgumentException("BigInteger not in Range: " + intValueExact);
    }

    public static ParSet getInstance(Object obj) {
        if (obj instanceof ParSet) {
            return (ParSet) obj;
        }
        if (obj != null) {
            return new ParSet(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int[] getH() {
        return Arrays.clone(this.f14493h);
    }

    public int[] getK() {
        return Arrays.clone(this.f14494k);
    }

    public int getT() {
        return this.f14495t;
    }

    public int[] getW() {
        return Arrays.clone(this.w);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        for (int i2 = 0; i2 < this.f14493h.length; i2++) {
            aSN1EncodableVector.add(new ASN1Integer(this.f14493h[i2]));
            aSN1EncodableVector2.add(new ASN1Integer(this.w[i2]));
            aSN1EncodableVector3.add(new ASN1Integer(this.f14494k[i2]));
        }
        ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
        aSN1EncodableVector4.add(new ASN1Integer(this.f14495t));
        aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector));
        aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector3));
        return new DERSequence(aSN1EncodableVector4);
    }
}
