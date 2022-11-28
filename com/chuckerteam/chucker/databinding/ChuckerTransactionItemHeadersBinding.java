package com.chuckerteam.chucker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.chuckerteam.chucker.R;
import java.util.Objects;
/* loaded from: classes.dex */
public final class ChuckerTransactionItemHeadersBinding implements ViewBinding {
    @NonNull
    public final TextView responseHeaders;
    @NonNull
    private final TextView rootView;

    private ChuckerTransactionItemHeadersBinding(@NonNull TextView textView, @NonNull TextView textView2) {
        this.rootView = textView;
        this.responseHeaders = textView2;
    }

    @NonNull
    public static ChuckerTransactionItemHeadersBinding bind(@NonNull View view) {
        Objects.requireNonNull(view, "rootView");
        TextView textView = (TextView) view;
        return new ChuckerTransactionItemHeadersBinding(textView, textView);
    }

    @NonNull
    public static ChuckerTransactionItemHeadersBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ChuckerTransactionItemHeadersBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.chucker_transaction_item_headers, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public TextView getRoot() {
        return this.rootView;
    }
}
