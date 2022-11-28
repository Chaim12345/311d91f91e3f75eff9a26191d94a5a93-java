package com.psa.mym.mycitroenconnect.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.textfield.TextInputEditText;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.ProfileDetailsActivity;
import com.psa.mym.mycitroenconnect.model.Token;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCarResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCars;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.commons.cli.HelpFormatter;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ConfirmCarDetailsActivity extends BusBaseActivity {
    @Nullable
    private RegisteredVehicleItem currentCar;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mPageMode = AppConstants.PAGE_MODE_ADD_CAR;
    @NotNull
    private String mobileNumber = "";
    @NotNull
    private String vinNumber = "";
    @NotNull
    private RegisterUserResponse registerUserResponse = new RegisterUserResponse(null, null, null, null, null, 31, null);

    private final void apiGetUserProfile(boolean z) {
        AppUtil.Companion.showDialog(this);
        OnBoardingService onBoardingService = new OnBoardingService();
        String str = this.mobileNumber;
        String substring = str.substring(1, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        onBoardingService.callGetUserProfileAPI(this, substring);
        if (z) {
            apiPermission();
        }
    }

    private final void apiPermission() {
        boolean isBlank;
        isBlank = StringsKt__StringsJVMKt.isBlank(this.mobileNumber);
        if (!isBlank) {
            if (this.mobileNumber.length() > 0) {
                SecondaryUserService secondaryUserService = new SecondaryUserService();
                StringBuilder sb = new StringBuilder();
                sb.append("91");
                String str = this.mobileNumber;
                String substring = str.substring(3, str.length());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                sb.append(substring);
                secondaryUserService.getMyCarList(this, sb.toString(), AppConstants.SCREEN_CONFIRM_CAR_DETAILS);
            }
        }
    }

    private final void getIntentData() {
        String stringExtra;
        if (getIntent() != null && getIntent().hasExtra("login") && (stringExtra = getIntent().getStringExtra("login")) != null) {
            this.mPageMode = stringExtra;
        }
        if (getIntent() != null && getIntent().hasExtra(AppConstants.REGUSER)) {
            Parcelable parcelableExtra = getIntent().getParcelableExtra(AppConstants.REGUSER);
            Intrinsics.checkNotNull(parcelableExtra);
            RegisterUserResponse registerUserResponse = (RegisterUserResponse) parcelableExtra;
            this.registerUserResponse = registerUserResponse;
            String mobileNum = registerUserResponse.getMobileNum();
            Intrinsics.checkNotNull(mobileNum);
            this.mobileNumber = mobileNum;
            apiGetUserProfile(false);
        }
        if ((Intrinsics.areEqual(this.mPageMode, AppConstants.PAGE_MODE_ADD_ANOTHER_CAR) || Intrinsics.areEqual(this.mPageMode, AppConstants.PAGE_MODE_ADD_CAR) || Intrinsics.areEqual(this.mPageMode, AppConstants.PAGE_MODE_ADD_VEHICLE)) && getIntent() != null && getIntent().hasExtra(AppConstants.ADD_CAR_BUNDLE_NAME)) {
            Bundle extras = getIntent().getExtras();
            this.vinNumber = String.valueOf(extras != null ? extras.get(AppConstants.ADD_CAR_BUNDLE_NAME) : null);
            this.mobileNumber = SharedPref.Companion.getMobileNumber(this);
            apiGetUserProfile(true);
        }
    }

    private final void goToMainDashboard() {
        SharedPref.Companion.setIsVerifiedUser(this, "true");
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        startActivity(intent);
        finish();
    }

    private final void goToProfileDetails() {
        Intent intent = new Intent(this, ProfileDetailsActivity.class);
        intent.putExtra(AppConstants.REGUSER, this.registerUserResponse);
        intent.putExtra("login", AppConstants.PAGE_MODE_ADD_VEHICLE);
        startActivity(intent);
        finish();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void setCarDetails(RegisteredVehicleItem registeredVehicleItem) {
        boolean isBlank;
        boolean isBlank2;
        String vinNum = registeredVehicleItem.getVinNum();
        if (vinNum != null) {
            SharedPref.Companion.setVinNumber(this, vinNum);
        }
        SharedPref.Companion companion = SharedPref.Companion;
        companion.setVehicleType(this, registeredVehicleItem.getVehicleType());
        String vehicleNumber = registeredVehicleItem.getVehicleNumber();
        if (vehicleNumber != null) {
            companion.setVehicleNumber(this, vehicleNumber);
        }
        if (registeredVehicleItem.getCarModelName() != null) {
            String carModelName = registeredVehicleItem.getCarModelName();
            Intrinsics.checkNotNull(carModelName);
            if (carModelName.length() > 0) {
                String carModelName2 = registeredVehicleItem.getCarModelName();
                Intrinsics.checkNotNull(carModelName2);
                isBlank2 = StringsKt__StringsJVMKt.isBlank(carModelName2);
                if (!isBlank2) {
                    ((TextInputEditText) _$_findCachedViewById(R.id.edModel)).setText(registeredVehicleItem.getCarModelName());
                    if (registeredVehicleItem.getVehicleNumber() != null) {
                        String vehicleNumber2 = registeredVehicleItem.getVehicleNumber();
                        Intrinsics.checkNotNull(vehicleNumber2);
                        if (vehicleNumber2.length() > 0) {
                            String vehicleNumber3 = registeredVehicleItem.getVehicleNumber();
                            Intrinsics.checkNotNull(vehicleNumber3);
                            isBlank = StringsKt__StringsJVMKt.isBlank(vehicleNumber3);
                            if (!isBlank) {
                                ((TextInputEditText) _$_findCachedViewById(R.id.edRegistrationNo)).setText(registeredVehicleItem.getVehicleNumber());
                                return;
                            }
                        }
                    }
                    ((TextInputEditText) _$_findCachedViewById(R.id.edRegistrationNo)).setText(HelpFormatter.DEFAULT_OPT_PREFIX);
                }
            }
        }
        ((TextInputEditText) _$_findCachedViewById(R.id.edModel)).setText(HelpFormatter.DEFAULT_OPT_PREFIX);
        if (registeredVehicleItem.getVehicleNumber() != null) {
        }
        ((TextInputEditText) _$_findCachedViewById(R.id.edRegistrationNo)).setText(HelpFormatter.DEFAULT_OPT_PREFIX);
    }

    private final void setListener() {
        ((AppCompatButton) _$_findCachedViewById(R.id.btnCarConfirm)).setOnClickListener(this);
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
    public final void getMessage(@NotNull UserProfileResponse event) {
        boolean isBlank;
        Object obj;
        List<RegisteredVehicleItem> registeredVehicle;
        RegisteredVehicleItem registeredVehicleItem;
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        List<RegisteredVehicleItem> registeredVehicle2 = event.getRegisteredVehicle();
        if ((registeredVehicle2 != null && (registeredVehicle2.isEmpty() ^ true)) && (registeredVehicle = event.getRegisteredVehicle()) != null && (registeredVehicleItem = registeredVehicle.get(0)) != null) {
            setCarDetails(registeredVehicleItem);
        }
        isBlank = StringsKt__StringsJVMKt.isBlank(this.vinNumber);
        if (!isBlank) {
            if ((this.vinNumber.length() > 0) && event.getRegisteredVehicle() != null) {
                List<RegisteredVehicleItem> registeredVehicle3 = event.getRegisteredVehicle();
                Intrinsics.checkNotNull(registeredVehicle3);
                if (!registeredVehicle3.isEmpty()) {
                    List<RegisteredVehicleItem> registeredVehicle4 = event.getRegisteredVehicle();
                    Intrinsics.checkNotNull(registeredVehicle4);
                    Iterator<T> it = registeredVehicle4.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it.next();
                        if (Intrinsics.areEqual(((RegisteredVehicleItem) obj).getVinNum(), this.vinNumber)) {
                            break;
                        }
                    }
                    RegisteredVehicleItem registeredVehicleItem2 = (RegisteredVehicleItem) obj;
                    this.currentCar = registeredVehicleItem2;
                    if (registeredVehicleItem2 != null) {
                        setCarDetails(registeredVehicleItem2);
                    }
                }
            }
        }
        String str = this.mobileNumber;
        String substring = str.substring(3, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        ((TextInputEditText) _$_findCachedViewById(R.id.edMobileNo)).setText(substring);
    }

    @NotNull
    public final String getMobileNumber() {
        return this.mobileNumber;
    }

    @Subscribe
    public final void getResponse(@NotNull MyCarResponse myCarResponse) {
        String str;
        Intrinsics.checkNotNullParameter(myCarResponse, "myCarResponse");
        AppUtil.Companion.dismissDialog();
        if (Intrinsics.areEqual(myCarResponse.getScreenName(), AppConstants.SCREEN_CONFIRM_CAR_DETAILS)) {
            MyCars myCars = myCarResponse.getMyCars();
            if (myCars == null || myCars.isEmpty()) {
                return;
            }
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
            ArrayList<MyCar> arrayList = new ArrayList();
            for (MyCar myCar : myCars) {
                if (Intrinsics.areEqual(myCar.getVinNum(), this.vinNumber)) {
                    arrayList.add(myCar);
                }
            }
            for (MyCar myCar2 : arrayList) {
                String vinNum = myCar2.getVinNum();
                if (vinNum != null) {
                    SharedPref.Companion.setVinNumber(this, vinNum);
                }
                String vehicleType = myCar2.getVehicleType();
                if (vehicleType != null) {
                    SharedPref.Companion.setVehicleType(this, vehicleType);
                }
                String vehicleRegNo = myCar2.getVehicleRegNo();
                if (vehicleRegNo != null) {
                    SharedPref.Companion.setVehicleNumber(this, vehicleRegNo);
                }
                Token token2 = myCar2.getToken();
                if (token2 != null) {
                    SharedPref.Companion.setPrimaryUserTokenDetails(this, token2);
                }
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (Intrinsics.areEqual(this.mPageMode, AppConstants.PAGE_MODE_ADD_VEHICLE)) {
            return;
        }
        super.onBackPressed();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnCarConfirm))) {
            String str = this.mPageMode;
            int hashCode = str.hashCode();
            if (hashCode != -1148260554) {
                if (hashCode != 991980026) {
                    if (hashCode == 1661369742 && str.equals(AppConstants.PAGE_MODE_ADD_VEHICLE)) {
                        goToProfileDetails();
                        return;
                    }
                    return;
                } else if (!str.equals(AppConstants.PAGE_MODE_ADD_ANOTHER_CAR)) {
                    return;
                }
            } else if (!str.equals(AppConstants.PAGE_MODE_ADD_CAR)) {
                return;
            }
            goToMainDashboard();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_confirm_car_details);
        getIntentData();
        setListener();
    }

    public final void setMobileNumber(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mobileNumber = str;
    }
}
