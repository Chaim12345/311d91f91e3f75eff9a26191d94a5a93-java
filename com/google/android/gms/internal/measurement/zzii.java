package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.CheckForNull;
/* loaded from: classes.dex */
final class zzii implements Serializable, zzif {

    /* renamed from: a  reason: collision with root package name */
    final Object f6091a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzii(Object obj) {
        this.f6091a = obj;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzii) {
            Object obj2 = this.f6091a;
            Object obj3 = ((zzii) obj).f6091a;
            return obj2 == obj3 || obj2.equals(obj3);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f6091a});
    }

    public final String toString() {
        return "Suppliers.ofInstance(" + this.f6091a + ")";
    }

    @Override // com.google.android.gms.internal.measurement.zzif
    public final Object zza() {
        return this.f6091a;
    }
}
