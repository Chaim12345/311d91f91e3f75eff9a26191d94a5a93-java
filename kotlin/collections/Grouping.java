package kotlin.collections;

import java.util.Iterator;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.1")
/* loaded from: classes3.dex */
public interface Grouping<T, K> {
    K keyOf(T t2);

    @NotNull
    Iterator<T> sourceIterator();
}
