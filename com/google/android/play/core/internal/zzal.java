package com.google.android.play.core.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.IInterface;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzal extends zzah {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzas f7860a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzal(zzas zzasVar) {
        this.f7860a = zzasVar;
    }

    @Override // com.google.android.play.core.internal.zzah
    public final void zza() {
        IInterface iInterface;
        zzag zzagVar;
        Context context;
        ServiceConnection serviceConnection;
        zzas zzasVar = this.f7860a;
        iInterface = zzasVar.zzo;
        if (iInterface != null) {
            zzagVar = zzasVar.zzc;
            zzagVar.zzd("Unbind from service.", new Object[0]);
            zzas zzasVar2 = this.f7860a;
            context = zzasVar2.zzb;
            serviceConnection = zzasVar2.zzn;
            context.unbindService(serviceConnection);
            this.f7860a.zzh = false;
            this.f7860a.zzo = null;
            this.f7860a.zzn = null;
        }
        this.f7860a.zzu();
    }
}
