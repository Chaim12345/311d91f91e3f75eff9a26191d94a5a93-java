package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzfc extends zzkc implements zzlk {
    private static final zzfc zza;
    private int zze;
    private String zzf = "";
    private boolean zzg;
    private boolean zzh;
    private int zzi;

    static {
        zzfc zzfcVar = new zzfc();
        zza = zzfcVar;
        zzkc.m(zzfc.class, zzfcVar);
    }

    private zzfc() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void p(zzfc zzfcVar, String str) {
        str.getClass();
        zzfcVar.zze |= 1;
        zzfcVar.zzf = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzkc
    public final Object n(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            return null;
                        }
                        return zza;
                    }
                    return new zzfb(null);
                }
                return new zzfc();
            }
            return zzkc.l(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        }
        return (byte) 1;
    }

    public final int zza() {
        return this.zzi;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final boolean zze() {
        return this.zzg;
    }

    public final boolean zzf() {
        return this.zzh;
    }

    public final boolean zzg() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzh() {
        return (this.zze & 4) != 0;
    }

    public final boolean zzi() {
        return (this.zze & 8) != 0;
    }
}
