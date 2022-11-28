package com.google.android.gms.internal.measurement;

import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes.dex */
final class zzih implements zzif {
    @CheckForNull

    /* renamed from: a  reason: collision with root package name */
    volatile zzif f6088a;

    /* renamed from: b  reason: collision with root package name */
    volatile boolean f6089b;
    @CheckForNull

    /* renamed from: c  reason: collision with root package name */
    Object f6090c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzih(zzif zzifVar) {
        Objects.requireNonNull(zzifVar);
        this.f6088a = zzifVar;
    }

    public final String toString() {
        Object obj = this.f6088a;
        StringBuilder sb = new StringBuilder();
        sb.append("Suppliers.memoize(");
        if (obj == null) {
            obj = "<supplier that returned " + this.f6090c + ">";
        }
        sb.append(obj);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzif
    public final Object zza() {
        if (!this.f6089b) {
            synchronized (this) {
                if (!this.f6089b) {
                    zzif zzifVar = this.f6088a;
                    zzifVar.getClass();
                    Object zza = zzifVar.zza();
                    this.f6090c = zza;
                    this.f6089b = true;
                    this.f6088a = null;
                    return zza;
                }
            }
        }
        return this.f6090c;
    }
}
