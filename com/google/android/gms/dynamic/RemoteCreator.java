package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import androidx.annotation.NonNull;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
@KeepForSdk
/* loaded from: classes.dex */
public abstract class RemoteCreator<T> {
    private final String zza;
    private T zzb;

    @KeepForSdk
    /* loaded from: classes.dex */
    public static class RemoteCreatorException extends Exception {
        @KeepForSdk
        public RemoteCreatorException(@NonNull String str) {
            super(str);
        }

        @KeepForSdk
        public RemoteCreatorException(@NonNull String str, @NonNull Throwable th) {
            super(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @KeepForSdk
    public RemoteCreator(@NonNull String str) {
        this.zza = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    @KeepForSdk
    public final Object a(@NonNull Context context) {
        if (this.zzb == null) {
            Preconditions.checkNotNull(context);
            Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            if (remoteContext == null) {
                throw new RemoteCreatorException("Could not get remote context.");
            }
            try {
                this.zzb = (T) getRemoteCreator((IBinder) remoteContext.getClassLoader().loadClass(this.zza).newInstance());
            } catch (ClassNotFoundException e2) {
                throw new RemoteCreatorException("Could not load creator class.", e2);
            } catch (IllegalAccessException e3) {
                throw new RemoteCreatorException("Could not access creator.", e3);
            } catch (InstantiationException e4) {
                throw new RemoteCreatorException("Could not instantiate creator.", e4);
            }
        }
        return this.zzb;
    }

    @NonNull
    @KeepForSdk
    protected abstract Object getRemoteCreator(@NonNull IBinder iBinder);
}
