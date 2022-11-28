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
public final class ItemViewMyVehicleHealthBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivIcon;
    @NonNull
    public final AppCompatImageView ivIconBackground;
    @NonNull
    private final CardView rootView;
    @NonNull
    public final AppCompatTextView tvStatus;
    @NonNull
    public final AppCompatTextView tvStatusLabel;

    private ItemViewMyVehicleHealthBinding(@NonNull CardView cardView, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = cardView;
        this.ivIcon = appCompatImageView;
        this.ivIconBackground = appCompatImageView2;
        this.tvStatus = appCompatTextView;
        this.tvStatusLabel = appCompatTextView2;
    }

    @NonNull
    public static ItemViewMyVehicleHealthBinding bind(@NonNull View view) {
        int i2 = R.id.ivIcon;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivIcon);
        if (appCompatImageView != null) {
            i2 = R.id.ivIconBackground;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivIconBackground);
            if (appCompatImageView2 != null) {
                i2 = R.id.tvStatus;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvStatus);
                if (appCompatTextView != null) {
                    i2 = R.id.tvStatusLabel;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvStatusLabel);
                    if (appCompatTextView2 != null) {
                        return new ItemViewMyVehicleHealthBinding((CardView) view, appCompatImageView, appCompatImageView2, appCompatTextView, appCompatTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewMyVehicleHealthBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewMyVehicleHealthBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_my_vehicle_health, viewGroup, false);
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
