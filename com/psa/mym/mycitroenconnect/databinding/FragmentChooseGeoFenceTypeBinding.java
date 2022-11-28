package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textview.MaterialTextView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentChooseGeoFenceTypeBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnCnfrmContinue;
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final RecyclerView rvFenceType;
    @NonNull
    public final MaterialTextView tvChooseFenceType;

    private FragmentChooseGeoFenceTypeBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatButton appCompatButton, @NonNull RecyclerView recyclerView, @NonNull MaterialTextView materialTextView) {
        this.rootView = linearLayoutCompat;
        this.btnCnfrmContinue = appCompatButton;
        this.rvFenceType = recyclerView;
        this.tvChooseFenceType = materialTextView;
    }

    @NonNull
    public static FragmentChooseGeoFenceTypeBinding bind(@NonNull View view) {
        int i2 = R.id.btnCnfrmContinue;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnCnfrmContinue);
        if (appCompatButton != null) {
            i2 = R.id.rvFenceType;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvFenceType);
            if (recyclerView != null) {
                i2 = R.id.tvChooseFenceType;
                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvChooseFenceType);
                if (materialTextView != null) {
                    return new FragmentChooseGeoFenceTypeBinding((LinearLayoutCompat) view, appCompatButton, recyclerView, materialTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentChooseGeoFenceTypeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentChooseGeoFenceTypeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_choose_geo_fence_type, viewGroup, false);
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
