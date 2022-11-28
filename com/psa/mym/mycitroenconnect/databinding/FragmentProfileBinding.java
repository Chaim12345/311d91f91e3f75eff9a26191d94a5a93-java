package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentProfileBinding implements ViewBinding {
    @NonNull
    public final ViewPager2 proPager;
    @NonNull
    public final TabLayout proTabLayout;
    @NonNull
    private final FrameLayout rootView;

    private FragmentProfileBinding(@NonNull FrameLayout frameLayout, @NonNull ViewPager2 viewPager2, @NonNull TabLayout tabLayout) {
        this.rootView = frameLayout;
        this.proPager = viewPager2;
        this.proTabLayout = tabLayout;
    }

    @NonNull
    public static FragmentProfileBinding bind(@NonNull View view) {
        int i2 = R.id.proPager;
        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.proPager);
        if (viewPager2 != null) {
            i2 = R.id.proTabLayout;
            TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(view, R.id.proTabLayout);
            if (tabLayout != null) {
                return new FragmentProfileBinding((FrameLayout) view, viewPager2, tabLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentProfileBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentProfileBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_profile, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
