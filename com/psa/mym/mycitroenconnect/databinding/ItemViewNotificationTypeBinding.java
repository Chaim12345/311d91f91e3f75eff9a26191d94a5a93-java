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
public final class ItemViewNotificationTypeBinding implements ViewBinding {
    @NonNull
    private final AppCompatTextView rootView;
    @NonNull
    public final AppCompatTextView tvNotificationType;

    private ItemViewNotificationTypeBinding(@NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = appCompatTextView;
        this.tvNotificationType = appCompatTextView2;
    }

    @NonNull
    public static ItemViewNotificationTypeBinding bind(@NonNull View view) {
        Objects.requireNonNull(view, "rootView");
        AppCompatTextView appCompatTextView = (AppCompatTextView) view;
        return new ItemViewNotificationTypeBinding(appCompatTextView, appCompatTextView);
    }

    @NonNull
    public static ItemViewNotificationTypeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewNotificationTypeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_notification_type, viewGroup, false);
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
