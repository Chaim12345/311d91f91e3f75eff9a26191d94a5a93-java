package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;
import java.util.List;
/* loaded from: classes2.dex */
final class zzao extends zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ IBinder f7861a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzar f7862b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzao(zzar zzarVar, IBinder iBinder) {
        this.f7862b = zzarVar;
        this.f7861a = iBinder;
    }

    @Override // com.google.android.play.core.internal.zzah
    public final void zza() {
        zzan zzanVar;
        List<Runnable> list;
        List list2;
        zzas zzasVar = this.f7862b.f7864a;
        zzanVar = zzasVar.zzj;
        zzasVar.zzo = (IInterface) zzanVar.zza(this.f7861a);
        zzas.l(this.f7862b.f7864a);
        this.f7862b.f7864a.zzh = false;
        list = this.f7862b.f7864a.zze;
        for (Runnable runnable : list) {
            runnable.run();
        }
        list2 = this.f7862b.f7864a.zze;
        list2.clear();
    }
}
