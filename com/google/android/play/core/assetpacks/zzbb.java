package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzbb extends com.google.android.play.core.listener.zzc {
    private final zzde zzc;
    private final zzcl zzd;
    private final com.google.android.play.core.internal.zzco zze;
    private final zzbx zzf;
    private final zzco zzg;
    private final com.google.android.play.core.internal.zzco zzh;
    private final com.google.android.play.core.internal.zzco zzi;
    private final zzeb zzj;
    private final Handler zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbb(Context context, zzde zzdeVar, zzcl zzclVar, com.google.android.play.core.internal.zzco zzcoVar, zzco zzcoVar2, zzbx zzbxVar, com.google.android.play.core.internal.zzco zzcoVar3, com.google.android.play.core.internal.zzco zzcoVar4, zzeb zzebVar) {
        super(new com.google.android.play.core.internal.zzag("AssetPackServiceListenerRegistry"), new IntentFilter("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE"), context);
        this.zzk = new Handler(Looper.getMainLooper());
        this.zzc = zzdeVar;
        this.zzd = zzclVar;
        this.zze = zzcoVar;
        this.zzg = zzcoVar2;
        this.zzf = zzbxVar;
        this.zzh = zzcoVar3;
        this.zzi = zzcoVar4;
        this.zzj = zzebVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.play.core.listener.zzc
    public final void a(Context context, Intent intent) {
        final Bundle bundleExtra = intent.getBundleExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE");
        if (bundleExtra == null) {
            this.f7869a.zzb("Empty bundle received from broadcast.", new Object[0]);
            return;
        }
        ArrayList<String> stringArrayList = bundleExtra.getStringArrayList("pack_names");
        if (stringArrayList == null || stringArrayList.size() != 1) {
            this.f7869a.zzb("Corrupt bundle received from broadcast.", new Object[0]);
            return;
        }
        final AssetPackState a2 = AssetPackState.a(bundleExtra, stringArrayList.get(0), this.zzg, this.zzj, zzbd.zza);
        this.f7869a.zza("ListenerRegistryBroadcastReceiver.onReceive: %s", a2);
        PendingIntent pendingIntent = (PendingIntent) bundleExtra.getParcelable("confirmation_intent");
        if (pendingIntent != null) {
            this.zzf.b(pendingIntent);
        }
        ((Executor) this.zzi.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzaz
            @Override // java.lang.Runnable
            public final void run() {
                zzbb.this.c(bundleExtra, a2);
            }
        });
        ((Executor) this.zzh.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzay
            @Override // java.lang.Runnable
            public final void run() {
                zzbb.this.b(bundleExtra);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void b(Bundle bundle) {
        if (this.zzc.p(bundle)) {
            this.zzd.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void c(Bundle bundle, AssetPackState assetPackState) {
        if (this.zzc.o(bundle)) {
            d(assetPackState);
            ((zzy) this.zze.zza()).zzf();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(final AssetPackState assetPackState) {
        this.zzk.post(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzba
            @Override // java.lang.Runnable
            public final void run() {
                zzbb.this.zzi(assetPackState);
            }
        });
    }
}
