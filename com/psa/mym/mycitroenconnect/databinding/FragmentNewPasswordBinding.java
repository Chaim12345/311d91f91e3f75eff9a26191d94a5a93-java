package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
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
public final class FragmentNewPasswordBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnPasswordConfirm;
    @NonNull
    public final LayoutPasswordErrorBinding confirmPasswordError;
    @NonNull
    public final TextInputEditText edConfirmPassword;
    @NonNull
    public final TextInputEditText edNewPassword;
    @NonNull
    public final LayoutPasswordErrorBinding newPasswordError;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final TextView tvEnterConfirmPassword;
    @NonNull
    public final AppCompatTextView tvEnterNewPassword;
    @NonNull
    public final AppCompatTextView tvSetNewPasswordTitle;
    @NonNull
    public final TextInputLayout txtLayoutConfirmPassword;
    @NonNull
    public final TextInputLayout txtLayoutNewPassword;

    private FragmentNewPasswordBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull LayoutPasswordErrorBinding layoutPasswordErrorBinding, @NonNull TextInputEditText textInputEditText, @NonNull TextInputEditText textInputEditText2, @NonNull LayoutPasswordErrorBinding layoutPasswordErrorBinding2, @NonNull TextView textView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2) {
        this.rootView = frameLayout;
        this.btnPasswordConfirm = appCompatButton;
        this.confirmPasswordError = layoutPasswordErrorBinding;
        this.edConfirmPassword = textInputEditText;
        this.edNewPassword = textInputEditText2;
        this.newPasswordError = layoutPasswordErrorBinding2;
        this.tvEnterConfirmPassword = textView;
        this.tvEnterNewPassword = appCompatTextView;
        this.tvSetNewPasswordTitle = appCompatTextView2;
        this.txtLayoutConfirmPassword = textInputLayout;
        this.txtLayoutNewPassword = textInputLayout2;
    }

    @NonNull
    public static FragmentNewPasswordBinding bind(@NonNull View view) {
        int i2 = R.id.btnPasswordConfirm;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnPasswordConfirm);
        if (appCompatButton != null) {
            i2 = R.id.confirm_password_error;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.confirm_password_error);
            if (findChildViewById != null) {
                LayoutPasswordErrorBinding bind = LayoutPasswordErrorBinding.bind(findChildViewById);
                i2 = R.id.edConfirmPassword;
                TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edConfirmPassword);
                if (textInputEditText != null) {
                    i2 = R.id.edNewPassword;
                    TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edNewPassword);
                    if (textInputEditText2 != null) {
                        i2 = R.id.new_password_error;
                        View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.new_password_error);
                        if (findChildViewById2 != null) {
                            LayoutPasswordErrorBinding bind2 = LayoutPasswordErrorBinding.bind(findChildViewById2);
                            i2 = R.id.tvEnterConfirmPassword;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvEnterConfirmPassword);
                            if (textView != null) {
                                i2 = R.id.tvEnterNewPassword;
                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEnterNewPassword);
                                if (appCompatTextView != null) {
                                    i2 = R.id.tvSetNewPasswordTitle;
                                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSetNewPasswordTitle);
                                    if (appCompatTextView2 != null) {
                                        i2 = R.id.txtLayoutConfirmPassword;
                                        TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutConfirmPassword);
                                        if (textInputLayout != null) {
                                            i2 = R.id.txtLayoutNewPassword;
                                            TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutNewPassword);
                                            if (textInputLayout2 != null) {
                                                return new FragmentNewPasswordBinding((FrameLayout) view, appCompatButton, bind, textInputEditText, textInputEditText2, bind2, textView, appCompatTextView, appCompatTextView2, textInputLayout, textInputLayout2);
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
    public static FragmentNewPasswordBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentNewPasswordBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_new_password, viewGroup, false);
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
