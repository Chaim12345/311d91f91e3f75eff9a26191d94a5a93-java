package org.bouncycastle.asn1.misc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class IDEACBCPar extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1OctetString f12804a;

    private IDEACBCPar(ASN1Sequence aSN1Sequence) {
        this.f12804a = aSN1Sequence.size() == 1 ? (ASN1OctetString) aSN1Sequence.getObjectAt(0) : null;
    }

    public IDEACBCPar(byte[] bArr) {
        this.f12804a = new DEROctetString(bArr);
    }

    public static IDEACBCPar getInstance(Object obj) {
        if (obj instanceof IDEACBCPar) {
            return (IDEACBCPar) obj;
        }
        if (obj != null) {
            return new IDEACBCPar(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getIV() {
        ASN1OctetString aSN1OctetString = this.f12804a;
        if (aSN1OctetString != null) {
            return Arrays.clone(aSN1OctetString.getOctets());
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(1);
        ASN1OctetString aSN1OctetString = this.f12804a;
        if (aSN1OctetString != null) {
            aSN1EncodableVector.add(aSN1OctetString);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
