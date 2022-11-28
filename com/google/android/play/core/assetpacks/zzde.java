package com.google.android.play.core.assetpacks;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzde {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("ExtractorSessionStoreView");
    private final zzbh zzb;
    private final com.google.android.play.core.internal.zzco zzc;
    private final zzco zzd;
    private final com.google.android.play.core.internal.zzco zze;
    private final Map zzf = new HashMap();
    private final ReentrantLock zzg = new ReentrantLock();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzde(zzbh zzbhVar, com.google.android.play.core.internal.zzco zzcoVar, zzco zzcoVar2, com.google.android.play.core.internal.zzco zzcoVar3) {
        this.zzb = zzbhVar;
        this.zzc = zzcoVar;
        this.zzd = zzcoVar2;
        this.zze = zzcoVar3;
    }

    private final zzdb zzq(int i2) {
        Map map = this.zzf;
        Integer valueOf = Integer.valueOf(i2);
        zzdb zzdbVar = (zzdb) map.get(valueOf);
        if (zzdbVar != null) {
            return zzdbVar;
        }
        throw new zzck(String.format("Could not find session %d while trying to get it", valueOf), i2);
    }

    private final Object zzr(zzdd zzddVar) {
        try {
            this.zzg.lock();
            return zzddVar.zza();
        } finally {
            this.zzg.unlock();
        }
    }

    private static String zzs(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        if (stringArrayList == null || stringArrayList.isEmpty()) {
            throw new zzck("Session without pack received.");
        }
        return stringArrayList.get(0);
    }

    private static List zzt(List list) {
        return list == null ? Collections.emptyList() : list;
    }

    private final Map zzu(final List list) {
        return (Map) zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzcx
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                return zzde.this.i(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Boolean a(Bundle bundle) {
        int i2 = bundle.getInt("session_id");
        if (i2 == 0) {
            return Boolean.TRUE;
        }
        Map map = this.zzf;
        Integer valueOf = Integer.valueOf(i2);
        if (map.containsKey(valueOf)) {
            zzdb zzdbVar = (zzdb) this.zzf.get(valueOf);
            if (zzdbVar.f7825c.f7820d == 6) {
                return Boolean.FALSE;
            }
            return Boolean.valueOf(!zzbg.a(zzdbVar.f7825c.f7820d, bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zza(NotificationCompat.CATEGORY_STATUS, zzs(bundle)))));
        }
        return Boolean.TRUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Boolean b(Bundle bundle) {
        zzdc zzdcVar;
        int i2 = bundle.getInt("session_id");
        if (i2 == 0) {
            return Boolean.FALSE;
        }
        Map map = this.zzf;
        Integer valueOf = Integer.valueOf(i2);
        boolean z = true;
        boolean z2 = false;
        if (map.containsKey(valueOf)) {
            zzdb zzq = zzq(i2);
            int i3 = bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zza(NotificationCompat.CATEGORY_STATUS, zzq.f7825c.f7817a));
            zzda zzdaVar = zzq.f7825c;
            int i4 = zzdaVar.f7820d;
            if (zzbg.a(i4, i3)) {
                zza.zza("Found stale update for session %s with status %d.", valueOf, Integer.valueOf(i4));
                zzda zzdaVar2 = zzq.f7825c;
                String str = zzdaVar2.f7817a;
                int i5 = zzdaVar2.f7820d;
                if (i5 == 4) {
                    ((zzy) this.zzc.zza()).zzh(i2, str);
                } else if (i5 == 5) {
                    ((zzy) this.zzc.zza()).zzi(i2);
                } else if (i5 == 6) {
                    ((zzy) this.zzc.zza()).zze(Arrays.asList(str));
                }
            } else {
                zzdaVar.f7820d = i3;
                if (zzbg.zzd(i3)) {
                    n(i2);
                    this.zzd.c(zzq.f7825c.f7817a);
                } else {
                    for (zzdc zzdcVar2 : zzdaVar.f7822f) {
                        ArrayList parcelableArrayList = bundle.getParcelableArrayList(com.google.android.play.core.assetpacks.model.zzb.zzb("chunk_intents", zzq.f7825c.f7817a, zzdcVar2.f7826a));
                        if (parcelableArrayList != null) {
                            for (int i6 = 0; i6 < parcelableArrayList.size(); i6++) {
                                if (parcelableArrayList.get(i6) != null && ((Intent) parcelableArrayList.get(i6)).getData() != null) {
                                    ((zzcz) zzdcVar2.f7829d.get(i6)).f7816a = true;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            String zzs = zzs(bundle);
            long j2 = bundle.getLong(com.google.android.play.core.assetpacks.model.zzb.zza("pack_version", zzs));
            String string = bundle.getString(com.google.android.play.core.assetpacks.model.zzb.zza("pack_version_tag", zzs), "");
            int i7 = bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zza(NotificationCompat.CATEGORY_STATUS, zzs));
            long j3 = bundle.getLong(com.google.android.play.core.assetpacks.model.zzb.zza("total_bytes_to_download", zzs));
            ArrayList<String> stringArrayList = bundle.getStringArrayList(com.google.android.play.core.assetpacks.model.zzb.zza("slice_ids", zzs));
            ArrayList arrayList = new ArrayList();
            for (String str2 : zzt(stringArrayList)) {
                ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(com.google.android.play.core.assetpacks.model.zzb.zzb("chunk_intents", zzs, str2));
                ArrayList arrayList2 = new ArrayList();
                for (Intent intent : zzt(parcelableArrayList2)) {
                    if (intent == null) {
                        z = z2;
                    }
                    arrayList2.add(new zzcz(z));
                    z = true;
                    z2 = false;
                }
                String string2 = bundle.getString(com.google.android.play.core.assetpacks.model.zzb.zzb("uncompressed_hash_sha256", zzs, str2));
                long j4 = bundle.getLong(com.google.android.play.core.assetpacks.model.zzb.zzb("uncompressed_size", zzs, str2));
                int i8 = bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zzb("patch_format", zzs, str2), 0);
                if (i8 != 0) {
                    zzdcVar = new zzdc(str2, string2, j4, arrayList2, 0, i8);
                    z2 = false;
                } else {
                    z2 = false;
                    zzdcVar = new zzdc(str2, string2, j4, arrayList2, bundle.getInt(com.google.android.play.core.assetpacks.model.zzb.zzb("compression_format", zzs, str2), 0), 0);
                }
                arrayList.add(zzdcVar);
                z = true;
            }
            this.zzf.put(Integer.valueOf(i2), new zzdb(i2, bundle.getInt("app_version_code"), new zzda(zzs, j2, i7, j3, arrayList, string)));
        }
        return Boolean.TRUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object c(String str, int i2, long j2) {
        zzdb zzdbVar = (zzdb) zzu(Arrays.asList(str)).get(str);
        if (zzdbVar == null || zzbg.zzd(zzdbVar.f7825c.f7820d)) {
            zza.zzb(String.format("Could not find pack %s while trying to complete it", str), new Object[0]);
        }
        this.zzb.e(str, i2, j2);
        zzdbVar.f7825c.f7820d = 4;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object d(int i2, int i3) {
        zzq(i2).f7825c.f7820d = 5;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Object e(int i2) {
        zzdb zzq = zzq(i2);
        zzda zzdaVar = zzq.f7825c;
        if (zzbg.zzd(zzdaVar.f7820d)) {
            this.zzb.e(zzdaVar.f7817a, zzq.f7824b, zzdaVar.f7818b);
            zzda zzdaVar2 = zzq.f7825c;
            int i3 = zzdaVar2.f7820d;
            if (i3 == 5 || i3 == 6) {
                this.zzb.f(zzdaVar2.f7817a, zzq.f7824b, zzdaVar2.f7818b);
                return null;
            }
            return null;
        }
        throw new zzck(String.format("Could not safely delete session %d because it is not in a terminal state.", Integer.valueOf(i2)), i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map f(final List list) {
        return (Map) zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzcw
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                return zzde.this.h(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map g() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Map h(List list) {
        int i2;
        Map zzu = zzu(list);
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            final zzdb zzdbVar = (zzdb) zzu.get(str);
            if (zzdbVar == null) {
                i2 = 8;
            } else {
                zzda zzdaVar = zzdbVar.f7825c;
                if (zzbg.zza(zzdaVar.f7820d)) {
                    try {
                        zzdaVar.f7820d = 6;
                        ((Executor) this.zze.zza()).execute(new Runnable() { // from class: com.google.android.play.core.assetpacks.zzcy
                            @Override // java.lang.Runnable
                            public final void run() {
                                zzde.this.n(zzdbVar.f7823a);
                            }
                        });
                        this.zzd.c(str);
                    } catch (zzck unused) {
                        zza.zzd("Session %d with pack %s does not exist, no need to cancel.", Integer.valueOf(zzdbVar.f7823a), str);
                    }
                }
                i2 = zzdbVar.f7825c.f7820d;
            }
            hashMap.put(str, Integer.valueOf(i2));
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Map i(List list) {
        HashMap hashMap = new HashMap();
        for (zzdb zzdbVar : this.zzf.values()) {
            String str = zzdbVar.f7825c.f7817a;
            if (list.contains(str)) {
                zzdb zzdbVar2 = (zzdb) hashMap.get(str);
                if ((zzdbVar2 == null ? -1 : zzdbVar2.f7823a) < zzdbVar.f7823a) {
                    hashMap.put(str, zzdbVar);
                }
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void j() {
        this.zzg.lock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void k(final String str, final int i2, final long j2) {
        zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzcv
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                zzde.this.c(str, i2, j2);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void l() {
        this.zzg.unlock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void m(final int i2, @AssetPackStatus int i3) {
        zzr(new zzdd(i2, 5) { // from class: com.google.android.play.core.assetpacks.zzcs
            public final /* synthetic */ int zzb;

            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                zzde.this.d(this.zzb, 5);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void n(final int i2) {
        zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzcr
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                zzde.this.e(i2);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean o(final Bundle bundle) {
        return ((Boolean) zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzct
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                return zzde.this.a(bundle);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean p(final Bundle bundle) {
        return ((Boolean) zzr(new zzdd() { // from class: com.google.android.play.core.assetpacks.zzcu
            @Override // com.google.android.play.core.assetpacks.zzdd
            public final Object zza() {
                return zzde.this.b(bundle);
            }
        })).booleanValue();
    }
}
