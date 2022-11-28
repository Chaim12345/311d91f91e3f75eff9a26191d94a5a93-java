package com.google.android.play.core.splitcompat;

import java.io.File;
/* loaded from: classes2.dex */
final class zzf implements zzk {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzg f7878a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(zzg zzgVar) {
        this.f7878a = zzgVar;
    }

    @Override // com.google.android.play.core.splitcompat.zzk
    public final void zza(zzl zzlVar, File file, boolean z) {
        this.f7878a.f7880b.add(file);
        if (z) {
            return;
        }
        this.f7878a.f7881c.set(false);
    }
}
