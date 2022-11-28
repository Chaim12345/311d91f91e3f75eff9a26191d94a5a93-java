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
public final class LayoutCustomNavViewNonVinBinding implements ViewBinding {
    @NonNull
    public final NavigationView navViewNonVin;
    @NonNull
    private final NavigationView rootView;
    @NonNull
    public final RecyclerView rvNavViewNonVin;

    private LayoutCustomNavViewNonVinBinding(@NonNull NavigationView navigationView, @NonNull NavigationView navigationView2, @NonNull RecyclerView recyclerView) {
        this.rootView = navigationView;
        this.navViewNonVin = navigationView2;
        this.rvNavViewNonVin = recyclerView;
    }

    @NonNull
    public static LayoutCustomNavViewNonVinBinding bind(@NonNull View view) {
        NavigationView navigationView = (NavigationView) view;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvNavViewNonVin);
        if (recyclerView != null) {
            return new LayoutCustomNavViewNonVinBinding(navigationView, navigationView, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.rvNavViewNonVin)));
    }

    @NonNull
    public static LayoutCustomNavViewNonVinBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutCustomNavViewNonVinBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_custom_nav_view_non_vin, viewGroup, false);
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
