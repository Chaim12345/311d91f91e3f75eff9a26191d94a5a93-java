package com.rd;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.rd.animation.AbsAnimation;
import com.rd.animation.AnimationType;
import com.rd.animation.ColorAnimation;
import com.rd.animation.ValueAnimation;
import com.rd.pageindicatorview.R;
import com.rd.utils.DensityUtils;
/* loaded from: classes3.dex */
public class PageIndicatorView extends View implements ViewPager.OnPageChangeListener {
    private static final int COUNT_NOT_SET = -1;
    private static final int DEFAULT_CIRCLES_COUNT = 3;
    private static final int DEFAULT_PADDING_DP = 8;
    private static final int DEFAULT_RADIUS_DP = 6;
    private ValueAnimation animation;
    private long animationDuration;
    private AnimationType animationType;
    private int count;
    private boolean dynamicCount;
    private Paint fillPaint;
    private int frameColor;
    private int frameColorReverse;
    private int frameHeight;
    private int frameLeftX;
    private int frameRadiusPx;
    private int frameRadiusReversePx;
    private int frameRightX;
    private int frameStrokePx;
    private int frameStrokeReversePx;
    private int frameXCoordinate;
    private boolean interactiveAnimation;
    private boolean isCountSet;
    private boolean isFrameValuesSet;
    private boolean isListenerSet;
    private int lastSelectedPosition;
    private int paddingPx;
    private int radiusPx;
    private RectF rect;
    private float scaleFactor;
    private int selectedColor;
    private int selectedPosition;
    private int selectingPosition;
    private DataSetObserver setObserver;
    private Paint strokePaint;
    private int strokePx;
    private int unselectedColor;
    private ViewPager viewPager;
    private int viewPagerId;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.rd.PageIndicatorView$3  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f10805a;

        static {
            int[] iArr = new int[AnimationType.values().length];
            f10805a = iArr;
            try {
                iArr[AnimationType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10805a[AnimationType.COLOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10805a[AnimationType.SCALE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10805a[AnimationType.WORM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10805a[AnimationType.FILL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10805a[AnimationType.SLIDE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10805a[AnimationType.THIN_WORM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public PageIndicatorView(Context context) {
        super(context);
        this.fillPaint = new Paint();
        this.strokePaint = new Paint();
        this.rect = new RectF();
        this.animationType = AnimationType.NONE;
        init(null);
    }

    public PageIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.fillPaint = new Paint();
        this.strokePaint = new Paint();
        this.rect = new RectF();
        this.animationType = AnimationType.NONE;
        init(attributeSet);
    }

    public PageIndicatorView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.fillPaint = new Paint();
        this.strokePaint = new Paint();
        this.rect = new RectF();
        this.animationType = AnimationType.NONE;
        init(attributeSet);
    }

    @TargetApi(21)
    public PageIndicatorView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.fillPaint = new Paint();
        this.strokePaint = new Paint();
        this.rect = new RectF();
        this.animationType = AnimationType.NONE;
        init(attributeSet);
    }

    private int calculateActualViewWidth() {
        int i2 = (this.radiusPx * 2) + this.strokePx;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = this.count;
            if (i3 >= i5) {
                return i4;
            }
            i4 += i2;
            if (i3 < i5 - 1) {
                i4 += this.paddingPx;
            }
            i3++;
        }
    }

    private void drawCircle(@NonNull Canvas canvas, int i2, int i3, int i4) {
        boolean z = this.interactiveAnimation;
        boolean z2 = true;
        boolean z3 = !z && (i2 == this.selectedPosition || i2 == this.lastSelectedPosition);
        if (!z || (i2 != this.selectingPosition && i2 != this.selectedPosition)) {
            z2 = false;
        }
        if (z3 || z2) {
            drawWithAnimationEffect(canvas, i2, i3, i4);
        } else {
            drawWithNoEffect(canvas, i2, i3, i4);
        }
    }

    private void drawIndicatorView(@NonNull Canvas canvas) {
        int height = getHeight() / 2;
        for (int i2 = 0; i2 < this.count; i2++) {
            drawCircle(canvas, i2, getXCoordinate(i2), height);
        }
    }

    private void drawWithAnimationEffect(@NonNull Canvas canvas, int i2, int i3, int i4) {
        switch (AnonymousClass3.f10805a[this.animationType.ordinal()]) {
            case 1:
                drawWithNoEffect(canvas, i2, i3, i4);
                return;
            case 2:
                drawWithColorAnimation(canvas, i2, i3, i4);
                return;
            case 3:
                drawWithScaleAnimation(canvas, i2, i3, i4);
                return;
            case 4:
                drawWithWormAnimation(canvas, i3, i4);
                return;
            case 5:
                drawWithFillAnimation(canvas, i2, i3, i4);
                return;
            case 6:
                drawWithSlideAnimation(canvas, i2, i3, i4);
                return;
            case 7:
                drawWithThinWormAnimation(canvas, i3, i4);
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0019, code lost:
        if (r4 == r2.lastSelectedPosition) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x001b, code lost:
        r0 = r2.frameColorReverse;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x000d, code lost:
        if (r4 == r2.selectedPosition) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void drawWithColorAnimation(@NonNull Canvas canvas, int i2, int i3, int i4) {
        int i5 = this.unselectedColor;
        if (this.interactiveAnimation) {
            if (i2 != this.selectingPosition) {
            }
            i5 = this.frameColor;
        } else {
            if (i2 != this.selectedPosition) {
            }
            i5 = this.frameColor;
        }
        this.fillPaint.setColor(i5);
        canvas.drawCircle(i3, i4, this.radiusPx, this.fillPaint);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
        if (r6 == r4.lastSelectedPosition) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
        r0 = r4.frameColorReverse;
        r1 = r4.frameRadiusReversePx;
        r2 = r4.frameStrokeReversePx;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0012, code lost:
        if (r6 == r4.selectedPosition) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void drawWithFillAnimation(@NonNull Canvas canvas, int i2, int i3, int i4) {
        int i5 = this.unselectedColor;
        float f2 = this.radiusPx;
        int i6 = this.strokePx;
        if (this.interactiveAnimation) {
            if (i2 != this.selectingPosition) {
            }
            i5 = this.frameColor;
            f2 = this.frameRadiusPx;
            i6 = this.frameStrokePx;
        } else {
            if (i2 != this.selectedPosition) {
            }
            i5 = this.frameColor;
            f2 = this.frameRadiusPx;
            i6 = this.frameStrokePx;
        }
        this.strokePaint.setColor(i5);
        this.strokePaint.setStrokeWidth(this.strokePx);
        float f3 = i3;
        float f4 = i4;
        canvas.drawCircle(f3, f4, this.radiusPx, this.strokePaint);
        this.strokePaint.setStrokeWidth(i6);
        canvas.drawCircle(f3, f4, f2, this.strokePaint);
    }

    private void drawWithNoEffect(@NonNull Canvas canvas, int i2, int i3, int i4) {
        Paint paint;
        float f2 = this.radiusPx;
        int i5 = this.unselectedColor;
        AnimationType animationType = this.animationType;
        if (animationType == AnimationType.SCALE) {
            f2 *= this.scaleFactor;
        }
        if (i2 == this.selectedPosition) {
            i5 = this.selectedColor;
        }
        if (animationType == AnimationType.FILL) {
            paint = this.strokePaint;
            paint.setStrokeWidth(this.strokePx);
        } else {
            paint = this.fillPaint;
        }
        paint.setColor(i5);
        canvas.drawCircle(i3, i4, f2, paint);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001d, code lost:
        if (r5 == r3.lastSelectedPosition) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x001f, code lost:
        r1 = r3.frameRadiusReversePx;
        r0 = r3.frameColorReverse;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x000f, code lost:
        if (r5 == r3.selectedPosition) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void drawWithScaleAnimation(@NonNull Canvas canvas, int i2, int i3, int i4) {
        int i5 = this.unselectedColor;
        int i6 = this.radiusPx;
        if (this.interactiveAnimation) {
            if (i2 != this.selectingPosition) {
            }
            i6 = this.frameRadiusPx;
            i5 = this.frameColor;
        } else {
            if (i2 != this.selectedPosition) {
            }
            i6 = this.frameRadiusPx;
            i5 = this.frameColor;
        }
        this.fillPaint.setColor(i5);
        canvas.drawCircle(i3, i4, i6, this.fillPaint);
    }

    private void drawWithSlideAnimation(@NonNull Canvas canvas, int i2, int i3, int i4) {
        this.fillPaint.setColor(this.unselectedColor);
        float f2 = i4;
        canvas.drawCircle(i3, f2, this.radiusPx, this.fillPaint);
        boolean z = this.interactiveAnimation;
        if (!z || (i2 != this.selectingPosition && i2 != this.selectedPosition)) {
            if (z) {
                return;
            }
            if (i2 != this.selectedPosition && i2 != this.lastSelectedPosition) {
                return;
            }
        }
        this.fillPaint.setColor(this.selectedColor);
        canvas.drawCircle(this.frameXCoordinate, f2, this.radiusPx, this.fillPaint);
    }

    private void drawWithThinWormAnimation(@NonNull Canvas canvas, int i2, int i3) {
        int i4 = this.radiusPx;
        int i5 = this.frameLeftX;
        int i6 = this.frameRightX;
        int i7 = this.frameHeight;
        RectF rectF = this.rect;
        rectF.left = i5;
        rectF.right = i6;
        rectF.top = i3 - (i7 / 2);
        rectF.bottom = (i7 / 2) + i3;
        this.fillPaint.setColor(this.unselectedColor);
        canvas.drawCircle(i2, i3, i4, this.fillPaint);
        this.fillPaint.setColor(this.selectedColor);
        RectF rectF2 = this.rect;
        int i8 = this.radiusPx;
        canvas.drawRoundRect(rectF2, i8, i8, this.fillPaint);
    }

    private void drawWithWormAnimation(@NonNull Canvas canvas, int i2, int i3) {
        int i4 = this.radiusPx;
        int i5 = this.frameLeftX;
        int i6 = this.frameRightX;
        RectF rectF = this.rect;
        rectF.left = i5;
        rectF.right = i6;
        rectF.top = i3 - i4;
        rectF.bottom = i3 + i4;
        this.fillPaint.setColor(this.unselectedColor);
        canvas.drawCircle(i2, i3, i4, this.fillPaint);
        this.fillPaint.setColor(this.selectedColor);
        RectF rectF2 = this.rect;
        int i7 = this.radiusPx;
        canvas.drawRoundRect(rectF2, i7, i7, this.fillPaint);
    }

    private void findViewPager() {
        View findViewById;
        if (this.viewPagerId != 0 && (getContext() instanceof Activity) && (findViewById = ((Activity) getContext()).findViewById(this.viewPagerId)) != null && (findViewById instanceof ViewPager)) {
            setViewPager((ViewPager) findViewById);
        }
    }

    private AnimationType getAnimationType(int i2) {
        switch (i2) {
            case 0:
                return AnimationType.NONE;
            case 1:
                return AnimationType.COLOR;
            case 2:
                return AnimationType.SCALE;
            case 3:
                return AnimationType.WORM;
            case 4:
                return AnimationType.SLIDE;
            case 5:
                return AnimationType.FILL;
            case 6:
                return AnimationType.THIN_WORM;
            default:
                return AnimationType.NONE;
        }
    }

    private Pair<Integer, Float> getProgress(int i2, float f2) {
        int i3 = this.selectedPosition;
        boolean z = false;
        boolean z2 = i2 > i3;
        int i4 = i2 + 1;
        boolean z3 = i4 < i3;
        if (z2 || z3) {
            this.selectedPosition = i2;
        }
        float f3 = 0.0f;
        if (this.selectedPosition == i2 && f2 != 0.0f) {
            z = true;
        }
        if (z) {
            i2 = i4;
        } else {
            f2 = 1.0f - f2;
        }
        if (f2 > 1.0f) {
            f3 = 1.0f;
        } else if (f2 >= 0.0f) {
            f3 = f2;
        }
        return new Pair<>(Integer.valueOf(i2), Float.valueOf(f3));
    }

    @Nullable
    private AbsAnimation getSelectedAnimation() {
        switch (AnonymousClass3.f10805a[this.animationType.ordinal()]) {
            case 2:
                return this.animation.color().with(this.unselectedColor, this.selectedColor);
            case 3:
                return this.animation.scale().with(this.unselectedColor, this.selectedColor, this.radiusPx, this.scaleFactor);
            case 4:
            case 6:
            case 7:
                int xCoordinate = getXCoordinate(this.selectedPosition);
                int xCoordinate2 = getXCoordinate(this.selectingPosition);
                AnimationType animationType = this.animationType;
                if (animationType == AnimationType.SLIDE) {
                    return this.animation.slide().with(xCoordinate, xCoordinate2);
                }
                boolean z = this.selectingPosition > this.selectedPosition;
                if (animationType == AnimationType.WORM) {
                    return this.animation.worm().with(xCoordinate, xCoordinate2, this.radiusPx, z);
                }
                if (animationType == AnimationType.THIN_WORM) {
                    return this.animation.thinWorm().with(xCoordinate, xCoordinate2, this.radiusPx, z);
                }
                return null;
            case 5:
                return this.animation.fill().with(this.unselectedColor, this.selectedColor, this.radiusPx, this.strokePx);
            default:
                return null;
        }
    }

    private int getViewPagerCount() {
        ViewPager viewPager = this.viewPager;
        return (viewPager == null || viewPager.getAdapter() == null) ? this.count : this.viewPager.getAdapter().getCount();
    }

    private int getXCoordinate(int i2) {
        int width = (getWidth() - calculateActualViewWidth()) / 2;
        if (width < 0) {
            width = 0;
        }
        for (int i3 = 0; i3 < this.count; i3++) {
            int i4 = this.radiusPx;
            int i5 = width + this.strokePx + i4;
            if (i2 == i3) {
                return i5;
            }
            width = i5 + i4 + this.paddingPx;
        }
        return width;
    }

    private void init(@Nullable AttributeSet attributeSet) {
        initAttributes(attributeSet);
        initAnimation();
        this.fillPaint.setStyle(Paint.Style.FILL);
        this.fillPaint.setAntiAlias(true);
        this.strokePaint.setStyle(Paint.Style.STROKE);
        this.strokePaint.setAntiAlias(true);
        this.strokePaint.setStrokeWidth(this.strokePx);
    }

    private void initAnimation() {
        this.animation = new ValueAnimation(new ValueAnimation.UpdateListener() { // from class: com.rd.PageIndicatorView.1
            @Override // com.rd.animation.ValueAnimation.UpdateListener
            public void onColorAnimationUpdated(int i2, int i3) {
                PageIndicatorView.this.frameColor = i2;
                PageIndicatorView.this.frameColorReverse = i3;
                PageIndicatorView.this.invalidate();
            }

            @Override // com.rd.animation.ValueAnimation.UpdateListener
            public void onFillAnimationUpdated(int i2, int i3, int i4, int i5, int i6, int i7) {
                PageIndicatorView.this.frameColor = i2;
                PageIndicatorView.this.frameColorReverse = i3;
                PageIndicatorView.this.frameRadiusPx = i4;
                PageIndicatorView.this.frameRadiusReversePx = i5;
                PageIndicatorView.this.frameStrokePx = i6;
                PageIndicatorView.this.frameStrokeReversePx = i7;
                PageIndicatorView.this.invalidate();
            }

            @Override // com.rd.animation.ValueAnimation.UpdateListener
            public void onScaleAnimationUpdated(int i2, int i3, int i4, int i5) {
                PageIndicatorView.this.frameColor = i2;
                PageIndicatorView.this.frameColorReverse = i3;
                PageIndicatorView.this.frameRadiusPx = i4;
                PageIndicatorView.this.frameRadiusReversePx = i5;
                PageIndicatorView.this.invalidate();
            }

            @Override // com.rd.animation.ValueAnimation.UpdateListener
            public void onSlideAnimationUpdated(int i2) {
                PageIndicatorView.this.frameXCoordinate = i2;
                PageIndicatorView.this.invalidate();
            }

            @Override // com.rd.animation.ValueAnimation.UpdateListener
            public void onThinWormAnimationUpdated(int i2, int i3, int i4) {
                PageIndicatorView.this.frameLeftX = i2;
                PageIndicatorView.this.frameRightX = i3;
                PageIndicatorView.this.frameHeight = i4;
                PageIndicatorView.this.invalidate();
            }

            @Override // com.rd.animation.ValueAnimation.UpdateListener
            public void onWormAnimationUpdated(int i2, int i3) {
                PageIndicatorView.this.frameLeftX = i2;
                PageIndicatorView.this.frameRightX = i3;
                PageIndicatorView.this.invalidate();
            }
        });
    }

    private void initAnimationAttribute(@NonNull TypedArray typedArray) {
        this.animationDuration = typedArray.getInt(R.styleable.PageIndicatorView_piv_animationDuration, AbsAnimation.DEFAULT_ANIMATION_TIME);
        this.interactiveAnimation = typedArray.getBoolean(R.styleable.PageIndicatorView_piv_interactiveAnimation, false);
        this.animationType = getAnimationType(typedArray.getInt(R.styleable.PageIndicatorView_piv_animationType, AnimationType.NONE.ordinal()));
    }

    private void initAttributes(@Nullable AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.PageIndicatorView, 0, 0);
        initCountAttribute(obtainStyledAttributes);
        initColorAttribute(obtainStyledAttributes);
        initAnimationAttribute(obtainStyledAttributes);
        initSizeAttribute(obtainStyledAttributes);
    }

    private void initColorAttribute(@NonNull TypedArray typedArray) {
        this.unselectedColor = typedArray.getColor(R.styleable.PageIndicatorView_piv_unselectedColor, Color.parseColor(ColorAnimation.DEFAULT_UNSELECTED_COLOR));
        this.selectedColor = typedArray.getColor(R.styleable.PageIndicatorView_piv_selectedColor, Color.parseColor(ColorAnimation.DEFAULT_SELECTED_COLOR));
    }

    private void initCountAttribute(@NonNull TypedArray typedArray) {
        setDynamicCount(typedArray.getBoolean(R.styleable.PageIndicatorView_dynamicCount, false));
        int i2 = typedArray.getInt(R.styleable.PageIndicatorView_piv_count, -1);
        this.count = i2;
        if (i2 != -1) {
            this.isCountSet = true;
        } else {
            this.count = 3;
        }
        int i3 = typedArray.getInt(R.styleable.PageIndicatorView_piv_select, 0);
        if (i3 < 0) {
            i3 = 0;
        } else {
            int i4 = this.count;
            if (i4 > 0 && i3 > i4 - 1) {
                i3 = i4 - 1;
            }
        }
        this.selectedPosition = i3;
        this.selectingPosition = i3;
        this.viewPagerId = typedArray.getResourceId(R.styleable.PageIndicatorView_piv_viewPager, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0038, code lost:
        if (r0 > 1.0f) goto L3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void initSizeAttribute(@NonNull TypedArray typedArray) {
        this.radiusPx = (int) typedArray.getDimension(R.styleable.PageIndicatorView_piv_radius, DensityUtils.dpToPx(6));
        this.paddingPx = (int) typedArray.getDimension(R.styleable.PageIndicatorView_piv_padding, DensityUtils.dpToPx(8));
        float f2 = typedArray.getFloat(R.styleable.PageIndicatorView_piv_scaleFactor, 0.7f);
        this.scaleFactor = f2;
        float f3 = f2 >= 0.3f ? 1.0f : 0.3f;
        this.scaleFactor = f3;
        int dimension = (int) typedArray.getDimension(R.styleable.PageIndicatorView_piv_strokeWidth, DensityUtils.dpToPx(1));
        this.strokePx = dimension;
        int i2 = this.radiusPx;
        if (dimension > i2) {
            this.strokePx = i2;
        }
        if (this.animationType != AnimationType.FILL) {
            this.strokePx = 0;
        }
    }

    private void onPageScroll(int i2, float f2) {
        Pair<Integer, Float> progress = getProgress(i2, f2);
        int intValue = ((Integer) progress.first).intValue();
        float floatValue = ((Float) progress.second).floatValue();
        if (floatValue == 1.0f) {
            this.lastSelectedPosition = this.selectedPosition;
            this.selectedPosition = intValue;
        }
        setProgress(intValue, floatValue);
    }

    private void registerSetObserver() {
        ViewPager viewPager;
        if (this.setObserver != null || (viewPager = this.viewPager) == null || viewPager.getAdapter() == null) {
            return;
        }
        this.setObserver = new DataSetObserver() { // from class: com.rd.PageIndicatorView.2
            @Override // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                if (PageIndicatorView.this.viewPager == null || PageIndicatorView.this.viewPager.getAdapter() == null) {
                    return;
                }
                PageIndicatorView.this.setCount(PageIndicatorView.this.viewPager.getAdapter().getCount());
            }
        };
        this.viewPager.getAdapter().registerDataSetObserver(this.setObserver);
    }

    private void setFrameValues() {
        int i2;
        if (this.isFrameValuesSet) {
            return;
        }
        this.frameColor = this.selectedColor;
        this.frameColorReverse = this.unselectedColor;
        int i3 = this.radiusPx;
        this.frameRadiusPx = i3;
        this.frameRadiusReversePx = i3;
        int xCoordinate = getXCoordinate(this.selectedPosition);
        int i4 = this.radiusPx;
        if (xCoordinate - i4 >= 0) {
            this.frameLeftX = xCoordinate - i4;
            i2 = xCoordinate + i4;
        } else {
            this.frameLeftX = xCoordinate;
            i2 = (i4 * 2) + xCoordinate;
        }
        this.frameRightX = i2;
        this.frameXCoordinate = xCoordinate;
        this.frameStrokePx = i4;
        this.frameStrokeReversePx = i4 / 2;
        if (this.animationType == AnimationType.FILL) {
            this.frameRadiusPx = i4 / 2;
            this.frameRadiusReversePx = i4;
        }
        this.frameHeight = i4 * 2;
        this.isFrameValuesSet = true;
    }

    private void startColorAnimation() {
        this.animation.color().end();
        this.animation.color().with(this.unselectedColor, this.selectedColor).duration(this.animationDuration).start();
    }

    private void startFillAnimation() {
        this.animation.fill().end();
        this.animation.fill().with(this.unselectedColor, this.selectedColor, this.radiusPx, this.strokePx).duration(this.animationDuration).start();
    }

    private void startScaleAnimation() {
        this.animation.scale().end();
        this.animation.scale().with(this.unselectedColor, this.selectedColor, this.radiusPx, this.scaleFactor).duration(this.animationDuration).start();
    }

    private void startSlideAnimation() {
        int xCoordinate = getXCoordinate(this.lastSelectedPosition);
        int xCoordinate2 = getXCoordinate(this.selectedPosition);
        this.animation.slide().end();
        this.animation.slide().with(xCoordinate, xCoordinate2).duration(this.animationDuration).start();
    }

    private void startThinWormAnimation() {
        int xCoordinate = getXCoordinate(this.lastSelectedPosition);
        int xCoordinate2 = getXCoordinate(this.selectedPosition);
        boolean z = this.selectedPosition > this.lastSelectedPosition;
        this.animation.thinWorm().end();
        this.animation.thinWorm().duration(this.animationDuration).with(xCoordinate, xCoordinate2, this.radiusPx, z).start();
    }

    private void startWormAnimation() {
        int xCoordinate = getXCoordinate(this.lastSelectedPosition);
        int xCoordinate2 = getXCoordinate(this.selectedPosition);
        boolean z = this.selectedPosition > this.lastSelectedPosition;
        this.animation.worm().end();
        this.animation.worm().with(xCoordinate, xCoordinate2, this.radiusPx, z).duration(this.animationDuration).start();
    }

    private void unRegisterSetObserver() {
        ViewPager viewPager;
        if (this.setObserver == null || (viewPager = this.viewPager) == null || viewPager.getAdapter() == null) {
            return;
        }
        this.viewPager.getAdapter().unregisterDataSetObserver(this.setObserver);
        this.setObserver = null;
    }

    public long getAnimationDuration() {
        return this.animationDuration;
    }

    public int getCount() {
        return this.count;
    }

    public int getPadding() {
        return this.paddingPx;
    }

    public int getRadius() {
        return this.radiusPx;
    }

    public float getScaleFactor() {
        return this.scaleFactor;
    }

    public int getSelectedColor() {
        return this.selectedColor;
    }

    public int getSelection() {
        return this.selectedPosition;
    }

    public int getStrokeWidth() {
        return this.strokePx;
    }

    public int getUnselectedColor() {
        return this.unselectedColor;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        findViewPager();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        unRegisterSetObserver();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawIndicatorView(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        setFrameValues();
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        int i4 = this.radiusPx * 2;
        int i5 = this.strokePx;
        int i6 = i4 + i5;
        int i7 = this.count;
        int i8 = i7 != 0 ? (i4 * i7) + (i5 * 2 * i7) + (this.paddingPx * (i7 - 1)) : 0;
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? Math.min(i8, size) : i8;
        }
        if (mode2 != 1073741824) {
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(i6, size2) : i6;
        }
        if (size < 0) {
            size = 0;
        }
        setMeasuredDimension(size, size2 >= 0 ? size2 : 0);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        if (this.interactiveAnimation) {
            onPageScroll(i2, f2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        if (!this.interactiveAnimation || this.animationType == AnimationType.NONE) {
            Log.e("TEST", "onPageSelected");
            setSelection(i2);
        }
    }

    public void releaseViewPager() {
        ViewPager viewPager = this.viewPager;
        if (viewPager != null) {
            viewPager.removeOnPageChangeListener(this);
            this.viewPager = null;
        }
    }

    public void setAnimationDuration(long j2) {
        this.animationDuration = j2;
    }

    public void setAnimationType(@Nullable AnimationType animationType) {
        if (animationType == null) {
            animationType = AnimationType.NONE;
        }
        this.animationType = animationType;
    }

    public void setCount(int i2) {
        if (this.count != i2) {
            this.count = i2;
            this.isCountSet = true;
            requestLayout();
        }
    }

    public void setDynamicCount(boolean z) {
        this.dynamicCount = z;
        if (z) {
            registerSetObserver();
        } else {
            unRegisterSetObserver();
        }
    }

    public void setInteractiveAnimation(boolean z) {
        this.interactiveAnimation = z;
    }

    public void setPadding(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.paddingPx = (int) f2;
        invalidate();
    }

    public void setPadding(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.paddingPx = DensityUtils.dpToPx(i2);
        invalidate();
    }

    public void setProgress(int i2, float f2) {
        if (this.interactiveAnimation) {
            if (i2 < 0) {
                i2 = 0;
            } else {
                int i3 = this.count;
                if (i2 > i3 - 1) {
                    i2 = i3 - 1;
                }
            }
            if (f2 < 0.0f) {
                f2 = 0.0f;
            } else if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            this.selectingPosition = i2;
            AbsAnimation selectedAnimation = getSelectedAnimation();
            if (selectedAnimation != null) {
                selectedAnimation.progress(f2);
            }
        }
    }

    public void setRadius(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.radiusPx = (int) f2;
        invalidate();
    }

    public void setRadius(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.radiusPx = DensityUtils.dpToPx(i2);
        invalidate();
    }

    public void setScaleFactor(float f2) {
        if (f2 > 1.0f) {
            f2 = 1.0f;
        } else if (f2 < 0.3f) {
            f2 = 0.3f;
        }
        this.scaleFactor = f2;
    }

    public void setSelectedColor(int i2) {
        this.selectedColor = i2;
        invalidate();
    }

    public void setSelection(int i2) {
        if (i2 < 0) {
            i2 = 0;
        } else {
            int i3 = this.count;
            if (i2 > i3 - 1) {
                i2 = i3 - 1;
            }
        }
        this.lastSelectedPosition = this.selectedPosition;
        this.selectedPosition = i2;
        switch (AnonymousClass3.f10805a[this.animationType.ordinal()]) {
            case 1:
                invalidate();
                return;
            case 2:
                startColorAnimation();
                return;
            case 3:
                startScaleAnimation();
                return;
            case 4:
                startWormAnimation();
                return;
            case 5:
                startFillAnimation();
                return;
            case 6:
                startSlideAnimation();
                return;
            case 7:
                startThinWormAnimation();
                return;
            default:
                return;
        }
    }

    public void setStrokeWidth(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else {
            int i2 = this.radiusPx;
            if (f2 > i2) {
                f2 = i2;
            }
        }
        this.strokePx = (int) f2;
        invalidate();
    }

    public void setStrokeWidth(int i2) {
        int dpToPx = DensityUtils.dpToPx(i2);
        if (dpToPx < 0) {
            dpToPx = 0;
        } else {
            int i3 = this.radiusPx;
            if (dpToPx > i3) {
                dpToPx = i3;
            }
        }
        this.strokePx = dpToPx;
        invalidate();
    }

    public void setUnselectedColor(int i2) {
        this.unselectedColor = i2;
        invalidate();
    }

    public void setViewPager(@Nullable ViewPager viewPager) {
        releaseViewPager();
        if (viewPager != null) {
            this.viewPager = viewPager;
            viewPager.addOnPageChangeListener(this);
            setDynamicCount(this.dynamicCount);
            if (this.isCountSet) {
                return;
            }
            setCount(getViewPagerCount());
        }
    }
}
