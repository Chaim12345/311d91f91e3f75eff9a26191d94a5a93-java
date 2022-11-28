package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@KeepForSdk
@SafeParcelable.Class(creator = "GetServiceRequestCreator")
@SafeParcelable.Reserved({9})
/* loaded from: classes.dex */
public class GetServiceRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzm();
    @SafeParcelable.VersionField(id = 1)

    /* renamed from: a  reason: collision with root package name */
    final int f5749a;
    @SafeParcelable.Field(id = 2)

    /* renamed from: b  reason: collision with root package name */
    final int f5750b;
    @SafeParcelable.Field(id = 3)

    /* renamed from: c  reason: collision with root package name */
    int f5751c;
    @SafeParcelable.Field(id = 4)

    /* renamed from: d  reason: collision with root package name */
    String f5752d;
    @SafeParcelable.Field(id = 5)

    /* renamed from: e  reason: collision with root package name */
    IBinder f5753e;
    @SafeParcelable.Field(id = 6)

    /* renamed from: f  reason: collision with root package name */
    Scope[] f5754f;
    @SafeParcelable.Field(id = 7)

    /* renamed from: g  reason: collision with root package name */
    Bundle f5755g;
    @Nullable
    @SafeParcelable.Field(id = 8)

    /* renamed from: h  reason: collision with root package name */
    Account f5756h;
    @SafeParcelable.Field(id = 10)

    /* renamed from: i  reason: collision with root package name */
    Feature[] f5757i;
    @SafeParcelable.Field(id = 11)

    /* renamed from: j  reason: collision with root package name */
    Feature[] f5758j;
    @SafeParcelable.Field(id = 12)

    /* renamed from: k  reason: collision with root package name */
    boolean f5759k;
    @SafeParcelable.Field(defaultValue = "0", id = 13)

    /* renamed from: l  reason: collision with root package name */
    int f5760l;
    @SafeParcelable.Field(getter = "isRequestingTelemetryConfiguration", id = 14)

    /* renamed from: m  reason: collision with root package name */
    boolean f5761m;
    @Nullable
    @SafeParcelable.Field(getter = "getAttributionTag", id = 15)
    private String zzn;

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeParcelable.Constructor
    public GetServiceRequest(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) int i4, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) IBinder iBinder, @SafeParcelable.Param(id = 6) Scope[] scopeArr, @SafeParcelable.Param(id = 7) Bundle bundle, @SafeParcelable.Param(id = 8) Account account, @SafeParcelable.Param(id = 10) Feature[] featureArr, @SafeParcelable.Param(id = 11) Feature[] featureArr2, @SafeParcelable.Param(id = 12) boolean z, @SafeParcelable.Param(id = 13) int i5, @SafeParcelable.Param(id = 14) boolean z2, @Nullable @SafeParcelable.Param(id = 15) String str2) {
        this.f5749a = i2;
        this.f5750b = i3;
        this.f5751c = i4;
        if ("com.google.android.gms".equals(str)) {
            this.f5752d = "com.google.android.gms";
        } else {
            this.f5752d = str;
        }
        if (i2 < 2) {
            this.f5756h = iBinder != null ? AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder)) : null;
        } else {
            this.f5753e = iBinder;
            this.f5756h = account;
        }
        this.f5754f = scopeArr;
        this.f5755g = bundle;
        this.f5757i = featureArr;
        this.f5758j = featureArr2;
        this.f5759k = z;
        this.f5760l = i5;
        this.f5761m = z2;
        this.zzn = str2;
    }

    public GetServiceRequest(int i2, @Nullable String str) {
        this.f5749a = 6;
        this.f5751c = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.f5750b = i2;
        this.f5759k = true;
        this.zzn = str;
    }

    @NonNull
    @KeepForSdk
    public Bundle getExtraArgs() {
        return this.f5755g;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i2) {
        zzm.a(this, parcel, i2);
    }

    @Nullable
    public final String zza() {
        return this.zzn;
    }
}
