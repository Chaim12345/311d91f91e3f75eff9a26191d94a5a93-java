package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewNotificationBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivType;
    @NonNull
    public final LinearLayout llDetails;
    @NonNull
    public final RelativeLayout llNotification;
    @NonNull
    public final AppCompatTextView message;
    @NonNull
    private final RelativeLayout rootView;
    @NonNull
    public final AppCompatTextView time;
    @NonNull
    public final AppCompatTextView title;
    @NonNull
    public final View unreadDot;

    private ItemViewNotificationBinding(@NonNull RelativeLayout relativeLayout, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayout linearLayout, @NonNull RelativeLayout relativeLayout2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull View view) {
        this.rootView = relativeLayout;
        this.ivType = appCompatImageView;
        this.llDetails = linearLayout;
        this.llNotification = relativeLayout2;
        this.message = appCompatTextView;
        this.time = appCompatTextView2;
        this.title = appCompatTextView3;
        this.unreadDot = view;
    }

    @NonNull
    public static ItemViewNotificationBinding bind(@NonNull View view) {
        int i2 = R.id.ivType;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivType);
        if (appCompatImageView != null) {
            i2 = R.id.llDetails;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.llDetails);
            if (linearLayout != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i2 = R.id.message;
                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.message);
                if (appCompatTextView != null) {
                    i2 = R.id.time;
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.time);
                    if (appCompatTextView2 != null) {
                        i2 = R.id.title;
                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.title);
                        if (appCompatTextView3 != null) {
                            i2 = R.id.unreadDot;
                            View findChildViewById = ViewBindings.findChildViewById(view, R.id.unreadDot);
                            if (findChildViewById != null) {
                                return new ItemViewNotificationBinding(relativeLayout, appCompatImageView, linearLayout, relativeLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, findChildViewById);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewNotificationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewNotificationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_notification, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
