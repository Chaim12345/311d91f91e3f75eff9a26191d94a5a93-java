package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.psa.mym.mycitroenconnect.views.RoundedBarChart;
import com.rd.PageIndicatorView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityTripDetailsBinding implements ViewBinding {
    @NonNull
    public final LinearLayout batteryUsageChart;
    @NonNull
    public final LayoutOngoingTripDetailCardBinding cardTdOngngTrip;
    @NonNull
    public final LayoutTripDetailCardBinding cardTdTrip;
    @NonNull
    public final ConstraintLayout clParent;
    @NonNull
    public final TextInputEditText edtTripName;
    @NonNull
    public final AppCompatImageView ivCloseTripNameEdit;
    @NonNull
    public final AppCompatImageView ivEditTripName;
    @NonNull
    public final AppCompatImageView ivTdBatteryInfo;
    @NonNull
    public final AppCompatImageView ivTdIdlingTripInfo;
    @NonNull
    public final AppCompatImageView ivTdMap;
    @NonNull
    public final AppCompatImageView ivUpdateTripName;
    @NonNull
    public final RelativeLayout layoutOnGoingChip;
    @NonNull
    public final LinearLayout layoutSourceDest;
    @NonNull
    public final LinearLayout layoutTdDriving;
    @NonNull
    public final CardView layoutTdHarshAcc;
    @NonNull
    public final CardView layoutTdHarshbraking;
    @NonNull
    public final CardView layoutTdOverSpeed;
    @NonNull
    public final LinearLayoutCompat layoutTripTitle;
    @NonNull
    public final LinearLayout linBatteryProgressLayout;
    @NonNull
    public final LinearLayout linChart;
    @NonNull
    public final RoundedBarChart mBatteryEfficiency;
    @NonNull
    public final PageIndicatorView pageIndicatorViewTd;
    @NonNull
    public final ProgressBar progressBar;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final RecyclerView rvBatteryConsCardViews;
    @NonNull
    public final RecyclerView rvTdCardViews;
    @NonNull
    public final RecyclerView rvTips;
    @NonNull
    public final TextView stickyLabel;
    @NonNull
    public final ScrollView tdScrollView;
    @NonNull
    public final LayoutDashboardModeHeaderBinding tripDetailsHeader;
    @NonNull
    public final AppCompatTextView tvDbCardLabel;
    @NonNull
    public final AppCompatTextView tvOngoingIdlingChip;
    @NonNull
    public final AppCompatTextView tvOngoingRunningChip;
    @NonNull
    public final AppCompatTextView tvOngoingTripChip;
    @NonNull
    public final AppCompatTextView tvTdBatteryConsumption;
    @NonNull
    public final AppCompatImageView tvTdDbCardBg;
    @NonNull
    public final AppCompatTextView tvTdDbCardHarshAcc;
    @NonNull
    public final AppCompatTextView tvTdDbCardHarshBraking;
    @NonNull
    public final AppCompatImageView tvTdDbCardIcon;
    @NonNull
    public final AppCompatImageView tvTdDbCardIconHarshAcc;
    @NonNull
    public final AppCompatImageView tvTdDbCardIconHarshAccBg;
    @NonNull
    public final AppCompatImageView tvTdDbCardIconHarshBraking;
    @NonNull
    public final AppCompatImageView tvTdDbCardIconHarshBrakingBg;
    @NonNull
    public final AppCompatTextView tvTdDbCardValue;
    @NonNull
    public final AppCompatTextView tvTdDbCardValueHarshAcc;
    @NonNull
    public final AppCompatTextView tvTdDbCardValueHarshBraking;
    @NonNull
    public final AppCompatTextView tvTdIdlingByTrips;
    @NonNull
    public final AppCompatTextView tvTdTips;
    @NonNull
    public final AppCompatTextView tvTdTripDuration;
    @NonNull
    public final AppCompatTextView tvViewOnMap;
    @NonNull
    public final View viewTripNameLine;

    private ActivityTripDetailsBinding(@NonNull ConstraintLayout constraintLayout, @NonNull LinearLayout linearLayout, @NonNull LayoutOngoingTripDetailCardBinding layoutOngoingTripDetailCardBinding, @NonNull LayoutTripDetailCardBinding layoutTripDetailCardBinding, @NonNull ConstraintLayout constraintLayout2, @NonNull TextInputEditText textInputEditText, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull AppCompatImageView appCompatImageView4, @NonNull AppCompatImageView appCompatImageView5, @NonNull AppCompatImageView appCompatImageView6, @NonNull RelativeLayout relativeLayout, @NonNull LinearLayout linearLayout2, @NonNull LinearLayout linearLayout3, @NonNull CardView cardView, @NonNull CardView cardView2, @NonNull CardView cardView3, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LinearLayout linearLayout4, @NonNull LinearLayout linearLayout5, @NonNull RoundedBarChart roundedBarChart, @NonNull PageIndicatorView pageIndicatorView, @NonNull ProgressBar progressBar, @NonNull RecyclerView recyclerView, @NonNull RecyclerView recyclerView2, @NonNull RecyclerView recyclerView3, @NonNull TextView textView, @NonNull ScrollView scrollView, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatImageView appCompatImageView7, @NonNull AppCompatTextView appCompatTextView6, @NonNull AppCompatTextView appCompatTextView7, @NonNull AppCompatImageView appCompatImageView8, @NonNull AppCompatImageView appCompatImageView9, @NonNull AppCompatImageView appCompatImageView10, @NonNull AppCompatImageView appCompatImageView11, @NonNull AppCompatImageView appCompatImageView12, @NonNull AppCompatTextView appCompatTextView8, @NonNull AppCompatTextView appCompatTextView9, @NonNull AppCompatTextView appCompatTextView10, @NonNull AppCompatTextView appCompatTextView11, @NonNull AppCompatTextView appCompatTextView12, @NonNull AppCompatTextView appCompatTextView13, @NonNull AppCompatTextView appCompatTextView14, @NonNull View view) {
        this.rootView = constraintLayout;
        this.batteryUsageChart = linearLayout;
        this.cardTdOngngTrip = layoutOngoingTripDetailCardBinding;
        this.cardTdTrip = layoutTripDetailCardBinding;
        this.clParent = constraintLayout2;
        this.edtTripName = textInputEditText;
        this.ivCloseTripNameEdit = appCompatImageView;
        this.ivEditTripName = appCompatImageView2;
        this.ivTdBatteryInfo = appCompatImageView3;
        this.ivTdIdlingTripInfo = appCompatImageView4;
        this.ivTdMap = appCompatImageView5;
        this.ivUpdateTripName = appCompatImageView6;
        this.layoutOnGoingChip = relativeLayout;
        this.layoutSourceDest = linearLayout2;
        this.layoutTdDriving = linearLayout3;
        this.layoutTdHarshAcc = cardView;
        this.layoutTdHarshbraking = cardView2;
        this.layoutTdOverSpeed = cardView3;
        this.layoutTripTitle = linearLayoutCompat;
        this.linBatteryProgressLayout = linearLayout4;
        this.linChart = linearLayout5;
        this.mBatteryEfficiency = roundedBarChart;
        this.pageIndicatorViewTd = pageIndicatorView;
        this.progressBar = progressBar;
        this.rvBatteryConsCardViews = recyclerView;
        this.rvTdCardViews = recyclerView2;
        this.rvTips = recyclerView3;
        this.stickyLabel = textView;
        this.tdScrollView = scrollView;
        this.tripDetailsHeader = layoutDashboardModeHeaderBinding;
        this.tvDbCardLabel = appCompatTextView;
        this.tvOngoingIdlingChip = appCompatTextView2;
        this.tvOngoingRunningChip = appCompatTextView3;
        this.tvOngoingTripChip = appCompatTextView4;
        this.tvTdBatteryConsumption = appCompatTextView5;
        this.tvTdDbCardBg = appCompatImageView7;
        this.tvTdDbCardHarshAcc = appCompatTextView6;
        this.tvTdDbCardHarshBraking = appCompatTextView7;
        this.tvTdDbCardIcon = appCompatImageView8;
        this.tvTdDbCardIconHarshAcc = appCompatImageView9;
        this.tvTdDbCardIconHarshAccBg = appCompatImageView10;
        this.tvTdDbCardIconHarshBraking = appCompatImageView11;
        this.tvTdDbCardIconHarshBrakingBg = appCompatImageView12;
        this.tvTdDbCardValue = appCompatTextView8;
        this.tvTdDbCardValueHarshAcc = appCompatTextView9;
        this.tvTdDbCardValueHarshBraking = appCompatTextView10;
        this.tvTdIdlingByTrips = appCompatTextView11;
        this.tvTdTips = appCompatTextView12;
        this.tvTdTripDuration = appCompatTextView13;
        this.tvViewOnMap = appCompatTextView14;
        this.viewTripNameLine = view;
    }

    @NonNull
    public static ActivityTripDetailsBinding bind(@NonNull View view) {
        int i2 = R.id.batteryUsageChart;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.batteryUsageChart);
        if (linearLayout != null) {
            i2 = R.id.cardTdOngngTrip;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.cardTdOngngTrip);
            if (findChildViewById != null) {
                LayoutOngoingTripDetailCardBinding bind = LayoutOngoingTripDetailCardBinding.bind(findChildViewById);
                i2 = R.id.cardTdTrip;
                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.cardTdTrip);
                if (findChildViewById2 != null) {
                    LayoutTripDetailCardBinding bind2 = LayoutTripDetailCardBinding.bind(findChildViewById2);
                    i2 = R.id.clParent;
                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clParent);
                    if (constraintLayout != null) {
                        i2 = R.id.edtTripName;
                        TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtTripName);
                        if (textInputEditText != null) {
                            i2 = R.id.ivCloseTripNameEdit;
                            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCloseTripNameEdit);
                            if (appCompatImageView != null) {
                                i2 = R.id.ivEditTripName;
                                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivEditTripName);
                                if (appCompatImageView2 != null) {
                                    i2 = R.id.ivTdBatteryInfo;
                                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivTdBatteryInfo);
                                    if (appCompatImageView3 != null) {
                                        i2 = R.id.ivTdIdlingTripInfo;
                                        AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivTdIdlingTripInfo);
                                        if (appCompatImageView4 != null) {
                                            i2 = R.id.ivTdMap;
                                            AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivTdMap);
                                            if (appCompatImageView5 != null) {
                                                i2 = R.id.ivUpdateTripName;
                                                AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivUpdateTripName);
                                                if (appCompatImageView6 != null) {
                                                    i2 = R.id.layoutOnGoingChip;
                                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.layoutOnGoingChip);
                                                    if (relativeLayout != null) {
                                                        i2 = R.id.layoutSourceDest;
                                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.layoutSourceDest);
                                                        if (linearLayout2 != null) {
                                                            i2 = R.id.layoutTdDriving;
                                                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.layoutTdDriving);
                                                            if (linearLayout3 != null) {
                                                                i2 = R.id.layoutTdHarshAcc;
                                                                CardView cardView = (CardView) ViewBindings.findChildViewById(view, R.id.layoutTdHarshAcc);
                                                                if (cardView != null) {
                                                                    i2 = R.id.layoutTdHarshbraking;
                                                                    CardView cardView2 = (CardView) ViewBindings.findChildViewById(view, R.id.layoutTdHarshbraking);
                                                                    if (cardView2 != null) {
                                                                        i2 = R.id.layoutTdOverSpeed;
                                                                        CardView cardView3 = (CardView) ViewBindings.findChildViewById(view, R.id.layoutTdOverSpeed);
                                                                        if (cardView3 != null) {
                                                                            i2 = R.id.layoutTripTitle;
                                                                            LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutTripTitle);
                                                                            if (linearLayoutCompat != null) {
                                                                                i2 = R.id.linBatteryProgressLayout;
                                                                                LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linBatteryProgressLayout);
                                                                                if (linearLayout4 != null) {
                                                                                    i2 = R.id.linChart;
                                                                                    LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linChart);
                                                                                    if (linearLayout5 != null) {
                                                                                        i2 = R.id.mBatteryEfficiency;
                                                                                        RoundedBarChart roundedBarChart = (RoundedBarChart) ViewBindings.findChildViewById(view, R.id.mBatteryEfficiency);
                                                                                        if (roundedBarChart != null) {
                                                                                            i2 = R.id.pageIndicatorViewTd;
                                                                                            PageIndicatorView pageIndicatorView = (PageIndicatorView) ViewBindings.findChildViewById(view, R.id.pageIndicatorViewTd);
                                                                                            if (pageIndicatorView != null) {
                                                                                                i2 = R.id.progressBar;
                                                                                                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.progressBar);
                                                                                                if (progressBar != null) {
                                                                                                    i2 = R.id.rvBatteryConsCardViews;
                                                                                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvBatteryConsCardViews);
                                                                                                    if (recyclerView != null) {
                                                                                                        i2 = R.id.rvTdCardViews;
                                                                                                        RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvTdCardViews);
                                                                                                        if (recyclerView2 != null) {
                                                                                                            i2 = R.id.rvTips;
                                                                                                            RecyclerView recyclerView3 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvTips);
                                                                                                            if (recyclerView3 != null) {
                                                                                                                i2 = R.id.sticky_label;
                                                                                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.sticky_label);
                                                                                                                if (textView != null) {
                                                                                                                    i2 = R.id.tdScrollView;
                                                                                                                    ScrollView scrollView = (ScrollView) ViewBindings.findChildViewById(view, R.id.tdScrollView);
                                                                                                                    if (scrollView != null) {
                                                                                                                        i2 = R.id.tripDetailsHeader;
                                                                                                                        View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.tripDetailsHeader);
                                                                                                                        if (findChildViewById3 != null) {
                                                                                                                            LayoutDashboardModeHeaderBinding bind3 = LayoutDashboardModeHeaderBinding.bind(findChildViewById3);
                                                                                                                            i2 = R.id.tvDbCardLabel;
                                                                                                                            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDbCardLabel);
                                                                                                                            if (appCompatTextView != null) {
                                                                                                                                i2 = R.id.tvOngoingIdlingChip;
                                                                                                                                AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOngoingIdlingChip);
                                                                                                                                if (appCompatTextView2 != null) {
                                                                                                                                    i2 = R.id.tvOngoingRunningChip;
                                                                                                                                    AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOngoingRunningChip);
                                                                                                                                    if (appCompatTextView3 != null) {
                                                                                                                                        i2 = R.id.tvOngoingTripChip;
                                                                                                                                        AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvOngoingTripChip);
                                                                                                                                        if (appCompatTextView4 != null) {
                                                                                                                                            i2 = R.id.tvTdBatteryConsumption;
                                                                                                                                            AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTdBatteryConsumption);
                                                                                                                                            if (appCompatTextView5 != null) {
                                                                                                                                                i2 = R.id.tvTdDbCardBg;
                                                                                                                                                AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardBg);
                                                                                                                                                if (appCompatImageView7 != null) {
                                                                                                                                                    i2 = R.id.tvTdDbCardHarshAcc;
                                                                                                                                                    AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardHarshAcc);
                                                                                                                                                    if (appCompatTextView6 != null) {
                                                                                                                                                        i2 = R.id.tvTdDbCardHarshBraking;
                                                                                                                                                        AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardHarshBraking);
                                                                                                                                                        if (appCompatTextView7 != null) {
                                                                                                                                                            i2 = R.id.tvTdDbCardIcon;
                                                                                                                                                            AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardIcon);
                                                                                                                                                            if (appCompatImageView8 != null) {
                                                                                                                                                                i2 = R.id.tvTdDbCardIconHarshAcc;
                                                                                                                                                                AppCompatImageView appCompatImageView9 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardIconHarshAcc);
                                                                                                                                                                if (appCompatImageView9 != null) {
                                                                                                                                                                    i2 = R.id.tvTdDbCardIconHarshAccBg;
                                                                                                                                                                    AppCompatImageView appCompatImageView10 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardIconHarshAccBg);
                                                                                                                                                                    if (appCompatImageView10 != null) {
                                                                                                                                                                        i2 = R.id.tvTdDbCardIconHarshBraking;
                                                                                                                                                                        AppCompatImageView appCompatImageView11 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardIconHarshBraking);
                                                                                                                                                                        if (appCompatImageView11 != null) {
                                                                                                                                                                            i2 = R.id.tvTdDbCardIconHarshBrakingBg;
                                                                                                                                                                            AppCompatImageView appCompatImageView12 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardIconHarshBrakingBg);
                                                                                                                                                                            if (appCompatImageView12 != null) {
                                                                                                                                                                                i2 = R.id.tvTdDbCardValue;
                                                                                                                                                                                AppCompatTextView appCompatTextView8 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardValue);
                                                                                                                                                                                if (appCompatTextView8 != null) {
                                                                                                                                                                                    i2 = R.id.tvTdDbCardValueHarshAcc;
                                                                                                                                                                                    AppCompatTextView appCompatTextView9 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardValueHarshAcc);
                                                                                                                                                                                    if (appCompatTextView9 != null) {
                                                                                                                                                                                        i2 = R.id.tvTdDbCardValueHarshBraking;
                                                                                                                                                                                        AppCompatTextView appCompatTextView10 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardValueHarshBraking);
                                                                                                                                                                                        if (appCompatTextView10 != null) {
                                                                                                                                                                                            i2 = R.id.tvTdIdlingByTrips;
                                                                                                                                                                                            AppCompatTextView appCompatTextView11 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTdIdlingByTrips);
                                                                                                                                                                                            if (appCompatTextView11 != null) {
                                                                                                                                                                                                i2 = R.id.tvTdTips;
                                                                                                                                                                                                AppCompatTextView appCompatTextView12 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTdTips);
                                                                                                                                                                                                if (appCompatTextView12 != null) {
                                                                                                                                                                                                    i2 = R.id.tvTdTripDuration;
                                                                                                                                                                                                    AppCompatTextView appCompatTextView13 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTdTripDuration);
                                                                                                                                                                                                    if (appCompatTextView13 != null) {
                                                                                                                                                                                                        i2 = R.id.tvViewOnMap;
                                                                                                                                                                                                        AppCompatTextView appCompatTextView14 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvViewOnMap);
                                                                                                                                                                                                        if (appCompatTextView14 != null) {
                                                                                                                                                                                                            i2 = R.id.viewTripNameLine;
                                                                                                                                                                                                            View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.viewTripNameLine);
                                                                                                                                                                                                            if (findChildViewById4 != null) {
                                                                                                                                                                                                                return new ActivityTripDetailsBinding((ConstraintLayout) view, linearLayout, bind, bind2, constraintLayout, textInputEditText, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, relativeLayout, linearLayout2, linearLayout3, cardView, cardView2, cardView3, linearLayoutCompat, linearLayout4, linearLayout5, roundedBarChart, pageIndicatorView, progressBar, recyclerView, recyclerView2, recyclerView3, textView, scrollView, bind3, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatImageView7, appCompatTextView6, appCompatTextView7, appCompatImageView8, appCompatImageView9, appCompatImageView10, appCompatImageView11, appCompatImageView12, appCompatTextView8, appCompatTextView9, appCompatTextView10, appCompatTextView11, appCompatTextView12, appCompatTextView13, appCompatTextView14, findChildViewById4);
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
    public static ActivityTripDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityTripDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_trip_details, viewGroup, false);
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
