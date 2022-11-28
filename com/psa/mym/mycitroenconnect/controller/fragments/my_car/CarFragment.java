package com.psa.mym.mycitroenconnect.controller.fragments.my_car;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.view_pager.CommonChildViewPagerAdapter;
import com.psa.mym.mycitroenconnect.controller.fragments.my_car.CarFragment;
import com.psa.mym.mycitroenconnect.model.dashboard.StoredDashboardData;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.CharsKt__CharKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class CarFragment extends Fragment implements View.OnClickListener, TabLayout.OnTabSelectedListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private long lastClickTime;
    @Nullable
    private MainActivity parentActivity;
    @Nullable
    private View rootView;
    private Skeleton skeleton;
    private TabLayout tabLayout;
    @Nullable
    private CommonChildViewPagerAdapter viewPagerAdapter;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String vehicleType = "";

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final CarFragment newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            CarFragment carFragment = new CarFragment();
            carFragment.setArguments(new Bundle());
            return carFragment;
        }
    }

    private final void displayLoading() {
        ConstraintLayout clParent = (ConstraintLayout) _$_findCachedViewById(R.id.clParent);
        Intrinsics.checkNotNullExpressionValue(clParent, "clParent");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(clParent, ExtensionsKt.skeletonConfig(requireContext));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    private final void getCarDetails() {
        displayLoading();
        if (this.tabLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
        }
        ViewPager2 pager = (ViewPager2) _$_findCachedViewById(R.id.pager);
        Intrinsics.checkNotNullExpressionValue(pager, "pager");
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        Fragment findCurrentFragment = ExtensionsKt.findCurrentFragment(pager, childFragmentManager);
        if (findCurrentFragment != null && Intrinsics.areEqual(ExtensionsKt.getName(findCurrentFragment), ExtensionsKt.getName(new MyCarHealthFragment()))) {
            ((MyCarHealthFragment) findCurrentFragment).updateHealthDetails();
            return;
        }
        Objects.requireNonNull(findCurrentFragment, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.fragments.my_car.MyCarStatusFragment");
        ((MyCarStatusFragment) findCurrentFragment).getDashboardInfo();
    }

    private final void initTabLayout() {
        final ArrayList arrayListOf;
        ArrayList arrayListOf2;
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(getString(uat.psa.mym.mycitroenconnect.R.string.status), getString(uat.psa.mym.mycitroenconnect.R.string.health));
        arrayListOf2 = CollectionsKt__CollectionsKt.arrayListOf(new MyCarStatusFragment(), new MyCarHealthFragment());
        View view = this.rootView;
        TabLayout tabLayout = null;
        TabLayout tabLayout2 = view != null ? (TabLayout) view.findViewById(uat.psa.mym.mycitroenconnect.R.id.tabLayout) : null;
        Intrinsics.checkNotNull(tabLayout2);
        this.tabLayout = tabLayout2;
        View view2 = this.rootView;
        ViewPager2 viewPager2 = view2 != null ? (ViewPager2) view2.findViewById(uat.psa.mym.mycitroenconnect.R.id.pager) : null;
        Intrinsics.checkNotNull(viewPager2);
        viewPager2.setUserInputEnabled(false);
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        Lifecycle lifecycle = getViewLifecycleOwner().getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "viewLifecycleOwner.lifecycle");
        CommonChildViewPagerAdapter commonChildViewPagerAdapter = new CommonChildViewPagerAdapter(childFragmentManager, lifecycle, arrayListOf2);
        this.viewPagerAdapter = commonChildViewPagerAdapter;
        viewPager2.setAdapter(commonChildViewPagerAdapter);
        TabLayout tabLayout3 = this.tabLayout;
        if (tabLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
        } else {
            tabLayout = tabLayout3;
        }
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() { // from class: n.a
            @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
            public final void onConfigureTab(TabLayout.Tab tab, int i2) {
                CarFragment.m150initTabLayout$lambda3(arrayListOf, tab, i2);
            }
        }).attach();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initTabLayout$lambda-3  reason: not valid java name */
    public static final void m150initTabLayout$lambda3(ArrayList tabTitle, TabLayout.Tab tab, int i2) {
        Intrinsics.checkNotNullParameter(tabTitle, "$tabTitle");
        Intrinsics.checkNotNullParameter(tab, "tab");
        Object obj = tabTitle.get(i2);
        Intrinsics.checkNotNullExpressionValue(obj, "tabTitle[position]");
        String str = (String) obj;
        if (str.length() > 0) {
            StringBuilder sb = new StringBuilder();
            char charAt = str.charAt(0);
            sb.append((Object) (Character.isLowerCase(charAt) ? CharsKt__CharKt.titlecase(charAt) : String.valueOf(charAt)));
            String substring = str.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            sb.append(substring);
            str = sb.toString();
        }
        tab.setText(str);
    }

    private final void initViews() {
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.vehicleType = companion.getVehicleType(requireContext);
        updateChargingStatus("");
        initTabLayout();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        StoredDashboardData saveDashboardData = companion.getSaveDashboardData(requireContext2);
        if (saveDashboardData != null) {
            setUpdateTime(saveDashboardData.getUpdatedTimeStamp());
        }
    }

    @JvmStatic
    @NotNull
    public static final CarFragment newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
    }

    private final void setListener() {
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvViewOnMap)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvFastCharging)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvUpdated)).setOnClickListener(this);
        TabLayout tabLayout = this.tabLayout;
        if (tabLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
            tabLayout = null;
        }
        tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
    }

    private final void showUpdatedTime() {
        AppCompatTextView tvUpdated = (AppCompatTextView) _$_findCachedViewById(R.id.tvUpdated);
        Intrinsics.checkNotNullExpressionValue(tvUpdated, "tvUpdated");
        ExtensionsKt.show(tvUpdated);
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

    public final void displayData() {
        Skeleton skeleton = this.skeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
            skeleton = null;
        }
        ExtensionsKt.showData$default(skeleton, 0L, 1, null);
    }

    public final void hideUpdatedTime() {
        AppCompatTextView tvUpdated = (AppCompatTextView) _$_findCachedViewById(R.id.tvUpdated);
        Intrinsics.checkNotNullExpressionValue(tvUpdated, "tvUpdated");
        ExtensionsKt.hide(tvUpdated);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        if (Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvViewOnMap))) {
            MainActivity mainActivity = this.parentActivity;
            if (mainActivity != null) {
                mainActivity.navigateToLocateFragment();
            }
        } else if (Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvFastCharging)) || !Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvUpdated))) {
        } else {
            getCarDetails();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_car, viewGroup, false);
        this.rootView = inflate;
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabReselected(@Nullable TabLayout.Tab tab) {
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabSelected(@Nullable TabLayout.Tab tab) {
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabUnselected(@Nullable TabLayout.Tab tab) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        MainActivity mainActivity;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            mainActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.MainActivity");
            mainActivity = (MainActivity) activity;
        }
        this.parentActivity = mainActivity;
        displayLoading();
        initViews();
        setListener();
    }

    public final void setUpdateTime(@NotNull String updateTime) {
        Intrinsics.checkNotNullParameter(updateTime, "updateTime");
        displayData();
        try {
            AppUtil.Companion companion = AppUtil.Companion;
            Date parse = new SimpleDateFormat(AppConstants.UTC_DATE_FORMAT, companion.getDefaultLocale()).parse(updateTime);
            showUpdatedTime();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.updated_time);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.updated_time)");
            String format = String.format(string, Arrays.copyOf(new Object[]{companion.getTimeAgo(parse.getTime())}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvUpdated)).setText(format);
        } catch (Exception e2) {
            Logger.INSTANCE.e(e2.toString());
        }
    }

    public final void updateChargingStatus(@Nullable String str) {
        String str2;
        int i2;
        AppCompatTextView appCompatTextView;
        int i3;
        AppCompatTextView appCompatTextView2;
        int i4;
        if (Intrinsics.areEqual(this.vehicleType, AppConstants.ICE)) {
            int i5 = R.id.tvFastCharging;
            ((AppCompatTextView) _$_findCachedViewById(i5)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.fueling));
            appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(i5);
            i4 = uat.psa.mym.mycitroenconnect.R.drawable.ic_fuel_drop_10dp;
        } else {
            if (str != null) {
                str2 = str.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            } else {
                str2 = null;
            }
            if (Intrinsics.areEqual(str2, "slow_charging")) {
                i2 = R.id.tvFastCharging;
                appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i2);
                i3 = uat.psa.mym.mycitroenconnect.R.string.slow_charging_text;
            } else if (Intrinsics.areEqual(str2, "quick_charging")) {
                i2 = R.id.tvFastCharging;
                appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i2);
                i3 = uat.psa.mym.mycitroenconnect.R.string.fast_charging_text;
            } else {
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvFastCharging)).setVisibility(8);
                appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvFastCharging);
                i4 = uat.psa.mym.mycitroenconnect.R.drawable.ic_fast_charging;
            }
            appCompatTextView.setText(getString(i3));
            ((AppCompatTextView) _$_findCachedViewById(i2)).setVisibility(0);
            appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvFastCharging);
            i4 = uat.psa.mym.mycitroenconnect.R.drawable.ic_fast_charging;
        }
        appCompatTextView2.setCompoundDrawablesWithIntrinsicBounds(i4, 0, 0, 0);
    }
}
