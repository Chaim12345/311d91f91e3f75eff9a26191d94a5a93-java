package androidx.car.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.IOnRequestPermissionsListener;
import androidx.car.app.utils.LogTags;
import java.util.ArrayList;
import java.util.Map;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class CarAppInternalActivity extends ComponentActivity {
    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$requestPermissions$0(IOnRequestPermissionsListener iOnRequestPermissionsListener, Map map) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            Boolean bool = (Boolean) entry.getValue();
            if (bool == null || !bool.booleanValue()) {
                arrayList2.add((String) entry.getKey());
            } else {
                arrayList.add((String) entry.getKey());
            }
        }
        try {
            iOnRequestPermissionsListener.onRequestPermissionsResult((String[]) arrayList.toArray(new String[0]), (String[]) arrayList2.toArray(new String[0]));
            finish();
        } catch (RemoteException e2) {
            throw new IllegalStateException(e2);
        }
    }

    private void processInternal(@Nullable Intent intent) {
        if (intent != null && "androidx.car.app.action.REQUEST_PERMISSIONS".equals(intent.getAction())) {
            requestPermissions(intent);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected intent action for CarAppInternalActivity: ");
        sb.append(intent == null ? "null Intent" : intent.getAction());
        Log.e(LogTags.TAG, sb.toString());
        finish();
    }

    private void requestPermissions(Intent intent) {
        Bundle extras = intent.getExtras();
        final IOnRequestPermissionsListener asInterface = IOnRequestPermissionsListener.Stub.asInterface(extras.getBinder("androidx.car.app.action.EXTRA_ON_REQUEST_PERMISSIONS_RESULT_LISTENER_KEY"));
        String[] stringArray = extras.getStringArray("androidx.car.app.action.EXTRA_PERMISSIONS_KEY");
        if (asInterface != null && stringArray != null) {
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: androidx.car.app.f
                @Override // androidx.activity.result.ActivityResultCallback
                public final void onActivityResult(Object obj) {
                    CarAppInternalActivity.this.lambda$requestPermissions$0(asInterface, (Map) obj);
                }
            }).launch(stringArray);
            return;
        }
        Log.e(LogTags.TAG, "Intent to request permissions is missing the callback binder");
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        processInternal(getIntent());
    }
}
