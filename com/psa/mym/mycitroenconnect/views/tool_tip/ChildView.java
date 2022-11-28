package com.psa.mym.mycitroenconnect.views.tool_tip;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ChildView extends LinearLayout {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    private AppCompatImageView iconEnd;
    private AppCompatImageView iconStart;
    private AppCompatTextView textView;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ChildView(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ChildView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ChildView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        init(context, attributeSet, i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChildView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        init(context, attributeSet, i2);
    }

    public /* synthetic */ ChildView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    private final void init(Context context, AttributeSet attributeSet, int i2) {
        AppCompatTextView appCompatTextView = new AppCompatTextView(context, attributeSet, i2);
        this.textView = appCompatTextView;
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        AppCompatImageView appCompatImageView = new AppCompatImageView(context, attributeSet, i2);
        this.iconStart = appCompatImageView;
        appCompatImageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        AppCompatImageView appCompatImageView2 = new AppCompatImageView(context, attributeSet, i2);
        this.iconEnd = appCompatImageView2;
        appCompatImageView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    }

    private final void removeParent(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) parent).removeView(view);
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

    public final void attach() {
        View view = this.iconStart;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iconStart");
            view = null;
        }
        removeParent(view);
        View view3 = this.iconEnd;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iconEnd");
            view3 = null;
        }
        removeParent(view3);
        View view4 = this.textView;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textView");
            view4 = null;
        }
        removeParent(view4);
        AppCompatImageView appCompatImageView = this.iconStart;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iconStart");
            appCompatImageView = null;
        }
        if (appCompatImageView.getDrawable() != null) {
            AppCompatImageView appCompatImageView2 = this.iconStart;
            if (appCompatImageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iconStart");
                appCompatImageView2 = null;
            }
            ViewGroup.LayoutParams layoutParams = appCompatImageView2.getLayoutParams();
            Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.setMarginEnd(getContext().getResources().getDimensionPixelSize(R.dimen.iconTextMargin));
            layoutParams2.gravity = 17;
            View view5 = this.iconStart;
            if (view5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iconStart");
                view5 = null;
            }
            addView(view5, layoutParams2);
        }
        AppCompatTextView appCompatTextView = this.textView;
        if (appCompatTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textView");
            appCompatTextView = null;
        }
        ViewGroup.LayoutParams layoutParams3 = appCompatTextView.getLayoutParams();
        Objects.requireNonNull(layoutParams3, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layoutParams3;
        layoutParams4.gravity = 17;
        View view6 = this.textView;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textView");
            view6 = null;
        }
        addView(view6, layoutParams4);
        AppCompatImageView appCompatImageView3 = this.iconEnd;
        if (appCompatImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iconEnd");
            appCompatImageView3 = null;
        }
        if (appCompatImageView3.getDrawable() != null) {
            AppCompatImageView appCompatImageView4 = this.iconEnd;
            if (appCompatImageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iconEnd");
                appCompatImageView4 = null;
            }
            ViewGroup.LayoutParams layoutParams5 = appCompatImageView4.getLayoutParams();
            Objects.requireNonNull(layoutParams5, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) layoutParams5;
            layoutParams6.setMarginStart(getContext().getResources().getDimensionPixelSize(R.dimen.iconTextMargin));
            layoutParams6.gravity = 17;
            View view7 = this.iconEnd;
            if (view7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iconEnd");
            } else {
                view2 = view7;
            }
            addView(view2, layoutParams6);
        }
    }

    @NotNull
    public final AppCompatImageView getEndImageView() {
        AppCompatImageView appCompatImageView = this.iconEnd;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iconEnd");
            return null;
        }
        return appCompatImageView;
    }

    @NotNull
    public final AppCompatImageView getStartImageView() {
        AppCompatImageView appCompatImageView = this.iconStart;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iconStart");
            return null;
        }
        return appCompatImageView;
    }

    @NotNull
    public final AppCompatTextView getTextView() {
        AppCompatTextView appCompatTextView = this.textView;
        if (appCompatTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textView");
            return null;
        }
        return appCompatTextView;
    }
}
