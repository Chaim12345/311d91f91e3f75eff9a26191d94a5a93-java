package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentMyCarsBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnAddAnotherCar;
    @NonNull
    public final FrameLayout flParent;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final RecyclerView rvMyCar;

    private FragmentMyCarsBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull FrameLayout frameLayout2, @NonNull RecyclerView recyclerView) {
        this.rootView = frameLayout;
        this.btnAddAnotherCar = appCompatButton;
        this.flParent = frameLayout2;
        this.rvMyCar = recyclerView;
    }

    @NonNull
    public static FragmentMyCarsBinding bind(@NonNull View view) {
        int i2 = R.id.btnAddAnotherCar;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnAddAnotherCar);
        if (appCompatButton != null) {
            FrameLayout frameLayout = (FrameLayout) view;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvMyCar);
            if (recyclerView != null) {
                return new FragmentMyCarsBinding(frameLayout, appCompatButton, frameLayout, recyclerView);
            }
            i2 = R.id.rvMyCar;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentMyCarsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentMyCarsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_my_cars, viewGroup, false);
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
