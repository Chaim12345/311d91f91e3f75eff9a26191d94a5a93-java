package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityConfirmCarDetailsBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnCarConfirm;
    @NonNull
    public final TextInputEditText edMobileNo;
    @NonNull
    public final TextInputEditText edModel;
    @NonNull
    public final TextInputEditText edRegistrationNo;
    @NonNull
    public final AppCompatImageView ivCarImage;
    @NonNull
    public final LinearLayoutCompat layoutCnfrmCarBtn;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final TextView tvAddCnfrmCarTitle;
    @NonNull
    public final TextInputLayout txtLayoutMobileNo;
    @NonNull
    public final TextInputLayout txtLayoutModel;
    @NonNull
    public final TextInputLayout txtLayoutRegistrationNo;

    private ActivityConfirmCarDetailsBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull TextInputEditText textInputEditText, @NonNull TextInputEditText textInputEditText2, @NonNull TextInputEditText textInputEditText3, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull TextView textView, @NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2, @NonNull TextInputLayout textInputLayout3) {
        this.rootView = constraintLayout;
        this.btnCarConfirm = appCompatButton;
        this.edMobileNo = textInputEditText;
        this.edModel = textInputEditText2;
        this.edRegistrationNo = textInputEditText3;
        this.ivCarImage = appCompatImageView;
        this.layoutCnfrmCarBtn = linearLayoutCompat;
        this.tvAddCnfrmCarTitle = textView;
        this.txtLayoutMobileNo = textInputLayout;
        this.txtLayoutModel = textInputLayout2;
        this.txtLayoutRegistrationNo = textInputLayout3;
    }

    @NonNull
    public static ActivityConfirmCarDetailsBinding bind(@NonNull View view) {
        int i2 = R.id.btnCarConfirm;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnCarConfirm);
        if (appCompatButton != null) {
            i2 = R.id.edMobileNo;
            TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edMobileNo);
            if (textInputEditText != null) {
                i2 = R.id.edModel;
                TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edModel);
                if (textInputEditText2 != null) {
                    i2 = R.id.edRegistrationNo;
                    TextInputEditText textInputEditText3 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edRegistrationNo);
                    if (textInputEditText3 != null) {
                        i2 = R.id.ivCarImage;
                        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCarImage);
                        if (appCompatImageView != null) {
                            i2 = R.id.layoutCnfrmCarBtn;
                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutCnfrmCarBtn);
                            if (linearLayoutCompat != null) {
                                i2 = R.id.tvAddCnfrmCarTitle;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvAddCnfrmCarTitle);
                                if (textView != null) {
                                    i2 = R.id.txtLayoutMobileNo;
                                    TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutMobileNo);
                                    if (textInputLayout != null) {
                                        i2 = R.id.txtLayoutModel;
                                        TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutModel);
                                        if (textInputLayout2 != null) {
                                            i2 = R.id.txtLayoutRegistrationNo;
                                            TextInputLayout textInputLayout3 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutRegistrationNo);
                                            if (textInputLayout3 != null) {
                                                return new ActivityConfirmCarDetailsBinding((ConstraintLayout) view, appCompatButton, textInputEditText, textInputEditText2, textInputEditText3, appCompatImageView, linearLayoutCompat, textView, textInputLayout, textInputLayout2, textInputLayout3);
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
    public static ActivityConfirmCarDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityConfirmCarDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_confirm_car_details, viewGroup, false);
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
