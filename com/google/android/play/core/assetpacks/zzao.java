package com.google.android.play.core.assetpacks;

import java.util.List;
/* loaded from: classes2.dex */
final class zzao extends zzal {

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzaw f7795c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzao(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar) {
        super(zzawVar, zziVar);
        this.f7795c = zzawVar;
    }

    @Override // com.google.android.play.core.assetpacks.zzal, com.google.android.play.core.internal.zzw
    public final void zzg(List list) {
        super.zzg(list);
        this.f7793a.zze(zzaw.m(this.f7795c, list));
    }
}
