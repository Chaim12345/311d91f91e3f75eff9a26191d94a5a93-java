package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* loaded from: classes2.dex */
public final class zzot implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzoi zzoiVar = null;
        String str = null;
        String str2 = null;
        zzoj[] zzojVarArr = null;
        zzog[] zzogVarArr = null;
        String[] strArr = null;
        zzob[] zzobVarArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    zzoiVar = (zzoi) SafeParcelReader.createParcelable(parcel, readHeader, zzoi.CREATOR);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    zzojVarArr = (zzoj[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzoj.CREATOR);
                    break;
                case 5:
                    zzogVarArr = (zzog[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzog.CREATOR);
                    break;
                case 6:
                    strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 7:
                    zzobVarArr = (zzob[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzob.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzoe(zzoiVar, str, str2, zzojVarArr, zzogVarArr, strArr, zzobVarArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzoe[i2];
    }
}
