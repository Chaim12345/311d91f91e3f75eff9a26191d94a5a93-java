package androidx.core.graphics;

import android.graphics.Point;
import android.graphics.PointF;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\b\u001a\r\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0086\n\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0000H\u0086\n\u001a\r\u0010\u0002\u001a\u00020\u0005*\u00020\u0004H\u0086\n\u001a\r\u0010\u0003\u001a\u00020\u0005*\u00020\u0004H\u0086\n\u001a\u0015\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0086\n\u001a\u0015\u0010\u0007\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0007\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\b\u001a\u00020\u0005H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\b\u001a\u00020\u0005H\u0086\n\u001a\r\u0010\n\u001a\u00020\u0000*\u00020\u0000H\u0086\n\u001a\r\u0010\n\u001a\u00020\u0004*\u00020\u0004H\u0086\n\u001a\r\u0010\u000b\u001a\u00020\u0004*\u00020\u0000H\u0086\b\u001a\r\u0010\f\u001a\u00020\u0000*\u00020\u0004H\u0086\b¨\u0006\r"}, d2 = {"Landroid/graphics/Point;", "", "component1", "component2", "Landroid/graphics/PointF;", "", "p", "plus", "xy", "minus", "unaryMinus", "toPointF", "toPoint", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class PointKt {
    public static final float component1(@NotNull PointF component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return component1.x;
    }

    public static final int component1(@NotNull Point component1) {
        Intrinsics.checkNotNullParameter(component1, "$this$component1");
        return component1.x;
    }

    public static final float component2(@NotNull PointF component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return component2.y;
    }

    public static final int component2(@NotNull Point component2) {
        Intrinsics.checkNotNullParameter(component2, "$this$component2");
        return component2.y;
    }

    @NotNull
    public static final Point minus(@NotNull Point minus, int i2) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        Point point = new Point(minus.x, minus.y);
        int i3 = -i2;
        point.offset(i3, i3);
        return point;
    }

    @NotNull
    public static final Point minus(@NotNull Point minus, @NotNull Point p2) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        Intrinsics.checkNotNullParameter(p2, "p");
        Point point = new Point(minus.x, minus.y);
        point.offset(-p2.x, -p2.y);
        return point;
    }

    @NotNull
    public static final PointF minus(@NotNull PointF minus, float f2) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        PointF pointF = new PointF(minus.x, minus.y);
        float f3 = -f2;
        pointF.offset(f3, f3);
        return pointF;
    }

    @NotNull
    public static final PointF minus(@NotNull PointF minus, @NotNull PointF p2) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        Intrinsics.checkNotNullParameter(p2, "p");
        PointF pointF = new PointF(minus.x, minus.y);
        pointF.offset(-p2.x, -p2.y);
        return pointF;
    }

    @NotNull
    public static final Point plus(@NotNull Point plus, int i2) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Point point = new Point(plus.x, plus.y);
        point.offset(i2, i2);
        return point;
    }

    @NotNull
    public static final Point plus(@NotNull Point plus, @NotNull Point p2) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(p2, "p");
        Point point = new Point(plus.x, plus.y);
        point.offset(p2.x, p2.y);
        return point;
    }

    @NotNull
    public static final PointF plus(@NotNull PointF plus, float f2) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        PointF pointF = new PointF(plus.x, plus.y);
        pointF.offset(f2, f2);
        return pointF;
    }

    @NotNull
    public static final PointF plus(@NotNull PointF plus, @NotNull PointF p2) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(p2, "p");
        PointF pointF = new PointF(plus.x, plus.y);
        pointF.offset(p2.x, p2.y);
        return pointF;
    }

    @NotNull
    public static final Point toPoint(@NotNull PointF toPoint) {
        Intrinsics.checkNotNullParameter(toPoint, "$this$toPoint");
        return new Point((int) toPoint.x, (int) toPoint.y);
    }

    @NotNull
    public static final PointF toPointF(@NotNull Point toPointF) {
        Intrinsics.checkNotNullParameter(toPointF, "$this$toPointF");
        return new PointF(toPointF);
    }

    @NotNull
    public static final Point unaryMinus(@NotNull Point unaryMinus) {
        Intrinsics.checkNotNullParameter(unaryMinus, "$this$unaryMinus");
        return new Point(-unaryMinus.x, -unaryMinus.y);
    }

    @NotNull
    public static final PointF unaryMinus(@NotNull PointF unaryMinus) {
        Intrinsics.checkNotNullParameter(unaryMinus, "$this$unaryMinus");
        return new PointF(-unaryMinus.x, -unaryMinus.y);
    }
}
