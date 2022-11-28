package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "ConnectionInfoCreator")
/* loaded from: classes.dex */
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();
    @SafeParcelable.Field(id = 1)

    /* renamed from: a  reason: collision with root package name */
    Bundle f5790a;
    @SafeParcelable.Field(id = 2)

    /* renamed from: b  reason: collision with root package name */
    Feature[] f5791b;
    @SafeParcelable.Field(defaultValue = "0", id = 3)

    /* renamed from: c  reason: collision with root package name */
    int f5792c;
    @Nullable
    @SafeParcelable.Field(id = 4)

    /* renamed from: d  reason: collision with root package name */
    ConnectionTelemetryConfiguration f5793d;

    public zzj() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public zzj(@SafeParcelable.Param(id = 1) Bundle bundle, @SafeParcelable.Param(id = 2) Feature[] featureArr, @SafeParcelable.Param(id = 3) int i2, @Nullable @SafeParcelable.Param(id = 4) ConnectionTelemetryConfiguration connectionTelemetryConfiguration) {
        this.f5790a = bundle;
        this.f5791b = featureArr;
        this.f5792c = i2;
        this.f5793d = connectionTelemetryConfiguration;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.f5790a, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.f5791b, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, this.f5792c);
        SafeParcelWriter.writeParcelable(parcel, 4, this.f5793d, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
