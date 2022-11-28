package com.psa.mym.mycitroenconnect.controller.activities.dashboard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.Coordinates;
import com.psa.mym.mycitroenconnect.api.body.geo_fence.GeoFenceBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.SpeedSettingFragment;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.MessageEvent;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import com.psa.mym.mycitroenconnect.model.geo_fence.LocationData;
import com.psa.mym.mycitroenconnect.model.geo_fence.PostGeoFenceResponse;
import com.psa.mym.mycitroenconnect.services.GeoFenceService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.MapExtensionKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class ValetActivity extends BusBaseActivity implements OnMapReadyCallback, CompoundButton.OnCheckedChangeListener {
    @Nullable
    private LatLng carLatLng;
    private double carLatitude;
    private double carLongitude;
    @Nullable
    private Circle circle;
    @Nullable
    private GetGeoFenceResponseItem geoFence;
    private GoogleMap googleMap;
    @Nullable
    private AppCompatButton[] mArrBtnRad;
    @Nullable
    private AppCompatButton[] mArrBtnSpeed;
    @Nullable
    private AppCompatButton[] mArrBtnTime;
    @Nullable
    private String mDlgMode;
    private SupportMapFragment mapFragment;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String carAddress = "";
    private double selectedRadius = 10.0d;
    @NotNull
    private String selectedTime = "";
    @NotNull
    private String selectedSpeedLimit = "";

    private final void activeDeactiveGeofence() {
        if (this.geoFence != null) {
            AppUtil.Companion.showDialog(this);
            GeoFenceService geoFenceService = new GeoFenceService();
            String vinNumber = SharedPref.Companion.getVinNumber(this);
            GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFence;
            String fenceId = getGeoFenceResponseItem != null ? getGeoFenceResponseItem.getFenceId() : null;
            Intrinsics.checkNotNull(fenceId);
            geoFenceService.activeDeactiveGeoFence(this, vinNumber, fenceId, getFenceStatus());
        }
    }

    private final void createValetMode() {
        GeoFenceBody geoFenceBody = new GeoFenceBody(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 262143, null);
        geoFenceBody.setFenceType(AppConstants.GEO_FENCE_VALET);
        geoFenceBody.setFenceGeometry(AppConstants.GEO_FENCE_GEOMETRY_CIRCLE);
        geoFenceBody.setFenceStatus(getFenceStatus());
        geoFenceBody.setTransitionType(AppConstants.GEO_FENCE_TRANSITION_OUT);
        geoFenceBody.setIdlingLimit(this.selectedTime);
        Calendar calendar = Calendar.getInstance();
        calendar.add(12, 1);
        calendar.set(13, 0);
        AppUtil.Companion companion = AppUtil.Companion;
        Date time = calendar.getTime();
        Intrinsics.checkNotNullExpressionValue(time, "cal.time");
        geoFenceBody.setStartTime(companion.getDateString(time, AppConstants.UTC_DATE_FORMAT));
        String idlingLimit = geoFenceBody.getIdlingLimit();
        Intrinsics.checkNotNull(idlingLimit);
        calendar.add(12, Integer.parseInt(idlingLimit));
        Date time2 = calendar.getTime();
        Intrinsics.checkNotNullExpressionValue(time2, "cal.time");
        geoFenceBody.setEndTime(companion.getDateString(time2, AppConstants.UTC_DATE_FORMAT));
        geoFenceBody.setRadius(Double.valueOf(this.selectedRadius * 1000));
        geoFenceBody.setCentre(new Coordinates(Double.valueOf(this.carLatitude), Double.valueOf(this.carLongitude)));
        geoFenceBody.setSpeedLimit(this.selectedSpeedLimit);
        Logger logger = Logger.INSTANCE;
        logger.e("Create Valet: " + geoFenceBody);
        new GeoFenceService().createGeoFence(this, SharedPref.Companion.getVinNumber(this), geoFenceBody);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0152  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void displayCircle() {
        GoogleMap googleMap;
        float f2;
        GoogleMap googleMap2;
        GoogleMap googleMap3 = this.googleMap;
        GoogleMap googleMap4 = null;
        if (googleMap3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap3 = null;
        }
        googleMap3.clear();
        Circle circle = this.circle;
        if (circle != null) {
            circle.remove();
        }
        double d2 = this.selectedRadius * 1000;
        LatLng latLng = new LatLng(this.carLatitude, this.carLongitude);
        GoogleMap googleMap5 = this.googleMap;
        if (googleMap5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap5 = null;
        }
        MapExtensionKt.drawMarker(googleMap5, this, latLng, R.drawable.ic_red_car_pin_marker, this.carAddress);
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(d2);
        circleOptions.strokeWidth(4.0f);
        circleOptions.strokeColor(Color.parseColor("#00000000"));
        circleOptions.fillColor(Color.parseColor("#1A9D0605"));
        GoogleMap googleMap6 = this.googleMap;
        if (googleMap6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            googleMap6 = null;
        }
        googleMap6.setMaxZoomPreference(25.0f);
        double d3 = this.selectedRadius;
        boolean z = true;
        if (0.0d <= d3 && d3 <= 1.0d) {
            GoogleMap googleMap7 = this.googleMap;
            if (googleMap7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                googleMap = null;
            } else {
                googleMap = googleMap7;
            }
            f2 = 14.0f;
        } else {
            if (2.0d <= d3 && d3 <= 5.0d) {
                GoogleMap googleMap8 = this.googleMap;
                if (googleMap8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                    googleMap = null;
                } else {
                    googleMap = googleMap8;
                }
                f2 = 12.0f;
            } else {
                if (6.0d <= d3 && d3 <= 10.0d) {
                    GoogleMap googleMap9 = this.googleMap;
                    if (googleMap9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                        googleMap = null;
                    } else {
                        googleMap = googleMap9;
                    }
                    f2 = 10.0f;
                } else {
                    if (11.0d <= d3 && d3 <= 30.0d) {
                        GoogleMap googleMap10 = this.googleMap;
                        if (googleMap10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                            googleMap = null;
                        } else {
                            googleMap = googleMap10;
                        }
                        f2 = 9.0f;
                    } else {
                        if (31.0d <= d3 && d3 <= 50.0d) {
                            GoogleMap googleMap11 = this.googleMap;
                            if (googleMap11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                                googleMap = null;
                            } else {
                                googleMap = googleMap11;
                            }
                            f2 = 8.0f;
                        } else {
                            if (!(51.0d <= d3 && d3 <= 100.0d)) {
                                if (999.0d > d3 || d3 > 1000.0d) {
                                    z = false;
                                }
                                if (z) {
                                    GoogleMap googleMap12 = this.googleMap;
                                    if (googleMap12 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                                        googleMap = null;
                                    } else {
                                        googleMap = googleMap12;
                                    }
                                    f2 = 16.0f;
                                }
                                googleMap2 = this.googleMap;
                                if (googleMap2 != null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                                } else {
                                    googleMap4 = googleMap2;
                                }
                                this.circle = googleMap4.addCircle(circleOptions);
                            }
                            GoogleMap googleMap13 = this.googleMap;
                            if (googleMap13 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                                googleMap = null;
                            } else {
                                googleMap = googleMap13;
                            }
                            f2 = 6.0f;
                        }
                    }
                }
            }
        }
        MapExtensionKt.moveCameraOnMap$default(googleMap, f2, latLng, false, 4, null);
        googleMap2 = this.googleMap;
        if (googleMap2 != null) {
        }
        this.circle = googleMap4.addCircle(circleOptions);
    }

    private final String getFenceStatus() {
        SwitchCompat switchCompat = (SwitchCompat) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutValetHeader).findViewById(com.psa.mym.mycitroenconnect.R.id.switchDashHeader);
        Intrinsics.checkNotNullExpressionValue(switchCompat, "layoutValetHeader.switchDashHeader");
        return ExtensionsKt.getFenceStatus(switchCompat);
    }

    private final void getIntentData() {
        if (getIntent() != null) {
            if (getIntent().hasExtra(AppConstants.GEO_FENCE)) {
                this.geoFence = (GetGeoFenceResponseItem) getIntent().getParcelableExtra(AppConstants.GEO_FENCE);
            }
            if (getIntent().hasExtra(AppConstants.CAR_LOCATION)) {
                LatLng latLng = (LatLng) getIntent().getParcelableExtra(AppConstants.CAR_LOCATION);
                this.carLatLng = latLng;
                if (latLng != null) {
                    this.carLatitude = latLng.latitude;
                    this.carLongitude = latLng.longitude;
                }
                setAddress();
            }
        }
    }

    private final void initViews() {
        if (SharedPref.Companion.isGuestUser(this)) {
            AppCompatButton btnRadSet = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadSet);
            Intrinsics.checkNotNullExpressionValue(btnRadSet, "btnRadSet");
            ExtensionsKt.hide(btnRadSet);
            ((SwitchCompat) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutValetHeader).findViewById(com.psa.mym.mycitroenconnect.R.id.switchDashHeader)).setClickable(false);
            AppCompatButton btnRadEditVal = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEditVal);
            Intrinsics.checkNotNullExpressionValue(btnRadEditVal, "btnRadEditVal");
            ExtensionsKt.clearDrawables(btnRadEditVal);
            AppCompatButton btnSpeedEditVal = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeedEditVal);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal, "btnSpeedEditVal");
            ExtensionsKt.clearDrawables(btnSpeedEditVal);
            AppCompatButton btnIdlingEditVal = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdlingEditVal);
            Intrinsics.checkNotNullExpressionValue(btnIdlingEditVal, "btnIdlingEditVal");
            ExtensionsKt.clearDrawables(btnIdlingEditVal);
        } else {
            AppCompatButton btnRadSet2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadSet);
            Intrinsics.checkNotNullExpressionValue(btnRadSet2, "btnRadSet");
            ExtensionsKt.show(btnRadSet2);
            ((SwitchCompat) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutValetHeader).findViewById(com.psa.mym.mycitroenconnect.R.id.switchDashHeader)).setClickable(true);
        }
        int i2 = com.psa.mym.mycitroenconnect.R.id.layoutValetHeader;
        View _$_findCachedViewById = _$_findCachedViewById(i2);
        int i3 = com.psa.mym.mycitroenconnect.R.id.tvDbSubTitle;
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById.findViewById(i3);
        Intrinsics.checkNotNullExpressionValue(appCompatTextView, "layoutValetHeader.tvDbSubTitle");
        ExtensionsKt.show(appCompatTextView);
        SwitchCompat switchCompat = (SwitchCompat) _$_findCachedViewById(i2).findViewById(com.psa.mym.mycitroenconnect.R.id.switchDashHeader);
        Intrinsics.checkNotNullExpressionValue(switchCompat, "layoutValetHeader.switchDashHeader");
        ExtensionsKt.show(switchCompat);
        ((AppCompatTextView) _$_findCachedViewById(i2).findViewById(com.psa.mym.mycitroenconnect.R.id.tvDbHeaderTitle)).setText(getString(R.string.label_valet_mode));
        ((AppCompatTextView) _$_findCachedViewById(i2).findViewById(i3)).setText(getString(R.string.label_valet_sub_title));
        AppCompatButton btnValetRad250m = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad250m);
        Intrinsics.checkNotNullExpressionValue(btnValetRad250m, "btnValetRad250m");
        AppCompatButton btnValetRad500m = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad500m);
        Intrinsics.checkNotNullExpressionValue(btnValetRad500m, "btnValetRad500m");
        AppCompatButton btnValetRad750m = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad750m);
        Intrinsics.checkNotNullExpressionValue(btnValetRad750m, "btnValetRad750m");
        AppCompatButton btnValetRad1km = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad1km);
        Intrinsics.checkNotNullExpressionValue(btnValetRad1km, "btnValetRad1km");
        AppCompatButton btnRadEditVal2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEditVal);
        Intrinsics.checkNotNullExpressionValue(btnRadEditVal2, "btnRadEditVal");
        AppCompatButton btnRadEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
        Intrinsics.checkNotNullExpressionValue(btnRadEdit, "btnRadEdit");
        this.mArrBtnRad = new AppCompatButton[]{btnValetRad250m, btnValetRad500m, btnValetRad750m, btnValetRad1km, btnRadEditVal2, btnRadEdit};
        AppCompatButton btnSpeed10 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed10);
        Intrinsics.checkNotNullExpressionValue(btnSpeed10, "btnSpeed10");
        AppCompatButton btnSpeed20 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed20);
        Intrinsics.checkNotNullExpressionValue(btnSpeed20, "btnSpeed20");
        AppCompatButton btnSpeed30 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed30);
        Intrinsics.checkNotNullExpressionValue(btnSpeed30, "btnSpeed30");
        AppCompatButton btnSpeed40 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed40);
        Intrinsics.checkNotNullExpressionValue(btnSpeed40, "btnSpeed40");
        AppCompatButton btnSpeed50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed50);
        Intrinsics.checkNotNullExpressionValue(btnSpeed50, "btnSpeed50");
        AppCompatButton btnSpeedEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeedEdit);
        Intrinsics.checkNotNullExpressionValue(btnSpeedEdit, "btnSpeedEdit");
        AppCompatButton btnSpeedEditVal2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeedEditVal);
        Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal2, "btnSpeedEditVal");
        this.mArrBtnSpeed = new AppCompatButton[]{btnSpeed10, btnSpeed20, btnSpeed30, btnSpeed40, btnSpeed50, btnSpeedEdit, btnSpeedEditVal2};
        AppCompatButton btnIdling2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling2);
        Intrinsics.checkNotNullExpressionValue(btnIdling2, "btnIdling2");
        AppCompatButton btnIdling4 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling4);
        Intrinsics.checkNotNullExpressionValue(btnIdling4, "btnIdling4");
        AppCompatButton btnIdling6 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling6);
        Intrinsics.checkNotNullExpressionValue(btnIdling6, "btnIdling6");
        AppCompatButton btnIdling8 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling8);
        Intrinsics.checkNotNullExpressionValue(btnIdling8, "btnIdling8");
        AppCompatButton btnIdlingEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdlingEdit);
        Intrinsics.checkNotNullExpressionValue(btnIdlingEdit, "btnIdlingEdit");
        AppCompatButton btnIdlingEditVal2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdlingEditVal);
        Intrinsics.checkNotNullExpressionValue(btnIdlingEditVal2, "btnIdlingEditVal");
        this.mArrBtnTime = new AppCompatButton[]{btnIdling2, btnIdling4, btnIdling6, btnIdling8, btnIdlingEdit, btnIdlingEditVal2};
        setViewData();
        setAddress();
        SupportMapFragment newInstance = SupportMapFragment.newInstance();
        Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance()");
        this.mapFragment = newInstance;
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "supportFragmentManager.beginTransaction()");
        SupportMapFragment supportMapFragment = this.mapFragment;
        SupportMapFragment supportMapFragment2 = null;
        if (supportMapFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapFragment");
            supportMapFragment = null;
        }
        beginTransaction.add(R.id.mapContainer, supportMapFragment);
        beginTransaction.commit();
        SupportMapFragment supportMapFragment3 = this.mapFragment;
        if (supportMapFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapFragment");
        } else {
            supportMapFragment2 = supportMapFragment3;
        }
        supportMapFragment2.getMapAsync(this);
    }

    private final void onRadiusDialogDismiss(String str) {
        boolean isBlank;
        String str2;
        boolean contains$default;
        String replace$default;
        CharSequence trim;
        boolean contains$default2;
        String replace$default2;
        CharSequence trim2;
        boolean contains$default3;
        String replace$default3;
        CharSequence trim3;
        isBlank = StringsKt__StringsJVMKt.isBlank(str);
        if (!isBlank) {
            int i2 = 0;
            if (!(str.length() > 0) || Intrinsics.areEqual(str, AppConstants.DEFAULT_VAL_SPEED_DLG) || (str2 = this.mDlgMode) == null) {
                return;
            }
            int hashCode = str2.hashCode();
            if (hashCode == -1239146154) {
                if (str2.equals(AppConstants.PAGE_MODE_MAX_SPEED_SETTING)) {
                    contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "kmph", false, 2, (Object) null);
                    if (contains$default) {
                        int i3 = com.psa.mym.mycitroenconnect.R.id.btnSpeedEditVal;
                        replace$default = StringsKt__StringsJVMKt.replace$default(str, "kmph", "", false, 4, (Object) null);
                        trim = StringsKt__StringsKt.trim((CharSequence) replace$default);
                        ((AppCompatButton) _$_findCachedViewById(i3)).setText(trim.toString());
                        AppCompatButton btnSpeedEditVal = (AppCompatButton) _$_findCachedViewById(i3);
                        Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal, "btnSpeedEditVal");
                        ExtensionsKt.show(btnSpeedEditVal);
                        AppCompatButton btnSpeedEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeedEdit);
                        Intrinsics.checkNotNullExpressionValue(btnSpeedEdit, "btnSpeedEdit");
                        ExtensionsKt.hide(btnSpeedEdit);
                        AppCompatButton btnSpeed50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed50);
                        Intrinsics.checkNotNullExpressionValue(btnSpeed50, "btnSpeed50");
                        ExtensionsKt.hide(btnSpeed50);
                    }
                    AppCompatButton[] appCompatButtonArr = this.mArrBtnSpeed;
                    if (appCompatButtonArr != null) {
                        int length = appCompatButtonArr.length;
                        while (i2 < length) {
                            ExtensionsKt.onSquareBtnDeselect(appCompatButtonArr[i2], this);
                            i2++;
                        }
                    }
                    int i4 = com.psa.mym.mycitroenconnect.R.id.btnSpeedEditVal;
                    AppCompatButton btnSpeedEditVal2 = (AppCompatButton) _$_findCachedViewById(i4);
                    Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal2, "btnSpeedEditVal");
                    ExtensionsKt.onSquareBtnSelected(btnSpeedEditVal2, this);
                    this.selectedSpeedLimit = ((AppCompatButton) _$_findCachedViewById(i4)).getText().toString();
                }
            } else if (hashCode != 915556993) {
                if (hashCode == 1551724885 && str2.equals(AppConstants.DLG_MODE_TIME_SELECTOR)) {
                    contains$default3 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "Mins", false, 2, (Object) null);
                    if (contains$default3) {
                        int i5 = com.psa.mym.mycitroenconnect.R.id.btnIdlingEditVal;
                        replace$default3 = StringsKt__StringsJVMKt.replace$default(str, "Mins", "", false, 4, (Object) null);
                        trim3 = StringsKt__StringsKt.trim((CharSequence) replace$default3);
                        ((AppCompatButton) _$_findCachedViewById(i5)).setText(trim3.toString());
                        AppCompatButton btnIdlingEditVal = (AppCompatButton) _$_findCachedViewById(i5);
                        Intrinsics.checkNotNullExpressionValue(btnIdlingEditVal, "btnIdlingEditVal");
                        ExtensionsKt.show(btnIdlingEditVal);
                        AppCompatButton btnIdlingEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdlingEdit);
                        Intrinsics.checkNotNullExpressionValue(btnIdlingEdit, "btnIdlingEdit");
                        ExtensionsKt.hide(btnIdlingEdit);
                        AppCompatButton btnIdling8 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling8);
                        Intrinsics.checkNotNullExpressionValue(btnIdling8, "btnIdling8");
                        ExtensionsKt.hide(btnIdling8);
                    }
                    AppCompatButton[] appCompatButtonArr2 = this.mArrBtnTime;
                    if (appCompatButtonArr2 != null) {
                        int length2 = appCompatButtonArr2.length;
                        while (i2 < length2) {
                            ExtensionsKt.onSquareBtnDeselect(appCompatButtonArr2[i2], this);
                            i2++;
                        }
                    }
                    int i6 = com.psa.mym.mycitroenconnect.R.id.btnIdlingEditVal;
                    AppCompatButton btnIdlingEditVal2 = (AppCompatButton) _$_findCachedViewById(i6);
                    Intrinsics.checkNotNullExpressionValue(btnIdlingEditVal2, "btnIdlingEditVal");
                    ExtensionsKt.onSquareBtnSelected(btnIdlingEditVal2, this);
                    this.selectedTime = ((AppCompatButton) _$_findCachedViewById(i6)).getText().toString();
                    updateRadTime();
                }
            } else if (str2.equals(AppConstants.PAGE_MODE_VALET_RADIUS)) {
                contains$default2 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "km", false, 2, (Object) null);
                if (contains$default2) {
                    int i7 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
                    replace$default2 = StringsKt__StringsJVMKt.replace$default(str, "km", "", false, 4, (Object) null);
                    trim2 = StringsKt__StringsKt.trim((CharSequence) replace$default2);
                    ((AppCompatButton) _$_findCachedViewById(i7)).setText(trim2.toString());
                    AppCompatButton btnRadEditVal = (AppCompatButton) _$_findCachedViewById(i7);
                    Intrinsics.checkNotNullExpressionValue(btnRadEditVal, "btnRadEditVal");
                    ExtensionsKt.show(btnRadEditVal);
                    AppCompatButton btnValetRad1km = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad1km);
                    Intrinsics.checkNotNullExpressionValue(btnValetRad1km, "btnValetRad1km");
                    ExtensionsKt.hide(btnValetRad1km);
                    AppCompatButton btnRadEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
                    Intrinsics.checkNotNullExpressionValue(btnRadEdit, "btnRadEdit");
                    ExtensionsKt.hide(btnRadEdit);
                    AppCompatButton[] appCompatButtonArr3 = this.mArrBtnRad;
                    if (appCompatButtonArr3 != null) {
                        int length3 = appCompatButtonArr3.length;
                        while (i2 < length3) {
                            ExtensionsKt.onSquareBtnDeselect(appCompatButtonArr3[i2], this);
                            i2++;
                        }
                    }
                    int i8 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
                    AppCompatButton btnRadEditVal2 = (AppCompatButton) _$_findCachedViewById(i8);
                    Intrinsics.checkNotNullExpressionValue(btnRadEditVal2, "btnRadEditVal");
                    ExtensionsKt.onSquareBtnSelected(btnRadEditVal2, this);
                    this.selectedRadius = Double.parseDouble(((AppCompatButton) _$_findCachedViewById(i8)).getText().toString());
                    updateRadTime();
                    displayCircle();
                }
            }
        }
    }

    private final void setAddress() {
        this.carAddress = AppUtil.Companion.getAddressNameString(this, this.carLatitude, this.carLongitude);
    }

    private final void setIdlingTimeButtonValue() {
        AppCompatButton btnIdling8;
        String str;
        AppCompatButton[] appCompatButtonArr = this.mArrBtnTime;
        if (appCompatButtonArr != null) {
            for (AppCompatButton appCompatButton : appCompatButtonArr) {
                ExtensionsKt.onSquareBtnDeselect(appCompatButton, this);
            }
        }
        String str2 = this.selectedTime;
        int hashCode = str2.hashCode();
        if (hashCode == 50) {
            if (str2.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                btnIdling8 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling2);
                str = "btnIdling2";
                Intrinsics.checkNotNullExpressionValue(btnIdling8, str);
            }
            int i2 = com.psa.mym.mycitroenconnect.R.id.btnIdlingEditVal;
            ((AppCompatButton) _$_findCachedViewById(i2)).setText(this.selectedTime);
            AppCompatButton btnIdlingEditVal = (AppCompatButton) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(btnIdlingEditVal, "btnIdlingEditVal");
            ExtensionsKt.onSquareBtnSelected(btnIdlingEditVal, this);
            AppCompatButton btnIdlingEditVal2 = (AppCompatButton) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(btnIdlingEditVal2, "btnIdlingEditVal");
            ExtensionsKt.show(btnIdlingEditVal2);
            AppCompatButton btnIdlingEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdlingEdit);
            Intrinsics.checkNotNullExpressionValue(btnIdlingEdit, "btnIdlingEdit");
            ExtensionsKt.hide(btnIdlingEdit);
            AppCompatButton btnIdling82 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling8);
            Intrinsics.checkNotNullExpressionValue(btnIdling82, "btnIdling8");
            ExtensionsKt.hide(btnIdling82);
            return;
        } else if (hashCode == 52) {
            if (str2.equals("4")) {
                btnIdling8 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling4);
                str = "btnIdling4";
                Intrinsics.checkNotNullExpressionValue(btnIdling8, str);
            }
            int i22 = com.psa.mym.mycitroenconnect.R.id.btnIdlingEditVal;
            ((AppCompatButton) _$_findCachedViewById(i22)).setText(this.selectedTime);
            AppCompatButton btnIdlingEditVal3 = (AppCompatButton) _$_findCachedViewById(i22);
            Intrinsics.checkNotNullExpressionValue(btnIdlingEditVal3, "btnIdlingEditVal");
            ExtensionsKt.onSquareBtnSelected(btnIdlingEditVal3, this);
            AppCompatButton btnIdlingEditVal22 = (AppCompatButton) _$_findCachedViewById(i22);
            Intrinsics.checkNotNullExpressionValue(btnIdlingEditVal22, "btnIdlingEditVal");
            ExtensionsKt.show(btnIdlingEditVal22);
            AppCompatButton btnIdlingEdit2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdlingEdit);
            Intrinsics.checkNotNullExpressionValue(btnIdlingEdit2, "btnIdlingEdit");
            ExtensionsKt.hide(btnIdlingEdit2);
            AppCompatButton btnIdling822 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling8);
            Intrinsics.checkNotNullExpressionValue(btnIdling822, "btnIdling8");
            ExtensionsKt.hide(btnIdling822);
            return;
        } else {
            if (hashCode != 54) {
                if (hashCode == 56 && str2.equals("8")) {
                    btnIdling8 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling8);
                    Intrinsics.checkNotNullExpressionValue(btnIdling8, "btnIdling8");
                }
            } else if (str2.equals("6")) {
                btnIdling8 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling6);
                str = "btnIdling6";
                Intrinsics.checkNotNullExpressionValue(btnIdling8, str);
            }
            int i222 = com.psa.mym.mycitroenconnect.R.id.btnIdlingEditVal;
            ((AppCompatButton) _$_findCachedViewById(i222)).setText(this.selectedTime);
            AppCompatButton btnIdlingEditVal32 = (AppCompatButton) _$_findCachedViewById(i222);
            Intrinsics.checkNotNullExpressionValue(btnIdlingEditVal32, "btnIdlingEditVal");
            ExtensionsKt.onSquareBtnSelected(btnIdlingEditVal32, this);
            AppCompatButton btnIdlingEditVal222 = (AppCompatButton) _$_findCachedViewById(i222);
            Intrinsics.checkNotNullExpressionValue(btnIdlingEditVal222, "btnIdlingEditVal");
            ExtensionsKt.show(btnIdlingEditVal222);
            AppCompatButton btnIdlingEdit22 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdlingEdit);
            Intrinsics.checkNotNullExpressionValue(btnIdlingEdit22, "btnIdlingEdit");
            ExtensionsKt.hide(btnIdlingEdit22);
            AppCompatButton btnIdling8222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling8);
            Intrinsics.checkNotNullExpressionValue(btnIdling8222, "btnIdling8");
            ExtensionsKt.hide(btnIdling8222);
            return;
        }
        ExtensionsKt.onSquareBtnSelected(btnIdling8, this);
    }

    private final void setListeners() {
        int i2 = com.psa.mym.mycitroenconnect.R.id.layoutValetHeader;
        ((AppCompatImageView) _$_findCachedViewById(i2).findViewById(com.psa.mym.mycitroenconnect.R.id.ivBack)).setOnClickListener(this);
        if (!SharedPref.Companion.isGuestUser(this)) {
            ((SwitchCompat) _$_findCachedViewById(i2).findViewById(com.psa.mym.mycitroenconnect.R.id.switchDashHeader)).setOnCheckedChangeListener(this);
            AppCompatButton[] appCompatButtonArr = this.mArrBtnRad;
            if (appCompatButtonArr != null) {
                for (AppCompatButton appCompatButton : appCompatButtonArr) {
                    appCompatButton.setOnClickListener(this);
                }
            }
            AppCompatButton[] appCompatButtonArr2 = this.mArrBtnSpeed;
            if (appCompatButtonArr2 != null) {
                for (AppCompatButton appCompatButton2 : appCompatButtonArr2) {
                    appCompatButton2.setOnClickListener(this);
                }
            }
            AppCompatButton[] appCompatButtonArr3 = this.mArrBtnTime;
            if (appCompatButtonArr3 != null) {
                for (AppCompatButton appCompatButton3 : appCompatButtonArr3) {
                    appCompatButton3.setOnClickListener(this);
                }
            }
        }
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadSet)).setOnClickListener(this);
    }

    private final void setOnBackPress(boolean z) {
        if (z) {
            GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFence;
            if (getGeoFenceResponseItem != null) {
                getGeoFenceResponseItem.setFenceStatus(getFenceStatus());
            }
        } else {
            GetGeoFenceResponseItem getGeoFenceResponseItem2 = this.geoFence;
            if (getGeoFenceResponseItem2 != null) {
                getGeoFenceResponseItem2.setFenceStatus(getFenceStatus());
                try {
                    getGeoFenceResponseItem2.setRadius(Integer.valueOf((int) (this.selectedRadius * 1000)));
                } catch (Exception unused) {
                }
                try {
                    getGeoFenceResponseItem2.setSpeedLimit(Integer.valueOf(Integer.parseInt(this.selectedSpeedLimit)));
                } catch (Exception unused2) {
                }
                try {
                    getGeoFenceResponseItem2.setIdlingLimit(Integer.valueOf(Integer.parseInt(this.selectedTime)));
                } catch (Exception unused3) {
                }
                getGeoFenceResponseItem2.setCentre(new LocationData(Double.valueOf(this.carLatitude), Double.valueOf(this.carLongitude)));
            }
        }
        Intent intent = new Intent();
        intent.putExtra(AppConstants.GEO_FENCE, this.geoFence);
        setResult(-1, intent);
        finish();
    }

    private final void setRadiusButtonValue() {
        AppCompatButton btnValetRad1km;
        String str;
        AppCompatButton[] appCompatButtonArr = this.mArrBtnRad;
        if (appCompatButtonArr != null) {
            for (AppCompatButton appCompatButton : appCompatButtonArr) {
                ExtensionsKt.onSquareBtnDeselect(appCompatButton, this);
            }
        }
        String valueOf = String.valueOf(this.selectedRadius);
        switch (valueOf.hashCode()) {
            case 47607:
                if (valueOf.equals("0.5")) {
                    btnValetRad1km = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad500m);
                    str = "btnValetRad500m";
                    Intrinsics.checkNotNullExpressionValue(btnValetRad1km, str);
                    ExtensionsKt.onSquareBtnSelected(btnValetRad1km, this);
                    return;
                }
                int i2 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf(this.selectedRadius)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                ((AppCompatButton) _$_findCachedViewById(i2)).setText(format);
                AppCompatButton btnRadEditVal = (AppCompatButton) _$_findCachedViewById(i2);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal, "btnRadEditVal");
                ExtensionsKt.onSquareBtnSelected(btnRadEditVal, this);
                AppCompatButton btnRadEditVal2 = (AppCompatButton) _$_findCachedViewById(i2);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal2, "btnRadEditVal");
                ExtensionsKt.show(btnRadEditVal2);
                AppCompatButton btnRadEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
                Intrinsics.checkNotNullExpressionValue(btnRadEdit, "btnRadEdit");
                ExtensionsKt.hide(btnRadEdit);
                AppCompatButton btnValetRad1km2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad1km);
                Intrinsics.checkNotNullExpressionValue(btnValetRad1km2, "btnValetRad1km");
                ExtensionsKt.hide(btnValetRad1km2);
                return;
            case 48563:
                if (valueOf.equals("1.0")) {
                    btnValetRad1km = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad1km);
                    Intrinsics.checkNotNullExpressionValue(btnValetRad1km, "btnValetRad1km");
                    ExtensionsKt.onSquareBtnSelected(btnValetRad1km, this);
                    return;
                }
                int i22 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String format2 = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf(this.selectedRadius)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                ((AppCompatButton) _$_findCachedViewById(i22)).setText(format2);
                AppCompatButton btnRadEditVal3 = (AppCompatButton) _$_findCachedViewById(i22);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal3, "btnRadEditVal");
                ExtensionsKt.onSquareBtnSelected(btnRadEditVal3, this);
                AppCompatButton btnRadEditVal22 = (AppCompatButton) _$_findCachedViewById(i22);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal22, "btnRadEditVal");
                ExtensionsKt.show(btnRadEditVal22);
                AppCompatButton btnRadEdit2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
                Intrinsics.checkNotNullExpressionValue(btnRadEdit2, "btnRadEdit");
                ExtensionsKt.hide(btnRadEdit2);
                AppCompatButton btnValetRad1km22 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad1km);
                Intrinsics.checkNotNullExpressionValue(btnValetRad1km22, "btnValetRad1km");
                ExtensionsKt.hide(btnValetRad1km22);
                return;
            case 1475777:
                if (valueOf.equals("0.25")) {
                    btnValetRad1km = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad250m);
                    str = "btnValetRad250m";
                    Intrinsics.checkNotNullExpressionValue(btnValetRad1km, str);
                    ExtensionsKt.onSquareBtnSelected(btnValetRad1km, this);
                    return;
                }
                int i222 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
                StringCompanionObject stringCompanionObject22 = StringCompanionObject.INSTANCE;
                String format22 = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf(this.selectedRadius)}, 1));
                Intrinsics.checkNotNullExpressionValue(format22, "format(format, *args)");
                ((AppCompatButton) _$_findCachedViewById(i222)).setText(format22);
                AppCompatButton btnRadEditVal32 = (AppCompatButton) _$_findCachedViewById(i222);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal32, "btnRadEditVal");
                ExtensionsKt.onSquareBtnSelected(btnRadEditVal32, this);
                AppCompatButton btnRadEditVal222 = (AppCompatButton) _$_findCachedViewById(i222);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal222, "btnRadEditVal");
                ExtensionsKt.show(btnRadEditVal222);
                AppCompatButton btnRadEdit22 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
                Intrinsics.checkNotNullExpressionValue(btnRadEdit22, "btnRadEdit");
                ExtensionsKt.hide(btnRadEdit22);
                AppCompatButton btnValetRad1km222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad1km);
                Intrinsics.checkNotNullExpressionValue(btnValetRad1km222, "btnValetRad1km");
                ExtensionsKt.hide(btnValetRad1km222);
                return;
            case 1475932:
                if (valueOf.equals("0.75")) {
                    btnValetRad1km = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad750m);
                    str = "btnValetRad750m";
                    Intrinsics.checkNotNullExpressionValue(btnValetRad1km, str);
                    ExtensionsKt.onSquareBtnSelected(btnValetRad1km, this);
                    return;
                }
                int i2222 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
                StringCompanionObject stringCompanionObject222 = StringCompanionObject.INSTANCE;
                String format222 = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf(this.selectedRadius)}, 1));
                Intrinsics.checkNotNullExpressionValue(format222, "format(format, *args)");
                ((AppCompatButton) _$_findCachedViewById(i2222)).setText(format222);
                AppCompatButton btnRadEditVal322 = (AppCompatButton) _$_findCachedViewById(i2222);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal322, "btnRadEditVal");
                ExtensionsKt.onSquareBtnSelected(btnRadEditVal322, this);
                AppCompatButton btnRadEditVal2222 = (AppCompatButton) _$_findCachedViewById(i2222);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal2222, "btnRadEditVal");
                ExtensionsKt.show(btnRadEditVal2222);
                AppCompatButton btnRadEdit222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
                Intrinsics.checkNotNullExpressionValue(btnRadEdit222, "btnRadEdit");
                ExtensionsKt.hide(btnRadEdit222);
                AppCompatButton btnValetRad1km2222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad1km);
                Intrinsics.checkNotNullExpressionValue(btnValetRad1km2222, "btnValetRad1km");
                ExtensionsKt.hide(btnValetRad1km2222);
                return;
            default:
                int i22222 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
                StringCompanionObject stringCompanionObject2222 = StringCompanionObject.INSTANCE;
                String format2222 = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf(this.selectedRadius)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2222, "format(format, *args)");
                ((AppCompatButton) _$_findCachedViewById(i22222)).setText(format2222);
                AppCompatButton btnRadEditVal3222 = (AppCompatButton) _$_findCachedViewById(i22222);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal3222, "btnRadEditVal");
                ExtensionsKt.onSquareBtnSelected(btnRadEditVal3222, this);
                AppCompatButton btnRadEditVal22222 = (AppCompatButton) _$_findCachedViewById(i22222);
                Intrinsics.checkNotNullExpressionValue(btnRadEditVal22222, "btnRadEditVal");
                ExtensionsKt.show(btnRadEditVal22222);
                AppCompatButton btnRadEdit2222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit);
                Intrinsics.checkNotNullExpressionValue(btnRadEdit2222, "btnRadEdit");
                ExtensionsKt.hide(btnRadEdit2222);
                AppCompatButton btnValetRad1km22222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad1km);
                Intrinsics.checkNotNullExpressionValue(btnValetRad1km22222, "btnValetRad1km");
                ExtensionsKt.hide(btnValetRad1km22222);
                return;
        }
    }

    private final void setSpeedLimitButtonValue() {
        AppCompatButton btnSpeed50;
        String str;
        AppCompatButton[] appCompatButtonArr = this.mArrBtnSpeed;
        if (appCompatButtonArr != null) {
            for (AppCompatButton appCompatButton : appCompatButtonArr) {
                ExtensionsKt.onSquareBtnDeselect(appCompatButton, this);
            }
        }
        String str2 = this.selectedSpeedLimit;
        int hashCode = str2.hashCode();
        if (hashCode == 1567) {
            if (str2.equals("10")) {
                btnSpeed50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed10);
                str = "btnSpeed10";
                Intrinsics.checkNotNullExpressionValue(btnSpeed50, str);
            }
            int i2 = com.psa.mym.mycitroenconnect.R.id.btnSpeedEditVal;
            ((AppCompatButton) _$_findCachedViewById(i2)).setText(this.selectedSpeedLimit);
            AppCompatButton btnSpeedEditVal = (AppCompatButton) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal, "btnSpeedEditVal");
            ExtensionsKt.onSquareBtnSelected(btnSpeedEditVal, this);
            AppCompatButton btnSpeedEditVal2 = (AppCompatButton) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal2, "btnSpeedEditVal");
            ExtensionsKt.show(btnSpeedEditVal2);
            AppCompatButton btnSpeedEdit = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeedEdit);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEdit, "btnSpeedEdit");
            ExtensionsKt.hide(btnSpeedEdit);
            AppCompatButton btnSpeed502 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed50);
            Intrinsics.checkNotNullExpressionValue(btnSpeed502, "btnSpeed50");
            ExtensionsKt.hide(btnSpeed502);
            return;
        } else if (hashCode == 1598) {
            if (str2.equals("20")) {
                btnSpeed50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed20);
                str = "btnSpeed20";
                Intrinsics.checkNotNullExpressionValue(btnSpeed50, str);
            }
            int i22 = com.psa.mym.mycitroenconnect.R.id.btnSpeedEditVal;
            ((AppCompatButton) _$_findCachedViewById(i22)).setText(this.selectedSpeedLimit);
            AppCompatButton btnSpeedEditVal3 = (AppCompatButton) _$_findCachedViewById(i22);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal3, "btnSpeedEditVal");
            ExtensionsKt.onSquareBtnSelected(btnSpeedEditVal3, this);
            AppCompatButton btnSpeedEditVal22 = (AppCompatButton) _$_findCachedViewById(i22);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal22, "btnSpeedEditVal");
            ExtensionsKt.show(btnSpeedEditVal22);
            AppCompatButton btnSpeedEdit2 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeedEdit);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEdit2, "btnSpeedEdit");
            ExtensionsKt.hide(btnSpeedEdit2);
            AppCompatButton btnSpeed5022 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed50);
            Intrinsics.checkNotNullExpressionValue(btnSpeed5022, "btnSpeed50");
            ExtensionsKt.hide(btnSpeed5022);
            return;
        } else if (hashCode == 1629) {
            if (str2.equals("30")) {
                btnSpeed50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed30);
                str = "btnSpeed30";
                Intrinsics.checkNotNullExpressionValue(btnSpeed50, str);
            }
            int i222 = com.psa.mym.mycitroenconnect.R.id.btnSpeedEditVal;
            ((AppCompatButton) _$_findCachedViewById(i222)).setText(this.selectedSpeedLimit);
            AppCompatButton btnSpeedEditVal32 = (AppCompatButton) _$_findCachedViewById(i222);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal32, "btnSpeedEditVal");
            ExtensionsKt.onSquareBtnSelected(btnSpeedEditVal32, this);
            AppCompatButton btnSpeedEditVal222 = (AppCompatButton) _$_findCachedViewById(i222);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal222, "btnSpeedEditVal");
            ExtensionsKt.show(btnSpeedEditVal222);
            AppCompatButton btnSpeedEdit22 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeedEdit);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEdit22, "btnSpeedEdit");
            ExtensionsKt.hide(btnSpeedEdit22);
            AppCompatButton btnSpeed50222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed50);
            Intrinsics.checkNotNullExpressionValue(btnSpeed50222, "btnSpeed50");
            ExtensionsKt.hide(btnSpeed50222);
            return;
        } else {
            if (hashCode != 1660) {
                if (hashCode == 1691 && str2.equals("50")) {
                    btnSpeed50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed50);
                    Intrinsics.checkNotNullExpressionValue(btnSpeed50, "btnSpeed50");
                }
            } else if (str2.equals("40")) {
                btnSpeed50 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed40);
                str = "btnSpeed40";
                Intrinsics.checkNotNullExpressionValue(btnSpeed50, str);
            }
            int i2222 = com.psa.mym.mycitroenconnect.R.id.btnSpeedEditVal;
            ((AppCompatButton) _$_findCachedViewById(i2222)).setText(this.selectedSpeedLimit);
            AppCompatButton btnSpeedEditVal322 = (AppCompatButton) _$_findCachedViewById(i2222);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal322, "btnSpeedEditVal");
            ExtensionsKt.onSquareBtnSelected(btnSpeedEditVal322, this);
            AppCompatButton btnSpeedEditVal2222 = (AppCompatButton) _$_findCachedViewById(i2222);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEditVal2222, "btnSpeedEditVal");
            ExtensionsKt.show(btnSpeedEditVal2222);
            AppCompatButton btnSpeedEdit222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeedEdit);
            Intrinsics.checkNotNullExpressionValue(btnSpeedEdit222, "btnSpeedEdit");
            ExtensionsKt.hide(btnSpeedEdit222);
            AppCompatButton btnSpeed502222 = (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed50);
            Intrinsics.checkNotNullExpressionValue(btnSpeed502222, "btnSpeed50");
            ExtensionsKt.hide(btnSpeed502222);
            return;
        }
        ExtensionsKt.onSquareBtnSelected(btnSpeed50, this);
    }

    private final void setValet() {
        AppUtil.Companion.showDialog(this);
        if (this.geoFence != null) {
            updateValetMode();
        } else {
            createValetMode();
        }
    }

    private final void setViewData() {
        GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFence;
        if (getGeoFenceResponseItem == null || getGeoFenceResponseItem == null) {
            return;
        }
        ((SwitchCompat) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutValetHeader).findViewById(com.psa.mym.mycitroenconnect.R.id.switchDashHeader)).setChecked(Intrinsics.areEqual(getGeoFenceResponseItem.getFenceStatus(), ExifInterface.GPS_MEASUREMENT_IN_PROGRESS));
        setAddress();
        Integer radius = getGeoFenceResponseItem.getRadius();
        Intrinsics.checkNotNull(radius);
        this.selectedRadius = radius.intValue() / 1000;
        setRadiusButtonValue();
        this.selectedSpeedLimit = String.valueOf(getGeoFenceResponseItem.getSpeedLimit());
        setSpeedLimitButtonValue();
        this.selectedTime = String.valueOf(getGeoFenceResponseItem.getIdlingLimit());
        setIdlingTimeButtonValue();
        updateRadTime();
    }

    private final void updateRadTime() {
        String str;
        boolean contains$default;
        double d2 = this.selectedRadius;
        if (d2 == 0.0d) {
            str = "";
        } else {
            String valueOf = String.valueOf(d2);
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) valueOf, (CharSequence) ".0", false, 2, (Object) null);
            if (contains$default) {
                valueOf = StringsKt__StringsJVMKt.replace$default(valueOf, ".0", "", false, 4, (Object) null);
            }
            str = valueOf + " KM";
        }
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutValetHeader).findViewById(com.psa.mym.mycitroenconnect.R.id.tvDbSubTitle)).setText(str);
    }

    private final void updateValetMode() {
        GeoFenceBody geoFenceBody = new GeoFenceBody(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 262143, null);
        GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFence;
        if (getGeoFenceResponseItem != null) {
            String fenceType = getGeoFenceResponseItem.getFenceType();
            Intrinsics.checkNotNull(fenceType);
            geoFenceBody.setFenceType(fenceType);
            geoFenceBody.setFenceGeometry(AppConstants.GEO_FENCE_GEOMETRY_CIRCLE);
            geoFenceBody.setFenceStatus(getFenceStatus());
            geoFenceBody.setTransitionType(AppConstants.GEO_FENCE_TRANSITION_OUT);
            geoFenceBody.setIdlingLimit(this.selectedTime);
            Calendar calendar = Calendar.getInstance();
            calendar.add(12, 1);
            calendar.set(13, 0);
            AppUtil.Companion companion = AppUtil.Companion;
            Date time = calendar.getTime();
            Intrinsics.checkNotNullExpressionValue(time, "cal.time");
            geoFenceBody.setStartTime(companion.getDateString(time, AppConstants.UTC_DATE_FORMAT));
            String idlingLimit = geoFenceBody.getIdlingLimit();
            Intrinsics.checkNotNull(idlingLimit);
            calendar.add(12, Integer.parseInt(idlingLimit));
            Date time2 = calendar.getTime();
            Intrinsics.checkNotNullExpressionValue(time2, "cal.time");
            geoFenceBody.setEndTime(companion.getDateString(time2, AppConstants.UTC_DATE_FORMAT));
            geoFenceBody.setRadius(Double.valueOf(this.selectedRadius * 1000));
            geoFenceBody.setCentre(new Coordinates(Double.valueOf(this.carLatitude), Double.valueOf(this.carLongitude)));
            geoFenceBody.setSpeedLimit(this.selectedSpeedLimit);
            Logger logger = Logger.INSTANCE;
            logger.e("Update Valet: " + geoFenceBody);
            GeoFenceService geoFenceService = new GeoFenceService();
            String vinNumber = SharedPref.Companion.getVinNumber(this);
            String fenceId = getGeoFenceResponseItem.getFenceId();
            Intrinsics.checkNotNull(fenceId);
            geoFenceService.updateGeoFence(this, vinNumber, fenceId, geoFenceBody);
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
    public final void getErrorResponse(@NotNull ErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AppUtil.Companion.dismissDialog();
        ExtensionsKt.showToast(this, response.getMsg());
    }

    @Subscribe
    public final void getResponse(@NotNull PostCommonResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AppUtil.Companion.dismissDialog();
        ExtensionsKt.showToast(this, response.getMessage());
        GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFence;
        if (getGeoFenceResponseItem != null) {
            getGeoFenceResponseItem.setFenceStatus(getFenceStatus());
        }
    }

    @Subscribe
    public final void getResponse(@NotNull PostGeoFenceResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AppUtil.Companion.dismissDialog();
        String message = response.getMessage();
        Intrinsics.checkNotNull(message);
        ExtensionsKt.showToast(this, message);
        setOnBackPress(false);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        setOnBackPress(true);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(@Nullable CompoundButton compoundButton, boolean z) {
        activeDeactiveGeofence();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Bundle bundle;
        SpeedSettingFragment speedSettingFragment;
        String str;
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.layoutValetHeader).findViewById(com.psa.mym.mycitroenconnect.R.id.ivBack))) {
            setOnBackPress(true);
            return;
        }
        int i2 = 0;
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad250m)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad500m)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad750m)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnValetRad1km))) {
            AppCompatButton[] appCompatButtonArr = this.mArrBtnRad;
            if (appCompatButtonArr != null) {
                int length = appCompatButtonArr.length;
                while (i2 < length) {
                    ExtensionsKt.onSquareBtnDeselect(appCompatButtonArr[i2], this);
                    i2++;
                }
            }
            Objects.requireNonNull(view, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatButton");
            AppCompatButton appCompatButton = (AppCompatButton) view;
            ExtensionsKt.onSquareBtnSelected(appCompatButton, this);
            this.selectedRadius = Double.parseDouble(appCompatButton.getText().toString());
            updateRadTime();
            displayCircle();
            return;
        }
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEdit)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadEditVal))) {
            bundle = new Bundle();
            int i3 = com.psa.mym.mycitroenconnect.R.id.btnRadEditVal;
            if (((AppCompatButton) _$_findCachedViewById(i3)).getVisibility() == 0) {
                bundle.putString(AppConstants.SPEED_SELECTED_VAL, ((AppCompatButton) _$_findCachedViewById(i3)).getText().toString());
            }
            speedSettingFragment = new SpeedSettingFragment();
            str = AppConstants.PAGE_MODE_VALET_RADIUS;
        } else {
            if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed10)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed20)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed30)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed40)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeed50))) {
                AppCompatButton[] appCompatButtonArr2 = this.mArrBtnSpeed;
                if (appCompatButtonArr2 != null) {
                    int length2 = appCompatButtonArr2.length;
                    while (i2 < length2) {
                        ExtensionsKt.onSquareBtnDeselect(appCompatButtonArr2[i2], this);
                        i2++;
                    }
                }
                Objects.requireNonNull(view, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatButton");
                AppCompatButton appCompatButton2 = (AppCompatButton) view;
                ExtensionsKt.onSquareBtnSelected(appCompatButton2, this);
                this.selectedSpeedLimit = appCompatButton2.getText().toString();
                return;
            }
            if (!(Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeedEdit)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnSpeedEditVal)))) {
                if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling2)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling4)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling6)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdling8))) {
                    AppCompatButton[] appCompatButtonArr3 = this.mArrBtnTime;
                    if (appCompatButtonArr3 != null) {
                        int length3 = appCompatButtonArr3.length;
                        while (i2 < length3) {
                            ExtensionsKt.onSquareBtnDeselect(appCompatButtonArr3[i2], this);
                            i2++;
                        }
                    }
                    Objects.requireNonNull(view, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatButton");
                    AppCompatButton appCompatButton3 = (AppCompatButton) view;
                    ExtensionsKt.onSquareBtnSelected(appCompatButton3, this);
                    this.selectedTime = appCompatButton3.getText().toString();
                    updateRadTime();
                    return;
                }
                if (!(Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdlingEdit)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnIdlingEditVal)))) {
                    if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnRadSet))) {
                        setValet();
                        return;
                    }
                    return;
                }
                Bundle bundle2 = new Bundle();
                int i4 = com.psa.mym.mycitroenconnect.R.id.btnIdlingEditVal;
                if (((AppCompatButton) _$_findCachedViewById(i4)).getVisibility() == 0) {
                    bundle2.putString(AppConstants.SPEED_SELECTED_VAL, ((AppCompatButton) _$_findCachedViewById(i4)).getText().toString());
                }
                SpeedSettingFragment speedSettingFragment2 = new SpeedSettingFragment();
                bundle2.putString("login", AppConstants.PAGE_MODE_VALET_MIN_SETTINGS);
                speedSettingFragment2.setArguments(bundle2);
                speedSettingFragment2.show(getSupportFragmentManager(), SpeedSettingFragment.TAG);
                speedSettingFragment2.setCancelable(false);
                this.mDlgMode = AppConstants.DLG_MODE_TIME_SELECTOR;
                return;
            }
            bundle = new Bundle();
            int i5 = com.psa.mym.mycitroenconnect.R.id.btnSpeedEditVal;
            if (((AppCompatButton) _$_findCachedViewById(i5)).getVisibility() == 0) {
                bundle.putString(AppConstants.SPEED_SELECTED_VAL, ((AppCompatButton) _$_findCachedViewById(i5)).getText().toString());
            }
            speedSettingFragment = new SpeedSettingFragment();
            str = AppConstants.PAGE_MODE_MAX_SPEED_SETTING;
        }
        bundle.putString("login", str);
        speedSettingFragment.setArguments(bundle);
        speedSettingFragment.show(getSupportFragmentManager(), SpeedSettingFragment.TAG);
        speedSettingFragment.setCancelable(false);
        this.mDlgMode = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_valet);
        getIntentData();
        initViews();
        setListeners();
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(@NotNull GoogleMap p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        this.googleMap = p0;
        if (p0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            p0 = null;
        }
        UiSettings uiSettings = p0.getUiSettings();
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setIndoorLevelPickerEnabled(false);
        p0.setIndoorEnabled(false);
        uiSettings.setCompassEnabled(false);
        uiSettings.setMapToolbarEnabled(false);
        displayCircle();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(@NotNull MessageEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        onRadiusDialogDismiss(event.getMessage());
    }
}
