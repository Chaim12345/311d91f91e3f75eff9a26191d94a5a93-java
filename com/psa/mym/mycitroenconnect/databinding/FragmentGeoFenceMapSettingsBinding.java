package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentGeoFenceMapSettingsBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnBack;
    @NonNull
    public final AppCompatButton btnChangeRoute;
    @NonNull
    public final AppCompatButton btnNext;
    @NonNull
    public final AppCompatButton btnRad10;
    @NonNull
    public final AppCompatButton btnRad20;
    @NonNull
    public final AppCompatButton btnRad30;
    @NonNull
    public final AppCompatButton btnRad40;
    @NonNull
    public final AppCompatButton btnRad50;
    @NonNull
    public final AppCompatButton btnRadEdit;
    @NonNull
    public final AppCompatButton btnRadEditVal;
    @NonNull
    public final AppCompatEditText edtGeoFenceName;
    @NonNull
    public final LinearLayoutCompat layoutRadiusButtons;
    @NonNull
    public final FragmentContainerView mapContainer;
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final AppCompatTextView tvSaveGeoFenceAs;
    @NonNull
    public final AppCompatTextView tvSetRadius;

    private FragmentGeoFenceMapSettingsBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatButton appCompatButton3, @NonNull AppCompatButton appCompatButton4, @NonNull AppCompatButton appCompatButton5, @NonNull AppCompatButton appCompatButton6, @NonNull AppCompatButton appCompatButton7, @NonNull AppCompatButton appCompatButton8, @NonNull AppCompatButton appCompatButton9, @NonNull AppCompatButton appCompatButton10, @NonNull AppCompatEditText appCompatEditText, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull FragmentContainerView fragmentContainerView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = linearLayoutCompat;
        this.btnBack = appCompatButton;
        this.btnChangeRoute = appCompatButton2;
        this.btnNext = appCompatButton3;
        this.btnRad10 = appCompatButton4;
        this.btnRad20 = appCompatButton5;
        this.btnRad30 = appCompatButton6;
        this.btnRad40 = appCompatButton7;
        this.btnRad50 = appCompatButton8;
        this.btnRadEdit = appCompatButton9;
        this.btnRadEditVal = appCompatButton10;
        this.edtGeoFenceName = appCompatEditText;
        this.layoutRadiusButtons = linearLayoutCompat2;
        this.mapContainer = fragmentContainerView;
        this.tvSaveGeoFenceAs = appCompatTextView;
        this.tvSetRadius = appCompatTextView2;
    }

    @NonNull
    public static FragmentGeoFenceMapSettingsBinding bind(@NonNull View view) {
        int i2 = R.id.btnBack;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnBack);
        if (appCompatButton != null) {
            i2 = R.id.btnChangeRoute;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnChangeRoute);
            if (appCompatButton2 != null) {
                i2 = R.id.btnNext;
                AppCompatButton appCompatButton3 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnNext);
                if (appCompatButton3 != null) {
                    i2 = R.id.btnRad10;
                    AppCompatButton appCompatButton4 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRad10);
                    if (appCompatButton4 != null) {
                        i2 = R.id.btnRad20;
                        AppCompatButton appCompatButton5 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRad20);
                        if (appCompatButton5 != null) {
                            i2 = R.id.btnRad30;
                            AppCompatButton appCompatButton6 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRad30);
                            if (appCompatButton6 != null) {
                                i2 = R.id.btnRad40;
                                AppCompatButton appCompatButton7 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRad40);
                                if (appCompatButton7 != null) {
                                    i2 = R.id.btnRad50;
                                    AppCompatButton appCompatButton8 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRad50);
                                    if (appCompatButton8 != null) {
                                        i2 = R.id.btnRadEdit;
                                        AppCompatButton appCompatButton9 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRadEdit);
                                        if (appCompatButton9 != null) {
                                            i2 = R.id.btnRadEditVal;
                                            AppCompatButton appCompatButton10 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRadEditVal);
                                            if (appCompatButton10 != null) {
                                                i2 = R.id.edtGeoFenceName;
                                                AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, R.id.edtGeoFenceName);
                                                if (appCompatEditText != null) {
                                                    i2 = R.id.layoutRadiusButtons;
                                                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutRadiusButtons);
                                                    if (linearLayoutCompat != null) {
                                                        i2 = R.id.mapContainer;
                                                        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, R.id.mapContainer);
                                                        if (fragmentContainerView != null) {
                                                            i2 = R.id.tvSaveGeoFenceAs;
                                                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSaveGeoFenceAs);
                                                            if (appCompatTextView != null) {
                                                                i2 = R.id.tvSetRadius;
                                                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSetRadius);
                                                                if (appCompatTextView2 != null) {
                                                                    return new FragmentGeoFenceMapSettingsBinding((LinearLayoutCompat) view, appCompatButton, appCompatButton2, appCompatButton3, appCompatButton4, appCompatButton5, appCompatButton6, appCompatButton7, appCompatButton8, appCompatButton9, appCompatButton10, appCompatEditText, linearLayoutCompat, fragmentContainerView, appCompatTextView, appCompatTextView2);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
    public static FragmentGeoFenceMapSettingsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentGeoFenceMapSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_geo_fence_map_settings, viewGroup, false);
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
