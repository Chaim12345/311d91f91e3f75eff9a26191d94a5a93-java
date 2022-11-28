package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.Iterator;
/* loaded from: classes2.dex */
final class zzat implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final Iterator f6709a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzau f6710b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzat(zzau zzauVar) {
        Bundle bundle;
        this.f6710b = zzauVar;
        bundle = zzauVar.zza;
        this.f6709a = bundle.keySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f6709a.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    @Override // java.util.Iterator
    /* renamed from: zza */
    public final String next() {
        return (String) this.f6709a.next();
    }
}
