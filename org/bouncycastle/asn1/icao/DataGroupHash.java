package org.bouncycastle.asn1.icao;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class DataGroupHash extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12797a;

    /* renamed from: b  reason: collision with root package name */
    ASN1OctetString f12798b;

    public DataGroupHash(int i2, ASN1OctetString aSN1OctetString) {
        this.f12797a = new ASN1Integer(i2);
        this.f12798b = aSN1OctetString;
    }

    private DataGroupHash(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f12797a = ASN1Integer.getInstance(objects.nextElement());
        this.f12798b = ASN1OctetString.getInstance(objects.nextElement());
    }

    public static DataGroupHash getInstance(Object obj) {
        if (obj instanceof DataGroupHash) {
            return (DataGroupHash) obj;
        }
        if (obj != null) {
            return new DataGroupHash(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1OctetString getDataGroupHashValue() {
        return this.f12798b;
    }

    public int getDataGroupNumber() {
        return this.f12797a.intValueExact();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.f12797a);
        aSN1EncodableVector.add(this.f12798b);
        return new DERSequence(aSN1EncodableVector);
    }
}
