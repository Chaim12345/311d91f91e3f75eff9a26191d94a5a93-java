package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentAddContactConfirmationBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnNo;
    @NonNull
    public final AppCompatButton btnYes;
    @NonNull
    public final AppCompatImageView ivAddClose;
    @NonNull
    public final AppCompatImageView ivImgSuccess;
    @NonNull
    public final ConstraintLayout layoutCnfrmtnSuccess;
    @NonNull
    public final LinearLayoutCompat layoutConfirmtionBtn;
    @NonNull
    public final ConstraintLayout layoutSelectedContact;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final RecyclerView rvConfirmContacts;
    @NonNull
    public final AppCompatTextView tvConfirmationText;
    @NonNull
    public final AppCompatTextView tvSelectedContact;
    @NonNull
    public final AppCompatTextView tvSuccessConfirmation;
    @NonNull
    public final View viewHorizontalLine;

    private FragmentAddContactConfirmationBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull ConstraintLayout constraintLayout, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull ConstraintLayout constraintLayout2, @NonNull RecyclerView recyclerView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull View view) {
        this.rootView = frameLayout;
        this.btnNo = appCompatButton;
        this.btnYes = appCompatButton2;
        this.ivAddClose = appCompatImageView;
        this.ivImgSuccess = appCompatImageView2;
        this.layoutCnfrmtnSuccess = constraintLayout;
        this.layoutConfirmtionBtn = linearLayoutCompat;
        this.layoutSelectedContact = constraintLayout2;
        this.rvConfirmContacts = recyclerView;
        this.tvConfirmationText = appCompatTextView;
        this.tvSelectedContact = appCompatTextView2;
        this.tvSuccessConfirmation = appCompatTextView3;
        this.viewHorizontalLine = view;
    }

    @NonNull
    public static FragmentAddContactConfirmationBinding bind(@NonNull View view) {
        int i2 = R.id.btnNo;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnNo);
        if (appCompatButton != null) {
            i2 = R.id.btnYes;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnYes);
            if (appCompatButton2 != null) {
                i2 = R.id.ivAddClose;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivAddClose);
                if (appCompatImageView != null) {
                    i2 = R.id.ivImgSuccess;
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivImgSuccess);
                    if (appCompatImageView2 != null) {
                        i2 = R.id.layoutCnfrmtnSuccess;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutCnfrmtnSuccess);
                        if (constraintLayout != null) {
                            i2 = R.id.layoutConfirmtionBtn;
                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutConfirmtionBtn);
                            if (linearLayoutCompat != null) {
                                i2 = R.id.layoutSelectedContact;
                                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutSelectedContact);
                                if (constraintLayout2 != null) {
                                    i2 = R.id.rvConfirmContacts;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvConfirmContacts);
                                    if (recyclerView != null) {
                                        i2 = R.id.tvConfirmationText;
                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvConfirmationText);
                                        if (appCompatTextView != null) {
                                            i2 = R.id.tvSelectedContact;
                                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSelectedContact);
                                            if (appCompatTextView2 != null) {
                                                i2 = R.id.tvSuccessConfirmation;
                                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSuccessConfirmation);
                                                if (appCompatTextView3 != null) {
                                                    i2 = R.id.viewHorizontalLine;
                                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.viewHorizontalLine);
                                                    if (findChildViewById != null) {
                                                        return new FragmentAddContactConfirmationBinding((FrameLayout) view, appCompatButton, appCompatButton2, appCompatImageView, appCompatImageView2, constraintLayout, linearLayoutCompat, constraintLayout2, recyclerView, appCompatTextView, appCompatTextView2, appCompatTextView3, findChildViewById);
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
    public static FragmentAddContactConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentAddContactConfirmationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_add_contact_confirmation, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
