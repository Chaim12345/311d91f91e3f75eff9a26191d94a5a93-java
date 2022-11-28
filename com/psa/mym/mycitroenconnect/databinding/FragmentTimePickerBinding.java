package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TimePicker;
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
public final class FragmentTimePickerBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnCancel;
    @NonNull
    public final AppCompatButton btnDone;
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final LinearLayoutCompat layoutTimeBtn;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final TimePicker timePicker;
    @NonNull
    public final AppCompatTextView tvMessage;
    @NonNull
    public final AppCompatTextView tvTitle;

    private FragmentTimePickerBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull TimePicker timePicker, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = frameLayout;
        this.btnCancel = appCompatButton;
        this.btnDone = appCompatButton2;
        this.ivClose = appCompatImageView;
        this.layoutTimeBtn = linearLayoutCompat;
        this.timePicker = timePicker;
        this.tvMessage = appCompatTextView;
        this.tvTitle = appCompatTextView2;
    }

    @NonNull
    public static FragmentTimePickerBinding bind(@NonNull View view) {
        int i2 = R.id.btnCancel;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnCancel);
        if (appCompatButton != null) {
            i2 = R.id.btnDone;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnDone);
            if (appCompatButton2 != null) {
                i2 = R.id.ivClose;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
                if (appCompatImageView != null) {
                    i2 = R.id.layoutTimeBtn;
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutTimeBtn);
                    if (linearLayoutCompat != null) {
                        i2 = R.id.timePicker;
                        TimePicker timePicker = (TimePicker) ViewBindings.findChildViewById(view, R.id.timePicker);
                        if (timePicker != null) {
                            i2 = R.id.tvMessage;
                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvMessage);
                            if (appCompatTextView != null) {
                                i2 = R.id.tvTitle;
                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTitle);
                                if (appCompatTextView2 != null) {
                                    return new FragmentTimePickerBinding((FrameLayout) view, appCompatButton, appCompatButton2, appCompatImageView, linearLayoutCompat, timePicker, appCompatTextView, appCompatTextView2);
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
    public static FragmentTimePickerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentTimePickerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_time_picker, viewGroup, false);
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
