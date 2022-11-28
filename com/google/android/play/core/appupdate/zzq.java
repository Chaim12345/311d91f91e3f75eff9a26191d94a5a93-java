package com.google.android.play.core.appupdate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.play.core.common.PlayCoreVersion;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzas;
import com.google.android.play.core.internal.zzce;
import com.google.android.play.core.internal.zzch;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
/* loaded from: classes2.dex */
final class zzq {
    private static final zzag zzb = new zzag("AppUpdateService");
    private static final Intent zzc = new Intent("com.google.android.play.core.install.BIND_UPDATE_SERVICE").setPackage("com.android.vending");
    @Nullable
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    zzas f7750a;
    private final String zzd;
    private final Context zze;
    private final zzs zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(Context context, zzs zzsVar) {
        this.zzd = context.getPackageName();
        this.zze = context;
        this.zzf = zzsVar;
        if (zzch.zzb(context)) {
            this.f7750a = new zzas(zzce.zza(context), zzb, "AppUpdateService", zzc, zzk.zza, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ Bundle b(zzq zzqVar, String str) {
        Integer num;
        Bundle bundle = new Bundle();
        bundle.putAll(zzi());
        bundle.putString("package.name", str);
        try {
            num = Integer.valueOf(zzqVar.zze.getPackageManager().getPackageInfo(zzqVar.zze.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            zzb.zzb("The current version of the app could not be retrieved", new Object[0]);
            num = null;
        }
        if (num != null) {
            bundle.putInt("app.version.code", num.intValue());
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle zzi() {
        Bundle bundle = new Bundle();
        bundle.putAll(PlayCoreVersion.zza("app_update"));
        bundle.putInt("playcore.version.code", 11003);
        return bundle;
    }

    private static Task zzj() {
        zzb.zzb("onError(%d)", -9);
        return Tasks.zza(new InstallException(-9));
    }

    public final Task zzf(String str) {
        if (this.f7750a == null) {
            return zzj();
        }
        zzb.zzd("completeUpdate(%s)", str);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.f7750a.zzq(new zzm(this, zziVar, zziVar, str), zziVar);
        return zziVar.zza();
    }

    public final Task zzg(String str) {
        if (this.f7750a == null) {
            return zzj();
        }
        zzb.zzd("requestUpdateInfo(%s)", str);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.f7750a.zzq(new zzl(this, zziVar, str, zziVar), zziVar);
        return zziVar.zza();
    }
}
