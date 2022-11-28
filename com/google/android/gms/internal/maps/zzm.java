package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
/* loaded from: classes.dex */
public final class zzm extends zza implements zzo {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final boolean zzA() {
        Parcel a2 = a(23, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final boolean zzB() {
        Parcel a2 = a(16, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final float zzd() {
        Parcel a2 = a(12, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final float zze() {
        Parcel a2 = a(8, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final float zzf() {
        Parcel a2 = a(18, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final float zzg() {
        Parcel a2 = a(7, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final float zzh() {
        Parcel a2 = a(14, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final int zzi() {
        Parcel a2 = a(20, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final IObjectWrapper zzj() {
        Parcel a2 = a(25, b());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final LatLng zzk() {
        Parcel a2 = a(4, b());
        LatLng latLng = (LatLng) zzc.zza(a2, LatLng.CREATOR);
        a2.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final LatLngBounds zzl() {
        Parcel a2 = a(10, b());
        LatLngBounds latLngBounds = (LatLngBounds) zzc.zza(a2, LatLngBounds.CREATOR);
        a2.recycle();
        return latLngBounds;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final String zzm() {
        Parcel a2 = a(2, b());
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzn() {
        c(1, b());
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzo(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(11, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzp(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(22, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzq(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(5, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzr(float f2, float f3) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        b2.writeFloat(f3);
        c(6, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzs(IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        zzc.zzf(b2, iObjectWrapper);
        c(21, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzt(LatLng latLng) {
        Parcel b2 = b();
        zzc.zzd(b2, latLng);
        c(3, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzu(LatLngBounds latLngBounds) {
        Parcel b2 = b();
        zzc.zzd(b2, latLngBounds);
        c(9, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzv(IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        zzc.zzf(b2, iObjectWrapper);
        c(24, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzw(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(17, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzx(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(15, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final void zzy(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(13, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzo
    public final boolean zzz(zzo zzoVar) {
        Parcel b2 = b();
        zzc.zzf(b2, zzoVar);
        Parcel a2 = a(19, b2);
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }
}
