package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textview.MaterialTextView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CarChargingViewBinding implements ViewBinding {
    @NonNull
    public final View indicatorDot;
    @NonNull
    public final View indicatorLine;
    @NonNull
    public final AppCompatImageView ivLoadingImageBack;
    @NonNull
    public final AppCompatImageView ivLoadingImageFront;
    @NonNull
    public final LinearLayoutCompat llPercentage;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final MaterialTextView tvPercentage;

    private CarChargingViewBinding(@NonNull ConstraintLayout constraintLayout, @NonNull View view, @NonNull View view2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull MaterialTextView materialTextView) {
        this.rootView = constraintLayout;
        this.indicatorDot = view;
        this.indicatorLine = view2;
        this.ivLoadingImageBack = appCompatImageView;
        this.ivLoadingImageFront = appCompatImageView2;
        this.llPercentage = linearLayoutCompat;
        this.tvPercentage = materialTextView;
    }

    @NonNull
    public static CarChargingViewBinding bind(@NonNull View view) {
        int i2 = R.id.indicatorDot;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.indicatorDot);
        if (findChildViewById != null) {
            i2 = R.id.indicatorLine;
            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.indicatorLine);
            if (findChildViewById2 != null) {
                i2 = R.id.ivLoadingImageBack;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLoadingImageBack);
                if (appCompatImageView != null) {
                    i2 = R.id.ivLoadingImageFront;
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLoadingImageFront);
                    if (appCompatImageView2 != null) {
                        i2 = R.id.llPercentage;
                        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llPercentage);
                        if (linearLayoutCompat != null) {
                            i2 = R.id.tvPercentage;
                            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvPercentage);
                            if (materialTextView != null) {
                                return new CarChargingViewBinding((ConstraintLayout) view, findChildViewById, findChildViewById2, appCompatImageView, appCompatImageView2, linearLayoutCompat, materialTextView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static CarChargingViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CarChargingViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.car_charging_view, viewGroup, false);
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
