package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.psa.mym.mycitroenconnect.common.AppConstants;
@SafeParcelable.Class(creator = "GoogleMapOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new zzab();
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getZOrderOnTopForParcel", id = 2, type = "byte")
    private Boolean zza;
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getUseViewLifecycleInFragmentForParcel", id = 3, type = "byte")
    private Boolean zzb;
    @SafeParcelable.Field(getter = "getMapType", id = 4)
    private int zzc;
    @Nullable
    @SafeParcelable.Field(getter = "getCamera", id = 5)
    private CameraPosition zzd;
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getZoomControlsEnabledForParcel", id = 6, type = "byte")
    private Boolean zze;
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getCompassEnabledForParcel", id = 7, type = "byte")
    private Boolean zzf;
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getScrollGesturesEnabledForParcel", id = 8, type = "byte")
    private Boolean zzg;
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getZoomGesturesEnabledForParcel", id = 9, type = "byte")
    private Boolean zzh;
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getTiltGesturesEnabledForParcel", id = 10, type = "byte")
    private Boolean zzi;
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getRotateGesturesEnabledForParcel", id = 11, type = "byte")
    private Boolean zzj;
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getLiteModeForParcel", id = 12, type = "byte")
    private Boolean zzk;
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getMapToolbarEnabledForParcel", id = 14, type = "byte")
    private Boolean zzl;
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getAmbientEnabledForParcel", id = 15, type = "byte")
    private Boolean zzm;
    @Nullable
    @SafeParcelable.Field(getter = "getMinZoomPreference", id = 16)
    private Float zzn;
    @Nullable
    @SafeParcelable.Field(getter = "getMaxZoomPreference", id = 17)
    private Float zzo;
    @Nullable
    @SafeParcelable.Field(getter = "getLatLngBoundsForCameraTarget", id = 18)
    private LatLngBounds zzp;
    @Nullable
    @SafeParcelable.Field(defaultValue = AppConstants.DEFAULT_VAL_SPEED_DLG, getter = "getScrollGesturesEnabledDuringRotateOrZoomForParcel", id = 19, type = "byte")
    private Boolean zzq;
    @Nullable
    @ColorInt
    @SafeParcelable.Field(getter = "getBackgroundColor", id = 20)
    private Integer zzr;
    @Nullable
    @SafeParcelable.Field(getter = "getMapId", id = 21)
    private String zzs;

    public GoogleMapOptions() {
        this.zzc = -1;
        this.zzn = null;
        this.zzo = null;
        this.zzp = null;
        this.zzr = null;
        this.zzs = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public GoogleMapOptions(@SafeParcelable.Param(id = 2) byte b2, @SafeParcelable.Param(id = 3) byte b3, @SafeParcelable.Param(id = 4) int i2, @Nullable @SafeParcelable.Param(id = 5) CameraPosition cameraPosition, @SafeParcelable.Param(id = 6) byte b4, @SafeParcelable.Param(id = 7) byte b5, @SafeParcelable.Param(id = 8) byte b6, @SafeParcelable.Param(id = 9) byte b7, @SafeParcelable.Param(id = 10) byte b8, @SafeParcelable.Param(id = 11) byte b9, @SafeParcelable.Param(id = 12) byte b10, @SafeParcelable.Param(id = 14) byte b11, @SafeParcelable.Param(id = 15) byte b12, @Nullable @SafeParcelable.Param(id = 16) Float f2, @Nullable @SafeParcelable.Param(id = 17) Float f3, @Nullable @SafeParcelable.Param(id = 18) LatLngBounds latLngBounds, @SafeParcelable.Param(id = 19) byte b13, @Nullable @SafeParcelable.Param(id = 20) @ColorInt Integer num, @Nullable @SafeParcelable.Param(id = 21) String str) {
        this.zzc = -1;
        this.zzn = null;
        this.zzo = null;
        this.zzp = null;
        this.zzr = null;
        this.zzs = null;
        this.zza = com.google.android.gms.maps.internal.zza.zzb(b2);
        this.zzb = com.google.android.gms.maps.internal.zza.zzb(b3);
        this.zzc = i2;
        this.zzd = cameraPosition;
        this.zze = com.google.android.gms.maps.internal.zza.zzb(b4);
        this.zzf = com.google.android.gms.maps.internal.zza.zzb(b5);
        this.zzg = com.google.android.gms.maps.internal.zza.zzb(b6);
        this.zzh = com.google.android.gms.maps.internal.zza.zzb(b7);
        this.zzi = com.google.android.gms.maps.internal.zza.zzb(b8);
        this.zzj = com.google.android.gms.maps.internal.zza.zzb(b9);
        this.zzk = com.google.android.gms.maps.internal.zza.zzb(b10);
        this.zzl = com.google.android.gms.maps.internal.zza.zzb(b11);
        this.zzm = com.google.android.gms.maps.internal.zza.zzb(b12);
        this.zzn = f2;
        this.zzo = f3;
        this.zzp = latLngBounds;
        this.zzq = com.google.android.gms.maps.internal.zza.zzb(b13);
        this.zzr = num;
        this.zzs = str;
    }

    @Nullable
    public static GoogleMapOptions createFromAttributes(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        String string;
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        int i2 = R.styleable.MapAttrs_mapType;
        if (obtainAttributes.hasValue(i2)) {
            googleMapOptions.mapType(obtainAttributes.getInt(i2, -1));
        }
        int i3 = R.styleable.MapAttrs_zOrderOnTop;
        if (obtainAttributes.hasValue(i3)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(i3, false));
        }
        int i4 = R.styleable.MapAttrs_useViewLifecycle;
        if (obtainAttributes.hasValue(i4)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(i4, false));
        }
        int i5 = R.styleable.MapAttrs_uiCompass;
        if (obtainAttributes.hasValue(i5)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(i5, true));
        }
        int i6 = R.styleable.MapAttrs_uiRotateGestures;
        if (obtainAttributes.hasValue(i6)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(i6, true));
        }
        int i7 = R.styleable.MapAttrs_uiScrollGesturesDuringRotateOrZoom;
        if (obtainAttributes.hasValue(i7)) {
            googleMapOptions.scrollGesturesEnabledDuringRotateOrZoom(obtainAttributes.getBoolean(i7, true));
        }
        int i8 = R.styleable.MapAttrs_uiScrollGestures;
        if (obtainAttributes.hasValue(i8)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(i8, true));
        }
        int i9 = R.styleable.MapAttrs_uiTiltGestures;
        if (obtainAttributes.hasValue(i9)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(i9, true));
        }
        int i10 = R.styleable.MapAttrs_uiZoomGestures;
        if (obtainAttributes.hasValue(i10)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(i10, true));
        }
        int i11 = R.styleable.MapAttrs_uiZoomControls;
        if (obtainAttributes.hasValue(i11)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(i11, true));
        }
        int i12 = R.styleable.MapAttrs_liteMode;
        if (obtainAttributes.hasValue(i12)) {
            googleMapOptions.liteMode(obtainAttributes.getBoolean(i12, false));
        }
        int i13 = R.styleable.MapAttrs_uiMapToolbar;
        if (obtainAttributes.hasValue(i13)) {
            googleMapOptions.mapToolbarEnabled(obtainAttributes.getBoolean(i13, true));
        }
        int i14 = R.styleable.MapAttrs_ambientEnabled;
        if (obtainAttributes.hasValue(i14)) {
            googleMapOptions.ambientEnabled(obtainAttributes.getBoolean(i14, false));
        }
        int i15 = R.styleable.MapAttrs_cameraMinZoomPreference;
        if (obtainAttributes.hasValue(i15)) {
            googleMapOptions.minZoomPreference(obtainAttributes.getFloat(i15, Float.NEGATIVE_INFINITY));
        }
        if (obtainAttributes.hasValue(i15)) {
            googleMapOptions.maxZoomPreference(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
        }
        TypedArray obtainAttributes2 = context.getResources().obtainAttributes(attributeSet, new int[]{zzc(context, "backgroundColor"), zzc(context, "mapId")});
        if (obtainAttributes2.hasValue(0)) {
            googleMapOptions.backgroundColor(Integer.valueOf(obtainAttributes2.getColor(0, 0)));
        }
        if (obtainAttributes2.hasValue(1) && (string = obtainAttributes2.getString(1)) != null && !string.isEmpty()) {
            googleMapOptions.mapId(string);
        }
        obtainAttributes2.recycle();
        googleMapOptions.latLngBoundsForCameraTarget(zzb(context, attributeSet));
        googleMapOptions.camera(zza(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    @Nullable
    public static CameraPosition zza(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        int i2 = R.styleable.MapAttrs_cameraTargetLat;
        float f2 = obtainAttributes.hasValue(i2) ? obtainAttributes.getFloat(i2, 0.0f) : 0.0f;
        int i3 = R.styleable.MapAttrs_cameraTargetLng;
        LatLng latLng = new LatLng(f2, obtainAttributes.hasValue(i3) ? obtainAttributes.getFloat(i3, 0.0f) : 0.0f);
        CameraPosition.Builder builder = CameraPosition.builder();
        builder.target(latLng);
        int i4 = R.styleable.MapAttrs_cameraZoom;
        if (obtainAttributes.hasValue(i4)) {
            builder.zoom(obtainAttributes.getFloat(i4, 0.0f));
        }
        int i5 = R.styleable.MapAttrs_cameraBearing;
        if (obtainAttributes.hasValue(i5)) {
            builder.bearing(obtainAttributes.getFloat(i5, 0.0f));
        }
        int i6 = R.styleable.MapAttrs_cameraTilt;
        if (obtainAttributes.hasValue(i6)) {
            builder.tilt(obtainAttributes.getFloat(i6, 0.0f));
        }
        obtainAttributes.recycle();
        return builder.build();
    }

    @Nullable
    public static LatLngBounds zzb(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        int i2 = R.styleable.MapAttrs_latLngBoundsSouthWestLatitude;
        Float valueOf = obtainAttributes.hasValue(i2) ? Float.valueOf(obtainAttributes.getFloat(i2, 0.0f)) : null;
        int i3 = R.styleable.MapAttrs_latLngBoundsSouthWestLongitude;
        Float valueOf2 = obtainAttributes.hasValue(i3) ? Float.valueOf(obtainAttributes.getFloat(i3, 0.0f)) : null;
        int i4 = R.styleable.MapAttrs_latLngBoundsNorthEastLatitude;
        Float valueOf3 = obtainAttributes.hasValue(i4) ? Float.valueOf(obtainAttributes.getFloat(i4, 0.0f)) : null;
        int i5 = R.styleable.MapAttrs_latLngBoundsNorthEastLongitude;
        Float valueOf4 = obtainAttributes.hasValue(i5) ? Float.valueOf(obtainAttributes.getFloat(i5, 0.0f)) : null;
        obtainAttributes.recycle();
        if (valueOf == null || valueOf2 == null || valueOf3 == null || valueOf4 == null) {
            return null;
        }
        return new LatLngBounds(new LatLng(valueOf.floatValue(), valueOf2.floatValue()), new LatLng(valueOf3.floatValue(), valueOf4.floatValue()));
    }

    private static int zzc(Context context, String str) {
        return context.getResources().getIdentifier(str, "attr", context.getPackageName());
    }

    @NonNull
    public GoogleMapOptions ambientEnabled(boolean z) {
        this.zzm = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions backgroundColor(@Nullable @ColorInt Integer num) {
        this.zzr = num;
        return this;
    }

    @NonNull
    public GoogleMapOptions camera(@Nullable CameraPosition cameraPosition) {
        this.zzd = cameraPosition;
        return this;
    }

    @NonNull
    public GoogleMapOptions compassEnabled(boolean z) {
        this.zzf = Boolean.valueOf(z);
        return this;
    }

    @Nullable
    public Boolean getAmbientEnabled() {
        return this.zzm;
    }

    @Nullable
    @ColorInt
    public Integer getBackgroundColor() {
        return this.zzr;
    }

    @Nullable
    public CameraPosition getCamera() {
        return this.zzd;
    }

    @Nullable
    public Boolean getCompassEnabled() {
        return this.zzf;
    }

    @Nullable
    public LatLngBounds getLatLngBoundsForCameraTarget() {
        return this.zzp;
    }

    @Nullable
    public Boolean getLiteMode() {
        return this.zzk;
    }

    @Nullable
    public String getMapId() {
        return this.zzs;
    }

    @Nullable
    public Boolean getMapToolbarEnabled() {
        return this.zzl;
    }

    public int getMapType() {
        return this.zzc;
    }

    @Nullable
    public Float getMaxZoomPreference() {
        return this.zzo;
    }

    @Nullable
    public Float getMinZoomPreference() {
        return this.zzn;
    }

    @Nullable
    public Boolean getRotateGesturesEnabled() {
        return this.zzj;
    }

    @Nullable
    public Boolean getScrollGesturesEnabled() {
        return this.zzg;
    }

    @Nullable
    public Boolean getScrollGesturesEnabledDuringRotateOrZoom() {
        return this.zzq;
    }

    @Nullable
    public Boolean getTiltGesturesEnabled() {
        return this.zzi;
    }

    @Nullable
    public Boolean getUseViewLifecycleInFragment() {
        return this.zzb;
    }

    @Nullable
    public Boolean getZOrderOnTop() {
        return this.zza;
    }

    @Nullable
    public Boolean getZoomControlsEnabled() {
        return this.zze;
    }

    @Nullable
    public Boolean getZoomGesturesEnabled() {
        return this.zzh;
    }

    @NonNull
    public GoogleMapOptions latLngBoundsForCameraTarget(@Nullable LatLngBounds latLngBounds) {
        this.zzp = latLngBounds;
        return this;
    }

    @NonNull
    public GoogleMapOptions liteMode(boolean z) {
        this.zzk = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions mapId(@NonNull String str) {
        this.zzs = str;
        return this;
    }

    @NonNull
    public GoogleMapOptions mapToolbarEnabled(boolean z) {
        this.zzl = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions mapType(int i2) {
        this.zzc = i2;
        return this;
    }

    @NonNull
    public GoogleMapOptions maxZoomPreference(float f2) {
        this.zzo = Float.valueOf(f2);
        return this;
    }

    @NonNull
    public GoogleMapOptions minZoomPreference(float f2) {
        this.zzn = Float.valueOf(f2);
        return this;
    }

    @NonNull
    public GoogleMapOptions rotateGesturesEnabled(boolean z) {
        this.zzj = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions scrollGesturesEnabled(boolean z) {
        this.zzg = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions scrollGesturesEnabledDuringRotateOrZoom(boolean z) {
        this.zzq = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions tiltGesturesEnabled(boolean z) {
        this.zzi = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("MapType", Integer.valueOf(this.zzc)).add("LiteMode", this.zzk).add("Camera", this.zzd).add("CompassEnabled", this.zzf).add("ZoomControlsEnabled", this.zze).add("ScrollGesturesEnabled", this.zzg).add("ZoomGesturesEnabled", this.zzh).add("TiltGesturesEnabled", this.zzi).add("RotateGesturesEnabled", this.zzj).add("ScrollGesturesEnabledDuringRotateOrZoom", this.zzq).add("MapToolbarEnabled", this.zzl).add("AmbientEnabled", this.zzm).add("MinZoomPreference", this.zzn).add("MaxZoomPreference", this.zzo).add("BackgroundColor", this.zzr).add("LatLngBoundsForCameraTarget", this.zzp).add("ZOrderOnTop", this.zza).add("UseViewLifecycleInFragment", this.zzb).toString();
    }

    @NonNull
    public GoogleMapOptions useViewLifecycleInFragment(boolean z) {
        this.zzb = Boolean.valueOf(z);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByte(parcel, 2, com.google.android.gms.maps.internal.zza.zza(this.zza));
        SafeParcelWriter.writeByte(parcel, 3, com.google.android.gms.maps.internal.zza.zza(this.zzb));
        SafeParcelWriter.writeInt(parcel, 4, getMapType());
        SafeParcelWriter.writeParcelable(parcel, 5, getCamera(), i2, false);
        SafeParcelWriter.writeByte(parcel, 6, com.google.android.gms.maps.internal.zza.zza(this.zze));
        SafeParcelWriter.writeByte(parcel, 7, com.google.android.gms.maps.internal.zza.zza(this.zzf));
        SafeParcelWriter.writeByte(parcel, 8, com.google.android.gms.maps.internal.zza.zza(this.zzg));
        SafeParcelWriter.writeByte(parcel, 9, com.google.android.gms.maps.internal.zza.zza(this.zzh));
        SafeParcelWriter.writeByte(parcel, 10, com.google.android.gms.maps.internal.zza.zza(this.zzi));
        SafeParcelWriter.writeByte(parcel, 11, com.google.android.gms.maps.internal.zza.zza(this.zzj));
        SafeParcelWriter.writeByte(parcel, 12, com.google.android.gms.maps.internal.zza.zza(this.zzk));
        SafeParcelWriter.writeByte(parcel, 14, com.google.android.gms.maps.internal.zza.zza(this.zzl));
        SafeParcelWriter.writeByte(parcel, 15, com.google.android.gms.maps.internal.zza.zza(this.zzm));
        SafeParcelWriter.writeFloatObject(parcel, 16, getMinZoomPreference(), false);
        SafeParcelWriter.writeFloatObject(parcel, 17, getMaxZoomPreference(), false);
        SafeParcelWriter.writeParcelable(parcel, 18, getLatLngBoundsForCameraTarget(), i2, false);
        SafeParcelWriter.writeByte(parcel, 19, com.google.android.gms.maps.internal.zza.zza(this.zzq));
        SafeParcelWriter.writeIntegerObject(parcel, 20, getBackgroundColor(), false);
        SafeParcelWriter.writeString(parcel, 21, getMapId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public GoogleMapOptions zOrderOnTop(boolean z) {
        this.zza = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions zoomControlsEnabled(boolean z) {
        this.zze = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions zoomGesturesEnabled(boolean z) {
        this.zzh = Boolean.valueOf(z);
        return this;
    }
}
