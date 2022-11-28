package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.base.GeneratorBase;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
/* loaded from: classes.dex */
public class UTF32Reader extends Reader {

    /* renamed from: a  reason: collision with root package name */
    protected final IOContext f5169a;

    /* renamed from: b  reason: collision with root package name */
    protected InputStream f5170b;

    /* renamed from: c  reason: collision with root package name */
    protected byte[] f5171c;

    /* renamed from: d  reason: collision with root package name */
    protected int f5172d;

    /* renamed from: e  reason: collision with root package name */
    protected int f5173e;

    /* renamed from: f  reason: collision with root package name */
    protected final boolean f5174f;

    /* renamed from: g  reason: collision with root package name */
    protected char f5175g = 0;

    /* renamed from: h  reason: collision with root package name */
    protected int f5176h;

    /* renamed from: i  reason: collision with root package name */
    protected int f5177i;

    /* renamed from: j  reason: collision with root package name */
    protected final boolean f5178j;

    /* renamed from: k  reason: collision with root package name */
    protected char[] f5179k;

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i2, int i3, boolean z) {
        this.f5169a = iOContext;
        this.f5170b = inputStream;
        this.f5171c = bArr;
        this.f5172d = i2;
        this.f5173e = i3;
        this.f5174f = z;
        this.f5178j = inputStream != null;
    }

    private void freeBuffers() {
        byte[] bArr = this.f5171c;
        if (bArr != null) {
            this.f5171c = null;
            this.f5169a.releaseReadIOBuffer(bArr);
        }
    }

    private boolean loadMore(int i2) {
        int read;
        this.f5177i += this.f5173e - i2;
        if (i2 > 0) {
            int i3 = this.f5172d;
            if (i3 > 0) {
                byte[] bArr = this.f5171c;
                System.arraycopy(bArr, i3, bArr, 0, i2);
                this.f5172d = 0;
            }
        } else {
            this.f5172d = 0;
            InputStream inputStream = this.f5170b;
            i2 = inputStream == null ? -1 : inputStream.read(this.f5171c);
            if (i2 < 1) {
                this.f5173e = 0;
                if (i2 < 0) {
                    if (this.f5178j) {
                        freeBuffers();
                    }
                    return false;
                }
                reportStrangeStream();
            }
        }
        this.f5173e = i2;
        while (true) {
            int i4 = this.f5173e;
            if (i4 >= 4) {
                return true;
            }
            InputStream inputStream2 = this.f5170b;
            if (inputStream2 == null) {
                read = -1;
            } else {
                byte[] bArr2 = this.f5171c;
                read = inputStream2.read(bArr2, i4, bArr2.length - i4);
            }
            if (read < 1) {
                if (read < 0) {
                    if (this.f5178j) {
                        freeBuffers();
                    }
                    reportUnexpectedEOF(this.f5173e, 4);
                }
                reportStrangeStream();
            }
            this.f5173e += read;
        }
    }

    private void reportBounds(char[] cArr, int i2, int i3) {
        throw new ArrayIndexOutOfBoundsException("read(buf," + i2 + "," + i3 + "), cbuf[" + cArr.length + "]");
    }

    private void reportInvalid(int i2, int i3, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid UTF-32 character 0x");
        sb.append(Integer.toHexString(i2));
        sb.append(str);
        sb.append(" at char #");
        sb.append(this.f5176h + i3);
        sb.append(", byte #");
        sb.append((this.f5177i + this.f5172d) - 1);
        sb.append(")");
        throw new CharConversionException(sb.toString());
    }

    private void reportStrangeStream() {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }

    private void reportUnexpectedEOF(int i2, int i3) {
        int i4 = this.f5176h;
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + i2 + ", needed " + i3 + ", at char #" + i4 + ", byte #" + (this.f5177i + i2) + ")");
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        InputStream inputStream = this.f5170b;
        if (inputStream != null) {
            this.f5170b = null;
            freeBuffers();
            inputStream.close();
        }
    }

    @Override // java.io.Reader
    public int read() {
        if (this.f5179k == null) {
            this.f5179k = new char[1];
        }
        if (read(this.f5179k, 0, 1) < 1) {
            return -1;
        }
        return this.f5179k[0];
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        if (this.f5171c == null) {
            return -1;
        }
        if (i3 < 1) {
            return i3;
        }
        if (i2 < 0 || i2 + i3 > cArr.length) {
            reportBounds(cArr, i2, i3);
        }
        int i8 = i3 + i2;
        char c2 = this.f5175g;
        if (c2 != 0) {
            i4 = i2 + 1;
            cArr[i2] = c2;
            this.f5175g = (char) 0;
        } else {
            int i9 = this.f5173e - this.f5172d;
            if (i9 < 4 && !loadMore(i9)) {
                if (i9 == 0) {
                    return -1;
                }
                reportUnexpectedEOF(this.f5173e - this.f5172d, 4);
            }
            i4 = i2;
        }
        int i10 = this.f5173e - 4;
        while (i4 < i8) {
            int i11 = this.f5172d;
            if (this.f5174f) {
                byte[] bArr = this.f5171c;
                i5 = (bArr[i11] << 8) | (bArr[i11 + 1] & 255);
                i6 = (bArr[i11 + 3] & 255) | ((bArr[i11 + 2] & 255) << 8);
            } else {
                byte[] bArr2 = this.f5171c;
                int i12 = (bArr2[i11] & 255) | ((bArr2[i11 + 1] & 255) << 8);
                i5 = (bArr2[i11 + 3] << 8) | (bArr2[i11 + 2] & 255);
                i6 = i12;
            }
            this.f5172d = i11 + 4;
            if (i5 != 0) {
                int i13 = 65535 & i5;
                int i14 = i6 | ((i13 - 1) << 16);
                if (i13 > 16) {
                    reportInvalid(i14, i4 - i2, String.format(" (above 0x%08x)", 1114111));
                }
                i7 = i4 + 1;
                cArr[i4] = (char) ((i14 >> 10) + GeneratorBase.SURR1_FIRST);
                int i15 = 56320 | (i14 & 1023);
                if (i7 >= i8) {
                    this.f5175g = (char) i14;
                    i4 = i7;
                    break;
                }
                i6 = i15;
                i4 = i7;
            }
            i7 = i4 + 1;
            cArr[i4] = (char) i6;
            if (this.f5172d > i10) {
                i4 = i7;
                break;
            }
            i4 = i7;
        }
        int i16 = i4 - i2;
        this.f5176h += i16;
        return i16;
    }
}
