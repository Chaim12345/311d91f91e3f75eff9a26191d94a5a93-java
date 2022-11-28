package kotlinx.serialization.json.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.json.JsonSchemaCacheKt;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class JsonNamesMapKt {
    @NotNull
    private static final DescriptorSchemaCache.Key<Map<String, Integer>> JsonAlternativeNamesKey = new DescriptorSchemaCache.Key<>();

    @NotNull
    public static final Map<String, Integer> buildAlternativeNamesMap(@NotNull SerialDescriptor serialDescriptor) {
        Map<String, Integer> emptyMap;
        Object singleOrNull;
        String[] names;
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        int elementsCount = serialDescriptor.getElementsCount();
        Map<String, Integer> map = null;
        int i2 = 0;
        while (i2 < elementsCount) {
            int i3 = i2 + 1;
            List<Annotation> elementAnnotations = serialDescriptor.getElementAnnotations(i2);
            ArrayList arrayList = new ArrayList();
            for (Object obj : elementAnnotations) {
                if (obj instanceof JsonNames) {
                    arrayList.add(obj);
                }
            }
            singleOrNull = CollectionsKt___CollectionsKt.singleOrNull((List<? extends Object>) arrayList);
            JsonNames jsonNames = (JsonNames) singleOrNull;
            if (jsonNames != null && (names = jsonNames.names()) != null) {
                int length = names.length;
                int i4 = 0;
                while (i4 < length) {
                    String str = names[i4];
                    i4++;
                    if (map == null) {
                        map = CreateMapForCacheKt.createMapForCache(serialDescriptor.getElementsCount());
                    }
                    Intrinsics.checkNotNull(map);
                    buildAlternativeNamesMap$putOrThrow(map, serialDescriptor, str, i2);
                }
            }
            i2 = i3;
        }
        if (map == null) {
            emptyMap = MapsKt__MapsKt.emptyMap();
            return emptyMap;
        }
        return map;
    }

    private static final void buildAlternativeNamesMap$putOrThrow(Map<String, Integer> map, SerialDescriptor serialDescriptor, String str, int i2) {
        Object value;
        if (!map.containsKey(str)) {
            map.put(str, Integer.valueOf(i2));
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The suggested name '");
        sb.append(str);
        sb.append("' for property ");
        sb.append(serialDescriptor.getElementName(i2));
        sb.append(" is already one of the names for property ");
        value = MapsKt__MapsKt.getValue(map, str);
        sb.append(serialDescriptor.getElementName(((Number) value).intValue()));
        sb.append(" in ");
        sb.append(serialDescriptor);
        throw new JsonException(sb.toString());
    }

    @NotNull
    public static final DescriptorSchemaCache.Key<Map<String, Integer>> getJsonAlternativeNamesKey() {
        return JsonAlternativeNamesKey;
    }

    public static /* synthetic */ void getJsonAlternativeNamesKey$annotations() {
    }

    public static final int getJsonNameIndex(@NotNull SerialDescriptor serialDescriptor, @NotNull Json json, @NotNull String name) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        int elementIndex = serialDescriptor.getElementIndex(name);
        if (elementIndex == -3 && json.getConfiguration().getUseAlternativeNames()) {
            Integer num = (Integer) ((Map) JsonSchemaCacheKt.getSchemaCache(json).getOrPut(serialDescriptor, JsonAlternativeNamesKey, new JsonNamesMapKt$getJsonNameIndex$alternativeNamesMap$1(serialDescriptor))).get(name);
            if (num == null) {
                return -3;
            }
            return num.intValue();
        }
        return elementIndex;
    }

    public static final int getJsonNameIndexOrThrow(@NotNull SerialDescriptor serialDescriptor, @NotNull Json json, @NotNull String name) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        int jsonNameIndex = getJsonNameIndex(serialDescriptor, json, name);
        if (jsonNameIndex != -3) {
            return jsonNameIndex;
        }
        throw new SerializationException(serialDescriptor.getSerialName() + " does not contain element with name '" + name + '\'');
    }

    public static final boolean tryCoerceValue(@NotNull Json json, @NotNull SerialDescriptor elementDescriptor, @NotNull Function0<Boolean> peekNull, @NotNull Function0<String> peekString, @NotNull Function0<Unit> onEnumCoercing) {
        String invoke;
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(elementDescriptor, "elementDescriptor");
        Intrinsics.checkNotNullParameter(peekNull, "peekNull");
        Intrinsics.checkNotNullParameter(peekString, "peekString");
        Intrinsics.checkNotNullParameter(onEnumCoercing, "onEnumCoercing");
        if (elementDescriptor.isNullable() || !peekNull.invoke().booleanValue()) {
            if (Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) && (invoke = peekString.invoke()) != null && getJsonNameIndex(elementDescriptor, json, invoke) == -3) {
                onEnumCoercing.invoke();
                return true;
            }
            return false;
        }
        return true;
    }

    public static /* synthetic */ boolean tryCoerceValue$default(Json json, SerialDescriptor elementDescriptor, Function0 peekNull, Function0 peekString, Function0 onEnumCoercing, int i2, Object obj) {
        String str;
        if ((i2 & 8) != 0) {
            onEnumCoercing = JsonNamesMapKt$tryCoerceValue$1.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(elementDescriptor, "elementDescriptor");
        Intrinsics.checkNotNullParameter(peekNull, "peekNull");
        Intrinsics.checkNotNullParameter(peekString, "peekString");
        Intrinsics.checkNotNullParameter(onEnumCoercing, "onEnumCoercing");
        if (elementDescriptor.isNullable() || !((Boolean) peekNull.invoke()).booleanValue()) {
            if (Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) && (str = (String) peekString.invoke()) != null && getJsonNameIndex(elementDescriptor, json, str) == -3) {
                onEnumCoercing.invoke();
                return true;
            }
            return false;
        }
        return true;
    }
}
