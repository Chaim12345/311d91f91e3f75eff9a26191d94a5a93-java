package com.chaos.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import java.util.Objects;
/* loaded from: classes.dex */
public class PinView extends AppCompatEditText {
    private static final int BLINK = 500;
    private static final boolean DBG = false;
    private static final int DEFAULT_COUNT = 4;
    private static final String TAG = "PinView";
    private static final int VIEW_TYPE_LINE = 1;
    private static final int VIEW_TYPE_NONE = 2;
    private static final int VIEW_TYPE_RECTANGLE = 0;
    private boolean drawCursor;
    private boolean isAnimationEnable;
    private boolean isCursorVisible;
    private boolean isPasswordHidden;
    private final TextPaint mAnimatorTextPaint;
    private Blink mBlink;
    private int mCurLineColor;
    private int mCursorColor;
    private float mCursorHeight;
    private int mCursorWidth;
    private ValueAnimator mDefaultAddAnimator;
    private boolean mHideLineWhenFilled;
    private Drawable mItemBackground;
    private int mItemBackgroundResource;
    private final RectF mItemBorderRect;
    private final PointF mItemCenterPoint;
    private final RectF mItemLineRect;
    private ColorStateList mLineColor;
    private int mLineWidth;
    private final Paint mPaint;
    private final Path mPath;
    private int mPinItemCount;
    private int mPinItemHeight;
    private int mPinItemRadius;
    private int mPinItemSpacing;
    private int mPinItemWidth;
    private final Rect mTextRect;
    private String mTransformed;
    private int mViewType;
    private static final InputFilter[] NO_FILTERS = new InputFilter[0];
    private static final int[] HIGHLIGHT_STATES = {16842913};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class Blink implements Runnable {
        private boolean mCancelled;

        private Blink() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancel() {
            if (this.mCancelled) {
                return;
            }
            PinView.this.removeCallbacks(this);
            this.mCancelled = true;
        }

        void b() {
            this.mCancelled = false;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mCancelled) {
                return;
            }
            PinView.this.removeCallbacks(this);
            if (PinView.this.shouldBlink()) {
                PinView pinView = PinView.this;
                pinView.invalidateCursor(!pinView.drawCursor);
                PinView.this.postDelayed(this, 500L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DefaultActionModeCallback implements ActionMode.Callback {
        private DefaultActionModeCallback() {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }
    }

    public PinView(Context context) {
        this(context, null);
    }

    public PinView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.pinViewStyle);
    }

    public PinView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TextPaint textPaint = new TextPaint();
        this.mAnimatorTextPaint = textPaint;
        this.mCurLineColor = ViewCompat.MEASURED_STATE_MASK;
        this.mTextRect = new Rect();
        this.mItemBorderRect = new RectF();
        this.mItemLineRect = new RectF();
        this.mPath = new Path();
        this.mItemCenterPoint = new PointF();
        this.isAnimationEnable = false;
        Resources resources = getResources();
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        textPaint.set(getPaint());
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PinView, i2, 0);
        this.mViewType = obtainStyledAttributes.getInt(R.styleable.PinView_viewType, 0);
        this.mPinItemCount = obtainStyledAttributes.getInt(R.styleable.PinView_itemCount, 4);
        int i3 = R.styleable.PinView_itemHeight;
        int i4 = R.dimen.pv_pin_view_item_size;
        this.mPinItemHeight = (int) obtainStyledAttributes.getDimension(i3, resources.getDimensionPixelSize(i4));
        this.mPinItemWidth = (int) obtainStyledAttributes.getDimension(R.styleable.PinView_itemWidth, resources.getDimensionPixelSize(i4));
        this.mPinItemSpacing = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PinView_itemSpacing, resources.getDimensionPixelSize(R.dimen.pv_pin_view_item_spacing));
        this.mPinItemRadius = (int) obtainStyledAttributes.getDimension(R.styleable.PinView_itemRadius, 0.0f);
        this.mLineWidth = (int) obtainStyledAttributes.getDimension(R.styleable.PinView_lineWidth, resources.getDimensionPixelSize(R.dimen.pv_pin_view_item_line_width));
        this.mLineColor = obtainStyledAttributes.getColorStateList(R.styleable.PinView_lineColor);
        this.isCursorVisible = obtainStyledAttributes.getBoolean(R.styleable.PinView_android_cursorVisible, true);
        this.mCursorColor = obtainStyledAttributes.getColor(R.styleable.PinView_cursorColor, getCurrentTextColor());
        this.mCursorWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PinView_cursorWidth, resources.getDimensionPixelSize(R.dimen.pv_pin_view_cursor_width));
        this.mItemBackground = obtainStyledAttributes.getDrawable(R.styleable.PinView_android_itemBackground);
        this.mHideLineWhenFilled = obtainStyledAttributes.getBoolean(R.styleable.PinView_hideLineWhenFilled, false);
        obtainStyledAttributes.recycle();
        ColorStateList colorStateList = this.mLineColor;
        if (colorStateList != null) {
            this.mCurLineColor = colorStateList.getDefaultColor();
        }
        updateCursorHeight();
        checkItemRadius();
        setMaxLength(this.mPinItemCount);
        paint.setStrokeWidth(this.mLineWidth);
        setupAnimator();
        setTransformationMethod(null);
        disableSelectionMenu();
        this.isPasswordHidden = isPasswordInputType(getInputType());
    }

    private void checkItemRadius() {
        int i2 = this.mViewType;
        if (i2 == 1) {
            if (this.mPinItemRadius > this.mLineWidth / 2.0f) {
                throw new IllegalArgumentException("The itemRadius can not be greater than lineWidth when viewType is line");
            }
        } else if (i2 == 0) {
            if (this.mPinItemRadius > this.mPinItemWidth / 2.0f) {
                throw new IllegalArgumentException("The itemRadius can not be greater than itemWidth");
            }
        }
    }

    private void disableSelectionMenu() {
        setCustomSelectionActionModeCallback(new DefaultActionModeCallback());
        if (Build.VERSION.SDK_INT >= 26) {
            setCustomInsertionActionModeCallback(new DefaultActionModeCallback(this) { // from class: com.chaos.view.PinView.2
                @Override // com.chaos.view.PinView.DefaultActionModeCallback, android.view.ActionMode.Callback
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    menu.removeItem(16908355);
                    return true;
                }
            });
        }
    }

    private int dpToPx(float f2) {
        return (int) ((f2 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void drawAnchorLine(Canvas canvas) {
        PointF pointF = this.mItemCenterPoint;
        float f2 = pointF.x;
        float f3 = pointF.y;
        this.mPaint.setStrokeWidth(1.0f);
        float strokeWidth = f2 - (this.mPaint.getStrokeWidth() / 2.0f);
        float strokeWidth2 = f3 - (this.mPaint.getStrokeWidth() / 2.0f);
        this.mPath.reset();
        this.mPath.moveTo(strokeWidth, this.mItemBorderRect.top);
        Path path = this.mPath;
        RectF rectF = this.mItemBorderRect;
        path.lineTo(strokeWidth, rectF.top + Math.abs(rectF.height()));
        canvas.drawPath(this.mPath, this.mPaint);
        this.mPath.reset();
        this.mPath.moveTo(this.mItemBorderRect.left, strokeWidth2);
        Path path2 = this.mPath;
        RectF rectF2 = this.mItemBorderRect;
        path2.lineTo(rectF2.left + Math.abs(rectF2.width()), strokeWidth2);
        canvas.drawPath(this.mPath, this.mPaint);
        this.mPath.reset();
        this.mPaint.setStrokeWidth(this.mLineWidth);
    }

    private void drawCircle(Canvas canvas, int i2) {
        Paint paintByIndex = getPaintByIndex(i2);
        PointF pointF = this.mItemCenterPoint;
        canvas.drawCircle(pointF.x, pointF.y, paintByIndex.getTextSize() / 2.0f, paintByIndex);
    }

    private void drawCursor(Canvas canvas) {
        if (this.drawCursor) {
            PointF pointF = this.mItemCenterPoint;
            float f2 = pointF.x;
            float f3 = pointF.y - (this.mCursorHeight / 2.0f);
            int color = this.mPaint.getColor();
            float strokeWidth = this.mPaint.getStrokeWidth();
            this.mPaint.setColor(this.mCursorColor);
            this.mPaint.setStrokeWidth(this.mCursorWidth);
            canvas.drawLine(f2, f3, f2, f3 + this.mCursorHeight, this.mPaint);
            this.mPaint.setColor(color);
            this.mPaint.setStrokeWidth(strokeWidth);
        }
    }

    private void drawHint(Canvas canvas, int i2) {
        Paint paintByIndex = getPaintByIndex(i2);
        paintByIndex.setColor(getCurrentHintTextColor());
        drawTextAtBox(canvas, paintByIndex, getHint(), i2);
    }

    private void drawItemBackground(Canvas canvas, boolean z) {
        if (this.mItemBackground == null) {
            return;
        }
        float f2 = this.mLineWidth / 2.0f;
        this.mItemBackground.setBounds(Math.round(this.mItemBorderRect.left - f2), Math.round(this.mItemBorderRect.top - f2), Math.round(this.mItemBorderRect.right + f2), Math.round(this.mItemBorderRect.bottom + f2));
        this.mItemBackground.setState(z ? HIGHLIGHT_STATES : getDrawableState());
        this.mItemBackground.draw(canvas);
    }

    private void drawPinBox(Canvas canvas, int i2) {
        if (!this.mHideLineWhenFilled || i2 >= getText().length()) {
            canvas.drawPath(this.mPath, this.mPaint);
        }
    }

    private void drawPinLine(Canvas canvas, int i2) {
        boolean z;
        boolean z2;
        int i3;
        if (!this.mHideLineWhenFilled || i2 >= getText().length()) {
            if (this.mPinItemSpacing == 0 && (i3 = this.mPinItemCount) > 1) {
                if (i2 == 0) {
                    z2 = false;
                    z = true;
                } else {
                    z = false;
                    if (i2 == i3 - 1) {
                        z2 = true;
                    }
                }
                this.mPaint.setStyle(Paint.Style.FILL);
                this.mPaint.setStrokeWidth(this.mLineWidth / 10.0f);
                float f2 = this.mLineWidth / 2.0f;
                RectF rectF = this.mItemLineRect;
                RectF rectF2 = this.mItemBorderRect;
                float f3 = rectF2.bottom;
                rectF.set(rectF2.left - f2, f3 - f2, rectF2.right + f2, f3 + f2);
                RectF rectF3 = this.mItemLineRect;
                int i4 = this.mPinItemRadius;
                updateRoundRectPath(rectF3, i4, i4, z, z2);
                canvas.drawPath(this.mPath, this.mPaint);
            }
            z = true;
            z2 = z;
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setStrokeWidth(this.mLineWidth / 10.0f);
            float f22 = this.mLineWidth / 2.0f;
            RectF rectF4 = this.mItemLineRect;
            RectF rectF22 = this.mItemBorderRect;
            float f32 = rectF22.bottom;
            rectF4.set(rectF22.left - f22, f32 - f22, rectF22.right + f22, f32 + f22);
            RectF rectF32 = this.mItemLineRect;
            int i42 = this.mPinItemRadius;
            updateRoundRectPath(rectF32, i42, i42, z, z2);
            canvas.drawPath(this.mPath, this.mPaint);
        }
    }

    private void drawPinView(Canvas canvas) {
        int length = getText().length();
        int i2 = 0;
        while (i2 < this.mPinItemCount) {
            boolean z = isFocused() && length == i2;
            this.mPaint.setColor(z ? getLineColorForState(HIGHLIGHT_STATES) : this.mCurLineColor);
            updateItemRectF(i2);
            updateCenterPoint();
            canvas.save();
            if (this.mViewType == 0) {
                updatePinBoxPath(i2);
                canvas.clipPath(this.mPath);
            }
            drawItemBackground(canvas, z);
            canvas.restore();
            if (z) {
                drawCursor(canvas);
            }
            int i3 = this.mViewType;
            if (i3 == 0) {
                drawPinBox(canvas, i2);
            } else if (i3 == 1) {
                drawPinLine(canvas, i2);
            }
            if (this.mTransformed.length() > i2) {
                if (getTransformationMethod() == null && this.isPasswordHidden) {
                    drawCircle(canvas, i2);
                } else {
                    drawText(canvas, i2);
                }
            } else if (!TextUtils.isEmpty(getHint()) && getHint().length() == this.mPinItemCount) {
                drawHint(canvas, i2);
            }
            i2++;
        }
        if (isFocused() && getText().length() != this.mPinItemCount && this.mViewType == 0) {
            int length2 = getText().length();
            updateItemRectF(length2);
            updateCenterPoint();
            updatePinBoxPath(length2);
            this.mPaint.setColor(getLineColorForState(HIGHLIGHT_STATES));
            drawPinBox(canvas, length2);
        }
    }

    private void drawText(Canvas canvas, int i2) {
        drawTextAtBox(canvas, getPaintByIndex(i2), this.mTransformed, i2);
    }

    private void drawTextAtBox(Canvas canvas, Paint paint, CharSequence charSequence, int i2) {
        int i3 = i2 + 1;
        paint.getTextBounds(charSequence.toString(), i2, i3, this.mTextRect);
        PointF pointF = this.mItemCenterPoint;
        float f2 = pointF.x;
        float f3 = pointF.y;
        float abs = f2 - (Math.abs(this.mTextRect.width()) / 2.0f);
        Rect rect = this.mTextRect;
        canvas.drawText(charSequence, i2, i3, abs - rect.left, (f3 + (Math.abs(rect.height()) / 2.0f)) - this.mTextRect.bottom, paint);
    }

    private int getLineColorForState(int... iArr) {
        ColorStateList colorStateList = this.mLineColor;
        return colorStateList != null ? colorStateList.getColorForState(iArr, this.mCurLineColor) : this.mCurLineColor;
    }

    private Paint getPaintByIndex(int i2) {
        if (this.isAnimationEnable && i2 == getText().length() - 1) {
            this.mAnimatorTextPaint.setColor(getPaint().getColor());
            return this.mAnimatorTextPaint;
        }
        return getPaint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateCursor(boolean z) {
        if (this.drawCursor != z) {
            this.drawCursor = z;
            invalidate();
        }
    }

    private static boolean isPasswordInputType(int i2) {
        int i3 = i2 & 4095;
        return i3 == 129 || i3 == 225 || i3 == 18;
    }

    private void makeBlink() {
        if (!shouldBlink()) {
            Blink blink = this.mBlink;
            if (blink != null) {
                removeCallbacks(blink);
                return;
            }
            return;
        }
        if (this.mBlink == null) {
            this.mBlink = new Blink();
        }
        removeCallbacks(this.mBlink);
        this.drawCursor = false;
        postDelayed(this.mBlink, 500L);
    }

    private void moveSelectionToEnd() {
        setSelection(getText().length());
    }

    private void resumeBlink() {
        Blink blink = this.mBlink;
        if (blink != null) {
            blink.b();
            makeBlink();
        }
    }

    private void setMaxLength(int i2) {
        if (i2 >= 0) {
            setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
        } else {
            setFilters(NO_FILTERS);
        }
    }

    private void setupAnimator() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.5f, 1.0f);
        this.mDefaultAddAnimator = ofFloat;
        ofFloat.setDuration(150L);
        this.mDefaultAddAnimator.setInterpolator(new DecelerateInterpolator());
        this.mDefaultAddAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.chaos.view.PinView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                PinView.this.mAnimatorTextPaint.setTextSize(PinView.this.getTextSize() * floatValue);
                PinView.this.mAnimatorTextPaint.setAlpha((int) (255.0f * floatValue));
                PinView.this.postInvalidate();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldBlink() {
        return isCursorVisible() && isFocused();
    }

    private void suspendBlink() {
        Blink blink = this.mBlink;
        if (blink != null) {
            blink.cancel();
            invalidateCursor(false);
        }
    }

    private void updateCenterPoint() {
        RectF rectF = this.mItemBorderRect;
        float abs = rectF.left + (Math.abs(rectF.width()) / 2.0f);
        RectF rectF2 = this.mItemBorderRect;
        this.mItemCenterPoint.set(abs, rectF2.top + (Math.abs(rectF2.height()) / 2.0f));
    }

    private void updateColors() {
        ColorStateList colorStateList = this.mLineColor;
        boolean z = false;
        int colorForState = colorStateList != null ? colorStateList.getColorForState(getDrawableState(), 0) : getCurrentTextColor();
        if (colorForState != this.mCurLineColor) {
            this.mCurLineColor = colorForState;
            z = true;
        }
        if (z) {
            invalidate();
        }
    }

    private void updateCursorHeight() {
        float dpToPx = dpToPx(2.0f) * 2;
        this.mCursorHeight = ((float) this.mPinItemHeight) - getTextSize() > dpToPx ? getTextSize() + dpToPx : getTextSize();
    }

    private void updateItemRectF(int i2) {
        float f2 = this.mLineWidth / 2.0f;
        int scrollX = getScrollX() + ViewCompat.getPaddingStart(this);
        int i3 = this.mPinItemSpacing;
        int i4 = this.mPinItemWidth;
        float f3 = scrollX + ((i3 + i4) * i2) + f2;
        if (i3 == 0 && i2 > 0) {
            f3 -= this.mLineWidth * i2;
        }
        float f4 = (i4 + f3) - this.mLineWidth;
        float scrollY = getScrollY() + getPaddingTop() + f2;
        this.mItemBorderRect.set(f3, scrollY, f4, (this.mPinItemHeight + scrollY) - this.mLineWidth);
    }

    private void updatePaints() {
        this.mPaint.setColor(this.mCurLineColor);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mLineWidth);
        getPaint().setColor(getCurrentTextColor());
    }

    private void updatePinBoxPath(int i2) {
        boolean z;
        boolean z2;
        if (this.mPinItemSpacing != 0) {
            z = true;
            z2 = true;
        } else {
            boolean z3 = i2 == 0 && i2 != this.mPinItemCount - 1;
            if (i2 != this.mPinItemCount - 1 || i2 == 0) {
                z = z3;
                z2 = false;
            } else {
                z = z3;
                z2 = true;
            }
        }
        RectF rectF = this.mItemBorderRect;
        int i3 = this.mPinItemRadius;
        updateRoundRectPath(rectF, i3, i3, z, z2);
    }

    private void updateRoundRectPath(RectF rectF, float f2, float f3, boolean z, boolean z2) {
        updateRoundRectPath(rectF, f2, f3, z, z2, z2, z);
    }

    private void updateRoundRectPath(RectF rectF, float f2, float f3, boolean z, boolean z2, boolean z3, boolean z4) {
        this.mPath.reset();
        float f4 = rectF.left;
        float f5 = rectF.top;
        float f6 = (rectF.right - f4) - (f2 * 2.0f);
        float f7 = (rectF.bottom - f5) - (2.0f * f3);
        this.mPath.moveTo(f4, f5 + f3);
        if (z) {
            float f8 = -f3;
            this.mPath.rQuadTo(0.0f, f8, f2, f8);
        } else {
            this.mPath.rLineTo(0.0f, -f3);
            this.mPath.rLineTo(f2, 0.0f);
        }
        this.mPath.rLineTo(f6, 0.0f);
        Path path = this.mPath;
        if (z2) {
            path.rQuadTo(f2, 0.0f, f2, f3);
        } else {
            path.rLineTo(f2, 0.0f);
            this.mPath.rLineTo(0.0f, f3);
        }
        this.mPath.rLineTo(0.0f, f7);
        Path path2 = this.mPath;
        if (z3) {
            path2.rQuadTo(0.0f, f3, -f2, f3);
        } else {
            path2.rLineTo(0.0f, f3);
            this.mPath.rLineTo(-f2, 0.0f);
        }
        this.mPath.rLineTo(-f6, 0.0f);
        Path path3 = this.mPath;
        float f9 = -f2;
        if (z4) {
            path3.rQuadTo(f9, 0.0f, f9, -f3);
        } else {
            path3.rLineTo(f9, 0.0f);
            this.mPath.rLineTo(0.0f, -f3);
        }
        this.mPath.rLineTo(0.0f, -f7);
        this.mPath.close();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        ColorStateList colorStateList = this.mLineColor;
        if (colorStateList == null || colorStateList.isStateful()) {
            updateColors();
        }
    }

    @ColorInt
    public int getCurrentLineColor() {
        return this.mCurLineColor;
    }

    public int getCursorColor() {
        return this.mCursorColor;
    }

    public int getCursorWidth() {
        return this.mCursorWidth;
    }

    @Override // android.widget.EditText, android.widget.TextView
    protected MovementMethod getDefaultMovementMethod() {
        return DefaultMovementMethod.getInstance();
    }

    public int getItemCount() {
        return this.mPinItemCount;
    }

    public int getItemHeight() {
        return this.mPinItemHeight;
    }

    public int getItemRadius() {
        return this.mPinItemRadius;
    }

    @Px
    public int getItemSpacing() {
        return this.mPinItemSpacing;
    }

    public int getItemWidth() {
        return this.mPinItemWidth;
    }

    public ColorStateList getLineColors() {
        return this.mLineColor;
    }

    public int getLineWidth() {
        return this.mLineWidth;
    }

    @Override // android.widget.TextView
    public boolean isCursorVisible() {
        return this.isCursorVisible;
    }

    public boolean isPasswordHidden() {
        return this.isPasswordHidden;
    }

    @Override // android.widget.TextView
    public boolean isSuggestionsEnabled() {
        return false;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        resumeBlink();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        suspendBlink();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.save();
        updatePaints();
        drawPinView(canvas);
        canvas.restore();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        super.onFocusChanged(z, i2, rect);
        if (z) {
            moveSelectionToEnd();
            makeBlink();
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        int i4 = this.mPinItemHeight;
        if (mode != 1073741824) {
            int i5 = this.mPinItemCount;
            size = ViewCompat.getPaddingStart(this) + ((i5 - 1) * this.mPinItemSpacing) + (i5 * this.mPinItemWidth) + ViewCompat.getPaddingEnd(this);
            if (this.mPinItemSpacing == 0) {
                size -= (this.mPinItemCount - 1) * this.mLineWidth;
            }
        }
        if (mode2 != 1073741824) {
            size2 = getPaddingBottom() + i4 + getPaddingTop();
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.widget.TextView, android.view.View
    public void onScreenStateChanged(int i2) {
        super.onScreenStateChanged(i2);
        if (i2 == 0) {
            suspendBlink();
        } else if (i2 != 1) {
        } else {
            resumeBlink();
        }
    }

    @Override // android.widget.TextView
    protected void onSelectionChanged(int i2, int i3) {
        super.onSelectionChanged(i2, i3);
        if (i3 != getText().length()) {
            moveSelectionToEnd();
        }
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        ValueAnimator valueAnimator;
        if (i2 != charSequence.length()) {
            moveSelectionToEnd();
        }
        makeBlink();
        if (this.isAnimationEnable) {
            if ((i4 - i3 > 0) && (valueAnimator = this.mDefaultAddAnimator) != null) {
                valueAnimator.end();
                this.mDefaultAddAnimator.start();
            }
        }
        TransformationMethod transformationMethod = getTransformationMethod();
        this.mTransformed = transformationMethod == null ? getText().toString() : transformationMethod.getTransformation(getText(), this).toString();
    }

    public void setAnimationEnable(boolean z) {
        this.isAnimationEnable = z;
    }

    public void setCursorColor(@ColorInt int i2) {
        this.mCursorColor = i2;
        if (isCursorVisible()) {
            invalidateCursor(true);
        }
    }

    @Override // android.widget.TextView
    public void setCursorVisible(boolean z) {
        if (this.isCursorVisible != z) {
            this.isCursorVisible = z;
            invalidateCursor(z);
            makeBlink();
        }
    }

    public void setCursorWidth(@Px int i2) {
        this.mCursorWidth = i2;
        if (isCursorVisible()) {
            invalidateCursor(true);
        }
    }

    public void setHideLineWhenFilled(boolean z) {
        this.mHideLineWhenFilled = z;
    }

    @Override // android.widget.TextView
    public void setInputType(int i2) {
        super.setInputType(i2);
        this.isPasswordHidden = isPasswordInputType(getInputType());
    }

    public void setItemBackground(Drawable drawable) {
        this.mItemBackgroundResource = 0;
        this.mItemBackground = drawable;
        invalidate();
    }

    public void setItemBackgroundColor(@ColorInt int i2) {
        Drawable drawable = this.mItemBackground;
        if (!(drawable instanceof ColorDrawable)) {
            setItemBackground(new ColorDrawable(i2));
            return;
        }
        ((ColorDrawable) drawable.mutate()).setColor(i2);
        this.mItemBackgroundResource = 0;
    }

    public void setItemBackgroundResources(@DrawableRes int i2) {
        if (i2 == 0 || this.mItemBackgroundResource == i2) {
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), i2, getContext().getTheme());
            this.mItemBackground = drawable;
            setItemBackground(drawable);
            this.mItemBackgroundResource = i2;
        }
    }

    public void setItemCount(int i2) {
        this.mPinItemCount = i2;
        setMaxLength(i2);
        requestLayout();
    }

    public void setItemHeight(@Px int i2) {
        this.mPinItemHeight = i2;
        updateCursorHeight();
        requestLayout();
    }

    public void setItemRadius(@Px int i2) {
        this.mPinItemRadius = i2;
        checkItemRadius();
        requestLayout();
    }

    public void setItemSpacing(@Px int i2) {
        this.mPinItemSpacing = i2;
        requestLayout();
    }

    public void setItemWidth(@Px int i2) {
        this.mPinItemWidth = i2;
        checkItemRadius();
        requestLayout();
    }

    public void setLineColor(@ColorInt int i2) {
        this.mLineColor = ColorStateList.valueOf(i2);
        updateColors();
    }

    public void setLineColor(ColorStateList colorStateList) {
        Objects.requireNonNull(colorStateList);
        this.mLineColor = colorStateList;
        updateColors();
    }

    public void setLineWidth(@Px int i2) {
        this.mLineWidth = i2;
        checkItemRadius();
        requestLayout();
    }

    public void setPasswordHidden(boolean z) {
        this.isPasswordHidden = z;
        requestLayout();
    }

    @Override // android.widget.TextView
    public void setTextSize(float f2) {
        super.setTextSize(f2);
        updateCursorHeight();
    }

    @Override // android.widget.TextView
    public void setTextSize(int i2, float f2) {
        super.setTextSize(i2, f2);
        updateCursorHeight();
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface) {
        super.setTypeface(typeface);
        TextPaint textPaint = this.mAnimatorTextPaint;
        if (textPaint != null) {
            textPaint.set(getPaint());
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i2) {
        super.setTypeface(typeface, i2);
    }
}
