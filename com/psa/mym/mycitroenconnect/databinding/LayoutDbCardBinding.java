package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutDbCardBinding implements ViewBinding {
    @NonNull
    public final CardView layouTripCard;
    @NonNull
    private final CardView rootView;
    @NonNull
    public final AppCompatImageView tvDbCardIcon;
    @NonNull
    public final AppCompatTextView tvDbCardLabel;
    @NonNull
    public final AppCompatTextView tvDbCardValue;

    private LayoutDbCardBinding(@NonNull CardView cardView, @NonNull CardView cardView2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = cardView;
        this.layouTripCard = cardView2;
        this.tvDbCardIcon = appCompatImageView;
        this.tvDbCardLabel = appCompatTextView;
        this.tvDbCardValue = appCompatTextView2;
    }

    @NonNull
    public static LayoutDbCardBinding bind(@NonNull View view) {
        CardView cardView = (CardView) view;
        int i2 = R.id.tvDbCardIcon;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvDbCardIcon);
        if (appCompatImageView != null) {
            i2 = R.id.tvDbCardLabel;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDbCardLabel);
            if (appCompatTextView != null) {
                i2 = R.id.tvDbCardValue;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDbCardValue);
                if (appCompatTextView2 != null) {
                    return new LayoutDbCardBinding(cardView, cardView, appCompatImageView, appCompatTextView, appCompatTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static LayoutDbCardBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutDbCardBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_db_card, viewGroup, false);
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
