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
public class RectDrawer extends BaseDrawer {
    @NotNull
    private RectF mRectF;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RectDrawer(@NotNull IndicatorOptions indicatorOptions) {
        super(indicatorOptions);
        Intrinsics.checkNotNullParameter(indicatorOptions, "indicatorOptions");
        this.mRectF = new RectF();
    }

    private final void drawCheckedSlider(Canvas canvas) {
        getMPaint$app_preprodQa().setColor(getMIndicatorOptions$app_preprodQa().getCheckedSliderColor());
        int slideMode = getMIndicatorOptions$app_preprodQa().getSlideMode();
        if (slideMode == 2) {
            drawSmoothSlider(canvas);
        } else if (slideMode == 3) {
            drawWormSlider(canvas);
        } else if (slideMode != 5) {
        } else {
            drawColorSlider(canvas);
        }
    }

    private final void drawColorSlider(Canvas canvas) {
        int currentPosition = getMIndicatorOptions$app_preprodQa().getCurrentPosition();
        float slideProgress = getMIndicatorOptions$app_preprodQa().getSlideProgress();
        float f2 = currentPosition;
        float minWidth$app_preprodQa = (getMinWidth$app_preprodQa() * f2) + (f2 * getMIndicatorOptions$app_preprodQa().getSliderGap());
        if (slideProgress < 0.99d) {
            ArgbEvaluator argbEvaluator$app_preprodQa = getArgbEvaluator$app_preprodQa();
            Object evaluate = argbEvaluator$app_preprodQa != null ? argbEvaluator$app_preprodQa.evaluate(slideProgress, Integer.valueOf(getMIndicatorOptions$app_preprodQa().getCheckedSliderColor()), Integer.valueOf(getMIndicatorOptions$app_preprodQa().getNormalSliderColor())) : null;
            Paint mPaint$app_preprodQa = getMPaint$app_preprodQa();
            Objects.requireNonNull(evaluate, "null cannot be cast to non-null type kotlin.Int");
            mPaint$app_preprodQa.setColor(((Integer) evaluate).intValue());
            this.mRectF.set(minWidth$app_preprodQa, 0.0f, getMinWidth$app_preprodQa() + minWidth$app_preprodQa, getMIndicatorOptions$app_preprodQa().getSliderHeight());
            d(canvas, getMIndicatorOptions$app_preprodQa().getSliderHeight(), getMIndicatorOptions$app_preprodQa().getSliderHeight());
        }
        float sliderGap = minWidth$app_preprodQa + getMIndicatorOptions$app_preprodQa().getSliderGap() + getMIndicatorOptions$app_preprodQa().getNormalSliderWidth();
        if (currentPosition == getMIndicatorOptions$app_preprodQa().getPageSize() - 1) {
            sliderGap = 0.0f;
        }
        ArgbEvaluator argbEvaluator$app_preprodQa2 = getArgbEvaluator$app_preprodQa();
        Object evaluate2 = argbEvaluator$app_preprodQa2 != null ? argbEvaluator$app_preprodQa2.evaluate(1 - slideProgress, Integer.valueOf(getMIndicatorOptions$app_preprodQa().getCheckedSliderColor()), Integer.valueOf(getMIndicatorOptions$app_preprodQa().getNormalSliderColor())) : null;
        Paint mPaint$app_preprodQa2 = getMPaint$app_preprodQa();
        Objects.requireNonNull(evaluate2, "null cannot be cast to non-null type kotlin.Int");
        mPaint$app_preprodQa2.setColor(((Integer) evaluate2).intValue());
        this.mRectF.set(sliderGap, 0.0f, getMinWidth$app_preprodQa() + sliderGap, getMIndicatorOptions$app_preprodQa().getSliderHeight());
        d(canvas, getMIndicatorOptions$app_preprodQa().getSliderHeight(), getMIndicatorOptions$app_preprodQa().getSliderHeight());
    }

    private final void drawInequalitySlider(Canvas canvas, int i2) {
        int i3 = 0;
        float f2 = 0.0f;
        while (i3 < i2) {
            float maxWidth$app_preprodQa = i3 == getMIndicatorOptions$app_preprodQa().getCurrentPosition() ? getMaxWidth$app_preprodQa() : getMinWidth$app_preprodQa();
            getMPaint$app_preprodQa().setColor(i3 == getMIndicatorOptions$app_preprodQa().getCurrentPosition() ? getMIndicatorOptions$app_preprodQa().getCheckedSliderColor() : getMIndicatorOptions$app_preprodQa().getNormalSliderColor());
            this.mRectF.set(f2, 0.0f, f2 + maxWidth$app_preprodQa, getMIndicatorOptions$app_preprodQa().getSliderHeight());
            d(canvas, getMIndicatorOptions$app_preprodQa().getSliderHeight(), getMIndicatorOptions$app_preprodQa().getSliderHeight());
            f2 += maxWidth$app_preprodQa + getMIndicatorOptions$app_preprodQa().getSliderGap();
            i3++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0173  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void drawScaleSlider(Canvas canvas, int i2) {
        float f2;
        int checkedSliderColor = getMIndicatorOptions$app_preprodQa().getCheckedSliderColor();
        float sliderGap = getMIndicatorOptions$app_preprodQa().getSliderGap();
        float sliderHeight = getMIndicatorOptions$app_preprodQa().getSliderHeight();
        int currentPosition = getMIndicatorOptions$app_preprodQa().getCurrentPosition();
        float normalSliderWidth = getMIndicatorOptions$app_preprodQa().getNormalSliderWidth();
        float checkedSliderWidth = getMIndicatorOptions$app_preprodQa().getCheckedSliderWidth();
        if (i2 < currentPosition) {
            getMPaint$app_preprodQa().setColor(getMIndicatorOptions$app_preprodQa().getNormalSliderColor());
            float f3 = i2;
            float slideProgress = currentPosition == getMIndicatorOptions$app_preprodQa().getPageSize() - 1 ? (f3 * normalSliderWidth) + (f3 * sliderGap) + ((checkedSliderWidth - normalSliderWidth) * getMIndicatorOptions$app_preprodQa().getSlideProgress()) : (f3 * normalSliderWidth) + (f3 * sliderGap);
            this.mRectF.set(slideProgress, 0.0f, normalSliderWidth + slideProgress, sliderHeight);
        } else if (i2 == currentPosition) {
            getMPaint$app_preprodQa().setColor(checkedSliderColor);
            float slideProgress2 = getMIndicatorOptions$app_preprodQa().getSlideProgress();
            if (currentPosition == getMIndicatorOptions$app_preprodQa().getPageSize() - 1) {
                ArgbEvaluator argbEvaluator$app_preprodQa = getArgbEvaluator$app_preprodQa();
                Object evaluate = argbEvaluator$app_preprodQa != null ? argbEvaluator$app_preprodQa.evaluate(slideProgress2, Integer.valueOf(checkedSliderColor), Integer.valueOf(getMIndicatorOptions$app_preprodQa().getNormalSliderColor())) : null;
                Paint mPaint$app_preprodQa = getMPaint$app_preprodQa();
                Objects.requireNonNull(evaluate, "null cannot be cast to non-null type kotlin.Int");
                mPaint$app_preprodQa.setColor(((Integer) evaluate).intValue());
                float pageSize = ((getMIndicatorOptions$app_preprodQa().getPageSize() - 1) * (getMIndicatorOptions$app_preprodQa().getSliderGap() + normalSliderWidth)) + checkedSliderWidth;
                this.mRectF.set((pageSize - checkedSliderWidth) + ((checkedSliderWidth - normalSliderWidth) * slideProgress2), 0.0f, pageSize, sliderHeight);
                d(canvas, sliderHeight, sliderHeight);
            } else if (slideProgress2 < 1.0f) {
                ArgbEvaluator argbEvaluator$app_preprodQa2 = getArgbEvaluator$app_preprodQa();
                Object evaluate2 = argbEvaluator$app_preprodQa2 != null ? argbEvaluator$app_preprodQa2.evaluate(slideProgress2, Integer.valueOf(checkedSliderColor), Integer.valueOf(getMIndicatorOptions$app_preprodQa().getNormalSliderColor())) : null;
                Paint mPaint$app_preprodQa2 = getMPaint$app_preprodQa();
                Objects.requireNonNull(evaluate2, "null cannot be cast to non-null type kotlin.Int");
                mPaint$app_preprodQa2.setColor(((Integer) evaluate2).intValue());
                float f4 = i2;
                float f5 = (f4 * normalSliderWidth) + (f4 * sliderGap);
                f2 = 0.0f;
                this.mRectF.set(f5, 0.0f, f5 + normalSliderWidth + ((checkedSliderWidth - normalSliderWidth) * (1 - slideProgress2)), sliderHeight);
                d(canvas, sliderHeight, sliderHeight);
                if (currentPosition != getMIndicatorOptions$app_preprodQa().getPageSize() - 1) {
                    if (slideProgress2 <= f2) {
                        return;
                    }
                    ArgbEvaluator argbEvaluator$app_preprodQa3 = getArgbEvaluator$app_preprodQa();
                    Object evaluate3 = argbEvaluator$app_preprodQa3 != null ? argbEvaluator$app_preprodQa3.evaluate(1 - slideProgress2, Integer.valueOf(checkedSliderColor), Integer.valueOf(getMIndicatorOptions$app_preprodQa().getNormalSliderColor())) : null;
                    Paint mPaint$app_preprodQa3 = getMPaint$app_preprodQa();
                    Objects.requireNonNull(evaluate3, "null cannot be cast to non-null type kotlin.Int");
                    mPaint$app_preprodQa3.setColor(((Integer) evaluate3).intValue());
                    this.mRectF.set(0.0f, 0.0f, normalSliderWidth + 0.0f + ((checkedSliderWidth - normalSliderWidth) * slideProgress2), sliderHeight);
                } else if (slideProgress2 <= f2) {
                    return;
                } else {
                    ArgbEvaluator argbEvaluator$app_preprodQa4 = getArgbEvaluator$app_preprodQa();
                    Object evaluate4 = argbEvaluator$app_preprodQa4 != null ? argbEvaluator$app_preprodQa4.evaluate(1 - slideProgress2, Integer.valueOf(checkedSliderColor), Integer.valueOf(getMIndicatorOptions$app_preprodQa().getNormalSliderColor())) : null;
                    Paint mPaint$app_preprodQa4 = getMPaint$app_preprodQa();
                    Objects.requireNonNull(evaluate4, "null cannot be cast to non-null type kotlin.Int");
                    mPaint$app_preprodQa4.setColor(((Integer) evaluate4).intValue());
                    float f6 = i2;
                    float f7 = (f6 * normalSliderWidth) + (f6 * sliderGap) + normalSliderWidth + sliderGap + checkedSliderWidth;
                    this.mRectF.set((f7 - normalSliderWidth) - ((checkedSliderWidth - normalSliderWidth) * slideProgress2), 0.0f, f7, sliderHeight);
                }
            }
            f2 = 0.0f;
            if (currentPosition != getMIndicatorOptions$app_preprodQa().getPageSize() - 1) {
            }
        } else {
            if (currentPosition + 1 == i2) {
                if (!(getMIndicatorOptions$app_preprodQa().getSlideProgress() == 0.0f)) {
                    return;
                }
            }
            getMPaint$app_preprodQa().setColor(getMIndicatorOptions$app_preprodQa().getNormalSliderColor());
            float f8 = i2;
            float minWidth$app_preprodQa = (getMinWidth$app_preprodQa() * f8) + (f8 * sliderGap) + (checkedSliderWidth - getMinWidth$app_preprodQa());
            this.mRectF.set(minWidth$app_preprodQa, 0.0f, getMinWidth$app_preprodQa() + minWidth$app_preprodQa, sliderHeight);
        }
        d(canvas, sliderHeight, sliderHeight);
    }

    private final void drawSmoothSlider(Canvas canvas) {
        int currentPosition = getMIndicatorOptions$app_preprodQa().getCurrentPosition();
        float sliderGap = getMIndicatorOptions$app_preprodQa().getSliderGap();
        float sliderHeight = getMIndicatorOptions$app_preprodQa().getSliderHeight();
        float f2 = currentPosition;
        float maxWidth$app_preprodQa = (getMaxWidth$app_preprodQa() * f2) + (f2 * sliderGap) + ((getMaxWidth$app_preprodQa() + sliderGap) * getMIndicatorOptions$app_preprodQa().getSlideProgress());
        this.mRectF.set(maxWidth$app_preprodQa, 0.0f, getMaxWidth$app_preprodQa() + maxWidth$app_preprodQa, sliderHeight);
        d(canvas, sliderHeight, sliderHeight);
    }

    private final void drawUncheckedSlider(Canvas canvas, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            getMPaint$app_preprodQa().setColor(getMIndicatorOptions$app_preprodQa().getNormalSliderColor());
            float f2 = i3;
            float maxWidth$app_preprodQa = (getMaxWidth$app_preprodQa() * f2) + (f2 * getMIndicatorOptions$app_preprodQa().getSliderGap()) + (getMaxWidth$app_preprodQa() - getMinWidth$app_preprodQa());
            this.mRectF.set(maxWidth$app_preprodQa, 0.0f, getMinWidth$app_preprodQa() + maxWidth$app_preprodQa, getMIndicatorOptions$app_preprodQa().getSliderHeight());
            d(canvas, getMIndicatorOptions$app_preprodQa().getSliderHeight(), getMIndicatorOptions$app_preprodQa().getSliderHeight());
        }
    }

    private final void drawWormSlider(Canvas canvas) {
        float coerceAtLeast;
        float coerceAtMost;
        float sliderHeight = getMIndicatorOptions$app_preprodQa().getSliderHeight();
        float slideProgress = getMIndicatorOptions$app_preprodQa().getSlideProgress();
        int currentPosition = getMIndicatorOptions$app_preprodQa().getCurrentPosition();
        float sliderGap = getMIndicatorOptions$app_preprodQa().getSliderGap() + getMIndicatorOptions$app_preprodQa().getNormalSliderWidth();
        float coordinateX = IndicatorUtils.INSTANCE.getCoordinateX(getMIndicatorOptions$app_preprodQa(), getMaxWidth$app_preprodQa(), currentPosition);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast((slideProgress - 0.5f) * sliderGap * 2.0f, 0.0f);
        float f2 = 2;
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(slideProgress * sliderGap * 2.0f, sliderGap);
        this.mRectF.set((coerceAtLeast + coordinateX) - (getMIndicatorOptions$app_preprodQa().getNormalSliderWidth() / f2), 0.0f, coordinateX + coerceAtMost + (getMIndicatorOptions$app_preprodQa().getNormalSliderWidth() / f2), sliderHeight);
        d(canvas, sliderHeight, sliderHeight);
    }

    protected void c(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
    }

    protected void d(@NotNull Canvas canvas, float f2, float f3) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        c(canvas);
    }

    @NotNull
    public final RectF getMRectF$app_preprodQa() {
        return this.mRectF;
    }

    @Override // com.psa.mym.mycitroenconnect.views.page_indicator_view.drawer.IDrawer
    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        int pageSize = getMIndicatorOptions$app_preprodQa().getPageSize();
        if (pageSize > 1 || (getMIndicatorOptions$app_preprodQa().getShowIndicatorOneItem() && pageSize == 1)) {
            if (a() && getMIndicatorOptions$app_preprodQa().getSlideMode() != 0) {
                drawUncheckedSlider(canvas, pageSize);
                drawCheckedSlider(canvas);
            } else if (getMIndicatorOptions$app_preprodQa().getSlideMode() != 4) {
                drawInequalitySlider(canvas, pageSize);
            } else {
                for (int i2 = 0; i2 < pageSize; i2++) {
                    drawScaleSlider(canvas, i2);
                }
            }
        }
    }

    public final void setMRectF$app_preprodQa(@NotNull RectF rectF) {
        Intrinsics.checkNotNullParameter(rectF, "<set-?>");
        this.mRectF = rectF;
    }
}
