package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class zzy extends zza implements zzaa {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolygonDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzA(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(13, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzB(zzaa zzaaVar) {
        Parcel b2 = b();
        zzc.zzf(b2, zzaaVar);
        Parcel a2 = a(19, b2);
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzC() {
        Parcel a2 = a(22, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzD() {
        Parcel a2 = a(18, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final boolean zzE() {
        Parcel a2 = a(16, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final float zzd() {
        Parcel a2 = a(8, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final float zze() {
        Parcel a2 = a(14, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final int zzf() {
        Parcel a2 = a(12, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final int zzg() {
        Parcel a2 = a(10, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final int zzh() {
        Parcel a2 = a(24, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final int zzi() {
        Parcel a2 = a(20, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final IObjectWrapper zzj() {
        Parcel a2 = a(28, b());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final String zzk() {
        Parcel a2 = a(2, b());
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final List zzl() {
        Parcel a2 = a(6, b());
        ArrayList zzb = zzc.zzb(a2);
        a2.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final List<LatLng> zzm() {
        Parcel a2 = a(4, b());
        ArrayList createTypedArrayList = a2.createTypedArrayList(LatLng.CREATOR);
        a2.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final List<PatternItem> zzn() {
        Parcel a2 = a(26, b());
        ArrayList createTypedArrayList = a2.createTypedArrayList(PatternItem.CREATOR);
        a2.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzo() {
        c(1, b());
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzp(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(21, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzq(int i2) {
        Parcel b2 = b();
        b2.writeInt(i2);
        c(11, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzr(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(17, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzs(List list) {
        Parcel b2 = b();
        b2.writeList(list);
        c(5, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzt(List<LatLng> list) {
        Parcel b2 = b();
        b2.writeTypedList(list);
        c(3, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzu(int i2) {
        Parcel b2 = b();
        b2.writeInt(i2);
        c(9, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzv(int i2) {
        Parcel b2 = b();
        b2.writeInt(i2);
        c(23, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzw(List<PatternItem> list) {
        Parcel b2 = b();
        b2.writeTypedList(list);
        c(25, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzx(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(7, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzy(IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        zzc.zzf(b2, iObjectWrapper);
        c(27, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzaa
    public final void zzz(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(15, b2);
    }
}
