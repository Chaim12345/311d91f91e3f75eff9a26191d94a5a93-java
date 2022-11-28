package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment;
import com.psa.mym.mycitroenconnect.api.body.dashboard.EmergencyContactBody;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyContactResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import com.psa.mym.mycitroenconnect.model.geo_fence.GetGeoFenceResponseItem;
import com.psa.mym.mycitroenconnect.model.geo_fence.PostGeoFenceResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUserCommonResponse;
import com.psa.mym.mycitroenconnect.services.DashboardService;
import com.psa.mym.mycitroenconnect.services.GeoFenceService;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class DeleteConfirmationFragment extends BusBaseBottomSheetFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "DeleteConfirmationFragment";
    @Nullable
    private String contactId;
    @Nullable
    private ArrayList<EmergencyDetailsItem> contactList;
    @Nullable
    private GetGeoFenceResponseItem geoFence;
    @Nullable
    private String name;
    @Nullable
    private OnDeleteDialogDismiss onDeleteDialogDismiss;
    @Nullable
    private Integer pageMode;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int position = -1;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ DeleteConfirmationFragment newInstance$default(Companion companion, String str, String str2, int i2, ArrayList arrayList, GetGeoFenceResponseItem getGeoFenceResponseItem, int i3, int i4, Object obj) {
            return companion.newInstance(str, str2, i2, (i4 & 8) != 0 ? null : arrayList, (i4 & 16) != 0 ? null : getGeoFenceResponseItem, (i4 & 32) != 0 ? -1 : i3);
        }

        @JvmStatic
        @NotNull
        public final DeleteConfirmationFragment newInstance(@NotNull String name, @NotNull String contactId, int i2, @Nullable ArrayList<EmergencyDetailsItem> arrayList, @Nullable GetGeoFenceResponseItem getGeoFenceResponseItem, int i3) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(contactId, "contactId");
            DeleteConfirmationFragment deleteConfirmationFragment = new DeleteConfirmationFragment();
            Bundle bundle = new Bundle();
            bundle.putString(AppConstants.ARG_CONTACT_NAME, name);
            bundle.putString(AppConstants.ARG_CONTACT_ID, contactId);
            bundle.putInt("login", i2);
            bundle.putParcelableArrayList(AppConstants.EMERGENCY_CONTACT_LIST, arrayList);
            bundle.putParcelable(AppConstants.GEO_FENCE, getGeoFenceResponseItem);
            bundle.putInt(AppConstants.ARG_POSITION, i3);
            deleteConfirmationFragment.setArguments(bundle);
            return deleteConfirmationFragment;
        }
    }

    private final void deleteChildContact() {
        ArrayList arrayList = new ArrayList();
        ArrayList<EmergencyDetailsItem> arrayList2 = this.contactList;
        if (arrayList2 != null) {
            for (EmergencyDetailsItem emergencyDetailsItem : arrayList2) {
                arrayList.add(emergencyDetailsItem);
            }
        }
        ArrayList<EmergencyDetailsItem> arrayList3 = new ArrayList();
        for (Object obj : arrayList) {
            if (Intrinsics.areEqual(((EmergencyDetailsItem) obj).getContactNum(), this.contactId)) {
                arrayList3.add(obj);
            }
        }
        for (EmergencyDetailsItem emergencyDetailsItem2 : arrayList3) {
            arrayList.remove(emergencyDetailsItem2);
        }
        String str = this.contactId;
        boolean z = false;
        if (str != null && str.length() == 10) {
            z = true;
        }
        if (z) {
            this.contactId = "91" + this.contactId;
        }
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.saveChildContacts(arrayList, requireContext);
        SecondaryUserService secondaryUserService = new SecondaryUserService();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String str2 = this.contactId;
        Intrinsics.checkNotNull(str2);
        secondaryUserService.callDeleteSecondaryUser(requireContext2, str2);
    }

    private final void deleteEmergencyContact() {
        String replace$default;
        CharSequence trim;
        EmergencyContactBody emergencyContactBody = new EmergencyContactBody(null, null, null, 7, null);
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        replace$default = StringsKt__StringsJVMKt.replace$default(companion.getMobileNumber(requireContext), "+91", "", false, 4, (Object) null);
        trim = StringsKt__StringsKt.trim((CharSequence) replace$default);
        emergencyContactBody.setUserName(trim.toString());
        emergencyContactBody.setCountryCode("+91");
        ArrayList<EmergencyDetailsItem> arrayList = new ArrayList<>();
        ArrayList<EmergencyDetailsItem> arrayList2 = this.contactList;
        if (arrayList2 != null) {
            for (EmergencyDetailsItem emergencyDetailsItem : arrayList2) {
                arrayList.add(emergencyDetailsItem);
            }
        }
        ArrayList<EmergencyDetailsItem> arrayList3 = new ArrayList();
        for (Object obj : arrayList) {
            if (Intrinsics.areEqual(((EmergencyDetailsItem) obj).getContactNum(), this.contactId)) {
                arrayList3.add(obj);
            }
        }
        for (EmergencyDetailsItem emergencyDetailsItem2 : arrayList3) {
            arrayList.remove(emergencyDetailsItem2);
        }
        emergencyContactBody.setEmergencyDetails(arrayList);
        AppUtil.Companion companion2 = AppUtil.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        companion2.showDialog(requireContext2);
        DashboardService dashboardService = new DashboardService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        dashboardService.callUpdateEmergencyContactAPI(requireActivity, emergencyContactBody);
    }

    private final void deleteGeoFence() {
        GetGeoFenceResponseItem getGeoFenceResponseItem = this.geoFence;
        if (getGeoFenceResponseItem != null) {
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
            String fenceId = getGeoFenceResponseItem.getFenceId();
            Intrinsics.checkNotNull(fenceId);
            geoFenceService.deleteFence(requireContext2, vinNumber, fenceId);
        }
    }

    private final void init() {
        String str;
        String str2;
        Integer num = this.pageMode;
        if (num != null && num.intValue() == 1) {
            str = getString(R.string.delete_emergency_contact, this.name);
            str2 = "getString(R.string.delete_emergency_contact, name)";
        } else if (num != null && num.intValue() == 2) {
            str = getString(R.string.delete_child_contact, this.name);
            str2 = "getString(R.string.delete_child_contact, name)";
        } else if (num == null || num.intValue() != 4) {
            str = "";
            ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvDeleteConfDesc)).setText(AppUtil.Companion.setSpannedString(str));
        } else {
            str = getString(R.string.delete_geo_fence, this.name);
            str2 = "getString(R.string.delete_geo_fence, name)";
        }
        Intrinsics.checkNotNullExpressionValue(str, str2);
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvDeleteConfDesc)).setText(AppUtil.Companion.setSpannedString(str));
    }

    @JvmStatic
    @NotNull
    public static final DeleteConfirmationFragment newInstance(@NotNull String str, @NotNull String str2, int i2, @Nullable ArrayList<EmergencyDetailsItem> arrayList, @Nullable GetGeoFenceResponseItem getGeoFenceResponseItem, int i3) {
        return Companion.newInstance(str, str2, i2, arrayList, getGeoFenceResponseItem, i3);
    }

    private final void setListener() {
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnDltDelete)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnDltCancel)).setOnClickListener(this);
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
        Logger.INSTANCE.e(String.valueOf(event));
        AppUtil.Companion.dismissDialog();
        dismiss();
        requireActivity().finish();
    }

    @Subscribe
    public final void getMessage(@NotNull SecondaryUserCommonResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getApiName(), AppConstants.API_NAME_DELETE_SECONDARY_USER)) {
            dismiss();
            OnDeleteDialogDismiss onDeleteDialogDismiss = this.onDeleteDialogDismiss;
            if (onDeleteDialogDismiss != null) {
                onDeleteDialogDismiss.onDeleteSuccess(this.position);
            }
        }
    }

    @Subscribe
    public final void getResponse(@NotNull PostGeoFenceResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (Intrinsics.areEqual(response.getApiName(), GeoFenceService.deleteGeoFence)) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String message = response.getMessage();
            Intrinsics.checkNotNull(message);
            ExtensionsKt.showToast(requireContext, message);
            OnDeleteDialogDismiss onDeleteDialogDismiss = this.onDeleteDialogDismiss;
            if (onDeleteDialogDismiss != null) {
                onDeleteDialogDismiss.onDeleteSuccess(this.position);
            }
            dismiss();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnDltDelete))) {
            if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnDltCancel))) {
                dismiss();
                OnDeleteDialogDismiss onDeleteDialogDismiss = this.onDeleteDialogDismiss;
                if (onDeleteDialogDismiss != null) {
                    onDeleteDialogDismiss.onCancel();
                    return;
                }
                return;
            }
            return;
        }
        Integer num = this.pageMode;
        if (num != null && num.intValue() == 1) {
            deleteEmergencyContact();
        } else if (num != null && num.intValue() == 2) {
            deleteChildContact();
        } else if (num != null && num.intValue() == 4) {
            deleteGeoFence();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.name = arguments.getString(AppConstants.ARG_CONTACT_NAME);
            this.contactId = arguments.getString(AppConstants.ARG_CONTACT_ID);
            this.pageMode = Integer.valueOf(arguments.getInt("login"));
            this.contactList = arguments.getParcelableArrayList(AppConstants.EMERGENCY_CONTACT_LIST);
            this.geoFence = (GetGeoFenceResponseItem) arguments.getParcelable(AppConstants.GEO_FENCE);
            this.position = arguments.getInt(AppConstants.ARG_POSITION);
        }
        setStyle(0, R.style.DialogStyle);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), getTheme());
        bottomSheetDialog.getBehavior().setState(3);
        bottomSheetDialog.getBehavior().setSkipCollapsed(true);
        return bottomSheetDialog;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_delete_confirmation, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        init();
        setListener();
    }

    public final void setOnDeleteDialogDismiss(@NotNull OnDeleteDialogDismiss onDeleteDialogDismiss) {
        Intrinsics.checkNotNullParameter(onDeleteDialogDismiss, "onDeleteDialogDismiss");
        this.onDeleteDialogDismiss = onDeleteDialogDismiss;
    }
}
