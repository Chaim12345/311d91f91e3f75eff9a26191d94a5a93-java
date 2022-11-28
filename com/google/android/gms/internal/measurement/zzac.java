package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes.dex */
final class zzac implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Iterator f5919a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Iterator f5920b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzac(zzae zzaeVar, Iterator it, Iterator it2) {
        this.f5919a = it;
        this.f5920b = it2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f5919a.hasNext()) {
            return true;
        }
        return this.f5920b.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        if (this.f5919a.hasNext()) {
            return new zzat(((Integer) this.f5919a.next()).toString());
        }
        if (this.f5920b.hasNext()) {
            return new zzat((String) this.f5920b.next());
        }
        throw new NoSuchElementException();
    }
}
