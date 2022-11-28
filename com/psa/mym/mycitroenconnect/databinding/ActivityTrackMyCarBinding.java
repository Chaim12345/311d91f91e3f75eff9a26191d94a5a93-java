package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityTrackMyCarBinding implements ViewBinding {
    @NonNull
    public final RelativeLayout container;
    @NonNull
    public final RelativeLayout rlMap;
    @NonNull
    private final RelativeLayout rootView;

    private ActivityTrackMyCarBinding(@NonNull RelativeLayout relativeLayout, @NonNull RelativeLayout relativeLayout2, @NonNull RelativeLayout relativeLayout3) {
        this.rootView = relativeLayout;
        this.container = relativeLayout2;
        this.rlMap = relativeLayout3;
    }

    @NonNull
    public static ActivityTrackMyCarBinding bind(@NonNull View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_map);
        if (relativeLayout2 != null) {
            return new ActivityTrackMyCarBinding(relativeLayout, relativeLayout, relativeLayout2);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.rl_map)));
    }

    @NonNull
    public static ActivityTrackMyCarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityTrackMyCarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_track_my_car, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
