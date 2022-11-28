package io.opencensus.trace;

import io.opencensus.internal.Utils;
import java.util.Random;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public final class TraceId implements Comparable<TraceId> {
    private static final int BASE16_SIZE = 32;
    public static final TraceId INVALID = new TraceId(0, 0);
    private static final long INVALID_ID = 0;
    public static final int SIZE = 16;
    private final long idHi;
    private final long idLo;

    private TraceId(long j2, long j3) {
        this.idHi = j2;
        this.idLo = j3;
    }

    public static TraceId fromBytes(byte[] bArr) {
        Utils.checkNotNull(bArr, "src");
        Utils.checkArgument(bArr.length == 16, "Invalid size: expected %s, got %s", 16, Integer.valueOf(bArr.length));
        return fromBytes(bArr, 0);
    }

    public static TraceId fromBytes(byte[] bArr, int i2) {
        Utils.checkNotNull(bArr, "src");
        return new TraceId(BigendianEncoding.d(bArr, i2), BigendianEncoding.d(bArr, i2 + 8));
    }

    public static TraceId fromLowerBase16(CharSequence charSequence) {
        Utils.checkNotNull(charSequence, "src");
        Utils.checkArgument(charSequence.length() == 32, "Invalid size: expected %s, got %s", 32, Integer.valueOf(charSequence.length()));
        return fromLowerBase16(charSequence, 0);
    }

    public static TraceId fromLowerBase16(CharSequence charSequence, int i2) {
        Utils.checkNotNull(charSequence, "src");
        return new TraceId(BigendianEncoding.c(charSequence, i2), BigendianEncoding.c(charSequence, i2 + 16));
    }

    public static TraceId generateRandomId(Random random) {
        long nextLong;
        long nextLong2;
        do {
            nextLong = random.nextLong();
            nextLong2 = random.nextLong();
            if (nextLong != 0) {
                break;
            }
        } while (nextLong2 == 0);
        return new TraceId(nextLong, nextLong2);
    }

    @Override // java.lang.Comparable
    public int compareTo(TraceId traceId) {
        long j2 = this.idHi;
        long j3 = traceId.idHi;
        if (j2 != j3) {
            return j2 < j3 ? -1 : 1;
        }
        long j4 = this.idLo;
        long j5 = traceId.idLo;
        if (j4 == j5) {
            return 0;
        }
        return j4 < j5 ? -1 : 1;
    }

    public void copyBytesTo(byte[] bArr, int i2) {
        BigendianEncoding.f(this.idHi, bArr, i2);
        BigendianEncoding.f(this.idLo, bArr, i2 + 8);
    }

    public void copyLowerBase16To(char[] cArr, int i2) {
        BigendianEncoding.e(this.idHi, cArr, i2);
        BigendianEncoding.e(this.idLo, cArr, i2 + 16);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TraceId) {
            TraceId traceId = (TraceId) obj;
            return this.idHi == traceId.idHi && this.idLo == traceId.idLo;
        }
        return false;
    }

    public byte[] getBytes() {
        byte[] bArr = new byte[16];
        BigendianEncoding.f(this.idHi, bArr, 0);
        BigendianEncoding.f(this.idLo, bArr, 8);
        return bArr;
    }

    public long getLowerLong() {
        long j2 = this.idHi;
        return j2 < 0 ? -j2 : j2;
    }

    public int hashCode() {
        long j2 = this.idHi;
        long j3 = this.idLo;
        return ((((int) (j2 ^ (j2 >>> 32))) + 31) * 31) + ((int) ((j3 >>> 32) ^ j3));
    }

    public boolean isValid() {
        return (this.idHi == 0 && this.idLo == 0) ? false : true;
    }

    public String toLowerBase16() {
        char[] cArr = new char[32];
        copyLowerBase16To(cArr, 0);
        return new String(cArr);
    }

    public String toString() {
        return "TraceId{traceId=" + toLowerBase16() + "}";
    }
}
