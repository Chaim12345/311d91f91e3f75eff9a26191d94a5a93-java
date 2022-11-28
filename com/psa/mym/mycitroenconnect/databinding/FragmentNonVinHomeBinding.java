package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.button.MaterialButton;
import com.rd.PageIndicatorView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentNonVinHomeBinding implements ViewBinding {
    @NonNull
    public final MaterialButton btnHomeAddCar;
    @NonNull
    public final Guideline horiGuideLine40;
    @NonNull
    public final AppCompatImageView ivVideo;
    @NonNull
    public final LayoutAddCarBottomBinding layoutAddCarBottom;
    @NonNull
    public final ConstraintLayout layoutOffer;
    @NonNull
    public final PageIndicatorView pageIndicatorViewNonVin;
    @NonNull
    public final PageIndicatorView pageIndicatorViewNonVinOffers;
    @NonNull
    private final FrameLayout rootView;
    @NonNull
    public final TextView tvAddCarTitle;
    @NonNull
    public final TextView tvAddVehicleSubTitle;
    @NonNull
    public final TextView tvOfferSubTitle;
    @NonNull
    public final TextView tvOffersTitle;
    @NonNull
    public final TextView tvtitleCitro;
    @NonNull
    public final ViewPager viewPagerNonVin1;
    @NonNull
    public final ViewPager viewPagerNonVinOffers;

    private FragmentNonVinHomeBinding(@NonNull FrameLayout frameLayout, @NonNull MaterialButton materialButton, @NonNull Guideline guideline, @NonNull AppCompatImageView appCompatImageView, @NonNull LayoutAddCarBottomBinding layoutAddCarBottomBinding, @NonNull ConstraintLayout constraintLayout, @NonNull PageIndicatorView pageIndicatorView, @NonNull PageIndicatorView pageIndicatorView2, @NonNull TextView textView, @NonNull TextView textView2, @NonNull TextView textView3, @NonNull TextView textView4, @NonNull TextView textView5, @NonNull ViewPager viewPager, @NonNull ViewPager viewPager2) {
        this.rootView = frameLayout;
        this.btnHomeAddCar = materialButton;
        this.horiGuideLine40 = guideline;
        this.ivVideo = appCompatImageView;
        this.layoutAddCarBottom = layoutAddCarBottomBinding;
        this.layoutOffer = constraintLayout;
        this.pageIndicatorViewNonVin = pageIndicatorView;
        this.pageIndicatorViewNonVinOffers = pageIndicatorView2;
        this.tvAddCarTitle = textView;
        this.tvAddVehicleSubTitle = textView2;
        this.tvOfferSubTitle = textView3;
        this.tvOffersTitle = textView4;
        this.tvtitleCitro = textView5;
        this.viewPagerNonVin1 = viewPager;
        this.viewPagerNonVinOffers = viewPager2;
    }

    @NonNull
    public static FragmentNonVinHomeBinding bind(@NonNull View view) {
        int i2 = R.id.btnHomeAddCar;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, R.id.btnHomeAddCar);
        if (materialButton != null) {
            i2 = R.id.horiGuideLine40;
            Guideline guideline = (Guideline) ViewBindings.findChildViewById(view, R.id.horiGuideLine40);
            if (guideline != null) {
                i2 = R.id.ivVideo;
                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivVideo);
                if (appCompatImageView != null) {
                    i2 = R.id.layoutAddCarBottom;
                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutAddCarBottom);
                    if (findChildViewById != null) {
                        LayoutAddCarBottomBinding bind = LayoutAddCarBottomBinding.bind(findChildViewById);
                        i2 = R.id.layoutOffer;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutOffer);
                        if (constraintLayout != null) {
                            i2 = R.id.pageIndicatorViewNonVin;
                            PageIndicatorView pageIndicatorView = (PageIndicatorView) ViewBindings.findChildViewById(view, R.id.pageIndicatorViewNonVin);
                            if (pageIndicatorView != null) {
                                i2 = R.id.pageIndicatorViewNonVinOffers;
                                PageIndicatorView pageIndicatorView2 = (PageIndicatorView) ViewBindings.findChildViewById(view, R.id.pageIndicatorViewNonVinOffers);
                                if (pageIndicatorView2 != null) {
                                    i2 = R.id.tvAddCarTitle;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvAddCarTitle);
                                    if (textView != null) {
                                        i2 = R.id.tvAddVehicleSubTitle;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tvAddVehicleSubTitle);
                                        if (textView2 != null) {
                                            i2 = R.id.tvOfferSubTitle;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tvOfferSubTitle);
                                            if (textView3 != null) {
                                                i2 = R.id.tvOffersTitle;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tvOffersTitle);
                                                if (textView4 != null) {
                                                    i2 = R.id.tvtitleCitro;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tvtitleCitro);
                                                    if (textView5 != null) {
                                                        i2 = R.id.viewPagerNonVin1;
                                                        ViewPager viewPager = (ViewPager) ViewBindings.findChildViewById(view, R.id.viewPagerNonVin1);
                                                        if (viewPager != null) {
                                                            i2 = R.id.viewPagerNonVinOffers;
                                                            ViewPager viewPager2 = (ViewPager) ViewBindings.findChildViewById(view, R.id.viewPagerNonVinOffers);
                                                            if (viewPager2 != null) {
                                                                return new FragmentNonVinHomeBinding((FrameLayout) view, materialButton, guideline, appCompatImageView, bind, constraintLayout, pageIndicatorView, pageIndicatorView2, textView, textView2, textView3, textView4, textView5, viewPager, viewPager2);
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
    public static FragmentNonVinHomeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentNonVinHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_non_vin_home, viewGroup, false);
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
