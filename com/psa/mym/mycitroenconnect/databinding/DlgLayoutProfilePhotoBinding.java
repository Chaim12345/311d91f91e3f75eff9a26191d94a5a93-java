package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class DlgLayoutProfilePhotoBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivCamera;
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final AppCompatImageView ivDelete;
    @NonNull
    public final AppCompatImageView ivGallery;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvCameraPhoto;
    @NonNull
    public final AppCompatTextView tvDeletePhoto;
    @NonNull
    public final AppCompatTextView tvGalleryPhoto;
    @NonNull
    public final AppCompatTextView tvProfilePhoto;

    private DlgLayoutProfilePhotoBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull AppCompatImageView appCompatImageView4, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4) {
        this.rootView = constraintLayout;
        this.ivCamera = appCompatImageView;
        this.ivClose = appCompatImageView2;
        this.ivDelete = appCompatImageView3;
        this.ivGallery = appCompatImageView4;
        this.tvCameraPhoto = appCompatTextView;
        this.tvDeletePhoto = appCompatTextView2;
        this.tvGalleryPhoto = appCompatTextView3;
        this.tvProfilePhoto = appCompatTextView4;
    }

    @NonNull
    public static DlgLayoutProfilePhotoBinding bind(@NonNull View view) {
        int i2 = R.id.ivCamera;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCamera);
        if (appCompatImageView != null) {
            i2 = R.id.ivClose;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
            if (appCompatImageView2 != null) {
                i2 = R.id.ivDelete;
                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivDelete);
                if (appCompatImageView3 != null) {
                    i2 = R.id.ivGallery;
                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivGallery);
                    if (appCompatImageView4 != null) {
                        i2 = R.id.tvCameraPhoto;
                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCameraPhoto);
                        if (appCompatTextView != null) {
                            i2 = R.id.tvDeletePhoto;
                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDeletePhoto);
                            if (appCompatTextView2 != null) {
                                i2 = R.id.tvGalleryPhoto;
                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvGalleryPhoto);
                                if (appCompatTextView3 != null) {
                                    i2 = R.id.tvProfilePhoto;
                                    AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvProfilePhoto);
                                    if (appCompatTextView4 != null) {
                                        return new DlgLayoutProfilePhotoBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4);
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
    public static DlgLayoutProfilePhotoBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DlgLayoutProfilePhotoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dlg_layout_profile_photo, viewGroup, false);
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
