package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.psa.mym.mycitroenconnect.views.CustomSpinner;
import com.psa.mym.mycitroenconnect.views.RoundedBarChart;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentMyCarHealthBinding implements ViewBinding {
    @NonNull
    public final RoundedBarChart barCarHealth;
    @NonNull
    public final MaterialButton btnDiagnosticAssistantCall;
    @NonNull
    public final ConstraintLayout clParent;
    @NonNull
    public final CardView cvB2Service;
    @NonNull
    public final CardView cvDiagnosedDetails;
    @NonNull
    public final CardView cvTotalDriveTime;
    @NonNull
    public final CardView cvVehicleHealthDetails;
    @NonNull
    public final AppCompatImageView ivIcon;
    @NonNull
    public final AppCompatImageView ivVehicleHealthInfo;
    @NonNull
    public final RelativeLayout rlSelectPeriod;
    @NonNull
    private final NestedScrollView rootView;
    @NonNull
    public final RecyclerView rvDiagnosedDetails;
    @NonNull
    public final RecyclerView rvVehicleHealth;
    @NonNull
    public final RecyclerView rvVehicleHealthDetails;
    @NonNull
    public final RecyclerView rvVehicleStatus;
    @NonNull
    public final CustomSpinner spPeriod;
    @NonNull
    public final AppCompatTextView tvCarDiagnosticLbl;
    @NonNull
    public final AppCompatTextView tvChargingLbl;
    @NonNull
    public final AppCompatTextView tvChargingPeriod;
    @NonNull
    public final AppCompatTextView tvEventsLbl;
    @NonNull
    public final AppCompatTextView tvHour;
    @NonNull
    public final AppCompatTextView tvLastDiagnosed;
    @NonNull
    public final AppCompatTextView tvMonthlyHealthReport;
    @NonNull
    public final AppCompatTextView tvSelectPeriod;
    @NonNull
    public final AppCompatTextView tvVehicleHealthLbl;

    private FragmentMyCarHealthBinding(@NonNull NestedScrollView nestedScrollView, @NonNull RoundedBarChart roundedBarChart, @NonNull MaterialButton materialButton, @NonNull ConstraintLayout constraintLayout, @NonNull CardView cardView, @NonNull CardView cardView2, @NonNull CardView cardView3, @NonNull CardView cardView4, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull RelativeLayout relativeLayout, @NonNull RecyclerView recyclerView, @NonNull RecyclerView recyclerView2, @NonNull RecyclerView recyclerView3, @NonNull RecyclerView recyclerView4, @NonNull CustomSpinner customSpinner, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull AppCompatTextView appCompatTextView7, @NonNull AppCompatTextView appCompatTextView8, @NonNull AppCompatTextView appCompatTextView9) {
        this.rootView = nestedScrollView;
        this.barCarHealth = roundedBarChart;
        this.btnDiagnosticAssistantCall = materialButton;
        this.clParent = constraintLayout;
        this.cvB2Service = cardView;
        this.cvDiagnosedDetails = cardView2;
        this.cvTotalDriveTime = cardView3;
        this.cvVehicleHealthDetails = cardView4;
        this.ivIcon = appCompatImageView;
        this.ivVehicleHealthInfo = appCompatImageView2;
        this.rlSelectPeriod = relativeLayout;
        this.rvDiagnosedDetails = recyclerView;
        this.rvVehicleHealth = recyclerView2;
        this.rvVehicleHealthDetails = recyclerView3;
        this.rvVehicleStatus = recyclerView4;
        this.spPeriod = customSpinner;
        this.tvCarDiagnosticLbl = appCompatTextView;
        this.tvChargingLbl = appCompatTextView2;
        this.tvChargingPeriod = appCompatTextView3;
        this.tvEventsLbl = appCompatTextView4;
        this.tvHour = appCompatTextView5;
        this.tvLastDiagnosed = appCompatTextView6;
        this.tvMonthlyHealthReport = appCompatTextView7;
        this.tvSelectPeriod = appCompatTextView8;
        this.tvVehicleHealthLbl = appCompatTextView9;
    }

    @NonNull
    public static FragmentMyCarHealthBinding bind(@NonNull View view) {
        int i2 = R.id.barCarHealth;
        RoundedBarChart roundedBarChart = (RoundedBarChart) ViewBindings.findChildViewById(view, R.id.barCarHealth);
        if (roundedBarChart != null) {
            i2 = R.id.btnDiagnosticAssistantCall;
            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, R.id.btnDiagnosticAssistantCall);
            if (materialButton != null) {
                i2 = R.id.clParent;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clParent);
                if (constraintLayout != null) {
                    i2 = R.id.cvB2Service;
                    CardView cardView = (CardView) ViewBindings.findChildViewById(view, R.id.cvB2Service);
                    if (cardView != null) {
                        i2 = R.id.cvDiagnosedDetails;
                        CardView cardView2 = (CardView) ViewBindings.findChildViewById(view, R.id.cvDiagnosedDetails);
                        if (cardView2 != null) {
                            i2 = R.id.cvTotalDriveTime;
                            CardView cardView3 = (CardView) ViewBindings.findChildViewById(view, R.id.cvTotalDriveTime);
                            if (cardView3 != null) {
                                i2 = R.id.cvVehicleHealthDetails;
                                CardView cardView4 = (CardView) ViewBindings.findChildViewById(view, R.id.cvVehicleHealthDetails);
                                if (cardView4 != null) {
                                    i2 = R.id.ivIcon;
                                    AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivIcon);
                                    if (appCompatImageView != null) {
                                        i2 = R.id.ivVehicleHealthInfo;
                                        AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivVehicleHealthInfo);
                                        if (appCompatImageView2 != null) {
                                            i2 = R.id.rlSelectPeriod;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rlSelectPeriod);
                                            if (relativeLayout != null) {
                                                i2 = R.id.rvDiagnosedDetails;
                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvDiagnosedDetails);
                                                if (recyclerView != null) {
                                                    i2 = R.id.rvVehicleHealth;
                                                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvVehicleHealth);
                                                    if (recyclerView2 != null) {
                                                        i2 = R.id.rvVehicleHealthDetails;
                                                        RecyclerView recyclerView3 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvVehicleHealthDetails);
                                                        if (recyclerView3 != null) {
                                                            i2 = R.id.rvVehicleStatus;
                                                            RecyclerView recyclerView4 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvVehicleStatus);
                                                            if (recyclerView4 != null) {
                                                                i2 = R.id.spPeriod;
                                                                CustomSpinner customSpinner = (CustomSpinner) ViewBindings.findChildViewById(view, R.id.spPeriod);
                                                                if (customSpinner != null) {
                                                                    i2 = R.id.tvCarDiagnosticLbl;
                                                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCarDiagnosticLbl);
                                                                    if (appCompatTextView != null) {
                                                                        i2 = R.id.tvChargingLbl;
                                                                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargingLbl);
                                                                        if (appCompatTextView2 != null) {
                                                                            i2 = R.id.tvChargingPeriod;
                                                                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargingPeriod);
                                                                            if (appCompatTextView3 != null) {
                                                                                i2 = R.id.tvEventsLbl;
                                                                                AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEventsLbl);
                                                                                if (appCompatTextView4 != null) {
                                                                                    i2 = R.id.tvHour;
                                                                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvHour);
                                                                                    if (appCompatTextView5 != null) {
                                                                                        i2 = R.id.tvLastDiagnosed;
                                                                                        AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvLastDiagnosed);
                                                                                        if (appCompatTextView6 != null) {
                                                                                            i2 = R.id.tvMonthlyHealthReport;
                                                                                            AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvMonthlyHealthReport);
                                                                                            if (appCompatTextView7 != null) {
                                                                                                i2 = R.id.tvSelectPeriod;
                                                                                                AppCompatTextView appCompatTextView8 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSelectPeriod);
                                                                                                if (appCompatTextView8 != null) {
                                                                                                    i2 = R.id.tvVehicleHealthLbl;
                                                                                                    AppCompatTextView appCompatTextView9 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvVehicleHealthLbl);
                                                                                                    if (appCompatTextView9 != null) {
                                                                                                        return new FragmentMyCarHealthBinding((NestedScrollView) view, roundedBarChart, materialButton, constraintLayout, cardView, cardView2, cardView3, cardView4, appCompatImageView, appCompatImageView2, relativeLayout, recyclerView, recyclerView2, recyclerView3, recyclerView4, customSpinner, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9);
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
    public static FragmentMyCarHealthBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentMyCarHealthBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_my_car_health, viewGroup, false);
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
