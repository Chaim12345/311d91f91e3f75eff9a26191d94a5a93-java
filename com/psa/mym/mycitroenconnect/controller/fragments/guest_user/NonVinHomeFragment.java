package com.psa.mym.mycitroenconnect.controller.fragments.guest_user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.button.MaterialButton;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.AddCarActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.view_pager.ImageSliderAdapter;
import com.rd.PageIndicatorView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class NonVinHomeFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private View mView;
    @Nullable
    private String param1;
    @Nullable
    private String param2;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final NonVinHomeFragment newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            NonVinHomeFragment nonVinHomeFragment = new NonVinHomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1", param1);
            bundle.putString("param2", param2);
            nonVinHomeFragment.setArguments(bundle);
            return nonVinHomeFragment;
        }
    }

    @JvmStatic
    @NotNull
    public static final NonVinHomeFragment newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i2) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i2)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @Nullable
    public final View getMView() {
        return this.mView;
    }

    public final void initView() {
        _$_findCachedViewById(R.id.layoutAddCarBottom).setVisibility(8);
        ((ConstraintLayout) _$_findCachedViewById(R.id.layoutOffer)).setVisibility(0);
        int[] iArr = {uat.psa.mym.mycitroenconnect.R.drawable.ic_non_vin_image_1, uat.psa.mym.mycitroenconnect.R.drawable.ic_non_vin_image_1, uat.psa.mym.mycitroenconnect.R.drawable.ic_non_vin_image_1, uat.psa.mym.mycitroenconnect.R.drawable.ic_non_vin_image_1, uat.psa.mym.mycitroenconnect.R.drawable.ic_non_vin_image_1};
        int[] iArr2 = {uat.psa.mym.mycitroenconnect.R.drawable.ic_img_offer_1, uat.psa.mym.mycitroenconnect.R.drawable.ic_img_offer_2, uat.psa.mym.mycitroenconnect.R.drawable.ic_img_offer_3};
        View view = this.mView;
        ViewPager viewPager = view != null ? (ViewPager) view.findViewById(uat.psa.mym.mycitroenconnect.R.id.viewPagerNonVin1) : null;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(requireContext, iArr, null, false, 12, null);
        if (viewPager != null) {
            viewPager.setAdapter(imageSliderAdapter);
        }
        int i2 = R.id.pageIndicatorViewNonVin;
        PageIndicatorView pageIndicatorView = (PageIndicatorView) _$_findCachedViewById(i2);
        if (pageIndicatorView != null) {
            pageIndicatorView.setCount(imageSliderAdapter.getCount());
        }
        PageIndicatorView pageIndicatorView2 = (PageIndicatorView) _$_findCachedViewById(i2);
        if (pageIndicatorView2 != null) {
            pageIndicatorView2.setInteractiveAnimation(true);
        }
        ((ViewPager) _$_findCachedViewById(R.id.viewPagerNonVin1)).addOnPageChangeListener(this);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        ImageSliderAdapter imageSliderAdapter2 = new ImageSliderAdapter(requireContext2, iArr2, ImageView.ScaleType.FIT_XY, true);
        int i3 = R.id.viewPagerNonVinOffers;
        ViewPager viewPager2 = (ViewPager) _$_findCachedViewById(i3);
        if (viewPager2 != null) {
            viewPager2.setAdapter(imageSliderAdapter2);
        }
        int i4 = R.id.pageIndicatorViewNonVinOffers;
        PageIndicatorView pageIndicatorView3 = (PageIndicatorView) _$_findCachedViewById(i4);
        if (pageIndicatorView3 != null) {
            pageIndicatorView3.setCount(imageSliderAdapter2.getCount());
        }
        PageIndicatorView pageIndicatorView4 = (PageIndicatorView) _$_findCachedViewById(i4);
        if (pageIndicatorView4 != null) {
            pageIndicatorView4.setInteractiveAnimation(true);
        }
        ((ViewPager) _$_findCachedViewById(i3)).addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.guest_user.NonVinHomeFragment$initView$1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i5) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i5, float f2, int i6) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i5) {
                PageIndicatorView pageIndicatorView5 = (PageIndicatorView) NonVinHomeFragment.this._$_findCachedViewById(R.id.pageIndicatorViewNonVinOffers);
                if (pageIndicatorView5 == null) {
                    return;
                }
                pageIndicatorView5.setSelection(i5);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (MaterialButton) _$_findCachedViewById(R.id.btnAddCar))) {
            Intent intent = new Intent(requireActivity(), AddCarActivity.class);
            intent.putExtra("login", AppConstants.PAGE_MODE_ADD_CAR);
            startActivity(intent);
        } else if (Intrinsics.areEqual(view, (MaterialButton) _$_findCachedViewById(R.id.btnHomeAddCar))) {
            _$_findCachedViewById(R.id.layoutAddCarBottom).setVisibility(0);
            ((ConstraintLayout) _$_findCachedViewById(R.id.layoutOffer)).setVisibility(8);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.param1 = arguments.getString("param1");
            this.param2 = arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_non_vin_home, viewGroup, false);
        this.mView = inflate;
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        PageIndicatorView pageIndicatorView = (PageIndicatorView) _$_findCachedViewById(R.id.pageIndicatorViewNonVin);
        if (pageIndicatorView == null) {
            return;
        }
        pageIndicatorView.setSelection(i2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initView();
        setListeners();
    }

    public final void setListeners() {
        MaterialButton materialButton = (MaterialButton) _$_findCachedViewById(R.id.btnAddCar);
        if (materialButton != null) {
            materialButton.setOnClickListener(this);
        }
        MaterialButton materialButton2 = (MaterialButton) _$_findCachedViewById(R.id.btnHomeAddCar);
        if (materialButton2 != null) {
            materialButton2.setOnClickListener(this);
        }
    }

    public final void setMView(@Nullable View view) {
        this.mView = view;
    }
}
