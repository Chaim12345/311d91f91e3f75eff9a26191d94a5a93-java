package org.bouncycastle.asn1.isismtt.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1PrintableString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class MonetaryLimit extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1PrintableString f12799a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12800b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Integer f12801c;

    public MonetaryLimit(String str, int i2, int i3) {
        this.f12799a = new DERPrintableString(str, true);
        this.f12800b = new ASN1Integer(i2);
        this.f12801c = new ASN1Integer(i3);
    }

    private MonetaryLimit(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        this.f12799a = ASN1PrintableString.getInstance(objects.nextElement());
        this.f12800b = ASN1Integer.getInstance(objects.nextElement());
        this.f12801c = ASN1Integer.getInstance(objects.nextElement());
    }

    public static MonetaryLimit getInstance(Object obj) {
        if (obj == null || (obj instanceof MonetaryLimit)) {
            return (MonetaryLimit) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new MonetaryLimit(ASN1Sequence.getInstance(obj));
        }
        throw new IllegalArgumentException("unknown object in getInstance");
    }

    public BigInteger getAmount() {
        return this.f12800b.getValue();
    }

    public String getCurrency() {
        return this.f12799a.getString();
    }

    public BigInteger getExponent() {
        return this.f12801c.getValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f12799a);
        aSN1EncodableVector.add(this.f12800b);
        aSN1EncodableVector.add(this.f12801c);
        return new DERSequence(aSN1EncodableVector);
    }
}
