package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentNotificationSettingsBinding implements ViewBinding {
    @NonNull
    public final ConstraintLayout clSettings;
    @NonNull
    public final MaterialCardView cvSpeedLimit;
    @NonNull
    public final View divider;
    @NonNull
    public final AppCompatImageView ivEditACIdlingTime;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final SwitchMaterial switchACIdling;
    @NonNull
    public final SwitchMaterial switchSpeedLimit;
    @NonNull
    public final AppCompatTextView tvACIdlingAlert;
    @NonNull
    public final AppCompatTextView tvACIdlingAlertTime;
    @NonNull
    public final AppCompatTextView tvMutedDesc;
    @NonNull
    public final AppCompatTextView tvMutedFor;
    @NonNull
    public final AppCompatTextView tvSpeedLimitAlert;

    private FragmentNotificationSettingsBinding(@NonNull FrameLayout frameLayout, @NonNull ConstraintLayout constraintLayout, @NonNull MaterialCardView materialCardView, @NonNull View view, @NonNull AppCompatImageView appCompatImageView, @NonNull SwitchMaterial switchMaterial, @NonNull SwitchMaterial switchMaterial2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5) {
        this.rootView = frameLayout;
        this.clSettings = constraintLayout;
        this.cvSpeedLimit = materialCardView;
        this.divider = view;
        this.ivEditACIdlingTime = appCompatImageView;
        this.switchACIdling = switchMaterial;
        this.switchSpeedLimit = switchMaterial2;
        this.tvACIdlingAlert = appCompatTextView;
        this.tvACIdlingAlertTime = appCompatTextView2;
        this.tvMutedDesc = appCompatTextView3;
        this.tvMutedFor = appCompatTextView4;
        this.tvSpeedLimitAlert = appCompatTextView5;
    }

    @NonNull
    public static FragmentNotificationSettingsBinding bind(@NonNull View view) {
        int i2 = R.id.clSettings;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clSettings);
        if (constraintLayout != null) {
            i2 = R.id.cvSpeedLimit;
            MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, R.id.cvSpeedLimit);
            if (materialCardView != null) {
                i2 = R.id.divider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                if (findChildViewById != null) {
                    i2 = R.id.ivEditACIdlingTime;
                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivEditACIdlingTime);
                    if (appCompatImageView != null) {
                        i2 = R.id.switchACIdling;
                        SwitchMaterial switchMaterial = (SwitchMaterial) ViewBindings.findChildViewById(view, R.id.switchACIdling);
                        if (switchMaterial != null) {
                            i2 = R.id.switchSpeedLimit;
                            SwitchMaterial switchMaterial2 = (SwitchMaterial) ViewBindings.findChildViewById(view, R.id.switchSpeedLimit);
                            if (switchMaterial2 != null) {
                                i2 = R.id.tvACIdlingAlert;
                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvACIdlingAlert);
                                if (appCompatTextView != null) {
                                    i2 = R.id.tvACIdlingAlertTime;
                                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvACIdlingAlertTime);
                                    if (appCompatTextView2 != null) {
                                        i2 = R.id.tvMutedDesc;
                                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvMutedDesc);
                                        if (appCompatTextView3 != null) {
                                            i2 = R.id.tvMutedFor;
                                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvMutedFor);
                                            if (appCompatTextView4 != null) {
                                                i2 = R.id.tvSpeedLimitAlert;
                                                AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSpeedLimitAlert);
                                                if (appCompatTextView5 != null) {
                                                    return new FragmentNotificationSettingsBinding((FrameLayout) view, constraintLayout, materialCardView, findChildViewById, appCompatImageView, switchMaterial, switchMaterial2, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5);
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
    public static FragmentNotificationSettingsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentNotificationSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_notification_settings, viewGroup, false);
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
