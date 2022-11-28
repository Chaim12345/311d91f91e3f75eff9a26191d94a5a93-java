package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.psa.mym.mycitroenconnect.views.infowindow.MapWrapperLayout;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentChargingStationBinding implements ViewBinding {
    @NonNull
    public final CardView cardNearbySearch;
    @NonNull
    public final AppCompatEditText edSearchStation;
    @NonNull
    public final MapWrapperLayout mapRelativeLayout;
    @NonNull
    private final FrameLayout rootView;

    private FragmentChargingStationBinding(@NonNull FrameLayout frameLayout, @NonNull CardView cardView, @NonNull AppCompatEditText appCompatEditText, @NonNull MapWrapperLayout mapWrapperLayout) {
        this.rootView = frameLayout;
        this.cardNearbySearch = cardView;
        this.edSearchStation = appCompatEditText;
        this.mapRelativeLayout = mapWrapperLayout;
    }

    @NonNull
    public static FragmentChargingStationBinding bind(@NonNull View view) {
        int i2 = R.id.cardNearbySearch;
        CardView cardView = (CardView) ViewBindings.findChildViewById(view, R.id.cardNearbySearch);
        if (cardView != null) {
            i2 = R.id.edSearchStation;
            AppCompatEditText appCompatEditText = (AppCompatEditText) ViewBindings.findChildViewById(view, R.id.edSearchStation);
            if (appCompatEditText != null) {
                i2 = R.id.map_relative_layout;
                MapWrapperLayout mapWrapperLayout = (MapWrapperLayout) ViewBindings.findChildViewById(view, R.id.map_relative_layout);
                if (mapWrapperLayout != null) {
                    return new FragmentChargingStationBinding((FrameLayout) view, cardView, appCompatEditText, mapWrapperLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentChargingStationBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentChargingStationBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_charging_station, viewGroup, false);
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
