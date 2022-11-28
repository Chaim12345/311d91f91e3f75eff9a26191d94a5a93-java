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
public final class CellOngoingTripBinding implements ViewBinding {
    @NonNull
    public final CardView cvTripCard;
    @NonNull
    public final View divider;
    @NonNull
    public final LinearLayout linD;
    @NonNull
    public final LinearLayout linEnd;
    @NonNull
    private final CardView rootView;
    @NonNull
    public final AppCompatTextView tvOngoingIdlingChip;
    @NonNull
    public final AppCompatTextView tvOngoingRunningChip;
    @NonNull
    public final AppCompatTextView tvOngoingTripChip;
    @NonNull
    public final AppCompatTextView tvTripDistanceCovered;
    @NonNull
    public final AppCompatTextView tvTripDuration;
    @NonNull
    public final AppCompatTextView tvTripSummarySource;
    @NonNull
    public final AppCompatTextView tvTripTitle;

    private CellOngoingTripBinding(@NonNull CardView cardView, @NonNull CardView cardView2, @NonNull View view, @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull AppCompatTextView appCompatTextView7) {
        this.rootView = cardView;
        this.cvTripCard = cardView2;
        this.divider = view;
        this.linD = linearLayout;
        this.linEnd = linearLayout2;
        this.tvOngoingIdlingChip = appCompatTextView;
        this.tvOngoingRunningChip = appCompatTextView2;
        this.tvOngoingTripChip = appCompatTextView3;
        this.tvTripDistanceCovered = appCompatTextView4;
        this.tvTripDuration = appCompatTextView5;
        this.tvTripSummarySource = appCompatTextView6;
        this.tvTripTitle = appCompatTextView7;
    }

    @NonNull
    public static CellOngoingTripBinding bind(@NonNull View view) {
        CardView cardView = (CardView) view;
        int i2 = R.id.divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (findChildViewById != null) {
            i2 = R.id.linD;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linD);
            if (linearLayout != null) {
                i2 = R.id.linEnd;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linEnd);
                if (linearLayout2 != null) {
                    i2 = R.id.tvOngoingIdlingChip;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOngoingIdlingChip);
                    if (appCompatTextView != null) {
                        i2 = R.id.tvOngoingRunningChip;
                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOngoingRunningChip);
                        if (appCompatTextView2 != null) {
                            i2 = R.id.tvOngoingTripChip;
                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOngoingTripChip);
                            if (appCompatTextView3 != null) {
                                i2 = R.id.tvTripDistanceCovered;
                                AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTripDistanceCovered);
                                if (appCompatTextView4 != null) {
                                    i2 = R.id.tvTripDuration;
                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTripDuration);
                                    if (appCompatTextView5 != null) {
                                        i2 = R.id.tvTripSummarySource;
                                        AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTripSummarySource);
                                        if (appCompatTextView6 != null) {
                                            i2 = R.id.tvTripTitle;
                                            AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTripTitle);
                                            if (appCompatTextView7 != null) {
                                                return new CellOngoingTripBinding(cardView, cardView, findChildViewById, linearLayout, linearLayout2, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7);
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
    public static CellOngoingTripBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CellOngoingTripBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.cell_ongoing_trip, viewGroup, false);
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
