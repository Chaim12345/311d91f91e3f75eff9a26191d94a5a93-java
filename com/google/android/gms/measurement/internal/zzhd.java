package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import org.checkerframework.dataflow.qual.Pure;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class zzhd implements zzhf {

    /* renamed from: a  reason: collision with root package name */
    protected final zzgk f6809a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhd(zzgk zzgkVar) {
        Preconditions.checkNotNull(zzgkVar);
        this.f6809a = zzgkVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    @Pure
    public final Context zzau() {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    @Pure
    public final Clock zzav() {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    @Pure
    public final zzab zzaw() {
        throw null;
    }

    public void zzax() {
        this.f6809a.zzaz().zzax();
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    @Pure
    public final zzfa zzay() {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzhf
    @Pure
    public final zzgh zzaz() {
        throw null;
    }

    public void zzg() {
        this.f6809a.zzaz().zzg();
    }
}
