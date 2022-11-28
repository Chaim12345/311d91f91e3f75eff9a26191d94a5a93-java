package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CellMyCarBinding implements ViewBinding {
    @NonNull
    public final AppCompatRadioButton chkMyCar;
    @NonNull
    public final View divider;
    @NonNull
    public final AppCompatImageView ivCar;
    @NonNull
    public final MaterialCardView layoutCarCard;
    @NonNull
    public final ConstraintLayout layoutCarConstraint;
    @NonNull
    public final LinearLayoutCompat layoutCarTitle;
    @NonNull
    private final MaterialCardView rootView;
    @NonNull
    public final AppCompatTextView tvCarName;
    @NonNull
    public final AppCompatTextView tvCarNumber;
    @NonNull
    public final AppCompatTextView tvViewDetails;
    @NonNull
    public final AppCompatTextView tvViewOnly;

    private CellMyCarBinding(@NonNull MaterialCardView materialCardView, @NonNull AppCompatRadioButton appCompatRadioButton, @NonNull View view, @NonNull AppCompatImageView appCompatImageView, @NonNull MaterialCardView materialCardView2, @NonNull ConstraintLayout constraintLayout, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4) {
        this.rootView = materialCardView;
        this.chkMyCar = appCompatRadioButton;
        this.divider = view;
        this.ivCar = appCompatImageView;
        this.layoutCarCard = materialCardView2;
        this.layoutCarConstraint = constraintLayout;
        this.layoutCarTitle = linearLayoutCompat;
        this.tvCarName = appCompatTextView;
        this.tvCarNumber = appCompatTextView2;
        this.tvViewDetails = appCompatTextView3;
        this.tvViewOnly = appCompatTextView4;
    }

    @NonNull
    public static CellMyCarBinding bind(@NonNull View view) {
        int i2 = R.id.chkMyCar;
        AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton) ViewBindings.findChildViewById(view, R.id.chkMyCar);
        if (appCompatRadioButton != null) {
            i2 = R.id.divider;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
            if (findChildViewById != null) {
                i2 = R.id.ivCar;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCar);
                if (appCompatImageView != null) {
                    MaterialCardView materialCardView = (MaterialCardView) view;
                    i2 = R.id.layoutCarConstraint;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutCarConstraint);
                    if (constraintLayout != null) {
                        i2 = R.id.layoutCarTitle;
                        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutCarTitle);
                        if (linearLayoutCompat != null) {
                            i2 = R.id.tvCarName;
                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCarName);
                            if (appCompatTextView != null) {
                                i2 = R.id.tvCarNumber;
                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCarNumber);
                                if (appCompatTextView2 != null) {
                                    i2 = R.id.tvViewDetails;
                                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvViewDetails);
                                    if (appCompatTextView3 != null) {
                                        i2 = R.id.tvViewOnly;
                                        AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvViewOnly);
                                        if (appCompatTextView4 != null) {
                                            return new CellMyCarBinding(materialCardView, appCompatRadioButton, findChildViewById, appCompatImageView, materialCardView, constraintLayout, linearLayoutCompat, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4);
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
    public static CellMyCarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CellMyCarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.cell_my_car, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public MaterialCardView getRoot() {
        return this.rootView;
    }
}
