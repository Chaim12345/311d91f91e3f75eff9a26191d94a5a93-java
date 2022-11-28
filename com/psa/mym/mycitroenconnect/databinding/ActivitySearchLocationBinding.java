package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivitySearchLocationBinding implements ViewBinding {
    @NonNull
    public final MaterialCardView cvSearch;
    @NonNull
    public final TextInputEditText edtSearchLocation;
    @NonNull
    public final AppCompatImageView ivBack;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final RecyclerView rvLocation;
    @NonNull
    public final Toolbar toolbar;

    private ActivitySearchLocationBinding(@NonNull ConstraintLayout constraintLayout, @NonNull MaterialCardView materialCardView, @NonNull TextInputEditText textInputEditText, @NonNull AppCompatImageView appCompatImageView, @NonNull RecyclerView recyclerView, @NonNull Toolbar toolbar) {
        this.rootView = constraintLayout;
        this.cvSearch = materialCardView;
        this.edtSearchLocation = textInputEditText;
        this.ivBack = appCompatImageView;
        this.rvLocation = recyclerView;
        this.toolbar = toolbar;
    }

    @NonNull
    public static ActivitySearchLocationBinding bind(@NonNull View view) {
        int i2 = R.id.cvSearch;
        MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, R.id.cvSearch);
        if (materialCardView != null) {
            i2 = R.id.edtSearchLocation;
            TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtSearchLocation);
            if (textInputEditText != null) {
                i2 = R.id.ivBack;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivBack);
                if (appCompatImageView != null) {
                    i2 = R.id.rvLocation;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvLocation);
                    if (recyclerView != null) {
                        i2 = R.id.toolbar;
                        Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
                        if (toolbar != null) {
                            return new ActivitySearchLocationBinding((ConstraintLayout) view, materialCardView, textInputEditText, appCompatImageView, recyclerView, toolbar);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ActivitySearchLocationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivitySearchLocationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_search_location, viewGroup, false);
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
