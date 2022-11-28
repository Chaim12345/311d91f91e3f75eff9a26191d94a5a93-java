package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.switchmaterial.SwitchMaterial;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentSecurityBinding implements ViewBinding {
    @NonNull
    public final View horiSeperator1;
    @NonNull
    public final View horiSeperator2;
    @NonNull
    public final View horiSeperator3;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final SwitchMaterial switchFingerPrint;
    @NonNull
    public final AppCompatTextView tvChangePass;
    @NonNull
    public final AppCompatTextView tvChangePassword;
    @NonNull
    public final AppCompatTextView tvChangePin;
    @NonNull
    public final AppCompatTextView tvChangePinNumber;
    @NonNull
    public final AppCompatTextView tvEnableFingerPrint;

    private FragmentSecurityBinding(@NonNull ConstraintLayout constraintLayout, @NonNull View view, @NonNull View view2, @NonNull View view3, @NonNull SwitchMaterial switchMaterial, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5) {
        this.rootView = constraintLayout;
        this.horiSeperator1 = view;
        this.horiSeperator2 = view2;
        this.horiSeperator3 = view3;
        this.switchFingerPrint = switchMaterial;
        this.tvChangePass = appCompatTextView;
        this.tvChangePassword = appCompatTextView2;
        this.tvChangePin = appCompatTextView3;
        this.tvChangePinNumber = appCompatTextView4;
        this.tvEnableFingerPrint = appCompatTextView5;
    }

    @NonNull
    public static FragmentSecurityBinding bind(@NonNull View view) {
        int i2 = R.id.horiSeperator1;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.horiSeperator1);
        if (findChildViewById != null) {
            i2 = R.id.horiSeperator2;
            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.horiSeperator2);
            if (findChildViewById2 != null) {
                i2 = R.id.horiSeperator3;
                View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.horiSeperator3);
                if (findChildViewById3 != null) {
                    i2 = R.id.switchFingerPrint;
                    SwitchMaterial switchMaterial = (SwitchMaterial) ViewBindings.findChildViewById(view, R.id.switchFingerPrint);
                    if (switchMaterial != null) {
                        i2 = R.id.tvChangePass;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChangePass);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvChangePassword;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChangePassword);
                            if (appCompatTextView2 != null) {
                                i2 = R.id.tvChangePin;
                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChangePin);
                                if (appCompatTextView3 != null) {
                                    i2 = R.id.tvChangePinNumber;
                                    AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChangePinNumber);
                                    if (appCompatTextView4 != null) {
                                        i2 = R.id.tvEnableFingerPrint;
                                        AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEnableFingerPrint);
                                        if (appCompatTextView5 != null) {
                                            return new FragmentSecurityBinding((ConstraintLayout) view, findChildViewById, findChildViewById2, findChildViewById3, switchMaterial, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5);
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
    public static FragmentSecurityBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentSecurityBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_security, viewGroup, false);
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
