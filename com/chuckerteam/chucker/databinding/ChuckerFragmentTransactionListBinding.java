package com.chuckerteam.chucker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.chuckerteam.chucker.R;
/* loaded from: classes.dex */
public final class ChuckerFragmentTransactionListBinding implements ViewBinding {
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final RecyclerView transactionsRecyclerView;
    @NonNull
    public final TextView tutorialLink;
    @NonNull
    public final LinearLayout tutorialView;

    private ChuckerFragmentTransactionListBinding(@NonNull FrameLayout frameLayout, @NonNull RecyclerView recyclerView, @NonNull TextView textView, @NonNull LinearLayout linearLayout) {
        this.rootView = frameLayout;
        this.transactionsRecyclerView = recyclerView;
        this.tutorialLink = textView;
        this.tutorialView = linearLayout;
    }

    @NonNull
    public static ChuckerFragmentTransactionListBinding bind(@NonNull View view) {
        int i2 = R.id.transactionsRecyclerView;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(i2);
        if (recyclerView != null) {
            i2 = R.id.tutorialLink;
            TextView textView = (TextView) view.findViewById(i2);
            if (textView != null) {
                i2 = R.id.tutorialView;
                LinearLayout linearLayout = (LinearLayout) view.findViewById(i2);
                if (linearLayout != null) {
                    return new ChuckerFragmentTransactionListBinding((FrameLayout) view, recyclerView, textView, linearLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ChuckerFragmentTransactionListBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ChuckerFragmentTransactionListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.chucker_fragment_transaction_list, viewGroup, false);
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
