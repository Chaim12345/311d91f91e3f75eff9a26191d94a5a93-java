package com.google.android.play.core.assetpacks;

import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzdh {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("ExtractorTaskFinder");
    private final zzde zzb;
    private final zzbh zzc;
    private final zzbu zzd;
    private final com.google.android.play.core.common.zza zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdh(zzde zzdeVar, zzbh zzbhVar, zzbu zzbuVar, com.google.android.play.core.common.zza zzaVar) {
        this.zzb = zzdeVar;
        this.zzc = zzbhVar;
        this.zzd = zzbuVar;
        this.zze = zzaVar;
    }

    private final boolean zzb(zzdb zzdbVar, zzdc zzdcVar) {
        zzbh zzbhVar = this.zzc;
        zzda zzdaVar = zzdbVar.f7825c;
        return new zzen(zzbhVar, zzdaVar.f7817a, zzdbVar.f7824b, zzdaVar.f7818b, zzdcVar.f7826a).m();
    }

    private static boolean zzc(zzdc zzdcVar) {
        int i2 = zzdcVar.f7831f;
        return i2 == 1 || i2 == 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00ae, code lost:
        if (r0 == null) goto L40;
     */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzdg a() {
        zzdg zzdgVar;
        int i2;
        try {
            this.zzb.j();
            ArrayList arrayList = new ArrayList();
            for (zzdb zzdbVar : this.zzb.g().values()) {
                if (zzbg.zzb(zzdbVar.f7825c.f7820d)) {
                    arrayList.add(zzdbVar);
                }
            }
            zzce zzceVar = null;
            if (!arrayList.isEmpty()) {
                if (this.zze.zza("assetOnlyUpdates")) {
                    Map A = this.zzc.A();
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            zzdgVar = null;
                            break;
                        }
                        zzdb zzdbVar2 = (zzdb) it.next();
                        Long l2 = (Long) A.get(zzdbVar2.f7825c.f7817a);
                        if (l2 != null && zzdbVar2.f7825c.f7818b == l2.longValue()) {
                            zza.zza("Found promote pack task for session %s with pack %s.", Integer.valueOf(zzdbVar2.f7823a), zzdbVar2.f7825c.f7817a);
                            int i3 = zzdbVar2.f7823a;
                            String str = zzdbVar2.f7825c.f7817a;
                            zzdgVar = new zzei(i3, str, this.zzc.h(str), zzdbVar2.f7824b, zzdbVar2.f7825c.f7818b);
                            break;
                        }
                    }
                }
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        zzdgVar = null;
                        break;
                    }
                    zzdb zzdbVar3 = (zzdb) it2.next();
                    try {
                        zzbh zzbhVar = this.zzc;
                        zzda zzdaVar = zzdbVar3.f7825c;
                        if (zzbhVar.i(zzdaVar.f7817a, zzdbVar3.f7824b, zzdaVar.f7818b) == zzdbVar3.f7825c.f7822f.size()) {
                            zza.zza("Found final move task for session %s with pack %s.", Integer.valueOf(zzdbVar3.f7823a), zzdbVar3.f7825c.f7817a);
                            int i4 = zzdbVar3.f7823a;
                            zzda zzdaVar2 = zzdbVar3.f7825c;
                            zzdgVar = new zzdw(i4, zzdaVar2.f7817a, zzdbVar3.f7824b, zzdaVar2.f7818b, zzdaVar2.f7819c);
                            break;
                        }
                    } catch (IOException e2) {
                        throw new zzck(String.format("Failed to check number of completed merges for session %s, pack %s", Integer.valueOf(zzdbVar3.f7823a), zzdbVar3.f7825c.f7817a), e2, zzdbVar3.f7823a);
                    }
                }
                if (zzdgVar == null) {
                    Iterator it3 = arrayList.iterator();
                    loop3: while (true) {
                        if (!it3.hasNext()) {
                            zzdgVar = null;
                            break;
                        }
                        zzdb zzdbVar4 = (zzdb) it3.next();
                        zzda zzdaVar3 = zzdbVar4.f7825c;
                        if (zzbg.zzb(zzdaVar3.f7820d)) {
                            for (zzdc zzdcVar : zzdaVar3.f7822f) {
                                zzbh zzbhVar2 = this.zzc;
                                zzda zzdaVar4 = zzdbVar4.f7825c;
                                if (zzbhVar2.x(zzdaVar4.f7817a, zzdbVar4.f7824b, zzdaVar4.f7818b, zzdcVar.f7826a).exists()) {
                                    zza.zza("Found merge task for session %s with pack %s and slice %s.", Integer.valueOf(zzdbVar4.f7823a), zzdbVar4.f7825c.f7817a, zzdcVar.f7826a);
                                    int i5 = zzdbVar4.f7823a;
                                    zzda zzdaVar5 = zzdbVar4.f7825c;
                                    zzdgVar = new zzdt(i5, zzdaVar5.f7817a, zzdbVar4.f7824b, zzdaVar5.f7818b, zzdcVar.f7826a);
                                    break loop3;
                                }
                            }
                            continue;
                        }
                    }
                    if (zzdgVar == null) {
                        Iterator it4 = arrayList.iterator();
                        loop5: while (true) {
                            if (!it4.hasNext()) {
                                zzdgVar = null;
                                break;
                            }
                            zzdb zzdbVar5 = (zzdb) it4.next();
                            zzda zzdaVar6 = zzdbVar5.f7825c;
                            if (zzbg.zzb(zzdaVar6.f7820d)) {
                                for (zzdc zzdcVar2 : zzdaVar6.f7822f) {
                                    if (zzb(zzdbVar5, zzdcVar2)) {
                                        zzbh zzbhVar3 = this.zzc;
                                        zzda zzdaVar7 = zzdbVar5.f7825c;
                                        if (zzbhVar3.w(zzdaVar7.f7817a, zzdbVar5.f7824b, zzdaVar7.f7818b, zzdcVar2.f7826a).exists()) {
                                            zza.zza("Found verify task for session %s with pack %s and slice %s.", Integer.valueOf(zzdbVar5.f7823a), zzdbVar5.f7825c.f7817a, zzdcVar2.f7826a);
                                            int i6 = zzdbVar5.f7823a;
                                            zzda zzdaVar8 = zzdbVar5.f7825c;
                                            zzdgVar = new zzeq(i6, zzdaVar8.f7817a, zzdbVar5.f7824b, zzdaVar8.f7818b, zzdcVar2.f7826a, zzdcVar2.f7827b, zzdcVar2.f7828c);
                                            break loop5;
                                        }
                                    }
                                }
                                continue;
                            }
                        }
                        if (zzdgVar == null) {
                            Iterator it5 = arrayList.iterator();
                            loop7: while (true) {
                                if (!it5.hasNext()) {
                                    zzceVar = null;
                                    break;
                                }
                                zzdb zzdbVar6 = (zzdb) it5.next();
                                zzda zzdaVar9 = zzdbVar6.f7825c;
                                if (zzbg.zzb(zzdaVar9.f7820d)) {
                                    Iterator it6 = zzdaVar9.f7822f.iterator();
                                    while (it6.hasNext()) {
                                        zzdc zzdcVar3 = (zzdc) it6.next();
                                        if (!zzc(zzdcVar3)) {
                                            zzbh zzbhVar4 = this.zzc;
                                            zzda zzdaVar10 = zzdbVar6.f7825c;
                                            Iterator it7 = it6;
                                            try {
                                                i2 = new zzen(zzbhVar4, zzdaVar10.f7817a, zzdbVar6.f7824b, zzdaVar10.f7818b, zzdcVar3.f7826a).a();
                                            } catch (IOException e3) {
                                                zza.zzb("Slice checkpoint corrupt, restarting extraction. %s", e3);
                                                i2 = 0;
                                            }
                                            if (i2 != -1 && ((zzcz) zzdcVar3.f7829d.get(i2)).f7816a) {
                                                zza.zza("Found extraction task using compression format %s for session %s, pack %s, slice %s, chunk %s.", Integer.valueOf(zzdcVar3.f7830e), Integer.valueOf(zzdbVar6.f7823a), zzdbVar6.f7825c.f7817a, zzdcVar3.f7826a, Integer.valueOf(i2));
                                                InputStream a2 = this.zzd.a(zzdbVar6.f7823a, zzdbVar6.f7825c.f7817a, zzdcVar3.f7826a, i2);
                                                int i7 = zzdbVar6.f7823a;
                                                zzda zzdaVar11 = zzdbVar6.f7825c;
                                                String str2 = zzdaVar11.f7817a;
                                                int i8 = zzdbVar6.f7824b;
                                                long j2 = zzdaVar11.f7818b;
                                                String str3 = zzdaVar11.f7819c;
                                                String str4 = zzdcVar3.f7826a;
                                                int i9 = zzdcVar3.f7830e;
                                                int size = zzdcVar3.f7829d.size();
                                                zzda zzdaVar12 = zzdbVar6.f7825c;
                                                zzceVar = new zzce(i7, str2, i8, j2, str3, str4, i9, i2, size, zzdaVar12.f7821e, zzdaVar12.f7820d, a2);
                                                break loop7;
                                            }
                                            it6 = it7;
                                        }
                                    }
                                    continue;
                                }
                            }
                            if (zzceVar == null) {
                                Iterator it8 = arrayList.iterator();
                                loop9: while (true) {
                                    if (!it8.hasNext()) {
                                        zzdgVar = null;
                                        break;
                                    }
                                    zzdb zzdbVar7 = (zzdb) it8.next();
                                    zzda zzdaVar13 = zzdbVar7.f7825c;
                                    if (zzbg.zzb(zzdaVar13.f7820d)) {
                                        for (zzdc zzdcVar4 : zzdaVar13.f7822f) {
                                            if (zzc(zzdcVar4) && ((zzcz) zzdcVar4.f7829d.get(0)).f7816a && !zzb(zzdbVar7, zzdcVar4)) {
                                                zza.zza("Found patch slice task using patch format %s for session %s, pack %s, slice %s.", Integer.valueOf(zzdcVar4.f7831f), Integer.valueOf(zzdbVar7.f7823a), zzdbVar7.f7825c.f7817a, zzdcVar4.f7826a);
                                                InputStream a3 = this.zzd.a(zzdbVar7.f7823a, zzdbVar7.f7825c.f7817a, zzdcVar4.f7826a, 0);
                                                int i10 = zzdbVar7.f7823a;
                                                String str5 = zzdbVar7.f7825c.f7817a;
                                                zzdgVar = new zzef(i10, str5, this.zzc.h(str5), this.zzc.j(zzdbVar7.f7825c.f7817a), zzdbVar7.f7824b, zzdbVar7.f7825c.f7818b, zzdcVar4.f7831f, zzdcVar4.f7826a, zzdcVar4.f7828c, a3);
                                                break loop9;
                                            }
                                        }
                                        continue;
                                    }
                                }
                                if (zzdgVar == null) {
                                    this.zzb.l();
                                    return null;
                                }
                            }
                        }
                    }
                }
                return zzdgVar;
            }
            return zzceVar;
        } finally {
            this.zzb.l();
        }
    }
}
