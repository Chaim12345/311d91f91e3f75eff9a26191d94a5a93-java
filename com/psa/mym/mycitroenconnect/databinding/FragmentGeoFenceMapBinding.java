package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentGeoFenceMapBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnBack;
    @NonNull
    public final AppCompatButton btnConfirm;
    @NonNull
    public final MaterialCardView cvMap;
    @NonNull
    public final MaterialCardView cvSource;
    @NonNull
    public final AppCompatEditText edtSource;
    @NonNull
    public final AppCompatImageView ivSource;
    @NonNull
    public final LinearLayoutCompat llSource;
    @NonNull
    public final FragmentContainerView mapContainer;
    @NonNull
    private final LinearLayoutCompat rootView;

    private FragmentGeoFenceMapBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull MaterialCardView materialCardView, @NonNull MaterialCardView materialCardView2, @NonNull AppCompatEditText appCompatEditText, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull FragmentContainerView fragmentContainerView) {
        this.rootView = linearLayoutCompat;
        this.btnBack = appCompatButton;
        this.btnConfirm = appCompatButton2;
        this.cvMap = materialCardView;
        this.cvSource = materialCardView2;
        this.edtSource = appCompatEditText;
        this.ivSource = appCompatImageView;
        this.llSource = linearLayoutCompat2;
        this.mapContainer = fragmentContainerView;
    }

    @NonNull
    public static FragmentGeoFenceMapBinding bind(@NonNull View view) {
        int i2 = R.id.btnBack;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnBack);
        if (appCompatButton != null) {
            i2 = R.id.btnConfirm;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnConfirm);
            if (appCompatButton2 != null) {
                i2 = R.id.cvMap;
                MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, R.id.cvMap);
                if (materialCardView != null) {
                    i2 = R.id.cvSource;
                    MaterialCardView materialCardView2 = (MaterialCardView) ViewBindings.findChildViewById(view, R.id.cvSource);
                    if (materialCardView2 != null) {
                        i2 = R.id.edtSource;
                        AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, R.id.edtSource);
                        if (appCompatEditText != null) {
                            i2 = R.id.ivSource;
                            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivSource);
                            if (appCompatImageView != null) {
                                i2 = R.id.llSource;
                                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llSource);
                                if (linearLayoutCompat != null) {
                                    i2 = R.id.mapContainer;
                                    FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, R.id.mapContainer);
                                    if (fragmentContainerView != null) {
                                        return new FragmentGeoFenceMapBinding((LinearLayoutCompat) view, appCompatButton, appCompatButton2, materialCardView, materialCardView2, appCompatEditText, appCompatImageView, linearLayoutCompat, fragmentContainerView);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentGeoFenceMapBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentGeoFenceMapBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_geo_fence_map, viewGroup, false);
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
