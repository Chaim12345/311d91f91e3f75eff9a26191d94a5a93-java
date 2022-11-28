package com.psa.mym.mycitroenconnect.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.onboarding.AddVehicleBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.VerifyOtpActivity;
import com.psa.mym.mycitroenconnect.model.ValidationErrorResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.AddVehicleResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.http.message.TokenParser;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AddCarActivity extends BusBaseActivity implements TextWatcher {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int REQ_BARCODE_SCAN = 231;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private RegisterUserResponse registerUserResponse = new RegisterUserResponse(null, null, null, null, null, 31, null);
    @NotNull
    private String mobileNumber = "";
    @NotNull
    private String mPageMode = AppConstants.PAGE_MODE_ADD_CAR;

    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void addInValidVin() {
        ((TextInputEditText) _$_findCachedViewById(R.id.edVinNumber)).setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, uat.psa.mym.mycitroenconnect.R.drawable.ic_invalid_vin, 0);
    }

    private final void addVinVerify() {
        ((TextInputEditText) _$_findCachedViewById(R.id.edVinNumber)).setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, uat.psa.mym.mycitroenconnect.R.drawable.ic_vin_verified, 0);
    }

    private final void apiAddVehicle() {
        AppUtil.Companion.showDialog(this);
        AddVehicleBody addVehicleBody = new AddVehicleBody(null, null, null, 7, null);
        String str = this.mobileNumber;
        String substring = str.substring(3, str.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        addVehicleBody.setUserName(substring);
        addVehicleBody.setCountryCode("+91");
        addVehicleBody.setVinNum(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edVinNumber)).getText()));
        new OnBoardingService().callAddVehicleAPI(this, addVehicleBody);
    }

    private final void initViews() {
        int i2;
        if (getIntent() != null && getIntent().hasExtra(AppConstants.REGUSER)) {
            Parcelable parcelableExtra = getIntent().getParcelableExtra(AppConstants.REGUSER);
            Intrinsics.checkNotNull(parcelableExtra);
            RegisterUserResponse registerUserResponse = (RegisterUserResponse) parcelableExtra;
            this.registerUserResponse = registerUserResponse;
            String mobileNum = registerUserResponse.getMobileNum();
            Intrinsics.checkNotNull(mobileNum);
            this.mobileNumber = mobileNum;
        }
        String str = this.mPageMode;
        int hashCode = str.hashCode();
        if (hashCode != -1148260554) {
            if (hashCode != 991980026) {
                if (hashCode == 1661369742 && str.equals(AppConstants.PAGE_MODE_ADD_VEHICLE)) {
                    TextView textView = (TextView) _$_findCachedViewById(R.id.tvAddCarTitle);
                    if (textView != null) {
                        textView.setText(getResources().getString(uat.psa.mym.mycitroenconnect.R.string.label_add_your_vehicle));
                    }
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvScanTitle)).setVisibility(0);
                    ((AppCompatImageView) _$_findCachedViewById(R.id.ivAddCarBack)).setVisibility(8);
                    return;
                }
                return;
            } else if (!str.equals(AppConstants.PAGE_MODE_ADD_ANOTHER_CAR)) {
                return;
            } else {
                TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvAddCarTitle);
                if (textView2 != null) {
                    textView2.setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_add_another_car));
                }
                this.mobileNumber = SharedPref.Companion.getMobileNumber(this);
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvScanTitle)).setVisibility(0);
                ((AppCompatImageView) _$_findCachedViewById(R.id.ivVinQrScan)).setVisibility(0);
                i2 = R.id.ivAddCarBack;
            }
        } else if (!str.equals(AppConstants.PAGE_MODE_ADD_CAR)) {
            return;
        } else {
            TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvAddCarTitle);
            if (textView3 != null) {
                textView3.setText(getResources().getString(uat.psa.mym.mycitroenconnect.R.string.label_add_car));
            }
            this.mobileNumber = SharedPref.Companion.getMobileNumber(this);
            ((AppCompatImageView) _$_findCachedViewById(R.id.ivAddCarBack)).setVisibility(8);
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvScanTitle)).setVisibility(0);
            i2 = R.id.ivVinQrScan;
        }
        ((AppCompatImageView) _$_findCachedViewById(i2)).setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchBarcodeScan() {
        startActivityForResult(new Intent(this, BarCodeScannerActivity.class), REQ_BARCODE_SCAN);
    }

    private final void scanBarcode() {
        Dexter.withActivity(this).withPermissions("android.permission.CAMERA").withListener(new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.AddCarActivity$scanBarcode$1
            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionRationaleShouldBeShown(@Nullable List<PermissionRequest> list, @NotNull PermissionToken token) {
                Intrinsics.checkNotNullParameter(token, "token");
                token.continuePermissionRequest();
            }

            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionsChecked(@NotNull MultiplePermissionsReport report) {
                Intrinsics.checkNotNullParameter(report, "report");
                if (report.areAllPermissionsGranted()) {
                    AddCarActivity.this.launchBarcodeScan();
                    return;
                }
                AddCarActivity addCarActivity = AddCarActivity.this;
                String string = addCarActivity.getString(uat.psa.mym.mycitroenconnect.R.string.camera_permission);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.camera_permission)");
                ExtensionsKt.showToast(addCarActivity, string);
            }
        }).check();
    }

    private final void setListeners() {
        ((TextInputEditText) _$_findCachedViewById(R.id.edVinNumber)).addTextChangedListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnAdd)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivVinQrScan)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivAddCarBack)).setOnClickListener(this);
    }

    private final boolean validate() {
        TextInputLayout textInputLayout;
        int i2;
        int i3 = R.id.edVinNumber;
        if (TextUtils.isEmpty(String.valueOf(((TextInputEditText) _$_findCachedViewById(i3)).getText()))) {
            ((TextInputEditText) _$_findCachedViewById(i3)).requestFocus();
            textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutVinNumber);
            i2 = uat.psa.mym.mycitroenconnect.R.string.enter_vin_number;
        } else if (String.valueOf(((TextInputEditText) _$_findCachedViewById(i3)).getText()).length() == 17) {
            return true;
        } else {
            ((TextInputEditText) _$_findCachedViewById(i3)).requestFocus();
            textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutVinNumber);
            i2 = uat.psa.mym.mycitroenconnect.R.string.invalid_vin_number;
        }
        textInputLayout.setError(getString(i2));
        return false;
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

    @Override // android.text.TextWatcher
    public void afterTextChanged(@Nullable Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Subscribe
    public final void getMessage(@NotNull ValidationErrorResponse event) {
        String replace$default;
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        StringBuilder sb = new StringBuilder();
        sb.append(getString(uat.psa.mym.mycitroenconnect.R.string.err_pls_enter_correct_vin));
        sb.append(TokenParser.SP);
        replace$default = StringsKt__StringsJVMKt.replace$default(event.getErrorList().get(0), "Unresolved key: ", "", false, 4, (Object) null);
        sb.append(replace$default);
        ExtensionsKt.showToast(this, sb.toString());
    }

    @Subscribe
    public final void getMessage(@NotNull AddVehicleResponse event) {
        Intent intent;
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        String str = this.mPageMode;
        String str2 = AppConstants.PAGE_MODE_ADD_CAR;
        if (Intrinsics.areEqual(str, AppConstants.PAGE_MODE_ADD_CAR)) {
            intent = new Intent(this, VerifyOtpActivity.class);
        } else {
            str2 = AppConstants.PAGE_MODE_ADD_ANOTHER_CAR;
            if (!Intrinsics.areEqual(str, AppConstants.PAGE_MODE_ADD_ANOTHER_CAR)) {
                intent = new Intent(this, ConfirmCarDetailsActivity.class);
                intent.putExtra(AppConstants.REGUSER, this.registerUserResponse);
                intent.putExtra(AppConstants.ADD_CAR_BUNDLE_NAME, ((TextInputEditText) _$_findCachedViewById(R.id.edVinNumber)).getText());
                str2 = AppConstants.PAGE_MODE_ADD_VEHICLE;
                intent.putExtra("login", str2);
                startActivity(intent);
            }
            intent = new Intent(this, ConfirmCarDetailsActivity.class);
        }
        intent.putExtra(AppConstants.ADD_CAR_BUNDLE_NAME, ((TextInputEditText) _$_findCachedViewById(R.id.edVinNumber)).getText());
        intent.putExtra("login", str2);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 231 && i3 == -1 && intent != null) {
            ((TextInputEditText) _$_findCachedViewById(R.id.edVinNumber)).setText(intent.getStringExtra("result"));
        }
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnAdd))) {
            if (validate()) {
                apiAddVehicle();
            }
        } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivVinQrScan))) {
            scanBarcode();
        } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivAddCarBack))) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_add_car);
        if (getIntent() != null && getIntent().hasExtra("login") && getIntent().getStringExtra("login") != null) {
            this.mPageMode = String.valueOf(getIntent().getStringExtra("login"));
        }
        initViews();
        setListeners();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
        Integer valueOf = charSequence != null ? Integer.valueOf(charSequence.length()) : null;
        if (valueOf != null && valueOf.intValue() == 0) {
            int i5 = R.id.txtLayoutVinNumber;
            if (((TextInputLayout) _$_findCachedViewById(i5)).getError() != null) {
                ((TextInputLayout) _$_findCachedViewById(i5)).setError(null);
            }
            ((TextInputEditText) _$_findCachedViewById(R.id.edVinNumber)).setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        } else if (valueOf == null || valueOf.intValue() != 17) {
            ((TextInputLayout) _$_findCachedViewById(R.id.txtLayoutVinNumber)).setError(getString(uat.psa.mym.mycitroenconnect.R.string.invalid_vin_number));
            addInValidVin();
        } else {
            int i6 = R.id.txtLayoutVinNumber;
            if (((TextInputLayout) _$_findCachedViewById(i6)).getError() != null) {
                ((TextInputLayout) _$_findCachedViewById(i6)).setError(null);
            }
            addVinVerify();
        }
    }
}
