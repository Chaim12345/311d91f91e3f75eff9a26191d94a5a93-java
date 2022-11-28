package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.psa.mym.mycitroenconnect.views.barcode_scanning.ViewFinderOverlay;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityBarCodeScannerBinding implements ViewBinding {
    @NonNull
    public final PreviewView cameraPreview;
    @NonNull
    public final AppCompatImageView ivBack;
    @NonNull
    public final AppCompatImageView ivFlashControl;
    @NonNull
    public final ViewFinderOverlay overlay;
    @NonNull
    private final ConstraintLayout rootView;

    private ActivityBarCodeScannerBinding(@NonNull ConstraintLayout constraintLayout, @NonNull PreviewView previewView, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull ViewFinderOverlay viewFinderOverlay) {
        this.rootView = constraintLayout;
        this.cameraPreview = previewView;
        this.ivBack = appCompatImageView;
        this.ivFlashControl = appCompatImageView2;
        this.overlay = viewFinderOverlay;
    }

    @NonNull
    public static ActivityBarCodeScannerBinding bind(@NonNull View view) {
        int i2 = R.id.cameraPreview;
        PreviewView previewView = (PreviewView) ViewBindings.findChildViewById(view, R.id.cameraPreview);
        if (previewView != null) {
            i2 = R.id.ivBack;
            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivBack);
            if (appCompatImageView != null) {
                i2 = R.id.ivFlashControl;
                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivFlashControl);
                if (appCompatImageView2 != null) {
                    i2 = R.id.overlay;
                    ViewFinderOverlay viewFinderOverlay = (ViewFinderOverlay) ViewBindings.findChildViewById(view, R.id.overlay);
                    if (viewFinderOverlay != null) {
                        return new ActivityBarCodeScannerBinding((ConstraintLayout) view, previewView, appCompatImageView, appCompatImageView2, viewFinderOverlay);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ActivityBarCodeScannerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityBarCodeScannerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_bar_code_scanner, viewGroup, false);
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
