package com.psa.mym.mycitroenconnect.controller.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.fragments.login.VerifyOtpFragment;
import com.psa.mym.mycitroenconnect.databinding.FragmentChangeUserPasswordBinding;
import com.psa.mym.mycitroenconnect.model.onboarding.ChangePassRequest;
import com.psa.mym.mycitroenconnect.model.onboarding.ChangePasswordResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterErrorResponse;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Marker;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ChangeUserPasswordFragment extends BusBaseFragment implements View.OnClickListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private FragmentChangeUserPasswordBinding binding;
    private boolean isPasswordValid;

    /* loaded from: classes3.dex */
    public class GenericTextWatcher implements TextWatcher {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ChangeUserPasswordFragment f10501a;
        @NotNull
        private final View view;

        public GenericTextWatcher(@NotNull ChangeUserPasswordFragment changeUserPasswordFragment, View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.f10501a = changeUserPasswordFragment;
            this.view = view;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(@NotNull Editable editable) {
            TextInputLayout textInputLayout;
            ChangeUserPasswordFragment changeUserPasswordFragment;
            TextInputEditText textInputEditText;
            Intrinsics.checkNotNullParameter(editable, "editable");
            int id = this.view.getId();
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding = this.f10501a.binding;
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding2 = null;
            if (fragmentChangeUserPasswordBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentChangeUserPasswordBinding = null;
            }
            if (id == fragmentChangeUserPasswordBinding.edCurrentPassword.getId()) {
                FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding3 = this.f10501a.binding;
                if (fragmentChangeUserPasswordBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentChangeUserPasswordBinding3 = null;
                }
                TextInputEditText textInputEditText2 = fragmentChangeUserPasswordBinding3.edCurrentPassword;
                Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.edCurrentPassword");
                boolean z = ExtensionsKt.myText(textInputEditText2).length() > 0;
                FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding4 = this.f10501a.binding;
                if (z) {
                    if (fragmentChangeUserPasswordBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding4 = null;
                    }
                    fragmentChangeUserPasswordBinding4.txtLayoutCurrentPassword.setError(null);
                    return;
                }
                if (fragmentChangeUserPasswordBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding4;
                }
                fragmentChangeUserPasswordBinding2.txtLayoutCurrentPassword.setError(this.f10501a.getString(R.string.enter_current_password_error));
                return;
            }
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding5 = this.f10501a.binding;
            if (fragmentChangeUserPasswordBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentChangeUserPasswordBinding5 = null;
            }
            if (id == fragmentChangeUserPasswordBinding5.edNewPassword.getId()) {
                FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding6 = this.f10501a.binding;
                if (fragmentChangeUserPasswordBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentChangeUserPasswordBinding6 = null;
                }
                if (fragmentChangeUserPasswordBinding6.edNewPassword.isFocused()) {
                    AppUtil.Companion companion = AppUtil.Companion;
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding7 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding7 = null;
                    }
                    TextInputLayout textInputLayout2 = fragmentChangeUserPasswordBinding7.txtLayoutNewPassword;
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding8 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding8 = null;
                    }
                    TextInputEditText textInputEditText3 = fragmentChangeUserPasswordBinding8.edNewPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText3, "binding.edNewPassword");
                    String myText = ExtensionsKt.myText(textInputEditText3);
                    String string = this.f10501a.getString(R.string.enter_new_password_valid_error);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enter_new_password_valid_error)");
                    if (!companion.isValidPassword(textInputLayout2, myText, string)) {
                        this.f10501a.isPasswordValid = false;
                        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding9 = this.f10501a.binding;
                        if (fragmentChangeUserPasswordBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentChangeUserPasswordBinding9 = null;
                        }
                        ConstraintLayout root = fragmentChangeUserPasswordBinding9.newPasswordError.getRoot();
                        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding10 = this.f10501a.binding;
                        if (fragmentChangeUserPasswordBinding10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentChangeUserPasswordBinding10 = null;
                        }
                        TextInputEditText textInputEditText4 = fragmentChangeUserPasswordBinding10.edNewPassword;
                        Intrinsics.checkNotNullExpressionValue(textInputEditText4, "binding.edNewPassword");
                        if (ExtensionsKt.myText(textInputEditText4).length() == 0) {
                            root.setVisibility(8);
                        }
                        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding11 = this.f10501a.binding;
                        if (fragmentChangeUserPasswordBinding11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding11;
                        }
                        textInputLayout = fragmentChangeUserPasswordBinding2.txtLayoutNewPassword;
                        textInputLayout.setErrorIconDrawable(this.f10501a.requireContext().getDrawable(R.drawable.ic_info));
                        return;
                    }
                    this.f10501a.isPasswordValid = true;
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding12 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding12 = null;
                    }
                    fragmentChangeUserPasswordBinding12.txtLayoutNewPassword.setErrorIconDrawable((Drawable) null);
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding13 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding13 = null;
                    }
                    fragmentChangeUserPasswordBinding13.txtLayoutNewPassword.setPasswordVisibilityToggleEnabled(true);
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding14 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding14 = null;
                    }
                    fragmentChangeUserPasswordBinding14.newPasswordError.getRoot().setVisibility(8);
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding15 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding15 = null;
                    }
                    fragmentChangeUserPasswordBinding15.txtLayoutNewPassword.setError(null);
                    changeUserPasswordFragment = this.f10501a;
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding16 = changeUserPasswordFragment.binding;
                    if (fragmentChangeUserPasswordBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding16;
                    }
                    textInputEditText = fragmentChangeUserPasswordBinding2.edNewPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.edNewPassword");
                    changeUserPasswordFragment.setValidInput(textInputEditText, true);
                }
                return;
            }
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding17 = this.f10501a.binding;
            if (fragmentChangeUserPasswordBinding17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentChangeUserPasswordBinding17 = null;
            }
            if (id == fragmentChangeUserPasswordBinding17.edConfirmPassword.getId()) {
                FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding18 = this.f10501a.binding;
                if (fragmentChangeUserPasswordBinding18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentChangeUserPasswordBinding18 = null;
                }
                if (fragmentChangeUserPasswordBinding18.edConfirmPassword.isFocused()) {
                    AppUtil.Companion companion2 = AppUtil.Companion;
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding19 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding19 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding19 = null;
                    }
                    TextInputLayout textInputLayout3 = fragmentChangeUserPasswordBinding19.txtLayoutConfirmPassword;
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding20 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding20 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding20 = null;
                    }
                    TextInputEditText textInputEditText5 = fragmentChangeUserPasswordBinding20.edConfirmPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText5, "binding.edConfirmPassword");
                    String myText2 = ExtensionsKt.myText(textInputEditText5);
                    String string2 = this.f10501a.getString(R.string.enter_new_password_valid_error);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.enter_new_password_valid_error)");
                    if (!companion2.isValidPassword(textInputLayout3, myText2, string2)) {
                        this.f10501a.isPasswordValid = false;
                        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding21 = this.f10501a.binding;
                        if (fragmentChangeUserPasswordBinding21 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentChangeUserPasswordBinding21 = null;
                        }
                        ConstraintLayout root2 = fragmentChangeUserPasswordBinding21.confirmPasswordError.getRoot();
                        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding22 = this.f10501a.binding;
                        if (fragmentChangeUserPasswordBinding22 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentChangeUserPasswordBinding22 = null;
                        }
                        TextInputEditText textInputEditText6 = fragmentChangeUserPasswordBinding22.edConfirmPassword;
                        Intrinsics.checkNotNullExpressionValue(textInputEditText6, "binding.edConfirmPassword");
                        if (ExtensionsKt.myText(textInputEditText6).length() == 0) {
                            root2.setVisibility(8);
                        }
                        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding23 = this.f10501a.binding;
                        if (fragmentChangeUserPasswordBinding23 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding23;
                        }
                        textInputLayout = fragmentChangeUserPasswordBinding2.txtLayoutConfirmPassword;
                        textInputLayout.setErrorIconDrawable(this.f10501a.requireContext().getDrawable(R.drawable.ic_info));
                        return;
                    }
                    this.f10501a.isPasswordValid = true;
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding24 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding24 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding24 = null;
                    }
                    fragmentChangeUserPasswordBinding24.txtLayoutConfirmPassword.setErrorIconDrawable((Drawable) null);
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding25 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding25 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding25 = null;
                    }
                    fragmentChangeUserPasswordBinding25.txtLayoutConfirmPassword.setPasswordVisibilityToggleEnabled(true);
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding26 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding26 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding26 = null;
                    }
                    fragmentChangeUserPasswordBinding26.confirmPasswordError.getRoot().setVisibility(8);
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding27 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding27 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding27 = null;
                    }
                    fragmentChangeUserPasswordBinding27.txtLayoutConfirmPassword.setError(null);
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding28 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding28 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding28 = null;
                    }
                    TextInputEditText textInputEditText7 = fragmentChangeUserPasswordBinding28.edNewPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText7, "binding.edNewPassword");
                    String myText3 = ExtensionsKt.myText(textInputEditText7);
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding29 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding29 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding29 = null;
                    }
                    TextInputEditText textInputEditText8 = fragmentChangeUserPasswordBinding29.edConfirmPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText8, "binding.edConfirmPassword");
                    if (!Intrinsics.areEqual(myText3, ExtensionsKt.myText(textInputEditText8))) {
                        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding30 = this.f10501a.binding;
                        if (fragmentChangeUserPasswordBinding30 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentChangeUserPasswordBinding30 = null;
                        }
                        fragmentChangeUserPasswordBinding30.txtLayoutConfirmPassword.setError(this.f10501a.getString(R.string.new_confirm_password_not_match));
                        ChangeUserPasswordFragment changeUserPasswordFragment2 = this.f10501a;
                        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding31 = changeUserPasswordFragment2.binding;
                        if (fragmentChangeUserPasswordBinding31 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding31;
                        }
                        TextInputEditText textInputEditText9 = fragmentChangeUserPasswordBinding2.edConfirmPassword;
                        Intrinsics.checkNotNullExpressionValue(textInputEditText9, "binding.edConfirmPassword");
                        changeUserPasswordFragment2.setValidInput(textInputEditText9, false);
                        return;
                    }
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding32 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding32 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding32 = null;
                    }
                    fragmentChangeUserPasswordBinding32.txtLayoutConfirmPassword.setError(null);
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding33 = this.f10501a.binding;
                    if (fragmentChangeUserPasswordBinding33 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding33 = null;
                    }
                    fragmentChangeUserPasswordBinding33.txtLayoutNewPassword.setError(null);
                    changeUserPasswordFragment = this.f10501a;
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding34 = changeUserPasswordFragment.binding;
                    if (fragmentChangeUserPasswordBinding34 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding34;
                    }
                    textInputEditText = fragmentChangeUserPasswordBinding2.edConfirmPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.edConfirmPassword");
                    changeUserPasswordFragment.setValidInput(textInputEditText, true);
                }
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(@NotNull CharSequence charSequence, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(charSequence, "charSequence");
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(@NotNull CharSequence charSequence, int i2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(charSequence, "charSequence");
        }
    }

    private final void setListeners() {
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding = this.binding;
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding2 = null;
        if (fragmentChangeUserPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding = null;
        }
        fragmentChangeUserPasswordBinding.btnSubmit.setOnClickListener(this);
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding3 = this.binding;
        if (fragmentChangeUserPasswordBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding3 = null;
        }
        fragmentChangeUserPasswordBinding3.btnCancel.setOnClickListener(this);
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding4 = this.binding;
        if (fragmentChangeUserPasswordBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding4 = null;
        }
        fragmentChangeUserPasswordBinding4.txtLayoutNewPassword.setErrorIconOnClickListener(new View.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChangeUserPasswordFragment.m129setListeners$lambda1(ChangeUserPasswordFragment.this, view);
            }
        });
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding5 = this.binding;
        if (fragmentChangeUserPasswordBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding5 = null;
        }
        fragmentChangeUserPasswordBinding5.txtLayoutConfirmPassword.setErrorIconOnClickListener(new View.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChangeUserPasswordFragment.m130setListeners$lambda2(ChangeUserPasswordFragment.this, view);
            }
        });
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding6 = this.binding;
        if (fragmentChangeUserPasswordBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding6 = null;
        }
        TextInputEditText textInputEditText = fragmentChangeUserPasswordBinding6.edNewPassword;
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding7 = this.binding;
        if (fragmentChangeUserPasswordBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding7 = null;
        }
        TextInputEditText textInputEditText2 = fragmentChangeUserPasswordBinding7.edNewPassword;
        Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.edNewPassword");
        textInputEditText.addTextChangedListener(new GenericTextWatcher(this, textInputEditText2));
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding8 = this.binding;
        if (fragmentChangeUserPasswordBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding8 = null;
        }
        TextInputEditText textInputEditText3 = fragmentChangeUserPasswordBinding8.edConfirmPassword;
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding9 = this.binding;
        if (fragmentChangeUserPasswordBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding9 = null;
        }
        TextInputEditText textInputEditText4 = fragmentChangeUserPasswordBinding9.edConfirmPassword;
        Intrinsics.checkNotNullExpressionValue(textInputEditText4, "binding.edConfirmPassword");
        textInputEditText3.addTextChangedListener(new GenericTextWatcher(this, textInputEditText4));
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding10 = this.binding;
        if (fragmentChangeUserPasswordBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding10 = null;
        }
        TextInputEditText textInputEditText5 = fragmentChangeUserPasswordBinding10.edCurrentPassword;
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding11 = this.binding;
        if (fragmentChangeUserPasswordBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding11;
        }
        TextInputEditText textInputEditText6 = fragmentChangeUserPasswordBinding2.edCurrentPassword;
        Intrinsics.checkNotNullExpressionValue(textInputEditText6, "binding.edCurrentPassword");
        textInputEditText5.addTextChangedListener(new GenericTextWatcher(this, textInputEditText6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setListeners$lambda-1  reason: not valid java name */
    public static final void m129setListeners$lambda1(ChangeUserPasswordFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding = this$0.binding;
        if (fragmentChangeUserPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding = null;
        }
        fragmentChangeUserPasswordBinding.newPasswordError.getRoot().setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setListeners$lambda-2  reason: not valid java name */
    public static final void m130setListeners$lambda2(ChangeUserPasswordFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding = this$0.binding;
        if (fragmentChangeUserPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding = null;
        }
        fragmentChangeUserPasswordBinding.confirmPasswordError.getRoot().setVisibility(0);
    }

    private final int validatePassword() {
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding = this.binding;
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding2 = null;
        if (fragmentChangeUserPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding = null;
        }
        fragmentChangeUserPasswordBinding.txtLayoutCurrentPassword.setError(null);
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding3 = this.binding;
        if (fragmentChangeUserPasswordBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding3 = null;
        }
        fragmentChangeUserPasswordBinding3.txtLayoutNewPassword.setError(null);
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding4 = this.binding;
        if (fragmentChangeUserPasswordBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding4 = null;
        }
        fragmentChangeUserPasswordBinding4.txtLayoutConfirmPassword.setError(null);
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding5 = this.binding;
        if (fragmentChangeUserPasswordBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding5 = null;
        }
        TextInputEditText textInputEditText = fragmentChangeUserPasswordBinding5.edCurrentPassword;
        Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.edCurrentPassword");
        String myText = ExtensionsKt.myText(textInputEditText);
        if (myText == null || myText.length() == 0) {
            return 3;
        }
        AppUtil.Companion companion = AppUtil.Companion;
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding6 = this.binding;
        if (fragmentChangeUserPasswordBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding6 = null;
        }
        TextInputLayout textInputLayout = fragmentChangeUserPasswordBinding6.txtLayoutNewPassword;
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding7 = this.binding;
        if (fragmentChangeUserPasswordBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding7 = null;
        }
        TextInputEditText textInputEditText2 = fragmentChangeUserPasswordBinding7.edNewPassword;
        Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.edNewPassword");
        String myText2 = ExtensionsKt.myText(textInputEditText2);
        String string = getString(R.string.enter_new_password);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enter_new_password)");
        if (companion.isValidPassword(textInputLayout, myText2, string)) {
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding8 = this.binding;
            if (fragmentChangeUserPasswordBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentChangeUserPasswordBinding8 = null;
            }
            TextInputEditText textInputEditText3 = fragmentChangeUserPasswordBinding8.edCurrentPassword;
            Intrinsics.checkNotNullExpressionValue(textInputEditText3, "binding.edCurrentPassword");
            String myText3 = ExtensionsKt.myText(textInputEditText3);
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding9 = this.binding;
            if (fragmentChangeUserPasswordBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentChangeUserPasswordBinding9 = null;
            }
            TextInputEditText textInputEditText4 = fragmentChangeUserPasswordBinding9.edNewPassword;
            Intrinsics.checkNotNullExpressionValue(textInputEditText4, "binding.edNewPassword");
            if (Intrinsics.areEqual(myText3, ExtensionsKt.myText(textInputEditText4))) {
                return 4;
            }
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding10 = this.binding;
            if (fragmentChangeUserPasswordBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentChangeUserPasswordBinding10 = null;
            }
            TextInputEditText textInputEditText5 = fragmentChangeUserPasswordBinding10.edNewPassword;
            Intrinsics.checkNotNullExpressionValue(textInputEditText5, "binding.edNewPassword");
            if (ExtensionsKt.myText(textInputEditText5).length() > 0) {
                FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding11 = this.binding;
                if (fragmentChangeUserPasswordBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentChangeUserPasswordBinding11 = null;
                }
                TextInputEditText textInputEditText6 = fragmentChangeUserPasswordBinding11.edConfirmPassword;
                Intrinsics.checkNotNullExpressionValue(textInputEditText6, "binding.edConfirmPassword");
                if (ExtensionsKt.myText(textInputEditText6).length() > 0) {
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding12 = this.binding;
                    if (fragmentChangeUserPasswordBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentChangeUserPasswordBinding12 = null;
                    }
                    TextInputEditText textInputEditText7 = fragmentChangeUserPasswordBinding12.edNewPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText7, "binding.edNewPassword");
                    String myText4 = ExtensionsKt.myText(textInputEditText7);
                    FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding13 = this.binding;
                    if (fragmentChangeUserPasswordBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding13;
                    }
                    TextInputEditText textInputEditText8 = fragmentChangeUserPasswordBinding2.edConfirmPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText8, "binding.edConfirmPassword");
                    return Intrinsics.areEqual(myText4, ExtensionsKt.myText(textInputEditText8)) ? 0 : -1;
                }
            }
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding14 = this.binding;
            if (fragmentChangeUserPasswordBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding14;
            }
            TextInputEditText textInputEditText9 = fragmentChangeUserPasswordBinding2.edConfirmPassword;
            Intrinsics.checkNotNullExpressionValue(textInputEditText9, "binding.edConfirmPassword");
            return ExtensionsKt.myText(textInputEditText9).length() == 0 ? 2 : -1;
        }
        return 1;
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
    @Nullable
    public View _$_findCachedViewById(int i2) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i2)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @Subscribe
    public final void getMessage(@NotNull ChangePasswordResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getStatusCode() == 200) {
            VerifyOtpFragment verifyOtpFragment = new VerifyOtpFragment();
            Bundle bundle = new Bundle();
            bundle.putString("login", AppConstants.PAGE_MODE_CHANGE_PWD);
            bundle.putString(AppConstants.MOBILENUMBER, event.getUserName());
            bundle.putParcelable(AppConstants.SENDOTPRESPONSE, event);
            verifyOtpFragment.setArguments(bundle);
            AppUtil.Companion companion = AppUtil.Companion;
            FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "requireActivity().supportFragmentManager");
            companion.replaceFragment(supportFragmentManager, verifyOtpFragment);
        }
    }

    @Subscribe
    public final void getMessage(@NotNull RegisterErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, event.getMessage());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        TextInputLayout textInputLayout;
        int i2;
        String replace$default;
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding = this.binding;
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding2 = null;
        if (fragmentChangeUserPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding = null;
        }
        if (!Intrinsics.areEqual(view, fragmentChangeUserPasswordBinding.btnSubmit)) {
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding3 = this.binding;
            if (fragmentChangeUserPasswordBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding3;
            }
            if (Intrinsics.areEqual(view, fragmentChangeUserPasswordBinding2.btnCancel)) {
                requireActivity().onBackPressed();
                return;
            }
            return;
        }
        int validatePassword = validatePassword();
        if (validatePassword == -1) {
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding4 = this.binding;
            if (fragmentChangeUserPasswordBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding4;
            }
            textInputLayout = fragmentChangeUserPasswordBinding2.txtLayoutConfirmPassword;
            i2 = R.string.new_confirm_password_not_match;
        } else if (validatePassword == 0) {
            ChangePassRequest changePassRequest = new ChangePassRequest();
            SharedPref.Companion companion = SharedPref.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            replace$default = StringsKt__StringsJVMKt.replace$default(companion.getMobileNumber(requireActivity), Marker.ANY_NON_NULL_MARKER, "", false, 4, (Object) null);
            changePassRequest.setUserName(replace$default);
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding5 = this.binding;
            if (fragmentChangeUserPasswordBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentChangeUserPasswordBinding5 = null;
            }
            TextInputEditText textInputEditText = fragmentChangeUserPasswordBinding5.edCurrentPassword;
            Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.edCurrentPassword");
            changePassRequest.setCurrentPassword(ExtensionsKt.myText(textInputEditText));
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding6 = this.binding;
            if (fragmentChangeUserPasswordBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding6;
            }
            TextInputEditText textInputEditText2 = fragmentChangeUserPasswordBinding2.edNewPassword;
            Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.edNewPassword");
            changePassRequest.setNewPassword(ExtensionsKt.myText(textInputEditText2));
            OnBoardingService onBoardingService = new OnBoardingService();
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            onBoardingService.changePassword(requireActivity2, changePassRequest);
            return;
        } else if (validatePassword == 1) {
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding7 = this.binding;
            if (fragmentChangeUserPasswordBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding7;
            }
            textInputLayout = fragmentChangeUserPasswordBinding2.txtLayoutNewPassword;
            i2 = R.string.enter_new_password;
        } else if (validatePassword == 2) {
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding8 = this.binding;
            if (fragmentChangeUserPasswordBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding8;
            }
            textInputLayout = fragmentChangeUserPasswordBinding2.txtLayoutConfirmPassword;
            i2 = R.string.enter_confirm_password;
        } else if (validatePassword == 3) {
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding9 = this.binding;
            if (fragmentChangeUserPasswordBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding9;
            }
            textInputLayout = fragmentChangeUserPasswordBinding2.txtLayoutCurrentPassword;
            i2 = R.string.enter_current_password_error;
        } else if (validatePassword != 4) {
            return;
        } else {
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding10 = this.binding;
            if (fragmentChangeUserPasswordBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentChangeUserPasswordBinding2 = fragmentChangeUserPasswordBinding10;
            }
            textInputLayout = fragmentChangeUserPasswordBinding2.txtLayoutCurrentPassword;
            i2 = R.string.same_new_old_password_error;
        }
        textInputLayout.setError(getString(i2));
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentChangeUserPasswordBinding inflate = FragmentChangeUserPasswordBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.binding = inflate;
        setListeners();
        FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding = this.binding;
        if (fragmentChangeUserPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentChangeUserPasswordBinding = null;
        }
        FrameLayout root = fragmentChangeUserPasswordBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setValidInput(@NotNull TextInputEditText view, boolean z) {
        int i2;
        Intrinsics.checkNotNullParameter(view, "view");
        if (z) {
            i2 = R.drawable.ic_vin_verified;
        } else {
            FragmentChangeUserPasswordBinding fragmentChangeUserPasswordBinding = this.binding;
            if (fragmentChangeUserPasswordBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentChangeUserPasswordBinding = null;
            }
            view = fragmentChangeUserPasswordBinding.edConfirmPassword;
            i2 = R.drawable.ic_invalid_vin;
        }
        view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, i2, 0);
    }
}
