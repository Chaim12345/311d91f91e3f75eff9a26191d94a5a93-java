package com.psa.mym.mycitroenconnect.controller.activities.dashboard;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelLazy;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.gms.maps.model.LatLng;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.view_pager.CommonViewPagerAdapter;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.ChooseGeoFenceTypeFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceMapFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceMapSettingsFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceSetTimeFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceSummaryFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.SetFenceForFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.SetLocationFragment;
import com.psa.mym.mycitroenconnect.controller.viewmodel.GeoFenceViewModel;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.GeoFenceCommonModel;
import com.psa.mym.mycitroenconnect.model.dashboard.VehicleLocationResponse;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import com.psa.mym.mycitroenconnect.services.DashboardService;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class GeoFenceActivity extends BusBaseActivity {
    @Nullable
    private LatLng carLatLng;
    @Nullable
    private CommonViewPagerAdapter geoFenceAdapter;
    private long lastClickTime;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int pageMode = 5;
    @NotNull
    private final Lazy viewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(GeoFenceViewModel.class), new GeoFenceActivity$special$$inlined$viewModels$default$2(this), new GeoFenceActivity$special$$inlined$viewModels$default$1(this));

    private final void getIntentData() {
        if (getViewModel().getGeoFenceCommonModel() == null) {
            getViewModel().setGeoFenceCommonModel(new GeoFenceCommonModel(null, null, null, null, null, 0, 63, null));
        }
        if (getIntent() != null) {
            getVehicleLocation();
            if (getIntent().hasExtra(AppConstants.ARG_PAGE_MODE)) {
                this.pageMode = getIntent().getIntExtra(AppConstants.ARG_PAGE_MODE, 5);
                GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel != null) {
                    geoFenceCommonModel.setFenceCreationMode(this.pageMode);
                }
            }
            if (getIntent().hasExtra(AppConstants.GEO_FENCE)) {
                GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel2 != null) {
                    geoFenceCommonModel2.setGeoFenceGetResponse((GetGeoFenceResponseItem) getIntent().getParcelableExtra(AppConstants.GEO_FENCE));
                }
                GeoFenceCommonModel geoFenceCommonModel3 = getViewModel().getGeoFenceCommonModel();
                if (geoFenceCommonModel3 == null) {
                    return;
                }
                geoFenceCommonModel3.setFenceCreationMode(6);
            }
        }
    }

    private final void getVehicleLocation() {
        new DashboardService().callGetVehicleCurrentLocation(this, SharedPref.Companion.getVinNumber(this));
    }

    private final GeoFenceViewModel getViewModel() {
        return (GeoFenceViewModel) this.viewModel$delegate.getValue();
    }

    private final void init() {
        ArrayList arrayListOf;
        String string = getString(R.string.label_geo_fence);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.label_geo_fence)");
        setTitle(string);
        int i2 = com.psa.mym.mycitroenconnect.R.id.layoutGeoHeader;
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i2).findViewById(com.psa.mym.mycitroenconnect.R.id.tvSkip);
        Intrinsics.checkNotNullExpressionValue(appCompatTextView, "layoutGeoHeader.tvSkip");
        ExtensionsKt.hide(appCompatTextView);
        SwitchCompat switchCompat = (SwitchCompat) _$_findCachedViewById(i2).findViewById(com.psa.mym.mycitroenconnect.R.id.switchDashHeader);
        Intrinsics.checkNotNullExpressionValue(switchCompat, "layoutGeoHeader.switchDashHeader");
        ExtensionsKt.hide(switchCompat);
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(ChooseGeoFenceTypeFragment.Companion.newInstance(), SetFenceForFragment.Companion.newInstance(), SetLocationFragment.Companion.newInstance(), GeoFenceMapFragment.Companion.newInstance(), GeoFenceMapSettingsFragment.Companion.newInstance(), GeoFenceSetTimeFragment.Companion.newInstance(), GeoFenceSummaryFragment.Companion.newInstance());
        this.geoFenceAdapter = new CommonViewPagerAdapter(this, arrayListOf);
        ViewPager2 viewPager2 = (ViewPager2) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.viewPager2);
        viewPager2.setAdapter(this.geoFenceAdapter);
        viewPager2.setOrientation(0);
        viewPager2.setUserInputEnabled(false);
        setClickListener();
    }

    private final void navigateForCreateMode() {
        navigateToChooseFenceTypeFragment();
    }

    private final void navigateForEditMode() {
        String name = ExtensionsKt.getName(new GeoFenceSummaryFragment());
        if (name != null) {
            navigateToSelectedFragment(name);
        }
    }

    private final void navigateToSelectedFragment(String str) {
        int i2;
        if (Intrinsics.areEqual(str, ExtensionsKt.getName(new ChooseGeoFenceTypeFragment()))) {
            i2 = 0;
        } else if (Intrinsics.areEqual(str, ExtensionsKt.getName(new SetFenceForFragment()))) {
            i2 = 1;
        } else if (Intrinsics.areEqual(str, ExtensionsKt.getName(new SetLocationFragment()))) {
            i2 = 2;
        } else if (Intrinsics.areEqual(str, ExtensionsKt.getName(new GeoFenceMapFragment()))) {
            i2 = 3;
        } else if (Intrinsics.areEqual(str, ExtensionsKt.getName(new GeoFenceMapSettingsFragment()))) {
            i2 = 4;
        } else if (Intrinsics.areEqual(str, ExtensionsKt.getName(new GeoFenceSetTimeFragment()))) {
            i2 = 5;
        } else if (!Intrinsics.areEqual(str, ExtensionsKt.getName(new GeoFenceSummaryFragment()))) {
            return;
        } else {
            i2 = 6;
        }
        setCurrentItem(i2);
    }

    private final void setClickListener() {
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutGeoHeader).findViewById(com.psa.mym.mycitroenconnect.R.id.ivBack)).setOnClickListener(this);
        ((ViewPager2) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.viewPager2)).registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity$setClickListener$1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i2) {
                super.onPageScrollStateChanged(i2);
                Logger logger = Logger.INSTANCE;
                logger.e("State: " + i2);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int i2, float f2, int i3) {
                GeoFenceActivity geoFenceActivity;
                String string;
                String str;
                super.onPageScrolled(i2, f2, i3);
                if (i2 == 2 || i2 == 3) {
                    geoFenceActivity = GeoFenceActivity.this;
                    string = geoFenceActivity.getString(R.string.set_a_location);
                    str = "getString(R.string.set_a_location)";
                } else {
                    geoFenceActivity = GeoFenceActivity.this;
                    string = geoFenceActivity.getString(R.string.label_geo_fence);
                    str = "getString(R.string.label_geo_fence)";
                }
                Intrinsics.checkNotNullExpressionValue(string, str);
                geoFenceActivity.setTitle(string);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i2) {
                super.onPageSelected(i2);
                Logger logger = Logger.INSTANCE;
                logger.e("Position: " + i2);
            }
        });
    }

    private final void setCurrentItem(int i2) {
        String string;
        String str;
        ViewPager2 viewPager2 = (ViewPager2) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.viewPager2);
        if (viewPager2 != null) {
            viewPager2.setCurrentItem(i2, false);
        }
        if (i2 == 2 || i2 == 3) {
            string = getString(R.string.set_a_location);
            str = "getString(R.string.set_a_location)";
        } else {
            string = getString(R.string.label_geo_fence);
            str = "getString(R.string.label_geo_fence)";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        setTitle(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTitle(String str) {
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutGeoHeader).findViewById(com.psa.mym.mycitroenconnect.R.id.tvDbHeaderTitle)).setText(str);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseActivity, com.psa.mym.mycitroenconnect.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseActivity, com.psa.mym.mycitroenconnect.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Nullable
    public final GetGeoFenceResponseItem getGeoFenceResponse() {
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel != null) {
            return geoFenceCommonModel.getGeoFenceGetResponse();
        }
        return null;
    }

    @Subscribe
    public final void getMessage(@NotNull VehicleLocationResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.carLatLng = new LatLng(event.getCoordinate().getX(), event.getCoordinate().getY());
        GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
        if (geoFenceCommonModel == null) {
            return;
        }
        geoFenceCommonModel.setCarLatLng(this.carLatLng);
    }

    @Subscribe
    public final void getResponse(@NotNull ErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        ExtensionsKt.showToast(this, response.getMsg());
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0099, code lost:
        if (r1 != 6) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void handleBackClick() {
        int i2 = com.psa.mym.mycitroenconnect.R.id.viewPager2;
        if (((ViewPager2) _$_findCachedViewById(i2)).getCurrentItem() != 0) {
            GeoFenceCommonModel geoFenceCommonModel = getViewModel().getGeoFenceCommonModel();
            Integer valueOf = geoFenceCommonModel != null ? Integer.valueOf(geoFenceCommonModel.getFenceCreationMode()) : null;
            if (valueOf != null && valueOf.intValue() == 5) {
                int currentItem = ((ViewPager2) _$_findCachedViewById(i2)).getCurrentItem();
                if (currentItem != 0) {
                    if (currentItem == 3) {
                        int selectedFenceId = getViewModel().getSelectedFenceId();
                        if (selectedFenceId == 0 || selectedFenceId == 1) {
                            ((ViewPager2) _$_findCachedViewById(i2)).setCurrentItem(1, false);
                            return;
                        } else if (selectedFenceId != 2) {
                            return;
                        }
                    }
                    ((ViewPager2) _$_findCachedViewById(i2)).setCurrentItem(((ViewPager2) _$_findCachedViewById(i2)).getCurrentItem() - 1, false);
                    return;
                }
            } else {
                if ((valueOf != null && valueOf.intValue() == 7) || (valueOf != null && valueOf.intValue() == 6)) {
                    int currentItem2 = ((ViewPager2) _$_findCachedViewById(i2)).getCurrentItem();
                    if (currentItem2 != 3) {
                        if (currentItem2 != 4) {
                        }
                        ((ViewPager2) _$_findCachedViewById(i2)).setCurrentItem(((ViewPager2) _$_findCachedViewById(i2)).getCurrentItem() - 1, false);
                        return;
                    }
                    int selectedFenceId2 = getViewModel().getSelectedFenceId();
                    if (selectedFenceId2 != 0 && selectedFenceId2 != 1) {
                        if (selectedFenceId2 != 2) {
                            return;
                        }
                        ((ViewPager2) _$_findCachedViewById(i2)).setCurrentItem(((ViewPager2) _$_findCachedViewById(i2)).getCurrentItem() - 1, false);
                        return;
                    }
                    ((ViewPager2) _$_findCachedViewById(i2)).setCurrentItem(6, false);
                    return;
                }
                Logger logger = Logger.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("GeoFence Creation Mode: ");
                GeoFenceCommonModel geoFenceCommonModel2 = getViewModel().getGeoFenceCommonModel();
                sb.append(geoFenceCommonModel2 != null ? Integer.valueOf(geoFenceCommonModel2.getFenceCreationMode()) : null);
                logger.e(sb.toString());
            }
        }
        finish();
    }

    public final void navigateToChooseFenceTypeFragment() {
        String name = ExtensionsKt.getName(new ChooseGeoFenceTypeFragment());
        if (name != null) {
            navigateToSelectedFragment(name);
        }
    }

    public final void navigateToGeoFenceMapFragment() {
        String name = ExtensionsKt.getName(new GeoFenceMapFragment());
        if (name != null) {
            navigateToSelectedFragment(name);
        }
    }

    public final void navigateToGeoFenceMapSettingsFragment() {
        String name = ExtensionsKt.getName(new GeoFenceMapSettingsFragment());
        if (name != null) {
            navigateToSelectedFragment(name);
        }
    }

    public final void navigateToSetFenceFragment() {
        String name = ExtensionsKt.getName(new SetFenceForFragment());
        if (name != null) {
            navigateToSelectedFragment(name);
        }
    }

    public final void navigateToSetLocationFragment() {
        String name = ExtensionsKt.getName(new SetLocationFragment());
        if (name != null) {
            navigateToSelectedFragment(name);
        }
    }

    public final void navigateToSetTimeFragment() {
        String name = ExtensionsKt.getName(new GeoFenceSetTimeFragment());
        if (name != null) {
            navigateToSelectedFragment(name);
        }
    }

    public final void navigateToSummaryFragment() {
        String name = ExtensionsKt.getName(new GeoFenceSummaryFragment());
        if (name != null) {
            navigateToSelectedFragment(name);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        handleBackClick();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutGeoHeader).findViewById(com.psa.mym.mycitroenconnect.R.id.ivBack))) {
            handleBackClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_geo_fence);
        getIntentData();
        init();
        if (this.pageMode == 5) {
            navigateForCreateMode();
        } else {
            navigateForEditMode();
        }
    }
}
