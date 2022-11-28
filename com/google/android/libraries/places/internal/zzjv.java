package com.google.android.libraries.places.internal;

import android.os.Build;
import dalvik.system.VMStack;
/* loaded from: classes2.dex */
public final class zzjv extends zzjq {
    private static final boolean zza = zza.zza();
    private static final boolean zzb;
    private static final zzjp zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class zza {
        zza() {
        }

        static boolean zza() {
            return zzjv.zzt();
        }
    }

    static {
        String str = Build.FINGERPRINT;
        boolean z = true;
        if (str != null && !"robolectric".equals(str)) {
            z = false;
        }
        zzb = z;
        zzc = new zzjp() { // from class: com.google.android.libraries.places.internal.zzjv.1
            @Override // com.google.android.libraries.places.internal.zzjp
            public zziv zza(Class<?> cls, int i2) {
                return zziv.zza;
            }

            @Override // com.google.android.libraries.places.internal.zzjp
            public String zzb(Class cls) {
                StackTraceElement zza2;
                if (zzjv.zza) {
                    try {
                        if (cls.equals(zzjv.zzp())) {
                            return VMStack.getStackClass2().getName();
                        }
                    } catch (Throwable unused) {
                    }
                }
                if (!zzjv.zzb || (zza2 = zzks.zza(cls, 1)) == null) {
                    return null;
                }
                return zza2.getClassName();
            }
        };
    }

    static Class<?> zzp() {
        return VMStack.getStackClass2();
    }

    static String zzq() {
        try {
            return VMStack.getStackClass2().getName();
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zzt() {
        try {
            Class.forName("dalvik.system.VMStack").getMethod("getStackClass2", new Class[0]);
            return zza.class.getName().equals(zzq());
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzjq
    protected zzja zze(String str) {
        return zzjy.zzb(str);
    }

    @Override // com.google.android.libraries.places.internal.zzjq
    protected zzjp zzh() {
        return zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzjq
    protected zzke zzj() {
        return zzjz.zzb();
    }

    @Override // com.google.android.libraries.places.internal.zzjq
    protected String zzm() {
        return "platform: Android";
    }
}
