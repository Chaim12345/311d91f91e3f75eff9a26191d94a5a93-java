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
public final class ChuckerTransactionItemBodyLineBinding implements ViewBinding {
    @NonNull
    public final TextView bodyLine;
    @NonNull
    private final TextView rootView;

    private ChuckerTransactionItemBodyLineBinding(@NonNull TextView textView, @NonNull TextView textView2) {
        this.rootView = textView;
        this.bodyLine = textView2;
    }

    @NonNull
    public static ChuckerTransactionItemBodyLineBinding bind(@NonNull View view) {
        Objects.requireNonNull(view, "rootView");
        TextView textView = (TextView) view;
        return new ChuckerTransactionItemBodyLineBinding(textView, textView);
    }

    @NonNull
    public static ChuckerTransactionItemBodyLineBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ChuckerTransactionItemBodyLineBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.chucker_transaction_item_body_line, viewGroup, false);
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
