package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SyncTask implements Runnable {
    private final FirebaseMessaging firebaseMessaging;
    private final long nextDelaySeconds;
    private final PowerManager.WakeLock syncWakeLock;

    @VisibleForTesting
    /* loaded from: classes2.dex */
    static class ConnectivityChangeReceiver extends BroadcastReceiver {
        @Nullable
        private SyncTask task;

        public ConnectivityChangeReceiver(SyncTask syncTask) {
            this.task = syncTask;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SyncTask syncTask = this.task;
            if (syncTask != null && syncTask.d()) {
                SyncTask.c();
                this.task.firebaseMessaging.o(this.task, 0L);
                this.task.b().unregisterReceiver(this);
                this.task = null;
            }
        }

        public void registerReceiver() {
            SyncTask.c();
            this.task.b().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    @VisibleForTesting
    @SuppressLint({"InvalidWakeLockTag"})
    public SyncTask(FirebaseMessaging firebaseMessaging, long j2) {
        new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory("firebase-iid-executor"));
        this.firebaseMessaging = firebaseMessaging;
        this.nextDelaySeconds = j2;
        PowerManager.WakeLock newWakeLock = ((PowerManager) b().getSystemService("power")).newWakeLock(1, "fiid-sync");
        this.syncWakeLock = newWakeLock;
        newWakeLock.setReferenceCounted(false);
    }

    static boolean c() {
        return Log.isLoggable(Constants.TAG, 3) || (Build.VERSION.SDK_INT == 23 && Log.isLoggable(Constants.TAG, 3));
    }

    Context b() {
        return this.firebaseMessaging.p();
    }

    boolean d() {
        ConnectivityManager connectivityManager = (ConnectivityManager) b().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @VisibleForTesting
    boolean e() {
        try {
            if (this.firebaseMessaging.n() == null) {
                Log.e(Constants.TAG, "Token retrieval failed: null");
                return false;
            }
            Log.isLoggable(Constants.TAG, 3);
            return true;
        } catch (IOException e2) {
            if (!GmsRpc.d(e2.getMessage())) {
                if (e2.getMessage() == null) {
                    return false;
                }
                throw e2;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Token retrieval failed: ");
            sb.append(e2.getMessage());
            sb.append(". Will retry token retrieval");
            return false;
        } catch (SecurityException unused) {
            return false;
        }
    }

    @Override // java.lang.Runnable
    @SuppressLint({"WakelockTimeout"})
    public void run() {
        if (ServiceStarter.a().d(b())) {
            this.syncWakeLock.acquire();
        }
        try {
            try {
                this.firebaseMessaging.s(true);
            } catch (IOException e2) {
                Log.e(Constants.TAG, "Topic sync or token retrieval failed on hard failure exceptions: " + e2.getMessage() + ". Won't retry the operation.");
                this.firebaseMessaging.s(false);
                if (!ServiceStarter.a().d(b())) {
                    return;
                }
            }
            if (!this.firebaseMessaging.r()) {
                this.firebaseMessaging.s(false);
                if (ServiceStarter.a().d(b())) {
                    this.syncWakeLock.release();
                }
            } else if (ServiceStarter.a().c(b()) && !d()) {
                new ConnectivityChangeReceiver(this).registerReceiver();
                if (ServiceStarter.a().d(b())) {
                    this.syncWakeLock.release();
                }
            } else {
                if (e()) {
                    this.firebaseMessaging.s(false);
                } else {
                    this.firebaseMessaging.t(this.nextDelaySeconds);
                }
                if (!ServiceStarter.a().d(b())) {
                    return;
                }
                this.syncWakeLock.release();
            }
        } catch (Throwable th) {
            if (ServiceStarter.a().d(b())) {
                this.syncWakeLock.release();
            }
            throw th;
        }
    }
}
