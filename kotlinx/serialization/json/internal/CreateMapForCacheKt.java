package kotlinx.serialization.json.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class CreateMapForCacheKt {
    @NotNull
    public static final <K, V> Map<K, V> createMapForCache(int i2) {
        return new ConcurrentHashMap(i2);
    }
}
