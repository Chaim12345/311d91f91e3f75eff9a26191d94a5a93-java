package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
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
public final class zzal extends zza implements zzam {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final Location zzd() {
        Parcel a2 = a(7, c());
        Location location = (Location) zzc.zza(a2, Location.CREATOR);
        a2.recycle();
        return location;
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final ICancelToken zze(CurrentLocationRequest currentLocationRequest, zzao zzaoVar) {
        Parcel c2 = c();
        zzc.zzc(c2, currentLocationRequest);
        zzc.zzd(c2, zzaoVar);
        Parcel a2 = a(87, c2);
        ICancelToken asInterface = ICancelToken.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final LocationAvailability zzf(String str) {
        Parcel c2 = c();
        c2.writeString(str);
        Parcel a2 = a(34, c2);
        LocationAvailability locationAvailability = (LocationAvailability) zzc.zza(a2, LocationAvailability.CREATOR);
        a2.recycle();
        return locationAvailability;
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzg(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, zzak zzakVar) {
        Parcel c2 = c();
        zzc.zzc(c2, geofencingRequest);
        zzc.zzc(c2, pendingIntent);
        zzc.zzd(c2, zzakVar);
        b(57, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzh(LocationSettingsRequest locationSettingsRequest, zzaq zzaqVar, String str) {
        Parcel c2 = c();
        zzc.zzc(c2, locationSettingsRequest);
        zzc.zzd(c2, zzaqVar);
        c2.writeString(null);
        b(63, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzi(zzai zzaiVar) {
        Parcel c2 = c();
        zzc.zzd(c2, zzaiVar);
        b(67, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzj(LastLocationRequest lastLocationRequest, zzao zzaoVar) {
        Parcel c2 = c();
        zzc.zzc(c2, lastLocationRequest);
        zzc.zzd(c2, zzaoVar);
        b(82, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzk(PendingIntent pendingIntent, IStatusCallback iStatusCallback) {
        Parcel c2 = c();
        zzc.zzc(c2, pendingIntent);
        zzc.zzd(c2, iStatusCallback);
        b(73, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzl(PendingIntent pendingIntent) {
        Parcel c2 = c();
        zzc.zzc(c2, pendingIntent);
        b(6, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzm(com.google.android.gms.location.zzbx zzbxVar, zzak zzakVar) {
        Parcel c2 = c();
        zzc.zzc(c2, zzbxVar);
        zzc.zzd(c2, zzakVar);
        b(74, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzn(PendingIntent pendingIntent, zzak zzakVar, String str) {
        Parcel c2 = c();
        zzc.zzc(c2, pendingIntent);
        zzc.zzd(c2, zzakVar);
        c2.writeString(str);
        b(2, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzo(String[] strArr, zzak zzakVar, String str) {
        Parcel c2 = c();
        c2.writeStringArray(strArr);
        zzc.zzd(c2, zzakVar);
        c2.writeString(str);
        b(3, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzp(PendingIntent pendingIntent, IStatusCallback iStatusCallback) {
        Parcel c2 = c();
        zzc.zzc(c2, pendingIntent);
        zzc.zzd(c2, iStatusCallback);
        b(69, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzq(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent, IStatusCallback iStatusCallback) {
        Parcel c2 = c();
        zzc.zzc(c2, activityTransitionRequest);
        zzc.zzc(c2, pendingIntent);
        zzc.zzd(c2, iStatusCallback);
        b(72, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzr(long j2, boolean z, PendingIntent pendingIntent) {
        Parcel c2 = c();
        c2.writeLong(j2);
        zzc.zzb(c2, true);
        zzc.zzc(c2, pendingIntent);
        b(5, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzs(com.google.android.gms.location.zzl zzlVar, PendingIntent pendingIntent, IStatusCallback iStatusCallback) {
        Parcel c2 = c();
        zzc.zzc(c2, zzlVar);
        zzc.zzc(c2, pendingIntent);
        zzc.zzd(c2, iStatusCallback);
        b(70, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzt(PendingIntent pendingIntent, SleepSegmentRequest sleepSegmentRequest, IStatusCallback iStatusCallback) {
        Parcel c2 = c();
        zzc.zzc(c2, pendingIntent);
        zzc.zzc(c2, sleepSegmentRequest);
        zzc.zzd(c2, iStatusCallback);
        b(79, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzu(Location location) {
        Parcel c2 = c();
        zzc.zzc(c2, location);
        b(13, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzv(Location location, IStatusCallback iStatusCallback) {
        Parcel c2 = c();
        zzc.zzc(c2, location);
        zzc.zzd(c2, iStatusCallback);
        b(85, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzw(boolean z) {
        Parcel c2 = c();
        zzc.zzb(c2, z);
        b(12, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzx(boolean z, IStatusCallback iStatusCallback) {
        Parcel c2 = c();
        zzc.zzb(c2, z);
        zzc.zzd(c2, iStatusCallback);
        b(84, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzy(zzj zzjVar) {
        Parcel c2 = c();
        zzc.zzc(c2, zzjVar);
        b(75, c2);
    }

    @Override // com.google.android.gms.internal.location.zzam
    public final void zzz(zzbh zzbhVar) {
        Parcel c2 = c();
        zzc.zzc(c2, zzbhVar);
        b(59, c2);
    }
}
