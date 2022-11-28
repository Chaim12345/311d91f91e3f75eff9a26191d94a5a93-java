package com.google.android.gms.internal.mlkit_common;

import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzm {
    private static final Method zza;
    private static final Method zzb;
    private static final Method zzc;
    private static final Field zzd;
    private static final Field zze;
    private static final Field zzf;
    private static final Object zzg;
    private static final Throwable zzh;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v15, types: [java.lang.reflect.Field] */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.reflect.Field] */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v9 */
    static {
        Field field;
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Field field2;
        Object obj;
        Throwable th = null;
        try {
            Class<?> cls = Class.forName("libcore.io.Libcore");
            Class<?> cls2 = Class.forName("libcore.io.StructStat");
            Class<?> cls3 = Class.forName("libcore.io.OsConstants");
            Class<?> cls4 = Class.forName("libcore.io.ForwardingOs");
            method = cls3.getDeclaredMethod("S_ISLNK", Integer.TYPE);
            try {
                method.setAccessible(true);
                method3 = cls4.getDeclaredMethod("lstat", String.class);
                try {
                    method2 = cls4.getDeclaredMethod("fstat", FileDescriptor.class);
                    try {
                        Field declaredField = cls.getDeclaredField("os");
                        declaredField.setAccessible(true);
                        obj = declaredField.get(cls);
                        try {
                            method4 = cls2.getField("st_dev");
                            try {
                                field2 = cls2.getField("st_ino");
                                try {
                                    field = cls2.getField("st_mode");
                                    try {
                                        method4.setAccessible(true);
                                        field2.setAccessible(true);
                                        field.setAccessible(true);
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                } catch (Throwable th3) {
                                    field = null;
                                    th = th3;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                field2 = null;
                                method4 = method4;
                                th = th;
                                field = field2;
                                zza = method;
                                zzb = method3;
                                zzc = method2;
                                zzd = method4;
                                zze = field2;
                                zzf = field;
                                zzg = obj;
                                zzh = th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            method4 = 0;
                            field2 = null;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        field = null;
                        method4 = 0;
                        field2 = method4;
                        th = th;
                        obj = field2;
                        zza = method;
                        zzb = method3;
                        zzc = method2;
                        zzd = method4;
                        zze = field2;
                        zzf = field;
                        zzg = obj;
                        zzh = th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    field = null;
                    method2 = null;
                    method4 = 0;
                }
            } catch (Throwable th8) {
                th = th8;
                field = null;
                method2 = null;
                method3 = method2;
                method4 = method3;
                field2 = method4;
                th = th;
                obj = field2;
                zza = method;
                zzb = method3;
                zzc = method2;
                zzd = method4;
                zze = field2;
                zzf = field;
                zzg = obj;
                zzh = th;
            }
        } catch (Throwable th9) {
            th = th9;
            field = null;
            method = null;
            method2 = null;
        }
        zza = method;
        zzb = method3;
        zzc = method2;
        zzd = method4;
        zze = field2;
        zzf = field;
        zzg = obj;
        zzh = th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzo a(final FileDescriptor fileDescriptor) {
        return (zzo) zzf(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzk
            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzo zze2;
                zze2 = zzm.zze(zzm.zzc.invoke(zzm.zzg, fileDescriptor));
                return zze2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzo d(final String str) {
        return (zzo) zzf(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzl
            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzo zze2;
                zze2 = zzm.zze(zzm.zzb.invoke(zzm.zzg, str));
                return zze2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzo zze(Object obj) {
        return new zzo(((Long) zzd.get(obj)).longValue(), ((Long) zze.get(obj)).longValue(), ((Boolean) zza.invoke(null, Integer.valueOf(((Integer) zzf.get(obj)).intValue()))).booleanValue());
    }

    private static Object zzf(Callable callable) {
        try {
            Throwable th = zzh;
            if (th == null) {
                return callable.call();
            }
            throw new IOException(th);
        } catch (Throwable th2) {
            throw new IOException(th2);
        }
    }
}
