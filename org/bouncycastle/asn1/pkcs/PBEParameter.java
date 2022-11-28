package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes3.dex */
public class PBEParameter extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12845a;

    /* renamed from: b  reason: collision with root package name */
    ASN1OctetString f12846b;

    private PBEParameter(ASN1Sequence aSN1Sequence) {
        this.f12846b = (ASN1OctetString) aSN1Sequence.getObjectAt(0);
        this.f12845a = (ASN1Integer) aSN1Sequence.getObjectAt(1);
    }

    public PBEParameter(byte[] bArr, int i2) {
        if (bArr.length != 8) {
            throw new IllegalArgumentException("salt length must be 8");
        }
        this.f12846b = new DEROctetString(bArr);
        this.f12845a = new ASN1Integer(i2);
    }

    public static PBEParameter getInstance(Object obj) {
        if (obj instanceof PBEParameter) {
            return (PBEParameter) obj;
        }
        if (obj != null) {
            return new PBEParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getIterationCount() {
        return this.f12845a.getValue();
    }

    public byte[] getSalt() {
        return this.f12846b.getOctets();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.f12846b);
        aSN1EncodableVector.add(this.f12845a);
        return new DERSequence(aSN1EncodableVector);
    }
}
