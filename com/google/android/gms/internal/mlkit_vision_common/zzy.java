package com.google.android.gms.internal.mlkit_vision_common;

import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzy extends zzr {

    /* renamed from: a  reason: collision with root package name */
    final transient Object[] f6583a;
    private final transient int zzc;

    static {
        new zzy(null, new Object[0], 0);
    }

    private zzy(@CheckForNull Object obj, Object[] objArr, int i2) {
        this.f6583a = objArr;
        this.zzc = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzy d(int i2, Object[] objArr, zzq zzqVar) {
        Object obj = objArr[0];
        obj.getClass();
        Object obj2 = objArr[1];
        obj2.getClass();
        zzi.a(obj, obj2);
        return new zzy(null, objArr, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzr
    final zzl a() {
        return new zzx(this.f6583a, 1, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzr
    final zzs b() {
        return new zzv(this, this.f6583a, 0, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzr
    final zzs c() {
        return new zzw(this, new zzx(this.f6583a, 0, this.zzc));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0020 A[RETURN] */
    @Override // com.google.android.gms.internal.mlkit_vision_common.zzr, java.util.Map
    @CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object get(@CheckForNull Object obj) {
        Object obj2;
        Object[] objArr = this.f6583a;
        int i2 = this.zzc;
        if (obj != null && i2 == 1) {
            Object obj3 = objArr[0];
            obj3.getClass();
            if (obj3.equals(obj)) {
                obj2 = objArr[1];
                obj2.getClass();
                if (obj2 != null) {
                    return null;
                }
                return obj2;
            }
        }
        obj2 = null;
        if (obj2 != null) {
        }
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzc;
    }
}
