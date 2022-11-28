package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes.dex */
final class zzig implements Serializable, zzif {

    /* renamed from: a  reason: collision with root package name */
    final zzif f6085a;

    /* renamed from: b  reason: collision with root package name */
    volatile transient boolean f6086b;
    @CheckForNull

    /* renamed from: c  reason: collision with root package name */
    transient Object f6087c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzig(zzif zzifVar) {
        Objects.requireNonNull(zzifVar);
        this.f6085a = zzifVar;
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append("Suppliers.memoize(");
        if (this.f6086b) {
            obj = "<supplier that returned " + this.f6087c + ">";
        } else {
            obj = this.f6085a;
        }
        sb.append(obj);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zzif
    public final Object zza() {
        if (!this.f6086b) {
            synchronized (this) {
                if (!this.f6086b) {
                    Object zza = this.f6085a.zza();
                    this.f6087c = zza;
                    this.f6086b = true;
                    return zza;
                }
            }
        }
        return this.f6087c;
    }
}
