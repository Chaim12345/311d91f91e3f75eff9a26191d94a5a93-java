package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class SchemaUtil {
    private static final int DEFAULT_LOOK_UP_START_NUMBER = 40;
    private static final Class<?> GENERATED_MESSAGE_CLASS = getGeneratedMessageClass();
    private static final UnknownFieldSchema<?, ?> PROTO2_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(false);
    private static final UnknownFieldSchema<?, ?> PROTO3_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(true);
    private static final UnknownFieldSchema<?, ?> UNKNOWN_FIELD_SET_LITE_SCHEMA = new UnknownFieldSetLiteSchema();

    private SchemaUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object A(int i2, List list, Internal.EnumVerifier enumVerifier, Object obj, UnknownFieldSchema unknownFieldSchema) {
        if (enumVerifier == null) {
            return obj;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                int intValue = ((Integer) list.get(i4)).intValue();
                if (enumVerifier.isInRange(intValue)) {
                    if (i4 != i3) {
                        list.set(i3, Integer.valueOf(intValue));
                    }
                    i3++;
                } else {
                    obj = F(i2, intValue, obj, unknownFieldSchema);
                }
            }
            if (i3 != size) {
                list.subList(i3, size).clear();
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!enumVerifier.isInRange(intValue2)) {
                    obj = F(i2, intValue2, obj, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void B(ExtensionSchema extensionSchema, Object obj, Object obj2) {
        FieldSet c2 = extensionSchema.c(obj2);
        if (c2.l()) {
            return;
        }
        extensionSchema.d(obj).mergeFrom(c2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void C(MapFieldSchema mapFieldSchema, Object obj, Object obj2, long j2) {
        UnsafeUtil.G(obj, j2, mapFieldSchema.mergeFrom(UnsafeUtil.u(obj, j2), UnsafeUtil.u(obj2, j2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void D(UnknownFieldSchema unknownFieldSchema, Object obj, Object obj2) {
        unknownFieldSchema.p(obj, unknownFieldSchema.k(unknownFieldSchema.g(obj), unknownFieldSchema.g(obj2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean E(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object F(int i2, int i3, Object obj, UnknownFieldSchema unknownFieldSchema) {
        if (obj == null) {
            obj = unknownFieldSchema.n();
        }
        unknownFieldSchema.e(obj, i2, i3);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return z ? CodedOutputStream.computeTagSize(i2) + CodedOutputStream.d(size) : size * CodedOutputStream.computeBoolSize(i2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(List list) {
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(int i2, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = size * CodedOutputStream.computeTagSize(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            computeTagSize += CodedOutputStream.computeBytesSizeNoTag((ByteString) list.get(i3));
        }
        return computeTagSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int e2 = e(list);
        int computeTagSize = CodedOutputStream.computeTagSize(i2);
        return z ? computeTagSize + CodedOutputStream.d(e2) : e2 + (size * computeTagSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int e(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeEnumSizeNoTag(intArrayList.getInt(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeEnumSizeNoTag(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int f(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return z ? CodedOutputStream.computeTagSize(i2) + CodedOutputStream.d(size * 4) : size * CodedOutputStream.computeFixed32Size(i2, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(List list) {
        return list.size() * 4;
    }

    private static Class<?> getGeneratedMessageClass() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static UnknownFieldSchema<?, ?> getUnknownFieldSetSchema(boolean z) {
        try {
            Class<?> unknownFieldSetSchemaClass = getUnknownFieldSetSchemaClass();
            if (unknownFieldSetSchemaClass == null) {
                return null;
            }
            return (UnknownFieldSchema) unknownFieldSetSchemaClass.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> getUnknownFieldSetSchemaClass() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return z ? CodedOutputStream.computeTagSize(i2) + CodedOutputStream.d(size * 8) : size * CodedOutputStream.computeFixed64Size(i2, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int j(int i2, List list, Schema schema) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += CodedOutputStream.b(i2, (MessageLite) list.get(i4), schema);
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int l2 = l(list);
        int computeTagSize = CodedOutputStream.computeTagSize(i2);
        return z ? computeTagSize + CodedOutputStream.d(l2) : l2 + (size * computeTagSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int l(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeInt32SizeNoTag(intArrayList.getInt(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeInt32SizeNoTag(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(int i2, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        int n2 = n(list);
        return z ? CodedOutputStream.computeTagSize(i2) + CodedOutputStream.d(n2) : n2 + (list.size() * CodedOutputStream.computeTagSize(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int n(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeInt64SizeNoTag(longArrayList.getLong(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeInt64SizeNoTag(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int o(int i2, Object obj, Schema schema) {
        return obj instanceof LazyFieldLite ? CodedOutputStream.computeLazyFieldSize(i2, (LazyFieldLite) obj) : CodedOutputStream.e(i2, (MessageLite) obj, schema);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int p(int i2, List list, Schema schema) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream.computeTagSize(i2) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            computeTagSize += obj instanceof LazyFieldLite ? CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite) obj) : CodedOutputStream.f((MessageLite) obj, schema);
        }
        return computeTagSize;
    }

    public static UnknownFieldSchema<?, ?> proto2UnknownFieldSetSchema() {
        return PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
    }

    public static UnknownFieldSchema<?, ?> proto3UnknownFieldSetSchema() {
        return PROTO3_UNKNOWN_FIELD_SET_SCHEMA;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int q(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int r2 = r(list);
        int computeTagSize = CodedOutputStream.computeTagSize(i2);
        return z ? computeTagSize + CodedOutputStream.d(r2) : r2 + (size * computeTagSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int r(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeSInt32SizeNoTag(intArrayList.getInt(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeSInt32SizeNoTag(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    public static void requireGeneratedMessage(Class<?> cls) {
        Class<?> cls2;
        if (!GeneratedMessageLite.class.isAssignableFrom(cls) && (cls2 = GENERATED_MESSAGE_CLASS) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int s(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int t2 = t(list);
        int computeTagSize = CodedOutputStream.computeTagSize(i2);
        return z ? computeTagSize + CodedOutputStream.d(t2) : t2 + (size * computeTagSize);
    }

    public static boolean shouldUseTableSwitch(int i2, int i3, int i4) {
        if (i3 < 40) {
            return true;
        }
        long j2 = i4;
        return ((((long) i3) - ((long) i2)) + 1) + 9 <= ((2 * j2) + 3) + ((j2 + 3) * 3);
    }

    public static boolean shouldUseTableSwitch(FieldInfo[] fieldInfoArr) {
        if (fieldInfoArr.length == 0) {
            return false;
        }
        return shouldUseTableSwitch(fieldInfoArr[0].getFieldNumber(), fieldInfoArr[fieldInfoArr.length - 1].getFieldNumber(), fieldInfoArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int t(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeSInt64SizeNoTag(longArrayList.getLong(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeSInt64SizeNoTag(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int u(int i2, List list) {
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream.computeTagSize(i2) * size;
        if (list instanceof LazyStringList) {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i3 < size) {
                Object raw = lazyStringList.getRaw(i3);
                computeTagSize += raw instanceof ByteString ? CodedOutputStream.computeBytesSizeNoTag((ByteString) raw) : CodedOutputStream.computeStringSizeNoTag((String) raw);
                i3++;
            }
        } else {
            while (i3 < size) {
                Object obj = list.get(i3);
                computeTagSize += obj instanceof ByteString ? CodedOutputStream.computeBytesSizeNoTag((ByteString) obj) : CodedOutputStream.computeStringSizeNoTag((String) obj);
                i3++;
            }
        }
        return computeTagSize;
    }

    public static UnknownFieldSchema<?, ?> unknownFieldSetLiteSchema() {
        return UNKNOWN_FIELD_SET_LITE_SCHEMA;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int v(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int w = w(list);
        int computeTagSize = CodedOutputStream.computeTagSize(i2);
        return z ? computeTagSize + CodedOutputStream.d(w) : w + (size * computeTagSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int w(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeUInt32SizeNoTag(intArrayList.getInt(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeUInt32SizeNoTag(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    public static void writeBool(int i2, boolean z, Writer writer) {
        if (z) {
            writer.writeBool(i2, true);
        }
    }

    public static void writeBoolList(int i2, List<Boolean> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeBoolList(i2, list, z);
    }

    public static void writeBytes(int i2, ByteString byteString, Writer writer) {
        if (byteString == null || byteString.isEmpty()) {
            return;
        }
        writer.writeBytes(i2, byteString);
    }

    public static void writeBytesList(int i2, List<ByteString> list, Writer writer) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeBytesList(i2, list);
    }

    public static void writeDouble(int i2, double d2, Writer writer) {
        if (Double.compare(d2, 0.0d) != 0) {
            writer.writeDouble(i2, d2);
        }
    }

    public static void writeDoubleList(int i2, List<Double> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeDoubleList(i2, list, z);
    }

    public static void writeEnum(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeEnum(i2, i3);
        }
    }

    public static void writeEnumList(int i2, List<Integer> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeEnumList(i2, list, z);
    }

    public static void writeFixed32(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeFixed32(i2, i3);
        }
    }

    public static void writeFixed32List(int i2, List<Integer> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeFixed32List(i2, list, z);
    }

    public static void writeFixed64(int i2, long j2, Writer writer) {
        if (j2 != 0) {
            writer.writeFixed64(i2, j2);
        }
    }

    public static void writeFixed64List(int i2, List<Long> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeFixed64List(i2, list, z);
    }

    public static void writeFloat(int i2, float f2, Writer writer) {
        if (Float.compare(f2, 0.0f) != 0) {
            writer.writeFloat(i2, f2);
        }
    }

    public static void writeFloatList(int i2, List<Float> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeFloatList(i2, list, z);
    }

    public static void writeGroupList(int i2, List<?> list, Writer writer) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeGroupList(i2, list);
    }

    public static void writeGroupList(int i2, List<?> list, Writer writer, Schema schema) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeGroupList(i2, list, schema);
    }

    public static void writeInt32(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeInt32(i2, i3);
        }
    }

    public static void writeInt32List(int i2, List<Integer> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeInt32List(i2, list, z);
    }

    public static void writeInt64(int i2, long j2, Writer writer) {
        if (j2 != 0) {
            writer.writeInt64(i2, j2);
        }
    }

    public static void writeInt64List(int i2, List<Long> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeInt64List(i2, list, z);
    }

    public static void writeLazyFieldList(int i2, List<?> list, Writer writer) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            ((LazyFieldLite) it.next()).b(writer, i2);
        }
    }

    public static void writeMessage(int i2, Object obj, Writer writer) {
        if (obj != null) {
            writer.writeMessage(i2, obj);
        }
    }

    public static void writeMessageList(int i2, List<?> list, Writer writer) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeMessageList(i2, list);
    }

    public static void writeMessageList(int i2, List<?> list, Writer writer, Schema schema) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeMessageList(i2, list, schema);
    }

    public static void writeSFixed32(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeSFixed32(i2, i3);
        }
    }

    public static void writeSFixed32List(int i2, List<Integer> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeSFixed32List(i2, list, z);
    }

    public static void writeSFixed64(int i2, long j2, Writer writer) {
        if (j2 != 0) {
            writer.writeSFixed64(i2, j2);
        }
    }

    public static void writeSFixed64List(int i2, List<Long> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeSFixed64List(i2, list, z);
    }

    public static void writeSInt32(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeSInt32(i2, i3);
        }
    }

    public static void writeSInt32List(int i2, List<Integer> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeSInt32List(i2, list, z);
    }

    public static void writeSInt64(int i2, long j2, Writer writer) {
        if (j2 != 0) {
            writer.writeSInt64(i2, j2);
        }
    }

    public static void writeSInt64List(int i2, List<Long> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeSInt64List(i2, list, z);
    }

    public static void writeString(int i2, Object obj, Writer writer) {
        if (obj instanceof String) {
            writeStringInternal(i2, (String) obj, writer);
        } else {
            writeBytes(i2, (ByteString) obj, writer);
        }
    }

    private static void writeStringInternal(int i2, String str, Writer writer) {
        if (str == null || str.isEmpty()) {
            return;
        }
        writer.writeString(i2, str);
    }

    public static void writeStringList(int i2, List<String> list, Writer writer) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeStringList(i2, list);
    }

    public static void writeUInt32(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeUInt32(i2, i3);
        }
    }

    public static void writeUInt32List(int i2, List<Integer> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeUInt32List(i2, list, z);
    }

    public static void writeUInt64(int i2, long j2, Writer writer) {
        if (j2 != 0) {
            writer.writeUInt64(i2, j2);
        }
    }

    public static void writeUInt64List(int i2, List<Long> list, Writer writer, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeUInt64List(i2, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int x(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int y = y(list);
        int computeTagSize = CodedOutputStream.computeTagSize(i2);
        return z ? computeTagSize + CodedOutputStream.d(y) : y + (size * computeTagSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int y(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeUInt64SizeNoTag(longArrayList.getLong(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += CodedOutputStream.computeUInt64SizeNoTag(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object z(int i2, List list, Internal.EnumLiteMap enumLiteMap, Object obj, UnknownFieldSchema unknownFieldSchema) {
        if (enumLiteMap == null) {
            return obj;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                int intValue = ((Integer) list.get(i4)).intValue();
                if (enumLiteMap.findValueByNumber(intValue) != null) {
                    if (i4 != i3) {
                        list.set(i3, Integer.valueOf(intValue));
                    }
                    i3++;
                } else {
                    obj = F(i2, intValue, obj, unknownFieldSchema);
                }
            }
            if (i3 != size) {
                list.subList(i3, size).clear();
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (enumLiteMap.findValueByNumber(intValue2) == null) {
                    obj = F(i2, intValue2, obj, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return obj;
    }
}
