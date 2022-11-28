package com.psa.mym.mycitroenconnect.controller.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceListActivity;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.LocateCarActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.NavDrawerAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.NavDrawerListInterface;
import com.psa.mym.mycitroenconnect.controller.adapters.view_pager.CarImageSliderAdapter;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ChildAccountInvitationDialog;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ExitFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.LogoutFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnInvitationView;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.QuickControlFragment;
import com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertFragment;
import com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertInterface;
import com.psa.mym.mycitroenconnect.controller.dialog.OnDialogOkClickListener;
import com.psa.mym.mycitroenconnect.controller.dialog.SingleCustomDialog;
import com.psa.mym.mycitroenconnect.controller.fragments.AboutCitroenFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.AppInfoFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.ECallFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.FeedbackFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.HomeFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.NotificationSettingsFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.NotificationsFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.RoadSideAssistanceFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.SOSFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.SecurityFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.WebViewFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.charging_station.ChargingStationFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.charging_station.ChargingStationListFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.my_car.CarFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.my_trips.TripFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.profile.ProfileFragment;
import com.psa.mym.mycitroenconnect.fcm.UpdatedEvents;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.MyNotificationModel;
import com.psa.mym.mycitroenconnect.model.NavItem;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.notification.UnreadNotificationCount;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCarResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCars;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUser;
import com.psa.mym.mycitroenconnect.model.secondary_user.SharedVehicle;
import com.psa.mym.mycitroenconnect.services.FCMService;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class MainActivity extends BusBaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, SharedPreferences.OnSharedPreferenceChangeListener, NavDrawerListInterface, OnInvitationView, OnDialogOkClickListener, FullScreenAlertInterface {
    @Nullable
    private CarImageSliderAdapter carImageSliderAdapter;
    private Fragment currentFragment;
    private boolean doubleBackToExitPressedOnce;
    private boolean isApiCalledOnce;
    private boolean isFromPushNotif;
    private boolean isInvitationAccepted;
    private long lastClickTime;
    @Nullable
    private ViewPager mViewPager;
    @Nullable
    private NavDrawerAdapter navAdapter;
    @Nullable
    private MyNotificationModel notificationModel;
    @Nullable
    private Toolbar toolbar;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mobileNumber = "";
    @NotNull
    private ArrayList<MyCar> carList = new ArrayList<>();

    private final void apiPermission() {
        boolean isBlank;
        this.isApiCalledOnce = false;
        Logger.INSTANCE.d("carImageSliderAdapter api permission called");
        if (this.mobileNumber.length() > 0) {
            isBlank = StringsKt__StringsJVMKt.isBlank(this.mobileNumber);
            if (!isBlank) {
                SecondaryUserService secondaryUserService = new SecondaryUserService();
                StringBuilder sb = new StringBuilder();
                sb.append("91");
                String str = this.mobileNumber;
                String substring = str.substring(3, str.length());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                sb.append(substring);
                secondaryUserService.getMyCarList(this, sb.toString(), AppConstants.SCREEN_HOME);
            }
        }
    }

    private final void callHelpLineNumber() {
        Dexter.withActivity(this).withPermissions("android.permission.CALL_PHONE").withListener(new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.MainActivity$callHelpLineNumber$1
            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionRationaleShouldBeShown(@Nullable List<PermissionRequest> list, @NotNull PermissionToken token) {
                Intrinsics.checkNotNullParameter(token, "token");
                token.continuePermissionRequest();
            }

            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            @SuppressLint({"MissingPermission"})
            public void onPermissionsChecked(@NotNull MultiplePermissionsReport report) {
                CharSequence trim;
                Intrinsics.checkNotNullParameter(report, "report");
                if (!report.areAllPermissionsGranted()) {
                    MainActivity mainActivity = MainActivity.this;
                    String string = mainActivity.getString(R.string.lbl_call_permission_msg);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.lbl_call_permission_msg)");
                    ExtensionsKt.showToast(mainActivity, string);
                    return;
                }
                try {
                    String string2 = MainActivity.this.getString(R.string.label_customer_care_number);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.label_customer_care_number)");
                    trim = StringsKt__StringsKt.trim((CharSequence) string2);
                    String obj = trim.toString();
                    MainActivity.this.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + obj)));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }).check();
    }

    private final void changeBackGround(boolean z) {
        ConstraintLayout constraintLayout;
        int i2;
        if (z) {
            constraintLayout = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.dashboard_bg);
            i2 = R.drawable.home_bg;
        } else {
            constraintLayout = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.dashboard_bg);
            i2 = 0;
        }
        constraintLayout.setBackgroundResource(i2);
    }

    private final Pair<Boolean, SharedVehicle> checkForInvitation(MyCars myCars) {
        for (MyCar myCar : myCars) {
            myCar.setVehicleImage(Integer.valueOf((int) R.drawable.citroen_nav_bar_car));
            myCar.setVehicleSelected(false);
            String str = this.mobileNumber;
            String substring = str.substring(1, str.length());
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            if (Intrinsics.areEqual(substring, myCar.getAccountId())) {
                SharedPref.Companion companion = SharedPref.Companion;
                companion.setIsPrimaryUser(this, "true");
                companion.setIsGuestUser(this, "false");
            } else {
                myCar.setAccessible(true);
                myCar.setViewOnly(true);
                if (myCar.getInviteStatus() != null && Intrinsics.areEqual(myCar.getInviteStatus(), AppConstants.SECONDARY_USER_STATE_PENDING)) {
                    String carModelName = myCar.getCarModelName();
                    Intrinsics.checkNotNull(carModelName);
                    String ownerName = myCar.getOwnerName();
                    Intrinsics.checkNotNull(ownerName);
                    String vehicleRegNo = myCar.getVehicleRegNo();
                    Intrinsics.checkNotNull(vehicleRegNo);
                    String vinNum = myCar.getVinNum();
                    Intrinsics.checkNotNull(vinNum);
                    String accountId = myCar.getAccountId();
                    Intrinsics.checkNotNull(accountId);
                    String substring2 = accountId.substring(2, myCar.getAccountId().length());
                    Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    return new Pair<>(Boolean.TRUE, new SharedVehicle(carModelName, ownerName, vehicleRegNo, vinNum, "+91", substring2, R.drawable.citroen_nav_bar_car));
                }
            }
        }
        return new Pair<>(Boolean.FALSE, null);
    }

    private final void exitByBackKey() {
        ExitFragment exitFragment = new ExitFragment();
        exitFragment.show(getSupportFragmentManager(), ExitFragment.TAG);
        exitFragment.setCancelable(false);
    }

    private final void generateRegisterVehicleResponse(MyCars myCars) {
        String str;
        String str2;
        String str3;
        String str4;
        Logger.INSTANCE.d("carImageSliderAdapter generateRegisterVehicleResponse");
        if ((!myCars.isEmpty()) && myCars.size() > 0) {
            MyCar myCar = myCars.get(0);
            Intrinsics.checkNotNullExpressionValue(myCar, "response[0]");
            String userType = myCar.getUserType();
            if (userType != null) {
                str4 = userType.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            } else {
                str4 = null;
            }
            if (Intrinsics.areEqual(str4, "g")) {
                myCars.remove(0);
            }
        }
        SharedPref.Companion companion = SharedPref.Companion;
        companion.setVinTokenDetails(this, myCars);
        companion.setTokenDetails(this, companion.getVinNumber(this));
        this.carList.clear();
        for (MyCar myCar2 : myCars) {
            String userType2 = myCar2.getUserType();
            if (userType2 != null) {
                str = userType2.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            } else {
                str = null;
            }
            if (!Intrinsics.areEqual(str, "g")) {
                String userType3 = myCar2.getUserType();
                if (userType3 != null) {
                    str2 = userType3.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                } else {
                    str2 = null;
                }
                if (!Intrinsics.areEqual(str2, "p")) {
                    if (Intrinsics.areEqual(str2, "s")) {
                        String inviteStatus = myCar2.getInviteStatus();
                        if (inviteStatus != null) {
                            str3 = inviteStatus.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        } else {
                            str3 = null;
                        }
                        if (!(Intrinsics.areEqual(str3, "accepted") ? true : Intrinsics.areEqual(str3, AppConstants.SECONDARY_USER_STATE_VERIFIED))) {
                            Logger.INSTANCE.e(myCar2.getVinNum() + " vin car invite status is: " + myCar2.getInviteStatus());
                        }
                    }
                }
                this.carList.add(myCar2);
            }
        }
        runOnUiThread(new Runnable() { // from class: i.l
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.m78generateRegisterVehicleResponse$lambda10(MainActivity.this);
            }
        });
        setArrow();
        setCarCurrentPage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: generateRegisterVehicleResponse$lambda-10  reason: not valid java name */
    public static final void m78generateRegisterVehicleResponse$lambda10(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CarImageSliderAdapter carImageSliderAdapter = this$0.carImageSliderAdapter;
        if (carImageSliderAdapter != null) {
            carImageSliderAdapter.updateCarList(this$0.carList);
        }
        Logger logger = Logger.INSTANCE;
        logger.d("carImageSliderAdapter list count:" + this$0.carList.size());
    }

    private final void getIntentData() {
        if (getIntent() == null || !getIntent().hasExtra(AppConstants.ARG_IS_FROM_PUSH_NOTIF) || !getIntent().hasExtra(AppConstants.ARG_PUSH_NOTIF_MODEL)) {
            navigateToHomeFragment();
            return;
        }
        this.isFromPushNotif = getIntent().getBooleanExtra(AppConstants.ARG_IS_FROM_PUSH_NOTIF, false);
        MyNotificationModel myNotificationModel = (MyNotificationModel) getIntent().getParcelableExtra(AppConstants.ARG_PUSH_NOTIF_MODEL);
        this.notificationModel = myNotificationModel;
        if (this.isFromPushNotif && myNotificationModel == null) {
            String string = getString(R.string.something_went_wrong);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.something_went_wrong)");
            ExtensionsKt.showToast(this, string);
        } else {
            if (!Intrinsics.areEqual(myNotificationModel != null ? myNotificationModel.getAlertState() : null, AppConstants.Notification_LowFuelAlert)) {
                navigateToHomeFragment();
            }
            MyNotificationModel myNotificationModel2 = this.notificationModel;
            if (myNotificationModel2 != null) {
                if (Intrinsics.areEqual(myNotificationModel2.getPriority(), "1") && (Intrinsics.areEqual(myNotificationModel2.getAlertState(), AppConstants.Notification_IntrusionAlert) || Intrinsics.areEqual(myNotificationModel2.getAlertState(), AppConstants.Notification_CrashAlert) || Intrinsics.areEqual(myNotificationModel2.getAlertState(), AppConstants.Notification_TowAwayAlert) || Intrinsics.areEqual(myNotificationModel2.getAlertState(), AppConstants.Notification_LowFuelAlert))) {
                    onAlertBtnClick(myNotificationModel2.getAlertState());
                } else {
                    showNotificationUI(myNotificationModel2);
                }
            }
        }
        getIntent().replaceExtras(new Bundle());
        getIntent().setAction("");
        getIntent().setData(null);
        getIntent().setFlags(0);
        this.isFromPushNotif = false;
    }

    private final void hideBottomAppBar() {
    }

    private final void initViews() {
        int i2 = com.psa.mym.mycitroenconnect.R.id.bottomNavView;
        ((BottomNavigationView) _$_findCachedViewById(i2)).getMenu().getItem(2).setEnabled(false);
        ((BottomNavigationView) _$_findCachedViewById(i2)).setOnNavigationItemSelectedListener(this);
        ((NavigationView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.navView)).setNavigationItemSelectedListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainAppHeader);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setHomeButtonEnabled(true);
        }
        ActionBar supportActionBar3 = getSupportActionBar();
        if (supportActionBar3 != null) {
            supportActionBar3.setHomeAsUpIndicator(R.drawable.ic_ham_menu);
        }
        AppCompatTextView tvBadgeNotifCount = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvBadgeNotifCount);
        Intrinsics.checkNotNullExpressionValue(tvBadgeNotifCount, "tvBadgeNotifCount");
        ExtensionsKt.hide(tvBadgeNotifCount);
        setUpNavView();
        setUpHeaderView();
    }

    private final boolean isCurrentFragment(Fragment fragment) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments, "supportFragmentManager.fragments");
        if ((!fragments.isEmpty()) && fragments.size() > 0) {
            CollectionsKt___CollectionsJvmKt.reverse(fragments);
            for (Fragment fragment2 : fragments) {
                if (fragment2 != null && Intrinsics.areEqual(ExtensionsKt.getName(fragment2), ExtensionsKt.getName(fragment))) {
                    if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment2), ExtensionsKt.getName(new HomeFragment()))) {
                        return !Intrinsics.areEqual(ExtensionsKt.getName(fragment2), ExtensionsKt.getName(new WebViewFragment()));
                    }
                    HomeFragment homeFragment = (HomeFragment) fragment2;
                    if (Intrinsics.areEqual(homeFragment.getCurrentDisplayMode(), getString(R.string.home))) {
                        changeBackGround(false);
                        return true;
                    }
                    homeFragment.displayHome();
                    changeBackGround(true);
                }
            }
        }
        return false;
    }

    private final void navigateToGeoFenceList() {
        boolean z;
        Fragment fragment = this.currentFragment;
        Fragment fragment2 = null;
        if (fragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
            fragment = null;
        }
        if (Intrinsics.areEqual(ExtensionsKt.getName(fragment), ExtensionsKt.getName(new HomeFragment()))) {
            Fragment fragment3 = this.currentFragment;
            if (fragment3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                fragment3 = null;
            }
            if (!Intrinsics.areEqual(((HomeFragment) fragment3).getCurrentDisplayMode(), getString(R.string.home))) {
                Fragment fragment4 = this.currentFragment;
                if (fragment4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                } else {
                    fragment2 = fragment4;
                }
                ((HomeFragment) fragment2).displayHome();
                z = true;
            }
            startActivity(new Intent(this, GeoFenceListActivity.class));
        }
        z = false;
        changeBackGround(z);
        startActivity(new Intent(this, GeoFenceListActivity.class));
    }

    private final void navigateToHomeFragment() {
        setDashboardTitle();
        changeBackGround(true);
        replaceFragment$default(this, new HomeFragment(), false, 2, null);
    }

    private final void navigateToNotificationSettings() {
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getResources().getString(R.string.label_notification_settings));
        replaceFragment$default(this, new NotificationSettingsFragment(), false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBackPressed$lambda-1  reason: not valid java name */
    public static final void m79onBackPressed$lambda1(MainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doubleBackToExitPressedOnce = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m80onCreate$lambda0(MainActivity this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ExtensionsKt.showToast(this$0, "Your access revoked. Please try logging in again");
        SharedPref.Companion.logoutFromApp(this$0);
    }

    public static /* synthetic */ void replaceFragment$default(MainActivity mainActivity, Fragment fragment, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        mainActivity.replaceFragment(fragment, z);
    }

    private final void setArrow() {
        if (!(!this.carList.isEmpty()) || this.carList.size() <= 1) {
            setLeftArrowEnable(false);
            setRightArrowEnable(false);
            return;
        }
        setLeftArrowEnable(false);
        setRightArrowEnable(true);
    }

    private final void setCarCurrentPage() {
        ViewPager viewPager;
        int i2 = 0;
        for (Object obj : this.carList) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual(((MyCar) obj).getVinNum(), SharedPref.Companion.getVinNumber(this)) && (viewPager = this.mViewPager) != null) {
                viewPager.setCurrentItem(i2, true);
            }
            i2 = i3;
        }
    }

    private final void setDashboardTitle() {
        boolean isBlank;
        String carTitle = SharedPref.Companion.getCarTitle(this);
        if (carTitle != null) {
            if (carTitle.length() > 0) {
                isBlank = StringsKt__StringsJVMKt.isBlank(carTitle);
                if (!isBlank) {
                    ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(carTitle);
                    return;
                }
            }
        }
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getString(R.string.my_citroen));
    }

    private final void setLeftArrowEnable(boolean z) {
        Logger logger = Logger.INSTANCE;
        logger.e("Is Enabled : " + z);
    }

    private final void setListener() {
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnNotif)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivNavClose)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivNavLeftArrow)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivNavRightArrow)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.viewSos)).setOnClickListener(this);
        ((FloatingActionButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.fab)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnNavSignOut)).setOnClickListener(this);
    }

    private final void setRightArrowEnable(boolean z) {
        Logger logger = Logger.INSTANCE;
        logger.e("Is Enabled : " + z);
    }

    private final void setUpHeaderView() {
        setArrow();
        this.mViewPager = (ViewPager) findViewById(R.id.viewPagerHeader);
        CarImageSliderAdapter carImageSliderAdapter = new CarImageSliderAdapter(this, this.carList);
        this.carImageSliderAdapter = carImageSliderAdapter;
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            viewPager.setAdapter(carImageSliderAdapter);
            viewPager.addOnPageChangeListener(this);
        }
    }

    private final void setUpNavView() {
        ArrayList arrayListOf;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        String string = getString(R.string.menu_sub_title_help);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.menu_sub_title_help)");
        String string2 = getString(R.string.menu_title_sos);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.menu_title_sos)");
        String string3 = getString(R.string.menu_title_roadside_assistance);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.menu_title_roadside_assistance)");
        String string4 = getString(R.string.menu_sub_title_e_settings);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.menu_sub_title_e_settings)");
        String string5 = getString(R.string.menu_title_notification);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.menu_title_notification)");
        String string6 = getString(R.string.menu_title_security);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.menu_title_security)");
        String string7 = getString(R.string.menu_sub_title_others);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.menu_sub_title_others)");
        String string8 = getString(R.string.menu_title_about_citroen);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.menu_title_about_citroen)");
        String string9 = getString(R.string.menu_title_app_info);
        Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.menu_title_app_info)");
        String string10 = getString(R.string.menu_title_privacy_policy);
        Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.menu_title_privacy_policy)");
        String string11 = getString(R.string.menu_title_terms_n_conditions);
        Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.menu_title_terms_n_conditions)");
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new NavItem(string, 0, true), new NavItem(string2, R.drawable.ic_sos_dark_red, false), new NavItem(string3, R.drawable.ic_roadside_assistance, false), new NavItem(string4, 0, true), new NavItem(string5, R.drawable.ic_notification_red, false), new NavItem(string6, R.drawable.ic_security, false), new NavItem(string7, 0, true), new NavItem(string8, R.drawable.ic_citroen_red, false), new NavItem(string9, R.drawable.ic_app_info, false), new NavItem(string10, R.drawable.ic_privacy_policy, false), new NavItem(string11, R.drawable.ic_terms_n_condition, false));
        this.navAdapter = new NavDrawerAdapter(this, arrayListOf, this);
        int i2 = com.psa.mym.mycitroenconnect.R.id.rvNavView;
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(linearLayoutManager);
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(this.navAdapter);
    }

    private final void showNotificationUI(final MyNotificationModel myNotificationModel) {
        runOnUiThread(new Runnable() { // from class: i.m
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.m81showNotificationUI$lambda13(MyNotificationModel.this, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002f, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_LowFuelAlert) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_CrashAlert) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r5.getPriority(), "1");
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001d, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_TowAwayAlert) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:
        if (r0.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_IntrusionAlert) != false) goto L5;
     */
    /* renamed from: showNotificationUI$lambda-13  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m81showNotificationUI$lambda13(MyNotificationModel event, MainActivity this$0) {
        boolean z;
        Intrinsics.checkNotNullParameter(event, "$event");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String alertState = event.getAlertState();
        switch (alertState.hashCode()) {
            case -894483947:
                break;
            case 93415538:
                break;
            case 970664341:
                break;
            case 1881199922:
                break;
            default:
                z = false;
                break;
        }
        if (z) {
            FullScreenAlertFragment.Companion companion = FullScreenAlertFragment.Companion;
            FullScreenAlertFragment newInstance = companion.newInstance(event.getAlertState());
            newInstance.show(this$0.getSupportFragmentManager(), companion.getTAG());
            newInstance.setOnFullScreenAlertListener(this$0);
            newInstance.setCancelable(false);
            return;
        }
        SharedPref.Companion companion2 = SharedPref.Companion;
        if (companion2.getLastFCMTime(this$0) != System.currentTimeMillis()) {
            companion2.setLastFCMTime(this$0, System.currentTimeMillis());
            new SingleCustomDialog(this$0, event, this$0.getString(R.string.ok), this$0);
        }
    }

    private final void unCheckAllMenuItems(Menu menu) {
        int size = menu.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = menu.getItem(i2);
            Intrinsics.checkNotNullExpressionValue(item, "menu.getItem(i)");
            if (item.hasSubMenu()) {
                SubMenu subMenu = item.getSubMenu();
                Intrinsics.checkNotNullExpressionValue(subMenu, "item.subMenu");
                unCheckAllMenuItems(subMenu);
            } else {
                item.setChecked(false);
            }
        }
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

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        String apiName = event.getApiName();
        switch (apiName.hashCode()) {
            case -1835279799:
                if (apiName.equals("MyCarsList")) {
                    this.isApiCalledOnce = false;
                    return;
                }
                return;
            case -1694127520:
                if (apiName.equals("UnReadCount")) {
                    Logger logger = Logger.INSTANCE;
                    logger.e("UnReadCount" + event.getMsg());
                    return;
                }
                return;
            case 428375213:
                if (!apiName.equals("LocationDetails")) {
                    return;
                }
                break;
            case 2041413192:
                if (!apiName.equals("GetUserProfile")) {
                    return;
                }
                break;
            default:
                return;
        }
        ExtensionsKt.showToast(this, event.getMsg());
    }

    @Subscribe
    public final void getMessage(@NotNull MyNotificationModel event) {
        Intrinsics.checkNotNullParameter(event, "event");
        getUnreadNotificationCount();
        showNotificationUI(event);
    }

    @Subscribe
    public final void getResponse(@NotNull UnreadNotificationCount response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.getUnReadCount() == 0) {
            AppCompatTextView tvBadgeNotifCount = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvBadgeNotifCount);
            Intrinsics.checkNotNullExpressionValue(tvBadgeNotifCount, "tvBadgeNotifCount");
            ExtensionsKt.hide(tvBadgeNotifCount);
            return;
        }
        int i2 = com.psa.mym.mycitroenconnect.R.id.tvBadgeNotifCount;
        AppCompatTextView tvBadgeNotifCount2 = (AppCompatTextView) _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(tvBadgeNotifCount2, "tvBadgeNotifCount");
        ExtensionsKt.show(tvBadgeNotifCount2);
        ((AppCompatTextView) _$_findCachedViewById(i2)).setText(String.valueOf(response.getUnReadCount()));
    }

    @Subscribe
    public final void getResponse(@NotNull MyCarResponse myCarResponse) {
        String str;
        Intrinsics.checkNotNullParameter(myCarResponse, "myCarResponse");
        boolean z = true;
        this.isApiCalledOnce = true;
        Logger.INSTANCE.d("carImageSliderAdapter getResponse");
        AppUtil.Companion.dismissDialog();
        if (Intrinsics.areEqual(myCarResponse.getScreenName(), AppConstants.SCREEN_HOME)) {
            MyCars myCars = myCarResponse.getMyCars();
            if (!(myCars == null || myCars.isEmpty())) {
                Fragment fragment = null;
                if ((!myCars.isEmpty()) && myCars.size() > 0) {
                    MyCar myCar = myCars.get(0);
                    Intrinsics.checkNotNullExpressionValue(myCar, "response[0]");
                    MyCar myCar2 = myCar;
                    String userType = myCar2.getUserType();
                    if (userType != null) {
                        str = userType.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    } else {
                        str = null;
                    }
                    if (Intrinsics.areEqual(str, "g")) {
                        Token token = myCar2.getToken();
                        if (token != null) {
                            SharedPref.Companion.setSecondaryUserTokenDetails(this, token);
                        }
                        myCars.remove(0);
                    }
                }
                ArrayList arrayList = new ArrayList();
                Pair<Boolean, SharedVehicle> checkForInvitation = checkForInvitation(myCars);
                boolean booleanValue = checkForInvitation.component1().booleanValue();
                SharedVehicle component2 = checkForInvitation.component2();
                if (!this.isInvitationAccepted || booleanValue) {
                    z = false;
                }
                this.isInvitationAccepted = z;
                if (component2 != null) {
                    arrayList.add(component2);
                }
                if (!this.isInvitationAccepted) {
                    Fragment fragment2 = this.currentFragment;
                    if (fragment2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                    } else {
                        fragment = fragment2;
                    }
                    if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment), ExtensionsKt.getName(new ProfileFragment())) && booleanValue) {
                        ChildAccountInvitationDialog newInstance = ChildAccountInvitationDialog.Companion.newInstance(new SecondaryUser("", "", "", arrayList));
                        newInstance.setOnInvitationView(this);
                        newInstance.show(getSupportFragmentManager(), ChildAccountInvitationDialog.TAG);
                        newInstance.setCancelable(false);
                    }
                }
            }
            generateRegisterVehicleResponse(myCars);
        }
    }

    public final void getUnreadNotificationCount() {
        new FCMService().getUnreadNotificationCount(this, SharedPref.Companion.getVinNumber(this));
    }

    public final void hideNavBar() {
        ((BottomNavigationView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.bottomNavView)).setVisibility(8);
        _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.mainAppHeader).setVisibility(8);
        _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.main_bottom_app_bar).setVisibility(8);
    }

    public final void navigateToChargingStationFragment() {
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getString(R.string.charging_st));
        replaceFragment$default(this, new ChargingStationFragment(), false, 2, null);
    }

    public final void navigateToLocateFragment() {
        startActivity(new Intent(this, LocateCarActivity.class));
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnInvitationView
    public void onAccepted() {
        this.isInvitationAccepted = true;
        apiPermission();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r4.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_IntrusionAlert) != false) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002f, code lost:
        if (r4.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_CrashAlert) == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0032, code lost:
        navigateToLocateFragment();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
        if (r4.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_TowAwayAlert) == false) goto L17;
     */
    @Override // com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onAlertBtnClick(@Nullable String str) {
        if (str != null) {
            switch (str.hashCode()) {
                case -894483947:
                    break;
                case 93415538:
                    if (str.equals(AppConstants.Notification_LowFuelAlert)) {
                        navigateToChargingStationFragment();
                        return;
                    }
                    break;
                case 970664341:
                    break;
                case 1881199922:
                    break;
            }
        }
        Logger logger = Logger.INSTANCE;
        logger.e("AlertState " + str);
    }

    @Override // com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertInterface
    public void onAlertDismissClick() {
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Fragment fragment = this.currentFragment;
        Fragment fragment2 = null;
        if (fragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
            fragment = null;
        }
        if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment), ExtensionsKt.getName(new SOSFragment()))) {
            Fragment fragment3 = this.currentFragment;
            if (fragment3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                fragment3 = null;
            }
            if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment3), ExtensionsKt.getName(new ECallFragment()))) {
                Fragment fragment4 = this.currentFragment;
                if (fragment4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                    fragment4 = null;
                }
                if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment4), ExtensionsKt.getName(new RoadSideAssistanceFragment()))) {
                    Fragment fragment5 = this.currentFragment;
                    if (fragment5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                        fragment5 = null;
                    }
                    if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment5), ExtensionsKt.getName(new ProfileFragment()))) {
                        Fragment fragment6 = this.currentFragment;
                        if (fragment6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                            fragment6 = null;
                        }
                        if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment6), ExtensionsKt.getName(new NotificationsFragment()))) {
                            Fragment fragment7 = this.currentFragment;
                            if (fragment7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                fragment7 = null;
                            }
                            if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment7), ExtensionsKt.getName(new ChargingStationFragment()))) {
                                Fragment fragment8 = this.currentFragment;
                                if (fragment8 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                    fragment8 = null;
                                }
                                if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment8), ExtensionsKt.getName(new CarFragment()))) {
                                    Fragment fragment9 = this.currentFragment;
                                    if (fragment9 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                        fragment9 = null;
                                    }
                                    if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment9), ExtensionsKt.getName(new TripFragment()))) {
                                        Fragment fragment10 = this.currentFragment;
                                        if (fragment10 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                            fragment10 = null;
                                        }
                                        if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment10), ExtensionsKt.getName(new NotificationSettingsFragment()))) {
                                            Fragment fragment11 = this.currentFragment;
                                            if (fragment11 == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                                fragment11 = null;
                                            }
                                            if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment11), ExtensionsKt.getName(new SecurityFragment()))) {
                                                Fragment fragment12 = this.currentFragment;
                                                if (fragment12 == null) {
                                                    Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                                    fragment12 = null;
                                                }
                                                if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment12), ExtensionsKt.getName(new AboutCitroenFragment()))) {
                                                    Fragment fragment13 = this.currentFragment;
                                                    if (fragment13 == null) {
                                                        Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                                        fragment13 = null;
                                                    }
                                                    if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment13), ExtensionsKt.getName(new AppInfoFragment()))) {
                                                        Fragment fragment14 = this.currentFragment;
                                                        if (fragment14 == null) {
                                                            Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                                            fragment14 = null;
                                                        }
                                                        if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment14), ExtensionsKt.getName(new WebViewFragment()))) {
                                                            Fragment fragment15 = this.currentFragment;
                                                            if (fragment15 == null) {
                                                                Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                                                fragment15 = null;
                                                            }
                                                            if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment15), ExtensionsKt.getName(new FeedbackFragment()))) {
                                                                Fragment fragment16 = this.currentFragment;
                                                                if (fragment16 == null) {
                                                                    Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                                                    fragment16 = null;
                                                                }
                                                                if (Intrinsics.areEqual(ExtensionsKt.getName(fragment16), ExtensionsKt.getName(new HomeFragment()))) {
                                                                    Fragment fragment17 = this.currentFragment;
                                                                    if (fragment17 == null) {
                                                                        Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                                                        fragment17 = null;
                                                                    }
                                                                    if (!Intrinsics.areEqual(((HomeFragment) fragment17).getCurrentDisplayMode(), getString(R.string.home))) {
                                                                        Fragment fragment18 = this.currentFragment;
                                                                        if (fragment18 == null) {
                                                                            Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                                                        } else {
                                                                            fragment2 = fragment18;
                                                                        }
                                                                        ((HomeFragment) fragment2).displayHome();
                                                                        return;
                                                                    }
                                                                    FragmentManager supportFragmentManager = getSupportFragmentManager();
                                                                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                                                                    int backStackEntryCount = supportFragmentManager.getBackStackEntryCount() - 1;
                                                                    for (int i2 = 0; i2 < backStackEntryCount; i2++) {
                                                                        supportFragmentManager.popBackStack();
                                                                    }
                                                                    int i3 = com.psa.mym.mycitroenconnect.R.id.navView;
                                                                    NavigationView navView = (NavigationView) _$_findCachedViewById(i3);
                                                                    Intrinsics.checkNotNullExpressionValue(navView, "navView");
                                                                    if (ExtensionsKt.isVisible(navView)) {
                                                                        NavigationView navView2 = (NavigationView) _$_findCachedViewById(i3);
                                                                        Intrinsics.checkNotNullExpressionValue(navView2, "navView");
                                                                        ExtensionsKt.hide(navView2);
                                                                        return;
                                                                    } else if (this.doubleBackToExitPressedOnce) {
                                                                        exitByBackKey();
                                                                        return;
                                                                    } else {
                                                                        this.doubleBackToExitPressedOnce = true;
                                                                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: i.k
                                                                            @Override // java.lang.Runnable
                                                                            public final void run() {
                                                                                MainActivity.m79onBackPressed$lambda1(MainActivity.this);
                                                                            }
                                                                        }, SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS);
                                                                        return;
                                                                    }
                                                                }
                                                                Fragment fragment19 = this.currentFragment;
                                                                if (fragment19 == null) {
                                                                    Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                                                    fragment19 = null;
                                                                }
                                                                if (Intrinsics.areEqual(ExtensionsKt.getName(fragment19), ExtensionsKt.getName(new ChargingStationListFragment()))) {
                                                                    showNavBar();
                                                                    try {
                                                                        this.currentFragment = new ChargingStationFragment();
                                                                        getSupportFragmentManager().popBackStack();
                                                                        return;
                                                                    } catch (Exception e2) {
                                                                        Logger logger = Logger.INSTANCE;
                                                                        StringBuilder sb = new StringBuilder();
                                                                        sb.append("Exception: ");
                                                                        e2.printStackTrace();
                                                                        sb.append(Unit.INSTANCE);
                                                                        logger.e(sb.toString());
                                                                        return;
                                                                    }
                                                                } else if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
                                                                    Logger logger2 = Logger.INSTANCE;
                                                                    StringBuilder sb2 = new StringBuilder();
                                                                    sb2.append("Else Fragment Name: ");
                                                                    Fragment fragment20 = this.currentFragment;
                                                                    if (fragment20 == null) {
                                                                        Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                                                    } else {
                                                                        fragment2 = fragment20;
                                                                    }
                                                                    sb2.append(ExtensionsKt.getName(fragment2));
                                                                    logger2.e(sb2.toString());
                                                                    return;
                                                                } else {
                                                                    Logger logger3 = Logger.INSTANCE;
                                                                    StringBuilder sb3 = new StringBuilder();
                                                                    sb3.append("If Fragment Name: ");
                                                                    Fragment fragment21 = this.currentFragment;
                                                                    if (fragment21 == null) {
                                                                        Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                                                                    } else {
                                                                        fragment2 = fragment21;
                                                                    }
                                                                    sb3.append(ExtensionsKt.getName(fragment2));
                                                                    logger3.e(sb3.toString());
                                                                    getSupportFragmentManager().popBackStack();
                                                                    return;
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
        int i4 = com.psa.mym.mycitroenconnect.R.id.bottomNavView;
        Menu menu = ((BottomNavigationView) _$_findCachedViewById(i4)).getMenu();
        Intrinsics.checkNotNullExpressionValue(menu, "bottomNavView.menu");
        unCheckAllMenuItems(menu);
        ((BottomNavigationView) _$_findCachedViewById(i4)).getMenu().getItem(0).setChecked(true);
        navigateToHomeFragment();
        showNavBar();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        DialogFragment logoutFragment;
        FragmentManager supportFragmentManager;
        String str;
        Fragment sOSFragment;
        ViewPager viewPager;
        int currentItem;
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        if (valueOf == null || valueOf.intValue() != R.id.btnNotif) {
            if (valueOf != null && valueOf.intValue() == R.id.ivNavClose) {
                ((NavigationView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.navView)).setVisibility(8);
                return;
            }
            if (valueOf != null && valueOf.intValue() == R.id.ivNavLeftArrow) {
                viewPager = this.mViewPager;
                if (viewPager == null) {
                    return;
                }
                currentItem = viewPager.getCurrentItem() - 1;
            } else if (valueOf != null && valueOf.intValue() == R.id.ivNavRightArrow) {
                viewPager = this.mViewPager;
                if (viewPager == null) {
                    return;
                }
                currentItem = viewPager.getCurrentItem() + 1;
            } else if (valueOf == null || valueOf.intValue() != R.id.viewSos) {
                if (valueOf != null && valueOf.intValue() == R.id.fab) {
                    logoutFragment = new QuickControlFragment();
                    supportFragmentManager = getSupportFragmentManager();
                    str = QuickControlFragment.Companion.getTAG();
                } else if (valueOf == null || valueOf.intValue() != R.id.btnNavSignOut) {
                    return;
                } else {
                    logoutFragment = new LogoutFragment();
                    supportFragmentManager = getSupportFragmentManager();
                    str = LogoutFragment.TAG;
                }
                logoutFragment.show(supportFragmentManager, str);
                logoutFragment.setCancelable(false);
                return;
            } else {
                hideNavBar();
                sOSFragment = new SOSFragment();
            }
            viewPager.setCurrentItem(currentItem, true);
            return;
        }
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getString(R.string.notifications));
        sOSFragment = new NotificationsFragment();
        replaceFragment$default(this, sOSFragment, false, 2, null);
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnInvitationView
    public void onClosed() {
        apiPermission();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        Logger logger = Logger.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("===Navigation End : ");
        AppUtil.Companion.logEndTime("onCreate");
        sb.append(Unit.INSTANCE);
        logger.e(sb.toString());
        setContentView(R.layout.activity_main);
        initViews();
        setListener();
        UpdatedEvents.INSTANCE.getAccessRevokeEvent().observe(this, new Observer() { // from class: i.j
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MainActivity.m80onCreate$lambda0(MainActivity.this, (String) obj);
            }
        });
        getIntentData();
        this.mobileNumber = SharedPref.Companion.getMobileNumber(this);
        apiPermission();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
        if (r3.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_TowAwayAlert) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
        if (r3.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_IntrusionAlert) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0042, code lost:
        if (r3.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_StolenVehicleAlert) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0065, code lost:
        if (r3.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_ValetModeAlert) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006e, code lost:
        if (r3.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_GeoFenceAlert) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0077, code lost:
        if (r3.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_CrashAlert) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007a, code lost:
        navigateToLocateFragment();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:?, code lost:
        return;
     */
    @Override // com.psa.mym.mycitroenconnect.controller.dialog.OnDialogOkClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onDialogCancelClick(@Nullable Dialog dialog, @NotNull MyNotificationModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (dialog != null) {
            dialog.dismiss();
        }
        String alertState = model.getAlertState();
        switch (alertState.hashCode()) {
            case -894483947:
                break;
            case -778729028:
                break;
            case -337523735:
                break;
            case 93415538:
                if (alertState.equals(AppConstants.Notification_LowFuelAlert)) {
                    navigateToChargingStationFragment();
                    return;
                }
                Logger logger = Logger.INSTANCE;
                logger.e("AlertState " + model.getAlertState());
                return;
            case 190548681:
                if (alertState.equals("ACIdlingAlert")) {
                    navigateToNotificationSettings();
                    return;
                }
                Logger logger2 = Logger.INSTANCE;
                logger2.e("AlertState " + model.getAlertState());
                return;
            case 654786679:
                break;
            case 970664341:
                break;
            case 1066474995:
                if (alertState.equals(AppConstants.Notification_UpdateAlert)) {
                    return;
                }
                Logger logger22 = Logger.INSTANCE;
                logger22.e("AlertState " + model.getAlertState());
                return;
            case 1881199922:
                break;
            case 2026364241:
                if (alertState.equals(AppConstants.Notification_NoMobileNetworkAlert)) {
                    return;
                }
                Logger logger222 = Logger.INSTANCE;
                logger222.e("AlertState " + model.getAlertState());
                return;
            default:
                Logger logger2222 = Logger.INSTANCE;
                logger2222.e("AlertState " + model.getAlertState());
                return;
        }
    }

    @Override // com.psa.mym.mycitroenconnect.controller.dialog.OnDialogOkClickListener
    public void onDialogOkClick(@Nullable Dialog dialog, @NotNull MyNotificationModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (dialog != null) {
            dialog.dismiss();
        }
        if (Intrinsics.areEqual(model.getAlertState(), AppConstants.Notification_StolenVehicleAlert)) {
            callHelpLineNumber();
            return;
        }
        Logger logger = Logger.INSTANCE;
        logger.e("AlertState " + model.getAlertState());
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.NavDrawerListInterface
    public void onItemClick(@NotNull NavItem navItem) {
        Fragment feedbackFragment;
        WebViewFragment.Companion companion;
        String str;
        Intrinsics.checkNotNullParameter(navItem, "navItem");
        ((NavigationView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.navView)).setVisibility(8);
        String menuName = navItem.getMenuName();
        if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_sos))) {
            hideNavBar();
            feedbackFragment = new SOSFragment();
        } else if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_roadside_assistance))) {
            hideNavBar();
            feedbackFragment = new RoadSideAssistanceFragment();
        } else if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_e_call))) {
            hideNavBar();
            feedbackFragment = new ECallFragment();
        } else if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_notification))) {
            navigateToNotificationSettings();
            return;
        } else if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_security))) {
            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getResources().getString(R.string.label_security_settings));
            feedbackFragment = SecurityFragment.Companion.newInstance(true);
        } else if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_about_citroen))) {
            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getResources().getString(R.string.menu_title_about_citroen));
            feedbackFragment = new AboutCitroenFragment();
        } else if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_app_info))) {
            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getResources().getString(R.string.menu_title_app_info));
            feedbackFragment = new AppInfoFragment();
        } else {
            if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_privacy_policy))) {
                ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getResources().getString(R.string.menu_title_privacy_policy));
                companion = WebViewFragment.Companion;
                str = AppConstants.PRIVACY_POLICY_URL;
            } else if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_terms_n_conditions))) {
                ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getResources().getString(R.string.menu_title_terms_n_conditions));
                companion = WebViewFragment.Companion;
                str = AppConstants.TERMS_CONDITION_URL;
            } else if (!Intrinsics.areEqual(menuName, getString(R.string.menu_title_feedback))) {
                return;
            } else {
                feedbackFragment = new FeedbackFragment();
            }
            feedbackFragment = companion.newInstance(str);
        }
        replaceFragment$default(this, feedbackFragment, false, 2, null);
    }

    @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener, com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        Fragment carFragment;
        Intrinsics.checkNotNullParameter(item, "item");
        switch (item.getItemId()) {
            case R.id.menuCar /* 2131362591 */:
                ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getResources().getString(R.string.label_my_car));
                carFragment = new CarFragment();
                break;
            case R.id.menuHome /* 2131362594 */:
                navigateToHomeFragment();
                changeBackGround(true);
                return true;
            case R.id.menuProfile /* 2131362602 */:
                ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getResources().getString(R.string.profile));
                carFragment = new ProfileFragment();
                break;
            case R.id.menuTrip /* 2131362609 */:
                ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvTitle)).setText(getResources().getString(R.string.my_trips));
                carFragment = new TripFragment();
                break;
            default:
                return false;
        }
        replaceFragment$default(this, carFragment, false, 2, null);
        changeBackGround(false);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            setCarCurrentPage();
            ((NavigationView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.navView)).setVisibility(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            if (ExtensionsKt.isFirstPage(viewPager)) {
                setLeftArrowEnable(false);
            } else {
                setLeftArrowEnable(true);
            }
            if (ExtensionsKt.isLastPage(viewPager)) {
                setRightArrowEnable(false);
            } else {
                setRightArrowEnable(true);
            }
        }
    }

    public final void onQuickControlSelection(@NotNull String selection) {
        Intrinsics.checkNotNullParameter(selection, "selection");
        Fragment fragment = this.currentFragment;
        Fragment fragment2 = null;
        if (fragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
            fragment = null;
        }
        if (Intrinsics.areEqual(ExtensionsKt.getName(fragment), ExtensionsKt.getName(new HomeFragment()))) {
            if (!Intrinsics.areEqual(selection, getString(R.string.label_geo_fence))) {
                if (Intrinsics.areEqual(selection, getString(R.string.label_valet_mode))) {
                    Fragment fragment3 = this.currentFragment;
                    if (fragment3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                    } else {
                        fragment2 = fragment3;
                    }
                    ((HomeFragment) fragment2).getGeoFenceData(AppConstants.GEO_FENCE_VALET);
                    changeBackGround(true);
                    return;
                }
                if (!Intrinsics.areEqual(selection, getString(R.string.label_locate_car))) {
                    if (!(Intrinsics.areEqual(selection, getString(R.string.label_charging_station)) ? true : Intrinsics.areEqual(selection, getString(R.string.lbl_fuel_station)))) {
                        return;
                    }
                    hideBottomAppBar();
                    navigateToChargingStationFragment();
                }
                navigateToLocateFragment();
            }
            navigateToGeoFenceList();
        } else {
            if (!Intrinsics.areEqual(selection, getString(R.string.label_geo_fence))) {
                if (!Intrinsics.areEqual(selection, getString(R.string.label_locate_car))) {
                    if (!(Intrinsics.areEqual(selection, getString(R.string.label_charging_station)) ? true : Intrinsics.areEqual(selection, getString(R.string.lbl_fuel_station)))) {
                        setDashboardTitle();
                        int i2 = com.psa.mym.mycitroenconnect.R.id.bottomNavView;
                        Menu menu = ((BottomNavigationView) _$_findCachedViewById(i2)).getMenu();
                        Intrinsics.checkNotNullExpressionValue(menu, "bottomNavView.menu");
                        unCheckAllMenuItems(menu);
                        ((BottomNavigationView) _$_findCachedViewById(i2)).getMenu().getItem(0).setChecked(true);
                        showNavBar();
                        AppUtil.Companion companion = AppUtil.Companion;
                        FragmentContainerView fragment_container = (FragmentContainerView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.fragment_container);
                        Intrinsics.checkNotNullExpressionValue(fragment_container, "fragment_container");
                        companion.setMargins(fragment_container, 0, 0, 0, ExtensionsKt.getPx(86));
                        HomeFragment homeFragment = new HomeFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("login", selection);
                        homeFragment.setArguments(bundle);
                        replaceFragment$default(this, homeFragment, false, 2, null);
                        changeBackGround(true);
                        return;
                    }
                    hideBottomAppBar();
                    navigateToChargingStationFragment();
                }
                navigateToLocateFragment();
            }
            navigateToGeoFenceList();
        }
        changeBackGround(false);
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnInvitationView
    public void onRejected() {
        apiPermission();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        SharedPref.Companion.setSession(this, true);
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(@Nullable SharedPreferences sharedPreferences, @Nullable String str) {
        if (str == null || !Intrinsics.areEqual(str, getString(R.string.spVin))) {
            return;
        }
        getUnreadNotificationCount();
    }

    public final void replaceFragment(@NotNull Fragment fragment, boolean z) {
        StringBuilder sb;
        String str;
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        if (isCurrentFragment(fragment)) {
            return;
        }
        this.currentFragment = fragment;
        Logger logger = Logger.INSTANCE;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(ExtensionsKt.getName(fragment));
        sb2.append("CURRENT_FRAGMENT:");
        Fragment fragment2 = this.currentFragment;
        if (fragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
            fragment2 = null;
        }
        sb2.append(fragment2);
        logger.i(sb2.toString());
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "supportFragmentManager.beginTransaction()");
        beginTransaction.replace(R.id.fragment_container, fragment);
        String name = ExtensionsKt.getName(fragment);
        if (z) {
            beginTransaction.addToBackStack(ExtensionsKt.getName(fragment));
            sb = new StringBuilder();
            sb.append(ExtensionsKt.getName(fragment));
            sb.append("Fragment Back Stack : ");
            sb.append(name);
            str = " Added";
        } else {
            sb = new StringBuilder();
            sb.append(ExtensionsKt.getName(fragment));
            sb.append("Fragment Back Stack : ");
            sb.append(name);
            str = " Not Added";
        }
        sb.append(str);
        logger.d(sb.toString());
        beginTransaction.commit();
    }

    public final void showHeader() {
        _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.mainAppHeader).setVisibility(0);
    }

    public final void showNavBar() {
        ((BottomNavigationView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.bottomNavView)).setVisibility(0);
        _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.mainAppHeader).setVisibility(0);
        _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.main_bottom_app_bar).setVisibility(0);
    }
}
