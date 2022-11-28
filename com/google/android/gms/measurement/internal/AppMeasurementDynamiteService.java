package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
@DynamiteApi
/* loaded from: classes2.dex */
public class AppMeasurementDynamiteService extends com.google.android.gms.internal.measurement.zzcb {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    zzgk f6677a = null;
    @GuardedBy("listenerMap")
    private final Map zzb = new ArrayMap();

    @EnsuresNonNull({"scion"})
    private final void zzb() {
        if (this.f6677a == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    private final void zzc(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str) {
        zzb();
        this.f6677a.zzv().zzV(zzcfVar, str);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void beginAdUnitExposure(@NonNull String str, long j2) {
        zzb();
        this.f6677a.zzd().zzd(str, j2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void clearConditionalUserProperty(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle) {
        zzb();
        this.f6677a.zzq().zzA(str, str2, bundle);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void clearMeasurementEnabled(long j2) {
        zzb();
        this.f6677a.zzq().zzW(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void endAdUnitExposure(@NonNull String str, long j2) {
        zzb();
        this.f6677a.zzd().zze(str, j2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void generateEventId(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        long zzq = this.f6677a.zzv().zzq();
        zzb();
        this.f6677a.zzv().zzU(zzcfVar, zzq);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getAppInstanceId(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        this.f6677a.zzaz().zzp(new zzi(this, zzcfVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getCachedAppInstanceId(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        zzc(zzcfVar, this.f6677a.zzq().zzo());
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getConditionalUserProperties(String str, String str2, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        this.f6677a.zzaz().zzp(new zzm(this, zzcfVar, str, str2));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getCurrentScreenClass(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        zzc(zzcfVar, this.f6677a.zzq().zzp());
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getCurrentScreenName(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        zzc(zzcfVar, this.f6677a.zzq().zzq());
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getGmpAppId(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        String str;
        zzb();
        zzip zzq = this.f6677a.zzq();
        if (zzq.f6809a.zzw() != null) {
            str = zzq.f6809a.zzw();
        } else {
            try {
                str = zziv.zzc(zzq.f6809a.zzau(), "google_app_id", zzq.f6809a.zzz());
            } catch (IllegalStateException e2) {
                zzq.f6809a.zzay().zzd().zzb("getGoogleAppId failed with exception", e2);
                str = null;
            }
        }
        zzc(zzcfVar, str);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getMaxUserProperties(String str, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        this.f6677a.zzq().zzh(str);
        zzb();
        this.f6677a.zzv().zzT(zzcfVar, 25);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getTestFlag(com.google.android.gms.internal.measurement.zzcf zzcfVar, int i2) {
        zzb();
        if (i2 == 0) {
            this.f6677a.zzv().zzV(zzcfVar, this.f6677a.zzq().zzr());
        } else if (i2 == 1) {
            this.f6677a.zzv().zzU(zzcfVar, this.f6677a.zzq().zzm().longValue());
        } else if (i2 != 2) {
            if (i2 == 3) {
                this.f6677a.zzv().zzT(zzcfVar, this.f6677a.zzq().zzl().intValue());
            } else if (i2 != 4) {
            } else {
                this.f6677a.zzv().zzP(zzcfVar, this.f6677a.zzq().zzi().booleanValue());
            }
        } else {
            zzlt zzv = this.f6677a.zzv();
            double doubleValue = this.f6677a.zzq().zzj().doubleValue();
            Bundle bundle = new Bundle();
            bundle.putDouble("r", doubleValue);
            try {
                zzcfVar.zzd(bundle);
            } catch (RemoteException e2) {
                zzv.f6809a.zzay().zzk().zzb("Error returning double value to wrapper", e2);
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void getUserProperties(String str, String str2, boolean z, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        this.f6677a.zzaz().zzp(new zzk(this, zzcfVar, str, str2, z));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void initForTests(@NonNull Map map) {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void initialize(IObjectWrapper iObjectWrapper, com.google.android.gms.internal.measurement.zzcl zzclVar, long j2) {
        zzgk zzgkVar = this.f6677a;
        if (zzgkVar == null) {
            this.f6677a = zzgk.zzp((Context) Preconditions.checkNotNull((Context) ObjectWrapper.unwrap(iObjectWrapper)), zzclVar, Long.valueOf(j2));
        } else {
            zzgkVar.zzay().zzk().zza("Attempting to initialize multiple times");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void isDataCollectionEnabled(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzb();
        this.f6677a.zzaz().zzp(new zzn(this, zzcfVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void logEvent(@NonNull String str, @NonNull String str2, @NonNull Bundle bundle, boolean z, boolean z2, long j2) {
        zzb();
        this.f6677a.zzq().zzF(str, str2, bundle, z, z2, j2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void logEventAndBundle(String str, String str2, Bundle bundle, com.google.android.gms.internal.measurement.zzcf zzcfVar, long j2) {
        zzb();
        Preconditions.checkNotEmpty(str2);
        (bundle != null ? new Bundle(bundle) : new Bundle()).putString("_o", CarContext.APP_SERVICE);
        this.f6677a.zzaz().zzp(new zzj(this, zzcfVar, new zzaw(str2, new zzau(bundle), CarContext.APP_SERVICE, j2), str));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void logHealthData(int i2, @NonNull String str, @NonNull IObjectWrapper iObjectWrapper, @NonNull IObjectWrapper iObjectWrapper2, @NonNull IObjectWrapper iObjectWrapper3) {
        zzb();
        this.f6677a.zzay().l(i2, true, false, str, iObjectWrapper == null ? null : ObjectWrapper.unwrap(iObjectWrapper), iObjectWrapper2 == null ? null : ObjectWrapper.unwrap(iObjectWrapper2), iObjectWrapper3 != null ? ObjectWrapper.unwrap(iObjectWrapper3) : null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityCreated(@NonNull IObjectWrapper iObjectWrapper, @NonNull Bundle bundle, long j2) {
        zzb();
        zzio zzioVar = this.f6677a.zzq().f6892b;
        if (zzioVar != null) {
            this.f6677a.zzq().zzB();
            zzioVar.onActivityCreated((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityDestroyed(@NonNull IObjectWrapper iObjectWrapper, long j2) {
        zzb();
        zzio zzioVar = this.f6677a.zzq().f6892b;
        if (zzioVar != null) {
            this.f6677a.zzq().zzB();
            zzioVar.onActivityDestroyed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityPaused(@NonNull IObjectWrapper iObjectWrapper, long j2) {
        zzb();
        zzio zzioVar = this.f6677a.zzq().f6892b;
        if (zzioVar != null) {
            this.f6677a.zzq().zzB();
            zzioVar.onActivityPaused((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityResumed(@NonNull IObjectWrapper iObjectWrapper, long j2) {
        zzb();
        zzio zzioVar = this.f6677a.zzq().f6892b;
        if (zzioVar != null) {
            this.f6677a.zzq().zzB();
            zzioVar.onActivityResumed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, com.google.android.gms.internal.measurement.zzcf zzcfVar, long j2) {
        zzb();
        zzio zzioVar = this.f6677a.zzq().f6892b;
        Bundle bundle = new Bundle();
        if (zzioVar != null) {
            this.f6677a.zzq().zzB();
            zzioVar.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzcfVar.zzd(bundle);
        } catch (RemoteException e2) {
            this.f6677a.zzay().zzk().zzb("Error returning bundle value to wrapper", e2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityStarted(@NonNull IObjectWrapper iObjectWrapper, long j2) {
        zzb();
        if (this.f6677a.zzq().f6892b != null) {
            this.f6677a.zzq().zzB();
            Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void onActivityStopped(@NonNull IObjectWrapper iObjectWrapper, long j2) {
        zzb();
        if (this.f6677a.zzq().f6892b != null) {
            this.f6677a.zzq().zzB();
            Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void performAction(Bundle bundle, com.google.android.gms.internal.measurement.zzcf zzcfVar, long j2) {
        zzb();
        zzcfVar.zzd(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void registerOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzci zzciVar) {
        zzhl zzhlVar;
        zzb();
        synchronized (this.zzb) {
            zzhlVar = (zzhl) this.zzb.get(Integer.valueOf(zzciVar.zzd()));
            if (zzhlVar == null) {
                zzhlVar = new zzp(this, zzciVar);
                this.zzb.put(Integer.valueOf(zzciVar.zzd()), zzhlVar);
            }
        }
        this.f6677a.zzq().zzK(zzhlVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void resetAnalyticsData(long j2) {
        zzb();
        this.f6677a.zzq().zzL(j2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setConditionalUserProperty(@NonNull Bundle bundle, long j2) {
        zzb();
        if (bundle == null) {
            this.f6677a.zzay().zzd().zza("Conditional user property must not be null");
        } else {
            this.f6677a.zzq().zzR(bundle, j2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setConsent(@NonNull Bundle bundle, long j2) {
        zzb();
        this.f6677a.zzq().zzU(bundle, j2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setConsentThirdParty(@NonNull Bundle bundle, long j2) {
        zzb();
        this.f6677a.zzq().zzS(bundle, -20, j2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setCurrentScreen(@NonNull IObjectWrapper iObjectWrapper, @NonNull String str, @NonNull String str2, long j2) {
        zzb();
        this.f6677a.zzs().zzw((Activity) ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setDataCollectionEnabled(boolean z) {
        zzb();
        zzip zzq = this.f6677a.zzq();
        zzq.zza();
        zzq.f6809a.zzaz().zzp(new zzil(zzq, z));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setDefaultEventParameters(@NonNull Bundle bundle) {
        zzb();
        final zzip zzq = this.f6677a.zzq();
        final Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
        zzq.f6809a.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzhp
            @Override // java.lang.Runnable
            public final void run() {
                zzip.this.e(bundle2);
            }
        });
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setEventInterceptor(com.google.android.gms.internal.measurement.zzci zzciVar) {
        zzb();
        zzo zzoVar = new zzo(this, zzciVar);
        if (this.f6677a.zzaz().zzs()) {
            this.f6677a.zzq().zzV(zzoVar);
        } else {
            this.f6677a.zzaz().zzp(new zzl(this, zzoVar));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setInstanceIdProvider(com.google.android.gms.internal.measurement.zzck zzckVar) {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setMeasurementEnabled(boolean z, long j2) {
        zzb();
        this.f6677a.zzq().zzW(Boolean.valueOf(z));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setMinimumSessionDuration(long j2) {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setSessionTimeoutDuration(long j2) {
        zzb();
        zzip zzq = this.f6677a.zzq();
        zzq.f6809a.zzaz().zzp(new zzht(zzq, j2));
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setUserId(@NonNull final String str, long j2) {
        zzb();
        final zzip zzq = this.f6677a.zzq();
        if (str != null && TextUtils.isEmpty(str)) {
            zzq.f6809a.zzay().zzk().zza("User ID must be non-empty or null");
            return;
        }
        zzq.f6809a.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzhq
            @Override // java.lang.Runnable
            public final void run() {
                zzip zzipVar = zzip.this;
                if (zzipVar.f6809a.zzh().j(str)) {
                    zzipVar.f6809a.zzh().i();
                }
            }
        });
        zzq.zzZ(null, "_id", str, true, j2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void setUserProperty(@NonNull String str, @NonNull String str2, @NonNull IObjectWrapper iObjectWrapper, boolean z, long j2) {
        zzb();
        this.f6677a.zzq().zzZ(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z, j2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcc
    public void unregisterOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzci zzciVar) {
        zzhl zzhlVar;
        zzb();
        synchronized (this.zzb) {
            zzhlVar = (zzhl) this.zzb.remove(Integer.valueOf(zzciVar.zzd()));
        }
        if (zzhlVar == null) {
            zzhlVar = new zzp(this, zzciVar);
        }
        this.f6677a.zzq().zzab(zzhlVar);
    }
}
