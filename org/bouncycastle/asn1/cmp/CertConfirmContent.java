package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
/* loaded from: classes3.dex */
public class CertConfirmContent extends ASN1Object {
    private ASN1Sequence content;

    private CertConfirmContent(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static CertConfirmContent getInstance(Object obj) {
        if (obj instanceof CertConfirmContent) {
            return (CertConfirmContent) obj;
        }
        if (obj != null) {
            return new CertConfirmContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }

    public CertStatus[] toCertStatusArray() {
        int size = this.content.size();
        CertStatus[] certStatusArr = new CertStatus[size];
        for (int i2 = 0; i2 != size; i2++) {
            certStatusArr[i2] = CertStatus.getInstance(this.content.getObjectAt(i2));
        }
        return certStatusArr;
    }
}
