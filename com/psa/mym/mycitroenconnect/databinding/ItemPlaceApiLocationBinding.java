package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textview.MaterialTextView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemPlaceApiLocationBinding implements ViewBinding {
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final MaterialTextView tvAddress;
    @NonNull
    public final MaterialTextView tvLocation;

    private ItemPlaceApiLocationBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull MaterialTextView materialTextView, @NonNull MaterialTextView materialTextView2) {
        this.rootView = linearLayoutCompat;
        this.tvAddress = materialTextView;
        this.tvLocation = materialTextView2;
    }

    @NonNull
    public static ItemPlaceApiLocationBinding bind(@NonNull View view) {
        int i2 = R.id.tvAddress;
        MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvAddress);
        if (materialTextView != null) {
            i2 = R.id.tvLocation;
            MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvLocation);
            if (materialTextView2 != null) {
                return new ItemPlaceApiLocationBinding((LinearLayoutCompat) view, materialTextView, materialTextView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemPlaceApiLocationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemPlaceApiLocationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_place_api_location, viewGroup, false);
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
