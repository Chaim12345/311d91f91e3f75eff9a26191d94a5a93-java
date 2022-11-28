package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityValetBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnIdling2;
    @NonNull
    public final AppCompatButton btnIdling4;
    @NonNull
    public final AppCompatButton btnIdling6;
    @NonNull
    public final AppCompatButton btnIdling8;
    @NonNull
    public final AppCompatButton btnIdlingEdit;
    @NonNull
    public final AppCompatButton btnIdlingEditVal;
    @NonNull
    public final AppCompatButton btnRadEdit;
    @NonNull
    public final AppCompatButton btnRadEditVal;
    @NonNull
    public final AppCompatButton btnRadSet;
    @NonNull
    public final AppCompatButton btnSpeed10;
    @NonNull
    public final AppCompatButton btnSpeed20;
    @NonNull
    public final AppCompatButton btnSpeed30;
    @NonNull
    public final AppCompatButton btnSpeed40;
    @NonNull
    public final AppCompatButton btnSpeed50;
    @NonNull
    public final AppCompatButton btnSpeedEdit;
    @NonNull
    public final AppCompatButton btnSpeedEditVal;
    @NonNull
    public final AppCompatButton btnValetRad1km;
    @NonNull
    public final AppCompatButton btnValetRad250m;
    @NonNull
    public final AppCompatButton btnValetRad500m;
    @NonNull
    public final AppCompatButton btnValetRad750m;
    @NonNull
    public final LinearLayoutCompat layoutIdlingButtons;
    @NonNull
    public final LinearLayoutCompat layoutMaxSpeedButtons;
    @NonNull
    public final LinearLayoutCompat layoutRadiusButtons;
    @NonNull
    public final LayoutDashboardModeHeaderBinding layoutValetHeader;
    @NonNull
    public final ConstraintLayout layoutValetIdling;
    @NonNull
    public final ConstraintLayout layoutValetRad;
    @NonNull
    public final ConstraintLayout layoutValetSpeed;
    @NonNull
    public final FragmentContainerView mapContainer;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final AppCompatTextView tvSetIdlingTime;
    @NonNull
    public final AppCompatTextView tvSetMaxSpeed;
    @NonNull
    public final AppCompatTextView tvSetRadius;

    private ActivityValetBinding(@NonNull ConstraintLayout constraintLayout, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatButton appCompatButton3, @NonNull AppCompatButton appCompatButton4, @NonNull AppCompatButton appCompatButton5, @NonNull AppCompatButton appCompatButton6, @NonNull AppCompatButton appCompatButton7, @NonNull AppCompatButton appCompatButton8, @NonNull AppCompatButton appCompatButton9, @NonNull AppCompatButton appCompatButton10, @NonNull AppCompatButton appCompatButton11, @NonNull AppCompatButton appCompatButton12, @NonNull AppCompatButton appCompatButton13, @NonNull AppCompatButton appCompatButton14, @NonNull AppCompatButton appCompatButton15, @NonNull AppCompatButton appCompatButton16, @NonNull AppCompatButton appCompatButton17, @NonNull AppCompatButton appCompatButton18, @NonNull AppCompatButton appCompatButton19, @NonNull AppCompatButton appCompatButton20, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull LinearLayoutCompat linearLayoutCompat3, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull ConstraintLayout constraintLayout2, @NonNull ConstraintLayout constraintLayout3, @NonNull ConstraintLayout constraintLayout4, @NonNull FragmentContainerView fragmentContainerView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3) {
        this.rootView = constraintLayout;
        this.btnIdling2 = appCompatButton;
        this.btnIdling4 = appCompatButton2;
        this.btnIdling6 = appCompatButton3;
        this.btnIdling8 = appCompatButton4;
        this.btnIdlingEdit = appCompatButton5;
        this.btnIdlingEditVal = appCompatButton6;
        this.btnRadEdit = appCompatButton7;
        this.btnRadEditVal = appCompatButton8;
        this.btnRadSet = appCompatButton9;
        this.btnSpeed10 = appCompatButton10;
        this.btnSpeed20 = appCompatButton11;
        this.btnSpeed30 = appCompatButton12;
        this.btnSpeed40 = appCompatButton13;
        this.btnSpeed50 = appCompatButton14;
        this.btnSpeedEdit = appCompatButton15;
        this.btnSpeedEditVal = appCompatButton16;
        this.btnValetRad1km = appCompatButton17;
        this.btnValetRad250m = appCompatButton18;
        this.btnValetRad500m = appCompatButton19;
        this.btnValetRad750m = appCompatButton20;
        this.layoutIdlingButtons = linearLayoutCompat;
        this.layoutMaxSpeedButtons = linearLayoutCompat2;
        this.layoutRadiusButtons = linearLayoutCompat3;
        this.layoutValetHeader = layoutDashboardModeHeaderBinding;
        this.layoutValetIdling = constraintLayout2;
        this.layoutValetRad = constraintLayout3;
        this.layoutValetSpeed = constraintLayout4;
        this.mapContainer = fragmentContainerView;
        this.tvSetIdlingTime = appCompatTextView;
        this.tvSetMaxSpeed = appCompatTextView2;
        this.tvSetRadius = appCompatTextView3;
    }

    @NonNull
    public static ActivityValetBinding bind(@NonNull View view) {
        int i2 = R.id.btnIdling2;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnIdling2);
        if (appCompatButton != null) {
            i2 = R.id.btnIdling4;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnIdling4);
            if (appCompatButton2 != null) {
                i2 = R.id.btnIdling6;
                AppCompatButton appCompatButton3 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnIdling6);
                if (appCompatButton3 != null) {
                    i2 = R.id.btnIdling8;
                    AppCompatButton appCompatButton4 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnIdling8);
                    if (appCompatButton4 != null) {
                        i2 = R.id.btnIdlingEdit;
                        AppCompatButton appCompatButton5 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnIdlingEdit);
                        if (appCompatButton5 != null) {
                            i2 = R.id.btnIdlingEditVal;
                            AppCompatButton appCompatButton6 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnIdlingEditVal);
                            if (appCompatButton6 != null) {
                                i2 = R.id.btnRadEdit;
                                AppCompatButton appCompatButton7 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRadEdit);
                                if (appCompatButton7 != null) {
                                    i2 = R.id.btnRadEditVal;
                                    AppCompatButton appCompatButton8 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRadEditVal);
                                    if (appCompatButton8 != null) {
                                        i2 = R.id.btnRadSet;
                                        AppCompatButton appCompatButton9 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnRadSet);
                                        if (appCompatButton9 != null) {
                                            i2 = R.id.btnSpeed10;
                                            AppCompatButton appCompatButton10 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSpeed10);
                                            if (appCompatButton10 != null) {
                                                i2 = R.id.btnSpeed20;
                                                AppCompatButton appCompatButton11 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSpeed20);
                                                if (appCompatButton11 != null) {
                                                    i2 = R.id.btnSpeed30;
                                                    AppCompatButton appCompatButton12 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSpeed30);
                                                    if (appCompatButton12 != null) {
                                                        i2 = R.id.btnSpeed40;
                                                        AppCompatButton appCompatButton13 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSpeed40);
                                                        if (appCompatButton13 != null) {
                                                            i2 = R.id.btnSpeed50;
                                                            AppCompatButton appCompatButton14 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSpeed50);
                                                            if (appCompatButton14 != null) {
                                                                i2 = R.id.btnSpeedEdit;
                                                                AppCompatButton appCompatButton15 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSpeedEdit);
                                                                if (appCompatButton15 != null) {
                                                                    i2 = R.id.btnSpeedEditVal;
                                                                    AppCompatButton appCompatButton16 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSpeedEditVal);
                                                                    if (appCompatButton16 != null) {
                                                                        i2 = R.id.btnValetRad1km;
                                                                        AppCompatButton appCompatButton17 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnValetRad1km);
                                                                        if (appCompatButton17 != null) {
                                                                            i2 = R.id.btnValetRad250m;
                                                                            AppCompatButton appCompatButton18 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnValetRad250m);
                                                                            if (appCompatButton18 != null) {
                                                                                i2 = R.id.btnValetRad500m;
                                                                                AppCompatButton appCompatButton19 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnValetRad500m);
                                                                                if (appCompatButton19 != null) {
                                                                                    i2 = R.id.btnValetRad750m;
                                                                                    AppCompatButton appCompatButton20 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnValetRad750m);
                                                                                    if (appCompatButton20 != null) {
                                                                                        i2 = R.id.layoutIdlingButtons;
                                                                                        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutIdlingButtons);
                                                                                        if (linearLayoutCompat != null) {
                                                                                            i2 = R.id.layoutMaxSpeedButtons;
                                                                                            LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutMaxSpeedButtons);
                                                                                            if (linearLayoutCompat2 != null) {
                                                                                                i2 = R.id.layoutRadiusButtons;
                                                                                                LinearLayoutCompat linearLayoutCompat3 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutRadiusButtons);
                                                                                                if (linearLayoutCompat3 != null) {
                                                                                                    i2 = R.id.layoutValetHeader;
                                                                                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutValetHeader);
                                                                                                    if (findChildViewById != null) {
                                                                                                        LayoutDashboardModeHeaderBinding bind = LayoutDashboardModeHeaderBinding.bind(findChildViewById);
                                                                                                        i2 = R.id.layoutValetIdling;
                                                                                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutValetIdling);
                                                                                                        if (constraintLayout != null) {
                                                                                                            i2 = R.id.layoutValetRad;
                                                                                                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutValetRad);
                                                                                                            if (constraintLayout2 != null) {
                                                                                                                i2 = R.id.layoutValetSpeed;
                                                                                                                ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutValetSpeed);
                                                                                                                if (constraintLayout3 != null) {
                                                                                                                    i2 = R.id.mapContainer;
                                                                                                                    FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, R.id.mapContainer);
                                                                                                                    if (fragmentContainerView != null) {
                                                                                                                        i2 = R.id.tvSetIdlingTime;
                                                                                                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSetIdlingTime);
                                                                                                                        if (appCompatTextView != null) {
                                                                                                                            i2 = R.id.tvSetMaxSpeed;
                                                                                                                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSetMaxSpeed);
                                                                                                                            if (appCompatTextView2 != null) {
                                                                                                                                i2 = R.id.tvSetRadius;
                                                                                                                                AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSetRadius);
                                                                                                                                if (appCompatTextView3 != null) {
                                                                                                                                    return new ActivityValetBinding((ConstraintLayout) view, appCompatButton, appCompatButton2, appCompatButton3, appCompatButton4, appCompatButton5, appCompatButton6, appCompatButton7, appCompatButton8, appCompatButton9, appCompatButton10, appCompatButton11, appCompatButton12, appCompatButton13, appCompatButton14, appCompatButton15, appCompatButton16, appCompatButton17, appCompatButton18, appCompatButton19, appCompatButton20, linearLayoutCompat, linearLayoutCompat2, linearLayoutCompat3, bind, constraintLayout, constraintLayout2, constraintLayout3, fragmentContainerView, appCompatTextView, appCompatTextView2, appCompatTextView3);
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
    public static ActivityValetBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityValetBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_valet, viewGroup, false);
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
