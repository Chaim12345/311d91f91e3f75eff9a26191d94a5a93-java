package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivitySetNewPinBinding implements ViewBinding {
    @NonNull
    public final PinView edtConfirmPin;
    @NonNull
    public final PinView edtSetNewPin;
    @NonNull
    public final AppCompatImageView ivLogo;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatImageButton setNewPinBtnBack;
    @NonNull
    public final AppCompatButton setNewPinBtnVerify;
    @NonNull
    public final TextView tvConfirmPin;
    @NonNull
    public final AppCompatTextView tvSetNewPin;
    @NonNull
    public final TextView tvTitle;
    @NonNull
    public final TextInputLayout txtConfirmPin;
    @NonNull
    public final TextInputLayout txtSetNewPin;

    private ActivitySetNewPinBinding(@NonNull ConstraintLayout constraintLayout, @NonNull PinView pinView, @NonNull PinView pinView2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageButton appCompatImageButton, @NonNull AppCompatButton appCompatButton, @NonNull TextView textView, @NonNull AppCompatTextView appCompatTextView, @NonNull TextView textView2, @NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2) {
        this.rootView = constraintLayout;
        this.edtConfirmPin = pinView;
        this.edtSetNewPin = pinView2;
        this.ivLogo = appCompatImageView;
        this.setNewPinBtnBack = appCompatImageButton;
        this.setNewPinBtnVerify = appCompatButton;
        this.tvConfirmPin = textView;
        this.tvSetNewPin = appCompatTextView;
        this.tvTitle = textView2;
        this.txtConfirmPin = textInputLayout;
        this.txtSetNewPin = textInputLayout2;
    }

    @NonNull
    public static ActivitySetNewPinBinding bind(@NonNull View view) {
        int i2 = R.id.edtConfirmPin;
        PinView pinView = (PinView) ViewBindings.findChildViewById(view, R.id.edtConfirmPin);
        if (pinView != null) {
            i2 = R.id.edtSetNewPin;
            PinView pinView2 = (PinView) ViewBindings.findChildViewById(view, R.id.edtSetNewPin);
            if (pinView2 != null) {
                i2 = R.id.ivLogo;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivLogo);
                if (appCompatImageView != null) {
                    i2 = R.id.setNewPinBtnBack;
                    AppCompatImageButton appCompatImageButton = (AppCompatImageButton) ViewBindings.findChildViewById(view, R.id.setNewPinBtnBack);
                    if (appCompatImageButton != null) {
                        i2 = R.id.setNewPinBtnVerify;
                        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.setNewPinBtnVerify);
                        if (appCompatButton != null) {
                            i2 = R.id.tvConfirmPin;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvConfirmPin);
                            if (textView != null) {
                                i2 = R.id.tvSetNewPin;
                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSetNewPin);
                                if (appCompatTextView != null) {
                                    i2 = R.id.tvTitle;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvTitle);
                                    if (textView2 != null) {
                                        i2 = R.id.txtConfirmPin;
                                        TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtConfirmPin);
                                        if (textInputLayout != null) {
                                            i2 = R.id.txtSetNewPin;
                                            TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtSetNewPin);
                                            if (textInputLayout2 != null) {
                                                return new ActivitySetNewPinBinding((ConstraintLayout) view, pinView, pinView2, appCompatImageView, appCompatImageButton, appCompatButton, textView, appCompatTextView, textView2, textInputLayout, textInputLayout2);
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
    public static ActivitySetNewPinBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivitySetNewPinBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_set_new_pin, viewGroup, false);
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
