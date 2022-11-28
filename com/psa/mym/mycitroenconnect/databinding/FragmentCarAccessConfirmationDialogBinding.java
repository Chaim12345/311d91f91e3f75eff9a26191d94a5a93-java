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
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentCarAccessConfirmationDialogBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnNo;
    @NonNull
    public final AppCompatButton btnYes;
    @NonNull
    public final ItemViewCarAccessNotGrantedBinding carAccessItemLayout;
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final AppCompatImageView ivImgSuccess;
    @NonNull
    public final ConstraintLayout layoutCarCnfrmtnSuccess;
    @NonNull
    public final LinearLayoutCompat layoutConfirmtionBtn;
    @NonNull
    public final ConstraintLayout layoutSelectedCar;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final AppCompatTextView tvCarSuccessConfirmation;
    @NonNull
    public final AppCompatTextView tvConfirmationText;
    @NonNull
    public final AppCompatTextView tvSelectedCars;
    @NonNull
    public final View viewHorizontalLine;

    private FragmentCarAccessConfirmationDialogBinding(@NonNull FrameLayout frameLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull ItemViewCarAccessNotGrantedBinding itemViewCarAccessNotGrantedBinding, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull ConstraintLayout constraintLayout, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull ConstraintLayout constraintLayout2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull View view) {
        this.rootView = frameLayout;
        this.btnNo = appCompatButton;
        this.btnYes = appCompatButton2;
        this.carAccessItemLayout = itemViewCarAccessNotGrantedBinding;
        this.ivClose = appCompatImageView;
        this.ivImgSuccess = appCompatImageView2;
        this.layoutCarCnfrmtnSuccess = constraintLayout;
        this.layoutConfirmtionBtn = linearLayoutCompat;
        this.layoutSelectedCar = constraintLayout2;
        this.tvCarSuccessConfirmation = appCompatTextView;
        this.tvConfirmationText = appCompatTextView2;
        this.tvSelectedCars = appCompatTextView3;
        this.viewHorizontalLine = view;
    }

    @NonNull
    public static FragmentCarAccessConfirmationDialogBinding bind(@NonNull View view) {
        int i2 = R.id.btnNo;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnNo);
        if (appCompatButton != null) {
            i2 = R.id.btnYes;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnYes);
            if (appCompatButton2 != null) {
                i2 = R.id.carAccessItemLayout;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.carAccessItemLayout);
                if (findChildViewById != null) {
                    ItemViewCarAccessNotGrantedBinding bind = ItemViewCarAccessNotGrantedBinding.bind(findChildViewById);
                    i2 = R.id.ivClose;
                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
                    if (appCompatImageView != null) {
                        i2 = R.id.ivImgSuccess;
                        AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivImgSuccess);
                        if (appCompatImageView2 != null) {
                            i2 = R.id.layoutCarCnfrmtnSuccess;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutCarCnfrmtnSuccess);
                            if (constraintLayout != null) {
                                i2 = R.id.layoutConfirmtionBtn;
                                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutConfirmtionBtn);
                                if (linearLayoutCompat != null) {
                                    i2 = R.id.layoutSelectedCar;
                                    ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutSelectedCar);
                                    if (constraintLayout2 != null) {
                                        i2 = R.id.tvCarSuccessConfirmation;
                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCarSuccessConfirmation);
                                        if (appCompatTextView != null) {
                                            i2 = R.id.tvConfirmationText;
                                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvConfirmationText);
                                            if (appCompatTextView2 != null) {
                                                i2 = R.id.tvSelectedCars;
                                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSelectedCars);
                                                if (appCompatTextView3 != null) {
                                                    i2 = R.id.viewHorizontalLine;
                                                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.viewHorizontalLine);
                                                    if (findChildViewById2 != null) {
                                                        return new FragmentCarAccessConfirmationDialogBinding((FrameLayout) view, appCompatButton, appCompatButton2, bind, appCompatImageView, appCompatImageView2, constraintLayout, linearLayoutCompat, constraintLayout2, appCompatTextView, appCompatTextView2, appCompatTextView3, findChildViewById2);
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
    public static FragmentCarAccessConfirmationDialogBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentCarAccessConfirmationDialogBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_car_access_confirmation_dialog, viewGroup, false);
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
