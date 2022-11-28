package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MapInfoViewBinding implements ViewBinding {
    @NonNull
    public final MaterialButton btnNavigate;
    @NonNull
    public final View horiSeperator;
    @NonNull
    public final AppCompatImageView ivCloseInfo;
    @NonNull
    public final LinearLayoutCompat layoutChargeInfo;
    @NonNull
    public final RelativeLayout layoutMapInforRoot;
    @NonNull
    private final RelativeLayout rootView;
    @NonNull
    public final AppCompatTextView tvDistance;
    @NonNull
    public final AppCompatTextView tvDistanceVal;
    @NonNull
    public final AppCompatTextView tvRate;
    @NonNull
    public final AppCompatTextView tvRateVal;
    @NonNull
    public final AppCompatTextView tvStationSubTitle;
    @NonNull
    public final AppCompatTextView tvTiming;
    @NonNull
    public final AppCompatTextView tvTimingVal;
    @NonNull
    public final AppCompatTextView tvTitleStation;

    private MapInfoViewBinding(@NonNull RelativeLayout relativeLayout, @NonNull MaterialButton materialButton, @NonNull View view, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull RelativeLayout relativeLayout2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull AppCompatTextView appCompatTextView7, @NonNull AppCompatTextView appCompatTextView8) {
        this.rootView = relativeLayout;
        this.btnNavigate = materialButton;
        this.horiSeperator = view;
        this.ivCloseInfo = appCompatImageView;
        this.layoutChargeInfo = linearLayoutCompat;
        this.layoutMapInforRoot = relativeLayout2;
        this.tvDistance = appCompatTextView;
        this.tvDistanceVal = appCompatTextView2;
        this.tvRate = appCompatTextView3;
        this.tvRateVal = appCompatTextView4;
        this.tvStationSubTitle = appCompatTextView5;
        this.tvTiming = appCompatTextView6;
        this.tvTimingVal = appCompatTextView7;
        this.tvTitleStation = appCompatTextView8;
    }

    @NonNull
    public static MapInfoViewBinding bind(@NonNull View view) {
        int i2 = R.id.btnNavigate;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, R.id.btnNavigate);
        if (materialButton != null) {
            i2 = R.id.horiSeperator;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.horiSeperator);
            if (findChildViewById != null) {
                i2 = R.id.ivCloseInfo;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCloseInfo);
                if (appCompatImageView != null) {
                    i2 = R.id.layoutChargeInfo;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutChargeInfo);
                    if (linearLayoutCompat != null) {
                        RelativeLayout relativeLayout = (RelativeLayout) view;
                        i2 = R.id.tvDistance;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDistance);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvDistanceVal;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDistanceVal);
                            if (appCompatTextView2 != null) {
                                i2 = R.id.tvRate;
                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRate);
                                if (appCompatTextView3 != null) {
                                    i2 = R.id.tvRateVal;
                                    AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvRateVal);
                                    if (appCompatTextView4 != null) {
                                        i2 = R.id.tvStationSubTitle;
                                        AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvStationSubTitle);
                                        if (appCompatTextView5 != null) {
                                            i2 = R.id.tvTiming;
                                            AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTiming);
                                            if (appCompatTextView6 != null) {
                                                i2 = R.id.tvTimingVal;
                                                AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTimingVal);
                                                if (appCompatTextView7 != null) {
                                                    i2 = R.id.tvTitleStation;
                                                    AppCompatTextView appCompatTextView8 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTitleStation);
                                                    if (appCompatTextView8 != null) {
                                                        return new MapInfoViewBinding(relativeLayout, materialButton, findChildViewById, appCompatImageView, linearLayoutCompat, relativeLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8);
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
    public static MapInfoViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static MapInfoViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.map_info_view, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
