package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "StringToIntConverterEntryCreator")
/* loaded from: classes.dex */
public final class zac extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zac> CREATOR = new zae();
    @SafeParcelable.VersionField(id = 1)

    /* renamed from: a  reason: collision with root package name */
    final int f5799a;
    @SafeParcelable.Field(id = 2)

    /* renamed from: b  reason: collision with root package name */
    final String f5800b;
    @SafeParcelable.Field(id = 3)

    /* renamed from: c  reason: collision with root package name */
    final int f5801c;

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public zac(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i3) {
        this.f5799a = i2;
        this.f5800b = str;
        this.f5801c = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zac(String str, int i2) {
        this.f5799a = 1;
        this.f5800b = str;
        this.f5801c = i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.f5799a);
        SafeParcelWriter.writeString(parcel, 2, this.f5800b, false);
        SafeParcelWriter.writeInt(parcel, 3, this.f5801c);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
