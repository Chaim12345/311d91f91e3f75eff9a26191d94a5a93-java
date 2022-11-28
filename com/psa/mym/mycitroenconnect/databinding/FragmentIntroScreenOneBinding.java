package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentIntroScreenOneBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView iconLayout1;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final TextView title1;
    @NonNull
    public final TextView title2;

    private FragmentIntroScreenOneBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatImageView appCompatImageView, @NonNull TextView textView, @NonNull TextView textView2) {
        this.rootView = constraintLayout;
        this.iconLayout1 = appCompatImageView;
        this.title1 = textView;
        this.title2 = textView2;
    }

    @NonNull
    public static FragmentIntroScreenOneBinding bind(@NonNull View view) {
        int i2 = R.id.icon_layout1;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.icon_layout1);
        if (appCompatImageView != null) {
            i2 = R.id.title1;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title1);
            if (textView != null) {
                i2 = R.id.title2;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title2);
                if (textView2 != null) {
                    return new FragmentIntroScreenOneBinding((ConstraintLayout) view, appCompatImageView, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentIntroScreenOneBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentIntroScreenOneBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_intro_screen_one, viewGroup, false);
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
