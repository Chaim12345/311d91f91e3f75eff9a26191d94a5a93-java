package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.bouncycastle.asn1.BERTags;
/* loaded from: classes.dex */
public final class JsonStringEncoder {
    private static final int INITIAL_BYTE_BUFFER_SIZE = 200;
    private static final int INITIAL_CHAR_BUFFER_SIZE = 120;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    private static final char[] HC = CharTypes.copyHexChars();
    private static final byte[] HB = CharTypes.copyHexBytes();
    private static final JsonStringEncoder instance = new JsonStringEncoder();

    private int _appendByte(int i2, int i3, ByteArrayBuilder byteArrayBuilder, int i4) {
        byte b2;
        byteArrayBuilder.setCurrentSegmentLength(i4);
        byteArrayBuilder.append(92);
        if (i3 < 0) {
            byteArrayBuilder.append(117);
            if (i2 > 255) {
                int i5 = i2 >> 8;
                byte[] bArr = HB;
                byteArrayBuilder.append(bArr[i5 >> 4]);
                byteArrayBuilder.append(bArr[i5 & 15]);
                i2 &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byte[] bArr2 = HB;
            byteArrayBuilder.append(bArr2[i2 >> 4]);
            b2 = bArr2[i2 & 15];
        } else {
            b2 = (byte) i3;
        }
        byteArrayBuilder.append(b2);
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _appendNamed(int i2, char[] cArr) {
        cArr[1] = (char) i2;
        return 2;
    }

    private int _appendNumeric(int i2, char[] cArr) {
        cArr[1] = AbstractJsonLexerKt.UNICODE_ESC;
        char[] cArr2 = HC;
        cArr[4] = cArr2[i2 >> 4];
        cArr[5] = cArr2[i2 & 15];
        return 6;
    }

    private static int _convert(int i2, int i3) {
        if (i3 < 56320 || i3 > 57343) {
            throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i2) + ", second 0x" + Integer.toHexString(i3) + "; illegal combination");
        }
        return ((i2 - 55296) << 10) + 65536 + (i3 - 56320);
    }

    private static void _illegal(int i2) {
        throw new IllegalArgumentException(UTF8Writer.c(i2));
    }

    private char[] _qbuf() {
        return new char[]{'\\', 0, '0', '0'};
    }

    public static JsonStringEncoder getInstance() {
        return instance;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e9 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] encodeAsUTF8(CharSequence charSequence) {
        int i2;
        char c2;
        int length = charSequence.length();
        int i3 = 200;
        byte[] bArr = new byte[200];
        ByteArrayBuilder byteArrayBuilder = null;
        int i4 = 0;
        int i5 = 0;
        loop0: while (true) {
            if (i4 >= length) {
                break;
            }
            int i6 = i4 + 1;
            char charAt = charSequence.charAt(i4);
            while (charAt <= 127) {
                if (i5 >= i3) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i5);
                    }
                    byte[] finishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    i5 = 0;
                    bArr = finishCurrentSegment;
                    i3 = finishCurrentSegment.length;
                }
                int i7 = i5 + 1;
                bArr[i5] = (byte) charAt;
                if (i6 >= length) {
                    i5 = i7;
                    break loop0;
                }
                char charAt2 = charSequence.charAt(i6);
                i6++;
                charAt = charAt2;
                i5 = i7;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i5);
            }
            if (i5 >= i3) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i3 = bArr.length;
                i5 = 0;
            }
            if (charAt < 2048) {
                i2 = i5 + 1;
                bArr[i5] = (byte) ((charAt >> 6) | 192);
            } else if (charAt < 55296 || charAt > 57343) {
                int i8 = i5 + 1;
                bArr[i5] = (byte) ((charAt >> '\f') | BERTags.FLAGS);
                if (i8 >= i3) {
                    byte[] finishCurrentSegment2 = byteArrayBuilder.finishCurrentSegment();
                    i8 = 0;
                    bArr = finishCurrentSegment2;
                    i3 = finishCurrentSegment2.length;
                }
                bArr[i8] = (byte) (((charAt >> 6) & 63) | 128);
                i2 = i8 + 1;
            } else {
                if (charAt > 56319) {
                    _illegal(charAt);
                }
                if (i6 >= length) {
                    _illegal(charAt);
                }
                int i9 = i6 + 1;
                int _convert = _convert(charAt, charSequence.charAt(i6));
                if (_convert > 1114111) {
                    _illegal(_convert);
                }
                int i10 = i5 + 1;
                bArr[i5] = (byte) ((_convert >> 18) | 240);
                if (i10 >= i3) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i3 = bArr.length;
                    i10 = 0;
                }
                int i11 = i10 + 1;
                bArr[i10] = (byte) (((_convert >> 12) & 63) | 128);
                if (i11 >= i3) {
                    byte[] finishCurrentSegment3 = byteArrayBuilder.finishCurrentSegment();
                    i11 = 0;
                    bArr = finishCurrentSegment3;
                    i3 = finishCurrentSegment3.length;
                }
                int i12 = i11 + 1;
                bArr[i11] = (byte) (((_convert >> 6) & 63) | 128);
                c2 = _convert;
                i4 = i9;
                i2 = i12;
                if (i2 < i3) {
                    byte[] finishCurrentSegment4 = byteArrayBuilder.finishCurrentSegment();
                    i2 = 0;
                    bArr = finishCurrentSegment4;
                    i3 = finishCurrentSegment4.length;
                }
                bArr[i2] = (byte) ((c2 & '?') | 128);
                i5 = i2 + 1;
            }
            c2 = charAt;
            i4 = i6;
            if (i2 < i3) {
            }
            bArr[i2] = (byte) ((c2 & '?') | 128);
            i5 = i2 + 1;
        }
        return byteArrayBuilder == null ? Arrays.copyOfRange(bArr, 0, i5) : byteArrayBuilder.completeAndCoalesce(i5);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e9 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] encodeAsUTF8(String str) {
        int i2;
        char c2;
        int length = str.length();
        int i3 = 200;
        byte[] bArr = new byte[200];
        ByteArrayBuilder byteArrayBuilder = null;
        int i4 = 0;
        int i5 = 0;
        loop0: while (true) {
            if (i4 >= length) {
                break;
            }
            int i6 = i4 + 1;
            char charAt = str.charAt(i4);
            while (charAt <= 127) {
                if (i5 >= i3) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i5);
                    }
                    byte[] finishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    i5 = 0;
                    bArr = finishCurrentSegment;
                    i3 = finishCurrentSegment.length;
                }
                int i7 = i5 + 1;
                bArr[i5] = (byte) charAt;
                if (i6 >= length) {
                    i5 = i7;
                    break loop0;
                }
                char charAt2 = str.charAt(i6);
                i6++;
                charAt = charAt2;
                i5 = i7;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i5);
            }
            if (i5 >= i3) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i3 = bArr.length;
                i5 = 0;
            }
            if (charAt < 2048) {
                i2 = i5 + 1;
                bArr[i5] = (byte) ((charAt >> 6) | 192);
            } else if (charAt < 55296 || charAt > 57343) {
                int i8 = i5 + 1;
                bArr[i5] = (byte) ((charAt >> '\f') | BERTags.FLAGS);
                if (i8 >= i3) {
                    byte[] finishCurrentSegment2 = byteArrayBuilder.finishCurrentSegment();
                    i8 = 0;
                    bArr = finishCurrentSegment2;
                    i3 = finishCurrentSegment2.length;
                }
                bArr[i8] = (byte) (((charAt >> 6) & 63) | 128);
                i2 = i8 + 1;
            } else {
                if (charAt > 56319) {
                    _illegal(charAt);
                }
                if (i6 >= length) {
                    _illegal(charAt);
                }
                int i9 = i6 + 1;
                int _convert = _convert(charAt, str.charAt(i6));
                if (_convert > 1114111) {
                    _illegal(_convert);
                }
                int i10 = i5 + 1;
                bArr[i5] = (byte) ((_convert >> 18) | 240);
                if (i10 >= i3) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i3 = bArr.length;
                    i10 = 0;
                }
                int i11 = i10 + 1;
                bArr[i10] = (byte) (((_convert >> 12) & 63) | 128);
                if (i11 >= i3) {
                    byte[] finishCurrentSegment3 = byteArrayBuilder.finishCurrentSegment();
                    i11 = 0;
                    bArr = finishCurrentSegment3;
                    i3 = finishCurrentSegment3.length;
                }
                int i12 = i11 + 1;
                bArr[i11] = (byte) (((_convert >> 6) & 63) | 128);
                c2 = _convert;
                i4 = i9;
                i2 = i12;
                if (i2 < i3) {
                    byte[] finishCurrentSegment4 = byteArrayBuilder.finishCurrentSegment();
                    i2 = 0;
                    bArr = finishCurrentSegment4;
                    i3 = finishCurrentSegment4.length;
                }
                bArr[i2] = (byte) ((c2 & '?') | 128);
                i5 = i2 + 1;
            }
            c2 = charAt;
            i4 = i6;
            if (i2 < i3) {
            }
            bArr[i2] = (byte) ((c2 & '?') | 128);
            i5 = i2 + 1;
        }
        return byteArrayBuilder == null ? Arrays.copyOfRange(bArr, 0, i5) : byteArrayBuilder.completeAndCoalesce(i5);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
        r6 = r5 + 1;
        r5 = r9.charAt(r5);
        r7 = r0[r5];
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0026, code lost:
        if (r7 >= 0) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0028, code lost:
        r5 = _appendNumeric(r5, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
        r5 = _appendNamed(r7, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
        r10.append(r4, 0, r5);
        r5 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
        if (r4 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001a, code lost:
        r4 = _qbuf();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void quoteAsString(CharSequence charSequence, StringBuilder sb) {
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = charSequence.length();
        char[] cArr = null;
        int i2 = 0;
        while (i2 < length2) {
            while (true) {
                char charAt = charSequence.charAt(i2);
                if (charAt < length && iArr[charAt] != 0) {
                    break;
                }
                sb.append(charAt);
                i2++;
                if (i2 >= length2) {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
        if (r6 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
        r6 = _qbuf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        r9 = r7 + 1;
        r7 = r13.charAt(r7);
        r10 = r1[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
        if (r10 >= 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
        r7 = _appendNumeric(r7, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003e, code lost:
        r7 = _appendNamed(r10, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
        r10 = r8 + r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
        if (r10 <= r0.length) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
        r10 = r0.length - r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
        if (r10 <= 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004b, code lost:
        java.lang.System.arraycopy(r6, 0, r0, r8, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004e, code lost:
        if (r4 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
        r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0054, code lost:
        r0 = r4.finishCurrentSegment();
        r7 = r7 - r10;
        java.lang.System.arraycopy(r6, r10, r0, 0, r7);
        r8 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005e, code lost:
        java.lang.System.arraycopy(r6, 0, r0, r8, r7);
        r8 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
        r7 = r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public char[] quoteAsString(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return quoteAsString((String) charSequence);
        }
        char[] cArr = new char[120];
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = charSequence.length();
        TextBuffer textBuffer = null;
        char[] cArr2 = null;
        int i2 = 0;
        int i3 = 0;
        loop0: while (i2 < length2) {
            while (true) {
                char charAt = charSequence.charAt(i2);
                if (charAt < length && iArr[charAt] != 0) {
                    break;
                }
                if (i3 >= cArr.length) {
                    if (textBuffer == null) {
                        textBuffer = TextBuffer.fromInitial(cArr);
                    }
                    cArr = textBuffer.finishCurrentSegment();
                    i3 = 0;
                }
                cArr[i3] = charAt;
                i2++;
                i3++;
                if (i2 >= length2) {
                    break loop0;
                }
            }
        }
        if (textBuffer == null) {
            return Arrays.copyOfRange(cArr, 0, i3);
        }
        textBuffer.setCurrentLength(i3);
        return textBuffer.contentsAsArray();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0024, code lost:
        r9 = r7 + 1;
        r7 = r13.charAt(r7);
        r10 = r1[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002c, code lost:
        if (r10 >= 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
        r7 = _appendNumeric(r7, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0033, code lost:
        r7 = _appendNamed(r10, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0037, code lost:
        r10 = r8 + r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
        if (r10 <= r0.length) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003c, code lost:
        r10 = r0.length - r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003e, code lost:
        if (r10 <= 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
        java.lang.System.arraycopy(r6, 0, r0, r8, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r4 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0045, code lost:
        r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
        r0 = r4.finishCurrentSegment();
        r7 = r7 - r10;
        java.lang.System.arraycopy(r6, r10, r0, 0, r7);
        r8 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0053, code lost:
        java.lang.System.arraycopy(r6, 0, r0, r8, r7);
        r8 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0057, code lost:
        r7 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
        if (r6 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        r6 = _qbuf();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public char[] quoteAsString(String str) {
        char[] cArr = new char[120];
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = str.length();
        TextBuffer textBuffer = null;
        char[] cArr2 = null;
        int i2 = 0;
        int i3 = 0;
        loop0: while (i2 < length2) {
            while (true) {
                char charAt = str.charAt(i2);
                if (charAt < length && iArr[charAt] != 0) {
                    break;
                }
                if (i3 >= cArr.length) {
                    if (textBuffer == null) {
                        textBuffer = TextBuffer.fromInitial(cArr);
                    }
                    cArr = textBuffer.finishCurrentSegment();
                    i3 = 0;
                }
                cArr[i3] = charAt;
                i2++;
                i3++;
                if (i2 >= length2) {
                    break loop0;
                }
            }
        }
        if (textBuffer == null) {
            return Arrays.copyOfRange(cArr, 0, i3);
        }
        textBuffer.setCurrentLength(i3);
        return textBuffer.contentsAsArray();
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] quoteAsUTF8(String str) {
        int i2;
        int i3;
        int i4;
        int length = str.length();
        byte[] bArr = new byte[200];
        ByteArrayBuilder byteArrayBuilder = null;
        int i5 = 0;
        int i6 = 0;
        loop0: while (i5 < length) {
            int[] iArr = CharTypes.get7BitOutputEscapes();
            while (true) {
                char charAt = str.charAt(i5);
                if (charAt <= 127 && iArr[charAt] == 0) {
                    if (i6 >= bArr.length) {
                        if (byteArrayBuilder == null) {
                            byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i6);
                        }
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i6 = 0;
                    }
                    bArr[i6] = (byte) charAt;
                    i5++;
                    i6++;
                    if (i5 >= length) {
                        break loop0;
                    }
                } else {
                    break;
                }
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i6);
            }
            if (i6 >= bArr.length) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i6 = 0;
            }
            int i7 = i5 + 1;
            char charAt2 = str.charAt(i5);
            if (charAt2 <= 127) {
                i6 = _appendByte(charAt2, iArr[charAt2], byteArrayBuilder, i6);
                bArr = byteArrayBuilder.getCurrentSegment();
                i5 = i7;
            } else {
                if (charAt2 <= 2047) {
                    i3 = i6 + 1;
                    bArr[i6] = (byte) ((charAt2 >> 6) | 192);
                    i2 = (charAt2 & '?') | 128;
                } else if (charAt2 < 55296 || charAt2 > 57343) {
                    int i8 = i6 + 1;
                    bArr[i6] = (byte) ((charAt2 >> '\f') | BERTags.FLAGS);
                    if (i8 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i8 = 0;
                    }
                    bArr[i8] = (byte) (((charAt2 >> 6) & 63) | 128);
                    i2 = (charAt2 & '?') | 128;
                    i3 = i8 + 1;
                } else {
                    if (charAt2 > 56319) {
                        _illegal(charAt2);
                    }
                    if (i7 >= length) {
                        _illegal(charAt2);
                    }
                    int i9 = i7 + 1;
                    int _convert = _convert(charAt2, str.charAt(i7));
                    if (_convert > 1114111) {
                        _illegal(_convert);
                    }
                    int i10 = i6 + 1;
                    bArr[i6] = (byte) ((_convert >> 18) | 240);
                    if (i10 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i10 = 0;
                    }
                    int i11 = i10 + 1;
                    bArr[i10] = (byte) (((_convert >> 12) & 63) | 128);
                    if (i11 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i11 = 0;
                    }
                    int i12 = i11 + 1;
                    bArr[i11] = (byte) (((_convert >> 6) & 63) | 128);
                    i4 = (_convert & 63) | 128;
                    i5 = i9;
                    i3 = i12;
                    if (i3 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i3 = 0;
                    }
                    bArr[i3] = (byte) i4;
                    i6 = i3 + 1;
                }
                i4 = i2;
                i5 = i7;
                if (i3 >= bArr.length) {
                }
                bArr[i3] = (byte) i4;
                i6 = i3 + 1;
            }
        }
        return byteArrayBuilder == null ? Arrays.copyOfRange(bArr, 0, i6) : byteArrayBuilder.completeAndCoalesce(i6);
    }
}
