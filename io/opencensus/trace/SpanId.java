package io.opencensus.trace;

import io.opencensus.internal.Utils;
import java.util.Random;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public final class SpanId implements Comparable<SpanId> {
    private static final int BASE16_SIZE = 16;
    public static final SpanId INVALID = new SpanId(0);
    private static final long INVALID_ID = 0;
    public static final int SIZE = 8;
    private final long id;

    private SpanId(long j2) {
        this.id = j2;
    }

    public static SpanId fromBytes(byte[] bArr) {
        Utils.checkNotNull(bArr, "src");
        Utils.checkArgument(bArr.length == 8, "Invalid size: expected %s, got %s", 8, Integer.valueOf(bArr.length));
        return fromBytes(bArr, 0);
    }

    public static SpanId fromBytes(byte[] bArr, int i2) {
        Utils.checkNotNull(bArr, "src");
        return new SpanId(BigendianEncoding.d(bArr, i2));
    }

    public static SpanId fromLowerBase16(CharSequence charSequence) {
        Utils.checkNotNull(charSequence, "src");
        Utils.checkArgument(charSequence.length() == 16, "Invalid size: expected %s, got %s", 16, Integer.valueOf(charSequence.length()));
        return fromLowerBase16(charSequence, 0);
    }

    public static SpanId fromLowerBase16(CharSequence charSequence, int i2) {
        Utils.checkNotNull(charSequence, "src");
        return new SpanId(BigendianEncoding.c(charSequence, i2));
    }

    public static SpanId generateRandomId(Random random) {
        long nextLong;
        do {
            nextLong = random.nextLong();
        } while (nextLong == 0);
        return new SpanId(nextLong);
    }

    @Override // java.lang.Comparable
    public int compareTo(SpanId spanId) {
        long j2 = this.id;
        long j3 = spanId.id;
        if (j2 < j3) {
            return -1;
        }
        return j2 == j3 ? 0 : 1;
    }

    public void copyBytesTo(byte[] bArr, int i2) {
        BigendianEncoding.f(this.id, bArr, i2);
    }

    public void copyLowerBase16To(char[] cArr, int i2) {
        BigendianEncoding.e(this.id, cArr, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SpanId) && this.id == ((SpanId) obj).id;
    }

    public byte[] getBytes() {
        byte[] bArr = new byte[8];
        BigendianEncoding.f(this.id, bArr, 0);
        return bArr;
    }

    public int hashCode() {
        long j2 = this.id;
        return (int) (j2 ^ (j2 >>> 32));
    }

    public boolean isValid() {
        return this.id != 0;
    }

    public String toLowerBase16() {
        char[] cArr = new char[16];
        copyLowerBase16To(cArr, 0);
        return new String(cArr);
    }

    public String toString() {
        return "SpanId{spanId=" + toLowerBase16() + "}";
    }
}
