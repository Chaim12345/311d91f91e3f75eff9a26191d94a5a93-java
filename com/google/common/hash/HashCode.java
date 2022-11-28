package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInts;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes2.dex */
public abstract class HashCode {
    private static final char[] hexDigits = "0123456789abcdef".toCharArray();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class BytesHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final byte[] f9232a;

        BytesHashCode(byte[] bArr) {
            this.f9232a = (byte[]) Preconditions.checkNotNull(bArr);
        }

        @Override // com.google.common.hash.HashCode
        boolean a(HashCode hashCode) {
            if (this.f9232a.length != hashCode.c().length) {
                return false;
            }
            boolean z = true;
            int i2 = 0;
            while (true) {
                byte[] bArr = this.f9232a;
                if (i2 >= bArr.length) {
                    return z;
                }
                z &= bArr[i2] == hashCode.c()[i2];
                i2++;
            }
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            return (byte[]) this.f9232a.clone();
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            byte[] bArr = this.f9232a;
            Preconditions.checkState(bArr.length >= 4, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", bArr.length);
            byte[] bArr2 = this.f9232a;
            return ((bArr2[3] & 255) << 24) | (bArr2[0] & 255) | ((bArr2[1] & 255) << 8) | ((bArr2[2] & 255) << 16);
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            byte[] bArr = this.f9232a;
            Preconditions.checkState(bArr.length >= 8, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", bArr.length);
            return padToLong();
        }

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return this.f9232a.length * 8;
        }

        @Override // com.google.common.hash.HashCode
        byte[] c() {
            return this.f9232a;
        }

        @Override // com.google.common.hash.HashCode
        void d(byte[] bArr, int i2, int i3) {
            System.arraycopy(this.f9232a, 0, bArr, i2, i3);
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            long j2 = this.f9232a[0] & 255;
            for (int i2 = 1; i2 < Math.min(this.f9232a.length, 8); i2++) {
                j2 |= (this.f9232a[i2] & 255) << (i2 * 8);
            }
            return j2;
        }
    }

    /* loaded from: classes2.dex */
    private static final class IntHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final int f9233a;

        IntHashCode(int i2) {
            this.f9233a = i2;
        }

        @Override // com.google.common.hash.HashCode
        boolean a(HashCode hashCode) {
            return this.f9233a == hashCode.asInt();
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            int i2 = this.f9233a;
            return new byte[]{(byte) i2, (byte) (i2 >> 8), (byte) (i2 >> 16), (byte) (i2 >> 24)};
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            return this.f9233a;
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
        }

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return 32;
        }

        @Override // com.google.common.hash.HashCode
        void d(byte[] bArr, int i2, int i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                bArr[i2 + i4] = (byte) (this.f9233a >> (i4 * 8));
            }
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            return UnsignedInts.toLong(this.f9233a);
        }
    }

    /* loaded from: classes2.dex */
    private static final class LongHashCode extends HashCode implements Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final long f9234a;

        LongHashCode(long j2) {
            this.f9234a = j2;
        }

        @Override // com.google.common.hash.HashCode
        boolean a(HashCode hashCode) {
            return this.f9234a == hashCode.asLong();
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            long j2 = this.f9234a;
            return new byte[]{(byte) j2, (byte) (j2 >> 8), (byte) (j2 >> 16), (byte) (j2 >> 24), (byte) (j2 >> 32), (byte) (j2 >> 40), (byte) (j2 >> 48), (byte) (j2 >> 56)};
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            return (int) this.f9234a;
        }

        @Override // com.google.common.hash.HashCode
        public long asLong() {
            return this.f9234a;
        }

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return 64;
        }

        @Override // com.google.common.hash.HashCode
        void d(byte[] bArr, int i2, int i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                bArr[i2 + i4] = (byte) (this.f9234a >> (i4 * 8));
            }
        }

        @Override // com.google.common.hash.HashCode
        public long padToLong() {
            return this.f9234a;
        }
    }

    HashCode() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HashCode b(byte[] bArr) {
        return new BytesHashCode(bArr);
    }

    private static int decode(char c2) {
        if (c2 < '0' || c2 > '9') {
            if (c2 < 'a' || c2 > 'f') {
                throw new IllegalArgumentException("Illegal hexadecimal character: " + c2);
            }
            return (c2 - 'a') + 10;
        }
        return c2 - '0';
    }

    public static HashCode fromBytes(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 1, "A HashCode must contain at least 1 byte.");
        return b((byte[]) bArr.clone());
    }

    public static HashCode fromInt(int i2) {
        return new IntHashCode(i2);
    }

    public static HashCode fromLong(long j2) {
        return new LongHashCode(j2);
    }

    public static HashCode fromString(String str) {
        Preconditions.checkArgument(str.length() >= 2, "input string (%s) must have at least 2 characters", str);
        Preconditions.checkArgument(str.length() % 2 == 0, "input string (%s) must have an even number of characters", str);
        byte[] bArr = new byte[str.length() / 2];
        for (int i2 = 0; i2 < str.length(); i2 += 2) {
            bArr[i2 / 2] = (byte) ((decode(str.charAt(i2)) << 4) + decode(str.charAt(i2 + 1)));
        }
        return b(bArr);
    }

    abstract boolean a(HashCode hashCode);

    public abstract byte[] asBytes();

    public abstract int asInt();

    public abstract long asLong();

    public abstract int bits();

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] c() {
        return asBytes();
    }

    abstract void d(byte[] bArr, int i2, int i3);

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof HashCode) {
            HashCode hashCode = (HashCode) obj;
            return bits() == hashCode.bits() && a(hashCode);
        }
        return false;
    }

    public final int hashCode() {
        if (bits() >= 32) {
            return asInt();
        }
        byte[] c2 = c();
        int i2 = c2[0] & 255;
        for (int i3 = 1; i3 < c2.length; i3++) {
            i2 |= (c2[i3] & 255) << (i3 * 8);
        }
        return i2;
    }

    public abstract long padToLong();

    public final String toString() {
        byte[] c2 = c();
        StringBuilder sb = new StringBuilder(c2.length * 2);
        for (byte b2 : c2) {
            char[] cArr = hexDigits;
            sb.append(cArr[(b2 >> 4) & 15]);
            sb.append(cArr[b2 & Ascii.SI]);
        }
        return sb.toString();
    }

    @CanIgnoreReturnValue
    public int writeBytesTo(byte[] bArr, int i2, int i3) {
        int min = Ints.min(i3, bits() / 8);
        Preconditions.checkPositionIndexes(i2, i2 + min, bArr.length);
        d(bArr, i2, min);
        return min;
    }
}
