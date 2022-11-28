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
public class ECGOST3410ParamSetParameters extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12763a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12764b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Integer f12765c;

    /* renamed from: d  reason: collision with root package name */
    ASN1Integer f12766d;

    /* renamed from: e  reason: collision with root package name */
    ASN1Integer f12767e;

    /* renamed from: f  reason: collision with root package name */
    ASN1Integer f12768f;

    public ECGOST3410ParamSetParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i2, BigInteger bigInteger5) {
        this.f12765c = new ASN1Integer(bigInteger);
        this.f12766d = new ASN1Integer(bigInteger2);
        this.f12763a = new ASN1Integer(bigInteger3);
        this.f12764b = new ASN1Integer(bigInteger4);
        this.f12767e = new ASN1Integer(i2);
        this.f12768f = new ASN1Integer(bigInteger5);
    }

    public ECGOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f12765c = (ASN1Integer) objects.nextElement();
        this.f12766d = (ASN1Integer) objects.nextElement();
        this.f12763a = (ASN1Integer) objects.nextElement();
        this.f12764b = (ASN1Integer) objects.nextElement();
        this.f12767e = (ASN1Integer) objects.nextElement();
        this.f12768f = (ASN1Integer) objects.nextElement();
    }

    public static ECGOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof ECGOST3410ParamSetParameters)) {
            return (ECGOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new ECGOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid GOST3410Parameter: " + obj.getClass().getName());
    }

    public static ECGOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getA() {
        return this.f12765c.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f12763a.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f12764b.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(6);
        aSN1EncodableVector.add(this.f12765c);
        aSN1EncodableVector.add(this.f12766d);
        aSN1EncodableVector.add(this.f12763a);
        aSN1EncodableVector.add(this.f12764b);
        aSN1EncodableVector.add(this.f12767e);
        aSN1EncodableVector.add(this.f12768f);
        return new DERSequence(aSN1EncodableVector);
    }
}
