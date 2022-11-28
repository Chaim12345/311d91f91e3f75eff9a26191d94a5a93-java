package org.bouncycastle.asn1;
/* loaded from: classes3.dex */
public class BERBitString extends ASN1BitString {
    private static final int DEFAULT_SEGMENT_LIMIT = 1000;
    private final ASN1BitString[] elements;
    private final int segmentLimit;

    public BERBitString(byte b2, int i2) {
        super(b2, i2);
        this.elements = null;
        this.segmentLimit = 1000;
    }

    public BERBitString(ASN1Encodable aSN1Encodable) {
        this(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER), 0);
    }

    public BERBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public BERBitString(byte[] bArr, int i2) {
        this(bArr, i2, 1000);
    }

    public BERBitString(byte[] bArr, int i2, int i3) {
        super(bArr, i2);
        this.elements = null;
        this.segmentLimit = i3;
    }

    public BERBitString(ASN1BitString[] aSN1BitStringArr) {
        this(aSN1BitStringArr, 1000);
    }

    public BERBitString(ASN1BitString[] aSN1BitStringArr, int i2) {
        super(k(aSN1BitStringArr), false);
        this.elements = aSN1BitStringArr;
        this.segmentLimit = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] k(ASN1BitString[] aSN1BitStringArr) {
        int length = aSN1BitStringArr.length;
        if (length != 0) {
            if (length != 1) {
                int i2 = length - 1;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    byte[] bArr = aSN1BitStringArr[i4].f12677a;
                    if (bArr[0] != 0) {
                        throw new IllegalArgumentException("only the last nested bitstring can have padding");
                    }
                    i3 += bArr.length - 1;
                }
                byte[] bArr2 = aSN1BitStringArr[i2].f12677a;
                byte b2 = bArr2[0];
                byte[] bArr3 = new byte[i3 + bArr2.length];
                bArr3[0] = b2;
                int i5 = 1;
                for (ASN1BitString aSN1BitString : aSN1BitStringArr) {
                    byte[] bArr4 = aSN1BitString.f12677a;
                    int length2 = bArr4.length - 1;
                    System.arraycopy(bArr4, 1, bArr3, i5, length2);
                    i5 += length2;
                }
                return bArr3;
            }
            return aSN1BitStringArr[0].f12677a;
        }
        return new byte[]{0};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        if (!d()) {
            byte[] bArr = this.f12677a;
            DLBitString.l(aSN1OutputStream, z, bArr, 0, bArr.length);
            return;
        }
        aSN1OutputStream.q(z, 35);
        aSN1OutputStream.g(128);
        ASN1BitString[] aSN1BitStringArr = this.elements;
        if (aSN1BitStringArr != null) {
            aSN1OutputStream.t(aSN1BitStringArr);
        } else {
            byte[] bArr2 = this.f12677a;
            if (bArr2.length >= 2) {
                byte b2 = bArr2[0];
                int length = bArr2.length;
                int i2 = length - 1;
                int i3 = this.segmentLimit - 1;
                while (i2 > i3) {
                    DLBitString.k(aSN1OutputStream, true, (byte) 0, this.f12677a, length - i2, i3);
                    i2 -= i3;
                }
                DLBitString.k(aSN1OutputStream, true, b2, this.f12677a, length - i2, i2);
            }
        }
        aSN1OutputStream.g(0);
        aSN1OutputStream.g(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean d() {
        return this.elements != null || this.f12677a.length > this.segmentLimit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        if (!d()) {
            return DLBitString.m(z, this.f12677a.length);
        }
        int i2 = z ? 4 : 3;
        if (this.elements == null) {
            byte[] bArr = this.f12677a;
            if (bArr.length < 2) {
                return i2;
            }
            int i3 = this.segmentLimit;
            int length = (bArr.length - 2) / (i3 - 1);
            return i2 + (DLBitString.m(true, i3) * length) + DLBitString.m(true, this.f12677a.length - (length * (this.segmentLimit - 1)));
        }
        int i4 = 0;
        while (true) {
            ASN1BitString[] aSN1BitStringArr = this.elements;
            if (i4 >= aSN1BitStringArr.length) {
                return i2;
            }
            i2 += aSN1BitStringArr[i4].e(true);
            i4++;
        }
    }
}
