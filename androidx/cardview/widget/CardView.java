package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.cardview.R;
/* loaded from: classes.dex */
public class CardView extends FrameLayout {
    private static final int[] COLOR_BACKGROUND_ATTR = {16842801};
    private static final CardViewImpl IMPL;

    /* renamed from: a  reason: collision with root package name */
    int f1572a;

    /* renamed from: b  reason: collision with root package name */
    int f1573b;

    /* renamed from: c  reason: collision with root package name */
    final Rect f1574c;

    /* renamed from: d  reason: collision with root package name */
    final Rect f1575d;
    private final CardViewDelegate mCardViewDelegate;
    private boolean mCompatPadding;
    private boolean mPreventCornerOverlap;

    static {
        int i2 = Build.VERSION.SDK_INT;
        IMPL = i2 >= 21 ? new CardViewApi21Impl() : i2 >= 17 ? new CardViewApi17Impl() : new CardViewBaseImpl();
        IMPL.initStatic();
    }

    public CardView(@NonNull Context context) {
        this(context, null);
    }

    public CardView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.cardViewStyle);
    }

    public CardView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Resources resources;
        int i3;
        ColorStateList valueOf;
        Rect rect = new Rect();
        this.f1574c = rect;
        this.f1575d = new Rect();
        CardViewDelegate cardViewDelegate = new CardViewDelegate() { // from class: androidx.cardview.widget.CardView.1
            private Drawable mCardBackground;

            @Override // androidx.cardview.widget.CardViewDelegate
            public Drawable getCardBackground() {
                return this.mCardBackground;
            }

            @Override // androidx.cardview.widget.CardViewDelegate
            public View getCardView() {
                return CardView.this;
            }

            @Override // androidx.cardview.widget.CardViewDelegate
            public boolean getPreventCornerOverlap() {
                return CardView.this.getPreventCornerOverlap();
            }

            @Override // androidx.cardview.widget.CardViewDelegate
            public boolean getUseCompatPadding() {
                return CardView.this.getUseCompatPadding();
            }

            @Override // androidx.cardview.widget.CardViewDelegate
            public void setCardBackground(Drawable drawable) {
                this.mCardBackground = drawable;
                CardView.this.setBackgroundDrawable(drawable);
            }

            @Override // androidx.cardview.widget.CardViewDelegate
            public void setMinWidthHeightInternal(int i4, int i5) {
                CardView cardView = CardView.this;
                if (i4 > cardView.f1572a) {
                    CardView.super.setMinimumWidth(i4);
                }
                CardView cardView2 = CardView.this;
                if (i5 > cardView2.f1573b) {
                    CardView.super.setMinimumHeight(i5);
                }
            }

            @Override // androidx.cardview.widget.CardViewDelegate
            public void setShadowPadding(int i4, int i5, int i6, int i7) {
                CardView.this.f1575d.set(i4, i5, i6, i7);
                CardView cardView = CardView.this;
                Rect rect2 = cardView.f1574c;
                CardView.super.setPadding(i4 + rect2.left, i5 + rect2.top, i6 + rect2.right, i7 + rect2.bottom);
            }
        };
        this.mCardViewDelegate = cardViewDelegate;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CardView, i2, R.style.CardView);
        int i4 = R.styleable.CardView_cardBackgroundColor;
        if (obtainStyledAttributes.hasValue(i4)) {
            valueOf = obtainStyledAttributes.getColorStateList(i4);
        } else {
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            int color = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color, fArr);
            if (fArr[2] > 0.5f) {
                resources = getResources();
                i3 = R.color.cardview_light_background;
            } else {
                resources = getResources();
                i3 = R.color.cardview_dark_background;
            }
            valueOf = ColorStateList.valueOf(resources.getColor(i3));
        }
        ColorStateList colorStateList = valueOf;
        float dimension = obtainStyledAttributes.getDimension(R.styleable.CardView_cardCornerRadius, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(R.styleable.CardView_cardElevation, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(R.styleable.CardView_cardMaxElevation, 0.0f);
        this.mCompatPadding = obtainStyledAttributes.getBoolean(R.styleable.CardView_cardUseCompatPadding, false);
        this.mPreventCornerOverlap = obtainStyledAttributes.getBoolean(R.styleable.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPadding, 0);
        rect.left = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingLeft, dimensionPixelSize);
        rect.top = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingTop, dimensionPixelSize);
        rect.right = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingRight, dimensionPixelSize);
        rect.bottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_contentPaddingBottom, dimensionPixelSize);
        float f2 = dimension2 > dimension3 ? dimension2 : dimension3;
        this.f1572a = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_android_minWidth, 0);
        this.f1573b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CardView_android_minHeight, 0);
        obtainStyledAttributes.recycle();
        IMPL.initialize(cardViewDelegate, context, colorStateList, dimension, dimension2, f2);
    }

    @NonNull
    public ColorStateList getCardBackgroundColor() {
        return IMPL.getBackgroundColor(this.mCardViewDelegate);
    }

    public float getCardElevation() {
        return IMPL.getElevation(this.mCardViewDelegate);
    }

    @Px
    public int getContentPaddingBottom() {
        return this.f1574c.bottom;
    }

    @Px
    public int getContentPaddingLeft() {
        return this.f1574c.left;
    }

    @Px
    public int getContentPaddingRight() {
        return this.f1574c.right;
    }

    @Px
    public int getContentPaddingTop() {
        return this.f1574c.top;
    }

    public float getMaxCardElevation() {
        return IMPL.getMaxElevation(this.mCardViewDelegate);
    }

    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }

    public float getRadius() {
        return IMPL.getRadius(this.mCardViewDelegate);
    }

    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        CardViewImpl cardViewImpl = IMPL;
        if (!(cardViewImpl instanceof CardViewApi21Impl)) {
            int mode = View.MeasureSpec.getMode(i2);
            if (mode == Integer.MIN_VALUE || mode == 1073741824) {
                i2 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(cardViewImpl.getMinWidth(this.mCardViewDelegate)), View.MeasureSpec.getSize(i2)), mode);
            }
            int mode2 = View.MeasureSpec.getMode(i3);
            if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                i3 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(cardViewImpl.getMinHeight(this.mCardViewDelegate)), View.MeasureSpec.getSize(i3)), mode2);
            }
        }
        super.onMeasure(i2, i3);
    }

    public void setCardBackgroundColor(@ColorInt int i2) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, ColorStateList.valueOf(i2));
    }

    public void setCardBackgroundColor(@Nullable ColorStateList colorStateList) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, colorStateList);
    }

    public void setCardElevation(float f2) {
        IMPL.setElevation(this.mCardViewDelegate, f2);
    }

    public void setContentPadding(@Px int i2, @Px int i3, @Px int i4, @Px int i5) {
        this.f1574c.set(i2, i3, i4, i5);
        IMPL.updatePadding(this.mCardViewDelegate);
    }

    public void setMaxCardElevation(float f2) {
        IMPL.setMaxElevation(this.mCardViewDelegate, f2);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i2) {
        this.f1573b = i2;
        super.setMinimumHeight(i2);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i2) {
        this.f1572a = i2;
        super.setMinimumWidth(i2);
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i2, int i3, int i4, int i5) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.mPreventCornerOverlap) {
            this.mPreventCornerOverlap = z;
            IMPL.onPreventCornerOverlapChanged(this.mCardViewDelegate);
        }
    }

    public void setRadius(float f2) {
        IMPL.setRadius(this.mCardViewDelegate, f2);
    }

    public void setUseCompatPadding(boolean z) {
        if (this.mCompatPadding != z) {
            this.mCompatPadding = z;
            IMPL.onCompatPaddingChanged(this.mCardViewDelegate);
        }
    }
}