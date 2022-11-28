package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentCarBinding implements ViewBinding {
    @NonNull
    public final ConstraintLayout clParent;
    @NonNull
    public final AppCompatImageView ivMyCar;
    @NonNull
    public final ViewPager2 pager;
    @NonNull
    private final NestedScrollView rootView;
    @NonNull
    public final NestedScrollView scrollViewCar;
    @NonNull
    public final TabLayout tabLayout;
    @NonNull
    public final AppCompatTextView tvFastCharging;
    @NonNull
    public final AppCompatTextView tvUpdated;
    @NonNull
    public final AppCompatTextView tvViewOnMap;

    private FragmentCarBinding(@NonNull NestedScrollView nestedScrollView, @NonNull ConstraintLayout constraintLayout, @NonNull AppCompatImageView appCompatImageView, @NonNull ViewPager2 viewPager2, @NonNull NestedScrollView nestedScrollView2, @NonNull TabLayout tabLayout, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3) {
        this.rootView = nestedScrollView;
        this.clParent = constraintLayout;
        this.ivMyCar = appCompatImageView;
        this.pager = viewPager2;
        this.scrollViewCar = nestedScrollView2;
        this.tabLayout = tabLayout;
        this.tvFastCharging = appCompatTextView;
        this.tvUpdated = appCompatTextView2;
        this.tvViewOnMap = appCompatTextView3;
    }

    @NonNull
    public static FragmentCarBinding bind(@NonNull View view) {
        int i2 = R.id.clParent;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clParent);
        if (constraintLayout != null) {
            i2 = R.id.ivMyCar;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivMyCar);
            if (appCompatImageView != null) {
                i2 = R.id.pager;
                ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.pager);
                if (viewPager2 != null) {
                    NestedScrollView nestedScrollView = (NestedScrollView) view;
                    i2 = R.id.tabLayout;
                    TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(view, R.id.tabLayout);
                    if (tabLayout != null) {
                        i2 = R.id.tvFastCharging;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFastCharging);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvUpdated;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvUpdated);
                            if (appCompatTextView2 != null) {
                                i2 = R.id.tvViewOnMap;
                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvViewOnMap);
                                if (appCompatTextView3 != null) {
                                    return new FragmentCarBinding(nestedScrollView, constraintLayout, appCompatImageView, viewPager2, nestedScrollView, tabLayout, appCompatTextView, appCompatTextView2, appCompatTextView3);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentCarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentCarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_car, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public NestedScrollView getRoot() {
        return this.rootView;
    }
}
