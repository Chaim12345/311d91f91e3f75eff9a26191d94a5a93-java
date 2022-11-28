package com.psa.mym.mycitroenconnect.common;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class AppConstants {
    @NotNull
    public static final String AC_IDLING_MODE = "ac_idling";
    @NotNull
    public static final String AC_STATUS_OFF = "AC_switch_off";
    @NotNull
    public static final String AC_STATUS_ON = "AC_switch_on";
    @NotNull
    public static final String ADD_CAR_BUNDLE_NAME = "current_add_car";
    @NotNull
    public static final String ANIM_IGNITION_OFF_1 = "anim_ignition_off_1";
    @NotNull
    public static final String ANIM_IGNITION_OFF_2 = "anim_ignition_off_2";
    @NotNull
    public static final String ANIM_IGNITION_ON_1 = "anim_ignition_on_1";
    @NotNull
    public static final String ANIM_TYPE_OFF = "off";
    @NotNull
    public static final String ANIM_TYPE_ON = "on";
    @NotNull
    public static final String API_GIVE_ACCESS_SECONDARY_USER = "giveAccessSecondaryUser";
    @NotNull
    public static final String API_NAME_ALL_TRIP_LIST = "trip_list";
    @NotNull
    public static final String API_NAME_DASHBOARD = "dashboard";
    @NotNull
    public static final String API_NAME_DELETE_SECONDARY_USER = "deleteSecondaryUser";
    @NotNull
    public static final String API_NAME_NOTIF_LIST = "NotificationList";
    @NotNull
    public static final String API_NAME_ONGOING = "ongoing";
    @NotNull
    public static final String API_NAME_SNAP_VEHICLE_STATUS = "SNAP_VEHICLE_STATUS";
    @NotNull
    public static final String API_NAME_TRIP_SUMMARY = "summary";
    @NotNull
    public static final String API_NAME_UPDATE_SECONDARY_USER_NAME = "updateSecondaryUserName";
    @NotNull
    public static final String API_NAME_UPDATE_TRIP_NAME = "updateTripName";
    @NotNull
    public static final String API_REVOKE_ACCESS_SECONDARY_USER = "revokeAccessSecondaryUser";
    @NotNull
    public static final String API_VEHICLE_LOCATION = "vehicleLocation";
    @NotNull
    public static final String ARG_CAR_DETAILS = "car_details";
    @NotNull
    public static final String ARG_CHARGING_STATION_DETAILS = "charging_station_details";
    @NotNull
    public static final String ARG_CONTACT = "contact";
    @NotNull
    public static final String ARG_CONTACT_ID = "contactId";
    @NotNull
    public static final String ARG_CONTACT_LIST = "contact_list";
    @NotNull
    public static final String ARG_CONTACT_NAME = "contactName";
    @NotNull
    public static final String ARG_IS_EDIT = "is_edit";
    @NotNull
    public static final String ARG_IS_FROM_MAIN_SCREEN = "isFromMainScreen";
    @NotNull
    public static final String ARG_IS_FROM_PUSH_NOTIF = "isFromPushNotif";
    @NotNull
    public static final String ARG_MESSAGE = "message";
    @NotNull
    public static final String ARG_PAGE_MODE = "page_mode";
    @NotNull
    public static final String ARG_POSITION = "position";
    @NotNull
    public static final String ARG_PUSH_NOTIF_ID = "pushNotifID";
    @NotNull
    public static final String ARG_PUSH_NOTIF_MODEL = "pushNotifModel";
    @NotNull
    public static final String ARG_STATUS_LIST = "arg_status_list";
    @NotNull
    public static final String ARG_SUCCESS_MESSAGE = "success_message";
    @NotNull
    public static final String ARG_TITLE = "title";
    @NotNull
    public static final String ARG_TRIP_SPINNER_SEL = "durationSpinnerSelection";
    @NotNull
    public static final String ARG_TRIP_SPINNER_SEL_END_DATE = "customSelectionEndDate";
    @NotNull
    public static final String ARG_TRIP_SPINNER_SEL_START_DATE = "customSelectionStartDate";
    @NotNull
    public static final String BASE_URL = "https://lb1.cvip-preprod.citroen.in:40543/";
    @NotNull
    public static final String BUNDLE_KEY_IS_ONGOING_TRIP = "isOnGoingTrip";
    @NotNull
    public static final String BUNDLE_KEY_ONGOING_TRIP_OBJ = "onGoingTripObj";
    @NotNull
    public static final String CAR_LOCATION = "car_location";
    @NotNull
    public static final String CHILD_ACCESS_REVOKE = "Child access revoke";
    public static final int CHILD_CONTACT = 2;
    public static final int CHILD_CONTACT_FROM_CONTACT = 3;
    @NotNull
    public static final String CHILD_INVITE = "Child invite received";
    @NotNull
    public static final String CHILD_INVITE_NOTI_ID = "101";
    @NotNull
    public static final String CHILD_REVOKE_NOTI_ID = "102";
    public static final int CREATE_GEO_FENCE = 5;
    @NotNull
    public static final String CURRENT_LOCATION = "current_location";
    @NotNull
    public static final String DATE_FORMAT_DD_MM_YY = "dd/MM/yy";
    @NotNull
    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    @NotNull
    public static final String DATE_FORMAT_DD_MM_YY_HH_mm_a = "dd-MM-yy hh:mm a";
    @NotNull
    public static final String DEFAULT_VAL_SPEED_DLG = "-1";
    public static final long DIALOG_AUTO_DISMISS_TIME = 5000;
    @NotNull
    public static final String DISPLAY_DATE_FORMAT = "dd MMM yyyy hh:mm a";
    @NotNull
    public static final String DISPLAY_DATE_FORMAT_WITHOUT_YEAR = "dd MMM hh:mm a";
    @NotNull
    public static final String DLG_MODE_TIME_SELECTOR = "time_settings";
    public static final int EDIT_GEO_FENCE = 6;
    public static final int EMERGENCY_CONTACT = 1;
    @NotNull
    public static final String EMERGENCY_CONTACT_BUNDLE_NAME = "emergency_contact";
    @NotNull
    public static final String EMERGENCY_CONTACT_LIST = "emergency_contact_list";
    @NotNull
    public static final String EV = "EV";
    @NotNull
    public static final String FLAVOR_DEV = "development";
    @NotNull
    public static final String FLAVOR_PREPROD = "preprod";
    @NotNull
    public static final String FLAVOR_PROD = "production";
    @NotNull
    public static final String FLAVOR_UAT = "uat";
    @NotNull
    public static final String FULLHASH = "full_hash";
    @NotNull
    public static final String GEO_FENCE = "geo_fence";
    @NotNull
    public static final String GEO_FENCE_GEOMETRY_CIRCLE = "CIRCLE";
    @NotNull
    public static final String GEO_FENCE_GEOMETRY_POLYGON = "POLYGON";
    @NotNull
    public static final String GEO_FENCE_LOCATION = "location";
    public static final int GEO_FENCE_MODE = 4;
    @NotNull
    public static final String GEO_FENCE_MODE_CUSTOM = "custom";
    @NotNull
    public static final String GEO_FENCE_MODE_RADIUS = "radius";
    @NotNull
    public static final String GEO_FENCE_MODE_ROUTE = "route";
    @NotNull
    public static final String GEO_FENCE_TIME = "time";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_FULL_DAY_ALL_DAYS = "full_day_all_days";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_FULL_DAY_CUSTOM_DATE_RANGE = "full_day_custom_date_range";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_FULL_DAY_CUSTOM_DAYS = "full_day_custom_days";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_FULL_DAY_WEEK_DAYS = "full_day_week_days";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_MULTIPLE_DAYS = "multiple_days";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_REPEAT = "repeat";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_REPEAT_CUSTOM = "repeat_custom";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_SET_TIME = "set_time";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_SET_TIME_ALL_DAYS = "set_time_all_days";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_SET_TIME_CUSTOM_DATE_RANGE = "set_time_custom_date_range";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_SET_TIME_CUSTOM_DAYS = "set_time_custom_days";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_SET_TIME_WEEK_DAYS = "set_time_week_days";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_SKIP = "skip";
    @NotNull
    public static final String GEO_FENCE_TIME_MODE_TODAY = "today";
    @NotNull
    public static final String GEO_FENCE_TRANSITION_BOTH = "INOUT";
    @NotNull
    public static final String GEO_FENCE_TRANSITION_IN = "IN";
    @NotNull
    public static final String GEO_FENCE_TRANSITION_OUT = "OUT";
    @NotNull
    public static final String GEO_FENCE_VALET = "valet";
    @NotNull
    public static final String HEADER_AUTH = "Authorization";
    @NotNull
    public static final String HELP_DESK = "7767003784";
    @NotNull
    public static final String ICE = "ICE";
    @NotNull
    public static final String IGNITION_STATUS_ACC = "ACC";
    @NotNull
    public static final String IGNITION_STATUS_CONTACT = "Contact";
    @NotNull
    public static final String IGNITION_STATUS_START = "Start";
    @NotNull
    public static final String IGNITION_STATUS_STOP = "Stop";
    @NotNull
    public static final String IS_FROM_DASHBOARD = "is_from_dashboard";
    @NotNull
    public static final String LAT_LNG = "latLng";
    public static final int MAXIMUM_CONTACT_SELECTION_COUNT = 2;
    @NotNull
    public static final String MM_DD_YY_FORMAT = "MMM dd, yyyy";
    @NotNull
    public static final String MOBILENUMBER = "MobileNumber";
    @NotNull
    public static final String Notification_ACIdlingAlert = "ACIdlingAlert";
    @NotNull
    public static final String Notification_CoDriverDoorOpenAlert = "CoDriverDoorOpenAlert";
    @NotNull
    public static final String Notification_CoDriverSeatbeltAlert = "CoDriverSeatbeltAlert";
    @NotNull
    public static final String Notification_CrashAlert = "CrashAlert";
    @NotNull
    public static final String Notification_DriverDoorOpenAlert = "DriverDoorOpenAlert";
    @NotNull
    public static final String Notification_DriverSeatBeltAlert = "DriverSeatBeltAlert";
    @NotNull
    public static final String Notification_EngineCoolantTempAlert = "EngineCoolantTempAlert";
    @NotNull
    public static final String Notification_EngineOilChangeAlert = "EngineOilChangeAlert";
    @NotNull
    public static final String Notification_GeoFenceAlert = "GeoFenceAlert";
    @NotNull
    public static final String Notification_IntrusionAlert = "IntrusionAlert";
    @NotNull
    public static final String Notification_LeftRearDoorOpenAlert = "LeftRearDoorOpenAlert";
    @NotNull
    public static final String Notification_LowFuelAlert = "LowFuelAlert";
    @NotNull
    public static final String Notification_MaintenanceAlert = "MaintenanceAlert";
    @NotNull
    public static final String Notification_NoMobileNetworkAlert = "NoMobileNetworkAlert";
    @NotNull
    public static final String Notification_RightRearDoorOpenAlert = "RightRearDoorOpenAlert";
    @NotNull
    public static final String Notification_SpeedAlert = "SpeedAlert";
    @NotNull
    public static final String Notification_StolenVehicleAlert = "StolenVehicleAlert";
    @NotNull
    public static final String Notification_TowAwayAlert = "TowAwayAlert";
    @NotNull
    public static final String Notification_UnauthorizedEntryAlert = "UnauthorizedEntryAlert";
    @NotNull
    public static final String Notification_UpdateAlert = "UpdateAlert";
    @NotNull
    public static final String Notification_ValetModeAlert = "ValetModeAlert";
    @NotNull
    public static final String ONGOING_TRIP_STATUS_IDLING = "Idle";
    @NotNull
    public static final String ONGOING_TRIP_STATUS_RUNNING = "Running";
    @NotNull
    public static final String ONLY_DATE_FORMAT = "yyyy-MM-dd";
    @NotNull
    public static final String PAGE_MODE = "login";
    @NotNull
    public static final String PAGE_MODE_ADD_ANOTHER_CAR = "add_another_car";
    @NotNull
    public static final String PAGE_MODE_ADD_CAR = "add_car";
    @NotNull
    public static final String PAGE_MODE_ADD_VEHICLE = "add_vehicle";
    @NotNull
    public static final String PAGE_MODE_CHANGE_PIN = "change_pin";
    @NotNull
    public static final String PAGE_MODE_CHANGE_PWD = "change_pwd";
    @NotNull
    public static final String PAGE_MODE_EDIT_EMERGENCY_CONTACT = "edit_emergency_contact";
    @NotNull
    public static final String PAGE_MODE_EDIT_PROFILE_DETAILS = "edit_profile_details";
    @NotNull
    public static final String PAGE_MODE_FORGOT_PIN = "forgot_pin";
    @NotNull
    public static final String PAGE_MODE_FORGOT_PWD = "forgot_pwd";
    @NotNull
    public static final String PAGE_MODE_GIVE_ACCESS = "give_access";
    @NotNull
    public static final String PAGE_MODE_LOGIN = "login";
    @NotNull
    public static final String PAGE_MODE_MAX_SPEED_SETTING = "max_speed_settings";
    @NotNull
    public static final String PAGE_MODE_PROFILE_DETAILS = "profile_details";
    @NotNull
    public static final String PAGE_MODE_RADIUS_SETTINGS = "radius_settings";
    @NotNull
    public static final String PAGE_MODE_REGISTRATION = "registration";
    @NotNull
    public static final String PAGE_MODE_REVOKE_ACCESS = "revoke_access";
    @NotNull
    public static final String PAGE_MODE_SPEED_SETTINGS = "speed_settings";
    @NotNull
    public static final String PAGE_MODE_VALET_MIN_SETTINGS = "valet_min_settings";
    @NotNull
    public static final String PAGE_MODE_VALET_RADIUS = "valet_radius_settings";
    @NotNull
    public static final String PICKER_TYPE_END = "end_picker";
    @NotNull
    public static final String PICKER_TYPE_START = "start_picker";
    @NotNull
    public static final String PRIVACY_POLICY_URL = "https://ac-mym.servicesgp.mpsa.com/webview/gdpr/privacy?culture=en-IN";
    @NotNull
    public static final String PROFILE_CAMERA = "CAMERA";
    @NotNull
    public static final String PROFILE_DELETE = "DELETE";
    @NotNull
    public static final String PROFILE_GALLERY = "GALLERY";
    @NotNull
    public static final String REGUSER = "reg_user";
    @NotNull
    public static final String REPEAT_TIME_1 = "08 – 08:30 am";
    @NotNull
    public static final String REPEAT_TIME_2 = "08:30 – 09 am";
    @NotNull
    public static final String REPEAT_TIME_3 = "09:30 – 10 am";
    @NotNull
    public static final String REPEAT_TIME_4 = "03 – 03:30 pm";
    @NotNull
    public static final String REPEAT_TIME_5 = "03:30 – 04 pm";
    @NotNull
    public static final String REPEAT_TIME_CUSTOM = "Custom";
    public static final int REQUEST_IMAGE = 100;
    public static final int REQ_ADD_FROM_CONTACT = 234;
    public static final int REQ_CONTACT_PERMISSION_SETTINGS = 987;
    public static final int REQ_TRIP_SPINNER_SELECTION = 678;
    @NotNull
    public static final String RESPONSE_UTC_DATE_FORMAT = "E MMM dd HH:mm:ss Z yyyy";
    @NotNull
    public static final String SCREEN_CONFIRM_CAR_DETAILS = "Confirm Car Details";
    @NotNull
    public static final String SCREEN_HOME = "Home";
    @NotNull
    public static final String SCREEN_MY_CARS = "My Cars";
    @NotNull
    public static final String SCREEN_NON_VIN_HOME = "Non Vin Home";
    @NotNull
    public static final String SCREEN_SET_NEW_PIN = "Set New Pin";
    @NotNull
    public static final String SECONDARY_USER_STATE_CANCELLED = "cancelled";
    @NotNull
    public static final String SECONDARY_USER_STATE_NA = "NA";
    @NotNull
    public static final String SECONDARY_USER_STATE_PENDING = "pending";
    @NotNull
    public static final String SECONDARY_USER_STATE_VERIFIED = "verified";
    @NotNull
    public static final String SENDOTPRESPONSE = "send_otp_response";
    @NotNull
    public static final String SERVER_DATE_FORMAT_WITHOUT_YEAR = "dd MMM";
    public static final int SET_GEO_FENCE = 7;
    @NotNull
    public static final String SHARED_PREFERENCE = "CVIP";
    @NotNull
    public static final String SPEED_LIMIT_MODE = "speed_limit";
    @NotNull
    public static final String SPEED_SELECTED_VAL = "selectedVal";
    public static final int SPLASH_TIME_OUT = 2000;
    @NotNull
    public static final String TERMS_CONDITION_URL = "https://ac-mym.servicesgp.mpsa.com/webview/cgu?culture=en-IN";
    @NotNull
    public static final String TIME_FORMAT_HH_mm = "HH:mm";
    @NotNull
    public static final String TIME_FORMAT_HH_mm_a = "hh:mm a";
    @NotNull
    public static final String TOKEN_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
    @NotNull
    public static final String TOKEN_HASH = "tokenHash";
    @NotNull
    public static final String UTC_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @NotNull
    public static final String VEHICLE_LOCKED = "Vehicle_locked";
    @NotNull
    public static final String VEHICLE_SUPER_LOCKED = "Vehicle_superlocked";
    @NotNull
    public static final String VEHICLE_UNLOCKED = "Vehicle_unlocked";
    private static boolean isDateChanged;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static String startDate = "";
    @NotNull
    private static String endDate = "";

    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getEndDate() {
            return AppConstants.endDate;
        }

        @NotNull
        public final String getStartDate() {
            return AppConstants.startDate;
        }

        public final boolean isDateChanged() {
            return AppConstants.isDateChanged;
        }

        public final void setDateChanged(boolean z) {
            AppConstants.isDateChanged = z;
        }

        public final void setEndDate(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            AppConstants.endDate = str;
        }

        public final void setStartDate(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            AppConstants.startDate = str;
        }
    }
}
