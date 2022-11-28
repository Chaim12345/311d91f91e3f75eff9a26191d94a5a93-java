package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.play.core.internal.zzce;
import com.google.android.play.core.internal.zzch;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
final class zzbc {
    private static final com.google.android.play.core.internal.zzag zzb = new com.google.android.play.core.internal.zzag("SplitInstallService");
    private static final Intent zzc = new Intent("com.google.android.play.core.splitinstall.BIND_SPLIT_INSTALL_SERVICE").setPackage("com.android.vending");
    @Nullable
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    com.google.android.play.core.internal.zzas f7925a;
    private final String zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbc(Context context, String str) {
        this.zzd = str;
        if (zzch.zzb(context)) {
            this.f7925a = new com.google.android.play.core.internal.zzas(zzce.zza(context), zzb, "SplitInstallService", zzc, zzak.zza, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 11003);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ ArrayList d(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Bundle bundle = new Bundle();
            bundle.putString("language", (String) it.next());
            arrayList.add(bundle);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ ArrayList e(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Bundle bundle = new Bundle();
            bundle.putString("module_name", (String) it.next());
            arrayList.add(bundle);
        }
        return arrayList;
    }

    private static Task zzn() {
        zzb.zzb("onError(%d)", -14);
        return Tasks.zza(new SplitInstallException(-14));
    }

    public final Task zzc(int i2) {
        if (this.f7925a == null) {
            return zzn();
        }
        zzb.zzd("cancelInstall(%d)", Integer.valueOf(i2));
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.f7925a.zzq(new zzas(this, zziVar, i2, zziVar), zziVar);
        return zziVar.zza();
    }

    public final Task zzd(List list) {
        if (this.f7925a == null) {
            return zzn();
        }
        zzb.zzd("deferredInstall(%s)", list);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.f7925a.zzq(new zzan(this, zziVar, list, zziVar), zziVar);
        return zziVar.zza();
    }

    public final Task zze(List list) {
        if (this.f7925a == null) {
            return zzn();
        }
        zzb.zzd("deferredLanguageInstall(%s)", list);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.f7925a.zzq(new zzao(this, zziVar, list, zziVar), zziVar);
        return zziVar.zza();
    }

    public final Task zzf(List list) {
        if (this.f7925a == null) {
            return zzn();
        }
        zzb.zzd("deferredLanguageUninstall(%s)", list);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.f7925a.zzq(new zzap(this, zziVar, list, zziVar), zziVar);
        return zziVar.zza();
    }

    public final Task zzg(List list) {
        if (this.f7925a == null) {
            return zzn();
        }
        zzb.zzd("deferredUninstall(%s)", list);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.f7925a.zzq(new zzam(this, zziVar, list, zziVar), zziVar);
        return zziVar.zza();
    }

    public final Task zzh(int i2) {
        if (this.f7925a == null) {
            return zzn();
        }
        zzb.zzd("getSessionState(%d)", Integer.valueOf(i2));
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.f7925a.zzq(new zzaq(this, zziVar, i2, zziVar), zziVar);
        return zziVar.zza();
    }

    public final Task zzi() {
        if (this.f7925a == null) {
            return zzn();
        }
        zzb.zzd("getSessionStates", new Object[0]);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.f7925a.zzq(new zzar(this, zziVar, zziVar), zziVar);
        return zziVar.zza();
    }

    public final Task zzj(Collection collection, Collection collection2) {
        if (this.f7925a == null) {
            return zzn();
        }
        zzb.zzd("startInstall(%s,%s)", collection, collection2);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.f7925a.zzq(new zzal(this, zziVar, collection, collection2, zziVar), zziVar);
        return zziVar.zza();
    }
}
