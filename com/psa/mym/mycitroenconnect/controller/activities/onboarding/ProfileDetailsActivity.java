package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.SpinnerAdapter;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.DexterBuilder;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.dashboard.EmergencyContactBody;
import com.psa.mym.mycitroenconnect.api.body.onboarding.UpdateProfileBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.DeleteConfirmationFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ProfilePhotoSelectionDlgFragment;
import com.psa.mym.mycitroenconnect.controller.dialog.InfoDialog;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.FailResponse;
import com.psa.mym.mycitroenconnect.model.PostCommonResponse;
import com.psa.mym.mycitroenconnect.model.ValidationErrorResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyContactResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisterUserResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.onboarding.UpdateUserProfileResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.UserPreferenceItem;
import com.psa.mym.mycitroenconnect.model.onboarding.UserProfileResponse;
import com.psa.mym.mycitroenconnect.p000interface.OnDialogProfilePhotoClickListener;
import com.psa.mym.mycitroenconnect.services.DashboardService;
import com.psa.mym.mycitroenconnect.services.OnBoardingService;
import com.psa.mym.mycitroenconnect.services.ProfileService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.FileUtil;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.codec.language.Soundex;
import org.apache.http.cookie.ClientCookie;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ProfileDetailsActivity extends BusBaseActivity {
    @Nullable
    private EmergencyDetailsItem emergencyContact;
    private long lastClickTime;
    @Nullable
    private ArrayList<EmergencyDetailsItem> mEmergencyContactList;
    @NotNull
    private final ActivityResultLauncher<Intent> selectImageResult;
    @Nullable
    private UserProfileResponse userProfileResponse;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mobileNumber = "";
    @NotNull
    private String vin = "";
    @NotNull
    private String mPageMode = AppConstants.PAGE_MODE_PROFILE_DETAILS;
    @NotNull
    private RegisterUserResponse registerUserResponse = new RegisterUserResponse(null, null, null, null, null, 31, null);

    public ProfileDetailsActivity() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.g
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ProfileDetailsActivity.m100selectImageResult$lambda7(ProfileDetailsActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…}\n            }\n        }");
        this.selectImageResult = registerForActivityResult;
    }

    private final void deleteEmergencyContact() {
        DeleteConfirmationFragment newInstance;
        DeleteConfirmationFragment.Companion companion = DeleteConfirmationFragment.Companion;
        String valueOf = String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edFullName)).getText());
        EmergencyDetailsItem emergencyDetailsItem = this.emergencyContact;
        String valueOf2 = String.valueOf(emergencyDetailsItem != null ? emergencyDetailsItem.getContactNum() : null);
        ArrayList<EmergencyDetailsItem> arrayList = this.mEmergencyContactList;
        Intrinsics.checkNotNull(arrayList);
        newInstance = companion.newInstance(valueOf, valueOf2, 1, (r16 & 8) != 0 ? null : arrayList, (r16 & 16) != 0 ? null : null, (r16 & 32) != 0 ? -1 : 0);
        newInstance.show(getSupportFragmentManager(), DeleteConfirmationFragment.TAG);
        newInstance.setCancelable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteProfilePic() {
        AppUtil.Companion.showDialog(this);
        String mobileNumber = SharedPref.Companion.getMobileNumber(this);
        ProfileService profileService = new ProfileService();
        String substring = mobileNumber.substring(1, mobileNumber.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        profileService.deleteProfilePic(this, substring);
    }

    private final void focusOnView(final View view) {
        ((ScrollView) _$_findCachedViewById(R.id.scrollViewPd)).post(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.h
            @Override // java.lang.Runnable
            public final void run() {
                ProfileDetailsActivity.m97focusOnView$lambda10(ProfileDetailsActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: focusOnView$lambda-10  reason: not valid java name */
    public static final void m97focusOnView$lambda10(ProfileDetailsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        ((ScrollView) this$0._$_findCachedViewById(R.id.scrollViewPd)).smoothScrollTo(0, view.getTop() - view.getHeight());
    }

    private final void getIntentData() {
        ArrayList<EmergencyDetailsItem> parcelableArrayListExtra;
        EmergencyDetailsItem emergencyDetailsItem;
        if (getIntent() != null && getIntent().hasExtra("login") && getIntent().getStringExtra("login") != null) {
            this.mPageMode = String.valueOf(getIntent().getStringExtra("login"));
        }
        if (getIntent() != null && getIntent().hasExtra(AppConstants.EMERGENCY_CONTACT_BUNDLE_NAME) && (emergencyDetailsItem = (EmergencyDetailsItem) getIntent().getParcelableExtra(AppConstants.EMERGENCY_CONTACT_BUNDLE_NAME)) != null) {
            this.emergencyContact = emergencyDetailsItem;
        }
        if (getIntent() == null || !getIntent().hasExtra(AppConstants.EMERGENCY_CONTACT_LIST) || (parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(AppConstants.EMERGENCY_CONTACT_LIST)) == null) {
            return;
        }
        this.mEmergencyContactList = parcelableArrayListExtra;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getMessage$lambda-16  reason: not valid java name */
    public static final void m98getMessage$lambda16(UserProfileResponse event, ProfileDetailsActivity this$0) {
        AppCompatSpinner appCompatSpinner;
        int i2;
        Intrinsics.checkNotNullParameter(event, "$event");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String gender = event.getGender();
        if (gender != null) {
            int hashCode = gender.hashCode();
            if (hashCode != 2390573) {
                if (hashCode != 76517104) {
                    if (hashCode != 2100660076 || !gender.equals("Female")) {
                        return;
                    }
                    appCompatSpinner = (AppCompatSpinner) this$0._$_findCachedViewById(R.id.genderSpinner);
                    i2 = 1;
                } else if (!gender.equals("Other")) {
                    return;
                } else {
                    appCompatSpinner = (AppCompatSpinner) this$0._$_findCachedViewById(R.id.genderSpinner);
                    i2 = 2;
                }
            } else if (!gender.equals("Male")) {
                return;
            } else {
                appCompatSpinner = (AppCompatSpinner) this$0._$_findCachedViewById(R.id.genderSpinner);
                i2 = 0;
            }
            appCompatSpinner.setSelection(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getMessage$lambda-17  reason: not valid java name */
    public static final void m99getMessage$lambda17(UserProfileResponse event, ProfileDetailsActivity this$0) {
        AppCompatSpinner appCompatSpinner;
        int i2;
        Intrinsics.checkNotNullParameter(event, "$event");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String bloodGroup = event.getBloodGroup();
        if (bloodGroup != null) {
            int hashCode = bloodGroup.hashCode();
            if (hashCode != 2058) {
                if (hashCode != 2060) {
                    if (hashCode != 2089) {
                        if (hashCode != 2091) {
                            if (hashCode != 2492) {
                                if (hashCode != 2494) {
                                    if (hashCode != 64554) {
                                        if (hashCode != 64556 || !bloodGroup.equals("AB-")) {
                                            return;
                                        }
                                        appCompatSpinner = (AppCompatSpinner) this$0._$_findCachedViewById(R.id.spBloodGroup);
                                        i2 = 8;
                                    } else if (!bloodGroup.equals("AB+")) {
                                        return;
                                    } else {
                                        appCompatSpinner = (AppCompatSpinner) this$0._$_findCachedViewById(R.id.spBloodGroup);
                                        i2 = 7;
                                    }
                                } else if (!bloodGroup.equals("O-")) {
                                    return;
                                } else {
                                    appCompatSpinner = (AppCompatSpinner) this$0._$_findCachedViewById(R.id.spBloodGroup);
                                    i2 = 6;
                                }
                            } else if (!bloodGroup.equals("O+")) {
                                return;
                            } else {
                                appCompatSpinner = (AppCompatSpinner) this$0._$_findCachedViewById(R.id.spBloodGroup);
                                i2 = 5;
                            }
                        } else if (!bloodGroup.equals("B-")) {
                            return;
                        } else {
                            appCompatSpinner = (AppCompatSpinner) this$0._$_findCachedViewById(R.id.spBloodGroup);
                            i2 = 4;
                        }
                    } else if (!bloodGroup.equals("B+")) {
                        return;
                    } else {
                        appCompatSpinner = (AppCompatSpinner) this$0._$_findCachedViewById(R.id.spBloodGroup);
                        i2 = 3;
                    }
                } else if (!bloodGroup.equals("A-")) {
                    return;
                } else {
                    appCompatSpinner = (AppCompatSpinner) this$0._$_findCachedViewById(R.id.spBloodGroup);
                    i2 = 2;
                }
            } else if (!bloodGroup.equals("A+")) {
                return;
            } else {
                appCompatSpinner = (AppCompatSpinner) this$0._$_findCachedViewById(R.id.spBloodGroup);
                i2 = 1;
            }
            appCompatSpinner.setSelection(i2);
        }
    }

    private final void initializeView() {
        List list;
        List list2;
        OnBoardingService onBoardingService;
        String[] stringArray = getResources().getStringArray(uat.psa.mym.mycitroenconnect.R.array.gender);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray(R.array.gender)");
        list = ArraysKt___ArraysKt.toList(stringArray);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, (int) uat.psa.mym.mycitroenconnect.R.layout.layout_custom_spinner_tv, (ArrayList) list);
        arrayAdapter.setDropDownViewResource(uat.psa.mym.mycitroenconnect.R.layout.layout_curom_spinner_item);
        ((AppCompatSpinner) _$_findCachedViewById(R.id.genderSpinner)).setAdapter((SpinnerAdapter) arrayAdapter);
        String[] stringArray2 = getResources().getStringArray(uat.psa.mym.mycitroenconnect.R.array.blood_group);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "resources.getStringArray(R.array.blood_group)");
        list2 = ArraysKt___ArraysKt.toList(stringArray2);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, (int) uat.psa.mym.mycitroenconnect.R.layout.layout_custom_spinner_tv, (ArrayList) list2);
        arrayAdapter2.setDropDownViewResource(uat.psa.mym.mycitroenconnect.R.layout.layout_curom_spinner_item);
        ((AppCompatSpinner) _$_findCachedViewById(R.id.spBloodGroup)).setAdapter((SpinnerAdapter) arrayAdapter2);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvDateOfBirthVal)).setText("");
        int i2 = R.id.layoutEmergencyContactDetails;
        View layoutEmergencyContactDetails = _$_findCachedViewById(i2);
        Intrinsics.checkNotNullExpressionValue(layoutEmergencyContactDetails, "layoutEmergencyContactDetails");
        ExtensionsKt.hide(layoutEmergencyContactDetails);
        int i3 = R.id.scrollViewPd;
        ScrollView scrollViewPd = (ScrollView) _$_findCachedViewById(i3);
        Intrinsics.checkNotNullExpressionValue(scrollViewPd, "scrollViewPd");
        ExtensionsKt.show(scrollViewPd);
        String str = this.mPageMode;
        switch (str.hashCode()) {
            case -2065507977:
                if (str.equals(AppConstants.PAGE_MODE_EDIT_PROFILE_DETAILS)) {
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_edit_profile));
                    ((AppCompatButton) _$_findCachedViewById(R.id.btnEditCancel)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_cancel));
                    ((AppCompatButton) _$_findCachedViewById(R.id.btnEditSave)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_save));
                    ConstraintLayout layoutImageView = (ConstraintLayout) _$_findCachedViewById(R.id.layoutImageView);
                    Intrinsics.checkNotNullExpressionValue(layoutImageView, "layoutImageView");
                    ExtensionsKt.hide(layoutImageView);
                    AppCompatTextView tvConfirmDetail = (AppCompatTextView) _$_findCachedViewById(R.id.tvConfirmDetail);
                    Intrinsics.checkNotNullExpressionValue(tvConfirmDetail, "tvConfirmDetail");
                    ExtensionsKt.hide(tvConfirmDetail);
                    TextInputLayout txtLayoutVRN = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutVRN);
                    Intrinsics.checkNotNullExpressionValue(txtLayoutVRN, "txtLayoutVRN");
                    ExtensionsKt.hide(txtLayoutVRN);
                    TextInputLayout txtLayoutVehicleName = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutVehicleName);
                    Intrinsics.checkNotNullExpressionValue(txtLayoutVehicleName, "txtLayoutVehicleName");
                    ExtensionsKt.hide(txtLayoutVehicleName);
                    AppCompatButton btnConfirmNContinue = (AppCompatButton) _$_findCachedViewById(R.id.btnConfirmNContinue);
                    Intrinsics.checkNotNullExpressionValue(btnConfirmNContinue, "btnConfirmNContinue");
                    ExtensionsKt.hide(btnConfirmNContinue);
                    TextInputLayout txtLayoutDummy = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutDummy);
                    Intrinsics.checkNotNullExpressionValue(txtLayoutDummy, "txtLayoutDummy");
                    ExtensionsKt.hide(txtLayoutDummy);
                    LinearLayoutCompat layoutEditBtn = (LinearLayoutCompat) _$_findCachedViewById(R.id.layoutEditBtn);
                    Intrinsics.checkNotNullExpressionValue(layoutEditBtn, "layoutEditBtn");
                    ExtensionsKt.show(layoutEditBtn);
                    AppCompatImageView appCompatImageView = (AppCompatImageView) _$_findCachedViewById(R.id.layoutEditContactHeader).findViewById(R.id.ivBack);
                    Intrinsics.checkNotNullExpressionValue(appCompatImageView, "layoutEditContactHeader.ivBack");
                    ExtensionsKt.show(appCompatImageView);
                    int i4 = R.id.edMobileNumber;
                    ((TextInputEditText) _$_findCachedViewById(i4)).setFocusable(false);
                    ((TextInputEditText) _$_findCachedViewById(i4)).setFocusableInTouchMode(false);
                    ((TextInputEditText) _$_findCachedViewById(i4)).setEnabled(false);
                    ((TextInputEditText) _$_findCachedViewById(i4)).setCursorVisible(false);
                    ((TextInputEditText) _$_findCachedViewById(i4)).setKeyListener(null);
                    this.mobileNumber = SharedPref.Companion.getMobileNumber(this);
                    AppUtil.Companion.showDialog(this);
                    onBoardingService = new OnBoardingService();
                    String str2 = this.mobileNumber;
                    String substring = str2.substring(1, str2.length());
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    onBoardingService.callGetUserProfileAPI(this, substring);
                    break;
                }
                break;
            case -376739412:
                if (str.equals(AppConstants.PAGE_MODE_PROFILE_DETAILS)) {
                    LinearLayoutCompat layoutEditBtn2 = (LinearLayoutCompat) _$_findCachedViewById(R.id.layoutEditBtn);
                    Intrinsics.checkNotNullExpressionValue(layoutEditBtn2, "layoutEditBtn");
                    ExtensionsKt.hide(layoutEditBtn2);
                    TextInputLayout txtLayoutDummy2 = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutDummy);
                    Intrinsics.checkNotNullExpressionValue(txtLayoutDummy2, "txtLayoutDummy");
                    ExtensionsKt.hide(txtLayoutDummy2);
                    if (getIntent() != null && getIntent().hasExtra(AppConstants.REGUSER)) {
                        Parcelable parcelableExtra = getIntent().getParcelableExtra(AppConstants.REGUSER);
                        Intrinsics.checkNotNull(parcelableExtra);
                        RegisterUserResponse registerUserResponse = (RegisterUserResponse) parcelableExtra;
                        this.registerUserResponse = registerUserResponse;
                        String mobileNum = registerUserResponse.getMobileNum();
                        Intrinsics.checkNotNull(mobileNum);
                        this.mobileNumber = mobileNum;
                    }
                    AppUtil.Companion.showDialog(this);
                    onBoardingService = new OnBoardingService();
                    String str22 = this.mobileNumber;
                    String substring2 = str22.substring(1, str22.length());
                    Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    onBoardingService.callGetUserProfileAPI(this, substring2);
                    break;
                }
                break;
            case 1439176829:
                if (str.equals(AppConstants.PAGE_MODE_EDIT_EMERGENCY_CONTACT)) {
                    View layoutEmergencyContactDetails2 = _$_findCachedViewById(i2);
                    Intrinsics.checkNotNullExpressionValue(layoutEmergencyContactDetails2, "layoutEmergencyContactDetails");
                    ExtensionsKt.show(layoutEmergencyContactDetails2);
                    ScrollView scrollViewPd2 = (ScrollView) _$_findCachedViewById(i3);
                    Intrinsics.checkNotNullExpressionValue(scrollViewPd2, "scrollViewPd");
                    ExtensionsKt.hide(scrollViewPd2);
                    ((AppCompatTextView) _$_findCachedViewById(R.id.layoutEmergEditContactHeader).findViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_edit_emergency_contact));
                    TextInputEditText textInputEditText = (TextInputEditText) _$_findCachedViewById(R.id.edFullName);
                    EmergencyDetailsItem emergencyDetailsItem = this.emergencyContact;
                    textInputEditText.setText(emergencyDetailsItem != null ? emergencyDetailsItem.getName() : null);
                    int i5 = R.id.edtMobileNumber;
                    TextInputEditText textInputEditText2 = (TextInputEditText) _$_findCachedViewById(i5);
                    EmergencyDetailsItem emergencyDetailsItem2 = this.emergencyContact;
                    textInputEditText2.setText(emergencyDetailsItem2 != null ? emergencyDetailsItem2.getContactNum() : null);
                    ((TextInputEditText) _$_findCachedViewById(i5)).setEnabled(false);
                    ((TextInputEditText) _$_findCachedViewById(i5)).setBackgroundColor(ContextCompat.getColor(this, 17170445));
                    break;
                }
                break;
            case 1661369742:
                if (str.equals(AppConstants.PAGE_MODE_ADD_VEHICLE)) {
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_personal_details));
                    AppCompatButton btnConfirmNContinue2 = (AppCompatButton) _$_findCachedViewById(R.id.btnConfirmNContinue);
                    Intrinsics.checkNotNullExpressionValue(btnConfirmNContinue2, "btnConfirmNContinue");
                    ExtensionsKt.show(btnConfirmNContinue2);
                    LinearLayoutCompat layoutEditBtn3 = (LinearLayoutCompat) _$_findCachedViewById(R.id.layoutEditBtn);
                    Intrinsics.checkNotNullExpressionValue(layoutEditBtn3, "layoutEditBtn");
                    ExtensionsKt.hide(layoutEditBtn3);
                    TextInputLayout txtLayoutDummy3 = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutDummy);
                    Intrinsics.checkNotNullExpressionValue(txtLayoutDummy3, "txtLayoutDummy");
                    ExtensionsKt.hide(txtLayoutDummy3);
                    AppCompatImageView appCompatImageView2 = (AppCompatImageView) _$_findCachedViewById(R.id.layoutEditContactHeader).findViewById(R.id.ivBack);
                    Intrinsics.checkNotNullExpressionValue(appCompatImageView2, "layoutEditContactHeader.ivBack");
                    ExtensionsKt.hide(appCompatImageView2);
                    int i6 = R.id.edMobileNumber;
                    ((TextInputEditText) _$_findCachedViewById(i6)).setFocusable(false);
                    ((TextInputEditText) _$_findCachedViewById(i6)).setFocusableInTouchMode(false);
                    ((TextInputEditText) _$_findCachedViewById(i6)).setEnabled(false);
                    ((TextInputEditText) _$_findCachedViewById(i6)).setCursorVisible(false);
                    ((TextInputEditText) _$_findCachedViewById(i6)).setKeyListener(null);
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvImageName)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.add_profile_picture));
                    ((AppCompatTextView) _$_findCachedViewById(R.id.tvChangeImage)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.upload_picture));
                    if (getIntent() != null && getIntent().hasExtra(AppConstants.REGUSER)) {
                        Parcelable parcelableExtra2 = getIntent().getParcelableExtra(AppConstants.REGUSER);
                        Intrinsics.checkNotNull(parcelableExtra2);
                        RegisterUserResponse registerUserResponse2 = (RegisterUserResponse) parcelableExtra2;
                        this.registerUserResponse = registerUserResponse2;
                        String mobileNum2 = registerUserResponse2.getMobileNum();
                        Intrinsics.checkNotNull(mobileNum2);
                        this.mobileNumber = mobileNum2;
                    }
                    AppUtil.Companion.showDialog(this);
                    onBoardingService = new OnBoardingService();
                    String str222 = this.mobileNumber;
                    String substring22 = str222.substring(1, str222.length());
                    Intrinsics.checkNotNullExpressionValue(substring22, "this as java.lang.String…ing(startIndex, endIndex)");
                    onBoardingService.callGetUserProfileAPI(this, substring22);
                    break;
                }
                break;
        }
        TextInputEditText edtFullName = (TextInputEditText) _$_findCachedViewById(R.id.edtFullName);
        Intrinsics.checkNotNullExpressionValue(edtFullName, "edtFullName");
        TextInputLayout txtLayoutFullName = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutFullName);
        Intrinsics.checkNotNullExpressionValue(txtLayoutFullName, "txtLayoutFullName");
        ExtensionsKt.removeError(edtFullName, txtLayoutFullName);
        TextInputEditText edtEmailId = (TextInputEditText) _$_findCachedViewById(R.id.edtEmailId);
        Intrinsics.checkNotNullExpressionValue(edtEmailId, "edtEmailId");
        TextInputLayout txtLayoutEmailId = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutEmailId);
        Intrinsics.checkNotNullExpressionValue(txtLayoutEmailId, "txtLayoutEmailId");
        ExtensionsKt.removeError(edtEmailId, txtLayoutEmailId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchCameraIntent() {
        Intent intent = new Intent(this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, 0);
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);
        this.selectImageResult.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchGalleryIntent() {
        Intent intent = new Intent(this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, 1);
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        this.selectImageResult.launch(intent);
    }

    private final void loadProfile(Uri uri) {
        Logger logger = Logger.INSTANCE;
        logger.d("Image cache path: " + uri);
        int i2 = R.id.imgProfile;
        Glide.with((FragmentActivity) this).load(uri).placeholder((int) uat.psa.mym.mycitroenconnect.R.drawable.ic_default_profile).error(uat.psa.mym.mycitroenconnect.R.drawable.ic_default_profile).into((CircleImageView) _$_findCachedViewById(i2));
        ((CircleImageView) _$_findCachedViewById(i2)).setColorFilter(ContextCompat.getColor(this, 17170445));
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x005e, code lost:
        if (r0 != false) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void saveEmergencyContact() {
        boolean z;
        boolean isBlank;
        boolean isBlank2;
        int i2 = R.id.edFullName;
        Editable text = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
        boolean z2 = false;
        if (!(text == null || text.length() == 0)) {
            Editable text2 = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
            if (text2 != null) {
                isBlank2 = StringsKt__StringsJVMKt.isBlank(text2);
                if (!isBlank2) {
                    z = false;
                    if (!z) {
                        int i3 = R.id.edtMobileNumber;
                        Editable text3 = ((TextInputEditText) _$_findCachedViewById(i3)).getText();
                        if (!(text3 == null || text3.length() == 0)) {
                            Editable text4 = ((TextInputEditText) _$_findCachedViewById(i3)).getText();
                            if (text4 != null) {
                                isBlank = StringsKt__StringsJVMKt.isBlank(text4);
                            }
                            z2 = true;
                            if (!z2) {
                                if (this.emergencyContact != null) {
                                    updateEmergencyContact();
                                    return;
                                }
                                return;
                            }
                        }
                    }
                }
            }
            z = true;
            if (!z) {
            }
        }
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.enter_all_details);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enter_all_details)");
        ExtensionsKt.showToast(this, string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: selectImageResult$lambda-7  reason: not valid java name */
    public static final void m100selectImageResult$lambda7(ProfileDetailsActivity this$0, ActivityResult activityResult) {
        Intent data;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult == null || activityResult.getData() == null || activityResult.getResultCode() != -1 || (data = activityResult.getData()) == null) {
            return;
        }
        Parcelable parcelableExtra = data.getParcelableExtra(ClientCookie.PATH_ATTR);
        Intrinsics.checkNotNull(parcelableExtra);
        Uri uri = (Uri) parcelableExtra;
        FileUtil fileUtil = FileUtil.INSTANCE;
        File from = fileUtil.from(this$0, uri);
        Logger logger = Logger.INSTANCE;
        logger.e("File Size: " + fileUtil.getReadableFileSize(from));
        this$0.loadProfile(uri);
        String absolutePath = from.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
        this$0.uploadProfilePic(absolutePath);
    }

    private final void setListeners() {
        ((AppCompatButton) _$_findCachedViewById(R.id.btnConfirmNContinue)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvChangeImage)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvDateOfBirthVal)).setOnClickListener(this);
        View _$_findCachedViewById = _$_findCachedViewById(R.id.layoutEmergEditContactHeader);
        int i2 = R.id.ivBack;
        ((AppCompatImageView) _$_findCachedViewById.findViewById(i2)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnEditSave)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnEditCancel)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivDatePicker)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(i2)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnSave)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnDelete)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivBloodGroupInfo)).setOnClickListener(this);
    }

    private final void showBloodGroupInfo() {
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.label_trips_info_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.label_trips_info_title)");
        String string2 = getString(uat.psa.mym.mycitroenconnect.R.string.info_blood_group);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.info_blood_group)");
        new InfoDialog(this, string, string2).show();
    }

    private final void showDOBPicker() {
        boolean isBlank;
        String replace$default;
        String replace$default2;
        List split$default;
        Calendar calendar = Calendar.getInstance();
        calendar.add(1, -18);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.f
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i2, int i3, int i4) {
                ProfileDetailsActivity.m101showDOBPicker$lambda8(ProfileDetailsActivity.this, datePicker, i2, i3, i4);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5));
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        int i2 = R.id.tvDateOfBirthVal;
        CharSequence text = ((AppCompatTextView) _$_findCachedViewById(i2)).getText();
        Intrinsics.checkNotNullExpressionValue(text, "tvDateOfBirthVal.text");
        isBlank = StringsKt__StringsJVMKt.isBlank(text);
        if (!isBlank) {
            CharSequence text2 = ((AppCompatTextView) _$_findCachedViewById(i2)).getText();
            Intrinsics.checkNotNullExpressionValue(text2, "tvDateOfBirthVal.text");
            if (text2.length() > 0) {
                replace$default = StringsKt__StringsJVMKt.replace$default(((AppCompatTextView) _$_findCachedViewById(i2)).getText().toString(), ".", HelpFormatter.DEFAULT_OPT_PREFIX, false, 4, (Object) null);
                replace$default2 = StringsKt__StringsJVMKt.replace$default(replace$default, "/", HelpFormatter.DEFAULT_OPT_PREFIX, false, 4, (Object) null);
                split$default = StringsKt__StringsKt.split$default((CharSequence) replace$default2, new String[]{HelpFormatter.DEFAULT_OPT_PREFIX}, false, 0, 6, (Object) null);
                if (split$default != null && (!split$default.isEmpty()) && split$default.size() == 3) {
                    calendar.set(Integer.parseInt((String) split$default.get(2)), Integer.parseInt((String) split$default.get(1)) - 1, Integer.parseInt((String) split$default.get(0)));
                    datePickerDialog.updateDate(calendar.get(1), calendar.get(2), calendar.get(5));
                }
            }
        }
        datePickerDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showDOBPicker$lambda-8  reason: not valid java name */
    public static final void m101showDOBPicker$lambda8(ProfileDetailsActivity this$0, DatePicker datePicker, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        StringBuilder sb = new StringBuilder();
        sb.append(i4);
        sb.append(Soundex.SILENT_MARKER);
        sb.append(i3 + 1);
        sb.append(Soundex.SILENT_MARKER);
        sb.append(i2);
        ((AppCompatTextView) this$0._$_findCachedViewById(R.id.tvDateOfBirthVal)).setText(sb.toString());
    }

    private final void showProfilePhotoSelection() {
        ProfilePhotoSelectionDlgFragment profilePhotoSelectionDlgFragment = new ProfilePhotoSelectionDlgFragment();
        profilePhotoSelectionDlgFragment.show(getSupportFragmentManager(), ProfilePhotoSelectionDlgFragment.TAG);
        profilePhotoSelectionDlgFragment.setListener(new OnDialogProfilePhotoClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.ProfileDetailsActivity$showProfilePhotoSelection$1
            @Override // com.psa.mym.mycitroenconnect.p000interface.OnDialogProfilePhotoClickListener
            public void onDialogProfilePhotoClickListener(@NotNull String option) {
                DexterBuilder.MultiPermissionListener withPermissions;
                MultiplePermissionsListener multiplePermissionsListener;
                Intrinsics.checkNotNullParameter(option, "option");
                int hashCode = option.hashCode();
                if (hashCode != 521667378) {
                    if (hashCode != 1980544805) {
                        if (hashCode == 2012838315 && option.equals("DELETE")) {
                            ProfileDetailsActivity.this.deleteProfilePic();
                            return;
                        }
                        return;
                    } else if (!option.equals(AppConstants.PROFILE_CAMERA)) {
                        return;
                    } else {
                        withPermissions = Dexter.withActivity(ProfileDetailsActivity.this).withPermissions("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
                        final ProfileDetailsActivity profileDetailsActivity = ProfileDetailsActivity.this;
                        multiplePermissionsListener = new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.ProfileDetailsActivity$showProfilePhotoSelection$1$onDialogProfilePhotoClickListener$1
                            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
                            public void onPermissionRationaleShouldBeShown(@Nullable List<PermissionRequest> list, @NotNull PermissionToken token) {
                                Intrinsics.checkNotNullParameter(token, "token");
                                token.continuePermissionRequest();
                            }

                            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
                            public void onPermissionsChecked(@NotNull MultiplePermissionsReport report) {
                                Intrinsics.checkNotNullParameter(report, "report");
                                if (report.areAllPermissionsGranted()) {
                                    ProfileDetailsActivity.this.launchCameraIntent();
                                    return;
                                }
                                ProfileDetailsActivity profileDetailsActivity2 = ProfileDetailsActivity.this;
                                String string = profileDetailsActivity2.getString(uat.psa.mym.mycitroenconnect.R.string.permission_required_for_profile_pic);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permi…required_for_profile_pic)");
                                ExtensionsKt.showToast(profileDetailsActivity2, string);
                            }
                        };
                    }
                } else if (!option.equals(AppConstants.PROFILE_GALLERY)) {
                    return;
                } else {
                    withPermissions = Dexter.withActivity(ProfileDetailsActivity.this).withPermissions("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
                    final ProfileDetailsActivity profileDetailsActivity2 = ProfileDetailsActivity.this;
                    multiplePermissionsListener = new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.ProfileDetailsActivity$showProfilePhotoSelection$1$onDialogProfilePhotoClickListener$2
                        @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
                        public void onPermissionRationaleShouldBeShown(@Nullable List<PermissionRequest> list, @NotNull PermissionToken token) {
                            Intrinsics.checkNotNullParameter(token, "token");
                            token.continuePermissionRequest();
                        }

                        @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
                        public void onPermissionsChecked(@NotNull MultiplePermissionsReport report) {
                            Intrinsics.checkNotNullParameter(report, "report");
                            if (report.areAllPermissionsGranted()) {
                                ProfileDetailsActivity.this.launchGalleryIntent();
                                return;
                            }
                            ProfileDetailsActivity profileDetailsActivity3 = ProfileDetailsActivity.this;
                            String string = profileDetailsActivity3.getString(uat.psa.mym.mycitroenconnect.R.string.permission_required_for_profile_pic);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permi…required_for_profile_pic)");
                            ExtensionsKt.showToast(profileDetailsActivity3, string);
                        }
                    };
                }
                withPermissions.withListener(multiplePermissionsListener).check();
            }
        });
        profilePhotoSelectionDlgFragment.setCancelable(true);
    }

    private final void updateEmergencyContact() {
        String replace$default;
        CharSequence trim;
        EmergencyContactBody emergencyContactBody = new EmergencyContactBody(null, null, null, 7, null);
        replace$default = StringsKt__StringsJVMKt.replace$default(SharedPref.Companion.getMobileNumber(this), "+91", "", false, 4, (Object) null);
        trim = StringsKt__StringsKt.trim((CharSequence) replace$default);
        emergencyContactBody.setUserName(trim.toString());
        emergencyContactBody.setCountryCode("+91");
        ArrayList<EmergencyDetailsItem> arrayList = this.mEmergencyContactList;
        if (arrayList != null) {
            ArrayList<EmergencyDetailsItem> arrayList2 = new ArrayList();
            Iterator<T> it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                String contactNum = ((EmergencyDetailsItem) next).getContactNum();
                EmergencyDetailsItem emergencyDetailsItem = this.emergencyContact;
                if (Intrinsics.areEqual(contactNum, emergencyDetailsItem != null ? emergencyDetailsItem.getContactNum() : null)) {
                    arrayList2.add(next);
                }
            }
            for (EmergencyDetailsItem emergencyDetailsItem2 : arrayList2) {
                EmergencyDetailsItem emergencyDetailsItem3 = this.emergencyContact;
                if (emergencyDetailsItem3 != null) {
                    emergencyDetailsItem3.setName(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edFullName)).getText()));
                }
                EmergencyDetailsItem emergencyDetailsItem4 = this.emergencyContact;
                if (emergencyDetailsItem4 != null) {
                    emergencyDetailsItem4.setContactNum(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtMobileNumber)).getText()));
                }
                EmergencyDetailsItem emergencyDetailsItem5 = this.emergencyContact;
                emergencyDetailsItem2.setName(String.valueOf(emergencyDetailsItem5 != null ? emergencyDetailsItem5.getName() : null));
                EmergencyDetailsItem emergencyDetailsItem6 = this.emergencyContact;
                emergencyDetailsItem2.setContactNum(String.valueOf(emergencyDetailsItem6 != null ? emergencyDetailsItem6.getContactNum() : null));
                emergencyDetailsItem2.setContactNum(AppUtil.Companion.getValidPhoneString(emergencyDetailsItem2.getContactNum()));
                EmergencyDetailsItem emergencyDetailsItem7 = this.emergencyContact;
                emergencyDetailsItem2.setName(String.valueOf(emergencyDetailsItem7 != null ? emergencyDetailsItem7.getName() : null));
                emergencyDetailsItem2.setCountryCode("+91");
            }
        }
        emergencyContactBody.setEmergencyDetails(this.mEmergencyContactList);
        AppUtil.Companion.showDialog(this);
        new DashboardService().callUpdateEmergencyContactAPI(this, emergencyContactBody);
    }

    private final void updateProfileDetails() {
        List<UserPreferenceItem> userPreference;
        ArrayList<UserPreferenceItem> userPreference2;
        UpdateProfileBody updateProfileBody = new UpdateProfileBody(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
        updateProfileBody.setFullName(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtFullName)).getText()));
        updateProfileBody.setEmail(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtEmailId)).getText()));
        updateProfileBody.setUserName(this.mobileNumber);
        updateProfileBody.setAddress(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtStreetName)).getText()));
        updateProfileBody.setBloodGroup(((AppCompatSpinner) _$_findCachedViewById(R.id.spBloodGroup)).getSelectedItem().toString());
        updateProfileBody.setCity(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtCity)).getText()));
        updateProfileBody.setDateOfBirth(((AppCompatTextView) _$_findCachedViewById(R.id.tvDateOfBirthVal)).getText().toString());
        updateProfileBody.setGender(((AppCompatSpinner) _$_findCachedViewById(R.id.genderSpinner)).getSelectedItem().toString());
        RegisteredVehicleItem registeredVehicleItem = new RegisteredVehicleItem(null, null, null, null, null, false, 0, false, false, null, 1023, null);
        registeredVehicleItem.setCarModelName(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtVehicleName)).getText()));
        registeredVehicleItem.setVehicleNumber(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtVRN)).getText()));
        registeredVehicleItem.setVinNum(this.vin);
        ArrayList<RegisteredVehicleItem> arrayList = new ArrayList<>();
        if (!SharedPref.Companion.isGuestUser(this)) {
            arrayList.add(registeredVehicleItem);
        }
        updateProfileBody.setRegisteredVehicle(arrayList);
        updateProfileBody.setProfilePic(null);
        updateProfileBody.setUserPreference(new ArrayList<>());
        UserProfileResponse userProfileResponse = this.userProfileResponse;
        if (userProfileResponse != null && (userPreference = userProfileResponse.getUserPreference()) != null && (userPreference2 = updateProfileBody.getUserPreference()) != null) {
            userPreference2.addAll(userPreference);
        }
        new OnBoardingService().callUpdateUserProfileAPI(this, updateProfileBody);
    }

    private final void updateSaveProfile() {
        String str = this.mPageMode;
        int hashCode = str.hashCode();
        if (hashCode != -2065507977) {
            if (hashCode != -376739412) {
                if (hashCode != 1661369742 || !str.equals(AppConstants.PAGE_MODE_ADD_VEHICLE)) {
                    return;
                }
            } else if (!str.equals(AppConstants.PAGE_MODE_PROFILE_DETAILS)) {
                return;
            }
        } else if (!str.equals(AppConstants.PAGE_MODE_EDIT_PROFILE_DETAILS)) {
            return;
        }
        if (validate()) {
            updateProfileDetails();
        }
    }

    private final void uploadProfilePic(String str) {
        AppUtil.Companion.showDialog(this);
        String str2 = this.mobileNumber;
        ProfileService profileService = new ProfileService();
        String substring = str2.substring(1, str2.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        profileService.uploadProfilePic(this, substring, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0115 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean validate() {
        boolean z;
        boolean z2;
        int i2;
        Editable text;
        boolean z3;
        TextInputLayout textInputLayout;
        int i3;
        boolean z4;
        boolean isBlank;
        boolean z5;
        boolean isBlank2;
        int i4 = R.id.edtFullName;
        Editable text2 = ((TextInputEditText) _$_findCachedViewById(i4)).getText();
        if (text2 == null || text2.length() == 0) {
            Editable text3 = ((TextInputEditText) _$_findCachedViewById(i4)).getText();
            if (text3 != null) {
                isBlank2 = StringsKt__StringsJVMKt.isBlank(text3);
                if (!isBlank2) {
                    z5 = false;
                    if (z5) {
                        ((TextInputEditText) _$_findCachedViewById(i4)).requestFocus();
                        TextInputEditText edtFullName = (TextInputEditText) _$_findCachedViewById(i4);
                        Intrinsics.checkNotNullExpressionValue(edtFullName, "edtFullName");
                        focusOnView(edtFullName);
                        ((TextInputLayout) _$_findCachedViewById(R.id.txtLayoutFullName)).setError(getString(uat.psa.mym.mycitroenconnect.R.string.enter_full_name));
                        z = false;
                        if (((AppCompatSpinner) _$_findCachedViewById(R.id.spBloodGroup)).getSelectedItemPosition() != 0) {
                            String string = getString(uat.psa.mym.mycitroenconnect.R.string.select_blood_group);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.select_blood_group)");
                            ExtensionsKt.showToast(this, string);
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        i2 = R.id.edtEmailId;
                        text = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
                        if (text != null || text.length() == 0) {
                            Editable text4 = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
                            if (text4 != null) {
                                isBlank = StringsKt__StringsJVMKt.isBlank(text4);
                                if (!isBlank) {
                                    z4 = false;
                                    if (z4) {
                                        if (!((TextInputEditText) _$_findCachedViewById(i4)).isFocused()) {
                                            ((TextInputEditText) _$_findCachedViewById(i2)).requestFocus();
                                        }
                                        textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutEmailId);
                                        i3 = uat.psa.mym.mycitroenconnect.R.string.enter_email_address;
                                        textInputLayout.setError(getString(i3));
                                        z3 = false;
                                        return !z && z2 && z3;
                                    }
                                }
                            }
                            z4 = true;
                            if (z4) {
                            }
                        }
                        if (!AppUtil.Companion.isValidEmail(String.valueOf(((TextInputEditText) _$_findCachedViewById(i2)).getText()))) {
                            z3 = true;
                            if (z) {
                            }
                        }
                        if (!((TextInputEditText) _$_findCachedViewById(i4)).isFocused()) {
                            ((TextInputEditText) _$_findCachedViewById(i2)).requestFocus();
                        }
                        textInputLayout = (TextInputLayout) _$_findCachedViewById(R.id.txtLayoutEmailId);
                        i3 = uat.psa.mym.mycitroenconnect.R.string.enter_valid_email_address;
                        textInputLayout.setError(getString(i3));
                        z3 = false;
                        if (z) {
                        }
                    }
                }
            }
            z5 = true;
            if (z5) {
            }
        }
        z = true;
        if (((AppCompatSpinner) _$_findCachedViewById(R.id.spBloodGroup)).getSelectedItemPosition() != 0) {
        }
        i2 = R.id.edtEmailId;
        text = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
        if (text != null || text.length() == 0) {
        }
        if (!AppUtil.Companion.isValidEmail(String.valueOf(((TextInputEditText) _$_findCachedViewById(i2)).getText()))) {
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
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        String apiName = event.getApiName();
        if (!Intrinsics.areEqual(apiName, ProfileService.uploadProfilePic)) {
            Intrinsics.areEqual(apiName, ProfileService.deleteProfilePic);
            ExtensionsKt.showToast(this, event.getMsg());
            return;
        }
        ExtensionsKt.showToast(this, event.getMsg());
        Logger logger = Logger.INSTANCE;
        logger.e("API: " + event.getApiName() + " Response Code: " + event.getStatusCode());
    }

    @Subscribe
    public final void getMessage(@NotNull ValidationErrorResponse validationError) {
        String replace$default;
        Intrinsics.checkNotNullParameter(validationError, "validationError");
        replace$default = StringsKt__StringsJVMKt.replace$default(validationError.getErrorList().get(0), "Unresolved key: ", "", false, 4, (Object) null);
        ExtensionsKt.showToast(this, replace$default);
    }

    @Subscribe
    public final void getMessage(@NotNull EmergencyContactResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        Logger logger = Logger.INSTANCE;
        logger.e("StatusCode: " + event.getStatusCode());
        logger.e("Message: " + event.getMessage());
        finish();
    }

    @Subscribe
    public final void getMessage(@NotNull UpdateUserProfileResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        UserProfileResponse userProfileResponse = new UserProfileResponse(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
        userProfileResponse.setBloodGroup(event.getBloodGroup());
        userProfileResponse.setAddress(event.getAddress());
        userProfileResponse.setUserPreference(event.getUserPreference());
        userProfileResponse.setGender(event.getGender());
        userProfileResponse.setCity(event.getCity());
        userProfileResponse.setRegisteredVehicle(event.getRegisteredVehicle());
        userProfileResponse.setProfilePic(event.getProfilePic());
        userProfileResponse.setFullName(event.getFullName());
        userProfileResponse.setDateOfBirth(event.getDateOfBirth());
        userProfileResponse.setUserName(event.getUserName());
        userProfileResponse.setEmail(event.getEmail());
        SharedPref.Companion companion = SharedPref.Companion;
        companion.setUserProfileResponse(this, userProfileResponse);
        String fullName = userProfileResponse.getFullName();
        if (fullName != null) {
            companion.setUserFirstName(this, fullName);
        }
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.profile_updated_successfully);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.profile_updated_successfully)");
        ExtensionsKt.showToast(this, string);
        String str = this.mPageMode;
        switch (str.hashCode()) {
            case -2065507977:
                if (!str.equals(AppConstants.PAGE_MODE_EDIT_PROFILE_DETAILS)) {
                    return;
                }
                finish();
                return;
            case -376739412:
                if (!str.equals(AppConstants.PAGE_MODE_PROFILE_DETAILS)) {
                    return;
                }
                Intent intent = new Intent(this, AppSecurityActivity.class);
                intent.putExtra(AppConstants.REGUSER, this.registerUserResponse);
                startActivity(intent);
                return;
            case 1439176829:
                if (!str.equals(AppConstants.PAGE_MODE_EDIT_EMERGENCY_CONTACT)) {
                    return;
                }
                finish();
                return;
            case 1661369742:
                if (!str.equals(AppConstants.PAGE_MODE_ADD_VEHICLE)) {
                    return;
                }
                Intent intent2 = new Intent(this, AppSecurityActivity.class);
                intent2.putExtra(AppConstants.REGUSER, this.registerUserResponse);
                startActivity(intent2);
                return;
            default:
                return;
        }
    }

    @Subscribe
    public final void getMessage(@NotNull final UserProfileResponse event) {
        List<RegisteredVehicleItem> registeredVehicle;
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        this.userProfileResponse = event;
        ((TextInputEditText) _$_findCachedViewById(R.id.edtFullName)).setText(event.getFullName());
        ((TextInputEditText) _$_findCachedViewById(R.id.edMobileNumber)).setText(this.mobileNumber);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvDateOfBirthVal)).setText(event.getDateOfBirth());
        ((TextInputEditText) _$_findCachedViewById(R.id.edtEmailId)).setText(event.getEmail());
        ((TextInputEditText) _$_findCachedViewById(R.id.edtStreetName)).setText(event.getAddress());
        ((TextInputEditText) _$_findCachedViewById(R.id.edtCity)).setText(event.getCity());
        if (event.getRegisteredVehicle() != null) {
            boolean z = true;
            if (event.getRegisteredVehicle() == null || !(!registeredVehicle.isEmpty())) {
                z = false;
            }
            if (z) {
                List<RegisteredVehicleItem> registeredVehicle2 = event.getRegisteredVehicle();
                Intrinsics.checkNotNull(registeredVehicle2);
                ((TextInputEditText) _$_findCachedViewById(R.id.edtVehicleName)).setText(registeredVehicle2.get(0).getCarModelName());
                List<RegisteredVehicleItem> registeredVehicle3 = event.getRegisteredVehicle();
                Intrinsics.checkNotNull(registeredVehicle3);
                ((TextInputEditText) _$_findCachedViewById(R.id.edtVRN)).setText(registeredVehicle3.get(0).getVehicleNumber());
                List<RegisteredVehicleItem> registeredVehicle4 = event.getRegisteredVehicle();
                Intrinsics.checkNotNull(registeredVehicle4);
                String vinNum = registeredVehicle4.get(0).getVinNum();
                Intrinsics.checkNotNull(vinNum);
                this.vin = vinNum;
            }
        }
        ((AppCompatSpinner) _$_findCachedViewById(R.id.genderSpinner)).post(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.i
            @Override // java.lang.Runnable
            public final void run() {
                ProfileDetailsActivity.m98getMessage$lambda16(UserProfileResponse.this, this);
            }
        });
        ((AppCompatSpinner) _$_findCachedViewById(R.id.spBloodGroup)).post(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.j
            @Override // java.lang.Runnable
            public final void run() {
                ProfileDetailsActivity.m99getMessage$lambda17(UserProfileResponse.this, this);
            }
        });
    }

    @Subscribe
    public final void getResponse(@NotNull FailResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        String apiName = response.getApiName();
        if (!Intrinsics.areEqual(apiName, ProfileService.uploadProfilePic)) {
            Intrinsics.areEqual(apiName, ProfileService.deleteProfilePic);
            ExtensionsKt.showToast(this, response.getMessage());
            return;
        }
        ExtensionsKt.showToast(this, response.getMessage());
        Logger logger = Logger.INSTANCE;
        logger.e("API: " + response.getApiName() + " Response Code: " + response.getStatusCode());
    }

    @Subscribe
    public final void getResponse(@NotNull PostCommonResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        String apiName = response.getApiName();
        if (Intrinsics.areEqual(apiName, ProfileService.uploadProfilePic)) {
            AppUtil.Companion.dismissDialog();
            ExtensionsKt.showToast(this, response.getMessage());
        } else if (Intrinsics.areEqual(apiName, ProfileService.deleteProfilePic)) {
            AppUtil.Companion.dismissDialog();
            ExtensionsKt.showToast(this, response.getMessage());
            ((CircleImageView) _$_findCachedViewById(R.id.imgProfile)).setImageResource(uat.psa.mym.mycitroenconnect.R.drawable.ic_default_profile);
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvChangeImage)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_upload_image));
        } else {
            Logger logger = Logger.INSTANCE;
            logger.e("API: " + response.getApiName() + " Response Code: " + response.getStatusCode() + " Message: " + response.getMessage());
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
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        View _$_findCachedViewById = _$_findCachedViewById(R.id.layoutEmergEditContactHeader);
        int i2 = R.id.ivBack;
        if (!(Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById.findViewById(i2)) ? true : Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(i2)))) {
            if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivDatePicker)) ? true : Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvDateOfBirthVal))) {
                showDOBPicker();
                return;
            } else if (Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvChangeImage))) {
                showProfilePhotoSelection();
                return;
            } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivBloodGroupInfo))) {
                showBloodGroupInfo();
                return;
            } else if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnEditCancel))) {
                if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnConfirmNContinue)) ? true : Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnEditSave))) {
                    updateSaveProfile();
                    return;
                } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnDelete))) {
                    deleteEmergencyContact();
                    return;
                } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnSave))) {
                    saveEmergencyContact();
                    return;
                } else {
                    return;
                }
            } else if (!Intrinsics.areEqual(this.mPageMode, AppConstants.PAGE_MODE_EDIT_PROFILE_DETAILS)) {
                return;
            }
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_profile_details);
        getIntentData();
        initializeView();
        setListeners();
        ImagePickerActivity.clearCache(this);
    }
}
