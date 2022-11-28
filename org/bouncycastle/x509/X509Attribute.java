package org.bouncycastle.x509;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.x509.Attribute;
/* loaded from: classes4.dex */
public class X509Attribute extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    Attribute f15147a;

    public X509Attribute(String str, ASN1Encodable aSN1Encodable) {
        this.f15147a = new Attribute(new ASN1ObjectIdentifier(str), new DERSet(aSN1Encodable));
    }

    public X509Attribute(String str, ASN1EncodableVector aSN1EncodableVector) {
        this.f15147a = new Attribute(new ASN1ObjectIdentifier(str), new DERSet(aSN1EncodableVector));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public X509Attribute(ASN1Encodable aSN1Encodable) {
        this.f15147a = Attribute.getInstance(aSN1Encodable);
    }

    public String getOID() {
        return this.f15147a.getAttrType().getId();
    }

    public ASN1Encodable[] getValues() {
        ASN1Set attrValues = this.f15147a.getAttrValues();
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[attrValues.size()];
        for (int i2 = 0; i2 != attrValues.size(); i2++) {
            aSN1EncodableArr[i2] = attrValues.getObjectAt(i2);
        }
        return aSN1EncodableArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f15147a.toASN1Primitive();
    }
}
