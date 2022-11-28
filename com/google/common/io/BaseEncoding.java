package com.google.common.io;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public abstract class BaseEncoding {
    private static final BaseEncoding BASE64 = new Base64Encoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final BaseEncoding BASE64_URL = new Base64Encoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
    private static final BaseEncoding BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final BaseEncoding BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final BaseEncoding BASE16 = new Base16Encoding("base16()", "0123456789ABCDEF");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Alphabet {

        /* renamed from: a  reason: collision with root package name */
        final int f9270a;

        /* renamed from: b  reason: collision with root package name */
        final int f9271b;

        /* renamed from: c  reason: collision with root package name */
        final int f9272c;
        private final char[] chars;

        /* renamed from: d  reason: collision with root package name */
        final int f9273d;
        private final byte[] decodabet;
        private final String name;
        private final boolean[] validPadding;

        Alphabet(String str, char[] cArr) {
            this.name = (String) Preconditions.checkNotNull(str);
            this.chars = (char[]) Preconditions.checkNotNull(cArr);
            try {
                int log2 = IntMath.log2(cArr.length, RoundingMode.UNNECESSARY);
                this.f9271b = log2;
                int min = Math.min(8, Integer.lowestOneBit(log2));
                try {
                    this.f9272c = 8 / min;
                    this.f9273d = log2 / min;
                    this.f9270a = cArr.length - 1;
                    byte[] bArr = new byte[128];
                    Arrays.fill(bArr, (byte) -1);
                    for (int i2 = 0; i2 < cArr.length; i2++) {
                        char c2 = cArr[i2];
                        Preconditions.checkArgument(c2 < 128, "Non-ASCII character: %s", c2);
                        Preconditions.checkArgument(bArr[c2] == -1, "Duplicate character: %s", c2);
                        bArr[c2] = (byte) i2;
                    }
                    this.decodabet = bArr;
                    boolean[] zArr = new boolean[this.f9272c];
                    for (int i3 = 0; i3 < this.f9273d; i3++) {
                        zArr[IntMath.divide(i3 * 8, this.f9271b, RoundingMode.CEILING)] = true;
                    }
                    this.validPadding = zArr;
                } catch (ArithmeticException e2) {
                    throw new IllegalArgumentException("Illegal alphabet " + new String(cArr), e2);
                }
            } catch (ArithmeticException e3) {
                throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e3);
            }
        }

        private boolean hasLowerCase() {
            for (char c2 : this.chars) {
                if (Ascii.isLowerCase(c2)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasUpperCase() {
            for (char c2 : this.chars) {
                if (Ascii.isUpperCase(c2)) {
                    return true;
                }
            }
            return false;
        }

        boolean b(char c2) {
            return c2 <= 127 && this.decodabet[c2] != -1;
        }

        int c(char c2) {
            if (c2 > 127) {
                throw new DecodingException("Unrecognized character: 0x" + Integer.toHexString(c2));
            }
            byte b2 = this.decodabet[c2];
            if (b2 == -1) {
                if (c2 <= ' ' || c2 == 127) {
                    throw new DecodingException("Unrecognized character: 0x" + Integer.toHexString(c2));
                }
                throw new DecodingException("Unrecognized character: " + c2);
            }
            return b2;
        }

        char d(int i2) {
            return this.chars[i2];
        }

        boolean e(int i2) {
            return this.validPadding[i2 % this.f9272c];
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof Alphabet) {
                return Arrays.equals(this.chars, ((Alphabet) obj).chars);
            }
            return false;
        }

        Alphabet f() {
            if (!hasUpperCase()) {
                return this;
            }
            Preconditions.checkState(!hasLowerCase(), "Cannot call lowerCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int i2 = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (i2 >= cArr2.length) {
                    return new Alphabet(this.name + ".lowerCase()", cArr);
                }
                cArr[i2] = Ascii.toLowerCase(cArr2[i2]);
                i2++;
            }
        }

        Alphabet g() {
            if (!hasLowerCase()) {
                return this;
            }
            Preconditions.checkState(!hasUpperCase(), "Cannot call upperCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int i2 = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (i2 >= cArr2.length) {
                    return new Alphabet(this.name + ".upperCase()", cArr);
                }
                cArr[i2] = Ascii.toUpperCase(cArr2[i2]);
                i2++;
            }
        }

        public int hashCode() {
            return Arrays.hashCode(this.chars);
        }

        public boolean matches(char c2) {
            byte[] bArr = this.decodabet;
            return c2 < bArr.length && bArr[c2] != -1;
        }

        public String toString() {
            return this.name;
        }
    }

    /* loaded from: classes2.dex */
    static final class Base16Encoding extends StandardBaseEncoding {

        /* renamed from: c  reason: collision with root package name */
        final char[] f9274c;

        private Base16Encoding(Alphabet alphabet) {
            super(alphabet, null);
            this.f9274c = new char[512];
            Preconditions.checkArgument(alphabet.chars.length == 16);
            for (int i2 = 0; i2 < 256; i2++) {
                this.f9274c[i2] = alphabet.d(i2 >>> 4);
                this.f9274c[i2 | 256] = alphabet.d(i2 & 15);
            }
        }

        Base16Encoding(String str, String str2) {
            this(new Alphabet(str, str2.toCharArray()));
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        int b(byte[] bArr, CharSequence charSequence) {
            Preconditions.checkNotNull(bArr);
            if (charSequence.length() % 2 == 1) {
                throw new DecodingException("Invalid input length " + charSequence.length());
            }
            int i2 = 0;
            int i3 = 0;
            while (i2 < charSequence.length()) {
                bArr[i3] = (byte) ((this.f9275a.c(charSequence.charAt(i2)) << 4) | this.f9275a.c(charSequence.charAt(i2 + 1)));
                i2 += 2;
                i3++;
            }
            return i3;
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        void c(Appendable appendable, byte[] bArr, int i2, int i3) {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i2, i2 + i3, bArr.length);
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = bArr[i2 + i4] & 255;
                appendable.append(this.f9274c[i5]);
                appendable.append(this.f9274c[i5 | 256]);
            }
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding
        BaseEncoding k(Alphabet alphabet, @NullableDecl Character ch) {
            return new Base16Encoding(alphabet);
        }
    }

    /* loaded from: classes2.dex */
    static final class Base64Encoding extends StandardBaseEncoding {
        private Base64Encoding(Alphabet alphabet, @NullableDecl Character ch) {
            super(alphabet, ch);
            Preconditions.checkArgument(alphabet.chars.length == 64);
        }

        Base64Encoding(String str, String str2, @NullableDecl Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        int b(byte[] bArr, CharSequence charSequence) {
            Preconditions.checkNotNull(bArr);
            CharSequence i2 = i(charSequence);
            if (!this.f9275a.e(i2.length())) {
                throw new DecodingException("Invalid input length " + i2.length());
            }
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2.length()) {
                int i5 = i3 + 1;
                int i6 = i5 + 1;
                int c2 = (this.f9275a.c(i2.charAt(i3)) << 18) | (this.f9275a.c(i2.charAt(i5)) << 12);
                int i7 = i4 + 1;
                bArr[i4] = (byte) (c2 >>> 16);
                if (i6 < i2.length()) {
                    int i8 = i6 + 1;
                    int c3 = c2 | (this.f9275a.c(i2.charAt(i6)) << 6);
                    i4 = i7 + 1;
                    bArr[i7] = (byte) ((c3 >>> 8) & 255);
                    if (i8 < i2.length()) {
                        i6 = i8 + 1;
                        i7 = i4 + 1;
                        bArr[i4] = (byte) ((c3 | this.f9275a.c(i2.charAt(i8))) & 255);
                    } else {
                        i3 = i8;
                    }
                }
                i4 = i7;
                i3 = i6;
            }
            return i4;
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding, com.google.common.io.BaseEncoding
        void c(Appendable appendable, byte[] bArr, int i2, int i3) {
            Preconditions.checkNotNull(appendable);
            int i4 = i2 + i3;
            Preconditions.checkPositionIndexes(i2, i4, bArr.length);
            while (i3 >= 3) {
                int i5 = i2 + 1;
                int i6 = i5 + 1;
                int i7 = ((bArr[i2] & 255) << 16) | ((bArr[i5] & 255) << 8) | (bArr[i6] & 255);
                appendable.append(this.f9275a.d(i7 >>> 18));
                appendable.append(this.f9275a.d((i7 >>> 12) & 63));
                appendable.append(this.f9275a.d((i7 >>> 6) & 63));
                appendable.append(this.f9275a.d(i7 & 63));
                i3 -= 3;
                i2 = i6 + 1;
            }
            if (i2 < i4) {
                j(appendable, bArr, i2, i4 - i2);
            }
        }

        @Override // com.google.common.io.BaseEncoding.StandardBaseEncoding
        BaseEncoding k(Alphabet alphabet, @NullableDecl Character ch) {
            return new Base64Encoding(alphabet, ch);
        }
    }

    /* loaded from: classes2.dex */
    public static final class DecodingException extends IOException {
        DecodingException(String str) {
            super(str);
        }
    }

    /* loaded from: classes2.dex */
    static final class SeparatedBaseEncoding extends BaseEncoding {
        private final int afterEveryChars;
        private final BaseEncoding delegate;
        private final String separator;

        SeparatedBaseEncoding(BaseEncoding baseEncoding, String str, int i2) {
            this.delegate = (BaseEncoding) Preconditions.checkNotNull(baseEncoding);
            this.separator = (String) Preconditions.checkNotNull(str);
            this.afterEveryChars = i2;
            Preconditions.checkArgument(i2 > 0, "Cannot add a separator after every %s chars", i2);
        }

        @Override // com.google.common.io.BaseEncoding
        int b(byte[] bArr, CharSequence charSequence) {
            StringBuilder sb = new StringBuilder(charSequence.length());
            for (int i2 = 0; i2 < charSequence.length(); i2++) {
                char charAt = charSequence.charAt(i2);
                if (this.separator.indexOf(charAt) < 0) {
                    sb.append(charAt);
                }
            }
            return this.delegate.b(bArr, sb);
        }

        @Override // com.google.common.io.BaseEncoding
        void c(Appendable appendable, byte[] bArr, int i2, int i3) {
            this.delegate.c(BaseEncoding.g(appendable, this.separator, this.afterEveryChars), bArr, i2, i3);
        }

        @Override // com.google.common.io.BaseEncoding
        public boolean canDecode(CharSequence charSequence) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < charSequence.length(); i2++) {
                char charAt = charSequence.charAt(i2);
                if (this.separator.indexOf(charAt) < 0) {
                    sb.append(charAt);
                }
            }
            return this.delegate.canDecode(sb);
        }

        @Override // com.google.common.io.BaseEncoding
        @GwtIncompatible
        public InputStream decodingStream(Reader reader) {
            return this.delegate.decodingStream(BaseEncoding.d(reader, this.separator));
        }

        @Override // com.google.common.io.BaseEncoding
        int e(int i2) {
            return this.delegate.e(i2);
        }

        @Override // com.google.common.io.BaseEncoding
        @GwtIncompatible
        public OutputStream encodingStream(Writer writer) {
            return this.delegate.encodingStream(BaseEncoding.h(writer, this.separator, this.afterEveryChars));
        }

        @Override // com.google.common.io.BaseEncoding
        int f(int i2) {
            int f2 = this.delegate.f(i2);
            return f2 + (this.separator.length() * IntMath.divide(Math.max(0, f2 - 1), this.afterEveryChars, RoundingMode.FLOOR));
        }

        @Override // com.google.common.io.BaseEncoding
        CharSequence i(CharSequence charSequence) {
            return this.delegate.i(charSequence);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding lowerCase() {
            return this.delegate.lowerCase().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.delegate.omitPadding().withSeparator(this.separator, this.afterEveryChars);
        }

        public String toString() {
            return this.delegate + ".withSeparator(\"" + this.separator + "\", " + this.afterEveryChars + ")";
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding upperCase() {
            return this.delegate.upperCase().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withPadChar(char c2) {
            return this.delegate.withPadChar(c2).withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withSeparator(String str, int i2) {
            throw new UnsupportedOperationException("Already have a separator");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class StandardBaseEncoding extends BaseEncoding {

        /* renamed from: a  reason: collision with root package name */
        final Alphabet f9275a;
        @NullableDecl

        /* renamed from: b  reason: collision with root package name */
        final Character f9276b;
        @NullableDecl
        @LazyInit
        private transient BaseEncoding lowerCase;
        @NullableDecl
        @LazyInit
        private transient BaseEncoding upperCase;

        StandardBaseEncoding(Alphabet alphabet, @NullableDecl Character ch) {
            this.f9275a = (Alphabet) Preconditions.checkNotNull(alphabet);
            Preconditions.checkArgument(ch == null || !alphabet.matches(ch.charValue()), "Padding character %s was already in alphabet", ch);
            this.f9276b = ch;
        }

        StandardBaseEncoding(String str, String str2, @NullableDecl Character ch) {
            this(new Alphabet(str, str2.toCharArray()), ch);
        }

        @Override // com.google.common.io.BaseEncoding
        int b(byte[] bArr, CharSequence charSequence) {
            Alphabet alphabet;
            Preconditions.checkNotNull(bArr);
            CharSequence i2 = i(charSequence);
            if (!this.f9275a.e(i2.length())) {
                throw new DecodingException("Invalid input length " + i2.length());
            }
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2.length()) {
                long j2 = 0;
                int i5 = 0;
                int i6 = 0;
                while (true) {
                    alphabet = this.f9275a;
                    if (i5 >= alphabet.f9272c) {
                        break;
                    }
                    j2 <<= alphabet.f9271b;
                    if (i3 + i5 < i2.length()) {
                        j2 |= this.f9275a.c(i2.charAt(i6 + i3));
                        i6++;
                    }
                    i5++;
                }
                int i7 = alphabet.f9273d;
                int i8 = (i7 * 8) - (i6 * alphabet.f9271b);
                int i9 = (i7 - 1) * 8;
                while (i9 >= i8) {
                    bArr[i4] = (byte) ((j2 >>> i9) & 255);
                    i9 -= 8;
                    i4++;
                }
                i3 += this.f9275a.f9272c;
            }
            return i4;
        }

        @Override // com.google.common.io.BaseEncoding
        void c(Appendable appendable, byte[] bArr, int i2, int i3) {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i2, i2 + i3, bArr.length);
            int i4 = 0;
            while (i4 < i3) {
                j(appendable, bArr, i2 + i4, Math.min(this.f9275a.f9273d, i3 - i4));
                i4 += this.f9275a.f9273d;
            }
        }

        @Override // com.google.common.io.BaseEncoding
        public boolean canDecode(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            CharSequence i2 = i(charSequence);
            if (this.f9275a.e(i2.length())) {
                for (int i3 = 0; i3 < i2.length(); i3++) {
                    if (!this.f9275a.b(i2.charAt(i3))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        @Override // com.google.common.io.BaseEncoding
        @GwtIncompatible
        public InputStream decodingStream(final Reader reader) {
            Preconditions.checkNotNull(reader);
            return new InputStream() { // from class: com.google.common.io.BaseEncoding.StandardBaseEncoding.2

                /* renamed from: a  reason: collision with root package name */
                int f9282a = 0;

                /* renamed from: b  reason: collision with root package name */
                int f9283b = 0;

                /* renamed from: c  reason: collision with root package name */
                int f9284c = 0;

                /* renamed from: d  reason: collision with root package name */
                boolean f9285d = false;

                @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    reader.close();
                }

                /* JADX WARN: Code restructure failed: missing block: B:24:0x0074, code lost:
                    throw new com.google.common.io.BaseEncoding.DecodingException("Padding cannot start at index " + r4.f9284c);
                 */
                @Override // java.io.InputStream
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public int read() {
                    int i2;
                    while (true) {
                        int read = reader.read();
                        if (read == -1) {
                            if (this.f9285d || StandardBaseEncoding.this.f9275a.e(this.f9284c)) {
                                return -1;
                            }
                            throw new DecodingException("Invalid input length " + this.f9284c);
                        }
                        this.f9284c++;
                        char c2 = (char) read;
                        Character ch = StandardBaseEncoding.this.f9276b;
                        if (ch == null || ch.charValue() != c2) {
                            if (this.f9285d) {
                                throw new DecodingException("Expected padding character but found '" + c2 + "' at index " + this.f9284c);
                            }
                            int i3 = this.f9282a;
                            Alphabet alphabet = StandardBaseEncoding.this.f9275a;
                            int i4 = i3 << alphabet.f9271b;
                            this.f9282a = i4;
                            int c3 = alphabet.c(c2) | i4;
                            this.f9282a = c3;
                            int i5 = this.f9283b + StandardBaseEncoding.this.f9275a.f9271b;
                            this.f9283b = i5;
                            if (i5 >= 8) {
                                int i6 = i5 - 8;
                                this.f9283b = i6;
                                return (c3 >> i6) & 255;
                            }
                        } else if (this.f9285d || ((i2 = this.f9284c) != 1 && StandardBaseEncoding.this.f9275a.e(i2 - 1))) {
                            this.f9285d = true;
                        }
                    }
                }

                @Override // java.io.InputStream
                public int read(byte[] bArr, int i2, int i3) {
                    int i4 = i3 + i2;
                    Preconditions.checkPositionIndexes(i2, i4, bArr.length);
                    int i5 = i2;
                    while (i5 < i4) {
                        int read = read();
                        if (read == -1) {
                            int i6 = i5 - i2;
                            if (i6 == 0) {
                                return -1;
                            }
                            return i6;
                        }
                        bArr[i5] = (byte) read;
                        i5++;
                    }
                    return i5 - i2;
                }
            };
        }

        @Override // com.google.common.io.BaseEncoding
        int e(int i2) {
            return (int) (((this.f9275a.f9271b * i2) + 7) / 8);
        }

        @Override // com.google.common.io.BaseEncoding
        @GwtIncompatible
        public OutputStream encodingStream(final Writer writer) {
            Preconditions.checkNotNull(writer);
            return new OutputStream() { // from class: com.google.common.io.BaseEncoding.StandardBaseEncoding.1

                /* renamed from: a  reason: collision with root package name */
                int f9277a = 0;

                /* renamed from: b  reason: collision with root package name */
                int f9278b = 0;

                /* renamed from: c  reason: collision with root package name */
                int f9279c = 0;

                @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    int i2 = this.f9278b;
                    if (i2 > 0) {
                        int i3 = this.f9277a;
                        Alphabet alphabet = StandardBaseEncoding.this.f9275a;
                        writer.write(alphabet.d((i3 << (alphabet.f9271b - i2)) & alphabet.f9270a));
                        this.f9279c++;
                        if (StandardBaseEncoding.this.f9276b != null) {
                            while (true) {
                                int i4 = this.f9279c;
                                StandardBaseEncoding standardBaseEncoding = StandardBaseEncoding.this;
                                if (i4 % standardBaseEncoding.f9275a.f9272c == 0) {
                                    break;
                                }
                                writer.write(standardBaseEncoding.f9276b.charValue());
                                this.f9279c++;
                            }
                        }
                    }
                    writer.close();
                }

                @Override // java.io.OutputStream, java.io.Flushable
                public void flush() {
                    writer.flush();
                }

                @Override // java.io.OutputStream
                public void write(int i2) {
                    int i3 = this.f9277a << 8;
                    this.f9277a = i3;
                    this.f9277a = (i2 & 255) | i3;
                    int i4 = this.f9278b + 8;
                    while (true) {
                        this.f9278b = i4;
                        int i5 = this.f9278b;
                        Alphabet alphabet = StandardBaseEncoding.this.f9275a;
                        int i6 = alphabet.f9271b;
                        if (i5 < i6) {
                            return;
                        }
                        writer.write(alphabet.d((this.f9277a >> (i5 - i6)) & alphabet.f9270a));
                        this.f9279c++;
                        i4 = this.f9278b - StandardBaseEncoding.this.f9275a.f9271b;
                    }
                }
            };
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof StandardBaseEncoding) {
                StandardBaseEncoding standardBaseEncoding = (StandardBaseEncoding) obj;
                return this.f9275a.equals(standardBaseEncoding.f9275a) && Objects.equal(this.f9276b, standardBaseEncoding.f9276b);
            }
            return false;
        }

        @Override // com.google.common.io.BaseEncoding
        int f(int i2) {
            Alphabet alphabet = this.f9275a;
            return alphabet.f9272c * IntMath.divide(i2, alphabet.f9273d, RoundingMode.CEILING);
        }

        public int hashCode() {
            return this.f9275a.hashCode() ^ Objects.hashCode(this.f9276b);
        }

        @Override // com.google.common.io.BaseEncoding
        CharSequence i(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            Character ch = this.f9276b;
            if (ch == null) {
                return charSequence;
            }
            char charValue = ch.charValue();
            int length = charSequence.length() - 1;
            while (length >= 0 && charSequence.charAt(length) == charValue) {
                length--;
            }
            return charSequence.subSequence(0, length + 1);
        }

        void j(Appendable appendable, byte[] bArr, int i2, int i3) {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i2, i2 + i3, bArr.length);
            int i4 = 0;
            Preconditions.checkArgument(i3 <= this.f9275a.f9273d);
            long j2 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                j2 = (j2 | (bArr[i2 + i5] & 255)) << 8;
            }
            int i6 = ((i3 + 1) * 8) - this.f9275a.f9271b;
            while (i4 < i3 * 8) {
                Alphabet alphabet = this.f9275a;
                appendable.append(alphabet.d(((int) (j2 >>> (i6 - i4))) & alphabet.f9270a));
                i4 += this.f9275a.f9271b;
            }
            if (this.f9276b != null) {
                while (i4 < this.f9275a.f9273d * 8) {
                    appendable.append(this.f9276b.charValue());
                    i4 += this.f9275a.f9271b;
                }
            }
        }

        BaseEncoding k(Alphabet alphabet, @NullableDecl Character ch) {
            return new StandardBaseEncoding(alphabet, ch);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding lowerCase() {
            BaseEncoding baseEncoding = this.lowerCase;
            if (baseEncoding == null) {
                Alphabet f2 = this.f9275a.f();
                baseEncoding = f2 == this.f9275a ? this : k(f2, this.f9276b);
                this.lowerCase = baseEncoding;
            }
            return baseEncoding;
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.f9276b == null ? this : k(this.f9275a, null);
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder("BaseEncoding.");
            sb.append(this.f9275a.toString());
            if (8 % this.f9275a.f9271b != 0) {
                if (this.f9276b == null) {
                    str = ".omitPadding()";
                } else {
                    sb.append(".withPadChar('");
                    sb.append(this.f9276b);
                    str = "')";
                }
                sb.append(str);
            }
            return sb.toString();
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding upperCase() {
            BaseEncoding baseEncoding = this.upperCase;
            if (baseEncoding == null) {
                Alphabet g2 = this.f9275a.g();
                baseEncoding = g2 == this.f9275a ? this : k(g2, this.f9276b);
                this.upperCase = baseEncoding;
            }
            return baseEncoding;
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withPadChar(char c2) {
            Character ch;
            return (8 % this.f9275a.f9271b == 0 || ((ch = this.f9276b) != null && ch.charValue() == c2)) ? this : k(this.f9275a, Character.valueOf(c2));
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withSeparator(String str, int i2) {
            for (int i3 = 0; i3 < str.length(); i3++) {
                Preconditions.checkArgument(!this.f9275a.matches(str.charAt(i3)), "Separator (%s) cannot contain alphabet characters", str);
            }
            Character ch = this.f9276b;
            if (ch != null) {
                Preconditions.checkArgument(str.indexOf(ch.charValue()) < 0, "Separator (%s) cannot contain padding character", str);
            }
            return new SeparatedBaseEncoding(this, str, i2);
        }
    }

    BaseEncoding() {
    }

    public static BaseEncoding base16() {
        return BASE16;
    }

    public static BaseEncoding base32() {
        return BASE32;
    }

    public static BaseEncoding base32Hex() {
        return BASE32_HEX;
    }

    public static BaseEncoding base64() {
        return BASE64;
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    @GwtIncompatible
    static Reader d(final Reader reader, final String str) {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(str);
        return new Reader() { // from class: com.google.common.io.BaseEncoding.3
            @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                reader.close();
            }

            @Override // java.io.Reader
            public int read() {
                int read;
                do {
                    read = reader.read();
                    if (read == -1) {
                        break;
                    }
                } while (str.indexOf((char) read) >= 0);
                return read;
            }

            @Override // java.io.Reader
            public int read(char[] cArr, int i2, int i3) {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static byte[] extract(byte[] bArr, int i2) {
        if (i2 == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }

    static Appendable g(Appendable appendable, String str, int i2) {
        Preconditions.checkNotNull(appendable);
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(i2 > 0);
        return new Appendable(i2, appendable, str) { // from class: com.google.common.io.BaseEncoding.4

            /* renamed from: a  reason: collision with root package name */
            int f9264a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ int f9265b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ Appendable f9266c;

            /* renamed from: d  reason: collision with root package name */
            final /* synthetic */ String f9267d;

            {
                this.f9265b = i2;
                this.f9266c = appendable;
                this.f9267d = str;
                this.f9264a = i2;
            }

            @Override // java.lang.Appendable
            public Appendable append(char c2) {
                if (this.f9264a == 0) {
                    this.f9266c.append(this.f9267d);
                    this.f9264a = this.f9265b;
                }
                this.f9266c.append(c2);
                this.f9264a--;
                return this;
            }

            @Override // java.lang.Appendable
            public Appendable append(@NullableDecl CharSequence charSequence) {
                throw new UnsupportedOperationException();
            }

            @Override // java.lang.Appendable
            public Appendable append(@NullableDecl CharSequence charSequence, int i3, int i4) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @GwtIncompatible
    static Writer h(final Writer writer, String str, int i2) {
        final Appendable g2 = g(writer, str, i2);
        return new Writer() { // from class: com.google.common.io.BaseEncoding.5
            @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                writer.close();
            }

            @Override // java.io.Writer, java.io.Flushable
            public void flush() {
                writer.flush();
            }

            @Override // java.io.Writer
            public void write(int i3) {
                g2.append((char) i3);
            }

            @Override // java.io.Writer
            public void write(char[] cArr, int i3, int i4) {
                throw new UnsupportedOperationException();
            }
        };
    }

    final byte[] a(CharSequence charSequence) {
        CharSequence i2 = i(charSequence);
        byte[] bArr = new byte[e(i2.length())];
        return extract(bArr, b(bArr, i2));
    }

    abstract int b(byte[] bArr, CharSequence charSequence);

    abstract void c(Appendable appendable, byte[] bArr, int i2, int i3);

    public abstract boolean canDecode(CharSequence charSequence);

    public final byte[] decode(CharSequence charSequence) {
        try {
            return a(charSequence);
        } catch (DecodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @GwtIncompatible
    public final ByteSource decodingSource(final CharSource charSource) {
        Preconditions.checkNotNull(charSource);
        return new ByteSource() { // from class: com.google.common.io.BaseEncoding.2
            @Override // com.google.common.io.ByteSource
            public InputStream openStream() {
                return BaseEncoding.this.decodingStream(charSource.openStream());
            }
        };
    }

    @GwtIncompatible
    public abstract InputStream decodingStream(Reader reader);

    abstract int e(int i2);

    public String encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public final String encode(byte[] bArr, int i2, int i3) {
        Preconditions.checkPositionIndexes(i2, i2 + i3, bArr.length);
        StringBuilder sb = new StringBuilder(f(i3));
        try {
            c(sb, bArr, i2, i3);
            return sb.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    @GwtIncompatible
    public final ByteSink encodingSink(final CharSink charSink) {
        Preconditions.checkNotNull(charSink);
        return new ByteSink() { // from class: com.google.common.io.BaseEncoding.1
            @Override // com.google.common.io.ByteSink
            public OutputStream openStream() {
                return BaseEncoding.this.encodingStream(charSink.openStream());
            }
        };
    }

    @GwtIncompatible
    public abstract OutputStream encodingStream(Writer writer);

    abstract int f(int i2);

    CharSequence i(CharSequence charSequence) {
        return (CharSequence) Preconditions.checkNotNull(charSequence);
    }

    public abstract BaseEncoding lowerCase();

    public abstract BaseEncoding omitPadding();

    public abstract BaseEncoding upperCase();

    public abstract BaseEncoding withPadChar(char c2);

    public abstract BaseEncoding withSeparator(String str, int i2);
}
