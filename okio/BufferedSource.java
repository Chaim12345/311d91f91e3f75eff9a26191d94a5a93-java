package okio;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0004\u001a\u00020\u0003H'J\b\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H&J\b\u0010\r\u001a\u00020\fH&J\b\u0010\u000f\u001a\u00020\u000eH&J\b\u0010\u0010\u001a\u00020\u000eH&J\b\u0010\u0012\u001a\u00020\u0011H&J\b\u0010\u0013\u001a\u00020\u0011H&J\b\u0010\u0014\u001a\u00020\u0007H&J\b\u0010\u0015\u001a\u00020\u0007H&J\b\u0010\u0016\u001a\u00020\u0007H&J\b\u0010\u0017\u001a\u00020\u0007H&J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H&J\b\u0010\u001a\u001a\u00020\u0019H&J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001bH&J\b\u0010\u001f\u001a\u00020\u001eH&J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u001eH&J\u0010\u0010\"\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001eH&J \u0010!\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0011H&J\u0018\u0010\"\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010%\u001a\u00020\u00072\u0006\u0010 \u001a\u00020$H&J\b\u0010'\u001a\u00020&H&J\u0010\u0010'\u001a\u00020&2\u0006\u0010\b\u001a\u00020\u0007H&J\n\u0010(\u001a\u0004\u0018\u00010&H&J\b\u0010)\u001a\u00020&H&J\u0010\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020\u0007H&J\b\u0010+\u001a\u00020\u0011H&J\u0010\u0010.\u001a\u00020&2\u0006\u0010-\u001a\u00020,H&J\u0018\u0010.\u001a\u00020&2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010-\u001a\u00020,H&J\u0010\u00100\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\fH&J\u0018\u00100\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\f2\u0006\u00101\u001a\u00020\u0007H&J \u00100\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\f2\u0006\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u0007H&J\u0010\u00100\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u0019H&J\u0018\u00100\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u00192\u0006\u00101\u001a\u00020\u0007H&J\u0010\u00105\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u0019H&J\u0018\u00105\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u00192\u0006\u00101\u001a\u00020\u0007H&J\u0018\u00106\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u0019H&J(\u00106\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u00192\u0006\u00107\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0011H&J\b\u00108\u001a\u00020\u0000H&J\b\u0010:\u001a\u000209H&R\u0016\u0010\u0004\u001a\u00020\u00038&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006="}, d2 = {"Lokio/BufferedSource;", "Lokio/Source;", "Ljava/nio/channels/ReadableByteChannel;", "Lokio/Buffer;", "buffer", "", "exhausted", "", "byteCount", "", "require", "request", "", "readByte", "", "readShort", "readShortLe", "", "readInt", "readIntLe", "readLong", "readLongLe", "readDecimalLong", "readHexadecimalUnsignedLong", AppConstants.GEO_FENCE_TIME_MODE_SKIP, "Lokio/ByteString;", "readByteString", "Lokio/Options;", "options", "select", "", "readByteArray", "sink", "read", "readFully", TypedValues.Cycle.S_WAVE_OFFSET, "Lokio/Sink;", "readAll", "", "readUtf8", "readUtf8Line", "readUtf8LineStrict", "limit", "readUtf8CodePoint", "Ljava/nio/charset/Charset;", "charset", "readString", "b", "indexOf", "fromIndex", "toIndex", "bytes", "targetBytes", "indexOfElement", "rangeEquals", "bytesOffset", "peek", "Ljava/io/InputStream;", "inputStream", "getBuffer", "()Lokio/Buffer;", "okio"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public interface BufferedSource extends Source, ReadableByteChannel {
    @Deprecated(level = DeprecationLevel.WARNING, message = "moved to val: use getBuffer() instead", replaceWith = @ReplaceWith(expression = "buffer", imports = {}))
    @NotNull
    Buffer buffer();

    boolean exhausted();

    @NotNull
    Buffer getBuffer();

    long indexOf(byte b2);

    long indexOf(byte b2, long j2);

    long indexOf(byte b2, long j2, long j3);

    long indexOf(@NotNull ByteString byteString);

    long indexOf(@NotNull ByteString byteString, long j2);

    long indexOfElement(@NotNull ByteString byteString);

    long indexOfElement(@NotNull ByteString byteString, long j2);

    @NotNull
    InputStream inputStream();

    @NotNull
    BufferedSource peek();

    boolean rangeEquals(long j2, @NotNull ByteString byteString);

    boolean rangeEquals(long j2, @NotNull ByteString byteString, int i2, int i3);

    int read(@NotNull byte[] bArr);

    int read(@NotNull byte[] bArr, int i2, int i3);

    long readAll(@NotNull Sink sink);

    byte readByte();

    @NotNull
    byte[] readByteArray();

    @NotNull
    byte[] readByteArray(long j2);

    @NotNull
    ByteString readByteString();

    @NotNull
    ByteString readByteString(long j2);

    long readDecimalLong();

    void readFully(@NotNull Buffer buffer, long j2);

    void readFully(@NotNull byte[] bArr);

    long readHexadecimalUnsignedLong();

    int readInt();

    int readIntLe();

    long readLong();

    long readLongLe();

    short readShort();

    short readShortLe();

    @NotNull
    String readString(long j2, @NotNull Charset charset);

    @NotNull
    String readString(@NotNull Charset charset);

    @NotNull
    String readUtf8();

    @NotNull
    String readUtf8(long j2);

    int readUtf8CodePoint();

    @Nullable
    String readUtf8Line();

    @NotNull
    String readUtf8LineStrict();

    @NotNull
    String readUtf8LineStrict(long j2);

    boolean request(long j2);

    void require(long j2);

    int select(@NotNull Options options);

    void skip(long j2);
}
