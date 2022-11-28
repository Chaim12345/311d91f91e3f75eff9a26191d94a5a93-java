package org.bouncycastle.asn1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class ASN1UniversalType extends ASN1Type {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1UniversalType(Class cls, int i2) {
        super(cls);
        ASN1Tag.a(0, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ASN1Primitive a(ASN1Primitive aSN1Primitive) {
        if (this.f12725a.isInstance(aSN1Primitive)) {
            return aSN1Primitive;
        }
        throw new IllegalStateException("unexpected object: " + aSN1Primitive.getClass().getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ASN1Primitive b(byte[] bArr) {
        return a(ASN1Primitive.fromByteArray(bArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Primitive c(ASN1Sequence aSN1Sequence) {
        throw new IllegalStateException("unexpected implicit constructed encoding");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Primitive d(DEROctetString dEROctetString) {
        throw new IllegalStateException("unexpected implicit primitive encoding");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ASN1Primitive e(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (128 == aSN1TaggedObject.getTagClass()) {
            return a(aSN1TaggedObject.l(z, this));
        }
        throw new IllegalStateException("this method only valid for CONTEXT_SPECIFIC tags");
    }
}
