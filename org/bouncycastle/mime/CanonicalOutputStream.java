package org.bouncycastle.mime;

import com.google.common.base.Ascii;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import org.bouncycastle.mime.smime.SMimeParserContext;
/* loaded from: classes4.dex */
public class CanonicalOutputStream extends FilterOutputStream {

    /* renamed from: b  reason: collision with root package name */
    protected static byte[] f14357b;

    /* renamed from: a  reason: collision with root package name */
    protected int f14358a;
    private final boolean is7Bit;

    static {
        f14357b = r0;
        byte[] bArr = {Ascii.CR, 10};
    }

    public CanonicalOutputStream(SMimeParserContext sMimeParserContext, Headers headers, OutputStream outputStream) {
        super(outputStream);
        this.f14358a = -1;
        this.is7Bit = headers.getContentType() != null ? (headers.getContentType() == null || headers.getContentType().equals("binary")) ? false : true : sMimeParserContext.getDefaultContentTransferEncoding().equals("7bit");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i2) {
        if (this.is7Bit) {
            if (i2 == 13) {
                ((FilterOutputStream) this).out.write(f14357b);
            } else if (i2 == 10) {
                if (this.f14358a != 13) {
                    ((FilterOutputStream) this).out.write(f14357b);
                }
            }
            this.f14358a = i2;
        }
        ((FilterOutputStream) this).out.write(i2);
        this.f14358a = i2;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 != i2 + i3; i4++) {
            write(bArr[i4]);
        }
    }

    public void writeln() {
        ((FilterOutputStream) this).out.write(f14357b);
    }
}
