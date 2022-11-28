package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Ascii;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.io.Closeable;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Objects;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Typography;
import okhttp3.internal.connection.RealConnection;
import okio.internal._BufferKt;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002\u0095\u0001B\t¢\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001J \u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u0000H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0000H\u0016J\b\u0010\u0017\u001a\u00020\u0000H\u0016J\b\u0010\u0018\u001a\u00020\tH\u0016J\u0010\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\u001b\u001a\u00020\u0001H\u0016J\b\u0010\u001c\u001a\u00020\u0005H\u0016J$\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0007J \u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u001e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0018\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u001e\u001a\u00020\u0007J\u001a\u0010 \u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00142\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0007J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0016\u0010\f\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007J\u0006\u0010!\u001a\u00020\u0007J\b\u0010#\u001a\u00020\"H\u0016J\u0018\u0010'\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b%\u0010&J\b\u0010)\u001a\u00020(H\u0016J\b\u0010+\u001a\u00020*H\u0016J\b\u0010,\u001a\u00020\u0007H\u0016J\b\u0010-\u001a\u00020(H\u0016J\b\u0010.\u001a\u00020*H\u0016J\b\u0010/\u001a\u00020\u0007H\u0016J\b\u00100\u001a\u00020\u0007H\u0016J\b\u00101\u001a\u00020\u0007H\u0016J\b\u00102\u001a\u00020\u000fH\u0016J\u0010\u00102\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u00105\u001a\u00020*2\u0006\u00104\u001a\u000203H\u0016J\u0018\u00107\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u00109\u001a\u00020\u00072\u0006\u00106\u001a\u000208H\u0016J\b\u0010:\u001a\u00020\rH\u0016J\u0010\u0010:\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010=\u001a\u00020\r2\u0006\u0010<\u001a\u00020;H\u0016J\u0018\u0010=\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010<\u001a\u00020;H\u0016J\n\u0010>\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010?\u001a\u00020\rH\u0016J\u0010\u0010?\u001a\u00020\r2\u0006\u0010@\u001a\u00020\u0007H\u0016J\b\u0010A\u001a\u00020*H\u0016J\b\u0010C\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u00020B2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010D\u001a\u00020*2\u0006\u00106\u001a\u00020BH\u0016J\u0010\u00107\u001a\u00020\u000b2\u0006\u00106\u001a\u00020BH\u0016J \u0010D\u001a\u00020*2\u0006\u00106\u001a\u00020B2\u0006\u0010\u001e\u001a\u00020*2\u0006\u0010\b\u001a\u00020*H\u0016J\u0010\u0010D\u001a\u00020*2\u0006\u00106\u001a\u00020EH\u0016J\u0006\u0010F\u001a\u00020\u000bJ\u0010\u0010G\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010I\u001a\u00020\u00002\u0006\u0010H\u001a\u00020\u000fH\u0016J \u0010I\u001a\u00020\u00002\u0006\u0010H\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020*2\u0006\u0010\b\u001a\u00020*H\u0016J\u0010\u0010K\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\rH\u0016J \u0010K\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\r2\u0006\u0010L\u001a\u00020*2\u0006\u0010M\u001a\u00020*H\u0016J\u0010\u0010O\u001a\u00020\u00002\u0006\u0010N\u001a\u00020*H\u0016J\u0018\u0010P\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\r2\u0006\u0010<\u001a\u00020;H\u0016J(\u0010P\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\r2\u0006\u0010L\u001a\u00020*2\u0006\u0010M\u001a\u00020*2\u0006\u0010<\u001a\u00020;H\u0016J\u0010\u0010I\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020BH\u0016J \u0010I\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020B2\u0006\u0010\u001e\u001a\u00020*2\u0006\u0010\b\u001a\u00020*H\u0016J\u0010\u0010I\u001a\u00020*2\u0006\u0010Q\u001a\u00020EH\u0016J\u0010\u0010S\u001a\u00020\u00072\u0006\u0010Q\u001a\u00020RH\u0016J\u0018\u0010I\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020R2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010U\u001a\u00020\u00002\u0006\u0010T\u001a\u00020*H\u0016J\u0010\u0010W\u001a\u00020\u00002\u0006\u0010V\u001a\u00020*H\u0016J\u0010\u0010X\u001a\u00020\u00002\u0006\u0010V\u001a\u00020*H\u0016J\u0010\u0010Z\u001a\u00020\u00002\u0006\u0010Y\u001a\u00020*H\u0016J\u0010\u0010[\u001a\u00020\u00002\u0006\u0010Y\u001a\u00020*H\u0016J\u0010\u0010]\u001a\u00020\u00002\u0006\u0010\\\u001a\u00020\u0007H\u0016J\u0010\u0010^\u001a\u00020\u00002\u0006\u0010\\\u001a\u00020\u0007H\u0016J\u0010\u0010_\u001a\u00020\u00002\u0006\u0010\\\u001a\u00020\u0007H\u0016J\u0010\u0010`\u001a\u00020\u00002\u0006\u0010\\\u001a\u00020\u0007H\u0016J\u0017\u0010e\u001a\u00020b2\u0006\u0010a\u001a\u00020*H\u0000¢\u0006\u0004\bc\u0010dJ\u0018\u0010I\u001a\u00020\u000b2\u0006\u0010Q\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0018\u0010D\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010f\u001a\u00020\u00072\u0006\u0010T\u001a\u00020\"H\u0016J\u0018\u0010f\u001a\u00020\u00072\u0006\u0010T\u001a\u00020\"2\u0006\u0010g\u001a\u00020\u0007H\u0016J \u0010f\u001a\u00020\u00072\u0006\u0010T\u001a\u00020\"2\u0006\u0010g\u001a\u00020\u00072\u0006\u0010h\u001a\u00020\u0007H\u0016J\u0010\u0010f\u001a\u00020\u00072\u0006\u0010i\u001a\u00020\u000fH\u0016J\u0018\u0010f\u001a\u00020\u00072\u0006\u0010i\u001a\u00020\u000f2\u0006\u0010g\u001a\u00020\u0007H\u0016J\u0010\u0010k\u001a\u00020\u00072\u0006\u0010j\u001a\u00020\u000fH\u0016J\u0018\u0010k\u001a\u00020\u00072\u0006\u0010j\u001a\u00020\u000f2\u0006\u0010g\u001a\u00020\u0007H\u0016J\u0018\u0010l\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010i\u001a\u00020\u000fH\u0016J(\u0010l\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010i\u001a\u00020\u000f2\u0006\u0010m\u001a\u00020*2\u0006\u0010\b\u001a\u00020*H\u0016J\b\u0010n\u001a\u00020\u000bH\u0016J\b\u0010o\u001a\u00020\tH\u0016J\b\u0010p\u001a\u00020\u000bH\u0016J\b\u0010r\u001a\u00020qH\u0016J\u0006\u0010s\u001a\u00020\u000fJ\u0006\u0010t\u001a\u00020\u000fJ\u0006\u0010u\u001a\u00020\u000fJ\u0006\u0010v\u001a\u00020\u000fJ\u000e\u0010w\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fJ\u000e\u0010x\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fJ\u000e\u0010y\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fJ\u0013\u0010|\u001a\u00020\t2\b\u0010{\u001a\u0004\u0018\u00010zH\u0096\u0002J\b\u0010}\u001a\u00020*H\u0016J\b\u0010~\u001a\u00020\rH\u0016J\u0006\u0010\u007f\u001a\u00020\u0000J\t\u0010\u0080\u0001\u001a\u00020\u0000H\u0016J\u0007\u0010\u0081\u0001\u001a\u00020\u000fJ\u000f\u0010\u0081\u0001\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020*J\u0016\u0010\u0084\u0001\u001a\u00030\u0082\u00012\n\b\u0002\u0010\u0083\u0001\u001a\u00030\u0082\u0001H\u0007J\u0016\u0010\u0085\u0001\u001a\u00030\u0082\u00012\n\b\u0002\u0010\u0083\u0001\u001a\u00030\u0082\u0001H\u0007J\u0019\u0010%\u001a\u00020\"2\u0007\u0010\u0086\u0001\u001a\u00020\u0007H\u0007¢\u0006\u0005\b\u0087\u0001\u0010&J\u0012\u0010\u008a\u0001\u001a\u00020\u0007H\u0007¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001R\u001b\u0010\u008b\u0001\u001a\u0004\u0018\u00010b8\u0000@\u0000X\u0081\u000e¢\u0006\b\n\u0006\b\u008b\u0001\u0010\u008c\u0001R2\u0010\u008a\u0001\u001a\u00020\u00072\u0007\u0010\u008d\u0001\u001a\u00020\u00078G@@X\u0086\u000e¢\u0006\u0018\n\u0006\b\u008a\u0001\u0010\u008e\u0001\u001a\u0006\b\u008a\u0001\u0010\u0089\u0001\"\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0018\u0010\u0013\u001a\u00020\u00008V@\u0016X\u0096\u0004¢\u0006\b\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001¨\u0006\u0096\u0001"}, d2 = {"Lokio/Buffer;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", "", "Ljava/nio/channels/ByteChannel;", "Ljava/io/InputStream;", "input", "", "byteCount", "", "forever", "", "readFrom", "", "algorithm", "Lokio/ByteString;", CMSAttributeTableGenerator.DIGEST, "key", "hmac", "buffer", "Ljava/io/OutputStream;", "outputStream", "emitCompleteSegments", "emit", "exhausted", "require", "request", "peek", "inputStream", "out", TypedValues.Cycle.S_WAVE_OFFSET, "copyTo", "writeTo", "completeSegmentByteCount", "", "readByte", "pos", "getByte", "(J)B", "get", "", "readShort", "", "readInt", "readLong", "readShortLe", "readIntLe", "readLongLe", "readDecimalLong", "readHexadecimalUnsignedLong", "readByteString", "Lokio/Options;", "options", "select", "sink", "readFully", "Lokio/Sink;", "readAll", "readUtf8", "Ljava/nio/charset/Charset;", "charset", "readString", "readUtf8Line", "readUtf8LineStrict", "limit", "readUtf8CodePoint", "", "readByteArray", "read", "Ljava/nio/ByteBuffer;", "clear", AppConstants.GEO_FENCE_TIME_MODE_SKIP, "byteString", "write", TypedValues.Custom.S_STRING, "writeUtf8", "beginIndex", "endIndex", "codePoint", "writeUtf8CodePoint", "writeString", "source", "Lokio/Source;", "writeAll", "b", "writeByte", "s", "writeShort", "writeShortLe", "i", "writeInt", "writeIntLe", "v", "writeLong", "writeLongLe", "writeDecimalLong", "writeHexadecimalUnsignedLong", "minimumCapacity", "Lokio/Segment;", "writableSegment$okio", "(I)Lokio/Segment;", "writableSegment", "indexOf", "fromIndex", "toIndex", "bytes", "targetBytes", "indexOfElement", "rangeEquals", "bytesOffset", "flush", "isOpen", "close", "Lokio/Timeout;", "timeout", "md5", "sha1", "sha256", "sha512", "hmacSha1", "hmacSha256", "hmacSha512", "", "other", "equals", "hashCode", "toString", "copy", "clone", "snapshot", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "readUnsafe", "readAndWriteUnsafe", FirebaseAnalytics.Param.INDEX, "-deprecated_getByte", "-deprecated_size", "()J", "size", "head", "Lokio/Segment;", "<set-?>", "J", "setSize$okio", "(J)V", "getBuffer", "()Lokio/Buffer;", "<init>", "()V", "UnsafeCursor", "okio"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
    @JvmField
    @Nullable
    public Segment head;
    private long size;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b!\u0010\"J\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002J\b\u0010\f\u001a\u00020\u000bH\u0016R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R$\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u001aR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b \u0010\u001f¨\u0006#"}, d2 = {"Lokio/Buffer$UnsafeCursor;", "Ljava/io/Closeable;", "", "next", "", TypedValues.Cycle.S_WAVE_OFFSET, "seek", "newSize", "resizeBuffer", "minByteCount", "expandBuffer", "", "close", "Lokio/Buffer;", "buffer", "Lokio/Buffer;", "", "readWrite", "Z", "Lokio/Segment;", "segment", "Lokio/Segment;", "getSegment$okio", "()Lokio/Segment;", "setSegment$okio", "(Lokio/Segment;)V", "J", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "[B", "start", "I", "end", "<init>", "()V", "okio"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes3.dex */
    public static final class UnsafeCursor implements Closeable {
        @JvmField
        @Nullable
        public Buffer buffer;
        @JvmField
        @Nullable
        public byte[] data;
        @JvmField
        public boolean readWrite;
        @Nullable
        private Segment segment;
        @JvmField
        public long offset = -1;
        @JvmField
        public int start = -1;
        @JvmField
        public int end = -1;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (!(this.buffer != null)) {
                throw new IllegalStateException("not attached to a buffer".toString());
            }
            this.buffer = null;
            setSegment$okio(null);
            this.offset = -1L;
            this.data = null;
            this.start = -1;
            this.end = -1;
        }

        public final long expandBuffer(int i2) {
            if (i2 > 0) {
                if (i2 <= 8192) {
                    Buffer buffer = this.buffer;
                    if (buffer != null) {
                        if (this.readWrite) {
                            long size = buffer.size();
                            Segment writableSegment$okio = buffer.writableSegment$okio(i2);
                            int i3 = 8192 - writableSegment$okio.limit;
                            writableSegment$okio.limit = 8192;
                            long j2 = i3;
                            buffer.setSize$okio(size + j2);
                            setSegment$okio(writableSegment$okio);
                            this.offset = size;
                            this.data = writableSegment$okio.data;
                            this.start = 8192 - i3;
                            this.end = 8192;
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

        @Nullable
        public final Segment getSegment$okio() {
            return this.segment;
        }

        public final int next() {
            long j2 = this.offset;
            Buffer buffer = this.buffer;
            Intrinsics.checkNotNull(buffer);
            if (j2 != buffer.size()) {
                long j3 = this.offset;
                return seek(j3 == -1 ? 0L : j3 + (this.end - this.start));
            }
            throw new IllegalStateException("no more bytes".toString());
        }

        public final long resizeBuffer(long j2) {
            Buffer buffer = this.buffer;
            if (buffer != null) {
                if (this.readWrite) {
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
                        setSegment$okio(null);
                        this.offset = j2;
                        this.data = null;
                        this.start = -1;
                        this.end = -1;
                    } else if (i2 > 0) {
                        long j5 = j2 - size;
                        boolean z = true;
                        while (j5 > 0) {
                            Segment writableSegment$okio = buffer.writableSegment$okio(i3);
                            int min = (int) Math.min(j5, 8192 - writableSegment$okio.limit);
                            writableSegment$okio.limit += min;
                            j5 -= min;
                            if (z) {
                                setSegment$okio(writableSegment$okio);
                                this.offset = size;
                                this.data = writableSegment$okio.data;
                                int i5 = writableSegment$okio.limit;
                                this.start = i5 - min;
                                this.end = i5;
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

        public final int seek(long j2) {
            Segment segment;
            Buffer buffer = this.buffer;
            if (buffer != null) {
                int i2 = (j2 > (-1L) ? 1 : (j2 == (-1L) ? 0 : -1));
                if (i2 < 0 || j2 > buffer.size()) {
                    throw new ArrayIndexOutOfBoundsException("offset=" + j2 + " > size=" + buffer.size());
                } else if (i2 == 0 || j2 == buffer.size()) {
                    setSegment$okio(null);
                    this.offset = j2;
                    this.data = null;
                    this.start = -1;
                    this.end = -1;
                    return -1;
                } else {
                    long j3 = 0;
                    long size = buffer.size();
                    Segment segment2 = buffer.head;
                    if (getSegment$okio() != null) {
                        long j4 = this.offset;
                        int i3 = this.start;
                        Segment segment$okio = getSegment$okio();
                        Intrinsics.checkNotNull(segment$okio);
                        long j5 = j4 - (i3 - segment$okio.pos);
                        if (j5 > j2) {
                            segment2 = getSegment$okio();
                            size = j5;
                            segment = segment2;
                        } else {
                            segment = getSegment$okio();
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
                    if (this.readWrite) {
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
                    setSegment$okio(segment);
                    this.offset = j2;
                    Intrinsics.checkNotNull(segment);
                    this.data = segment.data;
                    int i6 = segment.pos + ((int) (j2 - j3));
                    this.start = i6;
                    int i7 = segment.limit;
                    this.end = i7;
                    return i7 - i6;
                }
            }
            throw new IllegalStateException("not attached to a buffer".toString());
        }

        public final void setSegment$okio(@Nullable Segment segment) {
            this.segment = segment;
        }
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, OutputStream outputStream, long j2, long j3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j2 = 0;
        }
        long j4 = j2;
        if ((i2 & 4) != 0) {
            j3 = buffer.size - j4;
        }
        return buffer.copyTo(outputStream, j4, j3);
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, Buffer buffer2, long j2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j2 = 0;
        }
        return buffer.copyTo(buffer2, j2);
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, Buffer buffer2, long j2, long j3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j2 = 0;
        }
        return buffer.copyTo(buffer2, j2, j3);
    }

    private final ByteString digest(String str) {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        Segment segment = this.head;
        if (segment != null) {
            byte[] bArr = segment.data;
            int i2 = segment.pos;
            messageDigest.update(bArr, i2, segment.limit - i2);
            Segment segment2 = segment.next;
            while (true) {
                Intrinsics.checkNotNull(segment2);
                if (segment2 == segment) {
                    break;
                }
                byte[] bArr2 = segment2.data;
                int i3 = segment2.pos;
                messageDigest.update(bArr2, i3, segment2.limit - i3);
                segment2 = segment2.next;
            }
        }
        byte[] digest = messageDigest.digest();
        Intrinsics.checkNotNullExpressionValue(digest, "messageDigest.digest()");
        return new ByteString(digest);
    }

    private final ByteString hmac(String str, ByteString byteString) {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.internalArray$okio(), str));
            Segment segment = this.head;
            if (segment != null) {
                byte[] bArr = segment.data;
                int i2 = segment.pos;
                mac.update(bArr, i2, segment.limit - i2);
                Segment segment2 = segment.next;
                while (true) {
                    Intrinsics.checkNotNull(segment2);
                    if (segment2 == segment) {
                        break;
                    }
                    byte[] bArr2 = segment2.data;
                    int i3 = segment2.pos;
                    mac.update(bArr2, i3, segment2.limit - i3);
                    segment2 = segment2.next;
                }
            }
            byte[] doFinal = mac.doFinal();
            Intrinsics.checkNotNullExpressionValue(doFinal, "mac.doFinal()");
            return new ByteString(doFinal);
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static /* synthetic */ UnsafeCursor readAndWriteUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            unsafeCursor = _UtilKt.getDEFAULT__new_UnsafeCursor();
        }
        return buffer.readAndWriteUnsafe(unsafeCursor);
    }

    private final void readFrom(InputStream inputStream, long j2, boolean z) {
        while (true) {
            if (j2 <= 0 && !z) {
                return;
            }
            Segment writableSegment$okio = writableSegment$okio(1);
            int read = inputStream.read(writableSegment$okio.data, writableSegment$okio.limit, (int) Math.min(j2, 8192 - writableSegment$okio.limit));
            if (read == -1) {
                if (writableSegment$okio.pos == writableSegment$okio.limit) {
                    this.head = writableSegment$okio.pop();
                    SegmentPool.recycle(writableSegment$okio);
                }
                if (!z) {
                    throw new EOFException();
                }
                return;
            }
            writableSegment$okio.limit += read;
            long j3 = read;
            this.size += j3;
            j2 -= j3;
        }
    }

    public static /* synthetic */ UnsafeCursor readUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            unsafeCursor = _UtilKt.getDEFAULT__new_UnsafeCursor();
        }
        return buffer.readUnsafe(unsafeCursor);
    }

    public static /* synthetic */ Buffer writeTo$default(Buffer buffer, OutputStream outputStream, long j2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j2 = buffer.size;
        }
        return buffer.writeTo(outputStream, j2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to operator function", replaceWith = @ReplaceWith(expression = "this[index]", imports = {}))
    @JvmName(name = "-deprecated_getByte")
    /* renamed from: -deprecated_getByte  reason: not valid java name */
    public final byte m1834deprecated_getByte(long j2) {
        return getByte(j2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    @JvmName(name = "-deprecated_size")
    /* renamed from: -deprecated_size  reason: not valid java name */
    public final long m1835deprecated_size() {
        return this.size;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    @NotNull
    public Buffer buffer() {
        return this;
    }

    public final void clear() {
        skip(size());
    }

    @NotNull
    public Buffer clone() {
        return copy();
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public final long completeSegmentByteCount() {
        long size = size();
        if (size == 0) {
            return 0L;
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment.prev;
        Intrinsics.checkNotNull(segment2);
        int i2 = segment2.limit;
        if (i2 < 8192 && segment2.owner) {
            size -= i2 - segment2.pos;
        }
        return size;
    }

    @NotNull
    public final Buffer copy() {
        Buffer buffer = new Buffer();
        if (size() != 0) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            Segment sharedCopy = segment.sharedCopy();
            buffer.head = sharedCopy;
            sharedCopy.prev = sharedCopy;
            sharedCopy.next = sharedCopy;
            for (Segment segment2 = segment.next; segment2 != segment; segment2 = segment2.next) {
                Segment segment3 = sharedCopy.prev;
                Intrinsics.checkNotNull(segment3);
                Intrinsics.checkNotNull(segment2);
                segment3.push(segment2.sharedCopy());
            }
            buffer.setSize$okio(size());
        }
        return buffer;
    }

    @JvmOverloads
    @NotNull
    public final Buffer copyTo(@NotNull OutputStream out) {
        Intrinsics.checkNotNullParameter(out, "out");
        return copyTo$default(this, out, 0L, 0L, 6, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public final Buffer copyTo(@NotNull OutputStream out, long j2) {
        Intrinsics.checkNotNullParameter(out, "out");
        return copyTo$default(this, out, j2, 0L, 4, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public final Buffer copyTo(@NotNull OutputStream out, long j2, long j3) {
        int i2;
        Intrinsics.checkNotNullParameter(out, "out");
        _UtilKt.checkOffsetAndCount(this.size, j2, j3);
        if (j3 == 0) {
            return this;
        }
        Segment segment = this.head;
        while (true) {
            Intrinsics.checkNotNull(segment);
            int i3 = segment.limit;
            int i4 = segment.pos;
            if (j2 < i3 - i4) {
                break;
            }
            j2 -= i3 - i4;
            segment = segment.next;
        }
        while (j3 > 0) {
            Intrinsics.checkNotNull(segment);
            int min = (int) Math.min(segment.limit - i2, j3);
            out.write(segment.data, (int) (segment.pos + j2), min);
            j3 -= min;
            segment = segment.next;
            j2 = 0;
        }
        return this;
    }

    @NotNull
    public final Buffer copyTo(@NotNull Buffer out, long j2) {
        Intrinsics.checkNotNullParameter(out, "out");
        return copyTo(out, j2, this.size - j2);
    }

    @NotNull
    public final Buffer copyTo(@NotNull Buffer out, long j2, long j3) {
        Intrinsics.checkNotNullParameter(out, "out");
        _UtilKt.checkOffsetAndCount(size(), j2, j3);
        if (j3 != 0) {
            out.setSize$okio(out.size() + j3);
            Segment segment = this.head;
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
        }
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer emit() {
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer emitCompleteSegments() {
        return this;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Buffer)) {
                return false;
            }
            Buffer buffer = (Buffer) obj;
            if (size() != buffer.size()) {
                return false;
            }
            if (size() != 0) {
                Segment segment = this.head;
                Intrinsics.checkNotNull(segment);
                Segment segment2 = buffer.head;
                Intrinsics.checkNotNull(segment2);
                int i2 = segment.pos;
                int i3 = segment2.pos;
                long j2 = 0;
                while (j2 < size()) {
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
            }
        }
        return true;
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        return this.size == 0;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    @NotNull
    public Buffer getBuffer() {
        return this;
    }

    @JvmName(name = "getByte")
    public final byte getByte(long j2) {
        _UtilKt.checkOffsetAndCount(size(), j2, 1L);
        Segment segment = this.head;
        if (segment == null) {
            Intrinsics.checkNotNull(null);
            throw null;
        } else if (size() - j2 < j2) {
            long size = size();
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

    public int hashCode() {
        Segment segment = this.head;
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
        } while (segment != this.head);
        return i2;
    }

    @NotNull
    public final ByteString hmacSha1(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return hmac("HmacSHA1", key);
    }

    @NotNull
    public final ByteString hmacSha256(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return hmac("HmacSHA256", key);
    }

    @NotNull
    public final ByteString hmacSha512(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return hmac("HmacSHA512", key);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b2) {
        return indexOf(b2, 0L, LongCompanionObject.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b2, long j2) {
        return indexOf(b2, j2, LongCompanionObject.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b2, long j2, long j3) {
        Segment segment;
        int i2;
        long j4 = 0;
        boolean z = false;
        if (0 <= j2 && j2 <= j3) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(("size=" + size() + " fromIndex=" + j2 + " toIndex=" + j3).toString());
        }
        if (j3 > size()) {
            j3 = size();
        }
        long j5 = j3;
        if (j2 == j5 || (segment = this.head) == null) {
            return -1L;
        }
        if (size() - j2 < j2) {
            j4 = size();
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

    @Override // okio.BufferedSource
    public long indexOf(@NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return indexOf(bytes, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOf(@NotNull ByteString bytes, long j2) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (bytes.size() > 0) {
            long j3 = 0;
            if (j2 >= 0) {
                Segment segment = this.head;
                if (segment != null) {
                    if (size() - j2 < j2) {
                        long size = size();
                        while (size > j2) {
                            segment = segment.prev;
                            Intrinsics.checkNotNull(segment);
                            size -= segment.limit - segment.pos;
                        }
                        byte[] internalArray$okio = bytes.internalArray$okio();
                        byte b2 = internalArray$okio[0];
                        int size2 = bytes.size();
                        long size3 = (size() - size2) + 1;
                        long j4 = size;
                        long j5 = j2;
                        while (j4 < size3) {
                            byte[] bArr = segment.data;
                            int min = (int) Math.min(segment.limit, (segment.pos + size3) - j4);
                            int i2 = (int) ((segment.pos + j5) - j4);
                            if (i2 < min) {
                                while (true) {
                                    int i3 = i2 + 1;
                                    if (bArr[i2] == b2 && _BufferKt.rangeEquals(segment, i3, internalArray$okio, 1, size2)) {
                                        return (i2 - segment.pos) + j4;
                                    }
                                    if (i3 >= min) {
                                        break;
                                    }
                                    i2 = i3;
                                }
                            }
                            j4 += segment.limit - segment.pos;
                            segment = segment.next;
                            Intrinsics.checkNotNull(segment);
                            j5 = j4;
                        }
                    } else {
                        while (true) {
                            long j6 = (segment.limit - segment.pos) + j3;
                            if (j6 > j2) {
                                break;
                            }
                            segment = segment.next;
                            Intrinsics.checkNotNull(segment);
                            j3 = j6;
                        }
                        byte[] internalArray$okio2 = bytes.internalArray$okio();
                        byte b3 = internalArray$okio2[0];
                        int size4 = bytes.size();
                        long size5 = (size() - size4) + 1;
                        long j7 = j3;
                        long j8 = j2;
                        while (j7 < size5) {
                            byte[] bArr2 = segment.data;
                            long j9 = size5;
                            int min2 = (int) Math.min(segment.limit, (segment.pos + size5) - j7);
                            int i4 = (int) ((segment.pos + j8) - j7);
                            if (i4 < min2) {
                                while (true) {
                                    int i5 = i4 + 1;
                                    if (bArr2[i4] == b3 && _BufferKt.rangeEquals(segment, i5, internalArray$okio2, 1, size4)) {
                                        return (i4 - segment.pos) + j7;
                                    }
                                    if (i5 >= min2) {
                                        break;
                                    }
                                    i4 = i5;
                                }
                            }
                            j7 += segment.limit - segment.pos;
                            segment = segment.next;
                            Intrinsics.checkNotNull(segment);
                            j8 = j7;
                            size5 = j9;
                        }
                    }
                }
                return -1L;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("fromIndex < 0: ", Long.valueOf(j2)).toString());
        }
        throw new IllegalArgumentException("bytes is empty".toString());
    }

    @Override // okio.BufferedSource
    public long indexOfElement(@NotNull ByteString targetBytes) {
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        return indexOfElement(targetBytes, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(@NotNull ByteString targetBytes, long j2) {
        int i2;
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        long j3 = 0;
        if (j2 >= 0) {
            Segment segment = this.head;
            if (segment == null) {
                return -1L;
            }
            if (size() - j2 < j2) {
                j3 = size();
                while (j3 > j2) {
                    segment = segment.prev;
                    Intrinsics.checkNotNull(segment);
                    j3 -= segment.limit - segment.pos;
                }
                if (targetBytes.size() == 2) {
                    byte b2 = targetBytes.getByte(0);
                    byte b3 = targetBytes.getByte(1);
                    while (j3 < size()) {
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
                while (j3 < size()) {
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
                while (j3 < size()) {
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
            while (j3 < size()) {
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

    @Override // okio.BufferedSource
    @NotNull
    public InputStream inputStream() {
        return new InputStream() { // from class: okio.Buffer$inputStream$1
            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(Buffer.this.size(), Integer.MAX_VALUE);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.InputStream
            public int read() {
                if (Buffer.this.size() > 0) {
                    return Buffer.this.readByte() & 255;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public int read(@NotNull byte[] sink, int i2, int i3) {
                Intrinsics.checkNotNullParameter(sink, "sink");
                return Buffer.this.read(sink, i2, i3);
            }

            @NotNull
            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @NotNull
    public final ByteString md5() {
        return digest(MessageDigestAlgorithms.MD5);
    }

    @Override // okio.BufferedSink
    @NotNull
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.Buffer$outputStream$1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            @NotNull
            public String toString() {
                return Buffer.this + ".outputStream()";
            }

            @Override // java.io.OutputStream
            public void write(int i2) {
                Buffer.this.writeByte(i2);
            }

            @Override // java.io.OutputStream
            public void write(@NotNull byte[] data, int i2, int i3) {
                Intrinsics.checkNotNullParameter(data, "data");
                Buffer.this.write(data, i2, i3);
            }
        };
    }

    @Override // okio.BufferedSource
    @NotNull
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j2, @NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return rangeEquals(j2, bytes, 0, bytes.size());
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j2, @NotNull ByteString bytes, int i2, int i3) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (j2 < 0 || i2 < 0 || i3 < 0 || size() - j2 < i3 || bytes.size() - i2 < i3) {
            return false;
        }
        if (i3 > 0) {
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                if (getByte(i4 + j2) != bytes.getByte(i4 + i2)) {
                    return false;
                }
                if (i5 >= i3) {
                    break;
                }
                i4 = i5;
            }
        }
        return true;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(@NotNull ByteBuffer sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(sink.remaining(), segment.limit - segment.pos);
        sink.put(segment.data, segment.pos, min);
        int i2 = segment.pos + min;
        segment.pos = i2;
        this.size -= min;
        if (i2 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    @Override // okio.BufferedSource
    public int read(@NotNull byte[] sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return read(sink, 0, sink.length);
    }

    @Override // okio.BufferedSource
    public int read(@NotNull byte[] sink, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        _UtilKt.checkOffsetAndCount(sink.length, i2, i3);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i3, segment.limit - segment.pos);
        byte[] bArr = segment.data;
        int i4 = segment.pos;
        ArraysKt.copyInto(bArr, sink, i2, i4, i4 + min);
        segment.pos += min;
        setSize$okio(size() - min);
        if (segment.pos == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    @Override // okio.Source
    public long read(@NotNull Buffer sink, long j2) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (j2 >= 0) {
            if (size() == 0) {
                return -1L;
            }
            if (j2 > size()) {
                j2 = size();
            }
            sink.write(this, j2);
            return j2;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(j2)).toString());
    }

    @Override // okio.BufferedSource
    public long readAll(@NotNull Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long size = size();
        if (size > 0) {
            sink.write(this, size);
        }
        return size;
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readAndWriteUnsafe() {
        return readAndWriteUnsafe$default(this, null, 1, null);
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readAndWriteUnsafe(@NotNull UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        return _BufferKt.commonReadAndWriteUnsafe(this, unsafeCursor);
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        if (size() != 0) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            int i2 = segment.pos;
            int i3 = segment.limit;
            int i4 = i2 + 1;
            byte b2 = segment.data[i2];
            setSize$okio(size() - 1);
            if (i4 == i3) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return b2;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    @NotNull
    public byte[] readByteArray() {
        return readByteArray(size());
    }

    @Override // okio.BufferedSource
    @NotNull
    public byte[] readByteArray(long j2) {
        if (j2 >= 0 && j2 <= 2147483647L) {
            if (size() >= j2) {
                byte[] bArr = new byte[(int) j2];
                readFully(bArr);
                return bArr;
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount: ", Long.valueOf(j2)).toString());
    }

    @Override // okio.BufferedSource
    @NotNull
    public ByteString readByteString() {
        return readByteString(size());
    }

    @Override // okio.BufferedSource
    @NotNull
    public ByteString readByteString(long j2) {
        if (j2 >= 0 && j2 <= 2147483647L) {
            if (size() >= j2) {
                if (j2 >= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
                    ByteString snapshot = snapshot((int) j2);
                    skip(j2);
                    return snapshot;
                }
                return new ByteString(readByteArray(j2));
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount: ", Long.valueOf(j2)).toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0090, code lost:
        setSize$okio(size() - r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0099, code lost:
        if (r6 == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x009b, code lost:
        r14 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x009d, code lost:
        r14 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009e, code lost:
        if (r5 >= r14) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a8, code lost:
        if (size() == 0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00aa, code lost:
        if (r6 == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ac, code lost:
        r1 = "Expected a digit";
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00af, code lost:
        r1 = "Expected a digit or '-'";
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00d4, code lost:
        throw new java.lang.NumberFormatException(r1 + " but was 0x" + okio._UtilKt.toHexString(getByte(0)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00da, code lost:
        throw new java.io.EOFException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00db, code lost:
        if (r6 == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00df, code lost:
        return -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
        return r8;
     */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long readDecimalLong() {
        byte b2;
        if (size() != 0) {
            long j2 = -7;
            int i2 = 0;
            long j3 = 0;
            boolean z = false;
            boolean z2 = false;
            loop0: while (true) {
                Segment segment = this.head;
                Intrinsics.checkNotNull(segment);
                byte[] bArr = segment.data;
                int i3 = segment.pos;
                int i4 = segment.limit;
                while (i3 < i4) {
                    b2 = bArr[i3];
                    byte b3 = (byte) 48;
                    if (b2 >= b3 && b2 <= ((byte) 57)) {
                        int i5 = b3 - b2;
                        int i6 = (j3 > _BufferKt.OVERFLOW_ZONE ? 1 : (j3 == _BufferKt.OVERFLOW_ZONE ? 0 : -1));
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
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                } else {
                    segment.pos = i3;
                }
                if (z2 || this.head == null) {
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

    @NotNull
    public final Buffer readFrom(@NotNull InputStream input) {
        Intrinsics.checkNotNullParameter(input, "input");
        readFrom(input, LongCompanionObject.MAX_VALUE, true);
        return this;
    }

    @NotNull
    public final Buffer readFrom(@NotNull InputStream input, long j2) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (j2 >= 0) {
            readFrom(input, j2, false);
            return this;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(j2)).toString());
    }

    @Override // okio.BufferedSource
    public void readFully(@NotNull Buffer sink, long j2) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (size() >= j2) {
            sink.write(this, j2);
        } else {
            sink.write(this, size());
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public void readFully(@NotNull byte[] sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        int i2 = 0;
        while (i2 < sink.length) {
            int read = read(sink, i2, sink.length - i2);
            if (read == -1) {
                throw new EOFException();
            }
            i2 += read;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0098 A[EDGE_INSN: B:42:0x0098->B:37:0x0098 ?: BREAK  , SYNTHETIC] */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long readHexadecimalUnsignedLong() {
        int i2;
        if (size() != 0) {
            int i3 = 0;
            boolean z = false;
            long j2 = 0;
            do {
                Segment segment = this.head;
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
                                this.head = segment.pop();
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
            } while (this.head != null);
            setSize$okio(size() - i3);
            return j2;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public int readInt() {
        if (size() >= 4) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            int i2 = segment.pos;
            int i3 = segment.limit;
            if (i3 - i2 < 4) {
                return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
            }
            byte[] bArr = segment.data;
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i2] & 255) << 24) | ((bArr[i4] & 255) << 16);
            int i7 = i5 + 1;
            int i8 = i6 | ((bArr[i5] & 255) << 8);
            int i9 = i7 + 1;
            int i10 = i8 | (bArr[i7] & 255);
            setSize$okio(size() - 4);
            if (i9 == i3) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i9;
            }
            return i10;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public int readIntLe() {
        return _UtilKt.reverseBytes(readInt());
    }

    @Override // okio.BufferedSource
    public long readLong() {
        if (size() >= 8) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            int i2 = segment.pos;
            int i3 = segment.limit;
            if (i3 - i2 < 8) {
                return ((readInt() & BodyPartID.bodyIdMax) << 32) | (BodyPartID.bodyIdMax & readInt());
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
            setSize$okio(size() - 8);
            if (i11 == i3) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i11;
            }
            return j3;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public long readLongLe() {
        return _UtilKt.reverseBytes(readLong());
    }

    @Override // okio.BufferedSource
    public short readShort() {
        if (size() >= 2) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            int i2 = segment.pos;
            int i3 = segment.limit;
            if (i3 - i2 < 2) {
                return (short) (((readByte() & 255) << 8) | (readByte() & 255));
            }
            byte[] bArr = segment.data;
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i2] & 255) << 8) | (bArr[i4] & 255);
            setSize$okio(size() - 2);
            if (i5 == i3) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i5;
            }
            return (short) i6;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public short readShortLe() {
        return _UtilKt.reverseBytes(readShort());
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readString(long j2, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i2 >= 0 && j2 <= 2147483647L) {
            if (this.size >= j2) {
                if (i2 == 0) {
                    return "";
                }
                Segment segment = this.head;
                Intrinsics.checkNotNull(segment);
                int i3 = segment.pos;
                if (i3 + j2 > segment.limit) {
                    return new String(readByteArray(j2), charset);
                }
                int i4 = (int) j2;
                String str = new String(segment.data, i3, i4, charset);
                int i5 = segment.pos + i4;
                segment.pos = i5;
                this.size -= j2;
                if (i5 == segment.limit) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
                return str;
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount: ", Long.valueOf(j2)).toString());
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readString(@NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        return readString(this.size, charset);
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readUnsafe() {
        return readUnsafe$default(this, null, 1, null);
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readUnsafe(@NotNull UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        return _BufferKt.commonReadUnsafe(this, unsafeCursor);
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readUtf8() {
        return readString(this.size, Charsets.UTF_8);
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readUtf8(long j2) {
        return readString(j2, Charsets.UTF_8);
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() {
        int i2;
        int i3;
        int i4;
        if (size() != 0) {
            byte b2 = getByte(0L);
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
                skip(1L);
                return Utf8.REPLACEMENT_CODE_POINT;
            } else {
                i2 = b2 & 7;
                i3 = 4;
                i4 = 65536;
            }
            long j2 = i3;
            if (size() < j2) {
                throw new EOFException("size < " + i3 + ": " + size() + " (to read code point prefixed 0x" + _UtilKt.toHexString(b2) + ')');
            }
            if (1 < i3) {
                int i5 = 1;
                while (true) {
                    int i6 = i5 + 1;
                    long j3 = i5;
                    byte b3 = getByte(j3);
                    if ((b3 & 192) != 128) {
                        skip(j3);
                        return Utf8.REPLACEMENT_CODE_POINT;
                    }
                    i2 = (i2 << 6) | (b3 & Utf8.REPLACEMENT_BYTE);
                    if (i6 >= i3) {
                        break;
                    }
                    i5 = i6;
                }
            }
            skip(j2);
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

    @Override // okio.BufferedSource
    @Nullable
    public String readUtf8Line() {
        long indexOf = indexOf((byte) 10);
        if (indexOf != -1) {
            return _BufferKt.readUtf8Line(this, indexOf);
        }
        if (size() != 0) {
            return readUtf8(size());
        }
        return null;
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readUtf8LineStrict() {
        return readUtf8LineStrict(LongCompanionObject.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readUtf8LineStrict(long j2) {
        if (j2 >= 0) {
            long j3 = LongCompanionObject.MAX_VALUE;
            if (j2 != LongCompanionObject.MAX_VALUE) {
                j3 = j2 + 1;
            }
            byte b2 = (byte) 10;
            long indexOf = indexOf(b2, 0L, j3);
            if (indexOf != -1) {
                return _BufferKt.readUtf8Line(this, indexOf);
            }
            if (j3 < size() && getByte(j3 - 1) == ((byte) 13) && getByte(j3) == b2) {
                return _BufferKt.readUtf8Line(this, j3);
            }
            Buffer buffer = new Buffer();
            copyTo(buffer, 0L, Math.min(32, size()));
            throw new EOFException("\\n not found: limit=" + Math.min(size(), j2) + " content=" + buffer.readByteString().hex() + Typography.ellipsis);
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("limit < 0: ", Long.valueOf(j2)).toString());
    }

    @Override // okio.BufferedSource
    public boolean request(long j2) {
        return this.size >= j2;
    }

    @Override // okio.BufferedSource
    public void require(long j2) {
        if (this.size < j2) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public int select(@NotNull Options options) {
        Intrinsics.checkNotNullParameter(options, "options");
        int selectPrefix$default = _BufferKt.selectPrefix$default(this, options, false, 2, null);
        if (selectPrefix$default == -1) {
            return -1;
        }
        skip(options.getByteStrings$okio()[selectPrefix$default].size());
        return selectPrefix$default;
    }

    public final void setSize$okio(long j2) {
        this.size = j2;
    }

    @NotNull
    public final ByteString sha1() {
        return digest("SHA-1");
    }

    @NotNull
    public final ByteString sha256() {
        return digest("SHA-256");
    }

    @NotNull
    public final ByteString sha512() {
        return digest("SHA-512");
    }

    @JvmName(name = "size")
    public final long size() {
        return this.size;
    }

    @Override // okio.BufferedSource
    public void skip(long j2) {
        while (j2 > 0) {
            Segment segment = this.head;
            if (segment == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j2, segment.limit - segment.pos);
            long j3 = min;
            setSize$okio(size() - j3);
            j2 -= j3;
            int i2 = segment.pos + min;
            segment.pos = i2;
            if (i2 == segment.limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }

    @NotNull
    public final ByteString snapshot() {
        if (size() <= 2147483647L) {
            return snapshot((int) size());
        }
        throw new IllegalStateException(Intrinsics.stringPlus("size > Int.MAX_VALUE: ", Long.valueOf(size())).toString());
    }

    @NotNull
    public final ByteString snapshot(int i2) {
        if (i2 == 0) {
            return ByteString.EMPTY;
        }
        _UtilKt.checkOffsetAndCount(size(), 0L, i2);
        Segment segment = this.head;
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
        Segment segment2 = this.head;
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

    @Override // okio.Source
    @NotNull
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @NotNull
    public String toString() {
        return snapshot().toString();
    }

    @NotNull
    public final Segment writableSegment$okio(int i2) {
        boolean z = true;
        if ((i2 < 1 || i2 > 8192) ? false : false) {
            Segment segment = this.head;
            if (segment != null) {
                Intrinsics.checkNotNull(segment);
                Segment segment2 = segment.prev;
                Intrinsics.checkNotNull(segment2);
                return (segment2.limit + i2 > 8192 || !segment2.owner) ? segment2.push(SegmentPool.take()) : segment2;
            }
            Segment take = SegmentPool.take();
            this.head = take;
            take.prev = take;
            take.next = take;
            return take;
        }
        throw new IllegalArgumentException("unexpected capacity".toString());
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(@NotNull ByteBuffer source) {
        Intrinsics.checkNotNullParameter(source, "source");
        int remaining = source.remaining();
        int i2 = remaining;
        while (i2 > 0) {
            Segment writableSegment$okio = writableSegment$okio(1);
            int min = Math.min(i2, 8192 - writableSegment$okio.limit);
            source.get(writableSegment$okio.data, writableSegment$okio.limit, min);
            i2 -= min;
            writableSegment$okio.limit += min;
        }
        this.size += remaining;
        return remaining;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer write(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(this, 0, byteString.size());
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer write(@NotNull ByteString byteString, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(this, i2, i3);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer write(@NotNull Source source, long j2) {
        Intrinsics.checkNotNullParameter(source, "source");
        while (j2 > 0) {
            long read = source.read(this, j2);
            if (read == -1) {
                throw new EOFException();
            }
            j2 -= read;
        }
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer write(@NotNull byte[] source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return write(source, 0, source.length);
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer write(@NotNull byte[] source, int i2, int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        long j2 = i3;
        _UtilKt.checkOffsetAndCount(source.length, i2, j2);
        int i4 = i3 + i2;
        while (i2 < i4) {
            Segment writableSegment$okio = writableSegment$okio(1);
            int min = Math.min(i4 - i2, 8192 - writableSegment$okio.limit);
            int i5 = i2 + min;
            ArraysKt.copyInto(source, writableSegment$okio.data, writableSegment$okio.limit, i2, i5);
            writableSegment$okio.limit += min;
            i2 = i5;
        }
        setSize$okio(size() + j2);
        return this;
    }

    @Override // okio.Sink
    public void write(@NotNull Buffer source, long j2) {
        Segment segment;
        Segment segment2;
        Intrinsics.checkNotNullParameter(source, "source");
        if (!(source != this)) {
            throw new IllegalArgumentException("source == this".toString());
        }
        _UtilKt.checkOffsetAndCount(source.size(), 0L, j2);
        while (j2 > 0) {
            Segment segment3 = source.head;
            Intrinsics.checkNotNull(segment3);
            int i2 = segment3.limit;
            Intrinsics.checkNotNull(source.head);
            if (j2 < i2 - segment.pos) {
                Segment segment4 = this.head;
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
                        setSize$okio(size() + j2);
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
            Segment segment8 = this.head;
            if (segment8 == null) {
                this.head = segment7;
                segment7.prev = segment7;
                segment7.next = segment7;
            } else {
                Intrinsics.checkNotNull(segment8);
                Segment segment9 = segment8.prev;
                Intrinsics.checkNotNull(segment9);
                segment9.push(segment7).compact();
            }
            source.setSize$okio(source.size() - j3);
            setSize$okio(size() + j3);
            j2 -= j3;
        }
    }

    @Override // okio.BufferedSink
    public long writeAll(@NotNull Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        long j2 = 0;
        while (true) {
            long read = source.read(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j2;
            }
            j2 += read;
        }
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeByte(int i2) {
        Segment writableSegment$okio = writableSegment$okio(1);
        byte[] bArr = writableSegment$okio.data;
        int i3 = writableSegment$okio.limit;
        writableSegment$okio.limit = i3 + 1;
        bArr[i3] = (byte) i2;
        setSize$okio(size() + 1);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeDecimalLong(long j2) {
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i2 == 0) {
            return writeByte(48);
        }
        boolean z = false;
        int i3 = 1;
        if (i2 < 0) {
            j2 = -j2;
            if (j2 < 0) {
                return writeUtf8("-9223372036854775808");
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
        Segment writableSegment$okio = writableSegment$okio(i3);
        byte[] bArr = writableSegment$okio.data;
        int i4 = writableSegment$okio.limit + i3;
        while (j2 != 0) {
            long j3 = 10;
            i4--;
            bArr[i4] = _BufferKt.getHEX_DIGIT_BYTES()[(int) (j2 % j3)];
            j2 /= j3;
        }
        if (z) {
            bArr[i4 - 1] = (byte) 45;
        }
        writableSegment$okio.limit += i3;
        setSize$okio(size() + i3);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeHexadecimalUnsignedLong(long j2) {
        if (j2 == 0) {
            return writeByte(48);
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
        Segment writableSegment$okio = writableSegment$okio(i2);
        byte[] bArr = writableSegment$okio.data;
        int i3 = writableSegment$okio.limit;
        for (int i4 = (i3 + i2) - 1; i4 >= i3; i4--) {
            bArr[i4] = _BufferKt.getHEX_DIGIT_BYTES()[(int) (15 & j2)];
            j2 >>>= 4;
        }
        writableSegment$okio.limit += i2;
        setSize$okio(size() + i2);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeInt(int i2) {
        Segment writableSegment$okio = writableSegment$okio(4);
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
        setSize$okio(size() + 4);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeIntLe(int i2) {
        return writeInt(_UtilKt.reverseBytes(i2));
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeLong(long j2) {
        Segment writableSegment$okio = writableSegment$okio(8);
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
        setSize$okio(size() + 8);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeLongLe(long j2) {
        return writeLong(_UtilKt.reverseBytes(j2));
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeShort(int i2) {
        Segment writableSegment$okio = writableSegment$okio(2);
        byte[] bArr = writableSegment$okio.data;
        int i3 = writableSegment$okio.limit;
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i2 >>> 8) & 255);
        bArr[i4] = (byte) (i2 & 255);
        writableSegment$okio.limit = i4 + 1;
        setSize$okio(size() + 2);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeShortLe(int i2) {
        return writeShort((int) _UtilKt.reverseBytes((short) i2));
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeString(@NotNull String string, int i2, int i3, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (i2 >= 0) {
            if (!(i3 >= i2)) {
                throw new IllegalArgumentException(("endIndex < beginIndex: " + i3 + " < " + i2).toString());
            }
            if (!(i3 <= string.length())) {
                throw new IllegalArgumentException(("endIndex > string.length: " + i3 + " > " + string.length()).toString());
            } else if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
                return writeUtf8(string, i2, i3);
            } else {
                String substring = string.substring(i2, i3);
                Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                Objects.requireNonNull(substring, "null cannot be cast to non-null type java.lang.String");
                byte[] bytes = substring.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                return write(bytes, 0, bytes.length);
            }
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("beginIndex < 0: ", Integer.valueOf(i2)).toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeString(@NotNull String string, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return writeString(string, 0, string.length(), charset);
    }

    @JvmOverloads
    @NotNull
    public final Buffer writeTo(@NotNull OutputStream out) {
        Intrinsics.checkNotNullParameter(out, "out");
        return writeTo$default(this, out, 0L, 2, null);
    }

    @JvmOverloads
    @NotNull
    public final Buffer writeTo(@NotNull OutputStream out, long j2) {
        Intrinsics.checkNotNullParameter(out, "out");
        _UtilKt.checkOffsetAndCount(this.size, 0L, j2);
        Segment segment = this.head;
        while (j2 > 0) {
            Intrinsics.checkNotNull(segment);
            int min = (int) Math.min(j2, segment.limit - segment.pos);
            out.write(segment.data, segment.pos, min);
            int i2 = segment.pos + min;
            segment.pos = i2;
            long j3 = min;
            this.size -= j3;
            j2 -= j3;
            if (i2 == segment.limit) {
                Segment pop = segment.pop();
                this.head = pop;
                SegmentPool.recycle(segment);
                segment = pop;
            }
        }
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeUtf8(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return writeUtf8(string, 0, string.length());
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeUtf8(@NotNull String string, int i2, int i3) {
        char charAt;
        long size;
        long j2;
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
                    Segment writableSegment$okio = writableSegment$okio(1);
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
                    setSize$okio(size() + i7);
                } else {
                    if (charAt2 < 2048) {
                        Segment writableSegment$okio2 = writableSegment$okio(2);
                        byte[] bArr2 = writableSegment$okio2.data;
                        int i8 = writableSegment$okio2.limit;
                        bArr2[i8] = (byte) ((charAt2 >> 6) | 192);
                        bArr2[i8 + 1] = (byte) ((charAt2 & '?') | 128);
                        writableSegment$okio2.limit = i8 + 2;
                        size = size();
                        j2 = 2;
                    } else if (charAt2 < 55296 || charAt2 > 57343) {
                        Segment writableSegment$okio3 = writableSegment$okio(3);
                        byte[] bArr3 = writableSegment$okio3.data;
                        int i9 = writableSegment$okio3.limit;
                        bArr3[i9] = (byte) ((charAt2 >> '\f') | BERTags.FLAGS);
                        bArr3[i9 + 1] = (byte) ((63 & (charAt2 >> 6)) | 128);
                        bArr3[i9 + 2] = (byte) ((charAt2 & '?') | 128);
                        writableSegment$okio3.limit = i9 + 3;
                        size = size();
                        j2 = 3;
                    } else {
                        int i10 = i2 + 1;
                        char charAt3 = i10 < i3 ? string.charAt(i10) : (char) 0;
                        if (charAt2 <= 56319) {
                            if (56320 <= charAt3 && charAt3 <= 57343) {
                                int i11 = (((charAt2 & 1023) << 10) | (charAt3 & 1023)) + 65536;
                                Segment writableSegment$okio4 = writableSegment$okio(4);
                                byte[] bArr4 = writableSegment$okio4.data;
                                int i12 = writableSegment$okio4.limit;
                                bArr4[i12] = (byte) ((i11 >> 18) | 240);
                                bArr4[i12 + 1] = (byte) (((i11 >> 12) & 63) | 128);
                                bArr4[i12 + 2] = (byte) (((i11 >> 6) & 63) | 128);
                                bArr4[i12 + 3] = (byte) ((i11 & 63) | 128);
                                writableSegment$okio4.limit = i12 + 4;
                                setSize$okio(size() + 4);
                                i2 += 2;
                            }
                        }
                        writeByte(63);
                        i2 = i10;
                    }
                    setSize$okio(size + j2);
                    i2++;
                }
            }
            return this;
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("beginIndex < 0: ", Integer.valueOf(i2)).toString());
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeUtf8CodePoint(int i2) {
        long size;
        long j2;
        if (i2 < 128) {
            writeByte(i2);
        } else {
            if (i2 < 2048) {
                Segment writableSegment$okio = writableSegment$okio(2);
                byte[] bArr = writableSegment$okio.data;
                int i3 = writableSegment$okio.limit;
                bArr[i3] = (byte) ((i2 >> 6) | 192);
                bArr[i3 + 1] = (byte) ((i2 & 63) | 128);
                writableSegment$okio.limit = i3 + 2;
                size = size();
                j2 = 2;
            } else {
                boolean z = false;
                if (55296 <= i2 && i2 <= 57343) {
                    z = true;
                }
                if (z) {
                    writeByte(63);
                } else if (i2 < 65536) {
                    Segment writableSegment$okio2 = writableSegment$okio(3);
                    byte[] bArr2 = writableSegment$okio2.data;
                    int i4 = writableSegment$okio2.limit;
                    bArr2[i4] = (byte) ((i2 >> 12) | BERTags.FLAGS);
                    bArr2[i4 + 1] = (byte) (((i2 >> 6) & 63) | 128);
                    bArr2[i4 + 2] = (byte) ((i2 & 63) | 128);
                    writableSegment$okio2.limit = i4 + 3;
                    size = size();
                    j2 = 3;
                } else if (i2 > 1114111) {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Unexpected code point: 0x", _UtilKt.toHexString(i2)));
                } else {
                    Segment writableSegment$okio3 = writableSegment$okio(4);
                    byte[] bArr3 = writableSegment$okio3.data;
                    int i5 = writableSegment$okio3.limit;
                    bArr3[i5] = (byte) ((i2 >> 18) | 240);
                    bArr3[i5 + 1] = (byte) (((i2 >> 12) & 63) | 128);
                    bArr3[i5 + 2] = (byte) (((i2 >> 6) & 63) | 128);
                    bArr3[i5 + 3] = (byte) ((i2 & 63) | 128);
                    writableSegment$okio3.limit = i5 + 4;
                    size = size();
                    j2 = 4;
                }
            }
            setSize$okio(size + j2);
        }
        return this;
    }
}
