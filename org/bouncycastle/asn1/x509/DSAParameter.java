package org.bouncycastle.asn1.x509;

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
public class DSAParameter extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12945a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12946b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Integer f12947c;

    public DSAParameter(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f12945a = new ASN1Integer(bigInteger);
        this.f12946b = new ASN1Integer(bigInteger2);
        this.f12947c = new ASN1Integer(bigInteger3);
    }

    private DSAParameter(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        this.f12945a = ASN1Integer.getInstance(objects.nextElement());
        this.f12946b = ASN1Integer.getInstance(objects.nextElement());
        this.f12947c = ASN1Integer.getInstance(objects.nextElement());
    }

    public static DSAParameter getInstance(Object obj) {
        if (obj instanceof DSAParameter) {
            return (DSAParameter) obj;
        }
        if (obj != null) {
            return new DSAParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static DSAParameter getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getG() {
        return this.f12947c.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f12945a.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f12946b.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f12945a);
        aSN1EncodableVector.add(this.f12946b);
        aSN1EncodableVector.add(this.f12947c);
        return new DERSequence(aSN1EncodableVector);
    }
}
