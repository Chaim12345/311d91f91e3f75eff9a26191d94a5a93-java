package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class BottomSheetSwitchCarConfirmationBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnNo;
    @NonNull
    public final AppCompatButton btnYes;
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final LinearLayoutCompat layoutBtn;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvSwitchConfDesc;
    @NonNull
    public final AppCompatTextView tvSwitchConfirmation;

    private BottomSheetSwitchCarConfirmationBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = frameLayout;
        this.btnNo = appCompatButton;
        this.btnYes = appCompatButton2;
        this.ivClose = appCompatImageView;
        this.layoutBtn = linearLayoutCompat;
        this.tvSwitchConfDesc = appCompatTextView;
        this.tvSwitchConfirmation = appCompatTextView2;
    }

    @NonNull
    public static BottomSheetSwitchCarConfirmationBinding bind(@NonNull View view) {
        int i2 = R.id.btnNo;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnNo);
        if (appCompatButton != null) {
            i2 = R.id.btnYes;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnYes);
            if (appCompatButton2 != null) {
                i2 = R.id.ivClose;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
                if (appCompatImageView != null) {
                    i2 = R.id.layoutBtn;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutBtn);
                    if (linearLayoutCompat != null) {
                        i2 = R.id.tvSwitchConfDesc;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSwitchConfDesc);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvSwitchConfirmation;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSwitchConfirmation);
                            if (appCompatTextView2 != null) {
                                return new BottomSheetSwitchCarConfirmationBinding((FrameLayout) view, appCompatButton, appCompatButton2, appCompatImageView, linearLayoutCompat, appCompatTextView, appCompatTextView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static BottomSheetSwitchCarConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static BottomSheetSwitchCarConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.bottom_sheet_switch_car_confirmation, viewGroup, false);
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
