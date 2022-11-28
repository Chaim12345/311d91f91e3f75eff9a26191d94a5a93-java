package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityChargingStationDetailBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnNavigate;
    @NonNull
    public final AppCompatImageView ivChargeCall;
    @NonNull
    public final AppCompatImageView ivChargingStation;
    @NonNull
    public final AppCompatImageView ivClock;
    @NonNull
    public final AppCompatImageView ivLocationArrow;
    @NonNull
    public final LayoutDashboardModeHeaderBinding layoutChrgngStnDetailHeader;
    @NonNull
    public final AppCompatRatingBar ratingBarCharge;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final RecyclerView rvServicesAvailable;
    @NonNull
    public final AppCompatTextView tvChargeDistance;
    @NonNull
    public final AppCompatTextView tvChargeLocation;
    @NonNull
    public final AppCompatTextView tvChargeRate;
    @NonNull
    public final AppCompatTextView tvChargeSubLocation;
    @NonNull
    public final AppCompatTextView tvChargeTime;
    @NonNull
    public final AppCompatTextView tvChargeUnit;
    @NonNull
    public final AppCompatTextView tvServiceAvailable;

    private ActivityChargingStationDetailBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull AppCompatImageView appCompatImageView4, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull AppCompatRatingBar appCompatRatingBar, @NonNull RecyclerView recyclerView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull AppCompatTextView appCompatTextView7) {
        this.rootView = constraintLayout;
        this.btnNavigate = appCompatButton;
        this.ivChargeCall = appCompatImageView;
        this.ivChargingStation = appCompatImageView2;
        this.ivClock = appCompatImageView3;
        this.ivLocationArrow = appCompatImageView4;
        this.layoutChrgngStnDetailHeader = layoutDashboardModeHeaderBinding;
        this.ratingBarCharge = appCompatRatingBar;
        this.rvServicesAvailable = recyclerView;
        this.tvChargeDistance = appCompatTextView;
        this.tvChargeLocation = appCompatTextView2;
        this.tvChargeRate = appCompatTextView3;
        this.tvChargeSubLocation = appCompatTextView4;
        this.tvChargeTime = appCompatTextView5;
        this.tvChargeUnit = appCompatTextView6;
        this.tvServiceAvailable = appCompatTextView7;
    }

    @NonNull
    public static ActivityChargingStationDetailBinding bind(@NonNull View view) {
        int i2 = R.id.btnNavigate;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnNavigate);
        if (appCompatButton != null) {
            i2 = R.id.ivChargeCall;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivChargeCall);
            if (appCompatImageView != null) {
                i2 = R.id.ivChargingStation;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivChargingStation);
                if (appCompatImageView2 != null) {
                    i2 = R.id.ivClock;
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClock);
                    if (appCompatImageView3 != null) {
                        i2 = R.id.ivLocationArrow;
                        AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLocationArrow);
                        if (appCompatImageView4 != null) {
                            i2 = R.id.layoutChrgngStnDetailHeader;
                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutChrgngStnDetailHeader);
                            if (findChildViewById != null) {
                                LayoutDashboardModeHeaderBinding bind = LayoutDashboardModeHeaderBinding.bind(findChildViewById);
                                i2 = R.id.ratingBarCharge;
                                AppCompatRatingBar appCompatRatingBar = (AppCompatRatingBar) ViewBindings.findChildViewById(view, R.id.ratingBarCharge);
                                if (appCompatRatingBar != null) {
                                    i2 = R.id.rvServicesAvailable;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvServicesAvailable);
                                    if (recyclerView != null) {
                                        i2 = R.id.tvChargeDistance;
                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargeDistance);
                                        if (appCompatTextView != null) {
                                            i2 = R.id.tvChargeLocation;
                                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargeLocation);
                                            if (appCompatTextView2 != null) {
                                                i2 = R.id.tvChargeRate;
                                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargeRate);
                                                if (appCompatTextView3 != null) {
                                                    i2 = R.id.tvChargeSubLocation;
                                                    AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargeSubLocation);
                                                    if (appCompatTextView4 != null) {
                                                        i2 = R.id.tvChargeTime;
                                                        AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargeTime);
                                                        if (appCompatTextView5 != null) {
                                                            i2 = R.id.tvChargeUnit;
                                                            AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargeUnit);
                                                            if (appCompatTextView6 != null) {
                                                                i2 = R.id.tvServiceAvailable;
                                                                AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvServiceAvailable);
                                                                if (appCompatTextView7 != null) {
                                                                    return new ActivityChargingStationDetailBinding((ConstraintLayout) view, appCompatButton, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, bind, appCompatRatingBar, recyclerView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7);
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
    public static ActivityChargingStationDetailBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityChargingStationDetailBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_charging_station_detail, viewGroup, false);
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
