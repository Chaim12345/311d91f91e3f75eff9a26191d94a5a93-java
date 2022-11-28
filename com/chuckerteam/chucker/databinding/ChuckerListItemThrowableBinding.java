package com.chuckerteam.chucker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.chuckerteam.chucker.R;
/* loaded from: classes.dex */
public final class ChuckerListItemThrowableBinding implements ViewBinding {
    @NonNull
    public final TextView clazz;
    @NonNull
    public final TextView date;
    @NonNull
    public final TextView message;
    @NonNull
    private final LinearLayout rootView;
    @NonNull
    public final TextView tag;

    private ChuckerListItemThrowableBinding(@NonNull LinearLayout linearLayout, @NonNull TextView textView, @NonNull TextView textView2, @NonNull TextView textView3, @NonNull TextView textView4) {
        this.rootView = linearLayout;
        this.clazz = textView;
        this.date = textView2;
        this.message = textView3;
        this.tag = textView4;
    }

    @NonNull
    public static ChuckerListItemThrowableBinding bind(@NonNull View view) {
        int i2 = R.id.clazz;
        TextView textView = (TextView) view.findViewById(i2);
        if (textView != null) {
            i2 = R.id.date;
            TextView textView2 = (TextView) view.findViewById(i2);
            if (textView2 != null) {
                i2 = R.id.message;
                TextView textView3 = (TextView) view.findViewById(i2);
                if (textView3 != null) {
                    i2 = R.id.tag;
                    TextView textView4 = (TextView) view.findViewById(i2);
                    if (textView4 != null) {
                        return new ChuckerListItemThrowableBinding((LinearLayout) view, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ChuckerListItemThrowableBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ChuckerListItemThrowableBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.chucker_list_item_throwable, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayout getRoot() {
        return this.rootView;
    }
}
