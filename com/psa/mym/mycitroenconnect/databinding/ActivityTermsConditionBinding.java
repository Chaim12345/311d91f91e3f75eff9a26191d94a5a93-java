package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityTermsConditionBinding implements ViewBinding {
    @NonNull
    public final FragmentContainerView fragmentContainerPeripheral;
    @NonNull
    private final LinearLayout rootView;
    @NonNull
    public final RelativeLayout toolbar;

    private ActivityTermsConditionBinding(@NonNull LinearLayout linearLayout, @NonNull FragmentContainerView fragmentContainerView, @NonNull RelativeLayout relativeLayout) {
        this.rootView = linearLayout;
        this.fragmentContainerPeripheral = fragmentContainerView;
        this.toolbar = relativeLayout;
    }

    @NonNull
    public static ActivityTermsConditionBinding bind(@NonNull View view) {
        int i2 = R.id.fragmentContainerPeripheral;
        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, R.id.fragmentContainerPeripheral);
        if (fragmentContainerView != null) {
            i2 = R.id.toolbar;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.toolbar);
            if (relativeLayout != null) {
                return new ActivityTermsConditionBinding((LinearLayout) view, fragmentContainerView, relativeLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ActivityTermsConditionBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityTermsConditionBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_terms_condition, viewGroup, false);
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
