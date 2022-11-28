package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.psa.mym.mycitroenconnect.views.LoadingDots;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CustomProgressDialogBinding implements ViewBinding {
    @NonNull
    public final LoadingDots loadingDots;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvMsg;

    private CustomProgressDialogBinding(@NonNull ConstraintLayout constraintLayout, @NonNull LoadingDots loadingDots, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.loadingDots = loadingDots;
        this.tvMsg = appCompatTextView;
    }

    @NonNull
    public static CustomProgressDialogBinding bind(@NonNull View view) {
        int i2 = R.id.loadingDots;
        LoadingDots loadingDots = (LoadingDots) ViewBindings.findChildViewById(view, R.id.loadingDots);
        if (loadingDots != null) {
            i2 = R.id.tvMsg;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvMsg);
            if (appCompatTextView != null) {
                return new CustomProgressDialogBinding((ConstraintLayout) view, loadingDots, appCompatTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static CustomProgressDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CustomProgressDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.custom_progress_dialog, viewGroup, false);
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
