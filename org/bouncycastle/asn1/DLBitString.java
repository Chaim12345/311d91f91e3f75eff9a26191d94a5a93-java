package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
public class DLBitString extends ASN1BitString {
    public DLBitString(byte b2, int i2) {
        super(b2, i2);
    }

    public DLBitString(int i2) {
        super(ASN1BitString.i(i2), ASN1BitString.j(i2));
    }

    public DLBitString(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER), 0);
    }

    public DLBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DLBitString(byte[] bArr, int i2) {
        super(bArr, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DLBitString(byte[] bArr, boolean z) {
        super(bArr, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void k(ASN1OutputStream aSN1OutputStream, boolean z, byte b2, byte[] bArr, int i2, int i3) {
        aSN1OutputStream.l(z, 3, b2, bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void l(ASN1OutputStream aSN1OutputStream, boolean z, byte[] bArr, int i2, int i3) {
        aSN1OutputStream.n(z, 3, bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(boolean z, int i2) {
        return ASN1OutputStream.e(z, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.m(z, 3, this.f12677a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean d() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        return ASN1OutputStream.e(z, this.f12677a.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1BitString, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        return this;
    }
}
