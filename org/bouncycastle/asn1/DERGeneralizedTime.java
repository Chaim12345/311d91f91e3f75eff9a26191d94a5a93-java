package org.bouncycastle.asn1;

import java.util.Date;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
public class DERGeneralizedTime extends ASN1GeneralizedTime {
    public DERGeneralizedTime(String str) {
        super(str);
    }

    public DERGeneralizedTime(Date date) {
        super(date);
    }

    public DERGeneralizedTime(byte[] bArr) {
        super(bArr);
    }

    private byte[] getDERTime() {
        byte[] bArr = this.f12690a;
        if (bArr[bArr.length - 1] == 90) {
            if (!j()) {
                byte[] bArr2 = this.f12690a;
                byte[] bArr3 = new byte[bArr2.length + 4];
                System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length - 1);
                System.arraycopy(Strings.toByteArray("0000Z"), 0, bArr3, this.f12690a.length - 1, 5);
                return bArr3;
            } else if (!k()) {
                byte[] bArr4 = this.f12690a;
                byte[] bArr5 = new byte[bArr4.length + 2];
                System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length - 1);
                System.arraycopy(Strings.toByteArray("00Z"), 0, bArr5, this.f12690a.length - 1, 3);
                return bArr5;
            } else if (i()) {
                int length = this.f12690a.length - 2;
                while (length > 0 && this.f12690a[length] == 48) {
                    length--;
                }
                byte[] bArr6 = this.f12690a;
                if (bArr6[length] == 46) {
                    byte[] bArr7 = new byte[length + 1];
                    System.arraycopy(bArr6, 0, bArr7, 0, length);
                    bArr7[length] = 90;
                    return bArr7;
                }
                byte[] bArr8 = new byte[length + 2];
                int i2 = length + 1;
                System.arraycopy(bArr6, 0, bArr8, 0, i2);
                bArr8[i2] = 90;
                return bArr8;
            } else {
                return this.f12690a;
            }
        }
        return bArr;
    }

    @Override // org.bouncycastle.asn1.ASN1GeneralizedTime, org.bouncycastle.asn1.ASN1Primitive
    void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.m(z, 24, getDERTime());
    }

    @Override // org.bouncycastle.asn1.ASN1GeneralizedTime, org.bouncycastle.asn1.ASN1Primitive
    int e(boolean z) {
        return ASN1OutputStream.e(z, getDERTime().length);
    }

    @Override // org.bouncycastle.asn1.ASN1GeneralizedTime, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive f() {
        return this;
    }

    @Override // org.bouncycastle.asn1.ASN1GeneralizedTime, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive g() {
        return this;
    }
}
