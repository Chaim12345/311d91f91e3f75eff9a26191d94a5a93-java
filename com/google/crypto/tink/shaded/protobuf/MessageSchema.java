package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.ArrayDecoders;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.Internal;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class MessageSchema<T> implements Schema<T> {
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int INTS_PER_FIELD = 3;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    private static final int REQUIRED_MASK = 268435456;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.v();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.shaded.protobuf.MessageSchema$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9771a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f9771a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9771a[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9771a[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9771a[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9771a[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9771a[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9771a[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9771a[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9771a[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9771a[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9771a[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9771a[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f9771a[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f9771a[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f9771a[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f9771a[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f9771a[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    private MessageSchema(int[] iArr, Object[] objArr, int i2, int i3, MessageLite messageLite, boolean z, boolean z2, int[] iArr2, int i4, int i5, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i2;
        this.maxFieldNumber = i3;
        this.lite = messageLite instanceof GeneratedMessageLite;
        this.proto3 = z;
        this.hasExtensions = extensionSchema != null && extensionSchema.e(messageLite);
        this.useCachedSizeField = z2;
        this.intArray = iArr2;
        this.checkInitializedCount = i4;
        this.repeatedFieldOffsetStart = i5;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = messageLite;
        this.mapFieldSchema = mapFieldSchema;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UnknownFieldSetLite a(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
            UnknownFieldSetLite f2 = UnknownFieldSetLite.f();
            generatedMessageLite.unknownFields = f2;
            return f2;
        }
        return unknownFieldSetLite;
    }

    private boolean arePresentForEquals(T t2, T t3, int i2) {
        return isFieldPresent(t2, i2) == isFieldPresent(t3, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MessageSchema b(Class cls, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema unknownFieldSchema, ExtensionSchema extensionSchema, MapFieldSchema mapFieldSchema) {
        return messageInfo instanceof RawMessageInfo ? d((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema) : c((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private static <T> boolean booleanAt(T t2, long j2) {
        return UnsafeUtil.m(t2, j2);
    }

    static MessageSchema c(StructuralMessageInfo structuralMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema unknownFieldSchema, ExtensionSchema extensionSchema, MapFieldSchema mapFieldSchema) {
        int fieldNumber;
        int fieldNumber2;
        int i2;
        boolean z = structuralMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] fields = structuralMessageInfo.getFields();
        if (fields.length == 0) {
            fieldNumber = 0;
            fieldNumber2 = 0;
        } else {
            fieldNumber = fields[0].getFieldNumber();
            fieldNumber2 = fields[fields.length - 1].getFieldNumber();
        }
        int length = fields.length;
        int[] iArr = new int[length * 3];
        Object[] objArr = new Object[length * 2];
        int i3 = 0;
        int i4 = 0;
        for (FieldInfo fieldInfo : fields) {
            if (fieldInfo.getType() == FieldType.MAP) {
                i3++;
            } else if (fieldInfo.getType().id() >= 18 && fieldInfo.getType().id() <= 49) {
                i4++;
            }
        }
        int[] iArr2 = i3 > 0 ? new int[i3] : null;
        int[] iArr3 = i4 > 0 ? new int[i4] : null;
        int[] checkInitialized = structuralMessageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i5 < fields.length) {
            FieldInfo fieldInfo2 = fields[i5];
            int fieldNumber3 = fieldInfo2.getFieldNumber();
            storeFieldData(fieldInfo2, iArr, i6, z, objArr);
            if (i7 < checkInitialized.length && checkInitialized[i7] == fieldNumber3) {
                checkInitialized[i7] = i6;
                i7++;
            }
            if (fieldInfo2.getType() == FieldType.MAP) {
                iArr2[i8] = i6;
                i8++;
            } else if (fieldInfo2.getType().id() >= 18 && fieldInfo2.getType().id() <= 49) {
                i2 = i6;
                iArr3[i9] = (int) UnsafeUtil.y(fieldInfo2.getField());
                i9++;
                i5++;
                i6 = i2 + 3;
            }
            i2 = i6;
            i5++;
            i6 = i2 + 3;
        }
        if (iArr2 == null) {
            iArr2 = EMPTY_INT_ARRAY;
        }
        if (iArr3 == null) {
            iArr3 = EMPTY_INT_ARRAY;
        }
        int[] iArr4 = new int[checkInitialized.length + iArr2.length + iArr3.length];
        System.arraycopy(checkInitialized, 0, iArr4, 0, checkInitialized.length);
        System.arraycopy(iArr2, 0, iArr4, checkInitialized.length, iArr2.length);
        System.arraycopy(iArr3, 0, iArr4, checkInitialized.length + iArr2.length, iArr3.length);
        return new MessageSchema(iArr, objArr, fieldNumber, fieldNumber2, structuralMessageInfo.getDefaultInstance(), z, true, iArr4, checkInitialized.length, checkInitialized.length + iArr2.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x039e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static MessageSchema d(RawMessageInfo rawMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema unknownFieldSchema, ExtensionSchema extensionSchema, MapFieldSchema mapFieldSchema) {
        int i2;
        int charAt;
        int charAt2;
        int charAt3;
        int i3;
        int i4;
        int[] iArr;
        int i5;
        int i6;
        char charAt4;
        int i7;
        char charAt5;
        int i8;
        char charAt6;
        int i9;
        char charAt7;
        int i10;
        char charAt8;
        int i11;
        char charAt9;
        int i12;
        char charAt10;
        int i13;
        char charAt11;
        int i14;
        int i15;
        boolean z;
        int i16;
        int[] iArr2;
        int i17;
        int i18;
        String str;
        Class<?> cls;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        Field reflectField;
        int i25;
        char charAt12;
        int i26;
        int i27;
        Object obj;
        Field reflectField2;
        Object obj2;
        Field reflectField3;
        int i28;
        char charAt13;
        int i29;
        char charAt14;
        int i30;
        char charAt15;
        int i31;
        char charAt16;
        char charAt17;
        int i32 = 0;
        boolean z2 = rawMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        String b2 = rawMessageInfo.b();
        int length = b2.length();
        int charAt18 = b2.charAt(0);
        if (charAt18 >= 55296) {
            int i33 = charAt18 & 8191;
            int i34 = 1;
            int i35 = 13;
            while (true) {
                i2 = i34 + 1;
                charAt17 = b2.charAt(i34);
                if (charAt17 < 55296) {
                    break;
                }
                i33 |= (charAt17 & 8191) << i35;
                i35 += 13;
                i34 = i2;
            }
            charAt18 = i33 | (charAt17 << i35);
        } else {
            i2 = 1;
        }
        int i36 = i2 + 1;
        int charAt19 = b2.charAt(i2);
        if (charAt19 >= 55296) {
            int i37 = charAt19 & 8191;
            int i38 = 13;
            while (true) {
                i31 = i36 + 1;
                charAt16 = b2.charAt(i36);
                if (charAt16 < 55296) {
                    break;
                }
                i37 |= (charAt16 & 8191) << i38;
                i38 += 13;
                i36 = i31;
            }
            charAt19 = i37 | (charAt16 << i38);
            i36 = i31;
        }
        if (charAt19 == 0) {
            i5 = 0;
            charAt = 0;
            charAt2 = 0;
            i3 = 0;
            charAt3 = 0;
            iArr = EMPTY_INT_ARRAY;
            i4 = 0;
        } else {
            int i39 = i36 + 1;
            int charAt20 = b2.charAt(i36);
            if (charAt20 >= 55296) {
                int i40 = charAt20 & 8191;
                int i41 = 13;
                while (true) {
                    i13 = i39 + 1;
                    charAt11 = b2.charAt(i39);
                    if (charAt11 < 55296) {
                        break;
                    }
                    i40 |= (charAt11 & 8191) << i41;
                    i41 += 13;
                    i39 = i13;
                }
                charAt20 = i40 | (charAt11 << i41);
                i39 = i13;
            }
            int i42 = i39 + 1;
            int charAt21 = b2.charAt(i39);
            if (charAt21 >= 55296) {
                int i43 = charAt21 & 8191;
                int i44 = 13;
                while (true) {
                    i12 = i42 + 1;
                    charAt10 = b2.charAt(i42);
                    if (charAt10 < 55296) {
                        break;
                    }
                    i43 |= (charAt10 & 8191) << i44;
                    i44 += 13;
                    i42 = i12;
                }
                charAt21 = i43 | (charAt10 << i44);
                i42 = i12;
            }
            int i45 = i42 + 1;
            int charAt22 = b2.charAt(i42);
            if (charAt22 >= 55296) {
                int i46 = charAt22 & 8191;
                int i47 = 13;
                while (true) {
                    i11 = i45 + 1;
                    charAt9 = b2.charAt(i45);
                    if (charAt9 < 55296) {
                        break;
                    }
                    i46 |= (charAt9 & 8191) << i47;
                    i47 += 13;
                    i45 = i11;
                }
                charAt22 = i46 | (charAt9 << i47);
                i45 = i11;
            }
            int i48 = i45 + 1;
            charAt = b2.charAt(i45);
            if (charAt >= 55296) {
                int i49 = charAt & 8191;
                int i50 = 13;
                while (true) {
                    i10 = i48 + 1;
                    charAt8 = b2.charAt(i48);
                    if (charAt8 < 55296) {
                        break;
                    }
                    i49 |= (charAt8 & 8191) << i50;
                    i50 += 13;
                    i48 = i10;
                }
                charAt = i49 | (charAt8 << i50);
                i48 = i10;
            }
            int i51 = i48 + 1;
            charAt2 = b2.charAt(i48);
            if (charAt2 >= 55296) {
                int i52 = charAt2 & 8191;
                int i53 = 13;
                while (true) {
                    i9 = i51 + 1;
                    charAt7 = b2.charAt(i51);
                    if (charAt7 < 55296) {
                        break;
                    }
                    i52 |= (charAt7 & 8191) << i53;
                    i53 += 13;
                    i51 = i9;
                }
                charAt2 = i52 | (charAt7 << i53);
                i51 = i9;
            }
            int i54 = i51 + 1;
            int charAt23 = b2.charAt(i51);
            if (charAt23 >= 55296) {
                int i55 = charAt23 & 8191;
                int i56 = 13;
                while (true) {
                    i8 = i54 + 1;
                    charAt6 = b2.charAt(i54);
                    if (charAt6 < 55296) {
                        break;
                    }
                    i55 |= (charAt6 & 8191) << i56;
                    i56 += 13;
                    i54 = i8;
                }
                charAt23 = i55 | (charAt6 << i56);
                i54 = i8;
            }
            int i57 = i54 + 1;
            int charAt24 = b2.charAt(i54);
            if (charAt24 >= 55296) {
                int i58 = charAt24 & 8191;
                int i59 = 13;
                while (true) {
                    i7 = i57 + 1;
                    charAt5 = b2.charAt(i57);
                    if (charAt5 < 55296) {
                        break;
                    }
                    i58 |= (charAt5 & 8191) << i59;
                    i59 += 13;
                    i57 = i7;
                }
                charAt24 = i58 | (charAt5 << i59);
                i57 = i7;
            }
            int i60 = i57 + 1;
            charAt3 = b2.charAt(i57);
            if (charAt3 >= 55296) {
                int i61 = charAt3 & 8191;
                int i62 = i60;
                int i63 = 13;
                while (true) {
                    i6 = i62 + 1;
                    charAt4 = b2.charAt(i62);
                    if (charAt4 < 55296) {
                        break;
                    }
                    i61 |= (charAt4 & 8191) << i63;
                    i63 += 13;
                    i62 = i6;
                }
                charAt3 = i61 | (charAt4 << i63);
                i60 = i6;
            }
            i3 = (charAt20 * 2) + charAt21;
            i4 = charAt20;
            i36 = i60;
            int i64 = charAt23;
            iArr = new int[charAt3 + charAt23 + charAt24];
            i32 = charAt22;
            i5 = i64;
        }
        Unsafe unsafe = UNSAFE;
        Object[] a2 = rawMessageInfo.a();
        Class<?> cls2 = rawMessageInfo.getDefaultInstance().getClass();
        int[] iArr3 = new int[charAt2 * 3];
        Object[] objArr = new Object[charAt2 * 2];
        int i65 = charAt3 + i5;
        int i66 = charAt3;
        int i67 = i65;
        int i68 = 0;
        int i69 = 0;
        while (i36 < length) {
            int i70 = i36 + 1;
            int charAt25 = b2.charAt(i36);
            int i71 = length;
            if (charAt25 >= 55296) {
                int i72 = charAt25 & 8191;
                int i73 = i70;
                int i74 = 13;
                while (true) {
                    i30 = i73 + 1;
                    charAt15 = b2.charAt(i73);
                    i14 = charAt3;
                    if (charAt15 < 55296) {
                        break;
                    }
                    i72 |= (charAt15 & 8191) << i74;
                    i74 += 13;
                    i73 = i30;
                    charAt3 = i14;
                }
                charAt25 = i72 | (charAt15 << i74);
                i15 = i30;
            } else {
                i14 = charAt3;
                i15 = i70;
            }
            int i75 = i15 + 1;
            int charAt26 = b2.charAt(i15);
            if (charAt26 >= 55296) {
                int i76 = charAt26 & 8191;
                int i77 = i75;
                int i78 = 13;
                while (true) {
                    i29 = i77 + 1;
                    charAt14 = b2.charAt(i77);
                    z = z2;
                    if (charAt14 < 55296) {
                        break;
                    }
                    i76 |= (charAt14 & 8191) << i78;
                    i78 += 13;
                    i77 = i29;
                    z2 = z;
                }
                charAt26 = i76 | (charAt14 << i78);
                i16 = i29;
            } else {
                z = z2;
                i16 = i75;
            }
            int i79 = charAt26 & 255;
            int i80 = charAt;
            if ((charAt26 & 1024) != 0) {
                iArr[i68] = i69;
                i68++;
            }
            int i81 = i68;
            if (i79 >= 51) {
                int i82 = i16 + 1;
                int charAt27 = b2.charAt(i16);
                char c2 = 55296;
                if (charAt27 >= 55296) {
                    int i83 = charAt27 & 8191;
                    int i84 = 13;
                    while (true) {
                        i28 = i82 + 1;
                        charAt13 = b2.charAt(i82);
                        if (charAt13 < c2) {
                            break;
                        }
                        i83 |= (charAt13 & 8191) << i84;
                        i84 += 13;
                        i82 = i28;
                        c2 = 55296;
                    }
                    charAt27 = i83 | (charAt13 << i84);
                    i82 = i28;
                }
                int i85 = i79 - 51;
                int i86 = i82;
                if (i85 == 9 || i85 == 17) {
                    i27 = i3 + 1;
                    objArr[((i69 / 3) * 2) + 1] = a2[i3];
                } else {
                    if (i85 == 12 && (charAt18 & 1) == 1) {
                        i27 = i3 + 1;
                        objArr[((i69 / 3) * 2) + 1] = a2[i3];
                    }
                    int i87 = charAt27 * 2;
                    obj = a2[i87];
                    if (obj instanceof Field) {
                        reflectField2 = reflectField(cls2, (String) obj);
                        a2[i87] = reflectField2;
                    } else {
                        reflectField2 = (Field) obj;
                    }
                    iArr2 = iArr3;
                    i17 = charAt25;
                    int objectFieldOffset = (int) unsafe.objectFieldOffset(reflectField2);
                    int i88 = i87 + 1;
                    obj2 = a2[i88];
                    if (obj2 instanceof Field) {
                        reflectField3 = reflectField(cls2, (String) obj2);
                        a2[i88] = reflectField3;
                    } else {
                        reflectField3 = (Field) obj2;
                    }
                    str = b2;
                    cls = cls2;
                    i19 = charAt18;
                    i22 = (int) unsafe.objectFieldOffset(reflectField3);
                    i24 = objectFieldOffset;
                    i20 = i79;
                    i36 = i86;
                    i23 = 0;
                }
                i3 = i27;
                int i872 = charAt27 * 2;
                obj = a2[i872];
                if (obj instanceof Field) {
                }
                iArr2 = iArr3;
                i17 = charAt25;
                int objectFieldOffset2 = (int) unsafe.objectFieldOffset(reflectField2);
                int i882 = i872 + 1;
                obj2 = a2[i882];
                if (obj2 instanceof Field) {
                }
                str = b2;
                cls = cls2;
                i19 = charAt18;
                i22 = (int) unsafe.objectFieldOffset(reflectField3);
                i24 = objectFieldOffset2;
                i20 = i79;
                i36 = i86;
                i23 = 0;
            } else {
                iArr2 = iArr3;
                i17 = charAt25;
                int i89 = i3 + 1;
                Field reflectField4 = reflectField(cls2, (String) a2[i3]);
                if (i79 == 9 || i79 == 17) {
                    objArr[((i69 / 3) * 2) + 1] = reflectField4.getType();
                } else {
                    if (i79 == 27 || i79 == 49) {
                        i26 = i89 + 1;
                        objArr[((i69 / 3) * 2) + 1] = a2[i89];
                    } else if (i79 == 12 || i79 == 30 || i79 == 44) {
                        if ((charAt18 & 1) == 1) {
                            i26 = i89 + 1;
                            objArr[((i69 / 3) * 2) + 1] = a2[i89];
                        }
                    } else if (i79 == 50) {
                        int i90 = i66 + 1;
                        iArr[i66] = i69;
                        int i91 = (i69 / 3) * 2;
                        int i92 = i89 + 1;
                        objArr[i91] = a2[i89];
                        if ((charAt26 & 2048) != 0) {
                            i89 = i92 + 1;
                            objArr[i91 + 1] = a2[i92];
                            i66 = i90;
                        } else {
                            i66 = i90;
                            i89 = i92;
                        }
                    }
                    i18 = i79;
                    i89 = i26;
                    int objectFieldOffset3 = (int) unsafe.objectFieldOffset(reflectField4);
                    if ((charAt18 & 1) != 1) {
                        i20 = i18;
                        if (i20 <= 17) {
                            i21 = i16 + 1;
                            int charAt28 = b2.charAt(i16);
                            if (charAt28 >= 55296) {
                                int i93 = charAt28 & 8191;
                                int i94 = 13;
                                while (true) {
                                    i25 = i21 + 1;
                                    charAt12 = b2.charAt(i21);
                                    if (charAt12 < 55296) {
                                        break;
                                    }
                                    i93 |= (charAt12 & 8191) << i94;
                                    i94 += 13;
                                    i21 = i25;
                                }
                                charAt28 = i93 | (charAt12 << i94);
                                i21 = i25;
                            }
                            int i95 = (i4 * 2) + (charAt28 / 32);
                            Object obj3 = a2[i95];
                            str = b2;
                            if (obj3 instanceof Field) {
                                reflectField = (Field) obj3;
                            } else {
                                reflectField = reflectField(cls2, (String) obj3);
                                a2[i95] = reflectField;
                            }
                            cls = cls2;
                            i19 = charAt18;
                            i22 = (int) unsafe.objectFieldOffset(reflectField);
                            i23 = charAt28 % 32;
                            if (i20 >= 18 && i20 <= 49) {
                                iArr[i67] = objectFieldOffset3;
                                i67++;
                            }
                            int i96 = i21;
                            i3 = i89;
                            i24 = objectFieldOffset3;
                            i36 = i96;
                        } else {
                            str = b2;
                            cls = cls2;
                            i19 = charAt18;
                        }
                    } else {
                        str = b2;
                        cls = cls2;
                        i19 = charAt18;
                        i20 = i18;
                    }
                    i21 = i16;
                    i22 = 0;
                    i23 = 0;
                    if (i20 >= 18) {
                        iArr[i67] = objectFieldOffset3;
                        i67++;
                    }
                    int i962 = i21;
                    i3 = i89;
                    i24 = objectFieldOffset3;
                    i36 = i962;
                }
                i18 = i79;
                int objectFieldOffset32 = (int) unsafe.objectFieldOffset(reflectField4);
                if ((charAt18 & 1) != 1) {
                }
                i21 = i16;
                i22 = 0;
                i23 = 0;
                if (i20 >= 18) {
                }
                int i9622 = i21;
                i3 = i89;
                i24 = objectFieldOffset32;
                i36 = i9622;
            }
            int i97 = i69 + 1;
            iArr2[i69] = i17;
            int i98 = i97 + 1;
            int i99 = i19;
            iArr2[i97] = ((charAt26 & 512) != 0 ? 536870912 : 0) | ((charAt26 & 256) != 0 ? REQUIRED_MASK : 0) | (i20 << 20) | i24;
            int i100 = i98 + 1;
            iArr2[i98] = (i23 << 20) | i22;
            iArr3 = iArr2;
            cls2 = cls;
            charAt18 = i99;
            charAt = i80;
            length = i71;
            charAt3 = i14;
            z2 = z;
            i68 = i81;
            i69 = i100;
            b2 = str;
        }
        return new MessageSchema(iArr3, objArr, i32, charAt, rawMessageInfo.getDefaultInstance(), z2, false, iArr, charAt3, i65, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r19v0, types: [java.util.Map, java.util.Map<K, V>] */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    private <K, V> int decodeMapEntry(byte[] bArr, int i2, int i3, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map, ArrayDecoders.Registers registers) {
        int i4;
        int I = ArrayDecoders.I(bArr, i2, registers);
        int i5 = registers.int1;
        if (i5 < 0 || i5 > i3 - I) {
            throw InvalidProtocolBufferException.j();
        }
        int i6 = I + i5;
        K k2 = metadata.defaultKey;
        V v = metadata.defaultValue;
        while (I < i6) {
            int i7 = I + 1;
            byte b2 = bArr[I];
            if (b2 < 0) {
                i4 = ArrayDecoders.H(b2, bArr, i7, registers);
                b2 = registers.int1;
            } else {
                i4 = i7;
            }
            int i8 = b2 >>> 3;
            int i9 = b2 & 7;
            if (i8 != 1) {
                if (i8 == 2 && i9 == metadata.valueType.getWireType()) {
                    I = decodeMapEntryValue(bArr, i4, i3, metadata.valueType, metadata.defaultValue.getClass(), registers);
                    v = registers.object1;
                }
                I = ArrayDecoders.N(b2, bArr, i4, i3, registers);
            } else if (i9 == metadata.keyType.getWireType()) {
                I = decodeMapEntryValue(bArr, i4, i3, metadata.keyType, null, registers);
                k2 = registers.object1;
            } else {
                I = ArrayDecoders.N(b2, bArr, i4, i3, registers);
            }
        }
        if (I == i6) {
            map.put(k2, v);
            return i6;
        }
        throw InvalidProtocolBufferException.g();
    }

    private int decodeMapEntryValue(byte[] bArr, int i2, int i3, WireFormat.FieldType fieldType, Class<?> cls, ArrayDecoders.Registers registers) {
        int L;
        Object valueOf;
        Object valueOf2;
        Object valueOf3;
        int i4;
        long j2;
        switch (AnonymousClass1.f9771a[fieldType.ordinal()]) {
            case 1:
                L = ArrayDecoders.L(bArr, i2, registers);
                valueOf = Boolean.valueOf(registers.long1 != 0);
                registers.object1 = valueOf;
                return L;
            case 2:
                return ArrayDecoders.b(bArr, i2, registers);
            case 3:
                valueOf2 = Double.valueOf(ArrayDecoders.d(bArr, i2));
                registers.object1 = valueOf2;
                return i2 + 8;
            case 4:
            case 5:
                valueOf3 = Integer.valueOf(ArrayDecoders.h(bArr, i2));
                registers.object1 = valueOf3;
                return i2 + 4;
            case 6:
            case 7:
                valueOf2 = Long.valueOf(ArrayDecoders.j(bArr, i2));
                registers.object1 = valueOf2;
                return i2 + 8;
            case 8:
                valueOf3 = Float.valueOf(ArrayDecoders.l(bArr, i2));
                registers.object1 = valueOf3;
                return i2 + 4;
            case 9:
            case 10:
            case 11:
                L = ArrayDecoders.I(bArr, i2, registers);
                i4 = registers.int1;
                valueOf = Integer.valueOf(i4);
                registers.object1 = valueOf;
                return L;
            case 12:
            case 13:
                L = ArrayDecoders.L(bArr, i2, registers);
                j2 = registers.long1;
                valueOf = Long.valueOf(j2);
                registers.object1 = valueOf;
                return L;
            case 14:
                return ArrayDecoders.p(Protobuf.getInstance().schemaFor((Class) cls), bArr, i2, i3, registers);
            case 15:
                L = ArrayDecoders.I(bArr, i2, registers);
                i4 = CodedInputStream.decodeZigZag32(registers.int1);
                valueOf = Integer.valueOf(i4);
                registers.object1 = valueOf;
                return L;
            case 16:
                L = ArrayDecoders.L(bArr, i2, registers);
                j2 = CodedInputStream.decodeZigZag64(registers.long1);
                valueOf = Long.valueOf(j2);
                registers.object1 = valueOf;
                return L;
            case 17:
                return ArrayDecoders.F(bArr, i2, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static <T> double doubleAt(T t2, long j2) {
        return UnsafeUtil.p(t2, j2);
    }

    private boolean equals(T t2, T t3, int i2) {
        int typeAndOffsetAt = typeAndOffsetAt(i2);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return arePresentForEquals(t2, t3, i2) && Double.doubleToLongBits(UnsafeUtil.p(t2, offset)) == Double.doubleToLongBits(UnsafeUtil.p(t3, offset));
            case 1:
                return arePresentForEquals(t2, t3, i2) && Float.floatToIntBits(UnsafeUtil.q(t2, offset)) == Float.floatToIntBits(UnsafeUtil.q(t3, offset));
            case 2:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.t(t2, offset) == UnsafeUtil.t(t3, offset);
            case 3:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.t(t2, offset) == UnsafeUtil.t(t3, offset);
            case 4:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.r(t2, offset) == UnsafeUtil.r(t3, offset);
            case 5:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.t(t2, offset) == UnsafeUtil.t(t3, offset);
            case 6:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.r(t2, offset) == UnsafeUtil.r(t3, offset);
            case 7:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.m(t2, offset) == UnsafeUtil.m(t3, offset);
            case 8:
                return arePresentForEquals(t2, t3, i2) && SchemaUtil.E(UnsafeUtil.u(t2, offset), UnsafeUtil.u(t3, offset));
            case 9:
                return arePresentForEquals(t2, t3, i2) && SchemaUtil.E(UnsafeUtil.u(t2, offset), UnsafeUtil.u(t3, offset));
            case 10:
                return arePresentForEquals(t2, t3, i2) && SchemaUtil.E(UnsafeUtil.u(t2, offset), UnsafeUtil.u(t3, offset));
            case 11:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.r(t2, offset) == UnsafeUtil.r(t3, offset);
            case 12:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.r(t2, offset) == UnsafeUtil.r(t3, offset);
            case 13:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.r(t2, offset) == UnsafeUtil.r(t3, offset);
            case 14:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.t(t2, offset) == UnsafeUtil.t(t3, offset);
            case 15:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.r(t2, offset) == UnsafeUtil.r(t3, offset);
            case 16:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.t(t2, offset) == UnsafeUtil.t(t3, offset);
            case 17:
                return arePresentForEquals(t2, t3, i2) && SchemaUtil.E(UnsafeUtil.u(t2, offset), UnsafeUtil.u(t3, offset));
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
                return SchemaUtil.E(UnsafeUtil.u(t2, offset), UnsafeUtil.u(t3, offset));
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                return isOneofCaseEqual(t2, t3, i2) && SchemaUtil.E(UnsafeUtil.u(t2, offset), UnsafeUtil.u(t3, offset));
            default:
                return true;
        }
    }

    private final <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i2, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        Internal.EnumVerifier enumFieldVerifier;
        int numberAt = numberAt(i2);
        Object u = UnsafeUtil.u(obj, offset(typeAndOffsetAt(i2)));
        return (u == null || (enumFieldVerifier = getEnumFieldVerifier(i2)) == null) ? ub : (UB) filterUnknownEnumMap(i2, numberAt, this.mapFieldSchema.forMutableMapData(u), enumFieldVerifier, ub, unknownFieldSchema);
    }

    private final <K, V, UT, UB> UB filterUnknownEnumMap(int i2, int i3, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        MapEntryLite.Metadata<?, ?> forMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = (UB) unknownFieldSchema.n();
                }
                ByteString.CodedBuilder g2 = ByteString.g(MapEntryLite.a(forMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.e(g2.getCodedOutput(), forMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.d(ub, i3, g2.build());
                    it.remove();
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
        return ub;
    }

    private static <T> float floatAt(T t2, long j2) {
        return UnsafeUtil.q(t2, j2);
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int i2) {
        return (Internal.EnumVerifier) this.objects[((i2 / 3) * 2) + 1];
    }

    private Object getMapFieldDefaultEntry(int i2) {
        return this.objects[(i2 / 3) * 2];
    }

    private Schema getMessageFieldSchema(int i2) {
        int i3 = (i2 / 3) * 2;
        Schema schema = (Schema) this.objects[i3];
        if (schema != null) {
            return schema;
        }
        Schema<T> schemaFor = Protobuf.getInstance().schemaFor((Class) ((Class) this.objects[i3 + 1]));
        this.objects[i3] = schemaFor;
        return schemaFor;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x01aa, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01bc, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01ce, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01e0, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x01f2, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0203, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0214, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0225, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0236, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0247, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0258, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x025a, code lost:
        r2.putInt(r18, r11, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x025e, code lost:
        r4 = (com.google.crypto.tink.shaded.protobuf.CodedOutputStream.computeTagSize(r9) + com.google.crypto.tink.shaded.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r3)) + r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x0320, code lost:
        if ((r7 & r14) != 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0322, code lost:
        r3 = com.google.crypto.tink.shaded.protobuf.CodedOutputStream.b(r9, (com.google.crypto.tink.shaded.protobuf.MessageLite) r2.getObject(r18, r12), getMessageFieldSchema(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x034d, code lost:
        if ((r7 & r14) != 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x034f, code lost:
        r3 = com.google.crypto.tink.shaded.protobuf.CodedOutputStream.computeSFixed64Size(r9, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0356, code lost:
        if ((r7 & r14) != 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0358, code lost:
        r4 = com.google.crypto.tink.shaded.protobuf.CodedOutputStream.computeSFixed32Size(r9, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x037b, code lost:
        if ((r7 & r14) != 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x037d, code lost:
        r3 = com.google.crypto.tink.shaded.protobuf.CodedOutputStream.computeBytesSize(r9, (com.google.crypto.tink.shaded.protobuf.ByteString) r2.getObject(r18, r12));
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x038a, code lost:
        if ((r7 & r14) != 0) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x038c, code lost:
        r3 = com.google.crypto.tink.shaded.protobuf.SchemaUtil.o(r9, r2.getObject(r18, r12), getMessageFieldSchema(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x03b7, code lost:
        if ((r7 & r14) != 0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x03b9, code lost:
        r3 = com.google.crypto.tink.shaded.protobuf.CodedOutputStream.computeBoolSize(r9, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0069, code lost:
        if (isOneofPresent(r18, r9, r5) != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0089, code lost:
        if (isOneofPresent(r18, r9, r5) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0091, code lost:
        if (isOneofPresent(r18, r9, r5) != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b1, code lost:
        if (isOneofPresent(r18, r9, r5) != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b9, code lost:
        if (isOneofPresent(r18, r9, r5) != false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00df, code lost:
        if (isOneofPresent(r18, r9, r5) != false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0174, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0186, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0198, code lost:
        if (r17.useCachedSizeField != false) goto L82;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int getSerializedSizeProto2(T t2) {
        int i2;
        int i3;
        int computeDoubleSize;
        int computeBytesSize;
        int i4;
        int i5;
        int i6;
        long j2;
        boolean z;
        int f2;
        int i7;
        Unsafe unsafe = UNSAFE;
        int i8 = -1;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i9 < this.buffer.length) {
            int typeAndOffsetAt = typeAndOffsetAt(i9);
            int numberAt = numberAt(i9);
            int type = type(typeAndOffsetAt);
            if (type <= 17) {
                i2 = this.buffer[i9 + 2];
                int i12 = OFFSET_MASK & i2;
                int i13 = 1 << (i2 >>> 20);
                if (i12 != i8) {
                    i11 = unsafe.getInt(t2, i12);
                    i8 = i12;
                }
                i3 = i13;
            } else {
                i2 = (!this.useCachedSizeField || type < FieldType.DOUBLE_LIST_PACKED.id() || type > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.buffer[i9 + 2] & OFFSET_MASK;
                i3 = 0;
            }
            long offset = offset(typeAndOffsetAt);
            int i14 = i8;
            switch (type) {
                case 0:
                    if ((i11 & i3) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i10 += computeDoubleSize;
                        break;
                    }
                case 1:
                    if ((i11 & i3) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i10 += computeDoubleSize;
                        break;
                    }
                case 2:
                    if ((i11 & i3) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, unsafe.getLong(t2, offset));
                        i10 += computeDoubleSize;
                        break;
                    }
                case 3:
                    if ((i11 & i3) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, unsafe.getLong(t2, offset));
                        i10 += computeDoubleSize;
                        break;
                    }
                case 4:
                    if ((i11 & i3) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, unsafe.getInt(t2, offset));
                        i10 += computeDoubleSize;
                        break;
                    }
                case 5:
                    if ((i11 & i3) == 0) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i10 += computeDoubleSize;
                        break;
                    }
                case 6:
                    if ((i11 & i3) != 0) {
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i10 += computeDoubleSize;
                        break;
                    }
                    break;
                case 8:
                    if ((i11 & i3) != 0) {
                        Object object = unsafe.getObject(t2, offset);
                        computeBytesSize = object instanceof ByteString ? CodedOutputStream.computeBytesSize(numberAt, (ByteString) object) : CodedOutputStream.computeStringSize(numberAt, (String) object);
                        i10 += computeBytesSize;
                    }
                    break;
                case 11:
                    if ((i11 & i3) != 0) {
                        i4 = unsafe.getInt(t2, offset);
                        computeBytesSize = CodedOutputStream.computeUInt32Size(numberAt, i4);
                        i10 += computeBytesSize;
                    }
                    break;
                case 12:
                    if ((i11 & i3) != 0) {
                        i5 = unsafe.getInt(t2, offset);
                        computeBytesSize = CodedOutputStream.computeEnumSize(numberAt, i5);
                        i10 += computeBytesSize;
                    }
                    break;
                case 15:
                    if ((i11 & i3) != 0) {
                        i6 = unsafe.getInt(t2, offset);
                        computeBytesSize = CodedOutputStream.computeSInt32Size(numberAt, i6);
                        i10 += computeBytesSize;
                    }
                    break;
                case 16:
                    if ((i11 & i3) != 0) {
                        j2 = unsafe.getLong(t2, offset);
                        computeBytesSize = CodedOutputStream.computeSInt64Size(numberAt, j2);
                        i10 += computeBytesSize;
                    }
                    break;
                case 18:
                    computeBytesSize = SchemaUtil.h(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i10 += computeBytesSize;
                    break;
                case 19:
                case 24:
                case 31:
                    z = false;
                    f2 = SchemaUtil.f(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i10 += f2;
                    break;
                case 20:
                    z = false;
                    f2 = SchemaUtil.m(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i10 += f2;
                    break;
                case 21:
                    z = false;
                    f2 = SchemaUtil.x(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i10 += f2;
                    break;
                case 22:
                    z = false;
                    f2 = SchemaUtil.k(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i10 += f2;
                    break;
                case 23:
                case 32:
                    z = false;
                    f2 = SchemaUtil.h(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i10 += f2;
                    break;
                case 25:
                    z = false;
                    f2 = SchemaUtil.a(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i10 += f2;
                    break;
                case 26:
                    computeBytesSize = SchemaUtil.u(numberAt, (List) unsafe.getObject(t2, offset));
                    i10 += computeBytesSize;
                    break;
                case 27:
                    computeBytesSize = SchemaUtil.p(numberAt, (List) unsafe.getObject(t2, offset), getMessageFieldSchema(i9));
                    i10 += computeBytesSize;
                    break;
                case 28:
                    computeBytesSize = SchemaUtil.c(numberAt, (List) unsafe.getObject(t2, offset));
                    i10 += computeBytesSize;
                    break;
                case 29:
                    computeBytesSize = SchemaUtil.v(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i10 += computeBytesSize;
                    break;
                case 30:
                    z = false;
                    f2 = SchemaUtil.d(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i10 += f2;
                    break;
                case 33:
                    z = false;
                    f2 = SchemaUtil.q(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i10 += f2;
                    break;
                case 34:
                    z = false;
                    f2 = SchemaUtil.s(numberAt, (List) unsafe.getObject(t2, offset), false);
                    i10 += f2;
                    break;
                case 35:
                    i7 = SchemaUtil.i((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 36:
                    i7 = SchemaUtil.g((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 37:
                    i7 = SchemaUtil.n((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 38:
                    i7 = SchemaUtil.y((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 39:
                    i7 = SchemaUtil.l((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 40:
                    i7 = SchemaUtil.i((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 41:
                    i7 = SchemaUtil.g((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 42:
                    i7 = SchemaUtil.b((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 43:
                    i7 = SchemaUtil.w((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 44:
                    i7 = SchemaUtil.e((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 45:
                    i7 = SchemaUtil.g((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 46:
                    i7 = SchemaUtil.i((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 47:
                    i7 = SchemaUtil.r((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 48:
                    i7 = SchemaUtil.t((List) unsafe.getObject(t2, offset));
                    if (i7 > 0) {
                        break;
                    }
                    break;
                case 49:
                    computeBytesSize = SchemaUtil.j(numberAt, (List) unsafe.getObject(t2, offset), getMessageFieldSchema(i9));
                    i10 += computeBytesSize;
                    break;
                case 50:
                    computeBytesSize = this.mapFieldSchema.getSerializedSize(numberAt, unsafe.getObject(t2, offset), getMapFieldDefaultEntry(i9));
                    i10 += computeBytesSize;
                    break;
                case 51:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeBytesSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i10 += computeBytesSize;
                    }
                    break;
                case 52:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeBytesSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i10 += computeBytesSize;
                    }
                    break;
                case 53:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeBytesSize = CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(t2, offset));
                        i10 += computeBytesSize;
                    }
                    break;
                case 54:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeBytesSize = CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(t2, offset));
                        i10 += computeBytesSize;
                    }
                    break;
                case 55:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeBytesSize = CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(t2, offset));
                        i10 += computeBytesSize;
                    }
                    break;
                case 56:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        computeBytesSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i10 += computeBytesSize;
                    }
                    break;
                case 57:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        int computeTagSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i10 += computeTagSize;
                    }
                    break;
                case 59:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        Object object2 = unsafe.getObject(t2, offset);
                        computeBytesSize = object2 instanceof ByteString ? CodedOutputStream.computeBytesSize(numberAt, (ByteString) object2) : CodedOutputStream.computeStringSize(numberAt, (String) object2);
                        i10 += computeBytesSize;
                    }
                    break;
                case 62:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        i4 = oneofIntAt(t2, offset);
                        computeBytesSize = CodedOutputStream.computeUInt32Size(numberAt, i4);
                        i10 += computeBytesSize;
                    }
                    break;
                case 63:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        i5 = oneofIntAt(t2, offset);
                        computeBytesSize = CodedOutputStream.computeEnumSize(numberAt, i5);
                        i10 += computeBytesSize;
                    }
                    break;
                case 66:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        i6 = oneofIntAt(t2, offset);
                        computeBytesSize = CodedOutputStream.computeSInt32Size(numberAt, i6);
                        i10 += computeBytesSize;
                    }
                    break;
                case 67:
                    if (isOneofPresent(t2, numberAt, i9)) {
                        j2 = oneofLongAt(t2, offset);
                        computeBytesSize = CodedOutputStream.computeSInt64Size(numberAt, j2);
                        i10 += computeBytesSize;
                    }
                    break;
            }
            i9 += 3;
            i8 = i14;
        }
        int unknownFieldsSerializedSize = i10 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t2);
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.c(t2).getSerializedSize() : unknownFieldsSerializedSize;
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0184, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0196, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01a8, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x01b9, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01ca, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x01db, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x01ec, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01fd, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x020e, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0210, code lost:
        r2.putInt(r16, r6, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0214, code lost:
        r6 = (com.google.crypto.tink.shaded.protobuf.CodedOutputStream.computeTagSize(r8) + com.google.crypto.tink.shaded.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r7)) + r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x0334, code lost:
        if ((r6 instanceof com.google.crypto.tink.shaded.protobuf.ByteString) != false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a6, code lost:
        if ((r6 instanceof com.google.crypto.tink.shaded.protobuf.ByteString) != false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00aa, code lost:
        r6 = com.google.crypto.tink.shaded.protobuf.CodedOutputStream.computeStringSize(r8, (java.lang.String) r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x012a, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x013c, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x014e, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0160, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0172, code lost:
        if (r15.useCachedSizeField != false) goto L103;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int getSerializedSizeProto3(T t2) {
        long t3;
        long t4;
        int r2;
        Object u;
        int r3;
        int r4;
        int r5;
        long t5;
        int h2;
        int i2;
        Unsafe unsafe = UNSAFE;
        int i3 = 0;
        for (int i4 = 0; i4 < this.buffer.length; i4 += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i4);
            int type = type(typeAndOffsetAt);
            int numberAt = numberAt(i4);
            long offset = offset(typeAndOffsetAt);
            int i5 = (type < FieldType.DOUBLE_LIST_PACKED.id() || type > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.buffer[i4 + 2] & OFFSET_MASK;
            switch (type) {
                case 0:
                    if (!isFieldPresent(t2, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                    break;
                case 1:
                    if (!isFieldPresent(t2, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                    break;
                case 2:
                    if (isFieldPresent(t2, i4)) {
                        t3 = UnsafeUtil.t(t2, offset);
                        h2 = CodedOutputStream.computeInt64Size(numberAt, t3);
                        break;
                    } else {
                        continue;
                    }
                case 3:
                    if (isFieldPresent(t2, i4)) {
                        t4 = UnsafeUtil.t(t2, offset);
                        h2 = CodedOutputStream.computeUInt64Size(numberAt, t4);
                        break;
                    } else {
                        continue;
                    }
                case 4:
                    if (isFieldPresent(t2, i4)) {
                        r2 = UnsafeUtil.r(t2, offset);
                        h2 = CodedOutputStream.computeInt32Size(numberAt, r2);
                        break;
                    } else {
                        continue;
                    }
                case 5:
                    if (!isFieldPresent(t2, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                    break;
                case 6:
                    if (!isFieldPresent(t2, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeFixed32Size(numberAt, 0);
                    break;
                case 7:
                    if (!isFieldPresent(t2, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeBoolSize(numberAt, true);
                    break;
                case 8:
                    if (isFieldPresent(t2, i4)) {
                        u = UnsafeUtil.u(t2, offset);
                        break;
                    } else {
                        continue;
                    }
                case 9:
                    if (!isFieldPresent(t2, i4)) {
                        continue;
                    }
                    h2 = SchemaUtil.o(numberAt, UnsafeUtil.u(t2, offset), getMessageFieldSchema(i4));
                    break;
                case 10:
                    if (!isFieldPresent(t2, i4)) {
                        continue;
                    }
                    u = UnsafeUtil.u(t2, offset);
                    h2 = CodedOutputStream.computeBytesSize(numberAt, (ByteString) u);
                    break;
                case 11:
                    if (isFieldPresent(t2, i4)) {
                        r3 = UnsafeUtil.r(t2, offset);
                        h2 = CodedOutputStream.computeUInt32Size(numberAt, r3);
                        break;
                    } else {
                        continue;
                    }
                case 12:
                    if (isFieldPresent(t2, i4)) {
                        r4 = UnsafeUtil.r(t2, offset);
                        h2 = CodedOutputStream.computeEnumSize(numberAt, r4);
                        break;
                    } else {
                        continue;
                    }
                case 13:
                    if (!isFieldPresent(t2, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                    break;
                case 14:
                    if (!isFieldPresent(t2, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                    break;
                case 15:
                    if (isFieldPresent(t2, i4)) {
                        r5 = UnsafeUtil.r(t2, offset);
                        h2 = CodedOutputStream.computeSInt32Size(numberAt, r5);
                        break;
                    } else {
                        continue;
                    }
                case 16:
                    if (isFieldPresent(t2, i4)) {
                        t5 = UnsafeUtil.t(t2, offset);
                        h2 = CodedOutputStream.computeSInt64Size(numberAt, t5);
                        break;
                    } else {
                        continue;
                    }
                case 17:
                    if (!isFieldPresent(t2, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.b(numberAt, (MessageLite) UnsafeUtil.u(t2, offset), getMessageFieldSchema(i4));
                    break;
                case 18:
                case 23:
                case 32:
                    h2 = SchemaUtil.h(numberAt, listAt(t2, offset), false);
                    break;
                case 19:
                case 24:
                case 31:
                    h2 = SchemaUtil.f(numberAt, listAt(t2, offset), false);
                    break;
                case 20:
                    h2 = SchemaUtil.m(numberAt, listAt(t2, offset), false);
                    break;
                case 21:
                    h2 = SchemaUtil.x(numberAt, listAt(t2, offset), false);
                    break;
                case 22:
                    h2 = SchemaUtil.k(numberAt, listAt(t2, offset), false);
                    break;
                case 25:
                    h2 = SchemaUtil.a(numberAt, listAt(t2, offset), false);
                    break;
                case 26:
                    h2 = SchemaUtil.u(numberAt, listAt(t2, offset));
                    break;
                case 27:
                    h2 = SchemaUtil.p(numberAt, listAt(t2, offset), getMessageFieldSchema(i4));
                    break;
                case 28:
                    h2 = SchemaUtil.c(numberAt, listAt(t2, offset));
                    break;
                case 29:
                    h2 = SchemaUtil.v(numberAt, listAt(t2, offset), false);
                    break;
                case 30:
                    h2 = SchemaUtil.d(numberAt, listAt(t2, offset), false);
                    break;
                case 33:
                    h2 = SchemaUtil.q(numberAt, listAt(t2, offset), false);
                    break;
                case 34:
                    h2 = SchemaUtil.s(numberAt, listAt(t2, offset), false);
                    break;
                case 35:
                    i2 = SchemaUtil.i((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 36:
                    i2 = SchemaUtil.g((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 37:
                    i2 = SchemaUtil.n((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 38:
                    i2 = SchemaUtil.y((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 39:
                    i2 = SchemaUtil.l((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 40:
                    i2 = SchemaUtil.i((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 41:
                    i2 = SchemaUtil.g((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 42:
                    i2 = SchemaUtil.b((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 43:
                    i2 = SchemaUtil.w((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 44:
                    i2 = SchemaUtil.e((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 45:
                    i2 = SchemaUtil.g((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 46:
                    i2 = SchemaUtil.i((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 47:
                    i2 = SchemaUtil.r((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 48:
                    i2 = SchemaUtil.t((List) unsafe.getObject(t2, offset));
                    if (i2 > 0) {
                        break;
                    } else {
                        continue;
                    }
                case 49:
                    h2 = SchemaUtil.j(numberAt, listAt(t2, offset), getMessageFieldSchema(i4));
                    break;
                case 50:
                    h2 = this.mapFieldSchema.getSerializedSize(numberAt, UnsafeUtil.u(t2, offset), getMapFieldDefaultEntry(i4));
                    break;
                case 51:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                    break;
                case 52:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                    break;
                case 53:
                    if (isOneofPresent(t2, numberAt, i4)) {
                        t3 = oneofLongAt(t2, offset);
                        h2 = CodedOutputStream.computeInt64Size(numberAt, t3);
                        break;
                    } else {
                        continue;
                    }
                case 54:
                    if (isOneofPresent(t2, numberAt, i4)) {
                        t4 = oneofLongAt(t2, offset);
                        h2 = CodedOutputStream.computeUInt64Size(numberAt, t4);
                        break;
                    } else {
                        continue;
                    }
                case 55:
                    if (isOneofPresent(t2, numberAt, i4)) {
                        r2 = oneofIntAt(t2, offset);
                        h2 = CodedOutputStream.computeInt32Size(numberAt, r2);
                        break;
                    } else {
                        continue;
                    }
                case 56:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                    break;
                case 57:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeFixed32Size(numberAt, 0);
                    break;
                case 58:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeBoolSize(numberAt, true);
                    break;
                case 59:
                    if (isOneofPresent(t2, numberAt, i4)) {
                        u = UnsafeUtil.u(t2, offset);
                        break;
                    } else {
                        continue;
                    }
                case 60:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        continue;
                    }
                    h2 = SchemaUtil.o(numberAt, UnsafeUtil.u(t2, offset), getMessageFieldSchema(i4));
                    break;
                case 61:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        continue;
                    }
                    u = UnsafeUtil.u(t2, offset);
                    h2 = CodedOutputStream.computeBytesSize(numberAt, (ByteString) u);
                    break;
                case 62:
                    if (isOneofPresent(t2, numberAt, i4)) {
                        r3 = oneofIntAt(t2, offset);
                        h2 = CodedOutputStream.computeUInt32Size(numberAt, r3);
                        break;
                    } else {
                        continue;
                    }
                case 63:
                    if (isOneofPresent(t2, numberAt, i4)) {
                        r4 = oneofIntAt(t2, offset);
                        h2 = CodedOutputStream.computeEnumSize(numberAt, r4);
                        break;
                    } else {
                        continue;
                    }
                case 64:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeSFixed32Size(numberAt, 0);
                    break;
                case 65:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                    break;
                case 66:
                    if (isOneofPresent(t2, numberAt, i4)) {
                        r5 = oneofIntAt(t2, offset);
                        h2 = CodedOutputStream.computeSInt32Size(numberAt, r5);
                        break;
                    } else {
                        continue;
                    }
                case 67:
                    if (isOneofPresent(t2, numberAt, i4)) {
                        t5 = oneofLongAt(t2, offset);
                        h2 = CodedOutputStream.computeSInt64Size(numberAt, t5);
                        break;
                    } else {
                        continue;
                    }
                case 68:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        continue;
                    }
                    h2 = CodedOutputStream.b(numberAt, (MessageLite) UnsafeUtil.u(t2, offset), getMessageFieldSchema(i4));
                    break;
                default:
            }
            i3 += h2;
        }
        return i3 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t2);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t2) {
        return unknownFieldSchema.h(unknownFieldSchema.g(t2));
    }

    private static <T> int intAt(T t2, long j2) {
        return UnsafeUtil.r(t2, j2);
    }

    private static boolean isEnforceUtf8(int i2) {
        return (i2 & 536870912) != 0;
    }

    private boolean isFieldPresent(T t2, int i2) {
        if (!this.proto3) {
            int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i2);
            return (UnsafeUtil.r(t2, (long) (presenceMaskAndOffsetAt & OFFSET_MASK)) & (1 << (presenceMaskAndOffsetAt >>> 20))) != 0;
        }
        int typeAndOffsetAt = typeAndOffsetAt(i2);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return UnsafeUtil.p(t2, offset) != 0.0d;
            case 1:
                return UnsafeUtil.q(t2, offset) != 0.0f;
            case 2:
                return UnsafeUtil.t(t2, offset) != 0;
            case 3:
                return UnsafeUtil.t(t2, offset) != 0;
            case 4:
                return UnsafeUtil.r(t2, offset) != 0;
            case 5:
                return UnsafeUtil.t(t2, offset) != 0;
            case 6:
                return UnsafeUtil.r(t2, offset) != 0;
            case 7:
                return UnsafeUtil.m(t2, offset);
            case 8:
                Object u = UnsafeUtil.u(t2, offset);
                if (u instanceof String) {
                    return !((String) u).isEmpty();
                }
                if (u instanceof ByteString) {
                    return !ByteString.EMPTY.equals(u);
                }
                throw new IllegalArgumentException();
            case 9:
                return UnsafeUtil.u(t2, offset) != null;
            case 10:
                return !ByteString.EMPTY.equals(UnsafeUtil.u(t2, offset));
            case 11:
                return UnsafeUtil.r(t2, offset) != 0;
            case 12:
                return UnsafeUtil.r(t2, offset) != 0;
            case 13:
                return UnsafeUtil.r(t2, offset) != 0;
            case 14:
                return UnsafeUtil.t(t2, offset) != 0;
            case 15:
                return UnsafeUtil.r(t2, offset) != 0;
            case 16:
                return UnsafeUtil.t(t2, offset) != 0;
            case 17:
                return UnsafeUtil.u(t2, offset) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private boolean isFieldPresent(T t2, int i2, int i3, int i4) {
        return this.proto3 ? isFieldPresent(t2, i2) : (i3 & i4) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object obj, int i2, Schema schema) {
        return schema.isInitialized(UnsafeUtil.u(obj, offset(i2)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N> boolean isListInitialized(Object obj, int i2, int i3) {
        List list = (List) UnsafeUtil.u(obj, offset(i2));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(i3);
        for (int i4 = 0; i4 < list.size(); i4++) {
            if (!messageFieldSchema.isInitialized(list.get(i4))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v8, types: [com.google.crypto.tink.shaded.protobuf.Schema] */
    private boolean isMapInitialized(T t2, int i2, int i3) {
        Map<?, ?> forMapData = this.mapFieldSchema.forMapData(UnsafeUtil.u(t2, offset(i2)));
        if (forMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i3)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        Schema<T> schema = 0;
        for (Object obj : forMapData.values()) {
            if (schema == null) {
                schema = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            boolean isInitialized = schema.isInitialized(obj);
            schema = schema;
            if (!isInitialized) {
                return false;
            }
        }
        return true;
    }

    private boolean isOneofCaseEqual(T t2, T t3, int i2) {
        long presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i2) & OFFSET_MASK;
        return UnsafeUtil.r(t2, presenceMaskAndOffsetAt) == UnsafeUtil.r(t3, presenceMaskAndOffsetAt);
    }

    private boolean isOneofPresent(T t2, int i2, int i3) {
        return UnsafeUtil.r(t2, (long) (presenceMaskAndOffsetAt(i3) & OFFSET_MASK)) == i2;
    }

    private static boolean isRequired(int i2) {
        return (i2 & REQUIRED_MASK) != 0;
    }

    private static List<?> listAt(Object obj, long j2) {
        return (List) UnsafeUtil.u(obj, j2);
    }

    private static <T> long longAt(T t2, long j2) {
        return UnsafeUtil.t(t2, j2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:327:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0077, code lost:
        r0 = r16.checkInitializedCount;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007b, code lost:
        if (r0 >= r16.repeatedFieldOffsetStart) goto L292;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007d, code lost:
        r13 = filterMapUnknownEnumValues(r19, r16.intArray[r0], r13, r17);
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0088, code lost:
        if (r13 == null) goto L296;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008a, code lost:
        r17.o(r19, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008d, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        Object d2;
        long offset;
        int readEnum;
        List e2;
        List e3;
        List e4;
        List e5;
        List e6;
        List e7;
        List e8;
        List e9;
        List e10;
        List e11;
        Internal.EnumVerifier enumFieldVerifier;
        List e12;
        List e13;
        List e14;
        List e15;
        Object obj = null;
        FieldSet fieldSet = null;
        while (true) {
            try {
                int fieldNumber = reader.getFieldNumber();
                int positionForFieldNumber = positionForFieldNumber(fieldNumber);
                if (positionForFieldNumber >= 0) {
                    int typeAndOffsetAt = typeAndOffsetAt(positionForFieldNumber);
                    try {
                        switch (type(typeAndOffsetAt)) {
                            case 0:
                                UnsafeUtil.C(t2, offset(typeAndOffsetAt), reader.readDouble());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 1:
                                UnsafeUtil.D(t2, offset(typeAndOffsetAt), reader.readFloat());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 2:
                                UnsafeUtil.F(t2, offset(typeAndOffsetAt), reader.readInt64());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 3:
                                UnsafeUtil.F(t2, offset(typeAndOffsetAt), reader.readUInt64());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 4:
                                UnsafeUtil.E(t2, offset(typeAndOffsetAt), reader.readInt32());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 5:
                                UnsafeUtil.F(t2, offset(typeAndOffsetAt), reader.readFixed64());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 6:
                                UnsafeUtil.E(t2, offset(typeAndOffsetAt), reader.readFixed32());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 7:
                                UnsafeUtil.z(t2, offset(typeAndOffsetAt), reader.readBool());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 8:
                                readString(t2, typeAndOffsetAt, reader);
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 9:
                                if (isFieldPresent(t2, positionForFieldNumber)) {
                                    d2 = Internal.d(UnsafeUtil.u(t2, offset(typeAndOffsetAt)), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite));
                                    offset = offset(typeAndOffsetAt);
                                    UnsafeUtil.G(t2, offset, d2);
                                    break;
                                } else {
                                    UnsafeUtil.G(t2, offset(typeAndOffsetAt), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(t2, positionForFieldNumber);
                                    break;
                                }
                            case 10:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), reader.readBytes());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 11:
                                UnsafeUtil.E(t2, offset(typeAndOffsetAt), reader.readUInt32());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 12:
                                readEnum = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier2 = getEnumFieldVerifier(positionForFieldNumber);
                                if (enumFieldVerifier2 != null && !enumFieldVerifier2.isInRange(readEnum)) {
                                    obj = SchemaUtil.F(fieldNumber, readEnum, obj, unknownFieldSchema);
                                    break;
                                } else {
                                    UnsafeUtil.E(t2, offset(typeAndOffsetAt), readEnum);
                                    setFieldPresent(t2, positionForFieldNumber);
                                    break;
                                }
                                break;
                            case 13:
                                UnsafeUtil.E(t2, offset(typeAndOffsetAt), reader.readSFixed32());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 14:
                                UnsafeUtil.F(t2, offset(typeAndOffsetAt), reader.readSFixed64());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 15:
                                UnsafeUtil.E(t2, offset(typeAndOffsetAt), reader.readSInt32());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 16:
                                UnsafeUtil.F(t2, offset(typeAndOffsetAt), reader.readSInt64());
                                setFieldPresent(t2, positionForFieldNumber);
                                break;
                            case 17:
                                if (isFieldPresent(t2, positionForFieldNumber)) {
                                    d2 = Internal.d(UnsafeUtil.u(t2, offset(typeAndOffsetAt)), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite));
                                    offset = offset(typeAndOffsetAt);
                                    UnsafeUtil.G(t2, offset, d2);
                                    break;
                                } else {
                                    UnsafeUtil.G(t2, offset(typeAndOffsetAt), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(t2, positionForFieldNumber);
                                    break;
                                }
                            case 18:
                                e2 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readDoubleList(e2);
                                break;
                            case 19:
                                e3 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readFloatList(e3);
                                break;
                            case 20:
                                e4 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readInt64List(e4);
                                break;
                            case 21:
                                e5 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readUInt64List(e5);
                                break;
                            case 22:
                                e6 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readInt32List(e6);
                                break;
                            case 23:
                                e7 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readFixed64List(e7);
                                break;
                            case 24:
                                e8 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readFixed32List(e8);
                                break;
                            case 25:
                                e9 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readBoolList(e9);
                                break;
                            case 26:
                                readStringList(t2, typeAndOffsetAt, reader);
                                break;
                            case 27:
                                readMessageList(t2, typeAndOffsetAt, reader, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite);
                                break;
                            case 28:
                                reader.readBytesList(this.listFieldSchema.e(t2, offset(typeAndOffsetAt)));
                                break;
                            case 29:
                                e10 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readUInt32List(e10);
                                break;
                            case 30:
                                e11 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readEnumList(e11);
                                enumFieldVerifier = getEnumFieldVerifier(positionForFieldNumber);
                                obj = SchemaUtil.A(fieldNumber, e11, enumFieldVerifier, obj, unknownFieldSchema);
                                break;
                            case 31:
                                e12 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readSFixed32List(e12);
                                break;
                            case 32:
                                e13 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readSFixed64List(e13);
                                break;
                            case 33:
                                e14 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readSInt32List(e14);
                                break;
                            case 34:
                                e15 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readSInt64List(e15);
                                break;
                            case 35:
                                e2 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readDoubleList(e2);
                                break;
                            case 36:
                                e3 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readFloatList(e3);
                                break;
                            case 37:
                                e4 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readInt64List(e4);
                                break;
                            case 38:
                                e5 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readUInt64List(e5);
                                break;
                            case 39:
                                e6 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readInt32List(e6);
                                break;
                            case 40:
                                e7 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readFixed64List(e7);
                                break;
                            case 41:
                                e8 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readFixed32List(e8);
                                break;
                            case 42:
                                e9 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readBoolList(e9);
                                break;
                            case 43:
                                e10 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readUInt32List(e10);
                                break;
                            case 44:
                                e11 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readEnumList(e11);
                                enumFieldVerifier = getEnumFieldVerifier(positionForFieldNumber);
                                obj = SchemaUtil.A(fieldNumber, e11, enumFieldVerifier, obj, unknownFieldSchema);
                                break;
                            case 45:
                                e12 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readSFixed32List(e12);
                                break;
                            case 46:
                                e13 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readSFixed64List(e13);
                                break;
                            case 47:
                                e14 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readSInt32List(e14);
                                break;
                            case 48:
                                e15 = this.listFieldSchema.e(t2, offset(typeAndOffsetAt));
                                reader.readSInt64List(e15);
                                break;
                            case 49:
                                readGroupList(t2, offset(typeAndOffsetAt), reader, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite);
                                break;
                            case 50:
                                mergeMap(t2, positionForFieldNumber, getMapFieldDefaultEntry(positionForFieldNumber), extensionRegistryLite, reader);
                                break;
                            case 51:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Double.valueOf(reader.readDouble()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 52:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Float.valueOf(reader.readFloat()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 53:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Long.valueOf(reader.readInt64()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 54:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Long.valueOf(reader.readUInt64()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 55:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Integer.valueOf(reader.readInt32()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 56:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Long.valueOf(reader.readFixed64()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 57:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Integer.valueOf(reader.readFixed32()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 58:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Boolean.valueOf(reader.readBool()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 59:
                                readString(t2, typeAndOffsetAt, reader);
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 60:
                                if (isOneofPresent(t2, fieldNumber, positionForFieldNumber)) {
                                    UnsafeUtil.G(t2, offset(typeAndOffsetAt), Internal.d(UnsafeUtil.u(t2, offset(typeAndOffsetAt)), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite)));
                                } else {
                                    UnsafeUtil.G(t2, offset(typeAndOffsetAt), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(t2, positionForFieldNumber);
                                }
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 61:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), reader.readBytes());
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 62:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Integer.valueOf(reader.readUInt32()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 63:
                                readEnum = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier3 = getEnumFieldVerifier(positionForFieldNumber);
                                if (enumFieldVerifier3 != null && !enumFieldVerifier3.isInRange(readEnum)) {
                                    obj = SchemaUtil.F(fieldNumber, readEnum, obj, unknownFieldSchema);
                                    break;
                                }
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Integer.valueOf(readEnum));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 64:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Integer.valueOf(reader.readSFixed32()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 65:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Long.valueOf(reader.readSFixed64()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 66:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Integer.valueOf(reader.readSInt32()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 67:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), Long.valueOf(reader.readSInt64()));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            case 68:
                                UnsafeUtil.G(t2, offset(typeAndOffsetAt), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite));
                                setOneofPresent(t2, fieldNumber, positionForFieldNumber);
                                break;
                            default:
                                if (obj == null) {
                                    obj = unknownFieldSchema.n();
                                }
                                if (!unknownFieldSchema.m(obj, reader)) {
                                    for (int i2 = this.checkInitializedCount; i2 < this.repeatedFieldOffsetStart; i2++) {
                                        obj = filterMapUnknownEnumValues(t2, this.intArray[i2], obj, unknownFieldSchema);
                                    }
                                    if (obj != null) {
                                        unknownFieldSchema.o(t2, obj);
                                        return;
                                    }
                                    return;
                                }
                                break;
                        }
                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                        if (!unknownFieldSchema.q(reader)) {
                            if (obj == null) {
                                obj = unknownFieldSchema.f(t2);
                            }
                            if (!unknownFieldSchema.m(obj, reader)) {
                                for (int i3 = this.checkInitializedCount; i3 < this.repeatedFieldOffsetStart; i3++) {
                                    obj = filterMapUnknownEnumValues(t2, this.intArray[i3], obj, unknownFieldSchema);
                                }
                                if (obj != null) {
                                    unknownFieldSchema.o(t2, obj);
                                    return;
                                }
                                return;
                            }
                        } else if (!reader.skipField()) {
                            for (int i4 = this.checkInitializedCount; i4 < this.repeatedFieldOffsetStart; i4++) {
                                obj = filterMapUnknownEnumValues(t2, this.intArray[i4], obj, unknownFieldSchema);
                            }
                            if (obj != null) {
                                unknownFieldSchema.o(t2, obj);
                                return;
                            }
                            return;
                        }
                    }
                } else if (fieldNumber == Integer.MAX_VALUE) {
                    for (int i5 = this.checkInitializedCount; i5 < this.repeatedFieldOffsetStart; i5++) {
                        obj = filterMapUnknownEnumValues(t2, this.intArray[i5], obj, unknownFieldSchema);
                    }
                    if (obj != null) {
                        unknownFieldSchema.o(t2, obj);
                        return;
                    }
                    return;
                } else {
                    Object b2 = !this.hasExtensions ? null : extensionSchema.b(extensionRegistryLite, this.defaultInstance, fieldNumber);
                    if (b2 != null) {
                        if (fieldSet == null) {
                            fieldSet = extensionSchema.d(t2);
                        }
                        obj = extensionSchema.g(reader, b2, extensionRegistryLite, fieldSet, obj, unknownFieldSchema);
                    } else if (!unknownFieldSchema.q(reader)) {
                        if (obj == null) {
                            obj = unknownFieldSchema.f(t2);
                        }
                        if (unknownFieldSchema.m(obj, reader)) {
                        }
                    } else if (reader.skipField()) {
                    }
                }
            } catch (Throwable th) {
                for (int i6 = this.checkInitializedCount; i6 < this.repeatedFieldOffsetStart; i6++) {
                    obj = filterMapUnknownEnumValues(t2, this.intArray[i6], obj, unknownFieldSchema);
                }
                if (obj != null) {
                    unknownFieldSchema.o(t2, obj);
                }
                throw th;
            }
        }
    }

    private final <K, V> void mergeMap(Object obj, int i2, Object obj2, ExtensionRegistryLite extensionRegistryLite, Reader reader) {
        long offset = offset(typeAndOffsetAt(i2));
        Object u = UnsafeUtil.u(obj, offset);
        if (u == null) {
            u = this.mapFieldSchema.newMapField(obj2);
            UnsafeUtil.G(obj, offset, u);
        } else if (this.mapFieldSchema.isImmutable(u)) {
            Object newMapField = this.mapFieldSchema.newMapField(obj2);
            this.mapFieldSchema.mergeFrom(newMapField, u);
            UnsafeUtil.G(obj, offset, newMapField);
            u = newMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(u), this.mapFieldSchema.forMapMetadata(obj2), extensionRegistryLite);
    }

    private void mergeMessage(T t2, T t3, int i2) {
        long offset = offset(typeAndOffsetAt(i2));
        if (isFieldPresent(t3, i2)) {
            Object u = UnsafeUtil.u(t2, offset);
            Object u2 = UnsafeUtil.u(t3, offset);
            if (u != null && u2 != null) {
                u2 = Internal.d(u, u2);
            } else if (u2 == null) {
                return;
            }
            UnsafeUtil.G(t2, offset, u2);
            setFieldPresent(t2, i2);
        }
    }

    private void mergeOneofMessage(T t2, T t3, int i2) {
        int typeAndOffsetAt = typeAndOffsetAt(i2);
        int numberAt = numberAt(i2);
        long offset = offset(typeAndOffsetAt);
        if (isOneofPresent(t3, numberAt, i2)) {
            Object u = UnsafeUtil.u(t2, offset);
            Object u2 = UnsafeUtil.u(t3, offset);
            if (u != null && u2 != null) {
                u2 = Internal.d(u, u2);
            } else if (u2 == null) {
                return;
            }
            UnsafeUtil.G(t2, offset, u2);
            setOneofPresent(t2, numberAt, i2);
        }
    }

    private void mergeSingleField(T t2, T t3, int i2) {
        int typeAndOffsetAt = typeAndOffsetAt(i2);
        long offset = offset(typeAndOffsetAt);
        int numberAt = numberAt(i2);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(t3, i2)) {
                    UnsafeUtil.C(t2, offset, UnsafeUtil.p(t3, offset));
                    setFieldPresent(t2, i2);
                    return;
                }
                return;
            case 1:
                if (isFieldPresent(t3, i2)) {
                    UnsafeUtil.D(t2, offset, UnsafeUtil.q(t3, offset));
                    setFieldPresent(t2, i2);
                    return;
                }
                return;
            case 2:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.F(t2, offset, UnsafeUtil.t(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 3:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.F(t2, offset, UnsafeUtil.t(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 4:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.E(t2, offset, UnsafeUtil.r(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 5:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.F(t2, offset, UnsafeUtil.t(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 6:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.E(t2, offset, UnsafeUtil.r(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 7:
                if (isFieldPresent(t3, i2)) {
                    UnsafeUtil.z(t2, offset, UnsafeUtil.m(t3, offset));
                    setFieldPresent(t2, i2);
                    return;
                }
                return;
            case 8:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.G(t2, offset, UnsafeUtil.u(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 9:
            case 17:
                mergeMessage(t2, t3, i2);
                return;
            case 10:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.G(t2, offset, UnsafeUtil.u(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 11:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.E(t2, offset, UnsafeUtil.r(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 12:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.E(t2, offset, UnsafeUtil.r(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 13:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.E(t2, offset, UnsafeUtil.r(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 14:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.F(t2, offset, UnsafeUtil.t(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 15:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.E(t2, offset, UnsafeUtil.r(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 16:
                if (!isFieldPresent(t3, i2)) {
                    return;
                }
                UnsafeUtil.F(t2, offset, UnsafeUtil.t(t3, offset));
                setFieldPresent(t2, i2);
                return;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.d(t2, t3, offset);
                return;
            case 50:
                SchemaUtil.C(this.mapFieldSchema, t2, t3, offset);
                return;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (!isOneofPresent(t3, numberAt, i2)) {
                    return;
                }
                UnsafeUtil.G(t2, offset, UnsafeUtil.u(t3, offset));
                setOneofPresent(t2, numberAt, i2);
                return;
            case 60:
            case 68:
                mergeOneofMessage(t2, t3, i2);
                return;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (!isOneofPresent(t3, numberAt, i2)) {
                    return;
                }
                UnsafeUtil.G(t2, offset, UnsafeUtil.u(t3, offset));
                setOneofPresent(t2, numberAt, i2);
                return;
            default:
                return;
        }
    }

    private int numberAt(int i2) {
        return this.buffer[i2];
    }

    private static long offset(int i2) {
        return i2 & OFFSET_MASK;
    }

    private static <T> boolean oneofBooleanAt(T t2, long j2) {
        return ((Boolean) UnsafeUtil.u(t2, j2)).booleanValue();
    }

    private static <T> double oneofDoubleAt(T t2, long j2) {
        return ((Double) UnsafeUtil.u(t2, j2)).doubleValue();
    }

    private static <T> float oneofFloatAt(T t2, long j2) {
        return ((Float) UnsafeUtil.u(t2, j2)).floatValue();
    }

    private static <T> int oneofIntAt(T t2, long j2) {
        return ((Integer) UnsafeUtil.u(t2, j2)).intValue();
    }

    private static <T> long oneofLongAt(T t2, long j2) {
        return ((Long) UnsafeUtil.u(t2, j2)).longValue();
    }

    private <K, V> int parseMapField(T t2, byte[] bArr, int i2, int i3, int i4, long j2, ArrayDecoders.Registers registers) {
        Unsafe unsafe = UNSAFE;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(i4);
        Object object = unsafe.getObject(t2, j2);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(mapFieldDefaultEntry);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            unsafe.putObject(t2, j2, newMapField);
            object = newMapField;
        }
        return decodeMapEntry(bArr, i2, i3, this.mapFieldSchema.forMapMetadata(mapFieldDefaultEntry), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    private int parseOneofField(T t2, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j2, int i9, ArrayDecoders.Registers registers) {
        Object valueOf;
        Object valueOf2;
        int L;
        long j3;
        int i10;
        Object valueOf3;
        Unsafe unsafe = UNSAFE;
        long j4 = this.buffer[i9 + 2] & OFFSET_MASK;
        switch (i8) {
            case 51:
                if (i6 == 1) {
                    valueOf = Double.valueOf(ArrayDecoders.d(bArr, i2));
                    unsafe.putObject(t2, j2, valueOf);
                    L = i2 + 8;
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            case 52:
                if (i6 == 5) {
                    valueOf2 = Float.valueOf(ArrayDecoders.l(bArr, i2));
                    unsafe.putObject(t2, j2, valueOf2);
                    L = i2 + 4;
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            case 53:
            case 54:
                if (i6 == 0) {
                    L = ArrayDecoders.L(bArr, i2, registers);
                    j3 = registers.long1;
                    valueOf3 = Long.valueOf(j3);
                    unsafe.putObject(t2, j2, valueOf3);
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            case 55:
            case 62:
                if (i6 == 0) {
                    L = ArrayDecoders.I(bArr, i2, registers);
                    i10 = registers.int1;
                    valueOf3 = Integer.valueOf(i10);
                    unsafe.putObject(t2, j2, valueOf3);
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            case 56:
            case 65:
                if (i6 == 1) {
                    valueOf = Long.valueOf(ArrayDecoders.j(bArr, i2));
                    unsafe.putObject(t2, j2, valueOf);
                    L = i2 + 8;
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            case 57:
            case 64:
                if (i6 == 5) {
                    valueOf2 = Integer.valueOf(ArrayDecoders.h(bArr, i2));
                    unsafe.putObject(t2, j2, valueOf2);
                    L = i2 + 4;
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            case 58:
                if (i6 == 0) {
                    L = ArrayDecoders.L(bArr, i2, registers);
                    valueOf3 = Boolean.valueOf(registers.long1 != 0);
                    unsafe.putObject(t2, j2, valueOf3);
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            case 59:
                if (i6 == 2) {
                    L = ArrayDecoders.I(bArr, i2, registers);
                    int i11 = registers.int1;
                    if (i11 == 0) {
                        valueOf3 = "";
                        unsafe.putObject(t2, j2, valueOf3);
                        unsafe.putInt(t2, j4, i5);
                        return L;
                    } else if ((i7 & 536870912) == 0 || Utf8.isValidUtf8(bArr, L, L + i11)) {
                        unsafe.putObject(t2, j2, new String(bArr, L, i11, Internal.f9762a));
                        L += i11;
                        unsafe.putInt(t2, j4, i5);
                        return L;
                    } else {
                        throw InvalidProtocolBufferException.c();
                    }
                }
                return i2;
            case 60:
                if (i6 == 2) {
                    L = ArrayDecoders.p(getMessageFieldSchema(i9), bArr, i2, i3, registers);
                    Object object = unsafe.getInt(t2, j4) == i5 ? unsafe.getObject(t2, j2) : null;
                    valueOf3 = registers.object1;
                    if (object != null) {
                        valueOf3 = Internal.d(object, valueOf3);
                    }
                    unsafe.putObject(t2, j2, valueOf3);
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            case 61:
                if (i6 == 2) {
                    L = ArrayDecoders.b(bArr, i2, registers);
                    valueOf3 = registers.object1;
                    unsafe.putObject(t2, j2, valueOf3);
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            case 63:
                if (i6 == 0) {
                    int I = ArrayDecoders.I(bArr, i2, registers);
                    int i12 = registers.int1;
                    Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i9);
                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i12)) {
                        unsafe.putObject(t2, j2, Integer.valueOf(i12));
                        unsafe.putInt(t2, j4, i5);
                    } else {
                        a(t2).h(i4, Long.valueOf(i12));
                    }
                    return I;
                }
                return i2;
            case 66:
                if (i6 == 0) {
                    L = ArrayDecoders.I(bArr, i2, registers);
                    i10 = CodedInputStream.decodeZigZag32(registers.int1);
                    valueOf3 = Integer.valueOf(i10);
                    unsafe.putObject(t2, j2, valueOf3);
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            case 67:
                if (i6 == 0) {
                    L = ArrayDecoders.L(bArr, i2, registers);
                    j3 = CodedInputStream.decodeZigZag64(registers.long1);
                    valueOf3 = Long.valueOf(j3);
                    unsafe.putObject(t2, j2, valueOf3);
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            case 68:
                if (i6 == 3) {
                    L = ArrayDecoders.n(getMessageFieldSchema(i9), bArr, i2, i3, (i4 & (-8)) | 4, registers);
                    Object object2 = unsafe.getInt(t2, j4) == i5 ? unsafe.getObject(t2, j2) : null;
                    valueOf3 = registers.object1;
                    if (object2 != null) {
                        valueOf3 = Internal.d(object2, valueOf3);
                    }
                    unsafe.putObject(t2, j2, valueOf3);
                    unsafe.putInt(t2, j4, i5);
                    return L;
                }
                return i2;
            default:
                return i2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x020f, code lost:
        if (r0 != r15) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0212, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01c4, code lost:
        if (r0 != r15) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01f2, code lost:
        if (r0 != r15) goto L28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int parseProto3Message(T t2, byte[] bArr, int i2, int i3, ArrayDecoders.Registers registers) {
        byte b2;
        int i4;
        int i5;
        int i6;
        Unsafe unsafe;
        int i7;
        int i8;
        int i9;
        int i10;
        long j2;
        int L;
        long j3;
        Unsafe unsafe2;
        T t3;
        long j4;
        long j5;
        Object d2;
        int i11;
        MessageSchema<T> messageSchema = this;
        T t4 = t2;
        byte[] bArr2 = bArr;
        int i12 = i3;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe3 = UNSAFE;
        int i13 = -1;
        int i14 = i2;
        int i15 = -1;
        int i16 = 0;
        while (i14 < i12) {
            int i17 = i14 + 1;
            byte b3 = bArr2[i14];
            if (b3 < 0) {
                i4 = ArrayDecoders.H(b3, bArr2, i17, registers2);
                b2 = registers2.int1;
            } else {
                b2 = b3;
                i4 = i17;
            }
            int i18 = b2 >>> 3;
            int i19 = b2 & 7;
            int positionForFieldNumber = i18 > i15 ? messageSchema.positionForFieldNumber(i18, i16 / 3) : messageSchema.positionForFieldNumber(i18);
            if (positionForFieldNumber == i13) {
                i5 = i18;
                i6 = i4;
                unsafe = unsafe3;
                i7 = i13;
                i8 = 0;
            } else {
                int i20 = messageSchema.buffer[positionForFieldNumber + 1];
                int type = type(i20);
                long offset = offset(i20);
                if (type <= 17) {
                    switch (type) {
                        case 0:
                            i10 = positionForFieldNumber;
                            if (i19 != 1) {
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i8 = i10;
                                i7 = -1;
                                break;
                            } else {
                                UnsafeUtil.C(t4, offset, ArrayDecoders.d(bArr2, i4));
                                i14 = i4 + 8;
                                i15 = i18;
                                i16 = i10;
                                break;
                            }
                        case 1:
                            i10 = positionForFieldNumber;
                            if (i19 != 5) {
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i8 = i10;
                                i7 = -1;
                                break;
                            } else {
                                UnsafeUtil.D(t4, offset, ArrayDecoders.l(bArr2, i4));
                                i14 = i4 + 4;
                                i15 = i18;
                                i16 = i10;
                                break;
                            }
                        case 2:
                        case 3:
                            j2 = offset;
                            i10 = positionForFieldNumber;
                            if (i19 != 0) {
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i8 = i10;
                                i7 = -1;
                                break;
                            } else {
                                L = ArrayDecoders.L(bArr2, i4, registers2);
                                j3 = registers2.long1;
                                unsafe2 = unsafe3;
                                t3 = t2;
                                unsafe2.putLong(t3, j2, j3);
                                i14 = L;
                                i15 = i18;
                                i16 = i10;
                                break;
                            }
                        case 4:
                        case 11:
                            j4 = offset;
                            i10 = positionForFieldNumber;
                            if (i19 != 0) {
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i8 = i10;
                                i7 = -1;
                                break;
                            } else {
                                i14 = ArrayDecoders.I(bArr2, i4, registers2);
                                i11 = registers2.int1;
                                unsafe3.putInt(t4, j4, i11);
                                i15 = i18;
                                i16 = i10;
                                break;
                            }
                        case 5:
                        case 14:
                            if (i19 != 1) {
                                i8 = positionForFieldNumber;
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i7 = -1;
                                break;
                            } else {
                                i10 = positionForFieldNumber;
                                unsafe3.putLong(t2, offset, ArrayDecoders.j(bArr2, i4));
                                i14 = i4 + 8;
                                i15 = i18;
                                i16 = i10;
                                break;
                            }
                        case 6:
                        case 13:
                            if (i19 != 5) {
                                i8 = positionForFieldNumber;
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i7 = -1;
                                break;
                            } else {
                                unsafe3.putInt(t4, offset, ArrayDecoders.h(bArr2, i4));
                                i14 = i4 + 4;
                                i16 = positionForFieldNumber;
                                i15 = i18;
                                break;
                            }
                        case 7:
                            if (i19 != 0) {
                                i8 = positionForFieldNumber;
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i7 = -1;
                                break;
                            } else {
                                int L2 = ArrayDecoders.L(bArr2, i4, registers2);
                                UnsafeUtil.z(t4, offset, registers2.long1 != 0);
                                i14 = L2;
                                i16 = positionForFieldNumber;
                                i15 = i18;
                                break;
                            }
                        case 8:
                            j5 = offset;
                            if (i19 != 2) {
                                i8 = positionForFieldNumber;
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i7 = -1;
                                break;
                            } else {
                                i14 = (536870912 & i20) == 0 ? ArrayDecoders.C(bArr2, i4, registers2) : ArrayDecoders.F(bArr2, i4, registers2);
                                d2 = registers2.object1;
                                unsafe3.putObject(t4, j5, d2);
                                i16 = positionForFieldNumber;
                                i15 = i18;
                                break;
                            }
                        case 9:
                            j5 = offset;
                            if (i19 != 2) {
                                i8 = positionForFieldNumber;
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i7 = -1;
                                break;
                            } else {
                                i14 = ArrayDecoders.p(messageSchema.getMessageFieldSchema(positionForFieldNumber), bArr2, i4, i12, registers2);
                                Object object = unsafe3.getObject(t4, j5);
                                d2 = object == null ? registers2.object1 : Internal.d(object, registers2.object1);
                                unsafe3.putObject(t4, j5, d2);
                                i16 = positionForFieldNumber;
                                i15 = i18;
                                break;
                            }
                        case 10:
                            j5 = offset;
                            if (i19 != 2) {
                                i8 = positionForFieldNumber;
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i7 = -1;
                                break;
                            } else {
                                i14 = ArrayDecoders.b(bArr2, i4, registers2);
                                d2 = registers2.object1;
                                unsafe3.putObject(t4, j5, d2);
                                i16 = positionForFieldNumber;
                                i15 = i18;
                                break;
                            }
                        case 12:
                            j4 = offset;
                            i10 = positionForFieldNumber;
                            if (i19 != 0) {
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i8 = i10;
                                i7 = -1;
                                break;
                            } else {
                                i14 = ArrayDecoders.I(bArr2, i4, registers2);
                                i11 = registers2.int1;
                                unsafe3.putInt(t4, j4, i11);
                                i15 = i18;
                                i16 = i10;
                                break;
                            }
                        case 15:
                            j4 = offset;
                            i10 = positionForFieldNumber;
                            if (i19 != 0) {
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i8 = i10;
                                i7 = -1;
                                break;
                            } else {
                                i14 = ArrayDecoders.I(bArr2, i4, registers2);
                                i11 = CodedInputStream.decodeZigZag32(registers2.int1);
                                unsafe3.putInt(t4, j4, i11);
                                i15 = i18;
                                i16 = i10;
                                break;
                            }
                        case 16:
                            if (i19 != 0) {
                                i8 = positionForFieldNumber;
                                i5 = i18;
                                i9 = i4;
                                unsafe = unsafe3;
                                i7 = -1;
                                break;
                            } else {
                                L = ArrayDecoders.L(bArr2, i4, registers2);
                                unsafe2 = unsafe3;
                                j2 = offset;
                                t3 = t2;
                                i10 = positionForFieldNumber;
                                j3 = CodedInputStream.decodeZigZag64(registers2.long1);
                                unsafe2.putLong(t3, j2, j3);
                                i14 = L;
                                i15 = i18;
                                i16 = i10;
                                break;
                            }
                        default:
                            i8 = positionForFieldNumber;
                            i5 = i18;
                            i9 = i4;
                            unsafe = unsafe3;
                            i7 = -1;
                            break;
                    }
                    i13 = -1;
                } else if (type != 27) {
                    i8 = positionForFieldNumber;
                    if (type <= 49) {
                        i5 = i18;
                        int i21 = i4;
                        unsafe = unsafe3;
                        i7 = -1;
                        i14 = parseRepeatedField(t2, bArr, i4, i3, b2, i18, i19, i8, i20, type, offset, registers);
                    } else {
                        i5 = i18;
                        i9 = i4;
                        unsafe = unsafe3;
                        i7 = -1;
                        if (type != 50) {
                            i14 = parseOneofField(t2, bArr, i9, i3, b2, i5, i19, i20, type, offset, i8, registers);
                        } else if (i19 == 2) {
                            i14 = parseMapField(t2, bArr, i9, i3, i8, offset, registers);
                        }
                    }
                    messageSchema = this;
                    t4 = t2;
                    bArr2 = bArr;
                    i12 = i3;
                    registers2 = registers;
                    unsafe3 = unsafe;
                    i16 = i8;
                    i15 = i5;
                    i13 = i7;
                } else if (i19 == 2) {
                    Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe3.getObject(t4, offset);
                    if (!protobufList.isModifiable()) {
                        int size = protobufList.size();
                        protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
                        unsafe3.putObject(t4, offset, protobufList);
                    }
                    i14 = ArrayDecoders.q(messageSchema.getMessageFieldSchema(positionForFieldNumber), b2, bArr, i4, i3, protobufList, registers);
                    i15 = i18;
                    i16 = positionForFieldNumber;
                    i13 = -1;
                } else {
                    i8 = positionForFieldNumber;
                    i5 = i18;
                    i9 = i4;
                    unsafe = unsafe3;
                    i7 = -1;
                }
                i6 = i9;
            }
            i14 = ArrayDecoders.G(b2, bArr, i6, i3, a(t2), registers);
            messageSchema = this;
            t4 = t2;
            bArr2 = bArr;
            i12 = i3;
            registers2 = registers;
            unsafe3 = unsafe;
            i16 = i8;
            i15 = i5;
            i13 = i7;
        }
        if (i14 == i12) {
            return i14;
        }
        throw InvalidProtocolBufferException.g();
    }

    private int parseRepeatedField(T t2, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, long j2, int i8, long j3, ArrayDecoders.Registers registers) {
        int J;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe.getObject(t2, j3);
        if (!protobufList.isModifiable()) {
            int size = protobufList.size();
            protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
            unsafe.putObject(t2, j3, protobufList);
        }
        switch (i8) {
            case 18:
            case 35:
                if (i6 == 2) {
                    return ArrayDecoders.s(bArr, i2, protobufList, registers);
                }
                if (i6 == 1) {
                    return ArrayDecoders.e(i4, bArr, i2, i3, protobufList, registers);
                }
                break;
            case 19:
            case 36:
                if (i6 == 2) {
                    return ArrayDecoders.v(bArr, i2, protobufList, registers);
                }
                if (i6 == 5) {
                    return ArrayDecoders.m(i4, bArr, i2, i3, protobufList, registers);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i6 == 2) {
                    return ArrayDecoders.z(bArr, i2, protobufList, registers);
                }
                if (i6 == 0) {
                    return ArrayDecoders.M(i4, bArr, i2, i3, protobufList, registers);
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i6 == 2) {
                    return ArrayDecoders.y(bArr, i2, protobufList, registers);
                }
                if (i6 == 0) {
                    return ArrayDecoders.J(i4, bArr, i2, i3, protobufList, registers);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i6 == 2) {
                    return ArrayDecoders.u(bArr, i2, protobufList, registers);
                }
                if (i6 == 1) {
                    return ArrayDecoders.k(i4, bArr, i2, i3, protobufList, registers);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i6 == 2) {
                    return ArrayDecoders.t(bArr, i2, protobufList, registers);
                }
                if (i6 == 5) {
                    return ArrayDecoders.i(i4, bArr, i2, i3, protobufList, registers);
                }
                break;
            case 25:
            case 42:
                if (i6 == 2) {
                    return ArrayDecoders.r(bArr, i2, protobufList, registers);
                }
                if (i6 == 0) {
                    return ArrayDecoders.a(i4, bArr, i2, i3, protobufList, registers);
                }
                break;
            case 26:
                if (i6 == 2) {
                    int i9 = ((j2 & 536870912) > 0L ? 1 : ((j2 & 536870912) == 0L ? 0 : -1));
                    Internal.ProtobufList protobufList2 = protobufList;
                    return i9 == 0 ? ArrayDecoders.D(i4, bArr, i2, i3, protobufList2, registers) : ArrayDecoders.E(i4, bArr, i2, i3, protobufList2, registers);
                }
                break;
            case 27:
                if (i6 == 2) {
                    return ArrayDecoders.q(getMessageFieldSchema(i7), i4, bArr, i2, i3, protobufList, registers);
                }
                break;
            case 28:
                if (i6 == 2) {
                    return ArrayDecoders.c(i4, bArr, i2, i3, protobufList, registers);
                }
                break;
            case 30:
            case 44:
                if (i6 == 2) {
                    J = ArrayDecoders.y(bArr, i2, protobufList, registers);
                } else if (i6 == 0) {
                    J = ArrayDecoders.J(i4, bArr, i2, i3, protobufList, registers);
                }
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t2;
                UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
                if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
                    unknownFieldSetLite = null;
                }
                UnknownFieldSetLite unknownFieldSetLite2 = (UnknownFieldSetLite) SchemaUtil.A(i5, protobufList, getEnumFieldVerifier(i7), unknownFieldSetLite, this.unknownFieldSchema);
                if (unknownFieldSetLite2 != null) {
                    generatedMessageLite.unknownFields = unknownFieldSetLite2;
                }
                return J;
            case 33:
            case 47:
                if (i6 == 2) {
                    return ArrayDecoders.w(bArr, i2, protobufList, registers);
                }
                if (i6 == 0) {
                    return ArrayDecoders.A(i4, bArr, i2, i3, protobufList, registers);
                }
                break;
            case 34:
            case 48:
                if (i6 == 2) {
                    return ArrayDecoders.x(bArr, i2, protobufList, registers);
                }
                if (i6 == 0) {
                    return ArrayDecoders.B(i4, bArr, i2, i3, protobufList, registers);
                }
                break;
            case 49:
                if (i6 == 3) {
                    return ArrayDecoders.o(getMessageFieldSchema(i7), i4, bArr, i2, i3, protobufList, registers);
                }
                break;
        }
        return i2;
    }

    private int positionForFieldNumber(int i2) {
        if (i2 < this.minFieldNumber || i2 > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i2, 0);
    }

    private int positionForFieldNumber(int i2, int i3) {
        if (i2 < this.minFieldNumber || i2 > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i2, i3);
    }

    private int presenceMaskAndOffsetAt(int i2) {
        return this.buffer[i2 + 2];
    }

    private <E> void readGroupList(Object obj, long j2, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readGroupList(this.listFieldSchema.e(obj, j2), schema, extensionRegistryLite);
    }

    private <E> void readMessageList(Object obj, int i2, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readMessageList(this.listFieldSchema.e(obj, offset(i2)), schema, extensionRegistryLite);
    }

    private void readString(Object obj, int i2, Reader reader) {
        long offset;
        Object readBytes;
        if (isEnforceUtf8(i2)) {
            offset = offset(i2);
            readBytes = reader.readStringRequireUtf8();
        } else if (this.lite) {
            offset = offset(i2);
            readBytes = reader.readString();
        } else {
            offset = offset(i2);
            readBytes = reader.readBytes();
        }
        UnsafeUtil.G(obj, offset, readBytes);
    }

    private void readStringList(Object obj, int i2, Reader reader) {
        if (isEnforceUtf8(i2)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.e(obj, offset(i2)));
        } else {
            reader.readStringList(this.listFieldSchema.e(obj, offset(i2)));
        }
    }

    private static Field reflectField(Class<?> cls, String str) {
        Field[] declaredFields;
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            for (Field field : cls.getDeclaredFields()) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private void setFieldPresent(T t2, int i2) {
        if (this.proto3) {
            return;
        }
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i2);
        long j2 = presenceMaskAndOffsetAt & OFFSET_MASK;
        UnsafeUtil.E(t2, j2, UnsafeUtil.r(t2, j2) | (1 << (presenceMaskAndOffsetAt >>> 20)));
    }

    private void setOneofPresent(T t2, int i2, int i3) {
        UnsafeUtil.E(t2, presenceMaskAndOffsetAt(i3) & OFFSET_MASK, i2);
    }

    private int slowPositionForFieldNumber(int i2, int i3) {
        int length = (this.buffer.length / 3) - 1;
        while (i3 <= length) {
            int i4 = (length + i3) >>> 1;
            int i5 = i4 * 3;
            int numberAt = numberAt(i5);
            if (i2 == numberAt) {
                return i5;
            }
            if (i2 < numberAt) {
                length = i4 - 1;
            } else {
                i3 = i4 + 1;
            }
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void storeFieldData(FieldInfo fieldInfo, int[] iArr, int i2, boolean z, Object[] objArr) {
        int y;
        int y2;
        int i3;
        int i4;
        int i5;
        int i6;
        OneofInfo oneof = fieldInfo.getOneof();
        if (oneof == null) {
            FieldType type = fieldInfo.getType();
            y = (int) UnsafeUtil.y(fieldInfo.getField());
            int id = type.id();
            if (!z && !type.isList() && !type.isMap()) {
                i4 = Integer.numberOfTrailingZeros(fieldInfo.getPresenceMask());
                i3 = id;
                i5 = y;
                i6 = (int) UnsafeUtil.y(fieldInfo.getPresenceField());
            } else if (fieldInfo.getCachedSizeField() == null) {
                i4 = 0;
                i3 = id;
                i5 = y;
                i6 = 0;
            } else {
                y2 = (int) UnsafeUtil.y(fieldInfo.getCachedSizeField());
                i3 = id;
            }
            iArr[i2] = fieldInfo.getFieldNumber();
            iArr[i2 + 1] = (i3 << 20) | (fieldInfo.isRequired() ? REQUIRED_MASK : 0) | (!fieldInfo.isEnforceUtf8() ? 536870912 : 0) | i5;
            iArr[i2 + 2] = (i4 << 20) | i6;
            Class<?> messageFieldClass = fieldInfo.getMessageFieldClass();
            if (fieldInfo.getMapDefaultEntry() != null) {
                if (messageFieldClass != null) {
                    objArr[((i2 / 3) * 2) + 1] = messageFieldClass;
                    return;
                } else if (fieldInfo.getEnumVerifier() != null) {
                    objArr[((i2 / 3) * 2) + 1] = fieldInfo.getEnumVerifier();
                    return;
                } else {
                    return;
                }
            }
            int i7 = (i2 / 3) * 2;
            objArr[i7] = fieldInfo.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objArr[i7 + 1] = messageFieldClass;
                return;
            } else if (fieldInfo.getEnumVerifier() != null) {
                objArr[i7 + 1] = fieldInfo.getEnumVerifier();
                return;
            } else {
                return;
            }
        }
        i3 = fieldInfo.getType().id() + 51;
        y = (int) UnsafeUtil.y(oneof.getValueField());
        y2 = (int) UnsafeUtil.y(oneof.getCaseField());
        i5 = y;
        i6 = y2;
        i4 = 0;
        iArr[i2] = fieldInfo.getFieldNumber();
        if (!fieldInfo.isEnforceUtf8()) {
        }
        iArr[i2 + 1] = (i3 << 20) | (fieldInfo.isRequired() ? REQUIRED_MASK : 0) | (!fieldInfo.isEnforceUtf8() ? 536870912 : 0) | i5;
        iArr[i2 + 2] = (i4 << 20) | i6;
        Class<?> messageFieldClass2 = fieldInfo.getMessageFieldClass();
        if (fieldInfo.getMapDefaultEntry() != null) {
        }
    }

    private static int type(int i2) {
        return (i2 & FIELD_TYPE_MASK) >>> 20;
    }

    private int typeAndOffsetAt(int i2) {
        return this.buffer[i2 + 1];
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x049e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrderProto2(T t2, Writer writer) {
        Iterator it;
        Map.Entry entry;
        int length;
        int i2;
        Map.Entry entry2;
        int i3;
        if (this.hasExtensions) {
            FieldSet c2 = this.extensionSchema.c(t2);
            if (!c2.l()) {
                it = c2.iterator();
                entry = (Map.Entry) it.next();
                int i4 = -1;
                length = this.buffer.length;
                Unsafe unsafe = UNSAFE;
                i2 = 0;
                int i5 = 0;
                while (i2 < length) {
                    int typeAndOffsetAt = typeAndOffsetAt(i2);
                    int numberAt = numberAt(i2);
                    int type = type(typeAndOffsetAt);
                    if (this.proto3 || type > 17) {
                        entry2 = entry;
                        i3 = 0;
                    } else {
                        int i6 = this.buffer[i2 + 2];
                        int i7 = i6 & OFFSET_MASK;
                        Map.Entry entry3 = entry;
                        if (i7 != i4) {
                            i5 = unsafe.getInt(t2, i7);
                            i4 = i7;
                        }
                        i3 = 1 << (i6 >>> 20);
                        entry2 = entry3;
                    }
                    while (entry2 != null && this.extensionSchema.a(entry2) <= numberAt) {
                        this.extensionSchema.j(writer, entry2);
                        entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    Map.Entry entry4 = entry2;
                    int i8 = i4;
                    long offset = offset(typeAndOffsetAt);
                    switch (type) {
                        case 0:
                            if ((i3 & i5) != 0) {
                                writer.writeDouble(numberAt, doubleAt(t2, offset));
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 1:
                            if ((i3 & i5) != 0) {
                                writer.writeFloat(numberAt, floatAt(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 2:
                            if ((i3 & i5) != 0) {
                                writer.writeInt64(numberAt, unsafe.getLong(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 3:
                            if ((i3 & i5) != 0) {
                                writer.writeUInt64(numberAt, unsafe.getLong(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 4:
                            if ((i3 & i5) != 0) {
                                writer.writeInt32(numberAt, unsafe.getInt(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 5:
                            if ((i3 & i5) != 0) {
                                writer.writeFixed64(numberAt, unsafe.getLong(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 6:
                            if ((i3 & i5) != 0) {
                                writer.writeFixed32(numberAt, unsafe.getInt(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 7:
                            if ((i3 & i5) != 0) {
                                writer.writeBool(numberAt, booleanAt(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 8:
                            if ((i3 & i5) != 0) {
                                writeString(numberAt, unsafe.getObject(t2, offset), writer);
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 9:
                            if ((i3 & i5) != 0) {
                                writer.writeMessage(numberAt, unsafe.getObject(t2, offset), getMessageFieldSchema(i2));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 10:
                            if ((i3 & i5) != 0) {
                                writer.writeBytes(numberAt, (ByteString) unsafe.getObject(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 11:
                            if ((i3 & i5) != 0) {
                                writer.writeUInt32(numberAt, unsafe.getInt(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 12:
                            if ((i3 & i5) != 0) {
                                writer.writeEnum(numberAt, unsafe.getInt(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 13:
                            if ((i3 & i5) != 0) {
                                writer.writeSFixed32(numberAt, unsafe.getInt(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 14:
                            if ((i3 & i5) != 0) {
                                writer.writeSFixed64(numberAt, unsafe.getLong(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 15:
                            if ((i3 & i5) != 0) {
                                writer.writeSInt32(numberAt, unsafe.getInt(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 16:
                            if ((i3 & i5) != 0) {
                                writer.writeSInt64(numberAt, unsafe.getLong(t2, offset));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 17:
                            if ((i3 & i5) != 0) {
                                writer.writeGroup(numberAt, unsafe.getObject(t2, offset), getMessageFieldSchema(i2));
                            } else {
                                continue;
                            }
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, getMessageFieldSchema(i2));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, false);
                            continue;
                            i2 += 3;
                            i4 = i8;
                            entry = entry4;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(i2), (List) unsafe.getObject(t2, offset), writer, getMessageFieldSchema(i2));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, unsafe.getObject(t2, offset), i2);
                            break;
                        case 51:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeDouble(numberAt, oneofDoubleAt(t2, offset));
                                break;
                            }
                            break;
                        case 52:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeFloat(numberAt, oneofFloatAt(t2, offset));
                                break;
                            }
                            break;
                        case 53:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeInt64(numberAt, oneofLongAt(t2, offset));
                                break;
                            }
                            break;
                        case 54:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeUInt64(numberAt, oneofLongAt(t2, offset));
                                break;
                            }
                            break;
                        case 55:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeInt32(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 56:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeFixed64(numberAt, oneofLongAt(t2, offset));
                                break;
                            }
                            break;
                        case 57:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeFixed32(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 58:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeBool(numberAt, oneofBooleanAt(t2, offset));
                                break;
                            }
                            break;
                        case 59:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writeString(numberAt, unsafe.getObject(t2, offset), writer);
                                break;
                            }
                            break;
                        case 60:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeMessage(numberAt, unsafe.getObject(t2, offset), getMessageFieldSchema(i2));
                                break;
                            }
                            break;
                        case 61:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeBytes(numberAt, (ByteString) unsafe.getObject(t2, offset));
                                break;
                            }
                            break;
                        case 62:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeUInt32(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 63:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeEnum(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 64:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeSFixed32(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 65:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeSFixed64(numberAt, oneofLongAt(t2, offset));
                                break;
                            }
                            break;
                        case 66:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeSInt32(numberAt, oneofIntAt(t2, offset));
                                break;
                            }
                            break;
                        case 67:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeSInt64(numberAt, oneofLongAt(t2, offset));
                                break;
                            }
                            break;
                        case 68:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                writer.writeGroup(numberAt, unsafe.getObject(t2, offset), getMessageFieldSchema(i2));
                                break;
                            }
                            break;
                    }
                    i2 += 3;
                    i4 = i8;
                    entry = entry4;
                }
                while (entry != null) {
                    this.extensionSchema.j(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                writeUnknownInMessageTo(this.unknownFieldSchema, t2, writer);
            }
        }
        it = null;
        entry = null;
        int i42 = -1;
        length = this.buffer.length;
        Unsafe unsafe2 = UNSAFE;
        i2 = 0;
        int i52 = 0;
        while (i2 < length) {
        }
        while (entry != null) {
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, t2, writer);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0528  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrderProto3(T t2, Writer writer) {
        Iterator it;
        Map.Entry entry;
        int length;
        int i2;
        double doubleAt;
        float floatAt;
        long longAt;
        long longAt2;
        int intAt;
        long longAt3;
        int intAt2;
        boolean booleanAt;
        int intAt3;
        int intAt4;
        int intAt5;
        long longAt4;
        int intAt6;
        long longAt5;
        if (this.hasExtensions) {
            FieldSet c2 = this.extensionSchema.c(t2);
            if (!c2.l()) {
                it = c2.iterator();
                entry = (Map.Entry) it.next();
                length = this.buffer.length;
                for (i2 = 0; i2 < length; i2 += 3) {
                    int typeAndOffsetAt = typeAndOffsetAt(i2);
                    int numberAt = numberAt(i2);
                    while (entry != null && this.extensionSchema.a(entry) <= numberAt) {
                        this.extensionSchema.j(writer, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    switch (type(typeAndOffsetAt)) {
                        case 0:
                            if (isFieldPresent(t2, i2)) {
                                doubleAt = doubleAt(t2, offset(typeAndOffsetAt));
                                writer.writeDouble(numberAt, doubleAt);
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (isFieldPresent(t2, i2)) {
                                floatAt = floatAt(t2, offset(typeAndOffsetAt));
                                writer.writeFloat(numberAt, floatAt);
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (isFieldPresent(t2, i2)) {
                                longAt = longAt(t2, offset(typeAndOffsetAt));
                                writer.writeInt64(numberAt, longAt);
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (isFieldPresent(t2, i2)) {
                                longAt2 = longAt(t2, offset(typeAndOffsetAt));
                                writer.writeUInt64(numberAt, longAt2);
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (isFieldPresent(t2, i2)) {
                                intAt = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeInt32(numberAt, intAt);
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (isFieldPresent(t2, i2)) {
                                longAt3 = longAt(t2, offset(typeAndOffsetAt));
                                writer.writeFixed64(numberAt, longAt3);
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (isFieldPresent(t2, i2)) {
                                intAt2 = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeFixed32(numberAt, intAt2);
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (isFieldPresent(t2, i2)) {
                                booleanAt = booleanAt(t2, offset(typeAndOffsetAt));
                                writer.writeBool(numberAt, booleanAt);
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (!isFieldPresent(t2, i2)) {
                                break;
                            }
                            writeString(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer);
                            break;
                        case 9:
                            if (!isFieldPresent(t2, i2)) {
                                break;
                            }
                            writer.writeMessage(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), getMessageFieldSchema(i2));
                            break;
                        case 10:
                            if (!isFieldPresent(t2, i2)) {
                                break;
                            }
                            writer.writeBytes(numberAt, (ByteString) UnsafeUtil.u(t2, offset(typeAndOffsetAt)));
                            break;
                        case 11:
                            if (isFieldPresent(t2, i2)) {
                                intAt3 = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeUInt32(numberAt, intAt3);
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (isFieldPresent(t2, i2)) {
                                intAt4 = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeEnum(numberAt, intAt4);
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (isFieldPresent(t2, i2)) {
                                intAt5 = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeSFixed32(numberAt, intAt5);
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (isFieldPresent(t2, i2)) {
                                longAt4 = longAt(t2, offset(typeAndOffsetAt));
                                writer.writeSFixed64(numberAt, longAt4);
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (isFieldPresent(t2, i2)) {
                                intAt6 = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeSInt32(numberAt, intAt6);
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (isFieldPresent(t2, i2)) {
                                longAt5 = longAt(t2, offset(typeAndOffsetAt));
                                writer.writeSInt64(numberAt, longAt5);
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (!isFieldPresent(t2, i2)) {
                                break;
                            }
                            writer.writeGroup(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), getMessageFieldSchema(i2));
                            break;
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(i2));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(i2), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(i2));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), i2);
                            break;
                        case 51:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                doubleAt = oneofDoubleAt(t2, offset(typeAndOffsetAt));
                                writer.writeDouble(numberAt, doubleAt);
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                floatAt = oneofFloatAt(t2, offset(typeAndOffsetAt));
                                writer.writeFloat(numberAt, floatAt);
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                longAt = oneofLongAt(t2, offset(typeAndOffsetAt));
                                writer.writeInt64(numberAt, longAt);
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                longAt2 = oneofLongAt(t2, offset(typeAndOffsetAt));
                                writer.writeUInt64(numberAt, longAt2);
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                intAt = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeInt32(numberAt, intAt);
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                longAt3 = oneofLongAt(t2, offset(typeAndOffsetAt));
                                writer.writeFixed64(numberAt, longAt3);
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                intAt2 = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeFixed32(numberAt, intAt2);
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                booleanAt = oneofBooleanAt(t2, offset(typeAndOffsetAt));
                                writer.writeBool(numberAt, booleanAt);
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (!isOneofPresent(t2, numberAt, i2)) {
                                break;
                            }
                            writeString(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer);
                            break;
                        case 60:
                            if (!isOneofPresent(t2, numberAt, i2)) {
                                break;
                            }
                            writer.writeMessage(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), getMessageFieldSchema(i2));
                            break;
                        case 61:
                            if (!isOneofPresent(t2, numberAt, i2)) {
                                break;
                            }
                            writer.writeBytes(numberAt, (ByteString) UnsafeUtil.u(t2, offset(typeAndOffsetAt)));
                            break;
                        case 62:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                intAt3 = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeUInt32(numberAt, intAt3);
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                intAt4 = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeEnum(numberAt, intAt4);
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                intAt5 = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeSFixed32(numberAt, intAt5);
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                longAt4 = oneofLongAt(t2, offset(typeAndOffsetAt));
                                writer.writeSFixed64(numberAt, longAt4);
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                intAt6 = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeSInt32(numberAt, intAt6);
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (isOneofPresent(t2, numberAt, i2)) {
                                longAt5 = oneofLongAt(t2, offset(typeAndOffsetAt));
                                writer.writeSInt64(numberAt, longAt5);
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (!isOneofPresent(t2, numberAt, i2)) {
                                break;
                            }
                            writer.writeGroup(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), getMessageFieldSchema(i2));
                            break;
                    }
                }
                while (entry != null) {
                    this.extensionSchema.j(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                writeUnknownInMessageTo(this.unknownFieldSchema, t2, writer);
            }
        }
        it = null;
        entry = null;
        length = this.buffer.length;
        while (i2 < length) {
        }
        while (entry != null) {
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, t2, writer);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x052e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInDescendingOrder(T t2, Writer writer) {
        Iterator it;
        Map.Entry entry;
        int length;
        double doubleAt;
        float floatAt;
        long longAt;
        long longAt2;
        int intAt;
        long longAt3;
        int intAt2;
        boolean booleanAt;
        int intAt3;
        int intAt4;
        int intAt5;
        long longAt4;
        int intAt6;
        long longAt5;
        writeUnknownInMessageTo(this.unknownFieldSchema, t2, writer);
        if (this.hasExtensions) {
            FieldSet c2 = this.extensionSchema.c(t2);
            if (!c2.l()) {
                it = c2.j();
                entry = (Map.Entry) it.next();
                for (length = this.buffer.length - 3; length >= 0; length -= 3) {
                    int typeAndOffsetAt = typeAndOffsetAt(length);
                    int numberAt = numberAt(length);
                    while (entry != null && this.extensionSchema.a(entry) > numberAt) {
                        this.extensionSchema.j(writer, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    switch (type(typeAndOffsetAt)) {
                        case 0:
                            if (isFieldPresent(t2, length)) {
                                doubleAt = doubleAt(t2, offset(typeAndOffsetAt));
                                writer.writeDouble(numberAt, doubleAt);
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (isFieldPresent(t2, length)) {
                                floatAt = floatAt(t2, offset(typeAndOffsetAt));
                                writer.writeFloat(numberAt, floatAt);
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (isFieldPresent(t2, length)) {
                                longAt = longAt(t2, offset(typeAndOffsetAt));
                                writer.writeInt64(numberAt, longAt);
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (isFieldPresent(t2, length)) {
                                longAt2 = longAt(t2, offset(typeAndOffsetAt));
                                writer.writeUInt64(numberAt, longAt2);
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (isFieldPresent(t2, length)) {
                                intAt = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeInt32(numberAt, intAt);
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (isFieldPresent(t2, length)) {
                                longAt3 = longAt(t2, offset(typeAndOffsetAt));
                                writer.writeFixed64(numberAt, longAt3);
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (isFieldPresent(t2, length)) {
                                intAt2 = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeFixed32(numberAt, intAt2);
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (isFieldPresent(t2, length)) {
                                booleanAt = booleanAt(t2, offset(typeAndOffsetAt));
                                writer.writeBool(numberAt, booleanAt);
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (!isFieldPresent(t2, length)) {
                                break;
                            }
                            writeString(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer);
                            break;
                        case 9:
                            if (!isFieldPresent(t2, length)) {
                                break;
                            }
                            writer.writeMessage(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                            break;
                        case 10:
                            if (!isFieldPresent(t2, length)) {
                                break;
                            }
                            writer.writeBytes(numberAt, (ByteString) UnsafeUtil.u(t2, offset(typeAndOffsetAt)));
                            break;
                        case 11:
                            if (isFieldPresent(t2, length)) {
                                intAt3 = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeUInt32(numberAt, intAt3);
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (isFieldPresent(t2, length)) {
                                intAt4 = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeEnum(numberAt, intAt4);
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (isFieldPresent(t2, length)) {
                                intAt5 = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeSFixed32(numberAt, intAt5);
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (isFieldPresent(t2, length)) {
                                longAt4 = longAt(t2, offset(typeAndOffsetAt));
                                writer.writeSFixed64(numberAt, longAt4);
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (isFieldPresent(t2, length)) {
                                intAt6 = intAt(t2, offset(typeAndOffsetAt));
                                writer.writeSInt32(numberAt, intAt6);
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (isFieldPresent(t2, length)) {
                                longAt5 = longAt(t2, offset(typeAndOffsetAt));
                                writer.writeSInt64(numberAt, longAt5);
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (!isFieldPresent(t2, length)) {
                                break;
                            }
                            writer.writeGroup(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                            break;
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(length), (List) UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), length);
                            break;
                        case 51:
                            if (isOneofPresent(t2, numberAt, length)) {
                                doubleAt = oneofDoubleAt(t2, offset(typeAndOffsetAt));
                                writer.writeDouble(numberAt, doubleAt);
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (isOneofPresent(t2, numberAt, length)) {
                                floatAt = oneofFloatAt(t2, offset(typeAndOffsetAt));
                                writer.writeFloat(numberAt, floatAt);
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (isOneofPresent(t2, numberAt, length)) {
                                longAt = oneofLongAt(t2, offset(typeAndOffsetAt));
                                writer.writeInt64(numberAt, longAt);
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (isOneofPresent(t2, numberAt, length)) {
                                longAt2 = oneofLongAt(t2, offset(typeAndOffsetAt));
                                writer.writeUInt64(numberAt, longAt2);
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (isOneofPresent(t2, numberAt, length)) {
                                intAt = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeInt32(numberAt, intAt);
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (isOneofPresent(t2, numberAt, length)) {
                                longAt3 = oneofLongAt(t2, offset(typeAndOffsetAt));
                                writer.writeFixed64(numberAt, longAt3);
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (isOneofPresent(t2, numberAt, length)) {
                                intAt2 = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeFixed32(numberAt, intAt2);
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (isOneofPresent(t2, numberAt, length)) {
                                booleanAt = oneofBooleanAt(t2, offset(typeAndOffsetAt));
                                writer.writeBool(numberAt, booleanAt);
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (!isOneofPresent(t2, numberAt, length)) {
                                break;
                            }
                            writeString(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), writer);
                            break;
                        case 60:
                            if (!isOneofPresent(t2, numberAt, length)) {
                                break;
                            }
                            writer.writeMessage(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                            break;
                        case 61:
                            if (!isOneofPresent(t2, numberAt, length)) {
                                break;
                            }
                            writer.writeBytes(numberAt, (ByteString) UnsafeUtil.u(t2, offset(typeAndOffsetAt)));
                            break;
                        case 62:
                            if (isOneofPresent(t2, numberAt, length)) {
                                intAt3 = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeUInt32(numberAt, intAt3);
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (isOneofPresent(t2, numberAt, length)) {
                                intAt4 = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeEnum(numberAt, intAt4);
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (isOneofPresent(t2, numberAt, length)) {
                                intAt5 = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeSFixed32(numberAt, intAt5);
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (isOneofPresent(t2, numberAt, length)) {
                                longAt4 = oneofLongAt(t2, offset(typeAndOffsetAt));
                                writer.writeSFixed64(numberAt, longAt4);
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (isOneofPresent(t2, numberAt, length)) {
                                intAt6 = oneofIntAt(t2, offset(typeAndOffsetAt));
                                writer.writeSInt32(numberAt, intAt6);
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (isOneofPresent(t2, numberAt, length)) {
                                longAt5 = oneofLongAt(t2, offset(typeAndOffsetAt));
                                writer.writeSInt64(numberAt, longAt5);
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (!isOneofPresent(t2, numberAt, length)) {
                                break;
                            }
                            writer.writeGroup(numberAt, UnsafeUtil.u(t2, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                            break;
                    }
                }
                while (entry != null) {
                    this.extensionSchema.j(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
            }
        }
        it = null;
        entry = null;
        while (length >= 0) {
        }
        while (entry != null) {
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int i2, Object obj, int i3) {
        if (obj != null) {
            writer.writeMap(i2, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i3)), this.mapFieldSchema.forMapData(obj));
        }
    }

    private void writeString(int i2, Object obj, Writer writer) {
        if (obj instanceof String) {
            writer.writeString(i2, (String) obj);
        } else {
            writer.writeBytes(i2, (ByteString) obj);
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t2, Writer writer) {
        unknownFieldSchema.t(unknownFieldSchema.g(t2), writer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0340, code lost:
        if (r0 != r11) goto L202;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0342, code lost:
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r9 = r36;
        r1 = r17;
        r3 = r18;
        r7 = r19;
        r2 = r20;
        r6 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0389, code lost:
        if (r0 != r15) goto L202;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x03aa, code lost:
        if (r0 != r15) goto L202;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x03ad, code lost:
        r2 = r0;
        r8 = r18;
        r0 = r35;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int e(Object obj, byte[] bArr, int i2, int i3, int i4, ArrayDecoders.Registers registers) {
        Unsafe unsafe;
        int i5;
        MessageSchema<T> messageSchema;
        Object obj2;
        byte b2;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        char c2;
        int i17;
        int i18;
        int i19;
        long j2;
        int i20;
        long j3;
        int L;
        long j4;
        int i21;
        long j5;
        Object obj3;
        MessageSchema<T> messageSchema2 = this;
        Object obj4 = obj;
        byte[] bArr2 = bArr;
        int i22 = i3;
        int i23 = i4;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe2 = UNSAFE;
        int i24 = i2;
        int i25 = 0;
        int i26 = 0;
        int i27 = 0;
        int i28 = -1;
        int i29 = -1;
        while (true) {
            if (i24 < i22) {
                int i30 = i24 + 1;
                byte b3 = bArr2[i24];
                if (b3 < 0) {
                    i6 = ArrayDecoders.H(b3, bArr2, i30, registers2);
                    b2 = registers2.int1;
                } else {
                    b2 = b3;
                    i6 = i30;
                }
                int i31 = b2 >>> 3;
                int i32 = b2 & 7;
                int positionForFieldNumber = i31 > i28 ? messageSchema2.positionForFieldNumber(i31, i25 / 3) : messageSchema2.positionForFieldNumber(i31);
                if (positionForFieldNumber == -1) {
                    i7 = i31;
                    i8 = i6;
                    i9 = b2;
                    i10 = i27;
                    i11 = i29;
                    unsafe = unsafe2;
                    i12 = i23;
                    i13 = 0;
                } else {
                    int i33 = messageSchema2.buffer[positionForFieldNumber + 1];
                    int type = type(i33);
                    long offset = offset(i33);
                    int i34 = b2;
                    if (type <= 17) {
                        int i35 = messageSchema2.buffer[positionForFieldNumber + 2];
                        int i36 = 1 << (i35 >>> 20);
                        int i37 = i35 & OFFSET_MASK;
                        if (i37 != i29) {
                            c2 = 65535;
                            i16 = positionForFieldNumber;
                            if (i29 != -1) {
                                unsafe2.putInt(obj4, i29, i27);
                            }
                            i27 = unsafe2.getInt(obj4, i37);
                            i29 = i37;
                        } else {
                            i16 = positionForFieldNumber;
                            c2 = 65535;
                        }
                        switch (type) {
                            case 0:
                                i17 = i16;
                                i7 = i31;
                                bArr2 = bArr;
                                i18 = i6;
                                i19 = i34;
                                if (i32 == 1) {
                                    UnsafeUtil.C(obj4, offset, ArrayDecoders.d(bArr2, i18));
                                    i24 = i18 + 8;
                                    i27 |= i36;
                                    i23 = i4;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                    break;
                                } else {
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                }
                            case 1:
                                i17 = i16;
                                i7 = i31;
                                bArr2 = bArr;
                                i18 = i6;
                                i19 = i34;
                                if (i32 == 5) {
                                    UnsafeUtil.D(obj4, offset, ArrayDecoders.l(bArr2, i18));
                                    i24 = i18 + 4;
                                    i27 |= i36;
                                    i23 = i4;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                    break;
                                } else {
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                }
                            case 2:
                            case 3:
                                i17 = i16;
                                i7 = i31;
                                j3 = offset;
                                bArr2 = bArr;
                                i18 = i6;
                                i19 = i34;
                                if (i32 != 0) {
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                } else {
                                    L = ArrayDecoders.L(bArr2, i18, registers2);
                                    j4 = registers2.long1;
                                    unsafe2.putLong(obj, j3, j4);
                                    i27 |= i36;
                                    i25 = i17;
                                    i24 = L;
                                    i26 = i19;
                                    i28 = i7;
                                    i22 = i3;
                                    i23 = i4;
                                }
                            case 4:
                            case 11:
                                i17 = i16;
                                i7 = i31;
                                j2 = offset;
                                bArr2 = bArr;
                                i18 = i6;
                                i19 = i34;
                                if (i32 == 0) {
                                    i24 = ArrayDecoders.I(bArr2, i18, registers2);
                                    i20 = registers2.int1;
                                    unsafe2.putInt(obj4, j2, i20);
                                    i27 |= i36;
                                    i23 = i4;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                    break;
                                } else {
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                }
                            case 5:
                            case 14:
                                i17 = i16;
                                i7 = i31;
                                bArr2 = bArr;
                                i19 = i34;
                                if (i32 == 1) {
                                    i18 = i6;
                                    unsafe2.putLong(obj, offset, ArrayDecoders.j(bArr2, i6));
                                    i24 = i18 + 8;
                                    i27 |= i36;
                                    i23 = i4;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                    break;
                                } else {
                                    i18 = i6;
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                }
                            case 6:
                            case 13:
                                i17 = i16;
                                i7 = i31;
                                bArr2 = bArr;
                                i21 = i3;
                                i19 = i34;
                                if (i32 != 5) {
                                    i18 = i6;
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                } else {
                                    unsafe2.putInt(obj4, offset, ArrayDecoders.h(bArr2, i6));
                                    i24 = i6 + 4;
                                    i27 |= i36;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                    i22 = i21;
                                    i23 = i4;
                                }
                            case 7:
                                i17 = i16;
                                i7 = i31;
                                bArr2 = bArr;
                                i21 = i3;
                                i19 = i34;
                                if (i32 != 0) {
                                    i18 = i6;
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                } else {
                                    i24 = ArrayDecoders.L(bArr2, i6, registers2);
                                    UnsafeUtil.z(obj4, offset, registers2.long1 != 0);
                                    i27 |= i36;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                    i22 = i21;
                                    i23 = i4;
                                }
                            case 8:
                                i17 = i16;
                                i7 = i31;
                                j5 = offset;
                                bArr2 = bArr;
                                i21 = i3;
                                i19 = i34;
                                if (i32 != 2) {
                                    i18 = i6;
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                } else {
                                    i24 = (i33 & 536870912) == 0 ? ArrayDecoders.C(bArr2, i6, registers2) : ArrayDecoders.F(bArr2, i6, registers2);
                                    obj3 = registers2.object1;
                                    unsafe2.putObject(obj4, j5, obj3);
                                    i27 |= i36;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                    i22 = i21;
                                    i23 = i4;
                                }
                            case 9:
                                i17 = i16;
                                i19 = i34;
                                i7 = i31;
                                j5 = offset;
                                bArr2 = bArr;
                                if (i32 != 2) {
                                    i18 = i6;
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                } else {
                                    i21 = i3;
                                    i24 = ArrayDecoders.p(messageSchema2.getMessageFieldSchema(i17), bArr2, i6, i21, registers2);
                                    obj3 = (i27 & i36) == 0 ? registers2.object1 : Internal.d(unsafe2.getObject(obj4, j5), registers2.object1);
                                    unsafe2.putObject(obj4, j5, obj3);
                                    i27 |= i36;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                    i22 = i21;
                                    i23 = i4;
                                }
                            case 10:
                                i17 = i16;
                                i19 = i34;
                                i7 = i31;
                                bArr2 = bArr;
                                if (i32 == 2) {
                                    i24 = ArrayDecoders.b(bArr2, i6, registers2);
                                    unsafe2.putObject(obj4, offset, registers2.object1);
                                    i27 |= i36;
                                    i23 = i4;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                    break;
                                } else {
                                    i18 = i6;
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                }
                            case 12:
                                i17 = i16;
                                i19 = i34;
                                i7 = i31;
                                j2 = offset;
                                bArr2 = bArr;
                                if (i32 != 0) {
                                    i18 = i6;
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                } else {
                                    i24 = ArrayDecoders.I(bArr2, i6, registers2);
                                    i20 = registers2.int1;
                                    Internal.EnumVerifier enumFieldVerifier = messageSchema2.getEnumFieldVerifier(i17);
                                    if (enumFieldVerifier != null && !enumFieldVerifier.isInRange(i20)) {
                                        a(obj).h(i19, Long.valueOf(i20));
                                        i23 = i4;
                                        i25 = i17;
                                        i26 = i19;
                                        i28 = i7;
                                        break;
                                    }
                                    unsafe2.putInt(obj4, j2, i20);
                                    i27 |= i36;
                                    i23 = i4;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                }
                                break;
                            case 15:
                                i17 = i16;
                                i19 = i34;
                                i7 = i31;
                                j2 = offset;
                                bArr2 = bArr;
                                if (i32 == 0) {
                                    i24 = ArrayDecoders.I(bArr2, i6, registers2);
                                    i20 = CodedInputStream.decodeZigZag32(registers2.int1);
                                    unsafe2.putInt(obj4, j2, i20);
                                    i27 |= i36;
                                    i23 = i4;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                    break;
                                } else {
                                    i18 = i6;
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                }
                            case 16:
                                i17 = i16;
                                i19 = i34;
                                i7 = i31;
                                if (i32 != 0) {
                                    i18 = i6;
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                } else {
                                    j3 = offset;
                                    bArr2 = bArr;
                                    L = ArrayDecoders.L(bArr2, i6, registers2);
                                    j4 = CodedInputStream.decodeZigZag64(registers2.long1);
                                    unsafe2.putLong(obj, j3, j4);
                                    i27 |= i36;
                                    i25 = i17;
                                    i24 = L;
                                    i26 = i19;
                                    i28 = i7;
                                    i22 = i3;
                                    i23 = i4;
                                }
                            case 17:
                                if (i32 == 3) {
                                    int i38 = i16;
                                    i17 = i38;
                                    i7 = i31;
                                    i19 = i34;
                                    i24 = ArrayDecoders.n(messageSchema2.getMessageFieldSchema(i38), bArr, i6, i3, (i31 << 3) | 4, registers);
                                    unsafe2.putObject(obj4, offset, (i27 & i36) == 0 ? registers2.object1 : Internal.d(unsafe2.getObject(obj4, offset), registers2.object1));
                                    i27 |= i36;
                                    bArr2 = bArr;
                                    i23 = i4;
                                    i25 = i17;
                                    i26 = i19;
                                    i28 = i7;
                                    break;
                                } else {
                                    i17 = i16;
                                    i19 = i34;
                                    i7 = i31;
                                    i18 = i6;
                                    i12 = i4;
                                    i10 = i27;
                                    i11 = i29;
                                    i13 = i17;
                                    unsafe = unsafe2;
                                    i8 = i18;
                                    i9 = i19;
                                    break;
                                }
                            default:
                                i18 = i6;
                                i17 = i16;
                                i7 = i31;
                                i19 = i34;
                                i12 = i4;
                                i10 = i27;
                                i11 = i29;
                                i13 = i17;
                                unsafe = unsafe2;
                                i8 = i18;
                                i9 = i19;
                                break;
                        }
                        i22 = i3;
                    } else {
                        i7 = i31;
                        bArr2 = bArr;
                        int i39 = i6;
                        if (type != 27) {
                            i13 = positionForFieldNumber;
                            i10 = i27;
                            if (type <= 49) {
                                i11 = i29;
                                unsafe = unsafe2;
                                i15 = i34;
                                i24 = parseRepeatedField(obj, bArr, i39, i3, i34, i7, i32, i13, i33, type, offset, registers);
                            } else {
                                unsafe = unsafe2;
                                i14 = i39;
                                i15 = i34;
                                i11 = i29;
                                if (type != 50) {
                                    i24 = parseOneofField(obj, bArr, i14, i3, i15, i7, i32, i33, type, offset, i13, registers);
                                } else if (i32 == 2) {
                                    i24 = parseMapField(obj, bArr, i14, i3, i13, offset, registers);
                                }
                            }
                        } else if (i32 == 2) {
                            Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe2.getObject(obj4, offset);
                            if (!protobufList.isModifiable()) {
                                int size = protobufList.size();
                                protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
                                unsafe2.putObject(obj4, offset, protobufList);
                            }
                            i24 = ArrayDecoders.q(messageSchema2.getMessageFieldSchema(positionForFieldNumber), i34, bArr, i39, i3, protobufList, registers);
                            i23 = i4;
                            i26 = i34;
                            i28 = i7;
                            i25 = positionForFieldNumber;
                            i27 = i27;
                            i22 = i3;
                        } else {
                            i13 = positionForFieldNumber;
                            i10 = i27;
                            i11 = i29;
                            unsafe = unsafe2;
                            i14 = i39;
                            i15 = i34;
                        }
                        i12 = i4;
                        i8 = i14;
                        i9 = i15;
                    }
                }
                if (i9 != i12 || i12 == 0) {
                    int i40 = i12;
                    i24 = (!this.hasExtensions || registers.extensionRegistry == ExtensionRegistryLite.getEmptyRegistry()) ? ArrayDecoders.G(i9, bArr, i8, i3, a(obj), registers) : ArrayDecoders.g(i9, bArr, i8, i3, obj, this.defaultInstance, this.unknownFieldSchema, registers);
                    obj4 = obj;
                    bArr2 = bArr;
                    i22 = i3;
                    i26 = i9;
                    messageSchema2 = this;
                    registers2 = registers;
                    i28 = i7;
                    i29 = i11;
                    i25 = i13;
                    i27 = i10;
                    i23 = i40;
                    unsafe2 = unsafe;
                } else {
                    messageSchema = this;
                    i5 = i12;
                    i24 = i8;
                    i26 = i9;
                    i29 = i11;
                    i27 = i10;
                }
            } else {
                unsafe = unsafe2;
                i5 = i23;
                messageSchema = messageSchema2;
            }
        }
        if (i29 != -1) {
            obj2 = obj;
            unsafe.putInt(obj2, i29, i27);
        } else {
            obj2 = obj;
        }
        UnknownFieldSetLite unknownFieldSetLite = null;
        for (int i41 = messageSchema.checkInitializedCount; i41 < messageSchema.repeatedFieldOffsetStart; i41++) {
            unknownFieldSetLite = (UnknownFieldSetLite) messageSchema.filterMapUnknownEnumValues(obj2, messageSchema.intArray[i41], unknownFieldSetLite, messageSchema.unknownFieldSchema);
        }
        if (unknownFieldSetLite != null) {
            messageSchema.unknownFieldSchema.o(obj2, unknownFieldSetLite);
        }
        if (i5 == 0) {
            if (i24 != i3) {
                throw InvalidProtocolBufferException.g();
            }
        } else if (i24 > i3 || i26 != i5) {
            throw InvalidProtocolBufferException.g();
        }
        return i24;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public boolean equals(T t2, T t3) {
        int length = this.buffer.length;
        for (int i2 = 0; i2 < length; i2 += 3) {
            if (!equals(t2, t3, i2)) {
                return false;
            }
        }
        if (this.unknownFieldSchema.g(t2).equals(this.unknownFieldSchema.g(t3))) {
            if (this.hasExtensions) {
                return this.extensionSchema.c(t2).equals(this.extensionSchema.c(t3));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int getSerializedSize(T t2) {
        return this.proto3 ? getSerializedSizeProto3(t2) : getSerializedSizeProto2(t2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00cc, code lost:
        if (r3 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00de, code lost:
        if (r3 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e0, code lost:
        r7 = r3.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e4, code lost:
        r2 = (r2 * 53) + r7;
     */
    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int hashCode(T t2) {
        int i2;
        double p2;
        float q2;
        long t3;
        int r2;
        boolean m2;
        Object u;
        Object u2;
        int length = this.buffer.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4 += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i4);
            int numberAt = numberAt(i4);
            long offset = offset(typeAndOffsetAt);
            int i5 = 37;
            switch (type(typeAndOffsetAt)) {
                case 0:
                    i2 = i3 * 53;
                    p2 = UnsafeUtil.p(t2, offset);
                    t3 = Double.doubleToLongBits(p2);
                    r2 = Internal.hashLong(t3);
                    i3 = i2 + r2;
                    break;
                case 1:
                    i2 = i3 * 53;
                    q2 = UnsafeUtil.q(t2, offset);
                    r2 = Float.floatToIntBits(q2);
                    i3 = i2 + r2;
                    break;
                case 2:
                case 3:
                case 5:
                case 14:
                case 16:
                    i2 = i3 * 53;
                    t3 = UnsafeUtil.t(t2, offset);
                    r2 = Internal.hashLong(t3);
                    i3 = i2 + r2;
                    break;
                case 4:
                case 6:
                case 11:
                case 12:
                case 13:
                case 15:
                    i2 = i3 * 53;
                    r2 = UnsafeUtil.r(t2, offset);
                    i3 = i2 + r2;
                    break;
                case 7:
                    i2 = i3 * 53;
                    m2 = UnsafeUtil.m(t2, offset);
                    r2 = Internal.hashBoolean(m2);
                    i3 = i2 + r2;
                    break;
                case 8:
                    i2 = i3 * 53;
                    r2 = ((String) UnsafeUtil.u(t2, offset)).hashCode();
                    i3 = i2 + r2;
                    break;
                case 9:
                    u = UnsafeUtil.u(t2, offset);
                    break;
                case 10:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                    i2 = i3 * 53;
                    u2 = UnsafeUtil.u(t2, offset);
                    r2 = u2.hashCode();
                    i3 = i2 + r2;
                    break;
                case 17:
                    u = UnsafeUtil.u(t2, offset);
                    break;
                case 51:
                    if (isOneofPresent(t2, numberAt, i4)) {
                        i2 = i3 * 53;
                        p2 = oneofDoubleAt(t2, offset);
                        t3 = Double.doubleToLongBits(p2);
                        r2 = Internal.hashLong(t3);
                        i3 = i2 + r2;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(t2, numberAt, i4)) {
                        i2 = i3 * 53;
                        q2 = oneofFloatAt(t2, offset);
                        r2 = Float.floatToIntBits(q2);
                        i3 = i2 + r2;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    t3 = oneofLongAt(t2, offset);
                    r2 = Internal.hashLong(t3);
                    i3 = i2 + r2;
                    break;
                case 54:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    t3 = oneofLongAt(t2, offset);
                    r2 = Internal.hashLong(t3);
                    i3 = i2 + r2;
                    break;
                case 55:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    r2 = oneofIntAt(t2, offset);
                    i3 = i2 + r2;
                    break;
                case 56:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    t3 = oneofLongAt(t2, offset);
                    r2 = Internal.hashLong(t3);
                    i3 = i2 + r2;
                    break;
                case 57:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    r2 = oneofIntAt(t2, offset);
                    i3 = i2 + r2;
                    break;
                case 58:
                    if (isOneofPresent(t2, numberAt, i4)) {
                        i2 = i3 * 53;
                        m2 = oneofBooleanAt(t2, offset);
                        r2 = Internal.hashBoolean(m2);
                        i3 = i2 + r2;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    r2 = ((String) UnsafeUtil.u(t2, offset)).hashCode();
                    i3 = i2 + r2;
                    break;
                case 60:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    u2 = UnsafeUtil.u(t2, offset);
                    i2 = i3 * 53;
                    r2 = u2.hashCode();
                    i3 = i2 + r2;
                    break;
                case 61:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    u2 = UnsafeUtil.u(t2, offset);
                    r2 = u2.hashCode();
                    i3 = i2 + r2;
                    break;
                case 62:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    r2 = oneofIntAt(t2, offset);
                    i3 = i2 + r2;
                    break;
                case 63:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    r2 = oneofIntAt(t2, offset);
                    i3 = i2 + r2;
                    break;
                case 64:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    r2 = oneofIntAt(t2, offset);
                    i3 = i2 + r2;
                    break;
                case 65:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    t3 = oneofLongAt(t2, offset);
                    r2 = Internal.hashLong(t3);
                    i3 = i2 + r2;
                    break;
                case 66:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    r2 = oneofIntAt(t2, offset);
                    i3 = i2 + r2;
                    break;
                case 67:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    i2 = i3 * 53;
                    t3 = oneofLongAt(t2, offset);
                    r2 = Internal.hashLong(t3);
                    i3 = i2 + r2;
                    break;
                case 68:
                    if (!isOneofPresent(t2, numberAt, i4)) {
                        break;
                    }
                    u2 = UnsafeUtil.u(t2, offset);
                    i2 = i3 * 53;
                    r2 = u2.hashCode();
                    i3 = i2 + r2;
                    break;
            }
        }
        int hashCode = (i3 * 53) + this.unknownFieldSchema.g(t2).hashCode();
        return this.hasExtensions ? (hashCode * 53) + this.extensionSchema.c(t2).hashCode() : hashCode;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public final boolean isInitialized(T t2) {
        int i2;
        int i3 = -1;
        int i4 = 0;
        for (int i5 = 0; i5 < this.checkInitializedCount; i5++) {
            int i6 = this.intArray[i5];
            int numberAt = numberAt(i6);
            int typeAndOffsetAt = typeAndOffsetAt(i6);
            if (this.proto3) {
                i2 = 0;
            } else {
                int i7 = this.buffer[i6 + 2];
                int i8 = OFFSET_MASK & i7;
                i2 = 1 << (i7 >>> 20);
                if (i8 != i3) {
                    i4 = UNSAFE.getInt(t2, i8);
                    i3 = i8;
                }
            }
            if (isRequired(typeAndOffsetAt) && !isFieldPresent(t2, i6, i4, i2)) {
                return false;
            }
            int type = type(typeAndOffsetAt);
            if (type != 9 && type != 17) {
                if (type != 27) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(t2, numberAt, i6) && !isInitialized(t2, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                            return false;
                        }
                    } else if (type != 49) {
                        if (type == 50 && !isMapInitialized(t2, typeAndOffsetAt, i6)) {
                            return false;
                        }
                    }
                }
                if (!isListInitialized(t2, typeAndOffsetAt, i6)) {
                    return false;
                }
            } else if (isFieldPresent(t2, i6, i4, i2) && !isInitialized(t2, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                return false;
            }
        }
        return !this.hasExtensions || this.extensionSchema.c(t2).isInitialized();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void makeImmutable(T t2) {
        int i2;
        int i3 = this.checkInitializedCount;
        while (true) {
            i2 = this.repeatedFieldOffsetStart;
            if (i3 >= i2) {
                break;
            }
            long offset = offset(typeAndOffsetAt(this.intArray[i3]));
            Object u = UnsafeUtil.u(t2, offset);
            if (u != null) {
                UnsafeUtil.G(t2, offset, this.mapFieldSchema.toImmutable(u));
            }
            i3++;
        }
        int length = this.intArray.length;
        while (i2 < length) {
            this.listFieldSchema.c(t2, this.intArray[i2]);
            i2++;
        }
        this.unknownFieldSchema.j(t2);
        if (this.hasExtensions) {
            this.extensionSchema.f(t2);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        Objects.requireNonNull(extensionRegistryLite);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t2, reader, extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T t2, T t3) {
        Objects.requireNonNull(t3);
        for (int i2 = 0; i2 < this.buffer.length; i2 += 3) {
            mergeSingleField(t2, t3, i2);
        }
        SchemaUtil.D(this.unknownFieldSchema, t2, t3);
        if (this.hasExtensions) {
            SchemaUtil.B(this.extensionSchema, t2, t3);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T t2, byte[] bArr, int i2, int i3, ArrayDecoders.Registers registers) {
        if (this.proto3) {
            parseProto3Message(t2, bArr, i2, i3, registers);
        } else {
            e(t2, bArr, i2, i3, 0, registers);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void writeTo(T t2, Writer writer) {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(t2, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(t2, writer);
        } else {
            writeFieldsInAscendingOrderProto2(t2, writer);
        }
    }
}
