package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;
@SafeParcelable.Class(creator = "LocationRequestInternalCreator")
@SafeParcelable.Reserved({2, 3, 4, 1000})
/* loaded from: classes.dex */
public final class zzbf extends AbstractSafeParcelable {
    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 1)

    /* renamed from: a  reason: collision with root package name */
    final LocationRequest f5873a;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_CLIENTS", id = 5)

    /* renamed from: b  reason: collision with root package name */
    final List f5874b;
    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 6)

    /* renamed from: c  reason: collision with root package name */
    final String f5875c;
    @SafeParcelable.Field(defaultValue = "false", id = 7)

    /* renamed from: d  reason: collision with root package name */
    final boolean f5876d;
    @SafeParcelable.Field(defaultValue = "false", id = 8)

    /* renamed from: e  reason: collision with root package name */
    final boolean f5877e;
    @SafeParcelable.Field(defaultValue = "false", id = 9)

    /* renamed from: f  reason: collision with root package name */
    final boolean f5878f;
    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 10)

    /* renamed from: g  reason: collision with root package name */
    final String f5879g;
    @SafeParcelable.Field(defaultValue = "false", id = 11)

    /* renamed from: h  reason: collision with root package name */
    final boolean f5880h;
    @SafeParcelable.Field(defaultValue = "false", id = 12)

    /* renamed from: i  reason: collision with root package name */
    boolean f5881i;
    @Nullable
    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 13)

    /* renamed from: j  reason: collision with root package name */
    final String f5882j;
    @SafeParcelable.Field(defaultValueUnchecked = "Long.MAX_VALUE", id = 14)

    /* renamed from: k  reason: collision with root package name */
    long f5883k;

    /* renamed from: l  reason: collision with root package name */
    static final List f5872l = Collections.emptyList();
    public static final Parcelable.Creator<zzbf> CREATOR = new zzbg();

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public zzbf(@SafeParcelable.Param(id = 1) LocationRequest locationRequest, @SafeParcelable.Param(id = 5) List list, @Nullable @SafeParcelable.Param(id = 6) String str, @SafeParcelable.Param(id = 7) boolean z, @SafeParcelable.Param(id = 8) boolean z2, @SafeParcelable.Param(id = 9) boolean z3, @Nullable @SafeParcelable.Param(id = 10) String str2, @SafeParcelable.Param(id = 11) boolean z4, @SafeParcelable.Param(id = 12) boolean z5, @Nullable @SafeParcelable.Param(id = 13) String str3, @SafeParcelable.Param(id = 14) long j2) {
        this.f5873a = locationRequest;
        this.f5874b = list;
        this.f5875c = str;
        this.f5876d = z;
        this.f5877e = z2;
        this.f5878f = z3;
        this.f5879g = str2;
        this.f5880h = z4;
        this.f5881i = z5;
        this.f5882j = str3;
        this.f5883k = j2;
    }

    public static zzbf zzc(@Nullable String str, LocationRequest locationRequest) {
        return new zzbf(locationRequest, zzbx.zzk(), null, false, false, false, null, false, false, null, LongCompanionObject.MAX_VALUE);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof zzbf) {
            zzbf zzbfVar = (zzbf) obj;
            if (Objects.equal(this.f5873a, zzbfVar.f5873a) && Objects.equal(this.f5874b, zzbfVar.f5874b) && Objects.equal(this.f5875c, zzbfVar.f5875c) && this.f5876d == zzbfVar.f5876d && this.f5877e == zzbfVar.f5877e && this.f5878f == zzbfVar.f5878f && Objects.equal(this.f5879g, zzbfVar.f5879g) && this.f5880h == zzbfVar.f5880h && this.f5881i == zzbfVar.f5881i && Objects.equal(this.f5882j, zzbfVar.f5882j)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f5873a.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5873a);
        if (this.f5875c != null) {
            sb.append(" tag=");
            sb.append(this.f5875c);
        }
        if (this.f5879g != null) {
            sb.append(" moduleId=");
            sb.append(this.f5879g);
        }
        if (this.f5882j != null) {
            sb.append(" contextAttributionTag=");
            sb.append(this.f5882j);
        }
        sb.append(" hideAppOps=");
        sb.append(this.f5876d);
        sb.append(" clients=");
        sb.append(this.f5874b);
        sb.append(" forceCoarseLocation=");
        sb.append(this.f5877e);
        if (this.f5878f) {
            sb.append(" exemptFromBackgroundThrottle");
        }
        if (this.f5880h) {
            sb.append(" locationSettingsIgnored");
        }
        if (this.f5881i) {
            sb.append(" inaccurateLocationsDelayed");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.f5873a, i2, false);
        SafeParcelWriter.writeTypedList(parcel, 5, this.f5874b, false);
        SafeParcelWriter.writeString(parcel, 6, this.f5875c, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.f5876d);
        SafeParcelWriter.writeBoolean(parcel, 8, this.f5877e);
        SafeParcelWriter.writeBoolean(parcel, 9, this.f5878f);
        SafeParcelWriter.writeString(parcel, 10, this.f5879g, false);
        SafeParcelWriter.writeBoolean(parcel, 11, this.f5880h);
        SafeParcelWriter.writeBoolean(parcel, 12, this.f5881i);
        SafeParcelWriter.writeString(parcel, 13, this.f5882j, false);
        SafeParcelWriter.writeLong(parcel, 14, this.f5883k);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final long zza() {
        return this.f5883k;
    }

    public final LocationRequest zzb() {
        return this.f5873a;
    }

    @Deprecated
    public final zzbf zzd(boolean z) {
        this.f5881i = true;
        return this;
    }

    public final zzbf zze(long j2) {
        if (this.f5873a.getMaxWaitTime() <= this.f5873a.getInterval()) {
            this.f5883k = j2;
            return this;
        }
        long interval = this.f5873a.getInterval();
        long maxWaitTime = this.f5873a.getMaxWaitTime();
        StringBuilder sb = new StringBuilder(120);
        sb.append("could not set max age when location batching is requested, interval=");
        sb.append(interval);
        sb.append("maxWaitTime=");
        sb.append(maxWaitTime);
        throw new IllegalArgumentException(sb.toString());
    }

    public final List zzf() {
        return this.f5874b;
    }

    public final boolean zzg() {
        return this.f5880h;
    }
}
