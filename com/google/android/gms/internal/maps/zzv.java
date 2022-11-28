package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
/* loaded from: classes.dex */
public final class zzv extends zza implements zzx {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzA(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(27, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzB() {
        c(11, b());
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final boolean zzC(zzx zzxVar) {
        Parcel b2 = b();
        zzc.zzf(b2, zzxVar);
        Parcel a2 = a(16, b2);
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final boolean zzD() {
        Parcel a2 = a(10, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final boolean zzE() {
        Parcel a2 = a(21, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final boolean zzF() {
        Parcel a2 = a(13, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final boolean zzG() {
        Parcel a2 = a(15, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final float zzd() {
        Parcel a2 = a(26, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final float zze() {
        Parcel a2 = a(23, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final float zzf() {
        Parcel a2 = a(28, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final int zzg() {
        Parcel a2 = a(17, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final IObjectWrapper zzh() {
        Parcel a2 = a(30, b());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final LatLng zzi() {
        Parcel a2 = a(4, b());
        LatLng latLng = (LatLng) zzc.zza(a2, LatLng.CREATOR);
        a2.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final String zzj() {
        Parcel a2 = a(2, b());
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final String zzk() {
        Parcel a2 = a(8, b());
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final String zzl() {
        Parcel a2 = a(6, b());
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzm() {
        c(12, b());
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzn() {
        c(1, b());
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzo(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(25, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzp(float f2, float f3) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        b2.writeFloat(f3);
        c(19, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzq(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(9, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzr(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(20, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzs(IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        zzc.zzf(b2, iObjectWrapper);
        c(18, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzt(float f2, float f3) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        b2.writeFloat(f3);
        c(24, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzu(LatLng latLng) {
        Parcel b2 = b();
        zzc.zzd(b2, latLng);
        c(3, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzv(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(22, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzw(String str) {
        Parcel b2 = b();
        b2.writeString(str);
        c(7, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzx(IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        zzc.zzf(b2, iObjectWrapper);
        c(29, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzy(String str) {
        Parcel b2 = b();
        b2.writeString(str);
        c(5, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzz(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(14, b2);
    }
}
