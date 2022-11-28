package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityGeoFenceListBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnAddNew;
    @NonNull
    public final ConstraintLayout clParent;
    @NonNull
    public final LayoutDashboardModeHeaderBinding layoutGeoHeader;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final RecyclerView rvGeoFenceList;

    private ActivityGeoFenceListBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull ConstraintLayout constraintLayout2, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull RecyclerView recyclerView) {
        this.rootView = constraintLayout;
        this.btnAddNew = appCompatButton;
        this.clParent = constraintLayout2;
        this.layoutGeoHeader = layoutDashboardModeHeaderBinding;
        this.rvGeoFenceList = recyclerView;
    }

    @NonNull
    public static ActivityGeoFenceListBinding bind(@NonNull View view) {
        int i2 = R.id.btnAddNew;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnAddNew);
        if (appCompatButton != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i2 = R.id.layoutGeoHeader;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutGeoHeader);
            if (findChildViewById != null) {
                LayoutDashboardModeHeaderBinding bind = LayoutDashboardModeHeaderBinding.bind(findChildViewById);
                i2 = R.id.rvGeoFenceList;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvGeoFenceList);
                if (recyclerView != null) {
                    return new ActivityGeoFenceListBinding(constraintLayout, appCompatButton, constraintLayout, bind, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ActivityGeoFenceListBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityGeoFenceListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_geo_fence_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ConstraintLayout getRoot() {
        return this.rootView;
    }
}
