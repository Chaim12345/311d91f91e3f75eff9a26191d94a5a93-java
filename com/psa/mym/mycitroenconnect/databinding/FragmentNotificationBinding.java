package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import java.util.Objects;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentNotificationBinding implements ViewBinding {
    @NonNull
    private final FrameLayout rootView;

    private FragmentNotificationBinding(@NonNull FrameLayout frameLayout) {
        this.rootView = frameLayout;
    }

    @NonNull
    public static FragmentNotificationBinding bind(@NonNull View view) {
        Objects.requireNonNull(view, "rootView");
        return new FragmentNotificationBinding((FrameLayout) view);
    }

    @NonNull
    public static FragmentNotificationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentNotificationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_notification, viewGroup, false);
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
