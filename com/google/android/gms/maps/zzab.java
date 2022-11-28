package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;
/* loaded from: classes2.dex */
public final class zzab implements Parcelable.Creator<GoogleMapOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ GoogleMapOptions createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        CameraPosition cameraPosition = null;
        Float f2 = null;
        Float f3 = null;
        LatLngBounds latLngBounds = null;
        Integer num = null;
        String str = null;
        byte b2 = -1;
        byte b3 = -1;
        byte b4 = -1;
        byte b5 = -1;
        byte b6 = -1;
        byte b7 = -1;
        byte b8 = -1;
        byte b9 = -1;
        byte b10 = -1;
        byte b11 = -1;
        byte b12 = -1;
        byte b13 = -1;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    b2 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 3:
                    b3 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 4:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    cameraPosition = (CameraPosition) SafeParcelReader.createParcelable(parcel, readHeader, CameraPosition.CREATOR);
                    break;
                case 6:
                    b4 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 7:
                    b5 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 8:
                    b6 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 9:
                    b7 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 10:
                    b8 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 11:
                    b9 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 12:
                    b10 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 13:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 14:
                    b11 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 15:
                    b12 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 16:
                    f2 = SafeParcelReader.readFloatObject(parcel, readHeader);
                    break;
                case 17:
                    f3 = SafeParcelReader.readFloatObject(parcel, readHeader);
                    break;
                case 18:
                    latLngBounds = (LatLngBounds) SafeParcelReader.createParcelable(parcel, readHeader, LatLngBounds.CREATOR);
                    break;
                case 19:
                    b13 = SafeParcelReader.readByte(parcel, readHeader);
                    break;
                case 20:
                    num = SafeParcelReader.readIntegerObject(parcel, readHeader);
                    break;
                case 21:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GoogleMapOptions(b2, b3, i2, cameraPosition, b4, b5, b6, b7, b8, b9, b10, b11, b12, f2, f3, latLngBounds, b13, num, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleMapOptions[] newArray(int i2) {
        return new GoogleMapOptions[i2];
    }
}
