package com.google.android.gms.measurement.internal;

import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.internal.measurement.zzne;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes2.dex */
public final class zzkg extends zzkz {
    public final zzfl zza;
    public final zzfl zzb;
    public final zzfl zzc;
    public final zzfl zzd;
    public final zzfl zze;
    private final Map zzg;
    private String zzh;
    private boolean zzi;
    private long zzj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkg(zzll zzllVar) {
        super(zzllVar);
        this.zzg = new HashMap();
        zzfp zzm = this.f6809a.zzm();
        zzm.getClass();
        this.zza = new zzfl(zzm, "last_delete_stale", 0L);
        zzfp zzm2 = this.f6809a.zzm();
        zzm2.getClass();
        this.zzb = new zzfl(zzm2, "backoff", 0L);
        zzfp zzm3 = this.f6809a.zzm();
        zzm3.getClass();
        this.zzc = new zzfl(zzm3, "last_upload", 0L);
        zzfp zzm4 = this.f6809a.zzm();
        zzm4.getClass();
        this.zzd = new zzfl(zzm4, "last_upload_attempt", 0L);
        zzfp zzm5 = this.f6809a.zzm();
        zzm5.getClass();
        this.zze = new zzfl(zzm5, "midnight_offset", 0L);
    }

    @Override // com.google.android.gms.measurement.internal.zzkz
    protected final boolean c() {
        return false;
    }

    @WorkerThread
    @Deprecated
    final Pair d(String str) {
        AdvertisingIdClient.Info advertisingIdInfo;
        zzkf zzkfVar;
        AdvertisingIdClient.Info advertisingIdInfo2;
        zzg();
        long elapsedRealtime = this.f6809a.zzav().elapsedRealtime();
        zzne.zzc();
        if (this.f6809a.zzf().zzs(null, zzen.zzar)) {
            zzkf zzkfVar2 = (zzkf) this.zzg.get(str);
            if (zzkfVar2 == null || elapsedRealtime >= zzkfVar2.f6998c) {
                AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
                long zzi = elapsedRealtime + this.f6809a.zzf().zzi(str, zzen.zza);
                try {
                    advertisingIdInfo2 = AdvertisingIdClient.getAdvertisingIdInfo(this.f6809a.zzau());
                } catch (Exception e2) {
                    this.f6809a.zzay().zzc().zzb("Unable to get advertising id", e2);
                    zzkfVar = new zzkf("", false, zzi);
                }
                if (advertisingIdInfo2 == null) {
                    return new Pair("", Boolean.FALSE);
                }
                String id = advertisingIdInfo2.getId();
                zzkfVar = id != null ? new zzkf(id, advertisingIdInfo2.isLimitAdTrackingEnabled(), zzi) : new zzkf("", advertisingIdInfo2.isLimitAdTrackingEnabled(), zzi);
                this.zzg.put(str, zzkfVar);
                AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
                return new Pair(zzkfVar.f6996a, Boolean.valueOf(zzkfVar.f6997b));
            }
            return new Pair(zzkfVar2.f6996a, Boolean.valueOf(zzkfVar2.f6997b));
        }
        String str2 = this.zzh;
        if (str2 == null || elapsedRealtime >= this.zzj) {
            this.zzj = elapsedRealtime + this.f6809a.zzf().zzi(str, zzen.zza);
            AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
            try {
                advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.f6809a.zzau());
            } catch (Exception e3) {
                this.f6809a.zzay().zzc().zzb("Unable to get advertising id", e3);
                this.zzh = "";
            }
            if (advertisingIdInfo == null) {
                return new Pair("", Boolean.FALSE);
            }
            this.zzh = "";
            String id2 = advertisingIdInfo.getId();
            if (id2 != null) {
                this.zzh = id2;
            }
            this.zzi = advertisingIdInfo.isLimitAdTrackingEnabled();
            AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
            return new Pair(this.zzh, Boolean.valueOf(this.zzi));
        }
        return new Pair(str2, Boolean.valueOf(this.zzi));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final Pair e(String str, zzai zzaiVar) {
        return zzaiVar.zzi(zzah.AD_STORAGE) ? d(str) : new Pair("", Boolean.FALSE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    @Deprecated
    public final String f(String str) {
        zzg();
        String str2 = (String) d(str).first;
        MessageDigest h2 = zzlt.h();
        if (h2 == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, h2.digest(str2.getBytes())));
    }
}
