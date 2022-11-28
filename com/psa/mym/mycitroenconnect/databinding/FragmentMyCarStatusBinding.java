package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentMyCarStatusBinding implements ViewBinding {
    @NonNull
    public final ConstraintLayout clParent;
    @NonNull
    private final NestedScrollView rootView;
    @NonNull
    public final RecyclerView rvAlert;
    @NonNull
    public final RecyclerView rvCarStatus;
    @NonNull
    public final RecyclerView rvVehicleStatus;
    @NonNull
    public final NestedScrollView scrollViewCarStatus;
    @NonNull
    public final AppCompatTextView tvAlertLbl;
    @NonNull
    public final AppCompatTextView tvVehicleStatusLbl;

    private FragmentMyCarStatusBinding(@NonNull NestedScrollView nestedScrollView, @NonNull ConstraintLayout constraintLayout, @NonNull RecyclerView recyclerView, @NonNull RecyclerView recyclerView2, @NonNull RecyclerView recyclerView3, @NonNull NestedScrollView nestedScrollView2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = nestedScrollView;
        this.clParent = constraintLayout;
        this.rvAlert = recyclerView;
        this.rvCarStatus = recyclerView2;
        this.rvVehicleStatus = recyclerView3;
        this.scrollViewCarStatus = nestedScrollView2;
        this.tvAlertLbl = appCompatTextView;
        this.tvVehicleStatusLbl = appCompatTextView2;
    }

    @NonNull
    public static FragmentMyCarStatusBinding bind(@NonNull View view) {
        int i2 = R.id.clParent;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clParent);
        if (constraintLayout != null) {
            i2 = R.id.rvAlert;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvAlert);
            if (recyclerView != null) {
                i2 = R.id.rvCarStatus;
                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvCarStatus);
                if (recyclerView2 != null) {
                    i2 = R.id.rvVehicleStatus;
                    RecyclerView recyclerView3 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvVehicleStatus);
                    if (recyclerView3 != null) {
                        NestedScrollView nestedScrollView = (NestedScrollView) view;
                        i2 = R.id.tvAlertLbl;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAlertLbl);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvVehicleStatusLbl;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvVehicleStatusLbl);
                            if (appCompatTextView2 != null) {
                                return new FragmentMyCarStatusBinding(nestedScrollView, constraintLayout, recyclerView, recyclerView2, recyclerView3, nestedScrollView, appCompatTextView, appCompatTextView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentMyCarStatusBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentMyCarStatusBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_my_car_status, viewGroup, false);
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
