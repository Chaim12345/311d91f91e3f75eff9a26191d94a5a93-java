package kotlin.properties;

import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface ReadWriteProperty<T, V> extends ReadOnlyProperty<T, V> {
    @Override // kotlin.properties.ReadOnlyProperty
    V getValue(T t2, @NotNull KProperty<?> kProperty);

    void setValue(T t2, @NotNull KProperty<?> kProperty, V v);
}
