package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import java.util.Map;
/* loaded from: classes2.dex */
class MapFieldSchemaLite implements MapFieldSchema {
    private static <K, V> int getSerializedSizeLite(int i2, Object obj, Object obj2) {
        MapFieldLite mapFieldLite = (MapFieldLite) obj;
        MapEntryLite mapEntryLite = (MapEntryLite) obj2;
        int i3 = 0;
        if (mapFieldLite.isEmpty()) {
            return 0;
        }
        for (Map.Entry<K, V> entry : mapFieldLite.entrySet()) {
            i3 += mapEntryLite.computeMessageSize(i2, entry.getKey(), entry.getValue());
        }
        return i3;
    }

    private static <K, V> MapFieldLite<K, V> mergeFromLite(Object obj, Object obj2) {
        MapFieldLite<K, V> mapFieldLite = (MapFieldLite) obj;
        MapFieldLite<K, V> mapFieldLite2 = (MapFieldLite) obj2;
        if (!mapFieldLite2.isEmpty()) {
            if (!mapFieldLite.isMutable()) {
                mapFieldLite = mapFieldLite.mutableCopy();
            }
            mapFieldLite.mergeFrom(mapFieldLite2);
        }
        return mapFieldLite;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Map<?, ?> forMapData(Object obj) {
        return (MapFieldLite) obj;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public MapEntryLite.Metadata<?, ?> forMapMetadata(Object obj) {
        return ((MapEntryLite) obj).b();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Map<?, ?> forMutableMapData(Object obj) {
        return (MapFieldLite) obj;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public int getSerializedSize(int i2, Object obj, Object obj2) {
        return getSerializedSizeLite(i2, obj, obj2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public boolean isImmutable(Object obj) {
        return !((MapFieldLite) obj).isMutable();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Object mergeFrom(Object obj, Object obj2) {
        return mergeFromLite(obj, obj2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Object newMapField(Object obj) {
        return MapFieldLite.emptyMapField().mutableCopy();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Object toImmutable(Object obj) {
        ((MapFieldLite) obj).makeImmutable();
        return obj;
    }
}
