package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ContactSupportDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "ContactSupportDialog";
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callSupport() {
        CharSequence trim;
        try {
            try {
                String string = getString(R.string.label_customer_care_number);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.label_customer_care_number)");
                trim = StringsKt__StringsKt.trim((CharSequence) string);
                String obj = trim.toString();
                requireActivity().startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + obj)));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            dismiss();
        }
    }

    private final void checkForPermissionAndMakeCall() {
        Dexter.withActivity(requireActivity()).withPermissions("android.permission.CALL_PHONE").withListener(new MultiplePermissionsListener() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ContactSupportDialog$checkForPermissionAndMakeCall$1
            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionRationaleShouldBeShown(@Nullable List<PermissionRequest> list, @NotNull PermissionToken token) {
                Intrinsics.checkNotNullParameter(token, "token");
                token.continuePermissionRequest();
            }

            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            @SuppressLint({"MissingPermission"})
            public void onPermissionsChecked(@NotNull MultiplePermissionsReport report) {
                Intrinsics.checkNotNullParameter(report, "report");
                if (report.areAllPermissionsGranted()) {
                    ContactSupportDialog.this.callSupport();
                    return;
                }
                FragmentActivity requireActivity = ContactSupportDialog.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                String string = ContactSupportDialog.this.getString(R.string.lbl_call_permission_msg);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.lbl_call_permission_msg)");
                ExtensionsKt.showToast(requireActivity, string);
            }
        }).check();
    }

    private final void init() {
        ((AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivClose)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCancel)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCallSupport)).setOnClickListener(this);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCancel)) ? true : Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.ivClose))) {
            dismiss();
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btnCallSupport))) {
            checkForPermissionAndMakeCall();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
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
        return inflater.inflate(R.layout.bottom_sheet_contact_support, viewGroup, false);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
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
}
