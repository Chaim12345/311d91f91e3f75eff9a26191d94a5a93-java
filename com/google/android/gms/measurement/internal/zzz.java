package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzoi;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzz extends zzy {

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ zzaa f7064g;
    private final com.google.android.gms.internal.measurement.zzes zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzz(zzaa zzaaVar, String str, int i2, com.google.android.gms.internal.measurement.zzes zzesVar) {
        super(str, i2);
        this.f7064g = zzaaVar;
        this.zzh = zzesVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.measurement.internal.zzy
    public final int a() {
        return this.zzh.zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.measurement.internal.zzy
    public final boolean b() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.measurement.internal.zzy
    public final boolean c() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean j(Long l2, Long l3, com.google.android.gms.internal.measurement.zzgl zzglVar, boolean z) {
        zzey zzk;
        String f2;
        String str;
        Boolean e2;
        zzoi.zzc();
        boolean zzs = this.f7064g.f6809a.zzf().zzs(this.f7058a, zzen.zzV);
        boolean zzg = this.zzh.zzg();
        boolean zzh = this.zzh.zzh();
        boolean zzi = this.zzh.zzi();
        Object[] objArr = (zzg || zzh || zzi) ? 1 : null;
        Boolean bool = null;
        bool = null;
        if (z && objArr == null) {
            this.f7064g.f6809a.zzay().zzj().zzc("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.f7059b), this.zzh.zzj() ? Integer.valueOf(this.zzh.zza()) : null);
            return true;
        }
        com.google.android.gms.internal.measurement.zzel zzb = this.zzh.zzb();
        boolean zzg2 = zzb.zzg();
        if (zzglVar.zzr()) {
            if (zzb.zzi()) {
                e2 = zzy.g(zzglVar.zzb(), zzb.zzc());
                bool = zzy.i(e2, zzg2);
            } else {
                zzk = this.f7064g.f6809a.zzay().zzk();
                f2 = this.f7064g.f6809a.zzj().f(zzglVar.zzf());
                str = "No number filter for long property. property";
                zzk.zzb(str, f2);
            }
        } else if (!zzglVar.zzq()) {
            if (zzglVar.zzt()) {
                if (zzb.zzk()) {
                    e2 = zzy.e(zzglVar.zzg(), zzb.zzd(), this.f7064g.f6809a.zzay());
                } else if (!zzb.zzi()) {
                    zzk = this.f7064g.f6809a.zzay().zzk();
                    f2 = this.f7064g.f6809a.zzj().f(zzglVar.zzf());
                    str = "No string or number filter defined. property";
                } else if (zzln.x(zzglVar.zzg())) {
                    e2 = zzy.h(zzglVar.zzg(), zzb.zzc());
                } else {
                    this.f7064g.f6809a.zzay().zzk().zzc("Invalid user property value for Numeric number filter. property, value", this.f7064g.f6809a.zzj().f(zzglVar.zzf()), zzglVar.zzg());
                }
                bool = zzy.i(e2, zzg2);
            } else {
                zzk = this.f7064g.f6809a.zzay().zzk();
                f2 = this.f7064g.f6809a.zzj().f(zzglVar.zzf());
                str = "User property has no value, property";
            }
            zzk.zzb(str, f2);
        } else if (zzb.zzi()) {
            e2 = zzy.f(zzglVar.zza(), zzb.zzc());
            bool = zzy.i(e2, zzg2);
        } else {
            zzk = this.f7064g.f6809a.zzay().zzk();
            f2 = this.f7064g.f6809a.zzj().f(zzglVar.zzf());
            str = "No number filter for double property. property";
            zzk.zzb(str, f2);
        }
        this.f7064g.f6809a.zzay().zzj().zzb("Property filter result", bool == null ? "null" : bool);
        if (bool == null) {
            return false;
        }
        this.f7060c = Boolean.TRUE;
        if (!zzi || bool.booleanValue()) {
            if (!z || this.zzh.zzg()) {
                this.f7061d = bool;
            }
            if (bool.booleanValue() && objArr != null && zzglVar.zzs()) {
                long zzc = zzglVar.zzc();
                if (l2 != null) {
                    zzc = l2.longValue();
                }
                if (zzs && this.zzh.zzg() && !this.zzh.zzh() && l3 != null) {
                    zzc = l3.longValue();
                }
                if (this.zzh.zzh()) {
                    this.f7063f = Long.valueOf(zzc);
                } else {
                    this.f7062e = Long.valueOf(zzc);
                }
            }
            return true;
        }
        return true;
    }
}
