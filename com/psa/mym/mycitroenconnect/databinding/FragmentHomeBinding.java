package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentHomeBinding implements ViewBinding {
    @NonNull
    public final LottieAnimationView animationView;
    @NonNull
    public final View batteryEight;
    @NonNull
    public final View batteryFirst;
    @NonNull
    public final View batteryFive;
    @NonNull
    public final View batteryFour;
    @NonNull
    public final View batteryNine;
    @NonNull
    public final View batterySecond;
    @NonNull
    public final View batterySeven;
    @NonNull
    public final View batterySix;
    @NonNull
    public final View batteryThree;
    @NonNull
    public final AppCompatImageView chargingStatusIndicator;
    @NonNull
    public final ConstraintLayout clCarStatus;
    @NonNull
    public final ConstraintLayout clData;
    @NonNull
    public final CardView cvValetMode;
    @NonNull
    public final AppCompatImageView ivACOnOff;
    @NonNull
    public final AppCompatImageView ivCarLockOnOff;
    @NonNull
    public final AppCompatImageView ivCarMode;
    @NonNull
    public final AppCompatImageView ivCloud;
    @NonNull
    public final AppCompatImageView ivEdit;
    @NonNull
    public final AppCompatImageView ivEngineOnOff;
    @NonNull
    public final AppCompatImageView ivRefresh;
    @NonNull
    public final AppCompatImageView ivTBoxSignal;
    @NonNull
    public final AppCompatImageView ivValetBackground;
    @NonNull
    public final LinearLayoutCompat liBatteryIndicator;
    @NonNull
    public final ConstraintLayout llCarStatusBottom;
    @NonNull
    public final ConstraintLayout llCarStatusTop;
    @NonNull
    public final LinearLayoutCompat llCloudStatus;
    @NonNull
    public final LinearLayout llProgress;
    @NonNull
    public final LinearLayoutCompat llUpdatedTime;
    @NonNull
    public final View relCarOverlay;
    @NonNull
    public final ConstraintLayout rlCarImage;
    @NonNull
    private final NestedScrollView rootView;
    @NonNull
    public final SwitchMaterial switchFence;
    @NonNull
    public final AppCompatTextView tvAcStatus;
    @NonNull
    public final MaterialTextView tvBattery;
    @NonNull
    public final MaterialTextView tvBatteryLabel;
    @NonNull
    public final MaterialTextView tvBatteryPercentage;
    @NonNull
    public final AppCompatTextView tvCardSubTitle;
    @NonNull
    public final AppCompatTextView tvCardTitle;
    @NonNull
    public final AppCompatTextView tvChargingRemainingTime;
    @NonNull
    public final AppCompatTextView tvChargingStatus;
    @NonNull
    public final AppCompatTextView tvConnectionStatus;
    @NonNull
    public final MaterialTextView tvEmptyLabel;
    @NonNull
    public final AppCompatTextView tvEngineStatus;
    @NonNull
    public final AppCompatTextView tvFastChargingHome;
    @NonNull
    public final MaterialTextView tvFullLabel;
    @NonNull
    public final MaterialTextView tvKilometer;
    @NonNull
    public final MaterialTextView tvKilometerLabel;
    @NonNull
    public final MaterialTextView tvKms;
    @NonNull
    public final AppCompatTextView tvLockStatus;
    @NonNull
    public final AppCompatTextView tvUpdatedTime;
    @NonNull
    public final AppCompatTextView tvVehicleLocation;
    @NonNull
    public final MaterialTextView tvVehicleStatusLabel;
    @NonNull
    public final View verticalDivider;
    @NonNull
    public final View viewVerticalFirst;
    @NonNull
    public final View viewVerticalSecond;

    private FragmentHomeBinding(@NonNull NestedScrollView nestedScrollView, @NonNull LottieAnimationView lottieAnimationView, @NonNull View view, @NonNull View view2, @NonNull View view3, @NonNull View view4, @NonNull View view5, @NonNull View view6, @NonNull View view7, @NonNull View view8, @NonNull View view9, @NonNull AppCompatImageView appCompatImageView, @NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout2, @NonNull CardView cardView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull AppCompatImageView appCompatImageView4, @NonNull AppCompatImageView appCompatImageView5, @NonNull AppCompatImageView appCompatImageView6, @NonNull AppCompatImageView appCompatImageView7, @NonNull AppCompatImageView appCompatImageView8, @NonNull AppCompatImageView appCompatImageView9, @NonNull AppCompatImageView appCompatImageView10, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull ConstraintLayout constraintLayout3, @NonNull ConstraintLayout constraintLayout4, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull LinearLayout linearLayout, @NonNull LinearLayoutCompat linearLayoutCompat3, @NonNull View view10, @NonNull ConstraintLayout constraintLayout5, @NonNull SwitchMaterial switchMaterial, @NonNull AppCompatTextView appCompatTextView, @NonNull MaterialTextView materialTextView, @NonNull MaterialTextView materialTextView2, @NonNull MaterialTextView materialTextView3, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull MaterialTextView materialTextView4, @NonNull AppCompatTextView appCompatTextView7, @NonNull AppCompatTextView appCompatTextView8, @NonNull MaterialTextView materialTextView5, @NonNull MaterialTextView materialTextView6, @NonNull MaterialTextView materialTextView7, @NonNull MaterialTextView materialTextView8, @NonNull AppCompatTextView appCompatTextView9, @NonNull AppCompatTextView appCompatTextView10, @NonNull AppCompatTextView appCompatTextView11, @NonNull MaterialTextView materialTextView9, @NonNull View view11, @NonNull View view12, @NonNull View view13) {
        this.rootView = nestedScrollView;
        this.animationView = lottieAnimationView;
        this.batteryEight = view;
        this.batteryFirst = view2;
        this.batteryFive = view3;
        this.batteryFour = view4;
        this.batteryNine = view5;
        this.batterySecond = view6;
        this.batterySeven = view7;
        this.batterySix = view8;
        this.batteryThree = view9;
        this.chargingStatusIndicator = appCompatImageView;
        this.clCarStatus = constraintLayout;
        this.clData = constraintLayout2;
        this.cvValetMode = cardView;
        this.ivACOnOff = appCompatImageView2;
        this.ivCarLockOnOff = appCompatImageView3;
        this.ivCarMode = appCompatImageView4;
        this.ivCloud = appCompatImageView5;
        this.ivEdit = appCompatImageView6;
        this.ivEngineOnOff = appCompatImageView7;
        this.ivRefresh = appCompatImageView8;
        this.ivTBoxSignal = appCompatImageView9;
        this.ivValetBackground = appCompatImageView10;
        this.liBatteryIndicator = linearLayoutCompat;
        this.llCarStatusBottom = constraintLayout3;
        this.llCarStatusTop = constraintLayout4;
        this.llCloudStatus = linearLayoutCompat2;
        this.llProgress = linearLayout;
        this.llUpdatedTime = linearLayoutCompat3;
        this.relCarOverlay = view10;
        this.rlCarImage = constraintLayout5;
        this.switchFence = switchMaterial;
        this.tvAcStatus = appCompatTextView;
        this.tvBattery = materialTextView;
        this.tvBatteryLabel = materialTextView2;
        this.tvBatteryPercentage = materialTextView3;
        this.tvCardSubTitle = appCompatTextView2;
        this.tvCardTitle = appCompatTextView3;
        this.tvChargingRemainingTime = appCompatTextView4;
        this.tvChargingStatus = appCompatTextView5;
        this.tvConnectionStatus = appCompatTextView6;
        this.tvEmptyLabel = materialTextView4;
        this.tvEngineStatus = appCompatTextView7;
        this.tvFastChargingHome = appCompatTextView8;
        this.tvFullLabel = materialTextView5;
        this.tvKilometer = materialTextView6;
        this.tvKilometerLabel = materialTextView7;
        this.tvKms = materialTextView8;
        this.tvLockStatus = appCompatTextView9;
        this.tvUpdatedTime = appCompatTextView10;
        this.tvVehicleLocation = appCompatTextView11;
        this.tvVehicleStatusLabel = materialTextView9;
        this.verticalDivider = view11;
        this.viewVerticalFirst = view12;
        this.viewVerticalSecond = view13;
    }

    @NonNull
    public static FragmentHomeBinding bind(@NonNull View view) {
        int i2 = R.id.animationView;
        LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, R.id.animationView);
        if (lottieAnimationView != null) {
            i2 = R.id.battery_eight;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.battery_eight);
            if (findChildViewById != null) {
                i2 = R.id.battery_first;
                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.battery_first);
                if (findChildViewById2 != null) {
                    i2 = R.id.battery_five;
                    View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.battery_five);
                    if (findChildViewById3 != null) {
                        i2 = R.id.battery_four;
                        View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.battery_four);
                        if (findChildViewById4 != null) {
                            i2 = R.id.battery_nine;
                            View findChildViewById5 = ViewBindings.findChildViewById(view, R.id.battery_nine);
                            if (findChildViewById5 != null) {
                                i2 = R.id.battery_second;
                                View findChildViewById6 = ViewBindings.findChildViewById(view, R.id.battery_second);
                                if (findChildViewById6 != null) {
                                    i2 = R.id.battery_seven;
                                    View findChildViewById7 = ViewBindings.findChildViewById(view, R.id.battery_seven);
                                    if (findChildViewById7 != null) {
                                        i2 = R.id.battery_six;
                                        View findChildViewById8 = ViewBindings.findChildViewById(view, R.id.battery_six);
                                        if (findChildViewById8 != null) {
                                            i2 = R.id.battery_three;
                                            View findChildViewById9 = ViewBindings.findChildViewById(view, R.id.battery_three);
                                            if (findChildViewById9 != null) {
                                                i2 = R.id.chargingStatusIndicator;
                                                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.chargingStatusIndicator);
                                                if (appCompatImageView != null) {
                                                    i2 = R.id.clCarStatus;
                                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clCarStatus);
                                                    if (constraintLayout != null) {
                                                        i2 = R.id.clData;
                                                        ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clData);
                                                        if (constraintLayout2 != null) {
                                                            i2 = R.id.cv_valet_mode;
                                                            CardView cardView = (CardView) ViewBindings.findChildViewById(view, R.id.cv_valet_mode);
                                                            if (cardView != null) {
                                                                i2 = R.id.ivACOnOff;
                                                                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivACOnOff);
                                                                if (appCompatImageView2 != null) {
                                                                    i2 = R.id.ivCarLockOnOff;
                                                                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCarLockOnOff);
                                                                    if (appCompatImageView3 != null) {
                                                                        i2 = R.id.ivCarMode;
                                                                        AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCarMode);
                                                                        if (appCompatImageView4 != null) {
                                                                            i2 = R.id.ivCloud;
                                                                            AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCloud);
                                                                            if (appCompatImageView5 != null) {
                                                                                i2 = R.id.ivEdit;
                                                                                AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivEdit);
                                                                                if (appCompatImageView6 != null) {
                                                                                    i2 = R.id.ivEngineOnOff;
                                                                                    AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivEngineOnOff);
                                                                                    if (appCompatImageView7 != null) {
                                                                                        i2 = R.id.ivRefresh;
                                                                                        AppCompatImageView appCompatImageView8 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivRefresh);
                                                                                        if (appCompatImageView8 != null) {
                                                                                            i2 = R.id.ivTBoxSignal;
                                                                                            AppCompatImageView appCompatImageView9 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivTBoxSignal);
                                                                                            if (appCompatImageView9 != null) {
                                                                                                i2 = R.id.ivValetBackground;
                                                                                                AppCompatImageView appCompatImageView10 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivValetBackground);
                                                                                                if (appCompatImageView10 != null) {
                                                                                                    i2 = R.id.liBatteryIndicator;
                                                                                                    LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.liBatteryIndicator);
                                                                                                    if (linearLayoutCompat != null) {
                                                                                                        i2 = R.id.llCarStatusBottom;
                                                                                                        ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.llCarStatusBottom);
                                                                                                        if (constraintLayout3 != null) {
                                                                                                            i2 = R.id.llCarStatusTop;
                                                                                                            ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.llCarStatusTop);
                                                                                                            if (constraintLayout4 != null) {
                                                                                                                i2 = R.id.llCloudStatus;
                                                                                                                LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llCloudStatus);
                                                                                                                if (linearLayoutCompat2 != null) {
                                                                                                                    i2 = R.id.llProgress;
                                                                                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.llProgress);
                                                                                                                    if (linearLayout != null) {
                                                                                                                        i2 = R.id.llUpdatedTime;
                                                                                                                        LinearLayoutCompat linearLayoutCompat3 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llUpdatedTime);
                                                                                                                        if (linearLayoutCompat3 != null) {
                                                                                                                            i2 = R.id.rel_car_overlay;
                                                                                                                            View findChildViewById10 = ViewBindings.findChildViewById(view, R.id.rel_car_overlay);
                                                                                                                            if (findChildViewById10 != null) {
                                                                                                                                i2 = R.id.rlCarImage;
                                                                                                                                ConstraintLayout constraintLayout5 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.rlCarImage);
                                                                                                                                if (constraintLayout5 != null) {
                                                                                                                                    i2 = R.id.switchFence;
                                                                                                                                    SwitchMaterial switchMaterial = (SwitchMaterial) ViewBindings.findChildViewById(view, R.id.switchFence);
                                                                                                                                    if (switchMaterial != null) {
                                                                                                                                        i2 = R.id.tvAcStatus;
                                                                                                                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvAcStatus);
                                                                                                                                        if (appCompatTextView != null) {
                                                                                                                                            i2 = R.id.tvBattery;
                                                                                                                                            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvBattery);
                                                                                                                                            if (materialTextView != null) {
                                                                                                                                                i2 = R.id.tvBatteryLabel;
                                                                                                                                                MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvBatteryLabel);
                                                                                                                                                if (materialTextView2 != null) {
                                                                                                                                                    i2 = R.id.tvBatteryPercentage;
                                                                                                                                                    MaterialTextView materialTextView3 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvBatteryPercentage);
                                                                                                                                                    if (materialTextView3 != null) {
                                                                                                                                                        i2 = R.id.tvCardSubTitle;
                                                                                                                                                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCardSubTitle);
                                                                                                                                                        if (appCompatTextView2 != null) {
                                                                                                                                                            i2 = R.id.tvCardTitle;
                                                                                                                                                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvCardTitle);
                                                                                                                                                            if (appCompatTextView3 != null) {
                                                                                                                                                                i2 = R.id.tvChargingRemainingTime;
                                                                                                                                                                AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargingRemainingTime);
                                                                                                                                                                if (appCompatTextView4 != null) {
                                                                                                                                                                    i2 = R.id.tvChargingStatus;
                                                                                                                                                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChargingStatus);
                                                                                                                                                                    if (appCompatTextView5 != null) {
                                                                                                                                                                        i2 = R.id.tvConnectionStatus;
                                                                                                                                                                        AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvConnectionStatus);
                                                                                                                                                                        if (appCompatTextView6 != null) {
                                                                                                                                                                            i2 = R.id.tvEmptyLabel;
                                                                                                                                                                            MaterialTextView materialTextView4 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvEmptyLabel);
                                                                                                                                                                            if (materialTextView4 != null) {
                                                                                                                                                                                i2 = R.id.tvEngineStatus;
                                                                                                                                                                                AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEngineStatus);
                                                                                                                                                                                if (appCompatTextView7 != null) {
                                                                                                                                                                                    i2 = R.id.tvFastChargingHome;
                                                                                                                                                                                    AppCompatTextView appCompatTextView8 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFastChargingHome);
                                                                                                                                                                                    if (appCompatTextView8 != null) {
                                                                                                                                                                                        i2 = R.id.tvFullLabel;
                                                                                                                                                                                        MaterialTextView materialTextView5 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvFullLabel);
                                                                                                                                                                                        if (materialTextView5 != null) {
                                                                                                                                                                                            i2 = R.id.tvKilometer;
                                                                                                                                                                                            MaterialTextView materialTextView6 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvKilometer);
                                                                                                                                                                                            if (materialTextView6 != null) {
                                                                                                                                                                                                i2 = R.id.tvKilometerLabel;
                                                                                                                                                                                                MaterialTextView materialTextView7 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvKilometerLabel);
                                                                                                                                                                                                if (materialTextView7 != null) {
                                                                                                                                                                                                    i2 = R.id.tvKms;
                                                                                                                                                                                                    MaterialTextView materialTextView8 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvKms);
                                                                                                                                                                                                    if (materialTextView8 != null) {
                                                                                                                                                                                                        i2 = R.id.tvLockStatus;
                                                                                                                                                                                                        AppCompatTextView appCompatTextView9 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvLockStatus);
                                                                                                                                                                                                        if (appCompatTextView9 != null) {
                                                                                                                                                                                                            i2 = R.id.tvUpdatedTime;
                                                                                                                                                                                                            AppCompatTextView appCompatTextView10 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvUpdatedTime);
                                                                                                                                                                                                            if (appCompatTextView10 != null) {
                                                                                                                                                                                                                i2 = R.id.tvVehicleLocation;
                                                                                                                                                                                                                AppCompatTextView appCompatTextView11 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvVehicleLocation);
                                                                                                                                                                                                                if (appCompatTextView11 != null) {
                                                                                                                                                                                                                    i2 = R.id.tvVehicleStatusLabel;
                                                                                                                                                                                                                    MaterialTextView materialTextView9 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvVehicleStatusLabel);
                                                                                                                                                                                                                    if (materialTextView9 != null) {
                                                                                                                                                                                                                        i2 = R.id.verticalDivider;
                                                                                                                                                                                                                        View findChildViewById11 = ViewBindings.findChildViewById(view, R.id.verticalDivider);
                                                                                                                                                                                                                        if (findChildViewById11 != null) {
                                                                                                                                                                                                                            i2 = R.id.view_vertical_first;
                                                                                                                                                                                                                            View findChildViewById12 = ViewBindings.findChildViewById(view, R.id.view_vertical_first);
                                                                                                                                                                                                                            if (findChildViewById12 != null) {
                                                                                                                                                                                                                                i2 = R.id.view_vertical_second;
                                                                                                                                                                                                                                View findChildViewById13 = ViewBindings.findChildViewById(view, R.id.view_vertical_second);
                                                                                                                                                                                                                                if (findChildViewById13 != null) {
                                                                                                                                                                                                                                    return new FragmentHomeBinding((NestedScrollView) view, lottieAnimationView, findChildViewById, findChildViewById2, findChildViewById3, findChildViewById4, findChildViewById5, findChildViewById6, findChildViewById7, findChildViewById8, findChildViewById9, appCompatImageView, constraintLayout, constraintLayout2, cardView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatImageView7, appCompatImageView8, appCompatImageView9, appCompatImageView10, linearLayoutCompat, constraintLayout3, constraintLayout4, linearLayoutCompat2, linearLayout, linearLayoutCompat3, findChildViewById10, constraintLayout5, switchMaterial, appCompatTextView, materialTextView, materialTextView2, materialTextView3, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, materialTextView4, appCompatTextView7, appCompatTextView8, materialTextView5, materialTextView6, materialTextView7, materialTextView8, appCompatTextView9, appCompatTextView10, appCompatTextView11, materialTextView9, findChildViewById11, findChildViewById12, findChildViewById13);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FragmentHomeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_home, viewGroup, false);
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
