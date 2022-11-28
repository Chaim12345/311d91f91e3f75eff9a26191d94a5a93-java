package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import com.google.android.play.core.common.LocalTestingException;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.cli.HelpFormatter;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzdo implements zzy {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("FakeAssetPackService");
    private static final AtomicInteger zzb = new AtomicInteger(1);
    private final String zzc;
    private final zzbb zzd;
    private final zzco zze;
    private final Context zzf;
    private final zzed zzg;
    private final com.google.android.play.core.internal.zzco zzh;
    private final zzeb zzi;
    private final Handler zzj = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public zzdo(File file, zzbb zzbbVar, zzco zzcoVar, Context context, zzed zzedVar, com.google.android.play.core.internal.zzco zzcoVar2, zzeb zzebVar) {
        this.zzc = file.getAbsolutePath();
        this.zzd = zzbbVar;
        this.zze = zzcoVar;
        this.zzf = context;
        this.zzg = zzedVar;
        this.zzh = zzcoVar2;
        this.zzi = zzebVar;
    }

    @VisibleForTesting
    static long a(@AssetPackStatus int i2, long j2) {
        if (i2 != 2) {
            if (i2 == 3 || i2 == 4) {
                return j2;
            }
            return 0L;
        }
        return j2 / 2;
    }

    private final Bundle zzp(int i2, String str, @AssetPackStatus int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("app_version_code", this.zzg.zza());
        bundle.putInt("session_id", i2);
        File[] zzs = zzs(str);
        ArrayList<String> arrayList = new ArrayList<>();
        long j2 = 0;
        for (File file : zzs) {
            j2 += file.length();
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
            arrayList2.add(i3 == 3 ? new Intent().setData(Uri.EMPTY) : null);
            String zza2 = com.google.android.play.core.internal.zzcj.zza(file);
            bundle.putParcelableArrayList(com.google.android.play.core.assetpacks.model.zzb.zzb("chunk_intents", str, zza2), arrayList2);
            bundle.putString(com.google.android.play.core.assetpacks.model.zzb.zzb("uncompressed_hash_sha256", str, zza2), zzr(file));
            bundle.putLong(com.google.android.play.core.assetpacks.model.zzb.zzb("uncompressed_size", str, zza2), file.length());
            arrayList.add(zza2);
        }
        bundle.putStringArrayList(com.google.android.play.core.assetpacks.model.zzb.zza("slice_ids", str), arrayList);
        bundle.putLong(com.google.android.play.core.assetpacks.model.zzb.zza("pack_version", str), this.zzg.zza());
        bundle.putInt(com.google.android.play.core.assetpacks.model.zzb.zza(NotificationCompat.CATEGORY_STATUS, str), i3);
        bundle.putInt(com.google.android.play.core.assetpacks.model.zzb.zza("error_code", str), 0);
        bundle.putLong(com.google.android.play.core.assetpacks.model.zzb.zza("bytes_downloaded", str), a(i3, j2));
        bundle.putLong(com.google.android.play.core.assetpacks.model.zzb.zza("total_bytes_to_download", str), j2);
        bundle.putStringArrayList("pack_names", new ArrayList<>(Arrays.asList(str)));
        bundle.putLong("bytes_downloaded", a(i3, j2));
        bundle.putLong("total_bytes_to_download", j2);
        final Intent putExtra = new Intent("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE").putExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE", bundle);
        this.zzj.post(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzdl
            @Override // java.lang.Runnable
            public final void run() {
                zzdo.this.b(putExtra);
            }
        });
        return bundle;
    }

    private final AssetPackState zzq(String str, @AssetPackStatus int i2) {
        long j2 = 0;
        for (File file : zzs(str)) {
            j2 += file.length();
        }
        return AssetPackState.zzb(str, i2, 0, a(i2, j2), j2, this.zze.a(str), 1, String.valueOf(this.zzg.zza()), this.zzi.a(str));
    }

    private static String zzr(File file) {
        try {
            return zzdq.a(Arrays.asList(file));
        } catch (IOException e2) {
            throw new LocalTestingException(String.format("Could not digest file: %s.", file), e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new LocalTestingException("SHA256 algorithm not supported.", e3);
        }
    }

    private final File[] zzs(final String str) {
        File file = new File(this.zzc);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.google.android.play.core.assetpacks.zzdj
                @Override // java.io.FilenameFilter
                public final boolean accept(File file2, String str2) {
                    return str2.startsWith(String.valueOf(str).concat(HelpFormatter.DEFAULT_OPT_PREFIX)) && str2.endsWith(".apk");
                }
            });
            if (listFiles != null) {
                if (listFiles.length != 0) {
                    for (File file2 : listFiles) {
                        if (com.google.android.play.core.internal.zzcj.zza(file2).equals(str)) {
                            return listFiles;
                        }
                    }
                    throw new LocalTestingException(String.format("No main slice available for pack '%s'.", str));
                }
                throw new LocalTestingException(String.format("No APKs available for pack '%s'.", str));
            }
            throw new LocalTestingException(String.format("Failed fetching APKs for pack '%s'.", str));
        }
        throw new LocalTestingException(String.format("Local testing directory '%s' not found.", file));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void b(Intent intent) {
        this.zzd.a(this.zzf, intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void c(List list, zzbe zzbeVar, com.google.android.play.core.tasks.zzi zziVar) {
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        long j2 = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            try {
                AssetPackState zzq = zzq(str, ((zze) zzbeVar).zza.a(8, str));
                j2 += zzq.totalBytesToDownload();
                hashMap.put(str, zzq);
            } catch (LocalTestingException e2) {
                zziVar.zzb(e2);
                return;
            }
        }
        zziVar.zzc(new zzbo(j2, hashMap));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void d(int i2, String str) {
        try {
            zzp(i2, str, 4);
        } catch (LocalTestingException e2) {
            zza.zze("notifyModuleCompleted failed", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void e(List list, com.google.android.play.core.tasks.zzi zziVar, List list2) {
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        long j2 = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            try {
                AssetPackState zzq = zzq(str, 1);
                j2 += zzq.totalBytesToDownload();
                hashMap.put(str, zzq);
            } catch (LocalTestingException e2) {
                zziVar.zzb(e2);
                return;
            }
        }
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            String str2 = (String) it2.next();
            try {
                int andIncrement = zzb.getAndIncrement();
                zzp(andIncrement, str2, 1);
                zzp(andIncrement, str2, 2);
                zzp(andIncrement, str2, 3);
            } catch (LocalTestingException e3) {
                zziVar.zzb(e3);
                return;
            }
        }
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            String str3 = (String) it3.next();
            hashMap.put(str3, AssetPackState.zzb(str3, 4, 0, 0L, 0L, 0.0d, 1, String.valueOf(this.zzg.zza()), String.valueOf(this.zzg.zza())));
        }
        zziVar.zzc(new zzbo(j2, hashMap));
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zza(int i2, String str, String str2, int i3) {
        File[] zzs;
        int i4;
        zza.zzd("getChunkFileDescriptor(session=%d, %s, %s, %d)", Integer.valueOf(i2), str, str2, Integer.valueOf(i3));
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        try {
        } catch (LocalTestingException e2) {
            zza.zze("getChunkFileDescriptor failed", e2);
            zziVar.zzb(e2);
        } catch (FileNotFoundException e3) {
            zza.zze("getChunkFileDescriptor failed", e3);
            zziVar.zzb(new LocalTestingException("Asset Slice file not found.", e3));
        }
        for (File file : zzs(str)) {
            if (com.google.android.play.core.internal.zzcj.zza(file).equals(str2)) {
                zziVar.zzc(ParcelFileDescriptor.open(file, 268435456));
                return zziVar.zza();
            }
        }
        throw new LocalTestingException(String.format("Local testing slice for '%s' not found.", str2));
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zzb(final List list, final zzbe zzbeVar, Map map) {
        zza.zzd("getPackStates(%s)", list);
        final com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        ((Executor) this.zzh.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzdm
            @Override // java.lang.Runnable
            public final void run() {
                zzdo.this.c(list, zzbeVar, zziVar);
            }
        });
        return zziVar.zza();
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zzc(final List list, final List list2, Map map) {
        zza.zzd("startDownload(%s)", list2);
        final com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        ((Executor) this.zzh.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzdn
            @Override // java.lang.Runnable
            public final void run() {
                zzdo.this.e(list2, zziVar, list);
            }
        });
        return zziVar.zza();
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final Task zzd(Map map) {
        zza.zzd("syncPacks()", new Object[0]);
        return Tasks.zzb(new ArrayList());
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zze(List list) {
        zza.zzd("cancelDownload(%s)", list);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzf() {
        zza.zzd("keepAlive", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzg(int i2, String str, String str2, int i3) {
        zza.zzd("notifyChunkTransferred", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzh(final int i2, final String str) {
        zza.zzd("notifyModuleCompleted", new Object[0]);
        ((Executor) this.zzh.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzdk
            @Override // java.lang.Runnable
            public final void run() {
                zzdo.this.d(i2, str);
            }
        });
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzi(int i2) {
        zza.zzd("notifySessionFailed", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.zzy
    public final void zzj(String str) {
        zza.zzd("removePack(%s)", str);
    }
}
