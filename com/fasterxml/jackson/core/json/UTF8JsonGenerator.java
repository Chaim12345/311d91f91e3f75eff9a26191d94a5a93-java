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
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.bouncycastle.asn1.BERTags;
/* loaded from: classes.dex */
public class UTF8JsonGenerator extends JsonGeneratorImpl {
    private static final byte BYTE_0 = 48;
    private static final byte BYTE_BACKSLASH = 92;
    private static final byte BYTE_COLON = 58;
    private static final byte BYTE_COMMA = 44;
    private static final byte BYTE_LBRACKET = 91;
    private static final byte BYTE_LCURLY = 123;
    private static final byte BYTE_RBRACKET = 93;
    private static final byte BYTE_RCURLY = 125;
    private static final int MAX_BYTES_TO_BUFFER = 512;

    /* renamed from: o  reason: collision with root package name */
    protected final OutputStream f5204o;

    /* renamed from: p  reason: collision with root package name */
    protected byte f5205p;

    /* renamed from: q  reason: collision with root package name */
    protected byte[] f5206q;

    /* renamed from: r  reason: collision with root package name */
    protected int f5207r;

    /* renamed from: s  reason: collision with root package name */
    protected final int f5208s;

    /* renamed from: t  reason: collision with root package name */
    protected final int f5209t;
    protected char[] u;
    protected final int v;
    protected boolean w;
    private static final byte[] HEX_CHARS = CharTypes.copyHexBytes();
    private static final byte BYTE_u = 117;
    private static final byte[] NULL_BYTES = {110, BYTE_u, 108, 108};
    private static final byte[] TRUE_BYTES = {116, 114, BYTE_u, 101};
    private static final byte[] FALSE_BYTES = {102, 97, 108, 115, 101};

    @Deprecated
    public UTF8JsonGenerator(IOContext iOContext, int i2, ObjectCodec objectCodec, OutputStream outputStream) {
        this(iOContext, i2, objectCodec, outputStream, '\"');
    }

    public UTF8JsonGenerator(IOContext iOContext, int i2, ObjectCodec objectCodec, OutputStream outputStream, char c2) {
        super(iOContext, i2, objectCodec);
        this.f5204o = outputStream;
        this.f5205p = (byte) c2;
        if (c2 != '\"') {
            this.f5186i = CharTypes.get7BitOutputEscapes(c2);
        }
        this.w = true;
        byte[] allocWriteEncodingBuffer = iOContext.allocWriteEncodingBuffer();
        this.f5206q = allocWriteEncodingBuffer;
        int length = allocWriteEncodingBuffer.length;
        this.f5208s = length;
        this.f5209t = length >> 3;
        char[] allocConcatBuffer = iOContext.allocConcatBuffer();
        this.u = allocConcatBuffer;
        this.v = allocConcatBuffer.length;
        if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII)) {
            setHighestNonEscapedChar(127);
        }
    }

    public UTF8JsonGenerator(IOContext iOContext, int i2, ObjectCodec objectCodec, OutputStream outputStream, char c2, byte[] bArr, int i3, boolean z) {
        super(iOContext, i2, objectCodec);
        this.f5204o = outputStream;
        this.f5205p = (byte) c2;
        if (c2 != '\"') {
            this.f5186i = CharTypes.get7BitOutputEscapes(c2);
        }
        this.w = z;
        this.f5207r = i3;
        this.f5206q = bArr;
        int length = bArr.length;
        this.f5208s = length;
        this.f5209t = length >> 3;
        char[] allocConcatBuffer = iOContext.allocConcatBuffer();
        this.u = allocConcatBuffer;
        this.v = allocConcatBuffer.length;
    }

    @Deprecated
    public UTF8JsonGenerator(IOContext iOContext, int i2, ObjectCodec objectCodec, OutputStream outputStream, byte[] bArr, int i3, boolean z) {
        this(iOContext, i2, objectCodec, outputStream, '\"', bArr, i3, z);
    }

    private final int _handleLongCustomEscape(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        int length = bArr2.length;
        if (i2 + length > i3) {
            this.f5207r = i2;
            n();
            i2 = this.f5207r;
            if (length > bArr.length) {
                this.f5204o.write(bArr2, 0, length);
                return i2;
            }
        }
        System.arraycopy(bArr2, 0, bArr, i2, length);
        int i5 = i2 + length;
        if ((i4 * 6) + i5 > i3) {
            this.f5207r = i5;
            n();
            return this.f5207r;
        }
        return i5;
    }

    private final int _outputMultiByteChar(int i2, int i3) {
        byte[] bArr = this.f5206q;
        if (i2 < 55296 || i2 > 57343) {
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((i2 >> 12) | BERTags.FLAGS);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (((i2 >> 6) & 63) | 128);
            int i6 = i5 + 1;
            bArr[i5] = (byte) ((i2 & 63) | 128);
            return i6;
        }
        int i7 = i3 + 1;
        bArr[i3] = BYTE_BACKSLASH;
        int i8 = i7 + 1;
        bArr[i7] = BYTE_u;
        int i9 = i8 + 1;
        byte[] bArr2 = HEX_CHARS;
        bArr[i8] = bArr2[(i2 >> 12) & 15];
        int i10 = i9 + 1;
        bArr[i9] = bArr2[(i2 >> 8) & 15];
        int i11 = i10 + 1;
        bArr[i10] = bArr2[(i2 >> 4) & 15];
        int i12 = i11 + 1;
        bArr[i11] = bArr2[i2 & 15];
        return i12;
    }

    private final int _outputRawMultiByteChar(int i2, char[] cArr, int i3, int i4) {
        if (i2 >= 55296 && i2 <= 57343) {
            if (i3 >= i4 || cArr == null) {
                b(String.format("Split surrogate on writeRaw() input (last character): first character 0x%4x", Integer.valueOf(i2)));
            }
            o(i2, cArr[i3]);
            return i3 + 1;
        }
        byte[] bArr = this.f5206q;
        int i5 = this.f5207r;
        int i6 = i5 + 1;
        this.f5207r = i6;
        bArr[i5] = (byte) ((i2 >> 12) | BERTags.FLAGS);
        int i7 = i6 + 1;
        this.f5207r = i7;
        bArr[i6] = (byte) (((i2 >> 6) & 63) | 128);
        this.f5207r = i7 + 1;
        bArr[i7] = (byte) ((i2 & 63) | 128);
        return i3;
    }

    private final int _readMore(InputStream inputStream, byte[] bArr, int i2, int i3, int i4) {
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

    private final void _writeBytes(byte[] bArr) {
        int length = bArr.length;
        if (this.f5207r + length > this.f5208s) {
            n();
            if (length > 512) {
                this.f5204o.write(bArr, 0, length);
                return;
            }
        }
        System.arraycopy(bArr, 0, this.f5206q, this.f5207r, length);
        this.f5207r += length;
    }

    private final void _writeBytes(byte[] bArr, int i2, int i3) {
        if (this.f5207r + i3 > this.f5208s) {
            n();
            if (i3 > 512) {
                this.f5204o.write(bArr, i2, i3);
                return;
            }
        }
        System.arraycopy(bArr, i2, this.f5206q, this.f5207r, i3);
        this.f5207r += i3;
    }

    private final int _writeCustomEscape(byte[] bArr, int i2, SerializableString serializableString, int i3) {
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length = asUnquotedUTF8.length;
        if (length > 6) {
            return _handleLongCustomEscape(bArr, i2, this.f5208s, asUnquotedUTF8, i3);
        }
        System.arraycopy(asUnquotedUTF8, 0, bArr, i2, length);
        return i2 + length;
    }

    private final void _writeCustomStringSegment2(String str, int i2, int i3) {
        SerializableString escapeSequence;
        if (this.f5207r + ((i3 - i2) * 6) > this.f5208s) {
            n();
        }
        int i4 = this.f5207r;
        byte[] bArr = this.f5206q;
        int[] iArr = this.f5186i;
        int i5 = this.f5187j;
        if (i5 <= 0) {
            i5 = 65535;
        }
        CharacterEscapes characterEscapes = this.f5188k;
        while (i2 < i3) {
            int i6 = i2 + 1;
            char charAt = str.charAt(i2);
            if (charAt > 127) {
                if (charAt <= i5) {
                    escapeSequence = characterEscapes.getEscapeSequence(charAt);
                    if (escapeSequence == null) {
                        if (charAt <= 2047) {
                            int i7 = i4 + 1;
                            bArr[i4] = (byte) ((charAt >> 6) | 192);
                            i4 = i7 + 1;
                            bArr[i7] = (byte) ((charAt & '?') | 128);
                        } else {
                            i4 = _outputMultiByteChar(charAt, i4);
                        }
                        i2 = i6;
                    }
                    i4 = _writeCustomEscape(bArr, i4, escapeSequence, i3 - i6);
                    i2 = i6;
                }
                i4 = _writeGenericEscape(charAt, i4);
                i2 = i6;
            } else if (iArr[charAt] == 0) {
                bArr[i4] = (byte) charAt;
                i2 = i6;
                i4++;
            } else {
                int i8 = iArr[charAt];
                if (i8 > 0) {
                    int i9 = i4 + 1;
                    bArr[i4] = BYTE_BACKSLASH;
                    i4 = i9 + 1;
                    bArr[i9] = (byte) i8;
                    i2 = i6;
                } else {
                    if (i8 == -2) {
                        escapeSequence = characterEscapes.getEscapeSequence(charAt);
                        if (escapeSequence == null) {
                            b("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(charAt) + ", although was supposed to have one");
                        }
                        i4 = _writeCustomEscape(bArr, i4, escapeSequence, i3 - i6);
                        i2 = i6;
                    }
                    i4 = _writeGenericEscape(charAt, i4);
                    i2 = i6;
                }
            }
        }
        this.f5207r = i4;
    }

    private final void _writeCustomStringSegment2(char[] cArr, int i2, int i3) {
        SerializableString escapeSequence;
        if (this.f5207r + ((i3 - i2) * 6) > this.f5208s) {
            n();
        }
        int i4 = this.f5207r;
        byte[] bArr = this.f5206q;
        int[] iArr = this.f5186i;
        int i5 = this.f5187j;
        if (i5 <= 0) {
            i5 = 65535;
        }
        CharacterEscapes characterEscapes = this.f5188k;
        while (i2 < i3) {
            int i6 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 > 127) {
                if (c2 <= i5) {
                    escapeSequence = characterEscapes.getEscapeSequence(c2);
                    if (escapeSequence == null) {
                        if (c2 <= 2047) {
                            int i7 = i4 + 1;
                            bArr[i4] = (byte) ((c2 >> 6) | 192);
                            i4 = i7 + 1;
                            bArr[i7] = (byte) ((c2 & '?') | 128);
                        } else {
                            i4 = _outputMultiByteChar(c2, i4);
                        }
                        i2 = i6;
                    }
                    i4 = _writeCustomEscape(bArr, i4, escapeSequence, i3 - i6);
                    i2 = i6;
                }
                i4 = _writeGenericEscape(c2, i4);
                i2 = i6;
            } else if (iArr[c2] == 0) {
                bArr[i4] = (byte) c2;
                i2 = i6;
                i4++;
            } else {
                int i8 = iArr[c2];
                if (i8 > 0) {
                    int i9 = i4 + 1;
                    bArr[i4] = BYTE_BACKSLASH;
                    i4 = i9 + 1;
                    bArr[i9] = (byte) i8;
                    i2 = i6;
                } else {
                    if (i8 == -2) {
                        escapeSequence = characterEscapes.getEscapeSequence(c2);
                        if (escapeSequence == null) {
                            b("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(c2) + ", although was supposed to have one");
                        }
                        i4 = _writeCustomEscape(bArr, i4, escapeSequence, i3 - i6);
                        i2 = i6;
                    }
                    i4 = _writeGenericEscape(c2, i4);
                    i2 = i6;
                }
            }
        }
        this.f5207r = i4;
    }

    private int _writeGenericEscape(int i2, int i3) {
        int i4;
        byte[] bArr = this.f5206q;
        int i5 = i3 + 1;
        bArr[i3] = BYTE_BACKSLASH;
        int i6 = i5 + 1;
        bArr[i5] = BYTE_u;
        if (i2 > 255) {
            int i7 = 255 & (i2 >> 8);
            int i8 = i6 + 1;
            byte[] bArr2 = HEX_CHARS;
            bArr[i6] = bArr2[i7 >> 4];
            i4 = i8 + 1;
            bArr[i8] = bArr2[i7 & 15];
            i2 &= 255;
        } else {
            int i9 = i6 + 1;
            bArr[i6] = BYTE_0;
            i4 = i9 + 1;
            bArr[i9] = BYTE_0;
        }
        int i10 = i4 + 1;
        byte[] bArr3 = HEX_CHARS;
        bArr[i4] = bArr3[i2 >> 4];
        int i11 = i10 + 1;
        bArr[i10] = bArr3[i2 & 15];
        return i11;
    }

    private final void _writeNull() {
        if (this.f5207r + 4 >= this.f5208s) {
            n();
        }
        System.arraycopy(NULL_BYTES, 0, this.f5206q, this.f5207r, 4);
        this.f5207r += 4;
    }

    private final void _writeQuotedInt(int i2) {
        if (this.f5207r + 13 >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i3 = this.f5207r;
        int i4 = i3 + 1;
        this.f5207r = i4;
        bArr[i3] = this.f5205p;
        int outputInt = NumberOutput.outputInt(i2, bArr, i4);
        this.f5207r = outputInt;
        byte[] bArr2 = this.f5206q;
        this.f5207r = outputInt + 1;
        bArr2[outputInt] = this.f5205p;
    }

    private final void _writeQuotedLong(long j2) {
        if (this.f5207r + 23 >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i2 = this.f5207r;
        int i3 = i2 + 1;
        this.f5207r = i3;
        bArr[i2] = this.f5205p;
        int outputLong = NumberOutput.outputLong(j2, bArr, i3);
        this.f5207r = outputLong;
        byte[] bArr2 = this.f5206q;
        this.f5207r = outputLong + 1;
        bArr2[outputLong] = this.f5205p;
    }

    private final void _writeQuotedRaw(String str) {
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i2 = this.f5207r;
        this.f5207r = i2 + 1;
        bArr[i2] = this.f5205p;
        writeRaw(str);
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i3 = this.f5207r;
        this.f5207r = i3 + 1;
        bArr2[i3] = this.f5205p;
    }

    private void _writeQuotedRaw(char[] cArr, int i2, int i3) {
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i4 = this.f5207r;
        this.f5207r = i4 + 1;
        bArr[i4] = this.f5205p;
        writeRaw(cArr, i2, i3);
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i5 = this.f5207r;
        this.f5207r = i5 + 1;
        bArr2[i5] = this.f5205p;
    }

    private final void _writeQuotedShort(short s2) {
        if (this.f5207r + 8 >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i2 = this.f5207r;
        int i3 = i2 + 1;
        this.f5207r = i3;
        bArr[i2] = this.f5205p;
        int outputInt = NumberOutput.outputInt(s2, bArr, i3);
        this.f5207r = outputInt;
        byte[] bArr2 = this.f5206q;
        this.f5207r = outputInt + 1;
        bArr2[outputInt] = this.f5205p;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0008, code lost:
        r0 = r7 + 1;
        r7 = r6[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000e, code lost:
        if (r7 >= 2048) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
        r1 = r5.f5206q;
        r2 = r5.f5207r;
        r3 = r2 + 1;
        r5.f5207r = r3;
        r1[r2] = (byte) ((r7 >> 6) | 192);
        r5.f5207r = r3 + 1;
        r1[r3] = (byte) ((r7 & '?') | 128);
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002c, code lost:
        r7 = _outputRawMultiByteChar(r7, r6, r0, r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void _writeRawSegment(char[] cArr, int i2, int i3) {
        while (i2 < i3) {
            while (true) {
                char c2 = cArr[i2];
                if (c2 > 127) {
                    break;
                }
                byte[] bArr = this.f5206q;
                int i4 = this.f5207r;
                this.f5207r = i4 + 1;
                bArr[i4] = (byte) c2;
                i2++;
                if (i2 >= i3) {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
        if (r9 >= 2048) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0020, code lost:
        r4 = r7.f5207r;
        r5 = r4 + 1;
        r7.f5207r = r5;
        r1[r4] = (byte) ((r9 >> 6) | 192);
        r7.f5207r = r5 + 1;
        r1[r5] = (byte) ((r9 & '?') | 128);
        r9 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0039, code lost:
        r9 = _outputRawMultiByteChar(r9, r8, r2, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0013, code lost:
        if ((r7.f5207r + 3) < r7.f5208s) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0015, code lost:
        n();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0018, code lost:
        r2 = r9 + 1;
        r9 = r8[r9];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void _writeSegmentedRaw(char[] cArr, int i2, int i3) {
        int i4 = this.f5208s;
        byte[] bArr = this.f5206q;
        int i5 = i3 + i2;
        while (i2 < i5) {
            while (true) {
                char c2 = cArr[i2];
                if (c2 >= 128) {
                    break;
                }
                if (this.f5207r >= i4) {
                    n();
                }
                int i6 = this.f5207r;
                this.f5207r = i6 + 1;
                bArr[i6] = (byte) c2;
                i2++;
                if (i2 >= i5) {
                    return;
                }
            }
        }
    }

    private final void _writeStringSegment(String str, int i2, int i3) {
        int i4 = i3 + i2;
        int i5 = this.f5207r;
        byte[] bArr = this.f5206q;
        int[] iArr = this.f5186i;
        while (i2 < i4) {
            char charAt = str.charAt(i2);
            if (charAt > 127 || iArr[charAt] != 0) {
                break;
            }
            bArr[i5] = (byte) charAt;
            i2++;
            i5++;
        }
        this.f5207r = i5;
        if (i2 < i4) {
            if (this.f5188k != null) {
                _writeCustomStringSegment2(str, i2, i4);
            } else if (this.f5187j == 0) {
                _writeStringSegment2(str, i2, i4);
            } else {
                _writeStringSegmentASCII2(str, i2, i4);
            }
        }
    }

    private final void _writeStringSegment(char[] cArr, int i2, int i3) {
        int i4 = i3 + i2;
        int i5 = this.f5207r;
        byte[] bArr = this.f5206q;
        int[] iArr = this.f5186i;
        while (i2 < i4) {
            char c2 = cArr[i2];
            if (c2 > 127 || iArr[c2] != 0) {
                break;
            }
            bArr[i5] = (byte) c2;
            i2++;
            i5++;
        }
        this.f5207r = i5;
        if (i2 < i4) {
            if (this.f5188k != null) {
                _writeCustomStringSegment2(cArr, i2, i4);
            } else if (this.f5187j == 0) {
                _writeStringSegment2(cArr, i2, i4);
            } else {
                _writeStringSegmentASCII2(cArr, i2, i4);
            }
        }
    }

    private final void _writeStringSegment2(String str, int i2, int i3) {
        if (this.f5207r + ((i3 - i2) * 6) > this.f5208s) {
            n();
        }
        int i4 = this.f5207r;
        byte[] bArr = this.f5206q;
        int[] iArr = this.f5186i;
        while (i2 < i3) {
            int i5 = i2 + 1;
            char charAt = str.charAt(i2);
            if (charAt <= 127) {
                if (iArr[charAt] == 0) {
                    bArr[i4] = (byte) charAt;
                    i2 = i5;
                    i4++;
                } else {
                    int i6 = iArr[charAt];
                    if (i6 > 0) {
                        int i7 = i4 + 1;
                        bArr[i4] = BYTE_BACKSLASH;
                        i4 = i7 + 1;
                        bArr[i7] = (byte) i6;
                    } else {
                        i4 = _writeGenericEscape(charAt, i4);
                    }
                }
            } else if (charAt <= 2047) {
                int i8 = i4 + 1;
                bArr[i4] = (byte) ((charAt >> 6) | 192);
                i4 = i8 + 1;
                bArr[i8] = (byte) ((charAt & '?') | 128);
            } else {
                i4 = _outputMultiByteChar(charAt, i4);
            }
            i2 = i5;
        }
        this.f5207r = i4;
    }

    private final void _writeStringSegment2(char[] cArr, int i2, int i3) {
        if (this.f5207r + ((i3 - i2) * 6) > this.f5208s) {
            n();
        }
        int i4 = this.f5207r;
        byte[] bArr = this.f5206q;
        int[] iArr = this.f5186i;
        while (i2 < i3) {
            int i5 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 <= 127) {
                if (iArr[c2] == 0) {
                    bArr[i4] = (byte) c2;
                    i2 = i5;
                    i4++;
                } else {
                    int i6 = iArr[c2];
                    if (i6 > 0) {
                        int i7 = i4 + 1;
                        bArr[i4] = BYTE_BACKSLASH;
                        i4 = i7 + 1;
                        bArr[i7] = (byte) i6;
                    } else {
                        i4 = _writeGenericEscape(c2, i4);
                    }
                }
            } else if (c2 <= 2047) {
                int i8 = i4 + 1;
                bArr[i4] = (byte) ((c2 >> 6) | 192);
                i4 = i8 + 1;
                bArr[i8] = (byte) ((c2 & '?') | 128);
            } else {
                i4 = _outputMultiByteChar(c2, i4);
            }
            i2 = i5;
        }
        this.f5207r = i4;
    }

    private final void _writeStringSegmentASCII2(String str, int i2, int i3) {
        if (this.f5207r + ((i3 - i2) * 6) > this.f5208s) {
            n();
        }
        int i4 = this.f5207r;
        byte[] bArr = this.f5206q;
        int[] iArr = this.f5186i;
        int i5 = this.f5187j;
        while (i2 < i3) {
            int i6 = i2 + 1;
            char charAt = str.charAt(i2);
            if (charAt > 127) {
                if (charAt <= i5) {
                    if (charAt <= 2047) {
                        int i7 = i4 + 1;
                        bArr[i4] = (byte) ((charAt >> 6) | 192);
                        i4 = i7 + 1;
                        bArr[i7] = (byte) ((charAt & '?') | 128);
                    } else {
                        i4 = _outputMultiByteChar(charAt, i4);
                    }
                    i2 = i6;
                }
                i4 = _writeGenericEscape(charAt, i4);
                i2 = i6;
            } else if (iArr[charAt] == 0) {
                bArr[i4] = (byte) charAt;
                i2 = i6;
                i4++;
            } else {
                int i8 = iArr[charAt];
                if (i8 > 0) {
                    int i9 = i4 + 1;
                    bArr[i4] = BYTE_BACKSLASH;
                    i4 = i9 + 1;
                    bArr[i9] = (byte) i8;
                    i2 = i6;
                }
                i4 = _writeGenericEscape(charAt, i4);
                i2 = i6;
            }
        }
        this.f5207r = i4;
    }

    private final void _writeStringSegmentASCII2(char[] cArr, int i2, int i3) {
        if (this.f5207r + ((i3 - i2) * 6) > this.f5208s) {
            n();
        }
        int i4 = this.f5207r;
        byte[] bArr = this.f5206q;
        int[] iArr = this.f5186i;
        int i5 = this.f5187j;
        while (i2 < i3) {
            int i6 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 > 127) {
                if (c2 <= i5) {
                    if (c2 <= 2047) {
                        int i7 = i4 + 1;
                        bArr[i4] = (byte) ((c2 >> 6) | 192);
                        i4 = i7 + 1;
                        bArr[i7] = (byte) ((c2 & '?') | 128);
                    } else {
                        i4 = _outputMultiByteChar(c2, i4);
                    }
                    i2 = i6;
                }
                i4 = _writeGenericEscape(c2, i4);
                i2 = i6;
            } else if (iArr[c2] == 0) {
                bArr[i4] = (byte) c2;
                i2 = i6;
                i4++;
            } else {
                int i8 = iArr[c2];
                if (i8 > 0) {
                    int i9 = i4 + 1;
                    bArr[i4] = BYTE_BACKSLASH;
                    i4 = i9 + 1;
                    bArr[i9] = (byte) i8;
                    i2 = i6;
                }
                i4 = _writeGenericEscape(c2, i4);
                i2 = i6;
            }
        }
        this.f5207r = i4;
    }

    private final void _writeStringSegments(String str, int i2, int i3) {
        do {
            int min = Math.min(this.f5209t, i3);
            if (this.f5207r + min > this.f5208s) {
                n();
            }
            _writeStringSegment(str, i2, min);
            i2 += min;
            i3 -= min;
        } while (i3 > 0);
    }

    private final void _writeStringSegments(String str, boolean z) {
        if (z) {
            if (this.f5207r >= this.f5208s) {
                n();
            }
            byte[] bArr = this.f5206q;
            int i2 = this.f5207r;
            this.f5207r = i2 + 1;
            bArr[i2] = this.f5205p;
        }
        int length = str.length();
        int i3 = 0;
        while (length > 0) {
            int min = Math.min(this.f5209t, length);
            if (this.f5207r + min > this.f5208s) {
                n();
            }
            _writeStringSegment(str, i3, min);
            i3 += min;
            length -= min;
        }
        if (z) {
            if (this.f5207r >= this.f5208s) {
                n();
            }
            byte[] bArr2 = this.f5206q;
            int i4 = this.f5207r;
            this.f5207r = i4 + 1;
            bArr2[i4] = this.f5205p;
        }
    }

    private final void _writeStringSegments(char[] cArr, int i2, int i3) {
        do {
            int min = Math.min(this.f5209t, i3);
            if (this.f5207r + min > this.f5208s) {
                n();
            }
            _writeStringSegment(cArr, i2, min);
            i2 += min;
            i3 -= min;
        } while (i3 > 0);
    }

    private final void _writeUTF8Segment(byte[] bArr, int i2, int i3) {
        int[] iArr = this.f5186i;
        int i4 = i2 + i3;
        int i5 = i2;
        while (i5 < i4) {
            int i6 = i5 + 1;
            byte b2 = bArr[i5];
            if (b2 >= 0 && iArr[b2] != 0) {
                _writeUTF8Segment2(bArr, i2, i3);
                return;
            }
            i5 = i6;
        }
        if (this.f5207r + i3 > this.f5208s) {
            n();
        }
        System.arraycopy(bArr, i2, this.f5206q, this.f5207r, i3);
        this.f5207r += i3;
    }

    private final void _writeUTF8Segment2(byte[] bArr, int i2, int i3) {
        int i4 = this.f5207r;
        if ((i3 * 6) + i4 > this.f5208s) {
            n();
            i4 = this.f5207r;
        }
        byte[] bArr2 = this.f5206q;
        int[] iArr = this.f5186i;
        int i5 = i3 + i2;
        while (i2 < i5) {
            int i6 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 < 0 || iArr[b2] == 0) {
                bArr2[i4] = b2;
                i2 = i6;
                i4++;
            } else {
                int i7 = iArr[b2];
                if (i7 > 0) {
                    int i8 = i4 + 1;
                    bArr2[i4] = BYTE_BACKSLASH;
                    i4 = i8 + 1;
                    bArr2[i8] = (byte) i7;
                } else {
                    i4 = _writeGenericEscape(b2, i4);
                }
                i2 = i6;
            }
        }
        this.f5207r = i4;
    }

    private final void _writeUTF8Segments(byte[] bArr, int i2, int i3) {
        do {
            int min = Math.min(this.f5209t, i3);
            _writeUTF8Segment(bArr, i2, min);
            i2 += min;
            i3 -= min;
        } while (i3 > 0);
    }

    private final void _writeUnq(SerializableString serializableString) {
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this.f5206q, this.f5207r);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this.f5207r += appendQuotedUTF8;
        }
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        if (this.f5206q != null && isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)) {
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
        this.f5207r = 0;
        if (this.f5204o != null) {
            if (this.f5185h.isResourceManaged() || isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET)) {
                this.f5204o.close();
            } else if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
                this.f5204o.flush();
            }
        }
        p();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public void flush() {
        n();
        if (this.f5204o == null || !isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
            return;
        }
        this.f5204o.flush();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int getOutputBuffered() {
        return this.f5207r;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public Object getOutputTarget() {
        return this.f5204o;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected final void k(String str) {
        byte b2;
        int writeValue = this.f5086e.writeValue();
        if (this.f5041a != null) {
            m(str, writeValue);
            return;
        }
        if (writeValue == 1) {
            b2 = BYTE_COMMA;
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
                byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
                if (asUnquotedUTF8.length > 0) {
                    _writeBytes(asUnquotedUTF8);
                    return;
                }
                return;
            }
            return;
        } else {
            b2 = BYTE_COLON;
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i2 = this.f5207r;
        this.f5207r = i2 + 1;
        bArr[i2] = b2;
    }

    protected final void n() {
        int i2 = this.f5207r;
        if (i2 > 0) {
            this.f5207r = 0;
            this.f5204o.write(this.f5206q, 0, i2);
        }
    }

    protected final void o(int i2, int i3) {
        int j2 = j(i2, i3);
        if (this.f5207r + 4 > this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i4 = this.f5207r;
        int i5 = i4 + 1;
        this.f5207r = i5;
        bArr[i4] = (byte) ((j2 >> 18) | 240);
        int i6 = i5 + 1;
        this.f5207r = i6;
        bArr[i5] = (byte) (((j2 >> 12) & 63) | 128);
        int i7 = i6 + 1;
        this.f5207r = i7;
        bArr[i6] = (byte) (((j2 >> 6) & 63) | 128);
        this.f5207r = i7 + 1;
        bArr[i7] = (byte) ((j2 & 63) | 128);
    }

    protected void p() {
        byte[] bArr = this.f5206q;
        if (bArr != null && this.w) {
            this.f5206q = null;
            this.f5185h.releaseWriteEncodingBuffer(bArr);
        }
        char[] cArr = this.u;
        if (cArr != null) {
            this.u = null;
            this.f5185h.releaseConcatBuffer(cArr);
        }
    }

    protected final int q(Base64Variant base64Variant, InputStream inputStream, byte[] bArr) {
        int i2 = this.f5208s - 6;
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
            if (this.f5207r > i2) {
                n();
            }
            int i8 = i5 + 1;
            int i9 = i8 + 1;
            i5 = i9 + 1;
            i7 += 3;
            int encodeBase64Chunk = base64Variant.encodeBase64Chunk((((bArr[i8] & 255) | (bArr[i5] << 8)) << 8) | (bArr[i9] & 255), this.f5206q, this.f5207r);
            this.f5207r = encodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this.f5206q;
                int i10 = encodeBase64Chunk + 1;
                this.f5207r = i10;
                bArr2[encodeBase64Chunk] = BYTE_BACKSLASH;
                this.f5207r = i10 + 1;
                bArr2[i10] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (i6 > 0) {
            if (this.f5207r > i2) {
                n();
            }
            int i11 = bArr[0] << 16;
            if (1 < i6) {
                i11 |= (bArr[1] & 255) << 8;
            } else {
                i3 = 1;
            }
            int i12 = i7 + i3;
            this.f5207r = base64Variant.encodeBase64Partial(i11, i3, this.f5206q, this.f5207r);
            return i12;
        }
        return i7;
    }

    protected final int r(Base64Variant base64Variant, InputStream inputStream, byte[] bArr, int i2) {
        int _readMore;
        int i3 = this.f5208s - 6;
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
            if (this.f5207r > i3) {
                n();
            }
            int i8 = i6 + 1;
            int i9 = i8 + 1;
            i6 = i9 + 1;
            i2 -= 3;
            int encodeBase64Chunk = base64Variant.encodeBase64Chunk((((bArr[i8] & 255) | (bArr[i6] << 8)) << 8) | (bArr[i9] & 255), this.f5206q, this.f5207r);
            this.f5207r = encodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this.f5206q;
                int i10 = encodeBase64Chunk + 1;
                this.f5207r = i10;
                bArr2[encodeBase64Chunk] = BYTE_BACKSLASH;
                this.f5207r = i10 + 1;
                bArr2[i10] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (i2 <= 0 || (_readMore = _readMore(inputStream, bArr, i6, i7, i2)) <= 0) {
            return i2;
        }
        if (this.f5207r > i3) {
            n();
        }
        int i11 = bArr[0] << 16;
        if (1 < _readMore) {
            i11 |= (bArr[1] & 255) << 8;
        } else {
            i4 = 1;
        }
        this.f5207r = base64Variant.encodeBase64Partial(i11, i4, this.f5206q, this.f5207r);
        return i2 - i4;
    }

    protected final void s(Base64Variant base64Variant, byte[] bArr, int i2, int i3) {
        int i4 = i3 - 3;
        int i5 = this.f5208s - 6;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        while (i2 <= i4) {
            if (this.f5207r > i5) {
                n();
            }
            int i6 = i2 + 1;
            int i7 = i6 + 1;
            int i8 = i7 + 1;
            int encodeBase64Chunk = base64Variant.encodeBase64Chunk((((bArr[i2] << 8) | (bArr[i6] & 255)) << 8) | (bArr[i7] & 255), this.f5206q, this.f5207r);
            this.f5207r = encodeBase64Chunk;
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this.f5206q;
                int i9 = encodeBase64Chunk + 1;
                this.f5207r = i9;
                bArr2[encodeBase64Chunk] = BYTE_BACKSLASH;
                this.f5207r = i9 + 1;
                bArr2[i9] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
            i2 = i8;
        }
        int i10 = i3 - i2;
        if (i10 > 0) {
            if (this.f5207r > i5) {
                n();
            }
            int i11 = i2 + 1;
            int i12 = bArr[i2] << 16;
            if (i10 == 2) {
                i12 |= (bArr[i11] & 255) << 8;
            }
            this.f5207r = base64Variant.encodeBase64Partial(i12, i10, this.f5206q, this.f5207r);
        }
    }

    protected final void t(SerializableString serializableString) {
        int writeFieldName = this.f5086e.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            b("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            this.f5041a.writeObjectEntrySeparator(this);
        } else {
            this.f5041a.beforeObjectEntries(this);
        }
        boolean z = !this.f5190m;
        if (z) {
            if (this.f5207r >= this.f5208s) {
                n();
            }
            byte[] bArr = this.f5206q;
            int i2 = this.f5207r;
            this.f5207r = i2 + 1;
            bArr[i2] = this.f5205p;
        }
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this.f5206q, this.f5207r);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this.f5207r += appendQuotedUTF8;
        }
        if (z) {
            if (this.f5207r >= this.f5208s) {
                n();
            }
            byte[] bArr2 = this.f5206q;
            int i3 = this.f5207r;
            this.f5207r = i3 + 1;
            bArr2[i3] = this.f5205p;
        }
    }

    protected final void u(String str) {
        int writeFieldName = this.f5086e.writeFieldName(str);
        if (writeFieldName == 4) {
            b("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            this.f5041a.writeObjectEntrySeparator(this);
        } else {
            this.f5041a.beforeObjectEntries(this);
        }
        if (this.f5190m) {
            _writeStringSegments(str, false);
            return;
        }
        int length = str.length();
        if (length > this.v) {
            _writeStringSegments(str, true);
            return;
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i2 = this.f5207r;
        this.f5207r = i2 + 1;
        bArr[i2] = this.f5205p;
        str.getChars(0, length, this.u, 0);
        if (length <= this.f5209t) {
            if (this.f5207r + length > this.f5208s) {
                n();
            }
            _writeStringSegment(this.u, 0, length);
        } else {
            _writeStringSegments(this.u, 0, length);
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i3 = this.f5207r;
        this.f5207r = i3 + 1;
        bArr2[i3] = this.f5205p;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i2) {
        k("write a binary value");
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i3 = this.f5207r;
        this.f5207r = i3 + 1;
        bArr[i3] = this.f5205p;
        byte[] allocBase64Buffer = this.f5185h.allocBase64Buffer();
        try {
            if (i2 < 0) {
                i2 = q(base64Variant, inputStream, allocBase64Buffer);
            } else {
                int r2 = r(base64Variant, inputStream, allocBase64Buffer, i2);
                if (r2 > 0) {
                    b("Too few bytes available: missing " + r2 + " bytes (out of " + i2 + ")");
                }
            }
            this.f5185h.releaseBase64Buffer(allocBase64Buffer);
            if (this.f5207r >= this.f5208s) {
                n();
            }
            byte[] bArr2 = this.f5206q;
            int i4 = this.f5207r;
            this.f5207r = i4 + 1;
            bArr2[i4] = this.f5205p;
            return i2;
        } catch (Throwable th) {
            this.f5185h.releaseBase64Buffer(allocBase64Buffer);
            throw th;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i2, int i3) {
        k("write a binary value");
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i4 = this.f5207r;
        this.f5207r = i4 + 1;
        bArr2[i4] = this.f5205p;
        s(base64Variant, bArr, i2, i3 + i2);
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr3 = this.f5206q;
        int i5 = this.f5207r;
        this.f5207r = i5 + 1;
        bArr3[i5] = this.f5205p;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) {
        k("write a boolean value");
        if (this.f5207r + 5 >= this.f5208s) {
            n();
        }
        byte[] bArr = z ? TRUE_BYTES : FALSE_BYTES;
        int length = bArr.length;
        System.arraycopy(bArr, 0, this.f5206q, this.f5207r, length);
        this.f5207r += length;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndArray() {
        if (!this.f5086e.inArray()) {
            b("Current context not Array but " + this.f5086e.typeDesc());
        }
        PrettyPrinter prettyPrinter = this.f5041a;
        if (prettyPrinter != null) {
            prettyPrinter.writeEndArray(this, this.f5086e.getEntryCount());
        } else {
            if (this.f5207r >= this.f5208s) {
                n();
            }
            byte[] bArr = this.f5206q;
            int i2 = this.f5207r;
            this.f5207r = i2 + 1;
            bArr[i2] = BYTE_RBRACKET;
        }
        this.f5086e = this.f5086e.clearAndGetParent();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeEndObject() {
        if (!this.f5086e.inObject()) {
            b("Current context not Object but " + this.f5086e.typeDesc());
        }
        PrettyPrinter prettyPrinter = this.f5041a;
        if (prettyPrinter != null) {
            prettyPrinter.writeEndObject(this, this.f5086e.getEntryCount());
        } else {
            if (this.f5207r >= this.f5208s) {
                n();
            }
            byte[] bArr = this.f5206q;
            int i2 = this.f5207r;
            this.f5207r = i2 + 1;
            bArr[i2] = BYTE_RCURLY;
        }
        this.f5086e = this.f5086e.clearAndGetParent();
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(SerializableString serializableString) {
        if (this.f5041a != null) {
            t(serializableString);
            return;
        }
        int writeFieldName = this.f5086e.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            b("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            if (this.f5207r >= this.f5208s) {
                n();
            }
            byte[] bArr = this.f5206q;
            int i2 = this.f5207r;
            this.f5207r = i2 + 1;
            bArr[i2] = BYTE_COMMA;
        }
        if (this.f5190m) {
            _writeUnq(serializableString);
            return;
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i3 = this.f5207r;
        int i4 = i3 + 1;
        this.f5207r = i4;
        bArr2[i3] = this.f5205p;
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr2, i4);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this.f5207r += appendQuotedUTF8;
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr3 = this.f5206q;
        int i5 = this.f5207r;
        this.f5207r = i5 + 1;
        bArr3[i5] = this.f5205p;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(String str) {
        if (this.f5041a != null) {
            u(str);
            return;
        }
        int writeFieldName = this.f5086e.writeFieldName(str);
        if (writeFieldName == 4) {
            b("Can not write a field name, expecting a value");
        }
        if (writeFieldName == 1) {
            if (this.f5207r >= this.f5208s) {
                n();
            }
            byte[] bArr = this.f5206q;
            int i2 = this.f5207r;
            this.f5207r = i2 + 1;
            bArr[i2] = BYTE_COMMA;
        }
        if (this.f5190m) {
            _writeStringSegments(str, false);
            return;
        }
        int length = str.length();
        if (length > this.v) {
            _writeStringSegments(str, true);
            return;
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i3 = this.f5207r;
        int i4 = i3 + 1;
        this.f5207r = i4;
        bArr2[i3] = this.f5205p;
        if (length <= this.f5209t) {
            if (i4 + length > this.f5208s) {
                n();
            }
            _writeStringSegment(str, 0, length);
        } else {
            _writeStringSegments(str, 0, length);
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr3 = this.f5206q;
        int i5 = this.f5207r;
        this.f5207r = i5 + 1;
        bArr3[i5] = this.f5205p;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() {
        k("write a null");
        _writeNull();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(double d2) {
        if (this.f5085d || (NumberOutput.notFinite(d2) && JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this.f5084c))) {
            writeString(String.valueOf(d2));
            return;
        }
        k("write a number");
        writeRaw(String.valueOf(d2));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f2) {
        if (this.f5085d || (NumberOutput.notFinite(f2) && JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this.f5084c))) {
            writeString(String.valueOf(f2));
            return;
        }
        k("write a number");
        writeRaw(String.valueOf(f2));
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int i2) {
        k("write a number");
        if (this.f5207r + 11 >= this.f5208s) {
            n();
        }
        if (this.f5085d) {
            _writeQuotedInt(i2);
        } else {
            this.f5207r = NumberOutput.outputInt(i2, this.f5206q, this.f5207r);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(long j2) {
        k("write a number");
        if (this.f5085d) {
            _writeQuotedLong(j2);
            return;
        }
        if (this.f5207r + 21 >= this.f5208s) {
            n();
        }
        this.f5207r = NumberOutput.outputLong(j2, this.f5206q, this.f5207r);
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
        if (this.f5207r + 6 >= this.f5208s) {
            n();
        }
        if (this.f5085d) {
            _writeQuotedShort(s2);
        } else {
            this.f5207r = NumberOutput.outputInt(s2, this.f5206q, this.f5207r);
        }
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
        if (this.f5207r + 3 >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        if (c2 <= 127) {
            int i2 = this.f5207r;
            this.f5207r = i2 + 1;
            bArr[i2] = (byte) c2;
        } else if (c2 >= 2048) {
            _outputRawMultiByteChar(c2, null, 0, 0);
        } else {
            int i3 = this.f5207r;
            int i4 = i3 + 1;
            this.f5207r = i4;
            bArr[i3] = (byte) ((c2 >> 6) | 192);
            this.f5207r = i4 + 1;
            bArr[i4] = (byte) ((c2 & '?') | 128);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(SerializableString serializableString) {
        int appendUnquotedUTF8 = serializableString.appendUnquotedUTF8(this.f5206q, this.f5207r);
        if (appendUnquotedUTF8 < 0) {
            _writeBytes(serializableString.asUnquotedUTF8());
        } else {
            this.f5207r += appendUnquotedUTF8;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) {
        int length = str.length();
        char[] cArr = this.u;
        if (length > cArr.length) {
            writeRaw(str, 0, length);
            return;
        }
        str.getChars(0, length, cArr, 0);
        writeRaw(cArr, 0, length);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str, int i2, int i3) {
        char c2;
        char[] cArr = this.u;
        int length = cArr.length;
        if (i3 <= length) {
            str.getChars(i2, i2 + i3, cArr, 0);
            writeRaw(cArr, 0, i3);
            return;
        }
        int i4 = this.f5208s;
        int min = Math.min(length, (i4 >> 2) + (i4 >> 4));
        int i5 = min * 3;
        while (i3 > 0) {
            int min2 = Math.min(min, i3);
            str.getChars(i2, i2 + min2, cArr, 0);
            if (this.f5207r + i5 > this.f5208s) {
                n();
            }
            if (min2 > 1 && (c2 = cArr[min2 - 1]) >= 55296 && c2 <= 56319) {
                min2--;
            }
            _writeRawSegment(cArr, 0, min2);
            i2 += min2;
            i3 -= min2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001c, code lost:
        r0 = r7 + 1;
        r7 = r6[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
        if (r7 >= 2048) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0024, code lost:
        r1 = r5.f5206q;
        r2 = r5.f5207r;
        r3 = r2 + 1;
        r5.f5207r = r3;
        r1[r2] = (byte) ((r7 >> 6) | 192);
        r5.f5207r = r3 + 1;
        r1[r3] = (byte) ((r7 & '?') | 128);
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
        r7 = _outputRawMultiByteChar(r7, r6, r0, r8);
     */
    @Override // com.fasterxml.jackson.core.JsonGenerator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void writeRaw(char[] cArr, int i2, int i3) {
        int i4 = i3 + i3 + i3;
        int i5 = this.f5207r + i4;
        int i6 = this.f5208s;
        if (i5 > i6) {
            if (i6 < i4) {
                _writeSegmentedRaw(cArr, i2, i3);
                return;
            }
            n();
        }
        int i7 = i3 + i2;
        while (i2 < i7) {
            while (true) {
                char c2 = cArr[i2];
                if (c2 > 127) {
                    break;
                }
                byte[] bArr = this.f5206q;
                int i8 = this.f5207r;
                this.f5207r = i8 + 1;
                bArr[i8] = (byte) c2;
                i2++;
                if (i2 >= i7) {
                    return;
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i2, int i3) {
        k("write a string");
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i4 = this.f5207r;
        this.f5207r = i4 + 1;
        bArr2[i4] = this.f5205p;
        _writeBytes(bArr, i2, i3);
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr3 = this.f5206q;
        int i5 = this.f5207r;
        this.f5207r = i5 + 1;
        bArr3[i5] = this.f5205p;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(SerializableString serializableString) {
        k("write a raw (unencoded) value");
        int appendUnquotedUTF8 = serializableString.appendUnquotedUTF8(this.f5206q, this.f5207r);
        if (appendUnquotedUTF8 < 0) {
            _writeBytes(serializableString.asUnquotedUTF8());
        } else {
            this.f5207r += appendUnquotedUTF8;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartArray() {
        k("start an array");
        this.f5086e = this.f5086e.createChildArrayContext();
        PrettyPrinter prettyPrinter = this.f5041a;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartArray(this);
            return;
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i2 = this.f5207r;
        this.f5207r = i2 + 1;
        bArr[i2] = BYTE_LBRACKET;
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
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i3 = this.f5207r;
        this.f5207r = i3 + 1;
        bArr[i3] = BYTE_LBRACKET;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final void writeStartObject() {
        k("start an object");
        this.f5086e = this.f5086e.createChildObjectContext();
        PrettyPrinter prettyPrinter = this.f5041a;
        if (prettyPrinter != null) {
            prettyPrinter.writeStartObject(this);
            return;
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i2 = this.f5207r;
        this.f5207r = i2 + 1;
        bArr[i2] = BYTE_LCURLY;
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
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i2 = this.f5207r;
        this.f5207r = i2 + 1;
        bArr[i2] = BYTE_LCURLY;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public final void writeString(SerializableString serializableString) {
        k("write a string");
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i2 = this.f5207r;
        int i3 = i2 + 1;
        this.f5207r = i3;
        bArr[i2] = this.f5205p;
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(bArr, i3);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this.f5207r += appendQuotedUTF8;
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i4 = this.f5207r;
        this.f5207r = i4 + 1;
        bArr2[i4] = this.f5205p;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(Reader reader, int i2) {
        k("write a string");
        if (reader == null) {
            b("null reader");
        }
        int i3 = i2 >= 0 ? i2 : Integer.MAX_VALUE;
        char[] cArr = this.u;
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i4 = this.f5207r;
        this.f5207r = i4 + 1;
        bArr[i4] = this.f5205p;
        while (i3 > 0) {
            int read = reader.read(cArr, 0, Math.min(i3, cArr.length));
            if (read <= 0) {
                break;
            }
            if (this.f5207r + i2 >= this.f5208s) {
                n();
            }
            _writeStringSegments(cArr, 0, read);
            i3 -= read;
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i5 = this.f5207r;
        this.f5207r = i5 + 1;
        bArr2[i5] = this.f5205p;
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
        int length = str.length();
        if (length > this.f5209t) {
            _writeStringSegments(str, true);
            return;
        }
        if (this.f5207r + length >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i2 = this.f5207r;
        this.f5207r = i2 + 1;
        bArr[i2] = this.f5205p;
        _writeStringSegment(str, 0, length);
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i3 = this.f5207r;
        this.f5207r = i3 + 1;
        bArr2[i3] = this.f5205p;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int i2, int i3) {
        k("write a string");
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr = this.f5206q;
        int i4 = this.f5207r;
        int i5 = i4 + 1;
        this.f5207r = i5;
        bArr[i4] = this.f5205p;
        if (i3 <= this.f5209t) {
            if (i5 + i3 > this.f5208s) {
                n();
            }
            _writeStringSegment(cArr, i2, i3);
        } else {
            _writeStringSegments(cArr, i2, i3);
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i6 = this.f5207r;
        this.f5207r = i6 + 1;
        bArr2[i6] = this.f5205p;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeUTF8String(byte[] bArr, int i2, int i3) {
        k("write a string");
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr2 = this.f5206q;
        int i4 = this.f5207r;
        this.f5207r = i4 + 1;
        bArr2[i4] = this.f5205p;
        if (i3 <= this.f5209t) {
            _writeUTF8Segment(bArr, i2, i3);
        } else {
            _writeUTF8Segments(bArr, i2, i3);
        }
        if (this.f5207r >= this.f5208s) {
            n();
        }
        byte[] bArr3 = this.f5206q;
        int i5 = this.f5207r;
        this.f5207r = i5 + 1;
        bArr3[i5] = this.f5205p;
    }
}
