package com.google.crypto.tink.shaded.protobuf;

import com.google.common.base.Ascii;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.Internal;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class ArrayDecoders {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.shaded.protobuf.ArrayDecoders$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9721a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f9721a = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9721a[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9721a[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9721a[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9721a[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9721a[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9721a[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9721a[WireFormat.FieldType.SFIXED64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9721a[WireFormat.FieldType.FIXED32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9721a[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9721a[WireFormat.FieldType.BOOL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9721a[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f9721a[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f9721a[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f9721a[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f9721a[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f9721a[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f9721a[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Registers {
        public final ExtensionRegistryLite extensionRegistry;
        public int int1;
        public long long1;
        public Object object1;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Registers(ExtensionRegistryLite extensionRegistryLite) {
            Objects.requireNonNull(extensionRegistryLite);
            this.extensionRegistry = extensionRegistryLite;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int A(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int I = I(bArr, i3, registers);
        while (true) {
            intArrayList.addInt(CodedInputStream.decodeZigZag32(registers.int1));
            if (I >= i4) {
                break;
            }
            int I2 = I(bArr, I, registers);
            if (i2 != registers.int1) {
                break;
            }
            I = I(bArr, I2, registers);
        }
        return I;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int B(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int L = L(bArr, i3, registers);
        while (true) {
            longArrayList.addLong(CodedInputStream.decodeZigZag64(registers.long1));
            if (L >= i4) {
                break;
            }
            int I = I(bArr, L, registers);
            if (i2 != registers.int1) {
                break;
            }
            L = L(bArr, I, registers);
        }
        return L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int C(byte[] bArr, int i2, Registers registers) {
        int I = I(bArr, i2, registers);
        int i3 = registers.int1;
        if (i3 >= 0) {
            if (i3 == 0) {
                registers.object1 = "";
                return I;
            }
            registers.object1 = new String(bArr, I, i3, Internal.f9762a);
            return I + i3;
        }
        throw InvalidProtocolBufferException.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x001d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x002e -> B:6:0x000c). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0017 -> B:9:0x001b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int D(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        int I = I(bArr, i3, registers);
        int i5 = registers.int1;
        if (i5 >= 0) {
            if (i5 != 0) {
                String str = new String(bArr, I, i5, Internal.f9762a);
                protobufList.add(str);
                I += i5;
                if (I < i4) {
                    int I2 = I(bArr, I, registers);
                    if (i2 == registers.int1) {
                        I = I(bArr, I2, registers);
                        i5 = registers.int1;
                        if (i5 < 0) {
                            throw InvalidProtocolBufferException.f();
                        }
                        if (i5 != 0) {
                            str = new String(bArr, I, i5, Internal.f9762a);
                            protobufList.add(str);
                            I += i5;
                            if (I < i4) {
                            }
                        }
                    }
                }
                return I;
            }
            protobufList.add("");
            if (I < i4) {
            }
            return I;
        }
        throw InvalidProtocolBufferException.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x001f -> B:11:0x0023). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0036 -> B:6:0x000c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int E(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        int I = I(bArr, i3, registers);
        int i5 = registers.int1;
        if (i5 >= 0) {
            if (i5 != 0) {
                int i6 = I + i5;
                if (Utf8.isValidUtf8(bArr, I, i6)) {
                    String str = new String(bArr, I, i5, Internal.f9762a);
                    protobufList.add(str);
                    I = i6;
                    if (I < i4) {
                        int I2 = I(bArr, I, registers);
                        if (i2 == registers.int1) {
                            I = I(bArr, I2, registers);
                            int i7 = registers.int1;
                            if (i7 < 0) {
                                throw InvalidProtocolBufferException.f();
                            }
                            if (i7 != 0) {
                                i6 = I + i7;
                                if (!Utf8.isValidUtf8(bArr, I, i6)) {
                                    throw InvalidProtocolBufferException.c();
                                }
                                str = new String(bArr, I, i7, Internal.f9762a);
                                protobufList.add(str);
                                I = i6;
                                if (I < i4) {
                                }
                            }
                        }
                    }
                    return I;
                }
                throw InvalidProtocolBufferException.c();
            }
            protobufList.add("");
            if (I < i4) {
            }
            return I;
        }
        throw InvalidProtocolBufferException.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int F(byte[] bArr, int i2, Registers registers) {
        int I = I(bArr, i2, registers);
        int i3 = registers.int1;
        if (i3 >= 0) {
            if (i3 == 0) {
                registers.object1 = "";
                return I;
            }
            registers.object1 = Utf8.h(bArr, I, i3);
            return I + i3;
        }
        throw InvalidProtocolBufferException.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int G(int i2, byte[] bArr, int i3, int i4, UnknownFieldSetLite unknownFieldSetLite, Registers registers) {
        if (WireFormat.getTagFieldNumber(i2) != 0) {
            int tagWireType = WireFormat.getTagWireType(i2);
            if (tagWireType == 0) {
                int L = L(bArr, i3, registers);
                unknownFieldSetLite.h(i2, Long.valueOf(registers.long1));
                return L;
            } else if (tagWireType == 1) {
                unknownFieldSetLite.h(i2, Long.valueOf(j(bArr, i3)));
                return i3 + 8;
            } else if (tagWireType == 2) {
                int I = I(bArr, i3, registers);
                int i5 = registers.int1;
                if (i5 >= 0) {
                    if (i5 <= bArr.length - I) {
                        unknownFieldSetLite.h(i2, i5 == 0 ? ByteString.EMPTY : ByteString.copyFrom(bArr, I, i5));
                        return I + i5;
                    }
                    throw InvalidProtocolBufferException.j();
                }
                throw InvalidProtocolBufferException.f();
            } else if (tagWireType != 3) {
                if (tagWireType == 5) {
                    unknownFieldSetLite.h(i2, Integer.valueOf(h(bArr, i3)));
                    return i3 + 4;
                }
                throw InvalidProtocolBufferException.b();
            } else {
                UnknownFieldSetLite f2 = UnknownFieldSetLite.f();
                int i6 = (i2 & (-8)) | 4;
                int i7 = 0;
                while (true) {
                    if (i3 >= i4) {
                        break;
                    }
                    int I2 = I(bArr, i3, registers);
                    int i8 = registers.int1;
                    i7 = i8;
                    if (i8 == i6) {
                        i3 = I2;
                        break;
                    }
                    int G = G(i7, bArr, I2, i4, f2, registers);
                    i7 = i8;
                    i3 = G;
                }
                if (i3 > i4 || i7 != i6) {
                    throw InvalidProtocolBufferException.g();
                }
                unknownFieldSetLite.h(i2, f2);
                return i3;
            }
        }
        throw InvalidProtocolBufferException.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int H(int i2, byte[] bArr, int i3, Registers registers) {
        int i4;
        int i5;
        int i6 = i2 & 127;
        int i7 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 < 0) {
            int i8 = i6 | ((b2 & Byte.MAX_VALUE) << 7);
            int i9 = i7 + 1;
            byte b3 = bArr[i7];
            if (b3 >= 0) {
                i4 = b3 << Ascii.SO;
            } else {
                i6 = i8 | ((b3 & Byte.MAX_VALUE) << 14);
                i7 = i9 + 1;
                byte b4 = bArr[i9];
                if (b4 >= 0) {
                    i5 = b4 << Ascii.NAK;
                } else {
                    i8 = i6 | ((b4 & Byte.MAX_VALUE) << 21);
                    i9 = i7 + 1;
                    byte b5 = bArr[i7];
                    if (b5 >= 0) {
                        i4 = b5 << Ascii.FS;
                    } else {
                        int i10 = i8 | ((b5 & Byte.MAX_VALUE) << 28);
                        while (true) {
                            int i11 = i9 + 1;
                            if (bArr[i9] >= 0) {
                                registers.int1 = i10;
                                return i11;
                            }
                            i9 = i11;
                        }
                    }
                }
            }
            registers.int1 = i8 | i4;
            return i9;
        }
        i5 = b2 << 7;
        registers.int1 = i6 | i5;
        return i7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int I(byte[] bArr, int i2, Registers registers) {
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 >= 0) {
            registers.int1 = b2;
            return i3;
        }
        return H(b2, bArr, i3, registers);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int J(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int I = I(bArr, i3, registers);
        while (true) {
            intArrayList.addInt(registers.int1);
            if (I >= i4) {
                break;
            }
            int I2 = I(bArr, I, registers);
            if (i2 != registers.int1) {
                break;
            }
            I = I(bArr, I2, registers);
        }
        return I;
    }

    static int K(long j2, byte[] bArr, int i2, Registers registers) {
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        long j3 = (j2 & 127) | ((b2 & Byte.MAX_VALUE) << 7);
        int i4 = 7;
        while (b2 < 0) {
            int i5 = i3 + 1;
            byte b3 = bArr[i3];
            i4 += 7;
            j3 |= (b3 & Byte.MAX_VALUE) << i4;
            i3 = i5;
            b2 = b3;
        }
        registers.long1 = j3;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int L(byte[] bArr, int i2, Registers registers) {
        int i3 = i2 + 1;
        long j2 = bArr[i2];
        if (j2 >= 0) {
            registers.long1 = j2;
            return i3;
        }
        return K(j2, bArr, i3, registers);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int M(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int L = L(bArr, i3, registers);
        while (true) {
            longArrayList.addLong(registers.long1);
            if (L >= i4) {
                break;
            }
            int I = I(bArr, L, registers);
            if (i2 != registers.int1) {
                break;
            }
            L = L(bArr, I, registers);
        }
        return L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int N(int i2, byte[] bArr, int i3, int i4, Registers registers) {
        if (WireFormat.getTagFieldNumber(i2) != 0) {
            int tagWireType = WireFormat.getTagWireType(i2);
            if (tagWireType != 0) {
                if (tagWireType != 1) {
                    if (tagWireType != 2) {
                        if (tagWireType != 3) {
                            if (tagWireType == 5) {
                                return i3 + 4;
                            }
                            throw InvalidProtocolBufferException.b();
                        }
                        int i5 = (i2 & (-8)) | 4;
                        int i6 = 0;
                        while (i3 < i4) {
                            i3 = I(bArr, i3, registers);
                            i6 = registers.int1;
                            if (i6 == i5) {
                                break;
                            }
                            i3 = N(i6, bArr, i3, i4, registers);
                        }
                        if (i3 > i4 || i6 != i5) {
                            throw InvalidProtocolBufferException.g();
                        }
                        return i3;
                    }
                    return I(bArr, i3, registers) + registers.int1;
                }
                return i3 + 8;
            }
            return L(bArr, i3, registers);
        }
        throw InvalidProtocolBufferException.b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0021, code lost:
        r9 = L(r8, r0, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
        if (r12.long1 == 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
        return r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000e, code lost:
        if (r12.long1 != 0) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0013, code lost:
        r11.addBoolean(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0016, code lost:
        if (r9 >= r10) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
        r0 = I(r8, r9, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
        if (r7 == r12.int1) goto L8;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0029 -> B:4:0x0010). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:5:0x0012 -> B:6:0x0013). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int a(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        BooleanArrayList booleanArrayList = (BooleanArrayList) protobufList;
        int L = L(bArr, i3, registers);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(byte[] bArr, int i2, Registers registers) {
        int I = I(bArr, i2, registers);
        int i3 = registers.int1;
        if (i3 >= 0) {
            if (i3 <= bArr.length - I) {
                if (i3 == 0) {
                    registers.object1 = ByteString.EMPTY;
                    return I;
                }
                registers.object1 = ByteString.copyFrom(bArr, I, i3);
                return I + i3;
            }
            throw InvalidProtocolBufferException.j();
        }
        throw InvalidProtocolBufferException.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
        r0 = I(r3, r4, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0024, code lost:
        if (r2 == r7.int1) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
        r4 = I(r3, r0, r7);
        r0 = r7.int1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
        if (r0 < 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
        if (r0 > (r3.length - r4)) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
        if (r0 != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003a, code lost:
        throw com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.j();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
        throw com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0040, code lost:
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000c, code lost:
        if (r0 == 0) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
        r6.add(com.google.crypto.tink.shaded.protobuf.ByteString.EMPTY);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
        r6.add(com.google.crypto.tink.shaded.protobuf.ByteString.copyFrom(r3, r4, r0));
        r4 = r4 + r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r4 >= r5) goto L20;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0033 -> B:7:0x000e). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0014 -> B:9:0x001c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int c(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        int I = I(bArr, i3, registers);
        int i5 = registers.int1;
        if (i5 < 0) {
            throw InvalidProtocolBufferException.f();
        }
        if (i5 > bArr.length - I) {
            throw InvalidProtocolBufferException.j();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double d(byte[] bArr, int i2) {
        return Double.longBitsToDouble(j(bArr, i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        DoubleArrayList doubleArrayList = (DoubleArrayList) protobufList;
        doubleArrayList.addDouble(d(bArr, i3));
        int i5 = i3 + 8;
        while (i5 < i4) {
            int I = I(bArr, i5, registers);
            if (i2 != registers.int1) {
                break;
            }
            doubleArrayList.addDouble(d(bArr, I));
            i5 = I + 8;
        }
        return i5;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static int f(int i2, byte[] bArr, int i3, int i4, GeneratedMessageLite.ExtendableMessage extendableMessage, GeneratedMessageLite.GeneratedExtension generatedExtension, UnknownFieldSchema unknownFieldSchema, Registers registers) {
        long j2;
        int decodeZigZag32;
        Object field;
        Internal.ProtobufList doubleArrayList;
        int s2;
        FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSet = extendableMessage.extensions;
        int i5 = i2 >>> 3;
        if (generatedExtension.f9761d.isRepeated() && generatedExtension.f9761d.isPacked()) {
            switch (AnonymousClass1.f9721a[generatedExtension.getLiteType().ordinal()]) {
                case 1:
                    doubleArrayList = new DoubleArrayList();
                    s2 = s(bArr, i3, doubleArrayList, registers);
                    break;
                case 2:
                    doubleArrayList = new FloatArrayList();
                    s2 = v(bArr, i3, doubleArrayList, registers);
                    break;
                case 3:
                case 4:
                    doubleArrayList = new LongArrayList();
                    s2 = z(bArr, i3, doubleArrayList, registers);
                    break;
                case 5:
                case 6:
                    doubleArrayList = new IntArrayList();
                    s2 = y(bArr, i3, doubleArrayList, registers);
                    break;
                case 7:
                case 8:
                    doubleArrayList = new LongArrayList();
                    s2 = u(bArr, i3, doubleArrayList, registers);
                    break;
                case 9:
                case 10:
                    doubleArrayList = new IntArrayList();
                    s2 = t(bArr, i3, doubleArrayList, registers);
                    break;
                case 11:
                    doubleArrayList = new BooleanArrayList();
                    s2 = r(bArr, i3, doubleArrayList, registers);
                    break;
                case 12:
                    doubleArrayList = new IntArrayList();
                    s2 = w(bArr, i3, doubleArrayList, registers);
                    break;
                case 13:
                    doubleArrayList = new LongArrayList();
                    s2 = x(bArr, i3, doubleArrayList, registers);
                    break;
                case 14:
                    IntArrayList intArrayList = new IntArrayList();
                    int y = y(bArr, i3, intArrayList, registers);
                    UnknownFieldSetLite unknownFieldSetLite = extendableMessage.unknownFields;
                    UnknownFieldSetLite unknownFieldSetLite2 = (UnknownFieldSetLite) SchemaUtil.z(i5, intArrayList, generatedExtension.f9761d.getEnumType(), unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance() ? unknownFieldSetLite : null, unknownFieldSchema);
                    if (unknownFieldSetLite2 != null) {
                        extendableMessage.unknownFields = unknownFieldSetLite2;
                    }
                    fieldSet.setField(generatedExtension.f9761d, intArrayList);
                    return y;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + generatedExtension.f9761d.getLiteType());
            }
            fieldSet.setField(generatedExtension.f9761d, doubleArrayList);
            return s2;
        }
        if (generatedExtension.getLiteType() != WireFormat.FieldType.ENUM) {
            switch (AnonymousClass1.f9721a[generatedExtension.getLiteType().ordinal()]) {
                case 1:
                    r2 = Double.valueOf(d(bArr, i3));
                    i3 += 8;
                    break;
                case 2:
                    r2 = Float.valueOf(l(bArr, i3));
                    i3 += 4;
                    break;
                case 3:
                case 4:
                    i3 = L(bArr, i3, registers);
                    j2 = registers.long1;
                    r2 = Long.valueOf(j2);
                    break;
                case 5:
                case 6:
                    i3 = I(bArr, i3, registers);
                    break;
                case 7:
                case 8:
                    r2 = Long.valueOf(j(bArr, i3));
                    i3 += 8;
                    break;
                case 9:
                case 10:
                    r2 = Integer.valueOf(h(bArr, i3));
                    i3 += 4;
                    break;
                case 11:
                    i3 = L(bArr, i3, registers);
                    r2 = Boolean.valueOf(registers.long1 != 0);
                    break;
                case 12:
                    i3 = I(bArr, i3, registers);
                    decodeZigZag32 = CodedInputStream.decodeZigZag32(registers.int1);
                    r2 = Integer.valueOf(decodeZigZag32);
                    break;
                case 13:
                    i3 = L(bArr, i3, registers);
                    j2 = CodedInputStream.decodeZigZag64(registers.long1);
                    r2 = Long.valueOf(j2);
                    break;
                case 14:
                    throw new IllegalStateException("Shouldn't reach here.");
                case 15:
                    i3 = b(bArr, i3, registers);
                    r2 = registers.object1;
                    break;
                case 16:
                    i3 = C(bArr, i3, registers);
                    r2 = registers.object1;
                    break;
                case 17:
                    i3 = n(Protobuf.getInstance().schemaFor((Class) generatedExtension.getMessageDefaultInstance().getClass()), bArr, i3, i4, (i5 << 3) | 4, registers);
                    r2 = registers.object1;
                    break;
                case 18:
                    i3 = p(Protobuf.getInstance().schemaFor((Class) generatedExtension.getMessageDefaultInstance().getClass()), bArr, i3, i4, registers);
                    r2 = registers.object1;
                    break;
            }
            if (generatedExtension.isRepeated()) {
                int i6 = AnonymousClass1.f9721a[generatedExtension.getLiteType().ordinal()];
                if ((i6 == 17 || i6 == 18) && (field = fieldSet.getField(generatedExtension.f9761d)) != null) {
                    r2 = Internal.d(field, r2);
                }
                fieldSet.setField(generatedExtension.f9761d, r2);
            } else {
                fieldSet.addRepeatedField(generatedExtension.f9761d, r2);
            }
            return i3;
        }
        i3 = I(bArr, i3, registers);
        if (generatedExtension.f9761d.getEnumType().findValueByNumber(registers.int1) == null) {
            UnknownFieldSetLite unknownFieldSetLite3 = extendableMessage.unknownFields;
            if (unknownFieldSetLite3 == UnknownFieldSetLite.getDefaultInstance()) {
                unknownFieldSetLite3 = UnknownFieldSetLite.f();
                extendableMessage.unknownFields = unknownFieldSetLite3;
            }
            SchemaUtil.F(i5, registers.int1, unknownFieldSetLite3, unknownFieldSchema);
            return i3;
        }
        decodeZigZag32 = registers.int1;
        r2 = Integer.valueOf(decodeZigZag32);
        if (generatedExtension.isRepeated()) {
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(int i2, byte[] bArr, int i3, int i4, Object obj, MessageLite messageLite, UnknownFieldSchema unknownFieldSchema, Registers registers) {
        GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber = registers.extensionRegistry.findLiteExtensionByNumber(messageLite, i2 >>> 3);
        if (findLiteExtensionByNumber == null) {
            return G(i2, bArr, i3, i4, MessageSchema.a(obj), registers);
        }
        GeneratedMessageLite.ExtendableMessage extendableMessage = (GeneratedMessageLite.ExtendableMessage) obj;
        extendableMessage.M();
        return f(i2, bArr, i3, i4, extendableMessage, findLiteExtensionByNumber, unknownFieldSchema, registers);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(byte[] bArr, int i2) {
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        intArrayList.addInt(h(bArr, i3));
        int i5 = i3 + 4;
        while (i5 < i4) {
            int I = I(bArr, i5, registers);
            if (i2 != registers.int1) {
                break;
            }
            intArrayList.addInt(h(bArr, I));
            i5 = I + 4;
        }
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long j(byte[] bArr, int i2) {
        return ((bArr[i2 + 7] & 255) << 56) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 4] & 255) << 32) | ((bArr[i2 + 5] & 255) << 40) | ((bArr[i2 + 6] & 255) << 48);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        longArrayList.addLong(j(bArr, i3));
        int i5 = i3 + 8;
        while (i5 < i4) {
            int I = I(bArr, i5, registers);
            if (i2 != registers.int1) {
                break;
            }
            longArrayList.addLong(j(bArr, I));
            i5 = I + 8;
        }
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float l(byte[] bArr, int i2) {
        return Float.intBitsToFloat(h(bArr, i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        FloatArrayList floatArrayList = (FloatArrayList) protobufList;
        floatArrayList.addFloat(l(bArr, i3));
        int i5 = i3 + 4;
        while (i5 < i4) {
            int I = I(bArr, i5, registers);
            if (i2 != registers.int1) {
                break;
            }
            floatArrayList.addFloat(l(bArr, I));
            i5 = I + 4;
        }
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int n(Schema schema, byte[] bArr, int i2, int i3, int i4, Registers registers) {
        MessageSchema messageSchema = (MessageSchema) schema;
        Object newInstance = messageSchema.newInstance();
        int e2 = messageSchema.e(newInstance, bArr, i2, i3, i4, registers);
        messageSchema.makeImmutable(newInstance);
        registers.object1 = newInstance;
        return e2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int o(Schema schema, int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        int i5 = (i2 & (-8)) | 4;
        int n2 = n(schema, bArr, i3, i4, i5, registers);
        while (true) {
            protobufList.add(registers.object1);
            if (n2 >= i4) {
                break;
            }
            int I = I(bArr, n2, registers);
            if (i2 != registers.int1) {
                break;
            }
            n2 = n(schema, bArr, I, i4, i5, registers);
        }
        return n2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int p(Schema schema, byte[] bArr, int i2, int i3, Registers registers) {
        int i4 = i2 + 1;
        int i5 = bArr[i2];
        if (i5 < 0) {
            i4 = H(i5, bArr, i4, registers);
            i5 = registers.int1;
        }
        int i6 = i4;
        if (i5 < 0 || i5 > i3 - i6) {
            throw InvalidProtocolBufferException.j();
        }
        Object newInstance = schema.newInstance();
        int i7 = i5 + i6;
        schema.mergeFrom(newInstance, bArr, i6, i7, registers);
        schema.makeImmutable(newInstance);
        registers.object1 = newInstance;
        return i7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int q(Schema schema, int i2, byte[] bArr, int i3, int i4, Internal.ProtobufList protobufList, Registers registers) {
        int p2 = p(schema, bArr, i3, i4, registers);
        while (true) {
            protobufList.add(registers.object1);
            if (p2 >= i4) {
                break;
            }
            int I = I(bArr, p2, registers);
            if (i2 != registers.int1) {
                break;
            }
            p2 = p(schema, bArr, I, i4, registers);
        }
        return p2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int r(byte[] bArr, int i2, Internal.ProtobufList protobufList, Registers registers) {
        BooleanArrayList booleanArrayList = (BooleanArrayList) protobufList;
        int I = I(bArr, i2, registers);
        int i3 = registers.int1 + I;
        while (I < i3) {
            I = L(bArr, I, registers);
            booleanArrayList.addBoolean(registers.long1 != 0);
        }
        if (I == i3) {
            return I;
        }
        throw InvalidProtocolBufferException.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int s(byte[] bArr, int i2, Internal.ProtobufList protobufList, Registers registers) {
        DoubleArrayList doubleArrayList = (DoubleArrayList) protobufList;
        int I = I(bArr, i2, registers);
        int i3 = registers.int1 + I;
        while (I < i3) {
            doubleArrayList.addDouble(d(bArr, I));
            I += 8;
        }
        if (I == i3) {
            return I;
        }
        throw InvalidProtocolBufferException.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int t(byte[] bArr, int i2, Internal.ProtobufList protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int I = I(bArr, i2, registers);
        int i3 = registers.int1 + I;
        while (I < i3) {
            intArrayList.addInt(h(bArr, I));
            I += 4;
        }
        if (I == i3) {
            return I;
        }
        throw InvalidProtocolBufferException.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int u(byte[] bArr, int i2, Internal.ProtobufList protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int I = I(bArr, i2, registers);
        int i3 = registers.int1 + I;
        while (I < i3) {
            longArrayList.addLong(j(bArr, I));
            I += 8;
        }
        if (I == i3) {
            return I;
        }
        throw InvalidProtocolBufferException.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int v(byte[] bArr, int i2, Internal.ProtobufList protobufList, Registers registers) {
        FloatArrayList floatArrayList = (FloatArrayList) protobufList;
        int I = I(bArr, i2, registers);
        int i3 = registers.int1 + I;
        while (I < i3) {
            floatArrayList.addFloat(l(bArr, I));
            I += 4;
        }
        if (I == i3) {
            return I;
        }
        throw InvalidProtocolBufferException.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int w(byte[] bArr, int i2, Internal.ProtobufList protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int I = I(bArr, i2, registers);
        int i3 = registers.int1 + I;
        while (I < i3) {
            I = I(bArr, I, registers);
            intArrayList.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        }
        if (I == i3) {
            return I;
        }
        throw InvalidProtocolBufferException.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int x(byte[] bArr, int i2, Internal.ProtobufList protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int I = I(bArr, i2, registers);
        int i3 = registers.int1 + I;
        while (I < i3) {
            I = L(bArr, I, registers);
            longArrayList.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        }
        if (I == i3) {
            return I;
        }
        throw InvalidProtocolBufferException.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int y(byte[] bArr, int i2, Internal.ProtobufList protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int I = I(bArr, i2, registers);
        int i3 = registers.int1 + I;
        while (I < i3) {
            I = I(bArr, I, registers);
            intArrayList.addInt(registers.int1);
        }
        if (I == i3) {
            return I;
        }
        throw InvalidProtocolBufferException.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int z(byte[] bArr, int i2, Internal.ProtobufList protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int I = I(bArr, i2, registers);
        int i3 = registers.int1 + I;
        while (I < i3) {
            I = L(bArr, I, registers);
            longArrayList.addLong(registers.long1);
        }
        if (I == i3) {
            return I;
        }
        throw InvalidProtocolBufferException.j();
    }
}
