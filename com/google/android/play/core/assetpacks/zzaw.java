package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes2.dex */
public final class zzaw implements zzy {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("AssetPackServiceImpl");
    private static final Intent zzb = new Intent("com.google.android.play.core.assetmoduleservice.BIND_ASSET_MODULE_SERVICE").setPackage("com.android.vending");
    private final String zzc;
    private final zzco zzd;
    private final zzeb zze;
    @Nullable
    private com.google.android.play.core.internal.zzas zzf;
    @Nullable
    private com.google.android.play.core.internal.zzas zzg;
    private final AtomicBoolean zzh = new AtomicBoolean();

    public zzaw(Context context, zzco zzcoVar, zzeb zzebVar) {
        this.zzc = context.getPackageName();
        this.zzd = zzcoVar;
        this.zze = zzebVar;
        if (com.google.android.play.core.internal.zzch.zzb(context)) {
            Context zza2 = com.google.android.play.core.internal.zzce.zza(context);
            com.google.android.play.core.internal.zzag zzagVar = zza;
            Intent intent = zzb;
            zzz zzzVar = zzz.zza;
            this.zzf = new com.google.android.play.core.internal.zzas(zza2, zzagVar, "AssetPackService", intent, zzzVar, null);
            this.zzg = new com.google.android.play.core.internal.zzas(com.google.android.play.core.internal.zzce.zza(context), zzagVar, "AssetPackService-keepAlive", intent, zzzVar, null);
        }
        zza.zza("AssetPackService initiated.", new Object[0]);
    }

    public static /* bridge */ /* synthetic */ Bundle a(int i2, String str, String str2, int i3) {
        Bundle zzz = zzz(i2, str);
        zzz.putString("slice_id", str2);
        zzz.putInt("chunk_number", i3);
        return zzz;
    }

    public static /* bridge */ /* synthetic */ Bundle d(Map map) {
        Bundle zzA = zzA();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        for (Map.Entry entry : map.entrySet()) {
            Bundle bundle = new Bundle();
            bundle.putString("installed_asset_module_name", (String) entry.getKey());
            bundle.putLong("installed_asset_module_version", ((Long) entry.getValue()).longValue());
            arrayList.add(bundle);
        }
        zzA.putParcelableArrayList("installed_asset_module", arrayList);
        return zzA;
    }

    public static /* bridge */ /* synthetic */ com.google.android.play.core.internal.zzag h() {
        return zza;
    }

    public static /* bridge */ /* synthetic */ com.google.android.play.core.internal.zzas i(zzaw zzawVar) {
        return zzawVar.zzf;
    }

    public static /* bridge */ /* synthetic */ com.google.android.play.core.internal.zzas j(zzaw zzawVar) {
        return zzawVar.zzg;
    }

    public static /* bridge */ /* synthetic */ ArrayList l(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Bundle bundle = new Bundle();
            bundle.putString("module_name", (String) it.next());
            arrayList.add(bundle);
        }
        return arrayList;
    }

    public static /* bridge */ /* synthetic */ List m(zzaw zzawVar, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AssetPackState next = AssetPackStates.zza((Bundle) it.next(), zzawVar.zzd, zzawVar.zze).packStates().values().iterator().next();
            if (next == null) {
                zza.zzb("onGetSessionStates: Bundle contained no pack.", new Object[0]);
            }
            if (zzbg.zza(next.status())) {
                arrayList.add(next.name());
            }
        }
        return arrayList;
    }

    public static Bundle zzA() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 11003);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        bundle.putIntegerArrayList("supported_compression_formats", arrayList);
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(1);
        arrayList2.add(2);
        bundle.putIntegerArrayList("supported_patch_formats", arrayList2);
        return bundle;
    }

    public static Bundle zzB(int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", i2);
        return bundle;
    }

    private static Task zzC() {
        zza.zzb("onError(%d)", -11);
        return Tasks.zza(new AssetPackException(-11));
    }

    public final void zzD(int i2, String str, int i3) {
        if (this.zzf == null) {
            throw new zzck("The Play Store app is not installed or is an unofficial version.", i2);
        }
        zza.zzd("notifyModuleCompleted", new Object[0]);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.zzf.zzq(new zzah(this, zziVar, i2, str, zziVar, i3), zziVar);
    }

    public static Bundle zzz(int i2, String str) {
        Bundle zzB = zzB(i2);
        zzB.putString("module_name", str);
        return zzB;
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zza(int i2, String str, String str2, int i3) {
        if (this.zzf == null) {
            return zzC();
        }
        zza.zzd("getChunkFileDescriptor(%s, %s, %d, session=%d)", str, str2, Integer.valueOf(i3), Integer.valueOf(i2));
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.zzf.zzq(new zzaj(this, zziVar, i2, str, str2, i3, zziVar), zziVar);
        return zziVar.zza();
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zzb(List list, zzbe zzbeVar, Map map) {
        if (this.zzf == null) {
            return zzC();
        }
        zza.zzd("getPackStates(%s)", list);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.zzf.zzq(new zzaf(this, zziVar, list, map, zziVar, zzbeVar), zziVar);
        return zziVar.zza();
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zzc(List list, List list2, Map map) {
        if (this.zzf == null) {
            return zzC();
        }
        zza.zzd("startDownload(%s)", list2);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.zzf.zzq(new zzac(this, zziVar, list2, map, zziVar, list), zziVar);
        zziVar.zza().addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.play.core.assetpacks.zzaa
            @Override // com.google.android.play.core.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                AssetPackStates assetPackStates = (AssetPackStates) obj;
                zzaw.this.zzf();
            }
        });
        return zziVar.zza();
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zzd(Map map) {
        if (this.zzf == null) {
            return zzC();
        }
        zza.zzd("syncPacks", new Object[0]);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.zzf.zzq(new zzae(this, zziVar, map, zziVar), zziVar);
        return zziVar.zza();
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zze(List list) {
        if (this.zzf == null) {
            return;
        }
        zza.zzd("cancelDownloads(%s)", list);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.zzf.zzq(new zzad(this, zziVar, list, zziVar), zziVar);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final synchronized void zzf() {
        if (this.zzg == null) {
            zza.zze("Keep alive connection manager is not initialized.", new Object[0]);
            return;
        }
        com.google.android.play.core.internal.zzag zzagVar = zza;
        zzagVar.zzd("keepAlive", new Object[0]);
        if (!this.zzh.compareAndSet(false, true)) {
            zzagVar.zzd("Service is already kept alive.", new Object[0]);
            return;
        }
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.zzg.zzq(new zzak(this, zziVar, zziVar), zziVar);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzg(int i2, String str, String str2, int i3) {
        if (this.zzf == null) {
            throw new zzck("The Play Store app is not installed or is an unofficial version.", i2);
        }
        zza.zzd("notifyChunkTransferred", new Object[0]);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.zzf.zzq(new zzag(this, zziVar, i2, str, str2, i3, zziVar), zziVar);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzh(int i2, String str) {
        zzD(i2, str, 10);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzi(int i2) {
        if (this.zzf == null) {
            throw new zzck("The Play Store app is not installed or is an unofficial version.", i2);
        }
        zza.zzd("notifySessionFailed", new Object[0]);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.zzf.zzq(new zzai(this, zziVar, i2, zziVar), zziVar);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzj(String str) {
        if (this.zzf == null) {
            return;
        }
        zza.zzd("removePack(%s)", str);
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.zzf.zzq(new zzab(this, zziVar, str, zziVar), zziVar);
    }
}
