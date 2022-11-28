package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class CodedOutputStreamWriter implements Writer {
    private final CodedOutputStream output;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9739a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f9739a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9739a[WireFormat.FieldType.FIXED32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9739a[WireFormat.FieldType.INT32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9739a[WireFormat.FieldType.SFIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9739a[WireFormat.FieldType.SINT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9739a[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9739a[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9739a[WireFormat.FieldType.INT64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9739a[WireFormat.FieldType.SFIXED64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9739a[WireFormat.FieldType.SINT64.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9739a[WireFormat.FieldType.UINT64.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9739a[WireFormat.FieldType.STRING.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private CodedOutputStreamWriter(CodedOutputStream codedOutputStream) {
        CodedOutputStream codedOutputStream2 = (CodedOutputStream) Internal.b(codedOutputStream, "output");
        this.output = codedOutputStream2;
        codedOutputStream2.f9734a = this;
    }

    public static CodedOutputStreamWriter forCodedOutput(CodedOutputStream codedOutputStream) {
        CodedOutputStreamWriter codedOutputStreamWriter = codedOutputStream.f9734a;
        return codedOutputStreamWriter != null ? codedOutputStreamWriter : new CodedOutputStreamWriter(codedOutputStream);
    }

    private <V> void writeDeterministicBooleanMapEntry(int i2, boolean z, V v, MapEntryLite.Metadata<Boolean, V> metadata) {
        this.output.writeTag(i2, 2);
        this.output.writeUInt32NoTag(MapEntryLite.a(metadata, Boolean.valueOf(z), v));
        MapEntryLite.e(this.output, metadata, Boolean.valueOf(z), v);
    }

    private <V> void writeDeterministicIntegerMap(int i2, MapEntryLite.Metadata<Integer, V> metadata, Map<Integer, V> map) {
        int size = map.size();
        int[] iArr = new int[size];
        int i3 = 0;
        for (Integer num : map.keySet()) {
            iArr[i3] = num.intValue();
            i3++;
        }
        Arrays.sort(iArr);
        for (int i4 = 0; i4 < size; i4++) {
            int i5 = iArr[i4];
            V v = map.get(Integer.valueOf(i5));
            this.output.writeTag(i2, 2);
            this.output.writeUInt32NoTag(MapEntryLite.a(metadata, Integer.valueOf(i5), v));
            MapEntryLite.e(this.output, metadata, Integer.valueOf(i5), v);
        }
    }

    private <V> void writeDeterministicLongMap(int i2, MapEntryLite.Metadata<Long, V> metadata, Map<Long, V> map) {
        int size = map.size();
        long[] jArr = new long[size];
        int i3 = 0;
        for (Long l2 : map.keySet()) {
            jArr[i3] = l2.longValue();
            i3++;
        }
        Arrays.sort(jArr);
        for (int i4 = 0; i4 < size; i4++) {
            long j2 = jArr[i4];
            V v = map.get(Long.valueOf(j2));
            this.output.writeTag(i2, 2);
            this.output.writeUInt32NoTag(MapEntryLite.a(metadata, Long.valueOf(j2), v));
            MapEntryLite.e(this.output, metadata, Long.valueOf(j2), v);
        }
    }

    private <K, V> void writeDeterministicMap(int i2, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) {
        switch (AnonymousClass1.f9739a[metadata.keyType.ordinal()]) {
            case 1:
                V v = map.get(Boolean.FALSE);
                if (v != null) {
                    writeDeterministicBooleanMapEntry(i2, false, v, metadata);
                }
                V v2 = map.get(Boolean.TRUE);
                if (v2 != null) {
                    writeDeterministicBooleanMapEntry(i2, true, v2, metadata);
                    return;
                }
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                writeDeterministicIntegerMap(i2, metadata, map);
                return;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                writeDeterministicLongMap(i2, metadata, map);
                return;
            case 12:
                writeDeterministicStringMap(i2, metadata, map);
                return;
            default:
                throw new IllegalArgumentException("does not support key type: " + metadata.keyType);
        }
    }

    private <V> void writeDeterministicStringMap(int i2, MapEntryLite.Metadata<String, V> metadata, Map<String, V> map) {
        int size = map.size();
        String[] strArr = new String[size];
        int i3 = 0;
        for (String str : map.keySet()) {
            strArr[i3] = str;
            i3++;
        }
        Arrays.sort(strArr);
        for (int i4 = 0; i4 < size; i4++) {
            String str2 = strArr[i4];
            V v = map.get(str2);
            this.output.writeTag(i2, 2);
            this.output.writeUInt32NoTag(MapEntryLite.a(metadata, str2, v));
            MapEntryLite.e(this.output, metadata, str2, v);
        }
    }

    private void writeLazyString(int i2, Object obj) {
        if (obj instanceof String) {
            this.output.writeString(i2, (String) obj);
        } else {
            this.output.writeBytes(i2, (ByteString) obj);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public Writer.FieldOrder fieldOrder() {
        return Writer.FieldOrder.ASCENDING;
    }

    public int getTotalBytesWritten() {
        return this.output.getTotalBytesWritten();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeBool(int i2, boolean z) {
        this.output.writeBool(i2, z);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeBoolList(int i2, List<Boolean> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeBool(i2, list.get(i3).booleanValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeBoolSizeNoTag(list.get(i5).booleanValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeBoolNoTag(list.get(i3).booleanValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeBytes(int i2, ByteString byteString) {
        this.output.writeBytes(i2, byteString);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeBytesList(int i2, List<ByteString> list) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            this.output.writeBytes(i2, list.get(i3));
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeDouble(int i2, double d2) {
        this.output.writeDouble(i2, d2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeDoubleList(int i2, List<Double> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeDouble(i2, list.get(i3).doubleValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeDoubleSizeNoTag(list.get(i5).doubleValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeDoubleNoTag(list.get(i3).doubleValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeEndGroup(int i2) {
        this.output.writeTag(i2, 4);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeEnum(int i2, int i3) {
        this.output.writeEnum(i2, i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeEnumList(int i2, List<Integer> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeEnum(i2, list.get(i3).intValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeEnumSizeNoTag(list.get(i5).intValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeEnumNoTag(list.get(i3).intValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFixed32(int i2, int i3) {
        this.output.writeFixed32(i2, i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFixed32List(int i2, List<Integer> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeFixed32(i2, list.get(i3).intValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeFixed32SizeNoTag(list.get(i5).intValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeFixed32NoTag(list.get(i3).intValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFixed64(int i2, long j2) {
        this.output.writeFixed64(i2, j2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFixed64List(int i2, List<Long> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeFixed64(i2, list.get(i3).longValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeFixed64SizeNoTag(list.get(i5).longValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeFixed64NoTag(list.get(i3).longValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFloat(int i2, float f2) {
        this.output.writeFloat(i2, f2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFloatList(int i2, List<Float> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeFloat(i2, list.get(i3).floatValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeFloatSizeNoTag(list.get(i5).floatValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeFloatNoTag(list.get(i3).floatValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeGroup(int i2, Object obj) {
        this.output.writeGroup(i2, (MessageLite) obj);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeGroup(int i2, Object obj, Schema schema) {
        this.output.l(i2, (MessageLite) obj, schema);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeGroupList(int i2, List<?> list) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            writeGroup(i2, list.get(i3));
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeGroupList(int i2, List<?> list, Schema schema) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            writeGroup(i2, list.get(i3), schema);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeInt32(int i2, int i3) {
        this.output.writeInt32(i2, i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeInt32List(int i2, List<Integer> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeInt32(i2, list.get(i3).intValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeInt32SizeNoTag(list.get(i5).intValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeInt32NoTag(list.get(i3).intValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeInt64(int i2, long j2) {
        this.output.writeInt64(i2, j2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeInt64List(int i2, List<Long> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeInt64(i2, list.get(i3).longValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeInt64SizeNoTag(list.get(i5).longValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeInt64NoTag(list.get(i3).longValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public <K, V> void writeMap(int i2, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) {
        if (this.output.i()) {
            writeDeterministicMap(i2, metadata, map);
            return;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.output.writeTag(i2, 2);
            this.output.writeUInt32NoTag(MapEntryLite.a(metadata, entry.getKey(), entry.getValue()));
            MapEntryLite.e(this.output, metadata, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeMessage(int i2, Object obj) {
        this.output.writeMessage(i2, (MessageLite) obj);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeMessage(int i2, Object obj, Schema schema) {
        this.output.n(i2, (MessageLite) obj, schema);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeMessageList(int i2, List<?> list) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            writeMessage(i2, list.get(i3));
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeMessageList(int i2, List<?> list, Schema schema) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            writeMessage(i2, list.get(i3), schema);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeMessageSetItem(int i2, Object obj) {
        if (obj instanceof ByteString) {
            this.output.writeRawMessageSetExtension(i2, (ByteString) obj);
        } else {
            this.output.writeMessageSetExtension(i2, (MessageLite) obj);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSFixed32(int i2, int i3) {
        this.output.writeSFixed32(i2, i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSFixed32List(int i2, List<Integer> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeSFixed32(i2, list.get(i3).intValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeSFixed32SizeNoTag(list.get(i5).intValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeSFixed32NoTag(list.get(i3).intValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSFixed64(int i2, long j2) {
        this.output.writeSFixed64(i2, j2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSFixed64List(int i2, List<Long> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeSFixed64(i2, list.get(i3).longValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeSFixed64SizeNoTag(list.get(i5).longValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeSFixed64NoTag(list.get(i3).longValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSInt32(int i2, int i3) {
        this.output.writeSInt32(i2, i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSInt32List(int i2, List<Integer> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeSInt32(i2, list.get(i3).intValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeSInt32SizeNoTag(list.get(i5).intValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeSInt32NoTag(list.get(i3).intValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSInt64(int i2, long j2) {
        this.output.writeSInt64(i2, j2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSInt64List(int i2, List<Long> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeSInt64(i2, list.get(i3).longValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeSInt64SizeNoTag(list.get(i5).longValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeSInt64NoTag(list.get(i3).longValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeStartGroup(int i2) {
        this.output.writeTag(i2, 3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeString(int i2, String str) {
        this.output.writeString(i2, str);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeStringList(int i2, List<String> list) {
        int i3 = 0;
        if (!(list instanceof LazyStringList)) {
            while (i3 < list.size()) {
                this.output.writeString(i2, list.get(i3));
                i3++;
            }
            return;
        }
        LazyStringList lazyStringList = (LazyStringList) list;
        while (i3 < list.size()) {
            writeLazyString(i2, lazyStringList.getRaw(i3));
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeUInt32(int i2, int i3) {
        this.output.writeUInt32(i2, i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeUInt32List(int i2, List<Integer> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeUInt32(i2, list.get(i3).intValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeUInt32SizeNoTag(list.get(i5).intValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeUInt32NoTag(list.get(i3).intValue());
            i3++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeUInt64(int i2, long j2) {
        this.output.writeUInt64(i2, j2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeUInt64List(int i2, List<Long> list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.output.writeUInt64(i2, list.get(i3).longValue());
                i3++;
            }
            return;
        }
        this.output.writeTag(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += CodedOutputStream.computeUInt64SizeNoTag(list.get(i5).longValue());
        }
        this.output.writeUInt32NoTag(i4);
        while (i3 < list.size()) {
            this.output.writeUInt64NoTag(list.get(i3).longValue());
            i3++;
        }
    }
}
