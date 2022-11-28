package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.AbstractMap;
/* loaded from: classes2.dex */
final class zzcs extends zzcc {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzct f6291a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcs(zzct zzctVar) {
        this.f6291a = zzctVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i2) {
        int i3;
        Object[] objArr;
        Object[] objArr2;
        i3 = this.f6291a.zzc;
        zzaq.zza(i2, i3, FirebaseAnalytics.Param.INDEX);
        zzct zzctVar = this.f6291a;
        int i4 = i2 + i2;
        objArr = zzctVar.zzb;
        Object obj = objArr[i4];
        obj.getClass();
        objArr2 = zzctVar.zzb;
        Object obj2 = objArr2[i4 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i2;
        i2 = this.f6291a.zzc;
        return i2;
    }
}
