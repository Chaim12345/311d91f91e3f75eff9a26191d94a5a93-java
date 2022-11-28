package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutTripDetailCardBinding implements ViewBinding {
    @NonNull
    public final CardView layouTripCard;
    @NonNull
    public final LinearLayout linD;
    @NonNull
    public final LinearLayout linEnd;
    @NonNull
    private final CardView rootView;
    @NonNull
    public final AppCompatTextView tvTripDistanceCovered;
    @NonNull
    public final AppCompatTextView tvTripDuration;
    @NonNull
    public final AppCompatTextView tvTripSummaryDest;
    @NonNull
    public final AppCompatTextView tvTripSummarySource;

    private LayoutTripDetailCardBinding(@NonNull CardView cardView, @NonNull CardView cardView2, @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4) {
        this.rootView = cardView;
        this.layouTripCard = cardView2;
        this.linD = linearLayout;
        this.linEnd = linearLayout2;
        this.tvTripDistanceCovered = appCompatTextView;
        this.tvTripDuration = appCompatTextView2;
        this.tvTripSummaryDest = appCompatTextView3;
        this.tvTripSummarySource = appCompatTextView4;
    }

    @NonNull
    public static LayoutTripDetailCardBinding bind(@NonNull View view) {
        CardView cardView = (CardView) view;
        int i2 = R.id.linD;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linD);
        if (linearLayout != null) {
            i2 = R.id.linEnd;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linEnd);
            if (linearLayout2 != null) {
                i2 = R.id.tvTripDistanceCovered;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTripDistanceCovered);
                if (appCompatTextView != null) {
                    i2 = R.id.tvTripDuration;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTripDuration);
                    if (appCompatTextView2 != null) {
                        i2 = R.id.tvTripSummaryDest;
                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTripSummaryDest);
                        if (appCompatTextView3 != null) {
                            i2 = R.id.tvTripSummarySource;
                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTripSummarySource);
                            if (appCompatTextView4 != null) {
                                return new LayoutTripDetailCardBinding(cardView, cardView, linearLayout, linearLayout2, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static LayoutTripDetailCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutTripDetailCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_trip_detail_card, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public CardView getRoot() {
        return this.rootView;
    }
}
