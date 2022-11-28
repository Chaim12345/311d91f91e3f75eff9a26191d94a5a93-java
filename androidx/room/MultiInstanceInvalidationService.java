package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.room.IMultiInstanceInvalidationService;
import java.util.HashMap;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class MultiInstanceInvalidationService extends Service {

    /* renamed from: a  reason: collision with root package name */
    int f3904a = 0;

    /* renamed from: b  reason: collision with root package name */
    final HashMap f3905b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    final RemoteCallbackList f3906c = new RemoteCallbackList<IMultiInstanceInvalidationCallback>() { // from class: androidx.room.MultiInstanceInvalidationService.1
        @Override // android.os.RemoteCallbackList
        public void onCallbackDied(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, Object obj) {
            MultiInstanceInvalidationService.this.f3905b.remove(Integer.valueOf(((Integer) obj).intValue()));
        }
    };
    private final IMultiInstanceInvalidationService.Stub mBinder = new IMultiInstanceInvalidationService.Stub() { // from class: androidx.room.MultiInstanceInvalidationService.2
        @Override // androidx.room.IMultiInstanceInvalidationService
        public void broadcastInvalidation(int i2, String[] strArr) {
            synchronized (MultiInstanceInvalidationService.this.f3906c) {
                String str = (String) MultiInstanceInvalidationService.this.f3905b.get(Integer.valueOf(i2));
                if (str == null) {
                    return;
                }
                int beginBroadcast = MultiInstanceInvalidationService.this.f3906c.beginBroadcast();
                for (int i3 = 0; i3 < beginBroadcast; i3++) {
                    int intValue = ((Integer) MultiInstanceInvalidationService.this.f3906c.getBroadcastCookie(i3)).intValue();
                    String str2 = (String) MultiInstanceInvalidationService.this.f3905b.get(Integer.valueOf(intValue));
                    if (i2 != intValue && str.equals(str2)) {
                        try {
                            ((IMultiInstanceInvalidationCallback) MultiInstanceInvalidationService.this.f3906c.getBroadcastItem(i3)).onInvalidation(strArr);
                        } catch (RemoteException unused) {
                        }
                    }
                }
                MultiInstanceInvalidationService.this.f3906c.finishBroadcast();
            }
        }

        @Override // androidx.room.IMultiInstanceInvalidationService
        public int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) {
            if (str == null) {
                return 0;
            }
            synchronized (MultiInstanceInvalidationService.this.f3906c) {
                MultiInstanceInvalidationService multiInstanceInvalidationService = MultiInstanceInvalidationService.this;
                int i2 = multiInstanceInvalidationService.f3904a + 1;
                multiInstanceInvalidationService.f3904a = i2;
                if (multiInstanceInvalidationService.f3906c.register(iMultiInstanceInvalidationCallback, Integer.valueOf(i2))) {
                    MultiInstanceInvalidationService.this.f3905b.put(Integer.valueOf(i2), str);
                    return i2;
                }
                MultiInstanceInvalidationService multiInstanceInvalidationService2 = MultiInstanceInvalidationService.this;
                multiInstanceInvalidationService2.f3904a--;
                return 0;
            }
        }

        @Override // androidx.room.IMultiInstanceInvalidationService
        public void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, int i2) {
            synchronized (MultiInstanceInvalidationService.this.f3906c) {
                MultiInstanceInvalidationService.this.f3906c.unregister(iMultiInstanceInvalidationCallback);
                MultiInstanceInvalidationService.this.f3905b.remove(Integer.valueOf(i2));
            }
        }
    };

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
