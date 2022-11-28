package com.chuckerteam.chucker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import com.chuckerteam.chucker.R;
import com.google.android.material.appbar.MaterialToolbar;
/* loaded from: classes.dex */
public final class ChuckerActivityThrowableBinding implements ViewBinding {
    @NonNull
    private final CoordinatorLayout rootView;
    @NonNull
    public final ChuckerListItemThrowableBinding throwableItem;
    @NonNull
    public final TextView throwableStacktrace;
    @NonNull
    public final MaterialToolbar toolbar;
    @NonNull
    public final TextView toolbarTitle;

    private ChuckerActivityThrowableBinding(@NonNull CoordinatorLayout coordinatorLayout, @NonNull ChuckerListItemThrowableBinding chuckerListItemThrowableBinding, @NonNull TextView textView, @NonNull MaterialToolbar materialToolbar, @NonNull TextView textView2) {
        this.rootView = coordinatorLayout;
        this.throwableItem = chuckerListItemThrowableBinding;
        this.throwableStacktrace = textView;
        this.toolbar = materialToolbar;
        this.toolbarTitle = textView2;
    }

    @NonNull
    public static ChuckerActivityThrowableBinding bind(@NonNull View view) {
        int i2 = R.id.throwableItem;
        View findViewById = view.findViewById(i2);
        if (findViewById != null) {
            ChuckerListItemThrowableBinding bind = ChuckerListItemThrowableBinding.bind(findViewById);
            i2 = R.id.throwableStacktrace;
            TextView textView = (TextView) view.findViewById(i2);
            if (textView != null) {
                i2 = R.id.toolbar;
                MaterialToolbar materialToolbar = (MaterialToolbar) view.findViewById(i2);
                if (materialToolbar != null) {
                    i2 = R.id.toolbarTitle;
                    TextView textView2 = (TextView) view.findViewById(i2);
                    if (textView2 != null) {
                        return new ChuckerActivityThrowableBinding((CoordinatorLayout) view, bind, textView, materialToolbar, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ChuckerActivityThrowableBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ChuckerActivityThrowableBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.chucker_activity_throwable, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }
}
