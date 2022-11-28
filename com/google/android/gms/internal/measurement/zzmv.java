package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* loaded from: classes.dex */
final class zzmv {

    /* renamed from: a  reason: collision with root package name */
    static final long f6112a;

    /* renamed from: b  reason: collision with root package name */
    static final boolean f6113b;
    private static final Unsafe zzc;
    private static final Class zzd;
    private static final boolean zze;
    private static final zzmu zzf;
    private static final boolean zzg;
    private static final boolean zzh;

    /* JADX WARN: Removed duplicated region for block: B:22:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0133  */
    static {
        boolean z;
        zzmu zzmuVar;
        boolean z2;
        Field zzB;
        zzmu zzmuVar2;
        Unsafe g2 = g();
        zzc = g2;
        zzd = zzin.a();
        Class<?> cls = Long.TYPE;
        boolean v = v(cls);
        zze = v;
        boolean v2 = v(Integer.TYPE);
        zzmu zzmuVar3 = null;
        if (g2 != null) {
            if (v) {
                zzmuVar3 = new zzmt(g2);
            } else if (v2) {
                zzmuVar3 = new zzms(g2);
            }
        }
        zzf = zzmuVar3;
        if (zzmuVar3 != null) {
            try {
                Class<?> cls2 = zzmuVar3.f6111a.getClass();
                cls2.getMethod("objectFieldOffset", Field.class);
                cls2.getMethod("getLong", Object.class, cls);
            } catch (Throwable th) {
                h(th);
            }
            if (zzB() != null) {
                z = true;
                zzg = z;
                zzmuVar = zzf;
                if (zzmuVar != null) {
                    try {
                        Class<?> cls3 = zzmuVar.f6111a.getClass();
                        cls3.getMethod("objectFieldOffset", Field.class);
                        cls3.getMethod("arrayBaseOffset", Class.class);
                        cls3.getMethod("arrayIndexScale", Class.class);
                        Class<?> cls4 = Long.TYPE;
                        cls3.getMethod("getInt", Object.class, cls4);
                        cls3.getMethod("putInt", Object.class, cls4, Integer.TYPE);
                        cls3.getMethod("getLong", Object.class, cls4);
                        cls3.getMethod("putLong", Object.class, cls4, cls4);
                        cls3.getMethod("getObject", Object.class, cls4);
                        cls3.getMethod("putObject", Object.class, cls4, Object.class);
                        z2 = true;
                    } catch (Throwable th2) {
                        h(th2);
                    }
                    zzh = z2;
                    f6112a = zzz(byte[].class);
                    zzz(boolean[].class);
                    zzA(boolean[].class);
                    zzz(int[].class);
                    zzA(int[].class);
                    zzz(long[].class);
                    zzA(long[].class);
                    zzz(float[].class);
                    zzA(float[].class);
                    zzz(double[].class);
                    zzA(double[].class);
                    zzz(Object[].class);
                    zzA(Object[].class);
                    zzB = zzB();
                    if (zzB != null && (zzmuVar2 = zzf) != null) {
                        zzmuVar2.zzl(zzB);
                    }
                    f6113b = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
                }
                z2 = false;
                zzh = z2;
                f6112a = zzz(byte[].class);
                zzz(boolean[].class);
                zzA(boolean[].class);
                zzz(int[].class);
                zzA(int[].class);
                zzz(long[].class);
                zzA(long[].class);
                zzz(float[].class);
                zzA(float[].class);
                zzz(double[].class);
                zzA(double[].class);
                zzz(Object[].class);
                zzA(Object[].class);
                zzB = zzB();
                if (zzB != null) {
                    zzmuVar2.zzl(zzB);
                }
                f6113b = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
            }
        }
        z = false;
        zzg = z;
        zzmuVar = zzf;
        if (zzmuVar != null) {
        }
        z2 = false;
        zzh = z2;
        f6112a = zzz(byte[].class);
        zzz(boolean[].class);
        zzA(boolean[].class);
        zzz(int[].class);
        zzA(int[].class);
        zzz(long[].class);
        zzA(long[].class);
        zzz(float[].class);
        zzA(float[].class);
        zzz(double[].class);
        zzA(double[].class);
        zzz(Object[].class);
        zzA(Object[].class);
        zzB = zzB();
        if (zzB != null) {
        }
        f6113b = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    private zzmv() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double a(Object obj, long j2) {
        return zzf.zza(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float b(Object obj, long j2) {
        return zzf.zzb(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(Object obj, long j2) {
        return zzf.zzj(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long d(Object obj, long j2) {
        return zzf.zzk(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object e(Class cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object f(Object obj, long j2) {
        return zzf.zzm(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Unsafe g() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzmr());
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* bridge */ /* synthetic */ void h(Throwable th) {
        Logger.getLogger(zzmv.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void m(Object obj, long j2, boolean z) {
        zzf.zzc(obj, j2, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void n(byte[] bArr, long j2, byte b2) {
        zzf.zzd(bArr, f6112a + j2, b2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void o(Object obj, long j2, double d2) {
        zzf.zze(obj, j2, d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void p(Object obj, long j2, float f2) {
        zzf.zzf(obj, j2, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void q(Object obj, long j2, int i2) {
        zzf.zzn(obj, j2, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void r(Object obj, long j2, long j3) {
        zzf.zzo(obj, j2, j3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void s(Object obj, long j2, Object obj2) {
        zzf.zzp(obj, j2, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean t(Object obj, long j2) {
        return ((byte) ((zzf.zzj(obj, (-4) & j2) >>> ((int) (((~j2) & 3) << 3))) & 255)) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean u(Object obj, long j2) {
        return ((byte) ((zzf.zzj(obj, (-4) & j2) >>> ((int) ((j2 & 3) << 3))) & 255)) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static boolean v(Class cls) {
        int i2 = zzin.zza;
        try {
            Class cls2 = zzd;
            Class cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class cls4 = Integer.TYPE;
            cls2.getMethod("pokeInt", cls, cls4, cls3);
            cls2.getMethod("peekInt", cls, cls3);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls4, cls4);
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean w(Object obj, long j2) {
        return zzf.zzg(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean x() {
        return zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean y() {
        return zzg;
    }

    private static int zzA(Class cls) {
        if (zzh) {
            return zzf.zzi(cls);
        }
        return -1;
    }

    private static Field zzB() {
        int i2 = zzin.zza;
        Field zzC = zzC(Buffer.class, "effectiveDirectAddress");
        if (zzC == null) {
            Field zzC2 = zzC(Buffer.class, "address");
            if (zzC2 == null || zzC2.getType() != Long.TYPE) {
                return null;
            }
            return zzC2;
        }
        return zzC;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzD(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        zzmu zzmuVar = zzf;
        int zzj = zzmuVar.zzj(obj, j3);
        int i2 = ((~((int) j2)) & 3) << 3;
        zzmuVar.zzn(obj, j3, ((255 & b2) << i2) | (zzj & (~(255 << i2))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzE(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        zzmu zzmuVar = zzf;
        int i2 = (((int) j2) & 3) << 3;
        zzmuVar.zzn(obj, j3, ((255 & b2) << i2) | (zzmuVar.zzj(obj, j3) & (~(255 << i2))));
    }

    private static int zzz(Class cls) {
        if (zzh) {
            return zzf.zzh(cls);
        }
        return -1;
    }
}
