package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentWebViewBinding implements ViewBinding {
    @NonNull
    public final ProgressBinding progress;
    @NonNull
    private final RelativeLayout rootView;
    @NonNull
    public final WebView webView;

    private FragmentWebViewBinding(@NonNull RelativeLayout relativeLayout, @NonNull ProgressBinding progressBinding, @NonNull WebView webView) {
        this.rootView = relativeLayout;
        this.progress = progressBinding;
        this.webView = webView;
    }

    @NonNull
    public static FragmentWebViewBinding bind(@NonNull View view) {
        int i2 = R.id.progress;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.progress);
        if (findChildViewById != null) {
            ProgressBinding bind = ProgressBinding.bind(findChildViewById);
            WebView webView = (WebView) ViewBindings.findChildViewById(view, R.id.webView);
            if (webView != null) {
                return new FragmentWebViewBinding((RelativeLayout) view, bind, webView);
            }
            i2 = R.id.webView;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentWebViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentWebViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_web_view, viewGroup, false);
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
