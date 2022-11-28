package com.psa.mym.mycitroenconnect.views.tool_tip;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.psa.mym.mycitroenconnect.views.tool_tip.Tooltip;
import com.psa.mym.mycitroenconnect.views.tool_tip.TooltipClickListener;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Tooltip {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Activity activity;
    private int animIn;
    private int animOut;
    private boolean clickToHide;
    @Nullable
    private DisplayListener displayListener;
    @NotNull
    private final FrameLayout overlay;
    @NotNull
    private final View refView;
    @Nullable
    private TooltipClickListener refViewClickListener;
    @Nullable
    private TooltipClickListener tooltipClickListener;
    @NotNull
    private final TooltipView tooltipView;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final Activity getActivity(Context context) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            if (context instanceof ContextWrapper) {
                Context baseContext = ((ContextWrapper) context).getBaseContext();
                Intrinsics.checkNotNullExpressionValue(baseContext, "context.baseContext");
                return getActivity(baseContext);
            }
            return null;
        }

        public static /* synthetic */ TooltipBuilder on$default(Companion companion, View view, boolean z, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                z = true;
            }
            return companion.on(view, z);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final TooltipBuilder on(@NotNull View refView) {
            Intrinsics.checkNotNullParameter(refView, "refView");
            return on$default(this, refView, false, 2, null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final TooltipBuilder on(@NotNull View refView, boolean z) {
            Intrinsics.checkNotNullParameter(refView, "refView");
            Context context = refView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "refView.context");
            Activity activity = getActivity(context);
            Intrinsics.checkNotNull(activity);
            return new TooltipBuilder(new Tooltip(activity, refView, z, null));
        }
    }

    private Tooltip(Activity activity, View view, boolean z) {
        this.activity = activity;
        this.refView = view;
        TooltipView tooltipView = new TooltipView(activity, null, 0, 6, null);
        this.tooltipView = tooltipView;
        this.overlay = new FrameLayout(activity);
        this.clickToHide = true;
        tooltipView.setOnClickListener(new View.OnClickListener() { // from class: r.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                Tooltip.m175_init_$lambda0(Tooltip.this, view2);
            }
        });
        if (z) {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.psa.mym.mycitroenconnect.views.tool_tip.Tooltip.2
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(@Nullable View view2) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(@Nullable View view2) {
                    Tooltip.this.closeNow();
                    if (view2 != null) {
                        view2.removeOnAttachStateChangeListener(this);
                    }
                }
            });
        }
    }

    public /* synthetic */ Tooltip(Activity activity, View view, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity, view, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0  reason: not valid java name */
    public static final void m175_init_$lambda0(Tooltip this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TooltipClickListener tooltipClickListener = this$0.tooltipClickListener;
        if (tooltipClickListener != null) {
            tooltipClickListener.onClick(this$0.tooltipView, this$0);
        }
        if (this$0.clickToHide) {
            this$0.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: close$lambda-4  reason: not valid java name */
    public static final void m176close$lambda4(Tooltip this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.closeNow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: closeNow$lambda-5  reason: not valid java name */
    public static final void m177closeNow$lambda5(ViewParent viewParent, Tooltip this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ViewGroup) viewParent).removeView(this$0.overlay);
        DisplayListener displayListener = this$0.displayListener;
        if (displayListener != null) {
            displayListener.onDisplay(this$0.tooltipView, false);
        }
    }

    @JvmStatic
    private static final Activity getActivity(Context context) {
        return Companion.getActivity(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initTargetClone$lambda-6  reason: not valid java name */
    public static final void m178initTargetClone$lambda6(Tooltip this$0, TargetView targetGhostView, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(targetGhostView, "$targetGhostView");
        TooltipClickListener tooltipClickListener = this$0.refViewClickListener;
        if (tooltipClickListener != null) {
            tooltipClickListener.onClick(targetGhostView, this$0);
        }
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final TooltipBuilder on(@NotNull View view) {
        return Companion.on(view);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final TooltipBuilder on(@NotNull View view, boolean z) {
        return Companion.on(view, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOverlayListener$lambda-7  reason: not valid java name */
    public static final void m179setOverlayListener$lambda7(TooltipClickListener overlayClickListener, Tooltip this$0, View view) {
        Intrinsics.checkNotNullParameter(overlayClickListener, "$overlayClickListener");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        overlayClickListener.onClick(view, this$0);
    }

    public static /* synthetic */ Tooltip show$default(Tooltip tooltip, long j2, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = 0;
        }
        if ((i2 & 2) != 0) {
            str = null;
        }
        return tooltip.show(j2, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: show$lambda-3  reason: not valid java name */
    public static final void m180show$lambda3(final Tooltip this$0, String str, long j2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.closeNow();
        this$0.tooltipView.getChildView$app_preprodQa().attach();
        if (str != null) {
            this$0.getTextView$app_preprodQa().setText(str);
        }
        ViewGroup viewGroup = (ViewGroup) this$0.activity.getWindow().getDecorView();
        Rect rect = new Rect();
        this$0.refView.getGlobalVisibleRect(rect);
        this$0.tooltipView.setup(rect, viewGroup);
        viewGroup.addView(this$0.overlay, new ViewGroup.LayoutParams(viewGroup.getWidth(), viewGroup.getHeight()));
        if (this$0.animIn == 0) {
            DisplayListener displayListener = this$0.displayListener;
            if (displayListener != null) {
                displayListener.onDisplay(this$0.tooltipView, true);
            }
        } else {
            Animation loadAnimation = AnimationUtils.loadAnimation(this$0.tooltipView.getContext(), this$0.animIn);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.psa.mym.mycitroenconnect.views.tool_tip.Tooltip$show$1$2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(@Nullable Animation animation) {
                    DisplayListener displayListener$app_preprodQa = Tooltip.this.getDisplayListener$app_preprodQa();
                    if (displayListener$app_preprodQa != null) {
                        displayListener$app_preprodQa.onDisplay(Tooltip.this.getTooltipView$app_preprodQa(), true);
                    }
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(@Nullable Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(@Nullable Animation animation) {
                }
            });
            this$0.tooltipView.startAnimation(loadAnimation);
        }
        if (j2 > 0) {
            this$0.tooltipView.postDelayed(new Runnable() { // from class: r.f
                @Override // java.lang.Runnable
                public final void run() {
                    Tooltip.m181show$lambda3$lambda2(Tooltip.this);
                }
            }, j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: show$lambda-3$lambda-2  reason: not valid java name */
    public static final void m181show$lambda3$lambda2(Tooltip this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.close();
    }

    public final void close() {
        if (this.animOut == 0) {
            this.overlay.post(new Runnable() { // from class: r.e
                @Override // java.lang.Runnable
                public final void run() {
                    Tooltip.m176close$lambda4(Tooltip.this);
                }
            });
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.tooltipView.getContext(), this.animOut);
        loadAnimation.setAnimationListener(new Tooltip$close$2(this));
        this.tooltipView.startAnimation(loadAnimation);
    }

    public final void closeNow() {
        final ViewParent parent = this.overlay.getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            return;
        }
        this.overlay.post(new Runnable() { // from class: r.d
            @Override // java.lang.Runnable
            public final void run() {
                Tooltip.m177closeNow$lambda5(parent, this);
            }
        });
    }

    public final int getAnimIn$app_preprodQa() {
        return this.animIn;
    }

    public final int getAnimOut$app_preprodQa() {
        return this.animOut;
    }

    public final boolean getClickToHide$app_preprodQa() {
        return this.clickToHide;
    }

    @Nullable
    public final DisplayListener getDisplayListener$app_preprodQa() {
        return this.displayListener;
    }

    @NotNull
    public final AppCompatImageView getEndImageView$app_preprodQa() {
        return this.tooltipView.getChildView$app_preprodQa().getEndImageView();
    }

    @NotNull
    public final FrameLayout getOverlay$app_preprodQa() {
        return this.overlay;
    }

    @Nullable
    public final TooltipClickListener getRefViewClickListener$app_preprodQa() {
        return this.refViewClickListener;
    }

    @NotNull
    public final AppCompatImageView getStartImageView$app_preprodQa() {
        return this.tooltipView.getChildView$app_preprodQa().getStartImageView();
    }

    @NotNull
    public final AppCompatTextView getTextView$app_preprodQa() {
        return this.tooltipView.getChildView$app_preprodQa().getTextView();
    }

    @Nullable
    public final TooltipClickListener getTooltipClickListener$app_preprodQa() {
        return this.tooltipClickListener;
    }

    @NotNull
    public final TooltipView getTooltipView$app_preprodQa() {
        return this.tooltipView;
    }

    public final void initTargetClone$app_preprodQa() {
        final TargetView targetView = new TargetView(this.activity);
        targetView.setTarget(this.refView);
        this.overlay.addView(targetView);
        targetView.setOnClickListener(new View.OnClickListener() { // from class: r.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Tooltip.m178initTargetClone$lambda6(Tooltip.this, targetView, view);
            }
        });
    }

    public final boolean isShown() {
        return this.overlay.getParent() != null;
    }

    public final void moveTooltip(int i2, int i3) {
        FrameLayout frameLayout = this.overlay;
        frameLayout.setTranslationY(frameLayout.getTranslationY() - i3);
        FrameLayout frameLayout2 = this.overlay;
        frameLayout2.setTranslationX(frameLayout2.getTranslationX() - i2);
    }

    public final void setAnimIn$app_preprodQa(int i2) {
        this.animIn = i2;
    }

    public final void setAnimOut$app_preprodQa(int i2) {
        this.animOut = i2;
    }

    public final void setClickToHide$app_preprodQa(boolean z) {
        this.clickToHide = z;
    }

    public final void setDisplayListener$app_preprodQa(@Nullable DisplayListener displayListener) {
        this.displayListener = displayListener;
    }

    public final void setOverlayListener$app_preprodQa(@NotNull final TooltipClickListener overlayClickListener) {
        Intrinsics.checkNotNullParameter(overlayClickListener, "overlayClickListener");
        this.overlay.setOnClickListener(new View.OnClickListener() { // from class: r.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Tooltip.m179setOverlayListener$lambda7(TooltipClickListener.this, this, view);
            }
        });
    }

    public final void setRefViewClickListener$app_preprodQa(@Nullable TooltipClickListener tooltipClickListener) {
        this.refViewClickListener = tooltipClickListener;
    }

    public final void setTooltipClickListener$app_preprodQa(@Nullable TooltipClickListener tooltipClickListener) {
        this.tooltipClickListener = tooltipClickListener;
    }

    @JvmOverloads
    @NotNull
    public final Tooltip show() {
        return show$default(this, 0L, null, 3, null);
    }

    @NotNull
    public final Tooltip show(@StringRes int i2) {
        getTextView$app_preprodQa().setText(i2);
        return show$default(this, 0L, null, 3, null);
    }

    @JvmOverloads
    @NotNull
    public final Tooltip show(long j2) {
        return show$default(this, j2, null, 2, null);
    }

    @NotNull
    public final Tooltip show(long j2, @StringRes int i2) {
        getTextView$app_preprodQa().setText(i2);
        return show$default(this, j2, null, 2, null);
    }

    @NotNull
    public final Tooltip show(long j2, @NotNull Spanned text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getTextView$app_preprodQa().setText(text);
        return show$default(this, j2, null, 2, null);
    }

    @JvmOverloads
    @NotNull
    public final Tooltip show(final long j2, @Nullable final String str) {
        this.refView.post(new Runnable() { // from class: r.g
            @Override // java.lang.Runnable
            public final void run() {
                Tooltip.m180show$lambda3(Tooltip.this, str, j2);
            }
        });
        return this;
    }

    @NotNull
    public final Tooltip show(@NotNull Spanned text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getTextView$app_preprodQa().setText(text);
        return show$default(this, 0L, null, 3, null);
    }

    @NotNull
    public final Tooltip show(@NotNull String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getTextView$app_preprodQa().setText(text);
        return show$default(this, 0L, null, 3, null);
    }
}
