package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class zzab extends zza implements zzad {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzab(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzA(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(9, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final boolean zzB(zzad zzadVar) {
        Parcel b2 = b();
        zzc.zzf(b2, zzadVar);
        Parcel a2 = a(15, b2);
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final boolean zzC() {
        Parcel a2 = a(18, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final boolean zzD() {
        Parcel a2 = a(14, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final boolean zzE() {
        Parcel a2 = a(12, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final float zzd() {
        Parcel a2 = a(6, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final float zze() {
        Parcel a2 = a(10, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final int zzf() {
        Parcel a2 = a(8, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final int zzg() {
        Parcel a2 = a(24, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final int zzh() {
        Parcel a2 = a(16, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final IObjectWrapper zzi() {
        Parcel a2 = a(28, b());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final Cap zzj() {
        Parcel a2 = a(22, b());
        Cap cap = (Cap) zzc.zza(a2, Cap.CREATOR);
        a2.recycle();
        return cap;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final Cap zzk() {
        Parcel a2 = a(20, b());
        Cap cap = (Cap) zzc.zza(a2, Cap.CREATOR);
        a2.recycle();
        return cap;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final String zzl() {
        Parcel a2 = a(2, b());
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final List<PatternItem> zzm() {
        Parcel a2 = a(26, b());
        ArrayList createTypedArrayList = a2.createTypedArrayList(PatternItem.CREATOR);
        a2.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final List<LatLng> zzn() {
        Parcel a2 = a(4, b());
        ArrayList createTypedArrayList = a2.createTypedArrayList(LatLng.CREATOR);
        a2.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzo() {
        c(1, b());
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzp(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(17, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzq(int i2) {
        Parcel b2 = b();
        b2.writeInt(i2);
        c(7, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzr(Cap cap) {
        Parcel b2 = b();
        zzc.zzd(b2, cap);
        c(21, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzs(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(13, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzt(int i2) {
        Parcel b2 = b();
        b2.writeInt(i2);
        c(23, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzu(List<PatternItem> list) {
        Parcel b2 = b();
        b2.writeTypedList(list);
        c(25, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzv(List<LatLng> list) {
        Parcel b2 = b();
        b2.writeTypedList(list);
        c(3, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzw(Cap cap) {
        Parcel b2 = b();
        zzc.zzd(b2, cap);
        c(19, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzx(IObjectWrapper iObjectWrapper) {
        Parcel b2 = b();
        zzc.zzf(b2, iObjectWrapper);
        c(27, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzy(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(11, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzad
    public final void zzz(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(5, b2);
    }
}
