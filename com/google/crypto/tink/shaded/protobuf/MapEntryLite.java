package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.util.AbstractMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class MapEntryLite<K, V> {
    private static final int KEY_FIELD_NUMBER = 1;
    private static final int VALUE_FIELD_NUMBER = 2;
    private final K key;
    private final Metadata<K, V> metadata;
    private final V value;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.shaded.protobuf.MapEntryLite$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9770a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f9770a = iArr;
            try {
                iArr[WireFormat.FieldType.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9770a[WireFormat.FieldType.ENUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9770a[WireFormat.FieldType.GROUP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class Metadata<K, V> {
        public final K defaultKey;
        public final V defaultValue;
        public final WireFormat.FieldType keyType;
        public final WireFormat.FieldType valueType;

        public Metadata(WireFormat.FieldType fieldType, K k2, WireFormat.FieldType fieldType2, V v) {
            this.keyType = fieldType;
            this.defaultKey = k2;
            this.valueType = fieldType2;
            this.defaultValue = v;
        }
    }

    private MapEntryLite(Metadata<K, V> metadata, K k2, V v) {
        this.metadata = metadata;
        this.key = k2;
        this.value = v;
    }

    private MapEntryLite(WireFormat.FieldType fieldType, K k2, WireFormat.FieldType fieldType2, V v) {
        this.metadata = new Metadata<>(fieldType, k2, fieldType2, v);
        this.key = k2;
        this.value = v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Metadata metadata, Object obj, Object obj2) {
        return FieldSet.h(metadata.keyType, 1, obj) + FieldSet.h(metadata.valueType, 2, obj2);
    }

    static Map.Entry c(CodedInputStream codedInputStream, Metadata metadata, ExtensionRegistryLite extensionRegistryLite) {
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == WireFormat.a(1, metadata.keyType.getWireType())) {
                obj = d(codedInputStream, extensionRegistryLite, metadata.keyType, obj);
            } else if (readTag == WireFormat.a(2, metadata.valueType.getWireType())) {
                obj2 = d(codedInputStream, extensionRegistryLite, metadata.valueType, obj2);
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    static Object d(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, WireFormat.FieldType fieldType, Object obj) {
        int i2 = AnonymousClass1.f9770a[fieldType.ordinal()];
        if (i2 == 1) {
            MessageLite.Builder builder = ((MessageLite) obj).toBuilder();
            codedInputStream.readMessage(builder, extensionRegistryLite);
            return builder.buildPartial();
        } else if (i2 != 2) {
            if (i2 != 3) {
                return FieldSet.readPrimitiveField(codedInputStream, fieldType, true);
            }
            throw new RuntimeException("Groups are not allowed in maps.");
        } else {
            return Integer.valueOf(codedInputStream.readEnum());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(CodedOutputStream codedOutputStream, Metadata metadata, Object obj, Object obj2) {
        FieldSet.m(codedOutputStream, metadata.keyType, 1, obj);
        FieldSet.m(codedOutputStream, metadata.valueType, 2, obj2);
    }

    public static <K, V> MapEntryLite<K, V> newDefaultInstance(WireFormat.FieldType fieldType, K k2, WireFormat.FieldType fieldType2, V v) {
        return new MapEntryLite<>(fieldType, k2, fieldType2, v);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Metadata b() {
        return this.metadata;
    }

    public int computeMessageSize(int i2, K k2, V v) {
        return CodedOutputStream.computeTagSize(i2) + CodedOutputStream.d(a(this.metadata, k2, v));
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public Map.Entry<K, V> parseEntry(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return c(byteString.newCodedInput(), this.metadata, extensionRegistryLite);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void parseInto(MapFieldLite<K, V> mapFieldLite, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
        Metadata<K, V> metadata = this.metadata;
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == WireFormat.a(1, this.metadata.keyType.getWireType())) {
                obj = d(codedInputStream, extensionRegistryLite, this.metadata.keyType, obj);
            } else if (readTag == WireFormat.a(2, this.metadata.valueType.getWireType())) {
                obj2 = d(codedInputStream, extensionRegistryLite, this.metadata.valueType, obj2);
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        codedInputStream.checkLastTagWas(0);
        codedInputStream.popLimit(pushLimit);
        mapFieldLite.put(obj, obj2);
    }

    public void serializeTo(CodedOutputStream codedOutputStream, int i2, K k2, V v) {
        codedOutputStream.writeTag(i2, 2);
        codedOutputStream.writeUInt32NoTag(a(this.metadata, k2, v));
        e(codedOutputStream, this.metadata, k2, v);
    }
}
