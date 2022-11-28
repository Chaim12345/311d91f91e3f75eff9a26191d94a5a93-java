package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.rd.PageIndicatorView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentDrivingBehaviourBinding implements ViewBinding {
    @NonNull
    public final ConstraintLayout conLayout;
    @NonNull
    public final CardView layoutHarshAcc;
    @NonNull
    public final CardView layoutHarshBraking;
    @NonNull
    public final CardView layoutOverSpeed;
    @NonNull
    public final LinearLayout linDriving;
    @NonNull
    public final LinearLayout llDrivingBehaviour;
    @NonNull
    public final PageIndicatorView pageIndicatorViewD;
    @NonNull
    private final ScrollView rootView;
    @NonNull
    public final ScrollView scrollViewDrivingBehavior;
    @NonNull
    public final RecyclerView tipsRV;
    @NonNull
    public final AppCompatTextView tvDbCardHarshAcc;
    @NonNull
    public final AppCompatTextView tvDbCardHarshBraking;
    @NonNull
    public final AppCompatImageView tvDbCardIcon;
    @NonNull
    public final AppCompatImageView tvDbCardIconHarshAcc;
    @NonNull
    public final AppCompatImageView tvDbCardIconHarshBraking;
    @NonNull
    public final AppCompatTextView tvDbCardLabel;
    @NonNull
    public final AppCompatTextView tvDbCardValue;
    @NonNull
    public final AppCompatTextView tvDbCardValueHarshAcc;
    @NonNull
    public final AppCompatTextView tvDbCardValueHarshBraking;
    @NonNull
    public final TextView tvNoData;
    @NonNull
    public final AppCompatImageView tvTdDbCardBg;
    @NonNull
    public final AppCompatImageView tvTdDbCardIconHarshAccBg;
    @NonNull
    public final AppCompatImageView tvTdDbCardIconHarshBrakingBg;
    @NonNull
    public final AppCompatTextView tvTips;

    private FragmentDrivingBehaviourBinding(@NonNull ScrollView scrollView, @NonNull ConstraintLayout constraintLayout, @NonNull CardView cardView, @NonNull CardView cardView2, @NonNull CardView cardView3, @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2, @NonNull PageIndicatorView pageIndicatorView, @NonNull ScrollView scrollView2, @NonNull RecyclerView recyclerView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull TextView textView, @NonNull AppCompatImageView appCompatImageView4, @NonNull AppCompatImageView appCompatImageView5, @NonNull AppCompatImageView appCompatImageView6, @NonNull AppCompatTextView appCompatTextView7) {
        this.rootView = scrollView;
        this.conLayout = constraintLayout;
        this.layoutHarshAcc = cardView;
        this.layoutHarshBraking = cardView2;
        this.layoutOverSpeed = cardView3;
        this.linDriving = linearLayout;
        this.llDrivingBehaviour = linearLayout2;
        this.pageIndicatorViewD = pageIndicatorView;
        this.scrollViewDrivingBehavior = scrollView2;
        this.tipsRV = recyclerView;
        this.tvDbCardHarshAcc = appCompatTextView;
        this.tvDbCardHarshBraking = appCompatTextView2;
        this.tvDbCardIcon = appCompatImageView;
        this.tvDbCardIconHarshAcc = appCompatImageView2;
        this.tvDbCardIconHarshBraking = appCompatImageView3;
        this.tvDbCardLabel = appCompatTextView3;
        this.tvDbCardValue = appCompatTextView4;
        this.tvDbCardValueHarshAcc = appCompatTextView5;
        this.tvDbCardValueHarshBraking = appCompatTextView6;
        this.tvNoData = textView;
        this.tvTdDbCardBg = appCompatImageView4;
        this.tvTdDbCardIconHarshAccBg = appCompatImageView5;
        this.tvTdDbCardIconHarshBrakingBg = appCompatImageView6;
        this.tvTips = appCompatTextView7;
    }

    @NonNull
    public static FragmentDrivingBehaviourBinding bind(@NonNull View view) {
        int i2 = R.id.conLayout;
        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.conLayout);
        if (constraintLayout != null) {
            i2 = R.id.layout_Harsh_Acc;
            CardView cardView = (CardView) ViewBindings.findChildViewById(view, R.id.layout_Harsh_Acc);
            if (cardView != null) {
                i2 = R.id.layout_Harsh_braking;
                CardView cardView2 = (CardView) ViewBindings.findChildViewById(view, R.id.layout_Harsh_braking);
                if (cardView2 != null) {
                    i2 = R.id.layout_over_speed;
                    CardView cardView3 = (CardView) ViewBindings.findChildViewById(view, R.id.layout_over_speed);
                    if (cardView3 != null) {
                        i2 = R.id.lin_Driving;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.lin_Driving);
                        if (linearLayout != null) {
                            i2 = R.id.llDrivingBehaviour;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.llDrivingBehaviour);
                            if (linearLayout2 != null) {
                                i2 = R.id.pageIndicatorViewD;
                                PageIndicatorView pageIndicatorView = (PageIndicatorView) ViewBindings.findChildViewById(view, R.id.pageIndicatorViewD);
                                if (pageIndicatorView != null) {
                                    ScrollView scrollView = (ScrollView) view;
                                    i2 = R.id.tipsRV;
                                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.tipsRV);
                                    if (recyclerView != null) {
                                        i2 = R.id.tvDbCardHarshAcc;
                                        AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDbCardHarshAcc);
                                        if (appCompatTextView != null) {
                                            i2 = R.id.tvDbCardHarshBraking;
                                            AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDbCardHarshBraking);
                                            if (appCompatTextView2 != null) {
                                                i2 = R.id.tvDbCardIcon;
                                                AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvDbCardIcon);
                                                if (appCompatImageView != null) {
                                                    i2 = R.id.tvDbCardIconHarshAcc;
                                                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvDbCardIconHarshAcc);
                                                    if (appCompatImageView2 != null) {
                                                        i2 = R.id.tvDbCardIconHarshBraking;
                                                        AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvDbCardIconHarshBraking);
                                                        if (appCompatImageView3 != null) {
                                                            i2 = R.id.tvDbCardLabel;
                                                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDbCardLabel);
                                                            if (appCompatTextView3 != null) {
                                                                i2 = R.id.tvDbCardValue;
                                                                AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDbCardValue);
                                                                if (appCompatTextView4 != null) {
                                                                    i2 = R.id.tvDbCardValueHarshAcc;
                                                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDbCardValueHarshAcc);
                                                                    if (appCompatTextView5 != null) {
                                                                        i2 = R.id.tvDbCardValueHarshBraking;
                                                                        AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDbCardValueHarshBraking);
                                                                        if (appCompatTextView6 != null) {
                                                                            i2 = R.id.tvNoData;
                                                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tvNoData);
                                                                            if (textView != null) {
                                                                                i2 = R.id.tvTdDbCardBg;
                                                                                AppCompatImageView appCompatImageView4 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardBg);
                                                                                if (appCompatImageView4 != null) {
                                                                                    i2 = R.id.tvTdDbCardIconHarshAccBg;
                                                                                    AppCompatImageView appCompatImageView5 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardIconHarshAccBg);
                                                                                    if (appCompatImageView5 != null) {
                                                                                        i2 = R.id.tvTdDbCardIconHarshBrakingBg;
                                                                                        AppCompatImageView appCompatImageView6 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.tvTdDbCardIconHarshBrakingBg);
                                                                                        if (appCompatImageView6 != null) {
                                                                                            i2 = R.id.tvTips;
                                                                                            AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvTips);
                                                                                            if (appCompatTextView7 != null) {
                                                                                                return new FragmentDrivingBehaviourBinding(scrollView, constraintLayout, cardView, cardView2, cardView3, linearLayout, linearLayout2, pageIndicatorView, scrollView, recyclerView, appCompatTextView, appCompatTextView2, appCompatImageView, appCompatImageView2, appCompatImageView3, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, textView, appCompatImageView4, appCompatImageView5, appCompatImageView6, appCompatTextView7);
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
    public static FragmentDrivingBehaviourBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentDrivingBehaviourBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_driving_behaviour, viewGroup, false);
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
