package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.util.Objects;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutCustomSeekBarBinding implements ViewBinding {
    @NonNull
    public final AppCompatSeekBar csbSeekBar;
    @NonNull
    private final View rootView;
    @NonNull
    public final AppCompatTextView textView1;
    @NonNull
    public final AppCompatTextView textView2;

    private LayoutCustomSeekBarBinding(@NonNull View view, @NonNull AppCompatSeekBar appCompatSeekBar, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = view;
        this.csbSeekBar = appCompatSeekBar;
        this.textView1 = appCompatTextView;
        this.textView2 = appCompatTextView2;
    }

    @NonNull
    public static LayoutCustomSeekBarBinding bind(@NonNull View view) {
        int i2 = R.id.csb_seek_bar;
        AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) ViewBindings.findChildViewById(view, R.id.csb_seek_bar);
        if (appCompatSeekBar != null) {
            i2 = R.id.text_view_1;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.text_view_1);
            if (appCompatTextView != null) {
                i2 = R.id.text_view_2;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.text_view_2);
                if (appCompatTextView2 != null) {
                    return new LayoutCustomSeekBarBinding(view, appCompatSeekBar, appCompatTextView, appCompatTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static LayoutCustomSeekBarBinding inflate(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        Objects.requireNonNull(viewGroup, "parent");
        layoutInflater.inflate(R.layout.layout_custom_seek_bar, viewGroup);
        return bind(viewGroup);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public View getRoot() {
        return this.rootView;
    }
}
