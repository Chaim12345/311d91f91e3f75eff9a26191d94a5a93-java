package androidx.core.util;

import android.util.Range;
import androidx.annotation.RequiresApi;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\u00028\u00002\u0006\u0010\u0002\u001a\u00028\u0000H\u0087\f¢\u0006\u0004\b\u0004\u0010\u0005\u001a8\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0006\u001a\u00028\u0000H\u0087\n¢\u0006\u0004\b\u0007\u0010\b\u001a7\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0087\n\u001a7\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0087\f\u001a(\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0007\u001a(\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0007¨\u0006\u000e"}, d2 = {"", ExifInterface.GPS_DIRECTION_TRUE, "that", "Landroid/util/Range;", "rangeTo", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Landroid/util/Range;", "value", "plus", "(Landroid/util/Range;Ljava/lang/Comparable;)Landroid/util/Range;", "other", "and", "Lkotlin/ranges/ClosedRange;", "toClosedRange", "toRange", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class RangeKt {
    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> and(@NotNull Range<T> and, @NotNull Range<T> other) {
        Intrinsics.checkNotNullParameter(and, "$this$and");
        Intrinsics.checkNotNullParameter(other, "other");
        Range<T> intersect = and.intersect(other);
        Intrinsics.checkNotNullExpressionValue(intersect, "intersect(other)");
        return intersect;
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> plus(@NotNull Range<T> plus, @NotNull Range<T> other) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(other, "other");
        Range<T> extend = plus.extend(other);
        Intrinsics.checkNotNullExpressionValue(extend, "extend(other)");
        return extend;
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> plus(@NotNull Range<T> plus, @NotNull T value) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(value, "value");
        Range<T> extend = plus.extend((Range<T>) value);
        Intrinsics.checkNotNullExpressionValue(extend, "extend(value)");
        return extend;
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> rangeTo(@NotNull T rangeTo, @NotNull T that) {
        Intrinsics.checkNotNullParameter(rangeTo, "$this$rangeTo");
        Intrinsics.checkNotNullParameter(that, "that");
        return new Range<>(rangeTo, that);
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> ClosedRange<T> toClosedRange(@NotNull final Range<T> toClosedRange) {
        Intrinsics.checkNotNullParameter(toClosedRange, "$this$toClosedRange");
        return (ClosedRange<T>) new ClosedRange<T>() { // from class: androidx.core.util.RangeKt$toClosedRange$1
            /* JADX WARN: Incorrect types in method signature: (TT;)Z */
            @Override // kotlin.ranges.ClosedRange
            public boolean contains(@NotNull Comparable value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return ClosedRange.DefaultImpls.contains(this, value);
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // kotlin.ranges.ClosedRange
            public Comparable getEndInclusive() {
                return toClosedRange.getUpper();
            }

            /* JADX WARN: Incorrect return type in method signature: ()TT; */
            @Override // kotlin.ranges.ClosedRange
            public Comparable getStart() {
                return toClosedRange.getLower();
            }

            @Override // kotlin.ranges.ClosedRange
            public boolean isEmpty() {
                return ClosedRange.DefaultImpls.isEmpty(this);
            }
        };
    }

    @RequiresApi(21)
    @NotNull
    public static final <T extends Comparable<? super T>> Range<T> toRange(@NotNull ClosedRange<T> toRange) {
        Intrinsics.checkNotNullParameter(toRange, "$this$toRange");
        return new Range<>(toRange.getStart(), toRange.getEndInclusive());
    }
}
