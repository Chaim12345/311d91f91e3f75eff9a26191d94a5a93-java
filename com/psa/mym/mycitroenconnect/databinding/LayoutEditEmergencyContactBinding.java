package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutEditEmergencyContactBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnDelete;
    @NonNull
    public final AppCompatButton btnSave;
    @NonNull
    public final TextInputEditText edFullName;
    @NonNull
    public final TextInputEditText edtMobileNumber;
    @NonNull
    public final LayoutDashboardModeHeaderBinding layoutEmergEditContactHeader;
    @NonNull
    public final LinearLayoutCompat llBtn;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final TextInputLayout tilFullName;
    @NonNull
    public final TextInputLayout tilMobileNumber;

    private LayoutEditEmergencyContactBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull TextInputEditText textInputEditText, @NonNull TextInputEditText textInputEditText2, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2) {
        this.rootView = constraintLayout;
        this.btnDelete = appCompatButton;
        this.btnSave = appCompatButton2;
        this.edFullName = textInputEditText;
        this.edtMobileNumber = textInputEditText2;
        this.layoutEmergEditContactHeader = layoutDashboardModeHeaderBinding;
        this.llBtn = linearLayoutCompat;
        this.tilFullName = textInputLayout;
        this.tilMobileNumber = textInputLayout2;
    }

    @NonNull
    public static LayoutEditEmergencyContactBinding bind(@NonNull View view) {
        int i2 = R.id.btnDelete;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnDelete);
        if (appCompatButton != null) {
            i2 = R.id.btnSave;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSave);
            if (appCompatButton2 != null) {
                i2 = R.id.edFullName;
                TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edFullName);
                if (textInputEditText != null) {
                    i2 = R.id.edtMobileNumber;
                    TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtMobileNumber);
                    if (textInputEditText2 != null) {
                        i2 = R.id.layoutEmergEditContactHeader;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutEmergEditContactHeader);
                        if (findChildViewById != null) {
                            LayoutDashboardModeHeaderBinding bind = LayoutDashboardModeHeaderBinding.bind(findChildViewById);
                            i2 = R.id.llBtn;
                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llBtn);
                            if (linearLayoutCompat != null) {
                                i2 = R.id.tilFullName;
                                TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.tilFullName);
                                if (textInputLayout != null) {
                                    i2 = R.id.tilMobileNumber;
                                    TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.tilMobileNumber);
                                    if (textInputLayout2 != null) {
                                        return new LayoutEditEmergencyContactBinding((ConstraintLayout) view, appCompatButton, appCompatButton2, textInputEditText, textInputEditText2, bind, linearLayoutCompat, textInputLayout, textInputLayout2);
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
    public static LayoutEditEmergencyContactBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutEditEmergencyContactBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_edit_emergency_contact, viewGroup, false);
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
