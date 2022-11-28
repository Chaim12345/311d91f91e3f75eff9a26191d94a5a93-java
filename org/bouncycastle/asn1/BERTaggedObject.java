package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
public class BERTaggedObject extends ASN1TaggedObject {
    public BERTaggedObject(int i2) {
        super(false, i2, new BERSequence());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BERTaggedObject(int i2, int i3, int i4, ASN1Encodable aSN1Encodable) {
        super(i2, i3, i4, aSN1Encodable);
    }

    public BERTaggedObject(int i2, int i3, ASN1Encodable aSN1Encodable) {
        super(true, i2, i3, aSN1Encodable);
    }

    public BERTaggedObject(int i2, ASN1Encodable aSN1Encodable) {
        super(true, i2, aSN1Encodable);
    }

    public BERTaggedObject(boolean z, int i2, int i3, ASN1Encodable aSN1Encodable) {
        super(z, i2, i3, aSN1Encodable);
    }

    public BERTaggedObject(boolean z, int i2, ASN1Encodable aSN1Encodable) {
        super(z, i2, aSN1Encodable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        ASN1Primitive aSN1Primitive = this.f12724d.toASN1Primitive();
        boolean isExplicit = isExplicit();
        if (z) {
            int i2 = this.f12722b;
            if (isExplicit || aSN1Primitive.d()) {
                i2 |= 32;
            }
            aSN1OutputStream.r(true, i2, this.f12723c);
        }
        if (!isExplicit) {
            aSN1Primitive.c(aSN1OutputStream, false);
            return;
        }
        aSN1OutputStream.g(128);
        aSN1Primitive.c(aSN1OutputStream, true);
        aSN1OutputStream.g(0);
        aSN1OutputStream.g(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean d() {
        return isExplicit() || this.f12724d.toASN1Primitive().d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        ASN1Primitive aSN1Primitive = this.f12724d.toASN1Primitive();
        boolean isExplicit = isExplicit();
        int e2 = aSN1Primitive.e(isExplicit);
        if (isExplicit) {
            e2 += 3;
        }
        return e2 + (z ? ASN1OutputStream.f(this.f12723c) : 0);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    String k() {
        return ASN1Encoding.BER;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    ASN1Sequence n(ASN1Primitive aSN1Primitive) {
        return new BERSequence(aSN1Primitive);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    ASN1TaggedObject o(int i2, int i3) {
        return new BERTaggedObject(this.f12721a, i2, i3, this.f12724d);
    }
}
