package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IInterface;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SleepSegmentRequest;
/* loaded from: classes.dex */
public interface zzam extends IInterface {
    @Deprecated
    Location zzd();

    ICancelToken zze(CurrentLocationRequest currentLocationRequest, zzao zzaoVar);

    LocationAvailability zzf(String str);

    void zzg(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, zzak zzakVar);

    void zzh(LocationSettingsRequest locationSettingsRequest, zzaq zzaqVar, String str);

    void zzi(zzai zzaiVar);

    void zzj(LastLocationRequest lastLocationRequest, zzao zzaoVar);

    void zzk(PendingIntent pendingIntent, IStatusCallback iStatusCallback);

    void zzl(PendingIntent pendingIntent);

    void zzm(com.google.android.gms.location.zzbx zzbxVar, zzak zzakVar);

    void zzn(PendingIntent pendingIntent, zzak zzakVar, String str);

    void zzo(String[] strArr, zzak zzakVar, String str);

    void zzp(PendingIntent pendingIntent, IStatusCallback iStatusCallback);

    void zzq(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent, IStatusCallback iStatusCallback);

    void zzr(long j2, boolean z, PendingIntent pendingIntent);

    void zzs(com.google.android.gms.location.zzl zzlVar, PendingIntent pendingIntent, IStatusCallback iStatusCallback);

    void zzt(PendingIntent pendingIntent, SleepSegmentRequest sleepSegmentRequest, IStatusCallback iStatusCallback);

    @Deprecated
    void zzu(Location location);

    void zzv(Location location, IStatusCallback iStatusCallback);

    @Deprecated
    void zzw(boolean z);

    void zzx(boolean z, IStatusCallback iStatusCallback);

    void zzy(zzj zzjVar);

    void zzz(zzbh zzbhVar);
}
