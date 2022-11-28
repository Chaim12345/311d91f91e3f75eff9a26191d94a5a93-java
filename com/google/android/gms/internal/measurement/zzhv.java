package com.google.android.gms.internal.measurement;

import android.net.Uri;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
public final class zzhv {

    /* renamed from: a  reason: collision with root package name */
    final String f6073a;

    /* renamed from: b  reason: collision with root package name */
    final Uri f6074b;

    /* renamed from: c  reason: collision with root package name */
    final String f6075c;

    /* renamed from: d  reason: collision with root package name */
    final String f6076d;

    /* renamed from: e  reason: collision with root package name */
    final boolean f6077e;

    /* renamed from: f  reason: collision with root package name */
    final boolean f6078f;

    /* renamed from: g  reason: collision with root package name */
    final boolean f6079g;

    /* renamed from: h  reason: collision with root package name */
    final boolean f6080h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    final zzic f6081i;

    public zzhv(Uri uri) {
        this(null, uri, "", "", false, false, false, false, null);
    }

    private zzhv(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, @Nullable zzic zzicVar) {
        this.f6073a = null;
        this.f6074b = uri;
        this.f6075c = "";
        this.f6076d = "";
        this.f6077e = z;
        this.f6078f = false;
        this.f6079g = z3;
        this.f6080h = false;
        this.f6081i = null;
    }

    public final zzhv zza() {
        return new zzhv(null, this.f6074b, this.f6075c, this.f6076d, this.f6077e, false, true, false, null);
    }

    public final zzhv zzb() {
        if (this.f6075c.isEmpty()) {
            return new zzhv(null, this.f6074b, this.f6075c, this.f6076d, true, false, this.f6079g, false, null);
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    public final zzhy zzc(String str, double d2) {
        return new zzht(this, "measurement.test.double_flag", Double.valueOf(-3.0d), true);
    }

    public final zzhy zzd(String str, long j2) {
        return new zzhr(this, str, Long.valueOf(j2), true);
    }

    public final zzhy zze(String str, String str2) {
        return new zzhu(this, str, str2, true);
    }

    public final zzhy zzf(String str, boolean z) {
        return new zzhs(this, str, Boolean.valueOf(z), true);
    }
}
