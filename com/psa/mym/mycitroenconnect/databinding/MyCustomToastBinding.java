package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class MyCustomToastBinding implements ViewBinding {
    @NonNull
    public final ImageView imageView1;
    @NonNull
    public final LinearLayout linBg;
    @NonNull
    private final LinearLayout rootView;
    @NonNull
    public final TextView textView1;

    private MyCustomToastBinding(@NonNull LinearLayout linearLayout, @NonNull ImageView imageView, @NonNull LinearLayout linearLayout2, @NonNull TextView textView) {
        this.rootView = linearLayout;
        this.imageView1 = imageView;
        this.linBg = linearLayout2;
        this.textView1 = textView;
    }

    @NonNull
    public static MyCustomToastBinding bind(@NonNull View view) {
        int i2 = R.id.imageView1;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imageView1);
        if (imageView != null) {
            i2 = R.id.lin_bg;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.lin_bg);
            if (linearLayout != null) {
                i2 = R.id.textView1;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.textView1);
                if (textView != null) {
                    return new MyCustomToastBinding((LinearLayout) view, imageView, linearLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static MyCustomToastBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static MyCustomToastBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.my_custom_toast, viewGroup, false);
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
