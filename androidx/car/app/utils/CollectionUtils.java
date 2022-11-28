package androidx.car.app.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class CollectionUtils {
    private CollectionUtils() {
    }

    @NonNull
    public static <T> List<T> emptyIfNull(@Nullable List<T> list) {
        return list != null ? list : Collections.emptyList();
    }

    @NonNull
    public static <T> List<T> unmodifiableCopy(@Nullable List<T> list) {
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(list));
    }

    @NonNull
    public static <K, V> Map<K, V> unmodifiableCopy(@Nullable Map<K, V> map) {
        return map == null ? Collections.emptyMap() : Collections.unmodifiableMap(new HashMap(map));
    }
}
