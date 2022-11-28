package com.chuckerteam.chucker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.chuckerteam.chucker.R;
/* loaded from: classes.dex */
public final class ChuckerTransactionItemImageBinding implements ViewBinding {
    @NonNull
    public final ImageView binaryData;
    @NonNull
    private final FrameLayout rootView;

    private ChuckerTransactionItemImageBinding(@NonNull FrameLayout frameLayout, @NonNull ImageView imageView) {
        this.rootView = frameLayout;
        this.binaryData = imageView;
    }

    @NonNull
    public static ChuckerTransactionItemImageBinding bind(@NonNull View view) {
        int i2 = R.id.binaryData;
        ImageView imageView = (ImageView) view.findViewById(i2);
        if (imageView != null) {
            return new ChuckerTransactionItemImageBinding((FrameLayout) view, imageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ChuckerTransactionItemImageBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ChuckerTransactionItemImageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.chucker_transaction_item_image, viewGroup, false);
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
