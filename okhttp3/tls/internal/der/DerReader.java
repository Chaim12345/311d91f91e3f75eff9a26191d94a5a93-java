package okhttp3.tls.internal.der;

import java.math.BigInteger;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DerReader {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final DerHeader END_OF_DATA = new DerHeader(0, 0, false, -1);
    private boolean constructed;
    @NotNull
    private final CountingSource countingSource;
    private long limit;
    @NotNull
    private final List<String> path;
    @Nullable
    private DerHeader peekedHeader;
    @NotNull
    private final BufferedSource source;
    @NotNull
    private final List<Object> typeHintStack;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes3.dex */
    public static final class CountingSource extends ForwardingSource {
        private long bytesRead;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CountingSource(@NotNull Source source) {
            super(source);
            Intrinsics.checkNotNullParameter(source, "source");
        }

        public final long getBytesRead() {
            return this.bytesRead;
        }

        @Override // okio.ForwardingSource, okio.Source
        public long read(@NotNull Buffer sink, long j2) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            long read = delegate().read(sink, j2);
            if (read == -1) {
                return -1L;
            }
            this.bytesRead += read;
            return read;
        }

        public final void setBytesRead(long j2) {
            this.bytesRead = j2;
        }
    }

    public DerReader(@NotNull Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        CountingSource countingSource = new CountingSource(source);
        this.countingSource = countingSource;
        this.source = Okio.buffer(countingSource);
        this.limit = -1L;
        this.typeHintStack = new ArrayList();
        this.path = new ArrayList();
    }

    public final long getByteCount() {
        return this.countingSource.getBytesRead() - this.source.getBuffer().size();
    }

    private final long getBytesLeft() {
        long j2 = this.limit;
        if (j2 == -1) {
            return -1L;
        }
        return j2 - getByteCount();
    }

    private final long readVariableLengthLong() {
        long j2 = 0;
        while (true) {
            long readByte = this.source.readByte() & 255;
            if ((readByte & 128) != 128) {
                return j2 + readByte;
            }
            j2 = (j2 + (readByte & 127)) << 7;
        }
    }

    @Nullable
    public final Object getTypeHint() {
        Object lastOrNull;
        lastOrNull = CollectionsKt___CollectionsKt.lastOrNull((List<? extends Object>) this.typeHintStack);
        return lastOrNull;
    }

    public final boolean hasNext() {
        return peekHeader() != null;
    }

    @Nullable
    public final DerHeader peekHeader() {
        DerHeader derHeader = this.peekedHeader;
        if (derHeader == null) {
            derHeader = readHeader$okhttp_tls();
            this.peekedHeader = derHeader;
        }
        if (derHeader.isEndOfData()) {
            return null;
        }
        return derHeader;
    }

    public final <T> T read$okhttp_tls(@Nullable String str, @NotNull Function1<? super DerHeader, ? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (hasNext()) {
            DerHeader derHeader = this.peekedHeader;
            Intrinsics.checkNotNull(derHeader);
            this.peekedHeader = null;
            long j2 = this.limit;
            boolean z = this.constructed;
            long byteCount = derHeader.getLength() != -1 ? getByteCount() + derHeader.getLength() : -1L;
            if (j2 == -1 || byteCount <= j2) {
                this.limit = byteCount;
                this.constructed = derHeader.getConstructed();
                if (str != null) {
                    this.path.add(str);
                }
                try {
                    T invoke = block.invoke(derHeader);
                    if (byteCount != -1 && getByteCount() > byteCount) {
                        throw new ProtocolException(Intrinsics.stringPlus("unexpected byte count at ", this));
                    }
                    return invoke;
                } finally {
                    InlineMarker.finallyStart(1);
                    this.peekedHeader = null;
                    this.limit = j2;
                    this.constructed = z;
                    if (str != null) {
                        this.path.remove(this.path.size() - 1);
                    }
                    InlineMarker.finallyEnd(1);
                }
            }
            throw new ProtocolException("enclosed object too large");
        }
        throw new ProtocolException("expected a value");
    }

    @NotNull
    public final BigInteger readBigInteger() {
        if (getBytesLeft() != 0) {
            return new BigInteger(this.source.readByteArray(getBytesLeft()));
        }
        throw new ProtocolException("unexpected length: " + getBytesLeft() + " at " + this);
    }

    @NotNull
    public final BitString readBitString() {
        if (getBytesLeft() == -1 || this.constructed) {
            throw new ProtocolException("constructed bit strings not supported for DER");
        }
        if (getBytesLeft() >= 1) {
            return new BitString(this.source.readByteString(getBytesLeft()), this.source.readByte() & 255);
        }
        throw new ProtocolException("malformed bit string");
    }

    public final boolean readBoolean() {
        if (getBytesLeft() == 1) {
            return this.source.readByte() != 0;
        }
        throw new ProtocolException("unexpected length: " + getBytesLeft() + " at " + this);
    }

    @NotNull
    public final DerHeader readHeader$okhttp_tls() {
        long j2;
        int i2 = 1;
        if (this.peekedHeader == null) {
            long byteCount = getByteCount();
            long j3 = this.limit;
            if (byteCount == j3) {
                return END_OF_DATA;
            }
            if (j3 == -1 && this.source.exhausted()) {
                return END_OF_DATA;
            }
            int readByte = this.source.readByte() & 255;
            int i3 = readByte & 192;
            boolean z = (readByte & 32) == 32;
            int i4 = readByte & 31;
            long readVariableLengthLong = i4 == 31 ? readVariableLengthLong() : i4;
            int readByte2 = this.source.readByte() & 255;
            if (readByte2 != 128) {
                int i5 = readByte2 & 128;
                int i6 = readByte2 & 127;
                if (i5 != 128) {
                    j2 = i6;
                } else if (i6 > 8) {
                    throw new ProtocolException("length encoded with more than 8 bytes is not supported");
                } else {
                    j2 = this.source.readByte() & 255;
                    if (j2 == 0 || (i6 == 1 && (128 & j2) == 0)) {
                        throw new ProtocolException("invalid encoding for length");
                    }
                    while (i2 < i6) {
                        i2++;
                        j2 = (j2 << 8) + (this.source.readByte() & 255);
                    }
                    if (j2 < 0) {
                        throw new ProtocolException("length > Long.MAX_VALUE");
                    }
                }
                return new DerHeader(i3, readVariableLengthLong, z, j2);
            }
            throw new ProtocolException("indefinite length not permitted for DER");
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public final long readLong() {
        long bytesLeft = getBytesLeft();
        boolean z = false;
        if (1 <= bytesLeft && bytesLeft < 9) {
            z = true;
        }
        if (z) {
            long readByte = this.source.readByte();
            while (getByteCount() < this.limit) {
                readByte = (readByte << 8) + (this.source.readByte() & 255);
            }
            return readByte;
        }
        throw new ProtocolException("unexpected length: " + getBytesLeft() + " at " + this);
    }

    @NotNull
    public final String readObjectIdentifier() {
        Buffer buffer = new Buffer();
        long readVariableLengthLong = readVariableLengthLong();
        boolean z = true;
        if (0 <= readVariableLengthLong && readVariableLengthLong < 40) {
            buffer.writeDecimalLong(0L);
            buffer.writeByte(46);
        } else {
            if (40 > readVariableLengthLong || readVariableLengthLong >= 80) {
                z = false;
            }
            if (z) {
                buffer.writeDecimalLong(1L);
                buffer.writeByte(46);
                readVariableLengthLong -= 40;
            } else {
                buffer.writeDecimalLong(2L);
                buffer.writeByte(46);
                readVariableLengthLong -= 80;
            }
        }
        while (true) {
            buffer.writeDecimalLong(readVariableLengthLong);
            if (getByteCount() >= this.limit) {
                return buffer.readUtf8();
            }
            buffer.writeByte(46);
            readVariableLengthLong = readVariableLengthLong();
        }
    }

    @NotNull
    public final ByteString readOctetString() {
        if (getBytesLeft() == -1 || this.constructed) {
            throw new ProtocolException("constructed octet strings not supported for DER");
        }
        return this.source.readByteString(getBytesLeft());
    }

    @NotNull
    public final String readRelativeObjectIdentifier() {
        Buffer buffer = new Buffer();
        while (getByteCount() < this.limit) {
            if (buffer.size() > 0) {
                buffer.writeByte(46);
            }
            buffer.writeDecimalLong(readVariableLengthLong());
        }
        return buffer.readUtf8();
    }

    @NotNull
    public final ByteString readUnknown() {
        return this.source.readByteString(getBytesLeft());
    }

    @NotNull
    public final String readUtf8String() {
        if (getBytesLeft() == -1 || this.constructed) {
            throw new ProtocolException("constructed strings not supported for DER");
        }
        return this.source.readUtf8(getBytesLeft());
    }

    public final void setTypeHint(@Nullable Object obj) {
        List<Object> list = this.typeHintStack;
        list.set(list.size() - 1, obj);
    }

    @NotNull
    public String toString() {
        String joinToString$default;
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.path, " / ", null, null, 0, null, null, 62, null);
        return joinToString$default;
    }

    public final <T> T withTypeHint(@NotNull Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.typeHintStack.add(null);
        try {
            T invoke = block.invoke();
            List<Object> list = this.typeHintStack;
            list.remove(list.size() - 1);
            return invoke;
        } catch (Throwable th) {
            this.typeHintStack.remove(this.typeHintStack.size() - 1);
            throw th;
        }
    }
}
