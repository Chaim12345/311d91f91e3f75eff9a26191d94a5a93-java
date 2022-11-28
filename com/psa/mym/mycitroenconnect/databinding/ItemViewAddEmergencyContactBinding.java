package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewAddEmergencyContactBinding implements ViewBinding {
    @NonNull
    public final AppCompatCheckBox chkAddEmergencyContact;
    @NonNull
    public final ConstraintLayout layoutAddEmergContact;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvAddContactName;
    @NonNull
    public final AppCompatTextView tvAddContactNo;

    private ItemViewAddEmergencyContactBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatCheckBox appCompatCheckBox, @NonNull ConstraintLayout constraintLayout2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = constraintLayout;
        this.chkAddEmergencyContact = appCompatCheckBox;
        this.layoutAddEmergContact = constraintLayout2;
        this.tvAddContactName = appCompatTextView;
        this.tvAddContactNo = appCompatTextView2;
    }

    @NonNull
    public static ItemViewAddEmergencyContactBinding bind(@NonNull View view) {
        int i2 = R.id.chkAddEmergencyContact;
        AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) ViewBindings.findChildViewById(view, R.id.chkAddEmergencyContact);
        if (appCompatCheckBox != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i2 = R.id.tvAddContactName;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAddContactName);
            if (appCompatTextView != null) {
                i2 = R.id.tvAddContactNo;
                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAddContactNo);
                if (appCompatTextView2 != null) {
                    return new ItemViewAddEmergencyContactBinding(constraintLayout, appCompatCheckBox, constraintLayout, appCompatTextView, appCompatTextView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewAddEmergencyContactBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewAddEmergencyContactBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_add_emergency_contact, viewGroup, false);
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
