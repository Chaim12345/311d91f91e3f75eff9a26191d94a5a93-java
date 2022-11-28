package com.psa.mym.mycitroenconnect.controller.activities;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentManager;
import com.psa.mym.mycitroenconnect.BaseActivity;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.TermsConditionActivity;
import com.psa.mym.mycitroenconnect.controller.fragments.WebViewFragment;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TermsConditionActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private boolean isTerms;

    private final void setToolbar() {
        AppCompatTextView appCompatTextView;
        int i2;
        if (this.isTerms) {
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvDbHeaderTitle);
            i2 = uat.psa.mym.mycitroenconnect.R.string.menu_title_terms_n_conditions;
        } else {
            appCompatTextView = (AppCompatTextView) _$_findCachedViewById(R.id.tvDbHeaderTitle);
            i2 = uat.psa.mym.mycitroenconnect.R.string.menu_title_privacy_policy;
        }
        appCompatTextView.setText(getString(i2));
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() { // from class: i.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TermsConditionActivity.m84setToolbar$lambda0(TermsConditionActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setToolbar$lambda-0  reason: not valid java name */
    public static final void m84setToolbar$lambda0(TermsConditionActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        WebViewFragment.Companion companion;
        String str;
        super.onCreate(bundle);
        setContentView(uat.psa.mym.mycitroenconnect.R.layout.activity_terms_condition);
        this.isTerms = getIntent().getBooleanExtra("isTerms", false);
        setToolbar();
        if (this.isTerms) {
            companion = WebViewFragment.Companion;
            str = AppConstants.TERMS_CONDITION_URL;
        } else {
            companion = WebViewFragment.Companion;
            str = AppConstants.PRIVACY_POLICY_URL;
        }
        WebViewFragment newInstance = companion.newInstance(str);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        ExtensionsKt.replaceFragment(newInstance, false, uat.psa.mym.mycitroenconnect.R.id.fragmentContainerPeripheral, supportFragmentManager);
    }
}
