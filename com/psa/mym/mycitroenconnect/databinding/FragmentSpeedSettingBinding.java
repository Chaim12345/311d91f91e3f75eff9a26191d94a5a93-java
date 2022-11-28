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
import com.psa.mym.mycitroenconnect.views.custom_seek_bar.CustomSeekBar;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentSpeedSettingBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnSpeedCancel;
    @NonNull
    public final AppCompatButton btnSpeedDone;
    @NonNull
    public final CustomSeekBar csbSeekBar;
    @NonNull
    public final AppCompatImageView ivCloseSpeed;
    @NonNull
    public final LinearLayoutCompat layoutSpeedBtn;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvSliderToolTip;
    @NonNull
    public final AppCompatTextView tvSpeedLimitTitle;
    @NonNull
    public final AppCompatTextView tvSpeedSet;

    private FragmentSpeedSettingBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull CustomSeekBar customSeekBar, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3) {
        this.rootView = frameLayout;
        this.btnSpeedCancel = appCompatButton;
        this.btnSpeedDone = appCompatButton2;
        this.csbSeekBar = customSeekBar;
        this.ivCloseSpeed = appCompatImageView;
        this.layoutSpeedBtn = linearLayoutCompat;
        this.tvSliderToolTip = appCompatTextView;
        this.tvSpeedLimitTitle = appCompatTextView2;
        this.tvSpeedSet = appCompatTextView3;
    }

    @NonNull
    public static FragmentSpeedSettingBinding bind(@NonNull View view) {
        int i2 = R.id.btnSpeedCancel;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSpeedCancel);
        if (appCompatButton != null) {
            i2 = R.id.btnSpeedDone;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSpeedDone);
            if (appCompatButton2 != null) {
                i2 = R.id.csbSeekBar;
                CustomSeekBar customSeekBar = (CustomSeekBar) ViewBindings.findChildViewById(view, R.id.csbSeekBar);
                if (customSeekBar != null) {
                    i2 = R.id.ivCloseSpeed;
                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCloseSpeed);
                    if (appCompatImageView != null) {
                        i2 = R.id.layoutSpeedBtn;
                        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutSpeedBtn);
                        if (linearLayoutCompat != null) {
                            i2 = R.id.tvSliderToolTip;
                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSliderToolTip);
                            if (appCompatTextView != null) {
                                i2 = R.id.tvSpeedLimitTitle;
                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSpeedLimitTitle);
                                if (appCompatTextView2 != null) {
                                    i2 = R.id.tvSpeedSet;
                                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSpeedSet);
                                    if (appCompatTextView3 != null) {
                                        return new FragmentSpeedSettingBinding((FrameLayout) view, appCompatButton, appCompatButton2, customSeekBar, appCompatImageView, linearLayoutCompat, appCompatTextView, appCompatTextView2, appCompatTextView3);
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
    public static FragmentSpeedSettingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentSpeedSettingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_speed_setting, viewGroup, false);
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
