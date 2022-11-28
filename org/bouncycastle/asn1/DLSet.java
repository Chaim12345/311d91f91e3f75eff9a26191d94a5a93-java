package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
public class DLSet extends ASN1Set {
    private int contentsLength;

    public DLSet() {
        this.contentsLength = -1;
    }

    public DLSet(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
        this.contentsLength = -1;
    }

    public DLSet(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector, false);
        this.contentsLength = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DLSet(boolean z, ASN1Encodable[] aSN1EncodableArr) {
        super(z, aSN1EncodableArr);
        this.contentsLength = -1;
    }

    public DLSet(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr, false);
        this.contentsLength = -1;
    }

    private int getContentsLength() {
        if (this.contentsLength < 0) {
            int length = this.f12714a.length;
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                i2 += this.f12714a[i3].toASN1Primitive().g().e(true);
            }
            this.contentsLength = i2;
        }
        return this.contentsLength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.q(z, 49);
        DLOutputStream c2 = aSN1OutputStream.c();
        int length = this.f12714a.length;
        int i2 = 0;
        if (this.contentsLength >= 0 || length > 16) {
            aSN1OutputStream.i(getContentsLength());
            while (i2 < length) {
                c2.s(this.f12714a[i2].toASN1Primitive(), true);
                i2++;
            }
            return;
        }
        ASN1Primitive[] aSN1PrimitiveArr = new ASN1Primitive[length];
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            ASN1Primitive g2 = this.f12714a[i4].toASN1Primitive().g();
            aSN1PrimitiveArr[i4] = g2;
            i3 += g2.e(true);
        }
        this.contentsLength = i3;
        aSN1OutputStream.i(i3);
        while (i2 < length) {
            c2.s(aSN1PrimitiveArr[i2], true);
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        return ASN1OutputStream.e(z, getContentsLength());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Set, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        return this;
    }
}
