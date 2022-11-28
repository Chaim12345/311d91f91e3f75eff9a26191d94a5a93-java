package com.google.android.play.core.splitcompat;

import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzo implements com.google.android.play.core.splitinstall.zzq {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SplitCompat f7889a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(SplitCompat splitCompat) {
        this.f7889a = splitCompat;
    }

    @Override // com.google.android.play.core.splitinstall.zzq
    public final Set zza() {
        Set zzf;
        zzf = this.f7889a.zzf();
        return zzf;
    }
}
