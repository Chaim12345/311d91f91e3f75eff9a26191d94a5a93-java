package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class DlgLayoutTncBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnContinue;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final CheckBox tncAcceptCheckbox;
    @NonNull
    public final AppCompatImageView tncClose;
    @NonNull
    public final AppCompatImageView tncIcon;
    @NonNull
    public final AppCompatTextView tvTncTitle;
    @NonNull
    public final AppCompatTextView tvWelcome;

    private DlgLayoutTncBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull CheckBox checkBox, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = constraintLayout;
        this.btnContinue = appCompatButton;
        this.tncAcceptCheckbox = checkBox;
        this.tncClose = appCompatImageView;
        this.tncIcon = appCompatImageView2;
        this.tvTncTitle = appCompatTextView;
        this.tvWelcome = appCompatTextView2;
    }

    @NonNull
    public static DlgLayoutTncBinding bind(@NonNull View view) {
        int i2 = R.id.btnContinue;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnContinue);
        if (appCompatButton != null) {
            i2 = R.id.tnc_accept_checkbox;
            CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.tnc_accept_checkbox);
            if (checkBox != null) {
                i2 = R.id.tncClose;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tncClose);
                if (appCompatImageView != null) {
                    i2 = R.id.tnc_icon;
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tnc_icon);
                    if (appCompatImageView2 != null) {
                        i2 = R.id.tvTncTitle;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTncTitle);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvWelcome;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvWelcome);
                            if (appCompatTextView2 != null) {
                                return new DlgLayoutTncBinding((ConstraintLayout) view, appCompatButton, checkBox, appCompatImageView, appCompatImageView2, appCompatTextView, appCompatTextView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DlgLayoutTncBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DlgLayoutTncBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dlg_layout_tnc, viewGroup, false);
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
