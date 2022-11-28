package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.dashboard.EmergencyContactBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.ContactListAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.ContactListInterface;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.SelectCarForViewAccessDialog;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyContactResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.services.DashboardService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AddContactConfirmationFragment extends BusBaseBottomSheetFragment implements View.OnClickListener, OnContactAddedSuccessListener, ContactListInterface {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "AddContactConfirmationFragment";
    @Nullable
    private ContactListAdapter contactAdapter;
    @Nullable
    private String message;
    @Nullable
    private OnContactAddedSuccessListener onContactAddedSuccessListener;
    @Nullable
    private Integer pageMode;
    @Nullable
    private String successMessage;
    @Nullable
    private String title;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private ArrayList<EmergencyDetailsItem> contactList = new ArrayList<>();
    @Nullable
    private ArrayList<EmergencyDetailsItem> checkedContactList = new ArrayList<>();

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final AddContactConfirmationFragment newInstance(@NotNull String title, @NotNull String message, @NotNull String successMessage, int i2, @Nullable ArrayList<EmergencyDetailsItem> arrayList) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(successMessage, "successMessage");
            AddContactConfirmationFragment addContactConfirmationFragment = new AddContactConfirmationFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putString(AppConstants.ARG_MESSAGE, message);
            bundle.putString(AppConstants.ARG_SUCCESS_MESSAGE, successMessage);
            bundle.putInt(AppConstants.ARG_PAGE_MODE, i2);
            bundle.putParcelableArrayList(AppConstants.ARG_CONTACT_LIST, arrayList);
            addContactConfirmationFragment.setArguments(bundle);
            return addContactConfirmationFragment;
        }
    }

    private final void addEmergencyContact() {
        String replace$default;
        CharSequence trim;
        ArrayList<EmergencyDetailsItem> arrayList;
        EmergencyContactBody emergencyContactBody = new EmergencyContactBody(null, null, null, 7, null);
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        replace$default = StringsKt__StringsJVMKt.replace$default(companion.getMobileNumber(requireContext), "+91", "", false, 4, (Object) null);
        trim = StringsKt__StringsKt.trim((CharSequence) replace$default);
        emergencyContactBody.setUserName(trim.toString());
        emergencyContactBody.setCountryCode("+91");
        ArrayList<EmergencyDetailsItem> arrayList2 = this.contactList;
        Intrinsics.checkNotNull(arrayList2);
        int size = arrayList2.size();
        boolean z = false;
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<EmergencyDetailsItem> arrayList3 = this.contactList;
            Intrinsics.checkNotNull(arrayList3);
            arrayList3.get(i2).setCountryCode("+91");
            ArrayList<EmergencyDetailsItem> arrayList4 = this.contactList;
            Intrinsics.checkNotNull(arrayList4);
            AppUtil.Companion companion2 = AppUtil.Companion;
            ArrayList<EmergencyDetailsItem> arrayList5 = this.contactList;
            Intrinsics.checkNotNull(arrayList5);
            arrayList4.get(i2).setContactNum(companion2.getValidPhoneString(arrayList5.get(i2).getContactNum()));
        }
        if (this.checkedContactList != null && (!arrayList.isEmpty())) {
            z = true;
        }
        if (z) {
            ArrayList<EmergencyDetailsItem> arrayList6 = this.checkedContactList;
            if (arrayList6 != null) {
                ArrayList<EmergencyDetailsItem> arrayList7 = this.contactList;
                Intrinsics.checkNotNull(arrayList7);
                arrayList6.addAll(arrayList7);
            }
        } else {
            ArrayList<EmergencyDetailsItem> arrayList8 = this.contactList;
            Intrinsics.checkNotNull(arrayList8);
            this.checkedContactList = arrayList8;
        }
        emergencyContactBody.setEmergencyDetails(this.checkedContactList);
        AppUtil.Companion companion3 = AppUtil.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        companion3.showDialog(requireContext2);
        DashboardService dashboardService = new DashboardService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        dashboardService.callAddEmergencyContactAPI(requireActivity, emergencyContactBody);
    }

    private final void displayConfirmationDlg() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.layoutSelectedContact)).setVisibility(0);
        ((ConstraintLayout) _$_findCachedViewById(R.id.layoutCnfrmtnSuccess)).setVisibility(8);
    }

    private final void displaySuccessDlg() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.layoutSelectedContact)).setVisibility(8);
        ((ConstraintLayout) _$_findCachedViewById(R.id.layoutCnfrmtnSuccess)).setVisibility(0);
    }

    private final void getVehicleList() {
        boolean z;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ArrayList<RegisteredVehicleItem> registeredVehicleList = companion.getRegisteredVehicleList(requireContext);
        Iterator<T> it = registeredVehicleList.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            RegisteredVehicleItem registeredVehicleItem = (RegisteredVehicleItem) it.next();
            registeredVehicleItem.setVehicleImage(Integer.valueOf((int) uat.psa.mym.mycitroenconnect.R.drawable.citroen_nav_bar_car));
            registeredVehicleItem.setVehicleSelected(false);
        }
        int size = registeredVehicleList.size();
        if (size != 0) {
            if (size != 1) {
                if (2 <= size && size <= Integer.MAX_VALUE) {
                    z = true;
                }
                if (!z) {
                    return;
                }
            }
            showCarAccessDialog(registeredVehicleList);
        }
    }

    @SuppressLint({"SetTextI18n"})
    private final void initView() {
        AppCompatTextView appCompatTextView;
        String format;
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvConfirmationText)).setText(this.message);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvSuccessConfirmation)).setText(this.successMessage);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), 1, false);
        Integer num = this.pageMode;
        if (num != null && num.intValue() == 1) {
            SharedPref.Companion companion = SharedPref.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            ArrayList<EmergencyDetailsItem> loadEmergencyContacts = companion.loadEmergencyContacts(requireActivity);
            this.contactList = loadEmergencyContacts;
            if (loadEmergencyContacts != null) {
                ArrayList<EmergencyDetailsItem> arrayList = new ArrayList();
                for (Object obj : loadEmergencyContacts) {
                    if (((EmergencyDetailsItem) obj).getViewType() != 4) {
                        arrayList.add(obj);
                    }
                }
                for (EmergencyDetailsItem emergencyDetailsItem : arrayList) {
                    emergencyDetailsItem.setViewType(4);
                }
            }
            ArrayList<EmergencyDetailsItem> arrayList2 = this.contactList;
            if (arrayList2 != null) {
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                this.contactAdapter = new ContactListAdapter(requireContext, arrayList2, this, 0, 8, null);
                Integer num2 = this.pageMode;
                if (num2 != null && num2.intValue() == 1) {
                    int size = arrayList2.size();
                    appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvSelectedContact);
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = this.title;
                    Intrinsics.checkNotNull(str);
                    format = String.format(str, Arrays.copyOf(new Object[]{Integer.valueOf(size)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    appCompatTextView.setText(format);
                }
            }
        } else if ((num != null && num.intValue() == 2) || (num != null && num.intValue() == 3)) {
            SharedPref.Companion companion2 = SharedPref.Companion;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            ArrayList<EmergencyDetailsItem> loadChildContacts = companion2.loadChildContacts(requireActivity2);
            this.contactList = loadChildContacts;
            if (loadChildContacts != null) {
                ArrayList<EmergencyDetailsItem> arrayList3 = new ArrayList();
                for (Object obj2 : loadChildContacts) {
                    if (((EmergencyDetailsItem) obj2).getViewType() != 4) {
                        arrayList3.add(obj2);
                    }
                }
                for (EmergencyDetailsItem emergencyDetailsItem2 : arrayList3) {
                    emergencyDetailsItem2.setViewType(4);
                }
            }
            ArrayList<EmergencyDetailsItem> arrayList4 = this.contactList;
            if (arrayList4 != null) {
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                this.contactAdapter = new ContactListAdapter(requireContext2, arrayList4, this, 0, 8, null);
                Integer num3 = this.pageMode;
                if (num3 != null && num3.intValue() == 3) {
                    int size2 = arrayList4.size();
                    appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvSelectedContact);
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String str2 = this.title;
                    Intrinsics.checkNotNull(str2);
                    format = String.format(str2, Arrays.copyOf(new Object[]{Integer.valueOf(size2)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    appCompatTextView.setText(format);
                }
            }
        }
        AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvSelectedContact);
        StringBuilder sb = new StringBuilder();
        sb.append(this.title);
        sb.append(" (");
        ArrayList<EmergencyDetailsItem> arrayList5 = this.contactList;
        sb.append(arrayList5 != null ? Integer.valueOf(arrayList5.size()) : null);
        sb.append(')');
        appCompatTextView2.setText(sb.toString());
        int i2 = R.id.rvConfirmContacts;
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(linearLayoutManager);
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(this.contactAdapter);
        displayConfirmationDlg();
    }

    @JvmStatic
    @NotNull
    public static final AddContactConfirmationFragment newInstance(@NotNull String str, @NotNull String str2, @NotNull String str3, int i2, @Nullable ArrayList<EmergencyDetailsItem> arrayList) {
        return Companion.newInstance(str, str2, str3, i2, arrayList);
    }

    private final void setListener() {
        ((AppCompatButton) _$_findCachedViewById(R.id.btnYes)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnNo)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivAddClose)).setOnClickListener(this);
    }

    private final void showCarAccessDialog(List<RegisteredVehicleItem> list) {
        ArrayList<EmergencyDetailsItem> arrayList = this.contactList;
        if (arrayList != null) {
            for (EmergencyDetailsItem emergencyDetailsItem : arrayList) {
                emergencyDetailsItem.setCountryCode("+91");
                emergencyDetailsItem.getCarAccess().clear();
                for (RegisteredVehicleItem registeredVehicleItem : list) {
                    emergencyDetailsItem.getCarAccess().add(registeredVehicleItem);
                }
            }
        }
        SharedPref.Companion companion = SharedPref.Companion;
        ArrayList<EmergencyDetailsItem> arrayList2 = this.contactList;
        Intrinsics.checkNotNull(arrayList2);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.saveChildContacts(arrayList2, requireContext);
        SelectCarForViewAccessDialog.Companion companion2 = SelectCarForViewAccessDialog.Companion;
        ArrayList<EmergencyDetailsItem> arrayList3 = this.contactList;
        Intrinsics.checkNotNull(arrayList3);
        SelectCarForViewAccessDialog newInstance = companion2.newInstance(3, arrayList3);
        newInstance.setOnContactAddedSuccessListener(this);
        newInstance.show(getChildFragmentManager(), SelectCarForViewAccessDialog.TAG);
        newInstance.setCancelable(false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment
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

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showToast(requireActivity, event.getMsg());
    }

    @Subscribe
    public final void getMessage(@NotNull EmergencyContactResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Logger.INSTANCE.e(event.getMessage());
        AppUtil.Companion.dismissDialog();
        displaySuccessDlg();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.ContactListInterface
    public void onCheckedChange(@NotNull ArrayList<EmergencyDetailsItem> contactList) {
        Intrinsics.checkNotNullParameter(contactList, "contactList");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        boolean z = false;
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnYes))) {
            Integer num = this.pageMode;
            if (num != null && num.intValue() == 1) {
                addEmergencyContact();
                return;
            }
            if ((num != null && num.intValue() == 2) || (num != null && num.intValue() == 3)) {
                z = true;
            }
            if (z) {
                getVehicleList();
            }
        } else if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnNo))) {
            if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivAddClose))) {
                dismiss();
                if (((ConstraintLayout) _$_findCachedViewById(R.id.layoutCnfrmtnSuccess)).getVisibility() == 0) {
                    OnContactAddedSuccessListener onContactAddedSuccessListener = this.onContactAddedSuccessListener;
                    if (onContactAddedSuccessListener == null) {
                        requireActivity().getSupportFragmentManager().popBackStack();
                    } else if (onContactAddedSuccessListener != null) {
                        onContactAddedSuccessListener.onContactAddedSuccess();
                    }
                }
            }
        } else {
            Integer num2 = this.pageMode;
            if (num2 != null && num2.intValue() == 1) {
                SharedPref.Companion companion = SharedPref.Companion;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                companion.removeEmergencyContacts(requireContext);
            } else {
                if ((num2 != null && num2.intValue() == 2) || (num2 != null && num2.intValue() == 3)) {
                    z = true;
                }
                if (z) {
                    SharedPref.Companion companion2 = SharedPref.Companion;
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    companion2.removeChildContacts(requireContext2);
                }
            }
            dismiss();
        }
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnContactAddedSuccessListener
    public void onContactAddedSuccess() {
        displaySuccessDlg();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.title = arguments.getString("title");
            this.message = arguments.getString(AppConstants.ARG_MESSAGE);
            this.successMessage = arguments.getString(AppConstants.ARG_SUCCESS_MESSAGE);
            this.pageMode = Integer.valueOf(arguments.getInt(AppConstants.ARG_PAGE_MODE));
            this.checkedContactList = arguments.getParcelableArrayList(AppConstants.ARG_CONTACT_LIST);
        }
        setStyle(0, uat.psa.mym.mycitroenconnect.R.style.DialogStyle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_add_contact_confirmation, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.ContactListInterface
    public void onItemClick(@NotNull EmergencyDetailsItem objContact, boolean z) {
        Intrinsics.checkNotNullParameter(objContact, "objContact");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setListener();
        initView();
    }

    public final void setOnContactAddedSuccessListener(@NotNull OnContactAddedSuccessListener onContactAddedSuccessListener) {
        Intrinsics.checkNotNullParameter(onContactAddedSuccessListener, "onContactAddedSuccessListener");
        this.onContactAddedSuccessListener = onContactAddedSuccessListener;
    }
}
