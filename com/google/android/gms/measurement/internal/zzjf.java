package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjf implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6919a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6920b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzq f6921c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ boolean f6922d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf f6923e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzke f6924f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjf(zzke zzkeVar, String str, String str2, zzq zzqVar, boolean z, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.f6924f = zzkeVar;
        this.f6919a = str;
        this.f6920b = str2;
        this.f6921c = zzqVar;
        this.f6922d = z;
        this.f6923e = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle;
        RemoteException e2;
        zzeq zzeqVar;
        Bundle bundle2 = new Bundle();
        try {
            zzke zzkeVar = this.f6924f;
            zzeqVar = zzkeVar.zzb;
            if (zzeqVar == null) {
                zzkeVar.f6809a.zzay().zzd().zzc("Failed to get user properties; not connected to service", this.f6919a, this.f6920b);
                this.f6924f.f6809a.zzv().zzR(this.f6923e, bundle2);
                return;
            }
            Preconditions.checkNotNull(this.f6921c);
            List<zzlo> zzh = zzeqVar.zzh(this.f6919a, this.f6920b, this.f6922d, this.f6921c);
            bundle = new Bundle();
            if (zzh != null) {
                for (zzlo zzloVar : zzh) {
                    String str = zzloVar.zze;
                    if (str != null) {
                        bundle.putString(zzloVar.zzb, str);
                    } else {
                        Long l2 = zzloVar.zzd;
                        if (l2 != null) {
                            bundle.putLong(zzloVar.zzb, l2.longValue());
                        } else {
                            Double d2 = zzloVar.zzg;
                            if (d2 != null) {
                                bundle.putDouble(zzloVar.zzb, d2.doubleValue());
                            }
                        }
                    }
                }
            }
            try {
                try {
                    this.f6924f.zzQ();
                    this.f6924f.f6809a.zzv().zzR(this.f6923e, bundle);
                } catch (RemoteException e3) {
                    e2 = e3;
                    this.f6924f.f6809a.zzay().zzd().zzc("Failed to get user properties; remote exception", this.f6919a, e2);
                    this.f6924f.f6809a.zzv().zzR(this.f6923e, bundle);
                }
            } catch (Throwable th) {
                th = th;
                bundle2 = bundle;
                this.f6924f.f6809a.zzv().zzR(this.f6923e, bundle2);
                throw th;
            }
        } catch (RemoteException e4) {
            bundle = bundle2;
            e2 = e4;
        } catch (Throwable th2) {
            th = th2;
            this.f6924f.f6809a.zzv().zzR(this.f6923e, bundle2);
            throw th;
        }
    }
}
