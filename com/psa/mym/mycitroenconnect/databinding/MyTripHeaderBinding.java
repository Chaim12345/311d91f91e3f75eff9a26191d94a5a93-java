package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MyTripHeaderBinding implements ViewBinding {
    @NonNull
    private final Toolbar rootView;
    @NonNull
    public final TextView tvMyTripHeader;
    @NonNull
    public final AppCompatImageView viewBack;

    private MyTripHeaderBinding(@NonNull Toolbar toolbar, @NonNull TextView textView, @NonNull AppCompatImageView appCompatImageView) {
        this.rootView = toolbar;
        this.tvMyTripHeader = textView;
        this.viewBack = appCompatImageView;
    }

    @NonNull
    public static MyTripHeaderBinding bind(@NonNull View view) {
        int i2 = R.id.tvMyTripHeader;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvMyTripHeader);
        if (textView != null) {
            i2 = R.id.viewBack;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.viewBack);
            if (appCompatImageView != null) {
                return new MyTripHeaderBinding((Toolbar) view, textView, appCompatImageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static MyTripHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static MyTripHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.my_trip_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public Toolbar getRoot() {
        return this.rootView;
    }
}
