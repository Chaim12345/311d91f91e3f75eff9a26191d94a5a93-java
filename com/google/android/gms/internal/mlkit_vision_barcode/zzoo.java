package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* loaded from: classes2.dex */
public final class zzoo implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        int i3 = 0;
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        Point[] pointArr = null;
        zzog zzogVar = null;
        zzoj zzojVar = null;
        zzok zzokVar = null;
        zzom zzomVar = null;
        zzol zzolVar = null;
        zzoh zzohVar = null;
        zzod zzodVar = null;
        zzoe zzoeVar = null;
        zzof zzofVar = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 5:
                    pointArr = (Point[]) SafeParcelReader.createTypedArray(parcel, readHeader, Point.CREATOR);
                    break;
                case 6:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 7:
                    zzogVar = (zzog) SafeParcelReader.createParcelable(parcel, readHeader, zzog.CREATOR);
                    break;
                case 8:
                    zzojVar = (zzoj) SafeParcelReader.createParcelable(parcel, readHeader, zzoj.CREATOR);
                    break;
                case 9:
                    zzokVar = (zzok) SafeParcelReader.createParcelable(parcel, readHeader, zzok.CREATOR);
                    break;
                case 10:
                    zzomVar = (zzom) SafeParcelReader.createParcelable(parcel, readHeader, zzom.CREATOR);
                    break;
                case 11:
                    zzolVar = (zzol) SafeParcelReader.createParcelable(parcel, readHeader, zzol.CREATOR);
                    break;
                case 12:
                    zzohVar = (zzoh) SafeParcelReader.createParcelable(parcel, readHeader, zzoh.CREATOR);
                    break;
                case 13:
                    zzodVar = (zzod) SafeParcelReader.createParcelable(parcel, readHeader, zzod.CREATOR);
                    break;
                case 14:
                    zzoeVar = (zzoe) SafeParcelReader.createParcelable(parcel, readHeader, zzoe.CREATOR);
                    break;
                case 15:
                    zzofVar = (zzof) SafeParcelReader.createParcelable(parcel, readHeader, zzof.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzon(i2, str, str2, bArr, pointArr, i3, zzogVar, zzojVar, zzokVar, zzomVar, zzolVar, zzohVar, zzodVar, zzoeVar, zzofVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzon[i2];
    }
}
