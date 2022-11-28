package com.psa.mym.mycitroenconnect.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LoadingDots extends LinearLayout {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT_DOTS_COUNT = 3;
    public static final int DEFAULT_JUMP_DURATION = 400;
    public static final int DEFAULT_LOOP_DURATION = 600;
    public static final int DEFAULT_LOOP_START_DELAY = 100;
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    private boolean autoPlay;
    private int jumpDuration;
    @Nullable
    private ValueAnimator mAnimation;
    private int mDotSize;
    private int mDotSpace;
    @Nullable
    private List<View> mDots;
    private int mDotsColor;
    private int mDotsCount;
    @Nullable
    private int[] mDotsJumpDownEndTime;
    @Nullable
    private int[] mDotsJumpUpEndTime;
    @Nullable
    private int[] mDotsStartTime;
    private boolean mIsAttachedToWindow;
    private int mJumpHalfTime;
    private int mJumpHeight;
    private int mLoopDuration;
    private int mLoopStartDelay;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingDots(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        init(null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingDots(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        init(attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingDots(@NotNull Context context, @NotNull AttributeSet attrs, int i2) {
        super(context, attrs, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        init(attrs);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingDots(@NotNull Context context, @NotNull AttributeSet attrs, int i2, int i3) {
        super(context, attrs, i2, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        init(attrs);
    }

    private final void calculateCachedValues() {
        verifyNotRunning();
        int i2 = this.mLoopDuration;
        int i3 = this.jumpDuration;
        int i4 = i2 - (this.mLoopStartDelay + i3);
        int i5 = this.mDotsCount;
        int i6 = i4 / (i5 - 1);
        this.mJumpHalfTime = i3 / 2;
        this.mDotsStartTime = new int[i5];
        this.mDotsJumpUpEndTime = new int[i5];
        this.mDotsJumpDownEndTime = new int[i5];
        for (int i7 = 0; i7 < i5; i7++) {
            int i8 = this.mLoopStartDelay + (i6 * i7);
            int[] iArr = this.mDotsStartTime;
            Intrinsics.checkNotNull(iArr);
            iArr[i7] = i8;
            int[] iArr2 = this.mDotsJumpUpEndTime;
            Intrinsics.checkNotNull(iArr2);
            iArr2[i7] = this.mJumpHalfTime + i8;
            int[] iArr3 = this.mDotsJumpDownEndTime;
            Intrinsics.checkNotNull(iArr3);
            iArr3[i7] = i8 + this.jumpDuration;
        }
    }

    private final void createAnimation() {
        if (this.mAnimation != null) {
            return;
        }
        calculateCachedValues();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        initializeDots(context);
        ValueAnimator ofInt = ValueAnimator.ofInt(0, this.mLoopDuration);
        this.mAnimation = ofInt;
        Intrinsics.checkNotNull(ofInt);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.psa.mym.mycitroenconnect.views.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                LoadingDots.m168createAnimation$lambda0(LoadingDots.this, valueAnimator);
            }
        });
        ValueAnimator valueAnimator = this.mAnimation;
        Intrinsics.checkNotNull(valueAnimator);
        valueAnimator.setDuration(this.mLoopDuration);
        ValueAnimator valueAnimator2 = this.mAnimation;
        Intrinsics.checkNotNull(valueAnimator2);
        valueAnimator2.setRepeatCount(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createAnimation$lambda-0  reason: not valid java name */
    public static final void m168createAnimation$lambda0(LoadingDots this$0, ValueAnimator valueAnimator) {
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<View> list = this$0.mDots;
        Intrinsics.checkNotNull(list);
        int size = list.size();
        Object animatedValue = valueAnimator.getAnimatedValue();
        Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        int intValue = ((Integer) animatedValue).intValue();
        if (intValue < this$0.mLoopStartDelay) {
            return;
        }
        for (int i4 = 0; i4 < size; i4++) {
            List<View> list2 = this$0.mDots;
            Intrinsics.checkNotNull(list2);
            View view = list2.get(i4);
            int[] iArr = this$0.mDotsStartTime;
            Intrinsics.checkNotNull(iArr);
            float f2 = 0.0f;
            if (intValue >= iArr[i4]) {
                int[] iArr2 = this$0.mDotsJumpUpEndTime;
                Intrinsics.checkNotNull(iArr2);
                if (intValue < iArr2[i4]) {
                    f2 = (intValue - i2) / this$0.mJumpHalfTime;
                } else {
                    int[] iArr3 = this$0.mDotsJumpDownEndTime;
                    Intrinsics.checkNotNull(iArr3);
                    if (intValue < iArr3[i4]) {
                        f2 = 1 - (((intValue - i2) - i3) / this$0.mJumpHalfTime);
                    }
                }
            }
            view.setTranslationY(((-this$0.mJumpHeight) - 0) * f2);
        }
    }

    private final void createAnimationIfAutoPlay() {
        if (this.autoPlay) {
            createAnimation();
        }
    }

    private final View createDotView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.loading_dots_dot);
        Drawable drawable = imageView.getDrawable();
        Objects.requireNonNull(drawable, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
        ((GradientDrawable) drawable).setColor(this.mDotsColor);
        return imageView;
    }

    private final void init(AttributeSet attributeSet) {
        Context context = getContext();
        Resources resources = context.getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.psa.mym.mycitroenconnect.R.styleable.LoadingDots);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.obtainStyledAttr… R.styleable.LoadingDots)");
        this.autoPlay = obtainStyledAttributes.getBoolean(0, true);
        this.mDotsColor = obtainStyledAttributes.getColor(1, -7829368);
        this.mDotsCount = obtainStyledAttributes.getInt(2, 3);
        this.mDotSize = obtainStyledAttributes.getDimensionPixelSize(3, resources.getDimensionPixelSize(R.dimen.LoadingDots_dots_size_default));
        this.mDotSpace = obtainStyledAttributes.getDimensionPixelSize(4, resources.getDimensionPixelSize(R.dimen.LoadingDots_dots_space_default));
        this.mLoopDuration = obtainStyledAttributes.getInt(7, 600);
        this.mLoopStartDelay = obtainStyledAttributes.getInt(8, 100);
        this.jumpDuration = obtainStyledAttributes.getInt(5, 400);
        this.mJumpHeight = obtainStyledAttributes.getDimensionPixelSize(6, resources.getDimensionPixelSize(R.dimen.LoadingDots_jump_height_default));
        obtainStyledAttributes.recycle();
        setOrientation(0);
        setGravity(80);
        calculateCachedValues();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        initializeDots(context);
    }

    private final void initializeDots(Context context) {
        verifyNotRunning();
        removeAllViews();
        this.mDots = new ArrayList(this.mDotsCount);
        int i2 = this.mDotSize;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(this.mDotSpace, this.mDotSize);
        int i3 = this.mDotsCount;
        for (int i4 = 0; i4 < i3; i4++) {
            View createDotView = createDotView(context);
            addView(createDotView, layoutParams);
            List<View> list = this.mDots;
            Intrinsics.checkNotNull(list);
            list.add(createDotView);
            if (i4 < this.mDotsCount - 1) {
                addView(new View(context), layoutParams2);
            }
        }
    }

    private final void startAnimationIfAttached() {
        if (this.mIsAttachedToWindow) {
            ValueAnimator valueAnimator = this.mAnimation;
            Intrinsics.checkNotNull(valueAnimator);
            if (valueAnimator.isRunning()) {
                return;
            }
            ValueAnimator valueAnimator2 = this.mAnimation;
            Intrinsics.checkNotNull(valueAnimator2);
            valueAnimator2.start();
        }
    }

    private final void verifyNotRunning() {
        if (this.mAnimation != null) {
            throw new IllegalStateException("Can't change properties while animation is running!");
        }
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final boolean getAutoPlay() {
        return this.autoPlay;
    }

    public final int getDotsColor() {
        return this.mDotsColor;
    }

    public final int getDotsCount() {
        return this.mDotsCount;
    }

    public final int getDotsSize() {
        return this.mDotSize;
    }

    public final int getDotsSpace() {
        return this.mDotSpace;
    }

    public final int getJumpDuration() {
        return this.jumpDuration;
    }

    public final int getJumpHeight() {
        return this.mJumpHeight;
    }

    public final int getLoopDuration() {
        return this.mLoopDuration;
    }

    public final int getLoopStartDelay() {
        return this.mLoopStartDelay;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttachedToWindow = true;
        createAnimationIfAutoPlay();
        if (this.mAnimation == null || getVisibility() != 0) {
            return;
        }
        ValueAnimator valueAnimator = this.mAnimation;
        Intrinsics.checkNotNull(valueAnimator);
        valueAnimator.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsAttachedToWindow = false;
        ValueAnimator valueAnimator = this.mAnimation;
        if (valueAnimator != null) {
            Intrinsics.checkNotNull(valueAnimator);
            valueAnimator.end();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + this.mJumpHeight);
    }

    public final void setAutoPlay(boolean z) {
        this.autoPlay = z;
    }

    public final void setDotsColor(int i2) {
        verifyNotRunning();
        this.mDotsColor = i2;
    }

    public final void setDotsColorRes(int i2) {
        setDotsColor(ContextCompat.getColor(getContext(), i2));
    }

    public final void setDotsCount(int i2) {
        verifyNotRunning();
        this.mDotsCount = i2;
    }

    public final void setDotsSize(int i2) {
        verifyNotRunning();
        this.mDotSize = i2;
    }

    public final void setDotsSizeRes(int i2) {
        setDotsSize(getContext().getResources().getDimensionPixelSize(i2));
    }

    public final void setDotsSpace(int i2) {
        verifyNotRunning();
        this.mDotSpace = i2;
    }

    public final void setDotsSpaceRes(int i2) {
        setDotsSpace(getContext().getResources().getDimensionPixelSize(i2));
    }

    public final void setJumpDuration(int i2) {
        verifyNotRunning();
        this.jumpDuration = i2;
    }

    public final void setJumpHeight(int i2) {
        verifyNotRunning();
        this.mJumpHeight = i2;
    }

    public final void setJumpHeightRes(int i2) {
        setJumpHeight(getContext().getResources().getDimensionPixelSize(i2));
    }

    public final void setLoopDuration(int i2) {
        verifyNotRunning();
        this.mLoopDuration = i2;
    }

    public final void setLoopStartDelay(int i2) {
        verifyNotRunning();
        this.mLoopStartDelay = i2;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        ValueAnimator valueAnimator;
        super.setVisibility(i2);
        if (i2 == 0) {
            createAnimationIfAutoPlay();
            startAnimationIfAttached();
        } else if ((i2 == 4 || i2 == 8) && (valueAnimator = this.mAnimation) != null) {
            Intrinsics.checkNotNull(valueAnimator);
            valueAnimator.end();
        }
    }

    public final void startAnimation() {
        ValueAnimator valueAnimator = this.mAnimation;
        if (valueAnimator != null) {
            Intrinsics.checkNotNull(valueAnimator);
            if (valueAnimator.isRunning()) {
                return;
            }
        }
        createAnimation();
        startAnimationIfAttached();
    }

    public final void stopAnimation() {
        ValueAnimator valueAnimator = this.mAnimation;
        if (valueAnimator != null) {
            Intrinsics.checkNotNull(valueAnimator);
            valueAnimator.end();
            this.mAnimation = null;
        }
    }
}
