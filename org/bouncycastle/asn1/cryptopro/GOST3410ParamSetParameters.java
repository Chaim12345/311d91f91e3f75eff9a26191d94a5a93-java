package org.bouncycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class GOST3410ParamSetParameters extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    int f12771a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12772b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Integer f12773c;

    /* renamed from: d  reason: collision with root package name */
    ASN1Integer f12774d;

    public GOST3410ParamSetParameters(int i2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f12771a = i2;
        this.f12772b = new ASN1Integer(bigInteger);
        this.f12773c = new ASN1Integer(bigInteger2);
        this.f12774d = new ASN1Integer(bigInteger3);
    }

    public GOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f12771a = ((ASN1Integer) objects.nextElement()).intValueExact();
        this.f12772b = (ASN1Integer) objects.nextElement();
        this.f12773c = (ASN1Integer) objects.nextElement();
        this.f12774d = (ASN1Integer) objects.nextElement();
    }

    public static GOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof GOST3410ParamSetParameters)) {
            return (GOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new GOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid GOST3410Parameter: " + obj.getClass().getName());
    }

    public static GOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getA() {
        return this.f12774d.getPositiveValue();
    }

    public int getKeySize() {
        return this.f12771a;
    }

    public int getLKeySize() {
        return this.f12771a;
    }

    public BigInteger getP() {
        return this.f12772b.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f12773c.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        aSN1EncodableVector.add(new ASN1Integer(this.f12771a));
        aSN1EncodableVector.add(this.f12772b);
        aSN1EncodableVector.add(this.f12773c);
        aSN1EncodableVector.add(this.f12774d);
        return new DERSequence(aSN1EncodableVector);
    }
}
