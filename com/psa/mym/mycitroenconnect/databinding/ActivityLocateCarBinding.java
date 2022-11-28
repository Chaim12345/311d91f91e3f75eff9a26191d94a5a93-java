package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ActivityLocateCarBinding implements ViewBinding {
    @NonNull
    public final ConstraintLayout animationView;
    @NonNull
    public final MaterialButton btnNavigateToCar;
    @NonNull
    public final MaterialButton btnShare;
    @NonNull
    public final MaterialCardView cvRouteDetails;
    @NonNull
    public final View divider;
    @NonNull
    public final View dottedLine;
    @NonNull
    public final AppCompatImageView ivCarHome;
    @NonNull
    public final AppCompatImageView ivDestination;
    @NonNull
    public final AppCompatImageView ivSource;
    @NonNull
    public final LayoutDashboardModeHeaderBinding layoutLocateCar;
    @NonNull
    public final LinearLayoutCompat llBtns;
    @NonNull
    public final LinearLayoutCompat llMainView;
    @NonNull
    public final FragmentContainerView mapContainer;
    @NonNull
    private final ConstraintLayout rootView;
    @NonNull
    public final MaterialTextView tvDestination;
    @NonNull
    public final MaterialTextView tvDistance;
    @NonNull
    public final AppCompatTextView tvIgnitionStatus;
    @NonNull
    public final AppCompatTextView tvLastUpdated;
    @NonNull
    public final MaterialTextView tvSource;
    @NonNull
    public final MaterialTextView tvTime;
    @NonNull
    public final AppCompatImageView zoomMyCar;

    private ActivityLocateCarBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout2, @NonNull MaterialButton materialButton, @NonNull MaterialButton materialButton2, @NonNull MaterialCardView materialCardView, @NonNull View view, @NonNull View view2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LinearLayoutCompat linearLayoutCompat2, @NonNull FragmentContainerView fragmentContainerView, @NonNull MaterialTextView materialTextView, @NonNull MaterialTextView materialTextView2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull MaterialTextView materialTextView3, @NonNull MaterialTextView materialTextView4, @NonNull AppCompatImageView appCompatImageView4) {
        this.rootView = constraintLayout;
        this.animationView = constraintLayout2;
        this.btnNavigateToCar = materialButton;
        this.btnShare = materialButton2;
        this.cvRouteDetails = materialCardView;
        this.divider = view;
        this.dottedLine = view2;
        this.ivCarHome = appCompatImageView;
        this.ivDestination = appCompatImageView2;
        this.ivSource = appCompatImageView3;
        this.layoutLocateCar = layoutDashboardModeHeaderBinding;
        this.llBtns = linearLayoutCompat;
        this.llMainView = linearLayoutCompat2;
        this.mapContainer = fragmentContainerView;
        this.tvDestination = materialTextView;
        this.tvDistance = materialTextView2;
        this.tvIgnitionStatus = appCompatTextView;
        this.tvLastUpdated = appCompatTextView2;
        this.tvSource = materialTextView3;
        this.tvTime = materialTextView4;
        this.zoomMyCar = appCompatImageView4;
    }

    @NonNull
    public static ActivityLocateCarBinding bind(@NonNull View view) {
        int i2 = R.id.animationView;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.animationView);
        if (constraintLayout != null) {
            i2 = R.id.btnNavigateToCar;
            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, R.id.btnNavigateToCar);
            if (materialButton != null) {
                i2 = R.id.btnShare;
                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, R.id.btnShare);
                if (materialButton2 != null) {
                    i2 = R.id.cvRouteDetails;
                    MaterialCardView materialCardView = (MaterialCardView) ViewBindings.findChildViewById(view, R.id.cvRouteDetails);
                    if (materialCardView != null) {
                        i2 = R.id.divider;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                        if (findChildViewById != null) {
                            i2 = R.id.dottedLine;
                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.dottedLine);
                            if (findChildViewById2 != null) {
                                i2 = R.id.ivCarHome;
                                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivCarHome);
                                if (appCompatImageView != null) {
                                    i2 = R.id.ivDestination;
                                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivDestination);
                                    if (appCompatImageView2 != null) {
                                        i2 = R.id.ivSource;
                                        AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivSource);
                                        if (appCompatImageView3 != null) {
                                            i2 = R.id.layoutLocateCar;
                                            View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.layoutLocateCar);
                                            if (findChildViewById3 != null) {
                                                LayoutDashboardModeHeaderBinding bind = LayoutDashboardModeHeaderBinding.bind(findChildViewById3);
                                                i2 = R.id.llBtns;
                                                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llBtns);
                                                if (linearLayoutCompat != null) {
                                                    i2 = R.id.llMainView;
                                                    LinearLayoutCompat linearLayoutCompat2 = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.llMainView);
                                                    if (linearLayoutCompat2 != null) {
                                                        i2 = R.id.mapContainer;
                                                        FragmentContainerView fragmentContainerView = (FragmentContainerView) ViewBindings.findChildViewById(view, R.id.mapContainer);
                                                        if (fragmentContainerView != null) {
                                                            i2 = R.id.tvDestination;
                                                            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvDestination);
                                                            if (materialTextView != null) {
                                                                i2 = R.id.tvDistance;
                                                                MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvDistance);
                                                                if (materialTextView2 != null) {
                                                                    i2 = R.id.tvIgnitionStatus;
                                                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvIgnitionStatus);
                                                                    if (appCompatTextView != null) {
                                                                        i2 = R.id.tvLastUpdated;
                                                                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvLastUpdated);
                                                                        if (appCompatTextView2 != null) {
                                                                            i2 = R.id.tvSource;
                                                                            MaterialTextView materialTextView3 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvSource);
                                                                            if (materialTextView3 != null) {
                                                                                i2 = R.id.tvTime;
                                                                                MaterialTextView materialTextView4 = (MaterialTextView) ViewBindings.findChildViewById(view, R.id.tvTime);
                                                                                if (materialTextView4 != null) {
                                                                                    i2 = R.id.zoomMyCar;
                                                                                    AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.zoomMyCar);
                                                                                    if (appCompatImageView4 != null) {
                                                                                        return new ActivityLocateCarBinding((ConstraintLayout) view, constraintLayout, materialButton, materialButton2, materialCardView, findChildViewById, findChildViewById2, appCompatImageView, appCompatImageView2, appCompatImageView3, bind, linearLayoutCompat, linearLayoutCompat2, fragmentContainerView, materialTextView, materialTextView2, appCompatTextView, appCompatTextView2, materialTextView3, materialTextView4, appCompatImageView4);
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
    public static ActivityLocateCarBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ActivityLocateCarBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_locate_car, viewGroup, false);
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
