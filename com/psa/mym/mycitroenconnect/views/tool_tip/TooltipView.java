package com.psa.mym.mycitroenconnect.views.tool_tip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import androidx.core.view.ViewCompat;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class TooltipView extends FrameLayout {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    private float arrowHeight;
    private float arrowWidth;
    @NotNull
    private Paint borderPaint;
    @NotNull
    private final Paint bubblePaint;
    @Nullable
    private Path bubblePath;
    public ChildView childView;
    private int corner;
    private int distanceWithView;
    private boolean hasInverted;
    private int lMargin;
    private int minHeight;
    private int minWidth;
    private int paddingB;
    private int paddingE;
    private int paddingS;
    private int paddingT;
    private View parent;
    private Rect parentRect;
    @NotNull
    private Position position;
    private Rect rect;
    private float shadowPadding;

    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Position.values().length];
            iArr[Position.TOP.ordinal()] = 1;
            iArr[Position.BOTTOM.ordinal()] = 2;
            iArr[Position.START.ordinal()] = 3;
            iArr[Position.END.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TooltipView(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TooltipView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TooltipView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.bubblePaint = new Paint(1);
        this.borderPaint = new Paint(1);
        this.position = Position.BOTTOM;
        init(context, attributeSet, i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TooltipView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.bubblePaint = new Paint(1);
        this.borderPaint = new Paint(1);
        this.position = Position.BOTTOM;
        init(context, attributeSet, i2);
    }

    public /* synthetic */ TooltipView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    private final int calculateHeight(int i2) {
        View view = this.parent;
        Rect rect = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            view = null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        View view2 = this.parent;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            view2 = null;
        }
        int height = (view2.getHeight() - (marginLayoutParams != null ? marginLayoutParams.topMargin : 0)) - (marginLayoutParams != null ? marginLayoutParams.bottomMargin : 0);
        View view3 = this.parent;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            view3 = null;
        }
        int paddingTop = height - view3.getPaddingTop();
        View view4 = this.parent;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            view4 = null;
        }
        int paddingBottom = paddingTop - view4.getPaddingBottom();
        if (this.position == Position.TOP) {
            Rect rect2 = this.rect;
            if (rect2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rect");
                rect2 = null;
            }
            if (i2 > (rect2.top - this.lMargin) - this.distanceWithView) {
                Rect rect3 = this.rect;
                if (rect3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rect");
                } else {
                    rect = rect3;
                }
                return (rect.top - this.lMargin) - this.distanceWithView;
            }
        }
        if (this.position == Position.BOTTOM) {
            Rect rect4 = this.rect;
            if (rect4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rect");
                rect4 = null;
            }
            int i3 = rect4.bottom;
            Rect rect5 = this.parentRect;
            if (rect5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("parentRect");
                rect5 = null;
            }
            int i4 = i3 + rect5.top + i2;
            Rect rect6 = this.rect;
            if (rect6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rect");
                rect6 = null;
            }
            int i5 = paddingBottom - rect6.bottom;
            Rect rect7 = this.parentRect;
            if (rect7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("parentRect");
                rect7 = null;
            }
            if (i4 > ((i5 + rect7.top) - this.lMargin) - this.distanceWithView) {
                Rect rect8 = this.rect;
                if (rect8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rect");
                    rect8 = null;
                }
                int i6 = paddingBottom - rect8.bottom;
                Rect rect9 = this.parentRect;
                if (rect9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("parentRect");
                } else {
                    rect = rect9;
                }
                return ((i6 + rect.top) - this.lMargin) - this.distanceWithView;
            }
        }
        Position position = this.position;
        if (position == Position.START || position == Position.END) {
            int i7 = this.lMargin;
            return i2 > paddingBottom - (i7 * 2) ? paddingBottom - (i7 * 2) : i2;
        }
        return i2;
    }

    private final int calculatePosition(int i2, int i3, int i4, int i5) {
        int i6 = i2 + i4;
        return (i6 >= 0 || i4 + i3 >= i5) ? (i6 < 0 || i6 + i3 > i5) ? i5 - i3 : i6 : i4;
    }

    private final int calculateWidth(int i2) {
        View view = this.parent;
        Rect rect = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            view = null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        View view2 = this.parent;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            view2 = null;
        }
        int width = (view2.getWidth() - (marginLayoutParams != null ? marginLayoutParams.leftMargin : 0)) - (marginLayoutParams != null ? marginLayoutParams.rightMargin : 0);
        View view3 = this.parent;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            view3 = null;
        }
        int paddingLeft = width - view3.getPaddingLeft();
        View view4 = this.parent;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            view4 = null;
        }
        int paddingRight = paddingLeft - view4.getPaddingRight();
        if (this.position == Position.START) {
            Rect rect2 = this.rect;
            if (rect2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rect");
                rect2 = null;
            }
            if (i2 > (rect2.left - this.lMargin) - this.distanceWithView) {
                Rect rect3 = this.rect;
                if (rect3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rect");
                } else {
                    rect = rect3;
                }
                return (rect.left - this.lMargin) - this.distanceWithView;
            }
        }
        if (this.position == Position.END) {
            Rect rect4 = this.rect;
            if (rect4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rect");
                rect4 = null;
            }
            int i3 = rect4.right;
            Rect rect5 = this.parentRect;
            if (rect5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("parentRect");
                rect5 = null;
            }
            int i4 = i3 + rect5.left + i2;
            Rect rect6 = this.rect;
            if (rect6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rect");
                rect6 = null;
            }
            int i5 = paddingRight - rect6.right;
            Rect rect7 = this.parentRect;
            if (rect7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("parentRect");
                rect7 = null;
            }
            if (i4 > ((i5 + rect7.left) - this.lMargin) - this.distanceWithView) {
                Rect rect8 = this.rect;
                if (rect8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rect");
                    rect8 = null;
                }
                int i6 = paddingRight - rect8.right;
                Rect rect9 = this.parentRect;
                if (rect9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("parentRect");
                } else {
                    rect = rect9;
                }
                return ((i6 + rect.left) - this.lMargin) - this.distanceWithView;
            }
        }
        Position position = this.position;
        if (position == Position.TOP || position == Position.BOTTOM) {
            int i7 = this.lMargin;
            return i2 > paddingRight - (i7 * 2) ? paddingRight - (i7 * 2) : i2;
        }
        return i2;
    }

    private final Path drawBubble(float f2, float f3) {
        Position position;
        float f4;
        Path path = new Path();
        int i2 = this.corner;
        float f5 = i2 < 0 ? 0.0f : i2;
        float strokeWidth = this.distanceWithView + this.borderPaint.getStrokeWidth();
        float f6 = this.arrowHeight + strokeWidth;
        Position relativePosition = getRelativePosition();
        Position position2 = Position.END;
        float f7 = relativePosition == position2 ? f6 : strokeWidth;
        Position position3 = Position.TOP;
        float f8 = relativePosition == position3 ? f6 : strokeWidth;
        Position position4 = Position.START;
        float f9 = relativePosition == position4 ? f6 : strokeWidth;
        Position position5 = Position.BOTTOM;
        if (relativePosition == position5) {
            strokeWidth = f6;
        }
        float f10 = f5 + strokeWidth;
        path.moveTo(f7, f10);
        if (relativePosition == position2) {
            position = position5;
            float f11 = 2;
            path.lineTo(f7, (f3 - this.arrowWidth) / f11);
            path.lineTo(0.0f, f3 / f11);
            path.lineTo(f7, (this.arrowWidth + f3) / f11);
        } else {
            position = position5;
        }
        float f12 = (f3 - f5) - f8;
        path.lineTo(f7, f12);
        float f13 = f3 - f8;
        float f14 = f7 + f5;
        path.quadTo(f7, f13, f14, f13);
        if (relativePosition == position3) {
            f4 = f7;
            float f15 = 2;
            path.lineTo((f2 - this.arrowWidth) / f15, f13);
            path.lineTo(f2 / f15, f3);
            path.lineTo((this.arrowWidth + f2) / f15, f13);
        } else {
            f4 = f7;
        }
        float f16 = (f2 - f5) - f9;
        path.lineTo(f16, f13);
        float f17 = f2 - f9;
        path.quadTo(f17, f13, f17, f12);
        if (relativePosition == position4) {
            float f18 = 2;
            path.lineTo(f17, (this.arrowWidth + f3) / f18);
            path.lineTo(f2, f3 / f18);
            path.lineTo(f17, (f3 - this.arrowWidth) / f18);
        }
        path.lineTo(f17, f10);
        path.quadTo(f17, strokeWidth, f16, strokeWidth);
        if (relativePosition == position) {
            float f19 = 2;
            path.lineTo((this.arrowWidth + f2) / f19, strokeWidth);
            path.lineTo(f2 / f19, 0.0f);
            path.lineTo((f2 - this.arrowWidth) / f19, strokeWidth);
        }
        path.lineTo(f14, strokeWidth);
        float f20 = f4;
        path.quadTo(f20, strokeWidth, f20, f10);
        path.close();
        return path;
    }

    private final int getOffset(int i2, int i3) {
        return (i3 - i2) / 2;
    }

    private final Position getRelativePosition() {
        boolean z = ViewCompat.getLayoutDirection(this) == 1;
        Position position = this.position;
        if (z) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[position.ordinal()];
            return i2 != 3 ? i2 != 4 ? this.position : Position.START : Position.END;
        }
        return position;
    }

    private final void invertCurrentPosition() {
        Position position;
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.position.ordinal()];
        if (i2 == 1) {
            position = Position.BOTTOM;
        } else if (i2 == 2) {
            position = Position.TOP;
        } else if (i2 == 3) {
            position = Position.END;
        } else if (i2 != 4) {
            throw new NoWhenBranchMatchedException();
        } else {
            position = Position.START;
        }
        this.position = position;
    }

    private final void setPadding() {
        int strokeWidth = (int) (this.borderPaint.getStrokeWidth() + this.arrowHeight + this.distanceWithView);
        int i2 = WhenMappings.$EnumSwitchMapping$0[getRelativePosition().ordinal()];
        if (i2 == 1) {
            setPadding(this.paddingS, this.paddingT, this.paddingE, this.paddingB + strokeWidth);
        } else if (i2 == 2) {
            setPadding(this.paddingS, this.paddingT + strokeWidth, this.paddingE, this.paddingB);
        } else if (i2 == 3) {
            setPadding(this.paddingS, this.paddingT, this.paddingE + strokeWidth, this.paddingB);
        } else if (i2 != 4) {
        } else {
            setPadding(this.paddingS + strokeWidth, this.paddingT, this.paddingE, this.paddingB);
        }
    }

    public static /* synthetic */ void setShadow$default(TooltipView tooltipView, float f2, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = -5592406;
        }
        tooltipView.setShadow(f2, i2);
    }

    private final void setupPosition(Rect rect, int i2, int i3) {
        int calculatePosition;
        int i4;
        int strokeWidth = (int) (this.distanceWithView + this.borderPaint.getStrokeWidth());
        Position position = this.position;
        Position position2 = Position.START;
        Rect rect2 = null;
        if (position == position2 || position == Position.END) {
            int i5 = position == position2 ? (rect.left - i2) - strokeWidth : rect.right + strokeWidth;
            int offset = getOffset(i3, rect.height());
            int i6 = rect.top;
            int i7 = this.lMargin;
            if (i6 < i7) {
                i6 = i7;
            }
            View view = this.parent;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("parent");
                view = null;
            }
            calculatePosition = calculatePosition(offset, i3, i6, calculateHeight(view.getHeight()) + this.lMargin);
            i4 = i5;
        } else {
            calculatePosition = position == Position.BOTTOM ? rect.bottom + strokeWidth : (rect.top - i3) - strokeWidth;
            int offset2 = getOffset(i2, rect.width());
            int i8 = rect.left;
            int i9 = this.lMargin;
            if (i8 < i9) {
                i8 = i9;
            }
            View view2 = this.parent;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("parent");
                view2 = null;
            }
            i4 = calculatePosition(offset2, i2, i8, calculateWidth(view2.getWidth()) + this.lMargin);
        }
        boolean z = ViewCompat.getLayoutDirection(this) == 1;
        Rect rect3 = this.parentRect;
        if (rect3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parentRect");
            rect3 = null;
        }
        setTranslationX((i4 - rect3.left) * (z ? -1 : 1));
        Rect rect4 = this.parentRect;
        if (rect4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parentRect");
        } else {
            rect2 = rect4;
        }
        setTranslationY(calculatePosition - rect2.top);
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

    public final float getArrowHeight$app_preprodQa() {
        return this.arrowHeight;
    }

    public final float getArrowWidth$app_preprodQa() {
        return this.arrowWidth;
    }

    @NotNull
    public final Paint getBorderPaint$app_preprodQa() {
        return this.borderPaint;
    }

    @NotNull
    public final ChildView getChildView$app_preprodQa() {
        ChildView childView = this.childView;
        if (childView != null) {
            return childView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("childView");
        return null;
    }

    public final int getCorner$app_preprodQa() {
        return this.corner;
    }

    public final int getDistanceWithView$app_preprodQa() {
        return this.distanceWithView;
    }

    public final int getLMargin$app_preprodQa() {
        return this.lMargin;
    }

    public final int getMinHeight$app_preprodQa() {
        return this.minHeight;
    }

    public final int getMinWidth$app_preprodQa() {
        return this.minWidth;
    }

    public final int getPaddingB$app_preprodQa() {
        return this.paddingB;
    }

    public final int getPaddingE$app_preprodQa() {
        return this.paddingE;
    }

    public final int getPaddingS$app_preprodQa() {
        return this.paddingS;
    }

    public final int getPaddingT$app_preprodQa() {
        return this.paddingT;
    }

    @NotNull
    public final Position getPosition$app_preprodQa() {
        return this.position;
    }

    public final float getShadowPadding$app_preprodQa() {
        return this.shadowPadding;
    }

    public final void init(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        setWillNotDraw(false);
        setChildView$app_preprodQa(new ChildView(context, attributeSet, i2));
        getChildView$app_preprodQa().getTextView().setTextColor(-1);
        addView(getChildView$app_preprodQa(), -2, -2);
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.padding);
        this.paddingS = dimensionPixelSize;
        this.paddingT = dimensionPixelSize;
        this.paddingE = dimensionPixelSize;
        this.paddingB = dimensionPixelSize;
        this.corner = resources.getDimensionPixelSize(R.dimen.corner);
        this.arrowHeight = resources.getDimensionPixelSize(R.dimen.arrowH);
        this.arrowWidth = resources.getDimensionPixelSize(R.dimen.arrowW);
        this.shadowPadding = resources.getDimensionPixelSize(R.dimen.shadowPadding);
        this.lMargin = resources.getDimensionPixelSize(R.dimen.screenBorderMargin);
        this.minWidth = resources.getDimensionPixelSize(R.dimen.minWidth);
        this.minHeight = resources.getDimensionPixelSize(R.dimen.minHeight);
        this.bubblePaint.setStyle(Paint.Style.FILL);
        this.borderPaint.setStyle(Paint.Style.STROKE);
        setShadow$default(this, resources.getDimensionPixelSize(R.dimen.shadowW), 0, 2, null);
    }

    @Override // android.view.View
    protected void onDraw(@Nullable Canvas canvas) {
        super.onDraw(canvas);
        Path path = this.bubblePath;
        if (path != null) {
            if (canvas != null) {
                canvas.drawPath(path, this.bubblePaint);
            }
            if (canvas != null) {
                canvas.drawPath(path, this.borderPaint);
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    @SuppressLint({"WrongCall"})
    protected void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int calculateWidth = calculateWidth(size);
        int calculateHeight = calculateHeight(size2);
        float strokeWidth = this.distanceWithView + this.lMargin + this.borderPaint.getStrokeWidth();
        if (this.hasInverted || (calculateWidth >= this.minWidth + strokeWidth && calculateHeight >= this.minHeight + strokeWidth)) {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(calculateWidth, Integer.MIN_VALUE);
            i3 = View.MeasureSpec.makeMeasureSpec(calculateHeight, Integer.MIN_VALUE);
            i2 = makeMeasureSpec;
        } else {
            invertCurrentPosition();
            this.hasInverted = true;
        }
        setPadding();
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        Rect rect = this.rect;
        if (rect == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rect");
            rect = null;
        }
        setupPosition(rect, i2, i3);
        this.bubblePath = drawBubble(i2, i3);
    }

    public final void setArrowHeight$app_preprodQa(float f2) {
        this.arrowHeight = f2;
    }

    public final void setArrowWidth$app_preprodQa(float f2) {
        this.arrowWidth = f2;
    }

    public final void setBorderPaint$app_preprodQa(@NotNull Paint paint) {
        Intrinsics.checkNotNullParameter(paint, "<set-?>");
        this.borderPaint = paint;
    }

    public final void setChildView$app_preprodQa(@NotNull ChildView childView) {
        Intrinsics.checkNotNullParameter(childView, "<set-?>");
        this.childView = childView;
    }

    public final void setColor(int i2) {
        this.bubblePaint.setColor(i2);
        postInvalidate();
    }

    public final void setCorner$app_preprodQa(int i2) {
        this.corner = i2;
    }

    public final void setDistanceWithView$app_preprodQa(int i2) {
        this.distanceWithView = i2;
    }

    public final void setLMargin$app_preprodQa(int i2) {
        this.lMargin = i2;
    }

    public final void setMinHeight$app_preprodQa(int i2) {
        this.minHeight = i2;
    }

    public final void setMinWidth$app_preprodQa(int i2) {
        this.minWidth = i2;
    }

    public final void setPaddingB$app_preprodQa(int i2) {
        this.paddingB = i2;
    }

    public final void setPaddingE$app_preprodQa(int i2) {
        this.paddingE = i2;
    }

    public final void setPaddingS$app_preprodQa(int i2) {
        this.paddingS = i2;
    }

    public final void setPaddingT$app_preprodQa(int i2) {
        this.paddingT = i2;
    }

    public final void setPosition$app_preprodQa(@NotNull Position position) {
        Intrinsics.checkNotNullParameter(position, "<set-?>");
        this.position = position;
    }

    @JvmOverloads
    public final void setShadow(float f2) {
        setShadow$default(this, f2, 0, 2, null);
    }

    @JvmOverloads
    public final void setShadow(float f2, @ColorInt int i2) {
        Paint paint = this.bubblePaint;
        if (f2 == 0.0f) {
            i2 = 0;
        }
        paint.setShadowLayer(f2, 0.0f, 0.0f, i2);
    }

    public final void setShadowPadding$app_preprodQa(float f2) {
        this.shadowPadding = f2;
    }

    public final void setup(@NotNull Rect viewRect, @NotNull View tooltipParent) {
        Intrinsics.checkNotNullParameter(viewRect, "viewRect");
        Intrinsics.checkNotNullParameter(tooltipParent, "tooltipParent");
        this.parent = tooltipParent;
        this.parentRect = new Rect();
        View view = this.parent;
        Rect rect = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            view = null;
        }
        Rect rect2 = this.parentRect;
        if (rect2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parentRect");
        } else {
            rect = rect2;
        }
        view.getGlobalVisibleRect(rect);
        this.rect = viewRect;
    }
}
