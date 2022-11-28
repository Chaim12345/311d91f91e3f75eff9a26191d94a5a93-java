package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.rd.PageIndicatorView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class BottomSheetDialogShareBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivClose;
    @NonNull
    public final PageIndicatorView pageIndicatorView;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final RecyclerView rvAppList;
    @NonNull
    public final AppCompatTextView tvTitle;

    private BottomSheetDialogShareBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatImageView appCompatImageView, @NonNull PageIndicatorView pageIndicatorView, @NonNull RecyclerView recyclerView, @NonNull AppCompatTextView appCompatTextView) {
        this.rootView = constraintLayout;
        this.ivClose = appCompatImageView;
        this.pageIndicatorView = pageIndicatorView;
        this.rvAppList = recyclerView;
        this.tvTitle = appCompatTextView;
    }

    @NonNull
    public static BottomSheetDialogShareBinding bind(@NonNull View view) {
        int i2 = R.id.ivClose;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivClose);
        if (appCompatImageView != null) {
            i2 = R.id.pageIndicatorView;
            PageIndicatorView pageIndicatorView = (PageIndicatorView) ViewBindings.findChildViewById(view, R.id.pageIndicatorView);
            if (pageIndicatorView != null) {
                i2 = R.id.rvAppList;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvAppList);
                if (recyclerView != null) {
                    i2 = R.id.tvTitle;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTitle);
                    if (appCompatTextView != null) {
                        return new BottomSheetDialogShareBinding((ConstraintLayout) view, appCompatImageView, pageIndicatorView, recyclerView, appCompatTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static BottomSheetDialogShareBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static BottomSheetDialogShareBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.bottom_sheet_dialog_share, viewGroup, false);
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
