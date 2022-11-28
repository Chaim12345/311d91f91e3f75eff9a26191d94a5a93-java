package com.google.android.gms.safetynet;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "HarmfulAppsDataCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public class HarmfulAppsData extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<HarmfulAppsData> CREATOR = new zzc();
    @SafeParcelable.Field(id = 4)
    public final int apkCategory;
    @NonNull
    @SafeParcelable.Field(id = 2)
    public final String apkPackageName;
    @NonNull
    @SafeParcelable.Field(id = 3)
    public final byte[] apkSha256;

    @SafeParcelable.Constructor
    public HarmfulAppsData(@NonNull @SafeParcelable.Param(id = 2) String str, @NonNull @SafeParcelable.Param(id = 3) byte[] bArr, @SafeParcelable.Param(id = 4) int i2) {
        this.apkPackageName = str;
        this.apkSha256 = bArr;
        this.apkCategory = i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.apkPackageName, false);
        SafeParcelWriter.writeByteArray(parcel, 3, this.apkSha256, false);
        SafeParcelWriter.writeInt(parcel, 4, this.apkCategory);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
