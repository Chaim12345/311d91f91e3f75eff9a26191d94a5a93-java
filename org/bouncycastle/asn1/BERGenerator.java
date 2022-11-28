package org.bouncycastle.asn1;

import java.io.OutputStream;
/* loaded from: classes3.dex */
public abstract class BERGenerator extends ASN1Generator {
    private boolean _isExplicit;
    private int _tagNo;
    private boolean _tagged;

    /* JADX INFO: Access modifiers changed from: protected */
    public BERGenerator(OutputStream outputStream) {
        super(outputStream);
        this._tagged = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BERGenerator(OutputStream outputStream, int i2, boolean z) {
        super(outputStream);
        this._tagged = false;
        this._tagged = true;
        this._isExplicit = z;
        this._tagNo = i2;
    }

    private void writeHdr(int i2) {
        this.f12691a.write(i2);
        this.f12691a.write(128);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        this.f12691a.write(0);
        this.f12691a.write(0);
        if (this._tagged && this._isExplicit) {
            this.f12691a.write(0);
            this.f12691a.write(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(int i2) {
        if (this._tagged) {
            int i3 = this._tagNo | 128;
            if (this._isExplicit) {
                writeHdr(i3 | 32);
            } else if ((i2 & 32) == 0) {
                writeHdr(i3);
                return;
            } else {
                i2 = i3 | 32;
            }
        }
        writeHdr(i2);
    }

    @Override // org.bouncycastle.asn1.ASN1Generator
    public OutputStream getRawOutputStream() {
        return this.f12691a;
    }
}
