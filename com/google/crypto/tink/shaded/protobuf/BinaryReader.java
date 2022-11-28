package com.google.crypto.tink.shaded.protobuf;

import com.google.common.base.Ascii;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
abstract class BinaryReader implements Reader {
    private static final int FIXED32_MULTIPLE_MASK = 3;
    private static final int FIXED64_MULTIPLE_MASK = 7;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.shaded.protobuf.BinaryReader$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9722a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f9722a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9722a[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9722a[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9722a[WireFormat.FieldType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9722a[WireFormat.FieldType.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9722a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9722a[WireFormat.FieldType.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9722a[WireFormat.FieldType.INT32.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9722a[WireFormat.FieldType.INT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9722a[WireFormat.FieldType.MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9722a[WireFormat.FieldType.SFIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9722a[WireFormat.FieldType.SFIXED64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f9722a[WireFormat.FieldType.SINT32.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f9722a[WireFormat.FieldType.SINT64.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f9722a[WireFormat.FieldType.STRING.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f9722a[WireFormat.FieldType.UINT32.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f9722a[WireFormat.FieldType.UINT64.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* loaded from: classes2.dex */
    private static final class SafeHeapReader extends BinaryReader {
        private final byte[] buffer;
        private final boolean bufferIsImmutable;
        private int endGroupTag;
        private final int initialPos;
        private int limit;
        private int pos;
        private int tag;

        public SafeHeapReader(ByteBuffer byteBuffer, boolean z) {
            super(null);
            this.bufferIsImmutable = z;
            this.buffer = byteBuffer.array();
            int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
            this.pos = arrayOffset;
            this.initialPos = arrayOffset;
            this.limit = byteBuffer.arrayOffset() + byteBuffer.limit();
        }

        private boolean isAtEnd() {
            return this.pos == this.limit;
        }

        private byte readByte() {
            int i2 = this.pos;
            if (i2 != this.limit) {
                byte[] bArr = this.buffer;
                this.pos = i2 + 1;
                return bArr[i2];
            }
            throw InvalidProtocolBufferException.j();
        }

        private Object readField(WireFormat.FieldType fieldType, Class<?> cls, ExtensionRegistryLite extensionRegistryLite) {
            switch (AnonymousClass1.f9722a[fieldType.ordinal()]) {
                case 1:
                    return Boolean.valueOf(readBool());
                case 2:
                    return readBytes();
                case 3:
                    return Double.valueOf(readDouble());
                case 4:
                    return Integer.valueOf(readEnum());
                case 5:
                    return Integer.valueOf(readFixed32());
                case 6:
                    return Long.valueOf(readFixed64());
                case 7:
                    return Float.valueOf(readFloat());
                case 8:
                    return Integer.valueOf(readInt32());
                case 9:
                    return Long.valueOf(readInt64());
                case 10:
                    return readMessage(cls, extensionRegistryLite);
                case 11:
                    return Integer.valueOf(readSFixed32());
                case 12:
                    return Long.valueOf(readSFixed64());
                case 13:
                    return Integer.valueOf(readSInt32());
                case 14:
                    return Long.valueOf(readSInt64());
                case 15:
                    return readStringRequireUtf8();
                case 16:
                    return Integer.valueOf(readUInt32());
                case 17:
                    return Long.valueOf(readUInt64());
                default:
                    throw new RuntimeException("unsupported field type.");
            }
        }

        private <T> T readGroup(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) {
            int i2 = this.endGroupTag;
            this.endGroupTag = WireFormat.a(WireFormat.getTagFieldNumber(this.tag), 4);
            try {
                T newInstance = schema.newInstance();
                schema.mergeFrom(newInstance, this, extensionRegistryLite);
                schema.makeImmutable(newInstance);
                if (this.tag == this.endGroupTag) {
                    return newInstance;
                }
                throw InvalidProtocolBufferException.g();
            } finally {
                this.endGroupTag = i2;
            }
        }

        private int readLittleEndian32() {
            requireBytes(4);
            return readLittleEndian32_NoCheck();
        }

        private int readLittleEndian32_NoCheck() {
            int i2 = this.pos;
            byte[] bArr = this.buffer;
            this.pos = i2 + 4;
            return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
        }

        private long readLittleEndian64() {
            requireBytes(8);
            return readLittleEndian64_NoCheck();
        }

        private long readLittleEndian64_NoCheck() {
            int i2 = this.pos;
            byte[] bArr = this.buffer;
            this.pos = i2 + 8;
            return ((bArr[i2 + 7] & 255) << 56) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 4] & 255) << 32) | ((bArr[i2 + 5] & 255) << 40) | ((bArr[i2 + 6] & 255) << 48);
        }

        private <T> T readMessage(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) {
            int readVarint32 = readVarint32();
            requireBytes(readVarint32);
            int i2 = this.limit;
            int i3 = this.pos + readVarint32;
            this.limit = i3;
            try {
                T newInstance = schema.newInstance();
                schema.mergeFrom(newInstance, this, extensionRegistryLite);
                schema.makeImmutable(newInstance);
                if (this.pos == i3) {
                    return newInstance;
                }
                throw InvalidProtocolBufferException.g();
            } finally {
                this.limit = i2;
            }
        }

        private int readVarint32() {
            int i2;
            int i3 = this.pos;
            int i4 = this.limit;
            if (i4 != i3) {
                byte[] bArr = this.buffer;
                int i5 = i3 + 1;
                byte b2 = bArr[i3];
                if (b2 >= 0) {
                    this.pos = i5;
                    return b2;
                } else if (i4 - i5 < 9) {
                    return (int) readVarint64SlowPath();
                } else {
                    int i6 = i5 + 1;
                    int i7 = b2 ^ (bArr[i5] << 7);
                    if (i7 < 0) {
                        i2 = i7 ^ (-128);
                    } else {
                        int i8 = i6 + 1;
                        int i9 = i7 ^ (bArr[i6] << Ascii.SO);
                        if (i9 >= 0) {
                            i2 = i9 ^ 16256;
                        } else {
                            i6 = i8 + 1;
                            int i10 = i9 ^ (bArr[i8] << Ascii.NAK);
                            if (i10 < 0) {
                                i2 = i10 ^ (-2080896);
                            } else {
                                i8 = i6 + 1;
                                byte b3 = bArr[i6];
                                i2 = (i10 ^ (b3 << Ascii.FS)) ^ 266354560;
                                if (b3 < 0) {
                                    i6 = i8 + 1;
                                    if (bArr[i8] < 0) {
                                        i8 = i6 + 1;
                                        if (bArr[i6] < 0) {
                                            i6 = i8 + 1;
                                            if (bArr[i8] < 0) {
                                                i8 = i6 + 1;
                                                if (bArr[i6] < 0) {
                                                    i6 = i8 + 1;
                                                    if (bArr[i8] < 0) {
                                                        throw InvalidProtocolBufferException.e();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        i6 = i8;
                    }
                    this.pos = i6;
                    return i2;
                }
            }
            throw InvalidProtocolBufferException.j();
        }

        private long readVarint64SlowPath() {
            long j2 = 0;
            for (int i2 = 0; i2 < 64; i2 += 7) {
                byte readByte = readByte();
                j2 |= (readByte & Byte.MAX_VALUE) << i2;
                if ((readByte & 128) == 0) {
                    return j2;
                }
            }
            throw InvalidProtocolBufferException.e();
        }

        private void requireBytes(int i2) {
            if (i2 < 0 || i2 > this.limit - this.pos) {
                throw InvalidProtocolBufferException.j();
            }
        }

        private void requirePosition(int i2) {
            if (this.pos != i2) {
                throw InvalidProtocolBufferException.j();
            }
        }

        private void requireWireType(int i2) {
            if (WireFormat.getTagWireType(this.tag) != i2) {
                throw InvalidProtocolBufferException.d();
            }
        }

        private void skipBytes(int i2) {
            requireBytes(i2);
            this.pos += i2;
        }

        private void skipGroup() {
            int i2 = this.endGroupTag;
            this.endGroupTag = WireFormat.a(WireFormat.getTagFieldNumber(this.tag), 4);
            while (getFieldNumber() != Integer.MAX_VALUE && skipField()) {
            }
            if (this.tag != this.endGroupTag) {
                throw InvalidProtocolBufferException.g();
            }
            this.endGroupTag = i2;
        }

        private void skipVarint() {
            int i2 = this.limit;
            int i3 = this.pos;
            if (i2 - i3 >= 10) {
                byte[] bArr = this.buffer;
                int i4 = 0;
                while (i4 < 10) {
                    int i5 = i3 + 1;
                    if (bArr[i3] >= 0) {
                        this.pos = i5;
                        return;
                    } else {
                        i4++;
                        i3 = i5;
                    }
                }
            }
            skipVarintSlowPath();
        }

        private void skipVarintSlowPath() {
            for (int i2 = 0; i2 < 10; i2++) {
                if (readByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.e();
        }

        private void verifyPackedFixed32Length(int i2) {
            requireBytes(i2);
            if ((i2 & 3) != 0) {
                throw InvalidProtocolBufferException.g();
            }
        }

        private void verifyPackedFixed64Length(int i2) {
            requireBytes(i2);
            if ((i2 & 7) != 0) {
                throw InvalidProtocolBufferException.g();
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public int getFieldNumber() {
            if (isAtEnd()) {
                return Integer.MAX_VALUE;
            }
            int readVarint32 = readVarint32();
            this.tag = readVarint32;
            if (readVarint32 == this.endGroupTag) {
                return Integer.MAX_VALUE;
            }
            return WireFormat.getTagFieldNumber(readVarint32);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public int getTag() {
            return this.tag;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BinaryReader
        public int getTotalBytesRead() {
            return this.pos - this.initialPos;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public boolean readBool() {
            requireWireType(0);
            return readVarint32() != 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readBoolList(List<Boolean> list) {
            int i2;
            int readVarint32;
            int i3;
            if (list instanceof BooleanArrayList) {
                BooleanArrayList booleanArrayList = (BooleanArrayList) list;
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 0) {
                    do {
                        booleanArrayList.addBoolean(readBool());
                        if (isAtEnd()) {
                            return;
                        }
                        i3 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i3;
                    return;
                } else if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        booleanArrayList.addBoolean(readVarint32() != 0);
                    }
                }
            } else {
                int tagWireType2 = WireFormat.getTagWireType(this.tag);
                if (tagWireType2 == 0) {
                    do {
                        list.add(Boolean.valueOf(readBool()));
                        if (isAtEnd()) {
                            return;
                        }
                        i2 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i2;
                    return;
                } else if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        list.add(Boolean.valueOf(readVarint32() != 0));
                    }
                }
            }
            requirePosition(readVarint32);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public ByteString readBytes() {
            requireWireType(2);
            int readVarint32 = readVarint32();
            if (readVarint32 == 0) {
                return ByteString.EMPTY;
            }
            requireBytes(readVarint32);
            ByteString n2 = this.bufferIsImmutable ? ByteString.n(this.buffer, this.pos, readVarint32) : ByteString.copyFrom(this.buffer, this.pos, readVarint32);
            this.pos += readVarint32;
            return n2;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readBytesList(List<ByteString> list) {
            int i2;
            if (WireFormat.getTagWireType(this.tag) != 2) {
                throw InvalidProtocolBufferException.d();
            }
            do {
                list.add(readBytes());
                if (isAtEnd()) {
                    return;
                }
                i2 = this.pos;
            } while (readVarint32() == this.tag);
            this.pos = i2;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public double readDouble() {
            requireWireType(1);
            return Double.longBitsToDouble(readLittleEndian64());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readDoubleList(List<Double> list) {
            int i2;
            int i3;
            if (!(list instanceof DoubleArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 1) {
                    do {
                        list.add(Double.valueOf(readDouble()));
                        if (isAtEnd()) {
                            return;
                        }
                        i2 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i2;
                    return;
                } else if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    int readVarint32 = readVarint32();
                    verifyPackedFixed64Length(readVarint32);
                    int i4 = this.pos + readVarint32;
                    while (this.pos < i4) {
                        list.add(Double.valueOf(Double.longBitsToDouble(readLittleEndian64_NoCheck())));
                    }
                    return;
                }
            }
            DoubleArrayList doubleArrayList = (DoubleArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 1) {
                do {
                    doubleArrayList.addDouble(readDouble());
                    if (isAtEnd()) {
                        return;
                    }
                    i3 = this.pos;
                } while (readVarint32() == this.tag);
                this.pos = i3;
            } else if (tagWireType2 != 2) {
                throw InvalidProtocolBufferException.d();
            } else {
                int readVarint322 = readVarint32();
                verifyPackedFixed64Length(readVarint322);
                int i5 = this.pos + readVarint322;
                while (this.pos < i5) {
                    doubleArrayList.addDouble(Double.longBitsToDouble(readLittleEndian64_NoCheck()));
                }
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public int readEnum() {
            requireWireType(0);
            return readVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readEnumList(List<Integer> list) {
            int i2;
            int i3;
            if (!(list instanceof IntArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType != 0) {
                    if (tagWireType != 2) {
                        throw InvalidProtocolBufferException.d();
                    }
                    int readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        list.add(Integer.valueOf(readVarint32()));
                    }
                    return;
                }
                do {
                    list.add(Integer.valueOf(readEnum()));
                    if (isAtEnd()) {
                        return;
                    }
                    i2 = this.pos;
                } while (readVarint32() == this.tag);
                this.pos = i2;
                return;
            }
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 != 0) {
                if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.d();
                }
                int readVarint322 = this.pos + readVarint32();
                while (this.pos < readVarint322) {
                    intArrayList.addInt(readVarint32());
                }
                return;
            }
            do {
                intArrayList.addInt(readEnum());
                if (isAtEnd()) {
                    return;
                }
                i3 = this.pos;
            } while (readVarint32() == this.tag);
            this.pos = i3;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public int readFixed32() {
            requireWireType(5);
            return readLittleEndian32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readFixed32List(List<Integer> list) {
            int i2;
            int i3;
            if (!(list instanceof IntArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 2) {
                    int readVarint32 = readVarint32();
                    verifyPackedFixed32Length(readVarint32);
                    int i4 = this.pos + readVarint32;
                    while (this.pos < i4) {
                        list.add(Integer.valueOf(readLittleEndian32_NoCheck()));
                    }
                    return;
                } else if (tagWireType != 5) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    do {
                        list.add(Integer.valueOf(readFixed32()));
                        if (isAtEnd()) {
                            return;
                        }
                        i2 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i2;
                    return;
                }
            }
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 2) {
                int readVarint322 = readVarint32();
                verifyPackedFixed32Length(readVarint322);
                int i5 = this.pos + readVarint322;
                while (this.pos < i5) {
                    intArrayList.addInt(readLittleEndian32_NoCheck());
                }
            } else if (tagWireType2 != 5) {
                throw InvalidProtocolBufferException.d();
            } else {
                do {
                    intArrayList.addInt(readFixed32());
                    if (isAtEnd()) {
                        return;
                    }
                    i3 = this.pos;
                } while (readVarint32() == this.tag);
                this.pos = i3;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public long readFixed64() {
            requireWireType(1);
            return readLittleEndian64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readFixed64List(List<Long> list) {
            int i2;
            int i3;
            if (!(list instanceof LongArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 1) {
                    do {
                        list.add(Long.valueOf(readFixed64()));
                        if (isAtEnd()) {
                            return;
                        }
                        i2 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i2;
                    return;
                } else if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    int readVarint32 = readVarint32();
                    verifyPackedFixed64Length(readVarint32);
                    int i4 = this.pos + readVarint32;
                    while (this.pos < i4) {
                        list.add(Long.valueOf(readLittleEndian64_NoCheck()));
                    }
                    return;
                }
            }
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 1) {
                do {
                    longArrayList.addLong(readFixed64());
                    if (isAtEnd()) {
                        return;
                    }
                    i3 = this.pos;
                } while (readVarint32() == this.tag);
                this.pos = i3;
            } else if (tagWireType2 != 2) {
                throw InvalidProtocolBufferException.d();
            } else {
                int readVarint322 = readVarint32();
                verifyPackedFixed64Length(readVarint322);
                int i5 = this.pos + readVarint322;
                while (this.pos < i5) {
                    longArrayList.addLong(readLittleEndian64_NoCheck());
                }
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public float readFloat() {
            requireWireType(5);
            return Float.intBitsToFloat(readLittleEndian32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readFloatList(List<Float> list) {
            int i2;
            int i3;
            if (!(list instanceof FloatArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 2) {
                    int readVarint32 = readVarint32();
                    verifyPackedFixed32Length(readVarint32);
                    int i4 = this.pos + readVarint32;
                    while (this.pos < i4) {
                        list.add(Float.valueOf(Float.intBitsToFloat(readLittleEndian32_NoCheck())));
                    }
                    return;
                } else if (tagWireType != 5) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    do {
                        list.add(Float.valueOf(readFloat()));
                        if (isAtEnd()) {
                            return;
                        }
                        i2 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i2;
                    return;
                }
            }
            FloatArrayList floatArrayList = (FloatArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 2) {
                int readVarint322 = readVarint32();
                verifyPackedFixed32Length(readVarint322);
                int i5 = this.pos + readVarint322;
                while (this.pos < i5) {
                    floatArrayList.addFloat(Float.intBitsToFloat(readLittleEndian32_NoCheck()));
                }
            } else if (tagWireType2 != 5) {
                throw InvalidProtocolBufferException.d();
            } else {
                do {
                    floatArrayList.addFloat(readFloat());
                    if (isAtEnd()) {
                        return;
                    }
                    i3 = this.pos;
                } while (readVarint32() == this.tag);
                this.pos = i3;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public <T> T readGroup(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) {
            requireWireType(3);
            return (T) readGroup(Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public <T> T readGroupBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) {
            requireWireType(3);
            return (T) readGroup(schema, extensionRegistryLite);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public <T> void readGroupList(List<T> list, Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) {
            int i2;
            if (WireFormat.getTagWireType(this.tag) != 3) {
                throw InvalidProtocolBufferException.d();
            }
            int i3 = this.tag;
            do {
                list.add(readGroup(schema, extensionRegistryLite));
                if (isAtEnd()) {
                    return;
                }
                i2 = this.pos;
            } while (readVarint32() == i3);
            this.pos = i2;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public <T> void readGroupList(List<T> list, Class<T> cls, ExtensionRegistryLite extensionRegistryLite) {
            readGroupList(list, Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public int readInt32() {
            requireWireType(0);
            return readVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readInt32List(List<Integer> list) {
            int i2;
            int readVarint32;
            int i3;
            if (list instanceof IntArrayList) {
                IntArrayList intArrayList = (IntArrayList) list;
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 0) {
                    do {
                        intArrayList.addInt(readInt32());
                        if (isAtEnd()) {
                            return;
                        }
                        i3 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i3;
                    return;
                } else if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        intArrayList.addInt(readVarint32());
                    }
                }
            } else {
                int tagWireType2 = WireFormat.getTagWireType(this.tag);
                if (tagWireType2 == 0) {
                    do {
                        list.add(Integer.valueOf(readInt32()));
                        if (isAtEnd()) {
                            return;
                        }
                        i2 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i2;
                    return;
                } else if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        list.add(Integer.valueOf(readVarint32()));
                    }
                }
            }
            requirePosition(readVarint32);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public long readInt64() {
            requireWireType(0);
            return readVarint64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readInt64List(List<Long> list) {
            int i2;
            int readVarint32;
            int i3;
            if (list instanceof LongArrayList) {
                LongArrayList longArrayList = (LongArrayList) list;
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 0) {
                    do {
                        longArrayList.addLong(readInt64());
                        if (isAtEnd()) {
                            return;
                        }
                        i3 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i3;
                    return;
                } else if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        longArrayList.addLong(readVarint64());
                    }
                }
            } else {
                int tagWireType2 = WireFormat.getTagWireType(this.tag);
                if (tagWireType2 == 0) {
                    do {
                        list.add(Long.valueOf(readInt64()));
                        if (isAtEnd()) {
                            return;
                        }
                        i2 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i2;
                    return;
                } else if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        list.add(Long.valueOf(readVarint64()));
                    }
                }
            }
            requirePosition(readVarint32);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public <K, V> void readMap(Map<K, V> map, MapEntryLite.Metadata<K, V> metadata, ExtensionRegistryLite extensionRegistryLite) {
            requireWireType(2);
            int readVarint32 = readVarint32();
            requireBytes(readVarint32);
            int i2 = this.limit;
            this.limit = this.pos + readVarint32;
            try {
                Object obj = metadata.defaultKey;
                Object obj2 = metadata.defaultValue;
                while (true) {
                    int fieldNumber = getFieldNumber();
                    if (fieldNumber == Integer.MAX_VALUE) {
                        map.put(obj, obj2);
                        return;
                    } else if (fieldNumber == 1) {
                        obj = readField(metadata.keyType, null, null);
                    } else if (fieldNumber != 2) {
                        try {
                            if (!skipField()) {
                                throw new InvalidProtocolBufferException("Unable to parse map entry.");
                                break;
                            }
                        } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                            if (!skipField()) {
                                throw new InvalidProtocolBufferException("Unable to parse map entry.");
                            }
                        }
                    } else {
                        obj2 = readField(metadata.valueType, metadata.defaultValue.getClass(), extensionRegistryLite);
                    }
                }
            } finally {
                this.limit = i2;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public <T> T readMessage(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) {
            requireWireType(2);
            return (T) readMessage(Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public <T> T readMessageBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) {
            requireWireType(2);
            return (T) readMessage(schema, extensionRegistryLite);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public <T> void readMessageList(List<T> list, Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) {
            int i2;
            if (WireFormat.getTagWireType(this.tag) != 2) {
                throw InvalidProtocolBufferException.d();
            }
            int i3 = this.tag;
            do {
                list.add(readMessage(schema, extensionRegistryLite));
                if (isAtEnd()) {
                    return;
                }
                i2 = this.pos;
            } while (readVarint32() == i3);
            this.pos = i2;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public <T> void readMessageList(List<T> list, Class<T> cls, ExtensionRegistryLite extensionRegistryLite) {
            readMessageList(list, Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public int readSFixed32() {
            requireWireType(5);
            return readLittleEndian32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readSFixed32List(List<Integer> list) {
            int i2;
            int i3;
            if (!(list instanceof IntArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 2) {
                    int readVarint32 = readVarint32();
                    verifyPackedFixed32Length(readVarint32);
                    int i4 = this.pos + readVarint32;
                    while (this.pos < i4) {
                        list.add(Integer.valueOf(readLittleEndian32_NoCheck()));
                    }
                    return;
                } else if (tagWireType != 5) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    do {
                        list.add(Integer.valueOf(readSFixed32()));
                        if (isAtEnd()) {
                            return;
                        }
                        i2 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i2;
                    return;
                }
            }
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 2) {
                int readVarint322 = readVarint32();
                verifyPackedFixed32Length(readVarint322);
                int i5 = this.pos + readVarint322;
                while (this.pos < i5) {
                    intArrayList.addInt(readLittleEndian32_NoCheck());
                }
            } else if (tagWireType2 != 5) {
                throw InvalidProtocolBufferException.d();
            } else {
                do {
                    intArrayList.addInt(readSFixed32());
                    if (isAtEnd()) {
                        return;
                    }
                    i3 = this.pos;
                } while (readVarint32() == this.tag);
                this.pos = i3;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public long readSFixed64() {
            requireWireType(1);
            return readLittleEndian64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readSFixed64List(List<Long> list) {
            int i2;
            int i3;
            if (!(list instanceof LongArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 1) {
                    do {
                        list.add(Long.valueOf(readSFixed64()));
                        if (isAtEnd()) {
                            return;
                        }
                        i2 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i2;
                    return;
                } else if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    int readVarint32 = readVarint32();
                    verifyPackedFixed64Length(readVarint32);
                    int i4 = this.pos + readVarint32;
                    while (this.pos < i4) {
                        list.add(Long.valueOf(readLittleEndian64_NoCheck()));
                    }
                    return;
                }
            }
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 == 1) {
                do {
                    longArrayList.addLong(readSFixed64());
                    if (isAtEnd()) {
                        return;
                    }
                    i3 = this.pos;
                } while (readVarint32() == this.tag);
                this.pos = i3;
            } else if (tagWireType2 != 2) {
                throw InvalidProtocolBufferException.d();
            } else {
                int readVarint322 = readVarint32();
                verifyPackedFixed64Length(readVarint322);
                int i5 = this.pos + readVarint322;
                while (this.pos < i5) {
                    longArrayList.addLong(readLittleEndian64_NoCheck());
                }
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public int readSInt32() {
            requireWireType(0);
            return CodedInputStream.decodeZigZag32(readVarint32());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readSInt32List(List<Integer> list) {
            int i2;
            int i3;
            if (!(list instanceof IntArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType != 0) {
                    if (tagWireType != 2) {
                        throw InvalidProtocolBufferException.d();
                    }
                    int readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        list.add(Integer.valueOf(CodedInputStream.decodeZigZag32(readVarint32())));
                    }
                    return;
                }
                do {
                    list.add(Integer.valueOf(readSInt32()));
                    if (isAtEnd()) {
                        return;
                    }
                    i2 = this.pos;
                } while (readVarint32() == this.tag);
                this.pos = i2;
                return;
            }
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 != 0) {
                if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.d();
                }
                int readVarint322 = this.pos + readVarint32();
                while (this.pos < readVarint322) {
                    intArrayList.addInt(CodedInputStream.decodeZigZag32(readVarint32()));
                }
                return;
            }
            do {
                intArrayList.addInt(readSInt32());
                if (isAtEnd()) {
                    return;
                }
                i3 = this.pos;
            } while (readVarint32() == this.tag);
            this.pos = i3;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public long readSInt64() {
            requireWireType(0);
            return CodedInputStream.decodeZigZag64(readVarint64());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readSInt64List(List<Long> list) {
            int i2;
            int i3;
            if (!(list instanceof LongArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType != 0) {
                    if (tagWireType != 2) {
                        throw InvalidProtocolBufferException.d();
                    }
                    int readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        list.add(Long.valueOf(CodedInputStream.decodeZigZag64(readVarint64())));
                    }
                    return;
                }
                do {
                    list.add(Long.valueOf(readSInt64()));
                    if (isAtEnd()) {
                        return;
                    }
                    i2 = this.pos;
                } while (readVarint32() == this.tag);
                this.pos = i2;
                return;
            }
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 != 0) {
                if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.d();
                }
                int readVarint322 = this.pos + readVarint32();
                while (this.pos < readVarint322) {
                    longArrayList.addLong(CodedInputStream.decodeZigZag64(readVarint64()));
                }
                return;
            }
            do {
                longArrayList.addLong(readSInt64());
                if (isAtEnd()) {
                    return;
                }
                i3 = this.pos;
            } while (readVarint32() == this.tag);
            this.pos = i3;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public String readString() {
            return readStringInternal(false);
        }

        public String readStringInternal(boolean z) {
            requireWireType(2);
            int readVarint32 = readVarint32();
            if (readVarint32 == 0) {
                return "";
            }
            requireBytes(readVarint32);
            if (z) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                if (!Utf8.isValidUtf8(bArr, i2, i2 + readVarint32)) {
                    throw InvalidProtocolBufferException.c();
                }
            }
            String str = new String(this.buffer, this.pos, readVarint32, Internal.f9762a);
            this.pos += readVarint32;
            return str;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readStringList(List<String> list) {
            readStringListInternal(list, false);
        }

        public void readStringListInternal(List<String> list, boolean z) {
            int i2;
            int i3;
            if (WireFormat.getTagWireType(this.tag) != 2) {
                throw InvalidProtocolBufferException.d();
            }
            if (!(list instanceof LazyStringList) || z) {
                do {
                    list.add(readStringInternal(z));
                    if (isAtEnd()) {
                        return;
                    }
                    i2 = this.pos;
                } while (readVarint32() == this.tag);
                this.pos = i2;
                return;
            }
            LazyStringList lazyStringList = (LazyStringList) list;
            do {
                lazyStringList.add(readBytes());
                if (isAtEnd()) {
                    return;
                }
                i3 = this.pos;
            } while (readVarint32() == this.tag);
            this.pos = i3;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readStringListRequireUtf8(List<String> list) {
            readStringListInternal(list, true);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public String readStringRequireUtf8() {
            return readStringInternal(true);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public int readUInt32() {
            requireWireType(0);
            return readVarint32();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readUInt32List(List<Integer> list) {
            int i2;
            int i3;
            if (!(list instanceof IntArrayList)) {
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType != 0) {
                    if (tagWireType != 2) {
                        throw InvalidProtocolBufferException.d();
                    }
                    int readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        list.add(Integer.valueOf(readVarint32()));
                    }
                    return;
                }
                do {
                    list.add(Integer.valueOf(readUInt32()));
                    if (isAtEnd()) {
                        return;
                    }
                    i2 = this.pos;
                } while (readVarint32() == this.tag);
                this.pos = i2;
                return;
            }
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType2 = WireFormat.getTagWireType(this.tag);
            if (tagWireType2 != 0) {
                if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.d();
                }
                int readVarint322 = this.pos + readVarint32();
                while (this.pos < readVarint322) {
                    intArrayList.addInt(readVarint32());
                }
                return;
            }
            do {
                intArrayList.addInt(readUInt32());
                if (isAtEnd()) {
                    return;
                }
                i3 = this.pos;
            } while (readVarint32() == this.tag);
            this.pos = i3;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public long readUInt64() {
            requireWireType(0);
            return readVarint64();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public void readUInt64List(List<Long> list) {
            int i2;
            int readVarint32;
            int i3;
            if (list instanceof LongArrayList) {
                LongArrayList longArrayList = (LongArrayList) list;
                int tagWireType = WireFormat.getTagWireType(this.tag);
                if (tagWireType == 0) {
                    do {
                        longArrayList.addLong(readUInt64());
                        if (isAtEnd()) {
                            return;
                        }
                        i3 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i3;
                    return;
                } else if (tagWireType != 2) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        longArrayList.addLong(readVarint64());
                    }
                }
            } else {
                int tagWireType2 = WireFormat.getTagWireType(this.tag);
                if (tagWireType2 == 0) {
                    do {
                        list.add(Long.valueOf(readUInt64()));
                        if (isAtEnd()) {
                            return;
                        }
                        i2 = this.pos;
                    } while (readVarint32() == this.tag);
                    this.pos = i2;
                    return;
                } else if (tagWireType2 != 2) {
                    throw InvalidProtocolBufferException.d();
                } else {
                    readVarint32 = this.pos + readVarint32();
                    while (this.pos < readVarint32) {
                        list.add(Long.valueOf(readVarint64()));
                    }
                }
            }
            requirePosition(readVarint32);
        }

        public long readVarint64() {
            long j2;
            long j3;
            long j4;
            int i2;
            int i3 = this.pos;
            int i4 = this.limit;
            if (i4 != i3) {
                byte[] bArr = this.buffer;
                int i5 = i3 + 1;
                byte b2 = bArr[i3];
                if (b2 >= 0) {
                    this.pos = i5;
                    return b2;
                } else if (i4 - i5 < 9) {
                    return readVarint64SlowPath();
                } else {
                    int i6 = i5 + 1;
                    int i7 = b2 ^ (bArr[i5] << 7);
                    if (i7 >= 0) {
                        int i8 = i6 + 1;
                        int i9 = i7 ^ (bArr[i6] << Ascii.SO);
                        if (i9 >= 0) {
                            i6 = i8;
                            j2 = i9 ^ 16256;
                        } else {
                            i6 = i8 + 1;
                            int i10 = i9 ^ (bArr[i8] << Ascii.NAK);
                            if (i10 < 0) {
                                i2 = i10 ^ (-2080896);
                            } else {
                                long j5 = i10;
                                int i11 = i6 + 1;
                                long j6 = j5 ^ (bArr[i6] << 28);
                                if (j6 >= 0) {
                                    j4 = 266354560;
                                } else {
                                    i6 = i11 + 1;
                                    long j7 = j6 ^ (bArr[i11] << 35);
                                    if (j7 < 0) {
                                        j3 = -34093383808L;
                                    } else {
                                        i11 = i6 + 1;
                                        j6 = j7 ^ (bArr[i6] << 42);
                                        if (j6 >= 0) {
                                            j4 = 4363953127296L;
                                        } else {
                                            i6 = i11 + 1;
                                            j7 = j6 ^ (bArr[i11] << 49);
                                            if (j7 < 0) {
                                                j3 = -558586000294016L;
                                            } else {
                                                int i12 = i6 + 1;
                                                long j8 = (j7 ^ (bArr[i6] << 56)) ^ 71499008037633920L;
                                                if (j8 < 0) {
                                                    i6 = i12 + 1;
                                                    if (bArr[i12] < 0) {
                                                        throw InvalidProtocolBufferException.e();
                                                    }
                                                } else {
                                                    i6 = i12;
                                                }
                                                j2 = j8;
                                            }
                                        }
                                    }
                                    j2 = j7 ^ j3;
                                }
                                j2 = j6 ^ j4;
                                i6 = i11;
                            }
                        }
                        this.pos = i6;
                        return j2;
                    }
                    i2 = i7 ^ (-128);
                    j2 = i2;
                    this.pos = i6;
                    return j2;
                }
            }
            throw InvalidProtocolBufferException.j();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Reader
        public boolean skipField() {
            int i2;
            int i3;
            if (isAtEnd() || (i2 = this.tag) == this.endGroupTag) {
                return false;
            }
            int tagWireType = WireFormat.getTagWireType(i2);
            if (tagWireType == 0) {
                skipVarint();
                return true;
            }
            if (tagWireType == 1) {
                i3 = 8;
            } else if (tagWireType == 2) {
                i3 = readVarint32();
            } else if (tagWireType == 3) {
                skipGroup();
                return true;
            } else if (tagWireType != 5) {
                throw InvalidProtocolBufferException.d();
            } else {
                i3 = 4;
            }
            skipBytes(i3);
            return true;
        }
    }

    private BinaryReader() {
    }

    /* synthetic */ BinaryReader(AnonymousClass1 anonymousClass1) {
        this();
    }

    public static BinaryReader newInstance(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return new SafeHeapReader(byteBuffer, z);
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }

    public abstract int getTotalBytesRead();

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public boolean shouldDiscardUnknownFields() {
        return false;
    }
}
