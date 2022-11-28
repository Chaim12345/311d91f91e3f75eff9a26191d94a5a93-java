package com.psa.mym.mycitroenconnect.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import androidx.annotation.ColorInt;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.barcode.ModuleDescriptor;
import com.google.android.material.textview.MaterialTextView;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CarChargingView extends LinearLayout {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @Nullable
    private Integer percentageTextColor;
    @Nullable
    private Integer shadowColor;
    @Nullable
    private Integer srcImage;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarChargingView(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this._$_findViewCache = new LinkedHashMap();
        LinearLayout.inflate(getContext(), R.layout.car_charging_view, this);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attrs, com.psa.mym.mycitroenconnect.R.styleable.CarChargingView, 0, 0);
        try {
            this.shadowColor = Integer.valueOf(obtainStyledAttributes.getColor(0, Color.parseColor("#484848")));
            this.srcImage = Integer.valueOf(obtainStyledAttributes.getResourceId(4, 0));
            this.percentageTextColor = Integer.valueOf(obtainStyledAttributes.getColor(2, Color.parseColor("#3B3938")));
            obtainStyledAttributes.recycle();
            Integer num = this.srcImage;
            Intrinsics.checkNotNull(num);
            Drawable drawable = ContextCompat.getDrawable(context, num.intValue());
            Integer num2 = this.srcImage;
            Intrinsics.checkNotNull(num2);
            Drawable drawable2 = ContextCompat.getDrawable(context, num2.intValue());
            Intrinsics.checkNotNull(drawable2);
            Drawable.ConstantState constantState = drawable2.getConstantState();
            Drawable newDrawable = constantState != null ? constantState.newDrawable() : null;
            AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivLoadingImageBack);
            appCompatImageView.setAlpha(0.5f);
            appCompatImageView.setImageDrawable(new ClipDrawable(drawable, 80, 1));
            appCompatImageView.getDrawable().setLevel(ModuleDescriptor.MODULE_VERSION);
            ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivLoadingImageFront)).setImageDrawable(new ClipDrawable(newDrawable, 80, 2));
            Integer num3 = this.percentageTextColor;
            Intrinsics.checkNotNull(num3);
            ((MaterialTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvPercentage)).setTextColor(num3.intValue());
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private final void animateImage(int i2, int i3, long j2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i2, i3);
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.setDuration(j2);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.psa.mym.mycitroenconnect.views.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                CarChargingView.m167animateImage$lambda4$lambda3(CarChargingView.this, valueAnimator);
            }
        });
        ofInt.start();
        ImageViewCompat.setImageTintList((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivLoadingImageFront), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: animateImage$lambda-4$lambda-3  reason: not valid java name */
    public static final void m167animateImage$lambda4$lambda3(CarChargingView this$0, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        int intValue = ((Integer) animatedValue).intValue();
        ((AppCompatImageView) this$0._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivLoadingImageFront)).getDrawable().setLevel(intValue * 100);
        ((MaterialTextView) this$0._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvPercentage)).setText(String.valueOf(intValue));
        this$0.setTvParams(intValue);
        this$0.setChargeIndicatorColor(intValue);
        if (intValue == 100) {
            ((AppCompatImageView) this$0._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivLoadingImageBack)).setAlpha(1.0f);
        }
    }

    private final void changeIndicatorColor(@ColorInt int i2) {
        Drawable background = _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.indicatorLine).getBackground();
        Intrinsics.checkNotNullExpressionValue(background, "indicatorLine.background");
        ExtensionsKt.tint(background, i2);
        Drawable background2 = _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.indicatorDot).getBackground();
        Intrinsics.checkNotNullExpressionValue(background2, "indicatorDot.background");
        ExtensionsKt.tint(background2, i2);
    }

    private final void setChargeIndicatorColor(int i2) {
        boolean z = true;
        boolean z2 = i2 >= 0 && i2 < 37;
        int i3 = R.color.dark_red;
        if (!z2) {
            if (37 <= i2 && i2 < 51) {
                i3 = R.color.primary_color_1;
            } else {
                if (51 <= i2 && i2 < 81) {
                    i3 = R.color.dark_yellow;
                } else {
                    if (81 > i2 || i2 >= 101) {
                        z = false;
                    }
                    if (z) {
                        i3 = R.color.dark_green;
                    }
                }
            }
        }
        changeIndicatorColor(ContextCompat.getColor(getContext(), i3));
    }

    private final void setTvParams(int i2) {
        int i3 = com.psa.mym.mycitroenconnect.R.id.llPercentage;
        ViewGroup.LayoutParams layoutParams = ((LinearLayoutCompat) _$_findCachedViewById(i3)).getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = i2 * (((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivLoadingImageFront)).getHeight() / 100);
        ((LinearLayoutCompat) _$_findCachedViewById(i3)).setLayoutParams(layoutParams2);
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

    public final void setProgress(int i2) {
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivLoadingImageFront)).getDrawable().setLevel(i2 * 100);
        ((MaterialTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvPercentage)).setText(String.valueOf(i2));
        setTvParams(i2);
    }

    public final void setProgress(int i2, int i3, long j2) {
        animateImage(i2, i3, j2);
    }

    public final void setProgress(int i2, long j2) {
        setProgress(0, i2, j2);
    }
}
