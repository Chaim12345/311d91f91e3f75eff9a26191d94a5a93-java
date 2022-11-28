package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.controller.activities.onboarding.LoginActivity;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.PasswordSuccessBottomSheetFragment;
import com.psa.mym.mycitroenconnect.databinding.FragmentPasswordSuccessBottomSheetBinding;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.views.small_bang.SmallBang;
import com.psa.mym.mycitroenconnect.views.small_bang.SmallBangListener;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class PasswordSuccessBottomSheetFragment extends BottomSheetDialogFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private FragmentPasswordSuccessBottomSheetBinding binding;
    private SmallBang mSmallBang;
    private String pageMode;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreateView$lambda-1  reason: not valid java name */
    public static final void m123onCreateView$lambda1(PasswordSuccessBottomSheetFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0.requireActivity(), LoginActivity.class);
        intent.putExtra("login", "login");
        this$0.startActivity(intent);
        this$0.requireActivity().finish();
    }

    private final void startAnimation() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.PasswordSuccessBottomSheetFragment$startAnimation$$inlined$Runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                SmallBang smallBang;
                smallBang = PasswordSuccessBottomSheetFragment.this.mSmallBang;
                if (smallBang == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSmallBang");
                    smallBang = null;
                }
                smallBang.bang((AppCompatImageView) PasswordSuccessBottomSheetFragment.this._$_findCachedViewById(R.id.imgOtpVerified), new SmallBangListener() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.PasswordSuccessBottomSheetFragment$startAnimation$1$1
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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("login", "");
            Intrinsics.checkNotNullExpressionValue(string, "it.getString(AppConstants.PAGE_MODE, \"\")");
            this.pageMode = string;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentPasswordSuccessBottomSheetBinding inflate = FragmentPasswordSuccessBottomSheetBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.binding = inflate;
        SmallBang attach2Window = SmallBang.attach2Window(requireActivity());
        Intrinsics.checkNotNullExpressionValue(attach2Window, "attach2Window(requireActivity())");
        this.mSmallBang = attach2Window;
        int[] iArr = {6404107, 16762663, 6404107, 16762663, 6404107, 16762663, 6404107, 16762663};
        FragmentPasswordSuccessBottomSheetBinding fragmentPasswordSuccessBottomSheetBinding = null;
        if (attach2Window == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSmallBang");
            attach2Window = null;
        }
        attach2Window.setColors(iArr);
        startAnimation();
        FragmentPasswordSuccessBottomSheetBinding fragmentPasswordSuccessBottomSheetBinding2 = this.binding;
        if (fragmentPasswordSuccessBottomSheetBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPasswordSuccessBottomSheetBinding2 = null;
        }
        fragmentPasswordSuccessBottomSheetBinding2.btnLogin.setOnClickListener(new View.OnClickListener() { // from class: k.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PasswordSuccessBottomSheetFragment.m123onCreateView$lambda1(PasswordSuccessBottomSheetFragment.this, view);
            }
        });
        FragmentPasswordSuccessBottomSheetBinding fragmentPasswordSuccessBottomSheetBinding3 = this.binding;
        if (fragmentPasswordSuccessBottomSheetBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentPasswordSuccessBottomSheetBinding = fragmentPasswordSuccessBottomSheetBinding3;
        }
        FrameLayout root = fragmentPasswordSuccessBottomSheetBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }
}
