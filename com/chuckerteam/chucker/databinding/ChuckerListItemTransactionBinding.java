package com.chuckerteam.chucker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import com.chuckerteam.chucker.R;
/* loaded from: classes.dex */
public final class ChuckerListItemTransactionBinding implements ViewBinding {
    @NonNull
    public final TextView code;
    @NonNull
    public final TextView duration;
    @NonNull
    public final Guideline guideline;
    @NonNull
    public final TextView host;
    @NonNull
    public final TextView path;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final TextView size;
    @NonNull
    public final ImageView ssl;
    @NonNull
    public final TextView timeStart;

    private ChuckerListItemTransactionBinding(@NonNull ConstraintLayout constraintLayout, @NonNull TextView textView, @NonNull TextView textView2, @NonNull Guideline guideline, @NonNull TextView textView3, @NonNull TextView textView4, @NonNull TextView textView5, @NonNull ImageView imageView, @NonNull TextView textView6) {
        this.rootView = constraintLayout;
        this.code = textView;
        this.duration = textView2;
        this.guideline = guideline;
        this.host = textView3;
        this.path = textView4;
        this.size = textView5;
        this.ssl = imageView;
        this.timeStart = textView6;
    }

    @NonNull
    public static ChuckerListItemTransactionBinding bind(@NonNull View view) {
        int i2 = R.id.code;
        TextView textView = (TextView) view.findViewById(i2);
        if (textView != null) {
            i2 = R.id.duration;
            TextView textView2 = (TextView) view.findViewById(i2);
            if (textView2 != null) {
                i2 = R.id.guideline;
                Guideline guideline = (Guideline) view.findViewById(i2);
                if (guideline != null) {
                    i2 = R.id.host;
                    TextView textView3 = (TextView) view.findViewById(i2);
                    if (textView3 != null) {
                        i2 = R.id.path;
                        TextView textView4 = (TextView) view.findViewById(i2);
                        if (textView4 != null) {
                            i2 = R.id.size;
                            TextView textView5 = (TextView) view.findViewById(i2);
                            if (textView5 != null) {
                                i2 = R.id.ssl;
                                ImageView imageView = (ImageView) view.findViewById(i2);
                                if (imageView != null) {
                                    i2 = R.id.timeStart;
                                    TextView textView6 = (TextView) view.findViewById(i2);
                                    if (textView6 != null) {
                                        return new ChuckerListItemTransactionBinding((ConstraintLayout) view, textView, textView2, guideline, textView3, textView4, textView5, imageView, textView6);
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
    public static ChuckerListItemTransactionBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ChuckerListItemTransactionBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.chucker_list_item_transaction, viewGroup, false);
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
