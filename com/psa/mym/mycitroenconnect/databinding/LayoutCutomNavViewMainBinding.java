package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.navigation.NavigationView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutCutomNavViewMainBinding implements ViewBinding {
    @NonNull
    public final NavigationView navView;
    @NonNull
    private final NavigationView rootView;
    @NonNull
    public final RecyclerView rvNavView;

    private LayoutCutomNavViewMainBinding(@NonNull NavigationView navigationView, @NonNull NavigationView navigationView2, @NonNull RecyclerView recyclerView) {
        this.rootView = navigationView;
        this.navView = navigationView2;
        this.rvNavView = recyclerView;
    }

    @NonNull
    public static LayoutCutomNavViewMainBinding bind(@NonNull View view) {
        NavigationView navigationView = (NavigationView) view;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvNavView);
        if (recyclerView != null) {
            return new LayoutCutomNavViewMainBinding(navigationView, navigationView, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.rvNavView)));
    }

    @NonNull
    public static LayoutCutomNavViewMainBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutCutomNavViewMainBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_cutom_nav_view_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public NavigationView getRoot() {
        return this.rootView;
    }
}
