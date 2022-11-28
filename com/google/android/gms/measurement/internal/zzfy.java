package com.google.android.gms.measurement.internal;

import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzfy extends LruCache {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzgb f6743a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfy(zzgb zzgbVar, int i2) {
        super(20);
        this.f6743a = zzgbVar;
    }

    @Override // androidx.collection.LruCache
    protected final /* bridge */ /* synthetic */ Object a(Object obj) {
        String str = (String) obj;
        Preconditions.checkNotEmpty(str);
        return zzgb.e(this.f6743a, str);
    }
}
