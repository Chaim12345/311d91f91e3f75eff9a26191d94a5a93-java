package org.bouncycastle.asn1.oiw;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class ElGamalParameter extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12827a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12828b;

    public ElGamalParameter(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f12827a = new ASN1Integer(bigInteger);
        this.f12828b = new ASN1Integer(bigInteger2);
    }

    private ElGamalParameter(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f12827a = (ASN1Integer) objects.nextElement();
        this.f12828b = (ASN1Integer) objects.nextElement();
    }

    public static ElGamalParameter getInstance(Object obj) {
        if (obj instanceof ElGamalParameter) {
            return (ElGamalParameter) obj;
        }
        if (obj != null) {
            return new ElGamalParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getG() {
        return this.f12828b.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f12827a.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.f12827a);
        aSN1EncodableVector.add(this.f12828b);
        return new DERSequence(aSN1EncodableVector);
    }
}
