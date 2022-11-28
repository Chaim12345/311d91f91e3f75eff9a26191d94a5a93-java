package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewMyCarStatusAlertChildBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivIcon;
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final AppCompatTextView tvMessage;
    @NonNull
    public final AppCompatTextView tvTime;
    @NonNull
    public final AppCompatTextView tvTitle;

    private ItemViewMyCarStatusAlertChildBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3) {
        this.rootView = linearLayoutCompat;
        this.ivIcon = appCompatImageView;
        this.tvMessage = appCompatTextView;
        this.tvTime = appCompatTextView2;
        this.tvTitle = appCompatTextView3;
    }

    @NonNull
    public static ItemViewMyCarStatusAlertChildBinding bind(@NonNull View view) {
        int i2 = R.id.ivIcon;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivIcon);
        if (appCompatImageView != null) {
            i2 = R.id.tvMessage;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvMessage);
            if (appCompatTextView != null) {
                i2 = R.id.tvTime;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTime);
                if (appCompatTextView2 != null) {
                    i2 = R.id.tvTitle;
                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTitle);
                    if (appCompatTextView3 != null) {
                        return new ItemViewMyCarStatusAlertChildBinding((LinearLayoutCompat) view, appCompatImageView, appCompatTextView, appCompatTextView2, appCompatTextView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewMyCarStatusAlertChildBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewMyCarStatusAlertChildBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_my_car_status_alert_child, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }
}
