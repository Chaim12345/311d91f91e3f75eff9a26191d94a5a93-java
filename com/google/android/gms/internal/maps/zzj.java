package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class zzj extends zza implements zzl {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ICircleDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final boolean zzA() {
        Parcel a2 = a(16, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final double zzd() {
        Parcel a2 = a(6, b());
        double readDouble = a2.readDouble();
        a2.recycle();
        return readDouble;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final float zze() {
        Parcel a2 = a(8, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final float zzf() {
        Parcel a2 = a(14, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final int zzg() {
        Parcel a2 = a(12, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final int zzh() {
        Parcel a2 = a(10, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final int zzi() {
        Parcel a2 = a(18, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final IObjectWrapper zzj() {
        Parcel a2 = a(24, b());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final LatLng zzk() {
        Parcel a2 = a(4, b());
        LatLng latLng = (LatLng) zzc.zza(a2, LatLng.CREATOR);
        a2.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final String zzl() {
        Parcel a2 = a(2, b());
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final List<PatternItem> zzm() {
        Parcel a2 = a(22, b());
        ArrayList createTypedArrayList = a2.createTypedArrayList(PatternItem.CREATOR);
        a2.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final void zzn() {
        c(1, b());
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final void zzo(LatLng latLng) {
        Parcel b2 = b();
        zzc.zzd(b2, latLng);
        c(3, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final void zzp(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(19, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final void zzq(int i2) {
        Parcel b2 = b();
        b2.writeInt(i2);
        c(11, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final void zzr(double d2) {
        Parcel b2 = b();
        b2.writeDouble(d2);
        c(5, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final void zzs(int i2) {
        Parcel b2 = b();
        b2.writeInt(i2);
        c(9, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final void zzt(List<PatternItem> list) {
        Parcel b2 = b();
        b2.writeTypedList(list);
        c(21, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final void zzu(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(7, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final void zzv(IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        zzc.zzf(b2, iObjectWrapper);
        c(23, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final void zzw(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(15, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final void zzx(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(13, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final boolean zzy(zzl zzlVar) {
        Parcel b2 = b();
        zzc.zzf(b2, zzlVar);
        Parcel a2 = a(17, b2);
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzl
    public final boolean zzz() {
        Parcel a2 = a(20, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }
}
