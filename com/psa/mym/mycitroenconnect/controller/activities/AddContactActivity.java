package com.psa.mym.mycitroenconnect.controller.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.widget.Filter;
import android.widget.FrameLayout;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.AddContactActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.AddContactAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.AddContactSelectListener;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.AddContactConfirmationFragment;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class AddContactActivity extends BaseActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor>, SearchView.OnQueryTextListener, AddContactSelectListener {
    @Nullable
    private AddContactAdapter addContactAdapter;
    private boolean isCalledOnce;
    @NotNull
    private final ActivityResultLauncher<Intent> settingResult;
    @Nullable
    private Skeleton skeleton;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static String[] PROJECTION_NUMBERS = {"contact_id", "data1", "display_name", "data2", "data3"};
    @NotNull
    private static String[] PROJECTION_DETAILS = {"_id", "in_visible_group", "display_name", "starred", "photo_uri", "photo_uri", "photo_thumb_uri", "has_phone_number"};
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int pageMode = -1;
    @NotNull
    private Map<Long, ? extends ArrayList<String>> phones = new HashMap();
    @NotNull
    private ArrayList<EmergencyDetailsItem> selectedContactList = new ArrayList<>();
    @NotNull
    private ArrayList<EmergencyDetailsItem> contactList = new ArrayList<>();

    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AddContactActivity() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: i.c
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                AddContactActivity.m69settingResult$lambda17(AddContactActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…)\n            }\n        }");
        this.settingResult = registerForActivityResult;
    }

    private final void addChildContact() {
        String string;
        String str;
        CharSequence trim;
        CharSequence trim2;
        AddContactAdapter addContactAdapter = this.addContactAdapter;
        ArrayList<EmergencyDetailsItem> selectedContact = addContactAdapter != null ? addContactAdapter.getSelectedContact() : null;
        SharedPref.Companion companion = SharedPref.Companion;
        String mobileNumber = companion.getMobileNumber(this);
        if (selectedContact != null) {
            companion.saveChildContacts(selectedContact, this);
        }
        boolean z = false;
        if (selectedContact != null && (selectedContact.isEmpty() ^ true)) {
            ArrayList<EmergencyDetailsItem> arrayList = this.selectedContactList;
            if (!arrayList.isEmpty()) {
                for (EmergencyDetailsItem emergencyDetailsItem : selectedContact) {
                    ArrayList<EmergencyDetailsItem> arrayList2 = new ArrayList();
                    for (Object obj : arrayList) {
                        AppUtil.Companion companion2 = AppUtil.Companion;
                        trim = StringsKt__StringsKt.trim((CharSequence) companion2.getValidPhoneString(((EmergencyDetailsItem) obj).getContactNum()));
                        String obj2 = trim.toString();
                        trim2 = StringsKt__StringsKt.trim((CharSequence) companion2.getValidPhoneString(emergencyDetailsItem.getContactNum()));
                        if (Intrinsics.areEqual(obj2, trim2.toString())) {
                            arrayList2.add(obj);
                        }
                    }
                    for (EmergencyDetailsItem emergencyDetailsItem2 : arrayList2) {
                        SharedPref.Companion.deleteChildContact(this, emergencyDetailsItem2);
                        z = true;
                    }
                }
            }
            if (isSameNumberAsUser(selectedContact, mobileNumber)) {
                string = getString(R.string.contact_same_as_user);
                str = "getString(R.string.contact_same_as_user)";
            } else if (!z || selectedContact.size() != 1) {
                String string2 = getString(R.string.lbl_confirm_contact);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.lbl_confirm_contact)");
                String string3 = getString(R.string.child_account_confirm_msg);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.child_account_confirm_msg)");
                String string4 = getString(R.string.child_account_confirm_success_msg);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.child…ount_confirm_success_msg)");
                showAddConfirmationDialog(string2, string3, string4, 3, this.selectedContactList);
                return;
            } else {
                string = getString(R.string.contact_already_added);
                str = "getString(R.string.contact_already_added)";
            }
        } else {
            string = getString(R.string.select_at_least_one_contact);
            str = "getString(R.string.select_at_least_one_contact)";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        ExtensionsKt.showToast(this, string);
    }

    private final void addEmergencyContact() {
        String string;
        String str;
        CharSequence trim;
        CharSequence trim2;
        AddContactAdapter addContactAdapter = this.addContactAdapter;
        ArrayList<EmergencyDetailsItem> selectedContact = addContactAdapter != null ? addContactAdapter.getSelectedContact() : null;
        AppUtil.Companion companion = AppUtil.Companion;
        SharedPref.Companion companion2 = SharedPref.Companion;
        String validPhoneString = companion.getValidPhoneString(companion2.getMobileNumber(this));
        if (selectedContact != null) {
            companion2.saveEmergencyContacts(selectedContact, this);
        }
        boolean z = false;
        if (selectedContact != null && (selectedContact.isEmpty() ^ true)) {
            ArrayList<EmergencyDetailsItem> arrayList = this.selectedContactList;
            if (!arrayList.isEmpty()) {
                for (EmergencyDetailsItem emergencyDetailsItem : selectedContact) {
                    ArrayList<EmergencyDetailsItem> arrayList2 = new ArrayList();
                    for (Object obj : arrayList) {
                        AppUtil.Companion companion3 = AppUtil.Companion;
                        trim = StringsKt__StringsKt.trim((CharSequence) companion3.getValidPhoneString(((EmergencyDetailsItem) obj).getContactNum()));
                        String obj2 = trim.toString();
                        trim2 = StringsKt__StringsKt.trim((CharSequence) companion3.getValidPhoneString(emergencyDetailsItem.getContactNum()));
                        if (Intrinsics.areEqual(obj2, trim2.toString())) {
                            arrayList2.add(obj);
                        }
                    }
                    for (EmergencyDetailsItem emergencyDetailsItem2 : arrayList2) {
                        SharedPref.Companion.deleteEmergencyContact(this, emergencyDetailsItem2);
                        z = true;
                    }
                }
            }
            if (isSameNumberAsUser(selectedContact, validPhoneString)) {
                string = getString(R.string.contact_same_as_user);
                str = "getString(R.string.contact_same_as_user)";
            } else if (z && selectedContact.size() == 1) {
                string = getString(R.string.contact_already_added);
                str = "getString(R.string.contact_already_added)";
            } else {
                int size = selectedContact.size();
                AddContactAdapter addContactAdapter2 = this.addContactAdapter;
                Integer valueOf = addContactAdapter2 != null ? Integer.valueOf(addContactAdapter2.getMaxSelectionCount()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (size <= valueOf.intValue()) {
                    String string2 = getString(R.string.label_selected_contacts);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.label_selected_contacts)");
                    String string3 = getString(R.string.label_confirm_contact);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.label_confirm_contact)");
                    String string4 = getString(R.string.label_emergency_contact_success);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.label…mergency_contact_success)");
                    showAddConfirmationDialog(string2, string3, string4, 1, this.selectedContactList);
                    return;
                }
                string = getString(R.string.contact_max_selection);
                str = "getString(R.string.contact_max_selection)";
            }
        } else {
            string = getString(R.string.select_at_least_one_contact);
            str = "getString(R.string.select_at_least_one_contact)";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        ExtensionsKt.showToast(this, string);
    }

    private final void checkContactPermission() {
        displayLoading();
        Dexter.withActivity(this).withPermissions("android.permission.READ_CONTACTS").withListener(new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.activities.AddContactActivity$checkContactPermission$1
            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionRationaleShouldBeShown(@Nullable List<PermissionRequest> list, @Nullable PermissionToken permissionToken) {
                if (permissionToken != null) {
                    permissionToken.continuePermissionRequest();
                }
            }

            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionsChecked(@Nullable MultiplePermissionsReport multiplePermissionsReport) {
                boolean z;
                boolean z2 = false;
                if (multiplePermissionsReport != null && multiplePermissionsReport.areAllPermissionsGranted()) {
                    z2 = true;
                }
                AddContactActivity addContactActivity = AddContactActivity.this;
                if (!z2) {
                    addContactActivity.showPermissionNotGrantedDialog();
                    return;
                }
                z = addContactActivity.isCalledOnce;
                if (!z) {
                    AddContactActivity.this.initCursorLoader();
                }
                AddContactActivity.this.isCalledOnce = true;
            }
        }).onSameThread().check();
    }

    private final void displayData() {
        Skeleton skeleton;
        Skeleton skeleton2 = this.skeleton;
        if (skeleton2 != null) {
            boolean z = false;
            if (skeleton2 != null && skeleton2.isSkeleton()) {
                z = true;
            }
            if (!z || (skeleton = this.skeleton) == null) {
                return;
            }
            ExtensionsKt.showData$default(skeleton, 0L, 1, null);
        }
    }

    private final void displayLoading() {
        if (this.skeleton == null) {
            FrameLayout flParent = (FrameLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.flParent);
            Intrinsics.checkNotNullExpressionValue(flParent, "flParent");
            Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(flParent, ExtensionsKt.skeletonConfig(this));
            createSkeleton.showSkeleton();
            this.skeleton = createSkeleton;
        }
    }

    private final void getIntentData() {
        if (getIntent().hasExtra(AppConstants.ARG_PAGE_MODE)) {
            this.pageMode = getIntent().getIntExtra(AppConstants.ARG_PAGE_MODE, -1);
        }
        if (getIntent().hasExtra(AppConstants.ARG_CONTACT_LIST)) {
            ArrayList<EmergencyDetailsItem> parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(AppConstants.ARG_CONTACT_LIST);
            Intrinsics.checkNotNull(parcelableArrayListExtra);
            this.selectedContactList = parcelableArrayListExtra;
        }
    }

    private final void init() {
        int i2 = this.pageMode;
        if (i2 == 1) {
            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvDbHeaderTitle)).setText(getString(R.string.label_add_emergency_contacts));
            AppCompatTextView tvDbSubTitle = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvDbSubTitle);
            Intrinsics.checkNotNullExpressionValue(tvDbSubTitle, "tvDbSubTitle");
            ExtensionsKt.hide(tvDbSubTitle);
        } else if (i2 == 2) {
            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvDbHeaderTitle)).setText(getString(R.string.add_all_contact));
            int i3 = com.psa.mym.mycitroenconnect.R.id.tvDbSubTitle;
            AppCompatTextView tvDbSubTitle2 = (AppCompatTextView) _$_findCachedViewById(i3);
            Intrinsics.checkNotNullExpressionValue(tvDbSubTitle2, "tvDbSubTitle");
            ExtensionsKt.show(tvDbSubTitle2);
            ((AppCompatTextView) _$_findCachedViewById(i3)).setText(getString(R.string.maximum_01_contacts));
        }
        ArrayList<EmergencyDetailsItem> arrayList = this.contactList;
        int i4 = this.pageMode;
        AddContactAdapter addContactAdapter = new AddContactAdapter(arrayList, i4, i4 != 1 ? i4 != 2 ? -1 : 1 : 2);
        this.addContactAdapter = addContactAdapter;
        addContactAdapter.setOnAddContactSelectListener(this);
        RecyclerView rvContactList = (RecyclerView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.rvContactList);
        if (rvContactList != null) {
            Intrinsics.checkNotNullExpressionValue(rvContactList, "rvContactList");
            rvContactList.setLayoutManager(new LinearLayoutManager(this));
            rvContactList.setAdapter(this.addContactAdapter);
        }
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivBack)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnAddContact)).setOnClickListener(this);
        ((SearchView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.svContact)).setOnQueryTextListener(this);
        checkContactPermission();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initCursorLoader() {
        LoaderManager.getInstance(this).initLoader(0, null, this);
    }

    private final boolean isSameNumberAsUser(ArrayList<EmergencyDetailsItem> arrayList, String str) {
        CharSequence trim;
        CharSequence trim2;
        for (EmergencyDetailsItem emergencyDetailsItem : arrayList) {
            AppUtil.Companion companion = AppUtil.Companion;
            trim = StringsKt__StringsKt.trim((CharSequence) companion.getValidPhoneString(str));
            String obj = trim.toString();
            trim2 = StringsKt__StringsKt.trim((CharSequence) companion.getValidPhoneString(emergencyDetailsItem.getContactNum()));
            if (Intrinsics.areEqual(obj, trim2.toString())) {
                SharedPref.Companion companion2 = SharedPref.Companion;
                companion2.deleteEmergencyContact(this, emergencyDetailsItem);
                companion2.deleteChildContact(this, emergencyDetailsItem);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: settingResult$lambda-17  reason: not valid java name */
    public static final void m69settingResult$lambda17(AddContactActivity this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult == null || activityResult.getResultCode() != 0) {
            return;
        }
        this$0.checkContactPermission();
    }

    private final void showAddConfirmationDialog(String str, String str2, String str3, int i2, ArrayList<EmergencyDetailsItem> arrayList) {
        AddContactConfirmationFragment newInstance = AddContactConfirmationFragment.Companion.newInstance(str, str2, str3, i2, arrayList);
        newInstance.setOnContactAddedSuccessListener(this);
        newInstance.show(getSupportFragmentManager(), AddContactConfirmationFragment.TAG);
        newInstance.setCancelable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPermissionNotGrantedDialog() {
        AlertDialog create = new AlertDialog.Builder(this).create();
        Intrinsics.checkNotNullExpressionValue(create, "Builder(this@AddContactA…ty)\n            .create()");
        create.setTitle(getString(R.string.permission_denied_title));
        create.setMessage(getString(R.string.permission_denied_message));
        create.setIcon(R.mipmap.mycitroen_app);
        create.setCancelable(false);
        create.setCanceledOnTouchOutside(false);
        create.setButton(-1, getString(R.string.settings), new DialogInterface.OnClickListener() { // from class: i.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                AddContactActivity.m70showPermissionNotGrantedDialog$lambda18(AddContactActivity.this, dialogInterface, i2);
            }
        });
        create.setButton(-2, getString(R.string.label_cancel), new DialogInterface.OnClickListener() { // from class: i.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                AddContactActivity.m71showPermissionNotGrantedDialog$lambda19(AddContactActivity.this, dialogInterface, i2);
            }
        });
        Window window = create.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.drawable.ic_dialog_rounded_background);
        }
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showPermissionNotGrantedDialog$lambda-18  reason: not valid java name */
    public static final void m70showPermissionNotGrantedDialog$lambda18(AddContactActivity this$0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialogInterface.dismiss();
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.getPackageName(), null));
        this$0.settingResult.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showPermissionNotGrantedDialog$lambda-19  reason: not valid java name */
    public static final void m71showPermissionNotGrantedDialog$lambda19(AddContactActivity this$0, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialogInterface.dismiss();
        this$0.finish();
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

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.AddContactSelectListener
    public void onAddContactSelect(int i2) {
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivBack))) {
            finish();
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnAddContact))) {
            int i2 = this.pageMode;
            if (i2 == 1) {
                addEmergencyContact();
            } else if (i2 != 2) {
            } else {
                addChildContact();
            }
        }
    }

    public void onContactAddedSuccess() {
        int i2 = this.pageMode;
        if (i2 == 1 || i2 == 2) {
            ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivBack)).performClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_contact);
        getIntentData();
        init();
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    @NotNull
    public Loader<Cursor> onCreateLoader(int i2, @Nullable Bundle bundle) {
        return i2 == 0 ? new CursorLoader(this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION_NUMBERS, null, null, "display_name") : new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, PROJECTION_DETAILS, null, null, "display_name");
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    @SuppressLint({"NotifyDataSetChanged"})
    public void onLoadFinished(@NotNull Loader<Cursor> loader, @Nullable Cursor cursor) {
        ArrayList arrayList;
        boolean isBlank;
        Filter filter;
        Intrinsics.checkNotNullParameter(loader, "loader");
        HashSet hashSet = new HashSet();
        int id = loader.getId();
        int i2 = 0;
        if (id == 0) {
            this.phones = new HashMap();
            if (cursor != null) {
                while (!cursor.isClosed() && cursor.moveToNext()) {
                    long j2 = cursor.getLong(0);
                    String string = cursor.getString(1);
                    if (this.phones.containsKey(Long.valueOf(j2))) {
                        RandomAccess randomAccess = this.phones.get(Long.valueOf(j2));
                        Intrinsics.checkNotNull(randomAccess);
                        arrayList = (ArrayList) randomAccess;
                    } else {
                        ArrayList arrayList2 = new ArrayList();
                        ((HashMap) this.phones).put(Long.valueOf(j2), arrayList2);
                        arrayList = arrayList2;
                    }
                    arrayList.add(string);
                }
                cursor.close();
            }
            LoaderManager.getInstance(this).initLoader(1, null, this);
        } else if (id == 1) {
            if (cursor != null) {
                while (!cursor.isClosed() && cursor.moveToNext()) {
                    long j3 = cursor.getLong(i2);
                    String name = cursor.getString(2);
                    ArrayList<String> arrayList3 = this.phones.get(Long.valueOf(j3));
                    if (arrayList3 != null) {
                        for (String str : arrayList3) {
                            AppUtil.Companion companion = AppUtil.Companion;
                            String validPhoneString = companion.getValidPhoneString(str);
                            EmergencyDetailsItem emergencyDetailsItem = new EmergencyDetailsItem(null, null, null, false, false, 0, null, null, null, null, null, 2047, null);
                            emergencyDetailsItem.setId(String.valueOf(j3));
                            Intrinsics.checkNotNullExpressionValue(name, "name");
                            emergencyDetailsItem.setName(name);
                            emergencyDetailsItem.setContactNum(validPhoneString);
                            Logger logger = Logger.INSTANCE;
                            logger.e("Phone: " + str + " is valid number?: " + companion.isValidNumber(str));
                            if (!hashSet.contains(validPhoneString) && companion.isValidNumber(str)) {
                                this.contactList.add(emergencyDetailsItem);
                                hashSet.add(validPhoneString);
                                logger.i("Mobile No: " + emergencyDetailsItem.getContactNum());
                            }
                            i2 = 0;
                        }
                    }
                }
                ArrayList<EmergencyDetailsItem> arrayList4 = this.contactList;
                if (arrayList4.size() > 1) {
                    CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList4, new Comparator() { // from class: com.psa.mym.mycitroenconnect.controller.activities.AddContactActivity$onLoadFinished$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t2, T t3) {
                            int compareValues;
                            compareValues = ComparisonsKt__ComparisonsKt.compareValues(((EmergencyDetailsItem) t2).getName(), ((EmergencyDetailsItem) t3).getName());
                            return compareValues;
                        }
                    });
                }
                cursor.close();
                if (this.contactList.size() != 0) {
                    ArrayList<EmergencyDetailsItem> arrayList5 = this.selectedContactList;
                    AddContactAdapter addContactAdapter = this.addContactAdapter;
                    if (addContactAdapter != null) {
                        addContactAdapter.setMaxSelectionCount(arrayList5.size());
                    }
                    AddContactAdapter addContactAdapter2 = this.addContactAdapter;
                    if (addContactAdapter2 != null) {
                        addContactAdapter2.notifyDataSetChanged();
                    }
                    int i3 = com.psa.mym.mycitroenconnect.R.id.svContact;
                    if (((SearchView) _$_findCachedViewById(i3)) != null && ((SearchView) _$_findCachedViewById(i3)).getQuery() != null) {
                        CharSequence query = ((SearchView) _$_findCachedViewById(i3)).getQuery();
                        Intrinsics.checkNotNullExpressionValue(query, "svContact.query");
                        if (query.length() > 0) {
                            CharSequence query2 = ((SearchView) _$_findCachedViewById(i3)).getQuery();
                            Intrinsics.checkNotNullExpressionValue(query2, "svContact.query");
                            isBlank = StringsKt__StringsJVMKt.isBlank(query2);
                            if (!isBlank) {
                                Logger logger2 = Logger.INSTANCE;
                                logger2.e("Query String: " + ((Object) ((SearchView) _$_findCachedViewById(i3)).getQuery()));
                                AddContactAdapter addContactAdapter3 = this.addContactAdapter;
                                if (addContactAdapter3 != null && (filter = addContactAdapter3.getFilter()) != null) {
                                    filter.filter(((SearchView) _$_findCachedViewById(i3)).getQuery());
                                }
                            }
                        }
                    }
                }
            }
            displayData();
        }
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(@NotNull Loader<Cursor> loader) {
        Intrinsics.checkNotNullParameter(loader, "loader");
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(@Nullable String str) {
        Filter filter;
        AddContactAdapter addContactAdapter = this.addContactAdapter;
        if (addContactAdapter == null || (filter = addContactAdapter.getFilter()) == null) {
            return true;
        }
        filter.filter(str);
        return true;
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(@Nullable String str) {
        return true;
    }
}
