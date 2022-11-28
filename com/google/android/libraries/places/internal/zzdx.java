package com.google.android.libraries.places.internal;

import android.location.Location;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.RectangularBounds;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes2.dex */
public final class zzdx {
    private static final zzhv zza;

    static {
        zzhu zzhuVar = new zzhu();
        zzhuVar.zza(zzbf.NONE, "NONE");
        zzhuVar.zza(zzbf.PSK, "WPA_PSK");
        zzhuVar.zza(zzbf.EAP, "WPA_EAP");
        zzhuVar.zza(zzbf.OTHER, "SECURED_NONE");
        zza = zzhuVar.zzb();
    }

    @Nullable
    public static Integer zza(@Nullable Location location) {
        if (location == null) {
            return null;
        }
        float accuracy = location.getAccuracy();
        if (!location.hasAccuracy() || accuracy <= 0.0f) {
            return null;
        }
        return Integer.valueOf(Math.round(accuracy * 100.0f));
    }

    @Nullable
    public static String zzb(List list) {
        StringBuilder sb = new StringBuilder();
        Iterator it = list.iterator();
        while (true) {
            String str = null;
            if (!it.hasNext()) {
                break;
            }
            String str2 = (String) it.next();
            if (!TextUtils.isEmpty(str2)) {
                String valueOf = String.valueOf(str2.toLowerCase(Locale.US));
                str = valueOf.length() != 0 ? "country:".concat(valueOf) : new String("country:");
            }
            if (str != null) {
                if (sb.length() != 0) {
                    sb.append('|');
                }
                sb.append(str);
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    @Nullable
    public static String zzc(@Nullable Location location) {
        if (location == null) {
            return null;
        }
        return zzh(location.getLatitude(), location.getLongitude());
    }

    @Nullable
    public static String zzd(@Nullable LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return zzh(latLng.latitude, latLng.longitude);
    }

    @Nullable
    public static String zze(@Nullable LocationBias locationBias) {
        if (locationBias == null) {
            return null;
        }
        if (locationBias instanceof RectangularBounds) {
            return zzi((RectangularBounds) locationBias);
        }
        throw new AssertionError("Unknown LocationBias type.");
    }

    @Nullable
    public static String zzf(@Nullable LocationRestriction locationRestriction) {
        if (locationRestriction == null) {
            return null;
        }
        if (locationRestriction instanceof RectangularBounds) {
            return zzi((RectangularBounds) locationRestriction);
        }
        throw new AssertionError("Unknown LocationRestriction type.");
    }

    public static String zzg(zzhs zzhsVar, int i2) {
        StringBuilder sb = new StringBuilder();
        int size = zzhsVar.size();
        for (int i3 = 0; i3 < size; i3++) {
            zzbg zzbgVar = (zzbg) zzhsVar.get(i3);
            String str = sb.length() > 0 ? "|" : "";
            zzhu zzhuVar = new zzhu();
            zzhuVar.zza("mac", zzbgVar.zzd());
            zzhuVar.zza("strength_dbm", Integer.valueOf(zzbgVar.zzb()));
            zzhuVar.zza("wifi_auth_type", zza.get(zzbgVar.zzc()));
            zzhuVar.zza("is_connected", Boolean.valueOf(zzbgVar.zze()));
            zzhuVar.zza("frequency_mhz", Integer.valueOf(zzbgVar.zza()));
            zzhv zzb = zzhuVar.zzb();
            zzgv zzc = zzgv.zzc(",");
            Iterator<E> it = zzb.entrySet().iterator();
            StringBuilder sb2 = new StringBuilder();
            try {
                zzgu.zza(sb2, it, zzc, "=");
                String sb3 = sb2.toString();
                String concat = sb3.length() != 0 ? str.concat(sb3) : new String(str);
                if (sb.length() + concat.length() > 4000) {
                    break;
                }
                sb.append(concat);
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }
        return sb.toString();
    }

    private static String zzh(double d2, double d3) {
        return String.format(Locale.US, "%.15f,%.15f", Double.valueOf(d2), Double.valueOf(d3));
    }

    private static String zzi(RectangularBounds rectangularBounds) {
        LatLng southwest = rectangularBounds.getSouthwest();
        double d2 = southwest.latitude;
        double d3 = southwest.longitude;
        LatLng northeast = rectangularBounds.getNortheast();
        return String.format(Locale.US, "rectangle:%.15f,%.15f|%.15f,%.15f", Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(northeast.latitude), Double.valueOf(northeast.longitude));
    }
}
