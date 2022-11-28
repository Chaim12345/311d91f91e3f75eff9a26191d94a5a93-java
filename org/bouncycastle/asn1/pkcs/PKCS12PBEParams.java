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
public class PKCS12PBEParams extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12847a;

    /* renamed from: b  reason: collision with root package name */
    ASN1OctetString f12848b;

    private PKCS12PBEParams(ASN1Sequence aSN1Sequence) {
        this.f12848b = (ASN1OctetString) aSN1Sequence.getObjectAt(0);
        this.f12847a = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public PKCS12PBEParams(byte[] bArr, int i2) {
        this.f12848b = new DEROctetString(bArr);
        this.f12847a = new ASN1Integer(i2);
    }

    public static PKCS12PBEParams getInstance(Object obj) {
        if (obj instanceof PKCS12PBEParams) {
            return (PKCS12PBEParams) obj;
        }
        if (obj != null) {
            return new PKCS12PBEParams(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getIV() {
        return this.f12848b.getOctets();
    }

    public BigInteger getIterations() {
        return this.f12847a.getValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.f12848b);
        aSN1EncodableVector.add(this.f12847a);
        return new DERSequence(aSN1EncodableVector);
    }
}
