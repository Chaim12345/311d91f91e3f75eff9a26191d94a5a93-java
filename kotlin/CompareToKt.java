package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes3.dex */
public final class CompareToKt {
    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final <T> int compareTo(Comparable<? super T> comparable, T t2) {
        Intrinsics.checkNotNullParameter(comparable, "<this>");
        return comparable.compareTo(t2);
    }
}
