package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ProgressBinding implements ViewBinding {
    @NonNull
    public final ProgressBar loader;
    @NonNull
    public final TextView loadingMsg;
    @NonNull
    private final LinearLayout rootView;

    private ProgressBinding(@NonNull LinearLayout linearLayout, @NonNull ProgressBar progressBar, @NonNull TextView textView) {
        this.rootView = linearLayout;
        this.loader = progressBar;
        this.loadingMsg = textView;
    }

    @NonNull
    public static ProgressBinding bind(@NonNull View view) {
        int i2 = R.id.loader;
        ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.loader);
        if (progressBar != null) {
            i2 = R.id.loading_msg;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.loading_msg);
            if (textView != null) {
                return new ProgressBinding((LinearLayout) view, progressBar, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ProgressBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ProgressBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.progress, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayout getRoot() {
        return this.rootView;
    }
}
