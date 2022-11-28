package com.google.android.libraries.places.internal;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzc uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes2.dex */
public final class zzagi {
    public static final zzagi zza;
    public static final zzagi zzb;
    public static final zzagi zzc;
    public static final zzagi zzd;
    public static final zzagi zze;
    public static final zzagi zzf;
    public static final zzagi zzg;
    public static final zzagi zzh;
    public static final zzagi zzi;
    public static final zzagi zzj;
    public static final zzagi zzk;
    public static final zzagi zzl;
    public static final zzagi zzm;
    public static final zzagi zzn;
    public static final zzagi zzo;
    public static final zzagi zzp;
    public static final zzagi zzq;
    public static final zzagi zzr;
    private static final /* synthetic */ zzagi[] zzs;
    private final zzagj zzt;

    static {
        zzagi zzagiVar = new zzagi("DOUBLE", 0, zzagj.DOUBLE, 1);
        zza = zzagiVar;
        zzagi zzagiVar2 = new zzagi("FLOAT", 1, zzagj.FLOAT, 5);
        zzb = zzagiVar2;
        zzagj zzagjVar = zzagj.LONG;
        zzagi zzagiVar3 = new zzagi("INT64", 2, zzagjVar, 0);
        zzc = zzagiVar3;
        zzagi zzagiVar4 = new zzagi("UINT64", 3, zzagjVar, 0);
        zzd = zzagiVar4;
        zzagj zzagjVar2 = zzagj.INT;
        zzagi zzagiVar5 = new zzagi("INT32", 4, zzagjVar2, 0);
        zze = zzagiVar5;
        zzagi zzagiVar6 = new zzagi("FIXED64", 5, zzagjVar, 1);
        zzf = zzagiVar6;
        zzagi zzagiVar7 = new zzagi("FIXED32", 6, zzagjVar2, 5);
        zzg = zzagiVar7;
        zzagi zzagiVar8 = new zzagi("BOOL", 7, zzagj.BOOLEAN, 0);
        zzh = zzagiVar8;
        zzagi zzagiVar9 = new zzagi("STRING", 8, zzagj.STRING, 2);
        zzi = zzagiVar9;
        zzagj zzagjVar3 = zzagj.MESSAGE;
        zzagi zzagiVar10 = new zzagi("GROUP", 9, zzagjVar3, 3);
        zzj = zzagiVar10;
        zzagi zzagiVar11 = new zzagi("MESSAGE", 10, zzagjVar3, 2);
        zzk = zzagiVar11;
        zzagi zzagiVar12 = new zzagi("BYTES", 11, zzagj.BYTE_STRING, 2);
        zzl = zzagiVar12;
        zzagi zzagiVar13 = new zzagi("UINT32", 12, zzagjVar2, 0);
        zzm = zzagiVar13;
        zzagi zzagiVar14 = new zzagi("ENUM", 13, zzagj.ENUM, 0);
        zzn = zzagiVar14;
        zzagi zzagiVar15 = new zzagi("SFIXED32", 14, zzagjVar2, 5);
        zzo = zzagiVar15;
        zzagi zzagiVar16 = new zzagi("SFIXED64", 15, zzagjVar, 1);
        zzp = zzagiVar16;
        zzagi zzagiVar17 = new zzagi("SINT32", 16, zzagjVar2, 0);
        zzq = zzagiVar17;
        zzagi zzagiVar18 = new zzagi("SINT64", 17, zzagjVar, 0);
        zzr = zzagiVar18;
        zzs = new zzagi[]{zzagiVar, zzagiVar2, zzagiVar3, zzagiVar4, zzagiVar5, zzagiVar6, zzagiVar7, zzagiVar8, zzagiVar9, zzagiVar10, zzagiVar11, zzagiVar12, zzagiVar13, zzagiVar14, zzagiVar15, zzagiVar16, zzagiVar17, zzagiVar18};
    }

    private zzagi(String str, int i2, zzagj zzagjVar, int i3) {
        this.zzt = zzagjVar;
    }

    public static zzagi[] values() {
        return (zzagi[]) zzs.clone();
    }

    public final zzagj zza() {
        return this.zzt;
    }
}
