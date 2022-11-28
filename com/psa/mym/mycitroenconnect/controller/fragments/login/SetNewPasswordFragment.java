package com.psa.mym.mycitroenconnect.controller.fragments.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.api.body.onboarding.GetTokenBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.RegisterUserPassword;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.AddCarActivity;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.RegistrationActivity;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.PasswordSuccessBottomSheetFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.TermsAndConditionsFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.login.SetNewPasswordFragment;
import com.psa.mym.mycitroenconnect.databinding.FragmentNewPasswordBinding;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.SetPasswordResponse;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class SetNewPasswordFragment extends BusBaseFragment implements View.OnClickListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private FragmentNewPasswordBinding binding;
    private String mTokenHash;
    private String pageMode;
    private RegisterUserResponse registerUserResponse;

    /* loaded from: classes3.dex */
    public class GenericTextWatcher implements TextWatcher {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ SetNewPasswordFragment f10563a;
        @NotNull
        private final View view;

        public GenericTextWatcher(@NotNull SetNewPasswordFragment setNewPasswordFragment, View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.f10563a = setNewPasswordFragment;
            this.view = view;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(@NotNull Editable editable) {
            TextInputLayout textInputLayout;
            SetNewPasswordFragment setNewPasswordFragment;
            TextInputEditText textInputEditText;
            Intrinsics.checkNotNullParameter(editable, "editable");
            int id = this.view.getId();
            FragmentNewPasswordBinding fragmentNewPasswordBinding = this.f10563a.binding;
            FragmentNewPasswordBinding fragmentNewPasswordBinding2 = null;
            if (fragmentNewPasswordBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentNewPasswordBinding = null;
            }
            if (id == fragmentNewPasswordBinding.edNewPassword.getId()) {
                AppUtil.Companion companion = AppUtil.Companion;
                FragmentNewPasswordBinding fragmentNewPasswordBinding3 = this.f10563a.binding;
                if (fragmentNewPasswordBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentNewPasswordBinding3 = null;
                }
                TextInputLayout textInputLayout2 = fragmentNewPasswordBinding3.txtLayoutNewPassword;
                FragmentNewPasswordBinding fragmentNewPasswordBinding4 = this.f10563a.binding;
                if (fragmentNewPasswordBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentNewPasswordBinding4 = null;
                }
                TextInputEditText textInputEditText2 = fragmentNewPasswordBinding4.edNewPassword;
                Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.edNewPassword");
                String myText = ExtensionsKt.myText(textInputEditText2);
                String string = this.f10563a.getString(R.string.enter_new_password_valid_error);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enter_new_password_valid_error)");
                if (!companion.isValidPassword(textInputLayout2, myText, string)) {
                    FragmentNewPasswordBinding fragmentNewPasswordBinding5 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding5 = null;
                    }
                    ConstraintLayout root = fragmentNewPasswordBinding5.newPasswordError.getRoot();
                    FragmentNewPasswordBinding fragmentNewPasswordBinding6 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding6 = null;
                    }
                    TextInputEditText textInputEditText3 = fragmentNewPasswordBinding6.edNewPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText3, "binding.edNewPassword");
                    if (ExtensionsKt.myText(textInputEditText3).length() == 0) {
                        root.setVisibility(8);
                    }
                    FragmentNewPasswordBinding fragmentNewPasswordBinding7 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentNewPasswordBinding2 = fragmentNewPasswordBinding7;
                    }
                    textInputLayout = fragmentNewPasswordBinding2.txtLayoutNewPassword;
                    textInputLayout.setErrorIconDrawable(this.f10563a.requireContext().getDrawable(R.drawable.ic_info));
                    return;
                }
                FragmentNewPasswordBinding fragmentNewPasswordBinding8 = this.f10563a.binding;
                if (fragmentNewPasswordBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentNewPasswordBinding8 = null;
                }
                fragmentNewPasswordBinding8.txtLayoutNewPassword.setErrorIconDrawable((Drawable) null);
                FragmentNewPasswordBinding fragmentNewPasswordBinding9 = this.f10563a.binding;
                if (fragmentNewPasswordBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentNewPasswordBinding9 = null;
                }
                fragmentNewPasswordBinding9.txtLayoutNewPassword.setPasswordVisibilityToggleEnabled(true);
                FragmentNewPasswordBinding fragmentNewPasswordBinding10 = this.f10563a.binding;
                if (fragmentNewPasswordBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentNewPasswordBinding10 = null;
                }
                fragmentNewPasswordBinding10.newPasswordError.getRoot().setVisibility(8);
                FragmentNewPasswordBinding fragmentNewPasswordBinding11 = this.f10563a.binding;
                if (fragmentNewPasswordBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentNewPasswordBinding11 = null;
                }
                fragmentNewPasswordBinding11.txtLayoutNewPassword.setError(null);
                setNewPasswordFragment = this.f10563a;
                FragmentNewPasswordBinding fragmentNewPasswordBinding12 = setNewPasswordFragment.binding;
                if (fragmentNewPasswordBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentNewPasswordBinding2 = fragmentNewPasswordBinding12;
                }
                textInputEditText = fragmentNewPasswordBinding2.edNewPassword;
                Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.edNewPassword");
                setNewPasswordFragment.setValidInput(textInputEditText, true);
            }
            FragmentNewPasswordBinding fragmentNewPasswordBinding13 = this.f10563a.binding;
            if (fragmentNewPasswordBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentNewPasswordBinding13 = null;
            }
            if (id == fragmentNewPasswordBinding13.edConfirmPassword.getId()) {
                FragmentNewPasswordBinding fragmentNewPasswordBinding14 = this.f10563a.binding;
                if (fragmentNewPasswordBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentNewPasswordBinding14 = null;
                }
                if (fragmentNewPasswordBinding14.edConfirmPassword.isFocused()) {
                    AppUtil.Companion companion2 = AppUtil.Companion;
                    FragmentNewPasswordBinding fragmentNewPasswordBinding15 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding15 = null;
                    }
                    TextInputLayout textInputLayout3 = fragmentNewPasswordBinding15.txtLayoutConfirmPassword;
                    FragmentNewPasswordBinding fragmentNewPasswordBinding16 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding16 = null;
                    }
                    TextInputEditText textInputEditText4 = fragmentNewPasswordBinding16.edConfirmPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText4, "binding.edConfirmPassword");
                    String myText2 = ExtensionsKt.myText(textInputEditText4);
                    String string2 = this.f10563a.getString(R.string.enter_new_password_valid_error);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.enter_new_password_valid_error)");
                    if (!companion2.isValidPassword(textInputLayout3, myText2, string2)) {
                        FragmentNewPasswordBinding fragmentNewPasswordBinding17 = this.f10563a.binding;
                        if (fragmentNewPasswordBinding17 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentNewPasswordBinding17 = null;
                        }
                        ConstraintLayout root2 = fragmentNewPasswordBinding17.confirmPasswordError.getRoot();
                        FragmentNewPasswordBinding fragmentNewPasswordBinding18 = this.f10563a.binding;
                        if (fragmentNewPasswordBinding18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentNewPasswordBinding18 = null;
                        }
                        TextInputEditText textInputEditText5 = fragmentNewPasswordBinding18.edConfirmPassword;
                        Intrinsics.checkNotNullExpressionValue(textInputEditText5, "binding.edConfirmPassword");
                        if (ExtensionsKt.myText(textInputEditText5).length() == 0) {
                            root2.setVisibility(8);
                        }
                        FragmentNewPasswordBinding fragmentNewPasswordBinding19 = this.f10563a.binding;
                        if (fragmentNewPasswordBinding19 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            fragmentNewPasswordBinding2 = fragmentNewPasswordBinding19;
                        }
                        textInputLayout = fragmentNewPasswordBinding2.txtLayoutConfirmPassword;
                        textInputLayout.setErrorIconDrawable(this.f10563a.requireContext().getDrawable(R.drawable.ic_info));
                        return;
                    }
                    FragmentNewPasswordBinding fragmentNewPasswordBinding20 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding20 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding20 = null;
                    }
                    fragmentNewPasswordBinding20.txtLayoutConfirmPassword.setErrorIconDrawable((Drawable) null);
                    FragmentNewPasswordBinding fragmentNewPasswordBinding21 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding21 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding21 = null;
                    }
                    fragmentNewPasswordBinding21.txtLayoutConfirmPassword.setPasswordVisibilityToggleEnabled(true);
                    FragmentNewPasswordBinding fragmentNewPasswordBinding22 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding22 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding22 = null;
                    }
                    fragmentNewPasswordBinding22.confirmPasswordError.getRoot().setVisibility(8);
                    FragmentNewPasswordBinding fragmentNewPasswordBinding23 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding23 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding23 = null;
                    }
                    fragmentNewPasswordBinding23.txtLayoutConfirmPassword.setError(null);
                    FragmentNewPasswordBinding fragmentNewPasswordBinding24 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding24 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding24 = null;
                    }
                    TextInputEditText textInputEditText6 = fragmentNewPasswordBinding24.edNewPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText6, "binding.edNewPassword");
                    String myText3 = ExtensionsKt.myText(textInputEditText6);
                    FragmentNewPasswordBinding fragmentNewPasswordBinding25 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding25 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding25 = null;
                    }
                    TextInputEditText textInputEditText7 = fragmentNewPasswordBinding25.edConfirmPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText7, "binding.edConfirmPassword");
                    if (!Intrinsics.areEqual(myText3, ExtensionsKt.myText(textInputEditText7))) {
                        FragmentNewPasswordBinding fragmentNewPasswordBinding26 = this.f10563a.binding;
                        if (fragmentNewPasswordBinding26 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            fragmentNewPasswordBinding26 = null;
                        }
                        fragmentNewPasswordBinding26.txtLayoutConfirmPassword.setError(this.f10563a.getString(R.string.new_confirm_password_not_match));
                        SetNewPasswordFragment setNewPasswordFragment2 = this.f10563a;
                        FragmentNewPasswordBinding fragmentNewPasswordBinding27 = setNewPasswordFragment2.binding;
                        if (fragmentNewPasswordBinding27 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            fragmentNewPasswordBinding2 = fragmentNewPasswordBinding27;
                        }
                        TextInputEditText textInputEditText8 = fragmentNewPasswordBinding2.edConfirmPassword;
                        Intrinsics.checkNotNullExpressionValue(textInputEditText8, "binding.edConfirmPassword");
                        setNewPasswordFragment2.setValidInput(textInputEditText8, false);
                        return;
                    }
                    FragmentNewPasswordBinding fragmentNewPasswordBinding28 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding28 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding28 = null;
                    }
                    fragmentNewPasswordBinding28.txtLayoutConfirmPassword.setError(null);
                    FragmentNewPasswordBinding fragmentNewPasswordBinding29 = this.f10563a.binding;
                    if (fragmentNewPasswordBinding29 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding29 = null;
                    }
                    fragmentNewPasswordBinding29.txtLayoutNewPassword.setError(null);
                    setNewPasswordFragment = this.f10563a;
                    FragmentNewPasswordBinding fragmentNewPasswordBinding30 = setNewPasswordFragment.binding;
                    if (fragmentNewPasswordBinding30 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentNewPasswordBinding2 = fragmentNewPasswordBinding30;
                    }
                    textInputEditText = fragmentNewPasswordBinding2.edConfirmPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.edConfirmPassword");
                    setNewPasswordFragment.setValidInput(textInputEditText, true);
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

    private final void apiAuthenticateUser() {
        GetTokenBody getTokenBody = new GetTokenBody(null, null, null, null, 15, null);
        OnBoardingService onBoardingService = new OnBoardingService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        onBoardingService.callGetUserTokenAPI(requireActivity, getTokenBody, false);
    }

    private final void setListeners() {
        FragmentNewPasswordBinding fragmentNewPasswordBinding = this.binding;
        FragmentNewPasswordBinding fragmentNewPasswordBinding2 = null;
        if (fragmentNewPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding = null;
        }
        fragmentNewPasswordBinding.btnPasswordConfirm.setOnClickListener(this);
        FragmentNewPasswordBinding fragmentNewPasswordBinding3 = this.binding;
        if (fragmentNewPasswordBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding3 = null;
        }
        fragmentNewPasswordBinding3.txtLayoutNewPassword.setErrorIconOnClickListener(new View.OnClickListener() { // from class: m.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetNewPasswordFragment.m146setListeners$lambda1(SetNewPasswordFragment.this, view);
            }
        });
        FragmentNewPasswordBinding fragmentNewPasswordBinding4 = this.binding;
        if (fragmentNewPasswordBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding4 = null;
        }
        fragmentNewPasswordBinding4.txtLayoutConfirmPassword.setErrorIconOnClickListener(new View.OnClickListener() { // from class: m.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SetNewPasswordFragment.m147setListeners$lambda2(SetNewPasswordFragment.this, view);
            }
        });
        FragmentNewPasswordBinding fragmentNewPasswordBinding5 = this.binding;
        if (fragmentNewPasswordBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding5 = null;
        }
        TextInputEditText textInputEditText = fragmentNewPasswordBinding5.edNewPassword;
        FragmentNewPasswordBinding fragmentNewPasswordBinding6 = this.binding;
        if (fragmentNewPasswordBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding6 = null;
        }
        TextInputEditText textInputEditText2 = fragmentNewPasswordBinding6.edNewPassword;
        Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.edNewPassword");
        textInputEditText.addTextChangedListener(new GenericTextWatcher(this, textInputEditText2));
        FragmentNewPasswordBinding fragmentNewPasswordBinding7 = this.binding;
        if (fragmentNewPasswordBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding7 = null;
        }
        TextInputEditText textInputEditText3 = fragmentNewPasswordBinding7.edConfirmPassword;
        FragmentNewPasswordBinding fragmentNewPasswordBinding8 = this.binding;
        if (fragmentNewPasswordBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentNewPasswordBinding2 = fragmentNewPasswordBinding8;
        }
        TextInputEditText textInputEditText4 = fragmentNewPasswordBinding2.edConfirmPassword;
        Intrinsics.checkNotNullExpressionValue(textInputEditText4, "binding.edConfirmPassword");
        textInputEditText3.addTextChangedListener(new GenericTextWatcher(this, textInputEditText4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setListeners$lambda-1  reason: not valid java name */
    public static final void m146setListeners$lambda1(SetNewPasswordFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentNewPasswordBinding fragmentNewPasswordBinding = this$0.binding;
        if (fragmentNewPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding = null;
        }
        fragmentNewPasswordBinding.newPasswordError.getRoot().setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setListeners$lambda-2  reason: not valid java name */
    public static final void m147setListeners$lambda2(SetNewPasswordFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentNewPasswordBinding fragmentNewPasswordBinding = this$0.binding;
        if (fragmentNewPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding = null;
        }
        fragmentNewPasswordBinding.confirmPasswordError.getRoot().setVisibility(0);
    }

    private final int validatePassword() {
        AppUtil.Companion companion = AppUtil.Companion;
        FragmentNewPasswordBinding fragmentNewPasswordBinding = this.binding;
        FragmentNewPasswordBinding fragmentNewPasswordBinding2 = null;
        if (fragmentNewPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding = null;
        }
        TextInputLayout textInputLayout = fragmentNewPasswordBinding.txtLayoutNewPassword;
        FragmentNewPasswordBinding fragmentNewPasswordBinding3 = this.binding;
        if (fragmentNewPasswordBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding3 = null;
        }
        TextInputEditText textInputEditText = fragmentNewPasswordBinding3.edNewPassword;
        Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.edNewPassword");
        String myText = ExtensionsKt.myText(textInputEditText);
        String string = getString(R.string.enter_password);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enter_password)");
        if (companion.isValidPassword(textInputLayout, myText, string)) {
            FragmentNewPasswordBinding fragmentNewPasswordBinding4 = this.binding;
            if (fragmentNewPasswordBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentNewPasswordBinding4 = null;
            }
            TextInputEditText textInputEditText2 = fragmentNewPasswordBinding4.edConfirmPassword;
            Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.edConfirmPassword");
            if (ExtensionsKt.myText(textInputEditText2).length() == 0) {
                return 2;
            }
            FragmentNewPasswordBinding fragmentNewPasswordBinding5 = this.binding;
            if (fragmentNewPasswordBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentNewPasswordBinding5 = null;
            }
            TextInputEditText textInputEditText3 = fragmentNewPasswordBinding5.edNewPassword;
            Intrinsics.checkNotNullExpressionValue(textInputEditText3, "binding.edNewPassword");
            if (ExtensionsKt.myText(textInputEditText3).length() > 0) {
                FragmentNewPasswordBinding fragmentNewPasswordBinding6 = this.binding;
                if (fragmentNewPasswordBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentNewPasswordBinding6 = null;
                }
                TextInputEditText textInputEditText4 = fragmentNewPasswordBinding6.edConfirmPassword;
                Intrinsics.checkNotNullExpressionValue(textInputEditText4, "binding.edConfirmPassword");
                if (ExtensionsKt.myText(textInputEditText4).length() > 0) {
                    FragmentNewPasswordBinding fragmentNewPasswordBinding7 = this.binding;
                    if (fragmentNewPasswordBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentNewPasswordBinding7 = null;
                    }
                    TextInputEditText textInputEditText5 = fragmentNewPasswordBinding7.edNewPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText5, "binding.edNewPassword");
                    String myText2 = ExtensionsKt.myText(textInputEditText5);
                    FragmentNewPasswordBinding fragmentNewPasswordBinding8 = this.binding;
                    if (fragmentNewPasswordBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentNewPasswordBinding2 = fragmentNewPasswordBinding8;
                    }
                    TextInputEditText textInputEditText6 = fragmentNewPasswordBinding2.edConfirmPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText6, "binding.edConfirmPassword");
                    return Intrinsics.areEqual(myText2, ExtensionsKt.myText(textInputEditText6)) ? 0 : -1;
                }
            }
            return -1;
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
    public final void getMessage(@NotNull SetPasswordResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        String str = this.pageMode;
        String str2 = null;
        RegisterUserResponse registerUserResponse = null;
        RegisterUserResponse registerUserResponse2 = null;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageMode");
            str = null;
        }
        if (str.length() == 0) {
            return;
        }
        String str3 = this.pageMode;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageMode");
            str3 = null;
        }
        if (Intrinsics.areEqual(str3, AppConstants.PAGE_MODE_REGISTRATION)) {
            RegisterUserResponse registerUserResponse3 = this.registerUserResponse;
            if (registerUserResponse3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
                registerUserResponse3 = null;
            }
            if (!Intrinsics.areEqual(registerUserResponse3.isGuestUser(), "Y")) {
                apiAuthenticateUser();
                return;
            }
            SharedPref.Companion companion = SharedPref.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            companion.setPrimaryUserTokenDetails(requireActivity, event.getToken());
            Intent intent = new Intent(requireActivity(), RegistrationActivity.class);
            RegisterUserResponse registerUserResponse4 = this.registerUserResponse;
            if (registerUserResponse4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
            } else {
                registerUserResponse = registerUserResponse4;
            }
            intent.putExtra(AppConstants.REGUSER, registerUserResponse);
            startActivity(intent);
            return;
        }
        String str4 = this.pageMode;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageMode");
            str4 = null;
        }
        if (Intrinsics.areEqual(str4, AppConstants.PAGE_MODE_ADD_VEHICLE)) {
            Intent intent2 = new Intent(requireActivity(), AddCarActivity.class);
            SharedPref.Companion companion2 = SharedPref.Companion;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            companion2.setSecondaryUserTokenDetails(requireActivity2, event.getToken());
            RegisterUserResponse registerUserResponse5 = this.registerUserResponse;
            if (registerUserResponse5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
            } else {
                registerUserResponse2 = registerUserResponse5;
            }
            intent2.putExtra(AppConstants.REGUSER, registerUserResponse2);
            intent2.putExtra("login", AppConstants.PAGE_MODE_ADD_VEHICLE);
            startActivity(intent2);
            return;
        }
        String str5 = this.pageMode;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageMode");
            str5 = null;
        }
        if (Intrinsics.areEqual(str5, AppConstants.PAGE_MODE_FORGOT_PWD)) {
            PasswordSuccessBottomSheetFragment passwordSuccessBottomSheetFragment = new PasswordSuccessBottomSheetFragment();
            Bundle bundle = new Bundle();
            String str6 = this.pageMode;
            if (str6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pageMode");
            } else {
                str2 = str6;
            }
            bundle.putString("login", str2);
            passwordSuccessBottomSheetFragment.setArguments(bundle);
            passwordSuccessBottomSheetFragment.show(requireActivity().getSupportFragmentManager(), TermsAndConditionsFragment.TAG);
            passwordSuccessBottomSheetFragment.setCancelable(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x013e  */
    /* JADX WARN: Type inference failed for: r0v18, types: [java.lang.String] */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onClick(@NotNull View v) {
        TextInputLayout textInputLayout;
        int i2;
        String substring;
        FragmentNewPasswordBinding fragmentNewPasswordBinding;
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(v, "v");
        FragmentNewPasswordBinding fragmentNewPasswordBinding2 = this.binding;
        FragmentNewPasswordBinding fragmentNewPasswordBinding3 = null;
        if (fragmentNewPasswordBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding2 = null;
        }
        if (Intrinsics.areEqual(v, fragmentNewPasswordBinding2.btnPasswordConfirm)) {
            FragmentNewPasswordBinding fragmentNewPasswordBinding4 = this.binding;
            if (fragmentNewPasswordBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentNewPasswordBinding4 = null;
            }
            TextInputEditText textInputEditText = fragmentNewPasswordBinding4.edConfirmPassword;
            Intrinsics.checkNotNullExpressionValue(textInputEditText, "binding.edConfirmPassword");
            FragmentNewPasswordBinding fragmentNewPasswordBinding5 = this.binding;
            if (fragmentNewPasswordBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentNewPasswordBinding5 = null;
            }
            TextInputLayout textInputLayout2 = fragmentNewPasswordBinding5.txtLayoutConfirmPassword;
            Intrinsics.checkNotNullExpressionValue(textInputLayout2, "binding.txtLayoutConfirmPassword");
            ExtensionsKt.removeError(textInputEditText, textInputLayout2);
            FragmentNewPasswordBinding fragmentNewPasswordBinding6 = this.binding;
            if (fragmentNewPasswordBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentNewPasswordBinding6 = null;
            }
            TextInputEditText textInputEditText2 = fragmentNewPasswordBinding6.edNewPassword;
            Intrinsics.checkNotNullExpressionValue(textInputEditText2, "binding.edNewPassword");
            FragmentNewPasswordBinding fragmentNewPasswordBinding7 = this.binding;
            if (fragmentNewPasswordBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentNewPasswordBinding7 = null;
            }
            TextInputLayout textInputLayout3 = fragmentNewPasswordBinding7.txtLayoutNewPassword;
            Intrinsics.checkNotNullExpressionValue(textInputLayout3, "binding.txtLayoutNewPassword");
            ExtensionsKt.removeError(textInputEditText2, textInputLayout3);
            int validatePassword = validatePassword();
            if (validatePassword != -1) {
                if (validatePassword == 0) {
                    String str3 = this.mTokenHash;
                    if (str3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mTokenHash");
                        str3 = null;
                    }
                    if (str3.length() == 0) {
                        return;
                    }
                    RegisterUserPassword registerUserPassword = new RegisterUserPassword();
                    String str4 = this.pageMode;
                    if (str4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("pageMode");
                        str4 = null;
                    }
                    if (!Intrinsics.areEqual(str4, AppConstants.PAGE_MODE_REGISTRATION)) {
                        String str5 = this.pageMode;
                        if (str5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("pageMode");
                            str5 = null;
                        }
                        if (!Intrinsics.areEqual(str5, AppConstants.PAGE_MODE_ADD_VEHICLE)) {
                            RegisterUserResponse registerUserResponse = this.registerUserResponse;
                            if (registerUserResponse == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
                                registerUserResponse = null;
                            }
                            substring = registerUserResponse.getMobileNum();
                            Intrinsics.checkNotNull(substring);
                            registerUserPassword.setMobileNum(substring);
                            registerUserPassword.setCountryCode("+91");
                            fragmentNewPasswordBinding = this.binding;
                            if (fragmentNewPasswordBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                fragmentNewPasswordBinding = null;
                            }
                            TextInputEditText textInputEditText3 = fragmentNewPasswordBinding.edConfirmPassword;
                            Intrinsics.checkNotNullExpressionValue(textInputEditText3, "binding.edConfirmPassword");
                            registerUserPassword.setPassword(ExtensionsKt.myText(textInputEditText3));
                            str = this.mTokenHash;
                            if (str == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mTokenHash");
                                str = null;
                            }
                            if (str != null) {
                                registerUserPassword.setTokenHash(str);
                            }
                            str2 = this.pageMode;
                            if (str2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("pageMode");
                                str2 = null;
                            }
                            if (!Intrinsics.areEqual(str2, AppConstants.PAGE_MODE_REGISTRATION)) {
                                String str6 = this.pageMode;
                                if (str6 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("pageMode");
                                    str6 = null;
                                }
                                if (!Intrinsics.areEqual(str6, AppConstants.PAGE_MODE_ADD_VEHICLE)) {
                                    ?? r0 = this.pageMode;
                                    if (r0 == 0) {
                                        Intrinsics.throwUninitializedPropertyAccessException("pageMode");
                                    } else {
                                        fragmentNewPasswordBinding3 = r0;
                                    }
                                    if (Intrinsics.areEqual(fragmentNewPasswordBinding3, AppConstants.PAGE_MODE_FORGOT_PWD)) {
                                        OnBoardingService onBoardingService = new OnBoardingService();
                                        FragmentActivity requireActivity = requireActivity();
                                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                                        onBoardingService.setNewPasswordForgotPassword(requireActivity, registerUserPassword);
                                        return;
                                    }
                                    return;
                                }
                            }
                            OnBoardingService onBoardingService2 = new OnBoardingService();
                            FragmentActivity requireActivity2 = requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                            onBoardingService2.setNewPasswordRegistration(requireActivity2, registerUserPassword);
                            return;
                        }
                    }
                    RegisterUserResponse registerUserResponse2 = this.registerUserResponse;
                    if (registerUserResponse2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
                        registerUserResponse2 = null;
                    }
                    String mobileNum = registerUserResponse2.getMobileNum();
                    Intrinsics.checkNotNull(mobileNum);
                    RegisterUserResponse registerUserResponse3 = this.registerUserResponse;
                    if (registerUserResponse3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("registerUserResponse");
                        registerUserResponse3 = null;
                    }
                    String mobileNum2 = registerUserResponse3.getMobileNum();
                    Intrinsics.checkNotNull(mobileNum2);
                    substring = mobileNum.substring(3, mobileNum2.length());
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    registerUserPassword.setMobileNum(substring);
                    registerUserPassword.setCountryCode("+91");
                    fragmentNewPasswordBinding = this.binding;
                    if (fragmentNewPasswordBinding == null) {
                    }
                    TextInputEditText textInputEditText32 = fragmentNewPasswordBinding.edConfirmPassword;
                    Intrinsics.checkNotNullExpressionValue(textInputEditText32, "binding.edConfirmPassword");
                    registerUserPassword.setPassword(ExtensionsKt.myText(textInputEditText32));
                    str = this.mTokenHash;
                    if (str == null) {
                    }
                    if (str != null) {
                    }
                    str2 = this.pageMode;
                    if (str2 == null) {
                    }
                    if (!Intrinsics.areEqual(str2, AppConstants.PAGE_MODE_REGISTRATION)) {
                    }
                    OnBoardingService onBoardingService22 = new OnBoardingService();
                    FragmentActivity requireActivity22 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity22, "requireActivity()");
                    onBoardingService22.setNewPasswordRegistration(requireActivity22, registerUserPassword);
                    return;
                } else if (validatePassword == 1) {
                    FragmentNewPasswordBinding fragmentNewPasswordBinding8 = this.binding;
                    if (fragmentNewPasswordBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentNewPasswordBinding3 = fragmentNewPasswordBinding8;
                    }
                    textInputLayout = fragmentNewPasswordBinding3.txtLayoutNewPassword;
                    i2 = R.string.enter_new_password;
                } else if (validatePassword != 2) {
                    return;
                } else {
                    FragmentNewPasswordBinding fragmentNewPasswordBinding9 = this.binding;
                    if (fragmentNewPasswordBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentNewPasswordBinding3 = fragmentNewPasswordBinding9;
                    }
                    textInputLayout = fragmentNewPasswordBinding3.txtLayoutConfirmPassword;
                    i2 = R.string.enter_confirm_password;
                }
            } else {
                FragmentNewPasswordBinding fragmentNewPasswordBinding10 = this.binding;
                if (fragmentNewPasswordBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentNewPasswordBinding3 = fragmentNewPasswordBinding10;
                }
                textInputLayout = fragmentNewPasswordBinding3.txtLayoutConfirmPassword;
                i2 = R.string.new_confirm_password_not_match;
            }
            textInputLayout.setError(getString(i2));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("login", "");
            Intrinsics.checkNotNullExpressionValue(string, "it.getString(AppConstants.PAGE_MODE, \"\")");
            this.pageMode = string;
            if (string == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pageMode");
                string = null;
            }
            int hashCode = string.hashCode();
            if (hashCode != -1350309703) {
                if (hashCode != -305103839) {
                    if (hashCode != 1661369742 || !string.equals(AppConstants.PAGE_MODE_ADD_VEHICLE)) {
                        return;
                    }
                } else if (!string.equals(AppConstants.PAGE_MODE_FORGOT_PWD)) {
                    return;
                }
            } else if (!string.equals(AppConstants.PAGE_MODE_REGISTRATION)) {
                return;
            }
            Parcelable parcelable = arguments.getParcelable(AppConstants.REGUSER);
            Intrinsics.checkNotNull(parcelable);
            this.registerUserResponse = (RegisterUserResponse) parcelable;
            String string2 = arguments.getString(AppConstants.TOKEN_HASH, "");
            Intrinsics.checkNotNullExpressionValue(string2, "it.getString(AppConstants.TOKEN_HASH, \"\")");
            this.mTokenHash = string2;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentNewPasswordBinding inflate = FragmentNewPasswordBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.binding = inflate;
        setListeners();
        FragmentNewPasswordBinding fragmentNewPasswordBinding = this.binding;
        if (fragmentNewPasswordBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNewPasswordBinding = null;
        }
        FrameLayout root = fragmentNewPasswordBinding.getRoot();
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
            FragmentNewPasswordBinding fragmentNewPasswordBinding = this.binding;
            if (fragmentNewPasswordBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentNewPasswordBinding = null;
            }
            view = fragmentNewPasswordBinding.edConfirmPassword;
            i2 = R.drawable.ic_invalid_vin;
        }
        view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, i2, 0);
    }
}
