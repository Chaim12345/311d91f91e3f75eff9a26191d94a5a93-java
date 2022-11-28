package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentChangeUserPasswordBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnCancel;
    @NonNull
    public final AppCompatButton btnSubmit;
    @NonNull
    public final LayoutPasswordErrorBinding confirmPasswordError;
    @NonNull
    public final TextInputEditText edConfirmPassword;
    @NonNull
    public final TextInputEditText edCurrentPassword;
    @NonNull
    public final TextInputEditText edNewPassword;
    @NonNull
    public final LayoutPasswordErrorBinding newPasswordError;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvChangePassword;
    @NonNull
    public final AppCompatTextView tvEnterConfirmPassword;
    @NonNull
    public final AppCompatTextView tvEnterCurrentPassword;
    @NonNull
    public final AppCompatTextView tvEnterNewPassword;
    @NonNull
    public final TextInputLayout txtLayoutConfirmPassword;
    @NonNull
    public final TextInputLayout txtLayoutCurrentPassword;
    @NonNull
    public final TextInputLayout txtLayoutNewPassword;

    private FragmentChangeUserPasswordBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull LayoutPasswordErrorBinding layoutPasswordErrorBinding, @NonNull TextInputEditText textInputEditText, @NonNull TextInputEditText textInputEditText2, @NonNull TextInputEditText textInputEditText3, @NonNull LayoutPasswordErrorBinding layoutPasswordErrorBinding2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2, @NonNull TextInputLayout textInputLayout3) {
        this.rootView = frameLayout;
        this.btnCancel = appCompatButton;
        this.btnSubmit = appCompatButton2;
        this.confirmPasswordError = layoutPasswordErrorBinding;
        this.edConfirmPassword = textInputEditText;
        this.edCurrentPassword = textInputEditText2;
        this.edNewPassword = textInputEditText3;
        this.newPasswordError = layoutPasswordErrorBinding2;
        this.tvChangePassword = appCompatTextView;
        this.tvEnterConfirmPassword = appCompatTextView2;
        this.tvEnterCurrentPassword = appCompatTextView3;
        this.tvEnterNewPassword = appCompatTextView4;
        this.txtLayoutConfirmPassword = textInputLayout;
        this.txtLayoutCurrentPassword = textInputLayout2;
        this.txtLayoutNewPassword = textInputLayout3;
    }

    @NonNull
    public static FragmentChangeUserPasswordBinding bind(@NonNull View view) {
        int i2 = R.id.btnCancel;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnCancel);
        if (appCompatButton != null) {
            i2 = R.id.btnSubmit;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSubmit);
            if (appCompatButton2 != null) {
                i2 = R.id.confirm_password_error;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.confirm_password_error);
                if (findChildViewById != null) {
                    LayoutPasswordErrorBinding bind = LayoutPasswordErrorBinding.bind(findChildViewById);
                    i2 = R.id.edConfirmPassword;
                    TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edConfirmPassword);
                    if (textInputEditText != null) {
                        i2 = R.id.edCurrentPassword;
                        TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edCurrentPassword);
                        if (textInputEditText2 != null) {
                            i2 = R.id.edNewPassword;
                            TextInputEditText textInputEditText3 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edNewPassword);
                            if (textInputEditText3 != null) {
                                i2 = R.id.new_password_error;
                                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.new_password_error);
                                if (findChildViewById2 != null) {
                                    LayoutPasswordErrorBinding bind2 = LayoutPasswordErrorBinding.bind(findChildViewById2);
                                    i2 = R.id.tvChangePassword;
                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChangePassword);
                                    if (appCompatTextView != null) {
                                        i2 = R.id.tvEnterConfirmPassword;
                                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEnterConfirmPassword);
                                        if (appCompatTextView2 != null) {
                                            i2 = R.id.tvEnterCurrentPassword;
                                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEnterCurrentPassword);
                                            if (appCompatTextView3 != null) {
                                                i2 = R.id.tvEnterNewPassword;
                                                AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEnterNewPassword);
                                                if (appCompatTextView4 != null) {
                                                    i2 = R.id.txtLayoutConfirmPassword;
                                                    TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutConfirmPassword);
                                                    if (textInputLayout != null) {
                                                        i2 = R.id.txtLayoutCurrentPassword;
                                                        TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutCurrentPassword);
                                                        if (textInputLayout2 != null) {
                                                            i2 = R.id.txtLayoutNewPassword;
                                                            TextInputLayout textInputLayout3 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutNewPassword);
                                                            if (textInputLayout3 != null) {
                                                                return new FragmentChangeUserPasswordBinding((FrameLayout) view, appCompatButton, appCompatButton2, bind, textInputEditText, textInputEditText2, textInputEditText3, bind2, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, textInputLayout, textInputLayout2, textInputLayout3);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentChangeUserPasswordBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentChangeUserPasswordBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_change_user_password, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
