package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentQuickControlBinding implements ViewBinding {
    @NonNull
    public final ConstraintLayout clQCLayout;
    @NonNull
    public final FloatingActionButton fabQuickClose;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final RecyclerView rvQuickControl;
    @NonNull
    public final AppCompatTextView tvQCTitle;

    private FragmentQuickControlBinding(@NonNull FrameLayout frameLayout, @NonNull ConstraintLayout constraintLayout, @NonNull FloatingActionButton floatingActionButton, @NonNull RecyclerView recyclerView, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = frameLayout;
        this.clQCLayout = constraintLayout;
        this.fabQuickClose = floatingActionButton;
        this.rvQuickControl = recyclerView;
        this.tvQCTitle = appCompatTextView;
    }

    @NonNull
    public static FragmentQuickControlBinding bind(@NonNull View view) {
        int i2 = R.id.clQCLayout;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clQCLayout);
        if (constraintLayout != null) {
            i2 = R.id.fabQuickClose;
            FloatingActionButton floatingActionButton = (FloatingActionButton) ViewBindings.findChildViewById(view, R.id.fabQuickClose);
            if (floatingActionButton != null) {
                i2 = R.id.rvQuickControl;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvQuickControl);
                if (recyclerView != null) {
                    i2 = R.id.tvQCTitle;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvQCTitle);
                    if (appCompatTextView != null) {
                        return new FragmentQuickControlBinding((FrameLayout) view, constraintLayout, floatingActionButton, recyclerView, appCompatTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentQuickControlBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentQuickControlBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_quick_control, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
