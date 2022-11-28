package com.chuckerteam.chucker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.chuckerteam.chucker.R;
/* loaded from: classes.dex */
public final class ChuckerFragmentTransactionPayloadBinding implements ViewBinding {
    @NonNull
    public final ImageView emptyPayloadImage;
    @NonNull
    public final TextView emptyPayloadTextView;
    @NonNull
    public final Group emptyStateGroup;
    @NonNull
    public final ContentLoadingProgressBar loadingProgress;
    @NonNull
    public final RecyclerView payloadRecyclerView;
    @NonNull
    private final ConstraintLayout rootView;

    private ChuckerFragmentTransactionPayloadBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView, @NonNull TextView textView, @NonNull Group group, @NonNull ContentLoadingProgressBar contentLoadingProgressBar, @NonNull RecyclerView recyclerView) {
        this.rootView = constraintLayout;
        this.emptyPayloadImage = imageView;
        this.emptyPayloadTextView = textView;
        this.emptyStateGroup = group;
        this.loadingProgress = contentLoadingProgressBar;
        this.payloadRecyclerView = recyclerView;
    }

    @NonNull
    public static ChuckerFragmentTransactionPayloadBinding bind(@NonNull View view) {
        int i2 = R.id.emptyPayloadImage;
        ImageView imageView = (ImageView) view.findViewById(i2);
        if (imageView != null) {
            i2 = R.id.emptyPayloadTextView;
            TextView textView = (TextView) view.findViewById(i2);
            if (textView != null) {
                i2 = R.id.emptyStateGroup;
                Group group = (Group) view.findViewById(i2);
                if (group != null) {
                    i2 = R.id.loadingProgress;
                    ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) view.findViewById(i2);
                    if (contentLoadingProgressBar != null) {
                        i2 = R.id.payloadRecyclerView;
                        RecyclerView recyclerView = (RecyclerView) view.findViewById(i2);
                        if (recyclerView != null) {
                            return new ChuckerFragmentTransactionPayloadBinding((ConstraintLayout) view, imageView, textView, group, contentLoadingProgressBar, recyclerView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ChuckerFragmentTransactionPayloadBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ChuckerFragmentTransactionPayloadBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.chucker_fragment_transaction_payload, viewGroup, false);
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
