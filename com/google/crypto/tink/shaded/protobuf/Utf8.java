package com.google.crypto.tink.shaded.protobuf;

import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import org.bouncycastle.asn1.BERTags;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class Utf8 {
    private static final long ASCII_MASK_LONG = -9187201950435737472L;
    public static final int COMPLETE = 0;
    public static final int MALFORMED = -1;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Processor processor;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class DecodeUtil {
        private DecodeUtil() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleFourBytes(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i2) {
            if (isNotTrailingByte(b3) || (((b2 << Ascii.FS) + (b3 + 112)) >> 30) != 0 || isNotTrailingByte(b4) || isNotTrailingByte(b5)) {
                throw InvalidProtocolBufferException.c();
            }
            int trailingByteValue = ((b2 & 7) << 18) | (trailingByteValue(b3) << 12) | (trailingByteValue(b4) << 6) | trailingByteValue(b5);
            cArr[i2] = highSurrogate(trailingByteValue);
            cArr[i2 + 1] = lowSurrogate(trailingByteValue);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleOneByte(byte b2, char[] cArr, int i2) {
            cArr[i2] = (char) b2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleThreeBytes(byte b2, byte b3, byte b4, char[] cArr, int i2) {
            if (isNotTrailingByte(b3) || ((b2 == -32 && b3 < -96) || ((b2 == -19 && b3 >= -96) || isNotTrailingByte(b4)))) {
                throw InvalidProtocolBufferException.c();
            }
            cArr[i2] = (char) (((b2 & Ascii.SI) << 12) | (trailingByteValue(b3) << 6) | trailingByteValue(b4));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleTwoBytes(byte b2, byte b3, char[] cArr, int i2) {
            if (b2 < -62 || isNotTrailingByte(b3)) {
                throw InvalidProtocolBufferException.c();
            }
            cArr[i2] = (char) (((b2 & Ascii.US) << 6) | trailingByteValue(b3));
        }

        private static char highSurrogate(int i2) {
            return (char) ((i2 >>> 10) + okio.Utf8.HIGH_SURROGATE_HEADER);
        }

        private static boolean isNotTrailingByte(byte b2) {
            return b2 > -65;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isOneByte(byte b2) {
            return b2 >= 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isThreeBytes(byte b2) {
            return b2 < -16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isTwoBytes(byte b2) {
            return b2 < -32;
        }

        private static char lowSurrogate(int i2) {
            return (char) ((i2 & 1023) + 56320);
        }

        private static int trailingByteValue(byte b2) {
            return b2 & okio.Utf8.REPLACEMENT_BYTE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class Processor {
        Processor() {
        }

        private static int partialIsValidUtf8(ByteBuffer byteBuffer, int i2, int i3) {
            int estimateConsecutiveAscii = i2 + Utf8.estimateConsecutiveAscii(byteBuffer, i2, i3);
            while (estimateConsecutiveAscii < i3) {
                int i4 = estimateConsecutiveAscii + 1;
                byte b2 = byteBuffer.get(estimateConsecutiveAscii);
                if (b2 < 0) {
                    if (b2 >= -32) {
                        if (b2 < -16) {
                            if (i4 < i3 - 1) {
                                int i5 = i4 + 1;
                                byte b3 = byteBuffer.get(i4);
                                if (b3 > -65 || ((b2 == -32 && b3 < -96) || ((b2 == -19 && b3 >= -96) || byteBuffer.get(i5) > -65))) {
                                    return -1;
                                }
                                estimateConsecutiveAscii = i5 + 1;
                            }
                        } else if (i4 < i3 - 2) {
                            int i6 = i4 + 1;
                            byte b4 = byteBuffer.get(i4);
                            if (b4 <= -65 && (((b2 << Ascii.FS) + (b4 + 112)) >> 30) == 0) {
                                int i7 = i6 + 1;
                                if (byteBuffer.get(i6) <= -65) {
                                    i4 = i7 + 1;
                                    if (byteBuffer.get(i7) > -65) {
                                    }
                                }
                            }
                            return -1;
                        }
                        return Utf8.incompleteStateFor(byteBuffer, b2, i4, i3 - i4);
                    } else if (i4 >= i3) {
                        return b2;
                    } else {
                        if (b2 < -62 || byteBuffer.get(i4) > -65) {
                            return -1;
                        }
                        i4++;
                    }
                }
                estimateConsecutiveAscii = i4;
            }
            return 0;
        }

        final String a(ByteBuffer byteBuffer, int i2, int i3) {
            if (byteBuffer.hasArray()) {
                return b(byteBuffer.array(), byteBuffer.arrayOffset() + i2, i3);
            }
            return byteBuffer.isDirect() ? d(byteBuffer, i2, i3) : c(byteBuffer, i2, i3);
        }

        abstract String b(byte[] bArr, int i2, int i3);

        final String c(ByteBuffer byteBuffer, int i2, int i3) {
            if ((i2 | i3 | ((byteBuffer.limit() - i2) - i3)) >= 0) {
                int i4 = i2 + i3;
                char[] cArr = new char[i3];
                int i5 = 0;
                while (i2 < i4) {
                    byte b2 = byteBuffer.get(i2);
                    if (!DecodeUtil.isOneByte(b2)) {
                        break;
                    }
                    i2++;
                    DecodeUtil.handleOneByte(b2, cArr, i5);
                    i5++;
                }
                int i6 = i5;
                while (i2 < i4) {
                    int i7 = i2 + 1;
                    byte b3 = byteBuffer.get(i2);
                    if (DecodeUtil.isOneByte(b3)) {
                        int i8 = i6 + 1;
                        DecodeUtil.handleOneByte(b3, cArr, i6);
                        while (i7 < i4) {
                            byte b4 = byteBuffer.get(i7);
                            if (!DecodeUtil.isOneByte(b4)) {
                                break;
                            }
                            i7++;
                            DecodeUtil.handleOneByte(b4, cArr, i8);
                            i8++;
                        }
                        i2 = i7;
                        i6 = i8;
                    } else if (DecodeUtil.isTwoBytes(b3)) {
                        if (i7 >= i4) {
                            throw InvalidProtocolBufferException.c();
                        }
                        DecodeUtil.handleTwoBytes(b3, byteBuffer.get(i7), cArr, i6);
                        i2 = i7 + 1;
                        i6++;
                    } else if (DecodeUtil.isThreeBytes(b3)) {
                        if (i7 >= i4 - 1) {
                            throw InvalidProtocolBufferException.c();
                        }
                        int i9 = i7 + 1;
                        DecodeUtil.handleThreeBytes(b3, byteBuffer.get(i7), byteBuffer.get(i9), cArr, i6);
                        i2 = i9 + 1;
                        i6++;
                    } else if (i7 >= i4 - 2) {
                        throw InvalidProtocolBufferException.c();
                    } else {
                        int i10 = i7 + 1;
                        byte b5 = byteBuffer.get(i7);
                        int i11 = i10 + 1;
                        DecodeUtil.handleFourBytes(b3, b5, byteBuffer.get(i10), byteBuffer.get(i11), cArr, i6);
                        i2 = i11 + 1;
                        i6 = i6 + 1 + 1;
                    }
                }
                return new String(cArr, 0, i6);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i2), Integer.valueOf(i3)));
        }

        abstract String d(ByteBuffer byteBuffer, int i2, int i3);

        abstract int e(CharSequence charSequence, byte[] bArr, int i2, int i3);

        final void f(CharSequence charSequence, ByteBuffer byteBuffer) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                byteBuffer.position(Utf8.i(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
            } else if (byteBuffer.isDirect()) {
                h(charSequence, byteBuffer);
            } else {
                g(charSequence, byteBuffer);
            }
        }

        final void g(CharSequence charSequence, ByteBuffer byteBuffer) {
            int length = charSequence.length();
            int position = byteBuffer.position();
            int i2 = 0;
            while (i2 < length) {
                try {
                    char charAt = charSequence.charAt(i2);
                    if (charAt >= 128) {
                        break;
                    }
                    byteBuffer.put(position + i2, (byte) charAt);
                    i2++;
                } catch (IndexOutOfBoundsException unused) {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1)));
                }
            }
            if (i2 == length) {
                byteBuffer.position(position + i2);
                return;
            }
            position += i2;
            while (i2 < length) {
                char charAt2 = charSequence.charAt(i2);
                if (charAt2 < 128) {
                    byteBuffer.put(position, (byte) charAt2);
                } else if (charAt2 < 2048) {
                    int i3 = position + 1;
                    try {
                        byteBuffer.put(position, (byte) ((charAt2 >>> 6) | 192));
                        byteBuffer.put(i3, (byte) ((charAt2 & '?') | 128));
                        position = i3;
                    } catch (IndexOutOfBoundsException unused2) {
                        position = i3;
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1)));
                    }
                } else if (charAt2 >= 55296 && 57343 >= charAt2) {
                    int i4 = i2 + 1;
                    if (i4 != length) {
                        try {
                            char charAt3 = charSequence.charAt(i4);
                            if (Character.isSurrogatePair(charAt2, charAt3)) {
                                int codePoint = Character.toCodePoint(charAt2, charAt3);
                                int i5 = position + 1;
                                try {
                                    byteBuffer.put(position, (byte) ((codePoint >>> 18) | 240));
                                    int i6 = i5 + 1;
                                    byteBuffer.put(i5, (byte) (((codePoint >>> 12) & 63) | 128));
                                    int i7 = i6 + 1;
                                    byteBuffer.put(i6, (byte) (((codePoint >>> 6) & 63) | 128));
                                    byteBuffer.put(i7, (byte) ((codePoint & 63) | 128));
                                    position = i7;
                                    i2 = i4;
                                } catch (IndexOutOfBoundsException unused3) {
                                    position = i5;
                                    i2 = i4;
                                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1)));
                                }
                            } else {
                                i2 = i4;
                            }
                        } catch (IndexOutOfBoundsException unused4) {
                        }
                    }
                    throw new UnpairedSurrogateException(i2, length);
                } else {
                    int i8 = position + 1;
                    byteBuffer.put(position, (byte) ((charAt2 >>> '\f') | BERTags.FLAGS));
                    position = i8 + 1;
                    byteBuffer.put(i8, (byte) (((charAt2 >>> 6) & 63) | 128));
                    byteBuffer.put(position, (byte) ((charAt2 & '?') | 128));
                }
                i2++;
                position++;
            }
            byteBuffer.position(position);
        }

        abstract void h(CharSequence charSequence, ByteBuffer byteBuffer);

        final boolean i(ByteBuffer byteBuffer, int i2, int i3) {
            return k(0, byteBuffer, i2, i3) == 0;
        }

        final boolean j(byte[] bArr, int i2, int i3) {
            return l(0, bArr, i2, i3) == 0;
        }

        final int k(int i2, ByteBuffer byteBuffer, int i3, int i4) {
            if (!byteBuffer.hasArray()) {
                return byteBuffer.isDirect() ? n(i2, byteBuffer, i3, i4) : m(i2, byteBuffer, i3, i4);
            }
            int arrayOffset = byteBuffer.arrayOffset();
            return l(i2, byteBuffer.array(), i3 + arrayOffset, arrayOffset + i4);
        }

        abstract int l(int i2, byte[] bArr, int i3, int i4);

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0017, code lost:
            if (r8.get(r9) > (-65)) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x004c, code lost:
            if (r8.get(r9) > (-65)) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008b, code lost:
            if (r8.get(r9) > (-65)) goto L51;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        final int m(int i2, ByteBuffer byteBuffer, int i3, int i4) {
            int i5;
            if (i2 != 0) {
                if (i3 >= i4) {
                    return i2;
                }
                byte b2 = (byte) i2;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        i5 = i3 + 1;
                    }
                    return -1;
                } else if (b2 < -16) {
                    byte b3 = (byte) (~(i2 >> 8));
                    if (b3 == 0) {
                        int i6 = i3 + 1;
                        byte b4 = byteBuffer.get(i3);
                        if (i6 >= i4) {
                            return Utf8.incompleteStateFor(b2, b4);
                        }
                        i3 = i6;
                        b3 = b4;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        i5 = i3 + 1;
                    }
                    return -1;
                } else {
                    byte b5 = (byte) (~(i2 >> 8));
                    byte b6 = 0;
                    if (b5 == 0) {
                        int i7 = i3 + 1;
                        b5 = byteBuffer.get(i3);
                        if (i7 >= i4) {
                            return Utf8.incompleteStateFor(b2, b5);
                        }
                        i3 = i7;
                    } else {
                        b6 = (byte) (i2 >> 16);
                    }
                    if (b6 == 0) {
                        int i8 = i3 + 1;
                        b6 = byteBuffer.get(i3);
                        if (i8 >= i4) {
                            return Utf8.incompleteStateFor(b2, b5, b6);
                        }
                        i3 = i8;
                    }
                    if (b5 <= -65 && (((b2 << Ascii.FS) + (b5 + 112)) >> 30) == 0 && b6 <= -65) {
                        i5 = i3 + 1;
                    }
                    return -1;
                }
                i3 = i5;
            }
            return partialIsValidUtf8(byteBuffer, i3, i4);
        }

        abstract int n(int i2, ByteBuffer byteBuffer, int i3, int i4);
    }

    /* loaded from: classes2.dex */
    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        private static int partialIsValidUtf8(byte[] bArr, int i2, int i3) {
            while (i2 < i3 && bArr[i2] >= 0) {
                i2++;
            }
            if (i2 >= i3) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bArr, i2, i3);
        }

        private static int partialIsValidUtf8NonAscii(byte[] bArr, int i2, int i3) {
            while (i2 < i3) {
                int i4 = i2 + 1;
                byte b2 = bArr[i2];
                if (b2 < 0) {
                    if (b2 < -32) {
                        if (i4 >= i3) {
                            return b2;
                        }
                        if (b2 >= -62) {
                            i2 = i4 + 1;
                            if (bArr[i4] > -65) {
                            }
                        }
                        return -1;
                    } else if (b2 >= -16) {
                        if (i4 >= i3 - 2) {
                            return Utf8.incompleteStateFor(bArr, i4, i3);
                        }
                        int i5 = i4 + 1;
                        byte b3 = bArr[i4];
                        if (b3 <= -65 && (((b2 << Ascii.FS) + (b3 + 112)) >> 30) == 0) {
                            int i6 = i5 + 1;
                            if (bArr[i5] <= -65) {
                                i4 = i6 + 1;
                                if (bArr[i6] > -65) {
                                }
                            }
                        }
                        return -1;
                    } else if (i4 >= i3 - 1) {
                        return Utf8.incompleteStateFor(bArr, i4, i3);
                    } else {
                        int i7 = i4 + 1;
                        byte b4 = bArr[i4];
                        if (b4 <= -65 && ((b2 != -32 || b4 >= -96) && (b2 != -19 || b4 < -96))) {
                            i2 = i7 + 1;
                            if (bArr[i7] > -65) {
                            }
                        }
                        return -1;
                    }
                }
                i2 = i4;
            }
            return 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        String b(byte[] bArr, int i2, int i3) {
            if ((i2 | i3 | ((bArr.length - i2) - i3)) >= 0) {
                int i4 = i2 + i3;
                char[] cArr = new char[i3];
                int i5 = 0;
                while (i2 < i4) {
                    byte b2 = bArr[i2];
                    if (!DecodeUtil.isOneByte(b2)) {
                        break;
                    }
                    i2++;
                    DecodeUtil.handleOneByte(b2, cArr, i5);
                    i5++;
                }
                int i6 = i5;
                while (i2 < i4) {
                    int i7 = i2 + 1;
                    byte b3 = bArr[i2];
                    if (DecodeUtil.isOneByte(b3)) {
                        int i8 = i6 + 1;
                        DecodeUtil.handleOneByte(b3, cArr, i6);
                        while (i7 < i4) {
                            byte b4 = bArr[i7];
                            if (!DecodeUtil.isOneByte(b4)) {
                                break;
                            }
                            i7++;
                            DecodeUtil.handleOneByte(b4, cArr, i8);
                            i8++;
                        }
                        i2 = i7;
                        i6 = i8;
                    } else if (DecodeUtil.isTwoBytes(b3)) {
                        if (i7 >= i4) {
                            throw InvalidProtocolBufferException.c();
                        }
                        DecodeUtil.handleTwoBytes(b3, bArr[i7], cArr, i6);
                        i2 = i7 + 1;
                        i6++;
                    } else if (DecodeUtil.isThreeBytes(b3)) {
                        if (i7 >= i4 - 1) {
                            throw InvalidProtocolBufferException.c();
                        }
                        int i9 = i7 + 1;
                        DecodeUtil.handleThreeBytes(b3, bArr[i7], bArr[i9], cArr, i6);
                        i2 = i9 + 1;
                        i6++;
                    } else if (i7 >= i4 - 2) {
                        throw InvalidProtocolBufferException.c();
                    } else {
                        int i10 = i7 + 1;
                        byte b5 = bArr[i7];
                        int i11 = i10 + 1;
                        DecodeUtil.handleFourBytes(b3, b5, bArr[i10], bArr[i11], cArr, i6);
                        i2 = i11 + 1;
                        i6 = i6 + 1 + 1;
                    }
                }
                return new String(cArr, 0, i6);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        String d(ByteBuffer byteBuffer, int i2, int i3) {
            return c(byteBuffer, i2, i3);
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
            return r10 + r0;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int e(CharSequence charSequence, byte[] bArr, int i2, int i3) {
            int i4;
            int i5;
            int i6;
            char charAt;
            int length = charSequence.length();
            int i7 = i3 + i2;
            int i8 = 0;
            while (i8 < length && (i6 = i8 + i2) < i7 && (charAt = charSequence.charAt(i8)) < 128) {
                bArr[i6] = (byte) charAt;
                i8++;
            }
            int i9 = i2 + i8;
            while (i8 < length) {
                char charAt2 = charSequence.charAt(i8);
                if (charAt2 >= 128 || i9 >= i7) {
                    if (charAt2 < 2048 && i9 <= i7 - 2) {
                        int i10 = i9 + 1;
                        bArr[i9] = (byte) ((charAt2 >>> 6) | 960);
                        i9 = i10 + 1;
                        bArr[i10] = (byte) ((charAt2 & '?') | 128);
                    } else if ((charAt2 >= 55296 && 57343 >= charAt2) || i9 > i7 - 3) {
                        if (i9 > i7 - 4) {
                            if (55296 > charAt2 || charAt2 > 57343 || ((i5 = i8 + 1) != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i5)))) {
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i9);
                            }
                            throw new UnpairedSurrogateException(i8, length);
                        }
                        int i11 = i8 + 1;
                        if (i11 != charSequence.length()) {
                            char charAt3 = charSequence.charAt(i11);
                            if (Character.isSurrogatePair(charAt2, charAt3)) {
                                int codePoint = Character.toCodePoint(charAt2, charAt3);
                                int i12 = i9 + 1;
                                bArr[i9] = (byte) ((codePoint >>> 18) | 240);
                                int i13 = i12 + 1;
                                bArr[i12] = (byte) (((codePoint >>> 12) & 63) | 128);
                                int i14 = i13 + 1;
                                bArr[i13] = (byte) (((codePoint >>> 6) & 63) | 128);
                                i9 = i14 + 1;
                                bArr[i14] = (byte) ((codePoint & 63) | 128);
                                i8 = i11;
                            } else {
                                i8 = i11;
                            }
                        }
                        throw new UnpairedSurrogateException(i8 - 1, length);
                    } else {
                        int i15 = i9 + 1;
                        bArr[i9] = (byte) ((charAt2 >>> '\f') | 480);
                        int i16 = i15 + 1;
                        bArr[i15] = (byte) (((charAt2 >>> 6) & 63) | 128);
                        i4 = i16 + 1;
                        bArr[i16] = (byte) ((charAt2 & '?') | 128);
                    }
                    i8++;
                } else {
                    i4 = i9 + 1;
                    bArr[i9] = (byte) charAt2;
                }
                i9 = i4;
                i8++;
            }
            return i9;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        void h(CharSequence charSequence, ByteBuffer byteBuffer) {
            g(charSequence, byteBuffer);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
            if (r8[r9] > (-65)) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0046, code lost:
            if (r8[r9] > (-65)) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x007f, code lost:
            if (r8[r9] > (-65)) goto L51;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int l(int i2, byte[] bArr, int i3, int i4) {
            int i5;
            if (i2 != 0) {
                if (i3 >= i4) {
                    return i2;
                }
                byte b2 = (byte) i2;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        i5 = i3 + 1;
                    }
                    return -1;
                } else if (b2 < -16) {
                    byte b3 = (byte) (~(i2 >> 8));
                    if (b3 == 0) {
                        int i6 = i3 + 1;
                        byte b4 = bArr[i3];
                        if (i6 >= i4) {
                            return Utf8.incompleteStateFor(b2, b4);
                        }
                        i3 = i6;
                        b3 = b4;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        i5 = i3 + 1;
                    }
                    return -1;
                } else {
                    byte b5 = (byte) (~(i2 >> 8));
                    byte b6 = 0;
                    if (b5 == 0) {
                        int i7 = i3 + 1;
                        b5 = bArr[i3];
                        if (i7 >= i4) {
                            return Utf8.incompleteStateFor(b2, b5);
                        }
                        i3 = i7;
                    } else {
                        b6 = (byte) (i2 >> 16);
                    }
                    if (b6 == 0) {
                        int i8 = i3 + 1;
                        b6 = bArr[i3];
                        if (i8 >= i4) {
                            return Utf8.incompleteStateFor(b2, b5, b6);
                        }
                        i3 = i8;
                    }
                    if (b5 <= -65 && (((b2 << Ascii.FS) + (b5 + 112)) >> 30) == 0 && b6 <= -65) {
                        i5 = i3 + 1;
                    }
                    return -1;
                }
                i3 = i5;
            }
            return partialIsValidUtf8(bArr, i3, i4);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        int n(int i2, ByteBuffer byteBuffer, int i3, int i4) {
            return m(i2, byteBuffer, i3, i4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class UnpairedSurrogateException extends IllegalArgumentException {
        /* JADX INFO: Access modifiers changed from: package-private */
        public UnpairedSurrogateException(int i2, int i3) {
            super("Unpaired surrogate at index " + i2 + " of " + i3);
        }
    }

    /* loaded from: classes2.dex */
    static final class UnsafeProcessor extends Processor {
        UnsafeProcessor() {
        }

        static boolean o() {
            return UnsafeUtil.w() && UnsafeUtil.x();
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static int partialIsValidUtf8(long j2, int i2) {
            long j3;
            int unsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(j2, i2);
            long j4 = j2 + unsafeEstimateConsecutiveAscii;
            int i3 = i2 - unsafeEstimateConsecutiveAscii;
            while (true) {
                byte b2 = 0;
                while (true) {
                    if (i3 <= 0) {
                        break;
                    }
                    long j5 = j4 + 1;
                    b2 = UnsafeUtil.n(j4);
                    if (b2 < 0) {
                        j4 = j5;
                        break;
                    }
                    i3--;
                    j4 = j5;
                }
                if (i3 == 0) {
                    return 0;
                }
                int i4 = i3 - 1;
                if (b2 >= -32) {
                    if (b2 >= -16) {
                        if (i4 >= 3) {
                            i3 = i4 - 3;
                            long j6 = j4 + 1;
                            byte n2 = UnsafeUtil.n(j4);
                            if (n2 > -65 || (((b2 << Ascii.FS) + (n2 + 112)) >> 30) != 0) {
                                break;
                            }
                            long j7 = j6 + 1;
                            if (UnsafeUtil.n(j6) > -65) {
                                break;
                            }
                            j3 = 1 + j7;
                            if (UnsafeUtil.n(j7) > -65) {
                                break;
                            }
                        } else {
                            return unsafeIncompleteStateFor(j4, b2, i4);
                        }
                    } else if (i4 >= 2) {
                        i3 = i4 - 2;
                        long j8 = j4 + 1;
                        byte n3 = UnsafeUtil.n(j4);
                        if (n3 > -65 || ((b2 == -32 && n3 < -96) || (b2 == -19 && n3 >= -96))) {
                            break;
                        }
                        j3 = 1 + j8;
                        if (UnsafeUtil.n(j8) > -65) {
                            break;
                        }
                    } else {
                        return unsafeIncompleteStateFor(j4, b2, i4);
                    }
                } else if (i4 != 0) {
                    i3 = i4 - 1;
                    if (b2 < -62) {
                        break;
                    }
                    j3 = 1 + j4;
                    if (UnsafeUtil.n(j4) > -65) {
                        break;
                    }
                } else {
                    return b2;
                }
                j4 = j3;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static int partialIsValidUtf8(byte[] bArr, long j2, int i2) {
            long j3;
            int unsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(bArr, j2, i2);
            int i3 = i2 - unsafeEstimateConsecutiveAscii;
            long j4 = j2 + unsafeEstimateConsecutiveAscii;
            while (true) {
                byte b2 = 0;
                while (true) {
                    if (i3 <= 0) {
                        break;
                    }
                    long j5 = j4 + 1;
                    b2 = UnsafeUtil.o(bArr, j4);
                    if (b2 < 0) {
                        j4 = j5;
                        break;
                    }
                    i3--;
                    j4 = j5;
                }
                if (i3 == 0) {
                    return 0;
                }
                int i4 = i3 - 1;
                if (b2 >= -32) {
                    if (b2 >= -16) {
                        if (i4 >= 3) {
                            i3 = i4 - 3;
                            long j6 = j4 + 1;
                            byte o2 = UnsafeUtil.o(bArr, j4);
                            if (o2 > -65 || (((b2 << Ascii.FS) + (o2 + 112)) >> 30) != 0) {
                                break;
                            }
                            long j7 = j6 + 1;
                            if (UnsafeUtil.o(bArr, j6) > -65) {
                                break;
                            }
                            j3 = 1 + j7;
                            if (UnsafeUtil.o(bArr, j7) > -65) {
                                break;
                            }
                        } else {
                            return unsafeIncompleteStateFor(bArr, b2, j4, i4);
                        }
                    } else if (i4 >= 2) {
                        i3 = i4 - 2;
                        long j8 = j4 + 1;
                        byte o3 = UnsafeUtil.o(bArr, j4);
                        if (o3 > -65 || ((b2 == -32 && o3 < -96) || (b2 == -19 && o3 >= -96))) {
                            break;
                        }
                        j3 = 1 + j8;
                        if (UnsafeUtil.o(bArr, j8) > -65) {
                            break;
                        }
                    } else {
                        return unsafeIncompleteStateFor(bArr, b2, j4, i4);
                    }
                } else if (i4 != 0) {
                    i3 = i4 - 1;
                    if (b2 < -62) {
                        break;
                    }
                    j3 = 1 + j4;
                    if (UnsafeUtil.o(bArr, j4) > -65) {
                        break;
                    }
                } else {
                    return b2;
                }
                j4 = j3;
            }
        }

        private static int unsafeEstimateConsecutiveAscii(long j2, int i2) {
            if (i2 < 16) {
                return 0;
            }
            int i3 = 8 - (((int) j2) & 7);
            int i4 = i3;
            while (i4 > 0) {
                long j3 = 1 + j2;
                if (UnsafeUtil.n(j2) < 0) {
                    return i3 - i4;
                }
                i4--;
                j2 = j3;
            }
            int i5 = i2 - i3;
            while (i5 >= 8 && (UnsafeUtil.s(j2) & Utf8.ASCII_MASK_LONG) == 0) {
                j2 += 8;
                i5 -= 8;
            }
            return i2 - i5;
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bArr, long j2, int i2) {
            int i3 = 0;
            if (i2 < 16) {
                return 0;
            }
            while (i3 < i2) {
                long j3 = 1 + j2;
                if (UnsafeUtil.o(bArr, j2) < 0) {
                    return i3;
                }
                i3++;
                j2 = j3;
            }
            return i2;
        }

        private static int unsafeIncompleteStateFor(long j2, int i2, int i3) {
            if (i3 != 0) {
                if (i3 != 1) {
                    if (i3 == 2) {
                        return Utf8.incompleteStateFor(i2, UnsafeUtil.n(j2), UnsafeUtil.n(j2 + 1));
                    }
                    throw new AssertionError();
                }
                return Utf8.incompleteStateFor(i2, UnsafeUtil.n(j2));
            }
            return Utf8.incompleteStateFor(i2);
        }

        private static int unsafeIncompleteStateFor(byte[] bArr, int i2, long j2, int i3) {
            if (i3 != 0) {
                if (i3 != 1) {
                    if (i3 == 2) {
                        return Utf8.incompleteStateFor(i2, UnsafeUtil.o(bArr, j2), UnsafeUtil.o(bArr, j2 + 1));
                    }
                    throw new AssertionError();
                }
                return Utf8.incompleteStateFor(i2, UnsafeUtil.o(bArr, j2));
            }
            return Utf8.incompleteStateFor(i2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        String b(byte[] bArr, int i2, int i3) {
            if ((i2 | i3 | ((bArr.length - i2) - i3)) >= 0) {
                int i4 = i2 + i3;
                char[] cArr = new char[i3];
                int i5 = 0;
                while (i2 < i4) {
                    byte o2 = UnsafeUtil.o(bArr, i2);
                    if (!DecodeUtil.isOneByte(o2)) {
                        break;
                    }
                    i2++;
                    DecodeUtil.handleOneByte(o2, cArr, i5);
                    i5++;
                }
                int i6 = i5;
                while (i2 < i4) {
                    int i7 = i2 + 1;
                    byte o3 = UnsafeUtil.o(bArr, i2);
                    if (DecodeUtil.isOneByte(o3)) {
                        int i8 = i6 + 1;
                        DecodeUtil.handleOneByte(o3, cArr, i6);
                        while (i7 < i4) {
                            byte o4 = UnsafeUtil.o(bArr, i7);
                            if (!DecodeUtil.isOneByte(o4)) {
                                break;
                            }
                            i7++;
                            DecodeUtil.handleOneByte(o4, cArr, i8);
                            i8++;
                        }
                        i2 = i7;
                        i6 = i8;
                    } else if (DecodeUtil.isTwoBytes(o3)) {
                        if (i7 >= i4) {
                            throw InvalidProtocolBufferException.c();
                        }
                        DecodeUtil.handleTwoBytes(o3, UnsafeUtil.o(bArr, i7), cArr, i6);
                        i2 = i7 + 1;
                        i6++;
                    } else if (DecodeUtil.isThreeBytes(o3)) {
                        if (i7 >= i4 - 1) {
                            throw InvalidProtocolBufferException.c();
                        }
                        int i9 = i7 + 1;
                        DecodeUtil.handleThreeBytes(o3, UnsafeUtil.o(bArr, i7), UnsafeUtil.o(bArr, i9), cArr, i6);
                        i2 = i9 + 1;
                        i6++;
                    } else if (i7 >= i4 - 2) {
                        throw InvalidProtocolBufferException.c();
                    } else {
                        int i10 = i7 + 1;
                        int i11 = i10 + 1;
                        DecodeUtil.handleFourBytes(o3, UnsafeUtil.o(bArr, i7), UnsafeUtil.o(bArr, i10), UnsafeUtil.o(bArr, i11), cArr, i6);
                        i2 = i11 + 1;
                        i6 = i6 + 1 + 1;
                    }
                }
                return new String(cArr, 0, i6);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        String d(ByteBuffer byteBuffer, int i2, int i3) {
            if ((i2 | i3 | ((byteBuffer.limit() - i2) - i3)) >= 0) {
                long i4 = UnsafeUtil.i(byteBuffer) + i2;
                long j2 = i3 + i4;
                char[] cArr = new char[i3];
                int i5 = 0;
                while (i4 < j2) {
                    byte n2 = UnsafeUtil.n(i4);
                    if (!DecodeUtil.isOneByte(n2)) {
                        break;
                    }
                    i4++;
                    DecodeUtil.handleOneByte(n2, cArr, i5);
                    i5++;
                }
                while (true) {
                    int i6 = i5;
                    while (i4 < j2) {
                        long j3 = i4 + 1;
                        byte n3 = UnsafeUtil.n(i4);
                        if (DecodeUtil.isOneByte(n3)) {
                            int i7 = i6 + 1;
                            DecodeUtil.handleOneByte(n3, cArr, i6);
                            while (j3 < j2) {
                                byte n4 = UnsafeUtil.n(j3);
                                if (!DecodeUtil.isOneByte(n4)) {
                                    break;
                                }
                                j3++;
                                DecodeUtil.handleOneByte(n4, cArr, i7);
                                i7++;
                            }
                            i6 = i7;
                            i4 = j3;
                        } else if (DecodeUtil.isTwoBytes(n3)) {
                            if (j3 >= j2) {
                                throw InvalidProtocolBufferException.c();
                            }
                            i4 = j3 + 1;
                            DecodeUtil.handleTwoBytes(n3, UnsafeUtil.n(j3), cArr, i6);
                            i6++;
                        } else if (DecodeUtil.isThreeBytes(n3)) {
                            if (j3 >= j2 - 1) {
                                throw InvalidProtocolBufferException.c();
                            }
                            long j4 = j3 + 1;
                            DecodeUtil.handleThreeBytes(n3, UnsafeUtil.n(j3), UnsafeUtil.n(j4), cArr, i6);
                            i6++;
                            i4 = j4 + 1;
                        } else if (j3 >= j2 - 2) {
                            throw InvalidProtocolBufferException.c();
                        } else {
                            long j5 = j3 + 1;
                            byte n5 = UnsafeUtil.n(j3);
                            long j6 = j5 + 1;
                            byte n6 = UnsafeUtil.n(j5);
                            i4 = j6 + 1;
                            DecodeUtil.handleFourBytes(n3, n5, n6, UnsafeUtil.n(j6), cArr, i6);
                            i5 = i6 + 1 + 1;
                        }
                    }
                    return new String(cArr, 0, i6);
                }
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i2), Integer.valueOf(i3)));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        int e(CharSequence charSequence, byte[] bArr, int i2, int i3) {
            char c2;
            long j2;
            long j3;
            long j4;
            char c3;
            int i4;
            char charAt;
            long j5 = i2;
            long j6 = i3 + j5;
            int length = charSequence.length();
            if (length > i3 || bArr.length - i3 < i2) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length - 1) + " at index " + (i2 + i3));
            }
            int i5 = 0;
            while (true) {
                c2 = 128;
                j2 = 1;
                if (i5 >= length || (charAt = charSequence.charAt(i5)) >= 128) {
                    break;
                }
                UnsafeUtil.B(bArr, j5, (byte) charAt);
                i5++;
                j5 = 1 + j5;
            }
            if (i5 == length) {
                return (int) j5;
            }
            while (i5 < length) {
                char charAt2 = charSequence.charAt(i5);
                if (charAt2 < c2 && j5 < j6) {
                    long j7 = j5 + j2;
                    UnsafeUtil.B(bArr, j5, (byte) charAt2);
                    j4 = j2;
                    j3 = j7;
                    c3 = c2;
                } else if (charAt2 < 2048 && j5 <= j6 - 2) {
                    long j8 = j5 + j2;
                    UnsafeUtil.B(bArr, j5, (byte) ((charAt2 >>> 6) | 960));
                    long j9 = j8 + j2;
                    UnsafeUtil.B(bArr, j8, (byte) ((charAt2 & '?') | 128));
                    long j10 = j2;
                    c3 = 128;
                    j3 = j9;
                    j4 = j10;
                } else if ((charAt2 >= 55296 && 57343 >= charAt2) || j5 > j6 - 3) {
                    if (j5 > j6 - 4) {
                        if (55296 > charAt2 || charAt2 > 57343 || ((i4 = i5 + 1) != length && Character.isSurrogatePair(charAt2, charSequence.charAt(i4)))) {
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + j5);
                        }
                        throw new UnpairedSurrogateException(i5, length);
                    }
                    int i6 = i5 + 1;
                    if (i6 != length) {
                        char charAt3 = charSequence.charAt(i6);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            long j11 = j5 + 1;
                            UnsafeUtil.B(bArr, j5, (byte) ((codePoint >>> 18) | 240));
                            long j12 = j11 + 1;
                            c3 = 128;
                            UnsafeUtil.B(bArr, j11, (byte) (((codePoint >>> 12) & 63) | 128));
                            long j13 = j12 + 1;
                            UnsafeUtil.B(bArr, j12, (byte) (((codePoint >>> 6) & 63) | 128));
                            j4 = 1;
                            j3 = j13 + 1;
                            UnsafeUtil.B(bArr, j13, (byte) ((codePoint & 63) | 128));
                            i5 = i6;
                        } else {
                            i5 = i6;
                        }
                    }
                    throw new UnpairedSurrogateException(i5 - 1, length);
                } else {
                    long j14 = j5 + j2;
                    UnsafeUtil.B(bArr, j5, (byte) ((charAt2 >>> '\f') | 480));
                    long j15 = j14 + j2;
                    UnsafeUtil.B(bArr, j14, (byte) (((charAt2 >>> 6) & 63) | 128));
                    UnsafeUtil.B(bArr, j15, (byte) ((charAt2 & '?') | 128));
                    j3 = j15 + 1;
                    j4 = 1;
                    c3 = 128;
                }
                i5++;
                c2 = c3;
                long j16 = j4;
                j5 = j3;
                j2 = j16;
            }
            return (int) j5;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        void h(CharSequence charSequence, ByteBuffer byteBuffer) {
            char c2;
            int i2;
            long j2;
            int i3;
            int i4;
            long j3;
            char c3;
            char charAt;
            ByteBuffer byteBuffer2 = byteBuffer;
            long i5 = UnsafeUtil.i(byteBuffer);
            long position = byteBuffer.position() + i5;
            long limit = byteBuffer.limit() + i5;
            int length = charSequence.length();
            if (length > limit - position) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length - 1) + " at index " + byteBuffer.limit());
            }
            int i6 = 0;
            while (true) {
                c2 = 128;
                if (i6 >= length || (charAt = charSequence.charAt(i6)) >= 128) {
                    break;
                }
                UnsafeUtil.A(position, (byte) charAt);
                i6++;
                position++;
            }
            if (i6 == length) {
                i2 = (int) (position - i5);
            } else {
                while (i6 < length) {
                    char charAt2 = charSequence.charAt(i6);
                    if (charAt2 >= c2 || position >= limit) {
                        if (charAt2 >= 2048 || position > limit - 2) {
                            j2 = i5;
                            if ((charAt2 >= 55296 && 57343 >= charAt2) || position > limit - 3) {
                                if (position > limit - 4) {
                                    if (55296 <= charAt2 && charAt2 <= 57343 && ((i3 = i6 + 1) == length || !Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                                        throw new UnpairedSurrogateException(i6, length);
                                    }
                                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + position);
                                }
                                i4 = i6 + 1;
                                if (i4 != length) {
                                    char charAt3 = charSequence.charAt(i4);
                                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                                        j3 = limit;
                                        long j4 = position + 1;
                                        UnsafeUtil.A(position, (byte) ((codePoint >>> 18) | 240));
                                        long j5 = j4 + 1;
                                        c3 = 128;
                                        UnsafeUtil.A(j4, (byte) (((codePoint >>> 12) & 63) | 128));
                                        long j6 = j5 + 1;
                                        UnsafeUtil.A(j5, (byte) (((codePoint >>> 6) & 63) | 128));
                                        UnsafeUtil.A(j6, (byte) ((codePoint & 63) | 128));
                                        position = j6 + 1;
                                    } else {
                                        i6 = i4;
                                    }
                                }
                                throw new UnpairedSurrogateException(i6 - 1, length);
                            }
                            long j7 = position + 1;
                            UnsafeUtil.A(position, (byte) ((charAt2 >>> '\f') | 480));
                            long j8 = j7 + 1;
                            UnsafeUtil.A(j7, (byte) (((charAt2 >>> 6) & 63) | 128));
                            UnsafeUtil.A(j8, (byte) ((charAt2 & '?') | 128));
                            position = j8 + 1;
                        } else {
                            j2 = i5;
                            long j9 = position + 1;
                            UnsafeUtil.A(position, (byte) ((charAt2 >>> 6) | 960));
                            UnsafeUtil.A(j9, (byte) ((charAt2 & '?') | 128));
                            position = j9 + 1;
                        }
                        j3 = limit;
                        i4 = i6;
                        c3 = 128;
                    } else {
                        UnsafeUtil.A(position, (byte) charAt2);
                        j3 = limit;
                        i4 = i6;
                        c3 = c2;
                        position++;
                        j2 = i5;
                    }
                    c2 = c3;
                    i5 = j2;
                    limit = j3;
                    i6 = i4 + 1;
                }
                i2 = (int) (position - i5);
                byteBuffer2 = byteBuffer;
            }
            byteBuffer2.position(i2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x0059, code lost:
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.o(r13, r2) > (-65)) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x009e, code lost:
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.o(r13, r2) > (-65)) goto L56;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int l(int i2, byte[] bArr, int i3, int i4) {
            long j2;
            byte b2 = 0;
            if ((i3 | i4 | (bArr.length - i4)) >= 0) {
                long j3 = i3;
                long j4 = i4;
                if (i2 != 0) {
                    if (j3 >= j4) {
                        return i2;
                    }
                    byte b3 = (byte) i2;
                    if (b3 < -32) {
                        if (b3 >= -62) {
                            long j5 = 1 + j3;
                            if (UnsafeUtil.o(bArr, j3) <= -65) {
                                j3 = j5;
                            }
                        }
                        return -1;
                    }
                    if (b3 < -16) {
                        byte b4 = (byte) (~(i2 >> 8));
                        if (b4 == 0) {
                            long j6 = j3 + 1;
                            b4 = UnsafeUtil.o(bArr, j3);
                            if (j6 >= j4) {
                                return Utf8.incompleteStateFor(b3, b4);
                            }
                            j3 = j6;
                        }
                        if (b4 <= -65 && ((b3 != -32 || b4 >= -96) && (b3 != -19 || b4 < -96))) {
                            j2 = j3 + 1;
                        }
                        return -1;
                    }
                    byte b5 = (byte) (~(i2 >> 8));
                    if (b5 == 0) {
                        long j7 = j3 + 1;
                        b5 = UnsafeUtil.o(bArr, j3);
                        if (j7 >= j4) {
                            return Utf8.incompleteStateFor(b3, b5);
                        }
                        j3 = j7;
                    } else {
                        b2 = (byte) (i2 >> 16);
                    }
                    if (b2 == 0) {
                        long j8 = j3 + 1;
                        b2 = UnsafeUtil.o(bArr, j3);
                        if (j8 >= j4) {
                            return Utf8.incompleteStateFor(b3, b5, b2);
                        }
                        j3 = j8;
                    }
                    if (b5 <= -65 && (((b3 << Ascii.FS) + (b5 + 112)) >> 30) == 0 && b2 <= -65) {
                        j2 = j3 + 1;
                    }
                    return -1;
                    j3 = j2;
                }
                return partialIsValidUtf8(bArr, j3, (int) (j4 - j3));
            }
            throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i3), Integer.valueOf(i4)));
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x0063, code lost:
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.n(r2) > (-65)) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00a8, code lost:
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.n(r2) > (-65)) goto L56;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int n(int i2, ByteBuffer byteBuffer, int i3, int i4) {
            long j2;
            byte b2 = 0;
            if ((i3 | i4 | (byteBuffer.limit() - i4)) >= 0) {
                long i5 = UnsafeUtil.i(byteBuffer) + i3;
                long j3 = (i4 - i3) + i5;
                if (i2 != 0) {
                    if (i5 >= j3) {
                        return i2;
                    }
                    byte b3 = (byte) i2;
                    if (b3 < -32) {
                        if (b3 >= -62) {
                            long j4 = 1 + i5;
                            if (UnsafeUtil.n(i5) <= -65) {
                                i5 = j4;
                            }
                        }
                        return -1;
                    }
                    if (b3 < -16) {
                        byte b4 = (byte) (~(i2 >> 8));
                        if (b4 == 0) {
                            long j5 = i5 + 1;
                            b4 = UnsafeUtil.n(i5);
                            if (j5 >= j3) {
                                return Utf8.incompleteStateFor(b3, b4);
                            }
                            i5 = j5;
                        }
                        if (b4 <= -65 && ((b3 != -32 || b4 >= -96) && (b3 != -19 || b4 < -96))) {
                            j2 = i5 + 1;
                        }
                        return -1;
                    }
                    byte b5 = (byte) (~(i2 >> 8));
                    if (b5 == 0) {
                        long j6 = i5 + 1;
                        b5 = UnsafeUtil.n(i5);
                        if (j6 >= j3) {
                            return Utf8.incompleteStateFor(b3, b5);
                        }
                        i5 = j6;
                    } else {
                        b2 = (byte) (i2 >> 16);
                    }
                    if (b2 == 0) {
                        long j7 = i5 + 1;
                        b2 = UnsafeUtil.n(i5);
                        if (j7 >= j3) {
                            return Utf8.incompleteStateFor(b3, b5, b2);
                        }
                        i5 = j7;
                    }
                    if (b5 <= -65 && (((b3 << Ascii.FS) + (b5 + 112)) >> 30) == 0 && b2 <= -65) {
                        j2 = i5 + 1;
                    }
                    return -1;
                    i5 = j2;
                }
                return partialIsValidUtf8(i5, (int) (j3 - i5));
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i3), Integer.valueOf(i4)));
        }
    }

    static {
        processor = (!UnsafeProcessor.o() || Android.b()) ? new SafeProcessor() : new UnsafeProcessor();
    }

    private Utf8() {
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i2) {
        int length = charSequence.length();
        int i3 = 0;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
            } else {
                i3 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i2) < 65536) {
                        throw new UnpairedSurrogateException(i2, length);
                    }
                    i2++;
                }
            }
            i2++;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int estimateConsecutiveAscii(ByteBuffer byteBuffer, int i2, int i3) {
        int i4 = i3 - 7;
        int i5 = i2;
        while (i5 < i4 && (byteBuffer.getLong(i5) & ASCII_MASK_LONG) == 0) {
            i5 += 8;
        }
        return i5 - i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String g(ByteBuffer byteBuffer, int i2, int i3) {
        return processor.a(byteBuffer, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String h(byte[] bArr, int i2, int i3) {
        return processor.b(bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(CharSequence charSequence, byte[] bArr, int i2, int i3) {
        return processor.e(charSequence, bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i2) {
        if (i2 > -12) {
            return -1;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i2, int i3) {
        if (i2 > -12 || i3 > -65) {
            return -1;
        }
        return i2 ^ (i3 << 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i2, int i3, int i4) {
        if (i2 > -12 || i3 > -65 || i4 > -65) {
            return -1;
        }
        return (i2 ^ (i3 << 8)) ^ (i4 << 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(ByteBuffer byteBuffer, int i2, int i3, int i4) {
        if (i4 != 0) {
            if (i4 != 1) {
                if (i4 == 2) {
                    return incompleteStateFor(i2, byteBuffer.get(i3), byteBuffer.get(i3 + 1));
                }
                throw new AssertionError();
            }
            return incompleteStateFor(i2, byteBuffer.get(i3));
        }
        return incompleteStateFor(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(byte[] bArr, int i2, int i3) {
        byte b2 = bArr[i2 - 1];
        int i4 = i3 - i2;
        if (i4 != 0) {
            if (i4 != 1) {
                if (i4 == 2) {
                    return incompleteStateFor(b2, bArr[i2], bArr[i2 + 1]);
                }
                throw new AssertionError();
            }
            return incompleteStateFor(b2, bArr[i2]);
        }
        return incompleteStateFor(b2);
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return processor.j(bArr, 0, bArr.length);
    }

    public static boolean isValidUtf8(byte[] bArr, int i2, int i3) {
        return processor.j(bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void j(CharSequence charSequence, ByteBuffer byteBuffer) {
        processor.f(charSequence, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(CharSequence charSequence) {
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 < length) {
                char charAt = charSequence.charAt(i2);
                if (charAt >= 2048) {
                    i3 += encodedLengthGeneral(charSequence, i2);
                    break;
                }
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                break;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i3 + 4294967296L));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean l(ByteBuffer byteBuffer) {
        return processor.i(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(int i2, ByteBuffer byteBuffer, int i3, int i4) {
        return processor.k(i2, byteBuffer, i3, i4);
    }

    public static int partialIsValidUtf8(int i2, byte[] bArr, int i3, int i4) {
        return processor.l(i2, bArr, i3, i4);
    }
}
