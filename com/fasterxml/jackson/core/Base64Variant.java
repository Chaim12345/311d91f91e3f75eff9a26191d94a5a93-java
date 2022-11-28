package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class Base64Variant implements Serializable {
    public static final int BASE64_VALUE_INVALID = -1;
    public static final int BASE64_VALUE_PADDING = -2;
    private static final int INT_SPACE = 32;
    private static final long serialVersionUID = 1;
    private final transient int[] _asciiToBase64;
    private final transient byte[] _base64ToAsciiB;
    private final transient char[] _base64ToAsciiC;
    private final transient int _maxLineLength;
    private final transient char _paddingChar;
    private final transient boolean _usesPadding;

    /* renamed from: a  reason: collision with root package name */
    final String f5020a;

    public Base64Variant(Base64Variant base64Variant, String str, int i2) {
        this(base64Variant, str, base64Variant._usesPadding, base64Variant._paddingChar, i2);
    }

    public Base64Variant(Base64Variant base64Variant, String str, boolean z, char c2, int i2) {
        int[] iArr = new int[128];
        this._asciiToBase64 = iArr;
        char[] cArr = new char[64];
        this._base64ToAsciiC = cArr;
        byte[] bArr = new byte[64];
        this._base64ToAsciiB = bArr;
        this.f5020a = str;
        byte[] bArr2 = base64Variant._base64ToAsciiB;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        char[] cArr2 = base64Variant._base64ToAsciiC;
        System.arraycopy(cArr2, 0, cArr, 0, cArr2.length);
        int[] iArr2 = base64Variant._asciiToBase64;
        System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
        this._usesPadding = z;
        this._paddingChar = c2;
        this._maxLineLength = i2;
    }

    public Base64Variant(String str, String str2, boolean z, char c2, int i2) {
        int[] iArr = new int[128];
        this._asciiToBase64 = iArr;
        char[] cArr = new char[64];
        this._base64ToAsciiC = cArr;
        this._base64ToAsciiB = new byte[64];
        this.f5020a = str;
        this._usesPadding = z;
        this._paddingChar = c2;
        this._maxLineLength = i2;
        int length = str2.length();
        if (length != 64) {
            throw new IllegalArgumentException("Base64Alphabet length must be exactly 64 (was " + length + ")");
        }
        str2.getChars(0, length, cArr, 0);
        Arrays.fill(iArr, -1);
        for (int i3 = 0; i3 < length; i3++) {
            char c3 = this._base64ToAsciiC[i3];
            this._base64ToAsciiB[i3] = (byte) c3;
            this._asciiToBase64[c3] = i3;
        }
        if (z) {
            this._asciiToBase64[c2] = -2;
        }
    }

    protected void a() {
        throw new IllegalArgumentException(missingPaddingMessage());
    }

    protected void b(char c2, int i2, String str) {
        StringBuilder sb;
        String str2;
        String sb2;
        if (c2 <= ' ') {
            sb2 = "Illegal white space character (code 0x" + Integer.toHexString(c2) + ") as character #" + (i2 + 1) + " of 4-char base64 unit: can only used between units";
        } else if (usesPaddingChar(c2)) {
            sb2 = "Unexpected padding character ('" + getPaddingChar() + "') as character #" + (i2 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else {
            if (!Character.isDefined(c2) || Character.isISOControl(c2)) {
                sb = new StringBuilder();
                str2 = "Illegal character (code 0x";
            } else {
                sb = new StringBuilder();
                sb.append("Illegal character '");
                sb.append(c2);
                str2 = "' (code 0x";
            }
            sb.append(str2);
            sb.append(Integer.toHexString(c2));
            sb.append(") in base64 content");
            sb2 = sb.toString();
        }
        if (str != null) {
            sb2 = sb2 + ": " + str;
        }
        throw new IllegalArgumentException(sb2);
    }

    public void decode(String str, ByteArrayBuilder byteArrayBuilder) {
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            char charAt = str.charAt(i2);
            if (charAt > ' ') {
                int decodeBase64Char = decodeBase64Char(charAt);
                if (decodeBase64Char < 0) {
                    b(charAt, 0, null);
                }
                if (i3 >= length) {
                    a();
                }
                int i4 = i3 + 1;
                char charAt2 = str.charAt(i3);
                int decodeBase64Char2 = decodeBase64Char(charAt2);
                if (decodeBase64Char2 < 0) {
                    b(charAt2, 1, null);
                }
                int i5 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (i4 >= length) {
                    if (!usesPadding()) {
                        byteArrayBuilder.append(i5 >> 4);
                        return;
                    }
                    a();
                }
                int i6 = i4 + 1;
                char charAt3 = str.charAt(i4);
                int decodeBase64Char3 = decodeBase64Char(charAt3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        b(charAt3, 2, null);
                    }
                    if (i6 >= length) {
                        a();
                    }
                    i2 = i6 + 1;
                    char charAt4 = str.charAt(i6);
                    if (!usesPaddingChar(charAt4)) {
                        b(charAt4, 3, "expected padding character '" + getPaddingChar() + "'");
                    }
                    byteArrayBuilder.append(i5 >> 4);
                } else {
                    int i7 = (i5 << 6) | decodeBase64Char3;
                    if (i6 >= length) {
                        if (!usesPadding()) {
                            byteArrayBuilder.appendTwoBytes(i7 >> 2);
                            return;
                        }
                        a();
                    }
                    i3 = i6 + 1;
                    char charAt5 = str.charAt(i6);
                    int decodeBase64Char4 = decodeBase64Char(charAt5);
                    if (decodeBase64Char4 < 0) {
                        if (decodeBase64Char4 != -2) {
                            b(charAt5, 3, null);
                        }
                        byteArrayBuilder.appendTwoBytes(i7 >> 2);
                    } else {
                        byteArrayBuilder.appendThreeBytes((i7 << 6) | decodeBase64Char4);
                    }
                }
            }
            i2 = i3;
        }
    }

    public byte[] decode(String str) {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder();
        decode(str, byteArrayBuilder);
        return byteArrayBuilder.toByteArray();
    }

    public int decodeBase64Byte(byte b2) {
        if (b2 < 0) {
            return -1;
        }
        return this._asciiToBase64[b2];
    }

    public int decodeBase64Char(char c2) {
        if (c2 <= 127) {
            return this._asciiToBase64[c2];
        }
        return -1;
    }

    public int decodeBase64Char(int i2) {
        if (i2 <= 127) {
            return this._asciiToBase64[i2];
        }
        return -1;
    }

    public String encode(byte[] bArr) {
        return encode(bArr, false);
    }

    public String encode(byte[] bArr, boolean z) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder((length >> 2) + length + (length >> 3));
        if (z) {
            sb.append('\"');
        }
        int maxLineLength = getMaxLineLength() >> 2;
        int i2 = 0;
        int i3 = length - 3;
        while (i2 <= i3) {
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            encodeBase64Chunk(sb, (((bArr[i2] << 8) | (bArr[i4] & 255)) << 8) | (bArr[i5] & 255));
            maxLineLength--;
            if (maxLineLength <= 0) {
                sb.append('\\');
                sb.append('n');
                maxLineLength = getMaxLineLength() >> 2;
            }
            i2 = i6;
        }
        int i7 = length - i2;
        if (i7 > 0) {
            int i8 = i2 + 1;
            int i9 = bArr[i2] << 16;
            if (i7 == 2) {
                i9 |= (bArr[i8] & 255) << 8;
            }
            encodeBase64Partial(sb, i9, i7);
        }
        if (z) {
            sb.append('\"');
        }
        return sb.toString();
    }

    public String encode(byte[] bArr, boolean z, String str) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder((length >> 2) + length + (length >> 3));
        if (z) {
            sb.append('\"');
        }
        int maxLineLength = getMaxLineLength() >> 2;
        int i2 = 0;
        int i3 = length - 3;
        while (i2 <= i3) {
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            encodeBase64Chunk(sb, (((bArr[i2] << 8) | (bArr[i4] & 255)) << 8) | (bArr[i5] & 255));
            maxLineLength--;
            if (maxLineLength <= 0) {
                sb.append(str);
                maxLineLength = getMaxLineLength() >> 2;
            }
            i2 = i6;
        }
        int i7 = length - i2;
        if (i7 > 0) {
            int i8 = i2 + 1;
            int i9 = bArr[i2] << 16;
            if (i7 == 2) {
                i9 |= (bArr[i8] & 255) << 8;
            }
            encodeBase64Partial(sb, i9, i7);
        }
        if (z) {
            sb.append('\"');
        }
        return sb.toString();
    }

    public byte encodeBase64BitsAsByte(int i2) {
        return this._base64ToAsciiB[i2];
    }

    public char encodeBase64BitsAsChar(int i2) {
        return this._base64ToAsciiC[i2];
    }

    public int encodeBase64Chunk(int i2, byte[] bArr, int i3) {
        int i4 = i3 + 1;
        byte[] bArr2 = this._base64ToAsciiB;
        bArr[i3] = bArr2[(i2 >> 18) & 63];
        int i5 = i4 + 1;
        bArr[i4] = bArr2[(i2 >> 12) & 63];
        int i6 = i5 + 1;
        bArr[i5] = bArr2[(i2 >> 6) & 63];
        int i7 = i6 + 1;
        bArr[i6] = bArr2[i2 & 63];
        return i7;
    }

    public int encodeBase64Chunk(int i2, char[] cArr, int i3) {
        int i4 = i3 + 1;
        char[] cArr2 = this._base64ToAsciiC;
        cArr[i3] = cArr2[(i2 >> 18) & 63];
        int i5 = i4 + 1;
        cArr[i4] = cArr2[(i2 >> 12) & 63];
        int i6 = i5 + 1;
        cArr[i5] = cArr2[(i2 >> 6) & 63];
        int i7 = i6 + 1;
        cArr[i6] = cArr2[i2 & 63];
        return i7;
    }

    public void encodeBase64Chunk(StringBuilder sb, int i2) {
        sb.append(this._base64ToAsciiC[(i2 >> 18) & 63]);
        sb.append(this._base64ToAsciiC[(i2 >> 12) & 63]);
        sb.append(this._base64ToAsciiC[(i2 >> 6) & 63]);
        sb.append(this._base64ToAsciiC[i2 & 63]);
    }

    public int encodeBase64Partial(int i2, int i3, byte[] bArr, int i4) {
        int i5 = i4 + 1;
        byte[] bArr2 = this._base64ToAsciiB;
        bArr[i4] = bArr2[(i2 >> 18) & 63];
        int i6 = i5 + 1;
        bArr[i5] = bArr2[(i2 >> 12) & 63];
        if (!this._usesPadding) {
            if (i3 == 2) {
                int i7 = i6 + 1;
                bArr[i6] = bArr2[(i2 >> 6) & 63];
                return i7;
            }
            return i6;
        }
        byte b2 = (byte) this._paddingChar;
        int i8 = i6 + 1;
        bArr[i6] = i3 == 2 ? bArr2[(i2 >> 6) & 63] : b2;
        int i9 = i8 + 1;
        bArr[i8] = b2;
        return i9;
    }

    public int encodeBase64Partial(int i2, int i3, char[] cArr, int i4) {
        int i5 = i4 + 1;
        char[] cArr2 = this._base64ToAsciiC;
        cArr[i4] = cArr2[(i2 >> 18) & 63];
        int i6 = i5 + 1;
        cArr[i5] = cArr2[(i2 >> 12) & 63];
        if (this._usesPadding) {
            int i7 = i6 + 1;
            cArr[i6] = i3 == 2 ? cArr2[(i2 >> 6) & 63] : this._paddingChar;
            int i8 = i7 + 1;
            cArr[i7] = this._paddingChar;
            return i8;
        } else if (i3 == 2) {
            int i9 = i6 + 1;
            cArr[i6] = cArr2[(i2 >> 6) & 63];
            return i9;
        } else {
            return i6;
        }
    }

    public void encodeBase64Partial(StringBuilder sb, int i2, int i3) {
        char c2;
        sb.append(this._base64ToAsciiC[(i2 >> 18) & 63]);
        sb.append(this._base64ToAsciiC[(i2 >> 12) & 63]);
        if (this._usesPadding) {
            sb.append(i3 == 2 ? this._base64ToAsciiC[(i2 >> 6) & 63] : this._paddingChar);
            c2 = this._paddingChar;
        } else if (i3 != 2) {
            return;
        } else {
            c2 = this._base64ToAsciiC[(i2 >> 6) & 63];
        }
        sb.append(c2);
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int getMaxLineLength() {
        return this._maxLineLength;
    }

    public String getName() {
        return this.f5020a;
    }

    public byte getPaddingByte() {
        return (byte) this._paddingChar;
    }

    public char getPaddingChar() {
        return this._paddingChar;
    }

    public int hashCode() {
        return this.f5020a.hashCode();
    }

    public String missingPaddingMessage() {
        return String.format("Unexpected end of base64-encoded String: base64 variant '%s' expects padding (one or more '%c' characters) at the end", getName(), Character.valueOf(getPaddingChar()));
    }

    public String toString() {
        return this.f5020a;
    }

    public boolean usesPadding() {
        return this._usesPadding;
    }

    public boolean usesPaddingChar(char c2) {
        return c2 == this._paddingChar;
    }

    public boolean usesPaddingChar(int i2) {
        return i2 == this._paddingChar;
    }
}
