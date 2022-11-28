package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzke extends zzf {
    private final zzkd zza;
    private zzeq zzb;
    private volatile Boolean zzc;
    private final zzap zzd;
    private final zzkv zze;
    private final List zzf;
    private final zzap zzg;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzke(zzgk zzgkVar) {
        super(zzgkVar);
        this.zzf = new ArrayList();
        this.zze = new zzkv(zzgkVar.zzav());
        this.zza = new zzkd(this);
        this.zzd = new zzjo(this, zzgkVar);
        this.zzg = new zzjq(this, zzgkVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void s(zzke zzkeVar, ComponentName componentName) {
        zzkeVar.zzg();
        if (zzkeVar.zzb != null) {
            zzkeVar.zzb = null;
            zzkeVar.f6809a.zzay().zzj().zzb("Disconnected from device MeasurementService", componentName);
            zzkeVar.zzg();
            zzkeVar.v();
        }
    }

    @WorkerThread
    private final zzq zzO(boolean z) {
        Pair zza;
        this.f6809a.zzaw();
        zzer zzh = this.f6809a.zzh();
        String str = null;
        if (z) {
            zzfa zzay = this.f6809a.zzay();
            if (zzay.f6809a.zzm().zzb != null && (zza = zzay.f6809a.zzm().zzb.zza()) != null && zza != zzfp.f6737b) {
                str = String.valueOf(zza.second) + ":" + ((String) zza.first);
            }
        }
        return zzh.f(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzP() {
        zzg();
        this.f6809a.zzay().zzj().zzb("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        for (Runnable runnable : this.zzf) {
            try {
                runnable.run();
            } catch (RuntimeException e2) {
                this.f6809a.zzay().zzd().zzb("Task exception while flushing queue", e2);
            }
        }
        this.zzf.clear();
        this.zzg.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzQ() {
        zzg();
        this.zze.zzb();
        zzap zzapVar = this.zzd;
        this.f6809a.zzf();
        zzapVar.zzd(((Long) zzen.zzI.zza(null)).longValue());
    }

    @WorkerThread
    private final void zzR(Runnable runnable) {
        zzg();
        if (zzL()) {
            runnable.run();
            return;
        }
        int size = this.zzf.size();
        this.f6809a.zzf();
        if (size >= 1000) {
            this.f6809a.zzay().zzd().zza("Discarding data. Max runnable queue size reached");
            return;
        }
        this.zzf.add(runnable);
        this.zzg.zzd(AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS);
        v();
    }

    private final boolean zzS() {
        this.f6809a.zzaw();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void A(AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        zzg();
        zza();
        zzR(new zzjx(this, atomicReference, null, str2, str3, zzO(false), z));
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean c() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void d(zzaw zzawVar, String str) {
        Preconditions.checkNotNull(zzawVar);
        zzg();
        zza();
        zzS();
        zzR(new zzjt(this, true, zzO(true), this.f6809a.zzi().zzo(zzawVar), zzawVar, str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    @WorkerThread
    public final void e(zzeq zzeqVar, AbstractSafeParcelable abstractSafeParcelable, zzq zzqVar) {
        int i2;
        zzey zzd;
        String str;
        zzg();
        zza();
        zzS();
        this.f6809a.zzf();
        int i3 = 0;
        int i4 = 100;
        while (i3 < 1001 && i4 == 100) {
            ArrayList arrayList = new ArrayList();
            List zzi = this.f6809a.zzi().zzi(100);
            if (zzi != null) {
                arrayList.addAll(zzi);
                i2 = zzi.size();
            } else {
                i2 = 0;
            }
            if (abstractSafeParcelable != null && i2 < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            int size = arrayList.size();
            for (int i5 = 0; i5 < size; i5++) {
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) arrayList.get(i5);
                if (abstractSafeParcelable2 instanceof zzaw) {
                    try {
                        zzeqVar.zzk((zzaw) abstractSafeParcelable2, zzqVar);
                    } catch (RemoteException e2) {
                        e = e2;
                        zzd = this.f6809a.zzay().zzd();
                        str = "Failed to send event to the service";
                        zzd.zzb(str, e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzlo) {
                    try {
                        zzeqVar.zzt((zzlo) abstractSafeParcelable2, zzqVar);
                    } catch (RemoteException e3) {
                        e = e3;
                        zzd = this.f6809a.zzay().zzd();
                        str = "Failed to send user property to the service";
                        zzd.zzb(str, e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzac) {
                    try {
                        zzeqVar.zzn((zzac) abstractSafeParcelable2, zzqVar);
                    } catch (RemoteException e4) {
                        e = e4;
                        zzd = this.f6809a.zzay().zzd();
                        str = "Failed to send conditional user property to the service";
                        zzd.zzb(str, e);
                    }
                } else {
                    this.f6809a.zzay().zzd().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            i3++;
            i4 = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void f(zzac zzacVar) {
        Preconditions.checkNotNull(zzacVar);
        zzg();
        zza();
        this.f6809a.zzaw();
        zzR(new zzju(this, true, zzO(true), this.f6809a.zzi().zzn(zzacVar), new zzac(zzacVar), zzacVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void g(boolean z) {
        zzg();
        zza();
        if (z) {
            zzS();
            this.f6809a.zzi().zzj();
        }
        if (l()) {
            zzR(new zzjs(this, zzO(false)));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void h(zziw zziwVar) {
        zzg();
        zza();
        zzR(new zzjm(this, zziwVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void i() {
        zzg();
        zza();
        zzR(new zzjr(this, zzO(true)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @VisibleForTesting
    @WorkerThread
    public final void j(zzeq zzeqVar) {
        zzg();
        Preconditions.checkNotNull(zzeqVar);
        this.zzb = zzeqVar;
        zzQ();
        zzP();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void k(zzlo zzloVar) {
        zzg();
        zza();
        zzS();
        zzR(new zzjg(this, zzO(true), this.f6809a.zzi().zzp(zzloVar), zzloVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean l() {
        zzg();
        zza();
        return !m() || this.f6809a.zzv().zzm() >= ((Integer) zzen.zzai.zza(null)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0127  */
    @WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m() {
        zzey zzj;
        String str;
        zzey zzk;
        String str2;
        zzg();
        zza();
        if (this.zzc == null) {
            zzg();
            zza();
            zzfp zzm = this.f6809a.zzm();
            zzm.zzg();
            boolean z = false;
            Boolean valueOf = !zzm.e().contains("use_service") ? null : Boolean.valueOf(zzm.e().getBoolean("use_service", false));
            if (valueOf == null || !valueOf.booleanValue()) {
                this.f6809a.zzaw();
                if (this.f6809a.zzh().d() != 1) {
                    this.f6809a.zzay().zzj().zza("Checking service availability");
                    int zzo = this.f6809a.zzv().zzo(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                    if (zzo != 0) {
                        if (zzo == 1) {
                            this.f6809a.zzay().zzj().zza("Service missing");
                        } else if (zzo != 2) {
                            if (zzo == 3) {
                                zzk = this.f6809a.zzay().zzk();
                                str2 = "Service disabled";
                            } else if (zzo == 9) {
                                zzk = this.f6809a.zzay().zzk();
                                str2 = "Service invalid";
                            } else if (zzo != 18) {
                                this.f6809a.zzay().zzk().zzb("Unexpected service status", Integer.valueOf(zzo));
                                r1 = false;
                            } else {
                                zzj = this.f6809a.zzay().zzk();
                                str = "Service updating";
                            }
                            zzk.zza(str2);
                            r1 = false;
                        } else {
                            this.f6809a.zzay().zzc().zza("Service container out of date");
                            if (this.f6809a.zzv().zzm() >= 17443) {
                                z = valueOf == null;
                                r1 = false;
                            }
                        }
                        if (z && this.f6809a.zzf().g()) {
                            this.f6809a.zzay().zzd().zza("No way to upload. Consider using the full version of Analytics");
                        } else if (r1) {
                            zzfp zzm2 = this.f6809a.zzm();
                            zzm2.zzg();
                            SharedPreferences.Editor edit = zzm2.e().edit();
                            edit.putBoolean("use_service", z);
                            edit.apply();
                        }
                        r1 = z;
                    } else {
                        zzj = this.f6809a.zzay().zzj();
                        str = "Service available";
                    }
                    zzj.zza(str);
                }
                z = true;
                if (z) {
                }
                if (r1) {
                }
                r1 = z;
            }
            this.zzc = Boolean.valueOf(r1);
        }
        return this.zzc.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean p() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void u() {
        zzg();
        zza();
        zzq zzO = zzO(true);
        this.f6809a.zzi().zzk();
        zzR(new zzjl(this, zzO));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void v() {
        zzg();
        zza();
        if (zzL()) {
            return;
        }
        if (m()) {
            this.zza.zzc();
        } else if (this.f6809a.zzf().g()) {
        } else {
            this.f6809a.zzaw();
            List<ResolveInfo> queryIntentServices = this.f6809a.zzau().getPackageManager().queryIntentServices(new Intent().setClassName(this.f6809a.zzau(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
            if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                this.f6809a.zzay().zzd().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
                return;
            }
            Intent intent = new Intent("com.google.android.gms.measurement.START");
            Context zzau = this.f6809a.zzau();
            this.f6809a.zzaw();
            intent.setComponent(new ComponentName(zzau, "com.google.android.gms.measurement.AppMeasurementService"));
            this.zza.zzb(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void w(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2) {
        zzg();
        zza();
        zzR(new zzjw(this, str, str2, zzO(false), zzcfVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void x(AtomicReference atomicReference, String str, String str2, String str3) {
        zzg();
        zza();
        zzR(new zzjv(this, atomicReference, null, str2, str3, zzO(false)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void y(AtomicReference atomicReference, boolean z) {
        zzg();
        zza();
        zzR(new zzjh(this, atomicReference, zzO(false), z));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void z(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2, boolean z) {
        zzg();
        zza();
        zzR(new zzjf(this, str, str2, zzO(false), z, zzcfVar));
    }

    @WorkerThread
    public final void zzB(com.google.android.gms.internal.measurement.zzcf zzcfVar, zzaw zzawVar, String str) {
        zzg();
        zza();
        if (this.f6809a.zzv().zzo(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) == 0) {
            zzR(new zzjp(this, zzawVar, str, zzcfVar));
            return;
        }
        this.f6809a.zzay().zzk().zza("Not bundling data. Service unavailable or out of date");
        this.f6809a.zzv().zzS(zzcfVar, new byte[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @WorkerThread
    public final void zzC() {
        zzg();
        zza();
        zzq zzO = zzO(false);
        zzS();
        this.f6809a.zzi().zzj();
        zzR(new zzji(this, zzO));
    }

    @WorkerThread
    public final void zzH(Bundle bundle) {
        zzg();
        zza();
        zzR(new zzjn(this, zzO(false), bundle));
    }

    @WorkerThread
    public final boolean zzL() {
        zzg();
        zza();
        return this.zzb != null;
    }

    @WorkerThread
    public final void zzs() {
        zzg();
        zza();
        this.zza.zzd();
        try {
            ConnectionTracker.getInstance().unbindService(this.f6809a.zzau(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    @WorkerThread
    public final void zzt(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzg();
        zza();
        zzR(new zzjk(this, zzO(false), zzcfVar));
    }

    @WorkerThread
    public final void zzu(AtomicReference atomicReference) {
        zzg();
        zza();
        zzR(new zzjj(this, atomicReference, zzO(false)));
    }
}
