package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.fragments.RegistrationFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.login.LoginFragment;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class LoginActivity extends AppCompatActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String mPageMode;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        AppUtil.Companion companion;
        FragmentManager supportFragmentManager;
        Fragment registrationFragment;
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra("login");
            Intrinsics.checkNotNull(stringExtra);
            this.mPageMode = stringExtra;
            if (stringExtra == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPageMode");
                stringExtra = null;
            }
            int hashCode = stringExtra.hashCode();
            if (hashCode != -1350309703) {
                if (hashCode == -1148260554) {
                    stringExtra.equals(AppConstants.PAGE_MODE_ADD_CAR);
                    return;
                } else if (hashCode != 103149417 || !stringExtra.equals("login")) {
                    return;
                } else {
                    companion = AppUtil.Companion;
                    supportFragmentManager = getSupportFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                    registrationFragment = new LoginFragment();
                }
            } else if (!stringExtra.equals(AppConstants.PAGE_MODE_REGISTRATION)) {
                return;
            } else {
                companion = AppUtil.Companion;
                supportFragmentManager = getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
                registrationFragment = new RegistrationFragment();
            }
            companion.replaceFragment(supportFragmentManager, registrationFragment);
        }
    }
}
