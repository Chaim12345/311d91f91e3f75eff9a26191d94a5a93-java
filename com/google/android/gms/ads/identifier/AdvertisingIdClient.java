package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads_identifier.zze;
import com.google.android.gms.internal.ads_identifier.zzf;
import com.google.firebase.messaging.Constants;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
@KeepForSdk
@ParametersAreNonnullByDefault
/* loaded from: classes.dex */
public class AdvertisingIdClient {
    @Nullable
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    BlockingServiceConnection f5588a;
    @Nullable
    @GuardedBy("this")

    /* renamed from: b  reason: collision with root package name */
    zzf f5589b;
    @GuardedBy("this")

    /* renamed from: c  reason: collision with root package name */
    boolean f5590c;

    /* renamed from: d  reason: collision with root package name */
    final Object f5591d;
    @Nullable
    @GuardedBy("mAutoDisconnectTaskLock")

    /* renamed from: e  reason: collision with root package name */
    zzb f5592e;

    /* renamed from: f  reason: collision with root package name */
    final long f5593f;
    @GuardedBy("this")
    private final Context zzg;

    @KeepForSdkWithMembers
    /* loaded from: classes.dex */
    public static final class Info {
        @Nullable
        private final String zza;
        private final boolean zzb;

        @Deprecated
        public Info(@Nullable String str, boolean z) {
            this.zza = str;
            this.zzb = z;
        }

        @Nullable
        public String getId() {
            return this.zza;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.zzb;
        }

        @NonNull
        public String toString() {
            String str = this.zza;
            boolean z = this.zzb;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 7);
            sb.append("{");
            sb.append(str);
            sb.append("}");
            sb.append(z);
            return sb.toString();
        }
    }

    @KeepForSdk
    public AdvertisingIdClient(@NonNull Context context) {
        this(context, AutoConstants.FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS, false, false);
    }

    @VisibleForTesting
    public AdvertisingIdClient(@NonNull Context context, long j2, boolean z, boolean z2) {
        Context applicationContext;
        this.f5591d = new Object();
        Preconditions.checkNotNull(context);
        if (z && (applicationContext = context.getApplicationContext()) != null) {
            context = applicationContext;
        }
        this.zzg = context;
        this.f5590c = false;
        this.f5593f = j2;
    }

    @NonNull
    @KeepForSdk
    public static Info getAdvertisingIdInfo(@NonNull Context context) {
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1L, true, false);
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            advertisingIdClient.a(false);
            Info zzd = advertisingIdClient.zzd(-1);
            advertisingIdClient.b(zzd, true, 0.0f, SystemClock.elapsedRealtime() - elapsedRealtime, "", null);
            return zzd;
        } finally {
        }
    }

    @KeepForSdk
    public static boolean getIsAdIdFakeForDebugLogging(@NonNull Context context) {
        boolean zzd;
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1L, false, false);
        try {
            advertisingIdClient.a(false);
            Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
            synchronized (advertisingIdClient) {
                if (!advertisingIdClient.f5590c) {
                    synchronized (advertisingIdClient.f5591d) {
                        zzb zzbVar = advertisingIdClient.f5592e;
                        if (zzbVar == null || !zzbVar.f5596b) {
                            throw new IOException("AdvertisingIdClient is not connected.");
                        }
                    }
                    try {
                        advertisingIdClient.a(false);
                        if (!advertisingIdClient.f5590c) {
                            throw new IOException("AdvertisingIdClient cannot reconnect.");
                        }
                    } catch (Exception e2) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
                    }
                }
                Preconditions.checkNotNull(advertisingIdClient.f5588a);
                Preconditions.checkNotNull(advertisingIdClient.f5589b);
                try {
                    zzd = advertisingIdClient.f5589b.zzd();
                } catch (RemoteException unused) {
                    throw new IOException("Remote exception");
                }
            }
            advertisingIdClient.zze();
            return zzd;
        } finally {
            advertisingIdClient.zza();
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public static void setShouldSkipGmsCoreVersionCheck(boolean z) {
    }

    private final Info zzd(int i2) {
        Info info;
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (!this.f5590c) {
                synchronized (this.f5591d) {
                    zzb zzbVar = this.f5592e;
                    if (zzbVar == null || !zzbVar.f5596b) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    a(false);
                    if (!this.f5590c) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                } catch (Exception e2) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
                }
            }
            Preconditions.checkNotNull(this.f5588a);
            Preconditions.checkNotNull(this.f5589b);
            try {
                info = new Info(this.f5589b.zzc(), this.f5589b.zze(true));
            } catch (RemoteException unused) {
                throw new IOException("Remote exception");
            }
        }
        zze();
        return info;
    }

    private final void zze() {
        synchronized (this.f5591d) {
            zzb zzbVar = this.f5592e;
            if (zzbVar != null) {
                zzbVar.f5595a.countDown();
                try {
                    this.f5592e.join();
                } catch (InterruptedException unused) {
                }
            }
            long j2 = this.f5593f;
            if (j2 > 0) {
                this.f5592e = new zzb(this, j2);
            }
        }
    }

    @VisibleForTesting
    protected final void a(boolean z) {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.f5590c) {
                zza();
            }
            Context context = this.zzg;
            try {
                context.getPackageManager().getPackageInfo("com.android.vending", 0);
                int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                if (isGooglePlayServicesAvailable != 0 && isGooglePlayServicesAvailable != 2) {
                    throw new IOException("Google Play services not available");
                }
                BlockingServiceConnection blockingServiceConnection = new BlockingServiceConnection();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (!ConnectionTracker.getInstance().bindService(context, intent, blockingServiceConnection, 1)) {
                    throw new IOException("Connection failure");
                }
                this.f5588a = blockingServiceConnection;
                try {
                    this.f5589b = zze.zza(blockingServiceConnection.getServiceWithTimeout(10000L, TimeUnit.MILLISECONDS));
                    this.f5590c = true;
                    if (z) {
                        zze();
                    }
                } catch (InterruptedException unused) {
                    throw new IOException("Interrupted exception");
                } catch (Throwable th) {
                    throw new IOException(th);
                }
            } catch (PackageManager.NameNotFoundException unused2) {
                throw new GooglePlayServicesNotAvailableException(9);
            }
        }
    }

    @VisibleForTesting
    final boolean b(@Nullable Info info, boolean z, float f2, long j2, String str, @Nullable Throwable th) {
        if (Math.random() <= 0.0d) {
            HashMap hashMap = new HashMap();
            hashMap.put("app_context", "1");
            if (info != null) {
                hashMap.put("limit_ad_tracking", true != info.isLimitAdTrackingEnabled() ? "0" : "1");
                String id = info.getId();
                if (id != null) {
                    hashMap.put("ad_id_size", Integer.toString(id.length()));
                }
            }
            if (th != null) {
                hashMap.put(Constants.IPC_BUNDLE_KEY_SEND_ERROR, th.getClass().getName());
            }
            hashMap.put("tag", "AdvertisingIdClient");
            hashMap.put("time_spent", Long.toString(j2));
            new zza(this, hashMap).start();
            return true;
        }
        return false;
    }

    protected final void finalize() {
        zza();
        super.finalize();
    }

    @NonNull
    @KeepForSdk
    public Info getInfo() {
        return zzd(-1);
    }

    @KeepForSdk
    public void start() {
        a(true);
    }

    public final void zza() {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzg == null || this.f5588a == null) {
                return;
            }
            try {
                if (this.f5590c) {
                    ConnectionTracker.getInstance().unbindService(this.zzg, this.f5588a);
                }
            } catch (Throwable unused) {
            }
            this.f5590c = false;
            this.f5589b = null;
            this.f5588a = null;
        }
    }
}
