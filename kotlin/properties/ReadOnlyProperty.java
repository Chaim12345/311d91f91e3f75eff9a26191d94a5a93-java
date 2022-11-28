package kotlin.properties;

import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface ReadOnlyProperty<T, V> {
    V getValue(T t2, @NotNull KProperty<?> kProperty);
}
