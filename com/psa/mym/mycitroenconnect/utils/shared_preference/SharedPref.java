package com.psa.mym.mycitroenconnect.utils.shared_preference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.LoginActivity;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.VinToken;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import com.psa.mym.mycitroenconnect.model.dashboard.StoredDashboardData;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.onboarding.UpdateUserProfileResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.Logger;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class SharedPref {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final String getRefreshCommandTime(Context context) {
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spRefreshCommandTime);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spRefreshCommandTime)");
            return sPUtil.getString(context, string);
        }

        private final String getTokenExpireTimeString(int i2) {
            AppUtil.Companion companion = AppUtil.Companion;
            Calendar calendar = Calendar.getInstance(companion.getDefaultLocale());
            calendar.add(13, i2 - 120);
            Logger.INSTANCE.e(String.valueOf(calendar));
            Date time = calendar.getTime();
            Intrinsics.checkNotNullExpressionValue(time, "calendar.time");
            return companion.getDateString(time, AppConstants.TOKEN_DATE_FORMAT);
        }

        private final ArrayList<VinToken> getVinTokenDetails(Context context) {
            boolean isBlank;
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spVinTokenList);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spVinTokenList)");
            String string2 = sPUtil.getString(context, string);
            Type type = new TypeToken<ArrayList<VinToken>>() { // from class: com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref$Companion$getVinTokenDetails$type$1
            }.getType();
            isBlank = StringsKt__StringsJVMKt.isBlank(string2);
            if (!isBlank) {
                if (!(string2.length() == 0)) {
                    Object fromJson = new Gson().fromJson(string2, type);
                    Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(json, type)");
                    return (ArrayList) fromJson;
                }
            }
            return new ArrayList<>();
        }

        public final void addChildContact(@NotNull Context context, @NotNull EmergencyDetailsItem childAccount) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(childAccount, "childAccount");
            ArrayList<EmergencyDetailsItem> loadChildContacts = loadChildContacts(context);
            loadChildContacts.add(childAccount);
            saveChildContacts(loadChildContacts, context);
        }

        public final void deleteChildContact(@NotNull Context context, @NotNull EmergencyDetailsItem objContact) {
            Object obj;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(objContact, "objContact");
            ArrayList<EmergencyDetailsItem> loadChildContacts = loadChildContacts(context);
            Iterator<T> it = loadChildContacts.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((EmergencyDetailsItem) obj).getId(), objContact.getId())) {
                    break;
                }
            }
            TypeIntrinsics.asMutableCollection(loadChildContacts).remove((EmergencyDetailsItem) obj);
            saveChildContacts(loadChildContacts, context);
        }

        public final void deleteEmergencyContact(@NotNull Context context, @NotNull EmergencyDetailsItem objContact) {
            Object obj;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(objContact, "objContact");
            ArrayList<EmergencyDetailsItem> loadEmergencyContacts = loadEmergencyContacts(context);
            Iterator<T> it = loadEmergencyContacts.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((EmergencyDetailsItem) obj).getId(), objContact.getId())) {
                    break;
                }
            }
            TypeIntrinsics.asMutableCollection(loadEmergencyContacts).remove((EmergencyDetailsItem) obj);
            saveEmergencyContacts(loadEmergencyContacts, context);
        }

        public final void editChildContact(@NotNull Context context, @NotNull EmergencyDetailsItem objContact) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(objContact, "objContact");
            ArrayList<EmergencyDetailsItem> loadChildContacts = loadChildContacts(context);
            ArrayList<EmergencyDetailsItem> arrayList = new ArrayList();
            for (Object obj : loadChildContacts) {
                if (Intrinsics.areEqual(((EmergencyDetailsItem) obj).getId(), objContact.getId())) {
                    arrayList.add(obj);
                }
            }
            for (EmergencyDetailsItem emergencyDetailsItem : arrayList) {
                emergencyDetailsItem.setId(objContact.getId());
                emergencyDetailsItem.setName(objContact.getName());
                emergencyDetailsItem.setContactNum(objContact.getContactNum());
                emergencyDetailsItem.setContactCity(objContact.getContactCity());
                emergencyDetailsItem.setContactStreet(objContact.getContactStreet());
                emergencyDetailsItem.setViewType(objContact.getViewType());
                emergencyDetailsItem.setCountryCode(objContact.getCountryCode());
                emergencyDetailsItem.setChildContact(objContact.isChildContact());
                emergencyDetailsItem.setStatus(objContact.getStatus());
                emergencyDetailsItem.setCarAccess(objContact.getCarAccess());
            }
            saveChildContacts(loadChildContacts, context);
        }

        public final void editEmergencyContact(@NotNull Context context, @NotNull EmergencyDetailsItem objContact) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(objContact, "objContact");
            ArrayList<EmergencyDetailsItem> loadEmergencyContacts = loadEmergencyContacts(context);
            ArrayList<EmergencyDetailsItem> arrayList = new ArrayList();
            for (Object obj : loadEmergencyContacts) {
                if (Intrinsics.areEqual(((EmergencyDetailsItem) obj).getId(), objContact.getId())) {
                    arrayList.add(obj);
                }
            }
            for (EmergencyDetailsItem emergencyDetailsItem : arrayList) {
                emergencyDetailsItem.setEmergencyContact(objContact.isEmergencyContact());
                emergencyDetailsItem.setName(objContact.getName());
                emergencyDetailsItem.setContactNum(objContact.getContactNum());
                emergencyDetailsItem.setContactCity(objContact.getContactCity());
                emergencyDetailsItem.setContactStreet(objContact.getContactStreet());
                emergencyDetailsItem.setViewType(objContact.getViewType());
                emergencyDetailsItem.setId(objContact.getId());
            }
            saveEmergencyContacts(loadEmergencyContacts, context);
        }

        @NotNull
        public final String getAppPin(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spAppPin);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spAppPin)");
            return sPUtil.getString(context, string);
        }

        @NotNull
        public final String getCarTitle(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String vinNumber = getVinNumber(context);
            ArrayList<VinToken> vinTokenDetails = getVinTokenDetails(context);
            ArrayList arrayList = new ArrayList();
            for (Object obj : vinTokenDetails) {
                if (Intrinsics.areEqual(((VinToken) obj).getVinNumber(), vinNumber)) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            return it.hasNext() ? String.valueOf(((VinToken) it.next()).getCarModelName()) : "";
        }

        public final int getCountItemSelected(@NotNull ArrayList<EmergencyDetailsItem> contactList) {
            Intrinsics.checkNotNullParameter(contactList, "contactList");
            ArrayList arrayList = new ArrayList();
            for (Object obj : contactList) {
                if (((EmergencyDetailsItem) obj).isEmergencyContact()) {
                    arrayList.add(obj);
                }
            }
            return arrayList.size();
        }

        @NotNull
        public final String getIgnitionState(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return SPUtil.INSTANCE.getString(context, "IgnitionState");
        }

        public final long getLastFCMTime(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                return Long.parseLong(SPUtil.INSTANCE.getString(context, "fcm_time"));
            } catch (Exception unused) {
                return 0L;
            }
        }

        @NotNull
        public final String getMobileNumber(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spMobile);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spMobile)");
            return sPUtil.getString(context, string);
        }

        @NotNull
        public final String getPrimaryUserAuthToken(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            StringBuilder sb = new StringBuilder();
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spTokenType);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spTokenType)");
            sb.append(sPUtil.getString(context, string));
            sb.append(TokenParser.SP);
            sb.append(getPrimaryUserToken(context));
            return sb.toString();
        }

        @NotNull
        public final String getPrimaryUserRefreshToken(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spRefreshToken);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spRefreshToken)");
            return sPUtil.getString(context, string);
        }

        @NotNull
        public final String getPrimaryUserToken(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spToken);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spToken)");
            return sPUtil.getString(context, string);
        }

        @NotNull
        public final String getPrimaryUserTokenHash(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spTokenHash);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spTokenHash)");
            return sPUtil.getString(context, string);
        }

        @NotNull
        public final String getProfilePic(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spProfilePic);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spProfilePic)");
            return sPUtil.getString(context, string);
        }

        @NotNull
        public final ArrayList<RegisteredVehicleItem> getRegisteredVehicleList(@NotNull Context context) {
            boolean isBlank;
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spRegisteredVehicleList);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri….spRegisteredVehicleList)");
            String string2 = sPUtil.getString(context, string);
            Type type = new TypeToken<ArrayList<RegisteredVehicleItem>>() { // from class: com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref$Companion$getRegisteredVehicleList$type$1
            }.getType();
            isBlank = StringsKt__StringsJVMKt.isBlank(string2);
            if (!isBlank) {
                if (!(string2.length() == 0)) {
                    Object fromJson = new Gson().fromJson(string2, type);
                    Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(json, type)");
                    return (ArrayList) fromJson;
                }
            }
            return new ArrayList<>();
        }

        @Nullable
        public final StoredDashboardData getSaveDashboardData(@NotNull Context context) {
            boolean isBlank;
            Intrinsics.checkNotNullParameter(context, "context");
            String string = SPUtil.INSTANCE.getString(context, "dashboard_data");
            Type type = new TypeToken<StoredDashboardData>() { // from class: com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref$Companion$getSaveDashboardData$type$1
            }.getType();
            isBlank = StringsKt__StringsJVMKt.isBlank(string);
            if (!isBlank) {
                if (!(string.length() == 0)) {
                    return (StoredDashboardData) new Gson().fromJson(string, type);
                }
            }
            return null;
        }

        @NotNull
        public final String getSecondaryUserAuthToken(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            StringBuilder sb = new StringBuilder();
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spGuestTokenType);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spGuestTokenType)");
            sb.append(sPUtil.getString(context, string));
            sb.append(TokenParser.SP);
            String string2 = context.getString(R.string.spGuestUserToken);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.spGuestUserToken)");
            sb.append(sPUtil.getString(context, string2));
            return sb.toString();
        }

        @NotNull
        public final String getSecondaryUserToken(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spGuestUserToken);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spGuestUserToken)");
            return sPUtil.getString(context, string);
        }

        public final int getSelectChildAccountCount(@NotNull ArrayList<EmergencyDetailsItem> contactList) {
            Intrinsics.checkNotNullParameter(contactList, "contactList");
            ArrayList arrayList = new ArrayList();
            for (Object obj : contactList) {
                if (((EmergencyDetailsItem) obj).isChildContact()) {
                    arrayList.add(obj);
                }
            }
            return arrayList.size();
        }

        @NotNull
        public final HashMap<String, String> getTokenMap(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String userToken = getUserToken(context);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("Authorization", "Bearer " + userToken);
            return hashMap;
        }

        @NotNull
        public final String getUserFirstName(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spUserFirstName);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spUserFirstName)");
            return sPUtil.getString(context, string);
        }

        @NotNull
        public final UserProfileResponse getUserProfileResponse(@NotNull Context context) {
            boolean isBlank;
            UserProfileResponse userProfileResponse;
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spUserInfo);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spUserInfo)");
            String string2 = sPUtil.getString(context, string);
            if (string2 != null) {
                if (string2.length() > 0) {
                    isBlank = StringsKt__StringsJVMKt.isBlank(string2);
                    if (!isBlank) {
                        try {
                            userProfileResponse = (UserProfileResponse) new Gson().fromJson(string2, (Class<Object>) UserProfileResponse.class);
                        } catch (Exception e2) {
                            Logger logger = Logger.INSTANCE;
                            e2.printStackTrace();
                            logger.e(String.valueOf(Unit.INSTANCE));
                            userProfileResponse = new UserProfileResponse(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
                        }
                        Intrinsics.checkNotNullExpressionValue(userProfileResponse, "{\n                try {\n…          }\n            }");
                        return userProfileResponse;
                    }
                }
            }
            return new UserProfileResponse(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
        }

        @NotNull
        public final String getUserToken(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return (!isGuestUser(context) || isPrimaryUser(context)) ? getPrimaryUserToken(context) : getSecondaryUserToken(context);
        }

        @NotNull
        public final String getVehicleType(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spVehicleType);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spVehicleType)");
            return sPUtil.getString(context, string);
        }

        @NotNull
        public final String getVinNumber(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spVin);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spVin)");
            return sPUtil.getString(context, string);
        }

        public final boolean isFingerPrintAuth(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spIsFingerprintAuth);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spIsFingerprintAuth)");
            return Intrinsics.areEqual(sPUtil.getString(context, string), "True");
        }

        public final boolean isGuestUser(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spIsGuestUser);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spIsGuestUser)");
            return Intrinsics.areEqual(sPUtil.getString(context, string), "true");
        }

        public final boolean isLogin(@NotNull Context context) {
            boolean equals;
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spIsLogin);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spIsLogin)");
            equals = StringsKt__StringsJVMKt.equals(sPUtil.getString(context, string), "true", true);
            return equals;
        }

        public final boolean isPrimaryUser(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spIsPrimaryUser);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spIsPrimaryUser)");
            return Intrinsics.areEqual(sPUtil.getString(context, string), "true");
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x001a, code lost:
            if ((r8.length() == 0) != false) goto L7;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean isRefreshCommand(@NotNull Context context) {
            boolean isBlank;
            Intrinsics.checkNotNullParameter(context, "context");
            String refreshCommandTime = getRefreshCommandTime(context);
            isBlank = StringsKt__StringsJVMKt.isBlank(refreshCommandTime);
            if (isBlank) {
            }
            if (Math.abs(System.currentTimeMillis() - Long.parseLong(refreshCommandTime)) < TimeUnit.MINUTES.toMillis(15L)) {
                return false;
            }
            return true;
        }

        public final boolean isValidSecondaryUserToken(@NotNull Context context) {
            Date date;
            boolean isBlank;
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spGuestUserTokenCreationTime);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…estUserTokenCreationTime)");
            String string2 = sPUtil.getString(context, string);
            String string3 = context.getString(R.string.spGuestUserTokenExpireTime);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.stri…GuestUserTokenExpireTime)");
            String string4 = sPUtil.getString(context, string3);
            Logger logger = Logger.INSTANCE;
            logger.e("Guest User Token Expire Date: " + string4);
            String string5 = context.getString(R.string.spGuestUserExpireIn);
            Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.spGuestUserExpireIn)");
            String string6 = sPUtil.getString(context, string5);
            try {
                date = AppUtil.Companion.parseToDate(string2);
            } catch (ParseException e2) {
                e2.printStackTrace();
                date = null;
            }
            if (date == null) {
                return false;
            }
            if (string6.length() == 0) {
                return false;
            }
            isBlank = StringsKt__StringsJVMKt.isBlank(string6);
            if (isBlank) {
                return false;
            }
            AppUtil.Companion companion = AppUtil.Companion;
            Calendar calendar = Calendar.getInstance(companion.getDefaultLocale());
            calendar.setTimeInMillis(new Date().getTime());
            Logger logger2 = Logger.INSTANCE;
            logger2.e("Current Time:" + calendar.getTimeInMillis());
            Calendar calendar2 = Calendar.getInstance(companion.getDefaultLocale());
            calendar2.setTimeInMillis(date.getTime());
            calendar2.add(13, Integer.parseInt(string6) + (-60));
            logger2.e("Token Time:" + calendar2.getTimeInMillis());
            return calendar2.getTimeInMillis() - calendar.getTimeInMillis() > 0;
        }

        public final boolean isValidToken(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spTokenCreationTime);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spTokenCreationTime)");
            String string2 = sPUtil.getString(context, string);
            String string3 = context.getString(R.string.spTokenExpireTime);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.spTokenExpireTime)");
            String string4 = sPUtil.getString(context, string3);
            Logger logger = Logger.INSTANCE;
            logger.e("Token Expire Date: " + string4);
            String string5 = context.getString(R.string.spExpireIn);
            Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.spExpireIn)");
            sPUtil.getString(context, string5);
            try {
                AppUtil.Companion.parseToDate(string2);
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
            Date date = null;
            try {
                date = AppUtil.Companion.parseToDate(string4);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            if (date == null) {
                return false;
            }
            Calendar calendar = Calendar.getInstance(AppUtil.Companion.getDefaultLocale());
            calendar.setTimeInMillis(date.getTime());
            return calendar.getTimeInMillis() - System.currentTimeMillis() > 0;
        }

        public final boolean isVerifiedUser(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spIsVerified);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spIsVerified)");
            return Intrinsics.areEqual(sPUtil.getString(context, string), "true");
        }

        @NotNull
        public final ArrayList<EmergencyDetailsItem> loadChildContacts(@NotNull Context context) {
            boolean isBlank;
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spChildContactList);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spChildContactList)");
            String string2 = sPUtil.getString(context, string);
            Type type = new TypeToken<ArrayList<EmergencyDetailsItem>>() { // from class: com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref$Companion$loadChildContacts$type$1
            }.getType();
            isBlank = StringsKt__StringsJVMKt.isBlank(string2);
            if (!isBlank) {
                if (!(string2.length() == 0)) {
                    Object fromJson = new Gson().fromJson(string2, type);
                    Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(json, type)");
                    return (ArrayList) fromJson;
                }
            }
            return new ArrayList<>();
        }

        @NotNull
        public final ArrayList<EmergencyDetailsItem> loadEmergencyContacts(@NotNull Context context) {
            boolean isBlank;
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spEmergencyContactList);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spEmergencyContactList)");
            String string2 = sPUtil.getString(context, string);
            Type type = new TypeToken<ArrayList<EmergencyDetailsItem>>() { // from class: com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref$Companion$loadEmergencyContacts$type$1
            }.getType();
            isBlank = StringsKt__StringsJVMKt.isBlank(string2);
            if (!isBlank) {
                if (!(string2.length() == 0)) {
                    Object fromJson = new Gson().fromJson(string2, type);
                    Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(json, type)");
                    return (ArrayList) fromJson;
                }
            }
            return new ArrayList<>();
        }

        public final void logoutFromApp(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (AppUtil.Companion.isAppIsInBackground(context)) {
                logoutUser(context);
            } else {
                logoutUserOnScreen(context);
            }
        }

        public final void logoutUser(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spIsLogin);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spIsLogin)");
            sPUtil.setString(context, string, "False");
            String string2 = context.getString(R.string.spTokenCreationTime);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.spTokenCreationTime)");
            sPUtil.setString(context, string2, "");
            String string3 = context.getString(R.string.spTokenExpireTime);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.spTokenExpireTime)");
            sPUtil.setString(context, string3, "");
            String string4 = context.getString(R.string.spMobile);
            Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.spMobile)");
            sPUtil.setString(context, string4, "");
            String string5 = context.getString(R.string.spAppPin);
            Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.spAppPin)");
            sPUtil.setString(context, string5, "");
            String string6 = context.getString(R.string.spUserInfo);
            Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.spUserInfo)");
            sPUtil.setString(context, string6, "");
            sPUtil.clearALLPreference(context);
        }

        public final void logoutUserOnScreen(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            logoutUser(context);
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(268468224);
            intent.putExtra("login", "login");
            context.startActivity(intent);
            ((Activity) context).finish();
        }

        public final void removeChildContacts(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spChildContactList);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spChildContactList)");
            sPUtil.setString(context, string, "");
        }

        public final void removeEmergencyContacts(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spEmergencyContactList);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spEmergencyContactList)");
            sPUtil.setString(context, string, "");
        }

        public final void resetRefreshCommandTime(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spRefreshCommandTime);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spRefreshCommandTime)");
            sPUtil.setString(context, string, "");
        }

        public final void saveChildContacts(@NotNull List<EmergencyDetailsItem> contactList, @NotNull Context context) {
            Intrinsics.checkNotNullParameter(contactList, "contactList");
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spChildContactList);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spChildContactList)");
            String json = new Gson().toJson(contactList);
            Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(contactList)");
            sPUtil.setString(context, string, json);
        }

        public final void saveDashboardData(@NotNull Context context, @NotNull StoredDashboardData dashboardData) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(dashboardData, "dashboardData");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String json = new Gson().toJson(dashboardData);
            Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(dashboardData)");
            sPUtil.setString(context, "dashboard_data", json);
        }

        public final void saveEmergencyContacts(@NotNull List<EmergencyDetailsItem> contactList, @NotNull Context context) {
            Intrinsics.checkNotNullParameter(contactList, "contactList");
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spEmergencyContactList);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spEmergencyContactList)");
            String json = new Gson().toJson(contactList);
            Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(contactList)");
            sPUtil.setString(context, string, json);
        }

        public final void saveRegisteredVehicleList(@NotNull Context context, @NotNull List<RegisteredVehicleItem> registeredVehicle) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(registeredVehicle, "registeredVehicle");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spRegisteredVehicleList);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri….spRegisteredVehicleList)");
            String json = new Gson().toJson(registeredVehicle);
            Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(registeredVehicle)");
            sPUtil.setString(context, string, json);
        }

        public final void setAppPin(@NotNull Context context, @NotNull String appPin) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(appPin, "appPin");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spAppPin);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spAppPin)");
            sPUtil.setString(context, string, appPin);
        }

        public final void setIgnitionState(@NotNull Context context, @NotNull String ignitionStatus) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(ignitionStatus, "ignitionStatus");
            SPUtil.INSTANCE.setString(context, "IgnitionState", ignitionStatus);
        }

        public final void setIsFingerPrintAuth(@NotNull Context context, @NotNull String isFingerPrintAuth) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(isFingerPrintAuth, "isFingerPrintAuth");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spIsFingerprintAuth);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spIsFingerprintAuth)");
            sPUtil.setString(context, string, isFingerPrintAuth);
        }

        public final void setIsGuestUser(@NotNull Context context, @NotNull String isGuestUser) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(isGuestUser, "isGuestUser");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spIsGuestUser);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spIsGuestUser)");
            sPUtil.setString(context, string, isGuestUser);
        }

        public final void setIsLogin(@NotNull Context context, @NotNull String isLogin) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(isLogin, "isLogin");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spIsLogin);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spIsLogin)");
            sPUtil.setString(context, string, isLogin);
        }

        public final void setIsPrimaryUser(@NotNull Context context, @NotNull String isGuestUser) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(isGuestUser, "isGuestUser");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spIsPrimaryUser);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spIsPrimaryUser)");
            sPUtil.setString(context, string, isGuestUser);
        }

        public final void setIsVerifiedUser(@NotNull Context context, @NotNull String isVerifiedUser) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(isVerifiedUser, "isVerifiedUser");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spIsVerified);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spIsVerified)");
            sPUtil.setString(context, string, isVerifiedUser);
        }

        public final void setLastFCMTime(@NotNull Context context, long j2) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil.INSTANCE.setString(context, "fcm_time", String.valueOf(j2));
        }

        public final void setMobileNumber(@NotNull Context context, @NotNull String mobileNumber) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(mobileNumber, "mobileNumber");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spMobile);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spMobile)");
            sPUtil.setString(context, string, mobileNumber);
        }

        public final void setPrimaryUserToken(@NotNull Context context, @NotNull String token) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(token, "token");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spToken);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spToken)");
            sPUtil.setString(context, string, token);
        }

        public final void setPrimaryUserTokenDetails(@NotNull Context context, @NotNull Token token) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(token, "token");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spTokenCreationTime);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spTokenCreationTime)");
            sPUtil.setString(context, string, AppUtil.Companion.getCurrentTimeString());
            String string2 = context.getString(R.string.spTokenExpireTime);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.spTokenExpireTime)");
            Integer expiresIn = token.getExpiresIn();
            sPUtil.setString(context, string2, getTokenExpireTimeString(expiresIn != null ? expiresIn.intValue() : 0));
            String string3 = context.getString(R.string.spToken);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.spToken)");
            String accessToken = token.getAccessToken();
            if (accessToken == null) {
                accessToken = "";
            }
            sPUtil.setString(context, string3, accessToken);
            String string4 = context.getString(R.string.spGuestUserToken);
            Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.spGuestUserToken)");
            String accessToken2 = token.getAccessToken();
            if (accessToken2 == null) {
                accessToken2 = "";
            }
            sPUtil.setString(context, string4, accessToken2);
            String string5 = context.getString(R.string.spRefreshToken);
            Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.spRefreshToken)");
            String refreshToken = token.getRefreshToken();
            if (refreshToken == null) {
                refreshToken = "";
            }
            sPUtil.setString(context, string5, refreshToken);
            String string6 = context.getString(R.string.spExpireIn);
            Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.spExpireIn)");
            sPUtil.setString(context, string6, token.getExpiresIn() != null ? token.getExpiresIn().toString() : "");
            String string7 = context.getString(R.string.spTokenType);
            Intrinsics.checkNotNullExpressionValue(string7, "context.getString(R.string.spTokenType)");
            String tokenType = token.getTokenType();
            sPUtil.setString(context, string7, tokenType != null ? tokenType : "");
        }

        public final void setPrimaryUserTokenHash(@NotNull Context context, @NotNull String token) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(token, "token");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spTokenHash);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spTokenHash)");
            sPUtil.setString(context, string, token);
        }

        public final void setProfilePic(@NotNull Context context, @NotNull String profilePic) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(profilePic, "profilePic");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spProfilePic);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spProfilePic)");
            sPUtil.setString(context, string, profilePic);
        }

        public final void setRefreshCommandTime(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spRefreshCommandTime);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spRefreshCommandTime)");
            sPUtil.setString(context, string, String.valueOf(System.currentTimeMillis()));
        }

        public final void setSecondaryUserTokenDetails(@NotNull Context context, @NotNull Token token) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(token, "token");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spGuestUserTokenCreationTime);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…estUserTokenCreationTime)");
            sPUtil.setString(context, string, AppUtil.Companion.getCurrentTimeString());
            String string2 = context.getString(R.string.spGuestUserTokenExpireTime);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…GuestUserTokenExpireTime)");
            Companion companion = SharedPref.Companion;
            Integer expiresIn = token.getExpiresIn();
            sPUtil.setString(context, string2, companion.getTokenExpireTimeString(expiresIn != null ? expiresIn.intValue() : 0));
            if (token.getAccessToken() != null) {
                String accessToken = token.getAccessToken();
                String string3 = context.getString(R.string.spGuestUserToken);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.spGuestUserToken)");
                sPUtil.setString(context, string3, accessToken);
            }
            if (token.getRefreshToken() != null) {
                String refreshToken = token.getRefreshToken();
                String string4 = context.getString(R.string.spGuestUserRefreshToken);
                Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.stri….spGuestUserRefreshToken)");
                sPUtil.setString(context, string4, refreshToken);
            }
            if (token.getExpiresIn() != null) {
                int intValue = token.getExpiresIn().intValue();
                String string5 = context.getString(R.string.spGuestUserExpireIn);
                Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.spGuestUserExpireIn)");
                sPUtil.setString(context, string5, String.valueOf(intValue));
            }
            if (token.getTokenType() != null) {
                String tokenType = token.getTokenType();
                String string6 = context.getString(R.string.spGuestTokenType);
                Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.spGuestTokenType)");
                sPUtil.setString(context, string6, tokenType);
            }
        }

        public final void setSession(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            SPUtil.INSTANCE.setSession(context, z);
        }

        public final void setTokenDetails(@NotNull Context context, @NotNull String vinNumber) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(vinNumber, "vinNumber");
            ArrayList<VinToken> vinTokenDetails = getVinTokenDetails(context);
            ArrayList<VinToken> arrayList = new ArrayList();
            for (Object obj : vinTokenDetails) {
                if (Intrinsics.areEqual(((VinToken) obj).getVinNumber(), vinNumber)) {
                    arrayList.add(obj);
                }
            }
            for (VinToken vinToken : arrayList) {
                SPUtil sPUtil = SPUtil.INSTANCE;
                String string = context.getString(R.string.spVehicleType);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spVehicleType)");
                String vehicleType = vinToken.getVehicleType();
                if (vehicleType == null) {
                    vehicleType = "";
                }
                sPUtil.setString(context, string, vehicleType);
                String string2 = context.getString(R.string.spTokenCreationTime);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.spTokenCreationTime)");
                String createTime = vinToken.getCreateTime();
                if (createTime == null) {
                    createTime = "";
                }
                sPUtil.setString(context, string2, createTime);
                String string3 = context.getString(R.string.spTokenExpireTime);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.spTokenExpireTime)");
                String tokenExpireTime = vinToken.getTokenExpireTime();
                if (tokenExpireTime == null) {
                    tokenExpireTime = "";
                }
                sPUtil.setString(context, string3, tokenExpireTime);
                Token token = vinToken.getToken();
                if (token != null) {
                    String string4 = context.getString(R.string.spToken);
                    Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.spToken)");
                    String accessToken = token.getAccessToken();
                    if (accessToken == null) {
                        accessToken = "";
                    }
                    sPUtil.setString(context, string4, accessToken);
                    String string5 = context.getString(R.string.spRefreshToken);
                    Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.spRefreshToken)");
                    String refreshToken = token.getRefreshToken();
                    if (refreshToken == null) {
                        refreshToken = "";
                    }
                    sPUtil.setString(context, string5, refreshToken);
                    String string6 = context.getString(R.string.spExpireIn);
                    Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.spExpireIn)");
                    sPUtil.setString(context, string6, token.getExpiresIn() != null ? token.getExpiresIn().toString() : "");
                    String string7 = context.getString(R.string.spTokenType);
                    Intrinsics.checkNotNullExpressionValue(string7, "context.getString(R.string.spTokenType)");
                    String tokenType = token.getTokenType();
                    sPUtil.setString(context, string7, tokenType != null ? tokenType : "");
                }
            }
        }

        public final void setUpdateUserProfileResponse(@NotNull Context context, @NotNull UpdateUserProfileResponse userProfile) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(userProfile, "userProfile");
            String jsonObject = new Gson().toJson(userProfile);
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spUserInfo);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spUserInfo)");
            Intrinsics.checkNotNullExpressionValue(jsonObject, "jsonObject");
            sPUtil.setString(context, string, jsonObject);
        }

        public final void setUserFirstName(@NotNull Context context, @NotNull String fullName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fullName, "fullName");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spUserFirstName);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spUserFirstName)");
            sPUtil.setString(context, string, fullName);
        }

        public final void setUserProfileResponse(@NotNull Context context, @NotNull UserProfileResponse userProfile) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(userProfile, "userProfile");
            String jsonObject = new Gson().toJson(userProfile);
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spUserInfo);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spUserInfo)");
            Intrinsics.checkNotNullExpressionValue(jsonObject, "jsonObject");
            sPUtil.setString(context, string, jsonObject);
        }

        public final void setVehicleNumber(@NotNull Context context, @NotNull String vehicleNumber) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(vehicleNumber, "vehicleNumber");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spVehicleId);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spVehicleId)");
            sPUtil.setString(context, string, vehicleNumber);
        }

        public final void setVehicleType(@NotNull Context context, @NotNull String vehicleType) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(vehicleType, "vehicleType");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spVehicleType);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spVehicleType)");
            sPUtil.setString(context, string, vehicleType);
        }

        public final void setVinNumber(@NotNull Context context, @NotNull String vinNumber) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(vinNumber, "vinNumber");
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spVin);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spVin)");
            sPUtil.setString(context, string, vinNumber);
        }

        public final void setVinTokenDetails(@NotNull Context context, @NotNull ArrayList<MyCar> carList) {
            Integer expiresIn;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(carList, "carList");
            ArrayList arrayList = new ArrayList();
            for (MyCar myCar : carList) {
                VinToken vinToken = new VinToken(null, null, null, null, null, null, null, null, 255, null);
                vinToken.setVinNumber(myCar.getVinNum());
                vinToken.setCarModelName(myCar.getCarModelName());
                vinToken.setVehicleRegNo(myCar.getVehicleRegNo());
                vinToken.setVehicleType(myCar.getVehicleType());
                vinToken.setUserType(myCar.getUserType());
                vinToken.setCreateTime(AppUtil.Companion.getCurrentTimeString());
                Companion companion = SharedPref.Companion;
                Token token = myCar.getToken();
                vinToken.setTokenExpireTime(companion.getTokenExpireTimeString((token == null || (expiresIn = token.getExpiresIn()) == null) ? 0 : expiresIn.intValue()));
                vinToken.setToken(myCar.getToken());
                arrayList.add(vinToken);
            }
            SPUtil sPUtil = SPUtil.INSTANCE;
            String string = context.getString(R.string.spVinTokenList);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.spVinTokenList)");
            String json = new Gson().toJson(arrayList);
            Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(vinTokenList)");
            sPUtil.setString(context, string, json);
        }

        public final void updateVinTokenDetails(@NotNull Context context, @NotNull Token token) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(token, "token");
            String vinNumber = getVinNumber(context);
            ArrayList<VinToken> vinTokenDetails = getVinTokenDetails(context);
            ArrayList<VinToken> arrayList = new ArrayList();
            for (Object obj : vinTokenDetails) {
                if (Intrinsics.areEqual(((VinToken) obj).getVinNumber(), vinNumber)) {
                    arrayList.add(obj);
                }
            }
            for (VinToken vinToken : arrayList) {
                vinToken.setCreateTime(AppUtil.Companion.getCurrentTimeString());
                Companion companion = SharedPref.Companion;
                Integer expiresIn = token.getExpiresIn();
                vinToken.setTokenExpireTime(companion.getTokenExpireTimeString(expiresIn != null ? expiresIn.intValue() : 0));
                vinToken.setToken(token);
            }
        }
    }
}
