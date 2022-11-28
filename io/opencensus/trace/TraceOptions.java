package io.opencensus.trace;

import io.opencensus.internal.Utils;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public final class TraceOptions {
    private static final int BASE16_SIZE = 2;
    public static final TraceOptions DEFAULT = fromByte((byte) 0);
    private static final byte DEFAULT_OPTIONS = 0;
    private static final byte IS_SAMPLED = 1;
    public static final int SIZE = 1;
    private final byte options;

    /* loaded from: classes3.dex */
    public static final class Builder {
        private byte options;

        private Builder(byte b2) {
            this.options = b2;
        }

        public TraceOptions build() {
            return TraceOptions.fromByte(this.options);
        }

        @Deprecated
        public Builder setIsSampled() {
            return setIsSampled(true);
        }

        public Builder setIsSampled(boolean z) {
            this.options = (byte) (z ? this.options | 1 : this.options & (-2));
            return this;
        }
    }

    private TraceOptions(byte b2) {
        this.options = b2;
    }

    public static Builder builder() {
        return new Builder((byte) 0);
    }

    public static Builder builder(TraceOptions traceOptions) {
        return new Builder(traceOptions.options);
    }

    public static TraceOptions fromByte(byte b2) {
        return new TraceOptions(b2);
    }

    @Deprecated
    public static TraceOptions fromBytes(byte[] bArr) {
        Utils.checkNotNull(bArr, "buffer");
        Utils.checkArgument(bArr.length == 1, "Invalid size: expected %s, got %s", 1, Integer.valueOf(bArr.length));
        return fromByte(bArr[0]);
    }

    @Deprecated
    public static TraceOptions fromBytes(byte[] bArr, int i2) {
        Utils.checkIndex(i2, bArr.length);
        return fromByte(bArr[i2]);
    }

    public static TraceOptions fromLowerBase16(CharSequence charSequence, int i2) {
        return new TraceOptions(BigendianEncoding.a(charSequence, i2));
    }

    private boolean hasOption(int i2) {
        return (i2 & this.options) != 0;
    }

    public void copyBytesTo(byte[] bArr, int i2) {
        Utils.checkIndex(i2, bArr.length);
        bArr[i2] = this.options;
    }

    public void copyLowerBase16To(char[] cArr, int i2) {
        BigendianEncoding.b(this.options, cArr, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof TraceOptions) && this.options == ((TraceOptions) obj).options;
    }

    public byte getByte() {
        return this.options;
    }

    @Deprecated
    public byte[] getBytes() {
        return new byte[]{this.options};
    }

    public int hashCode() {
        return Arrays.hashCode(new byte[]{this.options});
    }

    public boolean isSampled() {
        return hasOption(1);
    }

    public String toLowerBase16() {
        char[] cArr = new char[2];
        copyLowerBase16To(cArr, 0);
        return new String(cArr);
    }

    public String toString() {
        return "TraceOptions{sampled=" + isSampled() + "}";
    }
}
