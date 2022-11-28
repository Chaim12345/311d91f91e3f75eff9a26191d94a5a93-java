package org.bouncycastle.asn1.ua;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.field.PolynomialExtensionField;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class DSTU4145ECBinary extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    BigInteger f12905a;

    /* renamed from: b  reason: collision with root package name */
    DSTU4145BinaryField f12906b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Integer f12907c;

    /* renamed from: d  reason: collision with root package name */
    ASN1OctetString f12908d;

    /* renamed from: e  reason: collision with root package name */
    ASN1Integer f12909e;

    /* renamed from: f  reason: collision with root package name */
    ASN1OctetString f12910f;

    private DSTU4145ECBinary(ASN1Sequence aSN1Sequence) {
        this.f12905a = BigInteger.valueOf(0L);
        int i2 = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(0);
            if (!aSN1TaggedObject.isExplicit() || aSN1TaggedObject.getTagNo() != 0) {
                throw new IllegalArgumentException("object parse error");
            }
            this.f12905a = ASN1Integer.getInstance(aSN1TaggedObject.getLoadedObject()).getValue();
            i2 = 1;
        }
        this.f12906b = DSTU4145BinaryField.getInstance(aSN1Sequence.getObjectAt(i2));
        int i3 = i2 + 1;
        this.f12907c = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i3));
        int i4 = i3 + 1;
        this.f12908d = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i4));
        int i5 = i4 + 1;
        this.f12909e = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i5));
        this.f12910f = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i5 + 1));
    }

    public DSTU4145ECBinary(ECDomainParameters eCDomainParameters) {
        DSTU4145BinaryField dSTU4145BinaryField;
        this.f12905a = BigInteger.valueOf(0L);
        ECCurve curve = eCDomainParameters.getCurve();
        if (!ECAlgorithms.isF2mCurve(curve)) {
            throw new IllegalArgumentException("only binary domain is possible");
        }
        int[] exponentsPresent = ((PolynomialExtensionField) curve.getField()).getMinimalPolynomial().getExponentsPresent();
        if (exponentsPresent.length == 3) {
            dSTU4145BinaryField = new DSTU4145BinaryField(exponentsPresent[2], exponentsPresent[1]);
        } else if (exponentsPresent.length != 5) {
            throw new IllegalArgumentException("curve must have a trinomial or pentanomial basis");
        } else {
            dSTU4145BinaryField = new DSTU4145BinaryField(exponentsPresent[4], exponentsPresent[1], exponentsPresent[2], exponentsPresent[3]);
        }
        this.f12906b = dSTU4145BinaryField;
        this.f12907c = new ASN1Integer(curve.getA().toBigInteger());
        this.f12908d = new DEROctetString(curve.getB().getEncoded());
        this.f12909e = new ASN1Integer(eCDomainParameters.getN());
        this.f12910f = new DEROctetString(DSTU4145PointEncoder.encodePoint(eCDomainParameters.getG()));
    }

    public static DSTU4145ECBinary getInstance(Object obj) {
        if (obj instanceof DSTU4145ECBinary) {
            return (DSTU4145ECBinary) obj;
        }
        if (obj != null) {
            return new DSTU4145ECBinary(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getA() {
        return this.f12907c.getValue();
    }

    public byte[] getB() {
        return Arrays.clone(this.f12908d.getOctets());
    }

    public DSTU4145BinaryField getField() {
        return this.f12906b;
    }

    public byte[] getG() {
        return Arrays.clone(this.f12910f.getOctets());
    }

    public BigInteger getN() {
        return this.f12909e.getValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(6);
        if (this.f12905a.compareTo(BigInteger.valueOf(0L)) != 0) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, (ASN1Encodable) new ASN1Integer(this.f12905a)));
        }
        aSN1EncodableVector.add(this.f12906b);
        aSN1EncodableVector.add(this.f12907c);
        aSN1EncodableVector.add(this.f12908d);
        aSN1EncodableVector.add(this.f12909e);
        aSN1EncodableVector.add(this.f12910f);
        return new DERSequence(aSN1EncodableVector);
    }
}
