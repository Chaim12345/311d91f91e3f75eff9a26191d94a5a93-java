package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentGeoFenceSummaryBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnActivate;
    @NonNull
    public final AppCompatButton btnActivateLater;
    @NonNull
    public final TextInputEditText edtFenceName;
    @NonNull
    public final RelativeLayout fenceMode;
    @NonNull
    public final AppCompatImageView ivFenceModeNav;
    @NonNull
    public final AppCompatImageView ivFenceNameClose;
    @NonNull
    public final AppCompatImageView ivFenceNameEdit;
    @NonNull
    public final AppCompatImageView ivFenceNameUpdateCheck;
    @NonNull
    public final AppCompatImageView ivFenceTypeNav;
    @NonNull
    public final AppCompatImageView ivRouteNav;
    @NonNull
    public final AppCompatImageView ivSetTimeNav;
    @NonNull
    public final ConstraintLayout layoutEditFence;
    @NonNull
    public final LinearLayoutCompat linD;
    @NonNull
    public final LinearLayoutCompat llSource;
    @NonNull
    public final RelativeLayout rlFenceRoute;
    @NonNull
    public final RelativeLayout rlFenceTime;
    @NonNull
    public final RelativeLayout rlFenceType;
    @NonNull
    private final ScrollView rootView;
    @NonNull
    public final MaterialTextView tvDate;
    @NonNull
    public final AppCompatTextView tvFenceMode;
    @NonNull
    public final AppCompatTextView tvFenceModeVal;
    @NonNull
    public final AppCompatTextView tvFenceName;
    @NonNull
    public final AppCompatTextView tvFenceRoute;
    @NonNull
    public final AppCompatTextView tvFenceSetTime;
    @NonNull
    public final AppCompatTextView tvFenceSummaryDest;
    @NonNull
    public final AppCompatTextView tvFenceSummarySource;
    @NonNull
    public final AppCompatTextView tvFenceType;
    @NonNull
    public final AppCompatTextView tvFenceTypeVal;
    @NonNull
    public final MaterialTextView tvTime;
    @NonNull
    public final View viewFullNameLine;
    @NonNull
    public final View viewSeperatorLine;

    private FragmentGeoFenceSummaryBinding(@NonNull ScrollView scrollView, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull TextInputEditText textInputEditText, @NonNull RelativeLayout relativeLayout, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull AppCompatImageView appCompatImageView4, @NonNull AppCompatImageView appCompatImageView5, @NonNull AppCompatImageView appCompatImageView6, @NonNull AppCompatImageView appCompatImageView7, @NonNull ConstraintLayout constraintLayout, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull RelativeLayout relativeLayout2, @NonNull RelativeLayout relativeLayout3, @NonNull RelativeLayout relativeLayout4, @NonNull MaterialTextView materialTextView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull AppCompatTextView appCompatTextView7, @NonNull AppCompatTextView appCompatTextView8, @NonNull AppCompatTextView appCompatTextView9, @NonNull MaterialTextView materialTextView2, @NonNull View view, @NonNull View view2) {
        this.rootView = scrollView;
        this.btnActivate = appCompatButton;
        this.btnActivateLater = appCompatButton2;
        this.edtFenceName = textInputEditText;
        this.fenceMode = relativeLayout;
        this.ivFenceModeNav = appCompatImageView;
        this.ivFenceNameClose = appCompatImageView2;
        this.ivFenceNameEdit = appCompatImageView3;
        this.ivFenceNameUpdateCheck = appCompatImageView4;
        this.ivFenceTypeNav = appCompatImageView5;
        this.ivRouteNav = appCompatImageView6;
        this.ivSetTimeNav = appCompatImageView7;
        this.layoutEditFence = constraintLayout;
        this.linD = linearLayoutCompat;
        this.llSource = linearLayoutCompat2;
        this.rlFenceRoute = relativeLayout2;
        this.rlFenceTime = relativeLayout3;
        this.rlFenceType = relativeLayout4;
        this.tvDate = materialTextView;
        this.tvFenceMode = appCompatTextView;
        this.tvFenceModeVal = appCompatTextView2;
        this.tvFenceName = appCompatTextView3;
        this.tvFenceRoute = appCompatTextView4;
        this.tvFenceSetTime = appCompatTextView5;
        this.tvFenceSummaryDest = appCompatTextView6;
        this.tvFenceSummarySource = appCompatTextView7;
        this.tvFenceType = appCompatTextView8;
        this.tvFenceTypeVal = appCompatTextView9;
        this.tvTime = materialTextView2;
        this.viewFullNameLine = view;
        this.viewSeperatorLine = view2;
    }

    @NonNull
    public static FragmentGeoFenceSummaryBinding bind(@NonNull View view) {
        int i2 = R.id.btnActivate;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnActivate);
        if (appCompatButton != null) {
            i2 = R.id.btnActivateLater;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnActivateLater);
            if (appCompatButton2 != null) {
                i2 = R.id.edtFenceName;
                TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtFenceName);
                if (textInputEditText != null) {
                    i2 = R.id.fenceMode;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.fenceMode);
                    if (relativeLayout != null) {
                        i2 = R.id.ivFenceModeNav;
                        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivFenceModeNav);
                        if (appCompatImageView != null) {
                            i2 = R.id.ivFenceNameClose;
                            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivFenceNameClose);
                            if (appCompatImageView2 != null) {
                                i2 = R.id.ivFenceNameEdit;
                                AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivFenceNameEdit);
                                if (appCompatImageView3 != null) {
                                    i2 = R.id.ivFenceNameUpdateCheck;
                                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivFenceNameUpdateCheck);
                                    if (appCompatImageView4 != null) {
                                        i2 = R.id.ivFenceTypeNav;
                                        AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivFenceTypeNav);
                                        if (appCompatImageView5 != null) {
                                            i2 = R.id.ivRouteNav;
                                            AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivRouteNav);
                                            if (appCompatImageView6 != null) {
                                                i2 = R.id.ivSetTimeNav;
                                                AppCompatImageView appCompatImageView7 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivSetTimeNav);
                                                if (appCompatImageView7 != null) {
                                                    i2 = R.id.layoutEditFence;
                                                    ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutEditFence);
                                                    if (constraintLayout != null) {
                                                        i2 = R.id.linD;
                                                        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.linD);
                                                        if (linearLayoutCompat != null) {
                                                            i2 = R.id.llSource;
                                                            LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llSource);
                                                            if (linearLayoutCompat2 != null) {
                                                                i2 = R.id.rlFenceRoute;
                                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rlFenceRoute);
                                                                if (relativeLayout2 != null) {
                                                                    i2 = R.id.rlFenceTime;
                                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rlFenceTime);
                                                                    if (relativeLayout3 != null) {
                                                                        i2 = R.id.rlFenceType;
                                                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rlFenceType);
                                                                        if (relativeLayout4 != null) {
                                                                            i2 = R.id.tvDate;
                                                                            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvDate);
                                                                            if (materialTextView != null) {
                                                                                i2 = R.id.tvFenceMode;
                                                                                AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceMode);
                                                                                if (appCompatTextView != null) {
                                                                                    i2 = R.id.tvFenceModeVal;
                                                                                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceModeVal);
                                                                                    if (appCompatTextView2 != null) {
                                                                                        i2 = R.id.tvFenceName;
                                                                                        AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceName);
                                                                                        if (appCompatTextView3 != null) {
                                                                                            i2 = R.id.tvFenceRoute;
                                                                                            AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceRoute);
                                                                                            if (appCompatTextView4 != null) {
                                                                                                i2 = R.id.tvFenceSetTime;
                                                                                                AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceSetTime);
                                                                                                if (appCompatTextView5 != null) {
                                                                                                    i2 = R.id.tvFenceSummaryDest;
                                                                                                    AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceSummaryDest);
                                                                                                    if (appCompatTextView6 != null) {
                                                                                                        i2 = R.id.tvFenceSummarySource;
                                                                                                        AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceSummarySource);
                                                                                                        if (appCompatTextView7 != null) {
                                                                                                            i2 = R.id.tvFenceType;
                                                                                                            AppCompatTextView appCompatTextView8 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceType);
                                                                                                            if (appCompatTextView8 != null) {
                                                                                                                i2 = R.id.tvFenceTypeVal;
                                                                                                                AppCompatTextView appCompatTextView9 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvFenceTypeVal);
                                                                                                                if (appCompatTextView9 != null) {
                                                                                                                    i2 = R.id.tvTime;
                                                                                                                    MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvTime);
                                                                                                                    if (materialTextView2 != null) {
                                                                                                                        i2 = R.id.viewFullNameLine;
                                                                                                                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.viewFullNameLine);
                                                                                                                        if (findChildViewById != null) {
                                                                                                                            i2 = R.id.viewSeperatorLine;
                                                                                                                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.viewSeperatorLine);
                                                                                                                            if (findChildViewById2 != null) {
                                                                                                                                return new FragmentGeoFenceSummaryBinding((ScrollView) view, appCompatButton, appCompatButton2, textInputEditText, relativeLayout, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatImageView7, constraintLayout, linearLayoutCompat, linearLayoutCompat2, relativeLayout2, relativeLayout3, relativeLayout4, materialTextView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8, appCompatTextView9, materialTextView2, findChildViewById, findChildViewById2);
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
    public static FragmentGeoFenceSummaryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentGeoFenceSummaryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_geo_fence_summary, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ScrollView getRoot() {
        return this.rootView;
    }
}
