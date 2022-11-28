package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.psa.mym.mycitroenconnect.views.CustomSpinner;
import com.psa.mym.mycitroenconnect.views.RoundedBarChart;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityBatteryUsageBinding implements ViewBinding {
    @NonNull
    public final LayoutTripCardBinding batteryCard1;
    @NonNull
    public final LayoutTripCardBinding batteryCard2;
    @NonNull
    public final CustomSpinner batteryPeriodSpinner;
    @NonNull
    public final LinearLayoutCompat batteryScrollView;
    @NonNull
    public final AppCompatTextView batterySelectPeriod;
    @NonNull
    public final LinearLayout batteryUsageChart;
    @NonNull
    public final LayoutDashboardModeHeaderBinding batteryUsageHeader;
    @NonNull
    public final ConstraintLayout clParent;
    @NonNull
    public final Guideline guide1;
    @NonNull
    public final Guideline guide2;
    @NonNull
    public final AppCompatImageView iconIdleTime;
    @NonNull
    public final LayoutTripCardBinding idlingCard1;
    @NonNull
    public final LayoutTripCardBinding idlingCard2;
    @NonNull
    public final RecyclerView idlingRV;
    @NonNull
    public final AppCompatImageView ivEnergyConsInfo;
    @NonNull
    public final AppCompatImageView ivIdlingInfo;
    @NonNull
    public final AppCompatImageView ivIdlingTripInfo;
    @NonNull
    public final LinearLayout layoutBatteryCards;
    @NonNull
    public final CardView layoutIdleTimeCard;
    @NonNull
    public final LinearLayout layoutIdlingCards;
    @NonNull
    public final LinearLayout linBatteryProgressLayout;
    @NonNull
    public final RoundedBarChart mBatteryEfficiency;
    @NonNull
    public final ProgressBar progressBar;
    @NonNull
    public final RelativeLayout relSpinner;
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final AppCompatTextView tvEnergyConsumption;
    @NonNull
    public final TextView tvGraphLabel;
    @NonNull
    public final AppCompatTextView tvIdling;
    @NonNull
    public final AppCompatTextView tvIdlingByTrips;
    @NonNull
    public final AppCompatTextView tvVehicleIdlingTime;
    @NonNull
    public final AppCompatTextView vehicleIdleTime;

    private ActivityBatteryUsageBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LayoutTripCardBinding layoutTripCardBinding, @NonNull LayoutTripCardBinding layoutTripCardBinding2, @NonNull CustomSpinner customSpinner, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull AppCompatTextView appCompatTextView, @NonNull LinearLayout linearLayout, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull ConstraintLayout constraintLayout, @NonNull Guideline guideline, @NonNull Guideline guideline2, @NonNull AppCompatImageView appCompatImageView, @NonNull LayoutTripCardBinding layoutTripCardBinding3, @NonNull LayoutTripCardBinding layoutTripCardBinding4, @NonNull RecyclerView recyclerView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull AppCompatImageView appCompatImageView4, @NonNull LinearLayout linearLayout2, @NonNull CardView cardView, @NonNull LinearLayout linearLayout3, @NonNull LinearLayout linearLayout4, @NonNull RoundedBarChart roundedBarChart, @NonNull ProgressBar progressBar, @NonNull RelativeLayout relativeLayout, @NonNull AppCompatTextView appCompatTextView2, @NonNull TextView textView, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6) {
        this.rootView = linearLayoutCompat;
        this.batteryCard1 = layoutTripCardBinding;
        this.batteryCard2 = layoutTripCardBinding2;
        this.batteryPeriodSpinner = customSpinner;
        this.batteryScrollView = linearLayoutCompat2;
        this.batterySelectPeriod = appCompatTextView;
        this.batteryUsageChart = linearLayout;
        this.batteryUsageHeader = layoutDashboardModeHeaderBinding;
        this.clParent = constraintLayout;
        this.guide1 = guideline;
        this.guide2 = guideline2;
        this.iconIdleTime = appCompatImageView;
        this.idlingCard1 = layoutTripCardBinding3;
        this.idlingCard2 = layoutTripCardBinding4;
        this.idlingRV = recyclerView;
        this.ivEnergyConsInfo = appCompatImageView2;
        this.ivIdlingInfo = appCompatImageView3;
        this.ivIdlingTripInfo = appCompatImageView4;
        this.layoutBatteryCards = linearLayout2;
        this.layoutIdleTimeCard = cardView;
        this.layoutIdlingCards = linearLayout3;
        this.linBatteryProgressLayout = linearLayout4;
        this.mBatteryEfficiency = roundedBarChart;
        this.progressBar = progressBar;
        this.relSpinner = relativeLayout;
        this.tvEnergyConsumption = appCompatTextView2;
        this.tvGraphLabel = textView;
        this.tvIdling = appCompatTextView3;
        this.tvIdlingByTrips = appCompatTextView4;
        this.tvVehicleIdlingTime = appCompatTextView5;
        this.vehicleIdleTime = appCompatTextView6;
    }

    @NonNull
    public static ActivityBatteryUsageBinding bind(@NonNull View view) {
        int i2 = R.id.batteryCard1;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.batteryCard1);
        if (findChildViewById != null) {
            LayoutTripCardBinding bind = LayoutTripCardBinding.bind(findChildViewById);
            i2 = R.id.batteryCard2;
            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.batteryCard2);
            if (findChildViewById2 != null) {
                LayoutTripCardBinding bind2 = LayoutTripCardBinding.bind(findChildViewById2);
                i2 = R.id.batteryPeriodSpinner;
                CustomSpinner customSpinner = (CustomSpinner) ViewBindings.findChildViewById(view, R.id.batteryPeriodSpinner);
                if (customSpinner != null) {
                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) view;
                    i2 = R.id.batterySelectPeriod;
                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.batterySelectPeriod);
                    if (appCompatTextView != null) {
                        i2 = R.id.batteryUsageChart;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.batteryUsageChart);
                        if (linearLayout != null) {
                            i2 = R.id.batteryUsageHeader;
                            View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.batteryUsageHeader);
                            if (findChildViewById3 != null) {
                                LayoutDashboardModeHeaderBinding bind3 = LayoutDashboardModeHeaderBinding.bind(findChildViewById3);
                                i2 = R.id.clParent;
                                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clParent);
                                if (constraintLayout != null) {
                                    i2 = R.id.guide1;
                                    Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, R.id.guide1);
                                    if (guideline != null) {
                                        i2 = R.id.guide2;
                                        Guideline guideline2 = (Guideline) ViewBindings.findChildViewById(view, R.id.guide2);
                                        if (guideline2 != null) {
                                            i2 = R.id.iconIdleTime;
                                            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.iconIdleTime);
                                            if (appCompatImageView != null) {
                                                i2 = R.id.idlingCard1;
                                                View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.idlingCard1);
                                                if (findChildViewById4 != null) {
                                                    LayoutTripCardBinding bind4 = LayoutTripCardBinding.bind(findChildViewById4);
                                                    i2 = R.id.idlingCard2;
                                                    View findChildViewById5 = ViewBindings.findChildViewById(view, R.id.idlingCard2);
                                                    if (findChildViewById5 != null) {
                                                        LayoutTripCardBinding bind5 = LayoutTripCardBinding.bind(findChildViewById5);
                                                        i2 = R.id.idlingRV;
                                                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.idlingRV);
                                                        if (recyclerView != null) {
                                                            i2 = R.id.ivEnergyConsInfo;
                                                            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivEnergyConsInfo);
                                                            if (appCompatImageView2 != null) {
                                                                i2 = R.id.ivIdlingInfo;
                                                                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivIdlingInfo);
                                                                if (appCompatImageView3 != null) {
                                                                    i2 = R.id.ivIdlingTripInfo;
                                                                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivIdlingTripInfo);
                                                                    if (appCompatImageView4 != null) {
                                                                        i2 = R.id.layoutBatteryCards;
                                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.layoutBatteryCards);
                                                                        if (linearLayout2 != null) {
                                                                            i2 = R.id.layoutIdleTimeCard;
                                                                            CardView cardView = (CardView) ViewBindings.findChildViewById(view, R.id.layoutIdleTimeCard);
                                                                            if (cardView != null) {
                                                                                i2 = R.id.layoutIdlingCards;
                                                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.layoutIdlingCards);
                                                                                if (linearLayout3 != null) {
                                                                                    i2 = R.id.linBatteryProgressLayout;
                                                                                    LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linBatteryProgressLayout);
                                                                                    if (linearLayout4 != null) {
                                                                                        i2 = R.id.mBatteryEfficiency;
                                                                                        RoundedBarChart roundedBarChart = (RoundedBarChart) ViewBindings.findChildViewById(view, R.id.mBatteryEfficiency);
                                                                                        if (roundedBarChart != null) {
                                                                                            i2 = R.id.progressBar;
                                                                                            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.progressBar);
                                                                                            if (progressBar != null) {
                                                                                                i2 = R.id.relSpinner;
                                                                                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.relSpinner);
                                                                                                if (relativeLayout != null) {
                                                                                                    i2 = R.id.tvEnergyConsumption;
                                                                                                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEnergyConsumption);
                                                                                                    if (appCompatTextView2 != null) {
                                                                                                        i2 = R.id.tvGraphLabel;
                                                                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvGraphLabel);
                                                                                                        if (textView != null) {
                                                                                                            i2 = R.id.tvIdling;
                                                                                                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvIdling);
                                                                                                            if (appCompatTextView3 != null) {
                                                                                                                i2 = R.id.tvIdlingByTrips;
                                                                                                                AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvIdlingByTrips);
                                                                                                                if (appCompatTextView4 != null) {
                                                                                                                    i2 = R.id.tvVehicleIdlingTime;
                                                                                                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvVehicleIdlingTime);
                                                                                                                    if (appCompatTextView5 != null) {
                                                                                                                        i2 = R.id.vehicleIdleTime;
                                                                                                                        AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.vehicleIdleTime);
                                                                                                                        if (appCompatTextView6 != null) {
                                                                                                                            return new ActivityBatteryUsageBinding(linearLayoutCompat, bind, bind2, customSpinner, linearLayoutCompat, appCompatTextView, linearLayout, bind3, constraintLayout, guideline, guideline2, appCompatImageView, bind4, bind5, recyclerView, appCompatImageView2, appCompatImageView3, appCompatImageView4, linearLayout2, cardView, linearLayout3, linearLayout4, roundedBarChart, progressBar, relativeLayout, appCompatTextView2, textView, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
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
    public static ActivityBatteryUsageBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityBatteryUsageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_battery_usage, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayoutCompat getRoot() {
        return this.rootView;
    }
}
