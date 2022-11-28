package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.psa.mym.mycitroenconnect.views.ZoomImageView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class DialogImagePreviewBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final ZoomImageView ivPreview;
    @NonNull
    public final RelativeLayout previewParentLayout;
    @NonNull
    public final ProgressBinding progress;
    @NonNull
    private final RelativeLayout rootView;

    private DialogImagePreviewBinding(@NonNull RelativeLayout relativeLayout, @NonNull AppCompatImageView appCompatImageView, @NonNull ZoomImageView zoomImageView, @NonNull RelativeLayout relativeLayout2, @NonNull ProgressBinding progressBinding) {
        this.rootView = relativeLayout;
        this.ivClose = appCompatImageView;
        this.ivPreview = zoomImageView;
        this.previewParentLayout = relativeLayout2;
        this.progress = progressBinding;
    }

    @NonNull
    public static DialogImagePreviewBinding bind(@NonNull View view) {
        int i2 = R.id.ivClose;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
        if (appCompatImageView != null) {
            i2 = R.id.ivPreview;
            ZoomImageView zoomImageView = (ZoomImageView) ViewBindings.findChildViewById(view, R.id.ivPreview);
            if (zoomImageView != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i2 = R.id.progress;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.progress);
                if (findChildViewById != null) {
                    return new DialogImagePreviewBinding(relativeLayout, appCompatImageView, zoomImageView, relativeLayout, ProgressBinding.bind(findChildViewById));
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DialogImagePreviewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DialogImagePreviewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_image_preview, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
