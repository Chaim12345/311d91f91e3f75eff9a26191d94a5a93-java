package com.google.android.gms.internal.location;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzbw extends zzbx {

    /* renamed from: a  reason: collision with root package name */
    final transient int f5885a;

    /* renamed from: b  reason: collision with root package name */
    final transient int f5886b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzbx f5887c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbw(zzbx zzbxVar, int i2, int i3) {
        this.f5887c = zzbxVar;
        this.f5885a = i2;
        this.f5886b = i3;
    }

    @Override // com.google.android.gms.internal.location.zzbu
    final int b() {
        return this.f5887c.c() + this.f5885a + this.f5886b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.location.zzbu
    public final int c() {
        return this.f5887c.c() + this.f5885a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.location.zzbu
    public final boolean d() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.location.zzbu
    @CheckForNull
    public final Object[] e() {
        return this.f5887c.e();
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzbr.zza(i2, this.f5886b, FirebaseAnalytics.Param.INDEX);
        return this.f5887c.get(i2 + this.f5885a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f5886b;
    }

    @Override // com.google.android.gms.internal.location.zzbx, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i2, int i3) {
        return subList(i2, i3);
    }

    @Override // com.google.android.gms.internal.location.zzbx
    public final zzbx zzh(int i2, int i3) {
        zzbr.zzc(i2, i3, this.f5886b);
        zzbx zzbxVar = this.f5887c;
        int i4 = this.f5885a;
        return zzbxVar.subList(i2 + i4, i3 + i4);
    }
}
