package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public abstract class zzec<MessageType extends zzec<MessageType, BuilderType>, BuilderType extends zzdw<MessageType, BuilderType>> extends zzck<MessageType, BuilderType> {
    private static final Map zza = new ConcurrentHashMap();
    protected zzgq zzc = zzgq.zzc();
    protected int zzd = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzec d(Class cls) {
        Map map = zza;
        zzec zzecVar = (zzec) map.get(cls);
        if (zzecVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzecVar = (zzec) map.get(cls);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException("Class initialization cannot fail.", e2);
            }
        }
        if (zzecVar == null) {
            zzecVar = (zzec) ((zzec) zzgz.e(cls)).p(6, null, null);
            if (zzecVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzecVar);
        }
        return zzecVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzec e(zzec zzecVar, byte[] bArr, zzdn zzdnVar) {
        zzec f2 = f(zzecVar, bArr, 0, bArr.length, zzdnVar);
        if (f2 == null || f2.zzY()) {
            return f2;
        }
        zzen zzenVar = new zzen(new zzgo(f2).getMessage());
        zzenVar.zzf(f2);
        throw zzenVar;
    }

    static zzec f(zzec zzecVar, byte[] bArr, int i2, int i3, zzdn zzdnVar) {
        zzec zzecVar2 = (zzec) zzecVar.p(4, null, null);
        try {
            zzgb zzb = zzfu.zza().zzb(zzecVar2.getClass());
            zzb.zzh(zzecVar2, bArr, 0, i3, new zzco(zzdnVar));
            zzb.zzf(zzecVar2);
            if (zzecVar2.zzb == 0) {
                return zzecVar2;
            }
            throw new RuntimeException();
        } catch (zzen e2) {
            e2.zzf(zzecVar2);
            throw e2;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzen) {
                throw ((zzen) e3.getCause());
            }
            zzen zzenVar = new zzen(e3);
            zzenVar.zzf(zzecVar2);
            throw zzenVar;
        } catch (IndexOutOfBoundsException unused) {
            zzen f2 = zzen.f();
            f2.zzf(zzecVar2);
            throw f2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzeh g() {
        return zzdu.zze();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzeh h(zzeh zzehVar) {
        int size = zzehVar.size();
        return zzehVar.zzf(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzei i() {
        return zzed.zzf();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzek j() {
        return zzfv.zze();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzek k(zzek zzekVar) {
        int size = zzekVar.size();
        return zzekVar.zzd(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object l(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object m(zzfl zzflVar, String str, Object[] objArr) {
        return new zzfw(zzflVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void n(Class cls, zzec zzecVar) {
        zza.put(cls, zzecVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final boolean o(zzec zzecVar, boolean z) {
        byte byteValue = ((Byte) zzecVar.p(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzj = zzfu.zza().zzb(zzecVar.getClass()).zzj(zzecVar);
        if (z) {
            zzecVar.p(2, true != zzj ? null : zzecVar, null);
        }
        return zzj;
    }

    public static zzea zzH(zzfl zzflVar, Object obj, zzfl zzflVar2, zzef zzefVar, int i2, zzhf zzhfVar, Class cls) {
        return new zzea(zzflVar, obj, zzflVar2, new zzdz(null, i2, zzhfVar, false, false), cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzck
    public final int a() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzck
    public final void b(int i2) {
        this.zzd = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzdw c() {
        return (zzdw) p(5, null, null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzfu.zza().zzb(getClass()).zzi(this, (zzec) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i2 = this.zzb;
        if (i2 != 0) {
            return i2;
        }
        int zzb = zzfu.zza().zzb(getClass()).zzb(this);
        this.zzb = zzb;
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object p(int i2, Object obj, Object obj2);

    public final String toString() {
        return zzfn.a(this, super.toString());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl
    public final int zzE() {
        int i2 = this.zzd;
        if (i2 == -1) {
            int zza2 = zzfu.zza().zzb(getClass()).zza(this);
            this.zzd = zza2;
            return zza2;
        }
        return i2;
    }

    public final zzdw zzG() {
        zzdw zzdwVar = (zzdw) p(5, null, null);
        zzdwVar.zzi(this);
        return zzdwVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl
    public final /* synthetic */ zzfk zzU() {
        return (zzdw) p(5, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl
    public final /* synthetic */ zzfk zzV() {
        zzdw zzdwVar = (zzdw) p(5, null, null);
        zzdwVar.zzi(this);
        return zzdwVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl
    public final void zzW(zzdi zzdiVar) {
        zzfu.zza().zzb(getClass()).zzm(this, zzdj.zza(zzdiVar));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm
    public final /* synthetic */ zzfl zzX() {
        return (zzec) p(6, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm
    public final boolean zzY() {
        return o(this, true);
    }
}
