package com.chuckerteam.chucker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager.widget.ViewPager;
import com.chuckerteam.chucker.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes.dex */
public final class ChuckerActivityTransactionBinding implements ViewBinding {
    @NonNull
    private final CoordinatorLayout rootView;
    @NonNull
    public final TabLayout tabLayout;
    @NonNull
    public final MaterialToolbar toolbar;
    @NonNull
    public final TextView toolbarTitle;
    @NonNull
    public final ViewPager viewPager;

    private ChuckerActivityTransactionBinding(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TabLayout tabLayout, @NonNull MaterialToolbar materialToolbar, @NonNull TextView textView, @NonNull ViewPager viewPager) {
        this.rootView = coordinatorLayout;
        this.tabLayout = tabLayout;
        this.toolbar = materialToolbar;
        this.toolbarTitle = textView;
        this.viewPager = viewPager;
    }

    @NonNull
    public static ChuckerActivityTransactionBinding bind(@NonNull View view) {
        int i2 = R.id.tabLayout;
        TabLayout tabLayout = (TabLayout) view.findViewById(i2);
        if (tabLayout != null) {
            i2 = R.id.toolbar;
            MaterialToolbar materialToolbar = (MaterialToolbar) view.findViewById(i2);
            if (materialToolbar != null) {
                i2 = R.id.toolbarTitle;
                TextView textView = (TextView) view.findViewById(i2);
                if (textView != null) {
                    i2 = R.id.viewPager;
                    ViewPager viewPager = (ViewPager) view.findViewById(i2);
                    if (viewPager != null) {
                        return new ChuckerActivityTransactionBinding((CoordinatorLayout) view, tabLayout, materialToolbar, textView, viewPager);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ChuckerActivityTransactionBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ChuckerActivityTransactionBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.chucker_activity_transaction, viewGroup, false);
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
