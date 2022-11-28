package org.bouncycastle.oer.its;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DEROctetString;
/* loaded from: classes4.dex */
public class SubjectAssurance extends DEROctetString {
    public SubjectAssurance(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public SubjectAssurance(byte[] bArr) {
        super(bArr);
    }

    public static SubjectAssurance getInstance(Object obj) {
        return obj instanceof SubjectAssurance ? (SubjectAssurance) obj : new SubjectAssurance(ASN1OctetString.getInstance(obj).getOctets());
    }
}
