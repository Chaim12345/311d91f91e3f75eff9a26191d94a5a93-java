package com.psa.mym.mycitroenconnect.views.skeleton_layout.mask;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.BaseExtensionsKt;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMaskable;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public abstract class SkeletonMask implements SkeletonMaskable {
    @NotNull
    private final Lazy bitmap$delegate;
    @NotNull
    private final Lazy canvas$delegate;
    private int color;
    @NotNull
    private final Lazy paint$delegate;
    @NotNull
    private final View parent;

    public SkeletonMask(@NotNull View parent, @ColorInt int i2) {
        Lazy lazy;
        Lazy lazy2;
        Lazy lazy3;
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.parent = parent;
        this.color = i2;
        lazy = LazyKt__LazyJVMKt.lazy(new SkeletonMask$bitmap$2(this));
        this.bitmap$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new SkeletonMask$canvas$2(this));
        this.canvas$delegate = lazy2;
        lazy3 = LazyKt__LazyJVMKt.lazy(new SkeletonMask$paint$2(this));
        this.paint$delegate = lazy3;
    }

    private final void draw(Rect rect, Paint paint) {
        getCanvas().drawRect(rect, paint);
    }

    private final void draw(RectF rectF, float f2, Paint paint) {
        getCanvas().drawRoundRect(rectF, f2, f2, paint);
    }

    private final Bitmap getBitmap() {
        return (Bitmap) this.bitmap$delegate.getValue();
    }

    private final Canvas getCanvas() {
        return (Canvas) this.canvas$delegate.getValue();
    }

    private final void mask(View view, ViewGroup viewGroup, Paint paint, float f2) {
        Unit unit = null;
        ViewGroup viewGroup2 = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup2 != null) {
            for (View view2 : BaseExtensionsKt.views(viewGroup2)) {
                mask(view2, viewGroup, paint, f2);
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            maskView(view, viewGroup, paint, f2);
        }
    }

    private final void maskView(View view, ViewGroup viewGroup, Paint paint, float f2) {
        validate(view);
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        viewGroup.offsetDescendantRectToMyCoords(view, rect);
        if (f2 > 0.0f) {
            draw(new RectF(rect.left, rect.top, rect.right, rect.bottom), f2, paint);
        } else {
            draw(rect, paint);
        }
    }

    private final void validate(View view) {
        if (view instanceof RecyclerView ? true : view instanceof ViewPager2) {
            Logger.INSTANCE.w("Passing ViewGroup with reusable children to SkeletonLayout - consider using applySkeleton() instead");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public Bitmap a() {
        Bitmap createBitmap = Bitmap.createBitmap(this.parent.getWidth(), this.parent.getHeight(), Bitmap.Config.ALPHA_8);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(parent.widt…t, Bitmap.Config.ALPHA_8)");
        return createBitmap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public Canvas b() {
        return new Canvas(getBitmap());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public Paint c() {
        Paint paint = new Paint();
        paint.setColor(this.color);
        return paint;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final Paint d() {
        return (Paint) this.paint$delegate.getValue();
    }

    public final void draw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.drawBitmap(getBitmap(), 0.0f, 0.0f, d());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final View e() {
        return this.parent;
    }

    public final int getColor() {
        return this.color;
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMaskable
    public void invalidate() {
        SkeletonMaskable.DefaultImpls.invalidate(this);
    }

    public final void mask(@NotNull ViewGroup viewGroup, float f2) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setAntiAlias(f2 > 0.0f);
        mask(viewGroup, viewGroup, paint, f2);
    }

    public final void setColor(int i2) {
        d().setColor(i2);
        this.color = i2;
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMaskable
    public void start() {
        SkeletonMaskable.DefaultImpls.start(this);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMaskable
    public void stop() {
        SkeletonMaskable.DefaultImpls.stop(this);
    }
}
