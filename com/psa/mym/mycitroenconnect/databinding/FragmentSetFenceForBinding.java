package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textview.MaterialTextView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentSetFenceForBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnCnfrmContinueSet;
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final RecyclerView rvFenceFor;
    @NonNull
    public final MaterialTextView tvChooseFenceType;

    private FragmentSetFenceForBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatButton appCompatButton, @NonNull RecyclerView recyclerView, @NonNull MaterialTextView materialTextView) {
        this.rootView = linearLayoutCompat;
        this.btnCnfrmContinueSet = appCompatButton;
        this.rvFenceFor = recyclerView;
        this.tvChooseFenceType = materialTextView;
    }

    @NonNull
    public static FragmentSetFenceForBinding bind(@NonNull View view) {
        int i2 = R.id.btnCnfrmContinueSet;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnCnfrmContinueSet);
        if (appCompatButton != null) {
            i2 = R.id.rvFenceFor;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvFenceFor);
            if (recyclerView != null) {
                i2 = R.id.tvChooseFenceType;
                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvChooseFenceType);
                if (materialTextView != null) {
                    return new FragmentSetFenceForBinding((LinearLayoutCompat) view, appCompatButton, recyclerView, materialTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentSetFenceForBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentSetFenceForBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_set_fence_for, viewGroup, false);
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
