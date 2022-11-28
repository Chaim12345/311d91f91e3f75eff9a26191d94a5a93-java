package com.google.android.play.core.assetpacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzl implements AssetPackManager {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("AssetPackManager");
    private final zzbh zzb;
    private final com.google.android.play.core.internal.zzco zzc;
    private final zzbb zzd;
    private final com.google.android.play.core.splitinstall.zzs zze;
    private final zzde zzf;
    private final zzco zzg;
    private final zzbx zzh;
    private final com.google.android.play.core.internal.zzco zzi;
    private final com.google.android.play.core.common.zza zzj;
    private final zzeb zzk;
    private final Handler zzl = new Handler(Looper.getMainLooper());
    private boolean zzm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(zzbh zzbhVar, com.google.android.play.core.internal.zzco zzcoVar, zzbb zzbbVar, com.google.android.play.core.splitinstall.zzs zzsVar, zzde zzdeVar, zzco zzcoVar2, zzbx zzbxVar, com.google.android.play.core.internal.zzco zzcoVar3, com.google.android.play.core.common.zza zzaVar, zzeb zzebVar) {
        this.zzb = zzbhVar;
        this.zzc = zzcoVar;
        this.zzd = zzbbVar;
        this.zze = zzsVar;
        this.zzf = zzdeVar;
        this.zzg = zzcoVar2;
        this.zzh = zzbxVar;
        this.zzi = zzcoVar3;
        this.zzj = zzaVar;
        this.zzk = zzebVar;
    }

    private final void zzh() {
        ((Executor) this.zzi.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzi
            @Override // java.lang.Runnable
            public final void run() {
                zzl.this.f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @AssetPackStatus
    @VisibleForTesting
    public final int a(@AssetPackStatus int i2, String str) {
        if (this.zzb.g(str) || i2 != 4) {
            if (!this.zzb.g(str) || i2 == 4) {
                return i2;
            }
            return 4;
        }
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void c() {
        this.zzb.F();
        this.zzb.D();
        this.zzb.E();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final AssetPackStates cancel(List<String> list) {
        Map f2 = this.zzf.f(list);
        HashMap hashMap = new HashMap();
        for (String str : list) {
            Integer num = (Integer) f2.get(str);
            hashMap.put(str, AssetPackState.zzb(str, num == null ? 0 : num.intValue(), 0, 0L, 0L, 0.0d, 0, "", ""));
        }
        ((zzy) this.zzc.zza()).zze(list);
        return new zzbo(0L, hashMap);
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final void clearListeners() {
        this.zzd.zze();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void d(String str, com.google.android.play.core.tasks.zzi zziVar) {
        if (!this.zzb.d(str)) {
            zziVar.zzb(new IOException(String.format("Failed to remove pack %s.", str)));
            return;
        }
        zziVar.zzc(null);
        ((zzy) this.zzc.zza()).zzj(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void f() {
        Task zzd = ((zzy) this.zzc.zza()).zzd(this.zzb.B());
        final zzbh zzbhVar = this.zzb;
        zzbhVar.getClass();
        zzd.addOnSuccessListener((Executor) this.zzi.zza(), new OnSuccessListener() { // from class: com.google.android.play.core.assetpacks.zzg
            @Override // com.google.android.play.core.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                zzbh.this.c((List) obj);
            }
        });
        zzd.addOnFailureListener((Executor) this.zzi.zza(), zzf.zza);
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<AssetPackStates> fetch(List<String> list) {
        Map B = this.zzb.B();
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList();
        if (!this.zzj.zza("assetOnlyUpdates")) {
            arrayList.removeAll(B.keySet());
            arrayList2.addAll(list);
            arrayList2.removeAll(arrayList);
        }
        if (arrayList.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putInt("session_id", 0);
            bundle.putInt("error_code", 0);
            for (String str : list) {
                bundle.putInt(com.google.android.play.core.assetpacks.model.zzb.zza(NotificationCompat.CATEGORY_STATUS, str), 4);
                bundle.putInt(com.google.android.play.core.assetpacks.model.zzb.zza("error_code", str), 0);
                bundle.putLong(com.google.android.play.core.assetpacks.model.zzb.zza("total_bytes_to_download", str), 0L);
                bundle.putLong(com.google.android.play.core.assetpacks.model.zzb.zza("bytes_downloaded", str), 0L);
            }
            bundle.putStringArrayList("pack_names", new ArrayList<>(list));
            bundle.putLong("total_bytes_to_download", 0L);
            bundle.putLong("bytes_downloaded", 0L);
            return Tasks.zzb(AssetPackStates.zza(bundle, this.zzg, this.zzk));
        }
        return ((zzy) this.zzc.zza()).zzc(arrayList2, arrayList, B);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void g(boolean z) {
        boolean zzj = this.zzd.zzj();
        this.zzd.zzg(z);
        if (!z || zzj) {
            return;
        }
        zzh();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    @Nullable
    public final AssetLocation getAssetLocation(String str, String str2) {
        AssetPackLocation m2;
        if (!this.zzm) {
            ((Executor) this.zzi.zza()).execute(new zzh(this));
            this.zzm = true;
        }
        if (this.zzb.g(str)) {
            try {
                m2 = this.zzb.m(str);
            } catch (IOException unused) {
            }
        } else {
            if (this.zze.zzc().contains(str)) {
                m2 = AssetPackLocation.a();
            }
            m2 = null;
        }
        if (m2 == null) {
            return null;
        }
        if (m2.packStorageMethod() == 1) {
            zzbh zzbhVar = this.zzb;
            return zzbhVar.k(str, str2, zzbhVar.z(str));
        } else if (m2.packStorageMethod() == 0) {
            return this.zzb.l(str, str2, m2);
        } else {
            zza.zza("The asset %s is not present in Asset Pack %s", str2, str);
            return null;
        }
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    @Nullable
    public final AssetPackLocation getPackLocation(String str) {
        if (!this.zzm) {
            ((Executor) this.zzi.zza()).execute(new zzh(this));
            this.zzm = true;
        }
        if (this.zzb.g(str)) {
            try {
                return this.zzb.m(str);
            } catch (IOException unused) {
                return null;
            }
        } else if (this.zze.zzc().contains(str)) {
            return AssetPackLocation.a();
        } else {
            return null;
        }
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Map<String, AssetPackLocation> getPackLocations() {
        Map<String, AssetPackLocation> C = this.zzb.C();
        HashMap hashMap = new HashMap();
        for (String str : this.zze.zzc()) {
            hashMap.put(str, AssetPackLocation.a());
        }
        C.putAll(hashMap);
        return C;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<AssetPackStates> getPackStates(List<String> list) {
        return ((zzy) this.zzc.zza()).zzb(list, new zze(this), this.zzb.B());
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final synchronized void registerListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        boolean zzj = this.zzd.zzj();
        this.zzd.zzf(assetPackStateUpdateListener);
        if (zzj) {
            return;
        }
        zzh();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<Void> removePack(final String str) {
        final com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        ((Executor) this.zzi.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzj
            @Override // java.lang.Runnable
            public final void run() {
                zzl.this.d(str, zziVar);
            }
        });
        return zziVar.zza();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<Integer> showCellularDataConfirmation(Activity activity) {
        if (activity == null) {
            return Tasks.zza(new AssetPackException(-3));
        }
        if (this.zzh.a() == null) {
            return Tasks.zza(new AssetPackException(-12));
        }
        Intent intent = new Intent(activity, PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", this.zzh.a());
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        intent.putExtra("result_receiver", new zzk(this, this.zzl, zziVar));
        activity.startActivity(intent);
        return zziVar.zza();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final void unregisterListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        this.zzd.zzh(assetPackStateUpdateListener);
    }
}
