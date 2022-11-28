package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutSingleDialogBinding implements ViewBinding {
    @NonNull
    public final CardView alertContainer;
    @NonNull
    public final AppCompatImageView ivTitle;
    @NonNull
    public final LinearLayout linTitle;
    @NonNull
    private final CardView rootView;
    @NonNull
    public final TextView tvCancel;
    @NonNull
    public final TextView tvConfirm;
    @NonNull
    public final TextView tvDate;
    @NonNull
    public final TextView tvMsg;
    @NonNull
    public final TextView tvTitle;

    private LayoutSingleDialogBinding(@NonNull CardView cardView, @NonNull CardView cardView2, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayout linearLayout, @NonNull TextView textView, @NonNull TextView textView2, @NonNull TextView textView3, @NonNull TextView textView4, @NonNull TextView textView5) {
        this.rootView = cardView;
        this.alertContainer = cardView2;
        this.ivTitle = appCompatImageView;
        this.linTitle = linearLayout;
        this.tvCancel = textView;
        this.tvConfirm = textView2;
        this.tvDate = textView3;
        this.tvMsg = textView4;
        this.tvTitle = textView5;
    }

    @NonNull
    public static LayoutSingleDialogBinding bind(@NonNull View view) {
        CardView cardView = (CardView) view;
        int i2 = R.id.ivTitle;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivTitle);
        if (appCompatImageView != null) {
            i2 = R.id.linTitle;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linTitle);
            if (linearLayout != null) {
                i2 = R.id.tvCancel;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvCancel);
                if (textView != null) {
                    i2 = R.id.tvConfirm;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvConfirm);
                    if (textView2 != null) {
                        i2 = R.id.tvDate;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tvDate);
                        if (textView3 != null) {
                            i2 = R.id.tv_msg;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_msg);
                            if (textView4 != null) {
                                i2 = R.id.tv_title;
                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                if (textView5 != null) {
                                    return new LayoutSingleDialogBinding(cardView, cardView, appCompatImageView, linearLayout, textView, textView2, textView3, textView4, textView5);
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
    public static LayoutSingleDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutSingleDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_single_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public CardView getRoot() {
        return this.rootView;
    }
}
