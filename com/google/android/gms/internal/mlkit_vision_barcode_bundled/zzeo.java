package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
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
/* loaded from: classes2.dex */
public final class zzeo {
    public static final zzeo zza;
    public static final zzeo zzb;
    public static final zzeo zzc;
    public static final zzeo zzd;
    public static final zzeo zze;
    public static final zzeo zzf;
    public static final zzeo zzg;
    public static final zzeo zzh;
    public static final zzeo zzi;
    public static final zzeo zzj;
    private static final /* synthetic */ zzeo[] zzk;
    private final Class zzl;
    private final Class zzm;
    private final Object zzn;

    static {
        zzeo zzeoVar = new zzeo("VOID", 0, Void.class, Void.class, null);
        zza = zzeoVar;
        Class cls = Integer.TYPE;
        zzeo zzeoVar2 = new zzeo("INT", 1, cls, Integer.class, 0);
        zzb = zzeoVar2;
        zzeo zzeoVar3 = new zzeo("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = zzeoVar3;
        zzeo zzeoVar4 = new zzeo("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzd = zzeoVar4;
        zzeo zzeoVar5 = new zzeo("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zze = zzeoVar5;
        zzeo zzeoVar6 = new zzeo("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzf = zzeoVar6;
        zzeo zzeoVar7 = new zzeo("STRING", 6, String.class, String.class, "");
        zzg = zzeoVar7;
        zzeo zzeoVar8 = new zzeo("BYTE_STRING", 7, zzdb.class, zzdb.class, zzdb.zzb);
        zzh = zzeoVar8;
        zzeo zzeoVar9 = new zzeo("ENUM", 8, cls, Integer.class, null);
        zzi = zzeoVar9;
        zzeo zzeoVar10 = new zzeo("MESSAGE", 9, Object.class, Object.class, null);
        zzj = zzeoVar10;
        zzk = new zzeo[]{zzeoVar, zzeoVar2, zzeoVar3, zzeoVar4, zzeoVar5, zzeoVar6, zzeoVar7, zzeoVar8, zzeoVar9, zzeoVar10};
    }

    private zzeo(String str, int i2, Class cls, Class cls2, Object obj) {
        this.zzl = cls;
        this.zzm = cls2;
        this.zzn = obj;
    }

    public static zzeo[] values() {
        return (zzeo[]) zzk.clone();
    }

    public final Class zza() {
        return this.zzm;
    }
}
