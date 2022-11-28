package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\r\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0086\n\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0000H\u0086\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0086\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\u0086\n\u001a\r\u0010\u0002\u001a\u00020\u0007*\u00020\u0006H\u0086\n\u001a\r\u0010\u0003\u001a\u00020\u0007*\u00020\u0006H\u0086\n\u001a\r\u0010\u0004\u001a\u00020\u0007*\u00020\u0006H\u0086\n\u001a\r\u0010\u0005\u001a\u00020\u0007*\u00020\u0006H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\n\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\n\u001a\u00020\u0007H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\n\u001a\u00020\fH\u0086\n\u001a\u0015\u0010\u000e\u001a\u00020\r*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000H\u0086\n\u001a\u0015\u0010\u000e\u001a\u00020\r*\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0086\n\u001a\u0015\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\n\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u000e\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\n\u001a\u00020\u0007H\u0086\n\u001a\u0015\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0086\n\u001a\u0015\u0010\u000e\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\n\u001a\u00020\fH\u0086\n\u001a\u0015\u0010\u0010\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0010\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0010\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0007H\u0086\n\u001a\u0015\u0010\u0011\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000H\u0086\f\u001a\u0015\u0010\u0011\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0086\f\u001a\u0015\u0010\u0012\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000H\u0087\f\u001a\u0015\u0010\u0012\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0087\f\u001a\u0015\u0010\u0013\u001a\u00020\r*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000H\u0086\f\u001a\u0015\u0010\u0013\u001a\u00020\r*\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0086\f\u001a\u0015\u0010\u0016\u001a\u00020\u0015*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u000bH\u0086\n\u001a\u0015\u0010\u0016\u001a\u00020\u0015*\u00020\u00062\u0006\u0010\u0014\u001a\u00020\fH\u0086\n\u001a\r\u0010\u0017\u001a\u00020\u0006*\u00020\u0000H\u0086\b\u001a\r\u0010\u0018\u001a\u00020\u0000*\u00020\u0006H\u0086\b\u001a\r\u0010\u0019\u001a\u00020\r*\u00020\u0000H\u0086\b\u001a\r\u0010\u0019\u001a\u00020\r*\u00020\u0006H\u0086\b\u001a\u0015\u0010\u001c\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001aH\u0086\b¨\u0006\u001d"}, d2 = {"Landroid/graphics/Rect;", "", "component1", "component2", "component3", "component4", "Landroid/graphics/RectF;", "", "r", "plus", "xy", "Landroid/graphics/Point;", "Landroid/graphics/PointF;", "Landroid/graphics/Region;", "minus", "factor", "times", "or", "and", "xor", "p", "", "contains", "toRectF", "toRect", "toRegion", "Landroid/graphics/Matrix;", "m", "transform", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class RectKt {
    @SuppressLint({"CheckResult"})
    @NotNull
    public static final Rect and(@NotNull Rect and, @NotNull Rect r2) {
        Intrinsics.checkNotNullParameter(and, "$this$and");
        Intrinsics.checkNotNullParameter(r2, "r");
        Rect rect = new Rect(and);
        rect.intersect(r2);
        return rect;
    }

    @SuppressLint({"CheckResult"})
    @NotNull
    public static final RectF and(@NotNull RectF and, @NotNull RectF r2) {
        Intrinsics.checkNotNullParameter(and, "$this$and");
        Intrinsics.checkNotNullParameter(r2, "r");
        RectF rectF = new RectF(and);
        rectF.intersect(r2);
        return rectF;
    }

    public static final float component1(@NotNull RectF component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return component1.left;
    }

    public static final int component1(@NotNull Rect component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return component1.left;
    }

    public static final float component2(@NotNull RectF component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return component2.top;
    }

    public static final int component2(@NotNull Rect component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return component2.top;
    }

    public static final float component3(@NotNull RectF component3) {
        Intrinsics.checkNotNullParameter(component3, "$this$component3");
        return component3.right;
    }

    public static final int component3(@NotNull Rect component3) {
        Intrinsics.checkNotNullParameter(component3, "$this$component3");
        return component3.right;
    }

    public static final float component4(@NotNull RectF component4) {
        Intrinsics.checkNotNullParameter(component4, "$this$component4");
        return component4.bottom;
    }

    public static final int component4(@NotNull Rect component4) {
        Intrinsics.checkNotNullParameter(component4, "$this$component4");
        return component4.bottom;
    }

    public static final boolean contains(@NotNull Rect contains, @NotNull Point p2) {
        Intrinsics.checkNotNullParameter(contains, "$this$contains");
        Intrinsics.checkNotNullParameter(p2, "p");
        return contains.contains(p2.x, p2.y);
    }

    public static final boolean contains(@NotNull RectF contains, @NotNull PointF p2) {
        Intrinsics.checkNotNullParameter(contains, "$this$contains");
        Intrinsics.checkNotNullParameter(p2, "p");
        return contains.contains(p2.x, p2.y);
    }

    @NotNull
    public static final Rect minus(@NotNull Rect minus, int i2) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        Rect rect = new Rect(minus);
        int i3 = -i2;
        rect.offset(i3, i3);
        return rect;
    }

    @NotNull
    public static final Rect minus(@NotNull Rect minus, @NotNull Point xy) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        Intrinsics.checkNotNullParameter(xy, "xy");
        Rect rect = new Rect(minus);
        rect.offset(-xy.x, -xy.y);
        return rect;
    }

    @NotNull
    public static final RectF minus(@NotNull RectF minus, float f2) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        RectF rectF = new RectF(minus);
        float f3 = -f2;
        rectF.offset(f3, f3);
        return rectF;
    }

    @NotNull
    public static final RectF minus(@NotNull RectF minus, @NotNull PointF xy) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        Intrinsics.checkNotNullParameter(xy, "xy");
        RectF rectF = new RectF(minus);
        rectF.offset(-xy.x, -xy.y);
        return rectF;
    }

    @NotNull
    public static final Region minus(@NotNull Rect minus, @NotNull Rect r2) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(minus);
        region.op(r2, Region.Op.DIFFERENCE);
        return region;
    }

    @NotNull
    public static final Region minus(@NotNull RectF minus, @NotNull RectF r2) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        Intrinsics.checkNotNullParameter(r2, "r");
        Rect rect = new Rect();
        minus.roundOut(rect);
        Region region = new Region(rect);
        Rect rect2 = new Rect();
        r2.roundOut(rect2);
        region.op(rect2, Region.Op.DIFFERENCE);
        return region;
    }

    @NotNull
    public static final Rect or(@NotNull Rect or, @NotNull Rect r2) {
        Intrinsics.checkNotNullParameter(or, "$this$or");
        Intrinsics.checkNotNullParameter(r2, "r");
        Rect rect = new Rect(or);
        rect.union(r2);
        return rect;
    }

    @NotNull
    public static final RectF or(@NotNull RectF or, @NotNull RectF r2) {
        Intrinsics.checkNotNullParameter(or, "$this$or");
        Intrinsics.checkNotNullParameter(r2, "r");
        RectF rectF = new RectF(or);
        rectF.union(r2);
        return rectF;
    }

    @NotNull
    public static final Rect plus(@NotNull Rect plus, int i2) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Rect rect = new Rect(plus);
        rect.offset(i2, i2);
        return rect;
    }

    @NotNull
    public static final Rect plus(@NotNull Rect plus, @NotNull Point xy) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(xy, "xy");
        Rect rect = new Rect(plus);
        rect.offset(xy.x, xy.y);
        return rect;
    }

    @NotNull
    public static final Rect plus(@NotNull Rect plus, @NotNull Rect r2) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(r2, "r");
        Rect rect = new Rect(plus);
        rect.union(r2);
        return rect;
    }

    @NotNull
    public static final RectF plus(@NotNull RectF plus, float f2) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        RectF rectF = new RectF(plus);
        rectF.offset(f2, f2);
        return rectF;
    }

    @NotNull
    public static final RectF plus(@NotNull RectF plus, @NotNull PointF xy) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(xy, "xy");
        RectF rectF = new RectF(plus);
        rectF.offset(xy.x, xy.y);
        return rectF;
    }

    @NotNull
    public static final RectF plus(@NotNull RectF plus, @NotNull RectF r2) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(r2, "r");
        RectF rectF = new RectF(plus);
        rectF.union(r2);
        return rectF;
    }

    @NotNull
    public static final Rect times(@NotNull Rect times, int i2) {
        Intrinsics.checkNotNullParameter(times, "$this$times");
        Rect rect = new Rect(times);
        rect.top *= i2;
        rect.left *= i2;
        rect.right *= i2;
        rect.bottom *= i2;
        return rect;
    }

    @NotNull
    public static final RectF times(@NotNull RectF times, float f2) {
        Intrinsics.checkNotNullParameter(times, "$this$times");
        RectF rectF = new RectF(times);
        rectF.top *= f2;
        rectF.left *= f2;
        rectF.right *= f2;
        rectF.bottom *= f2;
        return rectF;
    }

    @NotNull
    public static final RectF times(@NotNull RectF times, int i2) {
        Intrinsics.checkNotNullParameter(times, "$this$times");
        float f2 = i2;
        RectF rectF = new RectF(times);
        rectF.top *= f2;
        rectF.left *= f2;
        rectF.right *= f2;
        rectF.bottom *= f2;
        return rectF;
    }

    @NotNull
    public static final Rect toRect(@NotNull RectF toRect) {
        Intrinsics.checkNotNullParameter(toRect, "$this$toRect");
        Rect rect = new Rect();
        toRect.roundOut(rect);
        return rect;
    }

    @NotNull
    public static final RectF toRectF(@NotNull Rect toRectF) {
        Intrinsics.checkNotNullParameter(toRectF, "$this$toRectF");
        return new RectF(toRectF);
    }

    @NotNull
    public static final Region toRegion(@NotNull Rect toRegion) {
        Intrinsics.checkNotNullParameter(toRegion, "$this$toRegion");
        return new Region(toRegion);
    }

    @NotNull
    public static final Region toRegion(@NotNull RectF toRegion) {
        Intrinsics.checkNotNullParameter(toRegion, "$this$toRegion");
        Rect rect = new Rect();
        toRegion.roundOut(rect);
        return new Region(rect);
    }

    @NotNull
    public static final RectF transform(@NotNull RectF transform, @NotNull Matrix m2) {
        Intrinsics.checkNotNullParameter(transform, "$this$transform");
        Intrinsics.checkNotNullParameter(m2, "m");
        m2.mapRect(transform);
        return transform;
    }

    @NotNull
    public static final Region xor(@NotNull Rect xor, @NotNull Rect r2) {
        Intrinsics.checkNotNullParameter(xor, "$this$xor");
        Intrinsics.checkNotNullParameter(r2, "r");
        Region region = new Region(xor);
        region.op(r2, Region.Op.XOR);
        return region;
    }

    @NotNull
    public static final Region xor(@NotNull RectF xor, @NotNull RectF r2) {
        Intrinsics.checkNotNullParameter(xor, "$this$xor");
        Intrinsics.checkNotNullParameter(r2, "r");
        Rect rect = new Rect();
        xor.roundOut(rect);
        Region region = new Region(rect);
        Rect rect2 = new Rect();
        r2.roundOut(rect2);
        region.op(rect2, Region.Op.XOR);
        return region;
    }
}
