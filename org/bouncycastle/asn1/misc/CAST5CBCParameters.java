package org.bouncycastle.asn1.misc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class CAST5CBCParameters extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12802a;

    /* renamed from: b  reason: collision with root package name */
    ASN1OctetString f12803b;

    private CAST5CBCParameters(ASN1Sequence aSN1Sequence) {
        this.f12803b = (ASN1OctetString) aSN1Sequence.getObjectAt(0);
        this.f12802a = (ASN1Integer) aSN1Sequence.getObjectAt(1);
    }

    public CAST5CBCParameters(byte[] bArr, int i2) {
        this.f12803b = new DEROctetString(Arrays.clone(bArr));
        this.f12802a = new ASN1Integer(i2);
    }

    public static CAST5CBCParameters getInstance(Object obj) {
        if (obj instanceof CAST5CBCParameters) {
            return (CAST5CBCParameters) obj;
        }
        if (obj != null) {
            return new CAST5CBCParameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getIV() {
        return Arrays.clone(this.f12803b.getOctets());
    }

    public int getKeyLength() {
        return this.f12802a.intValueExact();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.f12803b);
        aSN1EncodableVector.add(this.f12802a);
        return new DERSequence(aSN1EncodableVector);
    }
}
