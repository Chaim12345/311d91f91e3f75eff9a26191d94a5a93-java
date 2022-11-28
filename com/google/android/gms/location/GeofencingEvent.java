package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.location.Geofence;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class GeofencingEvent {
    private final int zza;
    @Geofence.GeofenceTransition
    private final int zzb;
    @Nullable
    private final List zzc;
    @Nullable
    private final Location zzd;

    private GeofencingEvent(int i2, @Geofence.GeofenceTransition int i3, @Nullable List list, @Nullable Location location) {
        this.zza = i2;
        this.zzb = i3;
        this.zzc = list;
        this.zzd = location;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002b  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static GeofencingEvent fromIntent(@NonNull Intent intent) {
        ArrayList arrayList;
        ArrayList arrayList2;
        if (intent == null) {
            return null;
        }
        int intExtra = intent.getIntExtra(Constants.KEY_GMS_ERROR_CODE, -1);
        int intExtra2 = intent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
        if (intExtra2 != -1) {
            if (intExtra2 != 1 && intExtra2 != 2) {
                if (intExtra2 == 4) {
                    intExtra2 = 4;
                }
            }
            arrayList = (ArrayList) intent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
            if (arrayList != null) {
                arrayList2 = null;
            } else {
                arrayList2 = new ArrayList(arrayList.size());
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    byte[] bArr = (byte[]) arrayList.get(i2);
                    Parcel obtain = Parcel.obtain();
                    obtain.unmarshall(bArr, 0, bArr.length);
                    obtain.setDataPosition(0);
                    obtain.recycle();
                    arrayList2.add(com.google.android.gms.internal.location.zzbj.CREATOR.createFromParcel(obtain));
                }
            }
            Location location = (Location) intent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location");
            if (arrayList2 == null || intExtra != -1) {
                return new GeofencingEvent(intExtra, intExtra2, arrayList2, location);
            }
            return null;
        }
        intExtra2 = -1;
        arrayList = (ArrayList) intent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
        if (arrayList != null) {
        }
        Location location2 = (Location) intent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location");
        if (arrayList2 == null) {
        }
        return new GeofencingEvent(intExtra, intExtra2, arrayList2, location2);
    }

    public int getErrorCode() {
        return this.zza;
    }

    @Geofence.GeofenceTransition
    public int getGeofenceTransition() {
        return this.zzb;
    }

    @Nullable
    public List<Geofence> getTriggeringGeofences() {
        return this.zzc;
    }

    @Nullable
    public Location getTriggeringLocation() {
        return this.zzd;
    }

    public boolean hasError() {
        return this.zza != -1;
    }
}
