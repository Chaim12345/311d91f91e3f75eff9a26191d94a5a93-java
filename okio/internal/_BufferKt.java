package okio.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.google.common.base.Ascii;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.text.Typography;
import okhttp3.internal.connection.RealConnection;
import okio.Buffer;
import okio.ByteString;
import okio.Options;
import okio.Segment;
import okio.SegmentPool;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Source;
import okio.Utf8;
import okio._JvmPlatformKt;
import okio._UtilKt;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0016\u001a0\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0000\u001a\u0014\u0010\u000e\u001a\u00020\r*\u00020\n2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u001aA\u0010\u0013\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000f*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000b2\u001a\u0010\u0012\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\u0011H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001e\u0010\u0018\u001a\u00020\u0002*\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\bH\u0000\u001a%\u0010\u001c\u001a\u00020\n*\u00020\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000bH\u0080\b\u001a\r\u0010\u001d\u001a\u00020\u000b*\u00020\nH\u0080\b\u001a\r\u0010\u001f\u001a\u00020\u001e*\u00020\nH\u0080\b\u001a\r\u0010!\u001a\u00020 *\u00020\nH\u0080\b\u001a\r\u0010\"\u001a\u00020\u0002*\u00020\nH\u0080\b\u001a\r\u0010#\u001a\u00020\u000b*\u00020\nH\u0080\b\u001a\u0015\u0010%\u001a\u00020\u001e*\u00020\n2\u0006\u0010$\u001a\u00020\u000bH\u0080\b\u001a\r\u0010'\u001a\u00020&*\u00020\nH\u0080\b\u001a\u0015\u0010(\u001a\u00020&*\u00020\n2\u0006\u0010\u001b\u001a\u00020\u000bH\u0080\b\u001a)\u0010+\u001a\u00020\n*\u00020\n2\u0006\u0010*\u001a\u00020)2\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u0002H\u0080\b\u001a\u0015\u0010-\u001a\u00020\n*\u00020\n2\u0006\u0010,\u001a\u00020\u000bH\u0080\b\u001a\u0015\u0010.\u001a\u00020\n*\u00020\n2\u0006\u0010,\u001a\u00020\u000bH\u0080\b\u001a\u0015\u00100\u001a\u00020\u0000*\u00020\n2\u0006\u0010/\u001a\u00020\u0002H\u0080\b\u001a\u0015\u0010+\u001a\u00020\n*\u00020\n2\u0006\u00101\u001a\u00020\u0004H\u0080\b\u001a%\u0010+\u001a\u00020\n*\u00020\n2\u0006\u00101\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002H\u0080\b\u001a\r\u00102\u001a\u00020\u0004*\u00020\nH\u0080\b\u001a\u0015\u00102\u001a\u00020\u0004*\u00020\n2\u0006\u0010\u001b\u001a\u00020\u000bH\u0080\b\u001a\u0015\u00104\u001a\u00020\u0002*\u00020\n2\u0006\u00103\u001a\u00020\u0004H\u0080\b\u001a\u0015\u00105\u001a\u00020&*\u00020\n2\u0006\u00103\u001a\u00020\u0004H\u0080\b\u001a%\u00104\u001a\u00020\u0002*\u00020\n2\u0006\u00103\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002H\u0080\b\u001a\r\u00106\u001a\u00020\u000b*\u00020\nH\u0080\b\u001a\r\u00107\u001a\u00020\u000b*\u00020\nH\u0080\b\u001a\r\u00108\u001a\u00020)*\u00020\nH\u0080\b\u001a\u0015\u00108\u001a\u00020)*\u00020\n2\u0006\u0010\u001b\u001a\u00020\u000bH\u0080\b\u001a\u0015\u00109\u001a\u00020\u0002*\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0015H\u0080\b\u001a\u001d\u00105\u001a\u00020&*\u00020\n2\u0006\u00103\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u000bH\u0080\b\u001a\u0015\u0010;\u001a\u00020\u000b*\u00020\n2\u0006\u00103\u001a\u00020:H\u0080\b\u001a\u0015\u0010<\u001a\u00020\r*\u00020\n2\u0006\u0010\u001b\u001a\u00020\u000bH\u0080\b\u001a\u000f\u0010=\u001a\u0004\u0018\u00010\r*\u00020\nH\u0080\b\u001a\u0015\u0010?\u001a\u00020\r*\u00020\n2\u0006\u0010>\u001a\u00020\u000bH\u0080\b\u001a\r\u0010@\u001a\u00020\u0002*\u00020\nH\u0080\b\u001a%\u0010D\u001a\u00020\n*\u00020\n2\u0006\u0010A\u001a\u00020\r2\u0006\u0010B\u001a\u00020\u00022\u0006\u0010C\u001a\u00020\u0002H\u0080\b\u001a\u0015\u0010F\u001a\u00020\n*\u00020\n2\u0006\u0010E\u001a\u00020\u0002H\u0080\b\u001a\u0015\u0010H\u001a\u00020\u000b*\u00020\n2\u0006\u00101\u001a\u00020GH\u0080\b\u001a\u001d\u0010+\u001a\u00020\n*\u00020\n2\u0006\u00101\u001a\u00020G2\u0006\u0010\u001b\u001a\u00020\u000bH\u0080\b\u001a\u0015\u0010J\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u0002H\u0080\b\u001a\u0015\u0010L\u001a\u00020\n*\u00020\n2\u0006\u0010K\u001a\u00020\u0002H\u0080\b\u001a\u0015\u0010N\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u0002H\u0080\b\u001a\u0015\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010,\u001a\u00020\u000bH\u0080\b\u001a\u001d\u0010+\u001a\u00020&*\u00020\n2\u0006\u00101\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u000bH\u0080\b\u001a\u001d\u00104\u001a\u00020\u000b*\u00020\n2\u0006\u00103\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u000bH\u0080\b\u001a%\u0010Q\u001a\u00020\u000b*\u00020\n2\u0006\u0010I\u001a\u00020\u001e2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010P\u001a\u00020\u000bH\u0080\b\u001a\u001d\u0010Q\u001a\u00020\u000b*\u00020\n2\u0006\u0010\u0005\u001a\u00020)2\u0006\u0010\u0010\u001a\u00020\u000bH\u0080\b\u001a\u001d\u0010S\u001a\u00020\u000b*\u00020\n2\u0006\u0010R\u001a\u00020)2\u0006\u0010\u0010\u001a\u00020\u000bH\u0080\b\u001a-\u0010T\u001a\u00020\b*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020)2\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002H\u0080\b\u001a\u0017\u0010W\u001a\u00020\b*\u00020\n2\b\u0010V\u001a\u0004\u0018\u00010UH\u0080\b\u001a\r\u0010X\u001a\u00020\u0002*\u00020\nH\u0080\b\u001a\r\u0010Y\u001a\u00020\n*\u00020\nH\u0080\b\u001a\r\u0010Z\u001a\u00020)*\u00020\nH\u0080\b\u001a\u0015\u0010Z\u001a\u00020)*\u00020\n2\u0006\u0010\u001b\u001a\u00020\u0002H\u0080\b\u001a\u0014\u0010]\u001a\u00020[*\u00020\n2\u0006\u0010\\\u001a\u00020[H\u0000\u001a\u0014\u0010^\u001a\u00020[*\u00020\n2\u0006\u0010\\\u001a\u00020[H\u0000\u001a\r\u0010_\u001a\u00020\u0002*\u00020[H\u0080\b\u001a\u0015\u0010`\u001a\u00020\u0002*\u00020[2\u0006\u0010\u001a\u001a\u00020\u000bH\u0080\b\u001a\u0015\u0010b\u001a\u00020\u000b*\u00020[2\u0006\u0010a\u001a\u00020\u000bH\u0080\b\u001a\u0015\u0010d\u001a\u00020\u000b*\u00020[2\u0006\u0010c\u001a\u00020\u0002H\u0080\b\u001a\r\u0010e\u001a\u00020&*\u00020[H\u0080\b\"\"\u0010f\u001a\u00020\u00048\u0000@\u0001X\u0081\u0004¢\u0006\u0012\n\u0004\bf\u0010g\u0012\u0004\bj\u0010k\u001a\u0004\bh\u0010i\"\u0016\u0010l\u001a\u00020\u00028\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\bl\u0010m\"\u0016\u0010n\u001a\u00020\u000b8\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\bn\u0010o\"\u0016\u0010p\u001a\u00020\u000b8\u0000@\u0000X\u0080T¢\u0006\u0006\n\u0004\bp\u0010o\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006q"}, d2 = {"Lokio/Segment;", "segment", "", "segmentPos", "", "bytes", "bytesOffset", "bytesLimit", "", "rangeEquals", "Lokio/Buffer;", "", "newline", "", "readUtf8Line", ExifInterface.GPS_DIRECTION_TRUE, "fromIndex", "Lkotlin/Function2;", "lambda", "seek", "(Lokio/Buffer;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "Lokio/Options;", "options", "selectTruncated", "selectPrefix", "out", TypedValues.Cycle.S_WAVE_OFFSET, "byteCount", "commonCopyTo", "commonCompleteSegmentByteCount", "", "commonReadByte", "", "commonReadShort", "commonReadInt", "commonReadLong", "pos", "commonGet", "", "commonClear", "commonSkip", "Lokio/ByteString;", "byteString", "commonWrite", "v", "commonWriteDecimalLong", "commonWriteHexadecimalUnsignedLong", "minimumCapacity", "commonWritableSegment", "source", "commonReadByteArray", "sink", "commonRead", "commonReadFully", "commonReadDecimalLong", "commonReadHexadecimalUnsignedLong", "commonReadByteString", "commonSelect", "Lokio/Sink;", "commonReadAll", "commonReadUtf8", "commonReadUtf8Line", "limit", "commonReadUtf8LineStrict", "commonReadUtf8CodePoint", TypedValues.Custom.S_STRING, "beginIndex", "endIndex", "commonWriteUtf8", "codePoint", "commonWriteUtf8CodePoint", "Lokio/Source;", "commonWriteAll", "b", "commonWriteByte", "s", "commonWriteShort", "i", "commonWriteInt", "commonWriteLong", "toIndex", "commonIndexOf", "targetBytes", "commonIndexOfElement", "commonRangeEquals", "", "other", "commonEquals", "commonHashCode", "commonCopy", "commonSnapshot", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "commonReadUnsafe", "commonReadAndWriteUnsafe", "commonNext", "commonSeek", "newSize", "commonResizeBuffer", "minByteCount", "commonExpandBuffer", "commonClose", "HEX_DIGIT_BYTES", "[B", "getHEX_DIGIT_BYTES", "()[B", "getHEX_DIGIT_BYTES$annotations", "()V", "SEGMENTING_THRESHOLD", "I", "OVERFLOW_ZONE", "J", "OVERFLOW_DIGIT_START", "okio"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class _BufferKt {
    @NotNull
    private static final byte[] HEX_DIGIT_BYTES = _JvmPlatformKt.asUtf8ToByteArray("0123456789abcdef");
    public static final long OVERFLOW_DIGIT_START = -7;
    public static final long OVERFLOW_ZONE = -922337203685477580L;
    public static final int SEGMENTING_THRESHOLD = 4096;

    public static final void commonClear(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        buffer.skip(buffer.size());
    }

    public static final void commonClose(@NotNull Buffer.UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "<this>");
        if (!(unsafeCursor.buffer != null)) {
            throw new IllegalStateException("not attached to a buffer".toString());
        }
        unsafeCursor.buffer = null;
        unsafeCursor.setSegment$okio(null);
        unsafeCursor.offset = -1L;
        unsafeCursor.data = null;
        unsafeCursor.start = -1;
        unsafeCursor.end = -1;
    }

    public static final long commonCompleteSegmentByteCount(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        long size = buffer.size();
        if (size == 0) {
            return 0L;
        }
        Segment segment = buffer.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment.prev;
        Intrinsics.checkNotNull(segment2);
        int i2 = segment2.limit;
        return (i2 >= 8192 || !segment2.owner) ? size : size - (i2 - segment2.pos);
    }

    @NotNull
    public static final Buffer commonCopy(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Buffer buffer2 = new Buffer();
        if (buffer.size() == 0) {
            return buffer2;
        }
        Segment segment = buffer.head;
        Intrinsics.checkNotNull(segment);
        Segment sharedCopy = segment.sharedCopy();
        buffer2.head = sharedCopy;
        sharedCopy.prev = sharedCopy;
        sharedCopy.next = sharedCopy;
        for (Segment segment2 = segment.next; segment2 != segment; segment2 = segment2.next) {
            Segment segment3 = sharedCopy.prev;
            Intrinsics.checkNotNull(segment3);
            Intrinsics.checkNotNull(segment2);
            segment3.push(segment2.sharedCopy());
        }
        buffer2.setSize$okio(buffer.size());
        return buffer2;
    }

    @NotNull
    public static final Buffer commonCopyTo(@NotNull Buffer buffer, @NotNull Buffer out, long j2, long j3) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        _UtilKt.checkOffsetAndCount(buffer.size(), j2, j3);
        if (j3 == 0) {
            return buffer;
        }
        out.setSize$okio(out.size() + j3);
        Segment segment = buffer.head;
        while (true) {
            Intrinsics.checkNotNull(segment);
            int i2 = segment.limit;
            int i3 = segment.pos;
            if (j2 < i2 - i3) {
                break;
            }
            j2 -= i2 - i3;
            segment = segment.next;
        }
        while (j3 > 0) {
            Intrinsics.checkNotNull(segment);
            Segment sharedCopy = segment.sharedCopy();
            int i4 = sharedCopy.pos + ((int) j2);
            sharedCopy.pos = i4;
            sharedCopy.limit = Math.min(i4 + ((int) j3), sharedCopy.limit);
            Segment segment2 = out.head;
            if (segment2 == null) {
                sharedCopy.prev = sharedCopy;
                sharedCopy.next = sharedCopy;
                out.head = sharedCopy;
            } else {
                Intrinsics.checkNotNull(segment2);
                Segment segment3 = segment2.prev;
                Intrinsics.checkNotNull(segment3);
                segment3.push(sharedCopy);
            }
            j3 -= sharedCopy.limit - sharedCopy.pos;
            segment = segment.next;
            j2 = 0;
        }
        return buffer;
    }

    public static final boolean commonEquals(@NotNull Buffer buffer, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (buffer == obj) {
            return true;
        }
        if (obj instanceof Buffer) {
            Buffer buffer2 = (Buffer) obj;
            if (buffer.size() != buffer2.size()) {
                return false;
            }
            if (buffer.size() == 0) {
                return true;
            }
            Segment segment = buffer.head;
            Intrinsics.checkNotNull(segment);
            Segment segment2 = buffer2.head;
            Intrinsics.checkNotNull(segment2);
            int i2 = segment.pos;
            int i3 = segment2.pos;
            long j2 = 0;
            while (j2 < buffer.size()) {
                long min = Math.min(segment.limit - i2, segment2.limit - i3);
                if (0 < min) {
                    long j3 = 0;
                    while (true) {
                        j3++;
                        int i4 = i2 + 1;
                        int i5 = i3 + 1;
                        if (segment.data[i2] != segment2.data[i3]) {
                            return false;
                        }
                        if (j3 >= min) {
                            i2 = i4;
                            i3 = i5;
                            break;
                        }
                        i2 = i4;
                        i3 = i5;
                    }
                }
                if (i2 == segment.limit) {
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    i2 = segment.pos;
                }
                if (i3 == segment2.limit) {
                    segment2 = segment2.next;
                    Intrinsics.checkNotNull(segment2);
                    i3 = segment2.pos;
                }
                j2 += min;
            }
            return true;
        }
        return false;
    }

    public static final long commonExpandBuffer(@NotNull Buffer.UnsafeCursor unsafeCursor, int i2) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "<this>");
        if (i2 > 0) {
            if (i2 <= 8192) {
                Buffer buffer = unsafeCursor.buffer;
                if (buffer != null) {
                    if (unsafeCursor.readWrite) {
                        long size = buffer.size();
                        Segment writableSegment$okio = buffer.writableSegment$okio(i2);
                        int i3 = 8192 - writableSegment$okio.limit;
                        writableSegment$okio.limit = 8192;
                        long j2 = i3;
                        buffer.setSize$okio(size + j2);
                        unsafeCursor.setSegment$okio(writableSegment$okio);
                        unsafeCursor.offset = size;
                        unsafeCursor.data = writableSegment$okio.data;
                        unsafeCursor.start = 8192 - i3;
                        unsafeCursor.end = 8192;
                        return j2;
                    }
                    throw new IllegalStateException("expandBuffer() only permitted for read/write buffers".toString());
                }
                throw new IllegalStateException("not attached to a buffer".toString());
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("minByteCount > Segment.SIZE: ", Integer.valueOf(i2)).toString());
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("minByteCount <= 0: ", Integer.valueOf(i2)).toString());
    }

    public static final byte commonGet(@NotNull Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        _UtilKt.checkOffsetAndCount(buffer.size(), j2, 1L);
        Segment segment = buffer.head;
        if (segment == null) {
            Intrinsics.checkNotNull(null);
            throw null;
        } else if (buffer.size() - j2 < j2) {
            long size = buffer.size();
            while (size > j2) {
                segment = segment.prev;
                Intrinsics.checkNotNull(segment);
                size -= segment.limit - segment.pos;
            }
            Intrinsics.checkNotNull(segment);
            return segment.data[(int) ((segment.pos + j2) - size)];
        } else {
            long j3 = 0;
            while (true) {
                long j4 = (segment.limit - segment.pos) + j3;
                if (j4 > j2) {
                    Intrinsics.checkNotNull(segment);
                    return segment.data[(int) ((segment.pos + j2) - j3)];
                }
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j3 = j4;
            }
        }
    }

    public static final int commonHashCode(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Segment segment = buffer.head;
        if (segment == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = segment.limit;
            for (int i4 = segment.pos; i4 < i3; i4++) {
                i2 = (i2 * 31) + segment.data[i4];
            }
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
        } while (segment != buffer.head);
        return i2;
    }

    public static final long commonIndexOf(@NotNull Buffer buffer, byte b2, long j2, long j3) {
        Segment segment;
        int i2;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        long j4 = 0;
        boolean z = false;
        if (0 <= j2 && j2 <= j3) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(("size=" + buffer.size() + " fromIndex=" + j2 + " toIndex=" + j3).toString());
        }
        if (j3 > buffer.size()) {
            j3 = buffer.size();
        }
        long j5 = j3;
        if (j2 == j5 || (segment = buffer.head) == null) {
            return -1L;
        }
        if (buffer.size() - j2 < j2) {
            j4 = buffer.size();
            while (j4 > j2) {
                segment = segment.prev;
                Intrinsics.checkNotNull(segment);
                j4 -= segment.limit - segment.pos;
            }
            while (j4 < j5) {
                byte[] bArr = segment.data;
                int min = (int) Math.min(segment.limit, (segment.pos + j5) - j4);
                i2 = (int) ((segment.pos + j2) - j4);
                while (i2 < min) {
                    if (bArr[i2] != b2) {
                        i2++;
                    }
                }
                j4 += segment.limit - segment.pos;
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j2 = j4;
            }
            return -1L;
        }
        while (true) {
            long j6 = (segment.limit - segment.pos) + j4;
            if (j6 > j2) {
                break;
            }
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
            j4 = j6;
        }
        while (j4 < j5) {
            byte[] bArr2 = segment.data;
            int min2 = (int) Math.min(segment.limit, (segment.pos + j5) - j4);
            i2 = (int) ((segment.pos + j2) - j4);
            while (i2 < min2) {
                if (bArr2[i2] != b2) {
                    i2++;
                }
            }
            j4 += segment.limit - segment.pos;
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
            j2 = j4;
        }
        return -1L;
        return (i2 - segment.pos) + j4;
    }

    public static final long commonIndexOf(@NotNull Buffer buffer, @NotNull ByteString bytes, long j2) {
        long j3;
        Segment segment;
        int i2;
        boolean z;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        boolean z2 = true;
        if (bytes.size() > 0) {
            long j4 = 0;
            if (j2 >= 0) {
                Segment segment2 = buffer.head;
                long j5 = -1;
                if (segment2 == null) {
                    return -1L;
                }
                if (buffer.size() - j2 < j2) {
                    long size = buffer.size();
                    while (size > j2) {
                        segment2 = segment2.prev;
                        Intrinsics.checkNotNull(segment2);
                        size -= segment2.limit - segment2.pos;
                    }
                    byte[] internalArray$okio = bytes.internalArray$okio();
                    byte b2 = internalArray$okio[0];
                    int size2 = bytes.size();
                    long size3 = (buffer.size() - size2) + 1;
                    j3 = size;
                    segment = segment2;
                    long j6 = j2;
                    loop1: while (j3 < size3) {
                        byte[] bArr = segment.data;
                        int min = (int) Math.min(segment.limit, (segment.pos + size3) - j3);
                        i2 = (int) ((segment.pos + j6) - j3);
                        if (i2 < min) {
                            while (true) {
                                int i3 = i2 + 1;
                                if (bArr[i2] == b2 && rangeEquals(segment, i3, internalArray$okio, 1, size2)) {
                                    break loop1;
                                } else if (i3 >= min) {
                                    break;
                                } else {
                                    i2 = i3;
                                }
                            }
                        }
                        j3 += segment.limit - segment.pos;
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j6 = j3;
                        j5 = -1;
                    }
                    return j5;
                }
                while (true) {
                    long j7 = (segment2.limit - segment2.pos) + j4;
                    if (j7 > j2) {
                        break;
                    }
                    segment2 = segment2.next;
                    Intrinsics.checkNotNull(segment2);
                    j4 = j7;
                    z2 = z2;
                }
                byte[] internalArray$okio2 = bytes.internalArray$okio();
                byte b3 = internalArray$okio2[0];
                int size4 = bytes.size();
                long size5 = (buffer.size() - size4) + 1;
                j3 = j4;
                Segment segment3 = segment2;
                long j8 = j2;
                loop4: while (j3 < size5) {
                    byte[] bArr2 = segment3.data;
                    int min2 = (int) Math.min(segment3.limit, (segment3.pos + size5) - j3);
                    segment = segment3;
                    i2 = (int) ((segment.pos + j8) - j3);
                    if (i2 < min2) {
                        while (true) {
                            int i4 = i2 + 1;
                            if (bArr2[i2] == b3) {
                                z = true;
                                if (rangeEquals(segment, i4, internalArray$okio2, 1, size4)) {
                                    break loop4;
                                }
                            } else {
                                z = true;
                            }
                            if (i4 >= min2) {
                                break;
                            }
                            i2 = i4;
                        }
                    } else {
                        z = true;
                    }
                    j3 += segment.limit - segment.pos;
                    segment3 = segment.next;
                    Intrinsics.checkNotNull(segment3);
                    j8 = j3;
                }
                return -1L;
                return (i2 - segment.pos) + j3;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("fromIndex < 0: ", Long.valueOf(j2)).toString());
        }
        throw new IllegalArgumentException("bytes is empty".toString());
    }

    public static final long commonIndexOfElement(@NotNull Buffer buffer, @NotNull ByteString targetBytes, long j2) {
        int i2;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        long j3 = 0;
        if (j2 >= 0) {
            Segment segment = buffer.head;
            if (segment == null) {
                return -1L;
            }
            if (buffer.size() - j2 < j2) {
                j3 = buffer.size();
                while (j3 > j2) {
                    segment = segment.prev;
                    Intrinsics.checkNotNull(segment);
                    j3 -= segment.limit - segment.pos;
                }
                if (targetBytes.size() == 2) {
                    byte b2 = targetBytes.getByte(0);
                    byte b3 = targetBytes.getByte(1);
                    while (j3 < buffer.size()) {
                        byte[] bArr = segment.data;
                        i2 = (int) ((segment.pos + j2) - j3);
                        int i3 = segment.limit;
                        while (i2 < i3) {
                            byte b4 = bArr[i2];
                            if (b4 != b2 && b4 != b3) {
                                i2++;
                            }
                        }
                        j3 += segment.limit - segment.pos;
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j2 = j3;
                    }
                    return -1L;
                }
                byte[] internalArray$okio = targetBytes.internalArray$okio();
                while (j3 < buffer.size()) {
                    byte[] bArr2 = segment.data;
                    i2 = (int) ((segment.pos + j2) - j3);
                    int i4 = segment.limit;
                    while (i2 < i4) {
                        byte b5 = bArr2[i2];
                        int length = internalArray$okio.length;
                        int i5 = 0;
                        while (i5 < length) {
                            byte b6 = internalArray$okio[i5];
                            i5++;
                            if (b5 == b6) {
                            }
                        }
                        i2++;
                    }
                    j3 += segment.limit - segment.pos;
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    j2 = j3;
                }
                return -1L;
            }
            while (true) {
                long j4 = (segment.limit - segment.pos) + j3;
                if (j4 > j2) {
                    break;
                }
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j3 = j4;
            }
            if (targetBytes.size() == 2) {
                byte b7 = targetBytes.getByte(0);
                byte b8 = targetBytes.getByte(1);
                while (j3 < buffer.size()) {
                    byte[] bArr3 = segment.data;
                    i2 = (int) ((segment.pos + j2) - j3);
                    int i6 = segment.limit;
                    while (i2 < i6) {
                        byte b9 = bArr3[i2];
                        if (b9 != b7 && b9 != b8) {
                            i2++;
                        }
                    }
                    j3 += segment.limit - segment.pos;
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    j2 = j3;
                }
                return -1L;
            }
            byte[] internalArray$okio2 = targetBytes.internalArray$okio();
            while (j3 < buffer.size()) {
                byte[] bArr4 = segment.data;
                i2 = (int) ((segment.pos + j2) - j3);
                int i7 = segment.limit;
                while (i2 < i7) {
                    byte b10 = bArr4[i2];
                    int length2 = internalArray$okio2.length;
                    int i8 = 0;
                    while (i8 < length2) {
                        byte b11 = internalArray$okio2[i8];
                        i8++;
                        if (b10 == b11) {
                        }
                    }
                    i2++;
                }
                j3 += segment.limit - segment.pos;
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j2 = j3;
            }
            return -1L;
            return (i2 - segment.pos) + j3;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("fromIndex < 0: ", Long.valueOf(j2)).toString());
    }

    public static final int commonNext(@NotNull Buffer.UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "<this>");
        long j2 = unsafeCursor.offset;
        Buffer buffer = unsafeCursor.buffer;
        Intrinsics.checkNotNull(buffer);
        if (j2 != buffer.size()) {
            long j3 = unsafeCursor.offset;
            return unsafeCursor.seek(j3 == -1 ? 0L : j3 + (unsafeCursor.end - unsafeCursor.start));
        }
        throw new IllegalStateException("no more bytes".toString());
    }

    public static final boolean commonRangeEquals(@NotNull Buffer buffer, long j2, @NotNull ByteString bytes, int i2, int i3) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (j2 < 0 || i2 < 0 || i3 < 0 || buffer.size() - j2 < i3 || bytes.size() - i2 < i3) {
            return false;
        }
        if (i3 <= 0) {
            return true;
        }
        int i4 = 0;
        while (true) {
            int i5 = i4 + 1;
            if (buffer.getByte(i4 + j2) != bytes.getByte(i4 + i2)) {
                return false;
            }
            if (i5 >= i3) {
                return true;
            }
            i4 = i5;
        }
    }

    public static final int commonRead(@NotNull Buffer buffer, @NotNull byte[] sink) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        return buffer.read(sink, 0, sink.length);
    }

    public static final int commonRead(@NotNull Buffer buffer, @NotNull byte[] sink, int i2, int i3) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        _UtilKt.checkOffsetAndCount(sink.length, i2, i3);
        Segment segment = buffer.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i3, segment.limit - segment.pos);
        byte[] bArr = segment.data;
        int i4 = segment.pos;
        ArraysKt___ArraysJvmKt.copyInto(bArr, sink, i2, i4, i4 + min);
        segment.pos += min;
        buffer.setSize$okio(buffer.size() - min);
        if (segment.pos == segment.limit) {
            buffer.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    public static final long commonRead(@NotNull Buffer buffer, @NotNull Buffer sink, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (j2 >= 0) {
            if (buffer.size() == 0) {
                return -1L;
            }
            if (j2 > buffer.size()) {
                j2 = buffer.size();
            }
            sink.write(buffer, j2);
            return j2;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(j2)).toString());
    }

    public static final long commonReadAll(@NotNull Buffer buffer, @NotNull Sink sink) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        long size = buffer.size();
        if (size > 0) {
            sink.write(buffer, size);
        }
        return size;
    }

    @NotNull
    public static final Buffer.UnsafeCursor commonReadAndWriteUnsafe(@NotNull Buffer buffer, @NotNull Buffer.UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        Buffer.UnsafeCursor resolveDefaultParameter = _UtilKt.resolveDefaultParameter(unsafeCursor);
        if (resolveDefaultParameter.buffer == null) {
            resolveDefaultParameter.buffer = buffer;
            resolveDefaultParameter.readWrite = true;
            return resolveDefaultParameter;
        }
        throw new IllegalStateException("already attached to a buffer".toString());
    }

    public static final byte commonReadByte(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (buffer.size() != 0) {
            Segment segment = buffer.head;
            Intrinsics.checkNotNull(segment);
            int i2 = segment.pos;
            int i3 = segment.limit;
            int i4 = i2 + 1;
            byte b2 = segment.data[i2];
            buffer.setSize$okio(buffer.size() - 1);
            if (i4 == i3) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return b2;
        }
        throw new EOFException();
    }

    @NotNull
    public static final byte[] commonReadByteArray(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        return buffer.readByteArray(buffer.size());
    }

    @NotNull
    public static final byte[] commonReadByteArray(@NotNull Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (j2 >= 0 && j2 <= 2147483647L) {
            if (buffer.size() >= j2) {
                byte[] bArr = new byte[(int) j2];
                buffer.readFully(bArr);
                return bArr;
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount: ", Long.valueOf(j2)).toString());
    }

    @NotNull
    public static final ByteString commonReadByteString(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        return buffer.readByteString(buffer.size());
    }

    @NotNull
    public static final ByteString commonReadByteString(@NotNull Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (j2 >= 0 && j2 <= 2147483647L) {
            if (buffer.size() >= j2) {
                if (j2 >= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
                    ByteString snapshot = buffer.snapshot((int) j2);
                    buffer.skip(j2);
                    return snapshot;
                }
                return new ByteString(buffer.readByteArray(j2));
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount: ", Long.valueOf(j2)).toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x0095, code lost:
        r16.setSize$okio(r16.size() - r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x009e, code lost:
        if (r6 == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x00a0, code lost:
        r14 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x00a2, code lost:
        r14 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x00a3, code lost:
        if (r5 >= r14) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x00ad, code lost:
        if (r16.size() == 0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x00af, code lost:
        if (r6 == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x00b1, code lost:
        r1 = "Expected a digit";
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x00b4, code lost:
        r1 = "Expected a digit or '-'";
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x00d9, code lost:
        throw new java.lang.NumberFormatException(r1 + " but was 0x" + okio._UtilKt.toHexString(r16.getByte(0)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x00df, code lost:
        throw new java.io.EOFException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x00e0, code lost:
        if (r6 == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x00e4, code lost:
        return -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:?, code lost:
        return r8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final long commonReadDecimalLong(@NotNull Buffer buffer) {
        byte b2;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (buffer.size() != 0) {
            long j2 = -7;
            int i2 = 0;
            long j3 = 0;
            boolean z = false;
            boolean z2 = false;
            loop0: while (true) {
                Segment segment = buffer.head;
                Intrinsics.checkNotNull(segment);
                byte[] bArr = segment.data;
                int i3 = segment.pos;
                int i4 = segment.limit;
                while (i3 < i4) {
                    b2 = bArr[i3];
                    byte b3 = (byte) 48;
                    if (b2 >= b3 && b2 <= ((byte) 57)) {
                        int i5 = b3 - b2;
                        int i6 = (j3 > OVERFLOW_ZONE ? 1 : (j3 == OVERFLOW_ZONE ? 0 : -1));
                        if (i6 < 0 || (i6 == 0 && i5 < j2)) {
                            break loop0;
                        }
                        j3 = (j3 * 10) + i5;
                    } else if (b2 != ((byte) 45) || i2 != 0) {
                        z2 = true;
                        break;
                    } else {
                        j2--;
                        z = true;
                    }
                    i3++;
                    i2++;
                }
                if (i3 == i4) {
                    buffer.head = segment.pop();
                    SegmentPool.recycle(segment);
                } else {
                    segment.pos = i3;
                }
                if (z2 || buffer.head == null) {
                    break;
                }
            }
            Buffer writeByte = new Buffer().writeDecimalLong(j3).writeByte((int) b2);
            if (!z) {
                writeByte.readByte();
            }
            throw new NumberFormatException(Intrinsics.stringPlus("Number too large: ", writeByte.readUtf8()));
        }
        throw new EOFException();
    }

    public static final void commonReadFully(@NotNull Buffer buffer, @NotNull Buffer sink, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (buffer.size() >= j2) {
            sink.write(buffer, j2);
        } else {
            sink.write(buffer, buffer.size());
            throw new EOFException();
        }
    }

    public static final void commonReadFully(@NotNull Buffer buffer, @NotNull byte[] sink) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        int i2 = 0;
        while (i2 < sink.length) {
            int read = buffer.read(sink, i2, sink.length - i2);
            if (read == -1) {
                throw new EOFException();
            }
            i2 += read;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x009d A[EDGE_INSN: B:90:0x009d->B:85:0x009d ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final long commonReadHexadecimalUnsignedLong(@NotNull Buffer buffer) {
        int i2;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (buffer.size() != 0) {
            int i3 = 0;
            boolean z = false;
            long j2 = 0;
            do {
                Segment segment = buffer.head;
                Intrinsics.checkNotNull(segment);
                byte[] bArr = segment.data;
                int i4 = segment.pos;
                int i5 = segment.limit;
                while (i4 < i5) {
                    byte b2 = bArr[i4];
                    byte b3 = (byte) 48;
                    if (b2 < b3 || b2 > ((byte) 57)) {
                        byte b4 = (byte) 97;
                        if ((b2 >= b4 && b2 <= ((byte) 102)) || (b2 >= (b4 = (byte) 65) && b2 <= ((byte) 70))) {
                            i2 = (b2 - b4) + 10;
                        } else if (i3 == 0) {
                            throw new NumberFormatException(Intrinsics.stringPlus("Expected leading [0-9a-fA-F] character but was 0x", _UtilKt.toHexString(b2)));
                        } else {
                            z = true;
                            if (i4 != i5) {
                                buffer.head = segment.pop();
                                SegmentPool.recycle(segment);
                            } else {
                                segment.pos = i4;
                            }
                            if (!z) {
                                break;
                            }
                        }
                    } else {
                        i2 = b2 - b3;
                    }
                    if (((-1152921504606846976L) & j2) != 0) {
                        throw new NumberFormatException(Intrinsics.stringPlus("Number too large: ", new Buffer().writeHexadecimalUnsignedLong(j2).writeByte((int) b2).readUtf8()));
                    }
                    j2 = (j2 << 4) | i2;
                    i4++;
                    i3++;
                }
                if (i4 != i5) {
                }
                if (!z) {
                }
            } while (buffer.head != null);
            buffer.setSize$okio(buffer.size() - i3);
            return j2;
        }
        throw new EOFException();
    }

    public static final int commonReadInt(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (buffer.size() >= 4) {
            Segment segment = buffer.head;
            Intrinsics.checkNotNull(segment);
            int i2 = segment.pos;
            int i3 = segment.limit;
            if (i3 - i2 < 4) {
                return (buffer.readByte() & 255) | ((buffer.readByte() & 255) << 24) | ((buffer.readByte() & 255) << 16) | ((buffer.readByte() & 255) << 8);
            }
            byte[] bArr = segment.data;
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i2] & 255) << 24) | ((bArr[i4] & 255) << 16);
            int i7 = i5 + 1;
            int i8 = i6 | ((bArr[i5] & 255) << 8);
            int i9 = i7 + 1;
            int i10 = i8 | (bArr[i7] & 255);
            buffer.setSize$okio(buffer.size() - 4);
            if (i9 == i3) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i9;
            }
            return i10;
        }
        throw new EOFException();
    }

    public static final long commonReadLong(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (buffer.size() >= 8) {
            Segment segment = buffer.head;
            Intrinsics.checkNotNull(segment);
            int i2 = segment.pos;
            int i3 = segment.limit;
            if (i3 - i2 < 8) {
                return ((buffer.readInt() & BodyPartID.bodyIdMax) << 32) | (BodyPartID.bodyIdMax & buffer.readInt());
            }
            byte[] bArr = segment.data;
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int i8 = i7 + 1;
            int i9 = i8 + 1;
            long j2 = ((bArr[i2] & 255) << 56) | ((bArr[i4] & 255) << 48) | ((bArr[i5] & 255) << 40) | ((bArr[i6] & 255) << 32) | ((bArr[i7] & 255) << 24) | ((bArr[i8] & 255) << 16);
            int i10 = i9 + 1;
            int i11 = i10 + 1;
            long j3 = j2 | ((bArr[i9] & 255) << 8) | (bArr[i10] & 255);
            buffer.setSize$okio(buffer.size() - 8);
            if (i11 == i3) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i11;
            }
            return j3;
        }
        throw new EOFException();
    }

    public static final short commonReadShort(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (buffer.size() >= 2) {
            Segment segment = buffer.head;
            Intrinsics.checkNotNull(segment);
            int i2 = segment.pos;
            int i3 = segment.limit;
            if (i3 - i2 < 2) {
                return (short) ((buffer.readByte() & 255) | ((buffer.readByte() & 255) << 8));
            }
            byte[] bArr = segment.data;
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i2] & 255) << 8) | (bArr[i4] & 255);
            buffer.setSize$okio(buffer.size() - 2);
            if (i5 == i3) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i5;
            }
            return (short) i6;
        }
        throw new EOFException();
    }

    @NotNull
    public static final Buffer.UnsafeCursor commonReadUnsafe(@NotNull Buffer buffer, @NotNull Buffer.UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        Buffer.UnsafeCursor resolveDefaultParameter = _UtilKt.resolveDefaultParameter(unsafeCursor);
        if (resolveDefaultParameter.buffer == null) {
            resolveDefaultParameter.buffer = buffer;
            resolveDefaultParameter.readWrite = false;
            return resolveDefaultParameter;
        }
        throw new IllegalStateException("already attached to a buffer".toString());
    }

    @NotNull
    public static final String commonReadUtf8(@NotNull Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i2 >= 0 && j2 <= 2147483647L) {
            if (buffer.size() >= j2) {
                if (i2 == 0) {
                    return "";
                }
                Segment segment = buffer.head;
                Intrinsics.checkNotNull(segment);
                int i3 = segment.pos;
                if (i3 + j2 > segment.limit) {
                    return _Utf8Kt.commonToUtf8String$default(buffer.readByteArray(j2), 0, 0, 3, null);
                }
                int i4 = (int) j2;
                String commonToUtf8String = _Utf8Kt.commonToUtf8String(segment.data, i3, i3 + i4);
                segment.pos += i4;
                buffer.setSize$okio(buffer.size() - j2);
                if (segment.pos == segment.limit) {
                    buffer.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
                return commonToUtf8String;
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount: ", Long.valueOf(j2)).toString());
    }

    public static final int commonReadUtf8CodePoint(@NotNull Buffer buffer) {
        int i2;
        int i3;
        int i4;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (buffer.size() != 0) {
            byte b2 = buffer.getByte(0L);
            boolean z = false;
            if ((b2 & 128) == 0) {
                i2 = b2 & Byte.MAX_VALUE;
                i4 = 0;
                i3 = 1;
            } else if ((b2 & 224) == 192) {
                i2 = b2 & Ascii.US;
                i3 = 2;
                i4 = 128;
            } else if ((b2 & 240) == 224) {
                i2 = b2 & Ascii.SI;
                i3 = 3;
                i4 = 2048;
            } else if ((b2 & 248) != 240) {
                buffer.skip(1L);
                return Utf8.REPLACEMENT_CODE_POINT;
            } else {
                i2 = b2 & 7;
                i3 = 4;
                i4 = 65536;
            }
            long j2 = i3;
            if (buffer.size() < j2) {
                throw new EOFException("size < " + i3 + ": " + buffer.size() + " (to read code point prefixed 0x" + _UtilKt.toHexString(b2) + ')');
            }
            if (1 < i3) {
                int i5 = 1;
                while (true) {
                    int i6 = i5 + 1;
                    long j3 = i5;
                    byte b3 = buffer.getByte(j3);
                    if ((b3 & 192) != 128) {
                        buffer.skip(j3);
                        return Utf8.REPLACEMENT_CODE_POINT;
                    }
                    i2 = (i2 << 6) | (b3 & Utf8.REPLACEMENT_BYTE);
                    if (i6 >= i3) {
                        break;
                    }
                    i5 = i6;
                }
            }
            buffer.skip(j2);
            if (i2 > 1114111) {
                return Utf8.REPLACEMENT_CODE_POINT;
            }
            if (55296 <= i2 && i2 <= 57343) {
                z = true;
            }
            return (!z && i2 >= i4) ? i2 : Utf8.REPLACEMENT_CODE_POINT;
        }
        throw new EOFException();
    }

    @Nullable
    public static final String commonReadUtf8Line(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        long indexOf = buffer.indexOf((byte) 10);
        if (indexOf != -1) {
            return readUtf8Line(buffer, indexOf);
        }
        if (buffer.size() != 0) {
            return buffer.readUtf8(buffer.size());
        }
        return null;
    }

    @NotNull
    public static final String commonReadUtf8LineStrict(@NotNull Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (j2 >= 0) {
            long j3 = LongCompanionObject.MAX_VALUE;
            if (j2 != LongCompanionObject.MAX_VALUE) {
                j3 = j2 + 1;
            }
            byte b2 = (byte) 10;
            long indexOf = buffer.indexOf(b2, 0L, j3);
            if (indexOf != -1) {
                return readUtf8Line(buffer, indexOf);
            }
            if (j3 < buffer.size() && buffer.getByte(j3 - 1) == ((byte) 13) && buffer.getByte(j3) == b2) {
                return readUtf8Line(buffer, j3);
            }
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, Math.min(32, buffer.size()));
            throw new EOFException("\\n not found: limit=" + Math.min(buffer.size(), j2) + " content=" + buffer2.readByteString().hex() + Typography.ellipsis);
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("limit < 0: ", Long.valueOf(j2)).toString());
    }

    public static final long commonResizeBuffer(@NotNull Buffer.UnsafeCursor unsafeCursor, long j2) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "<this>");
        Buffer buffer = unsafeCursor.buffer;
        if (buffer != null) {
            if (unsafeCursor.readWrite) {
                long size = buffer.size();
                int i2 = (j2 > size ? 1 : (j2 == size ? 0 : -1));
                int i3 = 1;
                if (i2 <= 0) {
                    if (!(j2 >= 0)) {
                        throw new IllegalArgumentException(Intrinsics.stringPlus("newSize < 0: ", Long.valueOf(j2)).toString());
                    }
                    long j3 = size - j2;
                    while (true) {
                        if (j3 <= 0) {
                            break;
                        }
                        Segment segment = buffer.head;
                        Intrinsics.checkNotNull(segment);
                        Segment segment2 = segment.prev;
                        Intrinsics.checkNotNull(segment2);
                        int i4 = segment2.limit;
                        long j4 = i4 - segment2.pos;
                        if (j4 > j3) {
                            segment2.limit = i4 - ((int) j3);
                            break;
                        }
                        buffer.head = segment2.pop();
                        SegmentPool.recycle(segment2);
                        j3 -= j4;
                    }
                    unsafeCursor.setSegment$okio(null);
                    unsafeCursor.offset = j2;
                    unsafeCursor.data = null;
                    unsafeCursor.start = -1;
                    unsafeCursor.end = -1;
                } else if (i2 > 0) {
                    long j5 = j2 - size;
                    boolean z = true;
                    while (j5 > 0) {
                        Segment writableSegment$okio = buffer.writableSegment$okio(i3);
                        int min = (int) Math.min(j5, 8192 - writableSegment$okio.limit);
                        writableSegment$okio.limit += min;
                        j5 -= min;
                        if (z) {
                            unsafeCursor.setSegment$okio(writableSegment$okio);
                            unsafeCursor.offset = size;
                            unsafeCursor.data = writableSegment$okio.data;
                            int i5 = writableSegment$okio.limit;
                            unsafeCursor.start = i5 - min;
                            unsafeCursor.end = i5;
                            z = false;
                        }
                        i3 = 1;
                    }
                }
                buffer.setSize$okio(j2);
                return size;
            }
            throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers".toString());
        }
        throw new IllegalStateException("not attached to a buffer".toString());
    }

    public static final int commonSeek(@NotNull Buffer.UnsafeCursor unsafeCursor, long j2) {
        Segment segment;
        Intrinsics.checkNotNullParameter(unsafeCursor, "<this>");
        Buffer buffer = unsafeCursor.buffer;
        if (buffer != null) {
            int i2 = (j2 > (-1L) ? 1 : (j2 == (-1L) ? 0 : -1));
            if (i2 < 0 || j2 > buffer.size()) {
                throw new ArrayIndexOutOfBoundsException("offset=" + j2 + " > size=" + buffer.size());
            } else if (i2 == 0 || j2 == buffer.size()) {
                unsafeCursor.setSegment$okio(null);
                unsafeCursor.offset = j2;
                unsafeCursor.data = null;
                unsafeCursor.start = -1;
                unsafeCursor.end = -1;
                return -1;
            } else {
                long j3 = 0;
                long size = buffer.size();
                Segment segment2 = buffer.head;
                if (unsafeCursor.getSegment$okio() != null) {
                    long j4 = unsafeCursor.offset;
                    int i3 = unsafeCursor.start;
                    Segment segment$okio = unsafeCursor.getSegment$okio();
                    Intrinsics.checkNotNull(segment$okio);
                    long j5 = j4 - (i3 - segment$okio.pos);
                    if (j5 > j2) {
                        segment2 = unsafeCursor.getSegment$okio();
                        size = j5;
                        segment = segment2;
                    } else {
                        segment = unsafeCursor.getSegment$okio();
                        j3 = j5;
                    }
                } else {
                    segment = segment2;
                }
                if (size - j2 > j2 - j3) {
                    while (true) {
                        Intrinsics.checkNotNull(segment);
                        int i4 = segment.limit;
                        int i5 = segment.pos;
                        if (j2 < (i4 - i5) + j3) {
                            break;
                        }
                        j3 += i4 - i5;
                        segment = segment.next;
                    }
                } else {
                    while (size > j2) {
                        Intrinsics.checkNotNull(segment2);
                        segment2 = segment2.prev;
                        Intrinsics.checkNotNull(segment2);
                        size -= segment2.limit - segment2.pos;
                    }
                    j3 = size;
                    segment = segment2;
                }
                if (unsafeCursor.readWrite) {
                    Intrinsics.checkNotNull(segment);
                    if (segment.shared) {
                        Segment unsharedCopy = segment.unsharedCopy();
                        if (buffer.head == segment) {
                            buffer.head = unsharedCopy;
                        }
                        segment = segment.push(unsharedCopy);
                        Segment segment3 = segment.prev;
                        Intrinsics.checkNotNull(segment3);
                        segment3.pop();
                    }
                }
                unsafeCursor.setSegment$okio(segment);
                unsafeCursor.offset = j2;
                Intrinsics.checkNotNull(segment);
                unsafeCursor.data = segment.data;
                int i6 = segment.pos + ((int) (j2 - j3));
                unsafeCursor.start = i6;
                int i7 = segment.limit;
                unsafeCursor.end = i7;
                return i7 - i6;
            }
        }
        throw new IllegalStateException("not attached to a buffer".toString());
    }

    public static final int commonSelect(@NotNull Buffer buffer, @NotNull Options options) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        int selectPrefix$default = selectPrefix$default(buffer, options, false, 2, null);
        if (selectPrefix$default == -1) {
            return -1;
        }
        buffer.skip(options.getByteStrings$okio()[selectPrefix$default].size());
        return selectPrefix$default;
    }

    public static final void commonSkip(@NotNull Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        while (j2 > 0) {
            Segment segment = buffer.head;
            if (segment == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j2, segment.limit - segment.pos);
            long j3 = min;
            buffer.setSize$okio(buffer.size() - j3);
            j2 -= j3;
            int i2 = segment.pos + min;
            segment.pos = i2;
            if (i2 == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }

    @NotNull
    public static final ByteString commonSnapshot(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (buffer.size() <= 2147483647L) {
            return buffer.snapshot((int) buffer.size());
        }
        throw new IllegalStateException(Intrinsics.stringPlus("size > Int.MAX_VALUE: ", Long.valueOf(buffer.size())).toString());
    }

    @NotNull
    public static final ByteString commonSnapshot(@NotNull Buffer buffer, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (i2 == 0) {
            return ByteString.EMPTY;
        }
        _UtilKt.checkOffsetAndCount(buffer.size(), 0L, i2);
        Segment segment = buffer.head;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Intrinsics.checkNotNull(segment);
            int i6 = segment.limit;
            int i7 = segment.pos;
            if (i6 == i7) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += i6 - i7;
            i5++;
            segment = segment.next;
        }
        byte[][] bArr = new byte[i5];
        int[] iArr = new int[i5 * 2];
        Segment segment2 = buffer.head;
        int i8 = 0;
        while (i3 < i2) {
            Intrinsics.checkNotNull(segment2);
            bArr[i8] = segment2.data;
            i3 += segment2.limit - segment2.pos;
            iArr[i8] = Math.min(i3, i2);
            iArr[i8 + i5] = segment2.pos;
            segment2.shared = true;
            i8++;
            segment2 = segment2.next;
        }
        return new SegmentedByteString(bArr, iArr);
    }

    @NotNull
    public static final Segment commonWritableSegment(@NotNull Buffer buffer, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        boolean z = true;
        if ((i2 < 1 || i2 > 8192) ? false : false) {
            Segment segment = buffer.head;
            if (segment != null) {
                Intrinsics.checkNotNull(segment);
                Segment segment2 = segment.prev;
                Intrinsics.checkNotNull(segment2);
                return (segment2.limit + i2 > 8192 || !segment2.owner) ? segment2.push(SegmentPool.take()) : segment2;
            }
            Segment take = SegmentPool.take();
            buffer.head = take;
            take.prev = take;
            take.next = take;
            return take;
        }
        throw new IllegalArgumentException("unexpected capacity".toString());
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer buffer, @NotNull ByteString byteString, int i2, int i3) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(buffer, i2, i3);
        return buffer;
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer buffer, @NotNull Source source, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        while (j2 > 0) {
            long read = source.read(buffer, j2);
            if (read == -1) {
                throw new EOFException();
            }
            j2 -= read;
        }
        return buffer;
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer buffer, @NotNull byte[] source) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        return buffer.write(source, 0, source.length);
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer buffer, @NotNull byte[] source, int i2, int i3) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        long j2 = i3;
        _UtilKt.checkOffsetAndCount(source.length, i2, j2);
        int i4 = i3 + i2;
        while (i2 < i4) {
            Segment writableSegment$okio = buffer.writableSegment$okio(1);
            int min = Math.min(i4 - i2, 8192 - writableSegment$okio.limit);
            int i5 = i2 + min;
            ArraysKt___ArraysJvmKt.copyInto(source, writableSegment$okio.data, writableSegment$okio.limit, i2, i5);
            writableSegment$okio.limit += min;
            i2 = i5;
        }
        buffer.setSize$okio(buffer.size() + j2);
        return buffer;
    }

    public static final void commonWrite(@NotNull Buffer buffer, @NotNull Buffer source, long j2) {
        Segment segment;
        Segment segment2;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(source != buffer)) {
            throw new IllegalArgumentException("source == this".toString());
        }
        _UtilKt.checkOffsetAndCount(source.size(), 0L, j2);
        while (j2 > 0) {
            Segment segment3 = source.head;
            Intrinsics.checkNotNull(segment3);
            int i2 = segment3.limit;
            Intrinsics.checkNotNull(source.head);
            if (j2 < i2 - segment.pos) {
                Segment segment4 = buffer.head;
                if (segment4 != null) {
                    Intrinsics.checkNotNull(segment4);
                    segment2 = segment4.prev;
                } else {
                    segment2 = null;
                }
                if (segment2 != null && segment2.owner) {
                    if ((segment2.limit + j2) - (segment2.shared ? 0 : segment2.pos) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        Segment segment5 = source.head;
                        Intrinsics.checkNotNull(segment5);
                        segment5.writeTo(segment2, (int) j2);
                        source.setSize$okio(source.size() - j2);
                        buffer.setSize$okio(buffer.size() + j2);
                        return;
                    }
                }
                Segment segment6 = source.head;
                Intrinsics.checkNotNull(segment6);
                source.head = segment6.split((int) j2);
            }
            Segment segment7 = source.head;
            Intrinsics.checkNotNull(segment7);
            long j3 = segment7.limit - segment7.pos;
            source.head = segment7.pop();
            Segment segment8 = buffer.head;
            if (segment8 == null) {
                buffer.head = segment7;
                segment7.prev = segment7;
                segment7.next = segment7;
            } else {
                Intrinsics.checkNotNull(segment8);
                Segment segment9 = segment8.prev;
                Intrinsics.checkNotNull(segment9);
                segment9.push(segment7).compact();
            }
            source.setSize$okio(source.size() - j3);
            buffer.setSize$okio(buffer.size() + j3);
            j2 -= j3;
        }
    }

    public static /* synthetic */ Buffer commonWrite$default(Buffer buffer, ByteString byteString, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = byteString.size();
        }
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(buffer, i2, i3);
        return buffer;
    }

    public static final long commonWriteAll(@NotNull Buffer buffer, @NotNull Source source) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        long j2 = 0;
        while (true) {
            long read = source.read(buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j2;
            }
            j2 += read;
        }
    }

    @NotNull
    public static final Buffer commonWriteByte(@NotNull Buffer buffer, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Segment writableSegment$okio = buffer.writableSegment$okio(1);
        byte[] bArr = writableSegment$okio.data;
        int i3 = writableSegment$okio.limit;
        writableSegment$okio.limit = i3 + 1;
        bArr[i3] = (byte) i2;
        buffer.setSize$okio(buffer.size() + 1);
        return buffer;
    }

    @NotNull
    public static final Buffer commonWriteDecimalLong(@NotNull Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i2 == 0) {
            return buffer.writeByte(48);
        }
        boolean z = false;
        int i3 = 1;
        if (i2 < 0) {
            j2 = -j2;
            if (j2 < 0) {
                return buffer.writeUtf8("-9223372036854775808");
            }
            z = true;
        }
        if (j2 >= 100000000) {
            i3 = j2 < 1000000000000L ? j2 < RealConnection.IDLE_CONNECTION_HEALTHY_NS ? j2 < 1000000000 ? 9 : 10 : j2 < 100000000000L ? 11 : 12 : j2 < 1000000000000000L ? j2 < 10000000000000L ? 13 : j2 < 100000000000000L ? 14 : 15 : j2 < 100000000000000000L ? j2 < 10000000000000000L ? 16 : 17 : j2 < 1000000000000000000L ? 18 : 19;
        } else if (j2 >= 10000) {
            i3 = j2 < 1000000 ? j2 < 100000 ? 5 : 6 : j2 < 10000000 ? 7 : 8;
        } else if (j2 >= 100) {
            i3 = j2 < 1000 ? 3 : 4;
        } else if (j2 >= 10) {
            i3 = 2;
        }
        if (z) {
            i3++;
        }
        Segment writableSegment$okio = buffer.writableSegment$okio(i3);
        byte[] bArr = writableSegment$okio.data;
        int i4 = writableSegment$okio.limit + i3;
        while (j2 != 0) {
            long j3 = 10;
            i4--;
            bArr[i4] = getHEX_DIGIT_BYTES()[(int) (j2 % j3)];
            j2 /= j3;
        }
        if (z) {
            bArr[i4 - 1] = (byte) 45;
        }
        writableSegment$okio.limit += i3;
        buffer.setSize$okio(buffer.size() + i3);
        return buffer;
    }

    @NotNull
    public static final Buffer commonWriteHexadecimalUnsignedLong(@NotNull Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (j2 == 0) {
            return buffer.writeByte(48);
        }
        long j3 = (j2 >>> 1) | j2;
        long j4 = j3 | (j3 >>> 2);
        long j5 = j4 | (j4 >>> 4);
        long j6 = j5 | (j5 >>> 8);
        long j7 = j6 | (j6 >>> 16);
        long j8 = j7 | (j7 >>> 32);
        long j9 = j8 - ((j8 >>> 1) & 6148914691236517205L);
        long j10 = ((j9 >>> 2) & 3689348814741910323L) + (j9 & 3689348814741910323L);
        long j11 = ((j10 >>> 4) + j10) & 1085102592571150095L;
        long j12 = j11 + (j11 >>> 8);
        long j13 = j12 + (j12 >>> 16);
        int i2 = (int) ((((j13 & 63) + ((j13 >>> 32) & 63)) + 3) / 4);
        Segment writableSegment$okio = buffer.writableSegment$okio(i2);
        byte[] bArr = writableSegment$okio.data;
        int i3 = writableSegment$okio.limit;
        for (int i4 = (i3 + i2) - 1; i4 >= i3; i4--) {
            bArr[i4] = getHEX_DIGIT_BYTES()[(int) (15 & j2)];
            j2 >>>= 4;
        }
        writableSegment$okio.limit += i2;
        buffer.setSize$okio(buffer.size() + i2);
        return buffer;
    }

    @NotNull
    public static final Buffer commonWriteInt(@NotNull Buffer buffer, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Segment writableSegment$okio = buffer.writableSegment$okio(4);
        byte[] bArr = writableSegment$okio.data;
        int i3 = writableSegment$okio.limit;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 24) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i2 >>> 16) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((i2 >>> 8) & 255);
        bArr[i6] = (byte) (i2 & 255);
        writableSegment$okio.limit = i6 + 1;
        buffer.setSize$okio(buffer.size() + 4);
        return buffer;
    }

    @NotNull
    public static final Buffer commonWriteLong(@NotNull Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Segment writableSegment$okio = buffer.writableSegment$okio(8);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((j2 >>> 56) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((j2 >>> 48) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((j2 >>> 40) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((j2 >>> 32) & 255);
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((j2 >>> 24) & 255);
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((j2 >>> 16) & 255);
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((j2 >>> 8) & 255);
        bArr[i9] = (byte) (j2 & 255);
        writableSegment$okio.limit = i9 + 1;
        buffer.setSize$okio(buffer.size() + 8);
        return buffer;
    }

    @NotNull
    public static final Buffer commonWriteShort(@NotNull Buffer buffer, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Segment writableSegment$okio = buffer.writableSegment$okio(2);
        byte[] bArr = writableSegment$okio.data;
        int i3 = writableSegment$okio.limit;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 8) & 255);
        bArr[i4] = (byte) (i2 & 255);
        writableSegment$okio.limit = i4 + 1;
        buffer.setSize$okio(buffer.size() + 2);
        return buffer;
    }

    @NotNull
    public static final Buffer commonWriteUtf8(@NotNull Buffer buffer, @NotNull String string, int i2, int i3) {
        char charAt;
        long size;
        long j2;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        if (i2 >= 0) {
            if (!(i3 >= i2)) {
                throw new IllegalArgumentException(("endIndex < beginIndex: " + i3 + " < " + i2).toString());
            }
            if (!(i3 <= string.length())) {
                throw new IllegalArgumentException(("endIndex > string.length: " + i3 + " > " + string.length()).toString());
            }
            while (i2 < i3) {
                char charAt2 = string.charAt(i2);
                if (charAt2 < 128) {
                    Segment writableSegment$okio = buffer.writableSegment$okio(1);
                    byte[] bArr = writableSegment$okio.data;
                    int i4 = writableSegment$okio.limit - i2;
                    int min = Math.min(i3, 8192 - i4);
                    int i5 = i2 + 1;
                    bArr[i2 + i4] = (byte) charAt2;
                    while (true) {
                        i2 = i5;
                        if (i2 >= min || (charAt = string.charAt(i2)) >= 128) {
                            break;
                        }
                        i5 = i2 + 1;
                        bArr[i2 + i4] = (byte) charAt;
                    }
                    int i6 = writableSegment$okio.limit;
                    int i7 = (i4 + i2) - i6;
                    writableSegment$okio.limit = i6 + i7;
                    buffer.setSize$okio(buffer.size() + i7);
                } else {
                    if (charAt2 < 2048) {
                        Segment writableSegment$okio2 = buffer.writableSegment$okio(2);
                        byte[] bArr2 = writableSegment$okio2.data;
                        int i8 = writableSegment$okio2.limit;
                        bArr2[i8] = (byte) ((charAt2 >> 6) | 192);
                        bArr2[i8 + 1] = (byte) ((charAt2 & '?') | 128);
                        writableSegment$okio2.limit = i8 + 2;
                        size = buffer.size();
                        j2 = 2;
                    } else if (charAt2 < 55296 || charAt2 > 57343) {
                        Segment writableSegment$okio3 = buffer.writableSegment$okio(3);
                        byte[] bArr3 = writableSegment$okio3.data;
                        int i9 = writableSegment$okio3.limit;
                        bArr3[i9] = (byte) ((charAt2 >> '\f') | BERTags.FLAGS);
                        bArr3[i9 + 1] = (byte) ((63 & (charAt2 >> 6)) | 128);
                        bArr3[i9 + 2] = (byte) ((charAt2 & '?') | 128);
                        writableSegment$okio3.limit = i9 + 3;
                        size = buffer.size();
                        j2 = 3;
                    } else {
                        int i10 = i2 + 1;
                        char charAt3 = i10 < i3 ? string.charAt(i10) : (char) 0;
                        if (charAt2 <= 56319) {
                            if (56320 <= charAt3 && charAt3 <= 57343) {
                                int i11 = (((charAt2 & 1023) << 10) | (charAt3 & 1023)) + 65536;
                                Segment writableSegment$okio4 = buffer.writableSegment$okio(4);
                                byte[] bArr4 = writableSegment$okio4.data;
                                int i12 = writableSegment$okio4.limit;
                                bArr4[i12] = (byte) ((i11 >> 18) | 240);
                                bArr4[i12 + 1] = (byte) (((i11 >> 12) & 63) | 128);
                                bArr4[i12 + 2] = (byte) (((i11 >> 6) & 63) | 128);
                                bArr4[i12 + 3] = (byte) ((i11 & 63) | 128);
                                writableSegment$okio4.limit = i12 + 4;
                                buffer.setSize$okio(buffer.size() + 4);
                                i2 += 2;
                            }
                        }
                        buffer.writeByte(63);
                        i2 = i10;
                    }
                    buffer.setSize$okio(size + j2);
                    i2++;
                }
            }
            return buffer;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("beginIndex < 0: ", Integer.valueOf(i2)).toString());
    }

    @NotNull
    public static final Buffer commonWriteUtf8CodePoint(@NotNull Buffer buffer, int i2) {
        long size;
        long j2;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (i2 < 128) {
            buffer.writeByte(i2);
        } else {
            if (i2 < 2048) {
                Segment writableSegment$okio = buffer.writableSegment$okio(2);
                byte[] bArr = writableSegment$okio.data;
                int i3 = writableSegment$okio.limit;
                bArr[i3] = (byte) ((i2 >> 6) | 192);
                bArr[i3 + 1] = (byte) ((i2 & 63) | 128);
                writableSegment$okio.limit = i3 + 2;
                size = buffer.size();
                j2 = 2;
            } else {
                boolean z = false;
                if (55296 <= i2 && i2 <= 57343) {
                    z = true;
                }
                if (z) {
                    buffer.writeByte(63);
                } else if (i2 < 65536) {
                    Segment writableSegment$okio2 = buffer.writableSegment$okio(3);
                    byte[] bArr2 = writableSegment$okio2.data;
                    int i4 = writableSegment$okio2.limit;
                    bArr2[i4] = (byte) ((i2 >> 12) | BERTags.FLAGS);
                    bArr2[i4 + 1] = (byte) (((i2 >> 6) & 63) | 128);
                    bArr2[i4 + 2] = (byte) ((i2 & 63) | 128);
                    writableSegment$okio2.limit = i4 + 3;
                    size = buffer.size();
                    j2 = 3;
                } else if (i2 > 1114111) {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Unexpected code point: 0x", _UtilKt.toHexString(i2)));
                } else {
                    Segment writableSegment$okio3 = buffer.writableSegment$okio(4);
                    byte[] bArr3 = writableSegment$okio3.data;
                    int i5 = writableSegment$okio3.limit;
                    bArr3[i5] = (byte) ((i2 >> 18) | 240);
                    bArr3[i5 + 1] = (byte) (((i2 >> 12) & 63) | 128);
                    bArr3[i5 + 2] = (byte) (((i2 >> 6) & 63) | 128);
                    bArr3[i5 + 3] = (byte) ((i2 & 63) | 128);
                    writableSegment$okio3.limit = i5 + 4;
                    size = buffer.size();
                    j2 = 4;
                }
            }
            buffer.setSize$okio(size + j2);
        }
        return buffer;
    }

    @NotNull
    public static final byte[] getHEX_DIGIT_BYTES() {
        return HEX_DIGIT_BYTES;
    }

    public static /* synthetic */ void getHEX_DIGIT_BYTES$annotations() {
    }

    public static final boolean rangeEquals(@NotNull Segment segment, int i2, @NotNull byte[] bytes, int i3, int i4) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        int i5 = segment.limit;
        byte[] bArr = segment.data;
        while (i3 < i4) {
            if (i2 == i5) {
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                byte[] bArr2 = segment.data;
                bArr = bArr2;
                i2 = segment.pos;
                i5 = segment.limit;
            }
            if (bArr[i2] != bytes[i3]) {
                return false;
            }
            i2++;
            i3++;
        }
        return true;
    }

    @NotNull
    public static final String readUtf8Line(@NotNull Buffer buffer, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (j2 > 0) {
            long j3 = j2 - 1;
            if (buffer.getByte(j3) == ((byte) 13)) {
                String readUtf8 = buffer.readUtf8(j3);
                buffer.skip(2L);
                return readUtf8;
            }
        }
        String readUtf82 = buffer.readUtf8(j2);
        buffer.skip(1L);
        return readUtf82;
    }

    public static final <T> T seek(@NotNull Buffer buffer, long j2, @NotNull Function2<? super Segment, ? super Long, ? extends T> lambda) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(lambda, "lambda");
        Segment segment = buffer.head;
        if (segment == null) {
            return lambda.invoke(null, -1L);
        }
        if (buffer.size() - j2 < j2) {
            long size = buffer.size();
            while (size > j2) {
                segment = segment.prev;
                Intrinsics.checkNotNull(segment);
                size -= segment.limit - segment.pos;
            }
            return lambda.invoke(segment, Long.valueOf(size));
        }
        long j3 = 0;
        while (true) {
            long j4 = (segment.limit - segment.pos) + j3;
            if (j4 > j2) {
                return lambda.invoke(segment, Long.valueOf(j3));
            }
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
            j3 = j4;
        }
    }

    public static final int selectPrefix(@NotNull Buffer buffer, @NotNull Options options, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        Segment segment;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Segment segment2 = buffer.head;
        if (segment2 == null) {
            return z ? -2 : -1;
        }
        byte[] bArr = segment2.data;
        int i6 = segment2.pos;
        int i7 = segment2.limit;
        int[] trie$okio = options.getTrie$okio();
        Segment segment3 = segment2;
        int i8 = -1;
        int i9 = 0;
        loop0: while (true) {
            int i10 = i9 + 1;
            int i11 = trie$okio[i9];
            int i12 = i10 + 1;
            int i13 = trie$okio[i10];
            if (i13 != -1) {
                i8 = i13;
            }
            if (segment3 == null) {
                break;
            }
            if (i11 >= 0) {
                i2 = i6 + 1;
                int i14 = bArr[i6] & 255;
                int i15 = i12 + i11;
                while (i12 != i15) {
                    if (i14 == trie$okio[i12]) {
                        i3 = trie$okio[i12 + i11];
                        if (i2 == i7) {
                            segment3 = segment3.next;
                            Intrinsics.checkNotNull(segment3);
                            i2 = segment3.pos;
                            bArr = segment3.data;
                            i7 = segment3.limit;
                            if (segment3 == segment2) {
                                segment3 = null;
                            }
                        }
                    } else {
                        i12++;
                    }
                }
                return i8;
            }
            int i16 = i12 + (i11 * (-1));
            while (true) {
                int i17 = i6 + 1;
                int i18 = i12 + 1;
                if ((bArr[i6] & 255) != trie$okio[i12]) {
                    return i8;
                }
                boolean z2 = i18 == i16;
                if (i17 == i7) {
                    Intrinsics.checkNotNull(segment3);
                    Segment segment4 = segment3.next;
                    Intrinsics.checkNotNull(segment4);
                    i5 = segment4.pos;
                    byte[] bArr2 = segment4.data;
                    i4 = segment4.limit;
                    if (segment4 != segment2) {
                        segment = segment4;
                        bArr = bArr2;
                    } else if (!z2) {
                        break loop0;
                    } else {
                        bArr = bArr2;
                        segment = null;
                    }
                } else {
                    Segment segment5 = segment3;
                    i4 = i7;
                    i5 = i17;
                    segment = segment5;
                }
                if (z2) {
                    i3 = trie$okio[i18];
                    i2 = i5;
                    i7 = i4;
                    segment3 = segment;
                    break;
                }
                i6 = i5;
                i7 = i4;
                i12 = i18;
                segment3 = segment;
            }
            if (i3 >= 0) {
                return i3;
            }
            i9 = -i3;
            i6 = i2;
        }
        if (z) {
            return -2;
        }
        return i8;
    }

    public static /* synthetic */ int selectPrefix$default(Buffer buffer, Options options, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return selectPrefix(buffer, options, z);
    }
}
