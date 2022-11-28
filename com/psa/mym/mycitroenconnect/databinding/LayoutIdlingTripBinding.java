package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutIdlingTripBinding implements ViewBinding {
    @NonNull
    public final AppCompatTextView idleTripDateTime;
    @NonNull
    public final AppCompatTextView idlingLoss;
    @NonNull
    public final View idlingTripDivider;
    @NonNull
    public final LinearLayout llRow1;
    @NonNull
    public final LinearLayout llRow2;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvIdleTripCount;
    @NonNull
    public final AppCompatTextView tvIdlingLossValue;

    private LayoutIdlingTripBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull View view, @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4) {
        this.rootView = constraintLayout;
        this.idleTripDateTime = appCompatTextView;
        this.idlingLoss = appCompatTextView2;
        this.idlingTripDivider = view;
        this.llRow1 = linearLayout;
        this.llRow2 = linearLayout2;
        this.tvIdleTripCount = appCompatTextView3;
        this.tvIdlingLossValue = appCompatTextView4;
    }

    @NonNull
    public static LayoutIdlingTripBinding bind(@NonNull View view) {
        int i2 = R.id.idleTripDateTime;
        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.idleTripDateTime);
        if (appCompatTextView != null) {
            i2 = R.id.idlingLoss;
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.idlingLoss);
            if (appCompatTextView2 != null) {
                i2 = R.id.idlingTripDivider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.idlingTripDivider);
                if (findChildViewById != null) {
                    i2 = R.id.llRow1;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.llRow1);
                    if (linearLayout != null) {
                        i2 = R.id.llRow2;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.llRow2);
                        if (linearLayout2 != null) {
                            i2 = R.id.tvIdleTripCount;
                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvIdleTripCount);
                            if (appCompatTextView3 != null) {
                                i2 = R.id.tvIdlingLossValue;
                                AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvIdlingLossValue);
                                if (appCompatTextView4 != null) {
                                    return new LayoutIdlingTripBinding((ConstraintLayout) view, appCompatTextView, appCompatTextView2, findChildViewById, linearLayout, linearLayout2, appCompatTextView3, appCompatTextView4);
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
    public static LayoutIdlingTripBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutIdlingTripBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_idling_trip, viewGroup, false);
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
