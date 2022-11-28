package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
/* loaded from: classes4.dex */
public class McEliecePublicKey extends ASN1Object {

    /* renamed from: g  reason: collision with root package name */
    private final GF2Matrix f14490g;

    /* renamed from: n  reason: collision with root package name */
    private final int f14491n;

    /* renamed from: t  reason: collision with root package name */
    private final int f14492t;

    public McEliecePublicKey(int i2, int i3, GF2Matrix gF2Matrix) {
        this.f14491n = i2;
        this.f14492t = i3;
        this.f14490g = new GF2Matrix(gF2Matrix);
    }

    private McEliecePublicKey(ASN1Sequence aSN1Sequence) {
        this.f14491n = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).intValueExact();
        this.f14492t = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).intValueExact();
        this.f14490g = new GF2Matrix(((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets());
    }

    public static McEliecePublicKey getInstance(Object obj) {
        if (obj instanceof McEliecePublicKey) {
            return (McEliecePublicKey) obj;
        }
        if (obj != null) {
            return new McEliecePublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GF2Matrix getG() {
        return new GF2Matrix(this.f14490g);
    }

    public int getN() {
        return this.f14491n;
    }

    public int getT() {
        return this.f14492t;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.f14491n));
        aSN1EncodableVector.add(new ASN1Integer(this.f14492t));
        aSN1EncodableVector.add(new DEROctetString(this.f14490g.getEncoded()));
        return new DERSequence(aSN1EncodableVector);
    }
}
