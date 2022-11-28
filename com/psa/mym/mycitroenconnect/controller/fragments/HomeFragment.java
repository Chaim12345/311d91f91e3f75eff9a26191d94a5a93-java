package com.psa.mym.mycitroenconnect.controller.fragments;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GravityCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.dashboard.RefreshCommandBody;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceActivity;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.GeoFenceListActivity;
import com.psa.mym.mycitroenconnect.controller.activities.dashboard.ValetActivity;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.VehicleStatus;
import com.psa.mym.mycitroenconnect.model.accu_weather.WeatherLocationResponse;
import com.psa.mym.mycitroenconnect.model.accu_weather.WeatherResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.DashboardResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.StoredDashboardData;
import com.psa.mym.mycitroenconnect.model.dashboard.VehicleLocationResponse;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponse;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import com.psa.mym.mycitroenconnect.model.streaming.MessageTypeResponse;
import com.psa.mym.mycitroenconnect.services.DashboardService;
import com.psa.mym.mycitroenconnect.services.GeoFenceService;
import com.psa.mym.mycitroenconnect.services.SnapShotService;
import com.psa.mym.mycitroenconnect.services.WeatherService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.LocationUtility;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Deprecated;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.bouncycastle.tls.CipherSuite;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class HomeFragment extends BusBaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AnimationInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int REQ_VALET_UPDATE = 145;
    @Nullable
    private AnimationInterface animationListener;
    @Nullable
    private LatLng carLatLng;
    private int carPullRefreshRetryCounter;
    @Nullable
    private Location currentLocation;
    @Nullable
    private GetGeoFenceResponseItem geoFence;
    private boolean isOneAnimationRunning;
    private long lastClickTime;
    @Nullable
    private LocationUtility locationUtility;
    @Nullable
    private MainActivity parentActivity;
    private int retryCounter;
    @Nullable
    private EventSource sseEventSource;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mCurrentDisplayMode = "";
    private boolean shouldRetry = true;
    private final int maxRetryCount = 5;
    private final long retryDelayMillis = AutoConstants.FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS;
    @NotNull
    private String currentAnim = "";
    @NotNull
    private String prevIgnitionStatus = "";
    @NotNull
    private final HomeFragment$eventSourceListener$1 eventSourceListener = new HomeFragment$eventSourceListener$1(this);

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final HomeFragment newInstance() {
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(new Bundle());
            return homeFragment;
        }
    }

    private final void activeDeActiveGeofence() {
        if (this.geoFence != null) {
            AppUtil.Companion companion = AppUtil.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.showDialog(requireContext);
            GeoFenceService geoFenceService = new GeoFenceService();
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            SharedPref.Companion companion2 = SharedPref.Companion;
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            String vinNumber = companion2.getVinNumber(requireContext3);
            GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFence;
            String fenceId = getGeoFenceResponseItem != null ? getGeoFenceResponseItem.getFenceId() : null;
            Intrinsics.checkNotNull(fenceId);
            SwitchMaterial switchFence = (SwitchMaterial) _$_findCachedViewById(R.id.switchFence);
            Intrinsics.checkNotNullExpressionValue(switchFence, "switchFence");
            geoFenceService.activeDeactiveGeoFence(requireContext2, vinNumber, fenceId, ExtensionsKt.getFenceStatus(switchFence));
        }
    }

    private final void animateBottomCard() {
        int i2 = R.id.cv_valet_mode;
        CardView cardView = (CardView) _$_findCachedViewById(i2);
        if (cardView != null) {
            ExtensionsKt.show(cardView);
        }
        CardView cardView2 = (CardView) _$_findCachedViewById(i2);
        if (cardView2 != null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ExtensionsKt.bottomToTopAnimation(cardView2, requireContext, HomeFragment$animateBottomCard$1.INSTANCE, HomeFragment$animateBottomCard$2.INSTANCE, HomeFragment$animateBottomCard$3.INSTANCE);
        }
    }

    private final void animateGeoFence() {
    }

    private final void animateIgnitionOff() {
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivCarMode);
        if (appCompatImageView != null) {
            ExtensionsKt.hide(appCompatImageView);
        }
        int i2 = R.id.animationView;
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(i2);
        if (lottieAnimationView != null) {
            ExtensionsKt.show(lottieAnimationView);
        }
        this.isOneAnimationRunning = true;
        this.currentAnim = "off";
        Logger logger = Logger.INSTANCE;
        logger.e("animateIgnitionOff -> Animation Rotation:" + ((LottieAnimationView) _$_findCachedViewById(i2)).getRotation() + " currentAnim:" + this.currentAnim);
        ((LottieAnimationView) _$_findCachedViewById(i2)).animate().rotation(0.0f).setDuration(1500L).setInterpolator(new LinearInterpolator()).setListener(new Animator.AnimatorListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.HomeFragment$animateIgnitionOff$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(@Nullable Animator animator) {
                Logger.INSTANCE.e("ignition_off -> Animation Cancel");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@Nullable Animator animator) {
                String str;
                String str2;
                str = HomeFragment.this.currentAnim;
                if (Intrinsics.areEqual(str, "off")) {
                    Logger logger2 = Logger.INSTANCE;
                    logger2.e("ignition_off -> Animation End");
                    StringBuilder sb = new StringBuilder();
                    sb.append("animateIgnitionOff -> Animation Rotation End:");
                    sb.append(((LottieAnimationView) HomeFragment.this._$_findCachedViewById(R.id.animationView)).getRotation());
                    sb.append(" currentAnim:");
                    str2 = HomeFragment.this.currentAnim;
                    sb.append(str2);
                    logger2.e(sb.toString());
                    if (animator != null) {
                        animator.removeAllListeners();
                    }
                    AnimationInterface animationListener = HomeFragment.this.getAnimationListener();
                    if (animationListener != null) {
                        animationListener.onAnimationComplete(AppConstants.ANIM_IGNITION_OFF_1);
                    }
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(@Nullable Animator animator) {
                Logger.INSTANCE.e("ignition_off -> Animation Repeat");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(@Nullable Animator animator) {
                String str;
                str = HomeFragment.this.currentAnim;
                if (Intrinsics.areEqual(str, "off")) {
                    Logger logger2 = Logger.INSTANCE;
                    logger2.e("ignition_off -> Animation Start");
                    logger2.e("animateIgnitionOff -> Animation Rotation Start:" + ((LottieAnimationView) HomeFragment.this._$_findCachedViewById(R.id.animationView)).getRotation());
                }
            }
        }).start();
    }

    private final void animateIgnitionOn() {
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivCarMode);
        if (appCompatImageView != null) {
            ExtensionsKt.hide(appCompatImageView);
        }
        int i2 = R.id.animationView;
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(i2);
        if (lottieAnimationView != null) {
            ExtensionsKt.show(lottieAnimationView);
        }
        this.isOneAnimationRunning = true;
        this.currentAnim = "on";
        Logger logger = Logger.INSTANCE;
        logger.e("animateIgnitionOn -> Animation Rotation:" + ((LottieAnimationView) _$_findCachedViewById(i2)).getRotation());
        if (!(((LottieAnimationView) _$_findCachedViewById(i2)).getRotation() == 0.0f)) {
            logger.e("animateIgnitionOn -> Animation Rotation:" + ((LottieAnimationView) _$_findCachedViewById(i2)).getRotation() + " RETURN=====");
            resetFlags();
            return;
        }
        LottieAnimationView lottieAnimationView2 = (LottieAnimationView) _$_findCachedViewById(i2);
        if (lottieAnimationView2 != null) {
            lottieAnimationView2.setSpeed(1.5f);
        }
        LottieAnimationView lottieAnimationView3 = (LottieAnimationView) _$_findCachedViewById(i2);
        if (lottieAnimationView3 != null) {
            lottieAnimationView3.setAnimation("ignition_on.json");
        }
        LottieAnimationView lottieAnimationView4 = (LottieAnimationView) _$_findCachedViewById(i2);
        if (lottieAnimationView4 != null) {
            lottieAnimationView4.addAnimatorListener(new Animator.AnimatorListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.HomeFragment$animateIgnitionOn$1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(@Nullable Animator animator) {
                    Logger.INSTANCE.e("ignition_on -> Animation Cancel");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(@Nullable Animator animator) {
                    String str;
                    str = HomeFragment.this.currentAnim;
                    if (Intrinsics.areEqual(str, "on")) {
                        Logger logger2 = Logger.INSTANCE;
                        logger2.e("ignition_on -> Animation End");
                        logger2.e("animateIgnitionOn -> Animation End Rotation:" + ((LottieAnimationView) HomeFragment.this._$_findCachedViewById(R.id.animationView)).getRotation());
                        if (animator != null) {
                            animator.removeAllListeners();
                        }
                        AnimationInterface animationListener = HomeFragment.this.getAnimationListener();
                        if (animationListener != null) {
                            animationListener.onAnimationComplete(AppConstants.ANIM_IGNITION_ON_1);
                        }
                    }
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(@Nullable Animator animator) {
                    Logger.INSTANCE.e("ignition_on -> Animation Repeat");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(@Nullable Animator animator) {
                    String str;
                    str = HomeFragment.this.currentAnim;
                    if (Intrinsics.areEqual(str, "on")) {
                        Logger logger2 = Logger.INSTANCE;
                        logger2.e("ignition_on -> Animation Start");
                        logger2.e("animateIgnitionOn -> Animation Start Rotation:" + ((LottieAnimationView) HomeFragment.this._$_findCachedViewById(R.id.animationView)).getRotation());
                    }
                }
            });
        }
        LottieAnimationView lottieAnimationView5 = (LottieAnimationView) _$_findCachedViewById(i2);
        if (lottieAnimationView5 != null) {
            lottieAnimationView5.playAnimation();
        }
    }

    private final void animateUpdateButton() {
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivRefresh);
        if (appCompatImageView != null) {
            ExtensionsKt.rotateAnimation$default(appCompatImageView, 0L, 0, new HomeFragment$animateUpdateButton$1(this), HomeFragment$animateUpdateButton$2.INSTANCE, HomeFragment$animateUpdateButton$3.INSTANCE, 3, null);
        }
    }

    private final void animateValetMode() {
    }

    private final void closeConnection() {
        Logger.INSTANCE.d("closeConnection");
        EventSource eventSource = this.sseEventSource;
        if (eventSource != null) {
            eventSource.cancel();
        }
        this.sseEventSource = null;
    }

    private final void displayAnimation() {
        String str = this.mCurrentDisplayMode;
        if (Intrinsics.areEqual(str, getString(uat.psa.mym.mycitroenconnect.R.string.label_geo_fence))) {
            animateGeoFence();
        } else if (Intrinsics.areEqual(str, getString(uat.psa.mym.mycitroenconnect.R.string.label_valet_mode))) {
            animateValetMode();
        }
    }

    private final void displayCarRefreshErrorMessage() {
        String string;
        String str;
        if (this.carPullRefreshRetryCounter == 1) {
            string = getString(uat.psa.mym.mycitroenconnect.R.string.err_refresh_error);
            str = "{\n                // Dis…resh_error)\n            }";
        } else {
            string = getString(uat.psa.mym.mycitroenconnect.R.string.err_refresh_retry);
            str = "{\n                getStr…resh_retry)\n            }";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, string);
    }

    private final void displayGeoFenceMode() {
        AppCompatTextView appCompatTextView;
        Integer radius;
        animateBottomCard();
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) _$_findCachedViewById(R.id.llUpdatedTime);
        if (linearLayoutCompat != null) {
            ExtensionsKt.hide(linearLayoutCompat);
        }
        AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvCardTitle);
        if (appCompatTextView2 != null) {
            appCompatTextView2.setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_geo_fence));
        }
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.label_geo_fence);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.label_geo_fence)");
        this.mCurrentDisplayMode = string;
        GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFence;
        if (getGeoFenceResponseItem != null) {
            SwitchMaterial switchMaterial = (SwitchMaterial) _$_findCachedViewById(R.id.switchFence);
            if (switchMaterial != null) {
                switchMaterial.setChecked(Intrinsics.areEqual(getGeoFenceResponseItem.getFenceStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS));
            }
            String fenceGeometry = getGeoFenceResponseItem.getFenceGeometry();
            if (!Intrinsics.areEqual(fenceGeometry, AppConstants.GEO_FENCE_GEOMETRY_CIRCLE)) {
                if (!Intrinsics.areEqual(fenceGeometry, AppConstants.GEO_FENCE_GEOMETRY_POLYGON) || (appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvCardSubTitle)) == null) {
                    return;
                }
                appCompatTextView.setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_route));
                return;
            }
            Intrinsics.checkNotNull(getGeoFenceResponseItem.getRadius());
            double intValue = radius.intValue() / 1000;
            AppCompatTextView appCompatTextView3 = (AppCompatTextView) _$_findCachedViewById(R.id.tvCardSubTitle);
            if (appCompatTextView3 == null) {
                return;
            }
            appCompatTextView3.setText(ExtensionsKt.toKM(intValue));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayIgnitionStatus(final String str) {
        final FragmentActivity activity;
        if (!isAdded() || getActivity() == null || (activity = getActivity()) == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.h
            @Override // java.lang.Runnable
            public final void run() {
                HomeFragment.m131displayIgnitionStatus$lambda28$lambda27(str, this, activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: displayIgnitionStatus$lambda-28$lambda-27  reason: not valid java name */
    public static final void m131displayIgnitionStatus$lambda28$lambda27(String message, HomeFragment this$0, FragmentActivity it) {
        String str;
        String str2;
        String str3;
        String chargingStatus;
        double batteryCharging;
        String timeToFullCharge;
        boolean z;
        String str4;
        Intrinsics.checkNotNullParameter(message, "$message");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        MessageTypeResponse messageTypeResponse = (MessageTypeResponse) new Gson().fromJson(message, (Class<Object>) MessageTypeResponse.class);
        if (messageTypeResponse.getIgnitionStatus() != null) {
            str = messageTypeResponse.getIgnitionStatus();
            Intrinsics.checkNotNull(str);
        } else {
            str = AppConstants.IGNITION_STATUS_STOP;
        }
        String str5 = str;
        this$0.setIgnitionStatus(str5);
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!Intrinsics.areEqual(companion.getVehicleType(requireContext), AppConstants.ICE)) {
            if (messageTypeResponse.getChargingStatus() != null) {
                String chargingStatus2 = messageTypeResponse.getChargingStatus();
                if (chargingStatus2 != null) {
                    str4 = chargingStatus2.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                } else {
                    str4 = null;
                }
                String lowerCase = "No_charging".toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                if (!Intrinsics.areEqual(str4, lowerCase)) {
                    AppCompatImageView appCompatImageView = (AppCompatImageView) this$0._$_findCachedViewById(R.id.ivCarMode);
                    if (appCompatImageView != null) {
                        appCompatImageView.setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.car_img_new);
                    }
                    chargingStatus = messageTypeResponse.getChargingStatus();
                    batteryCharging = messageTypeResponse.getBatteryCharging();
                    timeToFullCharge = messageTypeResponse.getTimeToFullCharge();
                    z = true;
                    this$0.setHeightInPercentage(chargingStatus, batteryCharging, timeToFullCharge, z);
                }
            }
            String ignitionStatus = messageTypeResponse.getIgnitionStatus();
            if (ignitionStatus != null) {
                Locale locale = Locale.ROOT;
                String lowerCase2 = ignitionStatus.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                String lowerCase3 = AppConstants.IGNITION_STATUS_CONTACT.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                if (Intrinsics.areEqual(lowerCase2, lowerCase3)) {
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) this$0._$_findCachedViewById(R.id.ivCarMode);
                    if (appCompatImageView2 != null) {
                        appCompatImageView2.setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.car_img_new);
                    }
                } else {
                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) this$0._$_findCachedViewById(R.id.ivCarMode);
                    if (appCompatImageView3 != null) {
                        appCompatImageView3.setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.car_img_side);
                    }
                    this$0.setCarImage();
                }
                chargingStatus = messageTypeResponse.getChargingStatus();
                batteryCharging = messageTypeResponse.getBatteryCharging();
                timeToFullCharge = messageTypeResponse.getTimeToFullCharge();
                z = false;
                this$0.setHeightInPercentage(chargingStatus, batteryCharging, timeToFullCharge, z);
            }
        }
        if (messageTypeResponse.getAcSwitchStatus() != null) {
            str2 = messageTypeResponse.getAcSwitchStatus();
            Intrinsics.checkNotNull(str2);
        } else {
            str2 = AppConstants.AC_STATUS_OFF;
        }
        this$0.setACStatus(str2);
        if (messageTypeResponse.getVehicleLockingStatus() != null) {
            str3 = messageTypeResponse.getVehicleLockingStatus();
            Intrinsics.checkNotNull(str3);
        } else {
            str3 = AppConstants.VEHICLE_UNLOCKED;
        }
        this$0.setVehicleLockStatus(str3);
        Double tboxSignalStrength = messageTypeResponse.getTboxSignalStrength();
        if (tboxSignalStrength != null) {
            this$0.setTBoxStatus(tboxSignalStrength.doubleValue());
        } else {
            this$0.setTBoxStatus(0.0d);
        }
        StoredDashboardData saveDashboardData = companion.getSaveDashboardData(it);
        if (saveDashboardData == null) {
            saveDashboardData = new StoredDashboardData(null, null, null, null, null, null, null, 0.0d, 0.0d, 0.0d, null, null, 0, 8191, null);
        }
        if (!Intrinsics.areEqual(saveDashboardData.getIgnitionStatus(), str5)) {
            Locale locale2 = Locale.ROOT;
            String lowerCase4 = str5.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            String lowerCase5 = AppConstants.IGNITION_STATUS_CONTACT.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            if (!Intrinsics.areEqual(lowerCase4, lowerCase5)) {
                companion.resetRefreshCommandTime(it);
            }
        }
        saveDashboardData.setIgnitionStatus(str5);
        saveDashboardData.setAcSwitchStatus(str2);
        saveDashboardData.setVehicleLockedStatus(str3);
        if (tboxSignalStrength != null) {
            saveDashboardData.setTboxSignalStrength(tboxSignalStrength.doubleValue());
        } else {
            saveDashboardData.setTboxSignalStrength(0.0d);
        }
        companion.saveDashboardData(it, saveDashboardData);
    }

    private final void displayValetMode() {
        Context requireContext;
        Integer radius;
        String str;
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivValetBackground);
        if (appCompatImageView != null) {
            ExtensionsKt.show(appCompatImageView);
        }
        int i2 = R.id.ivCarMode;
        AppCompatImageView appCompatImageView2 = (AppCompatImageView) _$_findCachedViewById(i2);
        if (appCompatImageView2 != null) {
            ExtensionsKt.show(appCompatImageView2);
        }
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(R.id.animationView);
        if (lottieAnimationView != null) {
            ExtensionsKt.hide(lottieAnimationView);
        }
        AppCompatImageView appCompatImageView3 = (AppCompatImageView) _$_findCachedViewById(i2);
        if (appCompatImageView3 != null) {
            appCompatImageView3.setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.car_img_new);
        }
        animateBottomCard();
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvCardTitle);
        if (appCompatTextView != null) {
            appCompatTextView.setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_valet_mode));
        }
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.label_valet_mode);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.label_valet_mode)");
        this.mCurrentDisplayMode = string;
        GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFence;
        if (getGeoFenceResponseItem != null) {
            int i3 = R.id.switchFence;
            SwitchMaterial switchMaterial = (SwitchMaterial) _$_findCachedViewById(i3);
            if (switchMaterial != null) {
                switchMaterial.setChecked(Intrinsics.areEqual(getGeoFenceResponseItem.getFenceStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS));
            }
            SharedPref.Companion companion = SharedPref.Companion;
            Intrinsics.checkNotNullExpressionValue(requireContext(), "requireContext()");
            ((SwitchMaterial) _$_findCachedViewById(i3)).setClickable(!companion.isGuestUser(requireContext));
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            ((AppCompatImageView) _$_findCachedViewById(R.id.ivEdit)).setVisibility(companion.isGuestUser(requireContext2) ? 8 : 0);
            Intrinsics.checkNotNull(getGeoFenceResponseItem.getRadius());
            double intValue = radius.intValue() / 1000;
            if (intValue == 0.0d) {
                str = "";
            } else if (intValue < 1.0d) {
                str = intValue + " KM";
            } else {
                StringBuilder sb = new StringBuilder();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf(intValue)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                sb.append(format);
                sb.append(" KM");
                str = sb.toString();
            }
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvCardSubTitle);
            if (appCompatTextView2 == null) {
                return;
            }
            appCompatTextView2.setText(str);
        }
    }

    static /* synthetic */ void e(HomeFragment homeFragment, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        homeFragment.getVehicleDetails(z);
    }

    private final void fadeInAnimation(View view) {
        if (view != null) {
            ExtensionsKt.fadeIn(view, new HomeFragment$fadeInAnimation$1(view), HomeFragment$fadeInAnimation$2.INSTANCE);
        }
    }

    private final void fetchDashboardData() {
        DashboardService dashboardService = new DashboardService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        dashboardService.callDashboardAPI(requireActivity, companion.getVinNumber(requireContext));
    }

    private final void fetchRefreshCommand() {
        if (this.carPullRefreshRetryCounter > 2) {
            this.carPullRefreshRetryCounter = 0;
        }
        RefreshCommandBody refreshCommandBody = new RefreshCommandBody(0, null, null, 7, null);
        refreshCommandBody.setAccountId(CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA);
        refreshCommandBody.setActionType("refreshcommand");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        refreshCommandBody.setDeviceVinno(companion.getVinNumber(requireContext));
        DashboardService dashboardService = new DashboardService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        dashboardService.callNewRefreshCommandAPI(requireActivity, refreshCommandBody);
    }

    private final void getArgumentsData() {
        String str;
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            boolean z = true;
            if (arguments == null || !arguments.isEmpty()) {
                z = false;
            }
            if (!z) {
                Bundle requireArguments = requireArguments();
                Intrinsics.checkNotNullExpressionValue(requireArguments, "requireArguments()");
                if (requireArguments.containsKey("login")) {
                    Object obj = requireArguments.get("login");
                    if (Intrinsics.areEqual(obj, getString(uat.psa.mym.mycitroenconnect.R.string.label_geo_fence))) {
                        str = "location";
                    } else if (!Intrinsics.areEqual(obj, getString(uat.psa.mym.mycitroenconnect.R.string.label_valet_mode))) {
                        return;
                    } else {
                        str = AppConstants.GEO_FENCE_VALET;
                    }
                    getGeoFenceData(str);
                    return;
                }
                return;
            }
        }
        displayHome();
    }

    private final void getSnapShotVehicleStatus() {
        SnapShotService snapShotService = new SnapShotService();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        snapShotService.getVehicleStatus(requireContext, companion.getVinNumber(requireContext2));
    }

    private final OkHttpClient getUnsafeOkHttpClient() {
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.HomeFragment$getUnsafeOkHttpClient$trustAllCerts$1
                @Override // javax.net.ssl.X509TrustManager
                @SuppressLint({"TrustAllX509TrustManager"})
                public void checkClientTrusted(@Nullable X509Certificate[] x509CertificateArr, @Nullable String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                @SuppressLint({"TrustAllX509TrustManager"})
                public void checkServerTrusted(@Nullable X509Certificate[] x509CertificateArr, @Nullable String str) {
                }

                @Override // javax.net.ssl.X509TrustManager
                @NotNull
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sSLContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            Intrinsics.checkNotNullExpressionValue(sslSocketFactory, "sslSocketFactory");
            OkHttpClient.Builder hostnameVerifier = builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustManagerArr[0]).hostnameVerifier(i.f10560a);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            return hostnameVerifier.connectTimeout(60L, timeUnit).writeTimeout(60L, timeUnit).readTimeout(60L, timeUnit).build();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getUnsafeOkHttpClient$lambda-22  reason: not valid java name */
    public static final boolean m132getUnsafeOkHttpClient$lambda22(String str, SSLSession sSLSession) {
        return true;
    }

    private final void getVehicleDetails(boolean z) {
        fetchDashboardData();
        if (z) {
            return;
        }
        startStreaming();
    }

    private final void getVehicleLocation() {
        DashboardService dashboardService = new DashboardService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        dashboardService.callGetVehicleCurrentLocation(requireActivity, companion.getVinNumber(requireContext));
    }

    private final void init() {
        MainActivity mainActivity;
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            mainActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.MainActivity");
            mainActivity = (MainActivity) activity;
        }
        this.parentActivity = mainActivity;
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.home);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.home)");
        this.mCurrentDisplayMode = string;
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvVehicleLocation);
        if (appCompatTextView != null) {
            ExtensionsKt.hide(appCompatTextView);
        }
        showWeatherDetails(false);
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isGuestUser(requireContext)) {
            View _$_findCachedViewById = _$_findCachedViewById(R.id.verticalDivider);
            if (_$_findCachedViewById != null) {
                ExtensionsKt.hide(_$_findCachedViewById);
            }
            ((SwitchMaterial) _$_findCachedViewById(R.id.switchFence)).setClickable(false);
        } else {
            int i2 = R.id.switchFence;
            SwitchMaterial switchMaterial = (SwitchMaterial) _$_findCachedViewById(i2);
            if (switchMaterial != null) {
                ExtensionsKt.show(switchMaterial);
            }
            View _$_findCachedViewById2 = _$_findCachedViewById(R.id.verticalDivider);
            if (_$_findCachedViewById2 != null) {
                ExtensionsKt.show(_$_findCachedViewById2);
            }
            ((SwitchMaterial) _$_findCachedViewById(i2)).setClickable(true);
        }
        setUIData();
    }

    private final void initializeLocation() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        LocationUtility locationUtility = new LocationUtility(requireActivity);
        this.locationUtility = locationUtility;
        locationUtility.startLocationClient();
        getLifecycle().addObserver(locationUtility);
        locationUtility.getCurrentLocation().observe(getViewLifecycleOwner(), new Observer() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.f
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HomeFragment.m133initializeLocation$lambda5$lambda4(HomeFragment.this, (Location) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initializeLocation$lambda-5$lambda-4  reason: not valid java name */
    public static final void m133initializeLocation$lambda5$lambda4(HomeFragment this$0, Location location) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.currentLocation = location;
    }

    @JvmStatic
    @NotNull
    public static final HomeFragment newInstance() {
        return Companion.newInstance();
    }

    private final void redirectToCreateGeoFence() {
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isGuestUser(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.child_user_can_not_create_geo_fence);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.child…can_not_create_geo_fence)");
            ExtensionsKt.showToast(requireContext2, string);
            return;
        }
        Intent intent = new Intent(requireContext(), GeoFenceActivity.class);
        intent.putExtra(AppConstants.ARG_PAGE_MODE, 5);
        intent.putExtra(AppConstants.CURRENT_LOCATION, this.currentLocation);
        intent.putExtra(AppConstants.CAR_LOCATION, this.carLatLng);
        intent.putExtra(AppConstants.IS_FROM_DASHBOARD, true);
        startActivity(intent);
    }

    private final void redirectToGeoFenceList() {
        Intent intent = new Intent(requireContext(), GeoFenceListActivity.class);
        intent.putExtra(AppConstants.CURRENT_LOCATION, this.currentLocation);
        intent.putExtra(AppConstants.CAR_LOCATION, this.carLatLng);
        startActivity(intent);
    }

    private final void redirectToValetFence() {
        Intent intent = new Intent(requireContext(), ValetActivity.class);
        GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFence;
        if (getGeoFenceResponseItem != null) {
            intent.putExtra(AppConstants.GEO_FENCE, getGeoFenceResponseItem);
        }
        intent.putExtra(AppConstants.CAR_LOCATION, this.carLatLng);
        startActivityForResult(intent, 145);
    }

    private final void resetFlags() {
        this.isOneAnimationRunning = false;
        this.currentAnim = "";
    }

    private final void setACOnOffIcon(@DrawableRes int i2) {
        AppCompatImageView appCompatImageView;
        if (!isAdded() || getActivity() == null || getActivity() == null || (appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivACOnOff)) == null) {
            return;
        }
        appCompatImageView.setImageResource(i2);
    }

    private final void setACStatus(String str) {
        AppCompatTextView appCompatTextView;
        int i2;
        Locale locale = Locale.ROOT;
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        String lowerCase2 = AppConstants.AC_STATUS_ON.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
            int i3 = R.id.tvAcStatus;
            ((AppCompatTextView) _$_findCachedViewById(i3)).setText(requireContext().getString(uat.psa.mym.mycitroenconnect.R.string.txt_ac_on));
            setACOnOffIcon(uat.psa.mym.mycitroenconnect.R.drawable.ic_ac_on);
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i3);
            i2 = uat.psa.mym.mycitroenconnect.R.style.Font10spSemiBoldHotGrey100;
        } else {
            int i4 = R.id.tvAcStatus;
            ((AppCompatTextView) _$_findCachedViewById(i4)).setText(requireContext().getString(uat.psa.mym.mycitroenconnect.R.string.txt_ac_off));
            setACOnOffIcon(uat.psa.mym.mycitroenconnect.R.drawable.ic_ac_off);
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i4);
            i2 = uat.psa.mym.mycitroenconnect.R.style.Font10spRegularHotGrey100;
        }
        appCompatTextView.setTextAppearance(i2);
    }

    private final void setBatteryPercentage(String str, int i2) {
        FragmentActivity activity;
        boolean isBlank;
        int i3;
        if (!isAdded() || getActivity() == null || (activity = getActivity()) == null) {
            return;
        }
        isBlank = StringsKt__StringsJVMKt.isBlank(str);
        if (!isBlank) {
            if ((str.length() > 0) && !Intrinsics.areEqual(str, "")) {
                MaterialTextView materialTextView = (MaterialTextView) _$_findCachedViewById(R.id.tvBatteryPercentage);
                if (materialTextView != null) {
                    materialTextView.setText(" (" + str + "%)");
                }
                if (Intrinsics.areEqual(SharedPref.Companion.getVehicleType(activity), AppConstants.ICE)) {
                    ((MaterialTextView) _$_findCachedViewById(R.id.tvKilometerLabel)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.dte));
                    ((MaterialTextView) _$_findCachedViewById(R.id.tvBatteryLabel)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.fuel));
                    _$_findCachedViewById(R.id.battery_nine).setVisibility(8);
                    View _$_findCachedViewById = _$_findCachedViewById(R.id.battery_first);
                    switch (i2) {
                        case 1:
                            _$_findCachedViewById.setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_empty_bg);
                            _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            break;
                        case 2:
                            _$_findCachedViewById.setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            break;
                        case 3:
                            _$_findCachedViewById.setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            break;
                        case 4:
                            _$_findCachedViewById.setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            break;
                        case 5:
                            _$_findCachedViewById.setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            break;
                        case 6:
                            _$_findCachedViewById.setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            break;
                        case 7:
                            _$_findCachedViewById.setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            break;
                        case 8:
                            _$_findCachedViewById.setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            i3 = R.id.battery_eight;
                            _$_findCachedViewById(i3).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                            return;
                        default:
                            _$_findCachedViewById.setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                            break;
                    }
                    i3 = R.id.battery_eight;
                    _$_findCachedViewById(i3).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                    return;
                }
                ((MaterialTextView) _$_findCachedViewById(R.id.tvKilometerLabel)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.txt_km_range));
                ((MaterialTextView) _$_findCachedViewById(R.id.tvBatteryLabel)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.battery));
                i3 = R.id.battery_nine;
                _$_findCachedViewById(i3).setVisibility(0);
                switch (i2) {
                    case 1:
                        _$_findCachedViewById(R.id.battery_first).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_empty_bg);
                        _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_eight).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        break;
                    case 2:
                        _$_findCachedViewById(R.id.battery_first).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_eight).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        break;
                    case 3:
                        _$_findCachedViewById(R.id.battery_first).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_eight).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        break;
                    case 4:
                        _$_findCachedViewById(R.id.battery_first).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_eight).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        break;
                    case 5:
                        _$_findCachedViewById(R.id.battery_first).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_eight).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        break;
                    case 6:
                        _$_findCachedViewById(R.id.battery_first).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_eight).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        break;
                    case 7:
                        _$_findCachedViewById(R.id.battery_first).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_eight).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        break;
                    case 8:
                        _$_findCachedViewById(R.id.battery_first).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_eight).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        break;
                    case 9:
                        _$_findCachedViewById(R.id.battery_first).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(R.id.battery_eight).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        _$_findCachedViewById(i3).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.battery_full_bg);
                        return;
                    default:
                        _$_findCachedViewById(R.id.battery_first).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_second).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_three).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_four).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_five).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_six).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_seven).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        _$_findCachedViewById(R.id.battery_eight).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                        break;
                }
                _$_findCachedViewById(i3).setBackgroundResource(uat.psa.mym.mycitroenconnect.R.drawable.empty_battery_indicator_bg);
                return;
            }
        }
        MaterialTextView materialTextView2 = (MaterialTextView) _$_findCachedViewById(R.id.tvBatteryPercentage);
        if (materialTextView2 == null) {
            return;
        }
        materialTextView2.setText("0%");
    }

    private final void setCarImage() {
        ConstraintSet constraintSet = new ConstraintSet();
        int i2 = R.id.rlCarImage;
        constraintSet.clone((ConstraintLayout) _$_findCachedViewById(i2));
        constraintSet.connect(uat.psa.mym.mycitroenconnect.R.id.ivCarMode, 4, uat.psa.mym.mycitroenconnect.R.id.rlCarImage, 4, 0);
        constraintSet.connect(uat.psa.mym.mycitroenconnect.R.id.ivCarMode, 3, uat.psa.mym.mycitroenconnect.R.id.rlCarImage, 3, 0);
        constraintSet.connect(uat.psa.mym.mycitroenconnect.R.id.ivCarMode, 1, uat.psa.mym.mycitroenconnect.R.id.rlCarImage, 1, 0);
        constraintSet.connect(uat.psa.mym.mycitroenconnect.R.id.ivCarMode, 2, uat.psa.mym.mycitroenconnect.R.id.rlCarImage, 2, 0);
        constraintSet.applyTo((ConstraintLayout) _$_findCachedViewById(i2));
    }

    private final void setChargingStatus(String str) {
        int i2;
        AppCompatTextView appCompatTextView;
        int i3;
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        if (Intrinsics.areEqual(lowerCase, "slow_charging")) {
            i2 = R.id.tvFastChargingHome;
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i2);
            i3 = uat.psa.mym.mycitroenconnect.R.string.slow_charging_text;
        } else if (!Intrinsics.areEqual(lowerCase, "quick_charging")) {
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvFastChargingHome)).setVisibility(8);
            return;
        } else {
            i2 = R.id.tvFastChargingHome;
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i2);
            i3 = uat.psa.mym.mycitroenconnect.R.string.fast_charging_text;
        }
        appCompatTextView.setText(getString(i3));
        ((AppCompatTextView) _$_findCachedViewById(i2)).setVisibility(0);
    }

    private final void setConstraintToView() {
        ConstraintSet constraintSet = new ConstraintSet();
        int i2 = R.id.rlCarImage;
        constraintSet.clone((ConstraintLayout) _$_findCachedViewById(i2));
        constraintSet.connect(((AppCompatImageView) _$_findCachedViewById(R.id.ivCarMode)).getId(), 1, ((ConstraintLayout) _$_findCachedViewById(i2)).getId(), 2, 0);
        constraintSet.applyTo((ConstraintLayout) _$_findCachedViewById(i2));
    }

    private final void setDistanceToEmpty(String str) {
        MaterialTextView materialTextView = (MaterialTextView) _$_findCachedViewById(R.id.tvKms);
        if (materialTextView == null) {
            return;
        }
        materialTextView.setText(str);
    }

    private final void setEngineOnOffIcon(@DrawableRes int i2) {
        AppCompatImageView appCompatImageView;
        if (!isAdded() || getActivity() == null || getActivity() == null || (appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivEngineOnOff)) == null) {
            return;
        }
        appCompatImageView.setImageResource(i2);
    }

    private final void setHeightInPercentage(String str, double d2, String str2, boolean z) {
        AppCompatImageView chargingStatusIndicator;
        int i2;
        if (z) {
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvFastChargingHome)).setVisibility(0);
            int i3 = R.id.chargingStatusIndicator;
            ((AppCompatImageView) _$_findCachedViewById(i3)).setVisibility(0);
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvChargingStatus)).setVisibility(0);
            _$_findCachedViewById(R.id.rel_car_overlay).setVisibility(0);
            if (str2 != null) {
                int i4 = R.id.tvChargingRemainingTime;
                ((AppCompatTextView) _$_findCachedViewById(i4)).setText(str2 + " mins");
                ((AppCompatTextView) _$_findCachedViewById(i4)).setVisibility(0);
            }
            if (d2 > 40.0d) {
                chargingStatusIndicator = (AppCompatImageView) _$_findCachedViewById(i3);
                Intrinsics.checkNotNullExpressionValue(chargingStatusIndicator, "chargingStatusIndicator");
                i2 = uat.psa.mym.mycitroenconnect.R.drawable.ic_battery_green_indicator;
            } else {
                chargingStatusIndicator = (AppCompatImageView) _$_findCachedViewById(i3);
                Intrinsics.checkNotNullExpressionValue(chargingStatusIndicator, "chargingStatusIndicator");
                i2 = uat.psa.mym.mycitroenconnect.R.drawable.ic_battery_red_indicator;
            }
            ExtensionsKt.setDrawable(chargingStatusIndicator, i2);
        } else {
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvFastChargingHome)).setVisibility(8);
            int i5 = R.id.tvChargingRemainingTime;
            ((AppCompatTextView) _$_findCachedViewById(i5)).setVisibility(8);
            ((AppCompatImageView) _$_findCachedViewById(R.id.chargingStatusIndicator)).setVisibility(8);
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvChargingStatus)).setVisibility(8);
            _$_findCachedViewById(R.id.rel_car_overlay).setVisibility(8);
            ((AppCompatTextView) _$_findCachedViewById(i5)).setVisibility(8);
        }
        if (str != null) {
            setChargingStatus(str);
        }
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvChargingStatus)).setText(((int) d2) + " %");
        View _$_findCachedViewById = _$_findCachedViewById(R.id.rel_car_overlay);
        ViewGroup.LayoutParams layoutParams = _$_findCachedViewById.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.matchConstraintPercentHeight = (float) ((100 - d2) * 0.0075d);
        _$_findCachedViewById.setLayoutParams(layoutParams2);
    }

    private final void setIgnitionStatus(String str) {
        FragmentActivity activity;
        boolean isBlank;
        AppCompatTextView tvVehicleLocation;
        if (!isAdded() || getActivity() == null || (activity = getActivity()) == null) {
            return;
        }
        if (str == null || str.length() == 0) {
            return;
        }
        SharedPref.Companion.setIgnitionState(activity, str);
        Locale locale = Locale.ROOT;
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        String lowerCase2 = AppConstants.IGNITION_STATUS_CONTACT.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
            int i2 = R.id.tvEngineStatus;
            ((AppCompatTextView) _$_findCachedViewById(i2)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.txt_engine_on));
            ((AppCompatTextView) _$_findCachedViewById(i2)).setTextAppearance(uat.psa.mym.mycitroenconnect.R.style.Font10spSemiBoldHotGrey100);
            setEngineOnOffIcon(uat.psa.mym.mycitroenconnect.R.drawable.ic_power_on);
            AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivCarMode);
            if (appCompatImageView != null) {
                appCompatImageView.setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.car_img_new);
            }
            AppCompatTextView tvVehicleLocation2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvVehicleLocation);
            if (tvVehicleLocation2 != null) {
                Intrinsics.checkNotNullExpressionValue(tvVehicleLocation2, "tvVehicleLocation");
                ExtensionsKt.hide(tvVehicleLocation2);
                return;
            }
            return;
        }
        int i3 = R.id.tvEngineStatus;
        ((AppCompatTextView) _$_findCachedViewById(i3)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.txt_engine_off));
        ((AppCompatTextView) _$_findCachedViewById(i3)).setTextAppearance(uat.psa.mym.mycitroenconnect.R.style.Font10spRegularHotGrey100);
        setEngineOnOffIcon(uat.psa.mym.mycitroenconnect.R.drawable.ic_power_off);
        if (Intrinsics.areEqual(this.mCurrentDisplayMode, getString(uat.psa.mym.mycitroenconnect.R.string.home))) {
            setCarImage();
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) _$_findCachedViewById(R.id.ivCarMode);
            if (appCompatImageView2 != null) {
                appCompatImageView2.setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.car_img_side);
            }
        }
        int i4 = R.id.tvVehicleLocation;
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i4);
        if (String.valueOf(appCompatTextView != null ? appCompatTextView.getText() : null).length() > 0) {
            AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(i4);
            isBlank = StringsKt__StringsJVMKt.isBlank(String.valueOf(appCompatTextView2 != null ? appCompatTextView2.getText() : null));
            if (!(!isBlank) || (tvVehicleLocation = (AppCompatTextView) _$_findCachedViewById(i4)) == null) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(tvVehicleLocation, "tvVehicleLocation");
            ExtensionsKt.show(tvVehicleLocation);
        }
    }

    private final void setListeners() {
        this.animationListener = this;
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) _$_findCachedViewById(R.id.llUpdatedTime);
        if (linearLayoutCompat != null) {
            linearLayoutCompat.setOnClickListener(this);
        }
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivEdit);
        if (appCompatImageView != null) {
            appCompatImageView.setOnClickListener(this);
        }
        SwitchMaterial switchMaterial = (SwitchMaterial) _$_findCachedViewById(R.id.switchFence);
        if (switchMaterial != null) {
            switchMaterial.setOnClickListener(this);
        }
    }

    private final void setTBoxIcon(@DrawableRes int i2) {
        AppCompatImageView appCompatImageView;
        if (!isAdded() || getActivity() == null || getActivity() == null || (appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivTBoxSignal)) == null) {
            return;
        }
        appCompatImageView.setImageResource(i2);
    }

    private final void setTBoxStatus(double d2) {
        int roundToInt;
        roundToInt = MathKt__MathJVMKt.roundToInt(d2);
        if (roundToInt != 0) {
            if (roundToInt == 1 || roundToInt == 2 || roundToInt == 3) {
                String string = getString(uat.psa.mym.mycitroenconnect.R.string.txt_tbox_connected);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.txt_tbox_connected)");
                setTBoxTextStatus(string, 1);
                return;
            } else if (roundToInt == 4 || roundToInt == 5 || ((int) d2) > 5) {
                String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.txt_tbox_connected);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.txt_tbox_connected)");
                setTBoxTextStatus(string2, 5);
                return;
            }
        }
        String string3 = getString(uat.psa.mym.mycitroenconnect.R.string.txt_tbox_disconnected);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.txt_tbox_disconnected)");
        setTBoxTextStatus(string3, -1);
    }

    private final void setTBoxTextStatus(String str, int i2) {
        AppCompatImageView appCompatImageView;
        int i3;
        if (!isAdded() || getActivity() == null || getActivity() == null) {
            return;
        }
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvConnectionStatus)).setText(str);
        if (i2 < 0) {
            ((LinearLayoutCompat) _$_findCachedViewById(R.id.llCloudStatus)).setBackground(getResources().getDrawable(uat.psa.mym.mycitroenconnect.R.drawable.red_tbox_signal_bg, null));
            appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivCloud);
            i3 = uat.psa.mym.mycitroenconnect.R.drawable.ic_disconnected_connection;
        } else if (i2 == 5) {
            ((LinearLayoutCompat) _$_findCachedViewById(R.id.llCloudStatus)).setBackground(getResources().getDrawable(uat.psa.mym.mycitroenconnect.R.drawable.green_tbox_signal_bg, null));
            appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivCloud);
            i3 = uat.psa.mym.mycitroenconnect.R.drawable.ic_cloud;
        } else {
            ((LinearLayoutCompat) _$_findCachedViewById(R.id.llCloudStatus)).setBackground(getResources().getDrawable(uat.psa.mym.mycitroenconnect.R.drawable.yellow_tbox_signal_bg, null));
            appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivCloud);
            i3 = uat.psa.mym.mycitroenconnect.R.drawable.ic_medium_connection;
        }
        appCompatImageView.setImageResource(i3);
    }

    private final void setUIData() {
        String stateOfCharge;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        StoredDashboardData saveDashboardData = companion.getSaveDashboardData(requireContext);
        if (saveDashboardData == null) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.llProgress);
            if (linearLayout != null) {
                ExtensionsKt.show(linearLayout);
            }
            ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.clData);
            if (constraintLayout != null) {
                ExtensionsKt.hide(constraintLayout);
                return;
            }
            return;
        }
        ConstraintLayout constraintLayout2 = (ConstraintLayout) _$_findCachedViewById(R.id.clData);
        if (constraintLayout2 != null) {
            ExtensionsKt.show(constraintLayout2);
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.llProgress);
        if (linearLayout2 != null) {
            ExtensionsKt.hide(linearLayout2);
        }
        setDistanceToEmpty(saveDashboardData.getDistanceToEmpty());
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        if (Intrinsics.areEqual(companion.getVehicleType(requireContext2), AppConstants.ICE)) {
            String fuelLevel = saveDashboardData.getFuelLevel();
            stateOfCharge = String.valueOf((int) Math.ceil((fuelLevel == null || fuelLevel.length() == 0 ? 0 : Integer.parseInt(saveDashboardData.getFuelLevel())) / 0.08d));
        } else {
            stateOfCharge = saveDashboardData.getStateOfCharge();
        }
        setBatteryPercentage(stateOfCharge, 0);
        setUpdateTime(saveDashboardData.getUpdatedTimeStamp());
        setVehicleLocation(saveDashboardData.getCarLat(), saveDashboardData.getCarLong(), saveDashboardData.getCarAddress());
        setIgnitionStatus(saveDashboardData.getIgnitionStatus());
        setACStatus(saveDashboardData.getAcSwitchStatus());
        setVehicleLockStatus(saveDashboardData.getVehicleLockedStatus());
        setTBoxStatus(saveDashboardData.getTboxSignalStrength());
    }

    private final void setUpdateTime(String str) {
        AppCompatTextView appCompatTextView;
        try {
            AppUtil.Companion companion = AppUtil.Companion;
            Date parse = new SimpleDateFormat(AppConstants.UTC_DATE_FORMAT, companion.getDefaultLocale()).parse(str);
            if (parse != null && (appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvUpdatedTime)) != null) {
                appCompatTextView.setText(getString(uat.psa.mym.mycitroenconnect.R.string.updated_time, companion.getTimeAgo(parse.getTime())));
            }
        } catch (Exception e2) {
            Logger.INSTANCE.e(e2.getLocalizedMessage());
        }
    }

    private final void setVehicleLocation(double d2, double d3, String str) {
        if (!(str.length() == 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append(d2);
            sb.append(AbstractJsonLexerKt.COMMA);
            sb.append(d3);
            if (!Intrinsics.areEqual(str, sb.toString())) {
                SharedPref.Companion companion = SharedPref.Companion;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                if (Intrinsics.areEqual(companion.getIgnitionState(requireContext), AppConstants.IGNITION_STATUS_CONTACT)) {
                    AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvVehicleLocation);
                    if (appCompatTextView != null) {
                        ExtensionsKt.hide(appCompatTextView);
                    }
                } else {
                    AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvVehicleLocation);
                    if (appCompatTextView2 != null) {
                        ExtensionsKt.show(appCompatTextView2);
                    }
                }
                AppCompatTextView appCompatTextView3 = (AppCompatTextView) _$_findCachedViewById(R.id.tvVehicleLocation);
                if (appCompatTextView3 == null) {
                    return;
                }
                appCompatTextView3.setText(str);
                return;
            }
        }
        AppCompatTextView appCompatTextView4 = (AppCompatTextView) _$_findCachedViewById(R.id.tvVehicleLocation);
        if (appCompatTextView4 != null) {
            ExtensionsKt.hide(appCompatTextView4);
        }
    }

    private final void setVehicleLockStatus(String str) {
        AppCompatTextView appCompatTextView;
        int i2;
        Locale locale = Locale.ROOT;
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        String lowerCase2 = AppConstants.VEHICLE_SUPER_LOCKED.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
            int i3 = R.id.tvLockStatus;
            ((AppCompatTextView) _$_findCachedViewById(i3)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.txt_lock_status));
            setVehicleLocked(uat.psa.mym.mycitroenconnect.R.drawable.ic_lock);
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i3);
            i2 = uat.psa.mym.mycitroenconnect.R.style.Font10spRegularHotGrey100;
        } else {
            int i4 = R.id.tvLockStatus;
            ((AppCompatTextView) _$_findCachedViewById(i4)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.txt_unlock_status));
            setVehicleLocked(uat.psa.mym.mycitroenconnect.R.drawable.ic_un_lock);
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i4);
            i2 = uat.psa.mym.mycitroenconnect.R.style.Font10spSemiBoldHotGrey100;
        }
        appCompatTextView.setTextAppearance(i2);
    }

    private final void setVehicleLocked(@DrawableRes int i2) {
        AppCompatImageView appCompatImageView;
        if (!isAdded() || getActivity() == null || getActivity() == null || (appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivCarLockOnOff)) == null) {
            return;
        }
        appCompatImageView.setImageResource(i2);
    }

    private final void setWeatherTemperature(String str, int i2) {
    }

    private final void showWeatherDetails(boolean z) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        if (!z) {
            layoutParams.gravity = GravityCompat.END;
            return;
        }
        layoutParams.gravity = 17;
        MaterialTextView materialTextView = (MaterialTextView) _$_findCachedViewById(R.id.tvBatteryPercentage);
        if (materialTextView == null) {
            return;
        }
        materialTextView.setTextAlignment(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startStreaming() {
        FragmentActivity activity;
        int i2;
        if (getActivity() == null || !isAdded() || (activity = getActivity()) == null) {
            return;
        }
        OkHttpClient unsafeOkHttpClient = getUnsafeOkHttpClient();
        StringBuilder sb = new StringBuilder();
        sb.append("https://lb1.cvip-preprod.citroen.in:40545/cpi/vehicleStatus/stream/");
        SharedPref.Companion companion = SharedPref.Companion;
        sb.append(companion.getVinNumber(activity));
        Request.Builder addHeader = new Request.Builder().url(sb.toString()).addHeader("Accept", "text/event-stream");
        final Request build = addHeader.addHeader("Authorization", "Bearer " + companion.getPrimaryUserToken(activity)).build();
        final EventSource.Factory createFactory = EventSources.createFactory(unsafeOkHttpClient);
        if (!this.shouldRetry || (i2 = this.retryCounter) == 0 || i2 >= this.maxRetryCount) {
            this.sseEventSource = createFactory.newEventSource(build, this.eventSourceListener);
            return;
        }
        Runnable runnable = new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.g
            @Override // java.lang.Runnable
            public final void run() {
                HomeFragment.m134startStreaming$lambda24$lambda23(HomeFragment.this, createFactory, build);
            }
        };
        Logger logger = Logger.INSTANCE;
        logger.e("Retry with count : " + this.retryCounter);
        new Handler(Looper.getMainLooper()).postDelayed(runnable, this.retryDelayMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startStreaming$lambda-24$lambda-23  reason: not valid java name */
    public static final void m134startStreaming$lambda24$lambda23(HomeFragment this$0, EventSource.Factory factory, Request request) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(factory, "$factory");
        Intrinsics.checkNotNullParameter(request, "$request");
        this$0.sseEventSource = factory.newEventSource(request, this$0.eventSourceListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateData() {
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String ignitionState = companion.getIgnitionState(requireContext);
        if (ignitionState != null) {
            Locale locale = Locale.ROOT;
            String lowerCase = ignitionState.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            String lowerCase2 = AppConstants.IGNITION_STATUS_CONTACT.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            if (!Intrinsics.areEqual(lowerCase, lowerCase2)) {
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                if (companion.isRefreshCommand(requireContext2)) {
                    Context requireContext3 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    companion.setRefreshCommandTime(requireContext3);
                    fetchRefreshCommand();
                }
            }
        }
        getVehicleDetails(true);
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

    public final void displayHome() {
        AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivCarMode);
        if (appCompatImageView != null) {
            ExtensionsKt.show(appCompatImageView);
        }
        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) _$_findCachedViewById(R.id.llUpdatedTime);
        if (linearLayoutCompat != null) {
            ExtensionsKt.show(linearLayoutCompat);
        }
        AppCompatImageView appCompatImageView2 = (AppCompatImageView) _$_findCachedViewById(R.id.ivValetBackground);
        if (appCompatImageView2 != null) {
            ExtensionsKt.hide(appCompatImageView2);
        }
        CardView cardView = (CardView) _$_findCachedViewById(R.id.cv_valet_mode);
        if (cardView != null) {
            ExtensionsKt.hide(cardView);
        }
        String string = requireActivity().getString(uat.psa.mym.mycitroenconnect.R.string.home);
        Intrinsics.checkNotNullExpressionValue(string, "requireActivity().getString(R.string.home)");
        this.mCurrentDisplayMode = string;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        StoredDashboardData saveDashboardData = companion.getSaveDashboardData(requireContext);
        if (saveDashboardData != null) {
            setIgnitionStatus(saveDashboardData.getIgnitionStatus());
        }
    }

    @Nullable
    public final AnimationInterface getAnimationListener() {
        return this.animationListener;
    }

    @NotNull
    public final String getCurrentDisplayMode() {
        return this.mCurrentDisplayMode;
    }

    public final void getGeoFenceData(@NotNull String type) {
        String string;
        String str;
        Intrinsics.checkNotNullParameter(type, "type");
        this.geoFence = null;
        if (!Intrinsics.areEqual(type, "location")) {
            if (Intrinsics.areEqual(type, AppConstants.GEO_FENCE_VALET)) {
                string = getString(uat.psa.mym.mycitroenconnect.R.string.label_valet_mode);
                str = "getString(R.string.label_valet_mode)";
            }
            AppUtil.Companion companion = AppUtil.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.showDialog(requireContext);
            GeoFenceService geoFenceService = new GeoFenceService();
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            SharedPref.Companion companion2 = SharedPref.Companion;
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            geoFenceService.getGeoFence(requireContext2, companion2.getVinNumber(requireContext3), type);
        }
        string = getString(uat.psa.mym.mycitroenconnect.R.string.label_geo_fence);
        str = "getString(R.string.label_geo_fence)";
        Intrinsics.checkNotNullExpressionValue(string, str);
        this.mCurrentDisplayMode = string;
        AppUtil.Companion companion3 = AppUtil.Companion;
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        companion3.showDialog(requireContext4);
        GeoFenceService geoFenceService2 = new GeoFenceService();
        Context requireContext22 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext22, "requireContext()");
        SharedPref.Companion companion22 = SharedPref.Companion;
        Context requireContext32 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext32, "requireContext()");
        geoFenceService2.getGeoFence(requireContext22, companion22.getVinNumber(requireContext32), type);
    }

    @NotNull
    public final String getPrevIgnitionStatus() {
        return this.prevIgnitionStatus;
    }

    @Subscribe
    public final void getResponse(@NotNull ErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        String apiName = response.getApiName();
        boolean z = false;
        switch (apiName.hashCode()) {
            case -1670654960:
                if (apiName.equals("RefreshCommand")) {
                    this.carPullRefreshRetryCounter++;
                    Logger.INSTANCE.e("RefreshCommand" + response.getMsg());
                    AppUtil.Companion.dismissDialog();
                    displayCarRefreshErrorMessage();
                    return;
                }
                break;
            case -1047860588:
                if (apiName.equals(AppConstants.API_NAME_DASHBOARD)) {
                    int i2 = R.id.llProgress;
                    LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(i2);
                    if (linearLayout != null && ExtensionsKt.isVisible(linearLayout)) {
                        z = true;
                    }
                    if (z) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.clData);
                        if (constraintLayout != null) {
                            ExtensionsKt.show(constraintLayout);
                        }
                        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(i2);
                        if (linearLayout2 != null) {
                            ExtensionsKt.hide(linearLayout2);
                        }
                    }
                    getVehicleLocation();
                    return;
                }
                break;
            case -740353638:
                if (apiName.equals(AppConstants.API_NAME_SNAP_VEHICLE_STATUS)) {
                    setTBoxStatus(0.0d);
                    MainActivity mainActivity = this.parentActivity;
                    if (mainActivity != null) {
                        mainActivity.getUnreadNotificationCount();
                        return;
                    }
                    return;
                }
                break;
            case 111972240:
                if (apiName.equals(AppConstants.GEO_FENCE_VALET)) {
                    redirectToValetFence();
                    return;
                }
                break;
            case 743548673:
                if (apiName.equals(AppConstants.API_VEHICLE_LOCATION)) {
                    getSnapShotVehicleStatus();
                    return;
                }
                break;
            case 1223440372:
                if (apiName.equals("weather")) {
                    Logger.INSTANCE.e("weather: " + response.getMsg());
                    showWeatherDetails(false);
                    return;
                }
                break;
            case 1901043637:
                if (apiName.equals("location")) {
                    redirectToCreateGeoFence();
                    return;
                }
                break;
        }
        Logger.INSTANCE.e("Error" + response.getApiName());
        AppUtil.Companion.dismissDialog();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, response.getMsg());
    }

    @Subscribe
    public final void getResponse(@NotNull PostCommonResponse response) {
        String str;
        Intrinsics.checkNotNullParameter(response, "response");
        String apiName = response.getApiName();
        if (Intrinsics.areEqual(apiName, "RefreshCommand")) {
            fetchDashboardData();
        } else if (Intrinsics.areEqual(apiName, GeoFenceService.activeDeactiveGeoFence)) {
            AppUtil.Companion.dismissDialog();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ExtensionsKt.showToast(requireContext, response.getMessage());
            GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFence;
            if (getGeoFenceResponseItem != null) {
                SwitchMaterial switchFence = (SwitchMaterial) _$_findCachedViewById(R.id.switchFence);
                if (switchFence != null) {
                    Intrinsics.checkNotNullExpressionValue(switchFence, "switchFence");
                    str = ExtensionsKt.getFenceStatus(switchFence);
                } else {
                    str = null;
                }
                getGeoFenceResponseItem.setFenceStatus(str);
                if (Intrinsics.areEqual(getGeoFenceResponseItem.getFenceStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)) {
                    displayAnimation();
                }
            }
        }
    }

    @Subscribe
    public final void getResponse(@NotNull VehicleStatus response) {
        String chargingStatus;
        double doubleValue;
        String timeToFullCharge;
        boolean z;
        String str;
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.getTboxSignalStrength() != null) {
            Double tboxSignalStrength = response.getTboxSignalStrength();
            if (tboxSignalStrength != null) {
                double doubleValue2 = tboxSignalStrength.doubleValue();
                SharedPref.Companion companion = SharedPref.Companion;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                StoredDashboardData saveDashboardData = companion.getSaveDashboardData(requireContext);
                StoredDashboardData storedDashboardData = saveDashboardData == null ? new StoredDashboardData(null, null, null, null, null, null, null, 0.0d, 0.0d, 0.0d, null, null, 0, 8191, null) : saveDashboardData;
                storedDashboardData.setTboxSignalStrength(doubleValue2);
                String ignitionStatus = response.getIgnitionStatus();
                Intrinsics.checkNotNull(ignitionStatus);
                storedDashboardData.setIgnitionStatus(ignitionStatus);
                String ignitionStatus2 = response.getIgnitionStatus();
                if (ignitionStatus2 != null) {
                    setIgnitionStatus(ignitionStatus2);
                }
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                if (!Intrinsics.areEqual(companion.getVehicleType(requireContext2), AppConstants.ICE)) {
                    if (response.getChargingStatus() != null) {
                        String chargingStatus2 = response.getChargingStatus();
                        if (chargingStatus2 != null) {
                            str = chargingStatus2.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        } else {
                            str = null;
                        }
                        String lowerCase = "No_charging".toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        if (!Intrinsics.areEqual(str, lowerCase)) {
                            AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.ivCarMode);
                            if (appCompatImageView != null) {
                                appCompatImageView.setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.car_img_new);
                            }
                            chargingStatus = response.getChargingStatus();
                            Double batteryCharging = response.getBatteryCharging();
                            Intrinsics.checkNotNull(batteryCharging);
                            doubleValue = batteryCharging.doubleValue();
                            timeToFullCharge = response.getTimeToFullCharge();
                            z = true;
                            setHeightInPercentage(chargingStatus, doubleValue, timeToFullCharge, z);
                        }
                    }
                    String ignitionStatus3 = response.getIgnitionStatus();
                    if (ignitionStatus3 != null) {
                        Locale locale = Locale.ROOT;
                        String lowerCase2 = ignitionStatus3.toLowerCase(locale);
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        String lowerCase3 = AppConstants.IGNITION_STATUS_CONTACT.toLowerCase(locale);
                        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                        if (Intrinsics.areEqual(lowerCase2, lowerCase3)) {
                            AppCompatImageView appCompatImageView2 = (AppCompatImageView) _$_findCachedViewById(R.id.ivCarMode);
                            if (appCompatImageView2 != null) {
                                appCompatImageView2.setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.car_img_new);
                            }
                        } else {
                            AppCompatImageView appCompatImageView3 = (AppCompatImageView) _$_findCachedViewById(R.id.ivCarMode);
                            if (appCompatImageView3 != null) {
                                appCompatImageView3.setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.car_img_side);
                            }
                            setCarImage();
                        }
                        chargingStatus = response.getChargingStatus();
                        Double batteryCharging2 = response.getBatteryCharging();
                        Intrinsics.checkNotNull(batteryCharging2);
                        doubleValue = batteryCharging2.doubleValue();
                        timeToFullCharge = response.getTimeToFullCharge();
                        z = false;
                        setHeightInPercentage(chargingStatus, doubleValue, timeToFullCharge, z);
                    }
                }
                Context requireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                companion.saveDashboardData(requireContext3, storedDashboardData);
                setTBoxStatus(doubleValue2);
            }
        } else {
            setTBoxStatus(0.0d);
        }
        MainActivity mainActivity = this.parentActivity;
        if (mainActivity != null) {
            mainActivity.getUnreadNotificationCount();
        }
    }

    @Subscribe
    public final void getResponse(@NotNull WeatherLocationResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        WeatherService weatherService = new WeatherService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        weatherService.getCurrentWeatherCondition(requireActivity, response.getKey());
    }

    @Subscribe
    public final void getResponse(@NotNull WeatherResponse response) {
        int roundToInt;
        Intrinsics.checkNotNullParameter(response, "response");
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        StoredDashboardData saveDashboardData = companion.getSaveDashboardData(requireContext);
        if (saveDashboardData == null) {
            saveDashboardData = new StoredDashboardData(null, null, null, null, null, null, null, 0.0d, 0.0d, 0.0d, null, null, 0, 8191, null);
        }
        roundToInt = MathKt__MathJVMKt.roundToInt(response.getTemperature().getMetric().getValue());
        saveDashboardData.setTemperature(String.valueOf(roundToInt));
        saveDashboardData.setWeatherIcon(response.getWeatherIcon());
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        companion.saveDashboardData(requireContext2, saveDashboardData);
    }

    @Subscribe
    public final void getResponse(@NotNull DashboardResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AppUtil.Companion.dismissDialog();
        int i2 = R.id.llProgress;
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(i2);
        boolean z = true;
        if (linearLayout != null && ExtensionsKt.isVisible(linearLayout)) {
            ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.clData);
            if (constraintLayout != null) {
                ExtensionsKt.show(constraintLayout);
            }
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(i2);
            if (linearLayout2 != null) {
                ExtensionsKt.hide(linearLayout2);
            }
        }
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        StoredDashboardData saveDashboardData = companion.getSaveDashboardData(requireContext);
        if (saveDashboardData == null) {
            saveDashboardData = new StoredDashboardData(null, null, null, null, null, null, null, 0.0d, 0.0d, 0.0d, null, null, 0, 8191, null);
        }
        saveDashboardData.setDistanceToEmpty(String.valueOf(response.getDistanceToEmpty()));
        saveDashboardData.setFuelLevel(String.valueOf((int) response.getFuelLevel()));
        saveDashboardData.setStateOfCharge(String.valueOf(response.getStateOfCharge()));
        saveDashboardData.setUpdatedTimeStamp(response.getUpdatedTimeStamp());
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        companion.saveDashboardData(requireContext2, saveDashboardData);
        setDistanceToEmpty(String.valueOf(response.getDistanceToEmpty()));
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        if (Intrinsics.areEqual(companion.getVehicleType(requireContext3), AppConstants.ICE)) {
            setBatteryPercentage(String.valueOf((int) Math.ceil(response.getFuelLevel() / 0.08d)), (int) response.getFuelLevel());
        } else {
            double fuelLevel = response.getFuelLevel();
            if (1.0d > fuelLevel || fuelLevel > 9.0d) {
                z = false;
            }
            setBatteryPercentage(String.valueOf(response.getStateOfCharge()), z ? (int) response.getFuelLevel() : 0);
        }
        setUpdateTime(response.getUpdatedTimeStamp());
        getVehicleLocation();
    }

    @Subscribe
    public final void getResponse(@NotNull VehicleLocationResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        int i2 = R.id.llProgress;
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(i2);
        boolean z = true;
        if (linearLayout == null || !ExtensionsKt.isVisible(linearLayout)) {
            z = false;
        }
        if (z) {
            ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.clData);
            if (constraintLayout != null) {
                ExtensionsKt.show(constraintLayout);
            }
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(i2);
            if (linearLayout2 != null) {
                ExtensionsKt.hide(linearLayout2);
            }
        }
        LatLng latLng = new LatLng(response.getCoordinate().getX(), response.getCoordinate().getY());
        this.carLatLng = latLng;
        AppUtil.Companion companion = AppUtil.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String addressNameShortString = companion.getAddressNameShortString(requireContext, latLng.latitude, latLng.longitude);
        SharedPref.Companion companion2 = SharedPref.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        StoredDashboardData saveDashboardData = companion2.getSaveDashboardData(requireContext2);
        if (saveDashboardData == null) {
            saveDashboardData = new StoredDashboardData(null, null, null, null, null, null, null, 0.0d, 0.0d, 0.0d, null, null, 0, 8191, null);
        }
        saveDashboardData.setCarLat(latLng.latitude);
        saveDashboardData.setCarLong(latLng.longitude);
        saveDashboardData.setCarAddress(addressNameShortString);
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        companion2.saveDashboardData(requireContext3, saveDashboardData);
        setVehicleLocation(latLng.latitude, latLng.longitude, addressNameShortString);
        getSnapShotVehicleStatus();
    }

    @Subscribe
    public final void getResponse(@NotNull GetGeoFenceResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        GetGeoFenceResponseItem getGeoFenceResponseItem = response.get(0);
        this.geoFence = getGeoFenceResponseItem;
        String fenceType = getGeoFenceResponseItem != null ? getGeoFenceResponseItem.getFenceType() : null;
        if (!Intrinsics.areEqual(fenceType, "location")) {
            if (Intrinsics.areEqual(fenceType, AppConstants.GEO_FENCE_VALET)) {
                displayValetMode();
            }
        } else if (!(!response.isEmpty()) || response.size() <= 0) {
            redirectToCreateGeoFence();
        } else {
            redirectToGeoFenceList();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated(message = "Deprecated in Java")
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        String str;
        StringBuilder sb;
        super.onActivityResult(i2, i3, intent);
        LocationUtility locationUtility = this.locationUtility;
        if (locationUtility != null) {
            locationUtility.onActivityResult(i2, i3, intent);
        }
        if (i3 == -1 && i2 == 145 && intent != null && intent.hasExtra(AppConstants.GEO_FENCE)) {
            GetGeoFenceResponseItem getGeoFenceResponseItem = (GetGeoFenceResponseItem) intent.getParcelableExtra(AppConstants.GEO_FENCE);
            this.geoFence = getGeoFenceResponseItem;
            if (getGeoFenceResponseItem != null) {
                ((SwitchMaterial) _$_findCachedViewById(R.id.switchFence)).setChecked(Intrinsics.areEqual(getGeoFenceResponseItem.getFenceStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS));
                Integer radius = getGeoFenceResponseItem.getRadius();
                Double valueOf = radius != null ? Double.valueOf(radius.intValue() / 1000) : null;
                if (valueOf == null || Intrinsics.areEqual(valueOf, 0.0d)) {
                    str = "";
                } else {
                    if (valueOf.doubleValue() < 1.0d) {
                        sb = new StringBuilder();
                        sb.append(valueOf);
                    } else {
                        sb = new StringBuilder();
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format("%.0f", Arrays.copyOf(new Object[]{valueOf}, 1));
                        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                        sb.append(format);
                    }
                    sb.append(" KM");
                    str = sb.toString();
                }
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvCardSubTitle)).setText(str);
            }
        }
    }

    @Override // com.psa.mym.mycitroenconnect.controller.fragments.AnimationInterface
    public void onAnimationComplete(@Nullable String str) {
        int i2;
        if (str != null) {
            switch (str.hashCode()) {
                case -946534275:
                    if (str.equals(AppConstants.ANIM_IGNITION_OFF_1)) {
                        int i3 = R.id.animationView;
                        ((LottieAnimationView) _$_findCachedViewById(i3)).removeAllAnimatorListeners();
                        ((LottieAnimationView) _$_findCachedViewById(i3)).removeAllUpdateListeners();
                        ((LottieAnimationView) _$_findCachedViewById(i3)).setSpeed(1.5f);
                        ((LottieAnimationView) _$_findCachedViewById(i3)).setAnimation("ignition_off.json");
                        ((LottieAnimationView) _$_findCachedViewById(i3)).addAnimatorListener(new Animator.AnimatorListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.HomeFragment$onAnimationComplete$1
                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationCancel(@Nullable Animator animator) {
                            }

                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationEnd(@Nullable Animator animator) {
                                Logger logger = Logger.INSTANCE;
                                logger.e("ignition_off: 2 -> Animation End");
                                logger.e("animateIgnitionOff: 2 -> Animation Rotation End:" + ((LottieAnimationView) HomeFragment.this._$_findCachedViewById(R.id.animationView)).getRotation());
                                AnimationInterface animationListener = HomeFragment.this.getAnimationListener();
                                if (animationListener != null) {
                                    animationListener.onAnimationComplete(AppConstants.ANIM_IGNITION_OFF_2);
                                }
                            }

                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationRepeat(@Nullable Animator animator) {
                            }

                            @Override // android.animation.Animator.AnimatorListener
                            public void onAnimationStart(@Nullable Animator animator) {
                                Logger logger = Logger.INSTANCE;
                                logger.e("ignition_off: 2 -> Animation Start");
                                logger.e("animateIgnitionOff: 2 -> Animation Rotation Start:" + ((LottieAnimationView) HomeFragment.this._$_findCachedViewById(R.id.animationView)).getRotation());
                            }
                        });
                        ((LottieAnimationView) _$_findCachedViewById(i3)).playAnimation();
                        return;
                    }
                    return;
                case -946534274:
                    if (str.equals(AppConstants.ANIM_IGNITION_OFF_2)) {
                        i2 = R.id.animationView;
                        break;
                    } else {
                        return;
                    }
                case 1354947381:
                    if (str.equals(AppConstants.ANIM_IGNITION_ON_1)) {
                        i2 = R.id.animationView;
                        ((LottieAnimationView) _$_findCachedViewById(i2)).animate().rotation(90.0f).setDuration(1500L).setInterpolator(new LinearInterpolator()).start();
                        break;
                    } else {
                        return;
                    }
                default:
                    return;
            }
            ((LottieAnimationView) _$_findCachedViewById(i2)).removeAllAnimatorListeners();
            ((LottieAnimationView) _$_findCachedViewById(i2)).removeAllUpdateListeners();
            resetFlags();
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(@Nullable CompoundButton compoundButton, boolean z) {
        String str = this.mCurrentDisplayMode;
        if (Intrinsics.areEqual(str, getString(uat.psa.mym.mycitroenconnect.R.string.label_geo_fence)) || Intrinsics.areEqual(str, getString(uat.psa.mym.mycitroenconnect.R.string.label_valet_mode))) {
            activeDeActiveGeofence();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        int i2 = R.id.switchFence;
        if (Intrinsics.areEqual(view, (SwitchMaterial) _$_findCachedViewById(i2)) || SystemClock.elapsedRealtime() - this.lastClickTime >= 1500) {
            this.lastClickTime = SystemClock.elapsedRealtime();
            if (Intrinsics.areEqual(view, (LinearLayoutCompat) _$_findCachedViewById(R.id.llUpdatedTime))) {
                animateUpdateButton();
            } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivEdit))) {
                if (Intrinsics.areEqual(this.mCurrentDisplayMode, getString(uat.psa.mym.mycitroenconnect.R.string.label_valet_mode))) {
                    redirectToValetFence();
                }
            } else if (Intrinsics.areEqual(view, (SwitchMaterial) _$_findCachedViewById(i2))) {
                String str = this.mCurrentDisplayMode;
                if (Intrinsics.areEqual(str, getString(uat.psa.mym.mycitroenconnect.R.string.label_geo_fence)) || Intrinsics.areEqual(str, getString(uat.psa.mym.mycitroenconnect.R.string.label_valet_mode))) {
                    activeDeActiveGeofence();
                }
            }
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_home, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LocationUtility locationUtility = this.locationUtility;
        if (locationUtility != null) {
            getLifecycle().removeObserver(locationUtility);
        }
        super.onDestroy();
        closeConnection();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        LocationUtility locationUtility = this.locationUtility;
        if (locationUtility != null) {
            getLifecycle().removeObserver(locationUtility);
        }
        closeConnection();
        this.parentActivity = null;
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated(message = "Deprecated in Java")
    public void onRequestPermissionsResult(int i2, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i2, permissions, grantResults);
        LocationUtility locationUtility = this.locationUtility;
        if (locationUtility != null) {
            locationUtility.onRequestPermissionsResult(i2, permissions, grantResults);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        e(this, false, 1, null);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        closeConnection();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        view.setFilterTouchesWhenObscured(true);
        init();
        setListeners();
        getArgumentsData();
        initializeLocation();
    }

    public final void setAnimationListener(@Nullable AnimationInterface animationInterface) {
        this.animationListener = animationInterface;
    }

    public final void setPrevIgnitionStatus(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.prevIgnitionStatus = str;
    }
}
