package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class BottomAppBarBinding implements ViewBinding {
    @NonNull
    public final BottomNavigationView bottomNavView;
    @NonNull
    public final ConstraintLayout constraintLayout;
    @NonNull
    public final FloatingActionButton fab;
    @NonNull
    private final CoordinatorLayout rootView;

    private BottomAppBarBinding(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView bottomNavigationView, @NonNull ConstraintLayout constraintLayout, @NonNull FloatingActionButton floatingActionButton) {
        this.rootView = coordinatorLayout;
        this.bottomNavView = bottomNavigationView;
        this.constraintLayout = constraintLayout;
        this.fab = floatingActionButton;
    }

    @NonNull
    public static BottomAppBarBinding bind(@NonNull View view) {
        int i2 = R.id.bottomNavView;
        BottomNavigationView bottomNavigationView = (BottomNavigationView) ViewBindings.findChildViewById(view, R.id.bottomNavView);
        if (bottomNavigationView != null) {
            i2 = R.id.constraintLayout;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.constraintLayout);
            if (constraintLayout != null) {
                i2 = R.id.fab;
                FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.findChildViewById(view, R.id.fab);
                if (floatingActionButton != null) {
                    return new BottomAppBarBinding((CoordinatorLayout) view, bottomNavigationView, constraintLayout, floatingActionButton);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static BottomAppBarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static BottomAppBarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.bottom_app_bar, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }
}
