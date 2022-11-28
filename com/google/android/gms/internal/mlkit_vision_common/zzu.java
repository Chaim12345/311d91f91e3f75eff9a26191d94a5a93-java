package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
/* loaded from: classes2.dex */
final class zzu extends zzp {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzv f6582a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(zzv zzvVar) {
        this.f6582a = zzvVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i2) {
        int i3;
        Object[] objArr;
        Object[] objArr2;
        i3 = this.f6582a.zzc;
        zzf.zza(i2, i3, FirebaseAnalytics.Param.INDEX);
        zzv zzvVar = this.f6582a;
        int i4 = i2 + i2;
        objArr = zzvVar.zzb;
        Object obj = objArr[i4];
        obj.getClass();
        objArr2 = zzvVar.zzb;
        Object obj2 = objArr2[i4 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i2;
        i2 = this.f6582a.zzc;
        return i2;
    }
}
