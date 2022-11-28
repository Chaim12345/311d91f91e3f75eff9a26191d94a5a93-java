package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
public class DLApplicationSpecific extends ASN1ApplicationSpecific {
    public DLApplicationSpecific(int i2, ASN1Encodable aSN1Encodable) {
        this(true, i2, aSN1Encodable);
    }

    public DLApplicationSpecific(int i2, ASN1EncodableVector aSN1EncodableVector) {
        super(new DLTaggedObject(false, 64, i2, (ASN1Encodable) DLFactory.a(aSN1EncodableVector)));
    }

    public DLApplicationSpecific(int i2, byte[] bArr) {
        super(new DLTaggedObject(false, 64, i2, (ASN1Encodable) new DEROctetString(bArr)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DLApplicationSpecific(ASN1TaggedObject aSN1TaggedObject) {
        super(aSN1TaggedObject);
    }

    public DLApplicationSpecific(boolean z, int i2, ASN1Encodable aSN1Encodable) {
        super(new DLTaggedObject(z, 64, i2, aSN1Encodable));
    }

    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecific, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive g() {
        return this;
    }
}
