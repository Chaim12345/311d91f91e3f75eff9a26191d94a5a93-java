package com.google.mlkit.vision.barcode.internal;

import android.graphics.Point;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_barcode.zzob;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoc;
import com.google.android.gms.internal.mlkit_vision_barcode.zzod;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoe;
import com.google.android.gms.internal.mlkit_vision_barcode.zzof;
import com.google.android.gms.internal.mlkit_vision_barcode.zzog;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoh;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoi;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzok;
import com.google.android.gms.internal.mlkit_vision_barcode.zzol;
import com.google.android.gms.internal.mlkit_vision_barcode.zzom;
import com.google.android.gms.internal.mlkit_vision_barcode.zzon;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.barcode.common.internal.BarcodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzk implements BarcodeSource {
    private final zzon zza;

    public zzk(zzon zzonVar) {
        this.zza = zzonVar;
    }

    @Nullable
    private static Barcode.CalendarDateTime zza(@Nullable zzoc zzocVar) {
        if (zzocVar == null) {
            return null;
        }
        return new Barcode.CalendarDateTime(zzocVar.zzf(), zzocVar.zzd(), zzocVar.zza(), zzocVar.zzb(), zzocVar.zzc(), zzocVar.zze(), zzocVar.zzh(), zzocVar.zzg());
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Rect getBoundingBox() {
        Point[] zzo = this.zza.zzo();
        if (zzo == null) {
            return null;
        }
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MAX_VALUE;
        int i5 = Integer.MIN_VALUE;
        for (Point point : zzo) {
            i3 = Math.min(i3, point.x);
            i2 = Math.max(i2, point.x);
            i4 = Math.min(i4, point.y);
            i5 = Math.max(i5, point.y);
        }
        return new Rect(i3, i4, i2, i5);
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.CalendarEvent getCalendarEvent() {
        zzod zzc = this.zza.zzc();
        if (zzc != null) {
            return new Barcode.CalendarEvent(zzc.zzg(), zzc.zzc(), zzc.zzd(), zzc.zze(), zzc.zzf(), zza(zzc.zzb()), zza(zzc.zza()));
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.ContactInfo getContactInfo() {
        zzoe zzd = this.zza.zzd();
        if (zzd != null) {
            zzoi zza = zzd.zza();
            Barcode.PersonName personName = zza != null ? new Barcode.PersonName(zza.zzb(), zza.zzf(), zza.zze(), zza.zza(), zza.zzd(), zza.zzc(), zza.zzg()) : null;
            String zzb = zzd.zzb();
            String zzc = zzd.zzc();
            zzoj[] zzf = zzd.zzf();
            ArrayList arrayList = new ArrayList();
            if (zzf != null) {
                for (zzoj zzojVar : zzf) {
                    if (zzojVar != null) {
                        arrayList.add(new Barcode.Phone(zzojVar.zzb(), zzojVar.zza()));
                    }
                }
            }
            zzog[] zze = zzd.zze();
            ArrayList arrayList2 = new ArrayList();
            if (zze != null) {
                for (zzog zzogVar : zze) {
                    if (zzogVar != null) {
                        arrayList2.add(new Barcode.Email(zzogVar.zza(), zzogVar.zzb(), zzogVar.zzd(), zzogVar.zzc()));
                    }
                }
            }
            List asList = zzd.zzg() != null ? Arrays.asList((String[]) Preconditions.checkNotNull(zzd.zzg())) : new ArrayList();
            zzob[] zzd2 = zzd.zzd();
            ArrayList arrayList3 = new ArrayList();
            if (zzd2 != null) {
                for (zzob zzobVar : zzd2) {
                    if (zzobVar != null) {
                        arrayList3.add(new Barcode.Address(zzobVar.zza(), zzobVar.zzb()));
                    }
                }
            }
            return new Barcode.ContactInfo(personName, zzb, zzc, arrayList, arrayList2, asList, arrayList3);
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Point[] getCornerPoints() {
        return this.zza.zzo();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final String getDisplayValue() {
        return this.zza.zzl();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.DriverLicense getDriverLicense() {
        zzof zze = this.zza.zze();
        if (zze != null) {
            return new Barcode.DriverLicense(zze.zzf(), zze.zzh(), zze.zzn(), zze.zzl(), zze.zzi(), zze.zzc(), zze.zza(), zze.zzb(), zze.zzd(), zze.zzm(), zze.zzj(), zze.zzg(), zze.zze(), zze.zzk());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.Email getEmail() {
        zzog zzf = this.zza.zzf();
        if (zzf == null) {
            return null;
        }
        return new Barcode.Email(zzf.zza(), zzf.zzb(), zzf.zzd(), zzf.zzc());
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final int getFormat() {
        return this.zza.zza();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.GeoPoint getGeoPoint() {
        zzoh zzg = this.zza.zzg();
        if (zzg != null) {
            return new Barcode.GeoPoint(zzg.zza(), zzg.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.Phone getPhone() {
        zzoj zzh = this.zza.zzh();
        if (zzh != null) {
            return new Barcode.Phone(zzh.zzb(), zzh.zza());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final byte[] getRawBytes() {
        return this.zza.zzn();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final String getRawValue() {
        return this.zza.zzm();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.Sms getSms() {
        zzok zzi = this.zza.zzi();
        if (zzi != null) {
            return new Barcode.Sms(zzi.zza(), zzi.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.UrlBookmark getUrl() {
        zzol zzj = this.zza.zzj();
        if (zzj != null) {
            return new Barcode.UrlBookmark(zzj.zza(), zzj.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final int getValueType() {
        return this.zza.zzb();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    @Nullable
    public final Barcode.WiFi getWifi() {
        zzom zzk = this.zza.zzk();
        if (zzk != null) {
            return new Barcode.WiFi(zzk.zzc(), zzk.zzb(), zzk.zza());
        }
        return null;
    }
}
