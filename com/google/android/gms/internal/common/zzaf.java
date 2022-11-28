package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzaf extends zzag {

    /* renamed from: a  reason: collision with root package name */
    final transient int f5849a;

    /* renamed from: b  reason: collision with root package name */
    final transient int f5850b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzag f5851c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaf(zzag zzagVar, int i2, int i3) {
        this.f5851c = zzagVar;
        this.f5849a = i2;
        this.f5850b = i3;
    }

    @Override // com.google.android.gms.internal.common.zzac
    final int b() {
        return this.f5851c.c() + this.f5849a + this.f5850b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.common.zzac
    public final int c() {
        return this.f5851c.c() + this.f5849a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.common.zzac
    public final boolean d() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.common.zzac
    @CheckForNull
    public final Object[] e() {
        return this.f5851c.e();
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzs.zza(i2, this.f5850b, FirebaseAnalytics.Param.INDEX);
        return this.f5851c.get(i2 + this.f5849a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f5850b;
    }

    @Override // com.google.android.gms.internal.common.zzag, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }

    @Override // com.google.android.gms.internal.common.zzag
    public final zzag zzh(int i2, int i3) {
        zzs.zzc(i2, i3, this.f5850b);
        zzag zzagVar = this.f5851c;
        int i4 = this.f5849a;
        return zzagVar.subList(i2 + i4, i3 + i4);
    }
}
