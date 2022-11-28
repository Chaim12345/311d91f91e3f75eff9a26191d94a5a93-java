package com.psa.mym.mycitroenconnect.utils;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.LocationManager;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import com.psa.mym.mycitroenconnect.utils.GpsStatusDetector;
import java.lang.ref.WeakReference;
/* loaded from: classes3.dex */
public class GpsStatusDetector {
    private static final int REQUEST_CODE = 2;
    private Context context;
    private final WeakReference<Activity> mActivityWeakReference;
    private final WeakReference<GpsStatusDetectorCallBack> mCallBackWeakReference;

    /* loaded from: classes3.dex */
    public interface GpsStatusDetectorCallBack {
        void onGpsAlertCanceledByUser();

        void onGpsSettingStatus(boolean z);
    }

    public GpsStatusDetector(Activity activity) {
        this.mActivityWeakReference = new WeakReference<>(activity);
        this.mCallBackWeakReference = new WeakReference<>((GpsStatusDetectorCallBack) activity);
    }

    public GpsStatusDetector(Fragment fragment) {
        this.mActivityWeakReference = new WeakReference<>(fragment.getActivity());
        this.mCallBackWeakReference = new WeakReference<>((GpsStatusDetectorCallBack) fragment);
        this.context = fragment.getContext();
    }

    private boolean isGpsEnabled(Activity activity) {
        return ((LocationManager) activity.getSystemService("location")).isProviderEnabled("gps");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002e, code lost:
        if (r0 != 8502) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static /* synthetic */ void lambda$setLocationRequest$0(GpsStatusDetectorCallBack gpsStatusDetectorCallBack, Activity activity, GoogleApiClient googleApiClient, LocationSettingsResult locationSettingsResult) {
        Status status = locationSettingsResult.getStatus();
        Logger logger = Logger.INSTANCE;
        logger.e("Status Code : " + status.getStatusCode());
        int statusCode = status.getStatusCode();
        if (statusCode == 0) {
            gpsStatusDetectorCallBack.onGpsSettingStatus(true);
        } else if (statusCode != 6) {
            if (statusCode != 16) {
            }
            gpsStatusDetectorCallBack.onGpsSettingStatus(false);
        } else {
            try {
                status.startResolutionForResult(activity, 2);
            } catch (IntentSender.SendIntentException unused) {
            }
        }
        googleApiClient.disconnect();
    }

    private void setLocationRequest(final Activity activity, final GpsStatusDetectorCallBack gpsStatusDetectorCallBack) {
        final GoogleApiClient build = new GoogleApiClient.Builder(activity).addApi(LocationServices.API).build();
        build.connect();
        LocationServices.SettingsApi.checkLocationSettings(build, new LocationSettingsRequest.Builder().addLocationRequest(LocationRequest.create().setPriority(100).setInterval(AutoConstants.FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS).setFastestInterval(5000L)).setAlwaysShow(true).build()).setResultCallback(new ResultCallback() { // from class: com.psa.mym.mycitroenconnect.utils.f
            @Override // com.google.android.gms.common.api.ResultCallback
            public final void onResult(Result result) {
                GpsStatusDetector.lambda$setLocationRequest$0(GpsStatusDetector.GpsStatusDetectorCallBack.this, activity, build, (LocationSettingsResult) result);
            }
        });
    }

    public void checkGpsStatus() {
        Logger.INSTANCE.e("in checkGpsStatus");
        Activity activity = this.mActivityWeakReference.get();
        GpsStatusDetectorCallBack gpsStatusDetectorCallBack = this.mCallBackWeakReference.get();
        if (activity == null || gpsStatusDetectorCallBack == null) {
            return;
        }
        if (isGpsEnabled(activity)) {
            gpsStatusDetectorCallBack.onGpsSettingStatus(true);
        } else {
            setLocationRequest(activity, gpsStatusDetectorCallBack);
        }
    }

    public void checkOnActivityResult(int i2, int i3) {
        Logger logger = Logger.INSTANCE;
        logger.e("in checkOnActivityResult " + i2 + "--- " + i3);
        Activity activity = this.mActivityWeakReference.get();
        GpsStatusDetectorCallBack gpsStatusDetectorCallBack = this.mCallBackWeakReference.get();
        if (activity == null || gpsStatusDetectorCallBack == null || i2 != 2) {
            return;
        }
        if (i3 == -1) {
            gpsStatusDetectorCallBack.onGpsSettingStatus(true);
            return;
        }
        gpsStatusDetectorCallBack.onGpsSettingStatus(false);
        gpsStatusDetectorCallBack.onGpsAlertCanceledByUser();
    }

    public void showMessage(String str) {
        Toast.makeText(this.context, str, 0).show();
    }
}
