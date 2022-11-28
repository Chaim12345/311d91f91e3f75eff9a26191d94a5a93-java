package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.CarAccessBody;
import com.psa.mym.mycitroenconnect.api.body.secondary_user.SharedVehicleNew;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OnCarAccessDialogDismiss;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.onboarding.RegisteredVehicleItem;
import com.psa.mym.mycitroenconnect.model.secondary_user.SecondaryUserCommonResponse;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.Arrays;
import java.util.LinkedHashMap;
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
import org.slf4j.Marker;
/* loaded from: classes3.dex */
public final class CarAccessConfirmationDialog extends BusBaseBottomSheetFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "CarAccessConfirmationDialog";
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private RegisteredVehicleItem carAccessListItem;
    @Nullable
    private String contactNum;
    @Nullable
    private String message;
    @Nullable
    private OnCarAccessDialogDismiss onDialogDismiss;
    @Nullable
    private String pageMode;
    @Nullable
    private String userName;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ CarAccessConfirmationDialog newInstance$default(Companion companion, RegisteredVehicleItem registeredVehicleItem, String str, String str2, String str3, String str4, int i2, Object obj) {
            if ((i2 & 16) != 0) {
                str4 = "";
            }
            return companion.newInstance(registeredVehicleItem, str, str2, str3, str4);
        }

        @JvmStatic
        @NotNull
        public final CarAccessConfirmationDialog newInstance(@NotNull RegisteredVehicleItem param1, @NotNull String userName, @Nullable String str, @Nullable String str2, @NotNull String message) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(userName, "userName");
            Intrinsics.checkNotNullParameter(message, "message");
            CarAccessConfirmationDialog carAccessConfirmationDialog = new CarAccessConfirmationDialog();
            Bundle bundle = new Bundle();
            bundle.putParcelable("car_item", param1);
            bundle.putString("user_name", userName);
            bundle.putString("user_contact_num", str);
            bundle.putString("login", str2);
            bundle.putString(AppConstants.ARG_MESSAGE, message);
            carAccessConfirmationDialog.setArguments(bundle);
            return carAccessConfirmationDialog;
        }
    }

    private final void callGiveAccess() {
        String replace$default;
        CharSequence trim;
        String str = this.contactNum;
        Intrinsics.checkNotNull(str);
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        replace$default = StringsKt__StringsJVMKt.replace$default(companion.getMobileNumber(requireContext), Marker.ANY_NON_NULL_MARKER, "", false, 4, (Object) null);
        trim = StringsKt__StringsKt.trim((CharSequence) replace$default);
        String obj = trim.toString();
        String valueOf = String.valueOf(this.userName);
        RegisteredVehicleItem registeredVehicleItem = this.carAccessListItem;
        CarAccessBody carAccessBody = new CarAccessBody(str, "+91", obj, valueOf, new SharedVehicleNew(String.valueOf(registeredVehicleItem != null ? registeredVehicleItem.getVinNum() : null)));
        AppUtil.Companion companion2 = AppUtil.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        companion2.showDialog(requireContext2);
        SecondaryUserService secondaryUserService = new SecondaryUserService();
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        secondaryUserService.callGiveAccessSecondaryUser(requireContext3, carAccessBody);
    }

    private final void callRevokeAccess() {
        String replace$default;
        CharSequence trim;
        String str = this.contactNum;
        Intrinsics.checkNotNull(str);
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        replace$default = StringsKt__StringsJVMKt.replace$default(companion.getMobileNumber(requireContext), Marker.ANY_NON_NULL_MARKER, "", false, 4, (Object) null);
        trim = StringsKt__StringsKt.trim((CharSequence) replace$default);
        String obj = trim.toString();
        String valueOf = String.valueOf(this.userName);
        RegisteredVehicleItem registeredVehicleItem = this.carAccessListItem;
        CarAccessBody carAccessBody = new CarAccessBody(str, "+91", obj, valueOf, new SharedVehicleNew(String.valueOf(registeredVehicleItem != null ? registeredVehicleItem.getVinNum() : null)));
        AppUtil.Companion companion2 = AppUtil.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        companion2.showDialog(requireContext2);
        SecondaryUserService secondaryUserService = new SecondaryUserService();
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        secondaryUserService.callRevokeSecondaryUser(requireContext3, carAccessBody);
    }

    private final void displaySuccessDlg(String str) {
        AppCompatTextView appCompatTextView;
        int i2;
        ((ConstraintLayout) _$_findCachedViewById(R.id.layoutSelectedCar)).setVisibility(8);
        ((ConstraintLayout) _$_findCachedViewById(R.id.layoutCarCnfrmtnSuccess)).setVisibility(0);
        if (Intrinsics.areEqual(str, AppConstants.API_GIVE_ACCESS_SECONDARY_USER)) {
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvCarSuccessConfirmation);
            i2 = uat.psa.mym.mycitroenconnect.R.string.lbl_car_access_given_success;
        } else if (!Intrinsics.areEqual(str, AppConstants.API_REVOKE_ACCESS_SECONDARY_USER)) {
            return;
        } else {
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvCarSuccessConfirmation);
            i2 = uat.psa.mym.mycitroenconnect.R.string.lbl_car_access_revoke_success;
        }
        appCompatTextView.setText(getString(i2));
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initView() {
        AppCompatTextView appCompatTextView;
        String format;
        boolean z;
        boolean isBlank;
        ((AppCompatButton) _$_findCachedViewById(R.id.btnGrantAccess)).setVisibility(8);
        AppCompatTextView appCompatTextView2 = (AppCompatTextView) _$_findCachedViewById(R.id.tvCangCarName);
        RegisteredVehicleItem registeredVehicleItem = this.carAccessListItem;
        appCompatTextView2.setText(registeredVehicleItem != null ? registeredVehicleItem.getCarModelName() : null);
        AppCompatTextView appCompatTextView3 = (AppCompatTextView) _$_findCachedViewById(R.id.tvCangCarNumber);
        RegisteredVehicleItem registeredVehicleItem2 = this.carAccessListItem;
        appCompatTextView3.setText(registeredVehicleItem2 != null ? registeredVehicleItem2.getVehicleNumber() : null);
        String str = this.message;
        if (!(str == null || str.length() == 0)) {
            String str2 = this.message;
            if (str2 != null) {
                isBlank = StringsKt__StringsJVMKt.isBlank(str2);
                if (!isBlank) {
                    z = false;
                    if (!z) {
                        appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvConfirmationText);
                        format = this.message;
                        appCompatTextView.setText(format);
                    }
                }
            }
            z = true;
            if (!z) {
            }
        }
        appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvConfirmationText);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(uat.psa.mym.mycitroenconnect.R.string.lbl_confirm_car_access_desc);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.lbl_confirm_car_access_desc)");
        format = String.format(string, Arrays.copyOf(new Object[]{this.userName}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        appCompatTextView.setText(format);
    }

    @JvmStatic
    @NotNull
    public static final CarAccessConfirmationDialog newInstance(@NotNull RegisteredVehicleItem registeredVehicleItem, @NotNull String str, @Nullable String str2, @Nullable String str3, @NotNull String str4) {
        return Companion.newInstance(registeredVehicleItem, str, str2, str3, str4);
    }

    private final void setListener() {
        ((AppCompatButton) _$_findCachedViewById(R.id.btnYes)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnNo)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(this);
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
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, event.getMsg());
    }

    @Subscribe
    public final void getMessage(@NotNull SecondaryUserCommonResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        String apiName = event.getApiName();
        if (Intrinsics.areEqual(apiName, AppConstants.API_GIVE_ACCESS_SECONDARY_USER) ? true : Intrinsics.areEqual(apiName, AppConstants.API_REVOKE_ACCESS_SECONDARY_USER)) {
            if (Intrinsics.areEqual(event.getSuccess(), "true")) {
                String apiName2 = event.getApiName();
                Intrinsics.checkNotNull(apiName2);
                displaySuccessDlg(apiName2);
                return;
            }
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            ExtensionsKt.showToast(requireContext, event.getMessage());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnYes))) {
            if (Intrinsics.areEqual(this.pageMode, AppConstants.PAGE_MODE_GIVE_ACCESS)) {
                callGiveAccess();
                return;
            } else {
                callRevokeAccess();
                return;
            }
        }
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnNo))) {
            OnCarAccessDialogDismiss onCarAccessDialogDismiss = this.onDialogDismiss;
            if (onCarAccessDialogDismiss != null) {
                OnCarAccessDialogDismiss.DefaultImpls.onCarAccessDialogDismiss$default(onCarAccessDialogDismiss, false, null, null, 6, null);
            }
        } else if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivClose))) {
            return;
        } else {
            if (((ConstraintLayout) _$_findCachedViewById(R.id.layoutCarCnfrmtnSuccess)).getVisibility() == 0) {
                OnCarAccessDialogDismiss onCarAccessDialogDismiss2 = this.onDialogDismiss;
                if (onCarAccessDialogDismiss2 != null) {
                    onCarAccessDialogDismiss2.onCarAccessDialogDismiss(true, this.carAccessListItem, this.pageMode);
                }
            } else {
                OnCarAccessDialogDismiss onCarAccessDialogDismiss3 = this.onDialogDismiss;
                if (onCarAccessDialogDismiss3 != null) {
                    OnCarAccessDialogDismiss.DefaultImpls.onCarAccessDialogDismiss$default(onCarAccessDialogDismiss3, false, null, null, 6, null);
                }
            }
        }
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.carAccessListItem = (RegisteredVehicleItem) arguments.getParcelable("car_item");
            this.userName = arguments.getString("user_name");
            this.contactNum = arguments.getString("user_contact_num");
            this.pageMode = arguments.getString("login");
            this.message = arguments.getString(AppConstants.ARG_MESSAGE);
        }
        setStyle(0, uat.psa.mym.mycitroenconnect.R.style.DialogStyle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_car_access_confirmation_dialog, viewGroup, false);
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
        setListener();
    }

    public final void setOnDialogDismiss(@NotNull OnCarAccessDialogDismiss onCarAccessDialogDismiss) {
        Intrinsics.checkNotNullParameter(onCarAccessDialogDismiss, "onCarAccessDialogDismiss");
        this.onDialogDismiss = onCarAccessDialogDismiss;
    }
}
