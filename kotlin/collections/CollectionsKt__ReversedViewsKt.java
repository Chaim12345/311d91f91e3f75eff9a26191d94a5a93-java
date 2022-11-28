package kotlin.collections;

import java.util.List;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class CollectionsKt__ReversedViewsKt extends CollectionsKt__MutableCollectionsKt {
    @NotNull
    public static final <T> List<T> asReversed(@NotNull List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return new ReversedListReadOnly(list);
    }

    @JvmName(name = "asReversedMutable")
    @NotNull
    public static final <T> List<T> asReversedMutable(@NotNull List<T> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return new ReversedList(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final int reverseElementIndex$CollectionsKt__ReversedViewsKt(List<?> list, int i2) {
        boolean z;
        int lastIndex;
        int lastIndex2;
        int lastIndex3;
        if (i2 >= 0) {
            lastIndex3 = CollectionsKt__CollectionsKt.getLastIndex(list);
            if (i2 <= lastIndex3) {
                z = true;
                if (!z) {
                    lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(list);
                    return lastIndex2 - i2;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Element index ");
                sb.append(i2);
                sb.append(" must be in range [");
                lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
                sb.append(new IntRange(0, lastIndex));
                sb.append("].");
                throw new IndexOutOfBoundsException(sb.toString());
            }
        }
        z = false;
        if (!z) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int reversePositionIndex$CollectionsKt__ReversedViewsKt(List<?> list, int i2) {
        if (i2 >= 0 && i2 <= list.size()) {
            return list.size() - i2;
        }
        throw new IndexOutOfBoundsException("Position index " + i2 + " must be in range [" + new IntRange(0, list.size()) + "].");
    }
}
