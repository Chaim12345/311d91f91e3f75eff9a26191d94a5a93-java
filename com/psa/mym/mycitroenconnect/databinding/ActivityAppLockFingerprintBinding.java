package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityAppLockFingerprintBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView appLockFingerPrint;
    @NonNull
    public final TextView appLockForgotPin;
    @NonNull
    public final PinView edtAppLockPin;
    @NonNull
    public final AppCompatImageView ivCar;
    @NonNull
    public final TextView lableText;
    @NonNull
    public final LinearLayout linAuth;
    @NonNull
    public final LinearLayout linTop;
    @NonNull
    public final RelativeLayout relPin;
    @NonNull
    private final ScrollView rootView;
    @NonNull
    public final TextView tvAppLockRegNow;
    @NonNull
    public final TextView tvWelcome;
    @NonNull
    public final TextInputLayout txtAppLockPin;
    @NonNull
    public final View view;

    private ActivityAppLockFingerprintBinding(@NonNull ScrollView scrollView, @NonNull AppCompatImageView appCompatImageView, @NonNull TextView textView, @NonNull PinView pinView, @NonNull AppCompatImageView appCompatImageView2, @NonNull TextView textView2, @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2, @NonNull RelativeLayout relativeLayout, @NonNull TextView textView3, @NonNull TextView textView4, @NonNull TextInputLayout textInputLayout, @NonNull View view) {
        this.rootView = scrollView;
        this.appLockFingerPrint = appCompatImageView;
        this.appLockForgotPin = textView;
        this.edtAppLockPin = pinView;
        this.ivCar = appCompatImageView2;
        this.lableText = textView2;
        this.linAuth = linearLayout;
        this.linTop = linearLayout2;
        this.relPin = relativeLayout;
        this.tvAppLockRegNow = textView3;
        this.tvWelcome = textView4;
        this.txtAppLockPin = textInputLayout;
        this.view = view;
    }

    @NonNull
    public static ActivityAppLockFingerprintBinding bind(@NonNull View view) {
        int i2 = R.id.appLockFingerPrint;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.appLockFingerPrint);
        if (appCompatImageView != null) {
            i2 = R.id.appLockForgotPin;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.appLockForgotPin);
            if (textView != null) {
                i2 = R.id.edtAppLockPin;
                PinView pinView = (PinView) ViewBindings.findChildViewById(view, R.id.edtAppLockPin);
                if (pinView != null) {
                    i2 = R.id.ivCar;
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCar);
                    if (appCompatImageView2 != null) {
                        i2 = R.id.lableText;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.lableText);
                        if (textView2 != null) {
                            i2 = R.id.linAuth;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linAuth);
                            if (linearLayout != null) {
                                i2 = R.id.linTop;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linTop);
                                if (linearLayout2 != null) {
                                    i2 = R.id.relPin;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.relPin);
                                    if (relativeLayout != null) {
                                        i2 = R.id.tvAppLockRegNow;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tvAppLockRegNow);
                                        if (textView3 != null) {
                                            i2 = R.id.tvWelcome;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tvWelcome);
                                            if (textView4 != null) {
                                                i2 = R.id.txtAppLockPin;
                                                TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtAppLockPin);
                                                if (textInputLayout != null) {
                                                    i2 = R.id.view;
                                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.view);
                                                    if (findChildViewById != null) {
                                                        return new ActivityAppLockFingerprintBinding((ScrollView) view, appCompatImageView, textView, pinView, appCompatImageView2, textView2, linearLayout, linearLayout2, relativeLayout, textView3, textView4, textInputLayout, findChildViewById);
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
    public static ActivityAppLockFingerprintBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityAppLockFingerprintBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_app_lock_fingerprint, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ScrollView getRoot() {
        return this.rootView;
    }
}
