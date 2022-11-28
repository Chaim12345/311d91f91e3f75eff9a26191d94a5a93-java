package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzca;
import java.util.ArrayList;
import java.util.Collection;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzal extends com.google.android.play.core.internal.zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Collection f7899a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Collection f7900b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ com.google.android.play.core.tasks.zzi f7901c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzbc f7902d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzal(zzbc zzbcVar, com.google.android.play.core.tasks.zzi zziVar, Collection collection, Collection collection2, com.google.android.play.core.tasks.zzi zziVar2) {
        super(zziVar);
        this.f7902d = zzbcVar;
        this.f7899a = collection;
        this.f7900b = collection2;
        this.f7901c = zziVar2;
    }

    @Override // com.google.android.play.core.internal.zzah
    protected final void zza() {
        com.google.android.play.core.internal.zzag zzagVar;
        String str;
        ArrayList e2 = zzbc.e(this.f7899a);
        e2.addAll(zzbc.d(this.f7900b));
        try {
            str = this.f7902d.zzd;
            ((zzca) this.f7902d.f7925a.zze()).zzj(str, e2, zzbc.a(), new zzba(this.f7902d, this.f7901c));
        } catch (RemoteException e3) {
            zzagVar = zzbc.zzb;
            zzagVar.zzc(e3, "startInstall(%s,%s)", this.f7899a, this.f7900b);
            this.f7901c.zzd(new RuntimeException(e3));
        }
    }
}
