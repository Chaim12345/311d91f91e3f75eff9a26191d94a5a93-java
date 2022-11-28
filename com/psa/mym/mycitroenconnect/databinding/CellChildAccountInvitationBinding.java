package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CellChildAccountInvitationBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivCar;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvMobileNumber;
    @NonNull
    public final AppCompatTextView tvMobileNumberLbl;
    @NonNull
    public final AppCompatTextView tvModelName;
    @NonNull
    public final AppCompatTextView tvModelNameLbl;
    @NonNull
    public final AppCompatTextView tvOwnerName;
    @NonNull
    public final AppCompatTextView tvOwnerNameLbl;
    @NonNull
    public final AppCompatTextView tvRegistrationNumber;
    @NonNull
    public final AppCompatTextView tvRegistrationNumberLbl;

    private CellChildAccountInvitationBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull AppCompatTextView appCompatTextView7, @NonNull AppCompatTextView appCompatTextView8) {
        this.rootView = constraintLayout;
        this.ivCar = appCompatImageView;
        this.tvMobileNumber = appCompatTextView;
        this.tvMobileNumberLbl = appCompatTextView2;
        this.tvModelName = appCompatTextView3;
        this.tvModelNameLbl = appCompatTextView4;
        this.tvOwnerName = appCompatTextView5;
        this.tvOwnerNameLbl = appCompatTextView6;
        this.tvRegistrationNumber = appCompatTextView7;
        this.tvRegistrationNumberLbl = appCompatTextView8;
    }

    @NonNull
    public static CellChildAccountInvitationBinding bind(@NonNull View view) {
        int i2 = R.id.ivCar;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCar);
        if (appCompatImageView != null) {
            i2 = R.id.tvMobileNumber;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvMobileNumber);
            if (appCompatTextView != null) {
                i2 = R.id.tvMobileNumberLbl;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvMobileNumberLbl);
                if (appCompatTextView2 != null) {
                    i2 = R.id.tvModelName;
                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvModelName);
                    if (appCompatTextView3 != null) {
                        i2 = R.id.tvModelNameLbl;
                        AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvModelNameLbl);
                        if (appCompatTextView4 != null) {
                            i2 = R.id.tvOwnerName;
                            AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOwnerName);
                            if (appCompatTextView5 != null) {
                                i2 = R.id.tvOwnerNameLbl;
                                AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOwnerNameLbl);
                                if (appCompatTextView6 != null) {
                                    i2 = R.id.tvRegistrationNumber;
                                    AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRegistrationNumber);
                                    if (appCompatTextView7 != null) {
                                        i2 = R.id.tvRegistrationNumberLbl;
                                        AppCompatTextView appCompatTextView8 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRegistrationNumberLbl);
                                        if (appCompatTextView8 != null) {
                                            return new CellChildAccountInvitationBinding((ConstraintLayout) view, appCompatImageView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8);
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
    public static CellChildAccountInvitationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CellChildAccountInvitationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.cell_child_account_invitation, viewGroup, false);
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
