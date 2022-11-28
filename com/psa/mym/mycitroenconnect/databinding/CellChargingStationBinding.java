package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CellChargingStationBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivChargingStation;
    @NonNull
    public final LinearLayoutCompat layoutRootView;
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final AppCompatTextView tvChargeRate;
    @NonNull
    public final AppCompatTextView tvChargeStationsName;
    @NonNull
    public final AppCompatTextView tvChargeSubLocation;
    @NonNull
    public final AppCompatTextView tvChargeTime;
    @NonNull
    public final AppCompatTextView tvChargeUnit;
    @NonNull
    public final AppCompatTextView tvDistance;

    private CellChargingStationBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6) {
        this.rootView = linearLayoutCompat;
        this.ivChargingStation = appCompatImageView;
        this.layoutRootView = linearLayoutCompat2;
        this.tvChargeRate = appCompatTextView;
        this.tvChargeStationsName = appCompatTextView2;
        this.tvChargeSubLocation = appCompatTextView3;
        this.tvChargeTime = appCompatTextView4;
        this.tvChargeUnit = appCompatTextView5;
        this.tvDistance = appCompatTextView6;
    }

    @NonNull
    public static CellChargingStationBinding bind(@NonNull View view) {
        int i2 = R.id.ivChargingStation;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivChargingStation);
        if (appCompatImageView != null) {
            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) view;
            i2 = R.id.tvChargeRate;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargeRate);
            if (appCompatTextView != null) {
                i2 = R.id.tvChargeStationsName;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargeStationsName);
                if (appCompatTextView2 != null) {
                    i2 = R.id.tvChargeSubLocation;
                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargeSubLocation);
                    if (appCompatTextView3 != null) {
                        i2 = R.id.tvChargeTime;
                        AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargeTime);
                        if (appCompatTextView4 != null) {
                            i2 = R.id.tvChargeUnit;
                            AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargeUnit);
                            if (appCompatTextView5 != null) {
                                i2 = R.id.tvDistance;
                                AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDistance);
                                if (appCompatTextView6 != null) {
                                    return new CellChargingStationBinding(linearLayoutCompat, appCompatImageView, linearLayoutCompat, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
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
    public static CellChargingStationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CellChargingStationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.cell_charging_station, viewGroup, false);
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
