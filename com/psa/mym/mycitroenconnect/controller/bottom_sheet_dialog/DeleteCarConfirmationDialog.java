package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.chaos.view.PinView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.psa.mym.mycitroenconnect.BusBaseBottomSheetFragment;
import com.psa.mym.mycitroenconnect.model.DeleteCarResponse;
import com.psa.mym.mycitroenconnect.model.ValidationErrorResponse;
import com.psa.mym.mycitroenconnect.model.secondary_user.MyCar;
import com.psa.mym.mycitroenconnect.services.SecondaryUserService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Marker;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class DeleteCarConfirmationDialog extends BusBaseBottomSheetFragment implements View.OnClickListener {
    @NotNull
    private static final String CAR_DETAILS = "car_details";
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "DeleteCarConfirmationDialog";
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private MyCar carDetails;
    @Nullable
    private OnCarDelete onCarDelete;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final DeleteCarConfirmationDialog newInstance(@NotNull MyCar carDetails) {
            Intrinsics.checkNotNullParameter(carDetails, "carDetails");
            DeleteCarConfirmationDialog deleteCarConfirmationDialog = new DeleteCarConfirmationDialog();
            Bundle bundle = new Bundle();
            bundle.putParcelable("car_details", carDetails);
            deleteCarConfirmationDialog.setArguments(bundle);
            return deleteCarConfirmationDialog;
        }
    }

    private final void deleteCar() {
        String replace$default;
        MyCar myCar = this.carDetails;
        if (myCar != null) {
            AppUtil.Companion companion = AppUtil.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.showDialog(requireContext);
            SecondaryUserService secondaryUserService = new SecondaryUserService();
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            SharedPref.Companion companion2 = SharedPref.Companion;
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            replace$default = StringsKt__StringsJVMKt.replace$default(companion2.getMobileNumber(requireContext3), Marker.ANY_NON_NULL_MARKER, "", false, 4, (Object) null);
            String vinNum = myCar.getVinNum();
            Intrinsics.checkNotNull(vinNum);
            secondaryUserService.deleteMyCar(requireContext2, replace$default, vinNum);
        }
    }

    private final void init() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.car_delete_confirmation_description);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.car_d…confirmation_description)");
        Object[] objArr = new Object[1];
        MyCar myCar = this.carDetails;
        objArr[0] = myCar != null ? myCar.getCarModelName() : null;
        String format = String.format(string, Arrays.copyOf(objArr, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvDeleteConfDesc)).setText(AppUtil.Companion.setSpannedString(format));
        AppCompatTextView appCompatTextView = (AppCompatTextView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvOtpVerified);
        String string2 = getString(R.string.msg_car_delete_success);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.msg_car_delete_success)");
        Object[] objArr2 = new Object[1];
        MyCar myCar2 = this.carDetails;
        objArr2[0] = myCar2 != null ? myCar2.getCarModelName() : null;
        String format2 = String.format(string2, Arrays.copyOf(objArr2, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        appCompatTextView.setText(format2);
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivClose)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCancel)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnDelete)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnConfirmDelete)).setOnClickListener(this);
        ((PinView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edVerification)).addTextChangedListener(new TextWatcher() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.DeleteCarConfirmationDialog$init$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                AppCompatTextView tvWrongPin = (AppCompatTextView) DeleteCarConfirmationDialog.this._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.tvWrongPin);
                Intrinsics.checkNotNullExpressionValue(tvWrongPin, "tvWrongPin");
                ExtensionsKt.hide(tvWrongPin);
                DeleteCarConfirmationDialog deleteCarConfirmationDialog = DeleteCarConfirmationDialog.this;
                int i5 = com.psa.mym.mycitroenconnect.R.id.edVerification;
                ((PinView) deleteCarConfirmationDialog._$_findCachedViewById(i5)).setLineColor(ContextCompat.getColor(DeleteCarConfirmationDialog.this.requireContext(), R.color.dark_grey));
                ((PinView) DeleteCarConfirmationDialog.this._$_findCachedViewById(i5)).setTextColor(ContextCompat.getColor(DeleteCarConfirmationDialog.this.requireContext(), R.color.black));
            }
        });
        showDeleteConfirmationDialog();
    }

    @JvmStatic
    @NotNull
    public static final DeleteCarConfirmationDialog newInstance(@NotNull MyCar myCar) {
        return Companion.newInstance(myCar);
    }

    private final void showAppSecurityPinDialog() {
        ((PinView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.edVerification)).requestFocus();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.showKeyboard(requireActivity);
        AppCompatImageView ivClose = (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivClose);
        Intrinsics.checkNotNullExpressionValue(ivClose, "ivClose");
        ExtensionsKt.show(ivClose);
        ConstraintLayout clAppSecurityPin = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.clAppSecurityPin);
        Intrinsics.checkNotNullExpressionValue(clAppSecurityPin, "clAppSecurityPin");
        ExtensionsKt.show(clAppSecurityPin);
        ConstraintLayout clDeleteCar = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.clDeleteCar);
        Intrinsics.checkNotNullExpressionValue(clDeleteCar, "clDeleteCar");
        ExtensionsKt.hide(clDeleteCar);
        ConstraintLayout deleteSuccess = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.deleteSuccess);
        Intrinsics.checkNotNullExpressionValue(deleteSuccess, "deleteSuccess");
        ExtensionsKt.hide(deleteSuccess);
    }

    private final void showCarDeleteSuccessDialog() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.hideKeyboard(requireActivity);
        AppCompatImageView ivClose = (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivClose);
        Intrinsics.checkNotNullExpressionValue(ivClose, "ivClose");
        ExtensionsKt.show(ivClose);
        ConstraintLayout deleteSuccess = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.deleteSuccess);
        Intrinsics.checkNotNullExpressionValue(deleteSuccess, "deleteSuccess");
        ExtensionsKt.show(deleteSuccess);
        ConstraintLayout clDeleteCar = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.clDeleteCar);
        Intrinsics.checkNotNullExpressionValue(clDeleteCar, "clDeleteCar");
        ExtensionsKt.hide(clDeleteCar);
        ConstraintLayout clAppSecurityPin = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.clAppSecurityPin);
        Intrinsics.checkNotNullExpressionValue(clAppSecurityPin, "clAppSecurityPin");
        ExtensionsKt.hide(clAppSecurityPin);
    }

    private final void showDeleteConfirmationDialog() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionsKt.hideKeyboard(requireActivity);
        ConstraintLayout clDeleteCar = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.clDeleteCar);
        Intrinsics.checkNotNullExpressionValue(clDeleteCar, "clDeleteCar");
        ExtensionsKt.show(clDeleteCar);
        AppCompatImageView ivClose = (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivClose);
        Intrinsics.checkNotNullExpressionValue(ivClose, "ivClose");
        ExtensionsKt.hide(ivClose);
        ConstraintLayout clAppSecurityPin = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.clAppSecurityPin);
        Intrinsics.checkNotNullExpressionValue(clAppSecurityPin, "clAppSecurityPin");
        ExtensionsKt.hide(clAppSecurityPin);
        ConstraintLayout deleteSuccess = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.deleteSuccess);
        Intrinsics.checkNotNullExpressionValue(deleteSuccess, "deleteSuccess");
        ExtensionsKt.hide(deleteSuccess);
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
    public final void getErrorResponse(@NotNull ValidationErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, response.getErrorList().get(0));
    }

    @Subscribe
    public final void getResponse(@NotNull DeleteCarResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        String lowerCase = response.getSuccess().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        if (Intrinsics.areEqual(lowerCase, "true")) {
            showCarDeleteSuccessDialog();
            return;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ExtensionsKt.showToast(requireContext, response.getMessage());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        int i2;
        AppCompatTextView appCompatTextView;
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivClose))) {
            ConstraintLayout clAppSecurityPin = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.clAppSecurityPin);
            Intrinsics.checkNotNullExpressionValue(clAppSecurityPin, "clAppSecurityPin");
            if (ExtensionsKt.isVisible(clAppSecurityPin)) {
                showDeleteConfirmationDialog();
                return;
            }
            ConstraintLayout deleteSuccess = (ConstraintLayout) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.deleteSuccess);
            Intrinsics.checkNotNullExpressionValue(deleteSuccess, "deleteSuccess");
            if (!ExtensionsKt.isVisible(deleteSuccess)) {
                return;
            }
            OnCarDelete onCarDelete = this.onCarDelete;
            if (onCarDelete != null) {
                onCarDelete.carDeleteSuccess();
            }
        } else if (!Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCancel))) {
            if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnDelete))) {
                showAppSecurityPinDialog();
                return;
            } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnConfirmDelete))) {
                SharedPref.Companion companion = SharedPref.Companion;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String appPin = companion.getAppPin(requireContext);
                int i3 = com.psa.mym.mycitroenconnect.R.id.edVerification;
                if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() == 0) {
                    int i4 = com.psa.mym.mycitroenconnect.R.id.tvWrongPin;
                    AppCompatTextView tvWrongPin = (AppCompatTextView) _$_findCachedViewById(i4);
                    Intrinsics.checkNotNullExpressionValue(tvWrongPin, "tvWrongPin");
                    ExtensionsKt.show(tvWrongPin);
                    appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i4);
                    i2 = R.string.err_enter_pin;
                } else if (String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()).length() == 4) {
                    if (Intrinsics.areEqual(appPin, String.valueOf(((PinView) _$_findCachedViewById(i3)).getText()))) {
                        deleteCar();
                        return;
                    }
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    String string = getString(R.string.err_incorrect_pin);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.err_incorrect_pin)");
                    ExtensionsKt.showToast(requireContext2, string);
                    return;
                } else {
                    Context requireContext3 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    i2 = R.string.err_pin_length_4;
                    String string2 = getString(R.string.err_pin_length_4);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.err_pin_length_4)");
                    ExtensionsKt.showToast(requireContext3, string2);
                    int i5 = com.psa.mym.mycitroenconnect.R.id.tvWrongPin;
                    AppCompatTextView tvWrongPin2 = (AppCompatTextView) _$_findCachedViewById(i5);
                    Intrinsics.checkNotNullExpressionValue(tvWrongPin2, "tvWrongPin");
                    ExtensionsKt.show(tvWrongPin2);
                    appCompatTextView = (AppCompatTextView) _$_findCachedViewById(i5);
                }
                appCompatTextView.setText(getString(i2));
                ((PinView) _$_findCachedViewById(i3)).setLineColor(ContextCompat.getColor(requireContext(), R.color.dark_red));
                ((PinView) _$_findCachedViewById(i3)).setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_red));
                return;
            } else {
                return;
            }
        }
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.carDetails = (MyCar) arguments.getParcelable("car_details");
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
        return inflater.inflate(R.layout.bottom_sheet_delete_car_confirmation, viewGroup, false);
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
    }

    public final void setOnCarDelete(@NotNull OnCarDelete onCarDelete) {
        Intrinsics.checkNotNullParameter(onCarDelete, "onCarDelete");
        this.onCarDelete = onCarDelete;
    }
}
