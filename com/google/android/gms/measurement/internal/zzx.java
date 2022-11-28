package com.google.android.gms.measurement.internal;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzoi;
import java.util.HashSet;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzx extends zzy {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ zzaa f7057g;
    private final com.google.android.gms.internal.measurement.zzej zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzx(zzaa zzaaVar, String str, int i2, com.google.android.gms.internal.measurement.zzej zzejVar) {
        super(str, i2);
        this.f7057g = zzaaVar;
        this.zzh = zzejVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.measurement.internal.zzy
    public final int a() {
        return this.zzh.zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.measurement.internal.zzy
    public final boolean b() {
        return this.zzh.zzo();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.measurement.internal.zzy
    public final boolean c() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x010f, code lost:
        if (r2.booleanValue() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0111, code lost:
        r5 = java.lang.Boolean.FALSE;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x03d3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03d4  */
    /* JADX WARN: Type inference failed for: r11v10, types: [java.lang.Double] */
    /* JADX WARN: Type inference failed for: r11v13, types: [java.lang.Long] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean j(Long l2, Long l3, com.google.android.gms.internal.measurement.zzfs zzfsVar, long j2, zzas zzasVar, boolean z) {
        zzey zzk;
        String d2;
        String str;
        zzey zzk2;
        String d3;
        String e2;
        String str2;
        Boolean h2;
        String zzg;
        String str3;
        zzoi.zzc();
        boolean zzs = this.f7057g.f6809a.zzf().zzs(this.f7058a, zzen.zzX);
        long j3 = this.zzh.zzn() ? zzasVar.f6702e : j2;
        r5 = null;
        r5 = null;
        r5 = null;
        r5 = null;
        r5 = null;
        Boolean bool = null;
        if (Log.isLoggable(this.f7057g.f6809a.zzay().zzq(), 2)) {
            this.f7057g.f6809a.zzay().zzj().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(this.f7059b), this.zzh.zzp() ? Integer.valueOf(this.zzh.zzb()) : null, this.f7057g.f6809a.zzj().d(this.zzh.zzg()));
            this.f7057g.f6809a.zzay().zzj().zzb("Filter definition", this.f7057g.f7018b.zzu().o(this.zzh));
        }
        if (!this.zzh.zzp() || this.zzh.zzb() > 256) {
            this.f7057g.f6809a.zzay().zzk().zzc("Invalid event filter ID. appId, id", zzfa.g(this.f7058a), String.valueOf(this.zzh.zzp() ? Integer.valueOf(this.zzh.zzb()) : null));
            return false;
        }
        boolean z2 = this.zzh.zzk() || this.zzh.zzm() || this.zzh.zzn();
        if (z && !z2) {
            this.f7057g.f6809a.zzay().zzj().zzc("Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.f7059b), this.zzh.zzp() ? Integer.valueOf(this.zzh.zzb()) : null);
            return true;
        }
        com.google.android.gms.internal.measurement.zzej zzejVar = this.zzh;
        String zzh = zzfsVar.zzh();
        if (zzejVar.zzo()) {
            Boolean g2 = zzy.g(j3, zzejVar.zzf());
            if (g2 != null) {
            }
            this.f7057g.f6809a.zzay().zzj().zzb("Event filter result", bool != null ? "null" : bool);
            if (bool != null) {
                return false;
            }
            Boolean bool2 = Boolean.TRUE;
            this.f7060c = bool2;
            if (bool.booleanValue()) {
                this.f7061d = bool2;
                if (z2 && zzfsVar.zzu()) {
                    Long valueOf = Long.valueOf(zzfsVar.zzd());
                    if (this.zzh.zzm()) {
                        if (zzs && this.zzh.zzo()) {
                            valueOf = l2;
                        }
                        this.f7063f = valueOf;
                    } else {
                        if (zzs && this.zzh.zzo()) {
                            valueOf = l3;
                        }
                        this.f7062e = valueOf;
                    }
                }
                return true;
            }
            return true;
        }
        HashSet hashSet = new HashSet();
        Iterator it = zzejVar.zzh().iterator();
        while (true) {
            if (it.hasNext()) {
                com.google.android.gms.internal.measurement.zzel zzelVar = (com.google.android.gms.internal.measurement.zzel) it.next();
                if (zzelVar.zze().isEmpty()) {
                    zzk = this.f7057g.f6809a.zzay().zzk();
                    d2 = this.f7057g.f6809a.zzj().d(zzh);
                    str = "null or empty param name in filter. event";
                    break;
                }
                hashSet.add(zzelVar.zze());
            } else {
                ArrayMap arrayMap = new ArrayMap();
                Iterator it2 = zzfsVar.zzi().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        Iterator it3 = zzejVar.zzh().iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                bool = Boolean.TRUE;
                                break;
                            }
                            com.google.android.gms.internal.measurement.zzel zzelVar2 = (com.google.android.gms.internal.measurement.zzel) it3.next();
                            boolean z3 = zzelVar2.zzh() && zzelVar2.zzg();
                            String zze = zzelVar2.zze();
                            if (zze.isEmpty()) {
                                zzk = this.f7057g.f6809a.zzay().zzk();
                                d2 = this.f7057g.f6809a.zzj().d(zzh);
                                str = "Event has empty param name. event";
                                break;
                            }
                            Object obj = arrayMap.get(zze);
                            if (obj instanceof Long) {
                                if (!zzelVar2.zzi()) {
                                    zzk2 = this.f7057g.f6809a.zzay().zzk();
                                    d3 = this.f7057g.f6809a.zzj().d(zzh);
                                    e2 = this.f7057g.f6809a.zzj().e(zze);
                                    str2 = "No number filter for long param. event, param";
                                    break;
                                }
                                Boolean g3 = zzy.g(((Long) obj).longValue(), zzelVar2.zzc());
                                if (g3 == null) {
                                    break;
                                } else if (g3.booleanValue() == z3) {
                                    break;
                                }
                            } else if (obj instanceof Double) {
                                if (!zzelVar2.zzi()) {
                                    zzk2 = this.f7057g.f6809a.zzay().zzk();
                                    d3 = this.f7057g.f6809a.zzj().d(zzh);
                                    e2 = this.f7057g.f6809a.zzj().e(zze);
                                    str2 = "No number filter for double param. event, param";
                                    break;
                                }
                                Boolean f2 = zzy.f(((Double) obj).doubleValue(), zzelVar2.zzc());
                                if (f2 == null) {
                                    break;
                                } else if (f2.booleanValue() == z3) {
                                    break;
                                }
                            } else if (obj instanceof String) {
                                if (!zzelVar2.zzk()) {
                                    if (!zzelVar2.zzi()) {
                                        zzk2 = this.f7057g.f6809a.zzay().zzk();
                                        d3 = this.f7057g.f6809a.zzj().d(zzh);
                                        e2 = this.f7057g.f6809a.zzj().e(zze);
                                        str2 = "No filter for String param. event, param";
                                        break;
                                    }
                                    String str4 = (String) obj;
                                    if (!zzln.x(str4)) {
                                        zzk2 = this.f7057g.f6809a.zzay().zzk();
                                        d3 = this.f7057g.f6809a.zzj().d(zzh);
                                        e2 = this.f7057g.f6809a.zzj().e(zze);
                                        str2 = "Invalid param value for number filter. event, param";
                                        break;
                                    }
                                    h2 = zzy.h(str4, zzelVar2.zzc());
                                } else {
                                    h2 = zzy.e((String) obj, zzelVar2.zzd(), this.f7057g.f6809a.zzay());
                                }
                                if (h2 == null) {
                                    break;
                                } else if (h2.booleanValue() == z3) {
                                    break;
                                }
                            } else {
                                zzfa zzay = this.f7057g.f6809a.zzay();
                                if (obj == null) {
                                    zzay.zzj().zzc("Missing param for filter. event, param", this.f7057g.f6809a.zzj().d(zzh), this.f7057g.f6809a.zzj().e(zze));
                                } else {
                                    zzk2 = zzay.zzk();
                                    d3 = this.f7057g.f6809a.zzj().d(zzh);
                                    e2 = this.f7057g.f6809a.zzj().e(zze);
                                    str2 = "Unknown param type. event, param";
                                }
                            }
                        }
                    } else {
                        com.google.android.gms.internal.measurement.zzfw zzfwVar = (com.google.android.gms.internal.measurement.zzfw) it2.next();
                        if (hashSet.contains(zzfwVar.zzg())) {
                            if (zzfwVar.zzw()) {
                                zzg = zzfwVar.zzg();
                                if (zzfwVar.zzw()) {
                                    str3 = Long.valueOf(zzfwVar.zzd());
                                    arrayMap.put(zzg, str3);
                                }
                                str3 = null;
                                arrayMap.put(zzg, str3);
                            } else {
                                if (!zzfwVar.zzu()) {
                                    if (!zzfwVar.zzy()) {
                                        zzk2 = this.f7057g.f6809a.zzay().zzk();
                                        d3 = this.f7057g.f6809a.zzj().d(zzh);
                                        e2 = this.f7057g.f6809a.zzj().e(zzfwVar.zzg());
                                        str2 = "Unknown value for param. event, param";
                                        break;
                                    }
                                    zzg = zzfwVar.zzg();
                                    str3 = zzfwVar.zzh();
                                } else {
                                    zzg = zzfwVar.zzg();
                                    if (zzfwVar.zzu()) {
                                        str3 = Double.valueOf(zzfwVar.zza());
                                    }
                                    str3 = null;
                                }
                                arrayMap.put(zzg, str3);
                            }
                        }
                    }
                }
                zzk2.zzc(str2, d3, e2);
            }
        }
        zzk.zzb(str, d2);
        this.f7057g.f6809a.zzay().zzj().zzb("Event filter result", bool != null ? "null" : bool);
        if (bool != null) {
        }
    }
}
