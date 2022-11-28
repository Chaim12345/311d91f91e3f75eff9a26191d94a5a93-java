package com.google.android.play.core.assetpacks;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
final class zzci implements ServiceConnection {
    private final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("ExtractionForegroundServiceConnection");
    private final List zzb = new ArrayList();
    private final Context zzc;
    @Nullable
    private ExtractionForegroundService zzd;
    @Nullable
    private Notification zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzci(Context context) {
        this.zzc = context;
    }

    private final void zzd() {
        ArrayList arrayList;
        synchronized (this.zzb) {
            arrayList = new ArrayList(this.zzb);
            this.zzb.clear();
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ((com.google.android.play.core.internal.zzz) arrayList.get(i2)).zze(new Bundle(), new Bundle());
            } catch (RemoteException unused) {
                this.zza.zzb("Could not resolve Play Store service state update callback.", new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Notification notification) {
        this.zze = notification;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        this.zza.zza("Stopping foreground installation service.", new Object[0]);
        this.zzc.unbindService(this);
        ExtractionForegroundService extractionForegroundService = this.zzd;
        if (extractionForegroundService != null) {
            extractionForegroundService.zza();
        }
        zzd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(com.google.android.play.core.internal.zzz zzzVar) {
        synchronized (this.zzb) {
            this.zzb.add(zzzVar);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zza.zza("Starting foreground installation service.", new Object[0]);
        ExtractionForegroundService extractionForegroundService = ((zzch) iBinder).f7814a;
        this.zzd = extractionForegroundService;
        extractionForegroundService.startForeground(-1883842196, this.zze);
        zzd();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
