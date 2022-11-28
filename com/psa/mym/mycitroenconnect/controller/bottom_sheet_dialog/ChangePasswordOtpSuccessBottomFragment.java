package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.views.small_bang.SmallBang;
import com.psa.mym.mycitroenconnect.views.small_bang.SmallBangListener;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ChangePasswordOtpSuccessBottomFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "ChangePasswordOtpSuccessBottomFragment";
    private BottomSheetDialog dialog;
    @Nullable
    private SmallBang mSmallBang;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mPageMode = "";

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ChangePasswordOtpSuccessBottomFragment newInstance() {
            return new ChangePasswordOtpSuccessBottomFragment();
        }
    }

    @JvmStatic
    @NotNull
    public static final ChangePasswordOtpSuccessBottomFragment newInstance() {
        return Companion.newInstance();
    }

    private final void setListeners() {
        ((AppCompatButton) _$_findCachedViewById(R.id.btnOkay)).setOnClickListener(this);
    }

    private final void startAnimation() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ChangePasswordOtpSuccessBottomFragment$startAnimation$$inlined$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                SmallBang smallBang;
                smallBang = ChangePasswordOtpSuccessBottomFragment.this.mSmallBang;
                if (smallBang != null) {
                    smallBang.bang((AppCompatImageView) ChangePasswordOtpSuccessBottomFragment.this._$_findCachedViewById(R.id.imgOtpVerified), new SmallBangListener() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ChangePasswordOtpSuccessBottomFragment$startAnimation$1$1
                        @Override // com.psa.mym.mycitroenconnect.views.small_bang.SmallBangListener
                        public void onAnimationEnd() {
                            Logger.INSTANCE.e("Animation Ended");
                        }

                        @Override // com.psa.mym.mycitroenconnect.views.small_bang.SmallBangListener
                        public void onAnimationStart() {
                            Logger.INSTANCE.e("Animation Started");
                        }
                    });
                }
            }
        }, 300L);
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
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnOkay))) {
            dismiss();
            requireActivity().finish();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, uat.psa.mym.mycitroenconnect.R.style.DialogStyle);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), getTheme());
        this.dialog = bottomSheetDialog;
        bottomSheetDialog.getBehavior().setState(3);
        BottomSheetDialog bottomSheetDialog2 = this.dialog;
        if (bottomSheetDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialog");
            bottomSheetDialog2 = null;
        }
        bottomSheetDialog2.getBehavior().setSkipCollapsed(true);
        BottomSheetDialog bottomSheetDialog3 = this.dialog;
        if (bottomSheetDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialog");
            return null;
        }
        return bottomSheetDialog3;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.otp_success_change_password_fragment, viewGroup, false);
        Bundle requireArguments = requireArguments();
        Intrinsics.checkNotNullExpressionValue(requireArguments, "requireArguments()");
        if (requireArguments.containsKey("mPageMode")) {
            String string = requireArguments.getString("mPageMode");
            Intrinsics.checkNotNull(string);
            this.mPageMode = string;
        }
        SmallBang attach2Window = SmallBang.attach2Window(requireActivity());
        this.mSmallBang = attach2Window;
        int[] iArr = {6404107, 16762663, 6404107, 16762663, 6404107, 16762663, 6404107, 16762663};
        if (attach2Window != null) {
            attach2Window.setColors(iArr);
        }
        startAnimation();
        return inflate;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(@NotNull DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setListeners();
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvOtpVerified)).setText(getString(uat.psa.mym.mycitroenconnect.R.string.label_otp_verified_success));
    }
}
