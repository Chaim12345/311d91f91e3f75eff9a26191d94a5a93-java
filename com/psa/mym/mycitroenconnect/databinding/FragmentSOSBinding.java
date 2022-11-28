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
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.skyfishjy.library.RippleBackground;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentSOSBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnSosCancel;
    @NonNull
    public final AppCompatImageView ivSOS;
    @NonNull
    public final AppCompatImageView ivSosClose;
    @NonNull
    public final RippleBackground rippleBackgroundSos;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvSosDesc;
    @NonNull
    public final AppCompatTextView tvSosTitle;

    private FragmentSOSBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull RippleBackground rippleBackground, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = frameLayout;
        this.btnSosCancel = appCompatButton;
        this.ivSOS = appCompatImageView;
        this.ivSosClose = appCompatImageView2;
        this.rippleBackgroundSos = rippleBackground;
        this.tvSosDesc = appCompatTextView;
        this.tvSosTitle = appCompatTextView2;
    }

    @NonNull
    public static FragmentSOSBinding bind(@NonNull View view) {
        int i2 = R.id.btnSosCancel;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSosCancel);
        if (appCompatButton != null) {
            i2 = R.id.ivSOS;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivSOS);
            if (appCompatImageView != null) {
                i2 = R.id.ivSosClose;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivSosClose);
                if (appCompatImageView2 != null) {
                    i2 = R.id.rippleBackgroundSos;
                    RippleBackground rippleBackground = (RippleBackground) ViewBindings.findChildViewById(view, R.id.rippleBackgroundSos);
                    if (rippleBackground != null) {
                        i2 = R.id.tvSosDesc;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSosDesc);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvSosTitle;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSosTitle);
                            if (appCompatTextView2 != null) {
                                return new FragmentSOSBinding((FrameLayout) view, appCompatButton, appCompatImageView, appCompatImageView2, rippleBackground, appCompatTextView, appCompatTextView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentSOSBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentSOSBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_s_o_s, viewGroup, false);
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
