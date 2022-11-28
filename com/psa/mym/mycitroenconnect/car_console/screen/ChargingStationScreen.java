package com.psa.mym.mycitroenconnect.car_console.screen;

import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.CarLocation;
import androidx.car.app.model.Distance;
import androidx.car.app.model.DistanceSpan;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.Metadata;
import androidx.car.app.model.OnClickListener;
import androidx.car.app.model.Pane;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.Place;
import androidx.car.app.model.PlaceListMapTemplate;
import androidx.car.app.model.PlaceMarker;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiClient;
import com.psa.mym.mycitroenconnect.car_console.screen.ChargingStationScreen;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoUtils;
import com.psa.mym.mycitroenconnect.model.dashboard.VehicleLocationResponse;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.NearbySearch;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class ChargingStationScreen extends Screen implements DefaultLifecycleObserver {
    private String TAG;
    @Nullable
    private LatLng carLatLng;
    @NotNull
    private final Handler mHandler;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargingStationScreen(@NotNull CarContext carContext) {
        super(carContext);
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        this.TAG = ChargingStationScreen.class.getSimpleName();
        this.mHandler = new Handler(Looper.getMainLooper());
        getLifecycle().addObserver(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dismissScreen$lambda-2  reason: not valid java name */
    public static final void m49dismissScreen$lambda2(ChargingStationScreen this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getScreenManager().popToRoot();
    }

    private final Template displayApiResultWithMessage(String str) {
        CarContext carContext;
        int i2;
        Pane.Builder builder = new Pane.Builder();
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        CarContext carContext2 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext2, "carContext");
        if (autoUtils.isVehicleTypeEv(carContext2)) {
            carContext = getCarContext();
            i2 = R.string.lbl_auto_charging_station;
        } else {
            carContext = getCarContext();
            i2 = R.string.lbl_auto_fuel_station;
        }
        String string = carContext.getString(i2);
        Intrinsics.checkNotNullExpressionValue(string, "if (AutoUtils.isVehicleT…o_fuel_station)\n        }");
        builder.addRow(new Row.Builder().setTitle(str).build());
        dismissScreen();
        PaneTemplate build = new PaneTemplate.Builder(builder.build()).setTitle(string).setHeaderAction(Action.BACK).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(paneBuilder.buil…ACK)\n            .build()");
        return build;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onGetTemplate$lambda-1  reason: not valid java name */
    public static final void m50onGetTemplate$lambda1(ChargingStationScreen this$0, PlacesSearchResult place) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        CarContext carContext = this$0.getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        Intrinsics.checkNotNullExpressionValue(place, "place");
        autoUtils.onClickPlace(carContext, place);
    }

    private final void setSearchLocation() {
        if (this.carLatLng != null) {
            AutoConstants.Companion companion = AutoConstants.Companion;
            Location initial_search_location = companion.getINITIAL_SEARCH_LOCATION();
            LatLng latLng = this.carLatLng;
            Intrinsics.checkNotNull(latLng);
            initial_search_location.setLatitude(latLng.latitude);
            Location initial_search_location2 = companion.getINITIAL_SEARCH_LOCATION();
            LatLng latLng2 = this.carLatLng;
            Intrinsics.checkNotNull(latLng2);
            initial_search_location2.setLongitude(latLng2.longitude);
        }
    }

    public final void callGetVehicleCurrentLocation(@NotNull String userName) {
        Intrinsics.checkNotNullParameter(userName, "userName");
        Logger.INSTANCE.d("callGetVehicleCurrentLocation");
        ApiClient apiClient = ApiClient.INSTANCE;
        CarContext carContext = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        apiClient.getApiInterfaceWithToken(carContext).getCurrentVehicleLocation(userName).enqueue(new Callback<VehicleLocationResponse>() { // from class: com.psa.mym.mycitroenconnect.car_console.screen.ChargingStationScreen$callGetVehicleCurrentLocation$1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<VehicleLocationResponse> call, @NotNull Throwable t2) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t2, "t");
                AutoUtils autoUtils = AutoUtils.INSTANCE;
                CarContext carContext2 = ChargingStationScreen.this.getCarContext();
                Intrinsics.checkNotNullExpressionValue(carContext2, "carContext");
                autoUtils.showFailureToast(carContext2, t2);
                ChargingStationScreen.this.dismissScreen();
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<VehicleLocationResponse> call, @NotNull Response<VehicleLocationResponse> response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (!response.isSuccessful()) {
                    AutoUtils autoUtils = AutoUtils.INSTANCE;
                    int code = response.code();
                    CarContext carContext2 = ChargingStationScreen.this.getCarContext();
                    Intrinsics.checkNotNullExpressionValue(carContext2, "carContext");
                    autoUtils.getHttpErrorToast(code, carContext2);
                } else if (response.code() == 200 || response.code() == 201) {
                    VehicleLocationResponse body = response.body();
                    Intrinsics.checkNotNull(body);
                    VehicleLocationResponse vehicleLocationResponse = body;
                    ChargingStationScreen.this.carLatLng = new LatLng(vehicleLocationResponse.getCoordinate().getX(), vehicleLocationResponse.getCoordinate().getY());
                    ChargingStationScreen.this.invalidate();
                    return;
                } else {
                    AutoUtils autoUtils2 = AutoUtils.INSTANCE;
                    CarContext carContext3 = ChargingStationScreen.this.getCarContext();
                    Intrinsics.checkNotNullExpressionValue(carContext3, "carContext");
                    String string = ChargingStationScreen.this.getCarContext().getString(R.string.no_data_available);
                    Intrinsics.checkNotNullExpressionValue(string, "carContext.getString(R.string.no_data_available)");
                    autoUtils2.showToast(carContext3, string);
                }
                ChargingStationScreen.this.dismissScreen();
            }
        });
    }

    public final void dismissScreen() {
        this.mHandler.postDelayed(new Runnable() { // from class: g.b
            @Override // java.lang.Runnable
            public final void run() {
                ChargingStationScreen.m49dismissScreen$lambda2(ChargingStationScreen.this);
            }
        }, SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onCreate(@NotNull LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        SharedPref.Companion companion = SharedPref.Companion;
        CarContext carContext = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "carContext");
        callGetVehicleCurrentLocation(companion.getVinNumber(carContext));
    }

    @Override // androidx.car.app.Screen
    @NotNull
    public Template onGetTemplate() {
        CarContext carContext;
        int i2;
        CarContext carContext2;
        int i3;
        PlaceMarker.Builder builder;
        CarContext carContext3;
        int i4;
        ItemList.Builder builder2 = new ItemList.Builder();
        AutoUtils autoUtils = AutoUtils.INSTANCE;
        CarContext carContext4 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext4, "carContext");
        if (autoUtils.isVehicleTypeEv(carContext4)) {
            carContext = getCarContext();
            i2 = R.string.lbl_auto_charging_station;
        } else {
            carContext = getCarContext();
            i2 = R.string.lbl_auto_fuel_station;
        }
        String string = carContext.getString(i2);
        Intrinsics.checkNotNullExpressionValue(string, "if (AutoUtils.isVehicleT…o_fuel_station)\n        }");
        LatLng latLng = this.carLatLng;
        if (latLng != null) {
            if ((latLng != null ? Double.valueOf(latLng.latitude) : null) != null) {
                LatLng latLng2 = this.carLatLng;
                if ((latLng2 != null ? Double.valueOf(latLng2.longitude) : null) != null) {
                    setSearchLocation();
                    LatLng latLng3 = this.carLatLng;
                    if (latLng3 != null) {
                        latLng3.toString();
                    }
                    PlacesSearchResult[] placesSearchResultArr = new NearbySearch(this.carLatLng).run().results;
                    if (placesSearchResultArr != null) {
                        if (!(placesSearchResultArr.length == 0)) {
                            int length = placesSearchResultArr.length;
                            for (int i5 = 0; i5 < length && i5 < 6; i5++) {
                                final PlacesSearchResult placesSearchResult = placesSearchResultArr[i5];
                                Location location = new Location(getCarContext().getString(R.string.app_name));
                                location.setLatitude(placesSearchResult.geometry.location.lat);
                                location.setLongitude(placesSearchResult.geometry.location.lng);
                                AutoUtils autoUtils2 = AutoUtils.INSTANCE;
                                CarContext carContext5 = getCarContext();
                                Intrinsics.checkNotNullExpressionValue(carContext5, "carContext");
                                if (autoUtils2.isVehicleTypeEv(carContext5)) {
                                    builder = new PlaceMarker.Builder();
                                    carContext3 = getCarContext();
                                    Intrinsics.checkNotNullExpressionValue(carContext3, "carContext");
                                    i4 = R.drawable.ic_marker_current_red;
                                } else {
                                    builder = new PlaceMarker.Builder();
                                    carContext3 = getCarContext();
                                    Intrinsics.checkNotNullExpressionValue(carContext3, "carContext");
                                    i4 = R.drawable.ic_marker_fuel_red;
                                }
                                PlaceMarker build = builder.setIcon(autoUtils2.getCarIconBuilder(carContext3, i4), 1).build();
                                Intrinsics.checkNotNullExpressionValue(build, "if (AutoUtils.isVehicleT…build()\n                }");
                                BigDecimal valueOf = BigDecimal.valueOf(autoUtils2.getDistanceFromSearchCenter(location, AutoConstants.Companion.getINITIAL_SEARCH_LOCATION()));
                                Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this.toLong())");
                                BigDecimal valueOf2 = BigDecimal.valueOf(1000);
                                Intrinsics.checkNotNullExpressionValue(valueOf2, "valueOf(this.toLong())");
                                BigDecimal divide = valueOf.divide(valueOf2, RoundingMode.HALF_EVEN);
                                Intrinsics.checkNotNullExpressionValue(divide, "this.divide(other, RoundingMode.HALF_EVEN)");
                                StringBuilder sb = new StringBuilder();
                                sb.append("   · ");
                                AppUtil.Companion companion = AppUtil.Companion;
                                CarContext carContext6 = getCarContext();
                                Intrinsics.checkNotNullExpressionValue(carContext6, "carContext");
                                sb.append(companion.getAddressNameString(carContext6, location.getLatitude(), location.getLongitude()));
                                SpannableString spannableString = new SpannableString(sb.toString());
                                DistanceSpan create = DistanceSpan.create(Distance.create(divide.doubleValue(), 2));
                                Intrinsics.checkNotNullExpressionValue(create, "create(\n                …      )\n                )");
                                spannableString.setSpan(create, 0, 1, 18);
                                builder2.addItem(new Row.Builder().setOnClickListener(new OnClickListener() { // from class: g.a
                                    @Override // androidx.car.app.model.OnClickListener
                                    public final void onClick() {
                                        ChargingStationScreen.m50onGetTemplate$lambda1(ChargingStationScreen.this, placesSearchResult);
                                    }
                                }).setTitle(placesSearchResult.name).addText(spannableString).setMetadata(new Metadata.Builder().setPlace(new Place.Builder(CarLocation.create(location)).setMarker(build).build()).build()).build());
                            }
                            Place.Builder marker = new Place.Builder(CarLocation.create(AutoConstants.Companion.getINITIAL_SEARCH_LOCATION())).setMarker(new PlaceMarker.Builder().setColor(CarColor.BLUE).build());
                            Intrinsics.checkNotNullExpressionValue(marker, "Builder(CarLocation.crea…UE).build()\n            )");
                            PlaceListMapTemplate build2 = new PlaceListMapTemplate.Builder().setItemList(builder2.build()).setHeaderAction(Action.BACK).setTitle(string).setCurrentLocationEnabled(true).setAnchor(marker.build()).build();
                            Intrinsics.checkNotNullExpressionValue(build2, "Builder()\n            .s…d())\n            .build()");
                            return build2;
                        }
                    }
                    CarContext carContext7 = getCarContext();
                    Intrinsics.checkNotNullExpressionValue(carContext7, "carContext");
                    if (autoUtils.isVehicleTypeEv(carContext7)) {
                        carContext2 = getCarContext();
                        i3 = R.string.lbl_auto_no_charging_station_found;
                    } else {
                        carContext2 = getCarContext();
                        i3 = R.string.lbl_auto_no_fuel_station_found;
                    }
                    String string2 = carContext2.getString(i3);
                    Intrinsics.checkNotNullExpressionValue(string2, "if (AutoUtils.isVehicleT…tion_found)\n            }");
                    return displayApiResultWithMessage(string2);
                }
            }
        }
        Pane.Builder builder3 = new Pane.Builder();
        builder3.setLoading(true);
        PaneTemplate build3 = new PaneTemplate.Builder(builder3.build()).setTitle(string).setHeaderAction(Action.BACK).build();
        Intrinsics.checkNotNullExpressionValue(build3, "Builder(paneBuilder.buil…\n                .build()");
        return build3;
    }
}
