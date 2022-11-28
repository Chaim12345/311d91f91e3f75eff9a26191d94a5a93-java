package com.psa.mym.mycitroenconnect;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    private final void overRidePendingTransitionEnter() {
        overridePendingTransition(uat.psa.mym.mycitroenconnect.R.anim.slide_from_right, uat.psa.mym.mycitroenconnect.R.anim.slide_to_left);
    }

    private final void overRidePendingTransitionExit() {
        overridePendingTransition(uat.psa.mym.mycitroenconnect.R.anim.slide_from_left, uat.psa.mym.mycitroenconnect.R.anim.slide_to_right);
    }

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(@Nullable Context context) {
        Resources resources;
        super.attachBaseContext(context);
        Configuration configuration = new Configuration((context == null || (resources = context.getResources()) == null) ? null : resources.getConfiguration());
        configuration.fontScale = 1.0f;
        applyOverrideConfiguration(configuration);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overRidePendingTransitionExit();
    }

    @Override // android.app.Activity
    public void finishAffinity() {
        super.finishAffinity();
        overRidePendingTransitionExit();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        ExtensionsKt.hideKeyboard(this);
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(@Nullable Intent intent) {
        super.startActivity(intent);
        overRidePendingTransitionEnter();
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(@Nullable Intent intent, @Nullable Bundle bundle) {
        super.startActivity(intent, bundle);
        overRidePendingTransitionEnter();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    @Deprecated(message = "Deprecated in Java")
    public void startActivityForResult(@Nullable Intent intent, int i2) {
        super.startActivityForResult(intent, i2);
        overRidePendingTransitionEnter();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    @Deprecated(message = "Deprecated in Java")
    public void startActivityForResult(@Nullable Intent intent, int i2, @Nullable Bundle bundle) {
        super.startActivityForResult(intent, i2, bundle);
        overRidePendingTransitionEnter();
    }
}
