package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes3.dex */
public class ASN1OutputStream {
    private OutputStream os;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1OutputStream(OutputStream outputStream) {
        this.os = outputStream;
    }

    public static ASN1OutputStream create(OutputStream outputStream) {
        return new ASN1OutputStream(outputStream);
    }

    public static ASN1OutputStream create(OutputStream outputStream, String str) {
        return str.equals(ASN1Encoding.DER) ? new DEROutputStream(outputStream) : str.equals(ASN1Encoding.DL) ? new DLOutputStream(outputStream) : new ASN1OutputStream(outputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(int i2) {
        if (i2 < 128) {
            return 1;
        }
        int i3 = 2;
        while (true) {
            i2 >>>= 8;
            if (i2 == 0) {
                return i3;
            }
            i3++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(boolean z, int i2) {
        return (z ? 1 : 0) + d(i2) + i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int f(int i2) {
        if (i2 < 31) {
            return 1;
        }
        int i3 = 2;
        while (true) {
            i2 >>>= 7;
            if (i2 == 0) {
                return i3;
            }
            i3++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DEROutputStream b() {
        return new DEROutputStream(this.os);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DLOutputStream c() {
        return new DLOutputStream(this.os);
    }

    public void close() {
        this.os.close();
    }

    public void flush() {
        this.os.flush();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void g(int i2) {
        this.os.write(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void h(byte[] bArr, int i2, int i3) {
        this.os.write(bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void i(int i2) {
        if (i2 < 128) {
            g(i2);
            return;
        }
        int i3 = 5;
        byte[] bArr = new byte[5];
        do {
            i3--;
            bArr[i3] = (byte) i2;
            i2 >>>= 8;
        } while (i2 != 0);
        int i4 = 5 - i3;
        int i5 = i3 - 1;
        bArr[i5] = (byte) (i4 | 128);
        h(bArr, i5, i4 + 1);
    }

    void j(ASN1Encodable[] aSN1EncodableArr) {
        for (ASN1Encodable aSN1Encodable : aSN1EncodableArr) {
            aSN1Encodable.toASN1Primitive().c(this, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void k(boolean z, int i2, byte b2) {
        q(z, i2);
        i(1);
        g(b2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void l(boolean z, int i2, byte b2, byte[] bArr, int i3, int i4) {
        q(z, i2);
        i(i4 + 1);
        g(b2);
        h(bArr, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void m(boolean z, int i2, byte[] bArr) {
        q(z, i2);
        i(bArr.length);
        h(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void n(boolean z, int i2, byte[] bArr, int i3, int i4) {
        q(z, i2);
        i(i4);
        h(bArr, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void o(boolean z, int i2, byte[] bArr, int i3, int i4, byte b2) {
        q(z, i2);
        i(i4 + 1);
        h(bArr, i3, i4);
        g(b2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void p(boolean z, int i2, ASN1Encodable[] aSN1EncodableArr) {
        q(z, i2);
        g(128);
        j(aSN1EncodableArr);
        g(0);
        g(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void q(boolean z, int i2) {
        if (z) {
            g(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void r(boolean z, int i2, int i3) {
        if (z) {
            if (i3 < 31) {
                g(i2 | i3);
                return;
            }
            byte[] bArr = new byte[6];
            int i4 = 5;
            bArr[5] = (byte) (i3 & 127);
            while (i3 > 127) {
                i3 >>>= 7;
                i4--;
                bArr[i4] = (byte) ((i3 & 127) | 128);
            }
            int i5 = i4 - 1;
            bArr[i5] = (byte) (31 | i2);
            h(bArr, i5, 6 - i5);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(ASN1Primitive aSN1Primitive, boolean z) {
        aSN1Primitive.c(this, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t(ASN1Primitive[] aSN1PrimitiveArr) {
        for (ASN1Primitive aSN1Primitive : aSN1PrimitiveArr) {
            aSN1Primitive.c(this, true);
        }
    }

    public final void writeObject(ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable == null) {
            throw new IOException("null object detected");
        }
        s(aSN1Encodable.toASN1Primitive(), true);
        a();
    }

    public final void writeObject(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive == null) {
            throw new IOException("null object detected");
        }
        s(aSN1Primitive, true);
        a();
    }
}
