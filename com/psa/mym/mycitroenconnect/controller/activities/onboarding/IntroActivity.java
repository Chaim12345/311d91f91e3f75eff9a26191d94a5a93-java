package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.adapters.view_pager.IntroSliderAdapter;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.TermsAndConditionsFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.intro_screen.IntroScreenFive;
import com.psa.mym.mycitroenconnect.controller.fragments.intro_screen.IntroScreenFour;
import com.psa.mym.mycitroenconnect.controller.fragments.intro_screen.IntroScreenOne;
import com.psa.mym.mycitroenconnect.controller.fragments.intro_screen.IntroScreenThree;
import com.psa.mym.mycitroenconnect.controller.fragments.intro_screen.IntroScreenTwo;
import com.rd.PageIndicatorView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class IntroActivity extends BaseActivity {
    public BottomSheetDialog bottomSheetDialog;
    private long lastClickTime;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private final ArrayList<Fragment> fragmentList = new ArrayList<>();

    private final void displayDialog(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("login", str);
        TermsAndConditionsFragment termsAndConditionsFragment = new TermsAndConditionsFragment();
        termsAndConditionsFragment.setArguments(bundle);
        termsAndConditionsFragment.show(getSupportFragmentManager(), TermsAndConditionsFragment.TAG);
        termsAndConditionsFragment.setCancelable(true);
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

    @NotNull
    public final BottomSheetDialog getBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = this.bottomSheetDialog;
        if (bottomSheetDialog != null) {
            return bottomSheetDialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialog");
        return null;
    }

    @Override // com.psa.mym.mycitroenconnect.BaseActivity, android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        String str;
        if (SystemClock.elapsedRealtime() - this.lastClickTime < 1500) {
            return;
        }
        this.lastClickTime = SystemClock.elapsedRealtime();
        Intrinsics.checkNotNull(view);
        switch (view.getId()) {
            case R.id.btn_login /* 2131362012 */:
                str = "login";
                break;
            case R.id.btn_register /* 2131362013 */:
                str = AppConstants.PAGE_MODE_REGISTRATION;
                break;
            default:
                return;
        }
        displayDialog(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        List listOf;
        super.onCreate(bundle);
        setContentView(R.layout.activity_intro);
        Window window = getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "window");
        window.setFlags(512, 512);
        IntroSliderAdapter introSliderAdapter = new IntroSliderAdapter(this);
        int i2 = com.psa.mym.mycitroenconnect.R.id.viewPager;
        ((ViewPager2) _$_findCachedViewById(i2)).setAdapter(introSliderAdapter);
        this.fragmentList.clear();
        ArrayList<Fragment> arrayList = this.fragmentList;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Fragment[]{new IntroScreenOne(), new IntroScreenTwo(), new IntroScreenThree(), new IntroScreenFour(), new IntroScreenFive()});
        arrayList.addAll(listOf);
        introSliderAdapter.setFragmentList(this.fragmentList);
        int i3 = com.psa.mym.mycitroenconnect.R.id.pageIndicatorView;
        PageIndicatorView pageIndicatorView = (PageIndicatorView) _$_findCachedViewById(i3);
        if (pageIndicatorView != null) {
            pageIndicatorView.setCount(introSliderAdapter.getItemCount());
        }
        PageIndicatorView pageIndicatorView2 = (PageIndicatorView) _$_findCachedViewById(i3);
        if (pageIndicatorView2 != null) {
            pageIndicatorView2.setInteractiveAnimation(true);
        }
        ((ViewPager2) _$_findCachedViewById(i2)).registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.psa.mym.mycitroenconnect.controller.activities.onboarding.IntroActivity$onCreate$1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i4) {
                PageIndicatorView pageIndicatorView3 = (PageIndicatorView) IntroActivity.this._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.pageIndicatorView);
                if (pageIndicatorView3 == null) {
                    return;
                }
                pageIndicatorView3.setSelection(i4);
            }
        });
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btn_register)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.btn_login)).setOnClickListener(this);
    }

    public final void setBottomSheetDialog(@NotNull BottomSheetDialog bottomSheetDialog) {
        Intrinsics.checkNotNullParameter(bottomSheetDialog, "<set-?>");
        this.bottomSheetDialog = bottomSheetDialog;
    }
}
