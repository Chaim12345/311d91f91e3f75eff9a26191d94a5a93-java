package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityMainBinding implements ViewBinding {
    @NonNull
    public final ConstraintLayout dashboardBg;
    @NonNull
    public final DrawerLayout drawerLayout;
    @NonNull
    public final FragmentContainerView fragmentContainer;
    @NonNull
    public final AppHeaderBinding mainAppHeader;
    @NonNull
    public final BottomAppBarBinding mainBottomAppBar;
    @NonNull
    private final DrawerLayout rootView;

    private ActivityMainBinding(@NonNull DrawerLayout drawerLayout, @NonNull ConstraintLayout constraintLayout, @NonNull DrawerLayout drawerLayout2, @NonNull FragmentContainerView fragmentContainerView, @NonNull AppHeaderBinding appHeaderBinding, @NonNull BottomAppBarBinding bottomAppBarBinding) {
        this.rootView = drawerLayout;
        this.dashboardBg = constraintLayout;
        this.drawerLayout = drawerLayout2;
        this.fragmentContainer = fragmentContainerView;
        this.mainAppHeader = appHeaderBinding;
        this.mainBottomAppBar = bottomAppBarBinding;
    }

    @NonNull
    public static ActivityMainBinding bind(@NonNull View view) {
        int i2 = R.id.dashboard_bg;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.dashboard_bg);
        if (constraintLayout != null) {
            DrawerLayout drawerLayout = (DrawerLayout) view;
            i2 = R.id.fragment_container;
            FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, R.id.fragment_container);
            if (fragmentContainerView != null) {
                i2 = R.id.mainAppHeader;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.mainAppHeader);
                if (findChildViewById != null) {
                    AppHeaderBinding bind = AppHeaderBinding.bind(findChildViewById);
                    i2 = R.id.main_bottom_app_bar;
                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.main_bottom_app_bar);
                    if (findChildViewById2 != null) {
                        return new ActivityMainBinding(drawerLayout, constraintLayout, drawerLayout, fragmentContainerView, bind, BottomAppBarBinding.bind(findChildViewById2));
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ActivityMainBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_main, viewGroup, false);
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
