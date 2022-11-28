package com.psa.mym.mycitroenconnect.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.DeleteCarConfirmationDialog;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnCarDelete;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class MyCarDetailsActivity extends BaseActivity implements OnCarDelete {
    @Nullable
    private MyCar carDetails;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int position = -1;
    private int carSize = -1;

    private final void getIntentData() {
        if (getIntent() != null) {
            if (getIntent().hasExtra(AppConstants.ARG_POSITION)) {
                this.position = getIntent().getIntExtra(AppConstants.ARG_POSITION, -1);
            }
            if (getIntent().hasExtra("car_size")) {
                this.carSize = getIntent().getIntExtra("car_size", -1);
            }
            if (getIntent().hasExtra(AppConstants.ARG_CAR_DETAILS)) {
                this.carDetails = (MyCar) getIntent().getParcelableExtra(AppConstants.ARG_CAR_DETAILS);
            }
        }
    }

    private final void init() {
        Integer vehicleImage;
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.view_car_details));
        MyCar myCar = this.carDetails;
        if (myCar != null) {
            if (myCar.getVehicleImage() != null && (vehicleImage = myCar.getVehicleImage()) != null) {
                ((AppCompatImageView) _$_findCachedViewById(R.id.ivCar)).setImageResource(vehicleImage.intValue());
            }
            if (myCar.getCarModelName() != null) {
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvModelName)).setText(myCar.getCarModelName());
            }
            if (myCar.getVinNum() != null) {
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvVinNumber)).setText(myCar.getVinNum());
            }
            if (myCar.getVehicleRegNo() != null) {
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvRegistrationNumber)).setText(myCar.getVehicleRegNo());
            }
            if (myCar.getAccountId() != null) {
                if (myCar.getAccountId().length() > 0) {
                    String accountId = myCar.getAccountId();
                    String substring = accountId.substring(8, accountId.length());
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = getString(uat.psa.mym.mycitroenconnect.R.string.mask_mobile_number);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.mask_mobile_number)");
                    String format = String.format(string, Arrays.copyOf(new Object[]{substring}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvMobileNumber)).setText(format);
                }
            }
            if (myCar.getOwnerName() != null) {
                ((AppCompatTextView) _$_findCachedViewById(R.id.tvOwnerName)).setText(myCar.getOwnerName());
            }
        }
    }

    private final void setListeners() {
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnDeleteCar)).setOnClickListener(this);
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity
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

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnCarDelete
    public void carDeleteSuccess() {
        if (this.carSize > 1) {
            Intent intent = new Intent();
            intent.putExtra(AppConstants.ARG_POSITION, this.position);
            String vinNumber = SharedPref.Companion.getVinNumber(this);
            MyCar myCar = this.carDetails;
            intent.putExtra("is_selected_vin", Intrinsics.areEqual(vinNumber, myCar != null ? myCar.getVinNum() : null));
            setResult(-1, intent);
        } else {
            SharedPref.Companion companion = SharedPref.Companion;
            companion.setIsPrimaryUser(this, "false");
            companion.setIsVerifiedUser(this, "false");
            Intent intent2 = new Intent(this, NonVinMainActivity.class);
            intent2.addFlags(67108864);
            intent2.addFlags(268435456);
            startActivity(intent2);
        }
        finish();
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivBack))) {
            finish();
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnDeleteCar))) {
            DeleteCarConfirmationDialog.Companion companion = DeleteCarConfirmationDialog.Companion;
            MyCar myCar = this.carDetails;
            Intrinsics.checkNotNull(myCar);
            DeleteCarConfirmationDialog newInstance = companion.newInstance(myCar);
            newInstance.setOnCarDelete(this);
            newInstance.show(getSupportFragmentManager(), DeleteCarConfirmationDialog.TAG);
            newInstance.setCancelable(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_my_car_details);
        getIntentData();
        init();
        setListeners();
    }
}
