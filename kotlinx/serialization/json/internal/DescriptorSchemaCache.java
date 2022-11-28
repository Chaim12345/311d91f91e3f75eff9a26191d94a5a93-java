package kotlinx.serialization.json.internal;

import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DescriptorSchemaCache {
    @NotNull
    private final Map<SerialDescriptor, Map<Key<Object>, Object>> map = CreateMapForCacheKt.createMapForCache(1);

    /* loaded from: classes3.dex */
    public static final class Key<T> {
    }

    @Nullable
    public final <T> T get(@NotNull SerialDescriptor descriptor, @NotNull Key<T> key) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Map<Key<Object>, Object> map = this.map.get(descriptor);
        Object obj = map == null ? null : map.get(key);
        if (obj != null) {
            return (T) obj;
        }
        return null;
    }

    @NotNull
    public final <T> T getOrPut(@NotNull SerialDescriptor descriptor, @NotNull Key<T> key, @NotNull Function0<? extends T> defaultValue) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        T t2 = (T) get(descriptor, key);
        if (t2 == null) {
            T invoke = defaultValue.invoke();
            set(descriptor, key, invoke);
            return invoke;
        }
        return t2;
    }

    public final <T> void set(@NotNull SerialDescriptor descriptor, @NotNull Key<T> key, @NotNull T value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Map<SerialDescriptor, Map<Key<Object>, Object>> map = this.map;
        Map<Key<Object>, Object> map2 = map.get(descriptor);
        if (map2 == null) {
            map2 = CreateMapForCacheKt.createMapForCache(1);
            map.put(descriptor, map2);
        }
        map2.put(key, value);
    }
}
