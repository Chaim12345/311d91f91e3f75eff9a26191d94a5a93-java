package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.badge.BadgeDrawable;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {

    /* renamed from: a  reason: collision with root package name */
    final Rect f7125a;

    /* renamed from: b  reason: collision with root package name */
    final Rect f7126b;
    private int overlayTop;
    private int verticalLayoutGap;

    public HeaderScrollingViewBehavior() {
        this.f7125a = new Rect();
        this.f7126b = new Rect();
        this.verticalLayoutGap = 0;
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7125a = new Rect();
        this.f7126b = new Rect();
        this.verticalLayoutGap = 0;
    }

    private static int resolveGravity(int i2) {
        return i2 == 0 ? BadgeDrawable.TOP_START : i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.appbar.ViewOffsetBehavior
    public void a(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view, int i2) {
        int i3;
        View b2 = b(coordinatorLayout.getDependencies(view));
        if (b2 != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
            Rect rect = this.f7125a;
            rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, b2.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, ((coordinatorLayout.getHeight() + b2.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
            WindowInsetsCompat lastWindowInsets = coordinatorLayout.getLastWindowInsets();
            if (lastWindowInsets != null && ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(view)) {
                rect.left += lastWindowInsets.getSystemWindowInsetLeft();
                rect.right -= lastWindowInsets.getSystemWindowInsetRight();
            }
            Rect rect2 = this.f7126b;
            GravityCompat.apply(resolveGravity(layoutParams.gravity), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i2);
            int c2 = c(b2);
            view.layout(rect2.left, rect2.top - c2, rect2.right, rect2.bottom - c2);
            i3 = rect2.top - b2.getBottom();
        } else {
            super.a(coordinatorLayout, view, i2);
            i3 = 0;
        }
        this.verticalLayoutGap = i3;
    }

    @Nullable
    abstract View b(List list);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int c(View view) {
        if (this.overlayTop == 0) {
            return 0;
        }
        float d2 = d(view);
        int i2 = this.overlayTop;
        return MathUtils.clamp((int) (d2 * i2), 0, i2);
    }

    float d(View view) {
        return 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e(@NonNull View view) {
        return view.getMeasuredHeight();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int f() {
        return this.verticalLayoutGap;
    }

    protected boolean g() {
        return false;
    }

    public final int getOverlayTop() {
        return this.overlayTop;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onMeasureChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view, int i2, int i3, int i4, int i5) {
        View b2;
        WindowInsetsCompat lastWindowInsets;
        int i6 = view.getLayoutParams().height;
        if ((i6 == -1 || i6 == -2) && (b2 = b(coordinatorLayout.getDependencies(view))) != null) {
            int size = View.MeasureSpec.getSize(i4);
            if (size <= 0) {
                size = coordinatorLayout.getHeight();
            } else if (ViewCompat.getFitsSystemWindows(b2) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
                size += lastWindowInsets.getSystemWindowInsetTop() + lastWindowInsets.getSystemWindowInsetBottom();
            }
            int e2 = size + e(b2);
            int measuredHeight = b2.getMeasuredHeight();
            if (g()) {
                view.setTranslationY(-measuredHeight);
            } else {
                e2 -= measuredHeight;
            }
            coordinatorLayout.onMeasureChild(view, i2, i3, View.MeasureSpec.makeMeasureSpec(e2, i6 == -1 ? 1073741824 : Integer.MIN_VALUE), i5);
            return true;
        }
        return false;
    }

    public final void setOverlayTop(int i2) {
        this.overlayTop = i2;
    }
}
