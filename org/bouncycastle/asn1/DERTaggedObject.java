package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
public class DERTaggedObject extends ASN1TaggedObject {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DERTaggedObject(int i2, int i3, int i4, ASN1Encodable aSN1Encodable) {
        super(i2, i3, i4, aSN1Encodable);
    }

    public DERTaggedObject(int i2, int i3, ASN1Encodable aSN1Encodable) {
        super(true, i2, i3, aSN1Encodable);
    }

    public DERTaggedObject(int i2, ASN1Encodable aSN1Encodable) {
        super(true, i2, aSN1Encodable);
    }

    public DERTaggedObject(boolean z, int i2, int i3, ASN1Encodable aSN1Encodable) {
        super(z, i2, i3, aSN1Encodable);
    }

    public DERTaggedObject(boolean z, int i2, ASN1Encodable aSN1Encodable) {
        super(z, i2, aSN1Encodable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        ASN1Primitive f2 = this.f12724d.toASN1Primitive().f();
        boolean isExplicit = isExplicit();
        if (z) {
            int i2 = this.f12722b;
            if (isExplicit || f2.d()) {
                i2 |= 32;
            }
            aSN1OutputStream.r(true, i2, this.f12723c);
        }
        if (isExplicit) {
            aSN1OutputStream.i(f2.e(true));
        }
        f2.c(aSN1OutputStream.b(), isExplicit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean d() {
        return isExplicit() || this.f12724d.toASN1Primitive().f().d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        ASN1Primitive f2 = this.f12724d.toASN1Primitive().f();
        boolean isExplicit = isExplicit();
        int e2 = f2.e(isExplicit);
        if (isExplicit) {
            e2 += ASN1OutputStream.d(e2);
        }
        return e2 + (z ? ASN1OutputStream.f(this.f12723c) : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1TaggedObject, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive f() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1TaggedObject, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        return this;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    String k() {
        return ASN1Encoding.DER;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    ASN1Sequence n(ASN1Primitive aSN1Primitive) {
        return new DERSequence(aSN1Primitive);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObject
    ASN1TaggedObject o(int i2, int i3) {
        return new DERTaggedObject(this.f12721a, i2, i3, this.f12724d);
    }
}
