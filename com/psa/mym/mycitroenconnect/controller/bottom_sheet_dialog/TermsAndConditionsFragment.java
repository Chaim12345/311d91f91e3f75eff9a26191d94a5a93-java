package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.TermsConditionActivity;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.LoginActivity;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import k.e;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TermsAndConditionsFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "TnCBottomSheetFragment";
    private Intent intent;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String mPageMode = "login";

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final OtpSuccessBottomSheetFragment newInstance(@NotNull String param1) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            OtpSuccessBottomSheetFragment otpSuccessBottomSheetFragment = new OtpSuccessBottomSheetFragment();
            Bundle bundle = new Bundle();
            bundle.putString("login", param1);
            otpSuccessBottomSheetFragment.setArguments(bundle);
            return otpSuccessBottomSheetFragment;
        }
    }

    private final void continueToNextScreen() {
        if (!((CheckBox) _$_findCachedViewById(R.id.tnc_accept_checkbox)).isChecked()) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "this.requireActivity()");
            String string = getString(uat.psa.mym.mycitroenconnect.R.string.accept_terms_conditions);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.accept_terms_conditions)");
            ExtensionsKt.showToast(requireActivity, string);
            return;
        }
        String str = this.mPageMode;
        if (Intrinsics.areEqual(str, AppConstants.PAGE_MODE_REGISTRATION)) {
            Intent intent = new Intent(requireActivity(), LoginActivity.class);
            this.intent = intent;
            intent.putExtra("login", AppConstants.PAGE_MODE_REGISTRATION);
        } else if (Intrinsics.areEqual(str, "login")) {
            Intent intent2 = new Intent(requireActivity(), LoginActivity.class);
            this.intent = intent2;
            intent2.putExtra("login", "login");
        }
        Intent intent3 = this.intent;
        if (intent3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("intent");
            intent3 = null;
        }
        startActivity(intent3);
        requireActivity().finish();
    }

    @JvmStatic
    @NotNull
    public static final OtpSuccessBottomSheetFragment newInstance(@NotNull String str) {
        return Companion.newInstance(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreateView$lambda-1  reason: not valid java name */
    public static final void m125onCreateView$lambda1(DialogInterface dialogInterface) {
        Objects.requireNonNull(dialogInterface, "null cannot be cast to non-null type com.google.android.material.bottomsheet.BottomSheetDialog");
        FrameLayout frameLayout = (FrameLayout) ((BottomSheetDialog) dialogInterface).findViewById(uat.psa.mym.mycitroenconnect.R.id.design_bottom_sheet);
        Intrinsics.checkNotNull(frameLayout);
        ViewParent parent = frameLayout.getParent();
        Objects.requireNonNull(parent, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout");
        BottomSheetBehavior from = BottomSheetBehavior.from(frameLayout);
        Intrinsics.checkNotNullExpressionValue(from, "from(bottomSheet)");
        from.setPeekHeight(frameLayout.getHeight());
        ((CoordinatorLayout) parent).getParent().requestLayout();
    }

    private final void setListeners() {
        ((AppCompatImageView) _$_findCachedViewById(R.id.tncClose)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnContinue)).setOnClickListener(this);
    }

    private final void setSpannableString() {
        CharSequence text = getResources().getText(uat.psa.mym.mycitroenconnect.R.string.welcome_in_);
        Intrinsics.checkNotNullExpressionValue(text, "resources.getText(R.string.welcome_in_)");
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(requireContext(), uat.psa.mym.mycitroenconnect.R.color.primary_color_1));
        spannableString.setSpan(foregroundColorSpan, text.length() - 51, text.length() - 32, 33);
        spannableString.setSpan(new ClickableSpan() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.TermsAndConditionsFragment$setSpannableString$clickableSpanTerms$1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View textView) {
                Intrinsics.checkNotNullParameter(textView, "textView");
                Intent intent = new Intent(TermsAndConditionsFragment.this.requireActivity(), TermsConditionActivity.class);
                intent.putExtra("isTerms", true);
                TermsAndConditionsFragment.this.startActivity(intent);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NotNull TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(TermsAndConditionsFragment.this.requireContext(), uat.psa.mym.mycitroenconnect.R.color.primary_color_1));
                ds.setUnderlineText(false);
            }
        }, text.length() - 51, text.length() - 32, 33);
        spannableString.setSpan(foregroundColorSpan, text.length() - 14, text.length(), 33);
        spannableString.setSpan(new ClickableSpan() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.TermsAndConditionsFragment$setSpannableString$clickableSpan$1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View textView) {
                Intrinsics.checkNotNullParameter(textView, "textView");
                Intent intent = new Intent(TermsAndConditionsFragment.this.requireActivity(), TermsConditionActivity.class);
                intent.putExtra("isTerms", false);
                TermsAndConditionsFragment.this.startActivity(intent);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NotNull TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(TermsAndConditionsFragment.this.requireContext(), uat.psa.mym.mycitroenconnect.R.color.primary_color_1));
                ds.setUnderlineText(false);
            }
        }, text.length() - 14, text.length(), 33);
        int i2 = R.id.tvWelcome;
        ((AppCompatTextView) _$_findCachedViewById(i2)).setMovementMethod(LinkMovementMethod.getInstance());
        ((AppCompatTextView) _$_findCachedViewById(i2)).setHighlightColor(0);
        ((AppCompatTextView) _$_findCachedViewById(i2)).setText(spannableString);
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
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.tncClose))) {
            dismiss();
        } else if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnContinue))) {
            continueToNextScreen();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mPageMode = String.valueOf(arguments.getString("login"));
        }
        setStyle(0, uat.psa.mym.mycitroenconnect.R.style.DialogStyle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_terms_and_conditions, viewGroup, false);
        Dialog dialog = getDialog();
        Intrinsics.checkNotNull(dialog);
        dialog.setOnShowListener(e.f11014a);
        return inflate;
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
        setSpannableString();
        setListeners();
    }
}
