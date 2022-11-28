package androidx.core.graphics;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\u001a\u0015\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0086\n\u001a\u0015\u0010\b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0086\n\u001a\r\u0010\t\u001a\u00020\u0000*\u00020\u0000H\u0086\n\u001a\r\u0010\n\u001a\u00020\u0000*\u00020\u0000H\u0086\n\u001a\u0015\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005H\u0086\f\u001a\u0015\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0086\f\u001a\u0015\u0010\f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005H\u0086\f\u001a\u0015\u0010\f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0086\f\u001a\u0015\u0010\r\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005H\u0086\f\u001a\u0015\u0010\r\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0086\f\u001a3\u0010\u0014\u001a\u00020\u0012*\u00020\u00002!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u000eH\u0086\bø\u0001\u0000\u001a\u0013\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015*\u00020\u0000H\u0086\u0002\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0017"}, d2 = {"Landroid/graphics/Region;", "Landroid/graphics/Point;", "p", "", "contains", "Landroid/graphics/Rect;", "r", "plus", "minus", "unaryMinus", "not", "or", "and", "xor", "Lkotlin/Function1;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "rect", "", "action", "forEach", "", "iterator", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class RegionKt {
    @NotNull
    public static final Region and(@NotNull Region and, @NotNull Rect r2) {
        Intrinsics.checkNotNullParameter(and, "$this$and");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(and);
        region.op(r2, Region.Op.INTERSECT);
        return region;
    }

    @NotNull
    public static final Region and(@NotNull Region and, @NotNull Region r2) {
        Intrinsics.checkNotNullParameter(and, "$this$and");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(and);
        region.op(r2, Region.Op.INTERSECT);
        return region;
    }

    public static final boolean contains(@NotNull Region contains, @NotNull Point p2) {
        Intrinsics.checkNotNullParameter(contains, "$this$contains");
        Intrinsics.checkNotNullParameter(p2, "p");
        return contains.contains(p2.x, p2.y);
    }

    public static final void forEach(@NotNull Region forEach, @NotNull Function1<? super Rect, Unit> action) {
        Intrinsics.checkNotNullParameter(forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        RegionIterator regionIterator = new RegionIterator(forEach);
        while (true) {
            Rect rect = new Rect();
            if (!regionIterator.next(rect)) {
                return;
            }
            action.invoke(rect);
        }
    }

    @NotNull
    public static final Iterator<Rect> iterator(@NotNull Region iterator) {
        Intrinsics.checkNotNullParameter(iterator, "$this$iterator");
        return new RegionKt$iterator$1(iterator);
    }

    @NotNull
    public static final Region minus(@NotNull Region minus, @NotNull Rect r2) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(minus);
        region.op(r2, Region.Op.DIFFERENCE);
        return region;
    }

    @NotNull
    public static final Region minus(@NotNull Region minus, @NotNull Region r2) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(minus);
        region.op(r2, Region.Op.DIFFERENCE);
        return region;
    }

    @NotNull
    public static final Region not(@NotNull Region not) {
        Intrinsics.checkNotNullParameter(not, "$this$not");
        Region region = new Region(not.getBounds());
        region.op(not, Region.Op.DIFFERENCE);
        return region;
    }

    @NotNull
    public static final Region or(@NotNull Region or, @NotNull Rect r2) {
        Intrinsics.checkNotNullParameter(or, "$this$or");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(or);
        region.union(r2);
        return region;
    }

    @NotNull
    public static final Region or(@NotNull Region or, @NotNull Region r2) {
        Intrinsics.checkNotNullParameter(or, "$this$or");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(or);
        region.op(r2, Region.Op.UNION);
        return region;
    }

    @NotNull
    public static final Region plus(@NotNull Region plus, @NotNull Rect r2) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(plus);
        region.union(r2);
        return region;
    }

    @NotNull
    public static final Region plus(@NotNull Region plus, @NotNull Region r2) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(plus);
        region.op(r2, Region.Op.UNION);
        return region;
    }

    @NotNull
    public static final Region unaryMinus(@NotNull Region unaryMinus) {
        Intrinsics.checkNotNullParameter(unaryMinus, "$this$unaryMinus");
        Region region = new Region(unaryMinus.getBounds());
        region.op(unaryMinus, Region.Op.DIFFERENCE);
        return region;
    }

    @NotNull
    public static final Region xor(@NotNull Region xor, @NotNull Rect r2) {
        Intrinsics.checkNotNullParameter(xor, "$this$xor");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(xor);
        region.op(r2, Region.Op.XOR);
        return region;
    }

    @NotNull
    public static final Region xor(@NotNull Region xor, @NotNull Region r2) {
        Intrinsics.checkNotNullParameter(xor, "$this$xor");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(xor);
        region.op(r2, Region.Op.XOR);
        return region;
    }
}
