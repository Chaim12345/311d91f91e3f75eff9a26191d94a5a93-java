package com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer;

import android.animation.ArgbEvaluator;
import android.graphics.Paint;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.option.IndicatorOptions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public abstract class BaseDrawer implements IDrawer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int INDICATOR_PADDING = 3;
    public static final int INDICATOR_PADDING_ADDITION = 6;
    @Nullable
    private ArgbEvaluator argbEvaluator;
    @NotNull
    private IndicatorOptions mIndicatorOptions;
    @NotNull
    private final MeasureResult mMeasureResult;
    @NotNull
    private Paint mPaint;
    private float maxWidth;
    private float minWidth;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes3.dex */
    public final class MeasureResult {
        private int measureHeight;
        private int measureWidth;

        public MeasureResult(BaseDrawer baseDrawer) {
        }

        public final int getMeasureHeight() {
            return this.measureHeight;
        }

        public final int getMeasureWidth() {
            return this.measureWidth;
        }

        public final void setMeasureHeight$app_preprodQa(int i2) {
            this.measureHeight = i2;
        }

        public final void setMeasureResult$app_preprodQa(int i2, int i3) {
            this.measureWidth = i2;
            this.measureHeight = i3;
        }

        public final void setMeasureWidth$app_preprodQa(int i2) {
            this.measureWidth = i2;
        }
    }

    public BaseDrawer(@NotNull IndicatorOptions mIndicatorOptions) {
        Intrinsics.checkNotNullParameter(mIndicatorOptions, "mIndicatorOptions");
        this.mIndicatorOptions = mIndicatorOptions;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mMeasureResult = new MeasureResult(this);
        if (this.mIndicatorOptions.getSlideMode() == 4 || this.mIndicatorOptions.getSlideMode() == 5) {
            this.argbEvaluator = new ArgbEvaluator();
        }
    }

    private final int measureWidth() {
        float pageSize = this.mIndicatorOptions.getPageSize() - 1;
        return ((int) ((this.mIndicatorOptions.getSliderGap() * pageSize) + this.maxWidth + (pageSize * this.minWidth))) + 6;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean a() {
        return this.mIndicatorOptions.getNormalSliderWidth() == this.mIndicatorOptions.getCheckedSliderWidth();
    }

    protected int b() {
        return ((int) this.mIndicatorOptions.getSliderHeight()) + 3;
    }

    @Nullable
    public final ArgbEvaluator getArgbEvaluator$app_preprodQa() {
        return this.argbEvaluator;
    }

    @NotNull
    public final IndicatorOptions getMIndicatorOptions$app_preprodQa() {
        return this.mIndicatorOptions;
    }

    @NotNull
    public final Paint getMPaint$app_preprodQa() {
        return this.mPaint;
    }

    public final float getMaxWidth$app_preprodQa() {
        return this.maxWidth;
    }

    public final float getMinWidth$app_preprodQa() {
        return this.minWidth;
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.IDrawer
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.IDrawer
    @NotNull
    public MeasureResult onMeasure(int i2, int i3) {
        float coerceAtLeast;
        float coerceAtMost;
        MeasureResult measureResult;
        int measureWidth;
        int b2;
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(this.mIndicatorOptions.getNormalSliderWidth(), this.mIndicatorOptions.getCheckedSliderWidth());
        this.maxWidth = coerceAtLeast;
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(this.mIndicatorOptions.getNormalSliderWidth(), this.mIndicatorOptions.getCheckedSliderWidth());
        this.minWidth = coerceAtMost;
        if (this.mIndicatorOptions.getOrientation() == 1) {
            measureResult = this.mMeasureResult;
            measureWidth = b();
            b2 = measureWidth();
        } else {
            measureResult = this.mMeasureResult;
            measureWidth = measureWidth();
            b2 = b();
        }
        measureResult.setMeasureResult$app_preprodQa(measureWidth, b2);
        return this.mMeasureResult;
    }

    public final void setArgbEvaluator$app_preprodQa(@Nullable ArgbEvaluator argbEvaluator) {
        this.argbEvaluator = argbEvaluator;
    }

    public final void setMIndicatorOptions$app_preprodQa(@NotNull IndicatorOptions indicatorOptions) {
        Intrinsics.checkNotNullParameter(indicatorOptions, "<set-?>");
        this.mIndicatorOptions = indicatorOptions;
    }

    public final void setMPaint$app_preprodQa(@NotNull Paint paint) {
        Intrinsics.checkNotNullParameter(paint, "<set-?>");
        this.mPaint = paint;
    }

    public final void setMaxWidth$app_preprodQa(float f2) {
        this.maxWidth = f2;
    }

    public final void setMinWidth$app_preprodQa(float f2) {
        this.minWidth = f2;
    }
}
