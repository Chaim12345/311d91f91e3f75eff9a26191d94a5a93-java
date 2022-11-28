package com.google.android.play.core.splitcompat;

import java.util.Set;
import java.util.zip.ZipFile;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzh implements zzj {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Set f7883a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzs f7884b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzm f7885c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(zzm zzmVar, Set set, zzs zzsVar) {
        this.f7885c = zzmVar;
        this.f7883a = set;
        this.f7884b = zzsVar;
    }

    @Override // com.google.android.play.core.splitcompat.zzj
    public final void zza(ZipFile zipFile, Set set) {
        this.f7883a.addAll(zzm.a(this.f7885c, set, this.f7884b, zipFile));
    }
}
