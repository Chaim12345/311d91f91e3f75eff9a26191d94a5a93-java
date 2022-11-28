package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes2.dex */
final class zzas {

    /* renamed from: a  reason: collision with root package name */
    final String f6698a;

    /* renamed from: b  reason: collision with root package name */
    final String f6699b;

    /* renamed from: c  reason: collision with root package name */
    final long f6700c;

    /* renamed from: d  reason: collision with root package name */
    final long f6701d;

    /* renamed from: e  reason: collision with root package name */
    final long f6702e;

    /* renamed from: f  reason: collision with root package name */
    final long f6703f;

    /* renamed from: g  reason: collision with root package name */
    final long f6704g;

    /* renamed from: h  reason: collision with root package name */
    final Long f6705h;

    /* renamed from: i  reason: collision with root package name */
    final Long f6706i;

    /* renamed from: j  reason: collision with root package name */
    final Long f6707j;

    /* renamed from: k  reason: collision with root package name */
    final Boolean f6708k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzas(String str, String str2, long j2, long j3, long j4, long j5, long j6, Long l2, Long l3, Long l4, Boolean bool) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkArgument(j2 >= 0);
        Preconditions.checkArgument(j3 >= 0);
        Preconditions.checkArgument(j4 >= 0);
        Preconditions.checkArgument(j6 >= 0);
        this.f6698a = str;
        this.f6699b = str2;
        this.f6700c = j2;
        this.f6701d = j3;
        this.f6702e = j4;
        this.f6703f = j5;
        this.f6704g = j6;
        this.f6705h = l2;
        this.f6706i = l3;
        this.f6707j = l4;
        this.f6708k = bool;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzas a(Long l2, Long l3, Boolean bool) {
        return new zzas(this.f6698a, this.f6699b, this.f6700c, this.f6701d, this.f6702e, this.f6703f, this.f6704g, this.f6705h, l2, l3, (bool == null || bool.booleanValue()) ? bool : null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzas b(long j2, long j3) {
        return new zzas(this.f6698a, this.f6699b, this.f6700c, this.f6701d, this.f6702e, this.f6703f, j2, Long.valueOf(j3), this.f6706i, this.f6707j, this.f6708k);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzas c(long j2) {
        return new zzas(this.f6698a, this.f6699b, this.f6700c, this.f6701d, this.f6702e, j2, this.f6704g, this.f6705h, this.f6706i, this.f6707j, this.f6708k);
    }
}
