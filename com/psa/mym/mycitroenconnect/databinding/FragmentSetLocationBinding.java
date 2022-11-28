package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentSetLocationBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnConfirmLocation;
    @NonNull
    public final ConstraintLayout clEdit;
    @NonNull
    public final MaterialCardView cvDestination;
    @NonNull
    public final MaterialCardView cvSource;
    @NonNull
    public final View dottedLine;
    @NonNull
    public final AppCompatEditText edtDestination;
    @NonNull
    public final AppCompatEditText edtSource;
    @NonNull
    public final AppCompatImageView ivDestination;
    @NonNull
    public final AppCompatImageView ivSource;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final RecyclerView rvSearchResult;
    @NonNull
    public final AppCompatTextView tvSearchResult;

    private FragmentSetLocationBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull ConstraintLayout constraintLayout2, @NonNull MaterialCardView materialCardView, @NonNull MaterialCardView materialCardView2, @NonNull View view, @NonNull AppCompatEditText appCompatEditText, @NonNull AppCompatEditText appCompatEditText2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull RecyclerView recyclerView, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.btnConfirmLocation = appCompatButton;
        this.clEdit = constraintLayout2;
        this.cvDestination = materialCardView;
        this.cvSource = materialCardView2;
        this.dottedLine = view;
        this.edtDestination = appCompatEditText;
        this.edtSource = appCompatEditText2;
        this.ivDestination = appCompatImageView;
        this.ivSource = appCompatImageView2;
        this.rvSearchResult = recyclerView;
        this.tvSearchResult = appCompatTextView;
    }

    @NonNull
    public static FragmentSetLocationBinding bind(@NonNull View view) {
        int i2 = R.id.btnConfirmLocation;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnConfirmLocation);
        if (appCompatButton != null) {
            i2 = R.id.clEdit;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clEdit);
            if (constraintLayout != null) {
                i2 = R.id.cvDestination;
                MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, R.id.cvDestination);
                if (materialCardView != null) {
                    i2 = R.id.cvSource;
                    MaterialCardView materialCardView2 = (MaterialCardView) ViewBindings.findChildViewById(view, R.id.cvSource);
                    if (materialCardView2 != null) {
                        i2 = R.id.dottedLine;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.dottedLine);
                        if (findChildViewById != null) {
                            i2 = R.id.edtDestination;
                            AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, R.id.edtDestination);
                            if (appCompatEditText != null) {
                                i2 = R.id.edtSource;
                                AppCompatEditText appCompatEditText2 = (AppCompatEditText) ViewBindings.findChildViewById(view, R.id.edtSource);
                                if (appCompatEditText2 != null) {
                                    i2 = R.id.ivDestination;
                                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivDestination);
                                    if (appCompatImageView != null) {
                                        i2 = R.id.ivSource;
                                        AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivSource);
                                        if (appCompatImageView2 != null) {
                                            i2 = R.id.rvSearchResult;
                                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvSearchResult);
                                            if (recyclerView != null) {
                                                i2 = R.id.tvSearchResult;
                                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSearchResult);
                                                if (appCompatTextView != null) {
                                                    return new FragmentSetLocationBinding((ConstraintLayout) view, appCompatButton, constraintLayout, materialCardView, materialCardView2, findChildViewById, appCompatEditText, appCompatEditText2, appCompatImageView, appCompatImageView2, recyclerView, appCompatTextView);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentSetLocationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentSetLocationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_set_location, viewGroup, false);
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
