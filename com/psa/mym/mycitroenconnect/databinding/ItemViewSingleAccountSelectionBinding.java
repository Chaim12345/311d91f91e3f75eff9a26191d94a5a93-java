package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.radiobutton.MaterialRadioButton;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewSingleAccountSelectionBinding implements ViewBinding {
    @NonNull
    public final ConstraintLayout llSelectAccount;
    @NonNull
    public final MaterialRadioButton rbSelect;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvName;
    @NonNull
    public final AppCompatTextView tvNumber;

    private ItemViewSingleAccountSelectionBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout2, @NonNull MaterialRadioButton materialRadioButton, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = constraintLayout;
        this.llSelectAccount = constraintLayout2;
        this.rbSelect = materialRadioButton;
        this.tvName = appCompatTextView;
        this.tvNumber = appCompatTextView2;
    }

    @NonNull
    public static ItemViewSingleAccountSelectionBinding bind(@NonNull View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i2 = R.id.rbSelect;
        MaterialRadioButton materialRadioButton = (MaterialRadioButton) ViewBindings.findChildViewById(view, R.id.rbSelect);
        if (materialRadioButton != null) {
            i2 = R.id.tvName;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvName);
            if (appCompatTextView != null) {
                i2 = R.id.tvNumber;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvNumber);
                if (appCompatTextView2 != null) {
                    return new ItemViewSingleAccountSelectionBinding(constraintLayout, constraintLayout, materialRadioButton, appCompatTextView, appCompatTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewSingleAccountSelectionBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewSingleAccountSelectionBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_single_account_selection, viewGroup, false);
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
