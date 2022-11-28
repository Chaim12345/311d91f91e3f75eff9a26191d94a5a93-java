package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjw implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6971a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6972b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzq f6973c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf f6974d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzke f6975e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjw(zzke zzkeVar, String str, String str2, zzq zzqVar, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.f6975e = zzkeVar;
        this.f6971a = str;
        this.f6972b = str2;
        this.f6973c = zzqVar;
        this.f6974d = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzeq zzeqVar;
        ArrayList arrayList = new ArrayList();
        try {
            try {
                zzke zzkeVar = this.f6975e;
                zzeqVar = zzkeVar.zzb;
                if (zzeqVar == null) {
                    zzkeVar.f6809a.zzay().zzd().zzc("Failed to get conditional properties; not connected to service", this.f6971a, this.f6972b);
                } else {
                    Preconditions.checkNotNull(this.f6973c);
                    arrayList = zzlt.zzH(zzeqVar.zzf(this.f6971a, this.f6972b, this.f6973c));
                    this.f6975e.zzQ();
                }
            } catch (RemoteException e2) {
                this.f6975e.f6809a.zzay().zzd().zzd("Failed to get conditional properties; remote exception", this.f6971a, this.f6972b, e2);
            }
        } finally {
            this.f6975e.f6809a.zzv().zzQ(this.f6974d, arrayList);
        }
    }
}
