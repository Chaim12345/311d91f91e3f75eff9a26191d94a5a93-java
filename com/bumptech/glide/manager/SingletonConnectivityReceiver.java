package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.util.GlideSuppliers;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
final class SingletonConnectivityReceiver {
    private static final String TAG = "ConnectivityMonitor";
    private static volatile SingletonConnectivityReceiver instance;
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    final Set f4807a = new HashSet();
    private final FrameworkConnectivityMonitor frameworkConnectivityMonitor;
    @GuardedBy("this")
    private boolean isRegistered;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface FrameworkConnectivityMonitor {
        boolean register();

        void unregister();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(24)
    /* loaded from: classes.dex */
    public static final class FrameworkConnectivityMonitorPostApi24 implements FrameworkConnectivityMonitor {

        /* renamed from: a  reason: collision with root package name */
        boolean f4810a;

        /* renamed from: b  reason: collision with root package name */
        final ConnectivityMonitor.ConnectivityListener f4811b;
        private final GlideSuppliers.GlideSupplier<ConnectivityManager> connectivityManager;
        private final ConnectivityManager.NetworkCallback networkCallback = new AnonymousClass1();

        /* renamed from: com.bumptech.glide.manager.SingletonConnectivityReceiver$FrameworkConnectivityMonitorPostApi24$1  reason: invalid class name */
        /* loaded from: classes.dex */
        class AnonymousClass1 extends ConnectivityManager.NetworkCallback {
            AnonymousClass1() {
            }

            private void postOnConnectivityChange(final boolean z) {
                Util.postOnUiThread(new Runnable() { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitorPostApi24.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1.this.a(z);
                    }
                });
            }

            void a(boolean z) {
                Util.assertMainThread();
                FrameworkConnectivityMonitorPostApi24 frameworkConnectivityMonitorPostApi24 = FrameworkConnectivityMonitorPostApi24.this;
                boolean z2 = frameworkConnectivityMonitorPostApi24.f4810a;
                frameworkConnectivityMonitorPostApi24.f4810a = z;
                if (z2 != z) {
                    frameworkConnectivityMonitorPostApi24.f4811b.onConnectivityChanged(z);
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(@NonNull Network network) {
                postOnConnectivityChange(true);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(@NonNull Network network) {
                postOnConnectivityChange(false);
            }
        }

        FrameworkConnectivityMonitorPostApi24(GlideSuppliers.GlideSupplier glideSupplier, ConnectivityMonitor.ConnectivityListener connectivityListener) {
            this.connectivityManager = glideSupplier;
            this.f4811b = connectivityListener;
        }

        @Override // com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitor
        @SuppressLint({"MissingPermission"})
        public boolean register() {
            this.f4810a = this.connectivityManager.get().getActiveNetwork() != null;
            try {
                this.connectivityManager.get().registerDefaultNetworkCallback(this.networkCallback);
                return true;
            } catch (RuntimeException unused) {
                Log.isLoggable(SingletonConnectivityReceiver.TAG, 5);
                return false;
            }
        }

        @Override // com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitor
        public void unregister() {
            this.connectivityManager.get().unregisterNetworkCallback(this.networkCallback);
        }
    }

    /* loaded from: classes.dex */
    private static final class FrameworkConnectivityMonitorPreApi24 implements FrameworkConnectivityMonitor {

        /* renamed from: a  reason: collision with root package name */
        final ConnectivityMonitor.ConnectivityListener f4815a;

        /* renamed from: b  reason: collision with root package name */
        boolean f4816b;
        private final GlideSuppliers.GlideSupplier<ConnectivityManager> connectivityManager;
        private final BroadcastReceiver connectivityReceiver = new BroadcastReceiver() { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitorPreApi24.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(@NonNull Context context, Intent intent) {
                FrameworkConnectivityMonitorPreApi24 frameworkConnectivityMonitorPreApi24 = FrameworkConnectivityMonitorPreApi24.this;
                boolean z = frameworkConnectivityMonitorPreApi24.f4816b;
                frameworkConnectivityMonitorPreApi24.f4816b = frameworkConnectivityMonitorPreApi24.a();
                if (z != FrameworkConnectivityMonitorPreApi24.this.f4816b) {
                    if (Log.isLoggable(SingletonConnectivityReceiver.TAG, 3)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("connectivity changed, isConnected: ");
                        sb.append(FrameworkConnectivityMonitorPreApi24.this.f4816b);
                    }
                    FrameworkConnectivityMonitorPreApi24 frameworkConnectivityMonitorPreApi242 = FrameworkConnectivityMonitorPreApi24.this;
                    frameworkConnectivityMonitorPreApi242.f4815a.onConnectivityChanged(frameworkConnectivityMonitorPreApi242.f4816b);
                }
            }
        };
        private final Context context;

        FrameworkConnectivityMonitorPreApi24(Context context, GlideSuppliers.GlideSupplier glideSupplier, ConnectivityMonitor.ConnectivityListener connectivityListener) {
            this.context = context.getApplicationContext();
            this.connectivityManager = glideSupplier;
            this.f4815a = connectivityListener;
        }

        @SuppressLint({"MissingPermission"})
        boolean a() {
            try {
                NetworkInfo activeNetworkInfo = this.connectivityManager.get().getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            } catch (RuntimeException unused) {
                Log.isLoggable(SingletonConnectivityReceiver.TAG, 5);
                return true;
            }
        }

        @Override // com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitor
        public boolean register() {
            this.f4816b = a();
            try {
                this.context.registerReceiver(this.connectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                return true;
            } catch (SecurityException unused) {
                Log.isLoggable(SingletonConnectivityReceiver.TAG, 5);
                return false;
            }
        }

        @Override // com.bumptech.glide.manager.SingletonConnectivityReceiver.FrameworkConnectivityMonitor
        public void unregister() {
            this.context.unregisterReceiver(this.connectivityReceiver);
        }
    }

    private SingletonConnectivityReceiver(@NonNull final Context context) {
        GlideSuppliers.GlideSupplier memorize = GlideSuppliers.memorize(new GlideSuppliers.GlideSupplier<ConnectivityManager>(this) { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.util.GlideSuppliers.GlideSupplier
            public ConnectivityManager get() {
                return (ConnectivityManager) context.getSystemService("connectivity");
            }
        });
        ConnectivityMonitor.ConnectivityListener connectivityListener = new ConnectivityMonitor.ConnectivityListener() { // from class: com.bumptech.glide.manager.SingletonConnectivityReceiver.2
            @Override // com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener
            public void onConnectivityChanged(boolean z) {
                ArrayList<ConnectivityMonitor.ConnectivityListener> arrayList;
                synchronized (SingletonConnectivityReceiver.this) {
                    arrayList = new ArrayList(SingletonConnectivityReceiver.this.f4807a);
                }
                for (ConnectivityMonitor.ConnectivityListener connectivityListener2 : arrayList) {
                    connectivityListener2.onConnectivityChanged(z);
                }
            }
        };
        this.frameworkConnectivityMonitor = Build.VERSION.SDK_INT >= 24 ? new FrameworkConnectivityMonitorPostApi24(memorize, connectivityListener) : new FrameworkConnectivityMonitorPreApi24(context, memorize, connectivityListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SingletonConnectivityReceiver a(@NonNull Context context) {
        if (instance == null) {
            synchronized (SingletonConnectivityReceiver.class) {
                if (instance == null) {
                    instance = new SingletonConnectivityReceiver(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    @GuardedBy("this")
    private void maybeRegisterReceiver() {
        if (this.isRegistered || this.f4807a.isEmpty()) {
            return;
        }
        this.isRegistered = this.frameworkConnectivityMonitor.register();
    }

    @GuardedBy("this")
    private void maybeUnregisterReceiver() {
        if (this.isRegistered && this.f4807a.isEmpty()) {
            this.frameworkConnectivityMonitor.unregister();
            this.isRegistered = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void b(ConnectivityMonitor.ConnectivityListener connectivityListener) {
        this.f4807a.add(connectivityListener);
        maybeRegisterReceiver();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void c(ConnectivityMonitor.ConnectivityListener connectivityListener) {
        this.f4807a.remove(connectivityListener);
        maybeUnregisterReceiver();
    }
}
