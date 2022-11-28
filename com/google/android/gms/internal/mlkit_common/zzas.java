package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
/* loaded from: classes.dex */
final class zzas extends zzam {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzat f6128a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzas(zzat zzatVar) {
        this.f6128a = zzatVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i2) {
        int i3;
        Object[] objArr;
        Object[] objArr2;
        i3 = this.f6128a.zzc;
        zzab.zza(i2, i3, FirebaseAnalytics.Param.INDEX);
        zzat zzatVar = this.f6128a;
        int i4 = i2 + i2;
        objArr = zzatVar.zzb;
        Object obj = objArr[i4];
        obj.getClass();
        objArr2 = zzatVar.zzb;
        Object obj2 = objArr2[i4 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i2;
        i2 = this.f6128a.zzc;
        return i2;
    }
}
