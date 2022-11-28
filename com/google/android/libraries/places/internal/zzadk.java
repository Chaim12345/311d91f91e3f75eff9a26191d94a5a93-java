package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzadh;
import com.google.android.libraries.places.internal.zzadk;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public abstract class zzadk<MessageType extends zzadk<MessageType, BuilderType>, BuilderType extends zzadh<MessageType, BuilderType>> extends zzacc<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzafu zzc = zzafu.zzc();
    protected int zzd = -1;

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzadq zzA() {
        return zzaeg.zze();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzadr zzB() {
        return zzafa.zzd();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzadr zzC(zzadr zzadrVar) {
        int size = zzadrVar.size();
        return zzadrVar.zzf(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzE(Method method, Object obj, Object... objArr) {
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
    public static Object zzF(zzaer zzaerVar, String str, Object[] objArr) {
        return new zzafb(zzaerVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void zzG(Class cls, zzadk zzadkVar) {
        zzb.put(cls, zzadkVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzadk zzy(Class cls) {
        Map map = zzb;
        zzadk zzadkVar = (zzadk) map.get(cls);
        if (zzadkVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzadkVar = (zzadk) map.get(cls);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException("Class initialization cannot fail.", e2);
            }
        }
        if (zzadkVar == null) {
            zzadkVar = (zzadk) ((zzadk) zzagd.zze(cls)).zzb(6, null, null);
            if (zzadkVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzadkVar);
        }
        return zzadkVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzado zzz() {
        return zzadl.zze();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzaez.zza().zzb(getClass()).zze(this, (zzadk) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i2 = this.zza;
        if (i2 != 0) {
            return i2;
        }
        int zzb2 = zzaez.zza().zzb(getClass()).zzb(this);
        this.zza = zzb2;
        return zzb2;
    }

    public final String toString() {
        return zzaet.zza(this, super.toString());
    }

    @Override // com.google.android.libraries.places.internal.zzaer
    public final /* synthetic */ zzaeq zzD() {
        zzadh zzadhVar = (zzadh) zzb(5, null, null);
        zzadhVar.zzs(this);
        return zzadhVar;
    }

    @Override // com.google.android.libraries.places.internal.zzaer
    public final void zzH(zzacx zzacxVar) {
        zzaez.zza().zzb(getClass()).zzi(this, zzacy.zza(zzacxVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zzb(int i2, Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzacc
    public final int zzr() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzacc
    public final void zzu(int i2) {
        this.zzd = i2;
    }

    @Override // com.google.android.libraries.places.internal.zzaer
    public final int zzv() {
        int i2 = this.zzd;
        if (i2 == -1) {
            int zza = zzaez.zza().zzb(getClass()).zza(this);
            this.zzd = zza;
            return zza;
        }
        return i2;
    }

    @Override // com.google.android.libraries.places.internal.zzaes
    public final /* synthetic */ zzaer zzw() {
        return (zzadk) zzb(6, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzadh zzx() {
        return (zzadh) zzb(5, null, null);
    }
}
