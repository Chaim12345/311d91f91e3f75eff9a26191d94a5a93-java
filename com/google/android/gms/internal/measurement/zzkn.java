package com.google.android.gms.internal.measurement;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzb uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public final class zzkn {
    public static final zzkn zza;
    public static final zzkn zzb;
    public static final zzkn zzc;
    public static final zzkn zzd;
    public static final zzkn zze;
    public static final zzkn zzf;
    public static final zzkn zzg;
    public static final zzkn zzh;
    public static final zzkn zzi;
    public static final zzkn zzj;
    private static final /* synthetic */ zzkn[] zzk;
    private final Class zzl;
    private final Class zzm;
    private final Object zzn;

    static {
        zzkn zzknVar = new zzkn("VOID", 0, Void.class, Void.class, null);
        zza = zzknVar;
        Class cls = Integer.TYPE;
        zzkn zzknVar2 = new zzkn("INT", 1, cls, Integer.class, 0);
        zzb = zzknVar2;
        zzkn zzknVar3 = new zzkn("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = zzknVar3;
        zzkn zzknVar4 = new zzkn("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzd = zzknVar4;
        zzkn zzknVar5 = new zzkn("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zze = zzknVar5;
        zzkn zzknVar6 = new zzkn("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzf = zzknVar6;
        zzkn zzknVar7 = new zzkn("STRING", 6, String.class, String.class, "");
        zzg = zzknVar7;
        zzkn zzknVar8 = new zzkn("BYTE_STRING", 7, zzjb.class, zzjb.class, zzjb.zzb);
        zzh = zzknVar8;
        zzkn zzknVar9 = new zzkn("ENUM", 8, cls, Integer.class, null);
        zzi = zzknVar9;
        zzkn zzknVar10 = new zzkn("MESSAGE", 9, Object.class, Object.class, null);
        zzj = zzknVar10;
        zzk = new zzkn[]{zzknVar, zzknVar2, zzknVar3, zzknVar4, zzknVar5, zzknVar6, zzknVar7, zzknVar8, zzknVar9, zzknVar10};
    }

    private zzkn(String str, int i2, Class cls, Class cls2, Object obj) {
        this.zzl = cls;
        this.zzm = cls2;
        this.zzn = obj;
    }

    public static zzkn[] values() {
        return (zzkn[]) zzk.clone();
    }

    public final Class zza() {
        return this.zzm;
    }
}
