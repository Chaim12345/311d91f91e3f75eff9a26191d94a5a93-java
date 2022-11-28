package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
@ShowFirstParty
@SafeParcelable.Class(creator = "DeviceOrientationRequestCreator")
/* loaded from: classes2.dex */
public final class zzw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzw> CREATOR = new zzx();
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SHOULD_USE_MAG", id = 1)

    /* renamed from: a  reason: collision with root package name */
    boolean f6628a;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_MINIMUM_SAMPLING_PERIOD_MS", id = 2)

    /* renamed from: b  reason: collision with root package name */
    long f6629b;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SMALLEST_ANGLE_CHANGE_RADIANS", id = 3)

    /* renamed from: c  reason: collision with root package name */
    float f6630c;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_EXPIRE_AT_MS", id = 4)

    /* renamed from: d  reason: collision with root package name */
    long f6631d;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_NUM_UPDATES", id = 5)

    /* renamed from: e  reason: collision with root package name */
    int f6632e;

    public zzw() {
        this(true, 50L, 0.0f, LongCompanionObject.MAX_VALUE, Integer.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public zzw(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) float f2, @SafeParcelable.Param(id = 4) long j3, @SafeParcelable.Param(id = 5) int i2) {
        this.f6628a = z;
        this.f6629b = j2;
        this.f6630c = f2;
        this.f6631d = j3;
        this.f6632e = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzw) {
            zzw zzwVar = (zzw) obj;
            return this.f6628a == zzwVar.f6628a && this.f6629b == zzwVar.f6629b && Float.compare(this.f6630c, zzwVar.f6630c) == 0 && this.f6631d == zzwVar.f6631d && this.f6632e == zzwVar.f6632e;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.f6628a), Long.valueOf(this.f6629b), Float.valueOf(this.f6630c), Long.valueOf(this.f6631d), Integer.valueOf(this.f6632e));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DeviceOrientationRequest[mShouldUseMag=");
        sb.append(this.f6628a);
        sb.append(" mMinimumSamplingPeriodMs=");
        sb.append(this.f6629b);
        sb.append(" mSmallestAngleChangeRadians=");
        sb.append(this.f6630c);
        long j2 = this.f6631d;
        if (j2 != LongCompanionObject.MAX_VALUE) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(j2 - elapsedRealtime);
            sb.append("ms");
        }
        if (this.f6632e != Integer.MAX_VALUE) {
            sb.append(" num=");
            sb.append(this.f6632e);
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.f6628a);
        SafeParcelWriter.writeLong(parcel, 2, this.f6629b);
        SafeParcelWriter.writeFloat(parcel, 3, this.f6630c);
        SafeParcelWriter.writeLong(parcel, 4, this.f6631d);
        SafeParcelWriter.writeInt(parcel, 5, this.f6632e);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
