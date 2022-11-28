package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentTripSummaryBinding implements ViewBinding {
    @NonNull
    public final ConstraintLayout clParent;
    @NonNull
    public final AppCompatImageView ivRefresh;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final RecyclerView tripSumRV;
    @NonNull
    public final TextView tvNoTrip;
    @NonNull
    public final AppCompatTextView tvTripCount;

    private FragmentTripSummaryBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout2, @NonNull AppCompatImageView appCompatImageView, @NonNull RecyclerView recyclerView, @NonNull TextView textView, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.clParent = constraintLayout2;
        this.ivRefresh = appCompatImageView;
        this.tripSumRV = recyclerView;
        this.tvNoTrip = textView;
        this.tvTripCount = appCompatTextView;
    }

    @NonNull
    public static FragmentTripSummaryBinding bind(@NonNull View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i2 = R.id.ivRefresh;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivRefresh);
        if (appCompatImageView != null) {
            i2 = R.id.tripSumRV;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.tripSumRV);
            if (recyclerView != null) {
                i2 = R.id.tvNoTrip;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvNoTrip);
                if (textView != null) {
                    i2 = R.id.tvTripCount;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTripCount);
                    if (appCompatTextView != null) {
                        return new FragmentTripSummaryBinding(constraintLayout, constraintLayout, appCompatImageView, recyclerView, textView, appCompatTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentTripSummaryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentTripSummaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_trip_summary, viewGroup, false);
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
