package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
@SafeParcelable.Class(creator = "LocationRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes2.dex */
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<LocationRequest> CREATOR = new zzbo();
    @Deprecated
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    @Deprecated
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    @Deprecated
    public static final int PRIORITY_LOW_POWER = 104;
    @Deprecated
    public static final int PRIORITY_NO_POWER = 105;
    @SafeParcelable.Field(defaultValueUnchecked = "Priority.PRIORITY_BALANCED_POWER_ACCURACY", id = 1)

    /* renamed from: a  reason: collision with root package name */
    int f6611a;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequest.DEFAULT_INTERVAL", id = 2)

    /* renamed from: b  reason: collision with root package name */
    long f6612b;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequest.DEFAULT_FASTEST_INTERVAL", id = 3)

    /* renamed from: c  reason: collision with root package name */
    long f6613c;
    @SafeParcelable.Field(defaultValue = "false", id = 4)

    /* renamed from: d  reason: collision with root package name */
    boolean f6614d;
    @SafeParcelable.Field(defaultValueUnchecked = "Long.MAX_VALUE", id = 5)

    /* renamed from: e  reason: collision with root package name */
    long f6615e;
    @SafeParcelable.Field(defaultValueUnchecked = "Integer.MAX_VALUE", id = 6)

    /* renamed from: f  reason: collision with root package name */
    int f6616f;
    @SafeParcelable.Field(defaultValue = "0", id = 7)

    /* renamed from: g  reason: collision with root package name */
    float f6617g;
    @SafeParcelable.Field(defaultValue = "0", id = 8)

    /* renamed from: h  reason: collision with root package name */
    long f6618h;
    @SafeParcelable.Field(defaultValue = "false", id = 9)

    /* renamed from: i  reason: collision with root package name */
    boolean f6619i;

    @Deprecated
    public LocationRequest() {
        this(102, 3600000L, 600000L, false, LongCompanionObject.MAX_VALUE, Integer.MAX_VALUE, 0.0f, 0L, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public LocationRequest(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) long j3, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) long j4, @SafeParcelable.Param(id = 6) int i3, @SafeParcelable.Param(id = 7) float f2, @SafeParcelable.Param(id = 8) long j5, @SafeParcelable.Param(id = 9) boolean z2) {
        this.f6611a = i2;
        this.f6612b = j2;
        this.f6613c = j3;
        this.f6614d = z;
        this.f6615e = j4;
        this.f6616f = i3;
        this.f6617g = f2;
        this.f6618h = j5;
        this.f6619i = z2;
    }

    @NonNull
    public static LocationRequest create() {
        return new LocationRequest(102, 3600000L, 600000L, false, LongCompanionObject.MAX_VALUE, Integer.MAX_VALUE, 0.0f, 0L, true);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof LocationRequest) {
            LocationRequest locationRequest = (LocationRequest) obj;
            if (this.f6611a == locationRequest.f6611a && this.f6612b == locationRequest.f6612b && this.f6613c == locationRequest.f6613c && this.f6614d == locationRequest.f6614d && this.f6615e == locationRequest.f6615e && this.f6616f == locationRequest.f6616f && this.f6617g == locationRequest.f6617g && getMaxWaitTime() == locationRequest.getMaxWaitTime() && this.f6619i == locationRequest.f6619i) {
                return true;
            }
        }
        return false;
    }

    public long getExpirationTime() {
        return this.f6615e;
    }

    public long getFastestInterval() {
        return this.f6613c;
    }

    public long getInterval() {
        return this.f6612b;
    }

    public long getMaxWaitTime() {
        long j2 = this.f6618h;
        long j3 = this.f6612b;
        return j2 < j3 ? j3 : j2;
    }

    public int getNumUpdates() {
        return this.f6616f;
    }

    public int getPriority() {
        return this.f6611a;
    }

    public float getSmallestDisplacement() {
        return this.f6617g;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.f6611a), Long.valueOf(this.f6612b), Float.valueOf(this.f6617g), Long.valueOf(this.f6618h));
    }

    public boolean isFastestIntervalExplicitlySet() {
        return this.f6614d;
    }

    public boolean isWaitForAccurateLocation() {
        return this.f6619i;
    }

    @NonNull
    public LocationRequest setExpirationDuration(long j2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j3 = LongCompanionObject.MAX_VALUE;
        if (j2 <= LongCompanionObject.MAX_VALUE - elapsedRealtime) {
            j3 = j2 + elapsedRealtime;
        }
        this.f6615e = j3;
        if (j3 < 0) {
            this.f6615e = 0L;
        }
        return this;
    }

    @NonNull
    @Deprecated
    public LocationRequest setExpirationTime(long j2) {
        this.f6615e = j2;
        if (j2 < 0) {
            this.f6615e = 0L;
        }
        return this;
    }

    @NonNull
    public LocationRequest setFastestInterval(long j2) {
        Preconditions.checkArgument(j2 >= 0, "illegal fastest interval: %d", Long.valueOf(j2));
        this.f6614d = true;
        this.f6613c = j2;
        return this;
    }

    @NonNull
    public LocationRequest setInterval(long j2) {
        Preconditions.checkArgument(j2 >= 0, "illegal interval: %d", Long.valueOf(j2));
        this.f6612b = j2;
        if (!this.f6614d) {
            this.f6613c = (long) (j2 / 6.0d);
        }
        return this;
    }

    @NonNull
    public LocationRequest setMaxWaitTime(long j2) {
        Preconditions.checkArgument(j2 >= 0, "illegal max wait time: %d", Long.valueOf(j2));
        this.f6618h = j2;
        return this;
    }

    @NonNull
    public LocationRequest setNumUpdates(int i2) {
        if (i2 > 0) {
            this.f6616f = i2;
            return this;
        }
        StringBuilder sb = new StringBuilder(31);
        sb.append("invalid numUpdates: ");
        sb.append(i2);
        throw new IllegalArgumentException(sb.toString());
    }

    @NonNull
    public LocationRequest setPriority(int i2) {
        boolean z;
        if (i2 != 100 && i2 != 102 && i2 != 104) {
            if (i2 != 105) {
                z = false;
                Preconditions.checkArgument(z, "illegal priority: %d", Integer.valueOf(i2));
                this.f6611a = i2;
                return this;
            }
            i2 = 105;
        }
        z = true;
        Preconditions.checkArgument(z, "illegal priority: %d", Integer.valueOf(i2));
        this.f6611a = i2;
        return this;
    }

    @NonNull
    public LocationRequest setSmallestDisplacement(float f2) {
        if (f2 >= 0.0f) {
            this.f6617g = f2;
            return this;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("invalid displacement: ");
        sb.append(f2);
        throw new IllegalArgumentException(sb.toString());
    }

    @NonNull
    public LocationRequest setWaitForAccurateLocation(boolean z) {
        this.f6619i = z;
        return this;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request[");
        int i2 = this.f6611a;
        sb.append(i2 != 100 ? i2 != 102 ? i2 != 104 ? i2 != 105 ? "???" : "PRIORITY_NO_POWER" : "PRIORITY_LOW_POWER" : "PRIORITY_BALANCED_POWER_ACCURACY" : "PRIORITY_HIGH_ACCURACY");
        if (this.f6611a != 105) {
            sb.append(" requested=");
            sb.append(this.f6612b);
            sb.append("ms");
        }
        sb.append(" fastest=");
        sb.append(this.f6613c);
        sb.append("ms");
        if (this.f6618h > this.f6612b) {
            sb.append(" maxWait=");
            sb.append(this.f6618h);
            sb.append("ms");
        }
        if (this.f6617g > 0.0f) {
            sb.append(" smallestDisplacement=");
            sb.append(this.f6617g);
            sb.append("m");
        }
        long j2 = this.f6615e;
        if (j2 != LongCompanionObject.MAX_VALUE) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(j2 - elapsedRealtime);
            sb.append("ms");
        }
        if (this.f6616f != Integer.MAX_VALUE) {
            sb.append(" num=");
            sb.append(this.f6616f);
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.f6611a);
        SafeParcelWriter.writeLong(parcel, 2, this.f6612b);
        SafeParcelWriter.writeLong(parcel, 3, this.f6613c);
        SafeParcelWriter.writeBoolean(parcel, 4, this.f6614d);
        SafeParcelWriter.writeLong(parcel, 5, this.f6615e);
        SafeParcelWriter.writeInt(parcel, 6, this.f6616f);
        SafeParcelWriter.writeFloat(parcel, 7, this.f6617g);
        SafeParcelWriter.writeLong(parcel, 8, this.f6618h);
        SafeParcelWriter.writeBoolean(parcel, 9, this.f6619i);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
