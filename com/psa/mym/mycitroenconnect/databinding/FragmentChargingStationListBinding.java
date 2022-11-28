package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentChargingStationListBinding implements ViewBinding {
    @NonNull
    public final CardView cardNearbySearch;
    @NonNull
    public final AppCompatEditText edSearchNearbyStation;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final RecyclerView rvNearbyChargingStation;

    private FragmentChargingStationListBinding(@NonNull FrameLayout frameLayout, @NonNull CardView cardView, @NonNull AppCompatEditText appCompatEditText, @NonNull RecyclerView recyclerView) {
        this.rootView = frameLayout;
        this.cardNearbySearch = cardView;
        this.edSearchNearbyStation = appCompatEditText;
        this.rvNearbyChargingStation = recyclerView;
    }

    @NonNull
    public static FragmentChargingStationListBinding bind(@NonNull View view) {
        int i2 = R.id.cardNearbySearch;
        CardView cardView = (CardView) ViewBindings.findChildViewById(view, R.id.cardNearbySearch);
        if (cardView != null) {
            i2 = R.id.edSearchNearbyStation;
            AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, R.id.edSearchNearbyStation);
            if (appCompatEditText != null) {
                i2 = R.id.rvNearbyChargingStation;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvNearbyChargingStation);
                if (recyclerView != null) {
                    return new FragmentChargingStationListBinding((FrameLayout) view, cardView, appCompatEditText, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentChargingStationListBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentChargingStationListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_charging_station_list, viewGroup, false);
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
