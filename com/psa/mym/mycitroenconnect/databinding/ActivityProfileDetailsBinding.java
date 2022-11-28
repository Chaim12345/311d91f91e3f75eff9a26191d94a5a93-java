package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityProfileDetailsBinding implements ViewBinding {
    @NonNull
    public final LayoutEditEmergencyContactBinding layoutEmergencyContactDetails;
    @NonNull
    private final ConstraintLayout rootView;

    private ActivityProfileDetailsBinding(@NonNull ConstraintLayout constraintLayout, @NonNull LayoutEditEmergencyContactBinding layoutEditEmergencyContactBinding) {
        this.rootView = constraintLayout;
        this.layoutEmergencyContactDetails = layoutEditEmergencyContactBinding;
    }

    @NonNull
    public static ActivityProfileDetailsBinding bind(@NonNull View view) {
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutEmergencyContactDetails);
        if (findChildViewById != null) {
            return new ActivityProfileDetailsBinding((ConstraintLayout) view, LayoutEditEmergencyContactBinding.bind(findChildViewById));
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.layoutEmergencyContactDetails)));
    }

    @NonNull
    public static ActivityProfileDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityProfileDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_profile_details, viewGroup, false);
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
