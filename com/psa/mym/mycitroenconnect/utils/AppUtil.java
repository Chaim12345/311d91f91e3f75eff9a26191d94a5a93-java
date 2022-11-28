package com.psa.mym.mycitroenconnect.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.hardware.biometrics.BiometricManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.DrawableRes;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.RoomDatabase;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.dialog.OnDialogOkClickListener;
import com.psa.mym.mycitroenconnect.controller.dialog.SingleCustomDialog;
import com.psa.mym.mycitroenconnect.model.MyNotificationModel;
import com.psa.mym.mycitroenconnect.model.geo_fence.LocationData;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonConfig;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayout;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonShimmerDirection;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Marker;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class AppUtil {
    @NotNull
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @NotNull
    private static final String MOBILE_REGEX = "^([0]|\\+91)?[789]\\d{9}$";
    @NotNull
    private static final String MOBILE_REGEX_VAL = "^[6-9][0-9]{9}$";
    @NotNull
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,25}$";
    @Nullable
    private static Dialog dialog;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Locale defaultLocale = new Locale("en", AppConstants.GEO_FENCE_TRANSITION_IN);

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final boolean isValid(String str, TextInputLayout textInputLayout, String str2, String str3) {
            int length = str.length() - 1;
            int i2 = 0;
            boolean z = false;
            while (i2 <= length) {
                boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i2 : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i2++;
                } else {
                    z = true;
                }
            }
            String obj = str.subSequence(i2, length + 1).toString();
            if (textInputLayout != null) {
                textInputLayout.setErrorEnabled(false);
            }
            if (textInputLayout != null) {
                textInputLayout.setError(null);
            }
            if (Pattern.matches(str2, obj)) {
                return true;
            }
            if (textInputLayout != null) {
                textInputLayout.setErrorEnabled(true);
            }
            if (textInputLayout != null) {
                textInputLayout.setError(str3);
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: showGPSNotEnabledDialog$lambda-9  reason: not valid java name */
        public static final void m161showGPSNotEnabledDialog$lambda9(Context context, DialogInterface dialogInterface, int i2) {
            Intrinsics.checkNotNullParameter(context, "$context");
            context.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: showRefreshTokenErrorDialog$lambda-10  reason: not valid java name */
        public static final void m162showRefreshTokenErrorDialog$lambda10(final Context context, MyNotificationModel model) {
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(model, "$model");
            new SingleCustomDialog(context, model, "Ok", new OnDialogOkClickListener() { // from class: com.psa.mym.mycitroenconnect.utils.AppUtil$Companion$showRefreshTokenErrorDialog$1$1
                @Override // com.psa.mym.mycitroenconnect.controller.dialog.OnDialogOkClickListener
                public void onDialogCancelClick(@Nullable Dialog dialog, @Nullable MyNotificationModel myNotificationModel) {
                }

                /* JADX WARN: Code restructure failed: missing block: B:10:0x0022, code lost:
                    if (r3.isDestroyed() == false) goto L10;
                 */
                @Override // com.psa.mym.mycitroenconnect.controller.dialog.OnDialogOkClickListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public void onDialogOkClick(@Nullable Dialog dialog, @Nullable MyNotificationModel myNotificationModel) {
                    if (dialog != null && dialog.isShowing()) {
                        Context baseContext = ((ContextWrapper) dialog.getContext()).getBaseContext();
                        if (baseContext instanceof Activity) {
                            Activity activity = (Activity) baseContext;
                            if (!activity.isFinishing()) {
                            }
                        }
                        dialog.dismiss();
                    }
                    SharedPref.Companion.logoutFromApp(context);
                }
            });
        }

        public final void addFragment(@NotNull FragmentManager manager, @NotNull Fragment fragment, boolean z) {
            Intrinsics.checkNotNullParameter(manager, "manager");
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            String name = fragment.getClass().getName();
            boolean popBackStackImmediate = manager.popBackStackImmediate(name, 0);
            if (manager.getBackStackEntryCount() > 0) {
                Fragment findFragmentById = manager.findFragmentById(R.id.fragment_container);
                Intrinsics.checkNotNull(findFragmentById);
                if (Intrinsics.areEqual(findFragmentById.getClass(), fragment.getClass())) {
                    return;
                }
            }
            if (popBackStackImmediate) {
                return;
            }
            FragmentTransaction beginTransaction = manager.beginTransaction();
            Intrinsics.checkNotNullExpressionValue(beginTransaction, "manager.beginTransaction()");
            beginTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            beginTransaction.add(R.id.fragment_container, fragment);
            if (z) {
                beginTransaction.addToBackStack(name);
            } else {
                beginTransaction.addToBackStack(null);
            }
            beginTransaction.commit();
        }

        public final void callingIntent(@NotNull Activity activity, @NotNull String number) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(number, "number");
            Intent intent = new Intent("android.intent.action.DIAL");
            intent.setData(Uri.parse("tel:" + number));
            activity.startActivity(intent);
        }

        public final boolean checkForBiometrics(@NotNull Context context) {
            String str;
            Intrinsics.checkNotNullParameter(context, "context");
            Logger logger = Logger.INSTANCE;
            logger.d("checkForBiometrics started");
            boolean z = false;
            boolean z2 = ContextCompat.checkSelfPermission(context, "android.permission.USE_BIOMETRIC") == 0;
            if (Build.VERSION.SDK_INT < 29) {
                Object systemService = context.getSystemService("keyguard");
                Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
                KeyguardManager keyguardManager = (KeyguardManager) systemService;
                PackageManager packageManager = context.getPackageManager();
                Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
                if (!packageManager.hasSystemFeature("android.hardware.fingerprint")) {
                    logger.w("checkForBiometrics, Fingerprint Sensor not supported");
                    z2 = false;
                }
                if (!keyguardManager.isKeyguardSecure()) {
                    str = "checkForBiometrics, Lock screen security not enabled in Settings";
                    logger.w(str);
                }
                z = z2;
            } else {
                Object systemService2 = context.getSystemService(BiometricManager.class);
                Intrinsics.checkNotNullExpressionValue(systemService2, "context.getSystemService…etricManager::class.java)");
                if (((BiometricManager) systemService2).canAuthenticate() != 0) {
                    str = "checkForBiometrics, biometrics not supported";
                    logger.w(str);
                }
                z = z2;
            }
            logger.d("checkForBiometrics ended, canAuthenticate=" + z + TokenParser.SP);
            return z;
        }

        public final boolean compareDates(@NotNull String serverDate) {
            Intrinsics.checkNotNullParameter(serverDate, "serverDate");
            Calendar calendar = Calendar.getInstance();
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            Date parse = new SimpleDateFormat(AppConstants.ONLY_DATE_FORMAT, getDefaultLocale()).parse(serverDate);
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkNotNull(parse);
            calendar2.setTime(parse);
            calendar2.set(11, 0);
            calendar2.set(12, 0);
            calendar2.set(13, 0);
            calendar2.set(14, 0);
            return calendar.getTime().compareTo(calendar2.getTime()) == 0;
        }

        public final void convertActivityFromTranslucent(@Nullable Activity activity) {
            try {
                Method declaredMethod = Activity.class.getDeclaredMethod("convertFromTranslucent", new Class[0]);
                Intrinsics.checkNotNullExpressionValue(declaredMethod, "Activity::class.java.get…\"convertFromTranslucent\")");
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(activity, new Object[0]);
            } catch (Throwable unused) {
            }
        }

        @NotNull
        public final String convertDate(@NotNull String serverDate) {
            Intrinsics.checkNotNullParameter(serverDate, "serverDate");
            if (notEmpty(serverDate)) {
                try {
                    Date parse = new SimpleDateFormat(AppConstants.UTC_DATE_FORMAT, getDefaultLocale()).parse(serverDate);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.DISPLAY_DATE_FORMAT, getDefaultLocale());
                    if (parse != null) {
                        return simpleDateFormat.format(parse).toString();
                    }
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
            }
            return "";
        }

        public final int convertDipToPixels(float f2, @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
        }

        @NotNull
        public final String convertForLabelsDate(@NotNull String serverDate) {
            String str;
            Intrinsics.checkNotNullParameter(serverDate, "serverDate");
            if (notEmpty(serverDate)) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.ONLY_DATE_FORMAT, getDefaultLocale());
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(AppConstants.SERVER_DATE_FORMAT_WITHOUT_YEAR, getDefaultLocale());
                    Date parse = simpleDateFormat.parse(serverDate);
                    if (compareDates(serverDate)) {
                        str = "Today";
                    } else if (parse == null) {
                        return "";
                    } else {
                        str = simpleDateFormat2.format(parse).toString();
                    }
                    return str;
                } catch (ParseException e2) {
                    e2.printStackTrace();
                    return "";
                }
            }
            return "";
        }

        public final int convertPixelsToDp(float f2, @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return (int) (f2 / (context.getResources().getDisplayMetrics().densityDpi / 160.0f));
        }

        @NotNull
        public final String convertSeconds(long j2) {
            StringBuilder sb;
            boolean isBlank;
            boolean startsWith$default;
            boolean z;
            TimeUnit timeUnit = TimeUnit.SECONDS;
            long days = timeUnit.toDays(j2);
            long hours = timeUnit.toHours(j2) - (24 * days);
            long j3 = 60;
            long minutes = timeUnit.toMinutes(j2) - (timeUnit.toHours(j2) * j3);
            long j4 = j2 % j3;
            Logger logger = Logger.INSTANCE;
            logger.e("Day: " + days + ", Hours: " + hours + ", minute: " + minutes + ", second: " + j4);
            StringBuilder sb2 = new StringBuilder();
            if (j2 > 3599) {
                if (days != 0) {
                    StringBuilder sb3 = days != 1 ? new StringBuilder() : new StringBuilder();
                    sb3.append(days);
                    sb3.append("d ");
                    sb2.append(sb3.toString());
                    z = true;
                } else {
                    z = false;
                }
                if (hours != 0) {
                    StringBuilder sb4 = hours != 1 ? new StringBuilder() : new StringBuilder();
                    sb4.append(hours);
                    sb4.append("h ");
                    sb2.append(sb4.toString());
                }
                if (minutes != 0) {
                    logger.e("Flag: " + z);
                    sb = minutes != 1 ? new StringBuilder() : new StringBuilder();
                    sb.append(minutes);
                    sb.append("min ");
                    sb2.append(sb.toString());
                }
            } else {
                if (minutes != 0) {
                    StringBuilder sb5 = minutes != 1 ? new StringBuilder() : new StringBuilder();
                    sb5.append(minutes);
                    sb5.append("min ");
                    sb2.append(sb5.toString());
                }
                if (j4 != 0) {
                    sb = j4 != 1 ? new StringBuilder() : new StringBuilder();
                    sb.append(j4);
                    sb.append("sec ");
                    sb2.append(sb.toString());
                }
            }
            logger.e("Converted Time: " + ((Object) sb2));
            String sb6 = sb2.toString();
            Intrinsics.checkNotNullExpressionValue(sb6, "sb.toString()");
            if (sb6.length() > 0) {
                String sb7 = sb2.toString();
                Intrinsics.checkNotNullExpressionValue(sb7, "sb.toString()");
                isBlank = StringsKt__StringsJVMKt.isBlank(sb7);
                if (!isBlank) {
                    String sb8 = sb2.toString();
                    Intrinsics.checkNotNullExpressionValue(sb8, "sb.toString()");
                    startsWith$default = StringsKt__StringsJVMKt.startsWith$default(sb8, HelpFormatter.DEFAULT_OPT_PREFIX, false, 2, null);
                    if (!startsWith$default) {
                        String sb9 = sb2.toString();
                        Intrinsics.checkNotNullExpressionValue(sb9, "{\n                sb.toString()\n            }");
                        return sb9;
                    }
                }
            }
            return "0 h";
        }

        @NotNull
        public final String convertTimeToUICustomString(@NotNull String ipStartTime, @NotNull String ipEndTime) {
            String replace$default;
            String replace$default2;
            String replace$default3;
            CharSequence trim;
            String replace$default4;
            CharSequence trim2;
            Intrinsics.checkNotNullParameter(ipStartTime, "ipStartTime");
            Intrinsics.checkNotNullParameter(ipEndTime, "ipEndTime");
            String convertToTime = convertToTime(ipStartTime);
            String convertToTime2 = convertToTime(ipEndTime);
            Locale locale = Locale.ROOT;
            String lowerCase = convertToTime.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            replace$default = StringsKt__StringsJVMKt.replace$default(lowerCase, ":00", "", false, 4, (Object) null);
            replace$default2 = StringsKt__StringsJVMKt.replace$default(replace$default, "am", "", false, 4, (Object) null);
            replace$default3 = StringsKt__StringsJVMKt.replace$default(replace$default2, "pm", "", false, 4, (Object) null);
            trim = StringsKt__StringsKt.trim((CharSequence) replace$default3);
            String obj = trim.toString();
            String lowerCase2 = convertToTime2.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            replace$default4 = StringsKt__StringsJVMKt.replace$default(lowerCase2, ":00", "", false, 4, (Object) null);
            trim2 = StringsKt__StringsKt.trim((CharSequence) replace$default4);
            String obj2 = trim2.toString();
            return obj + " - " + obj2;
        }

        @NotNull
        public final String convertToDate(@NotNull String inputDate) {
            Intrinsics.checkNotNullParameter(inputDate, "inputDate");
            try {
                Date parse = new SimpleDateFormat(AppConstants.RESPONSE_UTC_DATE_FORMAT, getDefaultLocale()).parse(inputDate);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.DATE_FORMAT_DD_MM_YY, getDefaultLocale());
                Intrinsics.checkNotNull(parse);
                String format = simpleDateFormat.format(parse);
                Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(date!!)");
                return format;
            } catch (Exception unused) {
                Date parse2 = new SimpleDateFormat(AppConstants.UTC_DATE_FORMAT, getDefaultLocale()).parse(inputDate);
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(AppConstants.DATE_FORMAT_DD_MM_YY, getDefaultLocale());
                Intrinsics.checkNotNull(parse2);
                String format2 = simpleDateFormat2.format(parse2);
                Intrinsics.checkNotNullExpressionValue(format2, "dateFormat.format(date!!)");
                return format2;
            }
        }

        @NotNull
        public final String convertToTime(@NotNull String inputDate) {
            Intrinsics.checkNotNullParameter(inputDate, "inputDate");
            try {
                Date parse = new SimpleDateFormat(AppConstants.RESPONSE_UTC_DATE_FORMAT, getDefaultLocale()).parse(inputDate);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.TIME_FORMAT_HH_mm_a, getDefaultLocale());
                Intrinsics.checkNotNull(parse);
                String format = simpleDateFormat.format(parse);
                Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(date!!)");
                return format;
            } catch (Exception unused) {
                Date parse2 = new SimpleDateFormat(AppConstants.UTC_DATE_FORMAT, getDefaultLocale()).parse(inputDate);
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(AppConstants.TIME_FORMAT_HH_mm_a, getDefaultLocale());
                Intrinsics.checkNotNull(parse2);
                String format2 = simpleDateFormat2.format(parse2);
                Intrinsics.checkNotNullExpressionValue(format2, "dateFormat.format(date!!)");
                return format2;
            }
        }

        @NotNull
        public final String convertToTime24HrFormat(@NotNull String inputDate) {
            Intrinsics.checkNotNullParameter(inputDate, "inputDate");
            Date parse = new SimpleDateFormat(AppConstants.RESPONSE_UTC_DATE_FORMAT, getDefaultLocale()).parse(inputDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.TIME_FORMAT_HH_mm, getDefaultLocale());
            Intrinsics.checkNotNull(parse);
            String format = simpleDateFormat.format(parse);
            Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(date!!)");
            return format;
        }

        public final void dismissDialog() {
            Dialog dialog;
            try {
                if (getDialog() != null) {
                    Dialog dialog2 = getDialog();
                    boolean z = true;
                    if (dialog2 == null || !dialog2.isShowing()) {
                        z = false;
                    }
                    if (z && (dialog = getDialog()) != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @WorkerThread
        @NotNull
        public final String getAddressNameShortString(@NotNull Context context, double d2, double d3) {
            String sb;
            Logger logger;
            String str;
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                List<Address> fromLocation = new Geocoder(context, getDefaultLocale()).getFromLocation(d2, d3, 1);
                if (fromLocation == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(d2);
                    sb2.append(AbstractJsonLexerKt.COMMA);
                    sb2.append(d3);
                    sb = sb2.toString();
                    logger = Logger.INSTANCE;
                } else if (fromLocation.size() > 0) {
                    if (fromLocation.get(0).getSubAdminArea() != null && fromLocation.get(0).getLocality() != null) {
                        return fromLocation.get(0).getLocality() + ", " + fromLocation.get(0).getSubAdminArea();
                    } else if (fromLocation.get(0).getSubAdminArea() == null && fromLocation.get(0).getLocality() == null) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(d2);
                        sb3.append(AbstractJsonLexerKt.COMMA);
                        sb3.append(d3);
                        return sb3.toString();
                    } else {
                        if (fromLocation.get(0).getSubAdminArea() == null && fromLocation.get(0).getLocality() != null) {
                            sb = fromLocation.get(0).getLocality();
                            str = "addresses[0].locality";
                        } else if (fromLocation.get(0).getSubAdminArea() == null || fromLocation.get(0).getLocality() != null) {
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(d2);
                            sb4.append(AbstractJsonLexerKt.COMMA);
                            sb4.append(d3);
                            sb = sb4.toString();
                            logger = Logger.INSTANCE;
                        } else {
                            sb = fromLocation.get(0).getSubAdminArea();
                            str = "addresses[0].subAdminArea";
                        }
                        Intrinsics.checkNotNullExpressionValue(sb, str);
                        return sb;
                    }
                } else {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(d2);
                    sb5.append(AbstractJsonLexerKt.COMMA);
                    sb5.append(d3);
                    sb = sb5.toString();
                    logger = Logger.INSTANCE;
                }
                logger.w("My Current location: No Address returned!");
                return sb;
            } catch (Exception e2) {
                StringBuilder sb6 = new StringBuilder();
                sb6.append(d2);
                sb6.append(AbstractJsonLexerKt.COMMA);
                sb6.append(d3);
                String sb7 = sb6.toString();
                e2.printStackTrace();
                Logger.INSTANCE.w("My Current location: Can not get Address!");
                return sb7;
            }
        }

        @WorkerThread
        @NotNull
        public final String getAddressNameString(@NotNull Context context, double d2, double d3) {
            String sb;
            Logger logger;
            String str;
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                List<Address> fromLocation = new Geocoder(context, getDefaultLocale()).getFromLocation(d2, d3, 1);
                if (fromLocation == null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(d2);
                    sb2.append(AbstractJsonLexerKt.COMMA);
                    sb2.append(d3);
                    sb = sb2.toString();
                    logger = Logger.INSTANCE;
                    str = "My Current location: Can not get Address for lat long: " + d2 + AbstractJsonLexerKt.COMMA + d3;
                } else if (fromLocation.size() > 0) {
                    Address address = fromLocation.get(0);
                    if (address.getAddressLine(0) != null) {
                        String addressLine = address.getAddressLine(0);
                        Intrinsics.checkNotNullExpressionValue(addressLine, "returnedAddress.getAddressLine(0)");
                        if (addressLine.length() > 0) {
                            String addressLine2 = address.getAddressLine(0);
                            Intrinsics.checkNotNullExpressionValue(addressLine2, "returnedAddress.getAddressLine(0)");
                            return addressLine2;
                        }
                    }
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(d2);
                    sb3.append(AbstractJsonLexerKt.COMMA);
                    sb3.append(d3);
                    sb = sb3.toString();
                    logger = Logger.INSTANCE;
                    str = "My Current location: Can not get Address for lat long: " + d2 + AbstractJsonLexerKt.COMMA + d3;
                } else {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(d2);
                    sb4.append(AbstractJsonLexerKt.COMMA);
                    sb4.append(d3);
                    sb = sb4.toString();
                    logger = Logger.INSTANCE;
                    str = "My Current location: Can not get Address for lat long: " + d2 + AbstractJsonLexerKt.COMMA + d3;
                }
                logger.w(str);
                return sb;
            } catch (Exception e2) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(d2);
                sb5.append(AbstractJsonLexerKt.COMMA);
                sb5.append(d3);
                String sb6 = sb5.toString();
                Logger.INSTANCE.w("My Current location: Can not get Address for lat long: " + d2 + AbstractJsonLexerKt.COMMA + d3);
                e2.printStackTrace();
                return sb6;
            }
        }

        @NotNull
        public final String getBase64EncodedString(@NotNull byte[] byteArrayImage) {
            Intrinsics.checkNotNullParameter(byteArrayImage, "byteArrayImage");
            String encodeToString = Base64.encodeToString(byteArrayImage, 0);
            Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(byteArrayImage, Base64.DEFAULT)");
            return encodeToString;
        }

        @NotNull
        public final BitmapDescriptor getBitmapDescriptorFromPNG(@NotNull Context context, @DrawableRes int i2) {
            Intrinsics.checkNotNullParameter(context, "context");
            BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(context.getResources(), i2));
            Intrinsics.checkNotNullExpressionValue(fromBitmap, "fromBitmap(bitmap)");
            return fromBitmap;
        }

        @NotNull
        public final BitmapDescriptor getBitmapDescriptorFromVector(@NotNull Context context, @DrawableRes int i2) {
            Intrinsics.checkNotNullParameter(context, "context");
            Drawable drawable = AppCompatResources.getDrawable(context, i2);
            Intrinsics.checkNotNull(drawable);
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap(createBitmap);
            Intrinsics.checkNotNullExpressionValue(fromBitmap, "fromBitmap(bitmap)");
            return fromBitmap;
        }

        @NotNull
        public final Calendar getCalendarForNow() {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(new Date());
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            return calendar;
        }

        @NotNull
        public final String getCurrentDateString() {
            return getDateString(new Date(), AppConstants.ONLY_DATE_FORMAT);
        }

        @NotNull
        public final Pair<String, String> getCurrentMonthDates() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.ONLY_DATE_FORMAT, getDefaultLocale());
            Calendar calendarForNow = getCalendarForNow();
            calendarForNow.set(5, calendarForNow.getActualMinimum(5));
            setTimeToBeginningOfDay(calendarForNow);
            String format = simpleDateFormat.format(calendarForNow.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
            Calendar calendarForNow2 = getCalendarForNow();
            calendarForNow2.set(5, calendarForNow2.getActualMaximum(5));
            setTimeToEndOfDay(calendarForNow2);
            String format2 = simpleDateFormat.format(calendarForNow2.getTime());
            Intrinsics.checkNotNullExpressionValue(format2, "df.format(calendar.time)");
            return new Pair<>(format, format2);
        }

        @NotNull
        public final String getCurrentTimeString() {
            return getDateString(new Date(), AppConstants.TOKEN_DATE_FORMAT);
        }

        @NotNull
        public final String getCurrentTimeStringUtc() {
            return getDateString(new Date(), AppConstants.UTC_DATE_FORMAT);
        }

        @NotNull
        public final Pair<String, String> getCurrentWeekDates() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(7, 2);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.ONLY_DATE_FORMAT, getDefaultLocale());
            String format = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
            calendar.add(5, 6);
            String format2 = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format2, "df.format(calendar.time)");
            Logger logger = Logger.INSTANCE;
            logger.e("Start Date = " + format);
            logger.e("End Date = " + format2);
            return new Pair<>(format, format2);
        }

        @NotNull
        public final Date getDateFromString(@NotNull String strDate, @NotNull String dateFormat) {
            Intrinsics.checkNotNullParameter(strDate, "strDate");
            Intrinsics.checkNotNullParameter(dateFormat, "dateFormat");
            try {
                Date parse = new SimpleDateFormat(dateFormat, getDefaultLocale()).parse(strDate);
                Intrinsics.checkNotNull(parse);
                return parse;
            } catch (Exception e2) {
                e2.printStackTrace();
                return new Date();
            }
        }

        @NotNull
        public final Date getDateFromStringWithFormat(@NotNull String date, @NotNull String dateFormat) {
            Date parse;
            Intrinsics.checkNotNullParameter(date, "date");
            Intrinsics.checkNotNullParameter(dateFormat, "dateFormat");
            Date date2 = new Date();
            try {
                parse = new SimpleDateFormat(dateFormat, getDefaultLocale()).parse(date);
                Intrinsics.checkNotNull(parse);
            } catch (ParseException e2) {
                e = e2;
            }
            try {
                date2 = new Date(parse.getYear(), parse.getMonth(), parse.getDate() + 1);
                System.out.println((Object) (date + '\t' + date2));
            } catch (ParseException e3) {
                e = e3;
                date2 = parse;
                e.printStackTrace();
                return date2;
            }
            return date2;
        }

        @NotNull
        public final String getDateString(@NotNull Date date, @NotNull String dateFormat) {
            Intrinsics.checkNotNullParameter(date, "date");
            Intrinsics.checkNotNullParameter(dateFormat, "dateFormat");
            String format = new SimpleDateFormat(dateFormat, getDefaultLocale()).format(date);
            Intrinsics.checkNotNullExpressionValue(format, "sDateFormat.format(date)");
            return format;
        }

        @NotNull
        public final Locale getDefaultLocale() {
            return AppUtil.defaultLocale;
        }

        @SuppressLint({"HardwareIds"})
        @NotNull
        public final String getDeviceId(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            Intrinsics.checkNotNullExpressionValue(string, "getString(context.conten…ttings.Secure.ANDROID_ID)");
            return string;
        }

        @Nullable
        public final Dialog getDialog() {
            return AppUtil.dialog;
        }

        public final double getDistance(@NotNull LocationData sourceData, @NotNull LocationData destData) {
            Intrinsics.checkNotNullParameter(sourceData, "sourceData");
            Intrinsics.checkNotNullParameter(destData, "destData");
            Location location = new Location("source");
            Double gpsLat = sourceData.getGpsLat();
            Intrinsics.checkNotNull(gpsLat);
            location.setLatitude(gpsLat.doubleValue());
            Double gpsLong = sourceData.getGpsLong();
            Intrinsics.checkNotNull(gpsLong);
            location.setLongitude(gpsLong.doubleValue());
            Location location2 = new Location(FirebaseAnalytics.Param.DESTINATION);
            Double gpsLat2 = destData.getGpsLat();
            Intrinsics.checkNotNull(gpsLat2);
            location2.setLatitude(gpsLat2.doubleValue());
            Double gpsLong2 = destData.getGpsLong();
            Intrinsics.checkNotNull(gpsLong2);
            location2.setLongitude(gpsLong2.doubleValue());
            float distanceTo = location.distanceTo(location2);
            if (distanceTo > 0.0f) {
                return distanceTo / 1000;
            }
            return 0.0d;
        }

        @Nullable
        public final byte[] getDownsizedImageBytes(@Nullable Bitmap bitmap, int i2, int i3) {
            Intrinsics.checkNotNull(bitmap);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i2, i3, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createScaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        @NotNull
        public final String getFormattedTime(@NotNull String timeString) {
            boolean contains$default;
            List split$default;
            List split$default2;
            Intrinsics.checkNotNullParameter(timeString, "timeString");
            try {
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) timeString, (CharSequence) ":", false, 2, (Object) null);
                if (contains$default) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    split$default = StringsKt__StringsKt.split$default((CharSequence) timeString, new String[]{":"}, false, 0, 6, (Object) null);
                    split$default2 = StringsKt__StringsKt.split$default((CharSequence) timeString, new String[]{":"}, false, 0, 6, (Object) null);
                    String format = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(Integer.parseInt((String) split$default.get(0))), Integer.valueOf(Integer.parseInt((String) split$default2.get(1)))}, 2));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    return format;
                }
                return timeString;
            } catch (Exception e2) {
                e2.printStackTrace();
                return timeString;
            }
        }

        @NotNull
        public final CalendarConstraints getFutureDateDisableConstraint() {
            CalendarConstraints build = new CalendarConstraints.Builder().setValidator(DateValidatorPointBackward.now()).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder()\n              …\n                .build()");
            return build;
        }

        @NotNull
        public final Pair<String, String> getLastMonthDates() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.ONLY_DATE_FORMAT, getDefaultLocale());
            Calendar calendar = Calendar.getInstance();
            calendar.add(2, -1);
            calendar.set(5, 1);
            String format = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "df.format(aCalendar.time)");
            calendar.set(5, calendar.getActualMaximum(5));
            String format2 = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format2, "df.format(aCalendar.time)");
            Logger logger = Logger.INSTANCE;
            logger.e("Last Month Start Date = " + format);
            logger.e("Last Month End Date = " + format2);
            return new Pair<>(format, format2);
        }

        @NotNull
        public final Pair<String, String> getLastWeekDate() {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(5, (-(calendar.get(7) - 2)) - 7);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.ONLY_DATE_FORMAT, getDefaultLocale());
            String format = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "df.format(c.time)");
            calendar.add(5, 6);
            String format2 = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format2, "df.format(c.time)");
            Logger logger = Logger.INSTANCE;
            logger.e(format + " - " + format2);
            return new Pair<>(format, format2);
        }

        @NotNull
        public final String getPastMonth(int i2) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yyyy", getDefaultLocale());
            Calendar calendar = Calendar.getInstance();
            calendar.add(2, -i2);
            String format = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "format.format(calendar.time)");
            return format;
        }

        @NotNull
        public final Pair<String, String> getPastMonthDates(int i2) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.ONLY_DATE_FORMAT, getDefaultLocale());
            Calendar calendar = Calendar.getInstance();
            calendar.add(2, -i2);
            calendar.set(5, 1);
            String format = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format, "df.format(aCalendar.time)");
            calendar.set(5, calendar.getActualMaximum(5));
            String format2 = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(format2, "df.format(aCalendar.time)");
            Logger logger = Logger.INSTANCE;
            logger.e("Last Month Start Date = " + format);
            logger.e("Last Month End Date = " + format2);
            return new Pair<>(format, format2);
        }

        @NotNull
        public final String getTimeAgo(long j2) {
            StringBuilder sb;
            String str;
            if (j2 < 1000000000000L) {
                j2 *= 1000;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (j2 > currentTimeMillis || j2 <= 0) {
                return "just now";
            }
            long j3 = currentTimeMillis - j2;
            long j4 = 60000;
            if (j3 < j4) {
                return "just now";
            }
            if (j3 < 120000) {
                return "a minute ago";
            }
            if (j3 < 3000000) {
                sb = new StringBuilder();
                sb.append(j3 / j4);
                str = " minutes ago";
            } else if (j3 < 5400000) {
                return "an hour ago";
            } else {
                long j5 = 86400000;
                if (j3 < j5) {
                    return (j3 / 3600000) + " hours ago";
                } else if (j3 < 172800000) {
                    return "yesterday";
                } else {
                    sb = new StringBuilder();
                    sb.append(j3 / j5);
                    str = " days ago";
                }
            }
            sb.append(str);
            return sb.toString();
        }

        @NotNull
        public final String getTimeDescString(@NotNull String timeString) {
            boolean contains$default;
            StringBuilder sb;
            List split$default;
            String trimStart;
            List split$default2;
            String trimStart2;
            String str;
            Intrinsics.checkNotNullParameter(timeString, "timeString");
            try {
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) timeString, (CharSequence) ":", false, 2, (Object) null);
                if (contains$default) {
                    split$default = StringsKt__StringsKt.split$default((CharSequence) timeString, new String[]{":"}, false, 0, 6, (Object) null);
                    trimStart = StringsKt__StringsKt.trimStart((String) split$default.get(0), '0');
                    split$default2 = StringsKt__StringsKt.split$default((CharSequence) timeString, new String[]{":"}, false, 0, 6, (Object) null);
                    trimStart2 = StringsKt__StringsKt.trimStart((String) split$default2.get(1), '0');
                    String str2 = "";
                    if (Intrinsics.areEqual(trimStart, "0") || Intrinsics.areEqual(trimStart, "")) {
                        str = "";
                    } else {
                        str = trimStart + " h";
                    }
                    if (!Intrinsics.areEqual(trimStart2, "0") && !Intrinsics.areEqual(trimStart2, "")) {
                        str2 = trimStart2 + " min";
                    }
                    sb = new StringBuilder();
                    sb.append(str);
                    sb.append(TokenParser.SP);
                    sb.append(str2);
                } else {
                    sb = new StringBuilder();
                    sb.append(timeString);
                    sb.append(" min");
                }
                timeString = sb.toString();
                return timeString;
            } catch (Exception e2) {
                e2.printStackTrace();
                return timeString + " min";
            }
        }

        @NotNull
        public final Date getTimeInDateFormat(@NotNull String inputTime) {
            Intrinsics.checkNotNullParameter(inputTime, "inputTime");
            try {
                Date parse = new SimpleDateFormat(AppConstants.UTC_DATE_FORMAT, getDefaultLocale()).parse(inputTime);
                Intrinsics.checkNotNull(parse);
                Calendar.getInstance().setTime(parse);
                return parse;
            } catch (Exception unused) {
                Date parse2 = new SimpleDateFormat(AppConstants.RESPONSE_UTC_DATE_FORMAT, getDefaultLocale()).parse(inputTime);
                Intrinsics.checkNotNull(parse2);
                Calendar.getInstance().setTime(parse2);
                return parse2;
            }
        }

        @NotNull
        public final String getValidPhoneString(@NotNull String phoneNo) {
            CharSequence trim;
            boolean contains$default;
            boolean contains$default2;
            boolean contains$default3;
            boolean contains$default4;
            boolean contains$default5;
            String str;
            boolean contains$default6;
            boolean contains$default7;
            boolean contains$default8;
            boolean contains$default9;
            boolean contains$default10;
            boolean contains$default11;
            boolean contains$default12;
            String str2;
            CharSequence trim2;
            String replace$default;
            CharSequence trim3;
            String replace$default2;
            CharSequence trim4;
            String replace$default3;
            CharSequence trim5;
            String replace$default4;
            CharSequence trim6;
            String replace$default5;
            CharSequence trim7;
            String replace$default6;
            CharSequence trim8;
            Intrinsics.checkNotNullParameter(phoneNo, "phoneNo");
            trim = StringsKt__StringsKt.trim((CharSequence) phoneNo);
            String obj = trim.toString();
            while (true) {
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) obj, (CharSequence) "+91", false, 2, (Object) null);
                if (contains$default) {
                    replace$default6 = StringsKt__StringsJVMKt.replace$default(obj, "+91", "", false, 4, (Object) null);
                    trim8 = StringsKt__StringsKt.trim((CharSequence) replace$default6);
                    obj = trim8.toString();
                }
                String str3 = obj;
                contains$default2 = StringsKt__StringsKt.contains$default((CharSequence) str3, (CharSequence) Marker.ANY_NON_NULL_MARKER, false, 2, (Object) null);
                if (contains$default2) {
                    replace$default5 = StringsKt__StringsJVMKt.replace$default(str3, Marker.ANY_NON_NULL_MARKER, "", false, 4, (Object) null);
                    trim7 = StringsKt__StringsKt.trim((CharSequence) replace$default5);
                    str3 = trim7.toString();
                }
                String str4 = str3;
                contains$default3 = StringsKt__StringsKt.contains$default((CharSequence) str4, (CharSequence) HelpFormatter.DEFAULT_OPT_PREFIX, false, 2, (Object) null);
                if (contains$default3) {
                    replace$default4 = StringsKt__StringsJVMKt.replace$default(str4, HelpFormatter.DEFAULT_OPT_PREFIX, "", false, 4, (Object) null);
                    trim6 = StringsKt__StringsKt.trim((CharSequence) replace$default4);
                    str4 = trim6.toString();
                }
                String str5 = str4;
                contains$default4 = StringsKt__StringsKt.contains$default((CharSequence) str5, (CharSequence) "(", false, 2, (Object) null);
                if (contains$default4) {
                    replace$default3 = StringsKt__StringsJVMKt.replace$default(str5, "(", "", false, 4, (Object) null);
                    trim5 = StringsKt__StringsKt.trim((CharSequence) replace$default3);
                    str5 = trim5.toString();
                }
                String str6 = str5;
                contains$default5 = StringsKt__StringsKt.contains$default((CharSequence) str6, (CharSequence) ")", false, 2, (Object) null);
                if (contains$default5) {
                    replace$default2 = StringsKt__StringsJVMKt.replace$default(str6, ")", "", false, 4, (Object) null);
                    trim4 = StringsKt__StringsKt.trim((CharSequence) replace$default2);
                    str6 = trim4.toString();
                }
                str = str6;
                contains$default6 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) " ", false, 2, (Object) null);
                if (contains$default6) {
                    replace$default = StringsKt__StringsJVMKt.replace$default(str, " ", "", false, 4, (Object) null);
                    trim3 = StringsKt__StringsKt.trim((CharSequence) replace$default);
                    str = trim3.toString();
                }
                contains$default7 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "+91", false, 2, (Object) null);
                if (!contains$default7) {
                    contains$default8 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) Marker.ANY_NON_NULL_MARKER, false, 2, (Object) null);
                    if (contains$default8) {
                        continue;
                    } else {
                        contains$default9 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) HelpFormatter.DEFAULT_OPT_PREFIX, false, 2, (Object) null);
                        if (contains$default9) {
                            continue;
                        } else {
                            contains$default10 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "(", false, 2, (Object) null);
                            if (contains$default10) {
                                continue;
                            } else {
                                contains$default11 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) ")", false, 2, (Object) null);
                                if (contains$default11) {
                                    continue;
                                } else {
                                    contains$default12 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) " ", false, 2, (Object) null);
                                    if (!contains$default12) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                obj = str;
            }
            if (str.length() != 11) {
                if (str.length() > 10) {
                    str = str.substring(str.length() - 10);
                    str2 = "this as java.lang.String).substring(startIndex)";
                }
                trim2 = StringsKt__StringsKt.trim((CharSequence) str);
                return trim2.toString();
            }
            str = str.substring(1, 11);
            str2 = "this as java.lang.String…ing(startIndex, endIndex)";
            Intrinsics.checkNotNullExpressionValue(str, str2);
            trim2 = StringsKt__StringsKt.trim((CharSequence) str);
            return trim2.toString();
        }

        @NotNull
        public final String getVersionName(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                Intrinsics.checkNotNullExpressionValue(str, "{\n                contex…versionName\n            }");
                return str;
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                return "";
            }
        }

        public final int getWeatherIcon(int i2) {
            switch (i2) {
                case 2:
                    return R.drawable.ic_weather_mostly_sunny;
                case 3:
                    return R.drawable.ic_weather_partly_sunny;
                case 4:
                case 5:
                case 7:
                case 35:
                case 36:
                case 38:
                    return R.drawable.ic_weather_cloudy;
                case 6:
                    return R.drawable.ic_weather_mostly_cloudy;
                case 8:
                    return R.drawable.ic_weather_dreary;
                case 9:
                case 10:
                case 27:
                case 28:
                default:
                    return R.drawable.ic_weather_sunny;
                case 11:
                    return R.drawable.ic_weather_fog;
                case 12:
                case 13:
                case 14:
                    return R.drawable.ic_weather_showers;
                case 15:
                case 16:
                case 17:
                    return R.drawable.ic_weather_t_storm;
                case 18:
                    return R.drawable.ic_weather_rain;
                case 19:
                case 20:
                case 21:
                    return R.drawable.ic_weather_flurry;
                case 22:
                case 23:
                    return R.drawable.ic_weather_snow;
                case 24:
                    return R.drawable.ic_weather_ice;
                case 25:
                    return R.drawable.ic_weather_sleet;
                case 26:
                    return R.drawable.ic_weather_freezing_rain;
                case 29:
                    return R.drawable.ic_weather_rain_and_snow;
                case 30:
                    return R.drawable.ic_weather_hot;
                case 31:
                    return R.drawable.ic_weather_cold;
                case 32:
                    return R.drawable.ic_weather_windy;
                case 33:
                case 34:
                    return R.drawable.ic_weather_clear;
                case 37:
                    return R.drawable.ic_weather_hazy_moonlight;
                case 39:
                case 40:
                    return R.drawable.ic_weather_cloudy_showers;
                case 41:
                case 42:
                    return R.drawable.ic_weather_cloudy_t_stroms;
                case 43:
                    return R.drawable.ic_weather_cloudy_flurries;
                case 44:
                    return R.drawable.ic_weather_cloudy_snow;
            }
        }

        public final void hideKeyboard(@NotNull Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Object systemService = activity.getSystemService("input_method");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus == null) {
                currentFocus = new View(activity);
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }

        public final boolean isAppIsInBackground(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService("activity");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
            boolean z = true;
            if (runningAppProcesses != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.importance == 100) {
                        String[] strArr = runningAppProcessInfo.pkgList;
                        Intrinsics.checkNotNullExpressionValue(strArr, "processInfo.pkgList");
                        for (String str : strArr) {
                            if (Intrinsics.areEqual(str, context.getPackageName())) {
                                z = false;
                            }
                        }
                    }
                }
            }
            return z;
        }

        public final boolean isEmailAddress(@Nullable TextInputLayout textInputLayout, @NotNull String str, @NotNull String errorMsg) {
            Intrinsics.checkNotNullParameter(str, "str");
            Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
            return isValid(str, textInputLayout, AppUtil.EMAIL_REGEX, errorMsg);
        }

        public final boolean isInteger(float f2) {
            return f2 - ((float) ((int) f2)) <= 0.0f;
        }

        public final boolean isLocationEnabled(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService("location");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
            LocationManager locationManager = (LocationManager) systemService;
            return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
        }

        public final boolean isMobileNumberValidNew(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "str");
            int length = str.length() - 1;
            int i2 = 0;
            boolean z = false;
            while (i2 <= length) {
                boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i2 : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i2++;
                } else {
                    z = true;
                }
            }
            return Pattern.matches(AppUtil.MOBILE_REGEX_VAL, str.subSequence(i2, length + 1).toString());
        }

        public final boolean isPastTime(@NotNull String startTime) {
            Intrinsics.checkNotNullParameter(startTime, "startTime");
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
            return calendar.compareTo(timeCalendar(startTime)) < 0;
        }

        public final boolean isSameTime(@NotNull String startTime, @NotNull String endTime) {
            Intrinsics.checkNotNullParameter(startTime, "startTime");
            Intrinsics.checkNotNullParameter(endTime, "endTime");
            return timeCalendar(startTime).compareTo(timeCalendar(endTime)) == 0;
        }

        public final boolean isTimeGreaterThanStartTime(@NotNull String startTime, @NotNull String endTime) {
            Intrinsics.checkNotNullParameter(startTime, "startTime");
            Intrinsics.checkNotNullParameter(endTime, "endTime");
            return timeCalendar(startTime).compareTo(timeCalendar(endTime)) > 0;
        }

        public final boolean isUserLoggedIn(@NotNull Context context) {
            boolean isBlank;
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPref.Companion companion = SharedPref.Companion;
            String vinNumber = companion.getVinNumber(context);
            if (vinNumber != null) {
                if (!(vinNumber.length() == 0)) {
                    isBlank = StringsKt__StringsJVMKt.isBlank(vinNumber);
                    if (!isBlank && companion.isLogin(context)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public final boolean isValidEmail(@NotNull String email) {
            Intrinsics.checkNotNullParameter(email, "email");
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

        public final boolean isValidMobileNumber(@Nullable TextInputLayout textInputLayout, @NotNull String str, @NotNull String message) {
            Intrinsics.checkNotNullParameter(str, "str");
            Intrinsics.checkNotNullParameter(message, "message");
            return isValid(str, textInputLayout, AppUtil.MOBILE_REGEX, message);
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
            if (r0 == com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType.MOBILE) goto L18;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean isValidNumber(@NotNull String number) {
            boolean startsWith$default;
            boolean contains$default;
            boolean contains$default2;
            boolean contains$default3;
            boolean contains$default4;
            Intrinsics.checkNotNullParameter(number, "number");
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            boolean z = true;
            if (number.length() == 10) {
                return true;
            }
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(number, "+91", false, 2, null);
            if (!startsWith$default) {
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) number, (CharSequence) "(", false, 2, (Object) null);
                if (!contains$default) {
                    contains$default2 = StringsKt__StringsKt.contains$default((CharSequence) number, (CharSequence) ")", false, 2, (Object) null);
                    if (!contains$default2) {
                        contains$default3 = StringsKt__StringsKt.contains$default((CharSequence) number, (CharSequence) HelpFormatter.DEFAULT_OPT_PREFIX, false, 2, (Object) null);
                        if (!contains$default3) {
                            contains$default4 = StringsKt__StringsKt.contains$default((CharSequence) number, (CharSequence) " ", false, 2, (Object) null);
                            if (!contains$default4) {
                                try {
                                    Phonenumber.PhoneNumber parse = phoneNumberUtil.parse(number, AppConstants.GEO_FENCE_TRANSITION_IN);
                                    PhoneNumberUtil.PhoneNumberType numberType = phoneNumberUtil.getNumberType(parse);
                                    if (parse.getCountryCode() == 91) {
                                    }
                                    z = false;
                                    return z;
                                } catch (NumberParseException e2) {
                                    e2.printStackTrace();
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            return getValidPhoneString(number).length() == 10;
        }

        public final boolean isValidPassword(@Nullable TextInputLayout textInputLayout, @NotNull String str, @NotNull String errorMsg) {
            Intrinsics.checkNotNullParameter(str, "str");
            Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
            return isValid(str, textInputLayout, AppUtil.PASSWORD_REGEX, errorMsg);
        }

        public final void logEndTime(@NotNull String methodName) {
            Intrinsics.checkNotNullParameter(methodName, "methodName");
            Logger logger = Logger.INSTANCE;
            logger.d(methodName + ": end time in millis : " + System.currentTimeMillis());
        }

        public final void logStartTime(@NotNull String methodName) {
            Intrinsics.checkNotNullParameter(methodName, "methodName");
            Logger logger = Logger.INSTANCE;
            logger.d(methodName + ": start time in millis : " + System.currentTimeMillis());
        }

        public final void navigateToDestination(@NotNull Context context, double d2, double d3) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("google.navigation:q=" + d2 + AbstractJsonLexerKt.COMMA + d3 + "&mode=d"));
            intent.setPackage("com.google.android.apps.maps");
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        }

        public final boolean notEmpty(@Nullable String str) {
            if (str != null) {
                int length = str.length() - 1;
                int i2 = 0;
                boolean z = false;
                while (i2 <= length) {
                    boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i2 : length), 32) <= 0;
                    if (z) {
                        if (!z2) {
                            break;
                        }
                        length--;
                    } else if (z2) {
                        i2++;
                    } else {
                        z = true;
                    }
                }
                return str.subSequence(i2, length + 1).toString().length() > 0;
            }
            return false;
        }

        public final void openKeyboard(@Nullable Activity activity) {
            if (activity != null) {
                Object systemService = activity.getSystemService("input_method");
                Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                ((InputMethodManager) systemService).toggleSoftInput(2, 1);
            }
        }

        @NotNull
        public final String parseTime(@NotNull String timeIn24Hours) {
            Intrinsics.checkNotNullParameter(timeIn24Hours, "timeIn24Hours");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.TIME_FORMAT_HH_mm, getDefaultLocale());
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(AppConstants.TIME_FORMAT_HH_mm_a, getDefaultLocale());
            Date parse = simpleDateFormat.parse(timeIn24Hours);
            if (parse != null) {
                String format = simpleDateFormat2.format(parse);
                Intrinsics.checkNotNullExpressionValue(format, "timeFormatIn12.format(date)");
                return format;
            }
            return "";
        }

        @Nullable
        public final Date parseToDate(@NotNull String date) {
            Intrinsics.checkNotNullParameter(date, "date");
            if (notEmpty(date)) {
                return new SimpleDateFormat(AppConstants.TOKEN_DATE_FORMAT, getDefaultLocale()).parse(date);
            }
            return null;
        }

        public final void replaceFragment(@NotNull FragmentManager manager, @NotNull Fragment fragment) {
            Intrinsics.checkNotNullParameter(manager, "manager");
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            String name = fragment.getClass().getName();
            boolean popBackStackImmediate = manager.popBackStackImmediate(name, 0);
            if (manager.getBackStackEntryCount() > 0) {
                Fragment findFragmentById = manager.findFragmentById(R.id.fragment_container);
                Intrinsics.checkNotNull(findFragmentById);
                if (Intrinsics.areEqual(findFragmentById.getClass(), fragment.getClass())) {
                    return;
                }
            }
            if (popBackStackImmediate) {
                return;
            }
            FragmentTransaction beginTransaction = manager.beginTransaction();
            Intrinsics.checkNotNullExpressionValue(beginTransaction, "manager.beginTransaction()");
            beginTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            beginTransaction.replace(R.id.fragment_container, fragment);
            beginTransaction.addToBackStack(name);
            beginTransaction.commit();
        }

        public final void replaceFragmentWithoutBackstack(@NotNull FragmentManager manager, @NotNull Fragment fragment) {
            Intrinsics.checkNotNullParameter(manager, "manager");
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            boolean popBackStackImmediate = manager.popBackStackImmediate(fragment.getClass().getName(), 0);
            if (manager.getBackStackEntryCount() > 0) {
                Fragment findFragmentById = manager.findFragmentById(R.id.fragment_container);
                Intrinsics.checkNotNull(findFragmentById);
                if (Intrinsics.areEqual(findFragmentById.getClass(), fragment.getClass())) {
                    return;
                }
            }
            if (popBackStackImmediate) {
                return;
            }
            FragmentTransaction beginTransaction = manager.beginTransaction();
            Intrinsics.checkNotNullExpressionValue(beginTransaction, "manager.beginTransaction()");
            beginTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            beginTransaction.replace(R.id.fragment_container, fragment);
            beginTransaction.commit();
        }

        public final void setDialog(@Nullable Dialog dialog) {
            AppUtil.dialog = dialog;
        }

        @NotNull
        public final StateListDrawable setImageButtonStateNew(@NotNull Context mContext) {
            Intrinsics.checkNotNullParameter(mContext, "mContext");
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842913}, ContextCompat.getDrawable(mContext, R.drawable.ic_rounded_corner_red));
            stateListDrawable.addState(new int[]{-16842913}, ContextCompat.getDrawable(mContext, R.drawable.ic_rounded_corner_alert_red));
            return stateListDrawable;
        }

        public final void setMargins(@NotNull View view, int i2, int i3, int i4, int i5) {
            Intrinsics.checkNotNullParameter(view, "view");
            if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(i2, i3, i4, i5);
                view.requestLayout();
            }
        }

        @NotNull
        public final Spanned setSpannedString(@NotNull String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            Spanned fromHtml = Html.fromHtml(text, 0);
            Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(text, Html.FROM_HTML_MODE_LEGACY)");
            return fromHtml;
        }

        public final void setTimeToBeginningOfDay(@NotNull Calendar calendar) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 1);
            calendar.set(14, 0);
        }

        public final void setTimeToEndOfDay(@NotNull Calendar calendar) {
            Intrinsics.checkNotNullParameter(calendar, "calendar");
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, RoomDatabase.MAX_BIND_PARAMETER_CNT);
        }

        public final void showDialog(@NotNull Context mContext) {
            Dialog dialog;
            Window window;
            ColorDrawable colorDrawable;
            Intrinsics.checkNotNullParameter(mContext, "mContext");
            if (getDialog() == null) {
                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(1);
                window = dialog.getWindow();
                if (window != null) {
                    colorDrawable = new ColorDrawable(0);
                    window.setBackgroundDrawable(colorDrawable);
                }
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.progress);
                dialog.show();
                setDialog(dialog);
            }
            Dialog dialog2 = getDialog();
            if ((dialog2 == null || dialog2.isShowing()) ? false : true) {
                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(1);
                window = dialog.getWindow();
                if (window != null) {
                    colorDrawable = new ColorDrawable(0);
                    window.setBackgroundDrawable(colorDrawable);
                }
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.progress);
                dialog.show();
                setDialog(dialog);
            }
        }

        public final void showGPSNotEnabledDialog(@NotNull final Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            new AlertDialog.Builder(context).setTitle(context.getString(R.string.enable_gps)).setMessage(context.getString(R.string.required_for_this_app)).setCancelable(false).setPositiveButton(context.getString(R.string.enable_now), new DialogInterface.OnClickListener() { // from class: com.psa.mym.mycitroenconnect.utils.a
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    AppUtil.Companion.m161showGPSNotEnabledDialog$lambda9(context, dialogInterface, i2);
                }
            }).show();
        }

        public final void showRefreshTokenErrorDialog(@NotNull final Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string = context.getString(R.string.info);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.info)");
            String string2 = context.getString(R.string.err_refresh_token_logout);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…err_refresh_token_logout)");
            final MyNotificationModel myNotificationModel = new MyNotificationModel("", string, string2, "", "", "", "logout", "");
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.psa.mym.mycitroenconnect.utils.b
                @Override // java.lang.Runnable
                public final void run() {
                    AppUtil.Companion.m162showRefreshTokenErrorDialog$lambda10(context, myNotificationModel);
                }
            });
        }

        @NotNull
        public final SkeletonConfig skeletonConfig(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new SkeletonConfig(ContextCompat.getColor(context, R.color.skeleton_mask), 25.0f, true, ContextCompat.getColor(context, R.color.skeleton_shimmer), SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS, SkeletonShimmerDirection.LEFT_TO_RIGHT, 45);
        }

        @NotNull
        public final Calendar timeCalendar(@NotNull String time) {
            List split$default;
            boolean contains$default;
            List split$default2;
            Intrinsics.checkNotNullParameter(time, "time");
            split$default = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null);
            Object[] array = split$default.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            Calendar calendar = Calendar.getInstance();
            if (Integer.parseInt(strArr[0]) == 0 || Integer.parseInt(strArr[0]) == 12) {
                calendar.set(10, 0);
            } else {
                calendar.set(10, Integer.parseInt(strArr[0]));
            }
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) strArr[1], (CharSequence) " ", false, 2, (Object) null);
            if (contains$default) {
                split$default2 = StringsKt__StringsKt.split$default((CharSequence) strArr[1], new String[]{" "}, false, 0, 6, (Object) null);
                Object[] array2 = split$default2.toArray(new String[0]);
                Objects.requireNonNull(array2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                String[] strArr2 = (String[]) array2;
                calendar.set(12, Integer.parseInt(strArr2[0]));
                if (Intrinsics.areEqual(strArr2[1], "AM")) {
                    calendar.set(9, 0);
                } else {
                    calendar.set(9, 1);
                }
            } else {
                calendar.set(12, Integer.parseInt(strArr[1]));
            }
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            return calendar;
        }

        @NotNull
        public final Calendar timeCalendar24Hour(@NotNull String time) {
            List split$default;
            boolean contains$default;
            List split$default2;
            Intrinsics.checkNotNullParameter(time, "time");
            split$default = StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null);
            Object[] array = split$default.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            Calendar calendar = Calendar.getInstance();
            calendar.set(11, Integer.parseInt(strArr[0]));
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) strArr[1], (CharSequence) " ", false, 2, (Object) null);
            if (contains$default) {
                split$default2 = StringsKt__StringsKt.split$default((CharSequence) strArr[1], new String[]{" "}, false, 0, 6, (Object) null);
                Object[] array2 = split$default2.toArray(new String[0]);
                Objects.requireNonNull(array2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                String[] strArr2 = (String[]) array2;
                calendar.set(12, Integer.parseInt(strArr2[0]));
                if (Intrinsics.areEqual(strArr2[1], "AM")) {
                    calendar.set(9, 0);
                } else {
                    calendar.set(9, 1);
                }
            } else {
                calendar.set(12, Integer.parseInt(strArr[1]));
            }
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            return calendar;
        }
    }
}
