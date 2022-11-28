package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes2.dex */
public final class zzby extends com.google.android.gms.internal.maps.zza implements IUiSettingsDelegate {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzby(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isCompassEnabled() {
        Parcel a2 = a(10, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isIndoorLevelPickerEnabled() {
        Parcel a2 = a(17, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isMapToolbarEnabled() {
        Parcel a2 = a(19, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isMyLocationButtonEnabled() {
        Parcel a2 = a(11, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isRotateGesturesEnabled() {
        Parcel a2 = a(15, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isScrollGesturesEnabled() {
        Parcel a2 = a(12, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isScrollGesturesEnabledDuringRotateOrZoom() {
        Parcel a2 = a(21, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isTiltGesturesEnabled() {
        Parcel a2 = a(14, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isZoomControlsEnabled() {
        Parcel a2 = a(9, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isZoomGesturesEnabled() {
        Parcel a2 = a(13, b());
        boolean zzg = com.google.android.gms.internal.maps.zzc.zzg(a2);
        a2.recycle();
        return zzg;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setAllGesturesEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(8, b2);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setCompassEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(2, b2);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setIndoorLevelPickerEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(16, b2);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setMapToolbarEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(18, b2);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setMyLocationButtonEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(3, b2);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setRotateGesturesEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(7, b2);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setScrollGesturesEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(4, b2);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setScrollGesturesEnabledDuringRotateOrZoom(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(20, b2);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setTiltGesturesEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(6, b2);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setZoomControlsEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(1, b2);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setZoomGesturesEnabled(boolean z) {
        Parcel b2 = b();
        com.google.android.gms.internal.maps.zzc.zzc(b2, z);
        c(5, b2);
    }
}
