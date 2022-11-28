package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutTripCardBinding implements ViewBinding {
    @NonNull
    public final CardView layouTripCard;
    @NonNull
    private final CardView rootView;
    @NonNull
    public final AppCompatImageView tvCardIcon;
    @NonNull
    public final AppCompatTextView tvCardTitle;
    @NonNull
    public final AppCompatTextView tvCardUnit;
    @NonNull
    public final AppCompatTextView tvCardValue;
    @NonNull
    public final LinearLayout valueLayout;

    private LayoutTripCardBinding(@NonNull CardView cardView, @NonNull CardView cardView2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull LinearLayout linearLayout) {
        this.rootView = cardView;
        this.layouTripCard = cardView2;
        this.tvCardIcon = appCompatImageView;
        this.tvCardTitle = appCompatTextView;
        this.tvCardUnit = appCompatTextView2;
        this.tvCardValue = appCompatTextView3;
        this.valueLayout = linearLayout;
    }

    @NonNull
    public static LayoutTripCardBinding bind(@NonNull View view) {
        CardView cardView = (CardView) view;
        int i2 = R.id.tvCardIcon;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvCardIcon);
        if (appCompatImageView != null) {
            i2 = R.id.tvCardTitle;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCardTitle);
            if (appCompatTextView != null) {
                i2 = R.id.tvCardUnit;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCardUnit);
                if (appCompatTextView2 != null) {
                    i2 = R.id.tvCardValue;
                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCardValue);
                    if (appCompatTextView3 != null) {
                        i2 = R.id.valueLayout;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.valueLayout);
                        if (linearLayout != null) {
                            return new LayoutTripCardBinding(cardView, cardView, appCompatImageView, appCompatTextView, appCompatTextView2, appCompatTextView3, linearLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static LayoutTripCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutTripCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_trip_card, viewGroup, false);
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
