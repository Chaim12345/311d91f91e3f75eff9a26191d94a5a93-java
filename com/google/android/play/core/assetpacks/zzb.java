package com.google.android.play.core.assetpacks;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes2.dex */
final class zzb extends com.google.android.play.core.internal.zzx {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final NotificationManager f7801a;
    private final com.google.android.play.core.internal.zzag zzb = new com.google.android.play.core.internal.zzag("AssetPackExtractionService");
    private final Context zzc;
    private final zzbh zzd;
    private final zzl zze;
    private final zzci zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(Context context, zzbh zzbhVar, zzl zzlVar, zzci zzciVar) {
        this.zzc = context;
        this.zzd = zzbhVar;
        this.zze = zzlVar;
        this.zzf = zzciVar;
        this.f7801a = (NotificationManager) context.getSystemService("notification");
    }

    @TargetApi(26)
    private final synchronized void zzd(@Nullable String str) {
        if (str == null) {
            str = "File downloads by Play";
        }
        this.f7801a.createNotificationChannel(new NotificationChannel("playcore-assetpacks-service-notification-channel", str, 2));
    }

    private final synchronized void zze(Bundle bundle, com.google.android.play.core.internal.zzz zzzVar) {
        int i2;
        this.zzb.zza("updateServiceState AIDL call", new Object[0]);
        if (com.google.android.play.core.internal.zzch.zzb(this.zzc) && com.google.android.play.core.internal.zzch.zza(this.zzc)) {
            int i3 = bundle.getInt("action_type");
            this.zzf.c(zzzVar);
            if (i3 != 1) {
                if (i3 == 2) {
                    this.zze.g(false);
                    this.zzf.b();
                    return;
                }
                this.zzb.zzb("Unknown action type received: %d", Integer.valueOf(i3));
                zzzVar.zzd(new Bundle());
                return;
            }
            int i4 = Build.VERSION.SDK_INT;
            if (i4 >= 26) {
                zzd(bundle.getString("notification_channel_name"));
            }
            this.zze.g(true);
            zzci zzciVar = this.zzf;
            String string = bundle.getString("notification_title");
            String string2 = bundle.getString("notification_subtext");
            long j2 = bundle.getLong("notification_timeout", 600000L);
            Parcelable parcelable = bundle.getParcelable("notification_on_click_intent");
            Notification.Builder timeoutAfter = i4 >= 26 ? new Notification.Builder(this.zzc, "playcore-assetpacks-service-notification-channel").setTimeoutAfter(j2) : new Notification.Builder(this.zzc).setPriority(-2);
            if (parcelable instanceof PendingIntent) {
                timeoutAfter.setContentIntent((PendingIntent) parcelable);
            }
            Notification.Builder ongoing = timeoutAfter.setSmallIcon(17301633).setOngoing(false);
            if (string == null) {
                string = "Downloading additional file";
            }
            Notification.Builder contentTitle = ongoing.setContentTitle(string);
            if (string2 == null) {
                string2 = "Transferring";
            }
            contentTitle.setSubText(string2);
            if (i4 >= 21 && (i2 = bundle.getInt("notification_color")) != 0) {
                timeoutAfter.setColor(i2).setVisibility(-1);
            }
            zzciVar.a(timeoutAfter.build());
            this.zzc.bindService(new Intent(this.zzc, ExtractionForegroundService.class), this.zzf, 1);
            return;
        }
        zzzVar.zzd(new Bundle());
    }

    @Override // com.google.android.play.core.internal.zzy
    public final void zzb(Bundle bundle, com.google.android.play.core.internal.zzz zzzVar) {
        this.zzb.zza("clearAssetPackStorage AIDL call", new Object[0]);
        if (!com.google.android.play.core.internal.zzch.zzb(this.zzc) || !com.google.android.play.core.internal.zzch.zza(this.zzc)) {
            zzzVar.zzd(new Bundle());
            return;
        }
        this.zzd.G();
        zzzVar.zzc(new Bundle());
    }

    @Override // com.google.android.play.core.internal.zzy
    public final void zzc(Bundle bundle, com.google.android.play.core.internal.zzz zzzVar) {
        zze(bundle, zzzVar);
    }
}
