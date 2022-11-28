package androidx.car.app.connection;

import android.content.AsyncQueryHandler;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.car.app.utils.LogTags;
import androidx.lifecycle.LiveData;
import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes.dex */
final class CarConnectionTypeLiveData extends LiveData<Integer> {
    @VisibleForTesting
    static final String CAR_CONNECTION_AUTHORITY = "androidx.car.app.connection";
    private static final Uri PROJECTION_HOST_URI = new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority(CAR_CONNECTION_AUTHORITY).build();
    private static final int QUERY_TOKEN = 42;
    private final CarConnectionBroadcastReceiver mBroadcastReceiver = new CarConnectionBroadcastReceiver();
    private final Context mContext;
    private final AsyncQueryHandler mQueryHandler;

    /* loaded from: classes.dex */
    class CarConnectionBroadcastReceiver extends BroadcastReceiver {
        CarConnectionBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            CarConnectionTypeLiveData.this.queryForState();
        }
    }

    /* loaded from: classes.dex */
    class CarConnectionQueryHandler extends AsyncQueryHandler {
        CarConnectionQueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        @Override // android.content.AsyncQueryHandler
        protected void onQueryComplete(int i2, Object obj, Cursor cursor) {
            if (cursor == null) {
                CarConnectionTypeLiveData.this.postValue(0);
                return;
            }
            int columnIndex = cursor.getColumnIndex(CarConnection.CAR_CONNECTION_STATE);
            if (columnIndex < 0) {
                Log.e(LogTags.TAG_CONNECTION_TO_CAR, "Connection to car response is missing the connection type, treating as disconnected");
                CarConnectionTypeLiveData.this.postValue(0);
            } else if (cursor.moveToNext()) {
                CarConnectionTypeLiveData.this.postValue(Integer.valueOf(cursor.getInt(columnIndex)));
            } else {
                Log.e(LogTags.TAG_CONNECTION_TO_CAR, "Connection to car response is empty, treating as disconnected");
                CarConnectionTypeLiveData.this.postValue(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CarConnectionTypeLiveData(Context context) {
        this.mContext = context;
        this.mQueryHandler = new CarConnectionQueryHandler(context.getContentResolver());
    }

    @Override // androidx.lifecycle.LiveData
    public void onActive() {
        this.mContext.registerReceiver(this.mBroadcastReceiver, new IntentFilter(CarConnection.ACTION_CAR_CONNECTION_UPDATED));
        queryForState();
    }

    @Override // androidx.lifecycle.LiveData
    public void onInactive() {
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
        this.mQueryHandler.cancelOperation(42);
    }

    void queryForState() {
        this.mQueryHandler.startQuery(42, null, PROJECTION_HOST_URI, new String[]{CarConnection.CAR_CONNECTION_STATE}, null, null, null);
    }
}
