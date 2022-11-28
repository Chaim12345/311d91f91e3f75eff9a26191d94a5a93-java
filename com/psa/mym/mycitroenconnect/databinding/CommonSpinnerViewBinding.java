package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.psa.mym.mycitroenconnect.views.CustomSpinner;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CommonSpinnerViewBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivSpinner;
    @NonNull
    private final MaterialCardView rootView;
    @NonNull
    public final CustomSpinner spinner;

    private CommonSpinnerViewBinding(@NonNull MaterialCardView materialCardView, @NonNull AppCompatImageView appCompatImageView, @NonNull CustomSpinner customSpinner) {
        this.rootView = materialCardView;
        this.ivSpinner = appCompatImageView;
        this.spinner = customSpinner;
    }

    @NonNull
    public static CommonSpinnerViewBinding bind(@NonNull View view) {
        int i2 = R.id.ivSpinner;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivSpinner);
        if (appCompatImageView != null) {
            i2 = R.id.spinner;
            CustomSpinner customSpinner = (CustomSpinner) ViewBindings.findChildViewById(view, R.id.spinner);
            if (customSpinner != null) {
                return new CommonSpinnerViewBinding((MaterialCardView) view, appCompatImageView, customSpinner);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static CommonSpinnerViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CommonSpinnerViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.common_spinner_view, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public MaterialCardView getRoot() {
        return this.rootView;
    }
}
