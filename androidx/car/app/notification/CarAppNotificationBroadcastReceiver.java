package androidx.car.app.notification;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.car.app.CarContext;
import androidx.car.app.IStartCarApp;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class CarAppNotificationBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "CarApp.NBR";

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull final Intent intent) {
        IBinder iBinder;
        intent.removeExtra("androidx.car.app.notification.COMPONENT_EXTRA_KEY");
        intent.setComponent((ComponentName) intent.getParcelableExtra("androidx.car.app.notification.COMPONENT_EXTRA_KEY"));
        Bundle extras = intent.getExtras();
        if (extras != null) {
            iBinder = extras.getBinder(CarContext.EXTRA_START_CAR_APP_BINDER_KEY);
            extras.remove(CarContext.EXTRA_START_CAR_APP_BINDER_KEY);
        } else {
            iBinder = null;
        }
        if (iBinder == null) {
            Log.e(TAG, "Notification intent missing expected extra: " + intent);
            return;
        }
        IStartCarApp asInterface = IStartCarApp.Stub.asInterface(iBinder);
        Objects.requireNonNull(asInterface);
        final IStartCarApp iStartCarApp = asInterface;
        RemoteUtils.dispatchCallToHost("startCarApp from notification", new RemoteUtils.RemoteCall() { // from class: androidx.car.app.notification.a
            @Override // androidx.car.app.utils.RemoteUtils.RemoteCall
            public final Object call() {
                Object startCarApp;
                startCarApp = IStartCarApp.this.startCarApp(intent);
                return startCarApp;
            }
        });
    }
}
