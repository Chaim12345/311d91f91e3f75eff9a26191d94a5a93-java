package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
/* loaded from: classes2.dex */
final class zzag extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f7771a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f7772b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f7773c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ int f7774d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7775e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzaw f7776f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzag(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar, int i2, String str, String str2, int i3, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7776f = zzawVar;
        this.f7771a = i2;
        this.f7772b = str;
        this.f7773c = str2;
        this.f7774d = i3;
        this.f7775e = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        com.google.android.play.core.internal.zzas zzasVar;
        String str;
        Bundle zzA;
        try {
            zzasVar = this.f7776f.zzf;
            str = this.f7776f.zzc;
            Bundle a2 = zzaw.a(this.f7771a, this.f7772b, this.f7773c, this.f7774d);
            zzA = zzaw.zzA();
            ((com.google.android.play.core.internal.zzu) zzasVar.zze()).zzg(str, a2, zzA, new zzaq(this.f7776f, this.f7775e));
        } catch (RemoteException e2) {
            zzagVar = zzaw.zza;
            zzagVar.zzc(e2, "notifyChunkTransferred", new Object[0]);
        }
    }
}
