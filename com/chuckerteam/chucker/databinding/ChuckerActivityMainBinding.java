package com.chuckerteam.chucker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager.widget.ViewPager;
import com.chuckerteam.chucker.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
/* loaded from: classes.dex */
public final class ChuckerActivityMainBinding implements ViewBinding {
    @NonNull
    private final LinearLayout rootView;
    @NonNull
    public final TabLayout tabLayout;
    @NonNull
    public final MaterialToolbar toolbar;
    @NonNull
    public final ViewPager viewPager;

    private ChuckerActivityMainBinding(@NonNull LinearLayout linearLayout, @NonNull TabLayout tabLayout, @NonNull MaterialToolbar materialToolbar, @NonNull ViewPager viewPager) {
        this.rootView = linearLayout;
        this.tabLayout = tabLayout;
        this.toolbar = materialToolbar;
        this.viewPager = viewPager;
    }

    @NonNull
    public static ChuckerActivityMainBinding bind(@NonNull View view) {
        int i2 = R.id.tabLayout;
        TabLayout tabLayout = (TabLayout) view.findViewById(i2);
        if (tabLayout != null) {
            i2 = R.id.toolbar;
            MaterialToolbar materialToolbar = (MaterialToolbar) view.findViewById(i2);
            if (materialToolbar != null) {
                i2 = R.id.viewPager;
                ViewPager viewPager = (ViewPager) view.findViewById(i2);
                if (viewPager != null) {
                    return new ChuckerActivityMainBinding((LinearLayout) view, tabLayout, materialToolbar, viewPager);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ChuckerActivityMainBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ChuckerActivityMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.chucker_activity_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayout getRoot() {
        return this.rootView;
    }
}
