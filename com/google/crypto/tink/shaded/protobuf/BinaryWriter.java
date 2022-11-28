package com.google.crypto.tink.shaded.protobuf;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.crypto.tink.shaded.protobuf.Internal;
import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import com.google.crypto.tink.shaded.protobuf.Utf8;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class BinaryWriter extends ByteOutput implements Writer {
    public static final int DEFAULT_CHUNK_SIZE = 4096;
    private static final int MAP_KEY_NUMBER = 1;
    private static final int MAP_VALUE_NUMBER = 2;

    /* renamed from: a  reason: collision with root package name */
    final ArrayDeque f9723a;
    private final BufferAllocator alloc;

    /* renamed from: b  reason: collision with root package name */
    int f9724b;
    private final int chunkSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.shaded.protobuf.BinaryWriter$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9725a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f9725a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9725a[WireFormat.FieldType.FIXED32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9725a[WireFormat.FieldType.FIXED64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9725a[WireFormat.FieldType.INT32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9725a[WireFormat.FieldType.INT64.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9725a[WireFormat.FieldType.SFIXED32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9725a[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9725a[WireFormat.FieldType.SINT32.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9725a[WireFormat.FieldType.SINT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9725a[WireFormat.FieldType.STRING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9725a[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9725a[WireFormat.FieldType.UINT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f9725a[WireFormat.FieldType.FLOAT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f9725a[WireFormat.FieldType.DOUBLE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f9725a[WireFormat.FieldType.MESSAGE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f9725a[WireFormat.FieldType.BYTES.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f9725a[WireFormat.FieldType.ENUM.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class SafeDirectWriter extends BinaryWriter {
        private ByteBuffer buffer;
        private int limitMinusOne;
        private int pos;

        SafeDirectWriter(BufferAllocator bufferAllocator, int i2) {
            super(bufferAllocator, i2, null);
            nextBuffer();
        }

        private int bytesWrittenToCurrentBuffer() {
            return this.limitMinusOne - this.pos;
        }

        private void nextBuffer() {
            nextBuffer(e());
        }

        private void nextBuffer(int i2) {
            nextBuffer(f(i2));
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (!allocatedBuffer.hasNioBuffer()) {
                throw new RuntimeException("Allocated buffer does not have NIO buffer");
            }
            ByteBuffer nioBuffer = allocatedBuffer.nioBuffer();
            if (!nioBuffer.isDirect()) {
                throw new RuntimeException("Allocator returned non-direct buffer");
            }
            b();
            this.f9723a.addFirst(allocatedBuffer);
            this.buffer = nioBuffer;
            nioBuffer.limit(nioBuffer.capacity());
            this.buffer.position(0);
            this.buffer.order(ByteOrder.LITTLE_ENDIAN);
            int limit = this.buffer.limit() - 1;
            this.limitMinusOne = limit;
            this.pos = limit;
        }

        private int spaceLeft() {
            return this.pos + 1;
        }

        private void writeVarint32FiveBytes(int i2) {
            ByteBuffer byteBuffer = this.buffer;
            int i3 = this.pos;
            this.pos = i3 - 1;
            byteBuffer.put(i3, (byte) (i2 >>> 28));
            int i4 = this.pos - 4;
            this.pos = i4;
            this.buffer.putInt(i4 + 1, (i2 & 127) | 128 | ((((i2 >>> 21) & 127) | 128) << 24) | ((((i2 >>> 14) & 127) | 128) << 16) | ((((i2 >>> 7) & 127) | 128) << 8));
        }

        private void writeVarint32FourBytes(int i2) {
            int i3 = this.pos - 4;
            this.pos = i3;
            this.buffer.putInt(i3 + 1, (i2 & 127) | 128 | ((266338304 & i2) << 3) | (((2080768 & i2) | 2097152) << 2) | (((i2 & 16256) | 16384) << 1));
        }

        private void writeVarint32OneByte(int i2) {
            ByteBuffer byteBuffer = this.buffer;
            int i3 = this.pos;
            this.pos = i3 - 1;
            byteBuffer.put(i3, (byte) i2);
        }

        private void writeVarint32ThreeBytes(int i2) {
            int i3 = this.pos - 3;
            this.pos = i3;
            this.buffer.putInt(i3, (((i2 & 127) | 128) << 8) | ((2080768 & i2) << 10) | (((i2 & 16256) | 16384) << 9));
        }

        private void writeVarint32TwoBytes(int i2) {
            int i3 = this.pos - 2;
            this.pos = i3;
            this.buffer.putShort(i3 + 1, (short) ((i2 & 127) | 128 | ((i2 & 16256) << 1)));
        }

        private void writeVarint64EightBytes(long j2) {
            int i2 = this.pos - 8;
            this.pos = i2;
            this.buffer.putLong(i2 + 1, (j2 & 127) | 128 | ((71494644084506624L & j2) << 7) | (((558551906910208L & j2) | 562949953421312L) << 6) | (((4363686772736L & j2) | 4398046511104L) << 5) | (((34091302912L & j2) | 34359738368L) << 4) | (((266338304 & j2) | 268435456) << 3) | (((2080768 & j2) | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) << 2) | (((16256 & j2) | 16384) << 1));
        }

        private void writeVarint64EightBytesWithSign(long j2) {
            int i2 = this.pos - 8;
            this.pos = i2;
            this.buffer.putLong(i2 + 1, (j2 & 127) | 128 | (((71494644084506624L & j2) | 72057594037927936L) << 7) | (((558551906910208L & j2) | 562949953421312L) << 6) | (((4363686772736L & j2) | 4398046511104L) << 5) | (((34091302912L & j2) | 34359738368L) << 4) | (((266338304 & j2) | 268435456) << 3) | (((2080768 & j2) | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) << 2) | (((16256 & j2) | 16384) << 1));
        }

        private void writeVarint64FiveBytes(long j2) {
            int i2 = this.pos - 5;
            this.pos = i2;
            this.buffer.putLong(i2 - 2, (((j2 & 127) | 128) << 24) | ((34091302912L & j2) << 28) | (((266338304 & j2) | 268435456) << 27) | (((2080768 & j2) | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) << 26) | (((16256 & j2) | 16384) << 25));
        }

        private void writeVarint64FourBytes(long j2) {
            writeVarint32FourBytes((int) j2);
        }

        private void writeVarint64NineBytes(long j2) {
            ByteBuffer byteBuffer = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            byteBuffer.put(i2, (byte) (j2 >>> 56));
            writeVarint64EightBytesWithSign(j2 & 72057594037927935L);
        }

        private void writeVarint64OneByte(long j2) {
            writeVarint32OneByte((int) j2);
        }

        private void writeVarint64SevenBytes(long j2) {
            int i2 = this.pos - 7;
            this.pos = i2;
            this.buffer.putLong(i2, (((j2 & 127) | 128) << 8) | ((558551906910208L & j2) << 14) | (((4363686772736L & j2) | 4398046511104L) << 13) | (((34091302912L & j2) | 34359738368L) << 12) | (((266338304 & j2) | 268435456) << 11) | (((2080768 & j2) | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) << 10) | (((16256 & j2) | 16384) << 9));
        }

        private void writeVarint64SixBytes(long j2) {
            int i2 = this.pos - 6;
            this.pos = i2;
            this.buffer.putLong(i2 - 1, (((j2 & 127) | 128) << 16) | ((4363686772736L & j2) << 21) | (((34091302912L & j2) | 34359738368L) << 20) | (((266338304 & j2) | 268435456) << 19) | (((2080768 & j2) | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) << 18) | (((16256 & j2) | 16384) << 17));
        }

        private void writeVarint64TenBytes(long j2) {
            ByteBuffer byteBuffer = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            byteBuffer.put(i2, (byte) (j2 >>> 63));
            ByteBuffer byteBuffer2 = this.buffer;
            int i3 = this.pos;
            this.pos = i3 - 1;
            byteBuffer2.put(i3, (byte) (((j2 >>> 56) & 127) | 128));
            writeVarint64EightBytesWithSign(j2 & 72057594037927935L);
        }

        private void writeVarint64ThreeBytes(long j2) {
            writeVarint32ThreeBytes((int) j2);
        }

        private void writeVarint64TwoBytes(long j2) {
            writeVarint32TwoBytes((int) j2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void b() {
            if (this.buffer != null) {
                this.f9724b += bytesWrittenToCurrentBuffer();
                this.buffer.position(this.pos + 1);
                this.buffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.f9724b + bytesWrittenToCurrentBuffer();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void m(int i2) {
            if (spaceLeft() < i2) {
                nextBuffer(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void n(boolean z) {
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void o(int i2) {
            int i3 = this.pos - 4;
            this.pos = i3;
            this.buffer.putInt(i3 + 1, i2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void p(long j2) {
            int i2 = this.pos - 8;
            this.pos = i2;
            this.buffer.putLong(i2 + 1, j2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void q(int i2) {
            if (i2 >= 0) {
                u(i2);
            } else {
                v(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void s(int i2) {
            u(CodedOutputStream.encodeZigZag32(i2));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void t(long j2) {
            v(CodedOutputStream.encodeZigZag64(j2));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void u(int i2) {
            if ((i2 & (-128)) == 0) {
                writeVarint32OneByte(i2);
            } else if ((i2 & (-16384)) == 0) {
                writeVarint32TwoBytes(i2);
            } else if (((-2097152) & i2) == 0) {
                writeVarint32ThreeBytes(i2);
            } else if (((-268435456) & i2) == 0) {
                writeVarint32FourBytes(i2);
            } else {
                writeVarint32FiveBytes(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void v(long j2) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j2)) {
                case 1:
                    writeVarint64OneByte(j2);
                    return;
                case 2:
                    writeVarint64TwoBytes(j2);
                    return;
                case 3:
                    writeVarint64ThreeBytes(j2);
                    return;
                case 4:
                    writeVarint64FourBytes(j2);
                    return;
                case 5:
                    writeVarint64FiveBytes(j2);
                    return;
                case 6:
                    writeVarint64SixBytes(j2);
                    return;
                case 7:
                    writeVarint64SevenBytes(j2);
                    return;
                case 8:
                    writeVarint64EightBytes(j2);
                    return;
                case 9:
                    writeVarint64NineBytes(j2);
                    return;
                case 10:
                    writeVarint64TenBytes(j2);
                    return;
                default:
                    return;
            }
        }

        void w(String str) {
            int i2;
            ByteBuffer byteBuffer;
            int i3;
            int i4;
            int i5;
            int i6;
            char charAt;
            m(str.length());
            int length = str.length() - 1;
            this.pos -= length;
            while (length >= 0 && (charAt = str.charAt(length)) < 128) {
                this.buffer.put(this.pos + length, (byte) charAt);
                length--;
            }
            if (length == -1) {
                this.pos--;
                return;
            }
            this.pos += length;
            while (length >= 0) {
                char charAt2 = str.charAt(length);
                if (charAt2 >= 128 || (i6 = this.pos) < 0) {
                    if (charAt2 < 2048 && (i5 = this.pos) > 0) {
                        ByteBuffer byteBuffer2 = this.buffer;
                        this.pos = i5 - 1;
                        byteBuffer2.put(i5, (byte) ((charAt2 & '?') | 128));
                        byteBuffer = this.buffer;
                        i3 = this.pos;
                        this.pos = i3 - 1;
                        i4 = (charAt2 >>> 6) | 960;
                    } else if ((charAt2 < 55296 || 57343 < charAt2) && (i2 = this.pos) > 1) {
                        ByteBuffer byteBuffer3 = this.buffer;
                        this.pos = i2 - 1;
                        byteBuffer3.put(i2, (byte) ((charAt2 & '?') | 128));
                        ByteBuffer byteBuffer4 = this.buffer;
                        int i7 = this.pos;
                        this.pos = i7 - 1;
                        byteBuffer4.put(i7, (byte) (((charAt2 >>> 6) & 63) | 128));
                        byteBuffer = this.buffer;
                        i3 = this.pos;
                        this.pos = i3 - 1;
                        i4 = (charAt2 >>> '\f') | 480;
                    } else if (this.pos > 2) {
                        if (length != 0) {
                            char charAt3 = str.charAt(length - 1);
                            if (Character.isSurrogatePair(charAt3, charAt2)) {
                                length--;
                                int codePoint = Character.toCodePoint(charAt3, charAt2);
                                ByteBuffer byteBuffer5 = this.buffer;
                                int i8 = this.pos;
                                this.pos = i8 - 1;
                                byteBuffer5.put(i8, (byte) ((codePoint & 63) | 128));
                                ByteBuffer byteBuffer6 = this.buffer;
                                int i9 = this.pos;
                                this.pos = i9 - 1;
                                byteBuffer6.put(i9, (byte) (((codePoint >>> 6) & 63) | 128));
                                ByteBuffer byteBuffer7 = this.buffer;
                                int i10 = this.pos;
                                this.pos = i10 - 1;
                                byteBuffer7.put(i10, (byte) (((codePoint >>> 12) & 63) | 128));
                                byteBuffer = this.buffer;
                                i3 = this.pos;
                                this.pos = i3 - 1;
                                i4 = (codePoint >>> 18) | 240;
                            }
                        }
                        throw new Utf8.UnpairedSurrogateException(length - 1, length);
                    } else {
                        m(length);
                        length++;
                    }
                    byteBuffer.put(i3, (byte) i4);
                } else {
                    ByteBuffer byteBuffer8 = this.buffer;
                    this.pos = i6 - 1;
                    byteBuffer8.put(i6, (byte) charAt2);
                }
                length--;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte b2) {
            ByteBuffer byteBuffer = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            byteBuffer.put(i2, b2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                nextBuffer(remaining);
            }
            int i2 = this.pos - remaining;
            this.pos = i2;
            this.buffer.position(i2 + 1);
            this.buffer.put(byteBuffer);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte[] bArr, int i2, int i3) {
            if (spaceLeft() < i3) {
                nextBuffer(i3);
            }
            int i4 = this.pos - i3;
            this.pos = i4;
            this.buffer.position(i4 + 1);
            this.buffer.put(bArr, i2, i3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBool(int i2, boolean z) {
            m(6);
            write(z ? (byte) 1 : (byte) 0);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBytes(int i2, ByteString byteString) {
            try {
                byteString.p(this);
                m(10);
                u(byteString.size());
                writeTag(i2, 2);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeEndGroup(int i2) {
            writeTag(i2, 4);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed32(int i2, int i3) {
            m(9);
            o(i3);
            writeTag(i2, 5);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed64(int i2, long j2) {
            m(13);
            p(j2);
            writeTag(i2, 1);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int i2, Object obj) {
            writeTag(i2, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int i2, Object obj, Schema schema) {
            writeTag(i2, 4);
            schema.writeTo(obj, this);
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeInt32(int i2, int i3) {
            m(15);
            q(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                this.f9724b += remaining;
                this.f9723a.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
                return;
            }
            int i2 = this.pos - remaining;
            this.pos = i2;
            this.buffer.position(i2 + 1);
            this.buffer.put(byteBuffer);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i2, int i3) {
            if (spaceLeft() < i3) {
                this.f9724b += i3;
                this.f9723a.addFirst(AllocatedBuffer.wrap(bArr, i2, i3));
                nextBuffer();
                return;
            }
            int i4 = this.pos - i3;
            this.pos = i4;
            this.buffer.position(i4 + 1);
            this.buffer.put(bArr, i2, i3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int i2, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int i2, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt32(int i2, int i3) {
            m(10);
            s(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt64(int i2, long j2) {
            m(15);
            t(j2);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeStartGroup(int i2) {
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeString(int i2, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            w(str);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeTag(int i2, int i3) {
            u(WireFormat.a(i2, i3));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt32(int i2, int i3) {
            m(10);
            u(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt64(int i2, long j2) {
            m(15);
            v(j2);
            writeTag(i2, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class SafeHeapWriter extends BinaryWriter {
        private AllocatedBuffer allocatedBuffer;
        private byte[] buffer;
        private int limit;
        private int limitMinusOne;
        private int offset;
        private int offsetMinusOne;
        private int pos;

        SafeHeapWriter(BufferAllocator bufferAllocator, int i2) {
            super(bufferAllocator, i2, null);
            nextBuffer();
        }

        private void nextBuffer() {
            nextBuffer(g());
        }

        private void nextBuffer(int i2) {
            nextBuffer(h(i2));
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (!allocatedBuffer.hasArray()) {
                throw new RuntimeException("Allocator returned non-heap buffer");
            }
            b();
            this.f9723a.addFirst(allocatedBuffer);
            this.allocatedBuffer = allocatedBuffer;
            this.buffer = allocatedBuffer.array();
            int arrayOffset = allocatedBuffer.arrayOffset();
            this.limit = allocatedBuffer.limit() + arrayOffset;
            int position = arrayOffset + allocatedBuffer.position();
            this.offset = position;
            this.offsetMinusOne = position - 1;
            int i2 = this.limit - 1;
            this.limitMinusOne = i2;
            this.pos = i2;
        }

        private void writeVarint32FiveBytes(int i2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (i2 >>> 28);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (((i2 >>> 21) & 127) | 128);
            int i6 = i5 - 1;
            this.pos = i6;
            bArr[i5] = (byte) (((i2 >>> 14) & 127) | 128);
            int i7 = i6 - 1;
            this.pos = i7;
            bArr[i6] = (byte) (((i2 >>> 7) & 127) | 128);
            this.pos = i7 - 1;
            bArr[i7] = (byte) ((i2 & 127) | 128);
        }

        private void writeVarint32FourBytes(int i2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (i2 >>> 21);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (((i2 >>> 14) & 127) | 128);
            int i6 = i5 - 1;
            this.pos = i6;
            bArr[i5] = (byte) (((i2 >>> 7) & 127) | 128);
            this.pos = i6 - 1;
            bArr[i6] = (byte) ((i2 & 127) | 128);
        }

        private void writeVarint32OneByte(int i2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) i2;
        }

        private void writeVarint32ThreeBytes(int i2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (i2 >>> 14);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (((i2 >>> 7) & 127) | 128);
            this.pos = i5 - 1;
            bArr[i5] = (byte) ((i2 & 127) | 128);
        }

        private void writeVarint32TwoBytes(int i2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (i2 >>> 7);
            this.pos = i4 - 1;
            bArr[i4] = (byte) ((i2 & 127) | 128);
        }

        private void writeVarint64EightBytes(long j2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            int i3 = i2 - 1;
            this.pos = i3;
            bArr[i2] = (byte) (j2 >>> 49);
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (((j2 >>> 42) & 127) | 128);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (((j2 >>> 35) & 127) | 128);
            int i6 = i5 - 1;
            this.pos = i6;
            bArr[i5] = (byte) (((j2 >>> 28) & 127) | 128);
            int i7 = i6 - 1;
            this.pos = i7;
            bArr[i6] = (byte) (((j2 >>> 21) & 127) | 128);
            int i8 = i7 - 1;
            this.pos = i8;
            bArr[i7] = (byte) (((j2 >>> 14) & 127) | 128);
            int i9 = i8 - 1;
            this.pos = i9;
            bArr[i8] = (byte) (((j2 >>> 7) & 127) | 128);
            this.pos = i9 - 1;
            bArr[i9] = (byte) ((j2 & 127) | 128);
        }

        private void writeVarint64FiveBytes(long j2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            int i3 = i2 - 1;
            this.pos = i3;
            bArr[i2] = (byte) (j2 >>> 28);
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (((j2 >>> 21) & 127) | 128);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (((j2 >>> 14) & 127) | 128);
            int i6 = i5 - 1;
            this.pos = i6;
            bArr[i5] = (byte) (((j2 >>> 7) & 127) | 128);
            this.pos = i6 - 1;
            bArr[i6] = (byte) ((j2 & 127) | 128);
        }

        private void writeVarint64FourBytes(long j2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            int i3 = i2 - 1;
            this.pos = i3;
            bArr[i2] = (byte) (j2 >>> 21);
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (((j2 >>> 14) & 127) | 128);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (((j2 >>> 7) & 127) | 128);
            this.pos = i5 - 1;
            bArr[i5] = (byte) ((j2 & 127) | 128);
        }

        private void writeVarint64NineBytes(long j2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            int i3 = i2 - 1;
            this.pos = i3;
            bArr[i2] = (byte) (j2 >>> 56);
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (((j2 >>> 49) & 127) | 128);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (((j2 >>> 42) & 127) | 128);
            int i6 = i5 - 1;
            this.pos = i6;
            bArr[i5] = (byte) (((j2 >>> 35) & 127) | 128);
            int i7 = i6 - 1;
            this.pos = i7;
            bArr[i6] = (byte) (((j2 >>> 28) & 127) | 128);
            int i8 = i7 - 1;
            this.pos = i8;
            bArr[i7] = (byte) (((j2 >>> 21) & 127) | 128);
            int i9 = i8 - 1;
            this.pos = i9;
            bArr[i8] = (byte) (((j2 >>> 14) & 127) | 128);
            int i10 = i9 - 1;
            this.pos = i10;
            bArr[i9] = (byte) (((j2 >>> 7) & 127) | 128);
            this.pos = i10 - 1;
            bArr[i10] = (byte) ((j2 & 127) | 128);
        }

        private void writeVarint64OneByte(long j2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) j2;
        }

        private void writeVarint64SevenBytes(long j2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            int i3 = i2 - 1;
            this.pos = i3;
            bArr[i2] = (byte) (j2 >>> 42);
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (((j2 >>> 35) & 127) | 128);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (((j2 >>> 28) & 127) | 128);
            int i6 = i5 - 1;
            this.pos = i6;
            bArr[i5] = (byte) (((j2 >>> 21) & 127) | 128);
            int i7 = i6 - 1;
            this.pos = i7;
            bArr[i6] = (byte) (((j2 >>> 14) & 127) | 128);
            int i8 = i7 - 1;
            this.pos = i8;
            bArr[i7] = (byte) (((j2 >>> 7) & 127) | 128);
            this.pos = i8 - 1;
            bArr[i8] = (byte) ((j2 & 127) | 128);
        }

        private void writeVarint64SixBytes(long j2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            int i3 = i2 - 1;
            this.pos = i3;
            bArr[i2] = (byte) (j2 >>> 35);
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (((j2 >>> 28) & 127) | 128);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (((j2 >>> 21) & 127) | 128);
            int i6 = i5 - 1;
            this.pos = i6;
            bArr[i5] = (byte) (((j2 >>> 14) & 127) | 128);
            int i7 = i6 - 1;
            this.pos = i7;
            bArr[i6] = (byte) (((j2 >>> 7) & 127) | 128);
            this.pos = i7 - 1;
            bArr[i7] = (byte) ((j2 & 127) | 128);
        }

        private void writeVarint64TenBytes(long j2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            int i3 = i2 - 1;
            this.pos = i3;
            bArr[i2] = (byte) (j2 >>> 63);
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (((j2 >>> 56) & 127) | 128);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (((j2 >>> 49) & 127) | 128);
            int i6 = i5 - 1;
            this.pos = i6;
            bArr[i5] = (byte) (((j2 >>> 42) & 127) | 128);
            int i7 = i6 - 1;
            this.pos = i7;
            bArr[i6] = (byte) (((j2 >>> 35) & 127) | 128);
            int i8 = i7 - 1;
            this.pos = i8;
            bArr[i7] = (byte) (((j2 >>> 28) & 127) | 128);
            int i9 = i8 - 1;
            this.pos = i9;
            bArr[i8] = (byte) (((j2 >>> 21) & 127) | 128);
            int i10 = i9 - 1;
            this.pos = i10;
            bArr[i9] = (byte) (((j2 >>> 14) & 127) | 128);
            int i11 = i10 - 1;
            this.pos = i11;
            bArr[i10] = (byte) (((j2 >>> 7) & 127) | 128);
            this.pos = i11 - 1;
            bArr[i11] = (byte) ((j2 & 127) | 128);
        }

        private void writeVarint64ThreeBytes(long j2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            int i3 = i2 - 1;
            this.pos = i3;
            bArr[i2] = (byte) (((int) j2) >>> 14);
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (((j2 >>> 7) & 127) | 128);
            this.pos = i4 - 1;
            bArr[i4] = (byte) ((j2 & 127) | 128);
        }

        private void writeVarint64TwoBytes(long j2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            int i3 = i2 - 1;
            this.pos = i3;
            bArr[i2] = (byte) (j2 >>> 7);
            this.pos = i3 - 1;
            bArr[i3] = (byte) ((((int) j2) & 127) | 128);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void b() {
            if (this.allocatedBuffer != null) {
                this.f9724b += bytesWrittenToCurrentBuffer();
                AllocatedBuffer allocatedBuffer = this.allocatedBuffer;
                allocatedBuffer.position((this.pos - allocatedBuffer.arrayOffset()) + 1);
                this.allocatedBuffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        int bytesWrittenToCurrentBuffer() {
            return this.limitMinusOne - this.pos;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.f9724b + bytesWrittenToCurrentBuffer();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void m(int i2) {
            if (spaceLeft() < i2) {
                nextBuffer(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void n(boolean z) {
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void o(int i2) {
            byte[] bArr = this.buffer;
            int i3 = this.pos;
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) ((i2 >> 24) & 255);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) ((i2 >> 16) & 255);
            int i6 = i5 - 1;
            this.pos = i6;
            bArr[i5] = (byte) ((i2 >> 8) & 255);
            this.pos = i6 - 1;
            bArr[i6] = (byte) (i2 & 255);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void p(long j2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            int i3 = i2 - 1;
            this.pos = i3;
            bArr[i2] = (byte) (((int) (j2 >> 56)) & 255);
            int i4 = i3 - 1;
            this.pos = i4;
            bArr[i3] = (byte) (((int) (j2 >> 48)) & 255);
            int i5 = i4 - 1;
            this.pos = i5;
            bArr[i4] = (byte) (((int) (j2 >> 40)) & 255);
            int i6 = i5 - 1;
            this.pos = i6;
            bArr[i5] = (byte) (((int) (j2 >> 32)) & 255);
            int i7 = i6 - 1;
            this.pos = i7;
            bArr[i6] = (byte) (((int) (j2 >> 24)) & 255);
            int i8 = i7 - 1;
            this.pos = i8;
            bArr[i7] = (byte) (((int) (j2 >> 16)) & 255);
            int i9 = i8 - 1;
            this.pos = i9;
            bArr[i8] = (byte) (((int) (j2 >> 8)) & 255);
            this.pos = i9 - 1;
            bArr[i9] = (byte) (((int) j2) & 255);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void q(int i2) {
            if (i2 >= 0) {
                u(i2);
            } else {
                v(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void s(int i2) {
            u(CodedOutputStream.encodeZigZag32(i2));
        }

        int spaceLeft() {
            return this.pos - this.offsetMinusOne;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void t(long j2) {
            v(CodedOutputStream.encodeZigZag64(j2));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void u(int i2) {
            if ((i2 & (-128)) == 0) {
                writeVarint32OneByte(i2);
            } else if ((i2 & (-16384)) == 0) {
                writeVarint32TwoBytes(i2);
            } else if (((-2097152) & i2) == 0) {
                writeVarint32ThreeBytes(i2);
            } else if (((-268435456) & i2) == 0) {
                writeVarint32FourBytes(i2);
            } else {
                writeVarint32FiveBytes(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void v(long j2) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j2)) {
                case 1:
                    writeVarint64OneByte(j2);
                    return;
                case 2:
                    writeVarint64TwoBytes(j2);
                    return;
                case 3:
                    writeVarint64ThreeBytes(j2);
                    return;
                case 4:
                    writeVarint64FourBytes(j2);
                    return;
                case 5:
                    writeVarint64FiveBytes(j2);
                    return;
                case 6:
                    writeVarint64SixBytes(j2);
                    return;
                case 7:
                    writeVarint64SevenBytes(j2);
                    return;
                case 8:
                    writeVarint64EightBytes(j2);
                    return;
                case 9:
                    writeVarint64NineBytes(j2);
                    return;
                case 10:
                    writeVarint64TenBytes(j2);
                    return;
                default:
                    return;
            }
        }

        void w(String str) {
            int i2;
            int i3;
            int i4;
            char charAt;
            m(str.length());
            int length = str.length() - 1;
            this.pos -= length;
            while (length >= 0 && (charAt = str.charAt(length)) < 128) {
                this.buffer[this.pos + length] = (byte) charAt;
                length--;
            }
            if (length == -1) {
                this.pos--;
                return;
            }
            this.pos += length;
            while (length >= 0) {
                char charAt2 = str.charAt(length);
                if (charAt2 < 128 && (i4 = this.pos) > this.offsetMinusOne) {
                    byte[] bArr = this.buffer;
                    this.pos = i4 - 1;
                    bArr[i4] = (byte) charAt2;
                } else if (charAt2 < 2048 && (i3 = this.pos) > this.offset) {
                    byte[] bArr2 = this.buffer;
                    int i5 = i3 - 1;
                    this.pos = i5;
                    bArr2[i3] = (byte) ((charAt2 & '?') | 128);
                    this.pos = i5 - 1;
                    bArr2[i5] = (byte) ((charAt2 >>> 6) | 960);
                } else if ((charAt2 < 55296 || 57343 < charAt2) && (i2 = this.pos) > this.offset + 1) {
                    byte[] bArr3 = this.buffer;
                    int i6 = i2 - 1;
                    this.pos = i6;
                    bArr3[i2] = (byte) ((charAt2 & '?') | 128);
                    int i7 = i6 - 1;
                    this.pos = i7;
                    bArr3[i6] = (byte) (((charAt2 >>> 6) & 63) | 128);
                    this.pos = i7 - 1;
                    bArr3[i7] = (byte) ((charAt2 >>> '\f') | 480);
                } else if (this.pos > this.offset + 2) {
                    if (length != 0) {
                        char charAt3 = str.charAt(length - 1);
                        if (Character.isSurrogatePair(charAt3, charAt2)) {
                            length--;
                            int codePoint = Character.toCodePoint(charAt3, charAt2);
                            byte[] bArr4 = this.buffer;
                            int i8 = this.pos;
                            int i9 = i8 - 1;
                            this.pos = i9;
                            bArr4[i8] = (byte) ((codePoint & 63) | 128);
                            int i10 = i9 - 1;
                            this.pos = i10;
                            bArr4[i9] = (byte) (((codePoint >>> 6) & 63) | 128);
                            int i11 = i10 - 1;
                            this.pos = i11;
                            bArr4[i10] = (byte) (((codePoint >>> 12) & 63) | 128);
                            this.pos = i11 - 1;
                            bArr4[i11] = (byte) ((codePoint >>> 18) | 240);
                        }
                    }
                    throw new Utf8.UnpairedSurrogateException(length - 1, length);
                } else {
                    m(length);
                    length++;
                }
                length--;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte b2) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = b2;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                nextBuffer(remaining);
            }
            int i2 = this.pos - remaining;
            this.pos = i2;
            byteBuffer.get(this.buffer, i2 + 1, remaining);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte[] bArr, int i2, int i3) {
            if (spaceLeft() < i3) {
                nextBuffer(i3);
            }
            int i4 = this.pos - i3;
            this.pos = i4;
            System.arraycopy(bArr, i2, this.buffer, i4 + 1, i3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBool(int i2, boolean z) {
            m(6);
            write(z ? (byte) 1 : (byte) 0);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBytes(int i2, ByteString byteString) {
            try {
                byteString.p(this);
                m(10);
                u(byteString.size());
                writeTag(i2, 2);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeEndGroup(int i2) {
            writeTag(i2, 4);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed32(int i2, int i3) {
            m(9);
            o(i3);
            writeTag(i2, 5);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed64(int i2, long j2) {
            m(13);
            p(j2);
            writeTag(i2, 1);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int i2, Object obj) {
            writeTag(i2, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int i2, Object obj, Schema schema) {
            writeTag(i2, 4);
            schema.writeTo(obj, this);
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeInt32(int i2, int i3) {
            m(15);
            q(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                this.f9724b += remaining;
                this.f9723a.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            }
            int i2 = this.pos - remaining;
            this.pos = i2;
            byteBuffer.get(this.buffer, i2 + 1, remaining);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i2, int i3) {
            if (spaceLeft() < i3) {
                this.f9724b += i3;
                this.f9723a.addFirst(AllocatedBuffer.wrap(bArr, i2, i3));
                nextBuffer();
                return;
            }
            int i4 = this.pos - i3;
            this.pos = i4;
            System.arraycopy(bArr, i2, this.buffer, i4 + 1, i3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int i2, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int i2, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt32(int i2, int i3) {
            m(10);
            s(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt64(int i2, long j2) {
            m(15);
            t(j2);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeStartGroup(int i2) {
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeString(int i2, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            w(str);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeTag(int i2, int i3) {
            u(WireFormat.a(i2, i3));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt32(int i2, int i3) {
            m(10);
            u(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt64(int i2, long j2) {
            m(15);
            v(j2);
            writeTag(i2, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class UnsafeDirectWriter extends BinaryWriter {
        private ByteBuffer buffer;
        private long bufferOffset;
        private long limitMinusOne;
        private long pos;

        UnsafeDirectWriter(BufferAllocator bufferAllocator, int i2) {
            super(bufferAllocator, i2, null);
            nextBuffer();
        }

        private int bufferPos() {
            return (int) (this.pos - this.bufferOffset);
        }

        private int bytesWrittenToCurrentBuffer() {
            return (int) (this.limitMinusOne - this.pos);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isSupported() {
            return UnsafeUtil.x();
        }

        private void nextBuffer() {
            nextBuffer(e());
        }

        private void nextBuffer(int i2) {
            nextBuffer(f(i2));
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (!allocatedBuffer.hasNioBuffer()) {
                throw new RuntimeException("Allocated buffer does not have NIO buffer");
            }
            ByteBuffer nioBuffer = allocatedBuffer.nioBuffer();
            if (!nioBuffer.isDirect()) {
                throw new RuntimeException("Allocator returned non-direct buffer");
            }
            b();
            this.f9723a.addFirst(allocatedBuffer);
            this.buffer = nioBuffer;
            nioBuffer.limit(nioBuffer.capacity());
            this.buffer.position(0);
            long i2 = UnsafeUtil.i(this.buffer);
            this.bufferOffset = i2;
            long limit = i2 + (this.buffer.limit() - 1);
            this.limitMinusOne = limit;
            this.pos = limit;
        }

        private int spaceLeft() {
            return bufferPos() + 1;
        }

        private void writeVarint32FiveBytes(int i2) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.A(j2, (byte) (i2 >>> 28));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (((i2 >>> 21) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) (((i2 >>> 14) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) (((i2 >>> 7) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.A(j6, (byte) ((i2 & 127) | 128));
        }

        private void writeVarint32FourBytes(int i2) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.A(j2, (byte) (i2 >>> 21));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (((i2 >>> 14) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) (((i2 >>> 7) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) ((i2 & 127) | 128));
        }

        private void writeVarint32OneByte(int i2) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.A(j2, (byte) i2);
        }

        private void writeVarint32ThreeBytes(int i2) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.A(j2, (byte) (i2 >>> 14));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (((i2 >>> 7) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) ((i2 & 127) | 128));
        }

        private void writeVarint32TwoBytes(int i2) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.A(j2, (byte) (i2 >>> 7));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) ((i2 & 127) | 128));
        }

        private void writeVarint64EightBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (j2 >>> 49));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) (((j2 >>> 42) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) (((j2 >>> 35) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.A(j6, (byte) (((j2 >>> 28) & 127) | 128));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.A(j7, (byte) (((j2 >>> 21) & 127) | 128));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.A(j8, (byte) (((j2 >>> 14) & 127) | 128));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.A(j9, (byte) (((j2 >>> 7) & 127) | 128));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.A(j10, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64FiveBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (j2 >>> 28));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) (((j2 >>> 21) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) (((j2 >>> 14) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.A(j6, (byte) (((j2 >>> 7) & 127) | 128));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.A(j7, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64FourBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (j2 >>> 21));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) (((j2 >>> 14) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) (((j2 >>> 7) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.A(j6, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64NineBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (j2 >>> 56));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) (((j2 >>> 49) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) (((j2 >>> 42) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.A(j6, (byte) (((j2 >>> 35) & 127) | 128));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.A(j7, (byte) (((j2 >>> 28) & 127) | 128));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.A(j8, (byte) (((j2 >>> 21) & 127) | 128));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.A(j9, (byte) (((j2 >>> 14) & 127) | 128));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.A(j10, (byte) (((j2 >>> 7) & 127) | 128));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.A(j11, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64OneByte(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) j2);
        }

        private void writeVarint64SevenBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (j2 >>> 42));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) (((j2 >>> 35) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) (((j2 >>> 28) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.A(j6, (byte) (((j2 >>> 21) & 127) | 128));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.A(j7, (byte) (((j2 >>> 14) & 127) | 128));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.A(j8, (byte) (((j2 >>> 7) & 127) | 128));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.A(j9, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64SixBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (j2 >>> 35));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) (((j2 >>> 28) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) (((j2 >>> 21) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.A(j6, (byte) (((j2 >>> 14) & 127) | 128));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.A(j7, (byte) (((j2 >>> 7) & 127) | 128));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.A(j8, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64TenBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (j2 >>> 63));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) (((j2 >>> 56) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) (((j2 >>> 49) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.A(j6, (byte) (((j2 >>> 42) & 127) | 128));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.A(j7, (byte) (((j2 >>> 35) & 127) | 128));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.A(j8, (byte) (((j2 >>> 28) & 127) | 128));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.A(j9, (byte) (((j2 >>> 21) & 127) | 128));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.A(j10, (byte) (((j2 >>> 14) & 127) | 128));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.A(j11, (byte) (((j2 >>> 7) & 127) | 128));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.A(j12, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64ThreeBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (((int) j2) >>> 14));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) (((j2 >>> 7) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64TwoBytes(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (j2 >>> 7));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) ((((int) j2) & 127) | 128));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void b() {
            if (this.buffer != null) {
                this.f9724b += bytesWrittenToCurrentBuffer();
                this.buffer.position(bufferPos() + 1);
                this.buffer = null;
                this.pos = 0L;
                this.limitMinusOne = 0L;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.f9724b + bytesWrittenToCurrentBuffer();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void m(int i2) {
            if (spaceLeft() < i2) {
                nextBuffer(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void n(boolean z) {
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void o(int i2) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.A(j2, (byte) ((i2 >> 24) & 255));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) ((i2 >> 16) & 255));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) ((i2 >> 8) & 255));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) (i2 & 255));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void p(long j2) {
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.A(j3, (byte) (((int) (j2 >> 56)) & 255));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.A(j4, (byte) (((int) (j2 >> 48)) & 255));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.A(j5, (byte) (((int) (j2 >> 40)) & 255));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.A(j6, (byte) (((int) (j2 >> 32)) & 255));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.A(j7, (byte) (((int) (j2 >> 24)) & 255));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.A(j8, (byte) (((int) (j2 >> 16)) & 255));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.A(j9, (byte) (((int) (j2 >> 8)) & 255));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.A(j10, (byte) (((int) j2) & 255));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void q(int i2) {
            if (i2 >= 0) {
                u(i2);
            } else {
                v(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void s(int i2) {
            u(CodedOutputStream.encodeZigZag32(i2));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void t(long j2) {
            v(CodedOutputStream.encodeZigZag64(j2));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void u(int i2) {
            if ((i2 & (-128)) == 0) {
                writeVarint32OneByte(i2);
            } else if ((i2 & (-16384)) == 0) {
                writeVarint32TwoBytes(i2);
            } else if (((-2097152) & i2) == 0) {
                writeVarint32ThreeBytes(i2);
            } else if (((-268435456) & i2) == 0) {
                writeVarint32FourBytes(i2);
            } else {
                writeVarint32FiveBytes(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void v(long j2) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j2)) {
                case 1:
                    writeVarint64OneByte(j2);
                    return;
                case 2:
                    writeVarint64TwoBytes(j2);
                    return;
                case 3:
                    writeVarint64ThreeBytes(j2);
                    return;
                case 4:
                    writeVarint64FourBytes(j2);
                    return;
                case 5:
                    writeVarint64FiveBytes(j2);
                    return;
                case 6:
                    writeVarint64SixBytes(j2);
                    return;
                case 7:
                    writeVarint64SevenBytes(j2);
                    return;
                case 8:
                    writeVarint64EightBytes(j2);
                    return;
                case 9:
                    writeVarint64NineBytes(j2);
                    return;
                case 10:
                    writeVarint64TenBytes(j2);
                    return;
                default:
                    return;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte b2) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.A(j2, b2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                nextBuffer(remaining);
            }
            this.pos -= remaining;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(byteBuffer);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte[] bArr, int i2, int i3) {
            if (spaceLeft() < i3) {
                nextBuffer(i3);
            }
            this.pos -= i3;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(bArr, i2, i3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBool(int i2, boolean z) {
            m(6);
            write(z ? (byte) 1 : (byte) 0);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBytes(int i2, ByteString byteString) {
            try {
                byteString.p(this);
                m(10);
                u(byteString.size());
                writeTag(i2, 2);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeEndGroup(int i2) {
            writeTag(i2, 4);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed32(int i2, int i3) {
            m(9);
            o(i3);
            writeTag(i2, 5);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed64(int i2, long j2) {
            m(13);
            p(j2);
            writeTag(i2, 1);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int i2, Object obj) {
            writeTag(i2, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int i2, Object obj, Schema schema) {
            writeTag(i2, 4);
            schema.writeTo(obj, this);
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeInt32(int i2, int i3) {
            m(15);
            q(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                this.f9724b += remaining;
                this.f9723a.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
                return;
            }
            this.pos -= remaining;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(byteBuffer);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i2, int i3) {
            if (spaceLeft() < i3) {
                this.f9724b += i3;
                this.f9723a.addFirst(AllocatedBuffer.wrap(bArr, i2, i3));
                nextBuffer();
                return;
            }
            this.pos -= i3;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(bArr, i2, i3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int i2, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int i2, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt32(int i2, int i3) {
            m(10);
            s(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt64(int i2, long j2) {
            m(15);
            t(j2);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeStartGroup(int i2) {
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeString(int i2, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            x(str);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeTag(int i2, int i3) {
            u(WireFormat.a(i2, i3));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt32(int i2, int i3) {
            m(10);
            u(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt64(int i2, long j2) {
            m(15);
            v(j2);
            writeTag(i2, 0);
        }

        void x(String str) {
            long j2;
            char c2;
            char charAt;
            m(str.length());
            int length = str.length();
            while (true) {
                length--;
                if (length < 0 || (charAt = str.charAt(length)) >= 128) {
                    break;
                }
                long j3 = this.pos;
                this.pos = j3 - 1;
                UnsafeUtil.A(j3, (byte) charAt);
            }
            if (length == -1) {
                return;
            }
            while (length >= 0) {
                char charAt2 = str.charAt(length);
                if (charAt2 < 128) {
                    j2 = this.pos;
                    if (j2 >= this.bufferOffset) {
                        this.pos = j2 - 1;
                        c2 = charAt2;
                        UnsafeUtil.A(j2, (byte) c2);
                        length--;
                    }
                }
                if (charAt2 < 2048) {
                    long j4 = this.pos;
                    if (j4 > this.bufferOffset) {
                        this.pos = j4 - 1;
                        UnsafeUtil.A(j4, (byte) ((charAt2 & '?') | 128));
                        j2 = this.pos;
                        this.pos = j2 - 1;
                        c2 = (charAt2 >>> 6) | 960;
                        UnsafeUtil.A(j2, (byte) c2);
                        length--;
                    }
                }
                if (charAt2 < 55296 || 57343 < charAt2) {
                    long j5 = this.pos;
                    if (j5 > this.bufferOffset + 1) {
                        this.pos = j5 - 1;
                        UnsafeUtil.A(j5, (byte) ((charAt2 & '?') | 128));
                        long j6 = this.pos;
                        this.pos = j6 - 1;
                        UnsafeUtil.A(j6, (byte) (((charAt2 >>> 6) & 63) | 128));
                        j2 = this.pos;
                        this.pos = j2 - 1;
                        c2 = (charAt2 >>> '\f') | 480;
                        UnsafeUtil.A(j2, (byte) c2);
                        length--;
                    }
                }
                if (this.pos > this.bufferOffset + 2) {
                    if (length != 0) {
                        char charAt3 = str.charAt(length - 1);
                        if (Character.isSurrogatePair(charAt3, charAt2)) {
                            length--;
                            int codePoint = Character.toCodePoint(charAt3, charAt2);
                            long j7 = this.pos;
                            this.pos = j7 - 1;
                            UnsafeUtil.A(j7, (byte) ((codePoint & 63) | 128));
                            long j8 = this.pos;
                            this.pos = j8 - 1;
                            UnsafeUtil.A(j8, (byte) (((codePoint >>> 6) & 63) | 128));
                            long j9 = this.pos;
                            this.pos = j9 - 1;
                            UnsafeUtil.A(j9, (byte) (((codePoint >>> 12) & 63) | 128));
                            j2 = this.pos;
                            this.pos = j2 - 1;
                            c2 = (codePoint >>> 18) | 240;
                            UnsafeUtil.A(j2, (byte) c2);
                            length--;
                        }
                    }
                    throw new Utf8.UnpairedSurrogateException(length - 1, length);
                }
                m(length);
                length++;
                length--;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class UnsafeHeapWriter extends BinaryWriter {
        private AllocatedBuffer allocatedBuffer;
        private byte[] buffer;
        private long limit;
        private long limitMinusOne;
        private long offset;
        private long offsetMinusOne;
        private long pos;

        UnsafeHeapWriter(BufferAllocator bufferAllocator, int i2) {
            super(bufferAllocator, i2, null);
            nextBuffer();
        }

        private int arrayPos() {
            return (int) this.pos;
        }

        static boolean isSupported() {
            return UnsafeUtil.w();
        }

        private void nextBuffer() {
            nextBuffer(g());
        }

        private void nextBuffer(int i2) {
            nextBuffer(h(i2));
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (!allocatedBuffer.hasArray()) {
                throw new RuntimeException("Allocator returned non-heap buffer");
            }
            b();
            this.f9723a.addFirst(allocatedBuffer);
            this.allocatedBuffer = allocatedBuffer;
            this.buffer = allocatedBuffer.array();
            int arrayOffset = allocatedBuffer.arrayOffset();
            this.limit = allocatedBuffer.limit() + arrayOffset;
            long position = arrayOffset + allocatedBuffer.position();
            this.offset = position;
            this.offsetMinusOne = position - 1;
            long j2 = this.limit - 1;
            this.limitMinusOne = j2;
            this.pos = j2;
        }

        private void writeVarint32FiveBytes(int i2) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.B(bArr, j2, (byte) (i2 >>> 28));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr2, j3, (byte) (((i2 >>> 21) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr3, j4, (byte) (((i2 >>> 14) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr4, j5, (byte) (((i2 >>> 7) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.B(bArr5, j6, (byte) ((i2 & 127) | 128));
        }

        private void writeVarint32FourBytes(int i2) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.B(bArr, j2, (byte) (i2 >>> 21));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr2, j3, (byte) (((i2 >>> 14) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr3, j4, (byte) (((i2 >>> 7) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr4, j5, (byte) ((i2 & 127) | 128));
        }

        private void writeVarint32OneByte(int i2) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.B(bArr, j2, (byte) i2);
        }

        private void writeVarint32ThreeBytes(int i2) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.B(bArr, j2, (byte) (i2 >>> 14));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr2, j3, (byte) (((i2 >>> 7) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr3, j4, (byte) ((i2 & 127) | 128));
        }

        private void writeVarint32TwoBytes(int i2) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.B(bArr, j2, (byte) (i2 >>> 7));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr2, j3, (byte) ((i2 & 127) | 128));
        }

        private void writeVarint64EightBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr, j3, (byte) (j2 >>> 49));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr2, j4, (byte) (((j2 >>> 42) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr3, j5, (byte) (((j2 >>> 35) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.B(bArr4, j6, (byte) (((j2 >>> 28) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.B(bArr5, j7, (byte) (((j2 >>> 21) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.B(bArr6, j8, (byte) (((j2 >>> 14) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.B(bArr7, j9, (byte) (((j2 >>> 7) & 127) | 128));
            byte[] bArr8 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.B(bArr8, j10, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64FiveBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr, j3, (byte) (j2 >>> 28));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr2, j4, (byte) (((j2 >>> 21) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr3, j5, (byte) (((j2 >>> 14) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.B(bArr4, j6, (byte) (((j2 >>> 7) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.B(bArr5, j7, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64FourBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr, j3, (byte) (j2 >>> 21));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr2, j4, (byte) (((j2 >>> 14) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr3, j5, (byte) (((j2 >>> 7) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.B(bArr4, j6, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64NineBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr, j3, (byte) (j2 >>> 56));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr2, j4, (byte) (((j2 >>> 49) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr3, j5, (byte) (((j2 >>> 42) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.B(bArr4, j6, (byte) (((j2 >>> 35) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.B(bArr5, j7, (byte) (((j2 >>> 28) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.B(bArr6, j8, (byte) (((j2 >>> 21) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.B(bArr7, j9, (byte) (((j2 >>> 14) & 127) | 128));
            byte[] bArr8 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.B(bArr8, j10, (byte) (((j2 >>> 7) & 127) | 128));
            byte[] bArr9 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.B(bArr9, j11, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64OneByte(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr, j3, (byte) j2);
        }

        private void writeVarint64SevenBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr, j3, (byte) (j2 >>> 42));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr2, j4, (byte) (((j2 >>> 35) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr3, j5, (byte) (((j2 >>> 28) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.B(bArr4, j6, (byte) (((j2 >>> 21) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.B(bArr5, j7, (byte) (((j2 >>> 14) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.B(bArr6, j8, (byte) (((j2 >>> 7) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.B(bArr7, j9, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64SixBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr, j3, (byte) (j2 >>> 35));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr2, j4, (byte) (((j2 >>> 28) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr3, j5, (byte) (((j2 >>> 21) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.B(bArr4, j6, (byte) (((j2 >>> 14) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.B(bArr5, j7, (byte) (((j2 >>> 7) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.B(bArr6, j8, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64TenBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr, j3, (byte) (j2 >>> 63));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr2, j4, (byte) (((j2 >>> 56) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr3, j5, (byte) (((j2 >>> 49) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.B(bArr4, j6, (byte) (((j2 >>> 42) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.B(bArr5, j7, (byte) (((j2 >>> 35) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.B(bArr6, j8, (byte) (((j2 >>> 28) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.B(bArr7, j9, (byte) (((j2 >>> 21) & 127) | 128));
            byte[] bArr8 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.B(bArr8, j10, (byte) (((j2 >>> 14) & 127) | 128));
            byte[] bArr9 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.B(bArr9, j11, (byte) (((j2 >>> 7) & 127) | 128));
            byte[] bArr10 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.B(bArr10, j12, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64ThreeBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr, j3, (byte) (((int) j2) >>> 14));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr2, j4, (byte) (((j2 >>> 7) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr3, j5, (byte) ((j2 & 127) | 128));
        }

        private void writeVarint64TwoBytes(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr, j3, (byte) (j2 >>> 7));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr2, j4, (byte) ((((int) j2) & 127) | 128));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void b() {
            if (this.allocatedBuffer != null) {
                this.f9724b += bytesWrittenToCurrentBuffer();
                this.allocatedBuffer.position((arrayPos() - this.allocatedBuffer.arrayOffset()) + 1);
                this.allocatedBuffer = null;
                this.pos = 0L;
                this.limitMinusOne = 0L;
            }
        }

        int bytesWrittenToCurrentBuffer() {
            return (int) (this.limitMinusOne - this.pos);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.f9724b + bytesWrittenToCurrentBuffer();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void m(int i2) {
            if (spaceLeft() < i2) {
                nextBuffer(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void n(boolean z) {
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void o(int i2) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.B(bArr, j2, (byte) ((i2 >> 24) & 255));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr2, j3, (byte) ((i2 >> 16) & 255));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr3, j4, (byte) ((i2 >> 8) & 255));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr4, j5, (byte) (i2 & 255));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void p(long j2) {
            byte[] bArr = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.B(bArr, j3, (byte) (((int) (j2 >> 56)) & 255));
            byte[] bArr2 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.B(bArr2, j4, (byte) (((int) (j2 >> 48)) & 255));
            byte[] bArr3 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.B(bArr3, j5, (byte) (((int) (j2 >> 40)) & 255));
            byte[] bArr4 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.B(bArr4, j6, (byte) (((int) (j2 >> 32)) & 255));
            byte[] bArr5 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.B(bArr5, j7, (byte) (((int) (j2 >> 24)) & 255));
            byte[] bArr6 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.B(bArr6, j8, (byte) (((int) (j2 >> 16)) & 255));
            byte[] bArr7 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.B(bArr7, j9, (byte) (((int) (j2 >> 8)) & 255));
            byte[] bArr8 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.B(bArr8, j10, (byte) (((int) j2) & 255));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void q(int i2) {
            if (i2 >= 0) {
                u(i2);
            } else {
                v(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void s(int i2) {
            u(CodedOutputStream.encodeZigZag32(i2));
        }

        int spaceLeft() {
            return (int) (this.pos - this.offsetMinusOne);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void t(long j2) {
            v(CodedOutputStream.encodeZigZag64(j2));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void u(int i2) {
            if ((i2 & (-128)) == 0) {
                writeVarint32OneByte(i2);
            } else if ((i2 & (-16384)) == 0) {
                writeVarint32TwoBytes(i2);
            } else if (((-2097152) & i2) == 0) {
                writeVarint32ThreeBytes(i2);
            } else if (((-268435456) & i2) == 0) {
                writeVarint32FourBytes(i2);
            } else {
                writeVarint32FiveBytes(i2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void v(long j2) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j2)) {
                case 1:
                    writeVarint64OneByte(j2);
                    return;
                case 2:
                    writeVarint64TwoBytes(j2);
                    return;
                case 3:
                    writeVarint64ThreeBytes(j2);
                    return;
                case 4:
                    writeVarint64FourBytes(j2);
                    return;
                case 5:
                    writeVarint64FiveBytes(j2);
                    return;
                case 6:
                    writeVarint64SixBytes(j2);
                    return;
                case 7:
                    writeVarint64SevenBytes(j2);
                    return;
                case 8:
                    writeVarint64EightBytes(j2);
                    return;
                case 9:
                    writeVarint64NineBytes(j2);
                    return;
                case 10:
                    writeVarint64TenBytes(j2);
                    return;
                default:
                    return;
            }
        }

        void w(String str) {
            char charAt;
            m(str.length());
            int length = str.length();
            while (true) {
                length--;
                if (length < 0 || (charAt = str.charAt(length)) >= 128) {
                    break;
                }
                byte[] bArr = this.buffer;
                long j2 = this.pos;
                this.pos = j2 - 1;
                UnsafeUtil.B(bArr, j2, (byte) charAt);
            }
            if (length == -1) {
                return;
            }
            while (length >= 0) {
                char charAt2 = str.charAt(length);
                if (charAt2 < 128) {
                    long j3 = this.pos;
                    if (j3 > this.offsetMinusOne) {
                        byte[] bArr2 = this.buffer;
                        this.pos = j3 - 1;
                        UnsafeUtil.B(bArr2, j3, (byte) charAt2);
                        length--;
                    }
                }
                if (charAt2 < 2048) {
                    long j4 = this.pos;
                    if (j4 > this.offset) {
                        byte[] bArr3 = this.buffer;
                        this.pos = j4 - 1;
                        UnsafeUtil.B(bArr3, j4, (byte) ((charAt2 & '?') | 128));
                        byte[] bArr4 = this.buffer;
                        long j5 = this.pos;
                        this.pos = j5 - 1;
                        UnsafeUtil.B(bArr4, j5, (byte) ((charAt2 >>> 6) | 960));
                        length--;
                    }
                }
                if (charAt2 < 55296 || 57343 < charAt2) {
                    long j6 = this.pos;
                    if (j6 > this.offset + 1) {
                        byte[] bArr5 = this.buffer;
                        this.pos = j6 - 1;
                        UnsafeUtil.B(bArr5, j6, (byte) ((charAt2 & '?') | 128));
                        byte[] bArr6 = this.buffer;
                        long j7 = this.pos;
                        this.pos = j7 - 1;
                        UnsafeUtil.B(bArr6, j7, (byte) (((charAt2 >>> 6) & 63) | 128));
                        byte[] bArr7 = this.buffer;
                        long j8 = this.pos;
                        this.pos = j8 - 1;
                        UnsafeUtil.B(bArr7, j8, (byte) ((charAt2 >>> '\f') | 480));
                        length--;
                    }
                }
                if (this.pos > this.offset + 2) {
                    if (length != 0) {
                        char charAt3 = str.charAt(length - 1);
                        if (Character.isSurrogatePair(charAt3, charAt2)) {
                            length--;
                            int codePoint = Character.toCodePoint(charAt3, charAt2);
                            byte[] bArr8 = this.buffer;
                            long j9 = this.pos;
                            this.pos = j9 - 1;
                            UnsafeUtil.B(bArr8, j9, (byte) ((codePoint & 63) | 128));
                            byte[] bArr9 = this.buffer;
                            long j10 = this.pos;
                            this.pos = j10 - 1;
                            UnsafeUtil.B(bArr9, j10, (byte) (((codePoint >>> 6) & 63) | 128));
                            byte[] bArr10 = this.buffer;
                            long j11 = this.pos;
                            this.pos = j11 - 1;
                            UnsafeUtil.B(bArr10, j11, (byte) (((codePoint >>> 12) & 63) | 128));
                            byte[] bArr11 = this.buffer;
                            long j12 = this.pos;
                            this.pos = j12 - 1;
                            UnsafeUtil.B(bArr11, j12, (byte) ((codePoint >>> 18) | 240));
                        }
                    }
                    throw new Utf8.UnpairedSurrogateException(length - 1, length);
                }
                m(length);
                length++;
                length--;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte b2) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.B(bArr, j2, b2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            m(remaining);
            this.pos -= remaining;
            byteBuffer.get(this.buffer, arrayPos() + 1, remaining);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void write(byte[] bArr, int i2, int i3) {
            if (i2 < 0 || i2 + i3 > bArr.length) {
                throw new ArrayIndexOutOfBoundsException(String.format("value.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            m(i3);
            this.pos -= i3;
            System.arraycopy(bArr, i2, this.buffer, arrayPos() + 1, i3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBool(int i2, boolean z) {
            m(6);
            write(z ? (byte) 1 : (byte) 0);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeBytes(int i2, ByteString byteString) {
            try {
                byteString.p(this);
                m(10);
                u(byteString.size());
                writeTag(i2, 2);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeEndGroup(int i2) {
            writeTag(i2, 4);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed32(int i2, int i3) {
            m(9);
            o(i3);
            writeTag(i2, 5);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeFixed64(int i2, long j2) {
            m(13);
            p(j2);
            writeTag(i2, 1);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int i2, Object obj) {
            writeTag(i2, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeGroup(int i2, Object obj, Schema schema) {
            writeTag(i2, 4);
            schema.writeTo(obj, this);
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeInt32(int i2, int i3) {
            m(15);
            q(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (spaceLeft() < remaining) {
                this.f9724b += remaining;
                this.f9723a.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            }
            this.pos -= remaining;
            byteBuffer.get(this.buffer, arrayPos() + 1, remaining);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i2, int i3) {
            if (i2 < 0 || i2 + i3 > bArr.length) {
                throw new ArrayIndexOutOfBoundsException(String.format("value.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            if (spaceLeft() >= i3) {
                this.pos -= i3;
                System.arraycopy(bArr, i2, this.buffer, arrayPos() + 1, i3);
                return;
            }
            this.f9724b += i3;
            this.f9723a.addFirst(AllocatedBuffer.wrap(bArr, i2, i3));
            nextBuffer();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int i2, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeMessage(int i2, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt32(int i2, int i3) {
            m(10);
            s(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeSInt64(int i2, long j2) {
            m(15);
            t(j2);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeStartGroup(int i2) {
            writeTag(i2, 3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeString(int i2, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            w(str);
            m(10);
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryWriter
        void writeTag(int i2, int i3) {
            u(WireFormat.a(i2, i3));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt32(int i2, int i3) {
            m(10);
            u(i3);
            writeTag(i2, 0);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Writer
        public void writeUInt64(int i2, long j2) {
            m(15);
            v(j2);
            writeTag(i2, 0);
        }
    }

    private BinaryWriter(BufferAllocator bufferAllocator, int i2) {
        this.f9723a = new ArrayDeque(4);
        if (i2 <= 0) {
            throw new IllegalArgumentException("chunkSize must be > 0");
        }
        this.alloc = (BufferAllocator) Internal.b(bufferAllocator, "alloc");
        this.chunkSize = i2;
    }

    /* synthetic */ BinaryWriter(BufferAllocator bufferAllocator, int i2, AnonymousClass1 anonymousClass1) {
        this(bufferAllocator, i2);
    }

    static boolean c() {
        return UnsafeDirectWriter.isSupported();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte computeUInt64SizeNoTag(long j2) {
        byte b2;
        if (((-128) & j2) == 0) {
            return (byte) 1;
        }
        if (j2 < 0) {
            return (byte) 10;
        }
        if (((-34359738368L) & j2) != 0) {
            b2 = (byte) 6;
            j2 >>>= 28;
        } else {
            b2 = 2;
        }
        if (((-2097152) & j2) != 0) {
            b2 = (byte) (b2 + 2);
            j2 >>>= 14;
        }
        return (j2 & (-16384)) != 0 ? (byte) (b2 + 1) : b2;
    }

    static boolean d() {
        return UnsafeHeapWriter.isSupported();
    }

    static BinaryWriter i(BufferAllocator bufferAllocator, int i2) {
        return new SafeDirectWriter(bufferAllocator, i2);
    }

    static BinaryWriter j(BufferAllocator bufferAllocator, int i2) {
        return new SafeHeapWriter(bufferAllocator, i2);
    }

    static BinaryWriter k(BufferAllocator bufferAllocator, int i2) {
        if (c()) {
            return new UnsafeDirectWriter(bufferAllocator, i2);
        }
        throw new UnsupportedOperationException("Unsafe operations not supported");
    }

    static BinaryWriter l(BufferAllocator bufferAllocator, int i2) {
        if (d()) {
            return new UnsafeHeapWriter(bufferAllocator, i2);
        }
        throw new UnsupportedOperationException("Unsafe operations not supported");
    }

    public static BinaryWriter newDirectInstance(BufferAllocator bufferAllocator) {
        return newDirectInstance(bufferAllocator, 4096);
    }

    public static BinaryWriter newDirectInstance(BufferAllocator bufferAllocator, int i2) {
        return c() ? k(bufferAllocator, i2) : i(bufferAllocator, i2);
    }

    public static BinaryWriter newHeapInstance(BufferAllocator bufferAllocator) {
        return newHeapInstance(bufferAllocator, 4096);
    }

    public static BinaryWriter newHeapInstance(BufferAllocator bufferAllocator, int i2) {
        return d() ? l(bufferAllocator, i2) : j(bufferAllocator, i2);
    }

    static final void r(Writer writer, int i2, WireFormat.FieldType fieldType, Object obj) {
        int intValue;
        switch (AnonymousClass1.f9725a[fieldType.ordinal()]) {
            case 1:
                writer.writeBool(i2, ((Boolean) obj).booleanValue());
                return;
            case 2:
                writer.writeFixed32(i2, ((Integer) obj).intValue());
                return;
            case 3:
                writer.writeFixed64(i2, ((Long) obj).longValue());
                return;
            case 4:
                writer.writeInt32(i2, ((Integer) obj).intValue());
                return;
            case 5:
                writer.writeInt64(i2, ((Long) obj).longValue());
                return;
            case 6:
                writer.writeSFixed32(i2, ((Integer) obj).intValue());
                return;
            case 7:
                writer.writeSFixed64(i2, ((Long) obj).longValue());
                return;
            case 8:
                writer.writeSInt32(i2, ((Integer) obj).intValue());
                return;
            case 9:
                writer.writeSInt64(i2, ((Long) obj).longValue());
                return;
            case 10:
                writer.writeString(i2, (String) obj);
                return;
            case 11:
                writer.writeUInt32(i2, ((Integer) obj).intValue());
                return;
            case 12:
                writer.writeUInt64(i2, ((Long) obj).longValue());
                return;
            case 13:
                writer.writeFloat(i2, ((Float) obj).floatValue());
                return;
            case 14:
                writer.writeDouble(i2, ((Double) obj).doubleValue());
                return;
            case 15:
                writer.writeMessage(i2, obj);
                return;
            case 16:
                writer.writeBytes(i2, (ByteString) obj);
                return;
            case 17:
                if (obj instanceof Internal.EnumLite) {
                    intValue = ((Internal.EnumLite) obj).getNumber();
                } else if (!(obj instanceof Integer)) {
                    throw new IllegalArgumentException("Unexpected type for enum in map.");
                } else {
                    intValue = ((Integer) obj).intValue();
                }
                writer.writeEnum(i2, intValue);
                return;
            default:
                throw new IllegalArgumentException("Unsupported map value type for: " + fieldType);
        }
    }

    private final void writeBoolList_Internal(int i2, BooleanArrayList booleanArrayList, boolean z) {
        if (!z) {
            for (int size = booleanArrayList.size() - 1; size >= 0; size--) {
                writeBool(i2, booleanArrayList.getBoolean(size));
            }
            return;
        }
        m(booleanArrayList.size() + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = booleanArrayList.size() - 1; size2 >= 0; size2--) {
            n(booleanArrayList.getBoolean(size2));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeBoolList_Internal(int i2, List<Boolean> list, boolean z) {
        if (!z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeBool(i2, list.get(size).booleanValue());
            }
            return;
        }
        m(list.size() + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            n(list.get(size2).booleanValue());
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeDoubleList_Internal(int i2, DoubleArrayList doubleArrayList, boolean z) {
        if (!z) {
            for (int size = doubleArrayList.size() - 1; size >= 0; size--) {
                writeDouble(i2, doubleArrayList.getDouble(size));
            }
            return;
        }
        m((doubleArrayList.size() * 8) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = doubleArrayList.size() - 1; size2 >= 0; size2--) {
            p(Double.doubleToRawLongBits(doubleArrayList.getDouble(size2)));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeDoubleList_Internal(int i2, List<Double> list, boolean z) {
        if (!z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeDouble(i2, list.get(size).doubleValue());
            }
            return;
        }
        m((list.size() * 8) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            p(Double.doubleToRawLongBits(list.get(size2).doubleValue()));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeFixed32List_Internal(int i2, IntArrayList intArrayList, boolean z) {
        if (!z) {
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeFixed32(i2, intArrayList.getInt(size));
            }
            return;
        }
        m((intArrayList.size() * 4) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            o(intArrayList.getInt(size2));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeFixed32List_Internal(int i2, List<Integer> list, boolean z) {
        if (!z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeFixed32(i2, list.get(size).intValue());
            }
            return;
        }
        m((list.size() * 4) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            o(list.get(size2).intValue());
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeFixed64List_Internal(int i2, LongArrayList longArrayList, boolean z) {
        if (!z) {
            for (int size = longArrayList.size() - 1; size >= 0; size--) {
                writeFixed64(i2, longArrayList.getLong(size));
            }
            return;
        }
        m((longArrayList.size() * 8) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = longArrayList.size() - 1; size2 >= 0; size2--) {
            p(longArrayList.getLong(size2));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeFixed64List_Internal(int i2, List<Long> list, boolean z) {
        if (!z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeFixed64(i2, list.get(size).longValue());
            }
            return;
        }
        m((list.size() * 8) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            p(list.get(size2).longValue());
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeFloatList_Internal(int i2, FloatArrayList floatArrayList, boolean z) {
        if (!z) {
            for (int size = floatArrayList.size() - 1; size >= 0; size--) {
                writeFloat(i2, floatArrayList.getFloat(size));
            }
            return;
        }
        m((floatArrayList.size() * 4) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = floatArrayList.size() - 1; size2 >= 0; size2--) {
            o(Float.floatToRawIntBits(floatArrayList.getFloat(size2)));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeFloatList_Internal(int i2, List<Float> list, boolean z) {
        if (!z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeFloat(i2, list.get(size).floatValue());
            }
            return;
        }
        m((list.size() * 4) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            o(Float.floatToRawIntBits(list.get(size2).floatValue()));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeInt32List_Internal(int i2, IntArrayList intArrayList, boolean z) {
        if (!z) {
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeInt32(i2, intArrayList.getInt(size));
            }
            return;
        }
        m((intArrayList.size() * 10) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            q(intArrayList.getInt(size2));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeInt32List_Internal(int i2, List<Integer> list, boolean z) {
        if (!z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeInt32(i2, list.get(size).intValue());
            }
            return;
        }
        m((list.size() * 10) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            q(list.get(size2).intValue());
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private void writeLazyString(int i2, Object obj) {
        if (obj instanceof String) {
            writeString(i2, (String) obj);
        } else {
            writeBytes(i2, (ByteString) obj);
        }
    }

    private final void writeSInt32List_Internal(int i2, IntArrayList intArrayList, boolean z) {
        if (!z) {
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeSInt32(i2, intArrayList.getInt(size));
            }
            return;
        }
        m((intArrayList.size() * 5) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            s(intArrayList.getInt(size2));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeSInt32List_Internal(int i2, List<Integer> list, boolean z) {
        if (!z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeSInt32(i2, list.get(size).intValue());
            }
            return;
        }
        m((list.size() * 5) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            s(list.get(size2).intValue());
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeSInt64List_Internal(int i2, LongArrayList longArrayList, boolean z) {
        if (!z) {
            for (int size = longArrayList.size() - 1; size >= 0; size--) {
                writeSInt64(i2, longArrayList.getLong(size));
            }
            return;
        }
        m((longArrayList.size() * 10) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = longArrayList.size() - 1; size2 >= 0; size2--) {
            t(longArrayList.getLong(size2));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeSInt64List_Internal(int i2, List<Long> list, boolean z) {
        if (!z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeSInt64(i2, list.get(size).longValue());
            }
            return;
        }
        m((list.size() * 10) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            t(list.get(size2).longValue());
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeUInt32List_Internal(int i2, IntArrayList intArrayList, boolean z) {
        if (!z) {
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeUInt32(i2, intArrayList.getInt(size));
            }
            return;
        }
        m((intArrayList.size() * 5) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            u(intArrayList.getInt(size2));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeUInt32List_Internal(int i2, List<Integer> list, boolean z) {
        if (!z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeUInt32(i2, list.get(size).intValue());
            }
            return;
        }
        m((list.size() * 5) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            u(list.get(size2).intValue());
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeUInt64List_Internal(int i2, LongArrayList longArrayList, boolean z) {
        if (!z) {
            for (int size = longArrayList.size() - 1; size >= 0; size--) {
                writeUInt64(i2, longArrayList.getLong(size));
            }
            return;
        }
        m((longArrayList.size() * 10) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = longArrayList.size() - 1; size2 >= 0; size2--) {
            v(longArrayList.getLong(size2));
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    private final void writeUInt64List_Internal(int i2, List<Long> list, boolean z) {
        if (!z) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeUInt64(i2, list.get(size).longValue());
            }
            return;
        }
        m((list.size() * 10) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            v(list.get(size2).longValue());
        }
        u(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i2, 2);
    }

    abstract void b();

    public final Queue<AllocatedBuffer> complete() {
        b();
        return this.f9723a;
    }

    final AllocatedBuffer e() {
        return this.alloc.allocateDirectBuffer(this.chunkSize);
    }

    final AllocatedBuffer f(int i2) {
        return this.alloc.allocateDirectBuffer(Math.max(i2, this.chunkSize));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final Writer.FieldOrder fieldOrder() {
        return Writer.FieldOrder.DESCENDING;
    }

    final AllocatedBuffer g() {
        return this.alloc.allocateHeapBuffer(this.chunkSize);
    }

    public abstract int getTotalBytesWritten();

    final AllocatedBuffer h(int i2) {
        return this.alloc.allocateHeapBuffer(Math.max(i2, this.chunkSize));
    }

    abstract void m(int i2);

    abstract void n(boolean z);

    abstract void o(int i2);

    abstract void p(long j2);

    abstract void q(int i2);

    abstract void s(int i2);

    abstract void t(long j2);

    abstract void u(int i2);

    abstract void v(long j2);

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeBoolList(int i2, List<Boolean> list, boolean z) {
        if (list instanceof BooleanArrayList) {
            writeBoolList_Internal(i2, (BooleanArrayList) list, z);
        } else {
            writeBoolList_Internal(i2, list, z);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeBytesList(int i2, List<ByteString> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeBytes(i2, list.get(size));
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeDouble(int i2, double d2) {
        writeFixed64(i2, Double.doubleToRawLongBits(d2));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeDoubleList(int i2, List<Double> list, boolean z) {
        if (list instanceof DoubleArrayList) {
            writeDoubleList_Internal(i2, (DoubleArrayList) list, z);
        } else {
            writeDoubleList_Internal(i2, list, z);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeEnum(int i2, int i3) {
        writeInt32(i2, i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeEnumList(int i2, List<Integer> list, boolean z) {
        writeInt32List(i2, list, z);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeFixed32List(int i2, List<Integer> list, boolean z) {
        if (list instanceof IntArrayList) {
            writeFixed32List_Internal(i2, (IntArrayList) list, z);
        } else {
            writeFixed32List_Internal(i2, list, z);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeFixed64List(int i2, List<Long> list, boolean z) {
        if (list instanceof LongArrayList) {
            writeFixed64List_Internal(i2, (LongArrayList) list, z);
        } else {
            writeFixed64List_Internal(i2, list, z);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeFloat(int i2, float f2) {
        writeFixed32(i2, Float.floatToRawIntBits(f2));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeFloatList(int i2, List<Float> list, boolean z) {
        if (list instanceof FloatArrayList) {
            writeFloatList_Internal(i2, (FloatArrayList) list, z);
        } else {
            writeFloatList_Internal(i2, list, z);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeGroupList(int i2, List<?> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeGroup(i2, list.get(size));
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeGroupList(int i2, List<?> list, Schema schema) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeGroup(i2, list.get(size), schema);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeInt32List(int i2, List<Integer> list, boolean z) {
        if (list instanceof IntArrayList) {
            writeInt32List_Internal(i2, (IntArrayList) list, z);
        } else {
            writeInt32List_Internal(i2, list, z);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeInt64(int i2, long j2) {
        writeUInt64(i2, j2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeInt64List(int i2, List<Long> list, boolean z) {
        writeUInt64List(i2, list, z);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public <K, V> void writeMap(int i2, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            int totalBytesWritten = getTotalBytesWritten();
            r(this, 2, metadata.valueType, entry.getValue());
            r(this, 1, metadata.keyType, entry.getKey());
            u(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i2, 2);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeMessageList(int i2, List<?> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeMessage(i2, list.get(size));
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeMessageList(int i2, List<?> list, Schema schema) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeMessage(i2, list.get(size), schema);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeMessageSetItem(int i2, Object obj) {
        writeTag(1, 4);
        if (obj instanceof ByteString) {
            writeBytes(3, (ByteString) obj);
        } else {
            writeMessage(3, obj);
        }
        writeUInt32(2, i2);
        writeTag(1, 3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSFixed32(int i2, int i3) {
        writeFixed32(i2, i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSFixed32List(int i2, List<Integer> list, boolean z) {
        writeFixed32List(i2, list, z);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSFixed64(int i2, long j2) {
        writeFixed64(i2, j2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSFixed64List(int i2, List<Long> list, boolean z) {
        writeFixed64List(i2, list, z);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSInt32List(int i2, List<Integer> list, boolean z) {
        if (list instanceof IntArrayList) {
            writeSInt32List_Internal(i2, (IntArrayList) list, z);
        } else {
            writeSInt32List_Internal(i2, list, z);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeSInt64List(int i2, List<Long> list, boolean z) {
        if (list instanceof LongArrayList) {
            writeSInt64List_Internal(i2, (LongArrayList) list, z);
        } else {
            writeSInt64List_Internal(i2, list, z);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeStringList(int i2, List<String> list) {
        if (!(list instanceof LazyStringList)) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeString(i2, list.get(size));
            }
            return;
        }
        LazyStringList lazyStringList = (LazyStringList) list;
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeLazyString(i2, lazyStringList.getRaw(size2));
        }
    }

    abstract void writeTag(int i2, int i3);

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeUInt32List(int i2, List<Integer> list, boolean z) {
        if (list instanceof IntArrayList) {
            writeUInt32List_Internal(i2, (IntArrayList) list, z);
        } else {
            writeUInt32List_Internal(i2, list, z);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeUInt64List(int i2, List<Long> list, boolean z) {
        if (list instanceof LongArrayList) {
            writeUInt64List_Internal(i2, (LongArrayList) list, z);
        } else {
            writeUInt64List_Internal(i2, list, z);
        }
    }
}
