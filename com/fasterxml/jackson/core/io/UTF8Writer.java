package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.base.GeneratorBase;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.bouncycastle.asn1.BERTags;
/* loaded from: classes.dex */
public final class UTF8Writer extends Writer {
    private final IOContext _context;
    private OutputStream _out;
    private byte[] _outBuffer;
    private final int _outBufferEnd;
    private int _outPtr;
    private int _surrogate;

    public UTF8Writer(IOContext iOContext, OutputStream outputStream) {
        this._context = iOContext;
        this._out = outputStream;
        byte[] allocWriteEncodingBuffer = iOContext.allocWriteEncodingBuffer();
        this._outBuffer = allocWriteEncodingBuffer;
        this._outBufferEnd = allocWriteEncodingBuffer.length - 4;
        this._outPtr = 0;
    }

    protected static void b(int i2) {
        throw new IOException(c(i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String c(int i2) {
        if (i2 > 1114111) {
            return "Illegal character point (0x" + Integer.toHexString(i2) + ") to output; max is 0x10FFFF as per RFC 4627";
        } else if (i2 < 55296) {
            return "Illegal character point (0x" + Integer.toHexString(i2) + ") to output";
        } else if (i2 <= 56319) {
            return "Unmatched first part of surrogate pair (0x" + Integer.toHexString(i2) + ")";
        } else {
            return "Unmatched second part of surrogate pair (0x" + Integer.toHexString(i2) + ")";
        }
    }

    protected int a(int i2) {
        int i3 = this._surrogate;
        this._surrogate = 0;
        if (i2 < 56320 || i2 > 57343) {
            throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(i3) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
        }
        return ((i3 - GeneratorBase.SURR1_FIRST) << 10) + 65536 + (i2 - 56320);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c2) {
        write(c2);
        return this;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        OutputStream outputStream = this._out;
        if (outputStream != null) {
            int i2 = this._outPtr;
            if (i2 > 0) {
                outputStream.write(this._outBuffer, 0, i2);
                this._outPtr = 0;
            }
            OutputStream outputStream2 = this._out;
            this._out = null;
            byte[] bArr = this._outBuffer;
            if (bArr != null) {
                this._outBuffer = null;
                this._context.releaseWriteEncodingBuffer(bArr);
            }
            outputStream2.close();
            int i3 = this._surrogate;
            this._surrogate = 0;
            if (i3 > 0) {
                b(i3);
            }
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        OutputStream outputStream = this._out;
        if (outputStream != null) {
            int i2 = this._outPtr;
            if (i2 > 0) {
                outputStream.write(this._outBuffer, 0, i2);
                this._outPtr = 0;
            }
            this._out.flush();
        }
    }

    @Override // java.io.Writer
    public void write(int i2) {
        int i3;
        if (this._surrogate > 0) {
            i2 = a(i2);
        } else if (i2 >= 55296 && i2 <= 57343) {
            if (i2 > 56319) {
                b(i2);
            }
            this._surrogate = i2;
            return;
        }
        int i4 = this._outPtr;
        if (i4 >= this._outBufferEnd) {
            this._out.write(this._outBuffer, 0, i4);
            this._outPtr = 0;
        }
        if (i2 < 128) {
            byte[] bArr = this._outBuffer;
            int i5 = this._outPtr;
            this._outPtr = i5 + 1;
            bArr[i5] = (byte) i2;
            return;
        }
        int i6 = this._outPtr;
        if (i2 < 2048) {
            byte[] bArr2 = this._outBuffer;
            int i7 = i6 + 1;
            bArr2[i6] = (byte) ((i2 >> 6) | 192);
            i3 = i7 + 1;
            bArr2[i7] = (byte) ((i2 & 63) | 128);
        } else if (i2 <= 65535) {
            byte[] bArr3 = this._outBuffer;
            int i8 = i6 + 1;
            bArr3[i6] = (byte) ((i2 >> 12) | BERTags.FLAGS);
            int i9 = i8 + 1;
            bArr3[i8] = (byte) (((i2 >> 6) & 63) | 128);
            bArr3[i9] = (byte) ((i2 & 63) | 128);
            i3 = i9 + 1;
        } else {
            if (i2 > 1114111) {
                b(i2);
            }
            byte[] bArr4 = this._outBuffer;
            int i10 = i6 + 1;
            bArr4[i6] = (byte) ((i2 >> 18) | 240);
            int i11 = i10 + 1;
            bArr4[i10] = (byte) (((i2 >> 12) & 63) | 128);
            int i12 = i11 + 1;
            bArr4[i11] = (byte) (((i2 >> 6) & 63) | 128);
            i3 = i12 + 1;
            bArr4[i12] = (byte) ((i2 & 63) | 128);
        }
        this._outPtr = i3;
    }

    @Override // java.io.Writer
    public void write(String str) {
        write(str, 0, str.length());
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0029, code lost:
        continue;
     */
    @Override // java.io.Writer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void write(String str, int i2, int i3) {
        if (i3 < 2) {
            if (i3 == 1) {
                write(str.charAt(i2));
                return;
            }
            return;
        }
        if (this._surrogate > 0) {
            i3--;
            write(a(str.charAt(i2)));
            i2++;
        }
        int i4 = this._outPtr;
        byte[] bArr = this._outBuffer;
        int i5 = this._outBufferEnd;
        int i6 = i3 + i2;
        while (i2 < i6) {
            if (i4 >= i5) {
                this._out.write(bArr, 0, i4);
                i4 = 0;
            }
            int i7 = i2 + 1;
            char charAt = str.charAt(i2);
            if (charAt < 128) {
                int i8 = i4 + 1;
                bArr[i4] = (byte) charAt;
                int i9 = i6 - i7;
                int i10 = i5 - i8;
                if (i9 > i10) {
                    i9 = i10;
                }
                int i11 = i9 + i7;
                while (true) {
                    i2 = i7;
                    i4 = i8;
                    if (i2 >= i11) {
                        break;
                    }
                    i7 = i2 + 1;
                    charAt = str.charAt(i2);
                    if (charAt >= 128) {
                        break;
                    }
                    i8 = i4 + 1;
                    bArr[i4] = (byte) charAt;
                }
            }
            if (charAt < 2048) {
                int i12 = i4 + 1;
                bArr[i4] = (byte) ((charAt >> 6) | 192);
                i4 = i12 + 1;
                bArr[i12] = (byte) ((charAt & '?') | 128);
                i2 = i7;
            } else if (charAt < 55296 || charAt > 57343) {
                int i13 = i4 + 1;
                bArr[i4] = (byte) ((charAt >> '\f') | BERTags.FLAGS);
                int i14 = i13 + 1;
                bArr[i13] = (byte) (((charAt >> 6) & 63) | 128);
                bArr[i14] = (byte) ((charAt & '?') | 128);
                i2 = i7;
                i4 = i14 + 1;
            } else {
                if (charAt > 56319) {
                    this._outPtr = i4;
                    b(charAt);
                }
                this._surrogate = charAt;
                if (i7 >= i6) {
                    break;
                }
                i2 = i7 + 1;
                int a2 = a(str.charAt(i7));
                if (a2 > 1114111) {
                    this._outPtr = i4;
                    b(a2);
                }
                int i15 = i4 + 1;
                bArr[i4] = (byte) ((a2 >> 18) | 240);
                int i16 = i15 + 1;
                bArr[i15] = (byte) (((a2 >> 12) & 63) | 128);
                int i17 = i16 + 1;
                bArr[i16] = (byte) (((a2 >> 6) & 63) | 128);
                i4 = i17 + 1;
                bArr[i17] = (byte) ((a2 & 63) | 128);
            }
        }
        this._outPtr = i4;
    }

    @Override // java.io.Writer
    public void write(char[] cArr) {
        write(cArr, 0, cArr.length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0025, code lost:
        continue;
     */
    @Override // java.io.Writer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void write(char[] cArr, int i2, int i3) {
        if (i3 < 2) {
            if (i3 == 1) {
                write(cArr[i2]);
                return;
            }
            return;
        }
        if (this._surrogate > 0) {
            i3--;
            write(a(cArr[i2]));
            i2++;
        }
        int i4 = this._outPtr;
        byte[] bArr = this._outBuffer;
        int i5 = this._outBufferEnd;
        int i6 = i3 + i2;
        while (i2 < i6) {
            if (i4 >= i5) {
                this._out.write(bArr, 0, i4);
                i4 = 0;
            }
            int i7 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 < 128) {
                int i8 = i4 + 1;
                bArr[i4] = (byte) c2;
                int i9 = i6 - i7;
                int i10 = i5 - i8;
                if (i9 > i10) {
                    i9 = i10;
                }
                int i11 = i9 + i7;
                while (true) {
                    i2 = i7;
                    i4 = i8;
                    if (i2 >= i11) {
                        break;
                    }
                    i7 = i2 + 1;
                    c2 = cArr[i2];
                    if (c2 >= 128) {
                        break;
                    }
                    i8 = i4 + 1;
                    bArr[i4] = (byte) c2;
                }
            }
            if (c2 < 2048) {
                int i12 = i4 + 1;
                bArr[i4] = (byte) ((c2 >> 6) | 192);
                i4 = i12 + 1;
                bArr[i12] = (byte) ((c2 & '?') | 128);
                i2 = i7;
            } else if (c2 < 55296 || c2 > 57343) {
                int i13 = i4 + 1;
                bArr[i4] = (byte) ((c2 >> '\f') | BERTags.FLAGS);
                int i14 = i13 + 1;
                bArr[i13] = (byte) (((c2 >> 6) & 63) | 128);
                bArr[i14] = (byte) ((c2 & '?') | 128);
                i2 = i7;
                i4 = i14 + 1;
            } else {
                if (c2 > 56319) {
                    this._outPtr = i4;
                    b(c2);
                }
                this._surrogate = c2;
                if (i7 >= i6) {
                    break;
                }
                i2 = i7 + 1;
                int a2 = a(cArr[i7]);
                if (a2 > 1114111) {
                    this._outPtr = i4;
                    b(a2);
                }
                int i15 = i4 + 1;
                bArr[i4] = (byte) ((a2 >> 18) | 240);
                int i16 = i15 + 1;
                bArr[i15] = (byte) (((a2 >> 12) & 63) | 128);
                int i17 = i16 + 1;
                bArr[i16] = (byte) (((a2 >> 6) & 63) | 128);
                i4 = i17 + 1;
                bArr[i17] = (byte) ((a2 & 63) | 128);
            }
        }
        this._outPtr = i4;
    }
}
