package com.google.android.play.core.assetpacks;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public class ExtractionForegroundService extends Service {
    private final IBinder zza = new zzch(this);

    @Override // android.app.Service
    @Nullable
    public final IBinder onBind(Intent intent) {
        return this.zza;
    }

    public final synchronized void zza() {
        stopForeground(true);
        stopSelf();
    }
}
