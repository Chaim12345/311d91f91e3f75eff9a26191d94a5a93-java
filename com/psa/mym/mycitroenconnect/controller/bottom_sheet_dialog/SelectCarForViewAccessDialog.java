package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.SecondaryUserBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.SharedVehicleNew;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.SelectCarAccessAdapter;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.secondary_user.AddSecondaryUserError;
import com.psa.mym.mycitroenconnect.model.secondary_user.AddSecondaryUserResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.Message;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SelectCarForViewAccessDialog extends BusBaseBottomSheetFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "SelectCarForViewAccessDialog";
    @Nullable
    private SelectCarAccessAdapter carAccessAdapter;
    @Nullable
    private OnContactAddedSuccessListener onContactAddedSuccessListener;
    @Nullable
    private Integer pageMode;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private ArrayList<EmergencyDetailsItem> contactList = new ArrayList<>();

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SelectCarForViewAccessDialog newInstance(int i2, @Nullable ArrayList<EmergencyDetailsItem> arrayList) {
            SelectCarForViewAccessDialog selectCarForViewAccessDialog = new SelectCarForViewAccessDialog();
            Bundle bundle = new Bundle();
            bundle.putInt(AppConstants.ARG_PAGE_MODE, i2);
            bundle.putParcelableArrayList(AppConstants.ARG_CONTACT_LIST, arrayList);
            selectCarForViewAccessDialog.setArguments(bundle);
            return selectCarForViewAccessDialog;
        }
    }

    private final void addChildAccount() {
        SharedVehicleNew sharedVehicleNew;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String mobileNumber = companion.getMobileNumber(requireContext);
        ArrayList<EmergencyDetailsItem> arrayList = this.contactList;
        if (arrayList != null) {
            SharedVehicleNew sharedVehicleNew2 = null;
            for (EmergencyDetailsItem emergencyDetailsItem : arrayList) {
                for (RegisteredVehicleItem registeredVehicleItem : emergencyDetailsItem.getCarAccess()) {
                    if (registeredVehicleItem.isAccessible()) {
                        String vinNum = registeredVehicleItem.getVinNum();
                        Intrinsics.checkNotNull(vinNum);
                        sharedVehicleNew2 = new SharedVehicleNew(vinNum);
                    }
                }
            }
            sharedVehicleNew = sharedVehicleNew2;
        } else {
            sharedVehicleNew = null;
        }
        AppUtil.Companion companion2 = AppUtil.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        companion2.showDialog(requireContext2);
        ArrayList<EmergencyDetailsItem> arrayList2 = this.contactList;
        EmergencyDetailsItem emergencyDetailsItem2 = arrayList2 != null ? arrayList2.get(0) : null;
        String countryCode = emergencyDetailsItem2 != null ? emergencyDetailsItem2.getCountryCode() : null;
        Intrinsics.checkNotNull(countryCode);
        String contactNum = emergencyDetailsItem2.getContactNum();
        String substring = mobileNumber.substring(1, mobileNumber.length());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        String name = emergencyDetailsItem2.getName();
        Intrinsics.checkNotNull(sharedVehicleNew);
        SecondaryUserBody secondaryUserBody = new SecondaryUserBody(countryCode, contactNum, substring, name, sharedVehicleNew);
        Logger.INSTANCE.e("Secondary User Request" + secondaryUserBody);
        SecondaryUserService secondaryUserService = new SecondaryUserService();
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        secondaryUserService.addChildAccount(requireContext3, secondaryUserBody);
    }

    private final void initView() {
        ArrayList<EmergencyDetailsItem> arrayList = this.contactList;
        if (arrayList != null && arrayList.isEmpty()) {
            SharedPref.Companion companion = SharedPref.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ArrayList<EmergencyDetailsItem> loadChildContacts = companion.loadChildContacts(requireContext);
            this.contactList = loadChildContacts;
            if (loadChildContacts != null) {
                ArrayList<EmergencyDetailsItem> arrayList2 = new ArrayList();
                for (Object obj : loadChildContacts) {
                    if (((EmergencyDetailsItem) obj).getViewType() != 4) {
                        arrayList2.add(obj);
                    }
                }
                for (EmergencyDetailsItem emergencyDetailsItem : arrayList2) {
                    emergencyDetailsItem.setViewType(4);
                }
            }
        }
        ArrayList<EmergencyDetailsItem> arrayList3 = this.contactList;
        if (arrayList3 != null) {
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvName)).setText(arrayList3.get(0).getName());
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.full_mobile_number);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.full_mobile_number)");
            String format = String.format(string, Arrays.copyOf(new Object[]{arrayList3.get(0).getCountryCode(), arrayList3.get(0).getContactNum()}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvNumber)).setText(format);
            this.carAccessAdapter = new SelectCarAccessAdapter(arrayList3.get(0).getCarAccess());
        }
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvCarAccess);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(this.carAccessAdapter);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnConfirm)).setOnClickListener(this);
    }

    @JvmStatic
    @NotNull
    public static final SelectCarForViewAccessDialog newInstance(int i2, @Nullable ArrayList<EmergencyDetailsItem> arrayList) {
        return Companion.newInstance(i2, arrayList);
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
    public final void getErrorResponse(@NotNull ErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AppUtil.Companion.dismissDialog();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, response.getMsg());
    }

    @Subscribe
    public final void getMessage(@NotNull ErrorResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AppUtil.Companion.dismissDialog();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showToast(requireActivity, event.getMsg());
    }

    @Subscribe
    public final void getResponse(@NotNull AddSecondaryUserError response) {
        Intrinsics.checkNotNullParameter(response, "response");
        AppUtil.Companion.dismissDialog();
        Message message = response.getMessage();
        if (!(!message.getErrors().isEmpty()) || message.getErrors().size() <= 0) {
            return;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, message.getErrors().get(0).getDescription());
    }

    @Subscribe
    public final void getResponse(@NotNull AddSecondaryUserResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        Logger.INSTANCE.e(String.valueOf(response));
        dismiss();
        SharedPref.Companion companion = SharedPref.Companion;
        ArrayList<EmergencyDetailsItem> arrayList = this.contactList;
        Intrinsics.checkNotNull(arrayList);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        companion.saveChildContacts(arrayList, requireContext);
        OnContactAddedSuccessListener onContactAddedSuccessListener = this.onContactAddedSuccessListener;
        if (onContactAddedSuccessListener == null) {
            requireActivity().getSupportFragmentManager().popBackStack();
        } else if (onContactAddedSuccessListener != null) {
            onContactAddedSuccessListener.onContactAddedSuccess();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivClose))) {
            dismiss();
            SharedPref.Companion companion = SharedPref.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.removeChildContacts(requireContext);
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnConfirm))) {
            SelectCarAccessAdapter selectCarAccessAdapter = this.carAccessAdapter;
            Boolean valueOf = selectCarAccessAdapter != null ? Boolean.valueOf(selectCarAccessAdapter.isCarAccessGiven()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue()) {
                addChildAccount();
                return;
            }
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.msg_choose_min_01_car);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.msg_choose_min_01_car)");
            ExtensionsKt.showToast(requireContext2, string);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.pageMode = Integer.valueOf(arguments.getInt(AppConstants.ARG_PAGE_MODE));
            this.contactList = arguments.getParcelableArrayList(AppConstants.ARG_CONTACT_LIST);
        }
        setStyle(0, uat.psa.mym.mycitroenconnect.R.style.DialogStyle);
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.bottom_sheet_select_car_for_view_access, viewGroup, false);
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
        initView();
    }

    public final void setOnContactAddedSuccessListener(@NotNull OnContactAddedSuccessListener onContactAddedSuccessListener) {
        Intrinsics.checkNotNullParameter(onContactAddedSuccessListener, "onContactAddedSuccessListener");
        this.onContactAddedSuccessListener = onContactAddedSuccessListener;
    }
}
