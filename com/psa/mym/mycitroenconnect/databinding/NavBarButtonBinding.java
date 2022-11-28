package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class NavBarButtonBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnNavSignOut;
    @NonNull
    private final ConstraintLayout rootView;

    private NavBarButtonBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton) {
        this.rootView = constraintLayout;
        this.btnNavSignOut = appCompatButton;
    }

    @NonNull
    public static NavBarButtonBinding bind(@NonNull View view) {
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnNavSignOut);
        if (appCompatButton != null) {
            return new NavBarButtonBinding((ConstraintLayout) view, appCompatButton);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.btnNavSignOut)));
    }

    @NonNull
    public static NavBarButtonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static NavBarButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.nav_bar_button, viewGroup, false);
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
