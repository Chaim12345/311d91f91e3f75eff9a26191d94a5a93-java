package com.psa.mym.mycitroenconnect.views.tool_tip;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.AnimRes;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatImageView;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TooltipBuilder {
    @NotNull
    private final Tooltip tooltip;

    public TooltipBuilder(@NotNull Tooltip tooltip) {
        Intrinsics.checkNotNullParameter(tooltip, "tooltip");
        this.tooltip = tooltip;
    }

    public static /* synthetic */ TooltipBuilder animation$default(TooltipBuilder tooltipBuilder, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i3 = i2;
        }
        return tooltipBuilder.animation(i2, i3);
    }

    public static /* synthetic */ TooltipBuilder overlay$default(TooltipBuilder tooltipBuilder, int i2, TooltipClickListener tooltipClickListener, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            tooltipClickListener = null;
        }
        return tooltipBuilder.overlay(i2, tooltipClickListener);
    }

    public static /* synthetic */ TooltipBuilder shadow$default(TooltipBuilder tooltipBuilder, float f2, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = -5592406;
        }
        return tooltipBuilder.shadow(f2, i2);
    }

    public static /* synthetic */ Tooltip show$default(TooltipBuilder tooltipBuilder, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = 0;
        }
        return tooltipBuilder.show(j2);
    }

    @JvmOverloads
    @NotNull
    public final TooltipBuilder animation(@AnimRes int i2) {
        return animation$default(this, i2, 0, 2, null);
    }

    @JvmOverloads
    @NotNull
    public final TooltipBuilder animation(@AnimRes int i2, @AnimRes int i3) {
        this.tooltip.setAnimIn$app_preprodQa(i2);
        this.tooltip.setAnimOut$app_preprodQa(i3);
        return this;
    }

    @NotNull
    public final TooltipBuilder arrowSize(int i2, int i3) {
        this.tooltip.getTooltipView$app_preprodQa().setArrowHeight$app_preprodQa(i2);
        this.tooltip.getTooltipView$app_preprodQa().setArrowWidth$app_preprodQa(i3);
        return this;
    }

    @NotNull
    public final TooltipBuilder border(int i2, float f2) {
        Paint borderPaint$app_preprodQa = this.tooltip.getTooltipView$app_preprodQa().getBorderPaint$app_preprodQa();
        borderPaint$app_preprodQa.setColor(i2);
        borderPaint$app_preprodQa.setStrokeWidth(f2);
        return this;
    }

    @NotNull
    public final TooltipBuilder borderMargin(int i2) {
        this.tooltip.getTooltipView$app_preprodQa().setLMargin$app_preprodQa(i2);
        return this;
    }

    @NotNull
    public final TooltipBuilder clickToHide(boolean z) {
        this.tooltip.setClickToHide$app_preprodQa(z);
        return this;
    }

    @NotNull
    public final TooltipBuilder color(@ColorInt int i2) {
        this.tooltip.getTooltipView$app_preprodQa().setColor(i2);
        return this;
    }

    @NotNull
    public final TooltipBuilder corner(int i2) {
        this.tooltip.getTooltipView$app_preprodQa().setCorner$app_preprodQa(i2);
        return this;
    }

    @NotNull
    public final TooltipBuilder displayListener(@NotNull DisplayListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.tooltip.setDisplayListener$app_preprodQa(listener);
        return this;
    }

    @NotNull
    public final TooltipBuilder distanceWithView(int i2) {
        this.tooltip.getTooltipView$app_preprodQa().setDistanceWithView$app_preprodQa(i2);
        return this;
    }

    @NotNull
    public final TooltipBuilder drawableBottom(@DrawableRes int i2) {
        this.tooltip.getTextView$app_preprodQa().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, i2);
        return this;
    }

    @NotNull
    public final TooltipBuilder drawableTop(@DrawableRes int i2) {
        this.tooltip.getTextView$app_preprodQa().setCompoundDrawablesWithIntrinsicBounds(0, i2, 0, 0);
        return this;
    }

    @NotNull
    public final TooltipBuilder iconEnd(@DrawableRes int i2) {
        AppCompatImageView endImageView$app_preprodQa = this.tooltip.getEndImageView$app_preprodQa();
        endImageView$app_preprodQa.setImageResource(i2);
        endImageView$app_preprodQa.setVisibility(0);
        return this;
    }

    @NotNull
    public final TooltipBuilder iconEnd(@NotNull Bitmap icon) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        AppCompatImageView endImageView$app_preprodQa = this.tooltip.getEndImageView$app_preprodQa();
        endImageView$app_preprodQa.setImageBitmap(icon);
        endImageView$app_preprodQa.setVisibility(0);
        return this;
    }

    @NotNull
    public final TooltipBuilder iconEnd(@NotNull Drawable icon) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        AppCompatImageView endImageView$app_preprodQa = this.tooltip.getEndImageView$app_preprodQa();
        endImageView$app_preprodQa.setImageDrawable(icon);
        endImageView$app_preprodQa.setVisibility(0);
        return this;
    }

    @NotNull
    public final TooltipBuilder iconEndMargin(int i2, int i3, int i4, int i5) {
        AppCompatImageView endImageView$app_preprodQa = this.tooltip.getEndImageView$app_preprodQa();
        ViewGroup.LayoutParams layoutParams = endImageView$app_preprodQa.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).setMargins(i2, i3, i4, i5);
            endImageView$app_preprodQa.setLayoutParams(layoutParams);
        }
        return this;
    }

    @NotNull
    public final TooltipBuilder iconEndSize(int i2, int i3) {
        AppCompatImageView endImageView$app_preprodQa = this.tooltip.getEndImageView$app_preprodQa();
        ViewGroup.LayoutParams layoutParams = endImageView$app_preprodQa.getLayoutParams();
        layoutParams.height = i2;
        layoutParams.width = i3;
        endImageView$app_preprodQa.setLayoutParams(layoutParams);
        return this;
    }

    @NotNull
    public final TooltipBuilder iconStart(@DrawableRes int i2) {
        AppCompatImageView startImageView$app_preprodQa = this.tooltip.getStartImageView$app_preprodQa();
        startImageView$app_preprodQa.setImageResource(i2);
        startImageView$app_preprodQa.setVisibility(0);
        return this;
    }

    @NotNull
    public final TooltipBuilder iconStart(@NotNull Bitmap icon) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        AppCompatImageView startImageView$app_preprodQa = this.tooltip.getStartImageView$app_preprodQa();
        startImageView$app_preprodQa.setImageBitmap(icon);
        startImageView$app_preprodQa.setVisibility(0);
        return this;
    }

    @NotNull
    public final TooltipBuilder iconStart(@NotNull Drawable icon) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        AppCompatImageView startImageView$app_preprodQa = this.tooltip.getStartImageView$app_preprodQa();
        startImageView$app_preprodQa.setImageDrawable(icon);
        startImageView$app_preprodQa.setVisibility(0);
        return this;
    }

    @NotNull
    public final TooltipBuilder iconStartMargin(int i2, int i3, int i4, int i5) {
        AppCompatImageView startImageView$app_preprodQa = this.tooltip.getStartImageView$app_preprodQa();
        ViewGroup.LayoutParams layoutParams = startImageView$app_preprodQa.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).setMargins(i2, i3, i4, i5);
            startImageView$app_preprodQa.setLayoutParams(layoutParams);
        }
        return this;
    }

    @NotNull
    public final TooltipBuilder iconStartSize(int i2, int i3) {
        AppCompatImageView startImageView$app_preprodQa = this.tooltip.getStartImageView$app_preprodQa();
        ViewGroup.LayoutParams layoutParams = startImageView$app_preprodQa.getLayoutParams();
        layoutParams.height = i2;
        layoutParams.width = i3;
        startImageView$app_preprodQa.setLayoutParams(layoutParams);
        return this;
    }

    @NotNull
    public final TooltipBuilder lineHeight(float f2, float f3) {
        this.tooltip.getTextView$app_preprodQa().setLineSpacing(f2, f3);
        return this;
    }

    @NotNull
    public final TooltipBuilder minHeight(int i2) {
        this.tooltip.getTooltipView$app_preprodQa().setMinHeight$app_preprodQa(i2);
        return this;
    }

    @NotNull
    public final TooltipBuilder minWidth(int i2) {
        this.tooltip.getTooltipView$app_preprodQa().setMinWidth$app_preprodQa(i2);
        return this;
    }

    @JvmOverloads
    @NotNull
    public final TooltipBuilder overlay(@ColorInt int i2) {
        return overlay$default(this, i2, null, 2, null);
    }

    @JvmOverloads
    @NotNull
    public final TooltipBuilder overlay(@ColorInt int i2, @Nullable TooltipClickListener tooltipClickListener) {
        this.tooltip.getOverlay$app_preprodQa().setBackgroundColor(i2);
        this.tooltip.initTargetClone$app_preprodQa();
        if (tooltipClickListener != null) {
            this.tooltip.setOverlayListener$app_preprodQa(tooltipClickListener);
        }
        return this;
    }

    @NotNull
    public final TooltipBuilder padding(int i2, int i3, int i4, int i5) {
        this.tooltip.getTooltipView$app_preprodQa().setPaddingT$app_preprodQa(i2);
        this.tooltip.getTooltipView$app_preprodQa().setPaddingB$app_preprodQa(i4);
        this.tooltip.getTooltipView$app_preprodQa().setPaddingS$app_preprodQa(i5);
        this.tooltip.getTooltipView$app_preprodQa().setPaddingE$app_preprodQa(i3);
        return this;
    }

    @NotNull
    public final TooltipBuilder position(@NotNull Position position) {
        Intrinsics.checkNotNullParameter(position, "position");
        this.tooltip.getTooltipView$app_preprodQa().setPosition$app_preprodQa(position);
        return this;
    }

    @NotNull
    public final TooltipBuilder refViewClickListener(@NotNull TooltipClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.tooltip.setRefViewClickListener$app_preprodQa(listener);
        return this;
    }

    @JvmOverloads
    @NotNull
    public final TooltipBuilder shadow(float f2) {
        return shadow$default(this, f2, 0, 2, null);
    }

    @JvmOverloads
    @NotNull
    public final TooltipBuilder shadow(float f2, @ColorInt int i2) {
        this.tooltip.getTooltipView$app_preprodQa().setShadow(f2, i2);
        return this;
    }

    @NotNull
    public final TooltipBuilder shadowPadding(float f2) {
        this.tooltip.getTooltipView$app_preprodQa().setShadowPadding$app_preprodQa(f2);
        return this;
    }

    @JvmOverloads
    @NotNull
    public final Tooltip show() {
        return show$default(this, 0L, 1, null);
    }

    @JvmOverloads
    @NotNull
    public final Tooltip show(long j2) {
        this.tooltip.getOverlay$app_preprodQa().addView(this.tooltip.getTooltipView$app_preprodQa(), -2, -2);
        return Tooltip.show$default(this.tooltip, j2, null, 2, null);
    }

    @NotNull
    public final TooltipBuilder text(@StringRes int i2) {
        this.tooltip.getTextView$app_preprodQa().setText(i2);
        return this;
    }

    @NotNull
    public final TooltipBuilder text(@NotNull Spanned text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.tooltip.getTextView$app_preprodQa().setText(text);
        return this;
    }

    @NotNull
    public final TooltipBuilder text(@NotNull String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.tooltip.getTextView$app_preprodQa().setText(text);
        return this;
    }

    @NotNull
    public final TooltipBuilder textColor(@ColorInt int i2) {
        this.tooltip.getTextView$app_preprodQa().setTextColor(i2);
        return this;
    }

    @NotNull
    public final TooltipBuilder textGravity(int i2) {
        this.tooltip.getTextView$app_preprodQa().setGravity(i2);
        return this;
    }

    @NotNull
    public final TooltipBuilder textSize(float f2) {
        this.tooltip.getTextView$app_preprodQa().setTextSize(f2);
        return this;
    }

    @NotNull
    public final TooltipBuilder textSize(int i2, float f2) {
        this.tooltip.getTextView$app_preprodQa().setTextSize(i2, f2);
        return this;
    }

    @NotNull
    public final TooltipBuilder textTypeFace(@NotNull Typeface typeface) {
        Intrinsics.checkNotNullParameter(typeface, "typeface");
        this.tooltip.getTextView$app_preprodQa().setTypeface(typeface);
        return this;
    }

    @NotNull
    public final TooltipBuilder tooltipClickListener(@NotNull TooltipClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.tooltip.setTooltipClickListener$app_preprodQa(listener);
        return this;
    }
}
