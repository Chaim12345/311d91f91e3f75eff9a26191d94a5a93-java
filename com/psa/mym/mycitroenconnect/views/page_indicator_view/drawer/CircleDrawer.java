package com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer;

import android.animation.ArgbEvaluator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.option.IndicatorOptions;
import com.psa.mym.mycitroenconnect.views.page_indicator_view.utils.IndicatorUtils;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class CircleDrawer extends BaseDrawer {
    @NotNull
    private final RectF rectF;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CircleDrawer(@NotNull IndicatorOptions indicatorOptions) {
        super(indicatorOptions);
        Intrinsics.checkNotNullParameter(indicatorOptions, "indicatorOptions");
        this.rectF = new RectF();
    }

    private final void drawCircle(Canvas canvas, float f2, float f3, float f4) {
        float f5 = 3;
        canvas.drawCircle(f2 + f5, f3 + f5, f4, getMPaint$app_preprodQa());
    }

    private final void drawCircleSlider(Canvas canvas) {
        int currentPosition = getMIndicatorOptions$app_preprodQa().getCurrentPosition();
        IndicatorUtils indicatorUtils = IndicatorUtils.INSTANCE;
        float coordinateX = indicatorUtils.getCoordinateX(getMIndicatorOptions$app_preprodQa(), getMaxWidth$app_preprodQa(), currentPosition);
        drawCircle(canvas, coordinateX + ((indicatorUtils.getCoordinateX(getMIndicatorOptions$app_preprodQa(), getMaxWidth$app_preprodQa(), (currentPosition + 1) % getMIndicatorOptions$app_preprodQa().getPageSize()) - coordinateX) * getMIndicatorOptions$app_preprodQa().getSlideProgress()), indicatorUtils.getCoordinateY(getMaxWidth$app_preprodQa()), getMIndicatorOptions$app_preprodQa().getCheckedSliderWidth() / 2);
    }

    private final void drawColor(Canvas canvas) {
        int currentPosition = getMIndicatorOptions$app_preprodQa().getCurrentPosition();
        float slideProgress = getMIndicatorOptions$app_preprodQa().getSlideProgress();
        IndicatorUtils indicatorUtils = IndicatorUtils.INSTANCE;
        float coordinateX = indicatorUtils.getCoordinateX(getMIndicatorOptions$app_preprodQa(), getMaxWidth$app_preprodQa(), currentPosition);
        float coordinateY = indicatorUtils.getCoordinateY(getMaxWidth$app_preprodQa());
        ArgbEvaluator argbEvaluator$app_preprodQa = getArgbEvaluator$app_preprodQa();
        Object evaluate = argbEvaluator$app_preprodQa != null ? argbEvaluator$app_preprodQa.evaluate(slideProgress, Integer.valueOf(getMIndicatorOptions$app_preprodQa().getCheckedSliderColor()), Integer.valueOf(getMIndicatorOptions$app_preprodQa().getNormalSliderColor())) : null;
        Paint mPaint$app_preprodQa = getMPaint$app_preprodQa();
        Objects.requireNonNull(evaluate, "null cannot be cast to non-null type kotlin.Int");
        mPaint$app_preprodQa.setColor(((Integer) evaluate).intValue());
        float f2 = 2;
        drawCircle(canvas, coordinateX, coordinateY, getMIndicatorOptions$app_preprodQa().getNormalSliderWidth() / f2);
        ArgbEvaluator argbEvaluator$app_preprodQa2 = getArgbEvaluator$app_preprodQa();
        Object evaluate2 = argbEvaluator$app_preprodQa2 != null ? argbEvaluator$app_preprodQa2.evaluate(1 - slideProgress, Integer.valueOf(getMIndicatorOptions$app_preprodQa().getCheckedSliderColor()), Integer.valueOf(getMIndicatorOptions$app_preprodQa().getNormalSliderColor())) : null;
        Paint mPaint$app_preprodQa2 = getMPaint$app_preprodQa();
        Objects.requireNonNull(evaluate2, "null cannot be cast to non-null type kotlin.Int");
        mPaint$app_preprodQa2.setColor(((Integer) evaluate2).intValue());
        drawCircle(canvas, currentPosition == getMIndicatorOptions$app_preprodQa().getPageSize() - 1 ? indicatorUtils.getCoordinateX(getMIndicatorOptions$app_preprodQa(), getMaxWidth$app_preprodQa(), 0) : getMIndicatorOptions$app_preprodQa().getNormalSliderWidth() + coordinateX + getMIndicatorOptions$app_preprodQa().getSliderGap(), coordinateY, getMIndicatorOptions$app_preprodQa().getCheckedSliderWidth() / f2);
    }

    private final void drawNormal(Canvas canvas) {
        float normalSliderWidth = getMIndicatorOptions$app_preprodQa().getNormalSliderWidth();
        getMPaint$app_preprodQa().setColor(getMIndicatorOptions$app_preprodQa().getNormalSliderColor());
        int pageSize = getMIndicatorOptions$app_preprodQa().getPageSize();
        for (int i2 = 0; i2 < pageSize; i2++) {
            IndicatorUtils indicatorUtils = IndicatorUtils.INSTANCE;
            drawCircle(canvas, indicatorUtils.getCoordinateX(getMIndicatorOptions$app_preprodQa(), getMaxWidth$app_preprodQa(), i2), indicatorUtils.getCoordinateY(getMaxWidth$app_preprodQa()), normalSliderWidth / 2);
        }
    }

    private final void drawScaleSlider(Canvas canvas) {
        Object evaluate;
        int currentPosition = getMIndicatorOptions$app_preprodQa().getCurrentPosition();
        float slideProgress = getMIndicatorOptions$app_preprodQa().getSlideProgress();
        IndicatorUtils indicatorUtils = IndicatorUtils.INSTANCE;
        float coordinateX = indicatorUtils.getCoordinateX(getMIndicatorOptions$app_preprodQa(), getMaxWidth$app_preprodQa(), currentPosition);
        float coordinateY = indicatorUtils.getCoordinateY(getMaxWidth$app_preprodQa());
        if (slideProgress < 1.0f) {
            ArgbEvaluator argbEvaluator$app_preprodQa = getArgbEvaluator$app_preprodQa();
            Object evaluate2 = argbEvaluator$app_preprodQa != null ? argbEvaluator$app_preprodQa.evaluate(slideProgress, Integer.valueOf(getMIndicatorOptions$app_preprodQa().getCheckedSliderColor()), Integer.valueOf(getMIndicatorOptions$app_preprodQa().getNormalSliderColor())) : null;
            Paint mPaint$app_preprodQa = getMPaint$app_preprodQa();
            Objects.requireNonNull(evaluate2, "null cannot be cast to non-null type kotlin.Int");
            mPaint$app_preprodQa.setColor(((Integer) evaluate2).intValue());
            float f2 = 2;
            drawCircle(canvas, coordinateX, coordinateY, (getMIndicatorOptions$app_preprodQa().getCheckedSliderWidth() / f2) - (((getMIndicatorOptions$app_preprodQa().getCheckedSliderWidth() / f2) - (getMIndicatorOptions$app_preprodQa().getNormalSliderWidth() / f2)) * slideProgress));
        }
        if (currentPosition == getMIndicatorOptions$app_preprodQa().getPageSize() - 1) {
            ArgbEvaluator argbEvaluator$app_preprodQa2 = getArgbEvaluator$app_preprodQa();
            evaluate = argbEvaluator$app_preprodQa2 != null ? argbEvaluator$app_preprodQa2.evaluate(slideProgress, Integer.valueOf(getMIndicatorOptions$app_preprodQa().getNormalSliderColor()), Integer.valueOf(getMIndicatorOptions$app_preprodQa().getCheckedSliderColor())) : null;
            Paint mPaint$app_preprodQa2 = getMPaint$app_preprodQa();
            Objects.requireNonNull(evaluate, "null cannot be cast to non-null type kotlin.Int");
            mPaint$app_preprodQa2.setColor(((Integer) evaluate).intValue());
            float f3 = 2;
            drawCircle(canvas, getMaxWidth$app_preprodQa() / f3, coordinateY, (getMinWidth$app_preprodQa() / f3) + (((getMaxWidth$app_preprodQa() / f3) - (getMinWidth$app_preprodQa() / f3)) * slideProgress));
        } else if (slideProgress > 0.0f) {
            ArgbEvaluator argbEvaluator$app_preprodQa3 = getArgbEvaluator$app_preprodQa();
            evaluate = argbEvaluator$app_preprodQa3 != null ? argbEvaluator$app_preprodQa3.evaluate(slideProgress, Integer.valueOf(getMIndicatorOptions$app_preprodQa().getNormalSliderColor()), Integer.valueOf(getMIndicatorOptions$app_preprodQa().getCheckedSliderColor())) : null;
            Paint mPaint$app_preprodQa3 = getMPaint$app_preprodQa();
            Objects.requireNonNull(evaluate, "null cannot be cast to non-null type kotlin.Int");
            mPaint$app_preprodQa3.setColor(((Integer) evaluate).intValue());
            float f4 = 2;
            drawCircle(canvas, coordinateX + getMIndicatorOptions$app_preprodQa().getSliderGap() + getMIndicatorOptions$app_preprodQa().getNormalSliderWidth(), coordinateY, (getMIndicatorOptions$app_preprodQa().getNormalSliderWidth() / f4) + (((getMIndicatorOptions$app_preprodQa().getCheckedSliderWidth() / f4) - (getMIndicatorOptions$app_preprodQa().getNormalSliderWidth() / f4)) * slideProgress));
        }
    }

    private final void drawSlider(Canvas canvas) {
        getMPaint$app_preprodQa().setColor(getMIndicatorOptions$app_preprodQa().getCheckedSliderColor());
        int slideMode = getMIndicatorOptions$app_preprodQa().getSlideMode();
        if (slideMode == 0 || slideMode == 2) {
            drawCircleSlider(canvas);
        } else if (slideMode == 3) {
            drawWormSlider(canvas);
        } else if (slideMode == 4) {
            drawScaleSlider(canvas);
        } else if (slideMode != 5) {
        } else {
            drawColor(canvas);
        }
    }

    private final void drawWormSlider(Canvas canvas) {
        float coerceAtLeast;
        float coerceAtMost;
        float normalSliderWidth = getMIndicatorOptions$app_preprodQa().getNormalSliderWidth();
        float slideProgress = getMIndicatorOptions$app_preprodQa().getSlideProgress();
        int currentPosition = getMIndicatorOptions$app_preprodQa().getCurrentPosition();
        float sliderGap = getMIndicatorOptions$app_preprodQa().getSliderGap() + getMIndicatorOptions$app_preprodQa().getNormalSliderWidth();
        float coordinateX = IndicatorUtils.INSTANCE.getCoordinateX(getMIndicatorOptions$app_preprodQa(), getMaxWidth$app_preprodQa(), currentPosition);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast((slideProgress - 0.5f) * sliderGap * 2.0f, 0.0f);
        float f2 = 2;
        float normalSliderWidth2 = (coerceAtLeast + coordinateX) - (getMIndicatorOptions$app_preprodQa().getNormalSliderWidth() / f2);
        float f3 = 3;
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(slideProgress * sliderGap * 2.0f, sliderGap);
        this.rectF.set(normalSliderWidth2 + f3, 3.0f, coordinateX + coerceAtMost + (getMIndicatorOptions$app_preprodQa().getNormalSliderWidth() / f2) + f3, f3 + normalSliderWidth);
        canvas.drawRoundRect(this.rectF, normalSliderWidth, normalSliderWidth, getMPaint$app_preprodQa());
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.BaseDrawer
    protected int b() {
        return ((int) getMaxWidth$app_preprodQa()) + 6;
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.IDrawer
    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        int pageSize = getMIndicatorOptions$app_preprodQa().getPageSize();
        if (pageSize > 1 || (getMIndicatorOptions$app_preprodQa().getShowIndicatorOneItem() && pageSize == 1)) {
            drawNormal(canvas);
            drawSlider(canvas);
        }
    }
}
