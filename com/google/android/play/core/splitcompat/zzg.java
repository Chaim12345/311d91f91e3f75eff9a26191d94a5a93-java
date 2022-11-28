package com.google.android.play.core.splitcompat;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipFile;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzg implements zzj {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzs f7879a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Set f7880b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ AtomicBoolean f7881c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzm f7882d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(zzm zzmVar, zzs zzsVar, Set set, AtomicBoolean atomicBoolean) {
        this.f7882d = zzmVar;
        this.f7879a = zzsVar;
        this.f7880b = set;
        this.f7881c = atomicBoolean;
    }

    @Override // com.google.android.play.core.splitcompat.zzj
    public final void zza(ZipFile zipFile, Set set) {
        this.f7882d.zzf(this.f7879a, set, new zzf(this));
    }
}
