package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityEditContactBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnDelete;
    @NonNull
    public final AppCompatButton btnSave;
    @NonNull
    public final View divider;
    @NonNull
    public final TextInputEditText edtFullName;
    @NonNull
    public final TextInputEditText edtMobileNumber;
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final AppCompatImageView ivEdit;
    @NonNull
    public final AppCompatImageView ivNameUpdateCheck;
    @NonNull
    public final LayoutDashboardModeHeaderBinding layoutEditContactHeader;
    @NonNull
    public final LinearLayoutCompat llBtn;
    @NonNull
    private final ScrollView rootView;
    @NonNull
    public final RecyclerView rvCarAccess;
    @NonNull
    public final RecyclerView rvOtherAvailableCars;
    @NonNull
    public final TextInputLayout tilMobileNumber;
    @NonNull
    public final AppCompatTextView tvCarAccess;
    @NonNull
    public final AppCompatTextView tvFullName;
    @NonNull
    public final AppCompatTextView tvOtherAvailableCars;
    @NonNull
    public final View viewFullNameLine;

    private ActivityEditContactBinding(@NonNull ScrollView scrollView, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull View view, @NonNull TextInputEditText textInputEditText, @NonNull TextInputEditText textInputEditText2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull RecyclerView recyclerView, @NonNull RecyclerView recyclerView2, @NonNull TextInputLayout textInputLayout, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull View view2) {
        this.rootView = scrollView;
        this.btnDelete = appCompatButton;
        this.btnSave = appCompatButton2;
        this.divider = view;
        this.edtFullName = textInputEditText;
        this.edtMobileNumber = textInputEditText2;
        this.ivClose = appCompatImageView;
        this.ivEdit = appCompatImageView2;
        this.ivNameUpdateCheck = appCompatImageView3;
        this.layoutEditContactHeader = layoutDashboardModeHeaderBinding;
        this.llBtn = linearLayoutCompat;
        this.rvCarAccess = recyclerView;
        this.rvOtherAvailableCars = recyclerView2;
        this.tilMobileNumber = textInputLayout;
        this.tvCarAccess = appCompatTextView;
        this.tvFullName = appCompatTextView2;
        this.tvOtherAvailableCars = appCompatTextView3;
        this.viewFullNameLine = view2;
    }

    @NonNull
    public static ActivityEditContactBinding bind(@NonNull View view) {
        int i2 = R.id.btnDelete;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnDelete);
        if (appCompatButton != null) {
            i2 = R.id.btnSave;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSave);
            if (appCompatButton2 != null) {
                i2 = R.id.divider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                if (findChildViewById != null) {
                    i2 = R.id.edtFullName;
                    TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtFullName);
                    if (textInputEditText != null) {
                        i2 = R.id.edtMobileNumber;
                        TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtMobileNumber);
                        if (textInputEditText2 != null) {
                            i2 = R.id.ivClose;
                            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
                            if (appCompatImageView != null) {
                                i2 = R.id.ivEdit;
                                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivEdit);
                                if (appCompatImageView2 != null) {
                                    i2 = R.id.ivNameUpdateCheck;
                                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivNameUpdateCheck);
                                    if (appCompatImageView3 != null) {
                                        i2 = R.id.layoutEditContactHeader;
                                        View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.layoutEditContactHeader);
                                        if (findChildViewById2 != null) {
                                            LayoutDashboardModeHeaderBinding bind = LayoutDashboardModeHeaderBinding.bind(findChildViewById2);
                                            i2 = R.id.llBtn;
                                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llBtn);
                                            if (linearLayoutCompat != null) {
                                                i2 = R.id.rvCarAccess;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvCarAccess);
                                                if (recyclerView != null) {
                                                    i2 = R.id.rvOtherAvailableCars;
                                                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvOtherAvailableCars);
                                                    if (recyclerView2 != null) {
                                                        i2 = R.id.tilMobileNumber;
                                                        TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.tilMobileNumber);
                                                        if (textInputLayout != null) {
                                                            i2 = R.id.tvCarAccess;
                                                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCarAccess);
                                                            if (appCompatTextView != null) {
                                                                i2 = R.id.tvFullName;
                                                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFullName);
                                                                if (appCompatTextView2 != null) {
                                                                    i2 = R.id.tvOtherAvailableCars;
                                                                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOtherAvailableCars);
                                                                    if (appCompatTextView3 != null) {
                                                                        i2 = R.id.viewFullNameLine;
                                                                        View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.viewFullNameLine);
                                                                        if (findChildViewById3 != null) {
                                                                            return new ActivityEditContactBinding((ScrollView) view, appCompatButton, appCompatButton2, findChildViewById, textInputEditText, textInputEditText2, appCompatImageView, appCompatImageView2, appCompatImageView3, bind, linearLayoutCompat, recyclerView, recyclerView2, textInputLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, findChildViewById3);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ActivityEditContactBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityEditContactBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_edit_contact, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ScrollView getRoot() {
        return this.rootView;
    }
}
