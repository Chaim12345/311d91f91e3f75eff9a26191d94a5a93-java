package com.psa.mym.mycitroenconnect.controller.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.SpeedSettingFragment;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.MessageEvent;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.notification_settings.NotificationSettings;
import com.psa.mym.mycitroenconnect.model.notification_settings.Settings;
import com.psa.mym.mycitroenconnect.services.FCMService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class NotificationSettingsFragment extends BusBaseFragment implements View.OnClickListener {
    @NotNull
    public static final String AC_IDLING_ALERT = "ACIdlingAlert";
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String SPEED_ALERT = "SpeedAlert";
    @Nullable
    private String mDlgMode;
    @Nullable
    private NotificationSettings notificationSettings;
    private Skeleton skeleton;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int speedLimitTime = 10;
    private int acIdlingTime = 5;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final NotificationSettingsFragment newInstance() {
            NotificationSettingsFragment notificationSettingsFragment = new NotificationSettingsFragment();
            notificationSettingsFragment.setArguments(new Bundle());
            return notificationSettingsFragment;
        }
    }

    static /* synthetic */ void a(NotificationSettingsFragment notificationSettingsFragment, String str, String str2, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            str2 = "";
        }
        if ((i3 & 4) != 0) {
            i2 = -1;
        }
        notificationSettingsFragment.saveNotificationSettings(str, str2, i2);
    }

    private final void createNotificationSettings() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        FCMService fCMService = new FCMService();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        NotificationSettings notificationSettings = this.notificationSettings;
        Intrinsics.checkNotNull(notificationSettings);
        fCMService.createNotificationSettings(requireContext2, notificationSettings);
    }

    private final void displayData() {
        if (this.skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
        }
        Skeleton skeleton = this.skeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
            skeleton = null;
        }
        ExtensionsKt.showData(skeleton, 100L);
    }

    private final void displayLoading() {
        if (!isAdded() || getActivity() == null || getActivity() == null) {
            return;
        }
        ConstraintLayout clSettings = (ConstraintLayout) _$_findCachedViewById(R.id.clSettings);
        Intrinsics.checkNotNullExpressionValue(clSettings, "clSettings");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(clSettings, ExtensionsKt.skeletonConfig(requireActivity));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    private final void getNotificationSettings(boolean z) {
        if (z) {
            displayLoading();
        }
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String vinNumber = companion.getVinNumber(requireContext);
        FCMService fCMService = new FCMService();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        fCMService.getNotificationSettings(requireContext2, vinNumber);
    }

    private final int getSettingIndex(String str) {
        ArrayList<Settings> settings;
        NotificationSettings notificationSettings = this.notificationSettings;
        if (notificationSettings == null || (settings = notificationSettings.getSettings()) == null) {
            return -1;
        }
        int i2 = 0;
        for (Object obj : settings) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            String alertType = ((Settings) obj).getAlertType();
            Locale locale = Locale.ROOT;
            String lowerCase = alertType.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            String lowerCase2 = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
                return i2;
            }
            i2 = i3;
        }
        return -1;
    }

    private final void initViews() {
        setDefaultValues();
    }

    @JvmStatic
    @NotNull
    public static final NotificationSettingsFragment newInstance() {
        return Companion.newInstance();
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void saveNotificationSettings(String str, String str2, int i2) {
        int i3;
        String str3;
        Settings settings;
        String str4;
        NotificationSettings notificationSettings;
        ArrayList<Settings> arrayListOf;
        ArrayList arrayListOf2;
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        if (i2 != -1) {
            calendar.add(12, i2);
        } else {
            if (Intrinsics.areEqual(str, "SpeedAlert")) {
                i3 = 10;
            } else {
                i3 = Intrinsics.areEqual(str, "ACIdlingAlert") ? 5 : 5;
            }
            calendar.add(12, i3);
        }
        long timeInMillis2 = calendar.getTimeInMillis();
        String str5 = str2.length() == 0 ? ExifInterface.GPS_MEASUREMENT_IN_PROGRESS : str2;
        Settings settings2 = new Settings(str, str5, i2 + " mins", timeInMillis, timeInMillis2);
        if (i2 != -1) {
            StringBuilder sb = new StringBuilder();
            sb.append(i2);
            str3 = " mins";
            sb.append(str3);
            str4 = sb.toString();
            settings = settings2;
        } else {
            str3 = " mins";
            settings = settings2;
            if (!Intrinsics.areEqual(str, "SpeedAlert")) {
                if (Intrinsics.areEqual(str, "ACIdlingAlert")) {
                    str4 = "5 mins";
                }
                notificationSettings = this.notificationSettings;
                if (notificationSettings != null) {
                    SharedPref.Companion companion = SharedPref.Companion;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    String vinNumber = companion.getVinNumber(requireContext);
                    arrayListOf2 = CollectionsKt__CollectionsKt.arrayListOf(settings);
                    this.notificationSettings = new NotificationSettings("null", vinNumber, arrayListOf2);
                    createNotificationSettings();
                    return;
                }
                if (notificationSettings != null) {
                    if (!notificationSettings.getSettings().isEmpty()) {
                        int settingIndex = getSettingIndex(str);
                        Logger logger = Logger.INSTANCE;
                        logger.e("Position: " + settingIndex);
                        ArrayList<Settings> settings3 = notificationSettings.getSettings();
                        if (settingIndex != -1) {
                            Settings settings4 = settings3.get(settingIndex);
                            Settings settings5 = settings4;
                            if (str2.length() > 0) {
                                settings5.setAlertStatus(str2);
                            }
                            if (i2 != -1) {
                                settings5.setSnoozePeriod(i2 + str3);
                                settings5.setStartTimeStamp(timeInMillis);
                                settings5.setEndTimeStamp(timeInMillis2);
                            }
                            Intrinsics.checkNotNullExpressionValue(settings4, "{\n                      …  }\n                    }");
                        } else {
                            settings3.add(settings);
                        }
                    } else {
                        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(settings);
                        notificationSettings.setSettings(arrayListOf);
                        Unit unit = Unit.INSTANCE;
                    }
                }
                Logger logger2 = Logger.INSTANCE;
                logger2.e("Update Notification Settings: " + this.notificationSettings);
                updateNotificationSettings();
                return;
            }
            str4 = "10 mins";
        }
        settings.setSnoozePeriod(str4);
        notificationSettings = this.notificationSettings;
        if (notificationSettings != null) {
        }
    }

    private final void setACIdlingAlertText(String str) {
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvACIdlingAlertTime)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.ac_idling_alert_time, str));
    }

    private final void setDefaultValues() {
        setMutedForText("10");
        ((SwitchMaterial) _$_findCachedViewById(R.id.switchSpeedLimit)).setChecked(true);
        setACIdlingAlertText("5");
        ((SwitchMaterial) _$_findCachedViewById(R.id.switchACIdling)).setChecked(true);
        showSpeedLimitTimeOption();
        showACIdlingTimeOption();
    }

    private final void setListener() {
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isGuestUser(requireContext)) {
            ((SwitchMaterial) _$_findCachedViewById(R.id.switchSpeedLimit)).setClickable(false);
            ((SwitchMaterial) _$_findCachedViewById(R.id.switchACIdling)).setClickable(false);
            return;
        }
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvMutedFor)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivEditACIdlingTime)).setOnClickListener(this);
        int i2 = R.id.switchACIdling;
        ((SwitchMaterial) _$_findCachedViewById(i2)).setOnClickListener(this);
        int i3 = R.id.switchSpeedLimit;
        ((SwitchMaterial) _$_findCachedViewById(i3)).setOnClickListener(this);
        ((SwitchMaterial) _$_findCachedViewById(i3)).setClickable(true);
        ((SwitchMaterial) _$_findCachedViewById(i2)).setClickable(true);
    }

    private final void setMutedForText(String str) {
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvMutedFor)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.speed_limit_muted_for, str));
    }

    private final void showACIdlingTimeOption() {
        AppCompatTextView tvACIdlingAlertTime = (AppCompatTextView) _$_findCachedViewById(R.id.tvACIdlingAlertTime);
        Intrinsics.checkNotNullExpressionValue(tvACIdlingAlertTime, "tvACIdlingAlertTime");
        ExtensionsKt.hide(tvACIdlingAlertTime);
        AppCompatImageView ivEditACIdlingTime = (AppCompatImageView) _$_findCachedViewById(R.id.ivEditACIdlingTime);
        Intrinsics.checkNotNullExpressionValue(ivEditACIdlingTime, "ivEditACIdlingTime");
        ExtensionsKt.hide(ivEditACIdlingTime);
    }

    private final void showSpeedLimitTimeOption() {
        if (((SwitchMaterial) _$_findCachedViewById(R.id.switchSpeedLimit)).isChecked()) {
            MaterialCardView cvSpeedLimit = (MaterialCardView) _$_findCachedViewById(R.id.cvSpeedLimit);
            Intrinsics.checkNotNullExpressionValue(cvSpeedLimit, "cvSpeedLimit");
            ExtensionsKt.hide(cvSpeedLimit);
            return;
        }
        MaterialCardView cvSpeedLimit2 = (MaterialCardView) _$_findCachedViewById(R.id.cvSpeedLimit);
        Intrinsics.checkNotNullExpressionValue(cvSpeedLimit2, "cvSpeedLimit");
        ExtensionsKt.show(cvSpeedLimit2);
    }

    private final void showTimePicker(String str, String str2) {
        this.mDlgMode = str2;
        SpeedSettingFragment speedSettingFragment = new SpeedSettingFragment();
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.SPEED_SELECTED_VAL, str);
        bundle.putString("login", str2);
        speedSettingFragment.setArguments(bundle);
        speedSettingFragment.show(getChildFragmentManager(), SpeedSettingFragment.TAG);
        speedSettingFragment.setCancelable(false);
    }

    private final void updateNotificationSettings() {
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.showDialog(requireContext);
        SharedPref.Companion companion2 = SharedPref.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String vinNumber = companion2.getVinNumber(requireContext2);
        FCMService fCMService = new FCMService();
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        NotificationSettings notificationSettings = this.notificationSettings;
        Intrinsics.checkNotNull(notificationSettings);
        fCMService.updateNotificationSettings(requireContext3, vinNumber, notificationSettings);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
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

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
        if (r0.equals("UpdateNotificationSettings") == false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r0.equals("CreateNotificationSettings") == false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006c, code lost:
        com.psa.mym.mycitroenconnect.utils.AppUtil.Companion.dismissDialog();
     */
    @Subscribe
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void getResponse(@NotNull ErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        String apiName = response.getApiName();
        int hashCode = apiName.hashCode();
        if (hashCode != -1272731286) {
            if (hashCode != -787916617) {
                if (hashCode == 1077551748 && apiName.equals("GetNotificationSettings")) {
                    displayData();
                    if (response.getStatusCode() == 204) {
                        return;
                    }
                }
            }
            Logger logger = Logger.INSTANCE;
            logger.e("API Name: " + response.getApiName() + " & Message: " + response.getMsg());
            return;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, response.getMsg());
    }

    @Subscribe
    public final void getResponse(@NotNull PostCommonResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        String apiName = response.getApiName();
        if (Intrinsics.areEqual(apiName, "CreateNotificationSettings")) {
            AppUtil.Companion.dismissDialog();
            getNotificationSettings(false);
        } else if (!Intrinsics.areEqual(apiName, "UpdateNotificationSettings")) {
            Logger logger = Logger.INSTANCE;
            logger.e("API Name: " + response.getApiName() + " & Message: " + response.getMessage());
            return;
        } else {
            AppUtil.Companion.dismissDialog();
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, response.getMessage());
    }

    @Subscribe
    public final void getResponse(@NotNull NotificationSettings response) {
        String replace$default;
        boolean startsWith$default;
        CharSequence trim;
        String replace$default2;
        String replace$default3;
        boolean startsWith$default2;
        CharSequence trim2;
        String replace$default4;
        Intrinsics.checkNotNullParameter(response, "response");
        this.notificationSettings = response;
        for (Settings settings : response.getSettings()) {
            String alertType = settings.getAlertType();
            if (Intrinsics.areEqual(alertType, "ACIdlingAlert")) {
                ((SwitchMaterial) _$_findCachedViewById(R.id.switchACIdling)).setChecked(Intrinsics.areEqual(settings.getAlertStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS));
                replace$default = StringsKt__StringsJVMKt.replace$default(settings.getSnoozePeriod(), " mins", "", false, 4, (Object) null);
                startsWith$default = StringsKt__StringsJVMKt.startsWith$default(replace$default, "0", false, 2, null);
                if (startsWith$default) {
                    replace$default2 = StringsKt__StringsJVMKt.replace$default(replace$default, "0", "", false, 4, (Object) null);
                    trim = StringsKt__StringsKt.trim((CharSequence) replace$default2);
                } else {
                    trim = StringsKt__StringsKt.trim((CharSequence) replace$default);
                }
                int parseInt = Integer.parseInt(trim.toString());
                this.acIdlingTime = parseInt;
                setACIdlingAlertText(String.valueOf(parseInt));
            } else if (Intrinsics.areEqual(alertType, "SpeedAlert")) {
                ((SwitchMaterial) _$_findCachedViewById(R.id.switchSpeedLimit)).setChecked(Intrinsics.areEqual(settings.getAlertStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS));
                replace$default3 = StringsKt__StringsJVMKt.replace$default(settings.getSnoozePeriod(), " mins", "", false, 4, (Object) null);
                startsWith$default2 = StringsKt__StringsJVMKt.startsWith$default(replace$default3, "0", false, 2, null);
                if (startsWith$default2) {
                    replace$default4 = StringsKt__StringsJVMKt.replace$default(replace$default3, "0", "", false, 4, (Object) null);
                    trim2 = StringsKt__StringsKt.trim((CharSequence) replace$default4);
                } else {
                    trim2 = StringsKt__StringsKt.trim((CharSequence) replace$default3);
                }
                int parseInt2 = Integer.parseInt(trim2.toString());
                this.speedLimitTime = parseInt2;
                setMutedForText(String.valueOf(parseInt2));
            }
        }
        showSpeedLimitTimeOption();
        showACIdlingTimeOption();
        displayData();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        String fenceStatus;
        int i2;
        int i3;
        Object obj;
        String str;
        String valueOf;
        String str2;
        if (Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvMutedFor))) {
            valueOf = String.valueOf(this.speedLimitTime);
            str2 = AppConstants.SPEED_LIMIT_MODE;
        } else if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivEditACIdlingTime))) {
            int i4 = R.id.switchSpeedLimit;
            if (Intrinsics.areEqual(view, (SwitchMaterial) _$_findCachedViewById(i4))) {
                showSpeedLimitTimeOption();
                SwitchMaterial switchSpeedLimit = (SwitchMaterial) _$_findCachedViewById(i4);
                Intrinsics.checkNotNullExpressionValue(switchSpeedLimit, "switchSpeedLimit");
                fenceStatus = ExtensionsKt.getFenceStatus(switchSpeedLimit);
                i2 = 0;
                i3 = 4;
                obj = null;
                str = "SpeedAlert";
            } else {
                int i5 = R.id.switchACIdling;
                if (!Intrinsics.areEqual(view, (SwitchMaterial) _$_findCachedViewById(i5))) {
                    return;
                }
                showACIdlingTimeOption();
                SwitchMaterial switchACIdling = (SwitchMaterial) _$_findCachedViewById(i5);
                Intrinsics.checkNotNullExpressionValue(switchACIdling, "switchACIdling");
                fenceStatus = ExtensionsKt.getFenceStatus(switchACIdling);
                i2 = 0;
                i3 = 4;
                obj = null;
                str = "ACIdlingAlert";
            }
            a(this, str, fenceStatus, i2, i3, obj);
            return;
        } else {
            valueOf = String.valueOf(this.acIdlingTime);
            str2 = AppConstants.AC_IDLING_MODE;
        }
        showTimePicker(valueOf, str2);
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_notification_settings, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(@NotNull MessageEvent event) {
        boolean isBlank;
        boolean contains$default;
        String replace$default;
        CharSequence trim;
        String str;
        int i2;
        int i3;
        Object obj;
        String str2;
        Intrinsics.checkNotNullParameter(event, "event");
        isBlank = StringsKt__StringsJVMKt.isBlank(event.getMessage());
        if (!isBlank) {
            if (!(event.getMessage().length() > 0) || Intrinsics.areEqual(event.getMessage(), AppConstants.DEFAULT_VAL_SPEED_DLG)) {
                return;
            }
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) event.getMessage(), (CharSequence) "Mins", false, 2, (Object) null);
            if (contains$default) {
                replace$default = StringsKt__StringsJVMKt.replace$default(event.getMessage(), "Mins", "", false, 4, (Object) null);
                trim = StringsKt__StringsKt.trim((CharSequence) replace$default);
                String obj2 = trim.toString();
                String str3 = this.mDlgMode;
                if (Intrinsics.areEqual(str3, AppConstants.SPEED_LIMIT_MODE)) {
                    this.speedLimitTime = Integer.parseInt(obj2);
                    setMutedForText(obj2);
                    str = null;
                    i2 = this.speedLimitTime;
                    i3 = 2;
                    obj = null;
                    str2 = "SpeedAlert";
                } else if (!Intrinsics.areEqual(str3, AppConstants.AC_IDLING_MODE)) {
                    return;
                } else {
                    this.acIdlingTime = Integer.parseInt(obj2);
                    setACIdlingAlertText(obj2);
                    str = null;
                    i2 = this.acIdlingTime;
                    i3 = 2;
                    obj = null;
                    str2 = "ACIdlingAlert";
                }
                a(this, str2, str, i2, i3, obj);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initViews();
        setListener();
        getNotificationSettings(true);
    }
}
