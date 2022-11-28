package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
/* loaded from: classes3.dex */
public final class PropertyReferenceDelegatesKt {
    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V> V getValue(KProperty0<? extends V> kProperty0, Object obj, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(kProperty0, "<this>");
        Intrinsics.checkNotNullParameter(property, "property");
        return kProperty0.get();
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <T, V> V getValue(KProperty1<T, ? extends V> kProperty1, T t2, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(kProperty1, "<this>");
        Intrinsics.checkNotNullParameter(property, "property");
        return kProperty1.get(t2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <V> void setValue(KMutableProperty0<V> kMutableProperty0, Object obj, KProperty<?> property, V v) {
        Intrinsics.checkNotNullParameter(kMutableProperty0, "<this>");
        Intrinsics.checkNotNullParameter(property, "property");
        kMutableProperty0.set(v);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <T, V> void setValue(KMutableProperty1<T, V> kMutableProperty1, T t2, KProperty<?> property, V v) {
        Intrinsics.checkNotNullParameter(kMutableProperty1, "<this>");
        Intrinsics.checkNotNullParameter(property, "property");
        kMutableProperty1.set(t2, v);
    }
}
