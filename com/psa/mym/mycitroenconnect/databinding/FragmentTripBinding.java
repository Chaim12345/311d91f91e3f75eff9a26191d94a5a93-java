package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.psa.mym.mycitroenconnect.views.CustomSpinner;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentTripBinding implements ViewBinding {
    @NonNull
    public final AppCompatImageView ivTripRefresh;
    @NonNull
    public final LinearLayoutCompat llTripUpdatedTime;
    @NonNull
    public final LinearLayout llTrips;
    @NonNull
    public final RecyclerView mtCardViews;
    @NonNull
    public final ViewPager2 mtPager;
    @NonNull
    public final CustomSpinner mtPeriodSpinner;
    @NonNull
    public final TabLayout mtTabLayout;
    @NonNull
    public final AppCompatTextView mtViewDetails;
    @NonNull
    public final RelativeLayout relSpinner;
    @NonNull
    private final NestedScrollView rootView;
    @NonNull
    public final NestedScrollView tripScrollView;
    @NonNull
    public final AppCompatTextView tvSelectPeriod;
    @NonNull
    public final AppCompatTextView tvTripUpdated;

    private FragmentTripBinding(@NonNull NestedScrollView nestedScrollView, @NonNull AppCompatImageView appCompatImageView, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LinearLayout linearLayout, @NonNull RecyclerView recyclerView, @NonNull ViewPager2 viewPager2, @NonNull CustomSpinner customSpinner, @NonNull TabLayout tabLayout, @NonNull AppCompatTextView appCompatTextView, @NonNull RelativeLayout relativeLayout, @NonNull NestedScrollView nestedScrollView2, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3) {
        this.rootView = nestedScrollView;
        this.ivTripRefresh = appCompatImageView;
        this.llTripUpdatedTime = linearLayoutCompat;
        this.llTrips = linearLayout;
        this.mtCardViews = recyclerView;
        this.mtPager = viewPager2;
        this.mtPeriodSpinner = customSpinner;
        this.mtTabLayout = tabLayout;
        this.mtViewDetails = appCompatTextView;
        this.relSpinner = relativeLayout;
        this.tripScrollView = nestedScrollView2;
        this.tvSelectPeriod = appCompatTextView2;
        this.tvTripUpdated = appCompatTextView3;
    }

    @NonNull
    public static FragmentTripBinding bind(@NonNull View view) {
        int i2 = R.id.ivTripRefresh;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivTripRefresh);
        if (appCompatImageView != null) {
            i2 = R.id.llTripUpdatedTime;
            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llTripUpdatedTime);
            if (linearLayoutCompat != null) {
                i2 = R.id.llTrips;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.llTrips);
                if (linearLayout != null) {
                    i2 = R.id.mtCardViews;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.mtCardViews);
                    if (recyclerView != null) {
                        i2 = R.id.mtPager;
                        ViewPager2 viewPager2 = (ViewPager2) ViewBindings.findChildViewById(view, R.id.mtPager);
                        if (viewPager2 != null) {
                            i2 = R.id.mtPeriodSpinner;
                            CustomSpinner customSpinner = (CustomSpinner) ViewBindings.findChildViewById(view, R.id.mtPeriodSpinner);
                            if (customSpinner != null) {
                                i2 = R.id.mtTabLayout;
                                TabLayout tabLayout = (TabLayout) ViewBindings.findChildViewById(view, R.id.mtTabLayout);
                                if (tabLayout != null) {
                                    i2 = R.id.mtViewDetails;
                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.mtViewDetails);
                                    if (appCompatTextView != null) {
                                        i2 = R.id.relSpinner;
                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.relSpinner);
                                        if (relativeLayout != null) {
                                            NestedScrollView nestedScrollView = (NestedScrollView) view;
                                            i2 = R.id.tvSelectPeriod;
                                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSelectPeriod);
                                            if (appCompatTextView2 != null) {
                                                i2 = R.id.tvTripUpdated;
                                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTripUpdated);
                                                if (appCompatTextView3 != null) {
                                                    return new FragmentTripBinding(nestedScrollView, appCompatImageView, linearLayoutCompat, linearLayout, recyclerView, viewPager2, customSpinner, tabLayout, appCompatTextView, relativeLayout, nestedScrollView, appCompatTextView2, appCompatTextView3);
                                                }
                                            }
                                        }
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
    public static FragmentTripBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentTripBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_trip, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public NestedScrollView getRoot() {
        return this.rootView;
    }
}
