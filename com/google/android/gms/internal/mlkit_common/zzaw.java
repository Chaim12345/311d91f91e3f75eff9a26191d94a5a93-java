package com.google.android.gms.internal.mlkit_common;

import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzaw extends zzao {

    /* renamed from: a  reason: collision with root package name */
    final transient Object[] f6129a;
    private final transient int zzc;

    static {
        new zzaw(null, new Object[0], 0);
    }

    private zzaw(@CheckForNull Object obj, Object[] objArr, int i2) {
        this.f6129a = objArr;
        this.zzc = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaw d(int i2, Object[] objArr, zzan zzanVar) {
        Object obj = objArr[0];
        obj.getClass();
        Object obj2 = objArr[1];
        obj2.getClass();
        zzae.a(obj, obj2);
        return new zzaw(null, objArr, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzao
    final zzai a() {
        return new zzav(this.f6129a, 1, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzao
    final zzap b() {
        return new zzat(this, this.f6129a, 0, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzao
    final zzap c() {
        return new zzau(this, new zzav(this.f6129a, 0, this.zzc));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0020 A[RETURN] */
    @Override // com.google.android.gms.internal.mlkit_common.zzao, java.util.Map
    @CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object get(@CheckForNull Object obj) {
        Object obj2;
        Object[] objArr = this.f6129a;
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
