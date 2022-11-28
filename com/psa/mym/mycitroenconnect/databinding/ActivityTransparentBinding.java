package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import java.util.Objects;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityTransparentBinding implements ViewBinding {
    @NonNull
    private final ConstraintLayout rootView;

    private ActivityTransparentBinding(@NonNull ConstraintLayout constraintLayout) {
        this.rootView = constraintLayout;
    }

    @NonNull
    public static ActivityTransparentBinding bind(@NonNull View view) {
        Objects.requireNonNull(view, "rootView");
        return new ActivityTransparentBinding((ConstraintLayout) view);
    }

    @NonNull
    public static ActivityTransparentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityTransparentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_transparent, viewGroup, false);
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
