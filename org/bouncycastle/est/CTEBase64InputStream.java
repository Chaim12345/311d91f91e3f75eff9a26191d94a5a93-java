package org.bouncycastle.est;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class CTEBase64InputStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    protected final InputStream f13531a;

    /* renamed from: b  reason: collision with root package name */
    protected final byte[] f13532b = new byte[1024];

    /* renamed from: c  reason: collision with root package name */
    protected final byte[] f13533c = new byte[768];

    /* renamed from: d  reason: collision with root package name */
    protected final OutputStream f13534d = new OutputStream() { // from class: org.bouncycastle.est.CTEBase64InputStream.1
        @Override // java.io.OutputStream
        public void write(int i2) {
            CTEBase64InputStream cTEBase64InputStream = CTEBase64InputStream.this;
            byte[] bArr = cTEBase64InputStream.f13533c;
            int i3 = cTEBase64InputStream.f13537g;
            cTEBase64InputStream.f13537g = i3 + 1;
            bArr[i3] = (byte) i2;
        }
    };

    /* renamed from: e  reason: collision with root package name */
    protected final Long f13535e;

    /* renamed from: f  reason: collision with root package name */
    protected int f13536f;

    /* renamed from: g  reason: collision with root package name */
    protected int f13537g;

    /* renamed from: h  reason: collision with root package name */
    protected long f13538h;

    public CTEBase64InputStream(InputStream inputStream, Long l2) {
        this.f13531a = inputStream;
        this.f13535e = l2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
        org.bouncycastle.util.encoders.Base64.decode(r11.f13532b, 0, r2, r11.f13534d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005c, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0073, code lost:
        throw new java.io.IOException("Decode Base64 Content-Transfer-Encoding: " + r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int a() {
        int read;
        if (this.f13538h >= this.f13535e.longValue()) {
            return -1;
        }
        int i2 = 0;
        do {
            read = this.f13531a.read();
            if (read >= 33 || read == 13 || read == 10) {
                byte[] bArr = this.f13532b;
                if (i2 >= bArr.length) {
                    throw new IOException("Content Transfer Encoding, base64 line length > 1024");
                }
                bArr[i2] = (byte) read;
                this.f13538h++;
                i2++;
            } else if (read >= 0) {
                this.f13538h++;
            }
            if (read <= -1 || i2 >= this.f13532b.length || read == 10) {
                break;
            }
        } while (this.f13538h < this.f13535e.longValue());
        if (read == -1) {
            return -1;
        }
        return this.f13537g;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f13531a.close();
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.f13536f == this.f13537g) {
            this.f13536f = 0;
            this.f13537g = 0;
            int a2 = a();
            if (a2 == -1) {
                return a2;
            }
        }
        byte[] bArr = this.f13533c;
        int i2 = this.f13536f;
        this.f13536f = i2 + 1;
        return bArr[i2] & 255;
    }
}
