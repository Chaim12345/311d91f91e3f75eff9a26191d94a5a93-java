package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberOutput;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
public class WriterBasedJsonGenerator extends JsonGeneratorImpl {
    protected static final char[] x = CharTypes.copyHexChars();

    /* renamed from: o  reason: collision with root package name */
    protected final Writer f5210o;

    /* renamed from: p  reason: collision with root package name */
    protected char f5211p;

    /* renamed from: q  reason: collision with root package name */
    protected char[] f5212q;

    /* renamed from: r  reason: collision with root package name */
    protected int f5213r;

    /* renamed from: s  reason: collision with root package name */
    protected int f5214s;

    /* renamed from: t  reason: collision with root package name */
    protected int f5215t;
    protected char[] u;
    protected SerializableString v;
    protected char[] w;

    @Deprecated
    public WriterBasedJsonGenerator(IOContext iOContext, int i2, ObjectCodec objectCodec, Writer writer) {
        this(iOContext, i2, objectCodec, writer, '\"');
    }

    public WriterBasedJsonGenerator(IOContext iOContext, int i2, ObjectCodec objectCodec, Writer writer, char c2) {
        super(iOContext, i2, objectCodec);
        this.f5210o = writer;
        char[] allocConcatBuffer = iOContext.allocConcatBuffer();
        this.f5212q = allocConcatBuffer;
        this.f5215t = allocConcatBuffer.length;
        this.f5211p = c2;
        if (c2 != '\"') {
            this.f5186i = CharTypes.get7BitOutputEscapes(c2);
        }
    }

    private char[] _allocateCopyBuffer() {
        if (this.w == null) {
            this.w = this.f5185h.allocNameCopyBuffer(2000);
        }
        return this.w;
    }

    private char[] _allocateEntityBuffer() {
        char[] cArr = {'\\', 0, '\\', AbstractJsonLexerKt.UNICODE_ESC, '0', '0', 0, 0, '\\', AbstractJsonLexerKt.UNICODE_ESC};
        this.u = cArr;
        return cArr;
    }

    private void _appendCharacterEscape(char c2, int i2) {
        String value;
        int i3;
        if (i2 >= 0) {
            if (this.f5214s + 2 > this.f5215t) {
                n();
            }
            char[] cArr = this.f5212q;
            int i4 = this.f5214s;
            int i5 = i4 + 1;
            this.f5214s = i5;
            cArr[i4] = '\\';
            this.f5214s = i5 + 1;
            cArr[i5] = (char) i2;
        } else if (i2 == -2) {
            SerializableString serializableString = this.v;
            if (serializableString == null) {
                value = this.f5188k.getEscapeSequence(c2).getValue();
            } else {
                value = serializableString.getValue();
                this.v = null;
            }
            int length = value.length();
            if (this.f5214s + length > this.f5215t) {
                n();
                if (length > this.f5215t) {
                    this.f5210o.write(value);
                    return;
                }
            }
            value.getChars(0, length, this.f5212q, this.f5214s);
            this.f5214s += length;
        } else {
            if (this.f5214s + 5 >= this.f5215t) {
                n();
            }
            int i6 = this.f5214s;
            char[] cArr2 = this.f5212q;
            int i7 = i6 + 1;
            cArr2[i6] = '\\';
            int i8 = i7 + 1;
            cArr2[i7] = AbstractJsonLexerKt.UNICODE_ESC;
            if (c2 > 255) {
                int i9 = 255 & (c2 >> '\b');
                int i10 = i8 + 1;
                char[] cArr3 = x;
                cArr2[i8] = cArr3[i9 >> 4];
                i3 = i10 + 1;
                cArr2[i10] = cArr3[i9 & 15];
                c2 = (char) (c2 & 255);
            } else {
                int i11 = i8 + 1;
                cArr2[i8] = '0';
                i3 = i11 + 1;
                cArr2[i11] = '0';
            }
            int i12 = i3 + 1;
            char[] cArr4 = x;
            cArr2[i3] = cArr4[c2 >> 4];
            cArr2[i12] = cArr4[c2 & 15];
            this.f5214s = i12 + 1;
        }
    }

    private int _prependOrWriteCharacterEscape(char[] cArr, int i2, int i3, char c2, int i4) {
        String value;
        int i5;
        if (i4 >= 0) {
            if (i2 > 1 && i2 < i3) {
                int i6 = i2 - 2;
                cArr[i6] = '\\';
                cArr[i6 + 1] = (char) i4;
                return i6;
            }
            char[] cArr2 = this.u;
            if (cArr2 == null) {
                cArr2 = _allocateEntityBuffer();
            }
            cArr2[1] = (char) i4;
            this.f5210o.write(cArr2, 0, 2);
            return i2;
        } else if (i4 == -2) {
            SerializableString serializableString = this.v;
            if (serializableString == null) {
                value = this.f5188k.getEscapeSequence(c2).getValue();
            } else {
                value = serializableString.getValue();
                this.v = null;
            }
            int length = value.length();
            if (i2 < length || i2 >= i3) {
                this.f5210o.write(value);
                return i2;
            }
            int i7 = i2 - length;
            value.getChars(0, length, cArr, i7);
            return i7;
        } else if (i2 <= 5 || i2 >= i3) {
            char[] cArr3 = this.u;
            if (cArr3 == null) {
                cArr3 = _allocateEntityBuffer();
            }
            this.f5213r = this.f5214s;
            if (c2 <= 255) {
                char[] cArr4 = x;
                cArr3[6] = cArr4[c2 >> 4];
                cArr3[7] = cArr4[c2 & 15];
                this.f5210o.write(cArr3, 2, 6);
                return i2;
            }
            int i8 = (c2 >> '\b') & 255;
            int i9 = c2 & 255;
            char[] cArr5 = x;
            cArr3[10] = cArr5[i8 >> 4];
            cArr3[11] = cArr5[i8 & 15];
            cArr3[12] = cArr5[i9 >> 4];
            cArr3[13] = cArr5[i9 & 15];
            this.f5210o.write(cArr3, 8, 6);
            return i2;
        } else {
            int i10 = i2 - 6;
            int i11 = i10 + 1;
            cArr[i10] = '\\';
            int i12 = i11 + 1;
            cArr[i11] = AbstractJsonLexerKt.UNICODE_ESC;
            if (c2 > 255) {
                int i13 = (c2 >> '\b') & 255;
                int i14 = i12 + 1;
                char[] cArr6 = x;
                cArr[i12] = cArr6[i13 >> 4];
                i5 = i14 + 1;
                cArr[i14] = cArr6[i13 & 15];
                c2 = (char) (c2 & 255);
            } else {
                int i15 = i12 + 1;
                cArr[i12] = '0';
                i5 = i15 + 1;
                cArr[i15] = '0';
            }
            int i16 = i5 + 1;
            char[] cArr7 = x;
            cArr[i5] = cArr7[c2 >> 4];
            cArr[i16] = cArr7[c2 & 15];
            return i16 - 5;
        }
    }

    private void _prependOrWriteCharacterEscape(char c2, int i2) {
        String value;
        int i3;
        if (i2 >= 0) {
            int i4 = this.f5214s;
            if (i4 >= 2) {
                int i5 = i4 - 2;
                this.f5213r = i5;
                char[] cArr = this.f5212q;
                cArr[i5] = '\\';
                cArr[i5 + 1] = (char) i2;
                return;
            }
            char[] cArr2 = this.u;
            if (cArr2 == null) {
                cArr2 = _allocateEntityBuffer();
            }
            this.f5213r = this.f5214s;
            cArr2[1] = (char) i2;
            this.f5210o.write(cArr2, 0, 2);
        } else if (i2 == -2) {
            SerializableString serializableString = this.v;
            if (serializableString == null) {
                value = this.f5188k.getEscapeSequence(c2).getValue();
            } else {
                value = serializableString.getValue();
                this.v = null;
            }
            int length = value.length();
            int i6 = this.f5214s;
            if (i6 < length) {
                this.f5213r = i6;
                this.f5210o.write(value);
                return;
            }
            int i7 = i6 - length;
            this.f5213r = i7;
            value.getChars(0, length, this.f5212q, i7);
        } else {
            int i8 = this.f5214s;
            if (i8 < 6) {
                char[] cArr3 = this.u;
                if (cArr3 == null) {
                    cArr3 = _allocateEntityBuffer();
                }
                this.f5213r = this.f5214s;
                if (c2 <= 255) {
                    char[] cArr4 = x;
                    cArr3[6] = cArr4[c2 >> 4];
                    cArr3[7] = cArr4[c2 & 15];
                    this.f5210o.write(cArr3, 2, 6);
                    return;
                }
                int i9 = (c2 >> '\b') & 255;
                int i10 = c2 & 255;
                char[] cArr5 = x;
                cArr3[10] = cArr5[i9 >> 4];
                cArr3[11] = cArr5[i9 & 15];
                cArr3[12] = cArr5[i10 >> 4];
                cArr3[13] = cArr5[i10 & 15];
                this.f5210o.write(cArr3, 8, 6);
                return;
            }
            char[] cArr6 = this.f5212q;
            int i11 = i8 - 6;
            this.f5213r = i11;
            cArr6[i11] = '\\';
            int i12 = i11 + 1;
            cArr6[i12] = AbstractJsonLexerKt.UNICODE_ESC;
            if (c2 > 255) {
                int i13 = (c2 >> '\b') & 255;
                int i14 = i12 + 1;
                char[] cArr7 = x;
                cArr6[i14] = cArr7[i13 >> 4];
                i3 = i14 + 1;
                cArr6[i3] = cArr7[i13 & 15];
                c2 = (char) (c2 & 255);
            } else {
                int i15 = i12 + 1;
                cArr6[i15] = '0';
                i3 = i15 + 1;
                cArr6[i3] = '0';
            }
            int i16 = i3 + 1;
            char[] cArr8 = x;
            cArr6[i16] = cArr8[c2 >> 4];
            cArr6[i16 + 1] = cArr8[c2 & 15];
        }
    }

    private int _readMore(InputStream inputStream, byte[] bArr, int i2, int i3, int i4) {
        int i5 = 0;
        while (i2 < i3) {
            bArr[i5] = bArr[i2];
            i5++;
            i2++;
        }
        int min = Math.min(i4, bArr.length);
        do {
            int i6 = min - i5;
            if (i6 == 0) {
                break;
            }
            int read = inputStream.read(bArr, i5, i6);
            if (read < 0) {
                return i5;
            }
            i5 += read;
        } while (i5 < 3);
        return i5;
    }

    private final void _writeFieldNameTail(SerializableString serializableString) {
        char[] asQuotedChars = serializableString.asQuotedChars();
        writeRaw(asQuotedChars, 0, asQuotedChars.length);
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        this.f5214s = i2 + 1;
        cArr[i2] = this.f5211p;
    }

    private void _writeLongString(String str) {
        n();
        int length = str.length();
        int i2 = 0;
        while (true) {
            int i3 = this.f5215t;
            if (i2 + i3 > length) {
                i3 = length - i2;
            }
            int i4 = i2 + i3;
            str.getChars(i2, i4, this.f5212q, 0);
            if (this.f5188k != null) {
                _writeSegmentCustom(i3);
            } else {
                int i5 = this.f5187j;
                if (i5 != 0) {
                    _writeSegmentASCII(i3, i5);
                } else {
                    _writeSegment(i3);
                }
            }
            if (i4 >= length) {
                return;
            }
            i2 = i4;
        }
    }

    private final void _writeNull() {
        if (this.f5214s + 4 >= this.f5215t) {
            n();
        }
        int i2 = this.f5214s;
        char[] cArr = this.f5212q;
        cArr[i2] = 'n';
        int i3 = i2 + 1;
        cArr[i3] = AbstractJsonLexerKt.UNICODE_ESC;
        int i4 = i3 + 1;
        cArr[i4] = 'l';
        int i5 = i4 + 1;
        cArr[i5] = 'l';
        this.f5214s = i5 + 1;
    }

    private void _writeQuotedInt(int i2) {
        if (this.f5214s + 13 >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i3 = this.f5214s;
        int i4 = i3 + 1;
        this.f5214s = i4;
        cArr[i3] = this.f5211p;
        int outputInt = NumberOutput.outputInt(i2, cArr, i4);
        this.f5214s = outputInt;
        char[] cArr2 = this.f5212q;
        this.f5214s = outputInt + 1;
        cArr2[outputInt] = this.f5211p;
    }

    private void _writeQuotedLong(long j2) {
        if (this.f5214s + 23 >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        int i3 = i2 + 1;
        this.f5214s = i3;
        cArr[i2] = this.f5211p;
        int outputLong = NumberOutput.outputLong(j2, cArr, i3);
        this.f5214s = outputLong;
        char[] cArr2 = this.f5212q;
        this.f5214s = outputLong + 1;
        cArr2[outputLong] = this.f5211p;
    }

    private void _writeQuotedRaw(String str) {
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        this.f5214s = i2 + 1;
        cArr[i2] = this.f5211p;
        writeRaw(str);
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr2 = this.f5212q;
        int i3 = this.f5214s;
        this.f5214s = i3 + 1;
        cArr2[i3] = this.f5211p;
    }

    private void _writeQuotedRaw(char[] cArr, int i2, int i3) {
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr2 = this.f5212q;
        int i4 = this.f5214s;
        this.f5214s = i4 + 1;
        cArr2[i4] = this.f5211p;
        writeRaw(cArr, i2, i3);
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr3 = this.f5212q;
        int i5 = this.f5214s;
        this.f5214s = i5 + 1;
        cArr3[i5] = this.f5211p;
    }

    private void _writeQuotedShort(short s2) {
        if (this.f5214s + 8 >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        int i3 = i2 + 1;
        this.f5214s = i3;
        cArr[i2] = this.f5211p;
        int outputInt = NumberOutput.outputInt(s2, cArr, i3);
        this.f5214s = outputInt;
        char[] cArr2 = this.f5212q;
        this.f5214s = outputInt + 1;
        cArr2[outputInt] = this.f5211p;
    }

    private void _writeSegment(int i2) {
        char[] cArr;
        char c2;
        int[] iArr = this.f5186i;
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            do {
                cArr = this.f5212q;
                c2 = cArr[i3];
                if (c2 < length && iArr[c2] != 0) {
                    break;
                }
                i3++;
            } while (i3 < i2);
            int i5 = i3 - i4;
            if (i5 > 0) {
                this.f5210o.write(cArr, i4, i5);
                if (i3 >= i2) {
                    return;
                }
            }
            i3++;
            i4 = _prependOrWriteCharacterEscape(this.f5212q, i3, i2, c2, iArr[c2]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0021 A[EDGE_INSN: B:26:0x0021->B:13:0x0021 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void _writeSegmentASCII(int i2, int i3) {
        char[] cArr;
        char c2;
        int[] iArr = this.f5186i;
        int min = Math.min(iArr.length, i3 + 1);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i2) {
            while (true) {
                cArr = this.f5212q;
                c2 = cArr[i4];
                if (c2 < min) {
                    i6 = iArr[c2];
                    if (i6 != 0) {
                        break;
                    }
                    i4++;
                    if (i4 >= i2) {
                        break;
                    }
                } else {
                    if (c2 > i3) {
                        i6 = -1;
                        break;
                    }
                    i4++;
                    if (i4 >= i2) {
                    }
                }
            }
            int i7 = i4 - i5;
            if (i7 > 0) {
                this.f5210o.write(cArr, i5, i7);
                if (i4 >= i2) {
                    return;
                }
            }
            i4++;
            i5 = _prependOrWriteCharacterEscape(this.f5212q, i4, i2, c2, i6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0035 A[EDGE_INSN: B:31:0x0035->B:19:0x0035 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void _writeSegmentCustom(int i2) {
        char c2;
        int[] iArr = this.f5186i;
        int i3 = this.f5187j;
        if (i3 < 1) {
            i3 = 65535;
        }
        int min = Math.min(iArr.length, i3 + 1);
        CharacterEscapes characterEscapes = this.f5188k;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i2) {
            while (true) {
                c2 = this.f5212q[i4];
                if (c2 < min) {
                    i6 = iArr[c2];
                    if (i6 != 0) {
                        break;
                    }
                    i4++;
                    if (i4 >= i2) {
                        break;
                    }
                } else if (c2 > i3) {
                    i6 = -1;
                    break;
                } else {
                    SerializableString escapeSequence = characterEscapes.getEscapeSequence(c2);
                    this.v = escapeSequence;
                    if (escapeSequence != null) {
                        i6 = -2;
                        break;
                    }
                    i4++;
                    if (i4 >= i2) {
                    }
                }
            }
            int i7 = i4 - i5;
            if (i7 > 0) {
                this.f5210o.write(this.f5212q, i5, i7);
                if (i4 >= i2) {
                    return;
                }
            }
            i4++;
            i5 = _prependOrWriteCharacterEscape(this.f5212q, i4, i2, c2, i6);
        }
    }

    private void _writeString(String str) {
        int length = str.length();
        int i2 = this.f5215t;
        if (length > i2) {
            _writeLongString(str);
            return;
        }
        if (this.f5214s + length > i2) {
            n();
        }
        str.getChars(0, length, this.f5212q, this.f5214s);
        if (this.f5188k != null) {
            _writeStringCustom(length);
            return;
        }
        int i3 = this.f5187j;
        if (i3 != 0) {
            _writeStringASCII(length, i3);
        } else {
            _writeString2(length);
        }
    }

    private void _writeString(char[] cArr, int i2, int i3) {
        if (this.f5188k != null) {
            _writeStringCustom(cArr, i2, i3);
            return;
        }
        int i4 = this.f5187j;
        if (i4 != 0) {
            _writeStringASCII(cArr, i2, i3, i4);
            return;
        }
        int i5 = i3 + i2;
        int[] iArr = this.f5186i;
        int length = iArr.length;
        while (i2 < i5) {
            int i6 = i2;
            do {
                char c2 = cArr[i6];
                if (c2 < length && iArr[c2] != 0) {
                    break;
                }
                i6++;
            } while (i6 < i5);
            int i7 = i6 - i2;
            if (i7 < 32) {
                if (this.f5214s + i7 > this.f5215t) {
                    n();
                }
                if (i7 > 0) {
                    System.arraycopy(cArr, i2, this.f5212q, this.f5214s, i7);
                    this.f5214s += i7;
                }
            } else {
                n();
                this.f5210o.write(cArr, i2, i7);
            }
            if (i6 >= i5) {
                return;
            }
            i2 = i6 + 1;
            char c3 = cArr[i6];
            _appendCharacterEscape(c3, iArr[c3]);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r3 <= 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001b, code lost:
        r6.f5210o.write(r2, r4, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
        r2 = r6.f5212q;
        r3 = r6.f5214s;
        r6.f5214s = r3 + 1;
        r2 = r2[r3];
        _prependOrWriteCharacterEscape(r2, r7[r2]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0016, code lost:
        r4 = r6.f5213r;
        r3 = r3 - r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void _writeString2(int i2) {
        int i3 = this.f5214s + i2;
        int[] iArr = this.f5186i;
        int length = iArr.length;
        while (this.f5214s < i3) {
            while (true) {
                char[] cArr = this.f5212q;
                int i4 = this.f5214s;
                char c2 = cArr[i4];
                if (c2 < length && iArr[c2] != 0) {
                    break;
                }
                int i5 = i4 + 1;
                this.f5214s = i5;
                if (i5 >= i3) {
                    return;
                }
            }
        }
    }

    private void _writeString2(SerializableString serializableString) {
        char[] asQuotedChars = serializableString.asQuotedChars();
        int length = asQuotedChars.length;
        if (length < 32) {
            if (length > this.f5215t - this.f5214s) {
                n();
            }
            System.arraycopy(asQuotedChars, 0, this.f5212q, this.f5214s, length);
            this.f5214s += length;
        } else {
            n();
            this.f5210o.write(asQuotedChars, 0, length);
        }
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        this.f5214s = i2 + 1;
        cArr[i2] = this.f5211p;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void _writeStringASCII(int i2, int i3) {
        char[] cArr;
        int i4;
        char c2;
        int i5;
        int i6;
        int i7 = this.f5214s + i2;
        int[] iArr = this.f5186i;
        int min = Math.min(iArr.length, i3 + 1);
        while (this.f5214s < i7) {
            while (true) {
                cArr = this.f5212q;
                i4 = this.f5214s;
                c2 = cArr[i4];
                if (c2 < min) {
                    i5 = iArr[c2];
                    if (i5 != 0) {
                        break;
                    }
                    i6 = i4 + 1;
                    this.f5214s = i6;
                    if (i6 >= i7) {
                        return;
                    }
                } else {
                    if (c2 > i3) {
                        i5 = -1;
                        break;
                    }
                    i6 = i4 + 1;
                    this.f5214s = i6;
                    if (i6 >= i7) {
                    }
                }
            }
            int i8 = this.f5213r;
            int i9 = i4 - i8;
            if (i9 > 0) {
                this.f5210o.write(cArr, i8, i9);
            }
            this.f5214s++;
            _prependOrWriteCharacterEscape(c2, i5);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x001f A[EDGE_INSN: B:30:0x001f->B:14:0x001f ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void _writeStringASCII(char[] cArr, int i2, int i3, int i4) {
        char c2;
        int i5 = i3 + i2;
        int[] iArr = this.f5186i;
        int min = Math.min(iArr.length, i4 + 1);
        int i6 = 0;
        while (i2 < i5) {
            int i7 = i2;
            while (true) {
                c2 = cArr[i7];
                if (c2 < min) {
                    i6 = iArr[c2];
                    if (i6 != 0) {
                        break;
                    }
                    i7++;
                    if (i7 >= i5) {
                        break;
                    }
                } else {
                    if (c2 > i4) {
                        i6 = -1;
                        break;
                    }
                    i7++;
                    if (i7 >= i5) {
                    }
                }
            }
            int i8 = i7 - i2;
            if (i8 < 32) {
                if (this.f5214s + i8 > this.f5215t) {
                    n();
                }
                if (i8 > 0) {
                    System.arraycopy(cArr, i2, this.f5212q, this.f5214s, i8);
                    this.f5214s += i8;
                }
            } else {
                n();
                this.f5210o.write(cArr, i2, i8);
            }
            if (i7 >= i5) {
                return;
            }
            i2 = i7 + 1;
            _appendCharacterEscape(c2, i6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0052 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void _writeStringCustom(int i2) {
        char c2;
        int i3;
        int i4;
        int i5 = this.f5214s + i2;
        int[] iArr = this.f5186i;
        int i6 = this.f5187j;
        if (i6 < 1) {
            i6 = 65535;
        }
        int min = Math.min(iArr.length, i6 + 1);
        CharacterEscapes characterEscapes = this.f5188k;
        while (this.f5214s < i5) {
            while (true) {
                c2 = this.f5212q[this.f5214s];
                if (c2 < min) {
                    i3 = iArr[c2];
                    if (i3 != 0) {
                        break;
                    }
                    i4 = this.f5214s + 1;
                    this.f5214s = i4;
                    if (i4 >= i5) {
                        return;
                    }
                } else if (c2 > i6) {
                    i3 = -1;
                    break;
                } else {
                    SerializableString escapeSequence = characterEscapes.getEscapeSequence(c2);
                    this.v = escapeSequence;
                    if (escapeSequence != null) {
                        i3 = -2;
                        break;
                    }
                    i4 = this.f5214s + 1;
                    this.f5214s = i4;
                    if (i4 >= i5) {
                    }
                }
            }
            int i7 = this.f5214s;
            int i8 = this.f5213r;
            int i9 = i7 - i8;
            if (i9 > 0) {
                this.f5210o.write(this.f5212q, i8, i9);
            }
            this.f5214s++;
            _prependOrWriteCharacterEscape(c2, i3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0033 A[EDGE_INSN: B:34:0x0033->B:20:0x0033 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void _writeStringCustom(char[] cArr, int i2, int i3) {
        char c2;
        int i4 = i3 + i2;
        int[] iArr = this.f5186i;
        int i5 = this.f5187j;
        if (i5 < 1) {
            i5 = 65535;
        }
        int min = Math.min(iArr.length, i5 + 1);
        CharacterEscapes characterEscapes = this.f5188k;
        int i6 = 0;
        while (i2 < i4) {
            int i7 = i2;
            while (true) {
                c2 = cArr[i7];
                if (c2 < min) {
                    i6 = iArr[c2];
                    if (i6 != 0) {
                        break;
                    }
                    i7++;
                    if (i7 >= i4) {
                        break;
                    }
                } else if (c2 > i5) {
                    i6 = -1;
                    break;
                } else {
                    SerializableString escapeSequence = characterEscapes.getEscapeSequence(c2);
                    this.v = escapeSequence;
                    if (escapeSequence != null) {
                        i6 = -2;
                        break;
                    }
                    i7++;
                    if (i7 >= i4) {
                    }
                }
            }
            int i8 = i7 - i2;
            if (i8 < 32) {
                if (this.f5214s + i8 > this.f5215t) {
                    n();
                }
                if (i8 > 0) {
                    System.arraycopy(cArr, i2, this.f5212q, this.f5214s, i8);
                    this.f5214s += i8;
                }
            } else {
                n();
                this.f5210o.write(cArr, i2, i8);
            }
            if (i7 >= i4) {
                return;
            }
            i2 = i7 + 1;
            _appendCharacterEscape(c2, i6);
        }
    }

    private void writeRawLong(String str) {
        int i2 = this.f5215t;
        int i3 = this.f5214s;
        int i4 = i2 - i3;
        str.getChars(0, i4, this.f5212q, i3);
        this.f5214s += i4;
        n();
        int length = str.length() - i4;
        while (true) {
            int i5 = this.f5215t;
            if (length <= i5) {
                str.getChars(i4, i4 + length, this.f5212q, 0);
                this.f5213r = 0;
                this.f5214s = length;
                return;
            }
            int i6 = i4 + i5;
            str.getChars(i4, i6, this.f5212q, 0);
            this.f5213r = 0;
            this.f5214s = i5;
            n();
            length -= i5;
            i4 = i6;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public boolean canWriteFormattedNumbers() {
        return true;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        if (this.f5212q != null && isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                JsonStreamContext outputContext = getOutputContext();
                if (!outputContext.inArray()) {
                    if (!outputContext.inObject()) {
                        break;
                    }
                    writeEndObject();
                } else {
                    writeEndArray();
                }
            }
        }
        n();
        this.f5213r = 0;
        this.f5214s = 0;
        if (this.f5210o != null) {
            if (this.f5185h.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                this.f5210o.close();
            } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                this.f5210o.flush();
            }
        }
        o();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public void flush() {
        n();
        if (this.f5210o == null || !isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            return;
        }
        this.f5210o.flush();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int getOutputBuffered() {
        return Math.max(0, this.f5214s - this.f5213r);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public Object getOutputTarget() {
        return this.f5210o;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected final void k(String str) {
        char c2;
        int writeValue = this.f5086e.writeValue();
        if (this.f5041a != null) {
            m(str, writeValue);
            return;
        }
        if (writeValue == 1) {
            c2 = AbstractJsonLexerKt.COMMA;
        } else if (writeValue != 2) {
            if (writeValue != 3) {
                if (writeValue != 5) {
                    return;
                }
                l(str);
                return;
            }
            SerializableString serializableString = this.f5189l;
            if (serializableString != null) {
                writeRaw(serializableString.getValue());
                return;
            }
            return;
        } else {
            c2 = AbstractJsonLexerKt.COLON;
        }
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        this.f5214s = i2 + 1;
        cArr[i2] = c2;
    }

    protected void n() {
        int i2 = this.f5214s;
        int i3 = this.f5213r;
        int i4 = i2 - i3;
        if (i4 > 0) {
            this.f5213r = 0;
            this.f5214s = 0;
            this.f5210o.write(this.f5212q, i3, i4);
        }
    }

    protected void o() {
        char[] cArr = this.f5212q;
        if (cArr != null) {
            this.f5212q = null;
            this.f5185h.releaseConcatBuffer(cArr);
        }
        char[] cArr2 = this.w;
        if (cArr2 != null) {
            this.w = null;
            this.f5185h.releaseNameCopyBuffer(cArr2);
        }
    }

    protected final int p(Base64Variant base64Variant, InputStream inputStream, byte[] bArr) {
        int i2 = this.f5215t - 6;
        int i3 = 2;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i4 = -3;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            if (i5 > i4) {
                i6 = _readMore(inputStream, bArr, i5, i6, bArr.length);
                if (i6 < 3) {
                    break;
                }
                i4 = i6 - 3;
                i5 = 0;
            }
            if (this.f5214s > i2) {
                n();
            }
            int i8 = i5 + 1;
            int i9 = i8 + 1;
            i5 = i9 + 1;
            i7 += 3;
            int encodeBase64Chunk = base64Variant.encodeBase64Chunk((((bArr[i8] & 255) | (bArr[i5] << 8)) << 8) | (bArr[i9] & 255), this.f5212q, this.f5214s);
            this.f5214s = encodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                char[] cArr = this.f5212q;
                int i10 = encodeBase64Chunk + 1;
                this.f5214s = i10;
                cArr[encodeBase64Chunk] = '\\';
                this.f5214s = i10 + 1;
                cArr[i10] = 'n';
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (i6 > 0) {
            if (this.f5214s > i2) {
                n();
            }
            int i11 = bArr[0] << 16;
            if (1 < i6) {
                i11 |= (bArr[1] & 255) << 8;
            } else {
                i3 = 1;
            }
            int i12 = i7 + i3;
            this.f5214s = base64Variant.encodeBase64Partial(i11, i3, this.f5212q, this.f5214s);
            return i12;
        }
        return i7;
    }

    protected final int q(Base64Variant base64Variant, InputStream inputStream, byte[] bArr, int i2) {
        int _readMore;
        int i3 = this.f5215t - 6;
        int i4 = 2;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i5 = -3;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            if (i2 <= 2) {
                break;
            }
            if (i6 > i5) {
                i7 = _readMore(inputStream, bArr, i6, i7, i2);
                if (i7 < 3) {
                    i6 = 0;
                    break;
                }
                i5 = i7 - 3;
                i6 = 0;
            }
            if (this.f5214s > i3) {
                n();
            }
            int i8 = i6 + 1;
            int i9 = i8 + 1;
            i6 = i9 + 1;
            i2 -= 3;
            int encodeBase64Chunk = base64Variant.encodeBase64Chunk((((bArr[i8] & 255) | (bArr[i6] << 8)) << 8) | (bArr[i9] & 255), this.f5212q, this.f5214s);
            this.f5214s = encodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                char[] cArr = this.f5212q;
                int i10 = encodeBase64Chunk + 1;
                this.f5214s = i10;
                cArr[encodeBase64Chunk] = '\\';
                this.f5214s = i10 + 1;
                cArr[i10] = 'n';
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (i2 <= 0 || (_readMore = _readMore(inputStream, bArr, i6, i7, i2)) <= 0) {
            return i2;
        }
        if (this.f5214s > i3) {
            n();
        }
        int i11 = bArr[0] << 16;
        if (1 < _readMore) {
            i11 |= (bArr[1] & 255) << 8;
        } else {
            i4 = 1;
        }
        this.f5214s = base64Variant.encodeBase64Partial(i11, i4, this.f5212q, this.f5214s);
        return i2 - i4;
    }

    protected final void r(Base64Variant base64Variant, byte[] bArr, int i2, int i3) {
        int i4 = i3 - 3;
        int i5 = this.f5215t - 6;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        while (i2 <= i4) {
            if (this.f5214s > i5) {
                n();
            }
            int i6 = i2 + 1;
            int i7 = i6 + 1;
            int i8 = i7 + 1;
            int encodeBase64Chunk = base64Variant.encodeBase64Chunk((((bArr[i2] << 8) | (bArr[i6] & 255)) << 8) | (bArr[i7] & 255), this.f5212q, this.f5214s);
            this.f5214s = encodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                char[] cArr = this.f5212q;
                int i9 = encodeBase64Chunk + 1;
                this.f5214s = i9;
                cArr[encodeBase64Chunk] = '\\';
                this.f5214s = i9 + 1;
                cArr[i9] = 'n';
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
            i2 = i8;
        }
        int i10 = i3 - i2;
        if (i10 > 0) {
            if (this.f5214s > i5) {
                n();
            }
            int i11 = i2 + 1;
            int i12 = bArr[i2] << 16;
            if (i10 == 2) {
                i12 |= (bArr[i11] & 255) << 8;
            }
            this.f5214s = base64Variant.encodeBase64Partial(i12, i10, this.f5212q, this.f5214s);
        }
    }

    protected final void s(SerializableString serializableString, boolean z) {
        if (this.f5041a != null) {
            u(serializableString, z);
            return;
        }
        if (this.f5214s + 1 >= this.f5215t) {
            n();
        }
        if (z) {
            char[] cArr = this.f5212q;
            int i2 = this.f5214s;
            this.f5214s = i2 + 1;
            cArr[i2] = AbstractJsonLexerKt.COMMA;
        }
        if (this.f5190m) {
            char[] asQuotedChars = serializableString.asQuotedChars();
            writeRaw(asQuotedChars, 0, asQuotedChars.length);
            return;
        }
        char[] cArr2 = this.f5212q;
        int i3 = this.f5214s;
        int i4 = i3 + 1;
        this.f5214s = i4;
        cArr2[i3] = this.f5211p;
        int appendQuoted = serializableString.appendQuoted(cArr2, i4);
        if (appendQuoted < 0) {
            _writeFieldNameTail(serializableString);
            return;
        }
        int i5 = this.f5214s + appendQuoted;
        this.f5214s = i5;
        if (i5 >= this.f5215t) {
            n();
        }
        char[] cArr3 = this.f5212q;
        int i6 = this.f5214s;
        this.f5214s = i6 + 1;
        cArr3[i6] = this.f5211p;
    }

    protected final void t(String str, boolean z) {
        if (this.f5041a != null) {
            v(str, z);
            return;
        }
        if (this.f5214s + 1 >= this.f5215t) {
            n();
        }
        if (z) {
            char[] cArr = this.f5212q;
            int i2 = this.f5214s;
            this.f5214s = i2 + 1;
            cArr[i2] = AbstractJsonLexerKt.COMMA;
        }
        if (this.f5190m) {
            _writeString(str);
            return;
        }
        char[] cArr2 = this.f5212q;
        int i3 = this.f5214s;
        this.f5214s = i3 + 1;
        cArr2[i3] = this.f5211p;
        _writeString(str);
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr3 = this.f5212q;
        int i4 = this.f5214s;
        this.f5214s = i4 + 1;
        cArr3[i4] = this.f5211p;
    }

    protected final void u(SerializableString serializableString, boolean z) {
        if (z) {
            this.f5041a.writeObjectEntrySeparator(this);
        } else {
            this.f5041a.beforeObjectEntries(this);
        }
        char[] asQuotedChars = serializableString.asQuotedChars();
        if (this.f5190m) {
            writeRaw(asQuotedChars, 0, asQuotedChars.length);
            return;
        }
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        this.f5214s = i2 + 1;
        cArr[i2] = this.f5211p;
        writeRaw(asQuotedChars, 0, asQuotedChars.length);
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr2 = this.f5212q;
        int i3 = this.f5214s;
        this.f5214s = i3 + 1;
        cArr2[i3] = this.f5211p;
    }

    protected final void v(String str, boolean z) {
        if (z) {
            this.f5041a.writeObjectEntrySeparator(this);
        } else {
            this.f5041a.beforeObjectEntries(this);
        }
        if (this.f5190m) {
            _writeString(str);
            return;
        }
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        this.f5214s = i2 + 1;
        cArr[i2] = this.f5211p;
        _writeString(str);
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr2 = this.f5212q;
        int i3 = this.f5214s;
        this.f5214s = i3 + 1;
        cArr2[i3] = this.f5211p;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i2) {
        k("write a binary value");
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i3 = this.f5214s;
        this.f5214s = i3 + 1;
        cArr[i3] = this.f5211p;
        byte[] allocBase64Buffer = this.f5185h.allocBase64Buffer();
        try {
            if (i2 < 0) {
                i2 = p(base64Variant, inputStream, allocBase64Buffer);
            } else {
                int q2 = q(base64Variant, inputStream, allocBase64Buffer, i2);
                if (q2 > 0) {
                    b("Too few bytes available: missing " + q2 + " bytes (out of " + i2 + ")");
                }
            }
            this.f5185h.releaseBase64Buffer(allocBase64Buffer);
            if (this.f5214s >= this.f5215t) {
                n();
            }
            char[] cArr2 = this.f5212q;
            int i4 = this.f5214s;
            this.f5214s = i4 + 1;
            cArr2[i4] = this.f5211p;
            return i2;
        } catch (Throwable th) {
            this.f5185h.releaseBase64Buffer(allocBase64Buffer);
            throw th;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i2, int i3) {
        k("write a binary value");
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i4 = this.f5214s;
        this.f5214s = i4 + 1;
        cArr[i4] = this.f5211p;
        r(base64Variant, bArr, i2, i3 + i2);
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr2 = this.f5212q;
        int i5 = this.f5214s;
        this.f5214s = i5 + 1;
        cArr2[i5] = this.f5211p;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) {
        int i2;
        k("write a boolean value");
        if (this.f5214s + 5 >= this.f5215t) {
            n();
        }
        int i3 = this.f5214s;
        char[] cArr = this.f5212q;
        if (z) {
            cArr[i3] = 't';
            int i4 = i3 + 1;
            cArr[i4] = 'r';
            int i5 = i4 + 1;
            cArr[i5] = AbstractJsonLexerKt.UNICODE_ESC;
            i2 = i5 + 1;
            cArr[i2] = 'e';
        } else {
            cArr[i3] = 'f';
            int i6 = i3 + 1;
            cArr[i6] = 'a';
            int i7 = i6 + 1;
            cArr[i7] = 'l';
            int i8 = i7 + 1;
            cArr[i8] = 's';
            i2 = i8 + 1;
            cArr[i2] = 'e';
        }
        this.f5214s = i2 + 1;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeEndArray() {
        if (!this.f5086e.inArray()) {
            b("Current context not Array but " + this.f5086e.typeDesc());
        }
        PrettyPrinter prettyPrinter = this.f5041a;
        if (prettyPrinter != null) {
            prettyPrinter.writeEndArray(this, this.f5086e.getEntryCount());
        } else {
            if (this.f5214s >= this.f5215t) {
                n();
            }
            char[] cArr = this.f5212q;
            int i2 = this.f5214s;
            this.f5214s = i2 + 1;
            cArr[i2] = AbstractJsonLexerKt.END_LIST;
        }
        this.f5086e = this.f5086e.clearAndGetParent();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeEndObject() {
        if (!this.f5086e.inObject()) {
            b("Current context not Object but " + this.f5086e.typeDesc());
        }
        PrettyPrinter prettyPrinter = this.f5041a;
        if (prettyPrinter != null) {
            prettyPrinter.writeEndObject(this, this.f5086e.getEntryCount());
        } else {
            if (this.f5214s >= this.f5215t) {
                n();
            }
            char[] cArr = this.f5212q;
            int i2 = this.f5214s;
            this.f5214s = i2 + 1;
            cArr[i2] = AbstractJsonLexerKt.END_OBJ;
        }
        this.f5086e = this.f5086e.clearAndGetParent();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(SerializableString serializableString) {
        int writeFieldName = this.f5086e.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            b("Can not write a field name, expecting a value");
        }
        s(serializableString, writeFieldName == 1);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(String str) {
        int writeFieldName = this.f5086e.writeFieldName(str);
        if (writeFieldName == 4) {
            b("Can not write a field name, expecting a value");
        }
        t(str, writeFieldName == 1);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() {
        k("write a null");
        _writeNull();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(double d2) {
        if (this.f5085d || (NumberOutput.notFinite(d2) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            writeString(String.valueOf(d2));
            return;
        }
        k("write a number");
        writeRaw(String.valueOf(d2));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f2) {
        if (this.f5085d || (NumberOutput.notFinite(f2) && isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            writeString(String.valueOf(f2));
            return;
        }
        k("write a number");
        writeRaw(String.valueOf(f2));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int i2) {
        k("write a number");
        if (this.f5085d) {
            _writeQuotedInt(i2);
            return;
        }
        if (this.f5214s + 11 >= this.f5215t) {
            n();
        }
        this.f5214s = NumberOutput.outputInt(i2, this.f5212q, this.f5214s);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(long j2) {
        k("write a number");
        if (this.f5085d) {
            _writeQuotedLong(j2);
            return;
        }
        if (this.f5214s + 21 >= this.f5215t) {
            n();
        }
        this.f5214s = NumberOutput.outputLong(j2, this.f5212q, this.f5214s);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(String str) {
        k("write a number");
        if (this.f5085d) {
            _writeQuotedRaw(str);
        } else {
            writeRaw(str);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) {
        k("write a number");
        if (bigDecimal == null) {
            _writeNull();
            return;
        }
        boolean z = this.f5085d;
        String g2 = g(bigDecimal);
        if (z) {
            _writeQuotedRaw(g2);
        } else {
            writeRaw(g2);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigInteger bigInteger) {
        k("write a number");
        if (bigInteger == null) {
            _writeNull();
            return;
        }
        boolean z = this.f5085d;
        String bigInteger2 = bigInteger.toString();
        if (z) {
            _writeQuotedRaw(bigInteger2);
        } else {
            writeRaw(bigInteger2);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(short s2) {
        k("write a number");
        if (this.f5085d) {
            _writeQuotedShort(s2);
            return;
        }
        if (this.f5214s + 6 >= this.f5215t) {
            n();
        }
        this.f5214s = NumberOutput.outputInt(s2, this.f5212q, this.f5214s);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(char[] cArr, int i2, int i3) {
        k("write a number");
        if (this.f5085d) {
            _writeQuotedRaw(cArr, i2, i3);
        } else {
            writeRaw(cArr, i2, i3);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char c2) {
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        this.f5214s = i2 + 1;
        cArr[i2] = c2;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(SerializableString serializableString) {
        int appendUnquoted = serializableString.appendUnquoted(this.f5212q, this.f5214s);
        if (appendUnquoted < 0) {
            writeRaw(serializableString.getValue());
        } else {
            this.f5214s += appendUnquoted;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) {
        int length = str.length();
        int i2 = this.f5215t - this.f5214s;
        if (i2 == 0) {
            n();
            i2 = this.f5215t - this.f5214s;
        }
        if (i2 < length) {
            writeRawLong(str);
            return;
        }
        str.getChars(0, length, this.f5212q, this.f5214s);
        this.f5214s += length;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str, int i2, int i3) {
        int i4 = this.f5215t - this.f5214s;
        if (i4 < i3) {
            n();
            i4 = this.f5215t - this.f5214s;
        }
        if (i4 < i3) {
            writeRawLong(str.substring(i2, i3 + i2));
            return;
        }
        str.getChars(i2, i2 + i3, this.f5212q, this.f5214s);
        this.f5214s += i3;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char[] cArr, int i2, int i3) {
        if (i3 >= 32) {
            n();
            this.f5210o.write(cArr, i2, i3);
            return;
        }
        if (i3 > this.f5215t - this.f5214s) {
            n();
        }
        System.arraycopy(cArr, i2, this.f5212q, this.f5214s, i3);
        this.f5214s += i3;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i2, int i3) {
        c();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray() {
        k("start an array");
        this.f5086e = this.f5086e.createChildArrayContext();
        PrettyPrinter prettyPrinter = this.f5041a;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        this.f5214s = i2 + 1;
        cArr[i2] = AbstractJsonLexerKt.BEGIN_LIST;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray(int i2) {
        k("start an array");
        this.f5086e = this.f5086e.createChildArrayContext();
        PrettyPrinter prettyPrinter = this.f5041a;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i3 = this.f5214s;
        this.f5214s = i3 + 1;
        cArr[i3] = AbstractJsonLexerKt.BEGIN_LIST;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject() {
        k("start an object");
        this.f5086e = this.f5086e.createChildObjectContext();
        PrettyPrinter prettyPrinter = this.f5041a;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartObject(this);
            return;
        }
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        this.f5214s = i2 + 1;
        cArr[i2] = AbstractJsonLexerKt.BEGIN_OBJ;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject(Object obj) {
        k("start an object");
        this.f5086e = this.f5086e.createChildObjectContext(obj);
        PrettyPrinter prettyPrinter = this.f5041a;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartObject(this);
            return;
        }
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        this.f5214s = i2 + 1;
        cArr[i2] = AbstractJsonLexerKt.BEGIN_OBJ;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeString(SerializableString serializableString) {
        k("write a string");
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        int i3 = i2 + 1;
        this.f5214s = i3;
        cArr[i2] = this.f5211p;
        int appendQuoted = serializableString.appendQuoted(cArr, i3);
        if (appendQuoted < 0) {
            _writeString2(serializableString);
            return;
        }
        int i4 = this.f5214s + appendQuoted;
        this.f5214s = i4;
        if (i4 >= this.f5215t) {
            n();
        }
        char[] cArr2 = this.f5212q;
        int i5 = this.f5214s;
        this.f5214s = i5 + 1;
        cArr2[i5] = this.f5211p;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(Reader reader, int i2) {
        k("write a string");
        if (reader == null) {
            b("null reader");
        }
        int i3 = i2 >= 0 ? i2 : Integer.MAX_VALUE;
        if (this.f5214s + i2 >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i4 = this.f5214s;
        this.f5214s = i4 + 1;
        cArr[i4] = this.f5211p;
        char[] _allocateCopyBuffer = _allocateCopyBuffer();
        while (i3 > 0) {
            int read = reader.read(_allocateCopyBuffer, 0, Math.min(i3, _allocateCopyBuffer.length));
            if (read <= 0) {
                break;
            }
            if (this.f5214s + i2 >= this.f5215t) {
                n();
            }
            _writeString(_allocateCopyBuffer, 0, read);
            i3 -= read;
        }
        if (this.f5214s + i2 >= this.f5215t) {
            n();
        }
        char[] cArr2 = this.f5212q;
        int i5 = this.f5214s;
        this.f5214s = i5 + 1;
        cArr2[i5] = this.f5211p;
        if (i3 <= 0 || i2 < 0) {
            return;
        }
        b("Didn't read enough from reader");
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(String str) {
        k("write a string");
        if (str == null) {
            _writeNull();
            return;
        }
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr = this.f5212q;
        int i2 = this.f5214s;
        this.f5214s = i2 + 1;
        cArr[i2] = this.f5211p;
        _writeString(str);
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr2 = this.f5212q;
        int i3 = this.f5214s;
        this.f5214s = i3 + 1;
        cArr2[i3] = this.f5211p;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int i2, int i3) {
        k("write a string");
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr2 = this.f5212q;
        int i4 = this.f5214s;
        this.f5214s = i4 + 1;
        cArr2[i4] = this.f5211p;
        _writeString(cArr, i2, i3);
        if (this.f5214s >= this.f5215t) {
            n();
        }
        char[] cArr3 = this.f5212q;
        int i5 = this.f5214s;
        this.f5214s = i5 + 1;
        cArr3[i5] = this.f5211p;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeUTF8String(byte[] bArr, int i2, int i3) {
        c();
    }
}
