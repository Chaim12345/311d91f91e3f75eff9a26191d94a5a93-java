package com.psa.mym.mycitroenconnect.controller.activities;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.NonVinMainActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.NavDrawerAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.NavDrawerListInterface;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ChildAccountInvitationDialog;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ExitFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.LogoutFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnInvitationView;
import com.psa.mym.mycitroenconnect.controller.fragments.AboutCitroenFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.AppInfoFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.NotificationSettingsFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.SecurityFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.WebViewFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.guest_user.MyProfileFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.guest_user.NonVinHomeFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.guest_user.NotificationFragment;
import com.psa.mym.mycitroenconnect.fcm.UpdatedEvents;
import com.psa.mym.mycitroenconnect.model.NavItem;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCarResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCars;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUser;
import com.psa.mym.mycitroenconnect.model.secondary_user.SharedVehicle;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayout;
import com.rd.PageIndicatorView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class NonVinMainActivity extends BusBaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener, NavDrawerListInterface, OnInvitationView {
    private Fragment currentFragment;
    private boolean doubleBackToExitPressedOnce;
    private boolean isInvitationAccepted;
    @Nullable
    private NavDrawerAdapter navAdapter;
    private UserProfileResponse userProfileResponse;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int currentStatusBarColor = -1;
    @NotNull
    private String mobileNumber = "";

    private final void apiPermission(String str) {
        boolean isBlank;
        isBlank = StringsKt__StringsJVMKt.isBlank(this.mobileNumber);
        if (!isBlank) {
            if (this.mobileNumber.length() > 0) {
                SecondaryUserService secondaryUserService = new SecondaryUserService();
                StringBuilder sb = new StringBuilder();
                sb.append("91");
                String str2 = this.mobileNumber;
                String substring = str2.substring(3, str2.length());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                sb.append(substring);
                secondaryUserService.getMyCarList(this, sb.toString(), AppConstants.SCREEN_NON_VIN_HOME);
            }
        }
    }

    static /* synthetic */ void c(NonVinMainActivity nonVinMainActivity, Fragment fragment, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        nonVinMainActivity.replaceFragment(fragment, z);
    }

    private final Pair<Boolean, SharedVehicle> checkForInvitation(MyCars myCars) {
        Iterator<MyCar> it = myCars.iterator();
        while (true) {
            String str = null;
            if (!it.hasNext()) {
                return new Pair<>(Boolean.FALSE, null);
            }
            MyCar next = it.next();
            next.setVehicleImage(Integer.valueOf((int) R.drawable.citroen_nav_bar_car));
            next.setVehicleSelected(false);
            String str2 = this.mobileNumber;
            String substring = str2.substring(1, str2.length());
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            if (!Intrinsics.areEqual(substring, next.getAccountId())) {
                next.setAccessible(true);
                next.setViewOnly(true);
                if (next.getInviteStatus() != null && Intrinsics.areEqual(next.getInviteStatus(), AppConstants.SECONDARY_USER_STATE_PENDING)) {
                    String carModelName = next.getCarModelName();
                    String ownerName = next.getOwnerName();
                    String vehicleRegNo = next.getVehicleRegNo();
                    String vinNum = next.getVinNum();
                    String accountId = next.getAccountId();
                    if (accountId != null) {
                        str = accountId.substring(2, next.getAccountId().length());
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    return new Pair<>(Boolean.TRUE, new SharedVehicle(carModelName, ownerName, vehicleRegNo, vinNum, "+91", str, R.drawable.citroen_nav_bar_car));
                }
            }
        }
    }

    private final void displayTitleUI() {
        TextView tvNonVinTitle = (TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvNonVinTitle);
        Intrinsics.checkNotNullExpressionValue(tvNonVinTitle, "tvNonVinTitle");
        ExtensionsKt.show(tvNonVinTitle);
        AppUtil.Companion companion = AppUtil.Companion;
        FragmentContainerView fragmentContainerNonVin = (FragmentContainerView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.fragmentContainerNonVin);
        Intrinsics.checkNotNullExpressionValue(fragmentContainerNonVin, "fragmentContainerNonVin");
        companion.setMargins(fragmentContainerNonVin, 0, ExtensionsKt.getPx(50), 0, 0);
    }

    private final void exitByBackKey() {
        ExitFragment exitFragment = new ExitFragment();
        exitFragment.show(getSupportFragmentManager(), ExitFragment.TAG);
        exitFragment.setCancelable(false);
    }

    private final void goToMainDashBoard() {
        SharedPref.Companion companion = SharedPref.Companion;
        companion.setIsGuestUser(this, "true");
        companion.setIsVerifiedUser(this, "true");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private final void hideTitleUI() {
        TextView tvNonVinTitle = (TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvNonVinTitle);
        Intrinsics.checkNotNullExpressionValue(tvNonVinTitle, "tvNonVinTitle");
        ExtensionsKt.hide(tvNonVinTitle);
        AppUtil.Companion companion = AppUtil.Companion;
        FragmentContainerView fragmentContainerNonVin = (FragmentContainerView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.fragmentContainerNonVin);
        Intrinsics.checkNotNullExpressionValue(fragmentContainerNonVin, "fragmentContainerNonVin");
        companion.setMargins(fragmentContainerNonVin, 0, 0, 0, 0);
    }

    private final void initViews() {
        ((NavigationView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.navViewNonVin)).setNavigationItemSelectedListener(this);
        setSupportActionBar((Toolbar) findViewById(R.id.nonVinAppHeader));
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
            supportActionBar3.setDisplayHomeAsUpEnabled(true);
        }
        ActionBar supportActionBar4 = getSupportActionBar();
        if (supportActionBar4 != null) {
            supportActionBar4.setHomeAsUpIndicator(R.drawable.ic_ham_menu);
        }
        setUpNavView();
    }

    private final void navigateToNonVinHomeFragment() {
        hideTitleUI();
        c(this, new NonVinHomeFragment(), false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onBackPressed$lambda-1  reason: not valid java name */
    public static final void m82onBackPressed$lambda1(NonVinMainActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doubleBackToExitPressedOnce = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m83onCreate$lambda0(NonVinMainActivity this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.apiPermission(it);
    }

    private final void replaceFragment(Fragment fragment, boolean z) {
        StringBuilder sb;
        String str;
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
        beginTransaction.replace(R.id.fragmentContainerNonVin, fragment);
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

    private final void setListener() {
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnNotif)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnNavSignOut)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivNavCloseNonVin)).setOnClickListener(this);
    }

    private final void setUpNavView() {
        ArrayList arrayListOf;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        String string = getString(R.string.menu_sub_title_e_settings);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.menu_sub_title_e_settings)");
        String string2 = getString(R.string.menu_title_security);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.menu_title_security)");
        String string3 = getString(R.string.menu_sub_title_others);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.menu_sub_title_others)");
        String string4 = getString(R.string.menu_title_about_citroen);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.menu_title_about_citroen)");
        String string5 = getString(R.string.menu_title_app_info);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.menu_title_app_info)");
        String string6 = getString(R.string.menu_title_privacy_policy);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.menu_title_privacy_policy)");
        String string7 = getString(R.string.menu_title_terms_n_conditions);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.menu_title_terms_n_conditions)");
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new NavItem(string, 0, true), new NavItem(string2, R.drawable.ic_security, false), new NavItem(string3, 0, true), new NavItem(string4, R.drawable.ic_citroen_red, false), new NavItem(string5, R.drawable.ic_app_info, false), new NavItem(string6, R.drawable.ic_privacy_policy, false), new NavItem(string7, R.drawable.ic_terms_n_condition, false));
        this.navAdapter = new NavDrawerAdapter(this, arrayListOf, this);
        int i2 = com.psa.mym.mycitroenconnect.R.id.rvNavViewNonVin;
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(linearLayoutManager);
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(this.navAdapter);
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
    public final void getResponse(@NotNull MyCarResponse myCarResponse) {
        String str;
        Intrinsics.checkNotNullParameter(myCarResponse, "myCarResponse");
        AppUtil.Companion.dismissDialog();
        if (Intrinsics.areEqual(myCarResponse.getScreenName(), AppConstants.SCREEN_NON_VIN_HOME)) {
            MyCars myCars = myCarResponse.getMyCars();
            boolean z = true;
            if (myCars == null || myCars.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            if (myCars.size() > 0) {
                String userType = myCars.get(0).getUserType();
                if (userType != null) {
                    str = userType.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                } else {
                    str = null;
                }
                if (Intrinsics.areEqual(str, "g")) {
                    Token token = myCars.get(0).getToken();
                    if (token != null) {
                        SharedPref.Companion.setSecondaryUserTokenDetails(this, token);
                    }
                    myCars.remove(0);
                }
            }
            SharedPref.Companion.setVinTokenDetails(this, myCars);
            Pair<Boolean, SharedVehicle> checkForInvitation = checkForInvitation(myCars);
            boolean booleanValue = checkForInvitation.component1().booleanValue();
            SharedVehicle component2 = checkForInvitation.component2();
            this.isInvitationAccepted = (!this.isInvitationAccepted || booleanValue) ? false : false;
            if (component2 != null) {
                arrayList.add(component2);
            }
            for (MyCar myCar : myCars) {
                if (this.isInvitationAccepted) {
                    Token token2 = myCar.getToken();
                    if (token2 != null) {
                        SharedPref.Companion.setPrimaryUserTokenDetails(this, token2);
                    }
                    String vinNum = myCar.getVinNum();
                    if (vinNum != null) {
                        SharedPref.Companion.setVinNumber(this, vinNum);
                    }
                }
            }
            if (this.isInvitationAccepted) {
                Object systemService = getApplicationContext().getSystemService("notification");
                Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                ((NotificationManager) systemService).cancel(Integer.parseInt(AppConstants.CHILD_INVITE_NOTI_ID));
                goToMainDashBoard();
            } else if (booleanValue) {
                ChildAccountInvitationDialog newInstance = ChildAccountInvitationDialog.Companion.newInstance(new SecondaryUser("", "", "", arrayList));
                newInstance.setOnInvitationView(this);
                newInstance.show(getSupportFragmentManager(), ChildAccountInvitationDialog.TAG);
                newInstance.setCancelable(false);
            }
        }
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnInvitationView
    public void onAccepted() {
        this.isInvitationAccepted = true;
        apiPermission("Accepted");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Fragment fragment = this.currentFragment;
        Fragment fragment2 = null;
        if (fragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
            fragment = null;
        }
        if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment), ExtensionsKt.getName(new SecurityFragment()))) {
            Fragment fragment3 = this.currentFragment;
            if (fragment3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                fragment3 = null;
            }
            if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment3), ExtensionsKt.getName(new AboutCitroenFragment()))) {
                Fragment fragment4 = this.currentFragment;
                if (fragment4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                    fragment4 = null;
                }
                if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment4), ExtensionsKt.getName(new AppInfoFragment()))) {
                    Fragment fragment5 = this.currentFragment;
                    if (fragment5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("currentFragment");
                    } else {
                        fragment2 = fragment5;
                    }
                    if (!Intrinsics.areEqual(ExtensionsKt.getName(fragment2), ExtensionsKt.getName(new WebViewFragment()))) {
                        FragmentManager supportFragmentManager = getSupportFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                        int backStackEntryCount = supportFragmentManager.getBackStackEntryCount() - 1;
                        for (int i2 = 0; i2 < backStackEntryCount; i2++) {
                            supportFragmentManager.popBackStack();
                        }
                        int i3 = com.psa.mym.mycitroenconnect.R.id.navViewNonVin;
                        NavigationView navViewNonVin = (NavigationView) _$_findCachedViewById(i3);
                        Intrinsics.checkNotNullExpressionValue(navViewNonVin, "navViewNonVin");
                        if (ExtensionsKt.isVisible(navViewNonVin)) {
                            NavigationView navViewNonVin2 = (NavigationView) _$_findCachedViewById(i3);
                            Intrinsics.checkNotNullExpressionValue(navViewNonVin2, "navViewNonVin");
                            ExtensionsKt.hide(navViewNonVin2);
                            return;
                        } else if (this.doubleBackToExitPressedOnce) {
                            exitByBackKey();
                            return;
                        } else {
                            this.doubleBackToExitPressedOnce = true;
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: i.o
                                @Override // java.lang.Runnable
                                public final void run() {
                                    NonVinMainActivity.m82onBackPressed$lambda1(NonVinMainActivity.this);
                                }
                            }, SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS);
                            return;
                        }
                    }
                }
            }
        }
        hideTitleUI();
        navigateToNonVinHomeFragment();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnNotif))) {
            int i2 = com.psa.mym.mycitroenconnect.R.id.tvBadgeNotifCount;
            int visibility = ((AppCompatTextView) _$_findCachedViewById(i2)).getVisibility();
            AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i2);
            if (visibility == 0) {
                appCompatTextView.setVisibility(8);
            } else {
                appCompatTextView.setVisibility(0);
            }
        } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivNavCloseNonVin))) {
            NavigationView navViewNonVin = (NavigationView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.navViewNonVin);
            Intrinsics.checkNotNullExpressionValue(navViewNonVin, "navViewNonVin");
            ExtensionsKt.hide(navViewNonVin);
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnNavSignOut))) {
            LogoutFragment logoutFragment = new LogoutFragment();
            logoutFragment.show(getSupportFragmentManager(), LogoutFragment.TAG);
            logoutFragment.setCancelable(false);
        }
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnInvitationView
    public void onClosed() {
        apiPermission("closed");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_non_vin_main);
        setListener();
        initViews();
        navigateToNonVinHomeFragment();
        UpdatedEvents.INSTANCE.getChildInviteEvent().observe(this, new Observer() { // from class: i.n
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NonVinMainActivity.m83onCreate$lambda0(NonVinMainActivity.this, (String) obj);
            }
        });
        SharedPref.Companion companion = SharedPref.Companion;
        UserProfileResponse userProfileResponse = companion.getUserProfileResponse(this);
        this.userProfileResponse = userProfileResponse;
        UserProfileResponse userProfileResponse2 = null;
        if (userProfileResponse == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userProfileResponse");
            userProfileResponse = null;
        }
        userProfileResponse.setRegisteredVehicle(null);
        UserProfileResponse userProfileResponse3 = this.userProfileResponse;
        if (userProfileResponse3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userProfileResponse");
        } else {
            userProfileResponse2 = userProfileResponse3;
        }
        companion.setUserProfileResponse(this, userProfileResponse2);
        this.currentStatusBarColor = getWindow().getStatusBarColor();
        getWindow().setStatusBarColor(ContextCompat.getColor(this, 17170445));
        this.mobileNumber = companion.getMobileNumber(this);
        apiPermission("");
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.NavDrawerListInterface
    public void onItemClick(@NotNull NavItem navItem) {
        WebViewFragment.Companion companion;
        String str;
        Fragment newInstance;
        Intrinsics.checkNotNullParameter(navItem, "navItem");
        ((NavigationView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.navViewNonVin)).setVisibility(8);
        displayTitleUI();
        String menuName = navItem.getMenuName();
        if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_home))) {
            hideTitleUI();
            navigateToNonVinHomeFragment();
            return;
        }
        if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_notification))) {
            ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvNonVinTitle)).setText(getResources().getString(R.string.label_notification_settings));
            newInstance = new NotificationSettingsFragment();
        } else if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_security))) {
            ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvNonVinTitle)).setText(getResources().getString(R.string.label_security_settings));
            newInstance = SecurityFragment.Companion.newInstance(false);
        } else if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_about_citroen))) {
            ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvNonVinTitle)).setText(getResources().getString(R.string.menu_title_about_citroen));
            newInstance = new AboutCitroenFragment();
        } else if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_app_info))) {
            ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvNonVinTitle)).setText(getResources().getString(R.string.menu_title_app_info));
            newInstance = new AppInfoFragment();
        } else {
            if (Intrinsics.areEqual(menuName, getString(R.string.menu_title_privacy_policy))) {
                ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvNonVinTitle)).setText(getResources().getString(R.string.menu_title_privacy_policy));
                companion = WebViewFragment.Companion;
                str = AppConstants.PRIVACY_POLICY_URL;
            } else if (!Intrinsics.areEqual(menuName, getString(R.string.menu_title_terms_n_conditions))) {
                return;
            } else {
                displayTitleUI();
                ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvNonVinTitle)).setText(getResources().getString(R.string.menu_title_terms_n_conditions));
                companion = WebViewFragment.Companion;
                str = AppConstants.TERMS_CONDITION_URL;
            }
            newInstance = companion.newInstance(str);
        }
        c(this, newInstance, false, 2, null);
    }

    @Override // com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        Fragment aboutCitroenFragment;
        WebViewFragment.Companion companion;
        String str;
        Intrinsics.checkNotNullParameter(item, "item");
        item.setChecked(true);
        ((TextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvNonVinTitle)).setText(item.getTitle());
        ((DrawerLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.drawerLayoutNonVin)).closeDrawers();
        NavigationView navViewNonVin = (NavigationView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.navViewNonVin);
        Intrinsics.checkNotNullExpressionValue(navViewNonVin, "navViewNonVin");
        ExtensionsKt.hide(navViewNonVin);
        switch (item.getItemId()) {
            case R.id.menuAboutCitroenNv /* 2131362588 */:
                displayTitleUI();
                aboutCitroenFragment = new AboutCitroenFragment();
                break;
            case R.id.menuAppInfoNv /* 2131362590 */:
                displayTitleUI();
                aboutCitroenFragment = new AppInfoFragment();
                break;
            case R.id.menuHomeNv /* 2131362595 */:
                hideTitleUI();
                aboutCitroenFragment = new NonVinHomeFragment();
                break;
            case R.id.menuMyProfileNv /* 2131362596 */:
                displayTitleUI();
                aboutCitroenFragment = new MyProfileFragment();
                break;
            case R.id.menuNotifNv /* 2131362598 */:
                displayTitleUI();
                aboutCitroenFragment = new NotificationFragment();
                break;
            case R.id.menuPrivacyPolicyNv /* 2131362601 */:
                displayTitleUI();
                companion = WebViewFragment.Companion;
                str = AppConstants.PRIVACY_POLICY_URL;
                aboutCitroenFragment = companion.newInstance(str);
                break;
            case R.id.menuSecurityNv /* 2131362605 */:
                displayTitleUI();
                aboutCitroenFragment = SecurityFragment.Companion.newInstance(false);
                break;
            case R.id.menuTermsNconditionsNv /* 2131362608 */:
                displayTitleUI();
                companion = WebViewFragment.Companion;
                str = AppConstants.TERMS_CONDITION_URL;
                aboutCitroenFragment = companion.newInstance(str);
                break;
            default:
                return false;
        }
        c(this, aboutCitroenFragment, false, 2, null);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            NavigationView navViewNonVin = (NavigationView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.navViewNonVin);
            Intrinsics.checkNotNullExpressionValue(navViewNonVin, "navViewNonVin");
            ExtensionsKt.show(navViewNonVin);
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
        PageIndicatorView pageIndicatorView = (PageIndicatorView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.pageIndicatorViewNonVin);
        if (pageIndicatorView == null) {
            return;
        }
        pageIndicatorView.setSelection(i2);
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnInvitationView
    public void onRejected() {
        apiPermission("Rejected");
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        getWindow().setStatusBarColor(this.currentStatusBarColor);
    }
}
