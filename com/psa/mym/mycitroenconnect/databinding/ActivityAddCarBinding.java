package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityAddCarBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnAdd;
    @NonNull
    public final TextInputEditText edVinNumber;
    @NonNull
    public final AppCompatImageView ivAddCarBack;
    @NonNull
    public final AppCompatImageView ivVinQrScan;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final TextView tvAddCarTitle;
    @NonNull
    public final TextView tvAddVehicleSubTitle;
    @NonNull
    public final TextView tvQrSubTitle;
    @NonNull
    public final AppCompatTextView tvScanTitle;
    @NonNull
    public final TextInputLayout txtLayoutVinNumber;

    private ActivityAddCarBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull TextInputEditText textInputEditText, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull TextView textView, @NonNull TextView textView2, @NonNull TextView textView3, @NonNull AppCompatTextView appCompatTextView, @NonNull TextInputLayout textInputLayout) {
        this.rootView = constraintLayout;
        this.btnAdd = appCompatButton;
        this.edVinNumber = textInputEditText;
        this.ivAddCarBack = appCompatImageView;
        this.ivVinQrScan = appCompatImageView2;
        this.tvAddCarTitle = textView;
        this.tvAddVehicleSubTitle = textView2;
        this.tvQrSubTitle = textView3;
        this.tvScanTitle = appCompatTextView;
        this.txtLayoutVinNumber = textInputLayout;
    }

    @NonNull
    public static ActivityAddCarBinding bind(@NonNull View view) {
        int i2 = R.id.btnAdd;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnAdd);
        if (appCompatButton != null) {
            i2 = R.id.edVinNumber;
            TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edVinNumber);
            if (textInputEditText != null) {
                i2 = R.id.ivAddCarBack;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivAddCarBack);
                if (appCompatImageView != null) {
                    i2 = R.id.ivVinQrScan;
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivVinQrScan);
                    if (appCompatImageView2 != null) {
                        i2 = R.id.tvAddCarTitle;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvAddCarTitle);
                        if (textView != null) {
                            i2 = R.id.tvAddVehicleSubTitle;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvAddVehicleSubTitle);
                            if (textView2 != null) {
                                i2 = R.id.tvQrSubTitle;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tvQrSubTitle);
                                if (textView3 != null) {
                                    i2 = R.id.tvScanTitle;
                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvScanTitle);
                                    if (appCompatTextView != null) {
                                        i2 = R.id.txtLayoutVinNumber;
                                        TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutVinNumber);
                                        if (textInputLayout != null) {
                                            return new ActivityAddCarBinding((ConstraintLayout) view, appCompatButton, textInputEditText, appCompatImageView, appCompatImageView2, textView, textView2, textView3, appCompatTextView, textInputLayout);
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
    public static ActivityAddCarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityAddCarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_add_car, viewGroup, false);
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
