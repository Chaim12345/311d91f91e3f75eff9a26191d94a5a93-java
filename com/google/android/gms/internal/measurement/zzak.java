package com.google.android.gms.internal.measurement;

import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzak implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Iterator f5926a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzak(Iterator it) {
        this.f5926a = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f5926a.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return new zzat((String) this.f5926a.next());
    }
}
