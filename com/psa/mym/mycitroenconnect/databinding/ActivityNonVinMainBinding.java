package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityNonVinMainBinding implements ViewBinding {
    @NonNull
    public final DrawerLayout drawerLayoutNonVin;
    @NonNull
    public final FragmentContainerView fragmentContainerNonVin;
    @NonNull
    public final LayoutNonVinAppHeaderBinding nonVinAppHeader;
    @NonNull
    private final DrawerLayout rootView;

    private ActivityNonVinMainBinding(@NonNull DrawerLayout drawerLayout, @NonNull DrawerLayout drawerLayout2, @NonNull FragmentContainerView fragmentContainerView, @NonNull LayoutNonVinAppHeaderBinding layoutNonVinAppHeaderBinding) {
        this.rootView = drawerLayout;
        this.drawerLayoutNonVin = drawerLayout2;
        this.fragmentContainerNonVin = fragmentContainerView;
        this.nonVinAppHeader = layoutNonVinAppHeaderBinding;
    }

    @NonNull
    public static ActivityNonVinMainBinding bind(@NonNull View view) {
        DrawerLayout drawerLayout = (DrawerLayout) view;
        int i2 = R.id.fragmentContainerNonVin;
        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, R.id.fragmentContainerNonVin);
        if (fragmentContainerView != null) {
            i2 = R.id.nonVinAppHeader;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.nonVinAppHeader);
            if (findChildViewById != null) {
                return new ActivityNonVinMainBinding(drawerLayout, drawerLayout, fragmentContainerView, LayoutNonVinAppHeaderBinding.bind(findChildViewById));
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ActivityNonVinMainBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityNonVinMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_non_vin_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public DrawerLayout getRoot() {
        return this.rootView;
    }
}
