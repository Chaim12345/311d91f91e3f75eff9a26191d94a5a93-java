package com.psa.mym.mycitroenconnect.controller.activities;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.BusBaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.dashboard.EmergencyContactBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.UpdateSecondaryUserNameBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.EditContactActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.CarAccessListener;
import com.psa.mym.mycitroenconnect.controller.adapters.SelectCarAccessAdapter;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.CarAccessConfirmationDialog;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.DeleteConfirmationFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnCarAccessDialogDismiss;
import com.psa.mym.mycitroenconnect.controller.dialog.InfoDialog;
import com.psa.mym.mycitroenconnect.controller.dialog.OnInfoDialogDismiss;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.onboarding.UpdateUserProfileResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUser;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUserCommonResponse;
import com.psa.mym.mycitroenconnect.services.DashboardService;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Marker;
/* loaded from: classes2.dex */
public final class EditContactActivity extends BusBaseActivity implements CarAccessListener, OnInfoDialogDismiss, OnCarAccessDialogDismiss {
    @Nullable
    private SelectCarAccessAdapter carAccessAdapter;
    @Nullable
    private SelectCarAccessAdapter carAccessNotGrantedAdapter;
    @Nullable
    private EmergencyDetailsItem contact;
    @Nullable
    private ArrayList<EmergencyDetailsItem> contactList;
    @Nullable
    private Integer pageMode;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private List<RegisteredVehicleItem> carAccessGrantedList = new ArrayList();
    @NotNull
    private List<RegisteredVehicleItem> carAccessNotGrantedList = new ArrayList();
    @NotNull
    private ArrayList<SecondaryUser> statusMapList = new ArrayList<>();

    private final void deleteEmergencyAccount() {
        String valueOf = String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtFullName)).getText());
        EmergencyDetailsItem emergencyDetailsItem = this.contact;
        String valueOf2 = String.valueOf(emergencyDetailsItem != null ? emergencyDetailsItem.getContactNum() : null);
        ArrayList<EmergencyDetailsItem> arrayList = this.contactList;
        Intrinsics.checkNotNull(arrayList);
        showDeleteConfirmDialog(valueOf, valueOf2, 1, arrayList);
    }

    private final void displayEditNameView() {
        ((TextInputEditText) _$_findCachedViewById(R.id.edtFullName)).setEnabled(true);
        AppCompatImageView ivEdit = (AppCompatImageView) _$_findCachedViewById(R.id.ivEdit);
        Intrinsics.checkNotNullExpressionValue(ivEdit, "ivEdit");
        ExtensionsKt.invisible(ivEdit);
        View viewFullNameLine = _$_findCachedViewById(R.id.viewFullNameLine);
        Intrinsics.checkNotNullExpressionValue(viewFullNameLine, "viewFullNameLine");
        ExtensionsKt.show(viewFullNameLine);
        AppCompatImageView ivNameUpdateCheck = (AppCompatImageView) _$_findCachedViewById(R.id.ivNameUpdateCheck);
        Intrinsics.checkNotNullExpressionValue(ivNameUpdateCheck, "ivNameUpdateCheck");
        ExtensionsKt.show(ivNameUpdateCheck);
        AppCompatImageView ivClose = (AppCompatImageView) _$_findCachedViewById(R.id.ivClose);
        Intrinsics.checkNotNullExpressionValue(ivClose, "ivClose");
        ExtensionsKt.show(ivClose);
    }

    private final void generateAccessCarsList() {
        for (RegisteredVehicleItem registeredVehicleItem : SharedPref.Companion.getRegisteredVehicleList(this)) {
            if (!isCarAdded(String.valueOf(registeredVehicleItem.getVinNum()))) {
                this.carAccessNotGrantedList.add(registeredVehicleItem);
            }
        }
        EmergencyDetailsItem emergencyDetailsItem = this.contact;
        ArrayList<RegisteredVehicleItem> carAccess = emergencyDetailsItem != null ? emergencyDetailsItem.getCarAccess() : null;
        Intrinsics.checkNotNull(carAccess);
        this.carAccessGrantedList = carAccess;
        updateInviteStatus();
    }

    private final void getIntentData() {
        ArrayList<SecondaryUser> parcelableArrayListExtra;
        if (getIntent() != null) {
            this.pageMode = Integer.valueOf(getIntent().getIntExtra(AppConstants.ARG_PAGE_MODE, -1));
            this.contact = (EmergencyDetailsItem) getIntent().getParcelableExtra(AppConstants.ARG_CONTACT);
            this.contactList = getIntent().getParcelableArrayListExtra(AppConstants.ARG_CONTACT_LIST);
            if (!getIntent().hasExtra(AppConstants.ARG_STATUS_LIST) || (parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(AppConstants.ARG_STATUS_LIST)) == null) {
                return;
            }
            this.statusMapList = parcelableArrayListExtra;
        }
    }

    private final void hideEditNameView() {
        int i2 = R.id.edtFullName;
        ((TextInputEditText) _$_findCachedViewById(i2)).setEnabled(false);
        TextInputEditText textInputEditText = (TextInputEditText) _$_findCachedViewById(i2);
        EmergencyDetailsItem emergencyDetailsItem = this.contact;
        textInputEditText.setText(emergencyDetailsItem != null ? emergencyDetailsItem.getName() : null);
        AppCompatImageView ivEdit = (AppCompatImageView) _$_findCachedViewById(R.id.ivEdit);
        Intrinsics.checkNotNullExpressionValue(ivEdit, "ivEdit");
        ExtensionsKt.show(ivEdit);
        View viewFullNameLine = _$_findCachedViewById(R.id.viewFullNameLine);
        Intrinsics.checkNotNullExpressionValue(viewFullNameLine, "viewFullNameLine");
        ExtensionsKt.hide(viewFullNameLine);
        AppCompatImageView ivNameUpdateCheck = (AppCompatImageView) _$_findCachedViewById(R.id.ivNameUpdateCheck);
        Intrinsics.checkNotNullExpressionValue(ivNameUpdateCheck, "ivNameUpdateCheck");
        ExtensionsKt.hide(ivNameUpdateCheck);
        AppCompatImageView ivClose = (AppCompatImageView) _$_findCachedViewById(R.id.ivClose);
        Intrinsics.checkNotNullExpressionValue(ivClose, "ivClose");
        ExtensionsKt.hide(ivClose);
    }

    private final void init() {
        Integer num = this.pageMode;
        if (num != null && num.intValue() == 1) {
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_edit_emergency_contact));
        } else if (num != null && num.intValue() == 2) {
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvDbHeaderTitle)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.edit_child_account));
            generateAccessCarsList();
        }
        TextInputEditText textInputEditText = (TextInputEditText) _$_findCachedViewById(R.id.edtFullName);
        EmergencyDetailsItem emergencyDetailsItem = this.contact;
        textInputEditText.setText(emergencyDetailsItem != null ? emergencyDetailsItem.getName() : null);
        TextInputEditText textInputEditText2 = (TextInputEditText) _$_findCachedViewById(R.id.edtMobileNumber);
        EmergencyDetailsItem emergencyDetailsItem2 = this.contact;
        textInputEditText2.setText(emergencyDetailsItem2 != null ? emergencyDetailsItem2.getContactNum() : null);
        initRecyclerView();
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnSave)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnDelete)).setOnClickListener(this);
        ((TextInputLayout) _$_findCachedViewById(R.id.tilMobileNumber)).setEndIconOnClickListener(new View.OnClickListener() { // from class: i.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditContactActivity.m77init$lambda1(EditContactActivity.this, view);
            }
        });
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivEdit)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivNameUpdateCheck)).setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: init$lambda-1  reason: not valid java name */
    public static final void m77init$lambda1(EditContactActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((TextInputLayout) this$0._$_findCachedViewById(R.id.tilMobileNumber)).setEndIconTintList(ColorStateList.valueOf(this$0.getColor(uat.psa.mym.mycitroenconnect.R.color.primary_color_1)));
        String string = this$0.getString(uat.psa.mym.mycitroenconnect.R.string.label_trips_info_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.label_trips_info_title)");
        String string2 = this$0.getString(uat.psa.mym.mycitroenconnect.R.string.info_edit_child_account_mobile);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.info_edit_child_account_mobile)");
        InfoDialog infoDialog = new InfoDialog(this$0, string, string2);
        infoDialog.setOnDialogDismiss(this$0);
        infoDialog.show();
    }

    private final void initRecyclerView() {
        List<RegisteredVehicleItem> list = this.carAccessGrantedList;
        ArrayList<RegisteredVehicleItem> arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (((RegisteredVehicleItem) next).getViewType() != 2) {
                arrayList.add(next);
            }
        }
        for (RegisteredVehicleItem registeredVehicleItem : arrayList) {
            registeredVehicleItem.setViewType(2);
        }
        SelectCarAccessAdapter selectCarAccessAdapter = new SelectCarAccessAdapter((ArrayList) this.carAccessGrantedList);
        this.carAccessAdapter = selectCarAccessAdapter;
        Intrinsics.checkNotNull(selectCarAccessAdapter);
        selectCarAccessAdapter.setCarAccessListener(this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvCarAccess);
        if (recyclerView != null) {
            recyclerView.setAdapter(this.carAccessAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        List<RegisteredVehicleItem> list2 = this.carAccessNotGrantedList;
        ArrayList<RegisteredVehicleItem> arrayList2 = new ArrayList();
        for (Object obj : list2) {
            if (((RegisteredVehicleItem) obj).getViewType() != 3) {
                arrayList2.add(obj);
            }
        }
        for (RegisteredVehicleItem registeredVehicleItem2 : arrayList2) {
            registeredVehicleItem2.setViewType(3);
        }
        SelectCarAccessAdapter selectCarAccessAdapter2 = new SelectCarAccessAdapter((ArrayList) this.carAccessNotGrantedList);
        this.carAccessNotGrantedAdapter = selectCarAccessAdapter2;
        Intrinsics.checkNotNull(selectCarAccessAdapter2);
        selectCarAccessAdapter2.setCarAccessListener(this);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvOtherAvailableCars);
        if (recyclerView2 != null) {
            recyclerView2.setAdapter(this.carAccessNotGrantedAdapter);
            recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        }
        updateOtherCarLabel();
        updateCarAccessLabel();
    }

    private final boolean isCarAdded(String str) {
        ArrayList<RegisteredVehicleItem> carAccess;
        EmergencyDetailsItem emergencyDetailsItem = this.contact;
        if (emergencyDetailsItem != null && (carAccess = emergencyDetailsItem.getCarAccess()) != null) {
            int i2 = 0;
            for (Object obj : carAccess) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                if (Intrinsics.areEqual(((RegisteredVehicleItem) obj).getVinNum(), str)) {
                    return true;
                }
                i2 = i3;
            }
        }
        return false;
    }

    private final void saveChildAccount() {
        ArrayList<EmergencyDetailsItem> arrayList = this.contactList;
        if (arrayList != null) {
            ArrayList<EmergencyDetailsItem> arrayList2 = new ArrayList();
            Iterator<T> it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                String contactNum = ((EmergencyDetailsItem) next).getContactNum();
                EmergencyDetailsItem emergencyDetailsItem = this.contact;
                if (Intrinsics.areEqual(contactNum, emergencyDetailsItem != null ? emergencyDetailsItem.getContactNum() : null)) {
                    arrayList2.add(next);
                }
            }
            for (EmergencyDetailsItem emergencyDetailsItem2 : arrayList2) {
                emergencyDetailsItem2.setName(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtFullName)).getText()));
                emergencyDetailsItem2.setContactNum(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtMobileNumber)).getText()));
                emergencyDetailsItem2.setCountryCode("+91");
                EmergencyDetailsItem emergencyDetailsItem3 = this.contact;
                ArrayList<RegisteredVehicleItem> carAccess = emergencyDetailsItem3 != null ? emergencyDetailsItem3.getCarAccess() : null;
                Intrinsics.checkNotNull(carAccess);
                emergencyDetailsItem2.setCarAccess(carAccess);
            }
        }
        SharedPref.Companion companion = SharedPref.Companion;
        ArrayList<EmergencyDetailsItem> arrayList3 = this.contactList;
        Intrinsics.checkNotNull(arrayList3);
        companion.saveChildContacts(arrayList3, this);
        finish();
    }

    private final void saveEmergencyAccount() {
        updateEmergencyContact();
    }

    private final void showDeleteConfirmDialog(String str, String str2, int i2, ArrayList<EmergencyDetailsItem> arrayList) {
        DeleteConfirmationFragment newInstance;
        newInstance = DeleteConfirmationFragment.Companion.newInstance(str, str2, i2, (r16 & 8) != 0 ? null : arrayList, (r16 & 16) != 0 ? null : null, (r16 & 32) != 0 ? -1 : 0);
        newInstance.show(getSupportFragmentManager(), DeleteConfirmationFragment.TAG);
        newInstance.setCancelable(false);
    }

    private final void updateCarAccessLabel() {
        if (this.carAccessGrantedList.size() == 0) {
            AppCompatTextView tvCarAccess = (AppCompatTextView) _$_findCachedViewById(R.id.tvCarAccess);
            Intrinsics.checkNotNullExpressionValue(tvCarAccess, "tvCarAccess");
            ExtensionsKt.hide(tvCarAccess);
            View divider = _$_findCachedViewById(R.id.divider);
            Intrinsics.checkNotNullExpressionValue(divider, "divider");
            ExtensionsKt.hide(divider);
            return;
        }
        AppCompatTextView tvCarAccess2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvCarAccess);
        Intrinsics.checkNotNullExpressionValue(tvCarAccess2, "tvCarAccess");
        ExtensionsKt.show(tvCarAccess2);
        if (this.carAccessNotGrantedList.size() > 0) {
            View divider2 = _$_findCachedViewById(R.id.divider);
            Intrinsics.checkNotNullExpressionValue(divider2, "divider");
            ExtensionsKt.show(divider2);
        }
    }

    private final void updateEmergencyContact() {
        String replace$default;
        CharSequence trim;
        EmergencyContactBody emergencyContactBody = new EmergencyContactBody(null, null, null, 7, null);
        replace$default = StringsKt__StringsJVMKt.replace$default(SharedPref.Companion.getMobileNumber(this), "+91", "", false, 4, (Object) null);
        trim = StringsKt__StringsKt.trim((CharSequence) replace$default);
        emergencyContactBody.setUserName(trim.toString());
        emergencyContactBody.setCountryCode("+91");
        ArrayList<EmergencyDetailsItem> arrayList = this.contactList;
        if (arrayList != null) {
            ArrayList<EmergencyDetailsItem> arrayList2 = new ArrayList();
            Iterator<T> it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                String contactNum = ((EmergencyDetailsItem) next).getContactNum();
                EmergencyDetailsItem emergencyDetailsItem = this.contact;
                if (Intrinsics.areEqual(contactNum, emergencyDetailsItem != null ? emergencyDetailsItem.getContactNum() : null)) {
                    arrayList2.add(next);
                }
            }
            for (EmergencyDetailsItem emergencyDetailsItem2 : arrayList2) {
                EmergencyDetailsItem emergencyDetailsItem3 = this.contact;
                if (emergencyDetailsItem3 != null) {
                    emergencyDetailsItem3.setName(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtFullName)).getText()));
                }
                EmergencyDetailsItem emergencyDetailsItem4 = this.contact;
                if (emergencyDetailsItem4 != null) {
                    emergencyDetailsItem4.setContactNum(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edMobileNumber)).getText()));
                }
                EmergencyDetailsItem emergencyDetailsItem5 = this.contact;
                emergencyDetailsItem2.setName(String.valueOf(emergencyDetailsItem5 != null ? emergencyDetailsItem5.getName() : null));
                emergencyDetailsItem2.setContactNum(AppUtil.Companion.getValidPhoneString(emergencyDetailsItem2.getContactNum()));
                emergencyDetailsItem2.setCountryCode("+91");
            }
        }
        emergencyContactBody.setEmergencyDetails(this.contactList);
        AppUtil.Companion.showDialog(this);
        new DashboardService().callUpdateEmergencyContactAPI(this, emergencyContactBody);
    }

    private final void updateInviteStatus() {
        for (RegisteredVehicleItem registeredVehicleItem : this.carAccessGrantedList) {
            for (SecondaryUser secondaryUser : this.statusMapList) {
                if (Intrinsics.areEqual(secondaryUser.getSharedVehicle().get(0).getVinNum(), registeredVehicleItem.getVinNum())) {
                    String substring = secondaryUser.getSecondaryUsername().substring(2, secondaryUser.getSecondaryUsername().length());
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    EmergencyDetailsItem emergencyDetailsItem = this.contact;
                    if (Intrinsics.areEqual(substring, emergencyDetailsItem != null ? emergencyDetailsItem.getContactNum() : null)) {
                        registeredVehicleItem.setInviteStatus(secondaryUser.getInviteStatus());
                    }
                }
            }
        }
    }

    private final void updateOtherCarLabel() {
        if (this.carAccessNotGrantedList.size() == 0) {
            AppCompatTextView tvOtherAvailableCars = (AppCompatTextView) _$_findCachedViewById(R.id.tvOtherAvailableCars);
            Intrinsics.checkNotNullExpressionValue(tvOtherAvailableCars, "tvOtherAvailableCars");
            ExtensionsKt.hide(tvOtherAvailableCars);
            View divider = _$_findCachedViewById(R.id.divider);
            Intrinsics.checkNotNullExpressionValue(divider, "divider");
            ExtensionsKt.hide(divider);
            return;
        }
        AppCompatTextView tvOtherAvailableCars2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvOtherAvailableCars);
        Intrinsics.checkNotNullExpressionValue(tvOtherAvailableCars2, "tvOtherAvailableCars");
        ExtensionsKt.show(tvOtherAvailableCars2);
        View divider2 = _$_findCachedViewById(R.id.divider);
        Intrinsics.checkNotNullExpressionValue(divider2, "divider");
        ExtensionsKt.show(divider2);
    }

    private final void updateSecondaryUserName() {
        String replace$default;
        CharSequence trim;
        String valueOf = String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtFullName)).getText());
        replace$default = StringsKt__StringsJVMKt.replace$default(SharedPref.Companion.getMobileNumber(this), Marker.ANY_NON_NULL_MARKER, "", false, 4, (Object) null);
        trim = StringsKt__StringsKt.trim((CharSequence) replace$default);
        UpdateSecondaryUserNameBody updateSecondaryUserNameBody = new UpdateSecondaryUserNameBody(valueOf, "+91", trim.toString(), String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtMobileNumber)).getText()));
        AppUtil.Companion.showDialog(this);
        new SecondaryUserService().callUpdateSecondaryUserName(this, updateSecondaryUserNameBody);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0035 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean validate() {
        boolean z;
        boolean isBlank;
        int i2 = R.id.edtFullName;
        Editable text = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
        if (!(text == null || text.length() == 0)) {
            Editable text2 = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
            if (text2 != null) {
                isBlank = StringsKt__StringsJVMKt.isBlank(text2);
                if (!isBlank) {
                    z = false;
                    if (!z) {
                        return true;
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
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0035 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean validateSecondaryUserName() {
        boolean z;
        boolean isBlank;
        int i2 = R.id.edtFullName;
        Editable text = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
        if (!(text == null || text.length() == 0)) {
            Editable text2 = ((TextInputEditText) _$_findCachedViewById(i2)).getText();
            if (text2 != null) {
                isBlank = StringsKt__StringsJVMKt.isBlank(text2);
                if (!isBlank) {
                    z = false;
                    if (!z) {
                        return true;
                    }
                }
            }
            z = true;
            if (!z) {
            }
        }
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.msg_enter_name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.msg_enter_name)");
        ExtensionsKt.showToast(this, string);
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

    @NotNull
    public final List<RegisteredVehicleItem> getCarAccessGrantedList() {
        return this.carAccessGrantedList;
    }

    @NotNull
    public final List<RegisteredVehicleItem> getCarAccessNotGrantedList() {
        return this.carAccessNotGrantedList;
    }

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        ExtensionsKt.showToast(this, event.getMsg());
    }

    @Subscribe
    public final void getMessage(@NotNull UpdateUserProfileResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        SharedPref.Companion.setUpdateUserProfileResponse(this, event);
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.profile_updated_successfully);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.profile_updated_successfully)");
        ExtensionsKt.showToast(this, string);
        Integer num = this.pageMode;
        if (num != null && num.intValue() == 1) {
            finish();
        }
    }

    @Subscribe
    public final void getMessage(@NotNull SecondaryUserCommonResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getApiName(), AppConstants.API_NAME_UPDATE_SECONDARY_USER_NAME) && Intrinsics.areEqual(event.getSuccess(), "true")) {
            ExtensionsKt.showToast(this, event.getMessage());
            EmergencyDetailsItem emergencyDetailsItem = this.contact;
            if (emergencyDetailsItem != null) {
                emergencyDetailsItem.setName(String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtFullName)).getText()));
            }
            hideEditNameView();
        }
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.CarAccessListener
    public void onCarAccessButtonTap(int i2, @NotNull RegisteredVehicleItem car) {
        CarAccessConfirmationDialog newInstance$default;
        Intrinsics.checkNotNullParameter(car, "car");
        if (car.getViewType() == 2) {
            String str = "";
            SelectCarAccessAdapter selectCarAccessAdapter = this.carAccessAdapter;
            if (selectCarAccessAdapter != null && selectCarAccessAdapter.getItemCount() == 1) {
                str = getString(uat.psa.mym.mycitroenconnect.R.string.child_account_deleted_if_car_access_revoked);
                Intrinsics.checkNotNullExpressionValue(str, "getString(R.string.child…ed_if_car_access_revoked)");
            }
            String str2 = str;
            CarAccessConfirmationDialog.Companion companion = CarAccessConfirmationDialog.Companion;
            String valueOf = String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtFullName)).getText());
            EmergencyDetailsItem emergencyDetailsItem = this.contact;
            newInstance$default = companion.newInstance(car, valueOf, emergencyDetailsItem != null ? emergencyDetailsItem.getContactNum() : null, AppConstants.PAGE_MODE_REVOKE_ACCESS, str2);
        } else {
            CarAccessConfirmationDialog.Companion companion2 = CarAccessConfirmationDialog.Companion;
            String valueOf2 = String.valueOf(((TextInputEditText) _$_findCachedViewById(R.id.edtFullName)).getText());
            EmergencyDetailsItem emergencyDetailsItem2 = this.contact;
            newInstance$default = CarAccessConfirmationDialog.Companion.newInstance$default(companion2, car, valueOf2, emergencyDetailsItem2 != null ? emergencyDetailsItem2.getContactNum() : null, AppConstants.PAGE_MODE_GIVE_ACCESS, null, 16, null);
        }
        newInstance$default.setOnDialogDismiss(this);
        newInstance$default.show(getSupportFragmentManager(), CarAccessConfirmationDialog.TAG);
        newInstance$default.setCancelable(false);
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.CarAccessListener
    public void onCarAccessChanged(int i2, @NotNull RegisteredVehicleItem car) {
        Intrinsics.checkNotNullParameter(car, "car");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
        if (r1 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
        r1.notifyDataSetChanged();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        updateOtherCarLabel();
        updateCarAccessLabel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0056, code lost:
        if (r1 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0059, code lost:
        return;
     */
    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnCarAccessDialogDismiss
    @SuppressLint({"NotifyDataSetChanged"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCarAccessDialogDismiss(boolean z, @Nullable RegisteredVehicleItem registeredVehicleItem, @Nullable String str) {
        SelectCarAccessAdapter selectCarAccessAdapter;
        if (Intrinsics.areEqual(str, AppConstants.PAGE_MODE_GIVE_ACCESS)) {
            if (registeredVehicleItem != null) {
                registeredVehicleItem.setViewType(2);
            }
            List<RegisteredVehicleItem> list = this.carAccessGrantedList;
            Intrinsics.checkNotNull(registeredVehicleItem);
            list.add(registeredVehicleItem);
            SelectCarAccessAdapter selectCarAccessAdapter2 = this.carAccessAdapter;
            if (selectCarAccessAdapter2 != null) {
                selectCarAccessAdapter2.notifyDataSetChanged();
            }
            this.carAccessNotGrantedList.remove(registeredVehicleItem);
            selectCarAccessAdapter = this.carAccessNotGrantedAdapter;
        } else if (!Intrinsics.areEqual(str, AppConstants.PAGE_MODE_REVOKE_ACCESS)) {
        } else {
            if (registeredVehicleItem != null) {
                registeredVehicleItem.setViewType(3);
            }
            List<RegisteredVehicleItem> list2 = this.carAccessNotGrantedList;
            Intrinsics.checkNotNull(registeredVehicleItem);
            list2.add(registeredVehicleItem);
            SelectCarAccessAdapter selectCarAccessAdapter3 = this.carAccessNotGrantedAdapter;
            if (selectCarAccessAdapter3 != null) {
                selectCarAccessAdapter3.notifyDataSetChanged();
            }
            this.carAccessGrantedList.remove(registeredVehicleItem);
            selectCarAccessAdapter = this.carAccessAdapter;
        }
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivBack))) {
            finish();
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnSave))) {
            if (validate()) {
                Integer num = this.pageMode;
                if (num == null || num.intValue() != 2) {
                    if (num != null && num.intValue() == 1) {
                        saveEmergencyAccount();
                        return;
                    }
                    return;
                }
                SelectCarAccessAdapter selectCarAccessAdapter = this.carAccessAdapter;
                Boolean valueOf = selectCarAccessAdapter != null ? Boolean.valueOf(selectCarAccessAdapter.isCarAccessGiven()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue()) {
                    saveChildAccount();
                    return;
                }
                String string = getString(uat.psa.mym.mycitroenconnect.R.string.msg_choose_min_01_car);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.msg_choose_min_01_car)");
                ExtensionsKt.showToast(this, string);
            }
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnDelete))) {
            Integer num2 = this.pageMode;
            if ((num2 != null && num2.intValue() == 2) || num2 == null || num2.intValue() != 1) {
                return;
            }
            deleteEmergencyAccount();
        } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivEdit))) {
            displayEditNameView();
        } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivClose))) {
            hideEditNameView();
        } else if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivNameUpdateCheck)) && validateSecondaryUserName()) {
            updateSecondaryUserName();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_edit_contact);
        getIntentData();
        init();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.dialog.OnInfoDialogDismiss
    public void onDialogDismiss() {
        ((TextInputLayout) _$_findCachedViewById(R.id.tilMobileNumber)).setEndIconTintList(ColorStateList.valueOf(getColor(uat.psa.mym.mycitroenconnect.R.color.hot_grey_100)));
    }

    public final void setCarAccessGrantedList(@NotNull List<RegisteredVehicleItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.carAccessGrantedList = list;
    }

    public final void setCarAccessNotGrantedList(@NotNull List<RegisteredVehicleItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.carAccessNotGrantedList = list;
    }
}
