package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "DeviceOrientationRequestInternalCreator")
/* loaded from: classes.dex */
public final class zzh extends AbstractSafeParcelable {
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_DEVICE_ORIENTATION_REQUEST", id = 1)

    /* renamed from: a  reason: collision with root package name */
    final com.google.android.gms.location.zzw f5895a;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_CLIENTS", id = 2)

    /* renamed from: b  reason: collision with root package name */
    final List f5896b;
    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 3)

    /* renamed from: c  reason: collision with root package name */
    final String f5897c;
    @VisibleForTesting

    /* renamed from: d  reason: collision with root package name */
    static final List f5893d = Collections.emptyList();

    /* renamed from: e  reason: collision with root package name */
    static final com.google.android.gms.location.zzw f5894e = new com.google.android.gms.location.zzw();
    public static final Parcelable.Creator<zzh> CREATOR = new zzi();

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public zzh(@SafeParcelable.Param(id = 1) com.google.android.gms.location.zzw zzwVar, @SafeParcelable.Param(id = 2) List list, @SafeParcelable.Param(id = 3) String str) {
        this.f5895a = zzwVar;
        this.f5896b = list;
        this.f5897c = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzh) {
            zzh zzhVar = (zzh) obj;
            return Objects.equal(this.f5895a, zzhVar.f5895a) && Objects.equal(this.f5896b, zzhVar.f5896b) && Objects.equal(this.f5897c, zzhVar.f5897c);
        }
        return false;
    }

    public final int hashCode() {
        return this.f5895a.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f5895a);
        String valueOf2 = String.valueOf(this.f5896b);
        String str = this.f5897c;
        int length = valueOf.length();
        StringBuilder sb = new StringBuilder(length + 77 + valueOf2.length() + String.valueOf(str).length());
        sb.append("DeviceOrientationRequestInternal{deviceOrientationRequest=");
        sb.append(valueOf);
        sb.append(", clients=");
        sb.append(valueOf2);
        sb.append(", tag='");
        sb.append(str);
        sb.append("'}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.f5895a, i2, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.f5896b, false);
        SafeParcelWriter.writeString(parcel, 3, this.f5897c, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
