package com.psa.mym.mycitroenconnect.views.skeleton_layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMask;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonMaskFactory;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonShimmerDirection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public class SkeletonLayout extends FrameLayout implements Skeleton, SkeletonStyle {
    public static final int DEFAULT_MASK_COLOR = 2131100299;
    public static final float DEFAULT_MASK_CORNER_RADIUS = 8.0f;
    public static final int DEFAULT_SHIMMER_ANGLE = 0;
    public static final int DEFAULT_SHIMMER_COLOR = 2131100300;
    public static final long DEFAULT_SHIMMER_DURATION_IN_MILLIS = 2000;
    public static final boolean DEFAULT_SHIMMER_SHOW = true;
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    private final SkeletonConfig config;
    private boolean isRendered;
    private boolean isSkeleton;
    @Nullable
    private SkeletonMask mask;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final SkeletonShimmerDirection DEFAULT_SHIMMER_DIRECTION = SkeletonShimmerDirection.LEFT_TO_RIGHT;

    /* renamed from: com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayout$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function0<Unit> {
        AnonymousClass2(Object obj) {
            super(0, obj, SkeletonLayout.class, "invalidateMask", "invalidateMask()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((SkeletonLayout) this.f11145a).invalidateMask();
        }
    }

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SkeletonShimmerDirection getDEFAULT_SHIMMER_DIRECTION() {
            return SkeletonLayout.DEFAULT_SHIMMER_DIRECTION;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SkeletonLayout(@NotNull Context context) {
        this(context, null, 0, null, null, 30, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SkeletonLayout(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, null, null, 28, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SkeletonLayout(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, null, null, 24, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SkeletonLayout(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2, @Nullable View view) {
        this(context, attributeSet, i2, view, null, 16, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SkeletonLayout(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2, @Nullable View view, @NotNull SkeletonConfig config) {
        super(context, attributeSet, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this._$_findViewCache = new LinkedHashMap();
        this.config = config;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SkeletonLayout, 0, 0);
            Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.obtainStyledAttr…ble.SkeletonLayout, 0, 0)");
            setMaskColor(obtainStyledAttributes.getColor(0, getMaskColor()));
            setMaskCornerRadius(obtainStyledAttributes.getDimensionPixelSize(1, (int) getMaskCornerRadius()));
            setShowShimmer(obtainStyledAttributes.getBoolean(6, getShowShimmer()));
            setShimmerColor(obtainStyledAttributes.getColor(3, getShimmerColor()));
            setShimmerDurationInMillis(obtainStyledAttributes.getInt(5, (int) getShimmerDurationInMillis()));
            SkeletonShimmerDirection valueOf = SkeletonShimmerDirection.Companion.valueOf(obtainStyledAttributes.getInt(4, getShimmerDirection().ordinal()));
            setShimmerDirection(valueOf == null ? DEFAULT_SHIMMER_DIRECTION : valueOf);
            setShimmerAngle(obtainStyledAttributes.getInt(2, getShimmerAngle()));
            obtainStyledAttributes.recycle();
        }
        config.addValueObserver(new AnonymousClass2(this));
        if (view != null) {
            addView(view);
        }
    }

    public /* synthetic */ SkeletonLayout(Context context, AttributeSet attributeSet, int i2, View view, SkeletonConfig skeletonConfig, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? null : view, (i3 & 16) != 0 ? SkeletonConfig.Companion.m174default(context) : skeletonConfig);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SkeletonLayout(@NotNull View originView, @NotNull SkeletonConfig config) {
        this(r2, null, 0, originView, config);
        Intrinsics.checkNotNullParameter(originView, "originView");
        Intrinsics.checkNotNullParameter(config, "config");
        Context context = originView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "originView.context");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invalidateMask() {
        Logger logger;
        String str;
        if (this.isRendered) {
            SkeletonMask skeletonMask = this.mask;
            if (skeletonMask != null) {
                skeletonMask.stop();
            }
            this.mask = null;
            if (!this.isSkeleton) {
                return;
            }
            if (getWidth() > 0 && getHeight() > 0) {
                SkeletonMask createMask = SkeletonMaskFactory.INSTANCE.createMask(this, this.config);
                createMask.mask(this, getMaskCornerRadius());
                this.mask = createMask;
                return;
            }
            logger = Logger.INSTANCE;
            str = "Failed to mask view with invalid width and height";
        } else {
            logger = Logger.INSTANCE;
            str = "Skipping invalidation until view is rendered";
        }
        logger.e(str);
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

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    @ColorInt
    public int getMaskColor() {
        return this.config.getMaskColor();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public float getMaskCornerRadius() {
        return this.config.getMaskCornerRadius();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public int getShimmerAngle() {
        return this.config.getShimmerAngle();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    @ColorInt
    public int getShimmerColor() {
        return this.config.getShimmerColor();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    @NotNull
    public SkeletonShimmerDirection getShimmerDirection() {
        return this.config.getShimmerDirection();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public long getShimmerDurationInMillis() {
        return this.config.getShimmerDurationInMillis();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public boolean getShowShimmer() {
        return this.config.getShowShimmer();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonAction
    public boolean isSkeleton() {
        return this.isSkeleton;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isRendered) {
            invalidateMask();
            SkeletonMask skeletonMask = this.mask;
            if (skeletonMask != null) {
                skeletonMask.start();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SkeletonMask skeletonMask = this.mask;
        if (skeletonMask != null) {
            skeletonMask.stop();
        }
    }

    @Override // android.view.View
    protected void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        SkeletonMask skeletonMask = this.mask;
        if (skeletonMask != null) {
            skeletonMask.draw(canvas);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.isRendered = true;
        if (this.isSkeleton) {
            showSkeleton();
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NotNull View changedView, int i2) {
        Intrinsics.checkNotNullParameter(changedView, "changedView");
        super.onVisibilityChanged(changedView, i2);
        SkeletonMask skeletonMask = this.mask;
        if (skeletonMask != null) {
            skeletonMask.invalidate();
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        SkeletonMask skeletonMask;
        super.onWindowFocusChanged(z);
        if (z) {
            SkeletonMask skeletonMask2 = this.mask;
            if (skeletonMask2 != null) {
                skeletonMask2.start();
            }
        } else if (z || (skeletonMask = this.mask) == null) {
        } else {
            skeletonMask.stop();
        }
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setMaskColor(int i2) {
        this.config.setMaskColor(i2);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setMaskCornerRadius(float f2) {
        this.config.setMaskCornerRadius(f2);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerAngle(int i2) {
        this.config.setShimmerAngle(i2);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerColor(int i2) {
        this.config.setShimmerColor(i2);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerDirection(@NotNull SkeletonShimmerDirection skeletonShimmerDirection) {
        Intrinsics.checkNotNullParameter(skeletonShimmerDirection, "<set-?>");
        this.config.setShimmerDirection(skeletonShimmerDirection);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerDurationInMillis(long j2) {
        this.config.setShimmerDurationInMillis(j2);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShowShimmer(boolean z) {
        this.config.setShowShimmer(z);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonAction
    public void showOriginal() {
        this.isSkeleton = false;
        if (getChildCount() > 0) {
            for (View view : BaseExtensionsKt.views(this)) {
                view.setVisibility(0);
            }
            SkeletonMask skeletonMask = this.mask;
            if (skeletonMask != null) {
                skeletonMask.stop();
            }
            this.mask = null;
        }
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonAction
    public void showSkeleton() {
        this.isSkeleton = true;
        if (this.isRendered) {
            if (getChildCount() <= 0) {
                Logger.INSTANCE.i("No views to mask");
                return;
            }
            for (View view : BaseExtensionsKt.views(this)) {
                view.setVisibility(4);
            }
            setWillNotDraw(false);
            invalidateMask();
            SkeletonMask skeletonMask = this.mask;
            if (skeletonMask != null) {
                skeletonMask.invalidate();
            }
        }
    }
}
