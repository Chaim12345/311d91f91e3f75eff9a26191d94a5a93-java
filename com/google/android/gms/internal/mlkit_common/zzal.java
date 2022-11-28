package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzal extends zzam {

    /* renamed from: a  reason: collision with root package name */
    final transient int f6123a;

    /* renamed from: b  reason: collision with root package name */
    final transient int f6124b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzam f6125c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzal(zzam zzamVar, int i2, int i3) {
        this.f6125c = zzamVar;
        this.f6123a = i2;
        this.f6124b = i3;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzai
    final int b() {
        return this.f6125c.c() + this.f6123a + this.f6124b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_common.zzai
    public final int c() {
        return this.f6125c.c() + this.f6123a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_common.zzai
    @CheckForNull
    public final Object[] d() {
        return this.f6125c.d();
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzab.zza(i2, this.f6124b, FirebaseAnalytics.Param.INDEX);
        return this.f6125c.get(i2 + this.f6123a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f6124b;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzam, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzam
    public final zzam zzf(int i2, int i3) {
        zzab.zzc(i2, i3, this.f6124b);
        zzam zzamVar = this.f6125c;
        int i4 = this.f6123a;
        return zzamVar.subList(i2 + i4, i3 + i4);
    }
}
