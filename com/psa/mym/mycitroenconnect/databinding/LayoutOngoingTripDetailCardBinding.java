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
public final class LayoutOngoingTripDetailCardBinding implements ViewBinding {
    @NonNull
    public final CardView layoutOngoingTripCard;
    @NonNull
    public final LinearLayout linOngnD;
    @NonNull
    public final LinearLayout linOngnEnd;
    @NonNull
    private final CardView rootView;
    @NonNull
    public final AppCompatTextView tvOngnTripDistanceCovered;
    @NonNull
    public final AppCompatTextView tvOngnTripDuration;
    @NonNull
    public final AppCompatTextView tvOngnTripSummarySource;

    private LayoutOngoingTripDetailCardBinding(@NonNull CardView cardView, @NonNull CardView cardView2, @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3) {
        this.rootView = cardView;
        this.layoutOngoingTripCard = cardView2;
        this.linOngnD = linearLayout;
        this.linOngnEnd = linearLayout2;
        this.tvOngnTripDistanceCovered = appCompatTextView;
        this.tvOngnTripDuration = appCompatTextView2;
        this.tvOngnTripSummarySource = appCompatTextView3;
    }

    @NonNull
    public static LayoutOngoingTripDetailCardBinding bind(@NonNull View view) {
        CardView cardView = (CardView) view;
        int i2 = R.id.linOngnD;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linOngnD);
        if (linearLayout != null) {
            i2 = R.id.linOngnEnd;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linOngnEnd);
            if (linearLayout2 != null) {
                i2 = R.id.tvOngnTripDistanceCovered;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOngnTripDistanceCovered);
                if (appCompatTextView != null) {
                    i2 = R.id.tvOngnTripDuration;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOngnTripDuration);
                    if (appCompatTextView2 != null) {
                        i2 = R.id.tvOngnTripSummarySource;
                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOngnTripSummarySource);
                        if (appCompatTextView3 != null) {
                            return new LayoutOngoingTripDetailCardBinding(cardView, cardView, linearLayout, linearLayout2, appCompatTextView, appCompatTextView2, appCompatTextView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static LayoutOngoingTripDetailCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutOngoingTripDetailCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_ongoing_trip_detail_card, viewGroup, false);
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
