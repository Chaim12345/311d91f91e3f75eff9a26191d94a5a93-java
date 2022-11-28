package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textview.MaterialTextView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemShareAppBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivIcon;
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final MaterialTextView tvAppName;

    private ItemShareAppBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatImageView appCompatImageView, @NonNull MaterialTextView materialTextView) {
        this.rootView = linearLayoutCompat;
        this.ivIcon = appCompatImageView;
        this.tvAppName = materialTextView;
    }

    @NonNull
    public static ItemShareAppBinding bind(@NonNull View view) {
        int i2 = R.id.ivIcon;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivIcon);
        if (appCompatImageView != null) {
            i2 = R.id.tvAppName;
            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvAppName);
            if (materialTextView != null) {
                return new ItemShareAppBinding((LinearLayoutCompat) view, appCompatImageView, materialTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemShareAppBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemShareAppBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_share_app, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }
}
