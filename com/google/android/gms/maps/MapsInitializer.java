package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.zzca;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
public final class MapsInitializer {
    private static final String zza = "MapsInitializer";
    @GuardedBy("MapsInitializer.class")
    private static boolean zzb = false;
    @GuardedBy("MapsInitializer.class")
    private static Renderer zzc = Renderer.LEGACY;

    /* loaded from: classes2.dex */
    public enum Renderer {
        LEGACY,
        LATEST
    }

    private MapsInitializer() {
    }

    public static synchronized int initialize(@NonNull Context context) {
        int initialize;
        synchronized (MapsInitializer.class) {
            initialize = initialize(context, null, null);
        }
        return initialize;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:11|12|13|14|15|16|(10:18|(1:(1:21))|22|23|(1:25)|26|27|(1:29)|30|31)|35|22|23|(0)|26|27|(0)|30|31) */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0055, code lost:
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0056, code lost:
        android.util.Log.e(com.google.android.gms.maps.MapsInitializer.zza, "Failed to retrieve renderer type or log initialization.", r5);
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0049 A[Catch: RemoteException -> 0x0055, all -> 0x007d, TryCatch #2 {RemoteException -> 0x0055, blocks: (B:21:0x0043, B:23:0x0049, B:24:0x004d), top: B:47:0x0043, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006a A[Catch: all -> 0x007d, TRY_LEAVE, TryCatch #3 {, blocks: (B:4:0x0003, B:7:0x0018, B:10:0x001f, B:11:0x0023, B:13:0x0032, B:15:0x0037, B:21:0x0043, B:23:0x0049, B:24:0x004d, B:28:0x005d, B:30:0x006a, B:27:0x0056, B:34:0x0072, B:35:0x0077, B:37:0x0079), top: B:49:0x0003, inners: #0, #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized int initialize(@NonNull Context context, @Nullable Renderer renderer, @Nullable OnMapsSdkInitializedCallback onMapsSdkInitializedCallback) {
        synchronized (MapsInitializer.class) {
            Preconditions.checkNotNull(context, "Context is null");
            "preferredRenderer: ".concat(String.valueOf(renderer));
            if (zzb) {
                if (onMapsSdkInitializedCallback != null) {
                    onMapsSdkInitializedCallback.onMapsSdkInitialized(zzc);
                }
                return 0;
            }
            try {
                com.google.android.gms.maps.internal.zzf zza2 = zzca.zza(context, renderer);
                try {
                    CameraUpdateFactory.zza(zza2.zze());
                    BitmapDescriptorFactory.zza(zza2.zzj());
                    int i2 = 1;
                    zzb = true;
                    if (renderer != null) {
                        int ordinal = renderer.ordinal();
                        if (ordinal != 0) {
                            if (ordinal == 1) {
                                i2 = 2;
                            }
                        }
                        if (zza2.zzd() == 2) {
                            zzc = Renderer.LATEST;
                        }
                        zza2.zzl(ObjectWrapper.wrap(context), i2);
                        "loadedRenderer: ".concat(String.valueOf(zzc));
                        if (onMapsSdkInitializedCallback != null) {
                            onMapsSdkInitializedCallback.onMapsSdkInitialized(zzc);
                        }
                        return 0;
                    }
                    i2 = 0;
                    if (zza2.zzd() == 2) {
                    }
                    zza2.zzl(ObjectWrapper.wrap(context), i2);
                    "loadedRenderer: ".concat(String.valueOf(zzc));
                    if (onMapsSdkInitializedCallback != null) {
                    }
                    return 0;
                } catch (RemoteException e2) {
                    throw new RuntimeRemoteException(e2);
                }
            } catch (GooglePlayServicesNotAvailableException e3) {
                return e3.errorCode;
            }
        }
    }
}
