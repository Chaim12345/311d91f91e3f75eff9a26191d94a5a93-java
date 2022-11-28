package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class zzae extends zza implements zzag {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzae(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final float zzd() {
        Parcel a2 = a(13, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final float zze() {
        Parcel a2 = a(5, b());
        float readFloat = a2.readFloat();
        a2.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final int zzf() {
        Parcel a2 = a(9, b());
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final String zzg() {
        Parcel a2 = a(3, b());
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzh() {
        c(2, b());
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzi() {
        c(1, b());
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzj(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(10, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzk(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(12, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzl(boolean z) {
        Parcel b2 = b();
        zzc.zzc(b2, z);
        c(6, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final void zzm(float f2) {
        Parcel b2 = b();
        b2.writeFloat(f2);
        c(4, b2);
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final boolean zzn(zzag zzagVar) {
        Parcel b2 = b();
        zzc.zzf(b2, zzagVar);
        Parcel a2 = a(8, b2);
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final boolean zzo() {
        Parcel a2 = a(11, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.internal.maps.zzag
    public final boolean zzp() {
        Parcel a2 = a(7, b());
        boolean zzg = zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }
}
