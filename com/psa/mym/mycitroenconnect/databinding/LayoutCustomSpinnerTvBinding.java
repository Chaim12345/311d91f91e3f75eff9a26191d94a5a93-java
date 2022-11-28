package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import java.util.Objects;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutCustomSpinnerTvBinding implements ViewBinding {
    @NonNull
    private final AppCompatTextView rootView;

    private LayoutCustomSpinnerTvBinding(@NonNull AppCompatTextView appCompatTextView) {
        this.rootView = appCompatTextView;
    }

    @NonNull
    public static LayoutCustomSpinnerTvBinding bind(@NonNull View view) {
        Objects.requireNonNull(view, "rootView");
        return new LayoutCustomSpinnerTvBinding((AppCompatTextView) view);
    }

    @NonNull
    public static LayoutCustomSpinnerTvBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutCustomSpinnerTvBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_custom_spinner_tv, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public AppCompatTextView getRoot() {
        return this.rootView;
    }
}
