package com.google.android.play.core.assetpacks;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/* loaded from: classes2.dex */
public class AssetPackExtractionService extends Service {

    /* renamed from: a  reason: collision with root package name */
    zzb f7751a;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.f7751a;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        zzd.a(getApplicationContext()).zzb(this);
    }
}
