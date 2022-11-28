package com.psa.mym.mycitroenconnect;

import android.annotation.SuppressLint;
import android.view.View;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.event_bus.GlobalBusUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SuppressLint({"Registered"})
/* loaded from: classes.dex */
public class BusBaseActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

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
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Logger.INSTANCE.d("Bus Registered Activity");
        GlobalBusUtil.Companion.optBus().register(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Logger.INSTANCE.d("Bus Unregister Activity");
        GlobalBusUtil.Companion.optBus().unregister(this);
    }
}
