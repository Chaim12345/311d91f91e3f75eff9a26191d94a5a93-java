package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
/* loaded from: classes3.dex */
public abstract class DERGenerator extends ASN1Generator {
    private boolean _isExplicit;
    private int _tagNo;
    private boolean _tagged;

    /* JADX INFO: Access modifiers changed from: protected */
    public DERGenerator(OutputStream outputStream) {
        super(outputStream);
        this._tagged = false;
    }

    public DERGenerator(OutputStream outputStream, int i2, boolean z) {
        super(outputStream);
        this._tagged = false;
        this._tagged = true;
        this._isExplicit = z;
        this._tagNo = i2;
    }

    private void writeLength(OutputStream outputStream, int i2) {
        if (i2 <= 127) {
            outputStream.write((byte) i2);
            return;
        }
        int i3 = i2;
        int i4 = 1;
        while (true) {
            i3 >>>= 8;
            if (i3 == 0) {
                break;
            }
            i4++;
        }
        outputStream.write((byte) (i4 | 128));
        for (int i5 = (i4 - 1) * 8; i5 >= 0; i5 -= 8) {
            outputStream.write((byte) (i2 >> i5));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i2, byte[] bArr) {
        if (!this._tagged) {
            b(this.f12691a, i2, bArr);
            return;
        }
        int i3 = this._tagNo;
        int i4 = i3 | 128;
        if (this._isExplicit) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            b(byteArrayOutputStream, i2, bArr);
            b(this.f12691a, i3 | 32 | 128, byteArrayOutputStream.toByteArray());
        } else if ((i2 & 32) != 0) {
            b(this.f12691a, i4 | 32, bArr);
        } else {
            b(this.f12691a, i4, bArr);
        }
    }

    void b(OutputStream outputStream, int i2, byte[] bArr) {
        outputStream.write(i2);
        writeLength(outputStream, bArr.length);
        outputStream.write(bArr);
    }
}
