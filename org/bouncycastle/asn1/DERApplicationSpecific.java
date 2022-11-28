package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
public class DERApplicationSpecific extends ASN1ApplicationSpecific {
    public DERApplicationSpecific(int i2, ASN1Encodable aSN1Encodable) {
        this(true, i2, aSN1Encodable);
    }

    public DERApplicationSpecific(int i2, ASN1EncodableVector aSN1EncodableVector) {
        super(new DERTaggedObject(false, 64, i2, (ASN1Encodable) DERFactory.a(aSN1EncodableVector)));
    }

    public DERApplicationSpecific(int i2, byte[] bArr) {
        super(new DERTaggedObject(false, 64, i2, (ASN1Encodable) new DEROctetString(bArr)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERApplicationSpecific(ASN1TaggedObject aSN1TaggedObject) {
        super(aSN1TaggedObject);
    }

    public DERApplicationSpecific(boolean z, int i2, ASN1Encodable aSN1Encodable) {
        super(new DERTaggedObject(z, 64, i2, aSN1Encodable));
    }

    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecific, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive f() {
        return this;
    }

    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecific, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive g() {
        return this;
    }
}
