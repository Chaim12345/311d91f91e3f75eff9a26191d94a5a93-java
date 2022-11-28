package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ItemViewMyCarStatusAlertHeaderBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivExpandCollapse;
    @NonNull
    public final LinearLayoutCompat llHeader;
    @NonNull
    private final MaterialCardView rootView;
    @NonNull
    public final RecyclerView rvAlertChild;
    @NonNull
    public final AppCompatTextView tvAlertCount;
    @NonNull
    public final AppCompatTextView tvAlertLbl;

    private ItemViewMyCarStatusAlertHeaderBinding(@NonNull MaterialCardView materialCardView, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull RecyclerView recyclerView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2) {
        this.rootView = materialCardView;
        this.ivExpandCollapse = appCompatImageView;
        this.llHeader = linearLayoutCompat;
        this.rvAlertChild = recyclerView;
        this.tvAlertCount = appCompatTextView;
        this.tvAlertLbl = appCompatTextView2;
    }

    @NonNull
    public static ItemViewMyCarStatusAlertHeaderBinding bind(@NonNull View view) {
        int i2 = R.id.ivExpandCollapse;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivExpandCollapse);
        if (appCompatImageView != null) {
            i2 = R.id.llHeader;
            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llHeader);
            if (linearLayoutCompat != null) {
                i2 = R.id.rvAlertChild;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvAlertChild);
                if (recyclerView != null) {
                    i2 = R.id.tvAlertCount;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAlertCount);
                    if (appCompatTextView != null) {
                        i2 = R.id.tvAlertLbl;
                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAlertLbl);
                        if (appCompatTextView2 != null) {
                            return new ItemViewMyCarStatusAlertHeaderBinding((MaterialCardView) view, appCompatImageView, linearLayoutCompat, recyclerView, appCompatTextView, appCompatTextView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ItemViewMyCarStatusAlertHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ItemViewMyCarStatusAlertHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_view_my_car_status_alert_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public MaterialCardView getRoot() {
        return this.rootView;
    }
}
