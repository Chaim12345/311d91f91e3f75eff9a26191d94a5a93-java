package androidx.room;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.IMultiInstanceInvalidationCallback;
import androidx.room.IMultiInstanceInvalidationService;
import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MultiInstanceInvalidationClient {

    /* renamed from: a  reason: collision with root package name */
    final Context f3884a;

    /* renamed from: b  reason: collision with root package name */
    final String f3885b;

    /* renamed from: c  reason: collision with root package name */
    int f3886c;

    /* renamed from: d  reason: collision with root package name */
    final InvalidationTracker f3887d;

    /* renamed from: e  reason: collision with root package name */
    final InvalidationTracker.Observer f3888e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    IMultiInstanceInvalidationService f3889f;

    /* renamed from: g  reason: collision with root package name */
    final Executor f3890g;

    /* renamed from: h  reason: collision with root package name */
    final IMultiInstanceInvalidationCallback f3891h = new IMultiInstanceInvalidationCallback.Stub() { // from class: androidx.room.MultiInstanceInvalidationClient.1
        @Override // androidx.room.IMultiInstanceInvalidationCallback
        public void onInvalidation(final String[] strArr) {
            MultiInstanceInvalidationClient.this.f3890g.execute(new Runnable() { // from class: androidx.room.MultiInstanceInvalidationClient.1.1
                @Override // java.lang.Runnable
                public void run() {
                    MultiInstanceInvalidationClient.this.f3887d.notifyObserversByTableNames(strArr);
                }
            });
        }
    };

    /* renamed from: i  reason: collision with root package name */
    final AtomicBoolean f3892i = new AtomicBoolean(false);

    /* renamed from: j  reason: collision with root package name */
    final ServiceConnection f3893j;

    /* renamed from: k  reason: collision with root package name */
    final Runnable f3894k;

    /* renamed from: l  reason: collision with root package name */
    final Runnable f3895l;
    private final Runnable mTearDownRunnable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultiInstanceInvalidationClient(Context context, String str, InvalidationTracker invalidationTracker, Executor executor) {
        ServiceConnection serviceConnection = new ServiceConnection() { // from class: androidx.room.MultiInstanceInvalidationClient.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MultiInstanceInvalidationClient.this.f3889f = IMultiInstanceInvalidationService.Stub.asInterface(iBinder);
                MultiInstanceInvalidationClient multiInstanceInvalidationClient = MultiInstanceInvalidationClient.this;
                multiInstanceInvalidationClient.f3890g.execute(multiInstanceInvalidationClient.f3894k);
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                MultiInstanceInvalidationClient multiInstanceInvalidationClient = MultiInstanceInvalidationClient.this;
                multiInstanceInvalidationClient.f3890g.execute(multiInstanceInvalidationClient.f3895l);
                MultiInstanceInvalidationClient.this.f3889f = null;
            }
        };
        this.f3893j = serviceConnection;
        this.f3894k = new Runnable() { // from class: androidx.room.MultiInstanceInvalidationClient.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    MultiInstanceInvalidationClient multiInstanceInvalidationClient = MultiInstanceInvalidationClient.this;
                    IMultiInstanceInvalidationService iMultiInstanceInvalidationService = multiInstanceInvalidationClient.f3889f;
                    if (iMultiInstanceInvalidationService != null) {
                        multiInstanceInvalidationClient.f3886c = iMultiInstanceInvalidationService.registerCallback(multiInstanceInvalidationClient.f3891h, multiInstanceInvalidationClient.f3885b);
                        MultiInstanceInvalidationClient multiInstanceInvalidationClient2 = MultiInstanceInvalidationClient.this;
                        multiInstanceInvalidationClient2.f3887d.addObserver(multiInstanceInvalidationClient2.f3888e);
                    }
                } catch (RemoteException unused) {
                }
            }
        };
        this.f3895l = new Runnable() { // from class: androidx.room.MultiInstanceInvalidationClient.4
            @Override // java.lang.Runnable
            public void run() {
                MultiInstanceInvalidationClient multiInstanceInvalidationClient = MultiInstanceInvalidationClient.this;
                multiInstanceInvalidationClient.f3887d.removeObserver(multiInstanceInvalidationClient.f3888e);
            }
        };
        this.mTearDownRunnable = new Runnable() { // from class: androidx.room.MultiInstanceInvalidationClient.5
            @Override // java.lang.Runnable
            public void run() {
                MultiInstanceInvalidationClient multiInstanceInvalidationClient = MultiInstanceInvalidationClient.this;
                multiInstanceInvalidationClient.f3887d.removeObserver(multiInstanceInvalidationClient.f3888e);
                try {
                    MultiInstanceInvalidationClient multiInstanceInvalidationClient2 = MultiInstanceInvalidationClient.this;
                    IMultiInstanceInvalidationService iMultiInstanceInvalidationService = multiInstanceInvalidationClient2.f3889f;
                    if (iMultiInstanceInvalidationService != null) {
                        iMultiInstanceInvalidationService.unregisterCallback(multiInstanceInvalidationClient2.f3891h, multiInstanceInvalidationClient2.f3886c);
                    }
                } catch (RemoteException unused) {
                }
                MultiInstanceInvalidationClient multiInstanceInvalidationClient3 = MultiInstanceInvalidationClient.this;
                multiInstanceInvalidationClient3.f3884a.unbindService(multiInstanceInvalidationClient3.f3893j);
            }
        };
        Context applicationContext = context.getApplicationContext();
        this.f3884a = applicationContext;
        this.f3885b = str;
        this.f3887d = invalidationTracker;
        this.f3890g = executor;
        this.f3888e = new InvalidationTracker.Observer((String[]) invalidationTracker.f3866a.keySet().toArray(new String[0])) { // from class: androidx.room.MultiInstanceInvalidationClient.6
            @Override // androidx.room.InvalidationTracker.Observer
            boolean a() {
                return true;
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                if (MultiInstanceInvalidationClient.this.f3892i.get()) {
                    return;
                }
                try {
                    MultiInstanceInvalidationClient multiInstanceInvalidationClient = MultiInstanceInvalidationClient.this;
                    IMultiInstanceInvalidationService iMultiInstanceInvalidationService = multiInstanceInvalidationClient.f3889f;
                    if (iMultiInstanceInvalidationService != null) {
                        iMultiInstanceInvalidationService.broadcastInvalidation(multiInstanceInvalidationClient.f3886c, (String[]) set.toArray(new String[0]));
                    }
                } catch (RemoteException unused) {
                }
            }
        };
        applicationContext.bindService(new Intent(applicationContext, MultiInstanceInvalidationService.class), serviceConnection, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (this.f3892i.compareAndSet(false, true)) {
            this.f3890g.execute(this.mTearDownRunnable);
        }
    }
}
