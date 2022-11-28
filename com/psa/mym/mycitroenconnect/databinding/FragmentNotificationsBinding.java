package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.simform.refresh.SSPullToRefreshLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentNotificationsBinding implements ViewBinding {
    @NonNull
    public final LinearLayoutCompat llNotification;
    @NonNull
    private final SSPullToRefreshLayout rootView;
    @NonNull
    public final RecyclerView rvNotification;
    @NonNull
    public final RecyclerView rvNotificationType;
    @NonNull
    public final SSPullToRefreshLayout swipeRefresh;
    @NonNull
    public final TextView tvNoNotification;

    private FragmentNotificationsBinding(@NonNull SSPullToRefreshLayout sSPullToRefreshLayout, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull RecyclerView recyclerView, @NonNull RecyclerView recyclerView2, @NonNull SSPullToRefreshLayout sSPullToRefreshLayout2, @NonNull TextView textView) {
        this.rootView = sSPullToRefreshLayout;
        this.llNotification = linearLayoutCompat;
        this.rvNotification = recyclerView;
        this.rvNotificationType = recyclerView2;
        this.swipeRefresh = sSPullToRefreshLayout2;
        this.tvNoNotification = textView;
    }

    @NonNull
    public static FragmentNotificationsBinding bind(@NonNull View view) {
        int i2 = R.id.llNotification;
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llNotification);
        if (linearLayoutCompat != null) {
            i2 = R.id.rvNotification;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvNotification);
            if (recyclerView != null) {
                i2 = R.id.rvNotificationType;
                RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvNotificationType);
                if (recyclerView2 != null) {
                    SSPullToRefreshLayout sSPullToRefreshLayout = (SSPullToRefreshLayout) view;
                    i2 = R.id.tvNoNotification;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvNoNotification);
                    if (textView != null) {
                        return new FragmentNotificationsBinding(sSPullToRefreshLayout, linearLayoutCompat, recyclerView, recyclerView2, sSPullToRefreshLayout, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentNotificationsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentNotificationsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_notifications, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public SSPullToRefreshLayout getRoot() {
        return this.rootView;
    }
}
