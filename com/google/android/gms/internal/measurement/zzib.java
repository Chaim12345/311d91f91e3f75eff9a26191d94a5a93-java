package com.google.android.gms.internal.measurement;

import javax.annotation.CheckForNull;
/* loaded from: classes.dex */
final class zzib extends zzid {

    /* renamed from: a  reason: collision with root package name */
    static final zzib f6084a = new zzib();

    private zzib() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.android.gms.internal.measurement.zzid
    public final Object zza() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.android.gms.internal.measurement.zzid
    public final boolean zzb() {
        return false;
    }
}
