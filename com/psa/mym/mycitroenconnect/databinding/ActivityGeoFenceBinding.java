package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityGeoFenceBinding implements ViewBinding {
    @NonNull
    public final LayoutDashboardModeHeaderBinding layoutGeoHeader;
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final ViewPager2 viewPager2;

    private ActivityGeoFenceBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull ViewPager2 viewPager2) {
        this.rootView = linearLayoutCompat;
        this.layoutGeoHeader = layoutDashboardModeHeaderBinding;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    public static ActivityGeoFenceBinding bind(@NonNull View view) {
        int i2 = R.id.layoutGeoHeader;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutGeoHeader);
        if (findChildViewById != null) {
            LayoutDashboardModeHeaderBinding bind = LayoutDashboardModeHeaderBinding.bind(findChildViewById);
            ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.viewPager2);
            if (viewPager2 != null) {
                return new ActivityGeoFenceBinding((LinearLayoutCompat) view, bind, viewPager2);
            }
            i2 = R.id.viewPager2;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ActivityGeoFenceBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityGeoFenceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_geo_fence, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }
}
