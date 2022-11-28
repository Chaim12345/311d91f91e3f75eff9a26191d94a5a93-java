package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class DHParameter extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12836a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12837b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Integer f12838c;

    public DHParameter(BigInteger bigInteger, BigInteger bigInteger2, int i2) {
        this.f12836a = new ASN1Integer(bigInteger);
        this.f12837b = new ASN1Integer(bigInteger2);
        this.f12838c = i2 != 0 ? new ASN1Integer(i2) : null;
    }

    private DHParameter(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f12836a = ASN1Integer.getInstance(objects.nextElement());
        this.f12837b = ASN1Integer.getInstance(objects.nextElement());
        this.f12838c = objects.hasMoreElements() ? (ASN1Integer) objects.nextElement() : null;
    }

    public static DHParameter getInstance(Object obj) {
        if (obj instanceof DHParameter) {
            return (DHParameter) obj;
        }
        if (obj != null) {
            return new DHParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getG() {
        return this.f12837b.getPositiveValue();
    }

    public BigInteger getL() {
        ASN1Integer aSN1Integer = this.f12838c;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f12836a.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f12836a);
        aSN1EncodableVector.add(this.f12837b);
        if (getL() != null) {
            aSN1EncodableVector.add(this.f12838c);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
