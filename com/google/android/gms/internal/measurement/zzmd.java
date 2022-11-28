package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
final class zzmd implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzmh f6104a;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzmd(zzmh zzmhVar, zzmc zzmcVar) {
        this.f6104a = zzmhVar;
    }

    private final Iterator zza() {
        Map map;
        if (this.zzd == null) {
            map = this.f6104a.zzc;
            this.zzd = map.entrySet().iterator();
        }
        return this.zzd;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        Map map;
        int i2 = this.zzb + 1;
        list = this.f6104a.zzb;
        if (i2 >= list.size()) {
            map = this.f6104a.zzc;
            return !map.isEmpty() && zza().hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        List list;
        Object next;
        List list2;
        this.zzc = true;
        int i2 = this.zzb + 1;
        this.zzb = i2;
        list = this.f6104a.zzb;
        if (i2 < list.size()) {
            list2 = this.f6104a.zzb;
            next = list2.get(this.zzb);
        } else {
            next = zza().next();
        }
        return (Map.Entry) next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        List list;
        if (!this.zzc) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzc = false;
        this.f6104a.zzn();
        int i2 = this.zzb;
        list = this.f6104a.zzb;
        if (i2 >= list.size()) {
            zza().remove();
            return;
        }
        zzmh zzmhVar = this.f6104a;
        int i3 = this.zzb;
        this.zzb = i3 - 1;
        zzmhVar.zzl(i3);
    }
}
