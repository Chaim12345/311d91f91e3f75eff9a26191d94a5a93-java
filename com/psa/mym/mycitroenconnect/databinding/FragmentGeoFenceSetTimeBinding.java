package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.psa.mym.mycitroenconnect.views.CustomSpinner;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentGeoFenceSetTimeBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnSaveContinue;
    @NonNull
    public final ConstraintLayout clSetTimeHome;
    @NonNull
    public final MaterialCardView cvDays;
    @NonNull
    public final LayoutAmPmBinding endAMPM;
    @NonNull
    public final AppCompatImageView ivDown;
    @NonNull
    public final ConstraintLayout layoutSetTime;
    @NonNull
    public final MaterialRadioButton rbFullDay;
    @NonNull
    public final MaterialRadioButton rbNo;
    @NonNull
    public final MaterialRadioButton rbSetTiming;
    @NonNull
    public final MaterialRadioButton rbYes;
    @NonNull
    public final RadioGroup rgSetDayTime;
    @NonNull
    public final RadioGroup rgSetTime;
    @NonNull
    public final RelativeLayout rlSelectDays;
    @NonNull
    public final RelativeLayout rlTime;
    @NonNull
    private final LinearLayoutCompat rootView;
    @NonNull
    public final RecyclerView rvDays;
    @NonNull
    public final CustomSpinner spDays;
    @NonNull
    public final CommonSpinnerViewBinding spinEndTimeHr;
    @NonNull
    public final CommonSpinnerViewBinding spinEndTimeMin;
    @NonNull
    public final CommonSpinnerViewBinding spinStartTimeHr;
    @NonNull
    public final CommonSpinnerViewBinding spinStartTimeMin;
    @NonNull
    public final LayoutAmPmBinding startAMPM;
    @NonNull
    public final SwitchMaterial switchRepeatTime;
    @NonNull
    public final SwitchMaterial switchSetTime;
    @NonNull
    public final AppCompatTextView tvEndTime;
    @NonNull
    public final AppCompatTextView tvSelectDays;
    @NonNull
    public final AppCompatTextView tvSelectedDateRange;
    @NonNull
    public final AppCompatTextView tvSetTimeDefaultMsg;
    @NonNull
    public final AppCompatTextView tvSetTimeDesc;
    @NonNull
    public final AppCompatTextView tvSetTimeQst;
    @NonNull
    public final AppCompatTextView tvSetTimeRptQst;
    @NonNull
    public final AppCompatTextView tvStartTime;
    @NonNull
    public final AppCompatTextView tvWantToSetTime;
    @NonNull
    public final AppCompatTextView tvWantToSetTimeDesc;

    private FragmentGeoFenceSetTimeBinding(@NonNull LinearLayoutCompat linearLayoutCompat, @NonNull AppCompatButton appCompatButton, @NonNull ConstraintLayout constraintLayout, @NonNull MaterialCardView materialCardView, @NonNull LayoutAmPmBinding layoutAmPmBinding, @NonNull AppCompatImageView appCompatImageView, @NonNull ConstraintLayout constraintLayout2, @NonNull MaterialRadioButton materialRadioButton, @NonNull MaterialRadioButton materialRadioButton2, @NonNull MaterialRadioButton materialRadioButton3, @NonNull MaterialRadioButton materialRadioButton4, @NonNull RadioGroup radioGroup, @NonNull RadioGroup radioGroup2, @NonNull RelativeLayout relativeLayout, @NonNull RelativeLayout relativeLayout2, @NonNull RecyclerView recyclerView, @NonNull CustomSpinner customSpinner, @NonNull CommonSpinnerViewBinding commonSpinnerViewBinding, @NonNull CommonSpinnerViewBinding commonSpinnerViewBinding2, @NonNull CommonSpinnerViewBinding commonSpinnerViewBinding3, @NonNull CommonSpinnerViewBinding commonSpinnerViewBinding4, @NonNull LayoutAmPmBinding layoutAmPmBinding2, @NonNull SwitchMaterial switchMaterial, @NonNull SwitchMaterial switchMaterial2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull AppCompatTextView appCompatTextView7, @NonNull AppCompatTextView appCompatTextView8, @NonNull AppCompatTextView appCompatTextView9, @NonNull AppCompatTextView appCompatTextView10) {
        this.rootView = linearLayoutCompat;
        this.btnSaveContinue = appCompatButton;
        this.clSetTimeHome = constraintLayout;
        this.cvDays = materialCardView;
        this.endAMPM = layoutAmPmBinding;
        this.ivDown = appCompatImageView;
        this.layoutSetTime = constraintLayout2;
        this.rbFullDay = materialRadioButton;
        this.rbNo = materialRadioButton2;
        this.rbSetTiming = materialRadioButton3;
        this.rbYes = materialRadioButton4;
        this.rgSetDayTime = radioGroup;
        this.rgSetTime = radioGroup2;
        this.rlSelectDays = relativeLayout;
        this.rlTime = relativeLayout2;
        this.rvDays = recyclerView;
        this.spDays = customSpinner;
        this.spinEndTimeHr = commonSpinnerViewBinding;
        this.spinEndTimeMin = commonSpinnerViewBinding2;
        this.spinStartTimeHr = commonSpinnerViewBinding3;
        this.spinStartTimeMin = commonSpinnerViewBinding4;
        this.startAMPM = layoutAmPmBinding2;
        this.switchRepeatTime = switchMaterial;
        this.switchSetTime = switchMaterial2;
        this.tvEndTime = appCompatTextView;
        this.tvSelectDays = appCompatTextView2;
        this.tvSelectedDateRange = appCompatTextView3;
        this.tvSetTimeDefaultMsg = appCompatTextView4;
        this.tvSetTimeDesc = appCompatTextView5;
        this.tvSetTimeQst = appCompatTextView6;
        this.tvSetTimeRptQst = appCompatTextView7;
        this.tvStartTime = appCompatTextView8;
        this.tvWantToSetTime = appCompatTextView9;
        this.tvWantToSetTimeDesc = appCompatTextView10;
    }

    @NonNull
    public static FragmentGeoFenceSetTimeBinding bind(@NonNull View view) {
        int i2 = R.id.btnSaveContinue;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnSaveContinue);
        if (appCompatButton != null) {
            i2 = R.id.clSetTimeHome;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clSetTimeHome);
            if (constraintLayout != null) {
                i2 = R.id.cvDays;
                MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, R.id.cvDays);
                if (materialCardView != null) {
                    i2 = R.id.endAMPM;
                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.endAMPM);
                    if (findChildViewById != null) {
                        LayoutAmPmBinding bind = LayoutAmPmBinding.bind(findChildViewById);
                        i2 = R.id.ivDown;
                        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivDown);
                        if (appCompatImageView != null) {
                            i2 = R.id.layoutSetTime;
                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutSetTime);
                            if (constraintLayout2 != null) {
                                i2 = R.id.rbFullDay;
                                MaterialRadioButton materialRadioButton = (MaterialRadioButton) ViewBindings.findChildViewById(view, R.id.rbFullDay);
                                if (materialRadioButton != null) {
                                    i2 = R.id.rbNo;
                                    MaterialRadioButton materialRadioButton2 = (MaterialRadioButton) ViewBindings.findChildViewById(view, R.id.rbNo);
                                    if (materialRadioButton2 != null) {
                                        i2 = R.id.rbSetTiming;
                                        MaterialRadioButton materialRadioButton3 = (MaterialRadioButton) ViewBindings.findChildViewById(view, R.id.rbSetTiming);
                                        if (materialRadioButton3 != null) {
                                            i2 = R.id.rbYes;
                                            MaterialRadioButton materialRadioButton4 = (MaterialRadioButton) ViewBindings.findChildViewById(view, R.id.rbYes);
                                            if (materialRadioButton4 != null) {
                                                i2 = R.id.rgSetDayTime;
                                                RadioGroup radioGroup = (RadioGroup) ViewBindings.findChildViewById(view, R.id.rgSetDayTime);
                                                if (radioGroup != null) {
                                                    i2 = R.id.rgSetTime;
                                                    RadioGroup radioGroup2 = (RadioGroup) ViewBindings.findChildViewById(view, R.id.rgSetTime);
                                                    if (radioGroup2 != null) {
                                                        i2 = R.id.rlSelectDays;
                                                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rlSelectDays);
                                                        if (relativeLayout != null) {
                                                            i2 = R.id.rlTime;
                                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rlTime);
                                                            if (relativeLayout2 != null) {
                                                                i2 = R.id.rvDays;
                                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvDays);
                                                                if (recyclerView != null) {
                                                                    i2 = R.id.spDays;
                                                                    CustomSpinner customSpinner = (CustomSpinner) ViewBindings.findChildViewById(view, R.id.spDays);
                                                                    if (customSpinner != null) {
                                                                        i2 = R.id.spinEndTimeHr;
                                                                        View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.spinEndTimeHr);
                                                                        if (findChildViewById2 != null) {
                                                                            CommonSpinnerViewBinding bind2 = CommonSpinnerViewBinding.bind(findChildViewById2);
                                                                            i2 = R.id.spinEndTimeMin;
                                                                            View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.spinEndTimeMin);
                                                                            if (findChildViewById3 != null) {
                                                                                CommonSpinnerViewBinding bind3 = CommonSpinnerViewBinding.bind(findChildViewById3);
                                                                                i2 = R.id.spinStartTimeHr;
                                                                                View findChildViewById4 = ViewBindings.findChildViewById(view, R.id.spinStartTimeHr);
                                                                                if (findChildViewById4 != null) {
                                                                                    CommonSpinnerViewBinding bind4 = CommonSpinnerViewBinding.bind(findChildViewById4);
                                                                                    i2 = R.id.spinStartTimeMin;
                                                                                    View findChildViewById5 = ViewBindings.findChildViewById(view, R.id.spinStartTimeMin);
                                                                                    if (findChildViewById5 != null) {
                                                                                        CommonSpinnerViewBinding bind5 = CommonSpinnerViewBinding.bind(findChildViewById5);
                                                                                        i2 = R.id.startAMPM;
                                                                                        View findChildViewById6 = ViewBindings.findChildViewById(view, R.id.startAMPM);
                                                                                        if (findChildViewById6 != null) {
                                                                                            LayoutAmPmBinding bind6 = LayoutAmPmBinding.bind(findChildViewById6);
                                                                                            i2 = R.id.switchRepeatTime;
                                                                                            SwitchMaterial switchMaterial = (SwitchMaterial) ViewBindings.findChildViewById(view, R.id.switchRepeatTime);
                                                                                            if (switchMaterial != null) {
                                                                                                i2 = R.id.switchSetTime;
                                                                                                SwitchMaterial switchMaterial2 = (SwitchMaterial) ViewBindings.findChildViewById(view, R.id.switchSetTime);
                                                                                                if (switchMaterial2 != null) {
                                                                                                    i2 = R.id.tvEndTime;
                                                                                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvEndTime);
                                                                                                    if (appCompatTextView != null) {
                                                                                                        i2 = R.id.tvSelectDays;
                                                                                                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSelectDays);
                                                                                                        if (appCompatTextView2 != null) {
                                                                                                            i2 = R.id.tvSelectedDateRange;
                                                                                                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSelectedDateRange);
                                                                                                            if (appCompatTextView3 != null) {
                                                                                                                i2 = R.id.tvSetTimeDefaultMsg;
                                                                                                                AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSetTimeDefaultMsg);
                                                                                                                if (appCompatTextView4 != null) {
                                                                                                                    i2 = R.id.tvSetTimeDesc;
                                                                                                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSetTimeDesc);
                                                                                                                    if (appCompatTextView5 != null) {
                                                                                                                        i2 = R.id.tvSetTimeQst;
                                                                                                                        AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSetTimeQst);
                                                                                                                        if (appCompatTextView6 != null) {
                                                                                                                            i2 = R.id.tvSetTimeRptQst;
                                                                                                                            AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvSetTimeRptQst);
                                                                                                                            if (appCompatTextView7 != null) {
                                                                                                                                i2 = R.id.tvStartTime;
                                                                                                                                AppCompatTextView appCompatTextView8 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvStartTime);
                                                                                                                                if (appCompatTextView8 != null) {
                                                                                                                                    i2 = R.id.tvWantToSetTime;
                                                                                                                                    AppCompatTextView appCompatTextView9 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvWantToSetTime);
                                                                                                                                    if (appCompatTextView9 != null) {
                                                                                                                                        i2 = R.id.tvWantToSetTimeDesc;
                                                                                                                                        AppCompatTextView appCompatTextView10 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvWantToSetTimeDesc);
                                                                                                                                        if (appCompatTextView10 != null) {
                                                                                                                                            return new FragmentGeoFenceSetTimeBinding((LinearLayoutCompat) view, appCompatButton, constraintLayout, materialCardView, bind, appCompatImageView, constraintLayout2, materialRadioButton, materialRadioButton2, materialRadioButton3, materialRadioButton4, radioGroup, radioGroup2, relativeLayout, relativeLayout2, recyclerView, customSpinner, bind2, bind3, bind4, bind5, bind6, switchMaterial, switchMaterial2, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9, appCompatTextView10);
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
    public static FragmentGeoFenceSetTimeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentGeoFenceSetTimeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_geo_fence_set_time, viewGroup, false);
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
