package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
public class BERApplicationSpecific extends ASN1ApplicationSpecific {
    public BERApplicationSpecific(int i2, ASN1Encodable aSN1Encodable) {
        this(true, i2, aSN1Encodable);
    }

    public BERApplicationSpecific(int i2, ASN1EncodableVector aSN1EncodableVector) {
        super(new BERTaggedObject(false, 64, i2, (ASN1Encodable) BERFactory.a(aSN1EncodableVector)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BERApplicationSpecific(ASN1TaggedObject aSN1TaggedObject) {
        super(aSN1TaggedObject);
    }

    public BERApplicationSpecific(boolean z, int i2, ASN1Encodable aSN1Encodable) {
        super(new BERTaggedObject(z, 64, i2, aSN1Encodable));
    }
}
